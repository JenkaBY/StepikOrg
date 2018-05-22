package org.stepik.java.contest.three;

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
        Packer packer = new Packer();
        packer.repackage(new Package<Object>(), new Package<Bakery>());
    }

    /**
     * This packer has too much freedom and could repackage stuff in wrong direction.
     * Fix method types in signature and add implementation.
     */
    static class Packer {
        public <T extends Bakery> void repackage(Box<? super T> to, Box<T> from) {
            to.put(from.get());
        }
    }

    interface Goods {
    }

    static class Food implements Goods {
    }

    static class Bakery extends Food {
    }

    static class Cake extends Bakery {
    }

    static class Pie extends Bakery {
    }

    static class Tart extends Bakery {
    }

    static class Paper {
    }

    interface Box<T> {
        void put(T item);

        T get();
    }

    static class Package<T> implements Box<T> {
        T item;

        @Override
        public void put(T item) {
            this.item = item;
        }

        @Override
        public T get() {
            return item;
        }
    }
}
