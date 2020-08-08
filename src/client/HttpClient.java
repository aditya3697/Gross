package src.client;

import java.net.*;
import java.io.*;

/*
    This class can be used to test the HttpServer
*/
public class HttpClient{

    private int port;
    private String address;
    private Socket socket;

    public HttpClient(int port, String address)
    {
        this.port = port;
        this.address = address;
    }

    public String sendRequest(String data)
    {
        String response = null;

        try
        {
            // connect to the server
            initiateConnection();

            // send some data
            sendData(data);

            // receive the response from server
            response = receiveResponse();

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            // close the socket
            if(socket != null) {
                try{
                    socket.close();
                } catch(IOException e) {
                    e.printStackTrace();
                }
            }
        }
        
        return response;
    }

    private void initiateConnection() throws UnknownHostException, IOException
    {
        System.out.println(String.format("Sending a connection request to %s:%d", address, port));
        this.socket = new Socket(address, port);
        System.out.println(String.format("Connected to %s:%d", address, port));
    }
    
    private void sendData(String msg) throws IOException
    {
        // Initialize output stream
        OutputStream outStream = socket.getOutputStream();
        DataOutputStream out = new DataOutputStream(outStream);

        // send data to server
        System.out.println(String.format("Sending data to  %s:%d", address, port));
        out.writeUTF(msg);
    }

    private String receiveResponse() throws IOException
    {
        // Initalize input stream
        InputStream inStream = socket.getInputStream();
        DataInputStream in = new DataInputStream(inStream);

        // receive the data from the server
        String msgFromServer = in.readUTF();
        System.out.println(String.format("Received data from  %s:%d", address, port));
        return msgFromServer;
    }

}