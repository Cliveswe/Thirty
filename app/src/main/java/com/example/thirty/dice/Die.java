/**
 * Author: Clive Leddy
 * Email: clive@cliveleddy.com
 * Date: 2021-02-03
 */
package com.example.thirty.dice;

/**
 * This is the basic die design. A die has a value between 1 to 6. The die can be rolled to change
 * its value.
 */
public class Die {
    private int dieValue;
    public final static int die_max = 6;
    public final static int die_min = 1;

    /**
     * Class constructor.
     */
    public Die() {
        rollDie();
    }

    /**
     * Return the current value of the die.
     *
     * @return the value of the die as an int.
     */
    public int getDieValue() {
        return dieValue;
    }

    /**
     * Set the value of the die.
     *
     * @param dieValue the value of the die as an int.
     */
    private void setDieValue(int dieValue) {
        this.dieValue = dieValue;
    }

    /**
     * Generate a random int value between a min value and max value determined by the class.
     */
    protected void rollDie() {
        //Generate a random int value between die_min and die_max
        setDieValue((int) (Math.random() * (die_max - die_min + 1) + die_min));
    }

    public String toString() {
        return "Die value = " + getDieValue();
    }
}
