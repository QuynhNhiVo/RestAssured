package globals;

import helpers.PropertiesHelper;

public class ConfigGlobal {
    public static String URI = PropertiesHelper.getValue("URI");
    public static String USERNAME = PropertiesHelper.getValue("USERNAME");
    public static String PASSWORD = PropertiesHelper.getValue("PASSWORD");
    public static String HEADER_JSON = PropertiesHelper.getValue("HEADER_JSON");
}
