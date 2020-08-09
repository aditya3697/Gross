package src.server.request;

import java.io.File;
import java.io.FileInputStream;

import src.server.response.HttpResponse;
import src.server.ServerProperties;

public class HttpRequestHandler
{
    private HttpRequest request;

    public HttpRequestHandler(HttpRequest request) {
        this.request = request;
    }

    public String handleGetRequest()
    {
        HttpResponse response = new HttpResponse();

        try {
            String filepath = ServerProperties.GROSS_DEFAULT_SERVE_DIRECTORY + request.getUri();
            System.out.println("Sending the file " + filepath);
            File file = new File(filepath);

            if(!file.exists()) {
                // throw error
                System.out.println("File Not Found");
                return null;
            }

            FileInputStream fis = new FileInputStream(file);
            byte[] data = new byte[(int) file.length()];
            fis.read(data);
            fis.close();

            return (new String(data, "UTF-8"));
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

    }

    public void handlePostRequest()
    {

    }
}