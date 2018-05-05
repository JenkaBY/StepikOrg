package org.stepik.java.collections.framework.two;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Collections;
import java.util.stream.Collectors;

public class Main220 {
    /*
        Given string as "JButton Button JMenu..." to stdin of your programm

        Add all elements from this string to List<String>
        Next, using Stream API remove all items not starting with J and items beginning with J replace with the same elements
        but without the J ,for example JFrame -> Frame
        next, print all the remaining elements in reverse order(using .println())
        *in the lesson ListIterator. set() it was necessary to perform the same logic ,but using ListIterator. You can compare these 2 solutions
    * */
    private static final boolean INCLUSIVE = true;

    public static void main(String[] args) throws IOException {
        new BufferedReader(new InputStreamReader(System.in)).lines()
                .map((line) -> line.split(" "))
                .flatMap(Arrays::stream)
                .filter((word) -> word.startsWith("J"))
                .map((word) -> word.replace("J", ""))
                .collect(Collectors.collectingAndThen(
                        Collectors.toList(),
                        s -> {
                            Collections.reverse(s);
                            return s;
                        }
                ))
                .forEach(System.out::println);
    }
}
