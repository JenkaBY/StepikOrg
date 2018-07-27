package org.stepik.java.adaptive.one;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

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
        Matrix<Integer> matrix = new Matrix<>();
        matrix = convertToMatrix(input, (row) -> numberFromStr(row));
        System.out.println(new MatrixRotator<Integer>(matrix).rotate());

    }

    public static <T> Matrix<T> convertToMatrix(String[] input, Function<String, T> function) {
        Matrix<T> matrix = new Matrix<>();
        for (int i = 1; i <= input.length; i++) {
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

    public static int numberFromStr(String readLine) {
        return Integer.valueOf(readLine.trim());
    }

    public static String[] readStdIn(int times) {
        String[] inputs = null;
        String[] matrixInputs = null;
        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
            inputs = new String[times];

            for (int i = 0; i < times; i++) {
                inputs[i] = br.readLine().trim();
            }
//            int column = numberFromStr(inputs[0].split(" ")[0]);

            int row = numberFromStr(inputs[0].split(" ")[1]);
            matrixInputs = new String[row];
            for (int i = 0; i < row; i++) {
                matrixInputs[i] = br.readLine().trim();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return matrixInputs;
    }

    public static class Matrix<T> {
        private List<Cell<T>> cells;
        private int columnSize;
        private int rowSize;

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
                    .orElseThrow(() -> new IllegalArgumentException("Not found cells by column = " + column + ", row = " + row));
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
                    .orElse(-1);
        }
        @Override
        public String toString() {
            final List<Cell<T>> sorted = getCells();
            final StringBuilder sb = new StringBuilder();
            final String space = " ";
            sorted.forEach(cell -> {
                sb.append(cell.getValue());
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

    public static class MatrixRotator<T> {
        Matrix<T> matrix;

        public MatrixRotator(Matrix<T> matrix) {
            this.matrix = matrix;
        }

        public Matrix<T> rotate() {
            final Matrix<T> rotatedMatrix = new Matrix<>();
            matrix.getCells().forEach(cell -> {
                Cell<T> original = matrix.getCellByRowAndColumn(cell.getColumnNumber(), cell.getRowNumber());
                Cell<T> rotated = new Cell.Builder()
                        .withValue(original.getValue())
                        .withRowNumber(cell.getColumnNumber())
                        .withColumnNumber(original.getRowNumber() - cell.getColumnNumber() - 1)
                        .getCell();
                rotatedMatrix.addCell(rotated);
            });
            return rotatedMatrix;
        }
    }

//            public void rotateCWto90Degrees() {
//            int[][] tempMatrix = new int[getHorizontalSize()][getVerticalSize()];
//
//            for (int tempVertical = 0; tempVertical < getHorizontalSize(); tempVertical++) {
//                for (int tempHorizontal = 0; tempHorizontal < getVerticalSize(); tempHorizontal++) {
//                    tempMatrix[tempVertical][tempHorizontal] = getElement(getVerticalSize() - tempHorizontal - 1,
//                            tempVertical);
//                }
//            }
//            array = tempMatrix;
//        }

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

        public static class Builder<T> {
            private Cell<T> cell;

            private void initCell() {
                if (cell == null) {
                    cell = new Cell<>();
                }
            }

            public Builder<T> withValue(T value) {
                initCell();
                cell.value = value;
                return this;
            }

            public Builder<T> withRowNumber(int rowNumber) {
                initCell();
                checkPositiveNumber(rowNumber);
                cell.rowNumber = rowNumber;
                return this;
            }

            public Builder<T> withColumnNumber(int columnNumber) {
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
                    throw new RuntimeException("Number " + number + " is negative!");
                }
            }
        }
    }
}
