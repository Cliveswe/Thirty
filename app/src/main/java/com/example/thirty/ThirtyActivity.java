package com.example.thirty;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;

public class ThirtyActivity extends AppCompatActivity {
    //Logcat tags
    private static final String TAG = "ThirtyActivity";
    private static final String KEY_INDEX = "index";

    private int test;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(TAG, "onCreate(Bundle) called");//Logcat

        if(savedInstanceState != null){
            Log.i(TAG, "Testing saved bundle");
            test = savedInstanceState.getInt(KEY_INDEX, 0);
        }

        setContentView(R.layout.activity_thirty);

        //TODO add more data
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