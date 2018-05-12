package org.stepik.java.adaptive.one;

import java.util.*;

public class Main156 {
    /*
        Upon learning that DNA is not a random string, freshmen of the Bioinformatics Institute from the informatics group
         suggested using a compression algorithm that compresses repeated characters in a string.
        Encoding is performed as follows:
         s = 'aaaabbсaa' is converted into 'a4b2с1a2', that is, the groups of the same characters of the input string
                                are replaced by the symbol and the number of its repetitions in this string.
        Write a program, which reads the string, encodes it by this algorithm and outputs the encoded sequence.
            Encoding must be case sensitive.
    * */
    public static void main(String[] args) {
        String input = new Scanner(System.in).nextLine();
        String repeatingSequence = repeatingSequence(input);
        System.out.println(repeatingSequence);
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

        public String getRepeatings() {
            if (!wasOutput) {
                wasOutput = true;
                return chr + repeatings;
            }
            return "";
        }
    }
}
