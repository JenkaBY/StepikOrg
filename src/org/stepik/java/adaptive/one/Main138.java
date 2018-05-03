package org.stepik.java.adaptive.one;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Main138 {
/*
    Find the number of "Ds", "Cs", "Bs" and "As" for the last test on informatics in the class consisting of n students.
    The program gets number n as input, and then gets the grades themselves (one by one).
    The program should output four numbers in a single line - the number of "D", the number of "C", the number of "B" and the number of "A" grades.
*/

    public static void main(String[] args) {
        String[] grades = readStdIn();
        print(joining(getGradeStats(grades)));
    }

    public static void print(Object object) {
        System.out.println(object);
    }

    public static int numberFromStr(String readLine) {
        return Integer.valueOf(readLine);
    }

    public static Map<Grade, Long> getGradeStats(String[] grades) {
        return Stream.of(grades)
                .map(Integer::valueOf)
                .map(Grade::build)
                .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));
    }

    public static String joining(Map<Grade, Long> stats) {
        return getOrZero(stats, Grade.D) + " " + getOrZero(stats, Grade.C) + " " + getOrZero(stats, Grade.B) + " " + getOrZero(stats, Grade.A);
    }

    public static String getOrZero(Map<Grade, Long> stats, Grade grade) {
        return stats.getOrDefault(grade, 0L).toString();
    }

    public static String[] readStdIn() {
        String[] inputs = null;
        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
            int countNumbers = numberFromStr(br.readLine());
            inputs = new String[countNumbers];
            for (int i = 0; i < countNumbers; i++) {
                inputs[i] = br.readLine().trim();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return inputs;
    }

    public enum Grade {
        D, C, B, A;

        public static Grade build(int num) {
            switch (num) {
                case 2:
                    return D;
                case 3:
                    return C;
                case 4:
                    return B;
                case 5:
                    return A;
            }
            return null;
        }

        public Integer getNumber() {
            switch (this) {
                case A:
                    return 5;
                case B:
                    return 4;
                case C:
                    return 3;
                case D:
                    return 2;
                default:
                    return 0;
            }
        }
    }
}
