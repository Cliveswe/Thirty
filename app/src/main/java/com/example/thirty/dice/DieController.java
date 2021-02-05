/**
 * Author: Clive Leddy
 * Email: clive@cliveleddy.com
 * Date: 2021-02-03
 */
package com.example.thirty.dice;

import com.example.thirty.AllDieImages;
import com.example.thirty.DieColourEnum;

/**
 * This class extends the class Die. Here additional die properties and logic can be added.
 */
public class DieController extends Die {
    private boolean isSelected;
    private int dieId;

    /**
     * Create the die controller object and set the die is selected state.
     */
    public DieController() {
        super();
        this.isSelected = false;
    }

    /**
     * Is the die selected or not.
     *
     * @return true if selected otherwise false as boolean value.
     */
    public boolean isSelected() {
        return this.isSelected;
    }

    /**
     * Toggle the status of the die from false to true or from true to false.
     */
    public void select() {
        isSelected = !isSelected;
    }

    /**
     * Set the id of the die controller.
     *
     * @param id as an int.
     */
    public void setDieId(int id) {
        this.dieId = id;
    }

    /**
     * Roll a die if the die has the stats of not been selected.
     */
    public void roll() {
        if (!isSelected) {
            rollDie();
        }
    }

    /**
     * Get the id of the die controller.
     *
     * @return the id of the controller die as an int.
     */
    public int getDieId() {
        return dieId;
    }

    @Override
    public String toString() {
        return "DieController{" +
                "isSelected=" + isSelected +
                " die value=" + getDieValue() +
                '}';
    }
}
