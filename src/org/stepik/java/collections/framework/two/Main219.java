package org.stepik.java.collections.framework.two;

import java.util.Map;
import java.util.NavigableMap;
import java.util.TreeMap;

public class Main219 {
    /*
        Modify and return the given map as follows:
        if the first key % 2 != 0 return sub map from First Key inclusive to FirstKey+4 inclusive in descending order
        else return sub map from LastKey-4 inclusive to the Last Key inclusive in descending order
    * */
    private static final boolean INCLUSIVE = true;

    public static void main(String[] args) {
//        1:one 2:two 3:three 4:four 5:five 6:six 7:seven
        NavigableMap<Integer, String> map = new TreeMap<Integer, String>();
        map.put(1, "one");
        map.put(2, "two");
        map.put(3, "three");
        map.put(4, "four");
        map.put(5, "five");
        map.put(6, "six");
        map.put(7, "seven");

        printMap(getSubMap(map));
    }

    public static NavigableMap<Integer, String> getSubMap(NavigableMap<Integer, String> map) {
        return map.firstKey() % 2 != 0 ? getSubMapFirstKeyPlusFour(map).descendingMap() : getSubMapLastKeyMinusFour(map).descendingMap();
    }

    public static NavigableMap<Integer, String> getSubMapFirstKeyPlusFour(NavigableMap<Integer, String> map) {
        return map.subMap(map.firstKey(), INCLUSIVE, map.firstKey() + 4, INCLUSIVE);
    }

    public static NavigableMap<Integer, String> getSubMapLastKeyMinusFour(NavigableMap<Integer, String> map) {
        return map.subMap(map.lastKey() - 4, INCLUSIVE, map.lastKey(), INCLUSIVE);
    }

    public static <K, V> void printMap(Map<K, V> map) {
        map.forEach((key, value) -> System.out.println(key + " : " + value));
    }
}
