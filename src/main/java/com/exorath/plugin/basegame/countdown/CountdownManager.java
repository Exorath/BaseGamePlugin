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

package com.exorath.plugin.basegame.countdown;

import com.exorath.exoteams.TeamAPI;
import com.exorath.plugin.basegame.BaseGameAPI;
import com.exorath.plugin.basegame.Main;
import com.exorath.plugin.basegame.manager.ListeningManager;
import com.exorath.plugin.basegame.state.State;
import com.exorath.plugin.basegame.state.StateChangeEvent;
import com.exorath.plugin.basegame.state.StateManager;
import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.scheduler.BukkitRunnable;

/**
 * Created by toonsev on 3/18/2017.
 */
public class CountdownManager implements ListeningManager {
    private StateManager stateManager;
    private TeamAPI teamAPI;
    public CountdownManager(StateManager stateManager, TeamAPI teamAPI){
        this.stateManager = stateManager;
        this.teamAPI = teamAPI;
        teamAPI.getPlayerJoinTeamObservable().subscribe(event -> checkCountdown());
        teamAPI.getPlayerLeaveTeamObservable().subscribe(event -> checkCountdown());
    }

    @EventHandler
    public void onStateChange(StateChangeEvent event){
        if(event.getNewState() == State.COUNTING_DOWN){
            new CountdownTask().runTaskTimer(Main.getInstance(), 0, 20);
        }
    }
    private void checkCountdown() {
        boolean canStart = teamAPI.getGlobalStartRule().evaluate();
        if (canStart) {
            if (stateManager.getState().equals(State.WAITING_FOR_PLAYERS)) {
                stateManager.setState(State.COUNTING_DOWN);
            }
        } else if (stateManager.getState().equals(State.COUNTING_DOWN)) {
            stateManager.setState(State.WAITING_FOR_PLAYERS);
        }else {
            Bukkit.broadcastMessage("Waiting for more players...");
        }
    }

    private class CountdownTask extends BukkitRunnable {
        private int seconds = 10;

        @Override
        public void run() {
            if (stateManager.getState() != State.COUNTING_DOWN) {
                Bukkit.getOnlinePlayers().forEach(p -> p.sendMessage("countdown cancelled."));
                cancel();
                return;
            }
            if (seconds == 0) {
                Bukkit.getOnlinePlayers().forEach(p -> p.sendMessage("Starting now..."));
                stateManager.setState(State.STARTED);
                cancel();
            } else
                Bukkit.getOnlinePlayers().forEach(p -> p.sendMessage("Starting in " + seconds + " seconds."));
            seconds--;
        }
    }
}
