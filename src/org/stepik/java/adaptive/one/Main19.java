package org.stepik.java.adaptive.one;

import java.util.Scanner;

import static java.lang.System.in;
import static java.lang.System.out;

public class Main19 {
    /*
    *  Given a two-digit number. Output its first digit (i.e. the number of tens).
    * */
    public static void main1(String[] args) {
        out.println(new Scanner(in).nextLine().length());
    }

    public static void main(String[] args) {
        // put your code here
        Scanner sc = new Scanner(System.in);
        String number = Integer.valueOf(sc.nextLine()).toString();
        System.out.println(number.length() > 1 ? String.valueOf(number.charAt(1)) : 0);
    }
}
