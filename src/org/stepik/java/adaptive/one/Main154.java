package org.stepik.java.adaptive.one;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Main154 {
    /*
        In the Roman numbering system, the following symbols are used to represent numbers (on the right are the numbers,
        which correspond to these symbols in the decimal system):
            I = 1
            V = 5
            X = 10
            L = 50
            C = 100
            D = 500
            M = 1000
        We use an option in which the numbers 4, 9, 40, 90, 400 and 900 are represented by a subtraction of a smaller number
        from a larger one: IV, IX, XL, XC, CD and CM, respectively.
        Write a program that converts the number from Roman system into the decimal one.
        Input format:
            The string, containing the number, encoded into the Roman numbering system. It is guaranteed that this number is less than 4000.
        Output format:
            The string, containing the number in the decimal numbering system, which corresponds to the input.
        Sample Input 1:
            MCMLXXXIV
        Sample Output 1:
            1984
        Sample Input 2:
            IX
        Sample Output 2:
            9
        Sample Input 3:
            III
        Sample Output 3:
            3
    * */
    public static void main(String[] args) {
        String input = new Scanner(System.in).nextLine();
        RomanNumber number = new RomanNumberParser(input.trim()).parse();
        System.out.println(number.toArabicNumber());
    }

    public static class RomanNumberParser {
        String input;

        public RomanNumberParser(String input) {
            this.input = input;
        }

        public RomanNumber parse() {
            final RomanNumber roman = getNewRomanNumber();
            final RomanDigitFactory factory = getNewRomanDigitFactory();
            splitToRomanDigits().forEach(digit -> roman.addDigit(factory.produce(digit)));
            return roman;
        }

        private RomanDigitFactory getNewRomanDigitFactory() {
            return RomanDigitFactory.getInstance();
        }

        private RomanNumber getNewRomanNumber() {
            return new RomanNumber();
        }

        private List<String> splitToRomanDigits() {
            List<String> splitted = new LinkedList<>();
            boolean nextIsCurrent = false;
            String next = null;
            Iterator<String> iterator = Stream.of(input.toUpperCase().split("")).iterator();
            while (iterator.hasNext()) {
                String current = nextIsCurrent ? next : iterator.next();
                nextIsCurrent = false;
                if (Roman.startsWithComplicatedDigit(current) && iterator.hasNext()) {
                    next = iterator.next();
                    if (Roman.isComplicatedDigit(current + next)) {
                        splitted.add(current + next);
                        continue;
                    }
                    splitted.add(current);
                    nextIsCurrent = true;
                    continue;
                }
                splitted.add(current);
            }
            if (nextIsCurrent) {
                splitted.add(next);
            }
            return splitted;
        }

    }

    public static class I extends AbstractRomanDigit {
        public I() {
            this.value = 1;
        }
    }

    public static class IV extends AbstractRomanDigit {
        public IV() {
            this.value = 4;
        }
    }

    public static class V extends AbstractRomanDigit {
        public V() {
            this.value = 5;
        }
    }

    public static class IX extends AbstractRomanDigit {
        public IX() {
            this.value = 9;
        }
    }

    public static class X extends AbstractRomanDigit {
        public X() {
            this.value = 10;
        }
    }

    public static class XL extends AbstractRomanDigit {
        public XL() {
            this.value = 40;
        }
    }

    public static class L extends AbstractRomanDigit {
        public L() {
            this.value = 50;
        }
    }

    public static class C extends AbstractRomanDigit {
        public C() {
            this.value = 100;
        }
    }

    public static class XC extends AbstractRomanDigit {
        public XC() {
            this.value = 90;
        }
    }

    public static class CD extends AbstractRomanDigit {
        public CD() {
            this.value = 400;
        }
    }

    public static class D extends AbstractRomanDigit {
        public D() {
            this.value = 500;
        }
    }

    public static class CM extends AbstractRomanDigit {
        public CM() {
            this.value = 900;
        }
    }

    public static class M extends AbstractRomanDigit {
        public M() {
            this.value = 1000;
        }
    }

    public static abstract class AbstractRomanDigit implements RomanDigit {
        protected int value;

        @Override
        public int getValue() {
            return value;
        }

        @Override
        public String toString() {
            return getClass().getSimpleName();
        }
    }

    public static final class RomanDigitFactory {
        private static RomanDigitFactory factory;

        private RomanDigitFactory() {
        }

        public static RomanDigitFactory getInstance() {
            if (factory == null) {
                factory = new RomanDigitFactory();
            }
            return factory;
        }

        public RomanDigit produce(final String number) {
            switch (number) {
                case Roman.I: {
                    return new I();
                }
                case Roman.IV: {
                    return new IV();
                }
                case Roman.V: {
                    return new V();
                }
                case Roman.IX: {
                    return new IX();
                }
                case Roman.X: {
                    return new X();
                }
                case Roman.XL: {
                    return new XL();
                }
                case Roman.L: {
                    return new L();
                }
                case Roman.XC: {
                    return new XC();
                }
                case Roman.C: {
                    return new C();
                }
                case Roman.CD: {
                    return new CD();
                }
                case Roman.D: {
                    return new D();
                }
                case Roman.CM: {
                    return new CM();
                }
                case Roman.M: {
                    return new M();
                }
                default: {
                    throw new RuntimeException("Can't create RomanDigit from " + number);
                }
            }
        }
    }

    public static final class Roman {
        public static final String I = "I";
        public static final String IV = "IV";
        public static final String V = "V";
        public static final String IX = "IX";
        public static final String X = "X";
        public static final String XL = "XL";
        public static final String L = "L";
        public static final String XC = "XC";
        public static final String C = "C";
        public static final String CD = "CD";
        public static final String D = "D";
        public static final String CM = "CM";
        public static final String M = "M";
        public static final Set<String> COMPLICATED_DIGITS = Stream.of(IV, IX, XL, XC, CD, CM)
                .collect(Collectors.toSet());
        public static final Set<String> COMPLICATED_DIGIT_START_WITH = COMPLICATED_DIGITS.stream()
                .map(roman -> roman.split("")[0])
                .collect(Collectors.toSet());

        public static boolean startsWithComplicatedDigit(String digit) {
            return COMPLICATED_DIGIT_START_WITH.contains(digit);
        }

        public static boolean isComplicatedDigit(String digit) {
            return COMPLICATED_DIGITS.contains(digit);
        }
    }

    public interface RomanDigit {
        int getValue();
    }

    public static class RomanNumber {
        private List<RomanDigit> digits;
        private long arabic;

        public RomanNumber() {
            this.digits = new LinkedList<>();
        }

        public boolean addDigit(RomanDigit digit) {
            return digits.add(digit);
        }

        public long toArabicNumber() {
            if (arabic == 0) {
                arabic = digits.stream()
                        .mapToInt(RomanDigit::getValue)
                        .sum();
            }
            return arabic;
        }

        @Override
        public String toString() {
            return digits.stream()
                    .map(RomanDigit::toString)
                    .collect(Collectors.joining());
        }
    }
}
