package org.stepik.java.adaptive.one;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Main1115 {
    /*
        Write a program the input of which is the list of numbers in one line. For each elements of this list,
        the program should output the sum of its two neighbouring numbers. For list item that is first or last,
        an element from the opposite end of the list is considered in place of a missing neighbour. For example,
        if the input list is "1 3 5 6 10", the expected output list is "13 6 9 15 7" (without quotation marks).
        If only one number serves as input, the output shall display the same one number.
        The output must contain one line with the numbers from the new list, separated by space.
    */

    public static void main(String[] args) {
        List<Integer> input = Stream.of(readStdIn(1))
                .flatMap(inputs -> Stream.of(inputs.split(" ")))
                .map(Integer::valueOf)
                .collect(Collectors.toList());
        Neighbors neighbors = new Neighbors(input);
        SumNeighbors sumNeighbors = new SumNeighbors(neighbors);
        String res = sumNeighbors.transformToSumNeighbors().stream()
                .map(num -> String.valueOf(num.intValue()))
                .collect(Collectors.joining(" "));
        print(res);
    }

    public static class SumNeighbors {
        private Neighbors numbers;

        public SumNeighbors(Neighbors numbers) {
            this.numbers = numbers;
        }

        public List<Integer> transformToSumNeighbors() {
            List<Integer> result = new ArrayList<>();
            for (int i = 0; i < numbers.size(); i++) {
                Integer sum = numbers.getLeft(i) + numbers.getRight(i);
                result.add(sum);
            }
            return result;
        }
    }

    public static class Neighbors implements List<Integer> {
        private List<Integer> numbers;

        public Neighbors(List<Integer> numbers) {
            this.numbers = numbers;
        }

        public Integer getRight(int position) {
            checkBounders(position);
            if (position == numbers.size() - 1) {
                return numbers.get(0);
            }
            return numbers.get(position + 1);
        }

        public Integer getLeft(int position) {
            checkBounders(position);
            if (position == 0) {
                return numbers.get(numbers.size() - 1);
            }
            return numbers.get(position - 1);
        }

        private void checkBounders(int position) {
            if (position < 0 || position >= numbers.size()) {
                throw new RuntimeException("Position '" + position + "' is out of index");
            }
        }

        @Override
        public int size() {
            return numbers.size();
        }

        @Override
        public boolean isEmpty() {
            return numbers.isEmpty();
        }

        @Override
        public boolean contains(Object o) {
            return numbers.contains(o);
        }

        @Override
        public Iterator<Integer> iterator() {
            return numbers.iterator();
        }

        @Override
        public Object[] toArray() {
            return numbers.toArray();
        }

        @Override
        public <T> T[] toArray(T[] a) {
            return numbers.toArray(a);
        }

        @Override
        public boolean add(Integer integer) {
            return numbers.add(integer);
        }

        @Override
        public boolean remove(Object o) {
            return numbers.remove(o);
        }

        @Override
        public boolean containsAll(Collection<?> c) {
            return numbers.containsAll(c);
        }

        @Override
        public boolean addAll(Collection<? extends Integer> c) {
            return numbers.addAll(c);
        }

        @Override
        public boolean addAll(int index, Collection<? extends Integer> c) {
            return numbers.addAll(index, c);
        }

        @Override
        public boolean removeAll(Collection<?> c) {
            return removeAll(c);
        }

        @Override
        public boolean retainAll(Collection<?> c) {
            return numbers.retainAll(c);
        }

        @Override
        public void clear() {
            numbers.clear();
        }

        @Override
        public Integer get(int index) {
            return numbers.get(index);
        }

        @Override
        public Integer set(int index, Integer element) {
            return numbers.set(index, element);
        }

        @Override
        public void add(int index, Integer element) {
            numbers.add(index, element);
        }

        @Override
        public Integer remove(int index) {
            return numbers.remove(index);
        }

        @Override
        public int indexOf(Object o) {
            return numbers.indexOf(o);
        }

        @Override
        public int lastIndexOf(Object o) {
            return numbers.lastIndexOf(o);
        }

        @Override
        public ListIterator<Integer> listIterator() {
            return numbers.listIterator();
        }

        @Override
        public ListIterator<Integer> listIterator(int index) {
            return numbers.listIterator(index);
        }

        @Override
        public List<Integer> subList(int fromIndex, int toIndex) {
            return numbers.subList(fromIndex, toIndex);
        }
    }

    public static void print(Object object) {
        System.out.println(object);
    }

    public static List<Integer> toList(String[] nums) {
        return Stream.of(nums)
                .map(num -> numberFromStr(num))
                .collect(Collectors.toList());
    }

    public static int numberFromStr(String readLine) {
        return Integer.valueOf(readLine.trim());
    }

    public static String[] readStdIn(int times) {
        String[] inputs = null;
        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
            inputs = new String[times];
            for (int i = 0; i < times; i++) {
                inputs[i] = br.readLine().trim();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return inputs;
    }
}
