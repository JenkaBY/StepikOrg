package org.stepik.java.adaptive.one;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Main1123 {
    /*
        Find the indexes of the initial appearance of the maximum element in a matrix.
        Input data format
            On the input, the program receives the size of matrix n and m, and then n lines having m integer numbers in
            each. n and m do not exceed 100.
        Output data format
            Output two numbers: the row number and the column number, in which the greatest item in the two-dimensional
            array (matrix) is located. If there are several such elements, output the one, which has the smaller row number;
            and if row numbers are the same - the one having the smaller column number.
    */

    public static void main(String[] args) {
        String[] input = readStdIn(1);
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
