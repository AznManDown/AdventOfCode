import java.util.*;

public class Password {

    public static boolean passReq(String password) {
        boolean hasDouble;
        int lastDigit = 0;
        String[] sChars = password.split("");

        if (password.length() != 6) {
            return false;
        }

        for (String currentChar : sChars) {
            if (Integer.parseInt(currentChar) < lastDigit) {
                return false;
            }

            if (currentChar.equals(lastDigit)) {
                hasDouble = true;
            }

            lastDigit = Integer.parseInt(currentChar);
        }

        return true;


    }

    public static void main(String[] args) {
        String minRange = "272091";
        String maxRange = "815432";

        System.out.println(passReq("272091"));


    }
}