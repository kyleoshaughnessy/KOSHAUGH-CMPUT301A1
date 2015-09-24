package ca.ualberta.koshaugh.cmput301a1;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Spinner;

public class ReactionStatisticActivity extends AppCompatActivity {

    private Integer[] numberOptions;
    private Spinner numberSpinner;
    private ListView statsListView;
    private ArrayAdapter<Integer> numberAdapter;
    private ArrayAdapter<String> statsAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_statistics_list);

        this.numberOptions = new Integer[]{
                10, 100
        };

        numberAdapter = new ArrayAdapter<Integer>(
                this, android.R.layout.simple_spinner_item, numberOptions);
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
        Integer number = (Integer) numberSpinner.getSelectedItem();
        ReactionStatistic statistic = null;
        ReactionStatisticManager statisticManager = ReactionStatisticManager.getManager();
    }
}
