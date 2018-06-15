package org.stepik.java.other.codewars;

import java.util.Arrays;
import java.util.function.IntPredicate;

import static java.lang.Math.abs;

public class FindOutlier {
    static int find(int[] integers) {
        IntPredicate arrayTypeFilter = isOddArray(integers) ? isEven() : isOdd();
        return Arrays.stream(integers)
                .filter(arrayTypeFilter)
                .findFirst()
                .getAsInt();
    }

    static boolean isOddArray(int[] integers) {
        int first = integers[0];
        int second = integers[1];
        int third = integers[2];
        return countOdd(new int[]{first, second, third}) >= 2;
    }

    static long countOdd(int[] threeNumbers) {
        return Arrays.stream(threeNumbers).filter(isOdd()).count();
    }

    static IntPredicate isOdd() {
        return num -> abs(num) % 2 == 1;
    }

    static IntPredicate isEven() {
        return num -> abs(num) % 2 == 0;
    }
}
