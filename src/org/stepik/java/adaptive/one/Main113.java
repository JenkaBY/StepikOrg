package org.stepik.java.adaptive.one;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigInteger;

public class Main113 {
    /*
        Write a program, which reads two integers a and b (separated by a line break), and outputs the value of ab.
        The operator ** is used in Python for exponentiation.
    */

    public static void main(String[] args) {
        String[] numbers = readStdIn(2);

        print(new BigInteger(numbers[0]).pow(numberFromStr(numbers[1])));
    }

    public static void print(Object object) {
        System.out.println(object);
    }

    public static int numberFromStr(String readLine) {
        return Integer.valueOf(readLine);
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
