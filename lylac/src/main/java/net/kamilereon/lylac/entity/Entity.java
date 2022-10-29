package net.kamilereon.lylac.entity;

import java.lang.reflect.Field;

import org.bukkit.Bukkit;
import org.bukkit.entity.LivingEntity;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.plugin.PluginManager;

import net.kamilereon.lylac.Lylac;
import net.kamilereon.lylac.Utils;
import net.kamilereon.lylac.event.Cause.HealthMutateCause;
import net.kamilereon.lylac.event.player.LylacPlayerHealthMutateEvent;


/**
 * @Author kamilereon
 * @version 1.0.0
 */
public abstract class Entity <T extends org.bukkit.entity.LivingEntity> {

    public static final int RATE_DEFAULT = 100;

    protected final T bukkitEntity;

    protected int maxHealth;
    protected int health = maxHealth;
    protected int maxHealthIncRate = RATE_DEFAULT;
    protected int healthRegenRate = RATE_DEFAULT;

    /*
     * 속성 방어률 즉, 데미지 방어률
     * spellResistance는 모든 속성에 대해서 적용
     * 
     * 최대 200
     * 예1) 공기방어률이 120이면 받는 데미지 계수는 0.8로 계산
     * 예2) 공기방어률이 -100이면 받는 데미지 계수는 3으로 계산
     */
    protected int spellResistance = RATE_DEFAULT;
    protected int meleeResistance = RATE_DEFAULT;
    protected int waterResistance = RATE_DEFAULT;
    protected int fireResistance = RATE_DEFAULT;
    protected int airResistance = RATE_DEFAULT;
    protected int earthResistacne = RATE_DEFAULT;

    public Entity(int maxHealth, T bukkitEntity) {
        this.maxHealth = maxHealth;
        this.bukkitEntity = bukkitEntity;
        this.init();
    }

    /*
     * 플레이어가 소유한 아티팩트, 특성등 능력치에 영향을 주는
     * 모든 값을 계산하여 해당 객체에 반영
     */
    abstract public void update();

    /*
     * 객체가 처음 생산 되었을 때 실행
     */
    abstract protected void init();

    /*
     * 해당 객체의 엔티티가 죽거나 객체가 제거될 때 실행
     */
    abstract protected void fin();

    public <T2 extends Entity<? extends LivingEntity>> void mutateHealth(int mutateValue, HealthMutateCause cause, T2 by) {
        if(this.getBukkitEntity() instanceof org.bukkit.entity.Player) {
            Utils.Event.callEvent(new LylacPlayerHealthMutateEvent<T2>((Player) this, by, mutateValue, cause));
        }
        this.health += mutateValue;
        bukkitEntity.setHealth(this.health);
    }

    public T getBukkitEntity() {
        return bukkitEntity;
    }

    public int getHealth() {
        return this.health;
    }

    /**
     * 엔티티의 스탯을 설정하게 해주는 setter 메서드
     * 
     * @param stats 설정하고자 하는 스탯
     * @param value 설정하고자 하는 값
     * @return 해당 객체 반환
     */
    public void setStat(EntityStats stats, int value) {
        try {
            Field field = this.getClass().getDeclaredField(stats.name());
            field.setAccessible(true);
            field.setInt(this, value);
            field.setAccessible(false);
        }
        catch(Exception e) {
            e.printStackTrace();
        }
    }

        /**
     * 
     * @param stats 엔티티의 스탯에서 가져오고 싶은 스탯
     * @return 해당 스탯과 이름이 같은 필드 값을 리턴
     * @throws NoSuchFieldException 설정하고자 하는 스탯이 해당 필드에 존재하지 않을 때 발생하는 예외
     * @throws IllegalAccessException 필드 접근을 하지 못할 때 발생하는 예외.
     */
    public int getStat(EntityStats stats) {
        try {
            Field field = this.getClass().getDeclaredField(stats.name());
            field.setAccessible(true);
            int V = field.getInt(this);
            field.setAccessible(false);
            return V;
        }
        catch(NoSuchFieldException e) {
            e.printStackTrace();
        }
        catch(IllegalAccessException e) {
            e.printStackTrace();
        }
        return 0;
    }

    /**
     * 라일락 엔티티가 가진 모든 능력치의 이름과 초기값을 저장한 enum 클래스
     * 
     * <p>{@link #getDefaultValue()}를 통하여 해당 능력치 이름의 초기값을 구할 수 있다.</p>
     * <p>{@link #getMin()} 또는 {@link #getMax()}를 통하여 해당 능력치의 최소, 최댓값을 구할 수 있다.</p>
     */
    public enum EntityStats {

        maxMana(100),
        maxManaIncRate(RATE_DEFAULT),
        manaRegenRate(RATE_DEFAULT),
        manaConsumptionRate(RATE_DEFAULT, 10, 10000),
        maxHealth(1000),
        maxHealthIncRate(RATE_DEFAULT),
        healthRegenRate(RATE_DEFAULT),
        spellCastingSpeed(RATE_DEFAULT, 20, 10000),
        speedWhileSpellCasting(RATE_DEFAULT),
        spellResistance(RATE_DEFAULT),
        meleeResistance(RATE_DEFAULT),
        waterResistance(RATE_DEFAULT),
        fireResistance(RATE_DEFAULT),
        airResistance(RATE_DEFAULT),
        earthResistacne(RATE_DEFAULT),
        spellAmplificationRate(RATE_DEFAULT),
        waterAmplificationRate(RATE_DEFAULT),
        fireAmplificationRate(RATE_DEFAULT),
        airAmplificationRate(RATE_DEFAULT),
        earthAmplificationRate(RATE_DEFAULT);

        private final int defaultValue;
        private int min = 0;
        private int max = 999999;

        EntityStats(int defaultValue) {
            this.defaultValue = defaultValue;
        }

        EntityStats(int defaultValue, int min, int max) {
            this.defaultValue = defaultValue;
            this.min = min;
            this.max = max;
        }

        public int getDefaultValue() {
            return this.defaultValue;
        }

        public int getMin() {
            return this.min;
        }

        public int getMax() {
            return this.max;
        }
    }

    public static class Util {
        /**
         * 해당 능력치를 비율로 환산하여 실제 게임에 적용 될 수 있도록 해줌
         * 
         * @param value 최소값 0,
         * @return value를 100으로 나눈 값으로 리턴
         */
        public static double getValueToRate(int value) {
            return (double) value / 100;
        }
        public static double getValueToRate(int value, boolean inverse) {
            if(!inverse) return getValueToRate(value);
            return (double) (100 / value);
        }
    }
}
