/*
 * Copyright (C) 2015 RoboVM AB
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
 * {@link Logger} implementation forwards to another {@link Logger}.
 */
public class LoggerProxy implements Logger {
    private final Logger target;

    public LoggerProxy(Logger target) {
        this.target = target;
    }

    public void info(String format, Object... args) {
        target.info(format, args);
    }

    public void error(String format, Object... args) {
        target.error(format, args);
    }

    public void warn(String format, Object... args) {
        target.warn(format, args);
    }

    public void debug(String format, Object... args) {
        target.debug(format, args);
    }
}
