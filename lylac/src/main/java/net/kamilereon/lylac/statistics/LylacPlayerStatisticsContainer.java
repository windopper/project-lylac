package net.kamilereon.lylac.statistics;

import static com.mongodb.client.model.Filters.eq;

import java.lang.reflect.Field;

import org.bson.Document;

import com.mongodb.BasicDBObject;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

import net.kamilereon.lylac.database.MongoConfig;
import net.kamilereon.lylac.database.MongoConfig.LylacMongoCollections;
import net.kamilereon.lylac.entity.Player;

public class LylacPlayerStatisticsContainer {
    
    private final Player player;

    private long totalLogin = 0;
    private long playTime = 0;

    private long entityKill = 0;
    private long playerKill = 0;

    private long totalDuelCount = 0;
    private long totalDuelWin = 0;
    private long totalDuelLose = 0;

    public LylacPlayerStatisticsContainer(Player player) {
        this.player = player;
    }

    public void load() {
        MongoDatabase database = MongoConfig.mongoDatabase();
        MongoCollection<Document> collection = MongoConfig.getMongoCollection(database, LylacMongoCollections.player);
        Document doc = collection.find(eq("uuid", player.getBukkitEntity().getUniqueId().toString())).first();
        for(LylacPlayerStatistics statistic : LylacPlayerStatistics.values()) {
            try {
                String K = statistic.name();
                int V = doc.getInteger(K, 0);
                Field field = this.getClass().getDeclaredField(K);
                field.setAccessible(true);
                field.setInt(this, V);
                field.setAccessible(false);
            }
            catch(Exception e) {
    
            }
        }
    }

    public void save() {
        
    }

    public enum LylacPlayerStatistics {
        totalLogin,
        playTime,
        entityKill,
        playerKill,
        totalDuelCount,
        totalDuelWin,
        totalDueLose;
    }
}
