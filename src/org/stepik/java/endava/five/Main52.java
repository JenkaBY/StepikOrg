package org.stepik.java.endava.five;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.DecimalFormat;
import java.util.*;
import java.util.function.Consumer;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.DoubleStream;
import java.util.stream.Stream;

public class Main52 {
    /*
            Competitions
        You need to create a solution for storing data for competitions.
        There are three types of competitions and the stored data is shown in the following UML diagram:
        You will read commands from the input and they are the following:
            1 location day winning_team_name time_first_half time_second_half   Adds a new soccer competition 
            2 location day winner_name winner_surname set_times[5]         Adds a new tennis competition
            3 location day winner_name winner_surname winner_time last_player_time Adds a new formula competition
            4 day               Print the number of each competitions separated by blank space that held that day
            5                 Print the total additional time of each soccer competition
            6                 Print the length of the longest played set in any tennis competition
            7                 Print the shortest time of any formula 1 competition
            8                 Print the total time of all tennis and formula 1 competitions rounded to 2 decimals
            9                 Exit
        The input ends when the user inputs  9
        Note: For printing to 2 decimal places you can use the following command: System.out.printf("%.2f",variable);
        where variable is the name of the variable for which you want to print the result.
     *  */

    public static void main(String[] args) {
        System.out.println(SoccerCompetition.class.getSimpleName());
        Competition tennis = new SoccerCompetition("dsds", 1);
        System.out.println(tennis.getClass().getCanonicalName());
//        Runner runner = new Runner(new CompetitionConsumer());
//        runner.run();
    }

    public static class Runner {
        private CommandWithParamsConsumer<CommandWithParams> commandsConsumer;

        public Runner(CommandWithParamsConsumer<CommandWithParams> commandsConsumer) {
            this.commandsConsumer = commandsConsumer;
        }

        public void run() {
            InputParser parser = new InputParser(readStdIn());
            Consumer<CommandWithParams> consumer = getConsumer();
            parser.parse().getCommands().forEach(consumer);
        }

        public Consumer<CommandWithParams> getConsumer() {
            return commandWithParams -> commandsConsumer.action(commandWithParams);
        }
    }

    @FunctionalInterface
    interface CommandWithParamsConsumer<T extends CommandWithParams> {
        void action(T commandWithParams);
    }

    public static class CompetitionConsumer implements CommandWithParamsConsumer<CommandWithParams> {
        private CompetitionStore competitions;

        public CompetitionConsumer() {
            competitions = new CompetitionStore();
        }

        @Override
        public void action(CommandWithParams commandWithParams) {
            String result = "";
            final LinkedList<String> params = commandWithParams.getParams();
            switch (commandWithParams.getCommand()) {
                case ADD_NEW_SOCCER_COMPETITION:
                    addSoccerCompetition(params);
                    break;
                case ADD_NEW_TENNIS_COMPETITION:
                    addTennisCompetition(params);
                    break;
                case ADD_NEW_FORMULA_COMPETITION:
                    addFormulaCompetition(params);
                    break;
                case PRINT_EACH_COMPETITION_AT_DAY:
                    result = competitions.getNumberOfCompetitionAtDay(numberFromStr(commandWithParams.getParams().poll()));
                    break;
                case PRINT_TOTAL_TIME_OF_TENNIS_AND_FORMULA_COMPETITIONS:
                    result = roundDouble(competitions.getTotalTimeOfTennisAndFormulaCompetitions());
                    break;
                case PRINT_TOTAL_ADDITIONAL_TIME_OF_SOCCER:
                    result = roundDouble(competitions.getTotalAdditionalTimeOfSoccer());
                    break;
                case PRINT_LENGTH_OF_LONGEST_PLAYED_TENNIS_SET:
                    result = roundDouble(competitions.getLongestPlayedTennisSet());
                    break;
                case PRINT_SHORTEST_TIME_OF_FORMULA_COMPETITION:
                    result = roundDouble(competitions.getShortestTimeOfFormula());
                    break;
                case EXIT:
                    System.exit(0);
                    break;
                default:
                    break;
            }
            print(result);
        }

        private void addFormulaCompetition(LinkedList<String> params) {
            // location day winner_name winner_surname winner_time last_player_time
            final String location = params.poll();
            final int day = numberFromStr(params.poll());
            final String winnerFirstName = params.poll();
            final String winnerLastName = params.poll();
            final float winnerTime = floatFromStr(params.poll());
            final float lastPlayerTime = floatFromStr(params.poll());
            Competition competition = new FormulaCompetition(location, day)
                    .withWinnerFirstName(winnerFirstName)
                    .withWinnerLastName(winnerLastName)
                    .withWinnerTime(winnerTime)
                    .withLastPlayerTime(lastPlayerTime);
            competitions.addCompetition(competition);
        }

