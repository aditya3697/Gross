package src.server;

import java.io.*;
import java.net.*;
import src.utils.GrossLogger;

public class HttpServer extends Thread {

    private int port;
    private String address;
    private ServerSocket server = null;

    public HttpServer(int port, String address) throws IOException
    {
        this.port = port;
        this.address = address;
        this.server = new ServerSocket(port, ServerProperties.clientsBacklog);
        
        // Lets wait for 2mins after starting
        server.setSoTimeout(2 * 60 * 1000);
    }

    public void run()
    {
        while(true)
        {
            try {
                System.out.println(String.format("Waiting for connection at %s:%d... ", address, port));
                Socket socket = server.accept();
                System.out.println("Connection acception from "+ socket.getRemoteSocketAddress());

                DataInputStream in = new DataInputStream(socket.getInputStream());
                System.out.println(in.readUTF());

                DataOutputStream out = new DataOutputStream(socket.getOutputStream());
                out.writeUTF("Thank you for connecting to " + socket.getLocalSocketAddress()
                + "\nGoodbye!");
                socket.close();
            } catch (SocketTimeoutException e){
                System.out.println("Socket timed out!");
                break;
            } catch(IOException e){
                e.printStackTrace();
                break;
            }

        }
    }

}