package org.stepik.java.contest.three;

import java.util.ArrayList;
import java.util.List;

public class Main20 {
    /*
        You are asked to perform security audit in a company that bakes Bakery and different sublasses of it and sells it in a nice boxes.
        Before release to customer all boxes are checked with carefully designed NaiveQualityControl class.
        Numerous cases when something other than Bakery (e.g. Paper) was packed in Boxes and escaped the quality check happened recently.
        Short look at NaiveQualityControl leads to conclusion that it's quite easy to provide NaiveQualityControl with Box filled with Paper
        that will pass QC, and you task is to demonstrate this. Code of related classes follows:

            // This class and its subclasses should pass quality check
            class Bakery {}
            class Cake extends Bakery {}
            // But this should not
            class Paper {}
            // These boxes are used to pack stuff
            class Box<T> {
                void put(T item) {  implementation omitted  }
                T get() { implementation omitted  }
            }
            // This quality checker ensures that boxes for sale contain Bakery and anything else
            class NaiveQualityControl {
            public static boolean check(List<Box<? extends Bakery>> boxes) {
                // Method signature guarantees that all illegal calls will produce compile-time error... or not?
                return true;
                }
            }

        You need to add implementation to Violator.defraud() method that will do the following:
        ﻿Create List of Boxes<? extends Bakery> according to method signature
        Put Paper object in at least one Box in the list
        The resulting list should pass NaiveQualityControl check
        You shouldn't change method signature or change code of any other classes, just add implementaiton to defraud method.
    * */
    public static void main(String[] args) {

    }

    /**
     * Class to work with
     */
    static class Violator {

        public static List<Box<? extends Bakery>> defraud() {
            // Add implementation here
            List<Box<? extends Bakery>> boxes = new ArrayList<>();

            boxes.add(new Box() {
                Paper paper;

                @Override
                public void put(Object item) {
                    paper = (Paper) item;
                }

                @Override
                public Object get() {
                    return new Paper();
                }
            });
            return boxes;
        }

    }


    static class Bakery {
    }

    static class Cake extends Bakery {
    }


    static class Paper {
    }

    interface Box<T> {
        void put(T item);

        T get();
    }

    class NaiveQualityControl {
        public boolean check(List<Box<? extends Bakery>> boxes) {
            // Method signature guarantees that all illegal calls will produce compile-time error... or not?
            return true;
        }
    }
}
