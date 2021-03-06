package org.stepik.java.endava;

import java.util.Scanner;
import java.util.stream.IntStream;

public class Main14 {
    /*
        Given two integer ranges [x1, x2] [y1, y2] check if there is any overlap between these two ranges.
        Input. x1, x2, y1, y2   where x1 <= x2  and y1 <= y2
        Output: true if they overlap, false otherwise
    * */
    public static void main(String[] args) {
        String[] input = new Scanner(System.in).nextLine().split("\\s+");

        System.out.println(new Range(parse(input[0]), parse(input[1])).isIntersected(new Range(parse(input[2]), parse(input[3]))));
    }

    private static int parse(String readLine) {
        return Integer.valueOf(readLine);
    }

    public static class Range {
        private int left;
        private int right;

        public Range(int left, int right) {
            this.left = left;
            this.right = right;
        }

        public IntStream points() {
            return IntStream.rangeClosed(left, right);
        }

        public boolean isIntersected(Range another) {
            return points()
                    .anyMatch((point) -> another.points()
                            .boxed()
                            .anyMatch(
                                    (anotherPoint) -> anotherPoint.equals(point)
                            )
                    );
        }
    }
}
