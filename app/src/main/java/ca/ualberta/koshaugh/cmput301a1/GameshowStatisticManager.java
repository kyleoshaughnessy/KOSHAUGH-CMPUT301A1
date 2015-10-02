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

import android.content.Context;
import android.content.SharedPreferences;

import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.Arrays;


public class GameshowStatisticManager implements StatisticsManager<GameshowStatistic> {

    private static final String preferenceFile = "KOSHAUGH_A1";
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
        SharedPreferences mPrefs = context.getSharedPreferences(preferenceFile, Context.MODE_PRIVATE);
        SharedPreferences.Editor prefsEditor = mPrefs.edit();
        Gson gson = new Gson();
        String json = gson.toJson(statistics);
        prefsEditor.putString(key, json);
        prefsEditor.commit();
    }

    @Override
    public void loadStatistics() {
        Gson gson = new Gson();
        String json = context.getSharedPreferences(preferenceFile, context.MODE_PRIVATE).getString(key, "");
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

    @Override
    public ArrayList<String> getPrintedStatistics(Integer numOfPlayers) {
        ArrayList<GameshowStatistic> tempList = new ArrayList<>();
        ArrayList<Integer> count = new ArrayList<>();
        ArrayList<String> out = new ArrayList<>();

        for (GameshowStatistic stat : statistics) {
            if (stat.getNumberOfPlayers().equals(numOfPlayers)) {
                tempList.add(stat);
            }
        }

        for (int i = 0; i < numOfPlayers; i++) {
            count.add(i, 0);
            for (GameshowStatistic stat : tempList) {
                if (stat.getWinningPlayer().equals(i + 1)) {
                    count.set(i, count.get(i) + 1);
                }
            }
        }

        for (int i = 0; i < count.size(); i++) {
            out.add("Player " + (i + 1) + " wins: " + count.get(i));
        }

        return out;
    }
}
