package org.stepik.java.endava.three;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;
import java.util.stream.Collectors;

public class Main32 {
    public static void main(String[] args) {
        Runner runner = new Runner();
        runner.run();
    }

    public static class Runner {
        List<Course> courses;

        public Runner() {
            courses = new ArrayList<>();
        }

        public void run() {
            InputParser parser = new InputParser(readStdIn());
            parser.parse().getCommands().forEach(commandWithParams -> {
                switch (commandWithParams.command) {
                    case CREATE_COURSE_WITH_STUDENTS: {
                        String name = commandWithParams.params.poll();
                        Integer numberOfStudents = numberFromStr(commandWithParams.params.poll());
                        Integer numberOfSemester = numberFromStr(commandWithParams.params.poll());
                        courses.add(new Course(name, numberOfStudents, numberOfSemester));
                        break;
                    }
                    case CREATE_BASIC_COURSE: {
                        String name = commandWithParams.params.poll();
                        Integer numberOfSemester = numberFromStr(commandWithParams.params.poll());
                        courses.add(new Course(name, numberOfSemester));
                        break;
                    }
                    case PRINT:
                        courses.forEach(System.out::println);
                        break;
                    default:
                        break;
                }
            });
        }

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

        public CommandWithParams(Command command) {
            this.command = command;
            params = new LinkedList<>();
        }

        public CommandWithParams withParameters(String[] params) {
            this.params.addAll(Arrays.asList(params));
            return this;
        }
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
            String line;
            while ((line = lines.poll()) != null) {
                String parsedCommand = splitAndGetFirst(line);
                Command command = Command.build(parsedCommand);
                CommandWithParams commandWithParams =
                        new CommandWithParams(command)
                                .withParameters(getParsedParams(line, parsedCommand).split("\\s+"));
                commands.add(commandWithParams);
            }
            return this;
        }

        public List<CommandWithParams> getCommands() {
            return commands;
        }

        private static String splitAndGetFirst(String line) {
            return line.split("\\s+")[0];
        }

        private static String getParsedParams(String wholeLine, String parsedCommand) {
            return wholeLine.replaceFirst(parsedCommand, "").trim();
        }
    }

    public static int numberFromStr(String readLine) {
        return Integer.valueOf(readLine);
    }

    public static LinkedList<String> readStdIn() {
        LinkedList<String> inputs = new LinkedList<>();
        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
            inputs = br.lines()
                    .filter(s -> s != null)
                    .filter(s -> !s.trim().isEmpty())
                    .collect(Collectors.toCollection(LinkedList::new));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return inputs;
    }
}
