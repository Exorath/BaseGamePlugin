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

package com.exorath.plugin.basegame.gamePublisher;

import com.exorath.plugin.base.ExoBaseAPI;
import com.exorath.plugin.basegame.Main;
import com.exorath.plugin.basegame.manager.Manager;
import com.exorath.service.connector.res.BasicServer;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;

import java.net.UnknownHostException;

/**
 * Created by toonsev on 3/14/2017.
 */
public class GamePublishManager implements Manager{
    FileConfiguration configuration;
    public GamePublishManager(FileConfiguration configuration, String mapId, String flavorId) {
        this.configuration = configuration;
        try {
            ExoBaseAPI.getInstance().setupGame(new BasicServer(getGameId(), mapId, flavorId));//TODO: Fetch mapId and flavorId propperly
        } catch (UnknownHostException e) {
            e.printStackTrace();
            Main.terminate();
        }
    }

    public void onJoin(Player player){
        ExoBaseAPI.getInstance().onGameJoin(player);
    }

    public void onLeave(Player player){
        ExoBaseAPI.getInstance().onGameLeave(player);
    }


    private String getGameId(){
        if(!configuration.contains("connector.gameId")){
            System.out.println("MainLobbyPlugin config does not contain connector.gameId, exiting");
            Main.terminate();
        }
        return configuration.getString("connector.gameId");
    }
}
