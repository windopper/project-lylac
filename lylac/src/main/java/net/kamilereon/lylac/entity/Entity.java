package net.kamilereon.lylac.entity;

import net.kamilereon.lylac.event.entity.Cause;

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

    public void mutateHealth(int mutateValue, Cause cause) {
        this.health += mutateValue;
        bukkitEntity.setHealth(this.health);
    }

    public T getBukkitEntity() {
        return bukkitEntity;
    }

    public int getMaxHealth() {
        return maxHealth;
    }

    public void setMaxHealth(int maxHealth) {
        this.maxHealth = maxHealth;
    }

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public int getMaxHealthIncRate() {
        return maxHealthIncRate;
    }

    public void setMaxHealthIncRate(int maxHealthIncRate) {
        this.maxHealthIncRate = maxHealthIncRate;
    }

    public int getHealthRegenRate() {
        return healthRegenRate;
    }

    public void setHealthRegenRate(int healthRegenRate) {
        this.healthRegenRate = healthRegenRate;
    }

    public int getSpellResistance() {
        return spellResistance;
    }

    public void setSpellResistance(int spellResistance) {
        this.spellResistance = spellResistance;
    }

    public int getMeleeResistance() {
        return meleeResistance;
    }

    public void setMeleeResistance(int meleeResistance) {
        this.meleeResistance = meleeResistance;
    }

    public int getWaterResistance() {
        return waterResistance;
    }

    public void setWaterResistance(int waterResistance) {
        this.waterResistance = waterResistance;
    }

    public int getFireResistance() {
        return fireResistance;
    }

    public void setFireResistance(int fireResistance) {
        this.fireResistance = fireResistance;
    }

    public int getAirResistance() {
        return airResistance;
    }

    public void setAirResistance(int airResistance) {
        this.airResistance = airResistance;
    }

    public int getEarthResistacne() {
        return earthResistacne;
    }

    public void setEarthResistacne(int earthResistacne) {
        this.earthResistacne = earthResistacne;
    }

    /**
     * 라일락 엔티티가 가진 모든 능력치의 이름과 초기값을 저장한 enum 클래스
     * 
     * <p>{@link #getDefaultValue()}를 통하여 해당 능력치 이름의 초기값을 구할 수 있다.</p>
     */
    public enum EntityStats {

        maxMana(100),
        maxManaIncRate(RATE_DEFAULT),
        manaRegenRate(RATE_DEFAULT),
        manaConsumptionRate(RATE_DEFAULT),
        maxHealth(1000),
        maxHealthIncRate(RATE_DEFAULT),
        healthRegenRate(RATE_DEFAULT),
        spellCastingSpeed(RATE_DEFAULT),
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
        private final int min = 0;
        private final int max = 1000;

        EntityStats(int defaultValue) {
            this.defaultValue = defaultValue;
        }

        public int getDefaultValue() {
            return this.defaultValue;
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
            return (double) (200-value) / 100;
        }
    }
}
