package net.kamilereon.lylac.entity;

import java.util.function.Function;

import net.kamilereon.lylac.event.entity.Cause;

/**
 * @author kamilereon
 * @version 1.0.0
 */
public interface Player {

    public org.bukkit.entity.Player getPlayer();



    /**
     * 
     * @return 플레이어의 최대 체력을 리턴
     */
    public int getMaxHealth();
    
    public void setMaxHealth(final int value);

    public int getMaxMana();

    public void setMaxMana(final int value);

    public double getMaxManaRate();

    public void setMaxManaRate();

    public int getHealth();

    public void setHealth(Function<Integer, Integer> mutation, Cause cause);

    public boolean loadData();

    public boolean saveData();

}
