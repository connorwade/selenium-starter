package config;

public class GlobalConfig {
    public static final int DEFAULT_WAIT_TIMEOUT = 60;
    public static final int DEFAULT_SCRIPT_TIMEOUT = 30;
    public static final int DEFAULT_IMPLICIT_TIMEOUT = 5;
    public static final int DEFAULT_LOAD_TIMEOUT = 30;

    public static final String AEM_AUTHOR_URL = System.getProperty("AEM_AUTHOR_URL","http://dcad1dlaemap011.ihgext.global:4502/");

    public static final String AEM_PASSWORD = System.getProperty("AEM_PASSWORD","admin");
    public static final String AEM_USER = System.getProperty("AEM_USER","admin");

    public static final boolean DEBUG_MODE = false;
}
