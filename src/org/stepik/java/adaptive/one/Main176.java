package org.stepik.java.adaptive.one;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main176 {
    /*
        1.76 Uppercase
        Input a single character and change its register.
        That is, if the lowercase letter has been entered – make it uppercase, and vice versa.
        Characters that are not Latin ones need to stay unchanged.
    */
    public static void main(String[] args) {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
            String readLine = br.readLine();
            if (readLine.isEmpty()) return;

            String chr = readLine.substring(0, 1);
            String result = chr;
            String upperCase = "[A-Z]";
            String lowerCase = "[a-z]";
            if (chr.matches("\\p{IsLatin}")) {
                if (chr.matches(upperCase)) {
                    result = chr.toLowerCase();
                } else if (chr.matches(lowerCase)) {
                    result = chr.toUpperCase();
                }
            }
            System.out.println(result);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}