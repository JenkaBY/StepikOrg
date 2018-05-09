package org.stepik.java.endava.three;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class Main32 {
    public static void main(String[] args) {

    }

    public static class Course {
        private String name;
        private Integer numberOfStudents;
        private Semester semester;

        public Course(String name, Integer numberOfSemester) {
            this.name = name;
            this.semester = Semester.build(numberOfSemester);
            this.numberOfStudents = 0;
        }

        public Course(String name, Integer numberOfStudents, Integer numberOfSemester) {
            this.name = name;
            this.numberOfStudents = numberOfStudents;
            this.semester = Semester.build(numberOfSemester);
        }

        @Override
        public String toString() {
            return name + ", " + semester.toString() + ", " + numberOfStudents;
        }
    }

    enum Semester {
        WINTER, SUMMER;
        private final static String suffix = " Semester";

        public static Semester build(int number) {
            return number % 2 == 0 ? SUMMER : WINTER;
        }

        @Override
        public String toString() {
            return capitalize(this.name()) + suffix;
        }

        private static String capitalize(String word) {
            return word.substring(0, 1).toUpperCase() + word.substring(1).toLowerCase();
        }
    }

    public static class CommandWithParams {
        private Command command;
        private LinkedList<String> params;

    }

    enum Command {
        CREATE_COURSE_WITH_STUDENTS, CREATE_BASIC_COURSE, PRINT, NOTHING;

        private static final String ONE = "1";
        private static final String TWO = "2";
        private static final String THREE = "3";

        public static Command build(String type) {
            switch (type) {
                case ONE:
                    return CREATE_COURSE_WITH_STUDENTS;
                case TWO:
                    return CREATE_BASIC_COURSE;
                case THREE:
                    return PRINT;
                default:
                    return NOTHING;
            }
        }
    }

    public static class InputParser {
        Queue<String> lines;
        List<CommandWithParams> commands = new ArrayList<>();

        public InputParser(Queue<String> lines) {
            this.lines = lines;
        }

        public InputParser parse() {
            String line = lines.poll();
            String parsedCommand = splitAndGetFirst(line);
            Command command = Command.build(parsedCommand);


            return this;
        }

        public List<CommandWithParams> getCommands() {
            return commands;
        }

        private static String splitAndGetFirst(String line) {
            return line.split("\\s+")[0];
        }

        private static String getParsedParams(String wholeLine, String parsedCommand) {
            return wholeLine.replace(parsedCommand, "").trim();
        }
    }
}
