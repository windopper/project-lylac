package net.kamilereon.lylac.statistics;

import java.lang.reflect.Field;

import org.bson.Document;

import net.kamilereon.lylac.entity.Player;
import net.kamilereon.lylac.general.LylacData;

/**
 * 플레이어의 각 캐릭터들의 모든 통계 자료를 저장하는 컨테이너
 */
public class LylacPlayerCharacterStatisticsContainer implements LylacData {
    
    private final Player player;

    private long createdAt = System.currentTimeMillis();

    private long logins = 0;
    private long deaths = 0;

    private long playTime = 0;

    private long blocksWalked = 0;
    private long entityKill = 0;
    private long playerKill = 0;

    public LylacPlayerCharacterStatisticsContainer(Player player) {
        this.player = player;
    }

    /**
     * statistics object를 받아서 해당 객체에 로드해 주는 메서드
     * 
     * @param doc statistics object 
     */
    @Override
    public void load(Document doc) {
        Document statistics = doc.get("statistics", Document.class);
        if(statistics == null) return;
        for(LylacPlayerCharacterStatistics statistic : LylacPlayerCharacterStatistics.values()) {
            try {
                String K = statistic.name();
                long V = statistics.getLong(K);
                Field field = this.getClass().getDeclaredField(K);
                field.setAccessible(true);
                field.setLong(this, V);
                field.setAccessible(false);
            }
            catch(Exception e) {
    
            }
        }
    }

    @Override
    public Document getAsDocument() {
        Document statistics = new Document();
        for(LylacPlayerCharacterStatistics statistic : LylacPlayerCharacterStatistics.values()) {
            try {
                String K = statistic.name();
                Field field = this.getClass().getDeclaredField(K);
                field.setAccessible(true);
                long V = field.getLong(this);
                field.setAccessible(false);
                statistics.append(K, V);   
            }
            catch(Exception e) {

            }
        }
        return statistics;
    }

    private enum LylacPlayerCharacterStatistics {
        firstLogin,
        lastLogin,
        logins,
        deaths,
        playTime,
        blocksWalked,
        entityKilled,
        playerKilled,
        totalDuelCount,
        totalDuelWin,
        totalDuelLose;
    }
}
