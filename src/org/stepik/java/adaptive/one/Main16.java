package org.stepik.java.adaptive.one;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.Time;
import java.text.SimpleDateFormat;
import java.util.TimeZone;

public class Main16 {
/*
    Digital watches
    Digital watches display time in the h:mm:ss format (from 0:00:00 to 23:59:59),
    i.e. first goes the number of hours, then goes the two-digit number of minutes,
    followed by the two-digit number of seconds. If necessary, number of minutes and seconds are filled by zeroes to a two-digit number.
    N seconds passed from the beginning of the day. Output what the watches will display.
*/

    private final static Integer MILLISECONDS_IN_DAY = 60 * 60 * 24;
    private static final long UTC_OFFSET = TimeZone.getDefault().getOffset(System.currentTimeMillis());

    public static void main(String[] args) {
        Long seconds = numberFromStr(readOneFromStdIn());
        Long remainder = getRemainderSeconds(seconds);
        Time time = new Time(remainder * 1000 - UTC_OFFSET);
        SimpleDateFormat formatter = new SimpleDateFormat("H:mm:ss");
        print(formatter.format(time));
    }

    public static Long getRemainderSeconds(Long seconds) {
        return seconds % MILLISECONDS_IN_DAY;
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
}