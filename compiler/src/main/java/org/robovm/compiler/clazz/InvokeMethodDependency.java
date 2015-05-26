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
package org.robovm.compiler.clazz;

/**
 * Records a dependency between a method and the methods it calls.
 */
public class InvokeMethodDependency extends MethodDependency {
    private static final long serialVersionUID = 1L;

    InvokeMethodDependency(String owner, String name, String desc, String path, boolean inBootClasspath, boolean weak) {
        super(owner, name, desc, path, inBootClasspath, weak);
    }
}
