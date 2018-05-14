package org.stepik.java.adaptive.one;

import java.util.Scanner;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Main173 {

/*
    Input of the program is a line containing the words separated by a space. The program should output the information of lengths of words in the given line,
    from the shortest to the longest word (see the example).
    A word is a sequence of arbitrary characters surrounded by spaces or line boundaries. Note that punctuation marks also belong to a word.
    Input format:
        A string containing a sequence of Latin characters and punctuation marks, separated by a space.
    Output format:
        For each word length that appears in the original string, you need to specify the number of words with such length in a format:
        length: amount
    Output this information in the order of increasing length.
*/

    public static void main(String[] args) {
        Stream.of(new Scanner(System.in).nextLine().trim().split("\\s+"))
                .mapToInt(String::length)
                .sorted()
                .boxed()
//                .collect(Collectors.toMap(key -> key.length(), value -> 1, (a, b) -> a + 1))
                .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()))
                .forEach((length, amount) -> System.out.println(length + ": " + amount));
    }
}