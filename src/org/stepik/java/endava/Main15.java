package org.stepik.java.endava;

import java.util.Scanner;

public class Main15 {
    /*
        Kaladont
        Read two strings s1 and s2 from the standard input. Check if the last two letters of the first string are the same as the first two letters of the second string.
        The strings will always have length > 2 and containing small letters.
        Input: two strings s1, s2
        Output: true if the last two letters of the first string are the same as the first two letters of the second string.
    * */
    public static void main(String[] args) {
        String[] input = new Scanner(System.in).nextLine().split(" ");
        System.out.println(input[0].endsWith(input[1].substring(0, 2)));
    }
}
