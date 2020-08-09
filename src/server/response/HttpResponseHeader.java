package src.server.response;

public class HttpResponseHeader
{

    public enum ResponseHeader
    {
        SERVER("Server"),
        DATE("Date"),
        CONTENT_TYPE("Content-type"),
        CONTENT_LENGTH("Content-length");

        private String header;

        private ResponseHeader(String header) {
            this.header = header;
        }

        public static ResponseHeader valueOfHeaderType(String headerType) {
            for(ResponseHeader responseHeader: values()) {
                if (responseHeader.header.equals(headerType)) {
                    return responseHeader;
                }
            }
    
            return null;
        }
    }

}