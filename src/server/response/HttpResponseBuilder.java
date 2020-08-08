package src.server.response;

import java.util.Date;
import java.text.SimpleDateFormat;

import src.server.ServerProperties;
import src.server.response.ContentTypes.ContentType;

public class HttpResponseBuilder {

    /**
     * sample response - 
     * 
     * HTTP/1.1 200 OK
     * Server: GrossServer
     * Date: Sat, 8 Aug 2020 22:58:56 IST
     * Content-type: text/html; charset=UTF-8
     * Content-Length: 24
     *
     * Response from the server
     * 
     */
    public static String getResponse(String responseBody)
    {
        return getResponse(responseBody, 200);
    }

    public static String getResponse(String responseBody, int statusCode)
    {
        return getResponse(responseBody, statusCode, ContentType.HTML);
    }

    public static String getResponse(String responseBody, int statusCode, ContentType contentType)
    {
        return getResponse(responseBody, statusCode, contentType, getServerName());
    }

    public static String getResponse(String responseBody, int statusCode, ContentType contentType, String serverName)
    {
        StringBuffer response = new StringBuffer("");
        try
        {
            response.append(String.format("HTTP/1.1 %d %s\r\n", statusCode, getHttpResponseState(statusCode)));
            
            // TODO: update with isNullOrEmpty method with appropriate import
            if(serverName == null || serverName.equals("")) {
                serverName = getServerName();
            }
            response.append(String.format("Server: %s\r\n", serverName));

            response.append(String.format("Date: %s\r\n", getDate()));
            
            if(contentType != null && !contentType.equals("")) {
                response.append(String.format("Content-type: %s\r\n", ContentTypes.getContentType(contentType)));
            }
            
            if(responseBody != null) {
                response.append(String.format("Content-Length: %s\r\n", getContentLength(responseBody)));
            }

            response.append(String.format("\r\n"));

            response.append(responseBody);
        } catch (Exception e){
            e.printStackTrace();
            // throw a 500 if the status code is not already 500
            if(statusCode/100 != 5) {
                // respond with an internal error msg
                getResponse("We screwed up!!", 500);
            }
        }
        

        return response.toString();
    }

    // returns the reason phrase for the provided http status code
    public static String getHttpResponseState(int statusCode) throws Exception
    {
        return StatusCodes.getReasonPhrase(statusCode);
    }

    // return the current date in sample format - Sat, 08 Aug 2020 22:58:56 IST
    public static String getDate()
    {
        SimpleDateFormat formatter = new SimpleDateFormat("E, dd MM yyyy hh:mm:ss zzz");
        return formatter.format(new Date());
    }

    public static String getServerName()
    {
        return ServerProperties.serverAddress;
    }

    public static int getContentLength(String responseBody)
    {
        return responseBody.length();
    }

}