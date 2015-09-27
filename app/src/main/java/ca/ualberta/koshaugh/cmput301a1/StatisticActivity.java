package ca.ualberta.koshaugh.cmput301a1;

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

public class StatisticActivity extends AppCompatActivity {

    private String type;
    private StatisticsManager statisticsManager;
    private String[] numberOptions;
    private Spinner numberSpinner;
    private ListView statsListView;
    private TextView headerTextView;
    private ArrayAdapter<String> numberAdapter;
    private ArrayAdapter<String> statsAdapter;
    private ArrayList<String> stats;

    public StatisticActivity() {
    }

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
        Integer actualNumber;

        try {
            actualNumber = Integer.parseInt(number);
        } catch (Exception e) {
            actualNumber = ((ReactionStatisticManager) statisticsManager).getNumberOfStatistics();
        }

        stats.clear();
        stats.addAll(statisticsManager.getPrintedStatistics(actualNumber));
        statsAdapter.notifyDataSetChanged();
    }

    public void onClickClearStatistics(View view) {
        statisticsManager.clearStatistics();
        Toast.makeText(this, R.string.statistics_cleared, Toast.LENGTH_SHORT).show();
        updateListView();
    }

    public void onEmailStatisticsClick(View view) {
        //TODO : Stub out functionality of emailing feature.
        Toast.makeText(this, "Open Email intent", Toast.LENGTH_SHORT).show();
    }
}
