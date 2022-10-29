package net.kamilereon.lylac.event.player;

import org.bukkit.event.Cancellable;
import org.bukkit.event.HandlerList;

import net.kamilereon.lylac.element.ElementDamage;
import net.kamilereon.lylac.entity.Entity;
import net.kamilereon.lylac.entity.Player;

public class LylacPlayerDamageByEntityEvent <T extends Entity> extends LylacPlayerEvent implements Cancellable {
    private static final HandlerList HANDLER_LIST = new HandlerList();

    private final Player who;
    private final T by;
    private final ElementDamage elementDamage;

    private boolean cancelled = false;

    public LylacPlayerDamageByEntityEvent(Player who, T by, ElementDamage elementDamage) {
        super(who, true);
        this.who = who;
        this.by = by;
        this.elementDamage = elementDamage;
        //TODO Auto-generated constructor stub
    }

    /**
     * 
     * @return 피해를 받은 플레이어를 리턴
     */
    public Player getLylacPlayer() {
        return who;
    }

    /**
     * 
     * @return 피해를 준 엔티티
     */
    public T getDamager() {
        return by;
    }

    /**
     * 
     * @return {@link ElementDamage} 으로 감싸진 속성 데미지 객체를 반환
     * 
     */
    public ElementDamage getElementDamage() {
        return elementDamage;
    }

    @Override
    public boolean isCancelled() {
        // TODO Auto-generated method stub
        return this.cancelled;
    }

    @Override
    public void setCancelled(boolean cancel) {
        // TODO Auto-generated method stub
        this.cancelled = cancel;
    }

    @Override
    public HandlerList getHandlers() {
        // TODO Auto-generated method stub
        return null;
    }
    public static HandlerList getHandlerList() {
        return HANDLER_LIST;
    }

}
