package net.kamilereon.lylac.quest;

import org.bson.Document;

import net.kamilereon.lylac.entity.Player;
import net.kamilereon.lylac.general.LylacData;
/**
 * 플레이어의 퀘스트 진행 상태를 관리하는 클래스
 */
public class LylacQuestManager implements LylacData {

    private final Player player;

    public LylacQuestManager(Player player) {
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
