package server;

import java.io.*;
import java.net.*;
import utils.GrossLogger;

public class HttpServer extends Thread {

    private int port;
    private String address;
    private ServerSocket server = null;

    public HttpServer(int port, String address) throws IOException
    {
        this.port = port;
        this.address = address;
        this.server = new ServerSocket(port, ServerProperties.clientsBacklog);
    }

    public void run()
    {
        while(true)
        {
            GrossLogger.info(String.format("Waiting for connection at %s:%d... ", address, port));
            break;
        }
    }

}