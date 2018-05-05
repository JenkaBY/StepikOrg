package org.stepik.java.collections.framework.two;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main212 {
    /*
    Using BufferedReader read one string with numbers written across the space ("1 2 3 4 5 ...").
    Put this numbers to any Collection of Integers.Sort this collection!
    Using Iterator remove all even annoying numbers(death for elem%2=0 !) from your Collection
    Print all remaining elements to console (use System.out.println() method)
    * */
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        Arrays.stream(reader.readLine().split(" "))
                .map(Integer::valueOf)
                .filter((num) -> num % 2 != 0)
                .sorted()
                .forEach(System.out::println);
    }
}
