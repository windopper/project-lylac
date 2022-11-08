package net.kamilereon.lylac.event.player;

import org.bukkit.event.Cancellable;
import org.bukkit.event.HandlerList;

import net.kamilereon.lylac.entity.Player;

public class LylacPlayerStorageOpenEvent extends LylacPlayerEvent implements Cancellable {

    private final static HandlerList HANDLERS = new HandlerList();

    private final int page;
    private boolean isCancelled = false;

    public LylacPlayerStorageOpenEvent(Player who, int page) {
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


