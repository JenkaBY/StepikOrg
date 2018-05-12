package org.stepik.java.endava.four;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.DecimalFormat;
import java.util.*;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Main41 {

    /*
        Create a solution that stores data for multiple MarketItems and StorageItems.
        Create classes according to the following diagram, taking in consideration about inheritance and encapsulation.
        You need to simulate a program that stores and handles market and storage items.
        You read K commands from the input and display according output.
        Here is the list of commands
            ﻿1 name quantity price   Adds a new MarketItem in a store
            2 name  quantity weight  Adds a new StorageItem in the store
            3              Prints the name of the cheapest MarketItem
            4              Prints the total weight of all StorageItems (don't forget the quantity)
            5              Prints the names of all MarketItems in order they were added, separated with comma (,)
            6              Prints the names of all StorageItems which weight less than 10 in order they were added, separated with comma (,)
        Note: The printing commands 3-6 always print in one line and each print ends with new line.
     *  */

    public static void main(String[] args) {
        Runner runner = new Runner();
        runner.run();
    }

    public static class Runner {
        List<Item> store;

        public Runner() {
            store = new ArrayList<>();
        }

        public void run() {
            InputParser parser = new InputParser(readStdIn());
            parser.parse().getCommands().forEach(commandWithParams -> {
                switch (commandWithParams.command) {
                    case ADD_MARKET_ITEM_TO_STORE: {
                        saveMarketItemToStore(commandWithParams);
                        break;
                    }
                    case ADD_STORAGE_ITEM_TO_STORE: {
                        saveStorageItemToStore(commandWithParams);
                        break;
                    }
                    case PRINT_CHEAPEST_MARKET_ITEM:
                        printCheapestMarketItem();
                        break;
                    case PRINT_TOTAL_WEIGHT:
                        printTotalWeight();
                        break;
                    case PRINT_MARKET_ITEMS:
                        printMarketItems();
                        break;
                    case PRINT_STORAGE_ITEMS_WITH_WEIGHT_LESS_TEN:
                        printStorageItems();
                        break;
                    default:
                        break;
                }
            });
        }

        private void printMarketItems() {
            final String items = filteredStore(item -> item instanceof MarketItem)
                    .map(Item::toString)
                    .collect(Collectors.joining(", "));
            System.out.println(items);
        }

        private void printStorageItems() {
            final String items = filteredStore(item -> item instanceof StorageItem)
                    .map(item -> (StorageItem) item)
                    .filter(item -> item.getWeight() < 10)
                    .map(Item::toString)
                    .collect(Collectors.joining(", "));
            System.out.println(items);
        }

        private void printTotalWeight() {
            final double sum = filteredStore(item -> item instanceof StorageItem)
                    .map(item -> (StorageItem) item)
                    .map(item -> item.getWeight() * item.getQuantity())
                    .mapToDouble(Double::valueOf)
                    .sum();
            DecimalFormat df = new DecimalFormat("###.#");
            System.out.println(df.format(sum));
        }

        private void printCheapestMarketItem() {
            filteredStore(item -> item instanceof MarketItem)
                    .map(item -> (MarketItem) item)
                    .min((a, b) -> Float.compare(a.getPrice(), b.getPrice()))
                    .map(Item::toString)
                    .ifPresent(System.out::println);
        }

        private void saveStorageItemToStore(CommandWithParams commandWithParams) {
            String name = commandWithParams.params.poll();
            int quantity = numberFromStr(commandWithParams.params.poll());
            float weight = floatFromStr(commandWithParams.params.poll());
            Item storageItem = new StorageItem(name, quantity, weight);
            store.add(storageItem);
        }

        private void saveMarketItemToStore(CommandWithParams commandWithParams) {
            String name = commandWithParams.params.poll();
            int quantity = numberFromStr(commandWithParams.params.poll());
            float price = floatFromStr(commandWithParams.params.poll());
            Item marketItem = new MarketItem(name, quantity, price);
            store.add(marketItem);
        }

        private Stream<Item> filteredStore(Predicate<? super Item> condition) {
            return store.stream().filter(condition);
        }

    }

    public static abstract class Item {
        protected String name;

        protected int quantity;

        public Item(String name, int quantity) {
            this.name = name;
            this.quantity = quantity;
        }

        public int getQuantity() {
            return quantity;
        }

        @Override
        public String toString() {
            return name;
        }
    }

    public static class MarketItem extends Item {
        private float price;

        public MarketItem(String name, int quantity, float price) {
            super(name, quantity);
            this.price = price;
        }

        public float getPrice() {
            return price;
        }
    }

    public static class StorageItem extends Item {
        private float weight;

        public StorageItem(String name, int quantity, float weight) {
            super(name, quantity);
            this.weight = weight;
        }

        public float getWeight() {
            return weight;
        }
    }

    public static class CommandWithParams {
        private Command command;
        private LinkedList<String> params;

        public CommandWithParams(Command command) {
            this.command = command;
            params = new LinkedList<>();
        }

        public CommandWithParams withParameters(String[] params) {
            this.params.addAll(Arrays.asList(params));
            return this;
        }
    }

    enum Command {
        ADD_MARKET_ITEM_TO_STORE,
        ADD_STORAGE_ITEM_TO_STORE,
        PRINT_CHEAPEST_MARKET_ITEM,
        PRINT_TOTAL_WEIGHT,
        PRINT_MARKET_ITEMS,
        PRINT_STORAGE_ITEMS_WITH_WEIGHT_LESS_TEN,
        NOTHING;

        private static final String ONE = "1";
        private static final String TWO = "2";
        private static final String THREE = "3";
        private static final String FOUR = "4";
        private static final String FIVE = "5";
        private static final String SIX = "6";

        public static Command build(String type) {
            switch (type) {
                case ONE:
                    return ADD_MARKET_ITEM_TO_STORE;
                case TWO:
                    return ADD_STORAGE_ITEM_TO_STORE;
                case THREE:
                    return PRINT_CHEAPEST_MARKET_ITEM;
                case FOUR:
                    return PRINT_TOTAL_WEIGHT;
                case FIVE:
                    return PRINT_MARKET_ITEMS;
                case SIX:
                    return PRINT_STORAGE_ITEMS_WITH_WEIGHT_LESS_TEN;
                default:
                    return NOTHING;
            }
        }
    }

    public static class InputParser {
        Queue<String> lines;
        List<CommandWithParams> commands = new ArrayList<>();

        public InputParser(Queue<String> lines) {
            this.lines = lines;
        }

        public InputParser parse() {
            String line;
            while ((line = lines.poll()) != null) {
                String parsedCommand = splitAndGetFirst(line);
                Command command = Command.build(parsedCommand);
                CommandWithParams commandWithParams =
                        new CommandWithParams(command)
                                .withParameters(getParsedParams(line, parsedCommand).split("\\s+"));
                commands.add(commandWithParams);
            }
            return this;
        }

        public List<CommandWithParams> getCommands() {
            return commands;
        }

        private static String splitAndGetFirst(String line) {
            return line.split("\\s+")[0];
        }

        private static String getParsedParams(String wholeLine, String parsedCommand) {
            return wholeLine.replaceFirst(parsedCommand, "").trim();
        }
    }

    public static int numberFromStr(String readLine) {
        return Integer.valueOf(readLine);
    }

    public static float floatFromStr(String readLine) {
        return Float.valueOf(readLine);
    }

    public static LinkedList<String> readStdIn() {
        LinkedList<String> inputs = new LinkedList<>();
        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
            inputs = br.lines()
                    .filter(Objects::nonNull)
                    .filter(s -> !s.trim().isEmpty())
                    .collect(Collectors.toCollection(LinkedList::new));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return inputs;
    }
}
