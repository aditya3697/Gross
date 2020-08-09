package src.server.response;

import java.util.Map;
import java.util.HashMap;

import src.server.response.HttpResponseHeader.ResponseHeader;

public class HttpResponse
{
    private String version;
    private int statusCode;
    private String requestBody;
    
    private Map<ResponseHeader, String> responseHeaders = new HashMap<ResponseHeader, String>();

    public HttpResponse(String version) 
    {
        this.version = version;
    }

    public void setStatusCode(int statusCode) 
    {
        this.statusCode = statusCode;
    }

    public String getRequestBody()
    {
        return this.requestBody;
    }

    public void setRequestBody(String requestBody) 
    {
        this.requestBody = requestBody;
    }

    public void addHeader(ResponseHeader headerType, String headerValue) 
    {
        responseHeaders.put(headerType, headerValue);
    }

    public String getResponseString(int statusCode)
    {
        return null;
    }

}