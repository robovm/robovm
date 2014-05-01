/*
 * Copyright (C) 2013 Trillian Mobile AB
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
package org.robovm.compiler.log;

/**
 * {@link Logger} implementation which logs to {@code System.out}. 
 */
public class ConsoleLogger implements Logger {
    private final boolean verbose;

    public ConsoleLogger(boolean verbose) {
        this.verbose = verbose;
    }
    
    public void info(String format, Object... args) {
        if (verbose) {
            System.out.format(format, args);
            System.out.println();
        }
    }
    public void error(String format, Object... args) {
        System.out.format(format, args);
        System.out.println();
    }
    public void warn(String format, Object... args) {
        System.out.format(format, args);
        System.out.println();
    }
    public void debug(String format, Object... args) {
        if (verbose) {
            System.out.format(format, args);
            System.out.println();
        }
    }
}
