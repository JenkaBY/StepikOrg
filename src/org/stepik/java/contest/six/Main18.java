package org.stepik.java.contest.six;

public class Main18 {
    public static class Max {
        void max(float x, float y) {
            if (x > y) System.out.println("X - is float");
            else System.out.println("Y - is float");
        }

        void max(double x, double y) {
            if (x > y) System.out.println("X - is double");
            else System.out.println("Y - is double");
        }

        void max(Double x, Double y) {
            if (x > y) System.out.println("X - is double");
            else System.out.println("Y - is double");
        }
    }

    static public strictfp void main(String[] args) {
        Max max = new Max();
        max.max(1, 1);
//        max.max (null ,  null);
    }
}
