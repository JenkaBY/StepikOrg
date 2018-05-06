package org.stepik.java.endava.two;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Main26 {
    /*
        Write a program than reads N numbers and prints the numbers that occur exactly two times (pairs).
        Input: N, Array of N numbers
        Output: A list of numbers that occurred only twice (pairs) separated by a blank space. Print them in order that they are read.
        In case there are none print "None"
    * */
    public static void main(String[] args) {
        String[] input = readStdIn(2);
        String[] array = input[1].split("\\s+");
        List<String> result = buildResultArray(combine(array));
        System.out.println(result.size() > 0 ? result.stream().collect(Collectors.joining(" ")) : "None");
    }

    public static Map<String, Long> combine(String[] array) {
        return Arrays.stream(array)
                .collect(Collectors.groupingBy(s -> s, Collectors.counting()));
    }

    public static List<String> buildResultArray(Map<String, Long> groupedArray) {
        List<String> result = new ArrayList<>();
        groupedArray.forEach((k, v) -> {
            if (v == 2) {
                result.add(k);
            }
        });
        return result;
    }

    public static String[] readStdIn(int readTimes) {
        String[] inputs = null;
        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
            inputs = new String[readTimes];
            for (int i = 0; i < readTimes; i++) {
                inputs[i] = br.readLine().trim();
                if (inputs[0].equals("0")) {
                    Arrays.fill(inputs, "0");
                    break;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return inputs;
    }

}
