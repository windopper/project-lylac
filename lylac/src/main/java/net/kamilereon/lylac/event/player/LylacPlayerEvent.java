package net.kamilereon.lylac.event.player;

import org.bukkit.event.Event;

import net.kamilereon.lylac.entity.Player;

public abstract class LylacPlayerEvent extends Event {
    protected Player player;

    public LylacPlayerEvent(final Player who) {
        player = who;
    }

    LylacPlayerEvent(final Player who, boolean async) {
        super(async);
        player = who;
    }
}
