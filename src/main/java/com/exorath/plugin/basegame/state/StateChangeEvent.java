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

package com.exorath.plugin.basegame.state;

import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

/**
 * Created by toonsev on 2/6/2017.
 */
public class StateChangeEvent extends Event {
    private static HandlerList handlerList = new HandlerList();
    private State oldState;
    private State newState;

    public StateChangeEvent(State oldState, State newState) {
        this.oldState = oldState;
        this.newState = newState;
    }

    public State getNewState() {
        return newState;
    }

    public State getOldState() {
        return oldState;
    }

    @Override
    public HandlerList getHandlers() {
        return handlerList;
    }
    public static HandlerList getHandlerList() {
        return handlerList;
    }
}
