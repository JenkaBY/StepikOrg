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

    static class Pie extends Bakery {
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
        public <T extends Calorie> List grade(List<T> items) {

//        public <T extends Calorie> List<T> grade(List<? extends T> items) {
            // Add implementation
//
            return items.stream()
                    .sorted()
                    .collect(Collectors.toList());
        }

        /*
            List<Cake> caloryCakes = grader.grade(cakes);
            List<Pie> caloryPies = grader.grade(pies);
            List<Bakery> caloryBakery = grader.grade(bakeries);
            List<Bakery> caloryBakeryCakes = grader.grade(cakes);
            List<Drink> caloryDrinks = grader.grade(drinks);
        */
    }

    public static void main(String[] args) {
        List<Bakery> cakes = new ArrayList<>();
        List<Bakery> pies = new ArrayList<>();
        List<Bakery> bakeries = new ArrayList<>();
        List<Bakery> drinks = new ArrayList<>();
        cakes.add(new Cake());
        CaloryGrader grader = new CaloryGrader();
//        List<Bakery> caloryBakeryCakes = cg.grade(cakes);
        List<Cake> caloryCakes = grader.grade(cakes);
        List<Pie> caloryPies = grader.grade(pies);
        List<Bakery> caloryBakery = grader.grade(bakeries);
        List<Bakery> caloryBakeryCakes = grader.grade(cakes);
        List<Drink> caloryDrinks = grader.grade(drinks);
    }
}
