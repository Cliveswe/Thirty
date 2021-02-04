/**
 * Author: Clive Leddy
 * Email: clive@cliveleddy.com
 * Date: 2021-01-30
 */
package com.example.thirty;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.ImageButton;

import com.example.thirty.dice.Dice;
import com.example.thirty.dice.Die;
import com.example.thirty.dice.DieController;

public class ThirtyActivity extends AppCompatActivity {
    //Logcat tags
    private static final String TAG = "ThirtyActivity";
    private static final String KEY_INDEX = "index";
    private static final DieColourEnum PRIMARY_COLOUR = DieColourEnum.WHITE;
    private static final DieColourEnum SECONDARY_COLOUR = DieColourEnum.GREY;

    private DieController test_die;
    private AllDieImages mAllDieImages;

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
        mAllDieImages = new AllDieImages();
        /**
         * START HERE TO SET UP THE DICE WITH PRIMARY AND SECONDARY COLOURS!!!!
         */
        test_die = new DieController();


        Log.i(TAG, "testing dice " + test_die.toString());

        ImageButton testButton = (ImageButton) findViewById(R.id.die_Button_1);
        testButton.setImageResource(R.drawable.red4);

int t = mAllDieImages.getDieImage(DieColourEnum.RED, 2);
System.out.println("get value " + t);
Dice x = new Dice();
        System.out.println("number of dice = " + x.numberOfDice());
        System.out.println("id = " + x.getDie(5).getDieId() + "  dice = " + x.getDie(5));
        //testButton.setImageResource(mAllDieImages.getDieImage(DieColourEnum.GREY, 4));
        //testButton.setImageResource(t);
        testButton.setImageResource(mAllDieImages.getDieImage(DieColourEnum.RED, 3));
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