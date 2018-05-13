package org.stepik.java.adaptive.one;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.stream.Stream;

public class Main169 {
    /*
        Find the sum of two numbers.
    */

    public static void main(String[] args) {
        String[] number = readStdIn(1);

        print(Stream.of(number[0].split("\\s+"))
                .mapToLong(Long::valueOf)
                .sum());
    }

    public static void print(Object object) {
        System.out.println(object);
    }

    public static long numberFromStr(String readLine) {
        return Long.valueOf(readLine);
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
