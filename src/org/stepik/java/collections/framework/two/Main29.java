package org.stepik.java.collections.framework.two;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Set;
import java.util.TreeSet;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Main29 {
    /*
      Additional code,Soldier!
     Given one input string as "1 2 3 4 5;6 7 8 9 10;11 12 13 14 15"(it's 3 lists)
     1.String[] sets consists of these lists. Add all elements from each list to correct set(see the comments in the code) sets[0] = {"1 2 3 4 5"} - its string and so on
     *use split(" ") to separate elements from string
     2.On unionTreeLargeNumber(Set<Integer> set1,Set<Integer> set2,Set<Integer> set3):
        1)In each Set find the maximum element
        2)create TreeSet, add the resulting elements to TreeSet
        3)return this TreeSet with elements sorted in descending order:
     *you can use special method from class TreeSet or rather NavigableSet interface to change the order of storage on the reverse
      3.Using System.out.println() print elements of resultTreeSet
    * */
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String[] sets = reader.readLine().split(";");

        Set<Integer> set1 = convertToSet(split(sets[0]));
        Set<Integer> set2 = convertToSet(split(sets[1]));
        ;
        Set<Integer> set3 = convertToSet(split(sets[2]));
        ;
        Set<Integer> resultTreeSet = unionTreeLargeNumber(set1, set2, set3);
        resultTreeSet.forEach(System.out::println);
    }

    public static String[] split(String set) {
        return set.split(" ");
    }

    public static Set<Integer> convertToSet(String[] set) {
        return Arrays.stream(set)
                .map(Integer::valueOf)
                .collect(Collectors.toSet());
    }

    public static Integer max(Set<Integer> set) {
        return set.stream()
                .max(Comparator.naturalOrder())
                .orElse(0);
    }

    public static TreeSet<Integer> unionTreeLargeNumber(Set<Integer> set1, Set<Integer> set2, Set<Integer> set3) {
        return Stream.of(max(set1), max(set2), max(set3))
                .collect(Collectors.toCollection(() -> new TreeSet<Integer>(Comparator.reverseOrder())));
    }
}
