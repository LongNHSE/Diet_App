package com.example.diet.util;

public class Utils {

    public static String formatNumberWithDelimiters(int number) {
        // Use StringBuilder for efficient string manipulation
        StringBuilder sb = new StringBuilder();
        String numberString = String.valueOf(number);
        int length = numberString.length();


        // Iterate over the number string in reverse order
        for (int i = length - 1, count = 1; i >= 0; i--, count++) {
            sb.append(numberString.charAt(i));
            // Append a comma after every three digits, except after the last digit
            if (count % 3 == 0 && i != 0) {
                sb.append(',');
            }
        }


        // Reverse the string to get the correct order
        return sb.reverse().toString();
    }

}
