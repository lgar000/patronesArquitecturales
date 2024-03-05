package edu.escuelaing.arem.ASE.app;


import static spark.Spark.*;
import static spark.Spark.get;

public class LogServiceFachada {

    private static final String LOG_SERVICE= "localhost:5000/logService";

    public static void main(String... args){
        RemoteServicesInvoke remoteServicesInvoke= new RemoteServicesInvoke(LOG_SERVICE);

        staticFiles.location("/public");
        port(getPort());
        get("logServiceFachada", (req,res) ->
        {
            return remoteServicesInvoke.invoke(args);
        });
    }


    private static int getPort() {
        if (System.getenv("PORT") != null) {
            return Integer.parseInt(System.getenv("PORT"));
        }
        return 4567;
    }
}
