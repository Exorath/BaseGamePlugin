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

package com.exorath.plugin.basegame.state;

import com.exorath.plugin.basegame.manager.ListeningManager;
import com.exorath.plugin.basegame.manager.Manager;
import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.player.AsyncPlayerPreLoginEvent;

/**
 * Created by toonsev on 3/14/2017.
 */
public class StateManager implements ListeningManager {
    private State state = null;

    public StateManager() {
        setState(State.INITIALIZING);
    }

    public synchronized State getState() {
        return state;
    }

    public synchronized void setState(State state) {
        if (this.state == state)
            return;
        State oldState = this.state;
        this.state = state;
        StateChangeEvent stateChangeEvent = new StateChangeEvent(oldState, state);
        Bukkit.getPluginManager().callEvent(stateChangeEvent);
    }

    @EventHandler
    public void preJoinEvent(AsyncPlayerPreLoginEvent event) {
        State state = getState();
        if (state != State.WAITING_FOR_PLAYERS && state != State.COUNTING_DOWN) {
            event.setLoginResult(AsyncPlayerPreLoginEvent.Result.KICK_OTHER);
            event.setKickMessage("Game already started");
        } else if (state != State.INITIALIZING) {
            event.setLoginResult(AsyncPlayerPreLoginEvent.Result.KICK_OTHER);
            event.setKickMessage("Game not ready");
        }
    }


}
