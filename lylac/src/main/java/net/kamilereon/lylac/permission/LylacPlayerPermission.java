package net.kamilereon.lylac.permission;

import java.util.HashMap;
import java.util.Map;

import net.kamilereon.lylac.entity.Player;

/**
 * 라일락 플레이어들의 퍼미션을 대표하는 클래스
 * 
 * <p>플레이어 객체의 메서드들의 대부분은 해당 퍼미션의 허락이 있어야 수행이 가능하다.</p>
 */
public class LylacPlayerPermission {

    Map<LylacPlayerPermissionType, Boolean> permissions = new HashMap<>();

    public LylacPlayerPermission() {

    }

    public boolean isPermitted(LylacPlayerPermissionType type) {
        if(!permissions.containsKey(type)) return true;
        return permissions.get(type);
    }

    public void permit(LylacPlayerPermissionType type) { 
        if(!permissions.containsKey(type)) { permissions.put(type, true); return; }
        permissions.replace(type, true);
    }

    public void forbid(LylacPlayerPermissionType type) { 
        if(!permissions.containsKey(type)) { permissions.put(type, false); return; }
        permissions.replace(type, false);
    }

    /**
     * 권한을 확인하여 확인되지 않는다면 해당 플레이어에게 경고 메시지를 출력합니다.
     * 
     * @param player 라일락 플레이어 객체
     * @param type 권한의 종류
     * @param message 권한을 가지고 있지 않다면 플레이어에게 메시지를 출력
     * @return 권한을 가지고 있으면 <code>true</code> 아니면 <code>false</code>
     */
    public boolean checkPermission(Player player, LylacPlayerPermissionType type, String message) {
        if(isPermitted(type)) return true;
        player.getBukkitEntity().sendMessage(message);
        return false;
    }

    public enum LylacPlayerPermissionType {
        LYLAC_MARKET_OPEN_GUI,
        LYLAC_MARKET_BUY_ITEM,
        LYLAC_MARKET_SELL_ITEM,
        LYLAC_MARKET_GET_MONEY,

        LYLAC_QUEST_START,
        LYLAC_QUEST_NEXT_STEP,
        LYLAC_QUEST_NEXT_STAGE,
        LYLAC_QUEST_COMPLETE,

        LYLAC_TRADE_LISTEN_REQUEST,
        LYLAC_TRADE_SEND_REQUEST,
        LYLAC_TRADE_ACCEPT_REQUEST,
        LYLAC_TRADE_PUT_ITEM,
        LYLAC_TRADE_SET_MONEY,
        LYLAC_TRADE_CHECK,
        LYLAC_TRADE_SUCCESS,
        LYLAC_TRADE_CANCEL,

        LYLAC_SPELL_CAST,
        LYLAC_SPELL_USE,
        LYLAC_SPELL_CANCEL,

        LYLAC_DUEL_LISTEN_REQUEST,
        LYLAC_DUEL_REQUEST,
        LYLAC_DUEL_ACCEPT;
    }
}