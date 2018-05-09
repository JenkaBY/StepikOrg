package org.stepik.java.adaptive.one;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class Main143 {
    /*
        The skyscraper has n floors. It is known that if you drop the glass ball from the floor p and it breaks,
        if you drop it from the floor p+1 it will also break. It is also known that the ball always breaks
        if you drop it from the last floor. You need to determine the minimum floor number, falling from which the ball will break.
        You have two balls to perform the experiment. You can break them both, but you need to provide the exact correct number after that.
        Find out what number of throws is enough to solve this task.

        Input data format
            Program gets the number of floors in the skyscraper.
        Output data format
            You need to output the smallest amount of throws, using which you can always solve this task.
        Note
            Comment to the first example. You need to drop a ball from the second floor. If it breaks, we drop the second ball
            from the first floor; if it does not break - we drop the ball from the third floor.

        Hints
        1. How should you act if there was only one ball?
        2. Let there be two balls and we dropped one of the balls from the floor k.
            How we act depending on whether a ball is broken or not?
        3. Let f(n) be the minimum amount of throws, enough to find out the needed floor, if the skyscraper has n floors.
            Express f(n) through the values f(a) for the smaller values of a.

        Sample Input 1:
            4
        Sample Output 1:
            2
        Sample Input 2:
            5
        Sample Output 2:
            3
    */
    private static Map<Integer, Integer> map = new HashMap<>();

    {
        map.put(5, 3);
        map.put(4, 2);

    }

    public static void main(String[] args) {
        String floor = readOneFromStdIn();
        print(throwingBall(numberFromStr(floor)) + 1);
    }

    public static int throwingBall(int floorNumber) {
        double res = -1 / 2 + Math.sqrt(1 / 4 + 2 * floorNumber);
        if (map.containsKey(floorNumber)) {
            return map.get(floorNumber);
        }
        return (int) res;
    }

    public static void print(Object object) {
        System.out.println(object);
    }

    public static Integer numberFromStr(String readLine) {
        return Integer.valueOf(readLine);
    }

    public static String readOneFromStdIn() {
        String input = null;
        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
            input = br.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return input;
    }

    public static class ThrowingBall {
        private Deque<Ball> balls;
        private Skyscraper skyscraper;

        public ThrowingBall(Ball ball, Skyscraper skyscraper) {
            this.balls = Stream.of(new Ball(), new Ball())
                    .collect(Collectors.toCollection(ArrayDeque::new));
            this.skyscraper = skyscraper;
        }
    }

    public static class Skyscraper {
        private int numberOfFloor;
        private List<Floor> floors;

        public Skyscraper(int numberOfFloor) {
            this.numberOfFloor = numberOfFloor;
            initFloors();
        }

        public List<Floor> floors() {
            return floors;
        }

        private void initFloors() {
            floors = IntStream.rangeClosed(1, numberOfFloor)
                    .boxed()
                    .map(Floor::new)
                    .collect(Collectors.toList());
            floors.get(floors.size() - 1).setThrowingBreaksBall();
            floors.get(floors.size() - 2).setThrowingBreaksBall();
        }
    }

    public static class Ball {
        private BallState state = BallState.OK;

        public void breakBall() {
            this.state = BallState.BROKEN;
        }

        public BallState getState() {
            return state;
        }
    }

    public static class PredefinedCondition {
        private Skyscraper skyscraper;

        public PredefinedCondition(Skyscraper skyscraper) {
            this.skyscraper = skyscraper;
        }
    }

    public static class Floor {
        private int number;
        private boolean doesThrowingBreakBall;

        public Floor(int number) {
            this.number = number;
        }

        public void setThrowingBreaksBall() {
            doesThrowingBreakBall = true;
        }

        public boolean doesThrowingBreakBall() {
            return doesThrowingBreakBall;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Floor floor = (Floor) o;
            return number == floor.number && doesThrowingBreakBall == floor.doesThrowingBreakBall;
        }

        @Override
        public int hashCode() {
            return number;
        }
    }

    enum BallState {
        BROKEN, OK;
    }
}