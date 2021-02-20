package com.example.thirty.gameMessages;

import android.app.Activity;
import android.widget.TextView;

import com.example.thirty.R;

import java.util.HashMap;

/**
 * Author: Clive Leddy
 * Email: clive@cliveleddy.com
 * Date: 2021-02-05
 */
public class GameMessages {
    public enum GameMessageKeyEnum {
        MAX_ROLLS_REACHED,
        ROLL_DICE,
        TESTING,
        ROLL_SELECT_DICE,
        MAX_ROLLS_REACHED_CALCULATE_RESULT,
        NEW_GAME;
    };

    private  HashMap<GameMessageKeyEnum, String> mMessageList;

    public GameMessages() {
        mMessageList = new HashMap<GameMessageKeyEnum, String>() {{
            put(GameMessageKeyEnum.MAX_ROLLS_REACHED, "You have reached the maximum number of rolls for this round. Calculate your score!");
            put(GameMessageKeyEnum.ROLL_DICE, "Roll the dice!");
            put(GameMessageKeyEnum.TESTING, "Testing");
            put(GameMessageKeyEnum.ROLL_SELECT_DICE, "Roll or select the dice!");
            put(GameMessageKeyEnum.MAX_ROLLS_REACHED_CALCULATE_RESULT, "You have reached the maximum number of rolls for this round. Make your final selection and calculate your score!");
            put(GameMessageKeyEnum.NEW_GAME, "New Game!\n\nFirst roll the dice.\nYou may then select one or more die and then roll the unselected dice.");
        }};
    }

    public void displayMessage(Activity activity, GameMessageKeyEnum keyEnum) {
        TextView tv = (TextView) activity.findViewById(R.id.game_status_text);
        System.out.println("test text view: " + mMessageList.get(keyEnum));
        tv.setText(mMessageList.get(keyEnum));
    }

    public void displayMessage(Activity activity, String msg) {
        TextView tv = (TextView) activity.findViewById(R.id.game_status_text);
        System.out.println("test text view: " + msg);
        tv.setText(msg);
    }

}
