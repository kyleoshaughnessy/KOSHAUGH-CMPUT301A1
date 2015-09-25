package ca.ualberta.koshaugh.cmput301a1;

import android.content.Context;
import android.content.SharedPreferences;
import android.widget.ArrayAdapter;

import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;

/**
 * Created by kyleoshaughnessy on 15-09-15.
 */
public class ReactionStatisticManager {

    private static final String preferneceFile = "KOSHAUGH_A1";
    private static final String key = "reactionStatisticList";

    private static ReactionStatisticManager studentListManager = null;
    private Context context;
    private ArrayList<ReactionStatistic> statistics;

    private ReactionStatisticManager(Context context) {
        this.context = context;
        this.statistics = new ArrayList<>();
    }

    public static void createManager(Context context) {
        if (studentListManager == null) {
            if (context == null) {
                throw new RuntimeException("missing context");
            }
            studentListManager = new ReactionStatisticManager(context);
            studentListManager.loadStatistics();
        }
    }

    public static ReactionStatisticManager getManager() {
        if (studentListManager == null) {
            throw new RuntimeException("ReactionStatisticManager has not yet been initialized");
        }
        return studentListManager;
    }

    public Integer getNumberOfStatistics() {
        return statistics.size();
    }

    public void addStatistic(ReactionStatistic statistic) {
        statistics.add(statistic);
    }

    public void saveStatistics() {
        SharedPreferences mPrefs = context.getSharedPreferences(preferneceFile, Context.MODE_PRIVATE);
        SharedPreferences.Editor prefsEditor = mPrefs.edit();
        Gson gson = new Gson();
        String json = gson.toJson(statistics);
        prefsEditor.putString(key, json);
        prefsEditor.commit();
    }

    public void loadStatistics() {
        Gson gson = new Gson();
        String json = context.getSharedPreferences(preferneceFile, context.MODE_PRIVATE).getString(key, "");
        ReactionStatistic[] tempStats = gson.fromJson(json, ReactionStatistic[].class);
        if (tempStats != null) {
            statistics = new ArrayList<>(Arrays.asList(tempStats));
        } else {
            statistics = new ArrayList<>();
        }
    }

    public ArrayList<String> getPrintedStatistics(Integer lastN) {
        ArrayList<String> stats = new ArrayList<>();
        if (statistics.size() == 0) {
            return stats;
        }
        stats.add("Maximum:\n" + getMaximum(lastN).toString());
        stats.add("Minimum:\n" + getMinimum(lastN).toString());
        stats.add("Median:\n" + getMedian(lastN).toString());
        stats.add("Average:\n" + getAverage(lastN) + " ms");
        return stats;
    }

    private ReactionStatistic getMedian(Integer lastN) {
        ArrayList<ReactionStatistic> tempList = getLastNReactionStatsSorted(lastN);

        return tempList.get(tempList.size() / 2);
    }

    private Long getAverage(Integer lastN) {
        Long total = (long) 0;
        Long size = (long) 0;
        for (ReactionStatistic stat : getLastNReactionStatsSorted(lastN)) {
            total += stat.getReactionTime();
            size++;
        }
        return total / size;
    }

    private ReactionStatistic getMinimum(Integer lastN) {
        return getLastNReactionStatsSorted(lastN).get(0);

    }

    private ReactionStatistic getMaximum(Integer lastN) {
        ArrayList<ReactionStatistic> tempList = getLastNReactionStatsSorted(lastN);
        return tempList.get(tempList.size() - 1);
    }

    private ReactionStatistic getMedian() {
        return getMedian(statistics.size());
    }

    private Long getAverage() {
        return getAverage(statistics.size());
    }

    private ReactionStatistic getMinimum() {
        return getMinimum(statistics.size());
    }

    private ReactionStatistic getMaximum() {
        return getMaximum(statistics.size());
    }

    private void sortByReactionTime() {
        sortByReactionTime(statistics);
    }

    private void sortByReactionTime(ArrayList<ReactionStatistic> list) {
        Comparator<ReactionStatistic> reactionTimeComparator = new Comparator<ReactionStatistic>() {
            @Override
            public int compare(ReactionStatistic lhs, ReactionStatistic rhs) {
                return lhs.getReactionTime().compareTo(rhs.getReactionTime());
            }
        };

        Collections.sort(list, reactionTimeComparator);
    }

    private void sortByStartTime() {
        Collections.sort(statistics);
    }

    private ArrayList<ReactionStatistic> getLastNReactionStatsSorted(Integer lastN) {
        sortByStartTime();
        ArrayList<ReactionStatistic> tempList = new ArrayList<>();
        try {
            tempList.addAll(statistics.subList(0, lastN));
        }
        catch (Exception e) {
            tempList.addAll(statistics);

        }
        sortByReactionTime(tempList);
        return tempList;
    }

    public void clearStatistics() {
        statistics.clear();
        saveStatistics();
    }
}
