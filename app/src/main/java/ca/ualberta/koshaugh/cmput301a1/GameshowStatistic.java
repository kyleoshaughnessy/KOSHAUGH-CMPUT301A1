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
 * GameshowStatistic is meant to model a result of the gameshow mode represented by GameshowActivity
 *
 * This provides an abstraction for who won the game as well as how many players were playing
 * Will be used with the GameshowStatisticManager
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

    @Override
    public String toString() {
        return "Number of players: " + numberOfPlayers.toString() + "; Winner: " + winningPlayer.toString();
    }
}
