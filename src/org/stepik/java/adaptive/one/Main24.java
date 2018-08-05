package org.stepik.java.adaptive.one;

import java.util.Scanner;

public class Main24 {
    /*
        Symmetrical number
        Given a four-digit number. Determine whether its decimal notation is symmetric. If the number is symmetrical, output 1,
        otherwise output any other integer. The number may have less than four digits, then you should assume that its decimal notation
        is complemented by insignificant zeros on the left.

        Sample Input 1:
            2002
        Sample Output 1:
            1
        Sample Input 2:
            2008
        Sample Output 2:
            37
    * */
    public static void main(String[] args) {
        StringBuilder number = leftZeroPad(new Scanner(System.in).nextLine());
        System.out.println(number.toString().equals(number.reverse().toString()) ? 1 : 0);
    }

    public static StringBuilder leftZeroPad(String input) {
        StringBuilder sb = new StringBuilder();
        while (input.length() + sb.length() < 4) {
            sb.append("0");
        }
        return sb.append(input);
    }
}
