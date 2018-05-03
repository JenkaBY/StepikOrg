package org.stepik.java.adaptive.one;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.stream.Collectors;

public class Main120 {
/*
    Healthy sleep

    Ann watched a TV program about health and learned that it is recommended to sleep at least A hours per day, but oversleeping is also not healthy
    and you should not sleep more than B hours. Now Ann sleeps H hours per day. If Ann's sleep schedule complies with the requirements of that TV program – print "Normal".
    If Ann sleeps less than A hours, output “Deficiency”, if she sleeps more than B hours, output “Excess”.
    Input to this program are the three strings with variables in the following order: A, B, H. A is always less than or equal to B.
    Please note latters case: the output should exactly correspond to what required in the problem, i.e. if the program has to output "Excess",
    such output as "excess", "EXCESS", "ExCeSs" and others will not be graded as correct.

    You should carefully think about all conditions, which you need to use. A special attention should be paid to the strictness of the used conditional operators:
    distinguish between < and ≤; > and ≥. In order to understand which ones to use, please carefully read the problem statement.
*/

    public static void main(String[] args) {
        String[] inputHours = readStdIn(3);
        Hours hours = new Hours(Arrays.stream(inputHours)
                .map(Integer::valueOf)
                .collect(Collectors.toList()).toArray(new Integer[]{})
        );
        SleepSchedule schedule = new SleepSchedule(hours.getMin(), hours.getMax());
        print(schedule.howISlept(hours.slept));
    }

    public static void print(Object object) {
        System.out.println(object.toString());
    }

    public static Long numberFromStr(String readLine) {
        return Long.valueOf(readLine);
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

    public static String[] readStdIn(int readTimes) {
        String[] inputs = null;
        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
            inputs = new String[readTimes];
            for (int i = 0; i < readTimes; i++) {
                inputs[i] = br.readLine().trim();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return inputs;
    }

    public static class SleepSchedule {
        private final int minHours;
        private final int maxHours;
        private final static String NORMAL = "Normal";
        private final static String EXCESS = "Excess";
        private final static String DEFICIENCY = "Deficiency";

        public SleepSchedule(int minHours, int maxHours) {
            this.minHours = minHours;
            this.maxHours = maxHours;
        }

        public String howISlept(int sleepHours) {
            if (!sleptAtLeastMin(sleepHours)) {
                return DEFICIENCY;
            }
            if (!sleptLessThanMax(sleepHours)) {
                return EXCESS;
            }
            return NORMAL;
        }

        private boolean sleptAtLeastMin(int sleepHours) {
            return sleepHours >= minHours;
        }

        private boolean sleptLessThanMax(int sleepHours) {
            return sleepHours < maxHours;
        }
    }

    public static class Hours {
        private final int max;
        private final int min;
        private final int slept;

        public Hours(Integer[] hours) {
            this.min = hours[0];
            this.max = hours[1];
            this.slept = hours[2];
        }

        public int getMax() {
            return max;
        }

        public int getMin() {
            return min;
        }

        public int getSlept() {
            return slept;
        }
    }
}