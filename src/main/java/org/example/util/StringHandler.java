package org.example.util;

import org.apache.commons.codec.digest.Crypt;

public class StringHandler {
    public static String encryptString(String password) {
        String salt = "hGa5hu12fYafH";
        return Crypt.crypt(password, salt);
    }

    public static boolean checkIfEmail(String s) {
        return s.matches("^[-0-9a-zA-Z.+_]+@[-0-9a-zA-Z.+_]+\\.[a-zA-Z]{2,4}");
    }

    public static String toStringDate(int year, int month, int day) {
        return year + "-" + month + "-" + day;
    }
}
