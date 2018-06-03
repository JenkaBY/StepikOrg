package org.stepik.java.contest.three;

import java.util.ArrayList;
import java.util.List;

/*
    You are provided with the backbone of ListMultiplicator class that has multiply method that takes list and
    multiplies its content specified number of times. The original list content should be changed after method returns.
     The task is to add implementation to the method without changing its signature.
    You are guaranteed that:
        list is non null
        n is equals or greater than zero
        ﻿n stands for number of times the original list repeated in the result. So if n equals zero the resulting list
         should be empty.
    For original list:
        [1, 2, 3]
    and n equals 2 the result should be:
        [1, 2, 3, 1, 2, 3]
* */
public class Main13 {
    /**
     * Class to modify
     */
    static class ListMultiplicator {

        /**
         * Multiplies original list provided numper of times
         *
         * @param list list to multiply
         * @param n    times to multiply, should be zero or greater
         */
        public static void multiply(List<?> list, int n) {
            // Add implementation here
            List multiplied = new ArrayList();
            for (int i = 0; i < n; i++) {
                multiplied.addAll(list);
            }
            list.clear();
            list.addAll(multiplied);
        }
    }

    public static void main(String[] args) {
        List<String> str = new ArrayList<>();
        str.add("A");
        str.add("B");
        ListMultiplicator.multiply(str, 3);
        str.forEach(System.out::println);
    }
}
