package org.stepik.java.endava.six;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;
import java.util.stream.Collectors;

public class Main62 {
    /*
    *
    *           Bank
        John is working as a security guard at the bank. Each day he records the name and the age of the people entering the bank.
        At the end of his shift he writes 'END! ' to mark that there are no more people for that day.
        He then need to print a report of all of the people who entered the bank, ordered by their age. If there are two with the same age,
        they are ordered by their name alphabetically.
        A person with the same age and name can enter the bank more than once in the day, so that person should only occur once in the report.
        Input: Name (String) Age (Integer) until for name is inputted 'END!'
        Output: 'Age, Name' of the people that entered the bank,  separated by new line.
    * */
    public static void main(String[] args) {
        PersonStore people = new PersonStore();
        people.fillStoreFromInputs(new InputParser(readStdIn()).parse());
        people.getOrderedAndEnteredPeople()
                .stream()
                .distinct()
                .forEach(System.out::println);
    }

    public static class PersonStore {
        List<Person> people;

        public PersonStore() {
            people = new ArrayList<>();
        }

        public void fillStoreFromInputs(List<String[]> inputs) {
            inputs.stream()
                    .filter(pair -> pair.length == 2)
                    .map(pair -> new Person(pair[0], Integer.valueOf(pair[1])))
                    .forEach(this::addPerson);
        }

        public void addPerson(Person person) {
            people.add(person);
        }

        public List<Person> getOrderedAndEnteredPeople() {
            return people.stream()
                    .sorted(Comparator.comparing(Person::getAge)
                            .thenComparing(Person::getName))
                    .collect(Collectors.toList());
        }

    }

    public static class Person {
        private String name;
        private int age;
//        public final static Comparator<Person> BY_AGE_ASC_AND_NAME_ASC = new ByAgeAscAndNameAsc();

        public Person(String name, int age) {
            this.name = name;
            this.age = age;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getAge() {
            return age;
        }

        public void setAge(int age) {
            this.age = age;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;

            Person person = (Person) o;

            return age == person.age && name.equals(person.name);
        }

        @Override
        public int hashCode() {
            int result = name.hashCode();
            result = 31 * result + age;
            return result;
        }

        @Override
        public String toString() {
            return age + ", " + name;
        }


//        private static class ByAgeAscAndNameAsc implements Comparator<Person> {
//            @Override
//            public int compare(Person o1, Person o2) {
//                return 0;
//            }
//        }
    }

    public static class InputParser {
        List<String> inputs;
        private static final String lastLine = "END!";

        public InputParser(List<String> inputs) {
            this.inputs = inputs;
        }

        public List<String[]> parse() {
            return inputs.stream()
                    .filter(input -> !input.equals(lastLine))
                    .map(input -> input.trim().split(" "))
                    .collect(Collectors.toList());
        }
    }

    public static LinkedList<String> readStdIn() {
        LinkedList<String> inputs = new LinkedList<>();
        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
            inputs = br.lines()
                    .filter(Objects::nonNull)
                    .filter(s -> !s.trim().isEmpty())
                    .collect(Collectors.toCollection(LinkedList::new));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return inputs;
    }
}
