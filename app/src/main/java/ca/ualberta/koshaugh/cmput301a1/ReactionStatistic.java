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

import java.util.Date;

/**
 * this class models the result of a reaction timer. Start and end times are recorded in order to
 * calculate the reaction time as well as aid in sort chronologically.
 *
 * this class will be used in conjunction with the ReactionStatisticManager.
 */
public class ReactionStatistic implements Comparable<ReactionStatistic> {
    private Long startTime;
    private Long endTime;


    public ReactionStatistic() {
    }

    public Long getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime.getTime();
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime.getTime();
    }

    public Long getReactionTime() {
        return endTime - startTime;
    }

    public void start() {
        setStartTime(new Date());
    }

    public void stop() {
        setEndTime(new Date());
    }

    @Override
    public int compareTo(ReactionStatistic another) {
        if (another == null) {
            return 1;
        }
        return another.getStartTime().compareTo(getStartTime());
    }

    @Override
    public String toString() {
        return (new Date(startTime)).toString() + " : " + getReactionTime() + " ns";
    }
}
