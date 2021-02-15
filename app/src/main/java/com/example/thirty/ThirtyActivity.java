/**
 * Author: Clive Leddy
 * Email: clive@cliveleddy.com
 * Date: 2021-01-30
 */
package com.example.thirty;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;

import com.example.thirty.game.ThirtyGame;
import com.example.thirty.gameMessages.GameMessageKeyEnum;
import com.example.thirty.gameMessages.GameMessages;
import com.example.thirty.dice.Dice;
import com.example.thirty.dice.DieController;
import com.example.thirty.util.SetImageButtonsDice;

import java.util.HashMap;

public class ThirtyActivity extends AppCompatActivity {
    //Logcat tags
    private static final String TAG = "ThirtyActivity";
    private static final String KEY_INDEX = "index";
    private static final DieColourEnum PRIMARY_COLOUR = DieColourEnum.WHITE;
    private static final DieColourEnum SECONDARY_COLOUR = DieColourEnum.GREY;

    //private DieController test_die;
    private AllDieImages mAllDieImages;
    private HashMap<Integer, Integer> mAllDiceImageButtons;
    private ThirtyGame mGame;
    private GameMessages mGameMessages;

    private int test;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(TAG, "onCreate(Bundle) called");//Logcat

        if (savedInstanceState != null) {
            Log.i(TAG, "Testing saved bundle");
            test = savedInstanceState.getInt(KEY_INDEX, 0);
        }

        setContentView(R.layout.activity_thirty);

        //TODO add more data
        initialise();
        /**
         * START HERE TO SET UP THE DICE WITH PRIMARY AND SECONDARY COLOURS!!!!
         */
        //test_die = new DieController();

        //Log.i(TAG, "testing dice " + test_die.toString());

        //ImageButton testButton = (ImageButton) findViewById(R.id.die_Button_1);
        //testButton.setImageResource(R.drawable.red4);

        //int t = mAllDieImages.getDieImage(DieColourEnum.RED, 2);
        //System.out.println("get value " + t);
        //Dice x = new Dice();
        //System.out.println("number of dice = " + x.numberOfDice());
        //x.getDie(5).roll();
        //System.out.println("id = " + x.getDie(5).getDieId() + "  dice = " + x.getDie(5));
        //testButton.setImageResource(mAllDieImages.getDieImage(DieColourEnum.GREY, 4));
        //testButton.setImageResource(t);
        //testButton.setImageResource(mAllDieImages.getDieImage(DieColourEnum.GREY, 3));

        /*for (Integer element : mAllDiceImageButtons.keySet()) {
            ImageButton ib = (ImageButton) findViewById(mAllDiceImageButtons.get(element.intValue()));
            ib.setImageResource(mAllDieImages.getDieImage(DieColourEnum.RED, x.getDie(element.intValue()).getDieValue()));
        }*/


        /*mGameDice.selectDie(4);
        SetImageButtonsDice.setDiceImageButtonsImage(this,
                PRIMARY_COLOUR, SECONDARY_COLOUR,
                mAllDiceImageButtons, mGameDice);
*/

        /*SetImageButtonsDice.setDiceImageButtonsImage(this,
                SECONDARY_COLOUR, PRIMARY_COLOUR,
                mAllDiceImageButtons, mGameDice);*/
    }

    /**
     * Initialise app.
     */
    private void initialise() {
        mGame = new ThirtyGame();
        mAllDieImages = new AllDieImages();
        mGameMessages = new GameMessages();

        mAllDiceImageButtons = new HashMap<Integer, Integer>();
        SetImageButtonsDice.dieImageButtonsId(mAllDiceImageButtons);

        selectDieButtonAction();
        rollDiceButtonAction();
        calculateDiceResult();
        refreshUI();
    }

    private void refreshUI() {
        SetImageButtonsDice.setDiceImageButtonsImage(this,
                PRIMARY_COLOUR,
                SECONDARY_COLOUR,
                mAllDiceImageButtons,
                mGame.getDiceCopy());
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
                if (!mGame.canRollDice()) {
                    Toast.makeText(this, "That was the last roll.", Toast.LENGTH_SHORT).show();
                    mGameMessages.displayMessage(this, GameMessageKeyEnum.MAX_ROLLS_REACHED);
                } else {
                    mGameMessages.displayMessage(this, GameMessageKeyEnum.ROLL_DICE);
                }
            }
        });
    }

    private void calculateDiceResult() {
        Button bt = (Button) findViewById(R.id.calculate_dice);
        bt.setOnClickListener(v -> {
            if (!mGame.isGameOver()) {
                //mGame.resetRollDice();
                mGame.tesLowScore();
                mGame.startNewRound();
                refreshUI();
                mGameMessages.displayMessage(this, GameMessageKeyEnum.ROLL_DICE);
                //TODO Calculate round score
            } else {
                mGameMessages.displayMessage(this, GameMessageKeyEnum.TESTING);
                //TODO Game over show results
            }

        });
    }

    /**
     * Override methods for each of the activities lifecycle states.
     */
    @Override
    public void onStart() {
        super.onStart();
        //TODO add additional state control for onStart
    }

    @Override
    public void onResume() {
        super.onResume();
        //TODO add additional state control for onResume
    }

    @Override
    public void onPause() {
        super.onPause();
        //TODO add additional state control onPause
    }


    @Override
    public void onSaveInstanceState(Bundle savedInstanceState) {
        super.onSaveInstanceState(savedInstanceState);
        Log.i(TAG, "onSaveInstanceState");

        //TODO 0 is a dummy value and needs to be swapped out
        //TODO KEY_INDEX also need to be replaced
        savedInstanceState.putInt(KEY_INDEX, test);
    }

    @Override
    public void onStop() {
        super.onStop();
        //TODO add additional state control onStop
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        //TODO add additional state control onDestroy

    }

}