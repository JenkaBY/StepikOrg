package org.stepik.java.endava.two;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Main25 {
    /*
        Read two vectors v1, v2 with same dimension N, and calculate their scalar product.
        Input:
        First line: n
        Second line: v1
        Thirst line: v2
        Output: Scalar product
    * */
    public static void main(String[] args) {
        String[] input = readStdIn(3);
        Integer dim = Integer.valueOf(input[0]);
        String[] first = input[1].split("\\s+");
        String[] second = input[2].split("\\s+");
        Vector firstVector = new Vector(first);
        Vector secondVector = new Vector(second);
        System.out.println(firstVector.product(secondVector));
    }

    public static String[] readStdIn(int readTimes) {
        String[] inputs = null;
        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
            inputs = new String[readTimes];
            for (int i = 0; i < readTimes; i++) {
                inputs[i] = br.readLine().trim();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return inputs;
    }

    public static class Vector {
        List<Integer> values;

        public Vector(List<Integer> values) {
            this.values = values;
        }

        public Vector(String[] values) {
            this.values = Arrays.stream(values)
                    .filter(s -> !s.trim().isEmpty())
                    .map(Integer::valueOf)
                    .collect(Collectors.toList());
        }

        private long product(Vector another) {
            assert (this.dimension() == another.dimension());
            long sum = 0;
            for (int i = 0; i < values.size(); i++) {
                sum += this.getElement(i) * another.getElement(i);
            }
            return sum;
        }

        public Integer getElement(int index) {
            return values.get(index);
        }

        public int dimension() {
            return this.values.size();
        }

    }
}
