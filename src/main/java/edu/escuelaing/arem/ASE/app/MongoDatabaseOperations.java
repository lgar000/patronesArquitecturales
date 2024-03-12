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

/**
 * Define las operaciones necesarias para interactuar con la base de datos, obtener e insertar registros nuevos y conectar y cerrar conexión de la base de datos
 */
public class MongoDatabaseOperations {

    private MongoClient mongo;

    public MongoDatabaseOperations() {
        mongo = new MongoClient("db", 27017);
    }

    /**
     * Retorna una lista de de documentos que representan los logs almacenados en la colección "arepLogs"
     * @return retorna los ultimos 10 registros
     */
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

    /**
     * Inserta un nuevo registro
     * @param body el lo a registrar
     */
    public void insertLog(String body) {
        MongoDatabase db = mongo.getDatabase("admin");
        MongoCollection<Document> collection = db.getCollection("arepLogs");

        System.out.println(body);
        Document document = Document.parse(body);
        System.out.println("----");
        document.append("createdAt", new Date());

        collection.insertOne(document);
    }

    /**
     * Cierra conexión de la base de datos
     */
    public void closeConnection() {
        if (mongo != null) {
            mongo.close();
        }
    }
}
