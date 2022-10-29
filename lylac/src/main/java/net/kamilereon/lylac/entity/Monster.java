package net.kamilereon.lylac.entity;

import org.bukkit.entity.LivingEntity;

public abstract class Monster extends Entity<org.bukkit.entity.LivingEntity> {

    public Monster(int maxHealth, LivingEntity bukkitEntity) {
        super(maxHealth, bukkitEntity);
        //TODO Auto-generated constructor stub
    }
}
