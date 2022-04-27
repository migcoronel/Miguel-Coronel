package com.example.whatsappgroupapi.utils;

public class Utils {

    public static boolean isLong(String string){
        if (string == null) {
            return false;
        }

        try {
            double d = Long.parseLong(string);
            return true;
        } catch (NumberFormatException nfe) {
            return false;
        }
    }
}
