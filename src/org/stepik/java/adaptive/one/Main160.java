package org.stepik.java.adaptive.one;

import java.util.Map;
import java.util.Scanner;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Main160 {
    public static void main(String[] args) {
        String[] input = new Scanner(System.in).nextLine().split(" ");
        Map<String, Long> grouped = Stream.of(input)
                .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));
        System.out.println((float) grouped.getOrDefault("A", 0L) / input.length);
    }
}
