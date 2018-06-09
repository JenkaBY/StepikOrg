package org.stepik.java.adaptive.one;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Main183 {
    /*
        Given the natural number 1≤n≤105 and the array of integers A[1…n], not exceeding 109 by absolute value.
         Output 1, if the array A contains the number occurring strictly more than n/2 times, and output 0 otherwise.

        Sample Input:
            5
            2 3 9 2 2
        Sample Output:
            1
    */

    public static void main(String[] args) {
        String[] input = readStdIn(2);
        final Integer number = numberFromStr(input[0]);
        final Map<Long, Long> numbers = Stream.of(input[1].split("\\s+"))
                .mapToLong(Long::valueOf)
                .boxed()
                .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));
        long count = numbers.values().stream().filter(num -> num > number / 2).count();
        print(count > 0 ? 1 : 0);
    }

    public static void print(Object object) {
        System.out.println(object);
    }

    public static int numberFromStr(String readLine) {
        return Integer.valueOf(readLine.trim());
    }

    public static String[] readStdIn(int times) {
        String[] inputs = null;
        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
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
