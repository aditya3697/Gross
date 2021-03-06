package src.server.response;

import java.util.Map;
import java.util.HashMap;

public class ContentTypes
{
    public static Map<ContentType, String> contentTypes;
    static {
        contentTypes = new HashMap<ContentType, String>();
        contentTypes.put(ContentType.PLAIN_TEXT, "text/plain; charset=utf-8");
        contentTypes.put(ContentType.HTML, "text/html; charset=utf-8");
    }

    public enum ContentType {
        PLAIN_TEXT,
        HTML
    }

    public static String getContentType(ContentType contentType) {
        return contentTypes.get(contentType);
    }
}