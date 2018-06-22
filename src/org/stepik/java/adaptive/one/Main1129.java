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
            return cells.stream()
                    .sorted(CellSort.NATURAL)
                    .collect(Collectors.toList());
        }

        public Cell<T> getCellByRowAndColumn(int row, int column) {
            return cells.stream()
                    .filter(cell -> cell.getColumnNumber() == column && cell.getRowNumber() == row)
                    .findFirst()
                    .orElseThrow(() -> new IllegalArgumentException("Not found cells by column = " + column + ", row = " + row));
        }
    }

    public static class CellSort {
        public static final Comparator<Cell> NATURAL = byRowAndThenByColumn();

        private static final Comparator<Cell> byRowAndThenByColumn() {
            Comparator<Cell> cop = Comparator.comparing(Cell::getRowNumber);
            return cop.thenComparing(Cell::getColumnNumber);
        }
    }

    public static class MatrixRotator<T> {
        Matrix<T> matrix;

        public MatrixRotator(Matrix<T> matrix) {
            this.matrix = matrix;
        }

        public Matrix<T> rotate() {
            final Matrix<T> rotatedMatrix = new Matrix<>();
            matrix.getCells().forEach(cell -> {
                int row = 0;
                int column = 0;
                Cell<T> original = matrix.getCellByRowAndColumn(cell.getRowNumber(), cell.getColumnNumber());
                Cell<T> rot = new Cell.CeilBuilder().withValue(original.getValue())
//                        .withColumnNumber()
                        .getCell();
            });
            return rotatedMatrix;
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

    public static class Cell<T> {
        T value;
        int rowNumber;
        int columnNumber;

        public Cell() {
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
    }
}
