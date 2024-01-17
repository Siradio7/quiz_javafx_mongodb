package com.quiz_mongodb.dao;

import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.quiz_mongodb.database.Database;
import com.quiz_mongodb.modele.User;
import org.bson.Document;

import java.util.ArrayList;

public class UserDAO implements IDAO<User> {
    private final MongoDatabase database = Database.getDatabase();
    private final MongoCollection<Document> users_collection = database.getCollection("users");

    @Override
    public ArrayList<User> getAll() {
        FindIterable<Document> result = users_collection.find();
        ArrayList<User> users_list = new ArrayList<>();

        result.forEach(document -> {
            User user = new User();

            user.setUsername(document.getString("username"));
            user.setPassword(document.getString("password"));
            users_list.add(user);
        });

        return users_list;
    }

    public boolean find(User user) {
        Document filter = new Document();
        filter.append("username", user.getUsername());
        Document result =  users_collection.find(filter).first();

        return result != null;
    }

    @Override
    public void add(User user) {
        Document user_doc = new Document();

        user_doc.append("username", user.getUsername());
        user_doc.append("password", user.getPassword());
        System.out.println(users_collection.insertOne(user_doc).wasAcknowledged());
    }
}
