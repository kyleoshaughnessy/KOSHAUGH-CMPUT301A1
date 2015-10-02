package ca.ualberta.koshaugh.cmput301a1;

import java.util.Date;

/**
 * Created by kyleoshaughnessy on 15-09-15.
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
