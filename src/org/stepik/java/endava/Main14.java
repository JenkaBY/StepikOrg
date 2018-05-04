package org.stepik.java.endava;

import java.util.Scanner;

public class Main14 {
    /*
        Given two integer ranges [x1, x2] [y1, y2] check if there is any overlap between these two ranges.
        Input. x1, x2, y1, y2   where x1 <= x2  and y1 <= y2
        Output: true if they overlap, false otherwise
    * */
    public static void main(String[] args) {
        String[] input = new Scanner(System.in).nextLine().split(" ");

        System.out.println();
    }

    public static class Range {
        private int left;
        private int right;

        public Range(int left, int right) {
            this.left = left;
            this.right = right;
        }

        public boolean intersection(Range another) {
            return false;
        }
    }
}
