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

/**
 * StatisticManager is used to model the requirements of a manager used in the StatisticsActivity
 * If a new class can fulfil the contract supplied here it can be esasily integrated into that
 * activity
 *
 * This interface represents a manger that can load, save, clear, add to, and present its
 * statistics.
 */

import java.util.ArrayList;

public interface StatisticsManager<StatisticType> {

    public void saveStatistics();

    public void loadStatistics();

    public void clearStatistics();

    public void addStatistic(StatisticType statistic);

    public ArrayList<String> getPrintedStatistics(Integer number);
}