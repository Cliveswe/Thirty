package com.example.thirty.game;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

public class ThirtyScoreBoard implements Parcelable {

    private final List<ThirtyScorePerRound> mScoreBoard;

    public ThirtyScoreBoard() {
        mScoreBoard = new ArrayList<>();
    }

    public void addScoreResults(ThirtyScorePerRound thirtyScorePerRound) {
        mScoreBoard.add(thirtyScorePerRound);
    }

    public int getNumOfScores() {
        return mScoreBoard.size();
    }

    public Vector<Integer> getScoreResultForRound(int key) {
        return (mScoreBoard.get(key)).getRoundData();
    }

    @Override
    public String toString() {
        return "ThirtyScoreBoard{\n\t" +
                "mScoreBoard=\n\t\t" + mScoreBoard +
                '}';
    }

    /*
     * Parcelable
     */
    public static final Creator<ThirtyScoreBoard> CREATOR = new Creator<ThirtyScoreBoard>() {
        @Override
        public ThirtyScoreBoard createFromParcel(Parcel in) {
            return new ThirtyScoreBoard(in);
        }

        @Override
        public ThirtyScoreBoard[] newArray(int size) {
            return new ThirtyScoreBoard[size];
        }
    };

    protected ThirtyScoreBoard(Parcel in) {
        mScoreBoard = in.createTypedArrayList(ThirtyScorePerRound.CREATOR);
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeTypedList(mScoreBoard);
    }

    @Override
    public int describeContents() {
        return 0;
    }
}
