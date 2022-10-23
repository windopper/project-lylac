package net.kamilereon.lylac.entity;

import org.bukkit.entity.Player;

/**
 * @author kamilereon
 * @version 1.0.0
 */
public interface LPlayer {
    
    public void setMaxHealth();

    public void addHealth();

    public void removeHealth();

    public void setMaxMana();

    public void addMana();

    public void removeMana();

    public boolean loadData();

    public boolean saveData();



}
