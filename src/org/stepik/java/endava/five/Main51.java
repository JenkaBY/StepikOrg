package org.stepik.java.endava.five;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Main51 {

    /*
                The fox
        You need to create animal simulation program for some of the animals mentioned in the song:
        Ylvis - The Fox (https://www.youtube.com/watch?v=jofNR_WkoCE)
        Types of animals, their sound and if they make sounds at night.
            Animal   Sound   make_sounds_at_night
            Dog:    "Woof"   true
            Cat:    "Meow"   false
            Bird:    "Tweet"   false
            Mouse:   "Sqeek"   false
            Cow:    "Moo"    false
            ﻿Frog:    "Croak"   true
            The fox:   "Ring-ding-ding-ding-dingeringeding" true
        You will read commands from the input and process them.
        The list of the commands is the following:
         1  Add new Dog
         2 Add new Cat
         3 Add new Bird
         4 Add new Mouse
         5 Add new Cow
         6 Add new Frog
         7 Add new Fox
         8 Make daily ﻿sounds. Print the sequence of sounds of ﻿ all animals ﻿in sequence they were added, separated with commas (,)
         9 Make night sounds. Print the sequence of sounds of animals that can make sounds at night, separated with commas ﻿
         10 Print the sound of a ﻿fox ("Ring-ding-ding-ding-dingeringeding")  ﻿if there is at least one fox in the animal kingdom. Prints "
        What the fox say?" otherwise
         11 Exit

        The user inputs commands until he inputs 11.
        Sample Input 1:
            1 2 3 4 1 2 3 8 11
        Sample Output 1:
            Woof, Meow, Tweet, Sqeek, Woof, Meow, Tweet
        Sample Input 2:
            1 2 3 1 2 3 1 1 1 1 2 2 2 9 10 11
        Sample Output 2:
            Woof, Woof, Woof, Woof, Woof, Woof
            What the fox say?
        Sample Input 3:
            7 7 7 7 7 7 7 7 7 7 7 7 10 11
        Sample Output 3:
            Ring-ding-ding-ding-dingeringeding
        Sample Input 4:
            1 2 3 4 5 6 7 1 2 3 4 5 6 7 8 9 10 11
        Sample Output 4:
            Woof, Meow, Tweet, Sqeek, Moo, Croak, Ring-ding-ding-ding-dingeringeding, Woof, Meow, Tweet, Sqeek, Moo, Croak, Ring-ding-ding-ding-dingeringeding
            Woof, Croak, Ring-ding-ding-ding-dingeringeding, Woof, Croak, Ring-ding-ding-ding-dingeringeding
            Ring-ding-ding-ding-dingeringeding
     *  */

    public static void main(String[] args) {
        Process process = new Process();
        List<Integer> inputs = readStdIn();
        inputs.forEach(process::withCode);
    }

    public static abstract class Animal {
        protected boolean isMakeSoundAtNight;

        protected String sound;

        public Animal(String sound, boolean isMakeSoundAtNight) {
            this.isMakeSoundAtNight = isMakeSoundAtNight;
            this.sound = sound;
        }

        public Animal() {
        }

        public String getSound() {
            return sound;
        }

        public boolean isMakeSoundAtNight() {
            return isMakeSoundAtNight;
        }
    }

    public static class Dog extends Animal {
        public Dog() {
            super();
            this.isMakeSoundAtNight = true;
            this.sound = "Woof";
        }
    }

    public static class Cat extends Animal {
        public Cat() {
            super();
            this.isMakeSoundAtNight = false;
            this.sound = "Meow";
        }
    }

    public static class Bird extends Animal {
        public Bird() {
            super();
            this.isMakeSoundAtNight = false;
            this.sound = "Tweet";
        }
    }

    public static class Mouse extends Animal {
        public Mouse() {
            super();
            this.isMakeSoundAtNight = false;
            this.sound = "Sqeek";
        }
    }

    public static class Cow extends Animal {
        public Cow() {
            super();
            this.isMakeSoundAtNight = false;
            this.sound = "Moo";
        }
    }

    public static class Frog extends Animal {
        public Frog() {
            super();
            this.isMakeSoundAtNight = true;
            this.sound = "Croak";
        }
    }

    public static class Fox extends Animal {
        public Fox() {
            super();
            this.isMakeSoundAtNight = true;
            this.sound = "Ring-ding-ding-ding-dingeringeding";
        }
    }

    public static class AnimalFactory {
        private AnimalFactory factory;

        private AnimalFactory() {
        }

        public AnimalFactory getInstance() {
            initialize();
            return factory;
        }

        private void initialize() {
            if (Objects.isNull(factory)) {
                factory = new AnimalFactory();
            }
        }

        public Animal createAnimal(int code) {
            initialize();
            switch (code) {
                case 1:
                    return new Dog();
                case 2:
                    return new Cat();
                case 3:
                    return new Bird();
                case 4:
                    return new Mouse();
                case 5:
                    return new Cow();
                case 6:
                    return new Frog();
                case 7:
                    return new Fox();
                default:
                    throw new IllegalArgumentException("Could not create an animal using the code: " + code);
            }
        }
    }

    public static class Process {
        AnimalStore store;

        public Process() {
            this.store = new AnimalStore();
        }

        public void withCode(int code) {
            if (code >= 1 && code <= 7) {
                createAndAddAnimalByCode(code);
                return;
            }
            String sounds = "";
            switch (code) {
                case 8:
                    sounds = getSaidSoundsAllAnimal();
                    break;
                case 9:
                    sounds = getSaidSoundsAnimalsMakingSoundAtNight();
                    break;
                case 10:
                    sounds = getFoxSound();
                    break;
                case 11:
                    System.exit(0);
                    break;
                default:
                    throw new IllegalArgumentException("Wrong code: " + code);
            }
            System.out.println(sounds);
        }

        private void createAndAddAnimalByCode(int code) {
            store.addAnimal(new AnimalFactory().createAnimal(code));
        }

        private String getSaidSoundsAllAnimal() {
            return getSoundsAnimals(store.getAll());
        }

        private String getSaidSoundsAnimalsMakingSoundAtNight() {
            return getSoundsAnimals(store.getAnimalsMakingNightSounds());
        }

        private String getSoundsAnimals(Stream<Animal> animals) {
            return animals
                    .map(Animal::getSound)
                    .collect(Collectors.joining(", "));
        }

        private String getFoxSound() {
            return getSoundForAnimalOrDefault(store.getFirstFox(), "What the fox say?");
        }

        private String getSoundForAnimalOrDefault(Optional<? extends Animal> animal, String defaultSound) {
            return animal.map(Animal::getSound).orElse(defaultSound);
        }
    }

    public static class AnimalStore {
        private List<Animal> animals;

        public AnimalStore(List<Animal> animals) {
            this.animals = animals;
        }

        public AnimalStore() {
            this.animals = new ArrayList<>();
        }

        public void addAnimal(Animal animal) {
            animals.add(animal);
        }

        public Stream<Animal> getAnimalsMakingNightSounds() {
            return animals.stream()
                    .filter(Animal::isMakeSoundAtNight);
        }

        public Stream<Animal> getAll() {
            return animals.stream();
        }

        public Optional<Fox> getFirstFox() {
            return animals.stream()
                    .filter(animal -> animal instanceof Fox)
                    .findFirst()
                    .map(animal -> (Fox) animal);
        }
    }

    public static void print(Object object) {
        System.out.println(object);
    }

    public static int numberFromStr(String readLine) {
        return Integer.valueOf(readLine);
    }

    public static List<Integer> readStdIn() {
        LinkedList<Integer> inputs = new LinkedList<>();
        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
            final String[] numbers = br.readLine().split(" ");
            inputs = Stream.of(numbers)
                    .filter(Objects::nonNull)
                    .filter(s -> !s.trim().isEmpty())
                    .map(s -> numberFromStr(s))
                    .collect(Collectors.toCollection(LinkedList::new));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return inputs;
    }
}
