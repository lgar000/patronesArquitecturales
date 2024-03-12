package edu.escuelaing.arem.ASE.app;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;


public class RemoteServicesInvoke {

    private static final String USER_AGENT = "Mozilla/5.0";

    private static final String[]  GET_URL ={"http://log1:46000", "http://log2:46000", "http://log3:46000"} ;

    private static int instanceUrl= 0;

    /**
     * Manejo de peticiones get
     * @return una  cadena de respuesta a la solicitud HTTP GET
     * @throws IOException manejo de excepciones en caso de problemas de entrada/salida durante la conexión.
     */
    public static String getLogs() throws IOException {
        URL obj = new URL(roundRobin());
        HttpURLConnection con = (HttpURLConnection) obj.openConnection();
        con.setRequestMethod("GET");
        con.setRequestProperty("User-Agent", USER_AGENT);
        int responseCode = con.getResponseCode();
        System.out.println("GET Response Code :: " + responseCode);
        if (responseCode == HttpURLConnection.HTTP_OK) { // success
            BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
            String inputLine;
            StringBuffer response = new StringBuffer();

            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
            in.close();

            System.out.println(response + "\n");
            return response.toString();
        } else {
            System.out.println("GET request did not work.");
            return "";
        }
    }

    /**
     * Manejo de peticiones post
     * @throws IOException manejo de excepciones en caso de problemas de entrada/salida durante la conexión.
     */
    static void insertLog(String body) throws IOException {
        URL obj = new URL(roundRobin());
        HttpURLConnection con = (HttpURLConnection) obj.openConnection();
        con.setRequestMethod("POST");
        con.setRequestProperty("Content-type", "application/json");
        con.setDoOutput(true);

        OutputStream os = con.getOutputStream();
        os.write(body.getBytes());
        os.flush();
        os.close();

        int responseCode = con.getResponseCode();
        System.out.println("Response Code: " + responseCode);
    }

    /**
     * Se encarga del balanceo de cargas para que se distribuyan uniformente las peticiones a url de los Log service
     * @return una de las url disponibles en el arreglo GET_URL
     */
    private static String roundRobin() {
        instanceUrl= ( instanceUrl+1)% RemoteServicesInvoke.GET_URL.length;
        String newUrl = RemoteServicesInvoke.GET_URL[instanceUrl];
        return newUrl;
    }

}
