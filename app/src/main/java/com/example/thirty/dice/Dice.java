package com.example.thirty.dice;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * This is the main die controller as it will manage more than one die. The plural of die is
 * dice.
 * <p>
 * Author: Clive Leddy
 * Email: clive@cliveleddy.com
 * Date: 2021-02-03
 */
public class Dice implements Iterator<DieController>, Parcelable {
    private int mRollCount;
    private final int mNumberOfDie;
    private int mIndex;//index to the list of all die in the dice list

    //a collection of die as a list
    private final List<DieController> mDice;

    /**
     * Create a default number of die.
     */
    public Dice() {
        this(Die.die_max);
    }

    /**
     * parameters primary die colour, selected die colour
     *
     * @param numberOfDie the number of die in the dice.
     */
    public Dice(int numberOfDie) {
        this.mNumberOfDie = numberOfDie;
        mRollCount = 1;
        mIndex = 0;
        mDice = new ArrayList<>();
        DieController dc;

        for (int index = 0; index < numberOfDie; index++) {
            dc = new DieController();
            dc.setDieId(index);
            mDice.add(index, dc);
        }
    }

    /**
     * Copy constructor.
     *
     * @param src source to copy from as data type Dice.
     */
    public Dice(Dice src) {
        this.mNumberOfDie = src.mNumberOfDie;
        this.mRollCount = src.mRollCount;
        this.mIndex = src.mIndex;
        this.mDice = new ArrayList<>();

        //copy the array
        /*for (DieController element : src.mDice) {
            mDice.add(element);
        }*/
        mDice.addAll(src.mDice);
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
            return mDice.get(i);
        }
        return null;
    }

    /**
     * Roll all the dice.
     */
    public void rollDice() {
        //if (rollCount <= MAX_ROLLS) {
        for (DieController element : mDice) {
            element.roll();
        }
        increaseRollCount();
        //  return true;
        //}
        //return false;
    }

    /**
     * Increase the roll counter by 1 roll.
     */
    private void increaseRollCount() {
        mRollCount++;
    }

    /**
     * Reset the dice class values to its start up state.
     */
    public void resetRollDice() {
        mRollCount = 1;
        mIndex = 0;
        for (DieController element : mDice) {
            if (element.isSelected()) {
                element.select();
            }
        }
    }

    /**
     * The number of current throws.
     *
     * @return number of throws as int.
     */
    public int getRollCount() {
        return mRollCount;
    }

    /**
     * A die has been selected.
     *
     * @param dieId the id of the die to select as int.
     */
    public void selectDie(int dieId) {
        int i = dieId - 1;
        for (DieController element : mDice) {
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
        return mDice.size();
    }

    /**
     * Get the number of how many die that have been selected.
     *
     * @return the number of selected die as an int.
     */
    public int getNumberOfSelectedDie() {
        int res = 0;

        for (DieController element : mDice) {
            if (element.isSelected()) {
                res++;
            }
        }
        return res;
    }

    @Override
    public String toString() {
        return "Dice{" +
                "mRollCount=" + mRollCount +
                ", mNumberOfDie=" + mNumberOfDie +
                ", mIndex=" + mIndex +
                ", mDice=" + mDice +
                '}';
    }

    /**
     * Is there another selected die.
     *
     * @return true if there is another selected die otherwise false.
     */
    @Override
    public boolean hasNext() {
        for (int step = mIndex; step < mDice.size(); step++) {
            if (mDice.get(step).isSelected()) {
                mIndex = step;
                return true;
            }
        }
        return false;
    }

    /**
     * Get the next selected die.
     *
     * @return the next selected die as a DietController.
     */
    @Override
    public DieController next() {
        return mDice.get(mIndex++);
    }

    /*
     * Parcelable
     **/
    protected Dice(Parcel in) {
        mRollCount = in.readInt();
        mNumberOfDie = in.readInt();
        mIndex = in.readInt();
        mDice = in.createTypedArrayList(DieController.CREATOR);
    }

    public static final Creator<Dice> CREATOR = new Creator<Dice>() {
        @Override
        public Dice createFromParcel(Parcel in) {
            return new Dice(in);
        }

        @Override
        public Dice[] newArray(int size) {
            return new Dice[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(mRollCount);
        dest.writeInt(mNumberOfDie);
        dest.writeInt(mIndex);
        dest.writeTypedList(mDice);
    }
}
