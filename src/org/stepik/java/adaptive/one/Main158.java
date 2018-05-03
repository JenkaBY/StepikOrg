package org.stepik.java.adaptive.one;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main158 {
    /*
        Given a string. Find whether it is a palindrome, i.e. it reads the same both left-to-right and right-to-left.
        Output “yes” if the string is a palindrome and “no” otherwise.
    */
    public static void main(String[] args) {
        String input = readOneFromStdIn();
        print(input.equals(reverse(input)) ? "yes" : "no");
    }

    public static String reverse(String word) {
        return new StringBuilder(word).reverse().toString();
    }

    public static void print(Object object) {
        System.out.println(object.toString());
    }

    public static String readOneFromStdIn() {
        String input = null;
        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
            input = br.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return input;
    }
}
