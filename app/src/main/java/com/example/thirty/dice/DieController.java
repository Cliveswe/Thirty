package com.example.thirty.dice;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Author: Clive Leddy
 * Email: clive@cliveleddy.com
 * Date: 2021-02-03
 * <p>
 * This class extends the class Die. Here additional die properties and logic can be added.
 */
public class DieController extends Die implements Parcelable {
    private boolean mIsSelected;
    private int mDieId;

    /**
     * Create the die controller object and set the die is selected state.
     */
    public DieController() {
        super();
        this.mIsSelected = false;
    }

    /**
     * Copy constructor.
     * @param dieController source to copy from as type DieController.
     */
    public DieController(DieController dieController) {
        super(dieController);
        this.mIsSelected = dieController.mIsSelected;
        this.mDieId = dieController.mDieId;
    }

    /**
     * Is the die selected or not.
     *
     * @return true if selected otherwise false as boolean value.
     */
    public boolean isSelected() {
        return this.mIsSelected;
    }

    /**
     * Toggle the status of the die from false to true or from true to false.
     */
    public void select() {
        mIsSelected = !mIsSelected;
    }

    /**
     * Set the id of the die controller.
     *
     * @param id as an int.
     */
    public void setDieId(int id) {
        this.mDieId = id;
    }

    /**
     * Roll a die if the die has the stats of not been selected.
     */
    public void roll() {
        if (!mIsSelected) {
            rollDie();
        }
    }

    /**
     * Get the id of the die controller.
     *
     * @return the id of the controller die as an int.
     */
    public int getDieId() {
        return mDieId;
    }

    /**
     * @return the class variables as a String.
     */
    @Override
    public String toString() {
        return "DieController{" +
                "isSelected=" + mIsSelected +
                " die value=" + getDieValue() +
                '}';
    }

    /*
     *Parcelable
     */
    protected DieController(Parcel in) {
        super(in);
        mIsSelected = in.readByte() != 0;
        mDieId = in.readInt();
    }

    public static final Creator<DieController> CREATOR = new Creator<DieController>() {
        @Override
        public DieController createFromParcel(Parcel in) {
            return new DieController(in);
        }

        @Override
        public DieController[] newArray(int size) {
            return new DieController[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        super.writeToParcel(dest, flags);
        dest.writeByte((byte) (mIsSelected ? 1 : 0));
        dest.writeInt(mDieId);
    }
}
