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

import com.exorath.plugin.basegame.flavor.FlavorManager;
import com.exorath.plugin.basegame.gamePublisher.GamePublishManager;
import com.exorath.plugin.basegame.lobbyTeleport.StateTeleportManager;
import com.exorath.plugin.basegame.maps.MapsManager;
import com.exorath.plugin.basegame.state.StateManager;
import com.exorath.plugin.basegame.team.TeamManager;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

/**
 * Created by toonsev on 3/14/2017.
 */
public class Main extends JavaPlugin {
    private static Main instance;
    private static BaseGameAPI baseGameAPI;

    @Override
    public void onEnable() {
        Main.instance = this;
        Main.baseGameAPI = new SimpleBaseGameAPI();
        Main.getBaseGameAPI().addManager(new FlavorManager(getConfig()));
        Main.getBaseGameAPI().addManager(new StateManager());
        Main.getBaseGameAPI().addManager(new MapsManager(baseGameAPI.getManager(FlavorManager.class).getFlavor()));
        Main.getBaseGameAPI().addManager(new GamePublishManager(getConfig(),
                baseGameAPI.getMapsManager().getGameMap().getMapName(),
                Main.getBaseGameAPI().getManager(FlavorManager.class).getFlavor()));
        Main.getBaseGameAPI().addManager(new TeamManager(baseGameAPI.getStateManager()));
        Main.getBaseGameAPI().addManager(new StateTeleportManager(baseGameAPI.getMapsManager().getGameMap().getLobbySpawn()));
    }

    public static Main getInstance() {
        return instance;
    }


    public static BaseGameAPI getBaseGameAPI() {
        return baseGameAPI;
    }

    public static void terminate() {
        System.out.println("1v1Plugin is terminating...");
        Bukkit.shutdown();
        System.out.println("Termination failed, force exiting system...");
        System.exit(1);
    }
    public static void terminate(String message) {
        System.out.println(message);
        System.out.println("1v1Plugin is terminating...");
        Bukkit.shutdown();
        System.out.println("Termination failed, force exiting system...");
        System.exit(1);
    }

}
