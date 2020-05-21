import java.util.*;

public class Password {

    public static boolean passReq(String password) {
        boolean hasDouble = false;
        int digitGroup = 1;
        int lastDigit = 0;
        String[] sChars = password.split("");

        if (password.length() != 6) {
            return false;
        }

        for (String currentChar : sChars) {
            if (Integer.parseInt(currentChar) < lastDigit) {
                return false;
            }

            if (currentChar.equals(Integer.toString(lastDigit))) {
                digitGroup++;
            } else {
                if (digitGroup == 2) {
                    hasDouble = true;
                }
                digitGroup = 1;
            }
            lastDigit = Integer.parseInt(currentChar);
        }
        if (digitGroup == 2) {
            hasDouble = true;
        }

        return hasDouble;


    }

    public static void main(String[] args) {
        String minRange = "272091";
        String maxRange = "815432";
        int passCount = 0;

        for (int min = Integer.parseInt(minRange); min <= Integer.parseInt(maxRange); min++) {
            if (passReq(Integer.toString(min)) == true) {
                passCount++;
            }
        }

        System.out.println(passCount);


    }
}