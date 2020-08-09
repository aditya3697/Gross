package src.server;

public final class ServerProperties {

    public static final String GROSS_SERVER_ADDRESS = "localhost";
    public static final String GROSS_SERVER_NAME = "Gross Server";
    public static final String GROSS_DEFAULT_SERVE_DIRECTORY = "./files";

    public static final int GROSS_SERVER_PORT = 9001;
    public static final int MAX_CLIENTS_BACKLOGS = 3;
    public static final int SERVER_GLOBAL_TIMEOUT = 2 * 60 * 1000;
    public static final int SERVER_GLOBAL_MAX_REQUESTS = 2;

}