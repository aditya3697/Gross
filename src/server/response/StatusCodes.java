package src.server.response;

import java.util.Map;
import java.util.HashMap;

public class StatusCodes
{
    public static Map<Integer, String> codes;

    static
    {
        codes = new HashMap<Integer, String>();

        // 200's
        codes.put(200, "OK");
        codes.put(201, "");

        // 400's
        codes.put(404, "Not Found");

        // 500's
        codes.put(500, "Internal Server Error");
    }

    public static String getReasonPhrase(int statusCode) throws Exception {
        if(codes.containsKey(statusCode)) {
            return codes.get(statusCode);
        } 

        throw new Exception(String.format("Status Code %d is not supported yet!!", statusCode));
    }

}