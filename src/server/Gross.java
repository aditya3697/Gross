package src.server;

import java.io.IOException;

public class Gross {

    public static void main(String[] args) throws IOException {

        HttpServer server = new HttpServer(ServerProperties.serverPort, ServerProperties.serverAddress);
        
        // Start the server
        server.start();



    }

}