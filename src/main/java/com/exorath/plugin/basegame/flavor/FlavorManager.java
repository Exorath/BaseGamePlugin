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

package com.exorath.plugin.basegame.flavor;

import com.exorath.plugin.basegame.manager.Manager;
import org.bukkit.configuration.file.FileConfiguration;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

/**
 * Created by toonsev on 3/14/2017.
 */
public class FlavorManager implements Manager {
    private static Random random = new Random();
    private List<String> flavors;
    private String flavor;
    private FileConfiguration configuration;
    public FlavorManager(FileConfiguration configuration){
        this.configuration = configuration;
        this.flavors = loadFlavorsFromConfig();
        this.flavor = flavors.get(random.nextInt(flavors.size()));
    }

    public String getFlavor() {
        return flavor;
    }

    public List<String> getFlavors() {
        return flavors;
    }

    private List<String> loadFlavorsFromConfig(){
        List<String> flavors = configuration.getStringList("connector.flavorIds");
        if(flavors == null || flavors.isEmpty())
            return Arrays.asList(new String[]{"default"});
        return flavors;
    }
}
