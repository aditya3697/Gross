package src.server.response;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

import src.server.ServerProperties;
import src.server.response.ContentTypes.ContentType;
import src.server.response.HttpResponseHeader.ResponseHeader;

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
public class HttpResponseBuilder {

    private HttpResponse response;
    
    public HttpResponseBuilder(HttpResponse response)
    {
        this.response = response;
    }

    public String getResponseString()
    {
        try {
            StringBuffer responseBuffer = new StringBuffer("");

            // response first line
            responseBuffer.append(String.format("%s %d %s\r\n", response.getVersion(), response.getStatusCode(), getHttpResponseState(response.getStatusCode())));

            // add headers
            addHeadersToResponse(responseBuffer);

            // Add response body if exists
            addResponseBody(responseBuffer);

            return responseBuffer.toString();
        } catch (Exception e) {
            e.printStackTrace();
            if(response.getStatusCode() / 100 == 5) {
                return null;
            }
            return internalServerException();
        }
        
    }

    private void addHeadersToResponse(StringBuffer responseBuffer)
    {
        Map<ResponseHeader, String> responseHeaders = response.getResponseHeaders();

        setDefaultHeaderValues(responseHeaders);

        for(ResponseHeader responseHeader : responseHeaders.keySet())
        {
            responseBuffer.append(String.format("%s: %s\r\n", responseHeader.getHeader(), responseHeaders.get(responseHeader)));
        }
    }

    private void addResponseBody(StringBuffer responseBuffer)
    {
        String responseBody = response.getResponseBody();
        if(responseBody != null && !responseBody.isEmpty())
        {
            // Add an empty line
            responseBuffer.append("\r\n");

            // Add response body
            responseBuffer.append(responseBody);
        }
    }

    private void setDefaultHeaderValues(Map<ResponseHeader, String> responseHeaders)
    {
        // Set Server name
        if(!responseHeaders.containsKey(ResponseHeader.SERVER))
        {
            responseHeaders.put(ResponseHeader.SERVER, getServerName());
        }

        // Set Date
        if(!responseHeaders.containsKey(ResponseHeader.DATE))
        {
            responseHeaders.put(ResponseHeader.DATE, getDate());
        }
    }

    // returns the reason phrase for the provided http status code
    public static String getHttpResponseState(int statusCode) throws Exception
    {
        return StatusCodes.getReasonPhrase(statusCode);
    }

    // return the current date in sample format - Sat, 08 Aug 2020 22:58:56 IST
    public static String getDate()
    {
        SimpleDateFormat formatter = new SimpleDateFormat("E, dd MMM yyyy hh:mm:ss zzz");
        return formatter.format(new Date());
    }

    public static String getServerName()
    {
        return ServerProperties.GROSS_SERVER_NAME;
    }

    public static int getContentLength(String responseBody)
    {
        return responseBody.length();
    }

    private String internalServerException() {
        // change the status code of the current response to 500 and resend the response
        this.response = new HttpResponse();
        response.setStatusCode(500);

        return getResponseString();
    }

}