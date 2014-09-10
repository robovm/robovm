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

import java.util.List;
import java.util.Objects;

/**
 * Stores the {@link CompilerPluginArgument} along with the prefix to be used
 * for the arguments.
 */
public class CompilerPluginArguments {
    private final String prefix;
    private final List<CompilerPluginArgument> arguments;

    public CompilerPluginArguments(String prefix, List<CompilerPluginArgument> arguments) {
        this.prefix = Objects.requireNonNull(prefix, "Prefix must not be null");
        this.arguments = Objects.requireNonNull(arguments, "arguments must not be null");
    }

    public String getPrefix() {
        return prefix;
    }

    public List<CompilerPluginArgument> getArguments() {
        return arguments;
    }
}
