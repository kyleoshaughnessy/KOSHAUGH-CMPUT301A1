package ca.ualberta.koshaugh.cmput301a1;

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

    private static final Handler handler = new Handler();

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

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void onStartCountdownButtonClick(View view) {
        final Drawable reactionButtonBackground =
                findViewById(R.id.reactionButton).getBackground();

        final Button reactionTimeButton = (Button) findViewById(R.id.reactionButton);

        reactionButtonBackground.setColorFilter(Color.RED, PorterDuff.Mode.SRC_ATOP);

        final Runnable getReactionTime = new Runnable() {
            @Override
            public void run() {
                final ReactionStatistic stat = new ReactionStatistic();
                reactionButtonBackground.setColorFilter(Color.GREEN, PorterDuff.Mode.SRC_ATOP);
                reactionTimeButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        stat.stop();
                        Toast.makeText(ReactionTimerActivity.this, "You reacted in " + stat.getReactionTime().toString() + " ms", Toast.LENGTH_LONG).show();
                        reactionTimeButton.setOnClickListener(null);
                    }
                });
                stat.start();
            }
        };

        Runnable countDownToReactionTimer = new Runnable() {
            @Override
            public void run() {
                reactionButtonBackground.setColorFilter(Color.YELLOW, PorterDuff.Mode.SRC_ATOP);
                long delay = (long) (Math.random() * 2000);
                delay = Math.max(10, delay);
                handler.postDelayed(getReactionTime, delay);

                reactionTimeButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        handler.removeCallbacks(getReactionTime);
                        Toast.makeText(ReactionTimerActivity.this, "You reacted too quickly!", Toast.LENGTH_SHORT).show();
                    }
                });
            }
        };


        handler.postDelayed(countDownToReactionTimer, 2000);

    }
}
