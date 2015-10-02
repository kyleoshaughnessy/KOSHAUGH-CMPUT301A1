/**
 * Copyright 2015 Kyle O'Shaughnessy
 * <p/>
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * <p/>
 * http://www.apache.org/licenses/LICENSE-2.0
 * <p/>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package ca.ualberta.koshaugh.cmput301a1;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class ReactionTimerActivity extends AppCompatActivity {

    private final Handler handler = new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reaction_timer);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_reaction_timer, menu);
        return true;
    }

    public void onStartCountdownButtonClick(View view) {
        final Drawable reactionButtonBackground =
                findViewById(R.id.reactionButton).getBackground();
        final Button reactionTimeButton = (Button) findViewById(R.id.reactionButton);

        reactionButtonBackground.setColorFilter(Color.RED, PorterDuff.Mode.SRC_ATOP);
        reactionTimeButton.setText(R.string.reaction_ready);
        // negate effects of previous state if user resets countdown
        handler.removeCallbacksAndMessages(null);
        reactionTimeButton.setOnClickListener(null);

        final Runnable getReactionTime = new Runnable() {
            @Override
            public void run() {
                final ReactionStatistic stat = new ReactionStatistic();
                reactionButtonBackground.setColorFilter(Color.GREEN, PorterDuff.Mode.SRC_ATOP);
                reactionTimeButton.setText(R.string.reaction_go);
                reactionTimeButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        stat.stop();
                        Toast.makeText(ReactionTimerActivity.this, "You reacted in " + stat.getReactionTime().toString() + " ms", Toast.LENGTH_LONG).show();
                        reactionTimeButton.setOnClickListener(null);
                        reactionTimeButton.setText("");
                        reactionButtonBackground.setColorFilter(null);
                        ReactionStatisticManager.getManager().addStatistic(stat);
                    }
                });
                stat.start();
            }
        };

        Runnable countDownToReactionTimer = new Runnable() {
            @Override
            public void run() {
                reactionButtonBackground.setColorFilter(Color.YELLOW, PorterDuff.Mode.SRC_ATOP);
                reactionTimeButton.setText(R.string.reaction_set);

                long delay = (long) (Math.random() * 2000);
                delay = Math.max(10, delay);
                handler.postDelayed(getReactionTime, delay);

                reactionTimeButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        handler.removeCallbacks(getReactionTime);
                        reactionTimeButton.setText("");
                        reactionButtonBackground.setColorFilter(null);
                        Toast.makeText(ReactionTimerActivity.this, R.string.reaction_too_fast, Toast.LENGTH_SHORT).show();

                    }
                });
            }
        };


        handler.postDelayed(countDownToReactionTimer, 2000);

    }

    public void onStatisticsMenuClick(MenuItem menu) {
        Intent intent = new Intent(this, StatisticActivity.class);
        intent.putExtra("type", "reactionTimer");
        startActivity(intent);
    }
}
