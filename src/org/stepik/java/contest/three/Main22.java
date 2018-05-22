package org.stepik.java.contest.three;

import java.util.ArrayList;
import java.util.List;


public class Main22 {
    /*
        You are asked to create quality control system in a company that produces and packs Bakery, the snippet of main classes
        follows:
        // These and its sublasses should pass quality check
        class Bakery {}
        class Cake extends Bakery {}
        // And this and other stuff should not
        class Paper {}
        // These boxes are used to pack stuff
        interface Box<T> {
            void put(T item);
            T get();
        }
        // Class you need to work on
        class QualityControl {
          public static boolean check(List<Box<? extends Bakery>> boxes) {
              // Add implementation here
          }
        }
        Implement check method in a way it would:
            Return true if all objects in all boxes belong to class Bakery or its subclasses or list contains no boxes
            Return false otherwise, including cases when Box is empty or List contains something that is not Box at all
        The method shouldn't throw any exceptions.
    * */
    public static void main(String[] args) {
        List<Box<? extends Bakery>> boxes = new ArrayList<>();
        QualityControl qc = new QualityControl();
        System.out.println(qc.check(boxes));
        final PackageCake packageCake = new PackageCake();
        packageCake.put(new Cake());
        boxes.add(packageCake);
        System.out.println(qc.check(boxes));
    }

    interface Goods {
    }

    static class Food implements Goods {
    }

    static class Bakery extends Food {
    }

    static class Cake extends Bakery {
    }


    static class Paper {
    }

    interface Box<T> {
        void put(T item);

        T get();
    }


    static class QualityControl {

        public static boolean check(List<Box<? extends Bakery>> boxes) {
            try {
                return boxes.stream()
                        .allMatch(box ->
                                box != null &&
                                        box.get() != null &&
                                        box.get() instanceof Bakery);
            } catch (Throwable e) {
                return false;
            }
        }
    }

    static class PackageCake implements Box<Cake> {
        Cake item;

        @Override
        public void put(Cake item) {
            this.item = item;
        }

        @Override
        public Cake get() {
            return item;
        }
    }
}
