package org.stepik.java.adaptive.one;

import javafx.util.Pair;

import java.util.Objects;
import java.util.Scanner;

public class Main119 {
    /*
         Given an integer as input. Output True if its value is within the interval (−15,12]∪(14,17)∪[19,+∞), and False otherwise (case sensitive).
        the first number N is the number of elements (in the example - 10). And then - exactly N numbers. You must read them all.
    */
    public static void main(String[] args) {
        try (Scanner sc = new Scanner(System.in);) {
            int number = sc.nextInt();
            Range left = new Range(new Pair<>(-15, Border.OPENED), new Pair<>(12, Border.CLOSED));
            Range middle = new Range(new Pair<>(14, Border.OPENED), new Pair<>(17, Border.OPENED));
            Range right = new Range(new Pair<>(19, Border.CLOSED), new Pair<>(null, Border.OPENED));
            String result = left.isInRange(number) || middle.isInRange(number) || right.isInRange(number) ? "True" : "False";
            System.out.println(result);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static class Range {
        private Pair<Integer, Border> left;
        private Pair<Integer, Border> right;

        public Range(Pair<Integer, Border> left, Pair<Integer, Border> right) {
            this.left = left;
            this.right = right;
        }

        public boolean isInRange(int number) {
            if (isLeftBorderInf()) {
                return isRightBorderInf() || comparingRightRange(number);
            }
            if (isRightBorderInf()) {
                return comparingLeftRange(number);
            }
            return comparingLeftRange(number) && comparingRightRange(number);
        }

        private boolean isLeftBorderInf() {
            return isBorderNull(left);
        }

        private boolean isRightBorderInf() {
            return isBorderNull(right);
        }

        private boolean comparingRightRange(int number) {
            int compareResult = right.getKey().compareTo(number);
            switch (compareResult) {
                case -1:
                    return false;
                case 0:
                    return right.getValue() == Border.CLOSED;
                case 1:
                    return true;
            }
            return false;
        }

        private boolean comparingLeftRange(int number) {
            int compareResult = left.getKey().compareTo(number);
            switch (compareResult) {
                case -1:
                    return true;
                case 0:
                    return left.getValue() == Border.CLOSED;
                case 1:
                    return false;
            }
            return false;
        }

        private boolean isBorderNull(Pair<Integer, Border> border) {
            return Objects.isNull(border.getKey());
        }
    }

    public enum Border {
        CLOSED, OPENED;
    }
}