package edu.escuelaing.arem.ASE.app;

import java.util.ArrayList;
import java.util.Date;

import java.util.List;

import com.mongodb.*;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Sorts;
import org.bson.Document;

public class MongoDatabaseOperations {

    private MongoClient mongo;

    public MongoDatabaseOperations() {
        mongo = new MongoClient("db", 27017);
    }

    public List<Document> getLogs() {
        MongoDatabase db = mongo.getDatabase("admin");
        MongoCollection<Document> collection = db.getCollection("arepLogs");

        List<Document> documents = new ArrayList<>();
        try (MongoCursor<Document> cursor = collection.find().limit(10).sort(Sorts.descending("createdAt")).iterator()) {
            while (cursor.hasNext()) {
                documents.add(cursor.next());
            }
        }

        System.out.println(documents + "\n");
        return documents;
    }

    public void insertLog(String body) {
        MongoDatabase db = mongo.getDatabase("admin");
        MongoCollection<Document> collection = db.getCollection("arepLogs");

        System.out.println(body);
        Document document = Document.parse(body);
        System.out.println("----");
        document.append("createdAt", new Date());

        collection.insertOne(document);
    }

    public void closeConnection() {
        if (mongo != null) {
            mongo.close();
        }
    }
}
