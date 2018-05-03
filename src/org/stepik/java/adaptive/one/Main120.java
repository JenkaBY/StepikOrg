package org.stepik.java.adaptive.one;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

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
        Long seconds = numberFromStr(readOneFromStdIn());
        print("");
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