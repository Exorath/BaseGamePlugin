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

package com.exorath.plugin.basegame.maps;

import com.exorath.plugin.basegame.Main;
import com.exorath.plugin.basegame.manager.Manager;
import org.bukkit.Bukkit;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * A map is defined as a folder in the server root with an exomap.yml file in it.
 * Created by toonsev on 3/14/2017.
 */
public class MapsManager implements Manager {
    private ExoWorld gameMap;


    private void selectMap() {
        List<String> mapNames = getExoMapNames();
        if (mapNames.size() == 0) {
            System.out.println("No maps found, add a map with an exomap.yml");
            Main.terminate();
        }
        String randomMapName = mapNames.get(new Random().nextInt(mapNames.size()));
        gameMap = new ExoWorld(randomMapName);
        if (gameMap.getConfiguration() == null) {
            System.out.println("The map " + gameMap.getMapName() + " is not configured. Please add an exomap.yml Shutting down!");
            Main.terminate();
        }
    }

    private List<String> getExoMapNames() {
        File mapContainer = Bukkit.getWorldContainer();
        List<String> mapNames = new ArrayList<>();
        for (File mapDir : mapContainer.listFiles((dir, name) -> new File(dir, name).isDirectory())) {
            File exoDat = new File(mapDir, "exomap.yml");
            if (!exoDat.isFile())
                continue;
            mapNames.add(mapDir.getName());
        }
        return mapNames;
    }

    public ExoWorld getGameMap() {
        return gameMap;
    }
}
