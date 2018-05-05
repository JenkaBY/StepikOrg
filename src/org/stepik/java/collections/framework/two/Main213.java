package org.stepik.java.collections.framework.two;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.List;
import java.util.ListIterator;
import java.util.stream.Collectors;

public class Main213 {
    /*
        Additional code
        1.Using ListIterator iterate over the elements of list from the beginning to the end and after each word "Hip" add "Hop"
        2.Using ListIterator print the all elements(using .println())
    * */
    public static void main(String[] args) throws IOException {
        final String hip = "Hip";
        final String hop = "Hop";
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        List<String> words = Arrays.stream(reader.readLine().split(" "))
                .collect(Collectors.toList());
        ListIterator<String> iterator = words.listIterator();
        while (iterator.hasNext()) {
            if (iterator.next().equals(hip)) {
                iterator.add(hop);
            }
        }
        words.forEach(System.out::println);
    }
}
