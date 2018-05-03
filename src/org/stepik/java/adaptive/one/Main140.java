package org.stepik.java.adaptive.one;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.stream.IntStream;

public class Main140 {
    /*
        Given a sequence of natural numbers, not exceeding 30000. Find the its maximum element divisible by 4.
        As input, the program gets the number of elements in the sequence, and then the elements themselves.
        In the sequence, there is always an element divisible by 4. The number of elements does not exceed 1000.
        The program should print the single number – the maximum element of the sequence divisible by 4.

        the first number N is the number of elements (in the example - 10). And then - exactly N numbers. You must read them all.
    */
    public static void main(String[] args) {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
            int countNumbers = numberFromStr(br.readLine());
            int[] numbers = new int[countNumbers];
            for (int i = 0; i < countNumbers; i++) {
                numbers[i] = numberFromStr(br.readLine());
            }
            int max = IntStream.of(numbers)
                    .filter(num -> num % 4 == 0)
                    .sorted()
                    .max()
                    .getAsInt();
            System.out.println(max);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static int numberFromStr(String readLine) {
        return Integer.valueOf(readLine);
    }
}