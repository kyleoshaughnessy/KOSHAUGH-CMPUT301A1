package ca.ualberta.koshaugh.cmput301a1;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.Toast;

import java.util.ArrayList;

public class ReactionStatisticActivity extends AppCompatActivity {

    private String[] numberOptions;
    private Spinner numberSpinner;
    private ListView statsListView;
    private ArrayAdapter<String> numberAdapter;
    private ArrayAdapter<String> statsAdapter;
    private ArrayList<String> stats;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_statistics_list);

        statsListView = (ListView) findViewById(R.id.statisticsListView);
        stats = new ArrayList<>();
        statsAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, stats);
        statsListView.setAdapter(statsAdapter);
        statsAdapter.notifyDataSetChanged();


        this.numberOptions = new String[]{
                "10", "100", "All"
        };

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
        }
        catch (Exception e) {
            actualNumber = ReactionStatisticManager.getManager().getNumberOfStatistics();
        }

        ReactionStatisticManager statisticManager = ReactionStatisticManager.getManager();
        stats.clear();
        stats.addAll(statisticManager.getPrintedStatistics(actualNumber));
        statsAdapter.notifyDataSetChanged();
    }

    public void onClickClearStatistics(View view) {
        ReactionStatisticManager.getManager().clearStatistics();
        Toast.makeText(this, R.string.statistics_cleared, Toast.LENGTH_SHORT).show();
    }

    public void onEmailStatisticsClick(View view) {
        //TODO : Stub out functionality of emailing feature.
        Toast.makeText(this, "Open Email intent", Toast.LENGTH_SHORT).show();
    }
}
