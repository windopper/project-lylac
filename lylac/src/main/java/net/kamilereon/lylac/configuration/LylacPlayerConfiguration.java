package net.kamilereon.lylac.configuration;

import java.lang.reflect.Field;

import org.bson.Document;

import net.kamilereon.lylac.entity.Player;
import net.kamilereon.lylac.general.LylacData;

/**
 * 플레이어의 개인별 설정에 관한 클래스
 */
public class LylacPlayerConfiguration implements LylacData {

    private final Player player;

    public LylacPlayerConfiguration(Player player) {
        this.player = player;
    }

    @Override
    public void load(Document doc) {
        // TODO Auto-generated method stub
        Document configurationDoc = doc.get("configuration", Document.class);
        for(LylacConfigurationType type : LylacConfigurationType.values()) {
            String K = type.name();
            try {
                Field field = this.getClass().getDeclaredField(K);
                field.setAccessible(true);
                
                field.setAccessible(false);
            }   
            catch(Exception e) {

            }
        }
    }

    @Override
    public Document getAsDocument() {
        // TODO Auto-generated method stub
        return null;
    }

    public enum LylacConfigurationType {

    }
    
}
