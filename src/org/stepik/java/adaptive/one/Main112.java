package org.stepik.java.adaptive.one;

import java.util.Collections;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.toList;

public class Main112 {
    public static void main(String[] args) {
        try (Scanner sc = new Scanner(System.in)) {
            List<Integer> list = sc.nextLine().chars().map(chr -> ((char) chr)).boxed().collect(toList());
            Collections.reverse(list);
            System.out.println(list.stream()
                    .map(num -> Character.valueOf((char) num.intValue()).toString())
                    .collect(Collectors.joining("")));
        } catch (Exception e) {
        }
    }
}