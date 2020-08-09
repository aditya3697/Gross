package src.server.response;

import java.util.Map;
import java.util.HashMap;

import src.server.response.HttpResponseHeader.ResponseHeader;

public class HttpResponse
{

    public String version;
    public String serverName;
    public Map<ResponseHeader, String> responseHeaders = new HashMap<ResponseHeader, String>();

    public HttpResponse()
    {
        
    }

}