        private void addTennisCompetition(LinkedList<String> params) {
//            location day winner_name winner_surname set_times[5]
            final String location = params.poll();
            final int day = numberFromStr(params.poll());
            final String winnerFirstName = params.poll();
            final String winnerLastName = params.poll();
            final float[] setTimes = new float[params.size()];
            for (int i = 0; i < setTimes.length; i++) {
                setTimes[i] = floatFromStr(params.poll());
            }
            Competition competition = new TennisCompetition(location, day)
                    .withWinnerFirstName(winnerFirstName)
                    .withWinnerLastName(winnerLastName)
                    .withSetTimes(setTimes);
            competitions.addCompetition(competition);
        }

        private void addSoccerCompetition(LinkedList<String> params) {
            // location day winning_team_name time_first_half time_second_half
            final String location = params.poll();
            final int day = numberFromStr(params.poll());
            final String winnerTeam = params.poll();
            final float firstHalf = floatFromStr(params.poll());
            final float secondHalf = floatFromStr(params.poll());
            Competition competition = new SoccerCompetition(location, day)
                    .withWinnerTeamName(winnerTeam)
                    .withAdditionalFirstHalfTime(firstHalf)
                    .withAdditionalSecondHalfTime(secondHalf);
            competitions.addCompetition(competition);
        }

        private String roundDouble(double sum) {
            DecimalFormat df = new DecimalFormat("###.#");
            return df.format(sum);
        }

        private void print(String result) {
            System.out.println(result);
        }
    }


    public static class CompetitionStore {
        List<Competition> competitions;

        public CompetitionStore() {
            this.competitions = new ArrayList<>();
        }

        public void addCompetition(Competition competition) {
            this.competitions.add(competition);
        }

        public String getNumberOfCompetitionAtDay(int day) {
//            return getTypeCompetitionsAtDay(day).values()
//                    .stream()
//                    .sorted(Comparator.reverseOrder())
//                    .map(String::valueOf)
//                    .collect(Collectors.joining(" "))
            Map<String, Long> stats = getTypeCompetitionsAtDay(day);
            return stats.get(SoccerCompetition.class.getSimpleName()) + " "
                    + stats.get(TennisCompetition.class.getSimpleName()) + " "
                    + stats.get(FormulaCompetition.class.getSimpleName());
        }

        public Map<String, Long> getTypeCompetitionsAtDay(int day) {
            final Map<String, Long> stats = new HashMap<>();
            stats.put(SoccerCompetition.class.getSimpleName(), 0L);
            stats.put(TennisCompetition.class.getSimpleName(), 0L);
            stats.put(FormulaCompetition.class.getSimpleName(), 0L);
            getFilteredCompetition(competition -> competition.getDate() == day)
                    .map(Competition::getClass)
                    .map(Class::getSimpleName)
                    .forEach(clazz -> stats.compute(clazz, (key, value) -> value += 1));
            return stats;
        }

        public double getTotalTimeOfTennisAndFormulaCompetitions() {
            return getFilteredCompetition(competition -> competition instanceof TotalTime)
                    .map(competition -> (TotalTime) competition)
                    .map(TotalTime::getTotalTime)
                    .mapToDouble(Double::valueOf)
                    .sum();
        }

        private Stream<Competition> competitionsStream() {
            return this.competitions.stream();
        }

        private Stream<Competition> getFilteredCompetition(Predicate<Competition> filterFirst) {
            return competitionsStream()
                    .filter(filterFirst);
        }

        public double getTotalAdditionalTimeOfSoccer() {
            return getFilteredCompetition(competition -> competition instanceof SoccerCompetition)
                    .map(competition -> (SoccerCompetition) competition)
                    .map(competition -> competition.getAdditionalFirstHalfTime() + competition.additionalSecondHalfTime)
                    .mapToDouble(Double::valueOf)
                    .sum();

        }

        public double getLongestPlayedTennisSet() {
            return getFilteredCompetition(competition -> competition instanceof TennisCompetition)
                    .map(tennis -> (TennisCompetition) tennis)
                    .flatMapToDouble(tennis -> DoubleStream.of(tennis.getSetTimesDouble()))
                    .max()
                    .orElse(0d);
        }

        public double getShortestTimeOfFormula() {
            return getFilteredCompetition(competition -> competition instanceof FormulaCompetition)
                    .map(competition -> (FormulaCompetition) competition)
                    .map(FormulaCompetition::getWinnerTime)
                    .map(Double::valueOf)
                    .min(Double::compare)
                    .orElse(0d);
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

        public Command getCommand() {
            return command;
        }

        public LinkedList<String> getParams() {
            return params;
        }
    }

    enum Command {
        ADD_NEW_SOCCER_COMPETITION,
        ADD_NEW_TENNIS_COMPETITION,
        ADD_NEW_FORMULA_COMPETITION,
        PRINT_EACH_COMPETITION_AT_DAY,
        PRINT_TOTAL_ADDITIONAL_TIME_OF_SOCCER,
        PRINT_LENGTH_OF_LONGEST_PLAYED_TENNIS_SET,
        PRINT_SHORTEST_TIME_OF_FORMULA_COMPETITION,
        PRINT_TOTAL_TIME_OF_TENNIS_AND_FORMULA_COMPETITIONS,
        EXIT;

