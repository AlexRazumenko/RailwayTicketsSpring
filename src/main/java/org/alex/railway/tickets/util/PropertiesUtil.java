package org.alex.railway.tickets.util;

public class PropertiesUtil {

    private static String propertySources = "String.format(\"classpath:message_%s.properties\", Locale.getDefault().getCountry())";


    public static String getPropertySources() {
        return propertySources;
    }
}
