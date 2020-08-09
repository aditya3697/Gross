package src.server;

import java.io.*;
import java.net.*;
import src.server.response.HttpResponseBuilder;

public class HttpServer extends Thread {

    private int port;
    private String address;
    private ServerSocket server = null;
    private int max_requests = ServerProperties.SERVER_GLOBAL_MAX_REQUESTS;

    public HttpServer(int port, String address) throws IOException
    {
        this.port = port;
        this.address = address;
        this.server = new ServerSocket(port, ServerProperties.MAX_CLIENTS_BACKLOGS);
        
        // Lets wait for 2mins after starting
        server.setSoTimeout(ServerProperties.SERVER_GLOBAL_TIMEOUT);
    }

    public void run()
    {
        while(max_requests > 0)
        {
            max_requests--;
            try {
                System.out.println(String.format("Waiting for connection at %s:%d... ", address, port));
                Socket socket = server.accept();
                System.out.println("Connection acception from "+ socket.getRemoteSocketAddress());

                DataInputStream in = new DataInputStream(socket.getInputStream());
                //System.out.println(in.readUTF());

                BufferedWriter bf = new BufferedWriter(
                    new OutputStreamWriter(socket.getOutputStream()));
                    
                System.out.println("Thank you for connecting to " + socket.getLocalSocketAddress()
                + "\nGoodbye!");
                String response = HttpResponseBuilder.getResponse("Yo! it worked!!");
                System.out.println(response);

                bf.write(response);
                bf.flush();

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