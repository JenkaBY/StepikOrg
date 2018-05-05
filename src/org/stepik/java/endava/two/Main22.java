package org.stepik.java.endava.two;

import java.util.Scanner;

public class Main22 {
    /*
        Check if a given string is a palindrome or not.
        Output: The string, if it is a palindrome, "Not" otherwise.
    * */
    public static void main(String[] args) {
        String input = new Scanner(System.in).nextLine();
        System.out.println(input.equals(new StringBuilder(input).reverse().toString()) ?
                input : "Not");
    }
}
