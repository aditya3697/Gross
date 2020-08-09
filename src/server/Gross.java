package src.server;

import java.io.IOException;

public class Gross {

    public static void main(String[] args) throws IOException {

        HttpServer server = new HttpServer(ServerProperties.GROSS_SERVER_PORT, ServerProperties.GROSS_SERVER_ADDRESS);
        
        // Start the server
        server.start();

        

    }

}