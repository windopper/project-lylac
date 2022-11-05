package net.kamilereon.lylac.event.player;

import org.bukkit.event.Cancellable;
import org.bukkit.event.HandlerList;

import net.kamilereon.lylac.entity.Player;
import net.kamilereon.lylac.spell.Spell;

/**
 * 플레이어가 스펠을 장착할 때 발동
 * 
 * @see Player#equipSpell(int, Spell)
 */
public class LylacPlayerEquipSpellEvent extends LylacPlayerEvent implements Cancellable {

    public static HandlerList HANDLERS = new HandlerList();

    private final int position;
    private final Spell spell;
    private boolean isCancelled = false;

    public LylacPlayerEquipSpellEvent(Player who, int position, Spell spell) {
        super(who, true);
        this.position = position;
        this.spell = spell;
        //TODO Auto-generated constructor stub
    }

    public int getPosition() {
        return this.position;
    }

    public Spell getSpell() {
        return this.spell;
    }
    
    @Override
    public boolean isCancelled() {
        // TODO Auto-generated method stub
        return this.isCancelled;
    }
  
    @Override
    public void setCancelled(boolean cancel) {
        // TODO Auto-generated method stub
        this.isCancelled = cancel;
    }

    @Override
    public HandlerList getHandlers() {
        // TODO Auto-generated method stub
        return HANDLERS;
    }

    public static HandlerList getHandlerList() {
        return HANDLERS;
    }
    
}
