package ca.ualberta.koshaugh.cmput301a1;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ReactionStatisticManager.createManager(getApplicationContext());
        GameshowStatisticManager.createManager(getApplicationContext());
    }

    public void onReactionTimerButtonClick(View v) {
        final Intent intent = new Intent(this, ReactionTimerActivity.class);

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage(R.string.reaction_modal_dialog);
        builder.setCancelable(false);
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                startActivity(intent);
            }
        });
        AlertDialog alert = builder.create();
        alert.show();
    }

    public void onGameshowButtonClick(View v) {
        final Intent intent = new Intent(this, GameshowModeActivity.class);
        String[] numberOfPlayersOptions = new String[]{"2", "3", "4"};

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle(R.string.game_show_mode_choose_players);
        builder.setItems(numberOfPlayersOptions, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                intent.putExtra("numberOfPlayers", which + 2);
                startActivity(intent);
            }
        });
        builder.show();
    }
}
