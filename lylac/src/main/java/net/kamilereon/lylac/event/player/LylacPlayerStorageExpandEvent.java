package net.kamilereon.lylac.event.player;

import org.bukkit.event.Cancellable;
import org.bukkit.event.HandlerList;

import net.kamilereon.lylac.entity.Player;

/**
 * 플레이어가 스토리지를 확장할 때 발동하는 이벤트
 */
public class LylacPlayerStorageExpandEvent extends LylacPlayerEvent implements Cancellable {

    private final static HandlerList HANDLERS = new HandlerList();

    private final int page;
    private boolean isCancelled = false;

    /**
     * 
     * @param who
     * @param page 확장하는 페이지
     */
    public LylacPlayerStorageExpandEvent(Player who, int page) {
        super(who, true);
        this.page = page;
        //TODO Auto-generated constructor stub
    }

    @Override
    public HandlerList getHandlers() {
        return HANDLERS;
    }

    public static HandlerList getHandlerList() {
        return HANDLERS;
    }

    @Override
    public boolean isCancelled() {
        return this.isCancelled;
    }

    @Override
    public void setCancelled(boolean cancel) {
        this.isCancelled = cancel;
    }
}


