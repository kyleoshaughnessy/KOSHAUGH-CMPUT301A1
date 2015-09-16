package ca.ualberta.koshaugh.cmput301a1;

import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
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
        Toast.makeText(this, "On your marks!", Toast.LENGTH_SHORT).show();
        final Drawable reactionButtonBackground =
                findViewById(R.id.reactionButton).getBackground();

        reactionButtonBackground.setColorFilter(Color.RED, PorterDuff.Mode.SRC_ATOP);

        Runnable task = new Runnable() {
            @Override
            public void run() {
                Toast.makeText(ReactionTimerActivity.this, "Get set!", Toast.LENGTH_SHORT).show();
                reactionButtonBackground.setColorFilter(Color.YELLOW, PorterDuff.Mode.SRC_ATOP);
            }
        };

        handler.postDelayed(task, 3000);

    }
}