        private static final String ONE = "1";
        private static final String TWO = "2";
        private static final String THREE = "3";
        private static final String FOUR = "4";
        private static final String FIVE = "5";
        private static final String SIX = "6";
        private static final String SEVEN = "7";
        private static final String EIGHT = "8";
        private static final String NINE = "9";

        public static Command build(String type) {
            switch (type) {
                case ONE:
                    return ADD_NEW_SOCCER_COMPETITION;
                case TWO:
                    return ADD_NEW_TENNIS_COMPETITION;
                case THREE:
                    return ADD_NEW_FORMULA_COMPETITION;
                case FOUR:
                    return PRINT_EACH_COMPETITION_AT_DAY;
                case FIVE:
                    return PRINT_TOTAL_ADDITIONAL_TIME_OF_SOCCER;
                case SIX:
                    return PRINT_LENGTH_OF_LONGEST_PLAYED_TENNIS_SET;
                case SEVEN:
                    return PRINT_SHORTEST_TIME_OF_FORMULA_COMPETITION;
                case EIGHT:
                    return PRINT_TOTAL_TIME_OF_TENNIS_AND_FORMULA_COMPETITIONS;
                case NINE:
                default:
                    return EXIT;
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

    public static abstract class Competition {
        protected String location;
        protected int date;

        public Competition(String location, int date) {
            this.location = location;
            this.date = date;
        }

        public String getLocation() {
            return location;
        }

        public int getDate() {
            return date;
        }
    }

    public static class TennisCompetition extends Competition implements TotalTime {
        private String winnerFirstName;
        private String winnerLastName;
        private float[] setTimes;

        public TennisCompetition(String location, int date) {
            super(location, date);
        }

        public TennisCompetition withWinnerFirstName(String firstName) {
            this.winnerFirstName = firstName;
            return this;
        }

        public TennisCompetition withWinnerLastName(String lastName) {
            this.winnerLastName = lastName;
            return this;
        }

        public TennisCompetition withSetTimes(float[] setTimes) {
            this.setTimes = setTimes;
            return this;
        }

        public String getWinnerFirstName() {
            return winnerFirstName;
        }

        public String getWinnerLastName() {
            return winnerLastName;
        }

        public float[] getSetTimes() {
            return setTimes;
        }

        public double[] getSetTimesDouble() {
            double[] setTimes = new double[this.setTimes.length];
            for (int i = 0; i < setTimes.length; i++) {
                setTimes[i] = (double) this.setTimes[i];
            }
            return setTimes;
        }

        @Override
        public float getTotalTime() {
            float result = 0f;
            for (int i = 0; i < setTimes.length; i++) {
                result += setTimes[i];
            }
            return result;
        }
    }

    public static class SoccerCompetition extends Competition {
        private String winnerTeamName;
        private float additionalFirstHalfTime;
        private float additionalSecondHalfTime;

        public SoccerCompetition(String location, int date) {
            super(location, date);
        }

        public SoccerCompetition withWinnerTeamName(String teamName) {
            this.winnerTeamName = teamName;
            return this;
        }

        public SoccerCompetition withAdditionalFirstHalfTime(float additionalFirstHalfTime) {
            this.additionalFirstHalfTime = additionalFirstHalfTime;
            return this;
        }

        public SoccerCompetition withAdditionalSecondHalfTime(float additionalSecondHalfTime) {
            this.additionalSecondHalfTime = additionalSecondHalfTime;
            return this;
        }

        public String getWinnerTeamName() {
            return winnerTeamName;
        }

        public float getAdditionalFirstHalfTime() {
            return additionalFirstHalfTime;
        }

        public float getAdditionalSecondHalfTime() {
            return additionalSecondHalfTime;
        }

        public float getTotalAdditionalTime() {
            return additionalFirstHalfTime + additionalSecondHalfTime;
        }
    }

    public static class FormulaCompetition extends Competition implements TotalTime {
        private String winnerFirstName;
        private String winnerLastName;
        private float winnerTime;
        private float lastPlayerTime;

        public FormulaCompetition(String location, int date) {
            super(location, date);
        }

        public FormulaCompetition withWinnerFirstName(String firstName) {
            this.winnerFirstName = firstName;
            return this;
        }

        public FormulaCompetition withWinnerLastName(String lastName) {
            this.winnerLastName = lastName;
            return this;
        }

        public FormulaCompetition withWinnerTime(float winnerTime) {
            this.winnerTime = winnerTime;
            return this;
        }

        public FormulaCompetition withLastPlayerTime(float lastPlayerTime) {
            this.lastPlayerTime = lastPlayerTime;
            return this;
        }

        public float getWinnerTime() {
            return winnerTime;
        }

        public float getLastPlayerTime() {
            return lastPlayerTime;
        }

        public String getWinnerFirstName() {

            return winnerFirstName;
        }

        public String getWinnerLastName() {
            return winnerLastName;
        }

        @Override
        public float getTotalTime() {
            return lastPlayerTime;
        }
    }

    interface TotalTime {
        float getTotalTime();
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
