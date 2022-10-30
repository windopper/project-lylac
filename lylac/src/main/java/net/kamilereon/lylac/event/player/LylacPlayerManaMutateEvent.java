package net.kamilereon.lylac.event.player;

import java.util.logging.Handler;

import org.bukkit.event.Cancellable;
import org.bukkit.event.HandlerList;

import net.kamilereon.lylac.entity.Entity;
import net.kamilereon.lylac.entity.Player;
import net.kamilereon.lylac.event.Cause.ManaMutateCause;

public class LylacPlayerManaMutateEvent <T extends Entity> extends LylacPlayerEvent implements Cancellable {

    private final static HandlerList HANDLER_LIST = new HandlerList();

    private final T by;
    private final int mutateValue;
    private final ManaMutateCause cause;
    private boolean cancelled = false;

    public LylacPlayerManaMutateEvent(Player who, T by, int mutateValue, ManaMutateCause cause) {
        super(who, true);
        this.by = by;
        this.mutateValue = mutateValue;
        this.cause = cause;
    }

    public int getMutateValue() {
        return this.mutateValue;
    }

    public ManaMutateCause getManaMutateCause() {
        return this.cause;
    }

    @Override
    public HandlerList getHandlers() {
        return HANDLER_LIST;
    }

    public static HandlerList getHandlerList() {
        return HANDLER_LIST;
    }

    @Override
    public boolean isCancelled() {
        return this.cancelled;
    }

    @Override
    public void setCancelled(boolean cancel) {
        this.cancelled = cancel;
    }

}
