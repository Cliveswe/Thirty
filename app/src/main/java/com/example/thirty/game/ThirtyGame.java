package com.example.thirty.game;

import android.os.Parcel;
import android.os.Parcelable;
import android.util.Log;

import com.example.thirty.dice.Dice;
import com.example.thirty.scoreCalculator.ScoreCalculator;

public class ThirtyGame implements Parcelable {
    private static final String TAG = "DiceClass";

    //the maximum number of throw of the dice per round in the game
    private final static int VAL = 3;
    private final static int LOW_BOUNDARY = 1;
    private final static int TOP_BOUNDARY = 3;
    private final static int MAX_ROLLS = 3;
    private final static int MAX_ROUNDS = 3;
    private int mRoundNumber;
    private final Dice mDice;
    private final ThirtyScoreBoard mThirtyScoreBoard;

    public ThirtyGame() {
        this(new Dice());
    }

    public ThirtyGame(Dice dice) {
        this.mDice = dice;
        mRoundNumber = 1;
        mThirtyScoreBoard = new ThirtyScoreBoard();
    }

    /**
     * Start a new round in the game.
     *
     * @return true if a new round can be started otherwise false.
     */
    public boolean startNewRound() {
        mRoundNumber++;
        if (!isGameOver()) {
            resetRollDice();
        }
        return isGameOver();
    }


    /**
     * Get the current round number.
     *
     * @return game round as int.
     */
    public int getRoundNumber() {
        return mRoundNumber;
    }

    /**
     * Check to see if the game has reached it maximum number of rounds.
     *
     * @return true when max rounds otherwise false as boolean.
     */
    public boolean isGameOver() {
        if (getRoundNumber() <= MAX_ROUNDS) {
            return false;
        }
        return true;
    }

    /**
     * Throw the dice. There is a limit on how many time the dice can be thrown per round.
     *
     * @return true if the dice where thrown otherwise false.
     */
    public boolean rollDice() {
        if (mDice.getRollCount() <= MAX_ROLLS) {
            mDice.rollDice();
            return true;
        }
        return false;
    }

    /**
     * The dice cannot be thrown if the maximum number of throws has been met.
     *
     * @return true if the maximum number of throws has been reached otherwise false.
     */
    public boolean canRollDice() {
        if (mDice.getRollCount() <= MAX_ROLLS) {
            return true;
        }
        return false;
    }

    /**
     * How many times have the dice been thrown (rolled).
     *
     * @return the roll count of the dice as an int.
     */
    public int getRollCount() {
        return mDice.getRollCount();
    }

    /**
     * Reset the dice class values to its start up state.
     */
    private void resetRollDice() {
        mDice.resetRollDice();
    }

    /**
     * Get a cope of the dice used in the game.
     *
     * @return a new object Dice.
     */
    public Dice getDiceCopy() {
        return new Dice(this.mDice);
    }

    /**
     * A die has been selected.
     *
     * @param dieId the id of the die to select as int.
     */
    public void selectDie(int dieId) {
        mDice.selectDie(dieId);
    }

    /**
     * Update the score board with the current dice.
     */
    public void updateScoreBoard(){
        determineScoreForRound();
        startNewRound();
    }
    /**
     * To update the score board with the results of the current round this method must be triggered.
     */
    public void determineScoreForRound() {

        if (ScoreCalculator.CalculateSelectedDice(new Dice(mDice)) >
                ScoreCalculator.CalculateLOW(new Dice(mDice), LOW_BOUNDARY, TOP_BOUNDARY)) {
            createRoundScore(ScoreCalculator.PickedVal(new Dice(mDice)));
        } else {
            createRoundScore(VAL);
        }

        Log.i(TAG, "Results thus far:\nNumber of rounds: " +
                mThirtyScoreBoard.getNumOfScores());
        Log.i(TAG, "\nScores:\n" + this.toString());
    }

    /**
     * Update the score board with the results of a round.
     *
     * @param picked a picker (VAL in Swedish) that is used to determine the what to calculate.
     */
    private void createRoundScore(int picked) {
        if (picked > VAL) {
            mThirtyScoreBoard.addScoreResults(
                    new ThirtyScorePerRound(
                            mRoundNumber,
                            picked,
                            ScoreCalculator.CalculateSelectedDice(new Dice(mDice)))
            );
        } else {
            mThirtyScoreBoard.addScoreResults(
                    new ThirtyScorePerRound(
                            mRoundNumber,
                            VAL,
                            ScoreCalculator.CalculateLOW(
                                    new Dice(mDice),
                                    LOW_BOUNDARY,
                                    TOP_BOUNDARY))
            );
        }
    }

    @Override
    public String toString() {
        return "ThirtyGame{" +
                "mRoundNumber=" + mRoundNumber +
                ", mDice=" + mDice +
                ", mThirtyScoreBoard=" + mThirtyScoreBoard +
                '}';
    }

    /*
     * Parcelable
     */
    public static final Creator<ThirtyGame> CREATOR = new Creator<ThirtyGame>() {
        @Override
        public ThirtyGame createFromParcel(Parcel in) {
            return new ThirtyGame(in);
        }

        @Override
        public ThirtyGame[] newArray(int size) {
            return new ThirtyGame[size];
        }
    };

    protected ThirtyGame(Parcel in) {
        mRoundNumber = in.readInt();
        mDice = in.readParcelable(Dice.class.getClassLoader());
        mThirtyScoreBoard = in.readParcelable(ThirtyScoreBoard.class.getClassLoader());
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(mRoundNumber);
        dest.writeParcelable(mDice, flags);
        dest.writeParcelable(mThirtyScoreBoard, flags);
    }

    @Override
    public int describeContents() {
        return 0;
    }
}
