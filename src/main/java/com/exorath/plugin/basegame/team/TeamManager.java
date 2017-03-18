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

package com.exorath.plugin.basegame.team;

import com.exorath.exoteams.TeamAPI;
import com.exorath.exoteams.player.TeamPlayer;
import com.exorath.plugin.basegame.manager.Manager;
import com.exorath.plugin.basegame.state.State;
import com.exorath.plugin.basegame.state.StateChangeEvent;
import com.exorath.plugin.basegame.state.StateManager;
import org.bukkit.event.EventHandler;

/**
 * Created by toonsev on 3/15/2017.
 */
public class TeamManager implements Manager{
    private TeamAPI teamAPI;

    private StateManager stateManager;

    public TeamManager(StateManager stateManager) {
        this.teamAPI = new TeamAPI();
        this.stateManager = stateManager;
        teamAPI.getGlobalStartRule().getObservableEvaluation().subscribe(canStart -> {
            if(stateManager.getState() == State.WAITING_FOR_PLAYERS)
                stateManager.setState(State.COUNTING_DOWN);
        });
    }

    @EventHandler
    public void onStateChange(StateChangeEvent stateChangeEvent){
        if(stateChangeEvent.getNewState() == State.WAITING_FOR_PLAYERS)
            if(teamAPI.getGlobalStartRule().evaluate())
                stateManager.setState(State.COUNTING_DOWN);
    }

    public TeamAPI getTeamAPI() {
        return teamAPI;
    }

    public static TeamPlayer getTeamPlayer(String uuid){
        return new UUIDTeamPlayer(uuid);
    }

    private static class UUIDTeamPlayer implements TeamPlayer{
        private String uuid;
        public UUIDTeamPlayer(String uuid) {
            this.uuid = uuid;
        }

        @Override
        public int hashCode() {
            return uuid.hashCode();
        }

        @Override
        public boolean equals(Object obj) {
            if(obj == null)
                return false;
            if(!(obj instanceof UUIDTeamPlayer))
                return false;
            return uuid.equals(((UUIDTeamPlayer) obj).getUuid());
        }

        public String getUuid() {
            return uuid;
        }

        @Override
        protected Object clone() throws CloneNotSupportedException {
            return new UUIDTeamPlayer(uuid);
        }

        @Override
        public String toString() {
            return uuid.toString();
        }
    }
}
