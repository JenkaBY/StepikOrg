package org.stepik.java.adaptive.one;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Main1129 {
    /*
    Given a rectangle array n×m in size. Rotate it by 90 degrees clockwise, by recording the result into the new array m×n in size.
    Input data format
        Input the two numbers n and m, not exceeding 100, and then an array n×m in size.
    Output data format
        Output the resulting array. Separate numbers by a single space in the output.
    */

    public static void main(String[] args) {
        String[] input = readStdIn(1);
        final Integer number = numberFromStr(input[0]);
        final Map<Long, Long> numbers = Stream.of(input[1].split("\\s+"))
                .mapToLong(Long::valueOf)
                .boxed()
                .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));
        long count = numbers.values().stream().filter(num -> num > number / 2).count();
        print(count > 0 ? 1 : 0);
    }

    public static void print(Object object) {
        System.out.println(object);
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

    public static class Matrix {

    }

    public static class Column<T> extends Numbers<T> {

        public Column(T[] numbers) {
            super(numbers);
        }
    }

    public static abstract class Numbers<T> {
        private T[] numbers;

        public Numbers(T[] numbers) {
            this.numbers = numbers;
        }
    }

    public static class Ceil<T> {
        T value;
        int rowNumber;
        int columnNumber;

        public Ceil() {
        }

        public Ceil(T value) {
            this.value = value;
        }

        public T getValue() {
            return value;
        }

        public void setValue(T value) {
            this.value = value;
        }

        public int getRowNumber() {
            return rowNumber;
        }

        public void setRowNumber(int rowNumber) {
            this.rowNumber = rowNumber;
        }

        public int getColumnNumber() {
            return columnNumber;
        }

        public void setColumnNumber(int columnNumber) {
            this.columnNumber = columnNumber;
        }

        public static class CeilBuilder<T> {
            private Ceil<T> ceil;

            private void initCeil() {
                if (ceil == null) {
                    ceil = new Ceil<>();
                }
            }

            public CeilBuilder<T> withValue(T value) {
                initCeil();
                ceil.value = value;
                return this;
            }

            public CeilBuilder<T> withRowNumber(int rowNumber) {
                initCeil();
                checkPositiveNumber(rowNumber);
                ceil.rowNumber = rowNumber;
                return this;
            }

            public CeilBuilder<T> withColumnNumber(int columnNumber) {
                initCeil();
                checkPositiveNumber(columnNumber);
                ceil.columnNumber = columnNumber;
                return this;
            }

            public Ceil<T> getCeil() {
                initCeil();
                return ceil;
            }

            private void checkPositiveNumber(int number) {
                if (number <= 0) {
                    throw new RuntimeException("Number " + number + " is negative!");
                }
            }
        }
    }
}
