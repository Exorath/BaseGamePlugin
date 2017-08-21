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

package com.exorath.plugin.basegame.team;

import com.exorath.exoteams.TeamAPI;
import com.exorath.exoteams.player.TeamPlayer;
import com.exorath.plugin.base.manager.Manager;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import java.util.UUID;

/**
 * Created by toonsev on 3/15/2017.
 */
public class TeamManager implements Manager{
    private TeamAPI teamAPI;


    public TeamManager() {
        this.teamAPI = new TeamAPI();
    }

    public TeamAPI getTeamAPI() {
        return teamAPI;
    }

    public static TeamPlayer getTeamPlayer(String uuid){
        return new UUIDTeamPlayer(uuid);
    }

    public static Player getPlayer(TeamPlayer teamPlayer){
        if(!(teamPlayer instanceof UUIDTeamPlayer))
            return null;
        return Bukkit.getPlayer(UUID.fromString(((UUIDTeamPlayer) teamPlayer).getUuid()));
    }

    private static class UUIDTeamPlayer implements TeamPlayer{
        private String uuid;
        public UUIDTeamPlayer(String uuid) {
            this.uuid = uuid;
        }

        @Override
        public int hashCode() {
            return uuid.hashCode();
        }

        @Override
        public boolean equals(Object obj) {
            if(obj == null)
                return false;
            if(!(obj instanceof UUIDTeamPlayer))
                return false;
            return uuid.equals(((UUIDTeamPlayer) obj).getUuid());
        }

        public String getUuid() {
            return uuid;
        }

        @Override
        protected Object clone() throws CloneNotSupportedException {
            return new UUIDTeamPlayer(uuid);
        }

        @Override
        public String toString() {
            return uuid.toString();
        }
    }
}
