package org.stepik.java.adaptive.one;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main123 {
    /*
        Write a program, which reads the number of direction (1 – up, 2 – down, 3 – left, 4 – right, 0 – stay) and
        outputs the text «move up» (or «move down», or «move left», or «move right», or «do not move» depending on the entered number).
        If it is a number that does not belong to any of the listed directions, the program should output: «error!»
        Note: the output text should exactly match the sample! Including letters case and location of spaces.
    */
    public static void main(String[] args) {
        String direction = readOneFromStdIn();
        try {
            print(Direction.build(numberFromStr(direction)));
        } catch (IllegalArgumentException ex) {
            print("error!");
        }
    }

    public static void print(Object object) {
        System.out.println(object);
    }

    public static Integer numberFromStr(String readLine) {
        return Integer.valueOf(readLine);
    }

    public static String readOneFromStdIn() {
        String input = null;
        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
            input = br.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return input;
    }

    public enum Direction {
        UP, DOWN, LEFT, RIGHT,
        DO_NOT_MOVE() {
            @Override
            public String toString() {
                return this.name().toLowerCase().replaceAll("_", " ");
            }
        };
        private static String MOVE = "move";

        public static Direction build(Integer direction) {
            switch (direction) {
                case 1:
                    return UP;
                case 2:
                    return DOWN;
                case 3:
                    return LEFT;
                case 4:
                    return RIGHT;
                case 0:
                    return DO_NOT_MOVE;
                default:
                    throw new IllegalArgumentException("Illegal direction parameter: " + direction);
            }
        }

        @Override
        public String toString() {
            return MOVE + " " + this.name().toLowerCase();
        }
    }
}