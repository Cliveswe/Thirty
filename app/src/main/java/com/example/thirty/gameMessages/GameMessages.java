/**
 * Author: Clive Leddy
 * Email: clive@cliveleddy.com
 * Date: 2021-02-05
 */
package com.example.thirty.gameMessages;

import android.app.Activity;
import android.widget.TextView;
import com.example.thirty.R;
import java.util.HashMap;

/**
 *
 */
public class GameMessages {

    private HashMap<GameMessageKeyEnum, String> mMessageList;

    public GameMessages() {
        mMessageList = new HashMap<GameMessageKeyEnum, String>() {{
            put(GameMessageKeyEnum.MAX_ROLLS_REACHED, "You have reached the maximum number of rolls for this round. Calculate your score!");
            put(GameMessageKeyEnum.ROLL_DICE, "Roll the dice!");
        }};
    }

    public void displayMessage(Activity activity, GameMessageKeyEnum keyEnum) {
        TextView tv = (TextView) activity.findViewById(R.id.game_status_text);
        System.out.println("test text view: " + mMessageList.get(keyEnum));
        tv.setText(mMessageList.get(keyEnum));
    }

}
