package net.kamilereon.lylac.entity;

import org.bukkit.Bukkit;
import org.bukkit.entity.LivingEntity;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.scheduler.BukkitTask;

import net.kamilereon.lylac.Lylac;
import net.kamilereon.lylac.Utils;
import net.kamilereon.lylac.element.ElementDamage;
import net.kamilereon.lylac.element.ElementDamageRange;
import net.kamilereon.lylac.event.Cause.HealthMutateCause;
import net.kamilereon.lylac.event.player.LylacPlayerDamageByEntityEvent;
import net.kamilereon.lylac.event.player.LylacPlayerHealthMutateEvent;
import net.kamilereon.lylac.item.artifact.ArtifactInventory;
import net.kamilereon.lylac.item.artifact.Sceptor;
import net.kamilereon.lylac.item.artifact.Artifact.ArtifactType;
import net.kamilereon.lylac.spell.CastingCommand;
import net.kamilereon.lylac.spell.Spell;
import net.kamilereon.lylac.spell.SpellInventory;
import net.kamilereon.lylac.spell.SpellTechInventory;

/**
 * 라일락의 플레이어 구현 클래스
 * 
 * @author kamilereon
 * @version 1.0.0
 */
public class Player extends Entity implements Damageable {

    private final org.bukkit.entity.Player bukkitEntity;
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

    protected int maxHealth;
    protected int health = maxHealth;
    protected int maxHealthIncRate = RATE_DEFAULT;
    protected int healthRegenRate = RATE_DEFAULT;
    
    protected int mana = 0;
    protected int maxMana;
    protected int maxManaIncRate = RATE_DEFAULT;
    protected int manaRegenRate = RATE_DEFAULT;
    protected int manaConsumptionRate = RATE_DEFAULT;
    
    protected int spellCastingSpeed = RATE_DEFAULT;
    protected int speedWhileSpellCasting = RATE_DEFAULT;
    
    protected int spellAmplificationRate = RATE_DEFAULT;
    protected int waterAmplificationRate = RATE_DEFAULT;
    protected int fireAmplificationRate = RATE_DEFAULT;
    protected int airAmplificationRate = RATE_DEFAULT;
    protected int earthAmplificationRate = RATE_DEFAULT;
    
    protected int spellResistance = RATE_DEFAULT;
    protected int meleeResistance = RATE_DEFAULT;
    protected int waterResistance = RATE_DEFAULT;
    protected int fireResistance = RATE_DEFAULT;
    protected int airResistance = RATE_DEFAULT;
    protected int earthResistacne = RATE_DEFAULT;
    
    private BukkitTask bukkitTaskEveryTick;
    private BukkitTask bukkitTaskEvery5Ticks;
    private BukkitTask bukkitTaskEvery10Ticks;
    private BukkitTask bukkitTaskEverySecond;
    
    public Player(org.bukkit.entity.Player bukkitEntity) {
        super();
        this.bukkitEntity = bukkitEntity;
        this.init();
    }

    /**
     * 플레이어가 착용한 아티팩트가 바뀌면 자동으로 호출되는 메서드
     * 
     * @see ArtifactInventory#computeAllArtifactStatsAndUpdateToInventoryHoldersStat()
     */
    public void callWhenArtifactChanges() {
        artifactInventory.computeAllArtifactStatsAndUpdateToInventoryHoldersStat();
        callWhenElementDamageShouldChange();
    }

