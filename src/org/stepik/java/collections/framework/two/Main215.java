package org.stepik.java.collections.framework.two;

import java.util.*;

public class Main215 {
    /*
        On spliteratorWork( List<Double> lst ):
            -using Spliterator add to List<Double> sqrts all square roots of items from lst ,if this item greater than 1
            -use Math.sqrt(number) to find square root : Math.sqrt(4)-> 2.0
        On print(List<Double> list):
            -get spliterator() of given list
            -divide spliterator into 2 parts ( use spliterator.trySplit())
            -print all items which >=2 from first part (use .println())
            -print empty string
            -print all items which >=10 from second part (use .println())

        *! remember when you use trySplit() method :
        List<Integer> lst = new ArrayList<>(Arrays.asList(1,1,1,2,2,2,2));
        Spliterator<Integer> split = lst.spliterator();
        split.trySplit()...//-> 1 1 1 (its your first path of spliterator)
        split...//2 2 2 2 (now split contains of second path)
    * */
    public static void main(String[] args) {
        List<Double> lst = new ArrayList<>(Arrays.asList(1.0, 1.0, 1.0, 2.0, 2.0, 2.0, 2.0));
        List<Double> sqrt = spliteratorWork(lst);
        print(sqrt);

        Queue<String> queue = new PriorityQueue<>();
        queue.offer("a");
        queue.offer("c");
        queue.offer("b");

        String name = queue.peek();
        String name1 = queue.poll();
        String name2 = queue.poll();
        queue.offer(name);

        queue.forEach(System.out::print);

        Deque<String> states = new ArrayDeque<String>();
        states.add("Germany");
        states.add("France");
        states.push("UK");
        states.offerLast("Norway");

        String sFirst = states.pop();
        String s = states.peek();
        String sLast = states.peekLast();
        states.offer(sFirst);
        String s1 = states.pollLast();


        while (states.peek() != null) {
            System.out.print(states.pop());
        }
    }

    public static List<Double> spliteratorWork(List<Double> lst) {
        List<Double> sqrs = new ArrayList<>();
        lst.spliterator().forEachRemaining((doub) -> {
            if (doub > 1.0) {
                sqrs.add(Math.sqrt(doub));
            }
        });
        return sqrs;
    }

    public static void print(List<Double> list) {
        Spliterator<Double> spliterator = list.spliterator();
        Spliterator<Double> first = spliterator.trySplit();
        first.forEachRemaining((num) -> {
            if (num >= 2.0) {
                System.out.println(num);
            }
        });
        spliterator.forEachRemaining((num) -> {
            if (num >= 10) {
                System.out.println(num);
            }
        });
    }
}
