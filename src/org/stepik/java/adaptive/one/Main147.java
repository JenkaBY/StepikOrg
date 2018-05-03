package org.stepik.java.adaptive.one;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main147 {
    /*
        On the input, the program gets a sequence of non-negative integers; each integer is written in a separate line.
        The sequence ends with an integer 0, when reading which the program should end its work and output the length of
        the sequence (not counting the final 0).
        Don’t read numbers following the number 0.
    */
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        List<Integer> numbers = new ArrayList<>();
        while (sc.hasNext()) {
            String input = sc.nextLine();
            try {
                int num = Integer.valueOf(input);
                numbers.add(num);
                if (num == 0) {
                    System.out.println(numbers.size() - 1);
                    break;
                }
            } catch (Exception ex) {
                break;
            }
        }
        ;
    }
}
