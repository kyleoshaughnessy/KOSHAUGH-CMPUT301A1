package ca.ualberta.koshaugh.cmput301a1;

import android.content.Context;
import android.content.SharedPreferences;

import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * Created by kyleoshaughnessy on 15-09-26.
 */
public class GameshowStatisticManager implements StatisticsManager<GameshowStatistic> {

    private static final String preferneceFile = "KOSHAUGH_A1";
    private static final String key = "gameshowStatisticList";

    private static GameshowStatisticManager gameshowStatisticManager = null;
    private Context context;
    private ArrayList<GameshowStatistic> statistics;


    private GameshowStatisticManager(Context context) {
        this.context = context;
        this.statistics = new ArrayList<>();
    }

    public static void createManager(Context context) {
        if (gameshowStatisticManager == null) {
            if (context == null) {
                throw new RuntimeException("missing context");
            }
            gameshowStatisticManager = new GameshowStatisticManager(context);
            gameshowStatisticManager.loadStatistics();
        }
    }

    public static GameshowStatisticManager getManager() {
        if (gameshowStatisticManager == null) {
            throw new RuntimeException("ReactionStatisticManager has not yet been initialized");
        }
        return gameshowStatisticManager;
    }

    @Override
    public void saveStatistics() {
        SharedPreferences mPrefs = context.getSharedPreferences(preferneceFile, Context.MODE_PRIVATE);
        SharedPreferences.Editor prefsEditor = mPrefs.edit();
        Gson gson = new Gson();
        String json = gson.toJson(statistics);
        prefsEditor.putString(key, json);
        prefsEditor.commit();
    }

    @Override
    public void loadStatistics() {
        Gson gson = new Gson();
        String json = context.getSharedPreferences(preferneceFile, context.MODE_PRIVATE).getString(key, "");
        GameshowStatistic[] tempStats = gson.fromJson(json, GameshowStatistic[].class);
        if (tempStats != null) {
            statistics = new ArrayList<>(Arrays.asList(tempStats));
        } else {
            statistics = new ArrayList<>();
        }
    }

    @Override
    public void clearStatistics() {
        statistics.clear();
        saveStatistics();
    }

    @Override
    public void addStatistic(GameshowStatistic statistic) {
        statistics.add(statistic);
        saveStatistics();
    }
}
