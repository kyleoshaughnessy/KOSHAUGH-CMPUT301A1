package ca.ualberta.koshaugh.cmput301a1;

import android.app.AlertDialog;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

public class GameshowModeActivity extends AppCompatActivity {
    private Integer numberOfPlayers;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        numberOfPlayers = getIntent().getIntExtra("numberOfPlayers", 2);
        switch (numberOfPlayers) {
            case 4:
                setContentView(R.layout.activity_game_show_4_players);
                break;
            case 3:
                setContentView(R.layout.activity_game_show_3_players);
                break;
            case 2:
                setContentView(R.layout.activity_game_show_2_players);
                break;
            default:
                setContentView(R.layout.activity_game_show_2_players);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_gameshow_mode, menu);
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

    public void onPlayerButtonClick(View view) {
        GameshowStatistic stat = null;
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        switch (view.getId()) {
            case R.id.playerOneButton:
                stat = new GameshowStatistic(numberOfPlayers, 1);
                builder.setMessage("Player 1 buzzed in first!");
                break;
            case R.id.playerTwoButton:
                stat = new GameshowStatistic(numberOfPlayers, 2);
                builder.setMessage("Player 2 buzzed in first!");
                break;
            case R.id.playerThreeButton:
                stat = new GameshowStatistic(numberOfPlayers, 3);
                builder.setMessage("Player 3 buzzed in first!");
                break;
            case R.id.playerFourButton:
                stat = new GameshowStatistic(numberOfPlayers, 4);
                builder.setMessage("Player 4 buzzed in first!");
                break;
        }
        if (stat != null) {
            GameshowStatisticManager.getManager().addStatistic(stat);
            builder.show();
        }


    }
}
