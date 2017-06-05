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

import com.exorath.plugin.basegame.manager.ListeningManager;
import com.exorath.plugin.basegame.manager.Manager;
import com.exorath.service.stats.api.StatsServiceAPI;
import com.exorath.victoryHandler.VictoryHandlerAPI;
import com.exorath.victoryHandler.VictoryHandoutEvent;

/**
 * Created by toonsev on 5/31/2017.
 */
public class VictoryManager implements ListeningManager {
    private StatsServiceAPI statsServiceAPI;
    private VictoryHandlerAPI victoryHandlerAPI;
    public VictoryManager(StatsServiceAPI statsServiceAPI) {
        this.statsServiceAPI = statsServiceAPI;
        this.victoryHandlerAPI = new VictoryHandlerAPI();
    }

    public VictoryHandlerAPI getVictoryHandlerAPI() {
        return victoryHandlerAPI;
    }

    public void onVictoryHandout(VictoryHandoutEvent event){
        event.getVictoryHandlerAPI().getVictoryPlayersByUUID().forEach((uuid, victoryPlayer) -> {
                    if(victoryPlayer.getPosition() != null && victoryPlayer.getPosition() == 1){

                    }
                });
    }
}
