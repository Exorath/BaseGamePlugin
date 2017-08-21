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
import com.exorath.plugin.base.ExoBaseAPI;
import com.exorath.plugin.basegame.gamePublisher.GamePublishManager;
import com.exorath.plugin.basegame.maps.MapsManager;
import com.exorath.plugin.basegame.state.StateManager;
import com.exorath.plugin.basegame.team.TeamManager;
import com.exorath.plugin.basegame.victory.VictoryManager;
import org.bukkit.entity.Player;

/**
 * Created by toonsev on 3/14/2017.
 */
public class SimpleBaseGameAPI implements BaseGameAPI {
    private ExoBaseAPI exoBaseAPI;

    public SimpleBaseGameAPI() {
        this.exoBaseAPI = ExoBaseAPI.getInstance();
    }

    @Override
    public TeamAPI getTeamAPI() {
        TeamManager teamManager = exoBaseAPI.getManager(TeamManager.class);
        return teamManager == null ? null : teamManager.getTeamAPI();
    }

    @Override
    public MapsManager getMapsManager() {
        return exoBaseAPI.getManager(MapsManager.class);
    }

    @Override
    public StateManager getStateManager() {
        return exoBaseAPI.getManager(StateManager.class);
    }


    @Override
    public VictoryManager getVictoryManager() {
        return exoBaseAPI.getManager(VictoryManager.class);
    }


    @Override
    public GamePublishManager getGamePublishManager() {
        return exoBaseAPI.getManager(GamePublishManager.class);
    }

    @Override
    public void onPlayerJoinGame(Player player) {
        GamePublishManager gamePublishManager = exoBaseAPI.getManager(GamePublishManager.class);
        if (gamePublishManager != null)
            gamePublishManager.onJoin(player);
    }

    @Override
    public void onPlayerLeaveGame(Player player) {
        GamePublishManager gamePublishManager = exoBaseAPI.getManager(GamePublishManager.class);
        if (gamePublishManager != null)
            gamePublishManager.onLeave(player);
    }


}
