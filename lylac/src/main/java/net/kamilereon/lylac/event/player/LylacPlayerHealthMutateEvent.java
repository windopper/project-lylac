package net.kamilereon.lylac.event.player;

import org.bukkit.entity.LivingEntity;
import org.bukkit.event.HandlerList;

import net.kamilereon.lylac.entity.Entity;
import net.kamilereon.lylac.entity.Player;
import net.kamilereon.lylac.event.Cause.HealthMutateCause;

public class LylacPlayerHealthMutateEvent <T extends Entity<? extends LivingEntity>> extends LylacPlayerEvent {
    private static final HandlerList HANDLER_LIST = new HandlerList();

    private final T by;
    /**
     * 변한 체력
     */
    private final int mutateValue;
    /**
     * 체력이 변한 원인
     */
    private final HealthMutateCause cause;

    public LylacPlayerHealthMutateEvent(final Player who, final T by, int mutateValue, HealthMutateCause cause) {
        super(who, true);
        this.by = by;
        this.mutateValue = mutateValue;
        this.cause = cause;
    }

    public Player getLylacPlayer() {
        return this.player;
    }

    public T getMutatedBy() {
        return by;
    }

    @Override
    public HandlerList getHandlers() {
        return HANDLER_LIST;
    }

    public static HandlerList getHandlerList() {
        return HANDLER_LIST;
    }



}
