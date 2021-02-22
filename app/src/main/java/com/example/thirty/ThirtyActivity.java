package com.example.thirty;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.thirty.game.ThirtyGame;
import com.example.thirty.gameMessages.GameMessages;
import com.example.thirty.util.SetImageButtonsDice;

import java.util.HashMap;

/**
 * This is the main activity of the game.
 * <p>
 * Author: Clive Leddy
 * Email: clive@cliveleddy.com
 * Date: 2021-01-30
 */
public class ThirtyActivity extends AppCompatActivity {
    //Logcat tags
    private static final String TAG = "ThirtyActivity";

    //Die colours
    private static final DieColourEnum PRIMARY_COLOUR = DieColourEnum.WHITE;
    private static final DieColourEnum SECONDARY_COLOUR = DieColourEnum.GREY;

    //Bundle keys
    private static final String KEY_GAME = "game";
    private static final String KEY_GAME_INFO = "game info";
    //Extra's for communication between activities (process communication via the OS's Activity Manager)
    private static final int THIRTY_SHOW_RESULTS = 100;

    private HashMap<Integer, Integer> mAllDiceImageButtons;
    private ThirtyGame mGame;
    private GameMessages mGameMessages;
    private Bundle mSavedInstanceState;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(TAG, "onCreate(Bundle) called");//Logcat

        //copy the activity lifecycle state
        mSavedInstanceState = savedInstanceState;

        setContentView(R.layout.activity_thirty);
        initialise();//app set up

