package org.stepik.java.adaptive.one;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
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

    public static class Matrix<T> {
        List<Cell<T>> cells;

        public Matrix() {
            this.cells = new ArrayList<>();
        }

        public Matrix(List<Cell<T>> cells) {
            this.cells = cells;
        }

        public void addCell(Cell<T> cell) {
            cells.add(cell);
        }

        public List<Cell<T>> getCells() {
            return cells;
        }


    }

    public static class CellSort {
        public final static Comparator<Cell> BY_ROW_NUMBER = new ByRowNumber();

        private static class ByRowNumber implements Comparator<Cell> {
            @Override
            public int compare(Cell o1, Cell o2) {
                return 0;
            }
        }

        class ByColumnNumber {
        }
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

    public static class Cell<T> {
        T value;
        int rowNumber;
        int columnNumber;

        public Cell() {
        }

        public Cell(T value) {
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
            private Cell<T> cell;

            private void initCeil() {
                if (cell == null) {
                    cell = new Cell<>();
                }
            }

            public CeilBuilder<T> withValue(T value) {
                initCeil();
                cell.value = value;
                return this;
            }

            public CeilBuilder<T> withRowNumber(int rowNumber) {
                initCeil();
                checkPositiveNumber(rowNumber);
                cell.rowNumber = rowNumber;
                return this;
            }

            public CeilBuilder<T> withColumnNumber(int columnNumber) {
                initCeil();
                checkPositiveNumber(columnNumber);
                cell.columnNumber = columnNumber;
                return this;
            }

            public Cell<T> getCell() {
                initCeil();
                return cell;
            }

            private void checkPositiveNumber(int number) {
                if (number <= 0) {
                    throw new RuntimeException("Number " + number + " is negative!");
                }
            }
        }

//        public void rotateCWto90Degrees() {
//            int[][] tempMatrix = new int[getHorizontalSize()][getVerticalSize()];
//
//            for (int tempVertical = 0; tempVertical < getHorizontalSize(); tempVertical++) {
//
//                for (int tempHorizontal = 0; tempHorizontal < getVerticalSize(); tempHorizontal++) {
//
//                    tempMatrix[tempVertical][tempHorizontal] = getElement(getVerticalSize() - tempHorizontal - 1,
//                            tempVertical);
//                }
//            }
//            array = tempMatrix;
//        }
    }
}
