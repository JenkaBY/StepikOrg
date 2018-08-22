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

public class Main1130 {
    /*
        Given a two-dimensional array (matrix) and the two numbers: i and j. Swap the columns with indices i and j within the matrix.
        Input contains matrix dimensions n and m, not exceeding 100, then the elements of the matrix, then the indices i and j.
        Sample Input:
            3 4
            11 12 13 14
            21 22 23 24
            31 32 33 34
            0 1
        Sample Output:
            12 11 13 14
            22 21 23 24
            32 31 33 34
    */

    public static void main(String[] args) {
        String[] input = readStdIn(1);
        Matrix<Integer> matrix = new Matrix<>();
        matrix = convertToMatrix(input, (row) -> numberFromStr(row, 0));
        String swappedColumnFirst = input[input.length - 1].split(" ")[0];
        String swappedColumnSecond = input[input.length - 1].split(" ")[1];

        MatrixFilling filling = new MatrixFilling(matrix);
        filling.setInitialData(swappedColumnFirst, swappedColumnSecond);
        print(filling.fillMatrix().toString());
    }

    public static <T> Matrix<T> convertToMatrix(String[] input, Function<String, T> function) {
        Matrix<T> matrix = new Matrix<>();
        for (int i = 1; i <= input.length - 1; i++) {
            String[] row = input[i - 1].split(" ");

            for (int j = 1; j <= row.length; j++) {
                final Cell<T> cell = convertToCell(row[j - 1], i, j, function);
                matrix.addCell(cell);
            }
        }
        return matrix;
    }

    public static <T> Cell<T> convertToCell(String row, int rowNumber, int columnNumber, Function<String, T> function) {
        return new Cell.Builder<T>()
                .withValue(function.apply(row))
                .withRowNumber(rowNumber)
                .withColumnNumber(columnNumber)
                .getCell();
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

    public static String[] readStdIn(int times) {
        String[] inputs = null;
        String[] matrixInputs = null;
        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
            inputs = new String[times];

            for (int i = 0; i < times; i++) {
                inputs[i] = br.readLine().trim();
            }
            int row = numberFromStr(inputs[0].split(" ")[0], 0);
            matrixInputs = new String[row + 1];
            for (int i = 0; i < row; i++) {
                matrixInputs[i] = br.readLine().trim();
            }
            matrixInputs[row] = br.readLine().trim();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return matrixInputs;
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
        int firstSwappedColumn;
        int secondSwappedColumn;

        public MatrixFilling(Matrix<Integer> matrix) {
            this.matrix = matrix;
        }

        public void setInitialData(String... data) {
            initialData = data;
            secondSwappedColumn = Integer.valueOf(initialData[1]) + 1;
            firstSwappedColumn = Integer.valueOf(initialData[0]) + 1;
        }

        private Matrix<Integer> fillMatrix() {
            Matrix<Integer> table = new Matrix<>();
            matrix.getCells().forEach(cell -> table.addCell(getSwappedCells(cell)));
            return table;
        }

        private Cell<Integer> getSwappedCells(Cell<Integer> cell) {
            int newNewColumn = cell.getColumnNumber();
            if (cell.getColumnNumber() == firstSwappedColumn) {
                newNewColumn = secondSwappedColumn;
            }
            if (cell.getColumnNumber() == secondSwappedColumn) {
                newNewColumn = firstSwappedColumn;
            }
            return new Cell.Builder<Integer>()
                    .withRowNumber(cell.getRowNumber())
                    .withColumnNumber(cell.getColumnNumber())
                    .withValue(matrix.getCellByRowAndColumn(cell.getRowNumber(), newNewColumn).getValue())
                    .getCell();
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
            final String space = " ";
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