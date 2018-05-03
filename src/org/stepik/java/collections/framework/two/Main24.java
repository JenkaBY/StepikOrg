package org.stepik.java.collections.framework.two;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class Main24 {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String str = reader.readLine();
        final LinkedList<Integer> ll = new LinkedList<>();
        //your code
        Stream.of(str.split(" "))
                .map(Integer::valueOf)
                .forEach(ll::addFirst);
        IntStream.range(0, 3).forEach((ignore) -> ll.remove(0));
        ll.stream()
                .sorted()
                .forEach(System.out::println);
    }
}

