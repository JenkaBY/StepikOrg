package org.stepik.java.adaptive.one;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main136 {
    /*
        In the Bioinformatics Institute a competition is held between the computer science and biology students.
        The winners will get a large and tasty pie. The team of biology students consists of a students, computer science team — b students.

        It is necessary to pre-cut the pie so that it would be possible to distribute the pieces of the pie to any team
        that won the competition, with each member of this team should get the same number of pieces of the pie.
        And since you do not want to cut the pie into the too many small pieces, you need to find the minimum suitable number.

        Write a program, which helps to find this number.

        The program gets the size of the teams (two positive integers a and b, each number is entered in a separate line)
        and should output the smallest number d, which is divisible by both numbers without remainder.

        Sample Input 1:
            7
            5
        Sample Output 1:
            35
        Sample Input 2:
            15
            15
        Sample Output 2:
            15
        Sample Input 3:
            12
            16
        Sample Output 3:
            48
    */

    public static void main(String[] args) {
        String[] grades = readStdIn(2);
        print(lcm(numberFromStr(grades[0]), numberFromStr(grades[1])));
    }

    public static void print(Object object) {
        System.out.println(object);
    }

    public static long numberFromStr(String readLine) {
        return Long.valueOf(readLine);
    }

    public static long gcd(long a, long b) {
        return b == 0 ? a : gcd(b, a % b);
    }

    public static long lcm(long a, long b) {
        return a / gcd(a, b) * b;
    }

    public static String[] readStdIn(int countNumbers) {
        String[] inputs = null;
        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
//            int countNumbers = numberFromStr(br.readLine());
            inputs = new String[countNumbers];
            for (int i = 0; i < countNumbers; i++) {
                inputs[i] = br.readLine().trim();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return inputs;
    }
}
