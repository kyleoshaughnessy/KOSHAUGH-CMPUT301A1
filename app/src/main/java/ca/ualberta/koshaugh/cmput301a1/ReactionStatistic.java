package ca.ualberta.koshaugh.cmput301a1;

import java.util.Date;

/**
 * Created by kyleoshaughnessy on 15-09-15.
 */
public class ReactionStatistic implements Comparable<ReactionStatistic> {
    private Date startTime;
    private Date endTime;

    public ReactionStatistic() {
    }

    public ReactionStatistic(Date startTime) {
        this.startTime = startTime;
    }

    public ReactionStatistic(Date startTime, Date endTime) {
        this.startTime = startTime;
        this.endTime = endTime;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public Long getReactionTime() {
        return endTime.getTime() - startTime.getTime();
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
        return getStartTime().compareTo(another.getStartTime());
    }
}
