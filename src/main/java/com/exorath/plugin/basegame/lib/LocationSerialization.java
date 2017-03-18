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

package com.exorath.plugin.basegame.lib;

import com.exorath.plugin.basegame.Main;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.WorldCreator;
import org.bukkit.configuration.ConfigurationSection;

/**
 * Created by toonsev on 3/18/2017.
 */
public class LocationSerialization {
    public static Location getLocation(ConfigurationSection configSection){
        if(!configSection.contains("world"))
            Main.terminate("Tried to deserialize a location config section but it did not contain a world field");
        return getLocation(Bukkit.createWorld(new WorldCreator(configSection.getString("world"))), configSection);
    }

    public static Location getLocation(World world, ConfigurationSection configSection){
        if(!configSection.contains("x") || !configSection.contains("y") || !configSection.contains("z"))
            Main.terminate("Tried to deserialize a location config section but it did not contain an x, y or z");
        return new Location(world, configSection.getDouble("x"), configSection.getDouble("y"), configSection.getDouble("z"));
    }
}
