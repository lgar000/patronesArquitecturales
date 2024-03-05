package edu.escuelaing.arem.ASE.app;

import static spark.Spark.*;
import static spark.Spark.get;
import static spark.Spark.port;


public class AppLbRoundRobin {

    public static void main(String... args){
        staticFiles.location("/public");
        port(getPort());
        get("logServiceFachada", (req,res) ->
                {
                    res.type("application/json");
                    return "{\"message\":\"Custom 404\"}";
                });


    }

    private static int getPort() {
        if (System.getenv("PORT") != null) {
            return Integer.parseInt(System.getenv("PORT"));
        }
        return 4567;
    }
}
