package org.stepik.java.adaptive.one;

import java.util.Scanner;

public class Main18 {
    /*
        Next even number
        Given a natural number N, not greater than 10000. Output the even number following this N.
    * */
    public static void main(String[] args) {
        int number = new Scanner(System.in).nextInt();
        System.out.println(number % 2 == 0 ? number + 2 : number + 1);
    }
}