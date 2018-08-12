package org.stepik.java.adaptive.one;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Main134 {
    /*
        Multiplication table

        When Paul studied at school, he memorized the multiplication table by rectangular blocks. For his practice
        it would be very nice to have a program, which outputs a block of the multiplication table.
        Write a program, which gets four numbers a, b, c and d as input, each on its own line. The program should output
         a fragment of multiplication table for all numbers of the interval [a;b] by all numbers of the interval [c;d].

        Numbers a, b, c and d are natural ones and do not exceed 10, a≤b, c≤d.

        Follow the output format from the example, use '\t' (tab character) to separate elements inside the line.
        Adding a space instead of line break is performed by the "end" parameter of the print function:
            print(x, end=" ")
        Please output the numbers from the specified intervals themselves in the left column and the top row (as headers).

        Sample Input 1:
            7
            10
            5
            6
        Sample Output 1:
                5	6
            7	35	42
            8	40	48
            9	45	54
            10	50	60
        Sample Input 2:
            5
            5
            6
            6
        Sample Output 2:
                6
            5	30
    */

    public static void main(String[] args) {
        String[] numbers = readStdIn(4);
        MatrixFilling filling = new MatrixFilling();
        filling.setInitialData(numbers[2], numbers[3], numbers[0], numbers[1]);
        print(filling.fillMatrix().toString());
    }

    public static void print(Object object) {
        System.out.println(object);
    }

    public static long numberFromStr(String readLine) {
        return Long.valueOf(readLine);
    }

    public static int numberFromStr(String readLine, int i) {
        return Integer.valueOf(readLine);
    }

    public static String[] readStdIn(int countNumbers) {
        String[] inputs = null;
        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
            inputs = new String[countNumbers];
            for (int i = 0; i < countNumbers; i++) {
                inputs[i] = br.readLine().trim();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return inputs;
    }

    public static <T, I> Cell<T> convertToCell(I[] data, int rowNumber, int columnNumber, BiFunction<I, I, T> valueExtractor) {
        return new Cell.Builder<T>()
                .withValue(valueExtractor.apply(data[0], data[1]))
                .withRowNumber(rowNumber)
                .withColumnNumber(columnNumber)
                .getCell();
    }

    public static class MatrixFilling {
        private Matrix<Integer> matrix;
        private String[] initialData;

        public void setInitialData(String... data) {
            initialData = data;
        }

        private Matrix<Integer> fillMatrix() {
            Range columnRange = getRange(numberFromStr(initialData[0], 0), numberFromStr(initialData[1], 0));
            Range rowRange = getRange(numberFromStr(initialData[2], 0), numberFromStr(initialData[3], 0));
            Matrix<Integer> table = new Matrix<>();

            closedRange(columnRange.size())
                    .boxed()
                    .forEach(column -> {
                        closedRange(rowRange.size())
                                .boxed()
                                .forEach(row -> {
                                    Integer value = evaluateValue(row, column, rowRange, columnRange);
                                    Cell<Integer> cell = new Cell.Builder<Integer>()
                                            .withValue(value)
                                            .withRowNumber(row + 1)
                                            .withColumnNumber(column + 1)
                                            .getCell();
                                    table.addCell(cell);
                                });
                    });
            return table;
        }

        private Integer evaluateValue(int row, int column, Range rowRange, Range columnRange) {
            if (row == 0 && column == 0) {
                return null;
            }
            if (column == 0) {
                return rowRange.getByIndex(row - 1);
            }
            if (row == 0) {
                return columnRange.getByIndex(column - 1);
            }
            return rowRange.getByIndex(row - 1) * columnRange.getByIndex(column - 1);
        }

        private IntStream closedRange(int size) {
            return IntStream.rangeClosed(0, size);
        }

        private Range getRange(int min, int max) {
            return new Range(min, max);
        }
    }

    public static class Range {
        private int min;
        private int max;
        private List<Integer> sequence;

        public Range(int min, int max) {
            if (min > max) {
                throw new RuntimeException("Max value " + max + " should be equal or greater than Min: " + min);
            }
            this.min = min;
            this.max = max;
        }

        public List<Integer> getSequence() {
            if (sequence == null) {
                sequence = IntStream.rangeClosed(min, max)
                        .boxed()
                        .collect(Collectors.toList());
            }
            return sequence;
        }

        public Integer getByIndex(int index) {
            return sequence.get(index);
        }

        public int size() {
            return getSequence().size();
        }
    }

    public static class Matrix<T> {
        private List<Cell<T>> cells;
        private int columnSize;
        private int rowSize;

        public static <R> Matrix<R> initialize(int rowSize, int columnSize) {

            return null;
        }

        public Matrix() {
            this.cells = new ArrayList<>();
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
                    .orElseThrow(() -> new IllegalArgumentException("Not found cell by row = " + row + ", column = " + column));
        }

        public int getColumnSize() {
            if (columnSize == 0) {
                final Function<Cell<T>, Integer> getColumnNumber = Cell::getColumnNumber;
                columnSize = getMaxNumber(getColumnNumber);
            }
            return columnSize;
        }

        public int getRowSize() {
            if (rowSize == 0) {
                final Function<Cell<T>, Integer> getRowNumber = Cell::getRowNumber;
                rowSize = getMaxNumber(getRowNumber);
            }
            return rowSize;
        }

        private int getMaxNumber(Function<Cell<T>, Integer> mapper) {
            return cells.stream()
                    .map(mapper)
                    .mapToInt(Integer::valueOf)
                    .max()
                    .orElseThrow(() -> new RuntimeException("Max number is not found!"));
        }

        @Override
        public String toString() {
            final List<Cell<T>> sorted = getCells();
            final StringBuilder sb = new StringBuilder();
            final String space = "\t";
            sorted.forEach(cell -> {
                T value = cell.getValue();
                sb.append(value == null ? "" : value);
                String separator = cell.getColumnNumber() == getColumnSize() ? System.lineSeparator() : space;
                sb.append(separator);
            });
            return sb.toString();
        }
    }

    public static class CellSort {
        public static final Comparator<Cell> NATURAL = byRowAndThenByColumn();

        private static final Comparator<Cell> byRowAndThenByColumn() {
            Comparator<Cell> cop = Comparator.comparing(Cell::getRowNumber);
            return cop.thenComparing(Cell::getColumnNumber);
        }
    }

    public static class Cell<T> {
        private T value;
        private int rowNumber;
        private int columnNumber;

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

        public String toString() {
            return "column: " + columnNumber + " row: " + rowNumber + " value: " + value;
        }

        public static class Builder<T> {
            private Cell<T> cell;

            private void initCell() {
                if (cell == null) {
                    cell = new Cell<>();
                }
            }

            public Cell.Builder<T> withValue(T value) {
                initCell();
                cell.value = value;
                return this;
            }

            public Cell.Builder<T> withRowNumber(int rowNumber) {
                initCell();
                checkPositiveNumber(rowNumber);
                cell.rowNumber = rowNumber;
                return this;
            }

            public Cell.Builder<T> withColumnNumber(int columnNumber) {
                initCell();
                checkPositiveNumber(columnNumber);
                cell.columnNumber = columnNumber;
                return this;
            }

            public Cell<T> getCell() {
                initCell();
                return cell;
            }

            private void checkPositiveNumber(int number) {
                if (number <= 0) {
                    throw new RuntimeException("Number " + number + " is non positive! for cell (" + getCell().toString() + ")");
                }
            }
        }
    }
}