package org.stepik.java.endava.three;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;

public class Main31 {
    /*
        You need to create a solution that stores data for a bank account.
        A bank account will have name of the user and current balance (integer).
        After reading the data for the account, you need the read K transactions from the standard input.
        Transaction data will be stored in each line and represented with a number.
        Positive value means money gained, negative values mean money spent.
        You need to print the current balance of the user after the K transactions.
            Input: Name of the user, Current balance, K, data for K transactions
            Output: Balance of the user after K transactions.
    * */
    public static void main(String[] args) {
        LinkedList<String> inputs = readStdIn();
        Account account = new Account(inputs.pollFirst(), numberFromStr(inputs.pollFirst()));
        inputs.stream().map(Integer::valueOf).forEach(account::changeBalance);
        System.out.println(account.balance);
    }

    public static int numberFromStr(String readLine) {
        return Integer.valueOf(readLine);
    }

    public static LinkedList<String> readStdIn() {
        LinkedList<String> inputs = new LinkedList<>();
        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
            inputs.add(br.readLine().trim());
            inputs.add(br.readLine().trim());
            final int transactionCount = numberFromStr(br.readLine().trim());
            for (int i = 0; i < transactionCount; i++) {
                inputs.add(br.readLine().trim());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return inputs;
    }

    public static class Account {
        private String name;
        private Integer balance;

        public Account(String name, Integer balance) {
            this.name = name;
            this.balance = balance;
        }

        public void changeBalance(Integer changes) {
            balance = Integer.sum(balance, changes);
        }
    }
}
