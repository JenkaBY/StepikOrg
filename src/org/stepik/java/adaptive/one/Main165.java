package org.stepik.java.adaptive.one;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Main165 {
    /*
    * Output the prime factorization of the natural number n > 1. Prime multipliers should be listed in ascending order and separated by spaces.
    * */
    public static void main(String[] args) {
        int input = new Scanner(System.in).nextInt();
        List<Integer> factors = getFactors(input);
        print(factors.stream()
                .map(String::valueOf)
                .collect(Collectors.joining(" ")));
    }

    public static List<Integer> getFactors(int number) {
        List<Integer> factors = new ArrayList<>();
        int tempFactor = number;
        while (multiply(factors) != number) {
            final int factor = getFirstDivisor(tempFactor);
            factors.add(factor);
            tempFactor = tempFactor / factor;
        }
        return factors;
    }

    public static int getFirstDivisor(int number) {
        return IntStream.rangeClosed(2, number)
                .filter(num -> number % num == 0)
                .findFirst()
                .orElse(number);
    }

    public static int multiply(List<Integer> numbers) {
        return numbers.stream()
                .reduce(1, (a, b) -> a * b);
    }

    public static void print(Object object) {
        System.out.println(object);
    }
}
