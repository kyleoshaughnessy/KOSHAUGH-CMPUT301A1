package ca.ualberta.koshaugh.cmput301a1;

/**
 * Created by kyleoshaughnessy on 15-09-26.
 */
public interface StatisticsManager<StatisticType> {

    public void saveStatistics();

    public void loadStatistics();

    public void clearStatistics();

    public void addStatistic(StatisticType statistic);
}
