package src.server.response;

import java.util.Map;
import java.util.HashMap;

import src.server.response.HttpResponseHeader.ResponseHeader;

public class HttpResponse
{
    private String version;
    private int statusCode;
    private String responseBody;
    
    private Map<ResponseHeader, String> responseHeaders = new HashMap<ResponseHeader, String>();

    public HttpResponse()
    {
        this.version = "HTTP/1.1";
    }

    public HttpResponse(String version) 
    {
        this.version = version;
    }

    public String getVersion()
    {
        return this.version;
    }

    public void setStatusCode(int statusCode) 
    {
        this.statusCode = statusCode;
    }

    public int getStatusCode() 
    {
        return this.statusCode;
    }

    public String getResponseBody()
    {
        return this.responseBody;
    }

    public void setResponseBody(String responseBody) 
    {
        this.responseBody = responseBody;
    }

    public Map<ResponseHeader, String> getResponseHeaders()
    {
        return this.responseHeaders;
    }

    public void addHeader(ResponseHeader headerType, String headerValue) 
    {
        responseHeaders.put(headerType, headerValue);
    }

    public String getResponseString(int statusCode)
    {
        this.statusCode = statusCode;
        HttpResponseBuilder responseBuilder = new HttpResponseBuilder(this);
        return responseBuilder.getResponseString();
    }

}