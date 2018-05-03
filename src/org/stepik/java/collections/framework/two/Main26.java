package org.stepik.java.collections.framework.two;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Main26 {
    /*
        Additional code,son!
        1.Given a string as "1 2 3 4 5 6 ..." . String[] strings contains of element from given string ("1","2",..).
        Add all elements from String[] strings to Set<Integer> set.
        2.Add all the elements from helpList to set .
        3.Using Iterator remove/filter(Stream API)/"method-of-all-collections" all elements greater than 10(>) from set and return modified set.
    */
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String[] strings = reader.readLine().split(" ");
        List<Integer> helpList = getList();
        Set<Integer> set = new HashSet<>();
        //add elements from strings to set
        set.addAll(Stream.of(strings)
                .map(Integer::valueOf)
                .collect(Collectors.toSet()));
        //add elements from helpList to set
        set.addAll(helpList);
        set = removeAllNumbersMoreThan10(set);
        set.forEach(System.out::println);
    }

    public static List<Integer> getList() {
        return Arrays.asList(1, 2);
    }

    public static Set<Integer> removeAllNumbersMoreThan10(Set<Integer> set) {
        //put your code here
        set.removeIf((num) -> num > 10);
        return set;
    }

    public static Set<Integer> symDifference(Set<Integer> set1, Set<Integer> set2) {
        //Enter your code below
        return Stream.concat(set1.stream(), set2.stream())
                .filter((num) -> !(set1.contains(num) && set2.contains(num)))
                .collect(Collectors.toSet());
    }
}

