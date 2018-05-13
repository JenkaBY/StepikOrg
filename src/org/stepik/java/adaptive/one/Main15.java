package org.stepik.java.adaptive.one;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.time.LocalTime;
import java.time.temporal.ChronoField;

public class Main15 {
    /*
        Difference of times
        Given the values of the two moments in time in the same day: hours, minutes and seconds for each of the time moments.
        It is known that the second moment in time happened not earlier than the first one. Find how many seconds passed between
        these two moments of time.
        Input data format
            The program gets the input of the three integers: hours, minutes, seconds, defining the first moment of time
            and three integers that define the second moment time.
        Output data format
            Output the number of seconds between these two moments of time.
    */

    public static void main(String[] args) {
        String[] number = readStdIn(6);
        LocalTime first = getLocalTime(numberFromStr(number[0]), numberFromStr(number[1]), numberFromStr(number[2]));
        LocalTime second = getLocalTime(numberFromStr(number[3]), numberFromStr(number[4]), numberFromStr(number[5]));
        print(second.getLong(ChronoField.SECOND_OF_DAY) - first.getLong(ChronoField.SECOND_OF_DAY));
    }

    private static LocalTime getLocalTime(int hours, int minutes, int seconds) {
        return LocalTime.of(hours, minutes, seconds);
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
