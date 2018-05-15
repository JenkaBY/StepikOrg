package org.stepik.java.contest.three;

public class Main12 {
    /*
        You are working in a Pie company. The business going well and bakeries are selling abroad. Sometimes due to custom rules
         and trade regulations it is necessary to pakage bakeries into box with more basic name like Bakery or Food.
         Full class hierarchy follows:
            class Food {}
            class Bakery extends Food {}
            class Cake extends Bakery {}
            class Pie extends Bakery {}
            class Tart extends Bakery {}
            interface Box<T> {
                public void put(T item);
                public T get();
            }
        There is Packer class available, but he is designed with buisiness rule violation and lacks implementation. Correct the Packer code to ensure that:
        Any kind of Bakery could be repacked to the Box with more basic type (e.g. from box with Pie to box with Food)
        Basic stuff like food can't be repacked into narrowly typed Box'es (e.g. with Cakes)
        Arbitrary stuff like Strings or Objects can't be repacked without compile-time errors or warnings
        Repacking actually happens
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
        public <T extends Bakery, F extends Goods> void repackage(Box<? super F> to, Box<? extends T> from) {
            if (!(to.get() instanceof Food)) {
//                to.put(from.get());
            } else {
                System.err.println(from.getClass());
                throw new IllegalArgumentException();
            }
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

        @Override
        public void put(T item) {
        }

        @Override
        public T get() {
            return null;
        }
    }
}
