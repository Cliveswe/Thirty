package com.example.thirty.scoreCalculator;

import com.example.thirty.dice.Dice;
import com.example.thirty.dice.DieController;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Vector;

public class ScoreCalculator {
    //a restriction in the game anything less than 3 will be calculated as LOW
    //VAL is picker in English
    private static final int VAL = 4;

    /**
     * Calculate a LOW score from the selected.
     *
     * @param dice      dice rolled as Dice.
     * @param lowValue  the lower boundary including the boundary itself for calculating the LOW
     *                  score as int.
     * @param highValue the upper boundary including the boundary itself for calculating the LOW
     *                  score as int.
     * @return 0 if the conditions are not meet otherwise the parameter value of maxPoints.
     */
    public static int CalculateLOW(final Dice dice, final int lowValue, final int highValue) {
        int res = 0;
        //search for selected die
        while (dice.hasNext()) {
            DieController dc = dice.next();//found a selected die
            //test if the die is within bounds
            if ((dc.getDieValue() >= lowValue) && (dc.getDieValue() <= highValue)) {
                res += dc.getDieValue();//add the die value to the running total
            }
        }

        return res;
    }

    /**
     * Calculate the best score from the die selected in the object dice.
     *
     * @param dice a collection of die as Dice.
     * @return A total of the selected die as an int.
     */
    public static int CalculateSelectedDice(final Dice dice) {
        int res = 0;
        List<ArrayList<Integer>> sortingList = new ArrayList<>();

        InspectDie(dice, sortingList);

        RemoveLowScores(sortingList);

        Vector<Integer> vector = new Vector<>(sortingList.size());
        FindDoublets(sortingList, vector);

        // int x = LargestSum(sortingList);
        //System.out.println("Largest sum x = " + x);
        //System.out.println("Sorting list: " + sortingList + "\n***");

        //
        if (sortingList.size() == vector.size()) {
            RemoveLeastVector(vector, sortingList);
        }

        //
        if (vector.size() > 0) {
            res = CalculateVector(vector, sortingList);
        } else if (sortingList.size() == 1) {
            res = LargestSum(sortingList);
        }

        return res;
    }

    /**
     * This is a helper function that calculates the largest sum of grouped die that is used in the
     * games picker, "VAL in swedish"!
     *
     * @param dice a collection of die as Dice.
     * @return the largest sum of grouped die as an int.
     */
    public static int PickedVal(final Dice dice) {
        List<ArrayList<Integer>> sortingList = new ArrayList<>();
        InspectDie(dice, sortingList);
        RemoveLowScores(sortingList);
        return LargestSum(sortingList);
    }

    /**
     * Filter the list of vectors. Using the list of vectors examine group of die using each vector
     * value as an index in src. If the sum of the selected group of die is less than the largest
     * sum remove the vector value from the list of vectors.
     * <p>
     * group of die total sum is less that the largest sum then remove
     *
     * @param vector a list of indices where doublets groups of die are located in src.
     * @param src    a List of die values as a List of arrays.
     */
    private static void RemoveLeastVector(Vector<Integer> vector, List<ArrayList<Integer>> src) {
        //int x = LargestSum(src);

        for (int i = 0; i < vector.size(); i++) {
            if (SumOfAllElements(src.get(i)) < LargestSum(src)) {
                vector.remove(i);
                i = 0;
            }
        }
    }

    /**
     * For each group of die values in the list. Calculate the largest total sum of all the grouped
     * die values.
     *
     * @param src a List of die values as a List of arrays.
     * @return the largest sum of grouped die as an int.
     */
    private static int LargestSum(List<ArrayList<Integer>> src) {
        int res = 0;

        for (int i = 0; i < src.size(); i++) {
            if (SumOfAllElements(src.get(i)) > res) {
                res = SumOfAllElements(src.get(i));
            }
        }
        return res;
    }

    /**
     * Calculate the total sum of all the grouped die values in the array using the vector of indices.
     * In effect the size of the vector acts as a multiplier. Each value in the vector is index used
     * to locate the group of die that are to be summed together.
     * What this means that there may be one or more identical group of die that summed together have
     * a value that is grater than just one group of die.
     *
     * @param vector      a list of indices where doublets groups of die are located in sortingList.
     * @param sortingList a List of die values as a List of arrays.
     * @return the total sum of all grouped die values as int.
     */
    private static int CalculateVector(final Vector<Integer> vector, final List<ArrayList<Integer>> sortingList) {
        int res = 0;
        for (int i = 0; i < vector.size(); i++) {
            res += SumOfAllElements(sortingList.get(vector.get(i)));
        }
        return res;
    }

