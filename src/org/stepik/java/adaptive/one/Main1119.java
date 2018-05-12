package org.stepik.java.adaptive.one;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Map;
import java.util.stream.Stream;

import static java.util.stream.Collectors.*;

public class Main1119 {
    public static void main(String[] args) throws IOException {
        // put your code here
        String repeated = Stream.of(new BufferedReader(new InputStreamReader(System.in)).readLine().split(" "))
                .collect(groupingBy(s -> s, counting()))
                .entrySet().stream()
//                .peek(System.out::println)
                .filter(entry -> entry.getValue() >= 2L)
//                .map(entry -> entry.getKey())
                .map(Map.Entry::getKey)
                .sorted()
//                .peek(System.out::println)
                .collect(joining(" "));
        System.out.println(repeated);
    }
}
