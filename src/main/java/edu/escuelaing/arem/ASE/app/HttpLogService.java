package edu.escuelaing.arem.ASE.app;

import static spark.Spark.*;

public class HttpLogService {

    public static void main(String... args){

        port(getPort());
        staticFileLocation("/public");

        path("/logs", () -> {
            get("", (req,res) -> RemoteServicesInvoke.getLogs());
            post("", (req,res) -> {
                System.out.println(req.body());
                RemoteServicesInvoke.insertLog(req.body());
                return RemoteServicesInvoke.getLogs();
            });
        });
    }



    private static int getPort() {
        if (System.getenv("PORT") != null) {
            return Integer.parseInt(System.getenv("PORT"));
        }
        return 4567;
    }
}
