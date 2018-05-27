package org.stepik.java.endava.six;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Main61 {

    public static void main(String[] args) {
        LinkedList<String> inputs = readStdIn();

        List<Integer> initialNumbers = Stream.of(inputs.poll().split(" "))
                .map(Integer::valueOf)
                .collect(Collectors.toList());

        final int[] range = Stream.of(inputs.poll().split(" "))
                .mapToInt(Integer::valueOf)
                .toArray();

        String filteredNumbers = initialNumbers.stream()
                .filter(num -> num < range[0] || num > range[1])
                .map(String::valueOf)
                .collect(Collectors.joining(" "));

        System.out.println(filteredNumbers);
    }


    public static LinkedList<String> readStdIn() {
        LinkedList<String> inputs = new LinkedList<>();
        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
            inputs = br.lines()
                    .filter(Objects::nonNull)
                    .filter(s -> !s.trim().isEmpty())
                    .collect(Collectors.toCollection(LinkedList::new));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return inputs;
    }
}
