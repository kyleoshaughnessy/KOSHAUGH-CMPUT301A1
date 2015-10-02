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

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
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

    public void onPlayerButtonClick(View view) {
        GameshowStatistic stat = null;
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(false);
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });
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

    public void onStatisticsMenuClick(MenuItem menu) {
        Intent intent = new Intent(this, StatisticActivity.class);
        intent.putExtra("type", "gameShow");
        startActivity(intent);
    }
}
