package net.kamilereon.lylac.entity;

import org.bukkit.entity.LivingEntity;

public abstract class Monster extends Entity {

    protected int maxHealth;
    protected int health = maxHealth;
    protected int maxHealthIncRate = RATE_DEFAULT;
    protected int healthRegenRate = RATE_DEFAULT;

    protected int spellResistance = RATE_DEFAULT;
    protected int meleeResistance = RATE_DEFAULT;
    protected int waterResistance = RATE_DEFAULT;
    protected int fireResistance = RATE_DEFAULT;
    protected int airResistance = RATE_DEFAULT;
    protected int earthResistacne = RATE_DEFAULT;

    public Monster(int maxHealth, LivingEntity bukkitEntity) {
        super();
        this.maxHealth = maxHealth;
        //TODO Auto-generated constructor stub 
    }
}
