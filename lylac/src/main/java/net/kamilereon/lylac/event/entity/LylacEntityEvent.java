package net.kamilereon.lylac.event.entity;

import java.util.logging.Handler;

import org.bukkit.event.Cancellable;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;
import org.bukkit.event.Listener;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.PluginManager;

import net.kamilereon.lylac.entity.Entity;
import net.kamilereon.lylac.entity.Player;

public abstract class LylacEntityEvent extends Event {

    protected Entity entity;

    public LylacEntityEvent(final Entity who) {
        entity = who;
    }

    LylacEntityEvent(final Entity who, boolean async) {
        super(async);
        entity = who;
    }
}
