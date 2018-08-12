package org.stepik.java.adaptive.one;

import java.util.Scanner;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class Main1148 {
    /*
        Given positive integer N. Find the number of positive integers less than N such that their sum of digits
        (in decimal notation) is equal to the sum of digits in the number N. Output the number of such integers.
        Sample Input:
            123
        Sample Output:
            9
    */
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        final Integer input = sc.nextInt();
        final int sumOfInputDigit = getSumOfDigits(input);
        print(IntStream.range(1, input).filter(integer -> getSumOfDigits(integer) == sumOfInputDigit).count());
    }

    public static int getSumOfDigits(final Integer number) {
        return Stream.of(String.valueOf(number).split(""))
                .mapToInt(num -> numberFromStr(num))
                .sum();
    }

    public static void print(Object object) {
        System.out.println(object);
    }

    public static int numberFromStr(String readLine) {
        return Integer.valueOf(readLine);
    }
}
