package org.stepik.java.adaptive.one;

import java.util.Scanner;

public class Main171 {

/*
    At some point, you got tired of using the file names with spaces and you have decided to write a program that renames all files
    that contain spaces in their names by replacing the groups of spaces by the underscore symbol "_".
    First, you need to write a program that reads the string and replaces the group of white space characters by underscore symbols.
    Input format:
    Single line, containing arbitrary symbols, including spaces.
    Output format:
    Modified string.
*/

    public static void main(String[] args) {
        String fileName = new Scanner(System.in).nextLine();
        print(replaceSpaces(fileName));
    }

    public static String replaceSpaces(String line) {
        return line.replaceAll("\\s+", "_");
    }

    public static void print(Object object) {
        System.out.println(object);
    }
}