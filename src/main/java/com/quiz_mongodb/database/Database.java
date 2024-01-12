package com.quiz_mongodb.database;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoDatabase;

public class Database {

    private static MongoClient mongoClient;
    private static MongoDatabase database;
    static final String URI = "mongodb://localhost:27017";

    private Database() {
        // Constructeur privé pour empêcher l'instanciation directe depuis l'extérieur
    }

    public static synchronized MongoDatabase getDatabase() {
        if (mongoClient == null) {
            mongoClient = MongoClients.create(URI);
            database = mongoClient.getDatabase("quiz");
        }

        return database;
    }

    public static synchronized void close() {
        if (mongoClient != null) {
            mongoClient.close();
            mongoClient = null;
            database = null;
        }
    }
}

