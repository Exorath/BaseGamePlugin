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

import com.exorath.exomenus.MenuAPI;
import com.exorath.exoteams.TeamAPI;
import com.exorath.plugin.basegame.gamePublisher.GamePublishManager;
import com.exorath.plugin.basegame.manager.Manager;
import com.exorath.plugin.basegame.maps.MapsManager;
import com.exorath.plugin.basegame.menus.MenuManager;
import com.exorath.plugin.basegame.state.StateManager;
import com.exorath.plugin.basegame.team.TeamManager;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;

import java.util.HashMap;

/**
 * Created by toonsev on 3/14/2017.
 */
public class SimpleBaseGameAPI implements BaseGameAPI {
    private HashMap<Class<? extends Manager>, Manager> managers = new HashMap<>();

    @Override
    public void addManager(Manager manager) {
        if (manager instanceof Listener)
            Bukkit.getPluginManager().registerEvents((Listener) manager, Main.getInstance());
        managers.put(manager.getClass(), manager);
    }

    @Override
    public <T extends Manager> T getManager(Class<T> managerClass) {
        return (T) managers.get(managerClass);
    }

    @Override
    public <T extends Manager> void removeManager(Class<T> managerClass) {
        managers.remove(managerClass);
    }

    @Override
    public TeamAPI getTeamAPI() {
        TeamManager teamManager = getManager(TeamManager.class);
        return teamManager == null ? null : teamManager.getTeamAPI();
    }

    @Override
    public MenuAPI getMenuAPI() {
        return getManager(MenuManager.class).getMenuAPI();
    }

    @Override
    public MapsManager getMapsManager() {
        return getManager(MapsManager.class);
    }

    @Override
    public StateManager getStateManager() {
        return getManager(StateManager.class);
    }

    @Override
    public void onPlayerJoinGame(Player player) {
        GamePublishManager gamePublishManager = getManager(GamePublishManager.class);
        if (gamePublishManager != null)
            gamePublishManager.onJoin(player);
    }

    @Override
    public void onPlayerLeaveGame(Player player) {
        GamePublishManager gamePublishManager = getManager(GamePublishManager.class);
        if (gamePublishManager != null)
            gamePublishManager.onLeave(player);
    }


}
