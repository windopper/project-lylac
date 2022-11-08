package net.kamilereon.lylac.storage;

import org.bson.Document;

import net.kamilereon.lylac.LylacUtils;
import net.kamilereon.lylac.entity.Player;
import net.kamilereon.lylac.event.player.LylacPlayerStorageExpandEvent;
import net.kamilereon.lylac.event.player.LylacPlayerStorageOpenEvent;
import net.kamilereon.lylac.general.LylacData;

/**
 * 플레이어의 저장고에 관한 클래스
 */
public class LylacStorage implements LylacData {

    private final Player player;
    private int maxPage = 0;

    public LylacStorage(Player player) {
        this.player = player;
    }

    /**
     * 저장고를 연다. 
     * @see #open(int)
     */
    public void open() {
        open(0);
    }

    /**
     * 특정 페이지를 지정하여 저장고를 연다
     * @param page 열고자 하는 페이지
     */
    public void open(int page) {
        // 이벤트 방출
        LylacPlayerStorageOpenEvent event = new LylacPlayerStorageOpenEvent(player, page);
        LylacUtils.Event.callEvent(event);
        if(event.isCancelled()) return;
        
        
    }

    /**
     * 저장고를 확장한다.
     */
    public void expand() {
        LylacPlayerStorageExpandEvent event = new LylacPlayerStorageExpandEvent(player, maxPage + 1);
        LylacUtils.Event.callEvent(event);
        if(event.isCancelled()) return;

        this.maxPage += 1;
    }

    /**
     * 저장고를 DB에 저장한다.
     */
    public void save() {

    }

    @Override
    public void load(Document doc) {
        // TODO Auto-generated method stub
        Document storageDoc = doc.get("storage", Document.class);
    }

    @Override
    public Document getAsDocument() {
        // TODO Auto-generated method stub
        return null;
    }
    
}
