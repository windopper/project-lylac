package net.kamilereon.lylac.spell;

import org.bson.Document;

import net.kamilereon.lylac.entity.Player;
import net.kamilereon.lylac.general.LylacData;

public class SpellTechInventory implements LylacData {

    private final Player player;

    public SpellTechInventory(Player player) {
        this.player = player;
    }

    @Override
    public void load(Document doc) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public Document getAsDocument() {
        // TODO Auto-generated method stub
        return null;
    }
}
