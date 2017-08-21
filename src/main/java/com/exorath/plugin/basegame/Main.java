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

import com.exorath.plugin.base.ExoBaseAPI;
import com.exorath.plugin.basegame.clickableEntities.ClickableEntitiesManager;
import com.exorath.plugin.basegame.countdown.CountdownManager;
import com.exorath.plugin.basegame.flavor.FlavorManager;
import com.exorath.plugin.basegame.gamePublisher.GamePublishManager;
import com.exorath.plugin.basegame.lobbyTeleport.LobbyTeleportManager;
import com.exorath.plugin.basegame.maps.MapsManager;
import com.exorath.plugin.basegame.state.StateManager;
import com.exorath.plugin.basegame.team.TeamManager;
import com.exorath.plugin.basegame.victory.VictoryManager;
import com.exorath.service.stats.api.StatsServiceAPI;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

/**
 * Created by toonsev on 3/14/2017.
 */
public class Main extends JavaPlugin {
    private static Main instance;
    private static BaseGameAPI baseGameAPI;
    private ExoBaseAPI exoBaseAPI;


    @Override
    public void onEnable() {
        Main.instance = this;
        Main.baseGameAPI = new SimpleBaseGameAPI();
        this.exoBaseAPI = ExoBaseAPI.getInstance();
        exoBaseAPI.registerManager(new FlavorManager(getConfig()));
        exoBaseAPI.registerManager(new StateManager());
        exoBaseAPI.registerManager(new MapsManager(exoBaseAPI.getManager(FlavorManager.class).getFlavor()));
        exoBaseAPI.registerManager(new GamePublishManager(getConfig(),
                baseGameAPI.getMapsManager().getGameMap().getMapName(),
                exoBaseAPI.getManager(FlavorManager.class).getFlavor()));
        exoBaseAPI.registerManager(new TeamManager());
        exoBaseAPI.registerManager(new LobbyTeleportManager(baseGameAPI.getMapsManager().getGameMap().getLobbySpawn()));
        exoBaseAPI.registerManager(new CountdownManager(baseGameAPI.getStateManager(), baseGameAPI.getTeamAPI()));
        exoBaseAPI.registerManager(new ClickableEntitiesManager(this));
        exoBaseAPI.registerManager(new VictoryManager(baseGameAPI.getGamePublishManager().getGameId(), new StatsServiceAPI(getStatsServiceAddress())));
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


    private String getStatsServiceAddress() {
        String address = System.getenv("STATS_SERVICE_ADDRESS");
        if (address == null)
            Main.terminate("No STATS_SERVICE_ADDRESS env found.");
        return address;
    }

}
