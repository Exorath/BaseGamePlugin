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

package com.exorath.plugin.basegame.lobbyTeleport;

import com.exorath.plugin.basegame.Main;
import com.exorath.plugin.basegame.manager.ListeningManager;
import com.exorath.plugin.basegame.state.State;
import com.exorath.plugin.basegame.state.StateChangeEvent;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.player.PlayerJoinEvent;

/**
 * Created by toonsev on 3/18/2017.
 */
public class LobbyTeleportManager implements ListeningManager {
    private Location lobbySpawn;

    public LobbyTeleportManager(Location lobbySpawn){
        this.lobbySpawn = lobbySpawn;
        if(lobbySpawn == null)
            Main.terminate("NO lobbySpawn found");
    }
    @EventHandler
    public void onStateChangeEvent(StateChangeEvent event) {
        if (event.getNewState() == State.WAITING_FOR_PLAYERS && event.getOldState() != State.COUNTING_DOWN)
            for(Player player : Bukkit.getOnlinePlayers())
                player.teleport(lobbySpawn);
    }
    @EventHandler
    public void onJoin(PlayerJoinEvent event){
        event.getPlayer().teleport(lobbySpawn);
    }
}
