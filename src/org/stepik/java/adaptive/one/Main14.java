package org.stepik.java.adaptive.one;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import static java.lang.Math.abs;

public class Main14 {
    /*
        MKAD

        The length of the Moscow Ring Road (MKAD) is 109 kilometers. Biker Vasya starts from the zero kilometer of MKAD
        and drives with a speed of V kilometers per hour. On which mark will he stop after T hours?

        Input data format
            The program gets integers V and T as input. If V > 0, then Vasya moves in a positive direction along MKAD,
            if the value of V < 0 – in the negative direction. 0 ≤ T ≤ 1000, -1000 ≤ V ≤ 1000.
        Output data format
            The program should output an integer from 0 to 108 - the mark on which Vasya stops.
    */

    public static void main(String[] args) {
        String[] number = readStdIn(2);
        final int mkadDistance = 109;
        int velocity = numberFromStr(number[0]);
        int hour = numberFromStr(number[1]);

        print(new Ridding(new MKAD(mkadDistance), new Biker(velocity)).getMarkDistanceWhenRiderWillStop(hour));
    }

    public static class Ridding {
        protected final Road road;
        protected final Rider rider;
        private int riddenDistance;
        private int loops;

        public Ridding(Road road, Rider rider) {
            this.rider = rider;
            this.road = road;
        }

        public int getMarkDistanceWhenRiderWillStop(int hours) {
            riddenDistance = rider.calcRiddenDistanceForHours(hours);
            loops = road.getLoopsForDistance(riddenDistance);
            if (riddenDistance % road.getDistance() == 0) {
                return 0;
            }
            return rider.isBackDirection() ? getMarkForBackDirection(hours) : getMarkForForwardDirection(hours);
        }

        private int getMarkForBackDirection(int hours) {
            return loops > 0 ? road.getDistance() - riddenDistance % road.getDistance() : road.getDistance() - riddenDistance;
        }

        private int getMarkForForwardDirection(int hours) {
            return loops > 0 ? riddenDistance % road.getDistance() : riddenDistance;
        }
    }

    public static class Biker implements Rider {
        protected int speed;
        protected boolean isBackDirection;

        public Biker(int speed) {
            this.speed = abs(speed);
            isBackDirection = speed < 0;
        }

        @Override
        public int getSpeed() {
            return speed;
        }

        @Override
        public int calcRiddenDistanceForHours(int hours) {
            return speed * hours;
        }

        @Override
        public boolean isBackDirection() {
            return isBackDirection;
        }
    }

    public interface Rider {
        int getSpeed();

        int calcRiddenDistanceForHours(int hours);

        boolean isBackDirection();
    }

    public static class MKAD implements Road {
        private int distance;

        public MKAD(int distance) {
            this.distance = distance;
        }

        @Override
        public int getDistance() {
            return distance;
        }

        @Override
        public int getLoopsForDistance(int distance) {
            return abs(distance) / this.distance;
        }
    }

    public interface Road {
        int getDistance();

        int getLoopsForDistance(int distance);
    }

    public static void print(Object object) {
        System.out.println(object);
    }

    public static int numberFromStr(String readLine) {
        return Integer.valueOf(readLine);
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
}
