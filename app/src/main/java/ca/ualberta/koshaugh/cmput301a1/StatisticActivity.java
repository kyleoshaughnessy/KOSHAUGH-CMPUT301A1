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

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * dual purpose activity that can show statistics from any implementation of a StatisticManager.
 *
 * users can select different numbers in the spinner to get more specific statistics based on the
 * type.
 *
 * Users also have the ability to launch an email intent to send their statistics.
 * they can also choose to clear all statistics for their current mode.
 */

public class StatisticActivity extends AppCompatActivity {

    private String type;
    private Integer spinnerNumber;
    private StatisticsManager statisticsManager;
    private String[] numberOptions;
    private Spinner numberSpinner;
    private ListView statsListView;
    private TextView headerTextView;
    private ArrayAdapter<String> numberAdapter;
    private ArrayAdapter<String> statsAdapter;
    private ArrayList<String> stats;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_statistics_list);

        headerTextView = (TextView) findViewById(R.id.headerTextView);


        type = getIntent().getStringExtra("type");

        switch (type) {
            case "gameShow":
                statisticsManager = GameshowStatisticManager.getManager();
                this.numberOptions = new String[]{
                        "2", "3", "4"
                };
                headerTextView.setText(R.string.number_of_players);
                break;
            case "reactionTimer":
                statisticsManager = ReactionStatisticManager.getManager();
                this.numberOptions = new String[]{
                        "10", "100", "All"
                };
                headerTextView.setText(R.string.number_of_recent_statistics);
                break;
        }

        headerTextView = (TextView) findViewById(R.id.headerTextView);


        statsListView = (ListView) findViewById(R.id.statisticsListView);
        stats = new ArrayList<>();
        statsAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, stats);
        statsListView.setAdapter(statsAdapter);
        statsAdapter.notifyDataSetChanged();

        numberAdapter = new ArrayAdapter<String>(
                this, android.R.layout.simple_spinner_dropdown_item, numberOptions);
        numberSpinner = (Spinner) findViewById(R.id.numberOfStatisticsSpinner);
        numberSpinner.setAdapter(numberAdapter);
        numberSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                updateListView();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                numberSpinner.setSelection(0);
                updateListView();
            }
        });
    }

    public void updateListView() {
        String number = (String) numberSpinner.getSelectedItem();

        try {
            spinnerNumber = Integer.parseInt(number);
        } catch (Exception e) {
            spinnerNumber = ((ReactionStatisticManager) statisticsManager).getNumberOfStatistics();
        }

        stats.clear();
        stats.addAll(statisticsManager.getPrintedStatistics(spinnerNumber));
        statsAdapter.notifyDataSetChanged();
    }

    public void onClickClearStatistics(View view) {
        statisticsManager.clearStatistics();
        Toast.makeText(this, R.string.statistics_cleared, Toast.LENGTH_SHORT).show();
        updateListView();
    }

    // Intent help taken from Aerrow's answer here:
    // http://stackoverflow.com/questions/10294363/action-send-force-sending-with-email
    public void onEmailStatisticsClick(View view) {
        String subject = "Statistics";

        switch (type) {
            case "gameShow":
                subject = "Relex Statistics - GameShowBuzzer "
                        + spinnerNumber
                        + " Player mode";
                break;
            case "reactionTimer":
                subject = "Reflex Statistics - Reaction Timer - past " + spinnerNumber + " times";
                break;
        }

        String message = Arrays.toString(statisticsManager.getPrintedStatistics(spinnerNumber).toArray());

        Intent emailIntent = new Intent(Intent.ACTION_SEND);
        emailIntent.setData(Uri.parse("mailto:"));
        emailIntent.setType("message/rfc822");
        emailIntent.putExtra(Intent.EXTRA_SUBJECT, subject);
        emailIntent.putExtra(Intent.EXTRA_TEXT, message);
        try {
            startActivity(Intent.createChooser(emailIntent, getString(R.string.send_email_dialog_info)));
        } catch (ActivityNotFoundException ex) {
            Toast.makeText(this, R.string.email_error, Toast.LENGTH_LONG).show();
        }

    }
}
