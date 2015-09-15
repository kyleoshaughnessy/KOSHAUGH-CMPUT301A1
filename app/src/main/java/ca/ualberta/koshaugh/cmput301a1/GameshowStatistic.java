package ca.ualberta.koshaugh.cmput301a1;

/**
 * Created by kyleoshaughnessy on 15-09-15.
 */
public class GameshowStatistic {
    private Player winningPlayer;

    public GameshowStatistic(Player winningPlayer) {
        this.winningPlayer = winningPlayer;
    }

    public Player getWinningPlayer() {
        return winningPlayer;
    }

    public void setWinningPlayer(Player winningPlayer) {
        this.winningPlayer = winningPlayer;
    }

    public enum Player {
        PLAYER_ONE("Player One"),
        PLAYER_TWO("Player Two"),
        PLAYER_THREE("Player Three"),
        PLAYER_FOUR("Player Four");

        private String id;

        Player(String id) {
            this.id = id;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }
    }
}
