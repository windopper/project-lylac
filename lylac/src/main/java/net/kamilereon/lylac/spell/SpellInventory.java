package net.kamilereon.lylac.spell;

import java.util.ArrayList;
import java.util.List;

import net.kamilereon.lylac.entity.Player;

/**
 * 스펠 인벤토리 클래스. 해당 클래스에서 플레이어가 등록한 스펠들을 저장하고 관리한다.
 * @author kamilereon
 * @version 1.0.0
 * @see Spell
 */

public class SpellInventory {

    private final Player player;
    private final Spell[] registeredSpells = new Spell[8];

    public SpellInventory(Player player) {
        this.player = player;
    }
}
