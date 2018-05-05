package org.stepik.java.endava.two;

import java.util.Arrays;
import java.util.Scanner;

public class Main21 {
    /*
        Check if a number is a happy number. Happy number is a number that contains only the digits 5, 6 or 7.
        Output: "Happy" if the number is happy, or "Sad" otherwise.
    * */
    public static void main(String[] args) {
        System.out.println(Arrays.stream(new Scanner(System.in).nextLine().split(""))
                .allMatch((num) -> Arrays.asList("5", "6", "7").contains(num)) ?
                "Happy" : "Sad");
    }
}
