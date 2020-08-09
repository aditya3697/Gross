package src.server.request;

public enum HttpRequestMethod
{
    GET("get"),
    POST("post"),
    PUT("put"),
    DELETE("delete");

    private String method;
    
    private HttpRequestMethod(String method) {
        this.method = method;
    }

    public static HttpRequestMethod valueOfMethod(String method) {
        for(HttpRequestMethod requestMethod: values()) {
            if (requestMethod.method.equals(method)) {
                return requestMethod;
            }
        }

        return null;
    }
}