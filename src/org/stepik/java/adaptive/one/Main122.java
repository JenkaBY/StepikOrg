package org.stepik.java.adaptive.one;

import java.util.Arrays;
import java.util.Optional;
import java.util.Scanner;

public class Main122 {
    private static final String chosen = "You have chosen a ";
    private static final String noShape = "There is no such shape!";

    public static void main(String[] args) {
        int choice = new Scanner(System.in).nextInt();
        Optional<Shape> shapeOpt = Arrays.stream(Shape.values())
                .filter(shape -> shape.ordinal() + 1 == choice)
                .findFirst();
        System.out.println(shapeOpt.map(shape -> chosen + shape.name().toLowerCase()).orElse(noShape));
    }

    public enum Shape {
        SQUARE, CIRCLE, TRIANGLE, RHOMBUS
    }
}
