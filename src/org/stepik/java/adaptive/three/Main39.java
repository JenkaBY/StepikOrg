package org.stepik.java.adaptive.three;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Main39 {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String allElements = reader.readLine();
        List<Integer> bigList = createBigList(allElements);

        ArrayList<Integer> div2list = bigList.stream()
                .filter(num -> num % 2 == 0)
                .sorted()
                .collect(Collectors.toCollection(ArrayList::new));
        ArrayList<Integer> div3list = bigList.stream()
                .filter(num -> num % 3 == 0)
                .sorted()
                .collect(Collectors.toCollection(ArrayList::new));
        ArrayList<Integer> otherlist = bigList.stream()
                .filter(num -> !div2list.contains(num) && !div3list.contains(num))
                .sorted()
                .collect(Collectors.toCollection(ArrayList::new));
        //add the necessary elements from bigList to div2list,div3list,otherlist and sort all three lists

        //get result list from createListOfLists
        List<List<Integer>> resultList = createListOfLists(div2list, div3list, otherlist);

        //call printInLine() for resultList
        printInLine(resultList);
    }

    public static ArrayList<Integer> getCollect(List<Integer> bigList, Predicate<Integer> filter) {
        return bigList.stream()
                .filter(filter)
                .sorted()
                .collect(Collectors.toCollection(ArrayList::new));
    }

    public static List<Integer> createBigList(String str) {
        List<Integer> bigList = new ArrayList<Integer>();
        String[] strarr = str.split(" ");

        for (String s : strarr) {
            //convert(using Integer.parseInt(s) or Integer.valueOf(s)) and add all elements from srtarr to bigList
            bigList.add(Integer.valueOf(s));
        }
        return bigList;
    }

    public static List<List<Integer>> createListOfLists(List<Integer> div2list, List<Integer> div3list, List<Integer> otherList) {
        //add all lists like items in resultList(list of lists) and return it, first-div2list, second-div3list, third-otherList
        List<List<Integer>> resultList = new ArrayList<>();
        resultList.add(div2list);
        resultList.add(div3list);
        resultList.add(otherList);
        return resultList;
    }

    private static void printInLine(List<List<Integer>> resultList) {
        Stream.of(resultList).forEach(list -> System.out.println(list.toArray()));
    }
}