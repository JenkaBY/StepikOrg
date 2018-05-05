package org.stepik.java.endava.two;

import java.util.*;

public class Main23 {
    /*
        Read a string from the standard input.
        Find the character that is repeated the most consecutively in the string.
        Output: Print the character and the maximal number of consecutive occurences.
        In case there are two, print the first character that occurred.
    * */
    public static void main(String[] args) {
        String input = new Scanner(System.in).nextLine();
        String repeatingSequence = repeatingSequence(input);
        int max = maxNumber(repeatingSequence);
        System.out.println(getCharOfMaxSequence(repeatingSequence, max) + " " + max);
    }

    public static char getCharOfMaxSequence(String seq, int maxSeqNumber) {
        return seq.charAt(seq.indexOf(String.valueOf(maxSeqNumber)) - 1);
    }

    public static String repeatingSequence(String sequence) {
        StringBuilder repeatings = new StringBuilder();
        Pair repeating = new Pair(String.valueOf(sequence.charAt(0)));
        List<String> seq = Arrays.asList(sequence.split(""));
        for (int i = 1; i < seq.size(); i++) {
            if (repeating.chr.equals(seq.get(i))) {
                repeating.increment();
                continue;
            }
            repeatings.append(repeating.getRepeatings());
            repeating = new Pair(seq.get(i));
        }
        repeatings.append(repeating.getRepeatings());
        return repeatings.toString();
    }

    public static Integer maxNumber(String seq) {
        return Arrays.stream(seq.split("\\D"))
                .filter(Objects::nonNull)
                .filter((e) -> !e.trim().isEmpty())
                .map(Integer::valueOf)
                .max(Comparator.naturalOrder())
                .orElse(1);
    }

    public static class Pair {
        String chr;
        int repeatings = 1;
        boolean wasOutput = false;

        public Pair(String chr) {
            this.chr = chr;
        }

        public void increment() {
            repeatings++;
        }

        public String getChr() {
            return chr;
        }

        public String getRepeatings() {
            if (!wasOutput) {
                wasOutput = true;
                return chr + repeatings;
            }
            return "";
        }
    }
}
