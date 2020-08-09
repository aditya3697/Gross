package src.server.request;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.nio.file.Files;

import src.server.response.HttpResponse;
import src.server.response.HttpResponseHeader.ResponseHeader;
import src.server.ServerProperties;

public class HttpRequestHandler
{
    private HttpRequest request;
    HttpResponse response;

    public HttpRequestHandler(HttpRequest request) {
        this.request = request;

        response = new HttpResponse(request.getVersion());
    }

    public String handleGetRequest()
    {
        try {
            String filepath = ServerProperties.GROSS_DEFAULT_SERVE_DIRECTORY + request.getUri();
            System.out.println("Sending the file " + filepath);
            File file = new File(filepath);

            if(!file.exists()) {
                // throw a 404 error
                System.out.println("File Not Found");
                return response.getResponseString(404);
            }

            // Add Content-Type header
            String mimeType = Files.probeContentType(file.toPath());
            response.addHeader(ResponseHeader.CONTENT_TYPE, mimeType);

            // Read data from file
            FileInputStream fis = new FileInputStream(file);
            byte[] data = new byte[(int) file.length()];
            fis.read(data);
            fis.close();

            // Set request body
            response.setRequestBody(new String(data, "UTF-8"));
            
            // Add Content-Length header
            response.addHeader(ResponseHeader.CONTENT_LENGTH, Integer.toString(response.getRequestBody().length()));

            return response.getResponseString(200);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            return response.getResponseString(500);
        } catch(FileNotFoundException e) {
            e.printStackTrace();
            return response.getResponseString(500);
        } catch(IOException e) {
            e.printStackTrace();
            return response.getResponseString(500);
        } 
    }

    public void handlePostRequest()
    {

    }
}