package org.stepik.java.adaptive.one;

import java.util.Scanner;

import static java.lang.System.in;
import static java.lang.System.out;

public class Main110 {
    /*
    *  The number of tens
    *  Given a non-negative integer N (0 ≤ N ≤ 1000000), find the number of tens in it (i.e. next to last digit of the number).
    *  If there is no next to last digit, you can consider that the number of tens equals to zero.
    *
    *  Sample Input:
        173
        Sample Output:
        7
    * */
    public static void main1(String[] args) {
        out.println(new Scanner(in).nextLine().length());
    }

    public static void main(String[] args) {
        // put your code here
        Scanner sc = new Scanner(System.in);
        String number = Integer.valueOf(sc.nextLine().trim()).toString();
        System.out.println(number.length() > 1 ? String.valueOf(number.charAt(number.length() - 2)) : 0);
    }
}
