package org.stepik.java.adaptive.three;

public class Main371 {
    /*
    Generics Basics
    You are asked to help with Java programming in a Pie company. At the moment they bake and sell pies, cakes and
     tarts packed in a nice boxes. Unfortunately, approach to boxes programming is quite outdated – each pie type requires
     its own box class. This approach is poorly scalable and will turn the situation into a nightmare with product range growth
     (imagine all these ApplePieBox, StrawberryPieBox, etc.).
    To avoid this implement universal Box class that will accomodate anything with put and give it back with get methods.

    Sample Input:
        Magical Box class
    Sample Output:
       Well done!
    * */
    class Box<T> {
        T t;

        public void put(T t) {
            this.t = t;
        }

        public T get() {
            return this.t;
        }
    }
}
