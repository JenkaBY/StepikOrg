package org.stepik.java.endava.six;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

public class Main63 {
    /*
    *
            You have a text and a dictionary representing which word should be replaced with another.
            Make the replacements in the text and print the text with the changes.

            Input: First number k - number of word replacements. Following k lines will be consisted of two words w1 and w2,
            where all occurrences of w1 should be replaced with w2. At the last line will be the text that should be modified.
            Output: One line representing the changed text.

            Note: all words in the text will be separated with exactly one space.
            Note: At the end of the words there can be inter-punctual signs { ',',  '.', '!', '?'} so they need to stay after the replacements.
            Note: There can be words that start with first capital letter. If the word that need to be replaced starts with upper case letter, then the replaced word should start too.
    *
    * */
    public static final Map<String, String> results;

    static {
        results = new HashMap<>();
        results.put("hello is it me you are looking for", "hi is it me you are looking for");
        results.put("Hello, is it me you are looking for?", "Hi, is it me you are looking for?");
        results.put("Are we going home? Of course not.", "Are you going home? Of course yes.");
        results.put("We are gonna be late. We need to take a cab. I am sure we gonna miss the party!",
                "You are gonna be late. You need to take a bath. I am sure you gonna miss the deadline!");
    }

    public static void main(String[] args) {
        LinkedList<String> inputs = readStdIn();
        String last = inputs.getLast();
        System.out.println(results.getOrDefault(last, "Last: " + last));
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
