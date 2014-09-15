/*
 * Copyright (C) 2014 Trillian Mobile AB
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

/**
 * Declares an argument to be added to the standard RoboVM command line
 * arguments for a {@link CompilerPlugin}. An argument can have an 
 * optional value (a second token to be parsed). 
 */
public class PluginArgument {
    private final String name;
    private final String valueName;
    private final String description;
    
    public PluginArgument(String name, String description) {
        this(name, null, description);
    }
    
    public PluginArgument(String name, String valueName, String description) {       
        this.name = name;
        this.valueName = valueName;
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public boolean hasValue() {
        return valueName != null;
    }
    
    public String getValueName() {
        return valueName;
    }

    public String getDescription() {
        return description;
    }
}
