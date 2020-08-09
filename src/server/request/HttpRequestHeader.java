package src.server.request;

public class HttpRequestHeader
{
    
    public enum RequestHeader
    {
        ACCEPT("Accept"),
        ACCEPT_ENCODING("Accept-Encoding"),
        ACCEPT_LANGUAGE("Accept-Language"),
        REFERER("Referer"),
        AUTHORIZATION("Authorization"),
        CHARGE_TO("Charge-To"),
        IF_MODIFIED_SINCE("If-Modified-Since"),
        PRAGMA("Pragma"),

        // Request context
        FROM("From"),
        USER_AGENT("User-Agent"),
        HOST("Host"),

        // Connection management
        CONNECTION("Connection"),
        KEEP_ALIVE("Keep-Alive");

        private String header;

        private RequestHeader(String header) {
            this.header = header;
        }

        public static RequestHeader valueOfHeaderType(String headerType) {
            for(RequestHeader requestHeader: values()) {
                if (requestHeader.header.equals(headerType)) {
                    return requestHeader;
                }
            }
    
            return null;
        }

    }

}