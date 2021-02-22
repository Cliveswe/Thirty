package com.example.thirty;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.example.thirty.game.ThirtyScorePerRound;
import com.example.thirty.game.ThirtyScoreboard;

import java.util.ArrayList;

/**
 * The scoreboard activity. It uses a custom adapter to display a list of score results.
 * <p>
 * Author: Clive Leddy
 * Email: clive@cliveleddy.com
 * Date: 2021-02-18
 */
public class ScoreboardActivity extends AppCompatActivity {
    private static final String TAG = "ScoreboardActivity";
    //Extra's for communication between activities (process communication via the OS's Activity Manager)
    private static final String EXTRA_SCOREBOARD_RESULT = "com.example.thirty.scoreboard";
    private static final String EXTRA_SCOREBOARD_REPLY = "com.example.thirty.reply";
    private ThirtyScoreboard mThirtyScoreboard;

    /**
     * Wrap the activity intent in a method for data hiding. Create an Intent properly configured
     * with the extras ScoreboardActivity will need.
     *
     * @param packageContext tells the ActivityManager which application package the activity class
     *                       can be found in.
     * @param scoreboard     is the Parcelable object that contains the data for this activity.
     * @return a new intent used to communicate with the Scoreboard Activity.
     */
    public static Intent newIntent(Context packageContext, ThirtyScoreboard scoreboard) {
        Intent intent = new Intent(packageContext, ScoreboardActivity.class);
        intent.putExtra(EXTRA_SCOREBOARD_RESULT, scoreboard);
        return intent;
    }

    /**
     * Helper method to decode the choice made in the score board activity.
     *
     * @param choice what was chosen in the activity as an Intent.
     * @return true if the new game button was pushed false if the end game button was pushed.
     */
    public static boolean scoreboardChoice(Intent choice) {
        return choice.getBooleanExtra(EXTRA_SCOREBOARD_REPLY, false);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scoreboard);

        //initialize the activities buttons
        replyNewGame();
        replyEndGame();

        //get the parcelable object that contains the score results
        if (getIntent().getExtras() != null) {
            mThirtyScoreboard = getIntent().getParcelableExtra(EXTRA_SCOREBOARD_RESULT);
            Log.i(TAG, mThirtyScoreboard.toString());
            displayScoreResults();
        }

    }

    /**
     * Display the results of the game.
     */
    private void displayScoreResults() {
        ListView mListView = findViewById(R.id.scoreboard_listview);

        /*
         * An adapter class that formats the display for each card in the list view.
         */
        class ScoreboardListAdapter extends ArrayAdapter<ThirtyScorePerRound> {
            private final Context mContext;
            private final int mResource;

            public ScoreboardListAdapter(@NonNull Context context, int resource, @NonNull ArrayList<ThirtyScorePerRound> objects) {
                super(context, resource, objects);
                mContext = context;
                mResource = resource;
            }

            @SuppressLint({"ViewHolder", "SetTextI18n"})
            @NonNull
            @Override
            public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
                //get score data
                ThirtyScorePerRound scorePerRound = getItem(position);

                //inflate the layout from the context
                LayoutInflater inflater = LayoutInflater.from(mContext);

                //convertView = inflater.inflate(mResource, parent, false);
                convertView = inflater.inflate(mResource, parent, false);

                //get widgets to update
                TextView tvRound = convertView.findViewById(R.id.round_textView);
                TextView tvPick = convertView.findViewById(R.id.pick_textView);
                TextView tvScore = convertView.findViewById(R.id.score_textView);

                //update the widgets
                tvRound.setText(getString(R.string.scoreboard_result_round) + " " + scorePerRound.getRound());
                tvPick.setText(getString(R.string.scoreboard_result_pick) + " " + scorePerRound.getPick());
                tvScore.setText(getString(R.string.scoreboard_result_score) + " " + scorePerRound.getScore());

                return convertView;
            }
        }

        mListView.setAdapter(new ScoreboardListAdapter(this,
                R.layout.scoreboard_item, new ArrayList<>(mThirtyScoreboard.getScoreboard())));
    }

    /**
     * Click the button to start a new game.
     */
    private void replyNewGame() {
        Button bt = findViewById(R.id.new_game_button);
        bt.setOnClickListener(v -> scoreboardReply(true));
    }

    /**
     * Click the button to end the game.
     */
    private void replyEndGame() {
        Button bt = findViewById(R.id.end_game_button);
        bt.setOnClickListener(v -> scoreboardReply(false));
    }

    /**
     * Send a reply to the caller through an Intent.
     *
     * @param newOrEndGame true to start a new game or false to end the game.
     */
    private void scoreboardReply(boolean newOrEndGame) {
        Intent data = new Intent();
        data.putExtra(EXTRA_SCOREBOARD_REPLY, newOrEndGame);

        setResult(RESULT_OK, data);
        Log.i(TAG, "scoreboardReply!");
        super.onBackPressed();
        finish();
    }

}