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

    private List dice;

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
        dice = new ArrayList<DieController>();
        DieController dc;
        for (int index = 0; index < numberOfDie; index++) {
            dc = new DieController();
            dc.setDieId(index);
            dice.add(index, dc);

        }
    }

    public DieController getDie(int index) {
        int i = index - 1;
        if ((i >= 0) && (i < numberOfDice())) {
            return (DieController) dice.get(i);
        }
        return null;
    }

    public int numberOfDice() {
        return dice.size();
    }

    public String toString() {
        //TODO
        return "";
    }
}
