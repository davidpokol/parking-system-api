package com.davidpokolol.parkingsystemapi.util;

public class StringUtil {

    public static String lowerCaseFirstCharacter(final String str) {
        if (str == null || str.isBlank()) {
            return null;
        }
        return Character.toLowerCase(str.charAt(0)) + str.substring(1);
    }
}