    /**
     * 아티팩트 또는 관련 장비의 변화로 인해 능력치가 바뀐 경우 실행되는 메서드
     * <p>메서드가 실행되면 해당 능력치를 기준으로 {@link Player#currentElementDamage}를 재계산 함</p>
     */
    public void callWhenElementDamageShouldChange() {

        Sceptor sceptor = (Sceptor) artifactInventory.getArtifact(ArtifactType.SCEPTOR);
        // 만약 현재 가지고 있는 셉터가 없다면 객체 초기화
        if(sceptor == null) {
            this.currentElementDamage = ElementDamageRange.getElementDamageRange();
            return;
        }
        // 가지고 있다면 객체 복사 후
        // 능력치에 따라 속성 데미지 계산!
        ElementDamageRange sceptorElementDamage = sceptor.getElementDamage().clone();

        double computedSpellAmp = Util.getValueToRate(spellAmplificationRate);
        double computedEarthAmp = Util.getValueToRate(earthAmplificationRate);
        double computedWaterAmp = Util.getValueToRate(waterAmplificationRate);
        double computedFireAmp = Util.getValueToRate(fireAmplificationRate);
        double computedAirAmp = Util.getValueToRate(airAmplificationRate);

        sceptorElementDamage.getEarth().multiply(computedSpellAmp + computedEarthAmp);
        sceptorElementDamage.getWater().multiply(computedSpellAmp + computedWaterAmp);
        sceptorElementDamage.getFire().multiply(computedSpellAmp + computedFireAmp);
        sceptorElementDamage.getAir().multiply(computedSpellAmp + computedAirAmp);
        sceptorElementDamage.getNeutral().multiply(computedSpellAmp);

        this.currentElementDamage = sceptorElementDamage;
    }

    public void update() {

    }

    @Override
    public void init() {

        // 플레이어 초기 설정
        this.bukkitEntity.setMaximumNoDamageTicks(0);

        this.bukkitTaskEveryTick = Bukkit.getScheduler().runTask(Lylac.lylacPlugin, () -> {
            update();
            // 플레이어 액션바 전시 스케줄러
            // 보스바 전시 스케줄러
        });
        this.bukkitTaskEverySecond = Utils.Scheduler.executeContinuallyEveryTick(() -> {
            // 마나 초당 회복 스케쥴러
            // 체력 초당 회복 스케줄러
        }, 20);

        // 초기 능력치 계산 및 적용
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
    public <T2 extends Entity> void attackedBy(ElementDamageRange eDamage, T2 by) {
        // 총 피해량 계산 후 체력에 적용 및 이벤트 발생
        ElementDamage picked = eDamage.pickRandomElementDamage();
        LylacPlayerDamageByEntityEvent<T2> event = new LylacPlayerDamageByEntityEvent<T2>(this, by, picked);
        Utils.Event.callEvent(event);

        if(event.isCancelled()) return; // 이벤트가 캔슬되었을 경우 mutateHealth 메서드 호출 막음

        int totalDamage = event.getElementDamage().getTotalDamage();
        this.mutateHealth(-totalDamage, HealthMutateCause.SPELL, by);
        this.bukkitEntity.damage(0);

        // 아머스탠드를 활용한 데미지 인디케이터 소환

    }

    @Override
    public <T2 extends Entity & Damageable> void attack(T2 to) {
        to.attackedBy(currentElementDamage, this);
    }

    @Override
    public <T2 extends Entity> void mutateHealth(int mutateValue, HealthMutateCause cause, T2 by) {
        LylacPlayerHealthMutateEvent<T2> event = new LylacPlayerHealthMutateEvent<>(this, by, mutateValue, cause);
        Utils.Event.callEvent(event);
        int nextHealth = this.health + mutateValue;
        if(nextHealth > maxHealth) this.health = maxHealth;
        else if(nextHealth <= maxHealth && nextHealth > 0) this.health = nextHealth;
        else {
            this.kill();
        }
        // 버킷 플레이어에 체력 반영시키기
        
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
    public int getHealth() {
        // TODO Auto-generated method stub
        return 0;
    }

    public int getMana() {
        return this.mana;
    }

    public org.bukkit.entity.Player getBukkitEntity() {
        return this.bukkitEntity;
    }

    public ArtifactInventory getArtifactInventory() { return this.artifactInventory; }

    public void showStatusAsActionBar() {
        Utils.Chat.sendActionBar(bukkitEntity, "");
    }


}