        if (savedInstanceState != null) {
            Log.i(TAG, "Testing saved bundle");
            mGame = savedInstanceState.getParcelable(KEY_GAME);
        }
        refreshUI();
    }

    /**
     * Initialise app.
     */
    private void initialise() {
        mGame = new ThirtyGame();
        mGameMessages = new GameMessages();
        mAllDiceImageButtons = new HashMap<>();

        SetImageButtonsDice.dieImageButtonsId(mAllDiceImageButtons);

        selectDieButtonAction();
        rollDiceButtonAction();
        calculateDiceResult();
        mGameMessages.displayMessage(this, GameMessages.GameMessageKeyEnum.NEW_GAME);
    }

    /**
     * Update the game user interface.
     */
    private void refreshUI() {
        if (!mGame.isGameOver()) {
            setDiceColour();
            continueGame();
        } else {
            theGameEnded();
        }
    }

    /**
     * Display information that the game has ended.
     */
    private void theGameEnded() {
        Log.i(TAG, "Game is over");
        Log.i(TAG, mGame.toString());

        //create an intent and start the activity
        //tell the Activity manager that ThirtyGame activity wants to call the scoreboard activity
        Intent intent = ScoreboardActivity.newIntent(ThirtyActivity.this,
                mGame.getThirtyScoreBoardCopy());
        //send a copy of the scoreboard to the scoreboard activity using an extra and listen for a reply
        startActivityForResult(intent, THIRTY_SHOW_RESULTS);
    }

    /**
     * Get the specific data from the scoreboard activity.
     *
     * @param requestCode a code used to inform this activity what the called activity did as an int.
     * @param resultCode  the code sent by the child activity on how it finished.
     * @param data        Intent data used to communicate with this activity.
     */
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        //the back button was pressed thus as default start a new game
        if (resultCode == RESULT_CANCELED) {
            initialise();
            refreshUI();
        } else if (requestCode == THIRTY_SHOW_RESULTS) {//a button was pushed
            if ((data != null) && ScoreboardActivity.scoreboardChoice(data)) {//the new game button was pushed
                initialise();
                refreshUI();
            } else {//the end game button was pushed
                endGame();
            }
        }
        Log.i(TAG, "On Activity Result called");
    }

    /**
     * End the game.
     */
    private void endGame() {
        mGame = null;
        mGameMessages = null;
        finish();
        System.exit(0);
    }

    /**
     * Set the colour of the die based on how many time the dice has been thrown.
     */
    private void setDiceColour() {
        if (mGame.getRollCount() != 1) {
            SetImageButtonsDice.setDiceImageButtonsImage(this,
                    PRIMARY_COLOUR,
                    SECONDARY_COLOUR,
                    mAllDiceImageButtons,
                    mGame.getDiceCopy());
        } else {
            SetImageButtonsDice.setDiceImageButtonsImage(this,
                    DieColourEnum.RED,
                    SECONDARY_COLOUR,
                    mAllDiceImageButtons,
                    mGame.getDiceCopy());
            //display relevant text
            if (mGame.getRollCount() > 1) {
                mGameMessages.displayMessage(this, GameMessages.GameMessageKeyEnum.ROLL_DICE);
            } else {
                mGameMessages.displayMessage(this, GameMessages.GameMessageKeyEnum.NEW_GAME);
            }
        }
    }

    /**
     * Add a onClick event listener to each of the die in the games set of dice. This is where we
     * wire up a UI image button to a die. Thus when the image button is clicked the status of the
     * die is switched from is selected to unselected.
     */
    private void selectDieButtonAction() {
        ImageButton ib;
        for (Integer element : mAllDiceImageButtons.keySet()) {
            ib = (ImageButton) findViewById(mAllDiceImageButtons.get(element));
            ib.setOnClickListener(v -> {
                if (mGame.getRollCount() != 1) {
                    mGame.selectDie(element);
                    refreshUI();
                }
            });
        }
    }

    /**
     * Display information about the current round in the game.
     */
    private void continueGame() {

        if (mGame.getRollCount() == 1) {
            // mGameMessages.displayMessage(this, GameMessages.GameMessageKeyEnum.ROLL_DICE);
            if (mSavedInstanceState != null) {
                //update game info
                TextView tv = findViewById(R.id.game_status_text);
                tv.setText(mSavedInstanceState.getCharSequence(KEY_GAME_INFO));
            } else {
                mGameMessages.displayMessage(this, GameMessages.GameMessageKeyEnum.NEW_GAME);
            }
        } else if (mGame.getRollCount() > ThirtyGame.MAX_ROLLS) {
            Toast.makeText(this, "That was the last roll.", Toast.LENGTH_SHORT).show();
            mGameMessages.displayMessage(this, GameMessages.GameMessageKeyEnum.MAX_ROLLS_REACHED_CALCULATE_RESULT);
        } else {
            mGameMessages.displayMessage(this, GameMessages.GameMessageKeyEnum.ROLL_SELECT_DICE);
        }
    }

    /**
     * Add a onClick event listener to the games set of dice. This is where we wire up a UI button
     * to the object that contains all the die. Thus when the roll dice button is clicked the
     * value of each die is updated.
     */
    private void rollDiceButtonAction() {
        Button bt = (Button) findViewById(R.id.roll_dice);
        bt.setOnClickListener(v -> {
            if (mGame.canRollDice()) {
                mGame.rollDice();
                refreshUI();
                //continueGame();
            } else {
                Toast.makeText(this, "Click on \"Calculate Dice\".", Toast.LENGTH_SHORT).show();
                mGameMessages.displayMessage(this, GameMessages.GameMessageKeyEnum.MAX_ROLLS_REACHED);
            }
        });
    }

    private void calculateDiceResult() {
        Button bt = (Button) findViewById(R.id.calculate_dice);
        bt.setOnClickListener(v -> {

            //user did not roll the dice first
            if (mGame.getRollCount() == 1) {
                Toast.makeText(this, "You choose not to roll the dice!!", Toast.LENGTH_LONG).show();
            }

            mGame.updateScoreBoard();
            refreshUI();
            if (!mGame.isGameOver()) {
                mGameMessages.displayMessage(this, "Round " +
                        mGame.getLastRound().get(0) + " result." +
                        "\nVal: " + mGame.getLastRound().get(1) +
                        "\nScore: " + mGame.getLastRound().get(2) +
                        "\n\n-> Roll dice! <-");
            }
        });
    }

    /**
     * Override methods for each of the activities lifecycle states.
     */
    @Override
    public void onSaveInstanceState(Bundle savedInstanceState) {
        super.onSaveInstanceState(savedInstanceState);
        Log.i(TAG, "onSaveInstanceState");

        savedInstanceState.putParcelable(KEY_GAME, mGame);//save and instance of the game object
        TextView tv = findViewById(R.id.game_status_text);
        savedInstanceState.putCharSequence(KEY_GAME_INFO, tv.getText());
    }

    @Override
    public void onStart() {
        super.onStart();
        Log.i(TAG, "onStart() called");
    }

    @Override
    public void onResume() {
        super.onResume();
        refreshUI();
        //continueGame();
        Log.i(TAG, "onResume() called");
    }

    @Override
    public void onPause() {
        super.onPause();
        Log.i(TAG, "onPause() called");
    }

    @Override
    public void onStop() {
        super.onStop();
        Log.i(TAG, "onStop() called");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.i(TAG, "onDestroy() called");
        //clean up
        mGame = null;
        mGameMessages = null;
    }

}