package org.stepik.java.contest.four;

import java.util.*;

import static java.lang.System.out;
/*
* There is a queue of 4 elements. Put in it one more element - the number 5, and then get 2 items out of the queue.
* */

public class Main422 {

    public static void main(String[] args) {

        Deque<Integer> queue = new ArrayDeque<>(Arrays.asList(1, 2, 3, 4));

        //write your code here
        queue.add(5);
        queue.pop();
        queue.pop();

        out.println(queue);
        Map<String, Integer> map = new TreeMap<>();
    }

    public static void main(int[] args) {
        System.out.printf("I'm the main");
    }
}