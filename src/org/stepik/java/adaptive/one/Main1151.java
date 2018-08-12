package org.stepik.java.adaptive.one;

import java.util.Scanner;

public class Main1151 {
    /*
        Write a program that uses regular expressions to check whether the input string is a phone number.
        A phone number has the following form: 19∗∗∗∗∗∗∗∗∗, where ∗ is any number from 0 to 9, otherwise it's not a phone number.

        The program should print Yes or No.

        Sample Input:
            19123456789
        Sample Output:
            Yes
    */
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String input = sc.next();
        print(input.matches("^19[0-9]{9}") ? "Yes" : "No");
    }

    public static void print(Object object) {
        System.out.println(object);
    }
}
