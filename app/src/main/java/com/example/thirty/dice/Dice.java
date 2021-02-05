/**
 * Author: Clive Leddy
 * Email: clive@cliveleddy.com
 * Date: 2021-02-03
 */
package com.example.thirty.dice;

import java.util.ArrayList;
import java.util.List;

/**
 * This is the main die controller as it will manage more than one die. The plural of die is
 * dice.
 */
public class Dice {
    //the maximum number of throw of the dice per round in the game
    private final static int MAX_ROLLS = 3;
    private int rollCount;


    //a collection of die as a list
    private List<DieController> dice;

    /**
     * Create a default number of die.
     */
    public Dice() {
        this(Die.die_max);
    }

    /**
     * parameters primary die colour, selected die colour
     *
     * @param numberOfDie
     */
    public Dice(int numberOfDie) {
        rollCount = 1;
        dice = new ArrayList<DieController>();
        DieController dc;

        for (int index = 0; index < numberOfDie; index++) {
            dc = new DieController();
            dc.setDieId(index);
            dice.add(index, dc);
        }
    }

    /**
     * Get a die controller from the list of dice.
     *
     * @param index the first die controller is 1 and the last die controller is at max number
     *              of die controllers.
     * @return a die controller on success otherwise null.
     */
    public DieController getDie(int index) {
        int i = index - 1;
        if ((i >= 0) && (i < numberOfDice())) {
            return (DieController) dice.get(i);
        }
        return null;
    }

    /**
     * Roll all the dice.
     */
    public boolean rollDice() {
        if (rollCount <= MAX_ROLLS) {
            for (DieController element : dice) {
                element.roll();
            }
            increaseRollCount();
            return true;
        }
        return false;
    }

    private void increaseRollCount() {
        rollCount++;
    }

    public void resetRollDice() {
        rollCount = 1;
        for (DieController element: dice) {
            if(element.isSelected()){
                element.select();
            }
        }
    }

    public boolean canRollDice(){
        if(rollCount <= MAX_ROLLS){
            return true;
        }
        return false;
    }

    public int getRollCount() {
        return rollCount;
    }

    public void selectDie(int dieId) {
        int i = dieId - 1;
        for (DieController element : dice) {
            if (i == element.getDieId()) {
                element.select();
            }
        }
    }

    /**
     * The number of die controllers in the group of dice.
     *
     * @return the number of dice as an int.
     */
    public int numberOfDice() {
        return dice.size();
    }

    public String toString() {
        //TODO
        return "";
    }
}
