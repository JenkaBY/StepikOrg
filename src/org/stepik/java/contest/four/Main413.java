package org.stepik.java.contest.four;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/*
*
*   A list with numbers from 0 to 20 is given.
    Remove numbers less than 10 from it without additional lists or arrays usage.
    Modify the code so that it does not raise the ConcurrentModificationException.

    Hint ! Use the Iterator.
* */
public class Main413 {
    public static void main(String[] args) {


        List<Integer> delList = new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            delList.add(i);
        }


        //*** change this code which raises ConcurrentModificationException
        final Iterator<Integer> iterator = delList.iterator();
        while (iterator.hasNext()) {
            if (iterator.next() < 10) iterator.remove();
        }
        //***

        Long value = new Long(0);
        System.out.println(value.equals(0.));
        System.out.println(delList);

    }
}
