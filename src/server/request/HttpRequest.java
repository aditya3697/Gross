package src.server.request;

import java.io.*;
import java.util.Map;
import java.util.HashMap;

import src.server.request.HttpRequestHeader.RequestHeader;

/**
 * Sample Http Request -
 * 
 * GET /index.html HTTP/1.1
 * Host: gross.com
 * Connection: keep-alive
 * User-Agent: Chrome/1.0
 * 
 */
public class HttpRequest {

    private String uri;
    private String version;
    private HttpRequestMethod method;
    private BufferedReader request;
    private Map<RequestHeader, String> headers;
    private String requestBody;
    private boolean isRequestBodyExists;

    public HttpRequest(BufferedReader request) throws IOException
    {
        this.request = request;
        headers = new HashMap<RequestHeader, String>();

        parseRequest();
    }

    public String getUri() {
        return this.uri;
    }

    private void parseRequest() throws IOException, UnsupportedOperationException
    {
        if(request == null) {
            // nothing to parse
            return ;
        }

        // first line corresponds to RequestLine
        parseRequestLine(request.readLine());

        // parse all headers
        parseHeaders();

        // request body
        if(isRequestBodyExists) {
            // Request body is not supported yet !!

            // requestBody = request.readLine();
            // System.out.println("Request body: "+ requestBody);
        }

        System.out.println("Completed parsing the request");
    }

    private void parseHeaders() throws IOException
    {
        while(true) {
            String line = request.readLine();
            System.out.println("Header line: "+ line);

            if(line == null) {
                // no more lines to read and request body doesn't exist
                isRequestBodyExists = false;
                System.out.println("Encountered a null line. Marking end of request");
                break;
            }

            if(line.equals("")) {
                // End of request headers
                // followed by request body
                isRequestBodyExists = true;
                System.out.println("Encountered an empty line. Will attempt to read response body next.");
                break;
            }

            // parse the header
            String[] header = line.split(": ");
            RequestHeader headerType = RequestHeader.valueOfHeaderType(header[0]);
            if(headerType != null) {
                headers.put(headerType, header[1]);
            }
        }

        System.out.println("Completed parsing the headers");
    }

    private void parseRequestLine(String requestFirstLine) throws UnsupportedOperationException
    {
        String[] args = requestFirstLine.split(" ");
        System.out.println("Request first line: "+ requestFirstLine);

        this.method = HttpRequestMethod.valueOfMethod(args[0].toLowerCase());

        if(method == null) {
            throw new UnsupportedOperationException(String.format("Http Method %s is not supported yet.", args[0]));
        }

        this.uri = args[1];

        // default to HTTP/1.1 if not specified
        if(args.length >= 2) {
            this.version = args[2];
        } else {
            this.version = "HTTP/1.1";
        }
    }

    public String handleRequest() throws UnsupportedOperationException
    {
        HttpRequestHandler requestHandler = new HttpRequestHandler(this);
        switch(method)
        {
            case GET :
                return requestHandler.handleGetRequest();
            default :
                throw new UnsupportedOperationException(String.format("Http Method %s is not supported yet.", method.toString()));
        }
    }
    
}