package org.stepik.java.endava.two;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
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
        Scanner sc = new Scanner(System.in);
        Integer dim = sc.nextInt();
        Vector firstVector = new Vector(sc.nextLine().split(" "));
        Vector secondVector = new Vector(sc.nextLine().split(" "));
        System.out.println(firstVector.product(secondVector));
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
