package edu.escuelaing.arem.ASE.app;

import static spark.Spark.*;

public class LogService {



    public static void main(String... args){
        port(getPort());
        get("logService", (req,res) ->
        {
            res.type("application/json");
            return "{\"message\":\"Custom 404\"}";
        });
    }


    private static int getPort() {
        if (System.getenv("PORT") != null) {
            return Integer.parseInt(System.getenv("PORT"));
        }
        return 5000;
    }


}
