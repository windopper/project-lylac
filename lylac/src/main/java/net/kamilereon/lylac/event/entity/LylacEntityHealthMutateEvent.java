package net.kamilereon.lylac.event.entity;

import java.util.logging.Handler;

import org.bukkit.entity.LivingEntity;
import org.bukkit.event.HandlerList;

import com.google.common.collect.TreeMultimap;

import net.kamilereon.lylac.entity.Entity;
import net.kamilereon.lylac.event.Cause.HealthMutateCause;

/**
 * {@link Entity#mutateHealth(int, Cause)}가 실행되면 발생하는 이벤트
 * 
 * @author kamilereon
 * @version 1.0.0
 */
public class LylacEntityHealthMutateEvent <T extends LivingEntity> extends LylacEntityEvent {

    private static final HandlerList HANDLER_LIST = new HandlerList();

    private final Entity<T> by;
    private final HealthMutateCause cause;
    private final int mutateValue;

    public LylacEntityHealthMutateEvent(Entity<LivingEntity> who, Entity<T> by, int mutateValue, HealthMutateCause cause) {
        super(who, true);
        this.cause = cause;
        this.mutateValue = mutateValue;
        this.by = by;
    }

    @Override
    public HandlerList getHandlers() {
        return HANDLER_LIST;
    }

    public static HandlerList getHandlerList() {
        return HANDLER_LIST;
    }

    
}
