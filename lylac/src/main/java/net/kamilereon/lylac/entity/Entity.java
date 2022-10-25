package net.kamilereon.lylac.entity;

import net.kamilereon.lylac.event.entity.Cause;

/**
 * @Author kamilereon
 * @version 1.0.0
 */
public abstract class Entity <T extends org.bukkit.entity.LivingEntity> {

    public static final int RATE_DEFAULT_VALUE = 100;

    protected final T bukkitEntity;
    
    protected int maxHealth;
    protected int health = maxHealth;

    protected int maxHealthIncRate = RATE_DEFAULT_VALUE;
    protected int healthRegenRate = RATE_DEFAULT_VALUE;

    protected int spellResistance = RATE_DEFAULT_VALUE;
    protected int meleeResistance = RATE_DEFAULT_VALUE;
    protected int waterResistance = RATE_DEFAULT_VALUE;
    protected int fireResistance = RATE_DEFAULT_VALUE;
    protected int airResistance = RATE_DEFAULT_VALUE;
    protected int earthResistacne = RATE_DEFAULT_VALUE;

    public Entity(int maxHealth, T bukkitEntity) {
        this.maxHealth = maxHealth;
        this.bukkitEntity = bukkitEntity;
    }

    /*
     * 가상 엔티티의 업데이트 사항을 버킷 엔티티 객체로 업데이트
     */
    abstract public void update();

    abstract protected void init();

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

    /* */
}
