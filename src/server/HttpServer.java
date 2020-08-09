package src.server;

import java.io.*;
import java.net.*;
import src.server.request.HttpRequest;
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
                // accept connection
                System.out.println(String.format("Waiting for connection at %s:%d... ", address, port));
                Socket socket = server.accept();
                System.out.println("Connection acception from "+ socket.getRemoteSocketAddress());

                // Read the request
                BufferedReader requestReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));

                // parse the received request
                HttpRequest request = new HttpRequest(requestReader);

                // handle the received request
                String response = request.handleRequest();

                // // get the response to be sent
                // String response = HttpResponseBuilder.getResponse(responseBody);

                // initialize the output writter
                BufferedWriter responseWriter = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));

                // log the response
                System.out.println(response);

                // send the response
                responseWriter.write(response);
                responseWriter.flush();

                // close the connection
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