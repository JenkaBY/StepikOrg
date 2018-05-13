package org.stepik.java.adaptive.one;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.LongStream;

public class Main1156 {
    /*
    Write a program to calculate the integer-valued logarithm to base 2 (binary logarithm).
    Input – first line contains an integer T, next go the T lines with tests. Each tests consists of one positive integer ai<109.
    For each ai you need to output on a separate line such largest number p, that 2p≤ai.
    It is guaranteed that ai≥1. While solving this problem, you can define any functions you need any.
    Moreover, it is recommended to put the calculation of the logarithm into a separate function.
    */
    private static final int MAX = 1_000_000_000;

    public static void main(String[] args) {
        final List<Long> poweredNumbers = poweredNumbers(2);
        Arrays.stream(readStdIn())
                .map(Long::valueOf)
                .forEach(num -> System.out.println(poweredNumbers.indexOf(closestToNumber(poweredNumbers, num))));
//        print("");
//        poweredNumbers(2).forEach(System.out::println);
    }

    public static Long closestToNumber(List<Long> poweredNumbers, long number) {
        return poweredNumbers.parallelStream()
                .filter(num -> num <= number)
                .mapToLong(Long::intValue)
                .max()
                .orElse(number);
    }

    public static List<Long> poweredNumbers(int power) {
        return LongStream.iterate(0, i -> i + 1)
                .map(num -> (long) Math.pow(power, num))
                .limit(30)
                .filter(i -> i <= MAX)
                .boxed()
                .collect(Collectors.toList());
    }

    public static void print(Object object) {
        System.out.println(object);
    }

    public static int numberFromStr(String readLine) {
        return Integer.valueOf(readLine);
    }

    public static String[] readStdIn() {
        String[] inputs = null;
        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
            int times = numberFromStr(br.readLine().trim());
            inputs = new String[times];
            for (int i = 0; i < times; i++) {
                inputs[i] = br.readLine().trim();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return inputs;
    }
}
