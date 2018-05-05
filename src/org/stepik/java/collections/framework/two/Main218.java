package org.stepik.java.collections.framework.two;

import java.util.*;
import java.util.stream.Collectors;

public class Main218 {
    /*
        Given an array of strings,
        On wordCount() : return a
            SortedMap<String, Integer> with a key for each different string, with the value the number of times that string appears in the array.
        On printMap(): Using System.out.println() print all items of the Map ("key : value")
    * */
    public static void main(String[] args) {
        Map<String, String> map = new HashMap<>();
        String[] words = "a b b c d a b".split(" ");
        printMap(wordCount(words));
    }

    public static SortedMap<String, Integer> wordCount(String[] strings) {
        SortedMap<String, Integer> sortedMap = new TreeMap<>();
        sortedMap.putAll(Arrays.stream(strings)
                .collect(Collectors.groupingBy(t -> t, Collectors.reducing(0, e -> 1, Integer::sum)))
        );
        return sortedMap;
    }

    public static void printMap(Map<String, Integer> map) {
        map.forEach((key, value) -> System.out.println(key + " : " + value));
    }
}
