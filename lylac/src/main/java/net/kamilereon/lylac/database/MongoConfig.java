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

    public static MongoDatabase mongoDatabase() {
        MongoDatabase database = mongoClient.getDatabase(DATABASE);
        return database;
    }

    public static MongoCollection<Document> getMongoCollection(MongoDatabase database, LylacMongoCollections collection) {
        return database.getCollection(collection.name());
    }

    public enum LylacMongoCollections {
        player,
    }
}
