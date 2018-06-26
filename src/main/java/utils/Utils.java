package main.java.utils;

import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Utils {

    public static boolean isNumeric(String str) {
        if (str == null || str.trim() == "") {
            return false;
        }

        try {
            int d = Integer.parseInt(str);
        } catch(NumberFormatException nfe) {
            return false;
        }

        return true;
    }

    private static final Pattern VALID_EMAIL_ADDRESS_REGEX =
            Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE);

    public static boolean validateEmail(String emailStr) {
        Matcher matcher = VALID_EMAIL_ADDRESS_REGEX .matcher(emailStr);
        return matcher.find();
    }

    public static void stringPrinter(String modelo, String... strings) {
        System.out.println(String.format(modelo, strings));
    }

    public static int randomInt(int min, int max) {
        Random random = new Random();
        return random.nextInt(max + 1 - min) + min;
    }
}
