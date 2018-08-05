package org.stepik.java.adaptive.one;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.stream.IntStream;

public class Main140Another {
    /*
        Given the list of integers, which may contain up to 100,000 numbers. Find how many different numbers are in this list.
            Input data
            Integer N - the number of elements in the list, and then the N numbers.
        Sample Input:
            5
            1 2 3 2 1
        Sample Output:
            3
    */
    public static void main(String[] args) {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
            int countNumbers = numberFromStr(br.readLine());
            int[] numbers = new int[countNumbers];
            String[] numbersStr = br.readLine().split(" ");
            for (int i = 0; i < countNumbers; i++) {
                numbers[i] = numberFromStr(numbersStr[i]);
            }
            long count = IntStream.of(numbers)
                    .distinct()
                    .count();
            System.out.println(count);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static int numberFromStr(String readLine) {
        return Integer.valueOf(readLine);
    }
}