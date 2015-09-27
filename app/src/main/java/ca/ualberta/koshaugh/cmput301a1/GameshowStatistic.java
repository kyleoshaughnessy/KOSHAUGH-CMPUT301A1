package ca.ualberta.koshaugh.cmput301a1;

/**
 * Created by kyleoshaughnessy on 15-09-15.
 */
public class GameshowStatistic {
    private Integer numberOfPlayers;
    private Integer winningPlayer;

    public GameshowStatistic(Integer numberOfPlayers, Integer winningPlayer) {
        this.numberOfPlayers = numberOfPlayers;
        this.winningPlayer = winningPlayer;
    }

    public Integer getNumberOfPlayers() {
        return numberOfPlayers;
    }

    public Integer getWinningPlayer() {
        return winningPlayer;
    }
}
