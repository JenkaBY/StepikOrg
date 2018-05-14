package org.stepik.java.adaptive.one;

import java.util.Scanner;

public class Main168 {
    /*
        Write a program, which asks the name of the user and then greets him.
        Enter your name: Chuck
        Hello Chuck
    * */
    public static void main(String[] args) {
        print("Enter your name: ");
        String input = new Scanner(System.in).nextLine();
        println("Hello " + input);
    }


    public static void print(Object object) {
        System.out.print(object);
    }

    public static void println(Object object) {
        System.out.println(object);
    }
}
