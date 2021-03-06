/*
 * Copyright 2017 Exorath
 *
 *    Licensed under the Apache License, Version 2.0 (the "License");
 *    you may not use this file except in compliance with the License.
 *    You may obtain a copy of the License at
 *
 *        http://www.apache.org/licenses/LICENSE-2.0
 *
 *    Unless required by applicable law or agreed to in writing, software
 *    distributed under the License is distributed on an "AS IS" BASIS,
 *    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *    See the License for the specific language governing permissions and
 *    limitations under the License.
 */

package com.exorath.plugin.basegame;

import com.exorath.exoteams.TeamAPI;
import com.exorath.plugin.basegame.gamePublisher.GamePublishManager;
import com.exorath.plugin.basegame.maps.MapsManager;
import com.exorath.plugin.basegame.state.StateManager;
import com.exorath.plugin.basegame.victory.VictoryManager;
import org.bukkit.entity.Player;

/**
 * Created by toonsev on 3/14/2017.
 */
public interface BaseGameAPI {

    TeamAPI getTeamAPI();
    MapsManager getMapsManager();

    static BaseGameAPI getInstance(){
        return Main.getBaseGameAPI();
    }

    /**
     * The game should call this when a player joins the game (like the player actually joined a game)
     * @param player the player that joined the game
     */
    void onPlayerJoinGame(Player player);


    /**
     * The game should call this when a player leaves the game
     * @param player the player that left the game
     */
    void onPlayerLeaveGame(Player player);

    /**
     * Gets the statemanager
     * @return
     */
    StateManager getStateManager();

    VictoryManager getVictoryManager();

    GamePublishManager getGamePublishManager();

}
