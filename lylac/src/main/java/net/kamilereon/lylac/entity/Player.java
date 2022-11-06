package net.kamilereon.lylac.entity;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.bukkit.Bukkit;
import org.bukkit.inventory.ItemStack;
import org.bukkit.persistence.PersistentDataType;
import org.bukkit.scheduler.BukkitTask;

import net.kamilereon.lylac.Lylac;
import net.kamilereon.lylac.LylacUtils;
import net.kamilereon.lylac.element.ElementDamage;
import net.kamilereon.lylac.element.ElementDamageRange;
import net.kamilereon.lylac.event.Cause.HealthMutateCause;
import net.kamilereon.lylac.event.Cause.ManaMutateCause;
import net.kamilereon.lylac.event.player.LylacPlayerDamageByEntityEvent;
import net.kamilereon.lylac.event.player.LylacPlayerEquipSpellEvent;
import net.kamilereon.lylac.event.player.LylacPlayerHealthMutateEvent;
import net.kamilereon.lylac.event.player.LylacPlayerManaMutateEvent;
import net.kamilereon.lylac.item.artifact.ArtifactInventory;
import net.kamilereon.lylac.item.artifact.ArtifactInventory.ArtifactType;
import net.kamilereon.lylac.particle.LylacParticle;
import net.kamilereon.lylac.quest.LylacQuest.LylacQuestList;
import net.kamilereon.lylac.item.ItemUtil;
import net.kamilereon.lylac.item.LylacItem;
import net.kamilereon.lylac.permission.LylacPlayerPermission;
import net.kamilereon.lylac.permission.LylacPlayerPermission.LylacPlayerPermissionType;
import net.kamilereon.lylac.spell.CastingCommand;
import net.kamilereon.lylac.spell.Spell;
import net.kamilereon.lylac.spell.SpellInventory;
import net.kamilereon.lylac.spell.SpellTechInventory;
import net.kamilereon.lylac.stat.LylacPlayerStatContainer;
import net.kamilereon.lylac.stat.LylacPlayerStatContainer.LylacPlayerStats;
import net.kamilereon.lylac.spell.CastingCommand.CastingCommandCode;

/**
 * 라일락의 플레이어 구현 클래스
 * 
 * @author kamilereon
 * @version 1.0.0
 */
public class Player extends Entity implements IPlayer, Damageable, ManaControllable {

    private final org.bukkit.entity.Player bukkitEntity;
    private String characterUUID = null;

    /**
     * 아티팩트 인벤토리
     * @see ArtifactInventory
     */
    private final ArtifactInventory artifactInventory = new ArtifactInventory(this);

    /**
     * 스펠테크 인벤토리
     * @see SpellTechInventory
     */
    private final SpellTechInventory spellTechInventory = new SpellTechInventory(this);

    /**
     * 스펠 인벤토리. 플레이어가 지정한 스펠 목록을 저장하고 있는 객체
     * <p>{@link CastingCommand}를 통하여 {@link SpellInventory}에 저장된 {@link Spell}들을 실행</p>
     * @see SpellInventory
     * @see CastingCommand
     */
    private final SpellInventory spellInventory = new SpellInventory(this);

    private final CastingCommand castingCommand = new CastingCommand(this);

    private final LylacPlayerPermission permission = new LylacPlayerPermission();

    private final LylacParticle particle = new LylacParticle(this);

    private final LylacPlayerStatContainer stat = new LylacPlayerStatContainer(this);

    private BukkitTask bukkitTaskEveryTick;
    private BukkitTask bukkitTaskEvery5Ticks;
    private BukkitTask bukkitTaskEvery10Ticks;
    private BukkitTask bukkitTaskEverySecond;
    
    public Player(org.bukkit.entity.Player bukkitEntity) {
        super();
        this.bukkitEntity = bukkitEntity;
        this.init();
    }

    public void update() {

    }