    /**
     * Examine each die in the object dice and group them together. Each group has an individual die.
     * No group has a die with the same value.
     *
     * @param dice        a collection of die as Dice.
     * @param sortingList a List of die values as a List of arrays.
     */
    private static void InspectDie(final Dice dice, List<ArrayList<Integer>> sortingList) {
        while (dice.hasNext()) {
            DieController dc = dice.next();//found a selected die
            if (sortingList.size() == 0) {
                InsertNewArray(sortingList, dc);
            } else {
                GroupDice(sortingList, dc);
            }
        }
    }

    /**
     * Get the value of a die and add it to a list of die values.
     *
     * @param sortingList a List of die values as a List of arrays.
     * @param dc          a die as DieController.
     */
    private static void InsertNewArray(List<ArrayList<Integer>> sortingList, DieController dc) {
        ArrayList<Integer> tmp = new ArrayList<>();
        tmp.add(dc.getDieValue());
        sortingList.add(tmp);
    }

    /**
     * Build the different groups of die values. Each group will have a list of unique die values.
     *
     * @param dieGroup a List of die that are grouped together in an List of arrays.
     * @param dieValue the value of a die to be added to the group of die in dieGroup.
     */
    public static void GroupDice(List<ArrayList<Integer>> dieGroup, DieController dieValue) {

        if (!AddDieToDieGroup(dieGroup, dieValue)) {
            InsertNewArray(dieGroup, dieValue);
        }
    }

    /**
     * Add a die to a group of die. Loop through the dieGroup and add a die to the first group of
     * die if the die is not in that group. Thus each group will have a list of unique die values.
     * Each group of die is sorted in acceding order.
     *
     * @param dieGroup a List of die that are grouped together in an List of arrays.
     * @param dieValue the value of a die to be added to the group of die in dieGroup.
     * @return true if a die is added to a group otherwise false.
     */
    private static boolean AddDieToDieGroup(List<ArrayList<Integer>> dieGroup, final DieController dieValue) {
        for (int index = 0; index < dieGroup.size(); index++) {
            if (!dieGroup.get(index).contains(dieValue.getDieValue())) {
                dieGroup.get(index).add(dieValue.getDieValue());
                Collections.sort(dieGroup.get(index));
                return true;
            }
        }
        return false;
    }

    /**
     * Calculate the total sum of all the grouped die values in the array.
     *
     * @param src is the grouped die as an array of int.
     * @return the result of the calculation as an int.
     */
    private static int SumOfAllElements(final ArrayList<Integer> src) {
        int res = 0;

        for (Integer element : src) {
            res += element;
        }

        return res;
    }


    /**
     * Find the groups of die values that are doublets. When found add their index to the vector.
     *
     * @param src    a List of die values as a List of arrays.
     * @param vector a list of indices where doublets groups of die are located in src.
     */
    private static void FindDoublets(final List<ArrayList<Integer>> src, Vector<Integer> vector) {

        for (int i = 0; i < src.size(); i++) {
            for (int j = i + 1; j < src.size(); j++) {
                if ((i != j) &&
                        (src.get(i).equals(src.get(j)))) {
                    //make sure no doublets are in the vector
                    if (!vector.contains(i)) {
                        vector.add(i);
                    }
                    if (!vector.contains(j)) {
                        vector.add(j);
                    }
                }
            }
        }
    }

    /**
     * Part of the game rules are that VAL is a rule of the game. The sum of all the die in a group
     * or die must be greater than VAL. If not the group of die is deleted from the List of grouped
     * die.
     *
     * @param sortingList a List of die values as a List of arrays.
     */
    private static void RemoveLowScores(List<ArrayList<Integer>> sortingList) {

        for (int index = 0; index < sortingList.size(); index++) {
            if (SumOfAllElements(sortingList.get(index)) < VAL) {
                sortingList.remove(index);
                index = -1;
            }
        }
    }
}

