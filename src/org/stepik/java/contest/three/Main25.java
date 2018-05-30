package org.stepik.java.contest.three;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.Optional;
import java.util.stream.Stream;

public class Main25 {
    /*
        You need to implement method getComparatorType(Class) in ComparatorInspector class. The method should examine provided class
        and return Type object that corresponds to the type parameter that parameterizes Comparable interface the class implements.
        Consider the example:
            class MyInt implements Comparable<Integer> {
                // Implementation omitted
            }

            // Method to implement
            Type type = ComparatorInspector.getComparatorType(MyInt.class);

            System.out.println(type.getTypeName());
            // prints: java.lang.Integer since MyInt implements Comparable with Integer parameter type

        The method should:
            Return type parameter for Comparable interface class implements
            Return null if Comparable interface does not have type parameter
            Should not produce compile-time warnings
        Additional requirements:﻿
            Compile-time error should arise if class not implementing Comparable is provided as input value
            No 'rawtype' warnings should remain or be supressed
            Method getComparatorType should be generic
        You are free to correct method's type signature if needed.
    * */
    public static void main(String[] args) {
        Type type = ComparatorInspector.getComparatorType(MyInt.class);

        System.out.println(Optional.ofNullable(type).map(Type::getTypeName).orElse("null"));
    }

    static class MyInt implements Comparable<Integer> {
        @Override
        public int compareTo(Integer o) {
            return 0;
        }
    }

    static class MyComp implements Comparable {

        @Override
        public int compareTo(Object o) {
            return 0;
        }
    }

    static class MyCompObj implements Comparable<Object> {

        @Override
        public int compareTo(Object o) {
            return 0;
        }
    }
    /**
     * Class to work with
     */
    static class ComparatorInspector {

        /**
         * Return Type variable that corresponds to the type parameterizing Comparator.
         *
         * @param clazz {@link Class} object, should be non null
         * @return {@link Type} object or null if Comparable does not have type parameter
         */
        public static <T> Type getComparatorType(Class<? extends Comparable<T>> clazz) {
            // Add implementation
            Type[] allTypes = clazz.getGenericInterfaces();
            final Optional<Type> typeOptional = Stream.of(allTypes)
                    .filter(type -> type.getTypeName().startsWith("java.lang.Comparable<"))
                    .findFirst();

            return typeOptional
                    .map(type -> ((ParameterizedType) type))
                    .map(ParameterizedType::getActualTypeArguments)
                    .flatMap(types -> Stream.of(types).findFirst())
                    .orElse(null);
        }
    }
}
