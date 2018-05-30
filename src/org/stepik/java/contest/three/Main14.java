package org.stepik.java.contest.three;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Main14 {
    /*
    *
         You are asked to design CaloryGrader that is supposed to take list of anything that has calories and return list of
         the same items in order of ascending calories.
         The Calorie interface describing target items and some of the implementing classes are listed:
            // Interface that defines that item has calories and may be compared by it
            interface Calorie extends Comparable<Calorie> {
              public int compareTo(Calorie object);
              public int getCalories();
            }

            // Examples of classes CaloryGrader is supposed to work with
            class Drink implements Calorie {
                // Some implemenation...
            }

            class Bakery implements Calorie {
                // Some implementation...
            }

            class Cake extends Bakery {
                // Some implementation...
            }

        Design GaloryGrader.grade method type signatures and provide suitable implementation to ensure:
            Lists of anything implementing Calorie could be graded
            Lists of anything else couldn't be graded (should fail at compile time)
            Method signature should be as general as possible
            Grading order is correct
            The original list isn't modified by grading process
    * */

    /**
     * Grades different items by calories.
     */
    interface Calorie extends Comparable<Calorie> {
        public int compareTo(Calorie object);

        public int getCalories();
    }

    static class Drink implements Calorie {
        // Some implemenation...
        @Override
        public int compareTo(Calorie object) {
            return 0;
        }

        @Override
        public int getCalories() {
            return 0;
        }
    }

    static class Bakery implements Calorie {
        // Some implementation...
        @Override
        public int compareTo(Calorie object) {
            return 0;
        }

        @Override
        public int getCalories() {
            return 0;
        }
    }

    static class Cake extends Bakery {
        // Some implementation...
    }

    static class CaloryGrader {

        /**
         * Returns sorted in ascending order copy of items list.
         * <p>
         * Sort order is defined by item calories.
         *
         *
         * @param items collection of items to sort
         * @return sorted copy
         */
        public <T> List<? extends Calorie> grade(List<? extends Calorie> items) {
            // Add implementation
//
            return items.stream()
                    .sorted()
                    .collect(Collectors.toList());
        }

        /*
            Compilation error
            Main.java:78: error: incompatible types: List<CAP#1> cannot be converted to List<Cake>
                    List<Cake> caloryCakes = grader.grade(cakes);
                                                         ^
              where CAP#1 is a fresh type-variable:
                CAP#1 extends Calorie from capture of ? extends Calorie
            Main.java:87: error: incompatible types: List<CAP#1> cannot be converted to List<Pie>
                    List<Pie> caloryPies = grader.grade(pies);
                                                       ^
              where CAP#1 is a fresh type-variable:
                CAP#1 extends Calorie from capture of ? extends Calorie
            Main.java:93: error: incompatible types: List<CAP#1> cannot be converted to List<Bakery>
                    List<Bakery> caloryBakery = grader.grade(bakeries);
                                                            ^
              where CAP#1 is a fresh type-variable:
                CAP#1 extends Calorie from capture of ? extends Calorie
            Main.java:102: error: incompatible types: List<CAP#1> cannot be converted to List<Bakery>
                    List<Bakery> caloryBakeryCakes = grader.grade(cakes);
                                                                 ^
              where CAP#1 is a fresh type-variable:
                CAP#1 extends Calorie from capture of ? extends Calorie
            Main.java:108: error: incompatible types: List<CAP#1> cannot be converted to List<Drink>
                    List<Drink> caloryDrinks = grader.grade(drinks);
                                                           ^
              where CAP#1 is a fresh type-variable:
                CAP#1 extends Calorie from capture of ? extends Calorie
            5 errors
        */
    }

    public static void main(String[] args) {
        List<Bakery> cakes = new ArrayList<>();
        cakes.add(new Cake());
        CaloryGrader cg = new CaloryGrader();
//        List<Bakery> caloryBakeryCakes = cg.grade(cakes);
    }
}
