package net.kamilereon.lylac.event.player;

import org.bukkit.event.Cancellable;
import org.bukkit.event.HandlerList;

import net.kamilereon.lylac.entity.Player;

/**
 * 플레이어가 마켓을 열 때 발생하는 이벤트
 */
public class LylacPlayerMarketOpenEvent extends LylacPlayerEvent implements Cancellable {

    private static final HandlerList HANDLERS = new HandlerList();
    private boolean isCancelled = false;

    public LylacPlayerMarketOpenEvent(Player who) {
        super(who, false);
        //TODO Auto-generated constructor stub
    }

    @Override
    public HandlerList getHandlers() {
        // TODO Auto-generated method stub
        return HANDLERS;
    }

    public static HandlerList getHandlerList() {
        return HANDLERS;
    }

    @Override
    public boolean isCancelled() {
        // TODO Auto-generated method stub
        return isCancelled;
    }

    @Override
    public void setCancelled(boolean cancel) {
        this.isCancelled = cancel;
    }
    
}
