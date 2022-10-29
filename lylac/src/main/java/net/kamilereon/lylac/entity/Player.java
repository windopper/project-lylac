package net.kamilereon.lylac.entity;

import org.bukkit.Bukkit;
import org.bukkit.entity.LivingEntity;
import org.bukkit.scheduler.BukkitTask;

import net.kamilereon.lylac.Lylac;
import net.kamilereon.lylac.Utils;
import net.kamilereon.lylac.element.ElementDamage;
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
public class Player extends Entity<org.bukkit.entity.Player> implements Damageable {

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

    protected int mana = 0;

    protected int maxMana;
    protected int maxManaIncRate = RATE_DEFAULT;
    
    /**
     * 마나 재생률
     * <p>최솟값 0</p>
     */
    protected int manaRegenRate = RATE_DEFAULT;

    /**
     * 마나 소모률
     * <p>최솟값 10</p>
     * 
     * 예1) 마나 소모률이 10이면 원래 마나 소비량의 0.1배가 됨
    */
    protected int manaConsumptionRate = RATE_DEFAULT;

    /**
     * 스펠 캐스팅 속도
     * <p>최솟값 20</p>
     * 
     * 예1) 스펠 캐스팅 속도가 80이면 원래 캐스팅 속도에서 0.8배 느려짐
     */
    protected int spellCastingSpeed = RATE_DEFAULT;

    /**
     * 스펠 캐스팅 간 이동속도
     * <p>최솟값 0</p>
     * 
     * 예1) 스펠 캐스팅 간 이동속도가 120이면 원래 스펠 캐스팅 간 이동속도에서 1.2배 빨라짐
     */
    protected int speedWhileSpellCasting = RATE_DEFAULT;

    /*
     * 속성 증폭률 즉, 데미지 증가률
     * spellAmlificationRate는 모든 속성에 대해서 적용
     * 
     * <p>최솟값 0</p>
     * <p>예1) 공기증폭률이 120이면 데미지 계수는 1.2로 계산</p>
     * <p>예2) 공기증폭률이 80이면 데미지 계수는 0.8로 계산</p>
     */
    protected int spellAmplificationRate = RATE_DEFAULT;
    protected int waterAmplificationRate = RATE_DEFAULT;
    protected int fireAmplificationRate = RATE_DEFAULT;
    protected int airAmplificationRate = RATE_DEFAULT;
    protected int earthAmplificationRate = RATE_DEFAULT;

    private BukkitTask bukkitTaskEveryTick;
    private BukkitTask bukkitTaskEvery5Ticks;
    private BukkitTask bukkitTaskEvery10Ticks;
    private BukkitTask bukkitTaskEverySecond;
    
    public Player(int maxHealth, org.bukkit.entity.Player bukkitEntity) {
        super(maxHealth, bukkitEntity);
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
        ElementDamage sceptorElementDamage = sceptor.getElementDamage().clone(); // 클론 메서드 구현하기

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
        this.bukkitTaskEveryTick = Bukkit.getScheduler().runTask(Lylac.lylacPlugin, () -> {
            update();
            // 플레이어 액션바 전시 스케줄러
            // 보스바 전시 스케줄러
        });
        this.bukkitTaskEverySecond = Utils.Scheduler.executeContinuallyEveryTick(() -> {
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
    public <T2 extends Entity<? extends LivingEntity>> void attackedBy(ElementDamage eDamage, T2 by) {
        // TODO Auto-generated method stub
    }

    @Override
    public <T2 extends Entity<? extends LivingEntity> & Damageable> void attack(T2 to) {
        to.attackedBy(currentElementDamage, this);
    }

    public int getMana() {
        return this.mana;
    }

    public ArtifactInventory getArtifactInventory() { return this.artifactInventory; }

    public void showStatusAsActionBar() {
        Utils.Chat.sendActionBar(bukkitEntity, "");
    }

}
