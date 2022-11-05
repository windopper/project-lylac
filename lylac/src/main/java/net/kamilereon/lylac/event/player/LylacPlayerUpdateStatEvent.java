package net.kamilereon.lylac.event.player;

import org.bukkit.event.HandlerList;

import net.kamilereon.lylac.entity.Player;
import net.kamilereon.lylac.stat.LylacPlayerStatContainer;

/**
 * {@link LylacPlayerStatContainer}의 필드 값이 업데이트 되었을 때 발생하는 이벤트
 * @see LylacPlayerStatContainer
 */
public class LylacPlayerUpdateStatEvent extends LylacPlayerEvent {

    private static final HandlerList HANDLERS = new HandlerList();

    public LylacPlayerUpdateStatEvent(Player who) {
        super(who, true);
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
