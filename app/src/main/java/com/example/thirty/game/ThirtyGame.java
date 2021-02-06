package com.example.thirty.game;

import com.example.thirty.dice.Dice;

public class ThirtyGame {
    //the maximum number of throw of the dice per round in the game
    private final static int MAX_ROLLS = 3;
    private final static int MAX_ROUNDS = 10;
    private int mRoundNumber;
    private Dice mDice;
    private ThirtyScoreBoard mThirtyScoreBoard;

    public ThirtyGame() {
        this(new Dice());
    }

    public ThirtyGame(Dice dice) {
        this.mDice = dice;
        mRoundNumber = 1;
    }

    /**
     * Start a new round in the game.
     */
    public void startNewRound(){
        if (!isGameOver()){
            mRoundNumber++;
        }
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
     * @return true when max rounds otherwise false as boolean.
     */
    public boolean isGameOver() {
        if (getRoundNumber() <= MAX_ROUNDS) {
            return true;
        }
        return false;
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

    public int getRollCount() {
        return mDice.getRollCount();
    }

    /**
     * Reset the dice class values to its start up state.
     */
    public void resetRollDice() {
        mDice.resetRollDice();
    }

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
}
