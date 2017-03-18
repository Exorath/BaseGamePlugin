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

package com.exorath.plugin.basegame.menus;

import com.exorath.exomenus.MenuAPI;
import com.exorath.plugin.basegame.manager.Manager;

/**
 * Created by toonsev on 3/18/2017.
 */
public class MenuManager implements Manager {
    private MenuAPI menuAPI;

    public MenuManager(MenuAPI menuAPI) {
        this.menuAPI = menuAPI;
    }

    public MenuAPI getMenuAPI() {
        return menuAPI;
    }
}
