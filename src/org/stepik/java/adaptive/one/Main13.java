package org.stepik.java.adaptive.one;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main13 {
    /*
        Snail
    Snail creeps up the vertical pole of height H feets. Per day it goes A feets up, and per night it goes B feets down.
    In which day the snail will reach the top of the pole?
    Input data format
        On the input the program receives non-negative integers H, A, B, where H > B and A > B. Every integer does not exceed 100.
    */

    public static void main(String[] args) {
        String[] number = readStdIn(3);
        int high = numberFromStr(number[0]);
        int creepsUp = numberFromStr(number[1]);
        int creepsDown = numberFromStr(number[2]);

        print(new SnailCreeping(creepsUp, creepsDown).howManyDaysSnailCreepingToHigh(high));
    }

    public static class SnailCreeping {
        private int creepsUp;
        private int creepsDown;

        public SnailCreeping(int creepsUp, int creepsDown) {
            this.creepsUp = creepsUp;
            this.creepsDown = creepsDown;
        }

        public int howManyDaysSnailCreepingToHigh(int high) {
            int creepingDifferent = (creepsUp - creepsDown);
            int days = 1;
            while ((days * creepingDifferent + creepsDown) < high) {
                days++;
            }
            return days;
        }
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
