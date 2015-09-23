package ca.ualberta.koshaugh.cmput301a1;

import android.content.Context;
import android.content.SharedPreferences;

import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.Arrays;

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
        }
    }

    public static ReactionStatisticManager getManager() {
        if (studentListManager == null) {
            throw new RuntimeException("ReactionStatisticManager has not yet been initialized");
        }
        return studentListManager;
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

    public ReactionStatistic getMedian(Integer lastN) {
        return null;
    }

    public ReactionStatistic getAverage(Integer lastN) {
        return null;
    }

    public ReactionStatistic getMinimum(Integer lastN) {
        return null;
    }

    public ReactionStatistic getMaximum(Integer lastN) {
        return null;
    }

    public ReactionStatistic getMedian() {
        return getMedian(statistics.size());
    }

    public ReactionStatistic getAverage() {
        return getAverage(statistics.size());
    }

    public ReactionStatistic getMinimum() {
        return getMinimum(statistics.size());
    }

    public ReactionStatistic getMaximum() {
        return getMaximum(statistics.size());
    }

}
