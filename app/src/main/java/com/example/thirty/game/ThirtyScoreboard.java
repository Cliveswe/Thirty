package com.example.thirty.game;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

/**
 * This is the scoreboard controller. All the results per round are saved to an array of the class
 * ThirtyScorePerRound.
 * <p>
 * Author: Clive Leddy
 * Email: clive@cliveleddy.com
 * Date: 2021-02-06
 */
public class ThirtyScoreboard implements Parcelable {

    private final List<ThirtyScorePerRound> mScoreboard;

    public ThirtyScoreboard() {
        mScoreboard = new ArrayList<>();
    }

    /**
     * Copy constructor.
     *
     * @param src the source to be copied as ThirtyScoreboard.
     */
    public ThirtyScoreboard(ThirtyScoreboard src) {
        mScoreboard = new ArrayList<>();
        mScoreboard.addAll(src.mScoreboard);
    }

    /**
     * Get the scoreboard.
     *
     * @return the scoreboard as a Collection List<ThirtyScorePerRound>.
     */
    public List<ThirtyScorePerRound> getScoreboard() {
        return mScoreboard;
    }

    /**
     * Add a score to the scoreboard.
     *
     * @param thirtyScorePerRound a new score as ThirtyScorePerRound.
     */
    public void addScoreResults(ThirtyScorePerRound thirtyScorePerRound) {
        mScoreboard.add(thirtyScorePerRound);
    }

    /**
     * How many scores are registered in the scorebord.
     *
     * @return the number of registered scores as an int.
     */
    public int getNumOfScores() {
        return mScoreboard.size();
    }

    /**
     * Get a score from the scoreboard.
     *
     * @param key get a specific score.
     * @return the score data as a vector<round as int, pick as int, score as int>.
     */
    public Vector<Integer> getScoreResultForRound(int key) {
        return (mScoreboard.get(key - 1)).getRoundData();
    }

    @Override
    public String toString() {
        return "ThirtyScoreboard{\n\t" +
                "mScoreBoard=\n\t\t" + mScoreboard +
                '}';
    }

    /*
     * Parcelable
     */
    public static final Creator<ThirtyScoreboard> CREATOR = new Creator<ThirtyScoreboard>() {
        @Override
        public ThirtyScoreboard createFromParcel(Parcel in) {
            return new ThirtyScoreboard(in);
        }

        @Override
        public ThirtyScoreboard[] newArray(int size) {
            return new ThirtyScoreboard[size];
        }
    };

    protected ThirtyScoreboard(Parcel in) {
        mScoreboard = in.createTypedArrayList(ThirtyScorePerRound.CREATOR);
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeTypedList(mScoreboard);
    }

    @Override
    public int describeContents() {
        return 0;
    }
}
