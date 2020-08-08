import java.io.*;
import java.net.*;

public class HttpServer {

    private int port;
    private Socket socket = null;
    private ServerSocket server = null;
    private DataInputStream in = null;

    public HttpServer(int port) throws IOException {
        this.port = port;
        this.server = new ServerSocket(port);
    }

    public static void main(String args[]) throws IOException {
        HttpServer server = new HttpServer(9001);

        System.out.println("port: " + server.port);

    }

}