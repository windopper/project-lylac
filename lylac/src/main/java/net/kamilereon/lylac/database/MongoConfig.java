package net.kamilereon.lylac.database;

import org.bson.Document;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

public class MongoConfig {

    private static final String URI = "mongodb://";
    private static final String DATABASE = "sample";
    private static MongoClient mongoClient = MongoClients.create(URI);

    /**
     * 연결된 몽고DB 클라이언트로부터 데이터베이스 가져오기
     * @return 몽고데이터베이스 반환
     */
    public static MongoDatabase mongoDatabase() {
        MongoDatabase database = mongoClient.getDatabase(DATABASE);
        return database;
    }

    /**
     * 몽고DB로부터 도큐먼트 가져오기
     * 
     * @param database
     * @param collection
     * @return
     */
    public static MongoCollection<Document> getMongoCollection(MongoDatabase database, LylacMongoCollections collection) {
        return database.getCollection(collection.name());
    }

    public enum LylacMongoCollections {
        rootItem,
        player,
        market;
    }
}