    @Override
    public void init() {

        // 플레이어 데이터 불러오기
        loadData();

        // 플레이어 초기 설정
        this.bukkitEntity.setMaximumNoDamageTicks(0);

        // 초기 능력치 계산 및 적용
        

        this.bukkitTaskEveryTick = Bukkit.getScheduler().runTask(Lylac.lylacPlugin, () -> {
            update();
            // 플레이어 액션바 전시 스케줄러`
            // 보스바 전시 스케줄러
        });
        this.bukkitTaskEverySecond = LylacUtils.Scheduler.executeContinuallyEveryTick(() -> {
            // 마나 초당 회복 스케쥴러
            // 체력 초당 회복 스케줄러
        }, 20);
    }

    @Override
    public void fin() {
        // 버킷 스케쥴러 종료
        this.bukkitTaskEveryTick.cancel();
        this.bukkitTaskEvery5Ticks.cancel();
        this.bukkitTaskEvery10Ticks.cancel();
        this.bukkitTaskEverySecond.cancel();
    }
    
    @Override
    public <T2 extends Entity & Damageable> void attack(T2 to) {
        to.attackedBy(currentElementDamage.pickRandomElementDamage(), this);
    }

    @Override
    public void kill() {
        // 사망이벤트 발생시키기
        // 사망시 실행되는 로직들...
        /**
         * 
         * 
         */
        // 사망시 즉시 리스폰
    }

    @Override
    public <T2 extends Entity> void attackedBy(ElementDamage eDamage, T2 by) {
        // 총 피해량 계산 후 체력에 적용 및 이벤트 발생
        LylacPlayerDamageByEntityEvent<T2> event = new LylacPlayerDamageByEntityEvent<T2>(this, by, eDamage);
        LylacUtils.Event.callEvent(event);

        if(event.isCancelled()) return; // 이벤트가 캔슬되었을 경우 mutateHealth 메서드 호출 막음

        int totalDamage = event.getElementDamage().getTotalDamage();
        this.mutateHealth(-totalDamage, HealthMutateCause.SPELL, by);
        this.bukkitEntity.damage(0);

        // 아머스탠드를 활용한 데미지 인디케이터 소환

    }

    @Override
    public <T2 extends Entity> void mutateHealth(int mutateValue, HealthMutateCause cause, T2 by) {
        LylacPlayerHealthMutateEvent<T2> event = new LylacPlayerHealthMutateEvent<>(this, by, mutateValue, cause);
        LylacUtils.Event.callEvent(event);
        int nextHealth = stat.health + mutateValue;
        if(nextHealth > stat.maxHealth) stat.health = stat.maxHealth;
        else if(nextHealth <= stat.maxHealth && nextHealth > 0) stat.health = nextHealth;
        else {
            this.kill();
        }
        // 버킷 플레이어에 체력 반영시키기
    }
    
    @Override
    public void setHealth(int health) {
        stat.health = health;
    }

    @Override
    public int getMaxHealth() {
        return stat.maxHealth;
    }

    @Override
    public int getHealth() {
        return stat.health;
    }

    @Override
    public <T2 extends Entity> void mutateMana(int mutateValue, ManaMutateCause cause, T2 by) {

        LylacPlayerManaMutateEvent<T2> event = new LylacPlayerManaMutateEvent<>(this, by, mutateValue, cause);
        LylacUtils.Event.callEvent(event);

        if(event.isCancelled()) return;

        int nextMana = stat.mana + mutateValue;
        if(nextMana > stat.maxMana) stat.mana = stat.maxMana;
        else if(nextMana <= stat.maxMana && nextMana >= 0) stat.mana = nextMana;
        else stat.mana = 0 ;
    }

    @Override
    public int setMana(int mana) {
        return stat.mana = mana;
    }

    @Override
    public int getMana() {
        return stat.mana;
    }

    @Override
    public org.bukkit.entity.Player getBukkitEntity() {
        return this.bukkitEntity;
    }

