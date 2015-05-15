/*
 * Copyright (C) 2014 RoboVM AB
 *
 * This program is free software; you can redistribute it and/or
 * modify it under the terms of the GNU General Public License
 * as published by the Free Software Foundation; either version 2
 * of the License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/gpl-2.0.html>.
 */
package org.robovm.compiler.plugin;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import org.robovm.compiler.config.Config;

public abstract class Plugin {
    /**
     * Returns the plugin's prefix arguments to be parsed from XML or the command line
     */
    public abstract PluginArguments getArguments();
    
    protected Map<String, String> parseArguments(Config config) {
        if(config.getPluginArguments() == null) {
            return Collections.<String, String>emptyMap();
        }
        
        PluginArguments declaredArgs = getArguments();
        Map<String, String> args = new HashMap<>();
        for(String arg: config.getPluginArguments()) {
            String[] tokens = arg.split("=");            
            String[] argNameParts = tokens[0].split(":");
            String prefix = argNameParts[0];
            String argName = argNameParts[1];
            String value = "";
            if(tokens.length == 2) {
                value = tokens[1];
            }
            if(declaredArgs.getPrefix().equals(prefix) && declaredArgs.hasArgument(argName)) {                                
                args.put(argName, value);
            }
        }
        return args;
    }
}
