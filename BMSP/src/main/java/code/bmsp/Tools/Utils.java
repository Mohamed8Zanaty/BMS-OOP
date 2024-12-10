package code.bmsp.Tools;

import java.util.Random;
import java.util.regex.Pattern;

public class Utils {
    private static Random random = new Random();
    public static String generateRandomString(int size) {
        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < size; i++) {
            sb.append(random.nextInt(10));
        }
        return sb.toString();
    }
    public static String generateRandomCreditCardNumber() {
        return generateRandomString(15);
    }
    public static String generateCvv() {
        return generateRandomString(3);
    }
    public static boolean validAmount(String amount) {
        try {
            Float.parseFloat(amount);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }
    public static boolean checkPattern(String value, String regex) {
        Pattern pattern = Pattern.compile(regex);
        return pattern.matcher(value).matches();
    }
}