    @Override
    public ArtifactInventory getArtifactInventory() { return this.artifactInventory; }

    @Override
    public SpellInventory getSpellInventory() {
        return this.spellInventory;
    }

    @Override
    public SpellTechInventory getSpellTechInventory() {
        return this.spellTechInventory;
    }

    @Override
    public LylacPlayerPermission getPermission() { return this.permission; }

    @Override
    public void computeAllArtifactStatsAndUpdate() {

        Map<LylacItem.GeneratedItemModelField, Integer> combinedStats = artifactInventory.combineAllArtifactStats();

        combinedStats.forEach((_K, _V) -> {
            String K = _K.name();
            // 기본 스탯과 계산된 스탯 합산!
            int V = _V + LylacPlayerStatContainer.LylacPlayerStats.valueOf(K).getDefaultValue();
            stat.setStat(LylacPlayerStats.valueOf(K), V);
        });
    }

    @Override
    public void computeElementDamageRangeAndUpdate() {
        ItemStack sceptor = artifactInventory.getArtifact(ArtifactType.SCEPTOR);
        // 만약 현재 가지고 있는 셉터가 없다면 객체 초기화
        if(sceptor == null) {
            this.currentElementDamage = ElementDamageRange.getElementDamageRange();
            return;
        }
        // 가지고 있다면 객체 복사 후
        // 능력치에 따라 속성 데미지 계산!
        ElementDamageRange sceptorElementDamage = ElementDamageRange.parse(ItemUtil.getValueFromPersistentDataContainer(
            sceptor.getItemMeta(), "damage", PersistentDataType.STRING));

        double computedSpellAmp = LylacPlayerStatContainer.StatUtil.getValueToRate(stat.spellAmplificationRate);

        sceptorElementDamage.getNeutral().multiply(computedSpellAmp);

        this.currentElementDamage = sceptorElementDamage;
    }

    @Override
    public void startQuest(LylacQuestList quest) {
        if(!permission.checkPermission(this, LylacPlayerPermissionType.LYLAC_QUEST_START, "퀘스트를 시작할 수 없습니다")) return;
    }

    @Override
    public void progressQuest(LylacQuestList quest) {
        if(!permission.checkPermission(this, LylacPlayerPermissionType.LYLAC_QUEST_NEXT_STAGE, "퀘스트를 진행 할 권한이 없습니다")) return;
        
    }

    @Override
    public void completeQuest(LylacQuestList quest) {
        if(!permission.checkPermission(this, LylacPlayerPermissionType.LYLAC_QUEST_COMPLETE, "퀘스트를 완료할 권한이 없습니다.")) return;
    }

    @Override
    public SpellInventory equipSpell(int position, Spell spell) {
        LylacPlayerEquipSpellEvent event = new LylacPlayerEquipSpellEvent(this, position, spell);
        LylacUtils.Event.callEvent(event);
        if(event.isCancelled()) return this.spellInventory;
        this.spellInventory.setSpell(position, spell);
        return this.spellInventory;
    }

    @Override
    public void castSpell(int spellInventoryPosition) {
        Spell spell = this.spellInventory.getSpell(spellInventoryPosition);
        if(spell == null) {
            // 할당 된 스펠 없음
            return;
        }
        int computedRequireMana = spell.getComputedRequireMana();
        if(stat.mana > computedRequireMana) {
            // 마나 부족
            return;
        }
        this.mutateMana(-computedRequireMana, ManaMutateCause.USE, this);
        spell.cast();
    }

    @Override
    public void castingSpellCommand(CastingCommandCode castingCommand) {
        this.castingCommand.attachCastingCommandCode(castingCommand);
    }

    @Override
    public void loadData() {
        // TODO Auto-generated method stub
    }

    @Override
    public void saveData() {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void loadCharacterData(String uuid) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void enableCharacterSwitchMode() {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void createCharacter() {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void removeCharacter(String uuid) {
        // TODO Auto-generated method stub
        
    }
}
