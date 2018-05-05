package org.stepik.java.collections.framework.two;

import java.util.HashMap;
import java.util.Map;

public class Main217 {
    /*
        Modify and return the given map as follows: if the key "a" has a value, set the key "b" to have that same value.
        In all cases remove the key "c",leaving the rest of the map unchanged.
        {"a":"abstraction","b":"boolean","c":"xyz"} -> {"a":"abstraction","b":"abstraction"}
    * */
    public static void main(String[] args) {
//        a:Abstraction,b:Boolean,c:xyz
        Map<String, String> map = new HashMap<>();
        map.put("a", "Abstraction");
        map.put("b", "Boolean");
        map.put("c", "xyz");
        System.out.println(mapShare(map));
    }

    public static Map<String, String> mapShare(Map<String, String> map) {
        final String A = "a";
        final String B = "b";
        final String C = "c";
        map.remove(C);
        map.computeIfPresent(A, (keyA, valueA) -> {
            map.replace(B, valueA);
            return valueA;
        });
        return map;
    }

}
