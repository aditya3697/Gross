package src.server.request;

import java.util.Map;
import java.util.HashMap;

import src.server.request.HttpRequestHeader.RequestHeader;

/**
 * Sample Http Request -
 * 
 * GET /public.html HTTP/1.1
 * Host: gross.com
 * Connection: keep-alive
 * User-Agent: Chrome/1.0
 * 
 */
public class HttpRequest {

    private String request;
    private RequestLine requestLine;
    private Map<RequestHeader, String> headers;
    private String requestBody;
    private boolean isRequestBodyExists;

    HttpRequest(String request)
    {
        this.request = request;
        requestLine = new RequestLine();
        headers = new HashMap<RequestHeader, String>();

        parseRequest();
    }

    private void parseRequest()
    {
        if(request == null || request.isEmpty()) {
            // nothing to parse
            return ;
        }

        String[] requestLines = request.split("\r\n");

        // first line corresponds to RequestLine
        parseRequestLine(requestLines[0]);

        // parse all headers and body
        parseHeaders(requestLines);

        // request body
        if(isRequestBodyExists) {
            requestBody = requestLines[requestLines.length - 1];
        }
    }

    private void parseHeaders(String[] requestLines)
    {
        int size = requestLines.length;

        // parse the headers
        int i = 1;
        for(;i < size; i++) {
            if(requestLines[i].equals("")) {
                // request body follows an empty body. so break here
                break;
            }

            String[] header = requestLines[i].split(": ");
            RequestHeader headerType = RequestHeader.valueOfHeaderType(header[0]);
            headers.put(headerType, header[1]);
        }
        
        if(i != size) {
            isRequestBodyExists = true;
        }
    }

    private void parseRequestLine(String requestFirstLine)
    {
        String[] args = requestFirstLine.split(" ");

        requestLine.method = HttpRequestMethod.valueOfMethod(args[0].toLowerCase());
        requestLine.uri = args[1];
        requestLine.version = args[2];
    }

    public static class RequestLine
    {
        public HttpRequestMethod method;
        public String uri;
        public String version;
    } 
    
}