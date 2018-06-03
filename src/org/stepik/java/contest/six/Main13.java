package org.stepik.java.contest.six;

public class Main13 {
    static class ArrFl {
        static Object[] objects = new Object[0];

        static {
            objects[0] = new String("42");
        }
    }

    static class Runner {
        public static void main(String[] args) {
            ArrFl arrFl = new ArrFl();
            System.out.println(arrFl.objects.length);
        }
    }

}
