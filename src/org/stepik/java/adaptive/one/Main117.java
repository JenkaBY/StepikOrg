package org.stepik.java.adaptive.one;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main117 {
    /*
        Desks
            Some school have decided to create three new math groups and equip classrooms for them with the new desks.
            Only two students may sit at any desk. The number of students in each of the three groups is known.
            Output the smallest amount of desks, which will need to be purchased. Each new group sits in its own classroom.
        Input data format
            The program receives the input of the three non-negative integers: the number of students in each of the three
            classes (the numbers do not exceed 1000).
    */
    public static void main(String[] args) {
        final int sum = Arrays.stream(readStdIn(3))
                .mapToInt(Integer::valueOf)
                .map(num -> num / 2 + num % 2)
                .sum();
        print(sum);
    }

    public static void print(Object object) {
        System.out.println(object.toString());
    }

    public static Long numberFromStr(String readLine) {
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