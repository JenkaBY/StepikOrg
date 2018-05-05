package org.stepik.java.collections.framework.two;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.ListIterator;
import java.util.stream.Collectors;

public class Main214 {
    /*
        Given string as "JButton Button JMenu..." to stdin of your programm
        1.Add all elements from this string to List<String>
        2.Next, using ListIterator remove all items not starting with J and items beginning with J replace with the same elements but without the J ,for example JFrame -> Frame
        3.next, print all the remaining elements in reverse order(using .println())
    * */
    public static void main(String[] args) throws IOException {
        final String charJ = "J";
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        List<String> words = Arrays.stream(reader.readLine().split(" "))
                .collect(Collectors.toList());
        ListIterator<String> iterator = words.listIterator();
        while (iterator.hasNext()) {
            String currentWord = iterator.next();
            if (currentWord.startsWith(charJ)) {
                iterator.set(currentWord.replace(charJ, ""));
            } else {
                iterator.remove();
            }
        }
        Collections.reverse(words);
        words.forEach(System.out::println);
    }
}
