package org.stepik.java.adaptive.three.wildcards;

public class Main374 {
    /*
    * You are  invited to Pie company that succeeds in baking and packing Pies. Their current infrastructure is descibed in code:
    *   / Different Bakeries that should be packed to Box'es and delivered to customers
            class Bakery {}
            class Cake extends Bakery {}
            class Pie extends Bakery {}
            class Tart extends Bakery {}
            // This (of course) can be packed in box, but we want to disable it's delivery to customers
            class Paper {}
            // Implementing class exists that is used to pack everything
            interface Box<T> {
                public void put(T item);
                public T get();
            }
    * Now they decided to deliver bakeries to the customers and ask you to design Deliveryman class. The deliverymen should succeed in following:
    * Deliver Bakery or any subclass of it
    * Do not deliver anything else (e.g. Paper)
    * Prohibit Deliveryman to put anything into the Box inside deliver method at a compile time by raising errors or warnings.
    * Your task is to create method signature leaving implementation unchanged.
    * */
    public static void main(String[] args) {
    }

    static class Deliveryman {
        public void deliver(Box<? extends Bakery> box) {
        }
    }

    static class Bakery {
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
        public void put(T item);

        public T get();
    }
}
