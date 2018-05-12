package org.stepik.java.endava.four;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Main42 {

    /*
        You need to create a solution that stores data for multiple hotels.
        This time, you will define the classes on your own, by looking on the input and output of the commands.
        You are given a list of K commands with the meaning:
            1 hotel_name                          Add a new hotel with the given unique name
            2 hotel_name people# floor# size           Add a standard room with the specified data to an existing hotel with hotel_name
            3 hotel_name people# floor# has_jacuzzi      Add an apartment room with the specified data to an existing hotel with hotel_name
            4 hotel_name                         Prints the info of a hotel  in the following format Name: hotel_name,
                                                            Standard Rooms: Number, Apartments: Number﻿
            5 hotel_name hotel_name                 Prints the name of the hotel which has more jacuzzies of the two.
        Note: There won't be any two hotels with the same name.
        Note: The name of the hotels in the commands will always be valid.
        Legend: 
            people# - number of people for a room
            floor# - floor where the room is situated in
            size - the size of the room in square meters
            has_jacuzzi - does the room have a jacuzzi
     *  */

    public static void main(String[] args) {
        Runner runner = new Runner();
        runner.run();
    }

    public static class Runner {
        HotelStore hotels;

        public Runner() {
            hotels = new HotelStore();
        }

        public void run() {
            InputParser parser = new InputParser(readStdIn());
            parser.parse().getCommands().forEach(commandWithParams -> {
                switch (commandWithParams.command) {
                    case ADD_NEW_HOTEL:
                        createAndAddNewHotel(commandWithParams);
                        break;
                    case ADD_STANDARD_ROOM_TO_HOTEL:
                        addStandardRoomToHotel(commandWithParams);
                        break;
                    case ADD_APARTMENT_TO_HOTEL:
                        addApartmentToHotel(commandWithParams);
                        break;
                    case PRINT_HOTEL_INFO:
                        printHotelInfo(commandWithParams);
                        break;
                    case PRINT_HOTELS_WITH_MORE_JACUZZI:
                        printHotelWithMoreJacuzzi(commandWithParams);
                        break;
                    default:
                        break;
                }
            });
        }

        private void printHotelWithMoreJacuzzi(CommandWithParams commandWithParams) {
            String firstHotelName = commandWithParams.params.poll();
            String secondHotelName = commandWithParams.params.poll();
            Hotel hotel = Stream.of(hotels.getHotelByName(firstHotelName), hotels.getHotelByName(secondHotelName))
                    .sorted(Hotel.BY_JACUZZI_NUMBER.reversed())
                    .findFirst()
                    .orElse(new Hotel(""));
            System.out.println(hotel.getName());
        }

        private void printHotelInfo(CommandWithParams commandWithParams) {
            String hotelName = commandWithParams.params.poll();
            System.out.println(hotels.getHotelByName(hotelName).toString());
        }

        private void addApartmentToHotel(CommandWithParams commandWithParams) {
            String hotelName = commandWithParams.params.poll();
            int peopleNumber = numberFromStr(commandWithParams.params.poll());
            int floor = numberFromStr(commandWithParams.params.poll());
            boolean hasJacuzzi = Boolean.parseBoolean(commandWithParams.params.poll());
            hotels.getHotelByName(hotelName).addRoom(new Apartment(peopleNumber, floor, hasJacuzzi));
        }

        private void addStandardRoomToHotel(CommandWithParams commandWithParams) {
            String hotelName = commandWithParams.params.poll();
            int peopleNumber = numberFromStr(commandWithParams.params.poll());
            int floor = numberFromStr(commandWithParams.params.poll());
            double size = floatFromStr(commandWithParams.params.poll());
            hotels.getHotelByName(hotelName).addRoom(new StandardRoom(peopleNumber, floor, size));
        }

        private void createAndAddNewHotel(CommandWithParams commandWithParams) {
            String hotelName = commandWithParams.params.poll();
            hotels.createAndAddNewHotelWithName(hotelName);
        }
    }

    public static class Hotel {
        private List<Room> rooms = new ArrayList<>();
        private String name;
        public final static Comparator<Hotel> BY_JACUZZI_NUMBER = new ByJacuzziNumber();

        public Hotel(String name) {
            this.name = name;
        }

        public String getName() {
            return name;
        }

        public void addRoom(Room room) {
            rooms.add(room);
        }

        public long getNumberOfStandardRooms() {
            return getStandardRoomsStream()
                    .count();
        }

        private Stream<StandardRoom> getStandardRoomsStream() {
            return rooms.parallelStream()
                    .filter(room -> room instanceof StandardRoom)
                    .map(room -> (StandardRoom) room);
        }

        public long getNumberOfApartments() {
            return getApartmentsStream()
                    .count();
        }

        public long getNumberRoomWithJacuzzi() {
            return getApartmentsStream()
                    .filter(Apartment::hasJacuzzi)
                    .count();
        }

        private Stream<Apartment> getApartmentsStream() {
            return rooms.parallelStream()
                    .filter(room -> room instanceof Apartment)
                    .map(room -> (Apartment) room);
        }

        @Override
        public String toString() {
            return "Name: " + name
                    + ", Standard Rooms: " + getNumberOfStandardRooms()
                    + ", Apartments: " + getNumberOfApartments();
        }

        private static class ByJacuzziNumber implements Comparator<Hotel> {
            @Override
            public int compare(Hotel first, Hotel second) {
                return Long.compare(first.getNumberRoomWithJacuzzi(), second.getNumberRoomWithJacuzzi());
            }
        }
    }

    public static class HotelStore {
        List<Hotel> hotels;

        public HotelStore() {
            hotels = new ArrayList<>();
        }

        public Hotel getHotelByName(String hotelName) {
            return getHotels()
                    .filter(hotel -> hotel.getName().equals(hotelName))
                    .findFirst().orElse(new Hotel(""));
        }

        public void createAndAddNewHotelWithName(String hotelName) {
            hotels.add(new Hotel(hotelName));
        }

        private Stream<Hotel> getHotels() {
            return hotels.stream();
        }
    }

    public static abstract class Room {
        protected int peopleNumber;
        protected int floorNumber;

        public Room(int peopleNumber, int floorNumber) {
            this.peopleNumber = peopleNumber;
            this.floorNumber = floorNumber;
        }
    }

    public static class StandardRoom extends Room {
        private double size;

        public StandardRoom(int peopleNumber, int floorNumber, double size) {
            super(peopleNumber, floorNumber);
            this.size = size;
        }

        public double getSize() {
            return size;
        }
    }

    public static class Apartment extends Room {
        private boolean hasJacuzzi;

        public Apartment(int peopleNumber, int floorNumber, boolean hasJacuzzi) {
            super(peopleNumber, floorNumber);
            this.hasJacuzzi = hasJacuzzi;
        }

        public boolean hasJacuzzi() {
            return hasJacuzzi;
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
        ADD_NEW_HOTEL,
        ADD_STANDARD_ROOM_TO_HOTEL,
        ADD_APARTMENT_TO_HOTEL,
        PRINT_HOTEL_INFO,
        PRINT_HOTELS_WITH_MORE_JACUZZI,
        NOTHING;

        private static final String ONE = "1";
        private static final String TWO = "2";
        private static final String THREE = "3";
        private static final String FOUR = "4";
        private static final String FIVE = "5";

        public static Command build(String type) {
            switch (type) {
                case ONE:
                    return ADD_NEW_HOTEL;
                case TWO:
                    return ADD_STANDARD_ROOM_TO_HOTEL;
                case THREE:
                    return ADD_APARTMENT_TO_HOTEL;
                case FOUR:
                    return PRINT_HOTEL_INFO;
                case FIVE:
                    return PRINT_HOTELS_WITH_MORE_JACUZZI;
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
