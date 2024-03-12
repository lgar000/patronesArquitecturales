package edu.escuelaing.arem.ASE.app;

import com.google.gson.Gson;

import static spark.Spark.*;

/**
 * Servicio web en Spark que interactua con la base de datos para obtener o insertar registros
 */
public class LogService {



    public static void main(String... args){

        MongoDatabaseOperations mongoDatabaseOperations= new MongoDatabaseOperations();


        port(getPort());
        get("/", (req, res) -> {
            res.type("application/json");
            Gson gson = new Gson();
            return gson.toJson(mongoDatabaseOperations.getLogs());
        });

        post("/", (req, res) -> {
            res.type("application/json");
            mongoDatabaseOperations.insertLog(req.body());
            Gson gson = new Gson();
            return gson.toJson(mongoDatabaseOperations.getLogs());
        });
    }


    private static int getPort() {
        if (System.getenv("PORT") != null) {
            return Integer.parseInt(System.getenv("PORT"));
        }
        return 35001;
    }


}
