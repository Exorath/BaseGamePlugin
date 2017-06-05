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

package com.exorath.plugin.basegame.victory;

import com.exorath.plugin.basegame.Main;
import com.exorath.plugin.basegame.manager.ListeningManager;
import com.exorath.plugin.basegame.manager.Manager;
import com.exorath.service.stats.api.StatsServiceAPI;
import com.exorath.service.stats.res.PostStatReq;
import com.exorath.service.stats.res.Success;
import com.exorath.victoryHandler.VictoryHandlerAPI;
import com.exorath.victoryHandler.VictoryHandoutEvent;
import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;

/**
 * Created by toonsev on 5/31/2017.
 */
public class VictoryManager implements ListeningManager {
    private final String gameId;
    private StatsServiceAPI statsServiceAPI;
    private VictoryHandlerAPI victoryHandlerAPI;

    public VictoryManager(String gameId, StatsServiceAPI statsServiceAPI) {
        this.gameId = gameId;
        this.statsServiceAPI = statsServiceAPI;
        this.victoryHandlerAPI = new VictoryHandlerAPI();
    }

    public VictoryHandlerAPI getVictoryHandlerAPI() {
        return victoryHandlerAPI;
    }

    @EventHandler
    public void onVictoryHandout(VictoryHandoutEvent event) {
        event.getVictoryHandlerAPI().getVictoryPlayersByUUID().forEach((uuid, victoryPlayer) -> {
            if (victoryPlayer.getPosition() != null && victoryPlayer.getPosition() == 1)
                emitWin(uuid.toString());
        });
    }

    private void emitWin(String uuid) {
        System.out.println("Emitting win for " + uuid);
        Bukkit.getScheduler().runTaskAsynchronously(Main.getInstance(), () -> {
            Success success = statsServiceAPI.postStat(new PostStatReq(gameId, uuid, "wins", 1));
            if (!success.isSuccess())
                System.out.println("Failed to add victory win: " + success.getError());
        });
    }
}
