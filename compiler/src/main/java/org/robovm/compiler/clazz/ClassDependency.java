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
 * Records a dependency on a class <code>A</code> for a class or method
 * <code>B</code> and the path where <code>A</code> was located when
 * <code>B</code> was built.
 */
public class ClassDependency extends Dependency {
    private static final long serialVersionUID = 1L;

    ClassDependency(String className, String path, boolean inBootClasspath, boolean weak) {
        super(className, path, inBootClasspath, weak);
    }
}
