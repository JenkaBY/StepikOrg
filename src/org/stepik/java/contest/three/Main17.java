package org.stepik.java.contest.three;

public class Main17 {
    /*
        There are different points of view on "Apples and Oranges" problem. You are provided with implementation that
        allows comparison of apples with oranges, the task is to redesign type system and implementation in a classical
         way that prohibits comparison of Oranges and Apples.
        Following requirements exists:
            Comparison of Apples with Apples and Oranges with Oranges should be allowed
            Comparison of Apples with Oranges and Oranges with Apples should be restricted at a compile time
            Class hierarchy should be preserved
            Fruits comparison doesn't matter, you may disable it
            Solution should not raise compile-time warnings (e.g. "raw types")

    * */
    public static void main(String[] args) {
        Fruit apple = new Apple(10);
        Fruit orange = new Orange(9);
        apple.compareTo(orange);
    }

    // This implementaion allows comparison of apples and oranges
    static class Fruit<T extends Comparable<T>> implements Comparable<Fruit<T>> {

        private final int weigth;

        public Fruit(int weight) {
            this.weigth = weight;
        }

        public int getWeigth() {
            return this.weigth;
        }

        @Override
        public int compareTo(Fruit<T> other) {
            return Integer.compare(this.getWeigth(), other.getWeigth());
        }
    }

    static class Apple extends Fruit {

        public Apple(int weight) {
            super(weight);
        }

    }

    static class Orange extends Fruit {
        public Orange(int weight) {
            super(weight);
        }

    }
}
