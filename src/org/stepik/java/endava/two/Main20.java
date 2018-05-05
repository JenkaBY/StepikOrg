package org.stepik.java.endava.two;

import java.util.Scanner;

public class Main20 {
    /*
        Read a number n from the standard input.
        Print a message based on the condition:
        n < 0   "Negative number"
        n = 0   "Zero"
        n > 0 "Positive number"
    * */
    public static void main(String[] args) {
        Integer num = new Scanner(System.in).nextInt();
        String res = "Zero";
        switch (Integer.valueOf(new Scanner(System.in).nextInt()).compareTo(0)) {
            case -1: {
                res = "Negative number";
                break;
            }
            case 1: {
                res = "Positive number";
                break;
            }
        }
        System.out.println(res);
    }
}
