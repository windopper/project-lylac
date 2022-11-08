package net.kamilereon.lylac.spell;

import java.util.ArrayList;
import java.util.List;

import org.bson.Document;

import net.kamilereon.lylac.entity.Player;
import net.kamilereon.lylac.general.LylacData;

/**
 * 스펠 인벤토리 클래스. 해당 클래스에서 플레이어가 등록한 스펠들을 저장하고 관리한다.
 * @author kamilereon
 * @version 1.0.0
 * @see Spell
 */

public class SpellInventory implements LylacData {

    private final Player player;
    private final Spell[] registeredSpells = new Spell[8];

    public SpellInventory(Player player) {
        this.player = player;
    }

    /**
     * 
     * @param spellInventoryPosition 스펠 인벤토리의 위치
     * @return 스펠 리턴
     */
    public Spell getSpell(int spellInventoryPosition) {
        if(!isValidSpellPosition(spellInventoryPosition)) return null;
        return registeredSpells[spellInventoryPosition];
    }

    /**
     * 해당 인벤토리 위치에 스펠을 장착한다.
     * @param spellInventoryPosition 장착할 스펠의 위치 (범위 0 ~ 7)
     * @param spell 장착할 스펠
     */
    public void setSpell(int spellInventoryPosition, Spell spell) {
        if(!isValidSpellPosition(spellInventoryPosition)) return;
        this.registeredSpells[spellInventoryPosition] = spell;
    }

    private boolean isValidSpellPosition(int spellPosition) {
        if(spellPosition >= 0 && spellPosition < 8) return true;
        return false;
    }

    @Override
    public void load(Document doc) {
        
    }

    @Override
    public Document getAsDocument() {
        // TODO Auto-generated method stub
        return null;
    }
}
