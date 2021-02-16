
package com.example.thirty.dice;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * This is the basic die design. A die has a value between 1 to 6. The die can be rolled to change
 * its value.
 * Author: Clive Leddy
 * Email: clive@cliveleddy.com
 * Date: 2021-02-03
 */
public class Die implements Parcelable {
    private int mDieValue;
    public final static int die_max = 6;
    public final static int die_min = 1;


    /**
     * Class constructor.
     */
    public Die() {
        rollDie();
    }

    /**
     * Copy constructor
     *
     * @param src source to copy from as Die.
     */
    public Die(Die src) {
        this.mDieValue = src.mDieValue;
    }

    /**
     * Return the current value of the die.
     *
     * @return the value of the die as an int.
     */
    public int getDieValue() {
        return mDieValue;
    }

    /**
     * Set the value of the die.
     *
     * @param dieValue the value of the die as an int.
     */
    private void setDieValue(int dieValue) {
        this.mDieValue = dieValue;
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

    /*
     *Parcelable
     **/
    protected Die(Parcel in) {
        mDieValue = in.readInt();
    }

    public static final Creator<Die> CREATOR = new Creator<Die>() {
        @Override
        public Die createFromParcel(Parcel in) {
            return new Die(in);
        }

        @Override
        public Die[] newArray(int size) {
            return new Die[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(mDieValue);
    }
}
