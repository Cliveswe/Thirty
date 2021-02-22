package com.example.thirty.game;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.Vector;

/**
 * This is class contain the fields that hold the data of a round. I.E. it has the round number,
 * a picker, val in Swedish, and the score for that round.
 * <p>
 * Author: Clive Leddy
 * Email: clive@cliveleddy.com
 * Date: 2021-02-06
 */
public class ThirtyScorePerRound implements Parcelable {
    private final int mRound;
    private final int mPick;//Val in Swedish
    private final int mScore;

    public ThirtyScorePerRound(int round, int pick, int score) {
        mRound = round;
        mPick = pick;
        mScore = score;
    }

    /**
     * Get the round number.
     *
     * @return round number as int.
     */
    public int getRound() {
        return mRound;
    }

    /**
     * Get the pick value.
     *
     * @return pick value as int.
     */
    public int getPick() {
        return mPick;
    }

    /**
     * Get the score for a round.
     *
     * @return score as int.
     */
    public int getScore() {
        return mScore;
    }

    /**
     * Get the score data for a round in the game.
     *
     * @return the score data in the order of round, pick (Val), score as a vector.
     */
    public Vector<Integer> getRoundData() {
        return new Vector<Integer>() {{
            add(getRound());
            add(getPick());
            add(getScore());
        }};
    }

    @Override
    public String toString() {
        return "ThirtyScorePerRound{" +
                "mRound=" + mRound +
                ", mPick=" + mPick +
                ", mScore=" + mScore +
                '}';
    }

    /*
     * Parcelable
     * */
    protected ThirtyScorePerRound(Parcel in) {
        mRound = in.readInt();
        mPick = in.readInt();
        mScore = in.readInt();
    }

    public static final Creator<ThirtyScorePerRound> CREATOR = new Creator<ThirtyScorePerRound>() {
        @Override
        public ThirtyScorePerRound createFromParcel(Parcel in) {
            return new ThirtyScorePerRound(in);
        }

        @Override
        public ThirtyScorePerRound[] newArray(int size) {
            return new ThirtyScorePerRound[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(mRound);
        dest.writeInt(mPick);
        dest.writeInt(mScore);
    }
}
