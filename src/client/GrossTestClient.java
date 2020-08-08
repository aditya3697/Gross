package src.client;

public class GrossTestClient {

    public static void main(String[] args) 
    {
        HttpClient client = new HttpClient(ClientProperties.GROSS_SERVER_PORT, ClientProperties.GROSS_SERVER_ADDRESS);

        String response = client.sendRequest("Hello from client...");

        System.out.println(response);
    }

}