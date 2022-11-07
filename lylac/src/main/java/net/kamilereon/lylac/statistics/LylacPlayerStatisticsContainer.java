package net.kamilereon.lylac.statistics;

import static com.mongodb.client.model.Filters.eq;

import java.lang.reflect.Field;
import java.util.Date;

import org.bson.Document;

import com.mongodb.BasicDBObject;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

import net.kamilereon.lylac.database.MongoConfig;
import net.kamilereon.lylac.database.MongoConfig.LylacMongoCollections;
import net.kamilereon.lylac.entity.Player;

public class LylacPlayerStatisticsContainer {
    
    private final Player player;

    private long firstLogin = System.currentTimeMillis();
    private long lastLogin = System.currentTimeMillis();

    private long logins = 0;
    private long deaths = 0;

    private long playTime = 0;

    private long blocksWalked = 0;
    private long entityKill = 0;
    private long playerKill = 0;

    private long totalDuelCount = 0;
    private long totalDuelWin = 0;
    private long totalDuelLose = 0;

    public LylacPlayerStatisticsContainer(Player player) {
        this.player = player;
    }

    /**
     * statistics object를 받아서 해당 객체에 로드해 주는 메서드
     * 
     * @param doc statistics object 
     */
    public void load(Document doc) {
        if(doc == null) return;
        for(LylacPlayerStatistics statistic : LylacPlayerStatistics.values()) {
            try {
                String K = statistic.name();
                long V = doc.getLong(K);
                Field field = this.getClass().getDeclaredField(K);
                field.setAccessible(true);
                field.setLong(this, V);
                field.setAccessible(false);
            }
            catch(Exception e) {
    
            }
        }
    }

    public Document getAsDocument() {
        Document statistics = new Document();
        for(LylacPlayerStatistics statistic : LylacPlayerStatistics.values()) {
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

    public enum LylacPlayerStatistics {
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
