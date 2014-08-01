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
package org.robovm.compiler;

import org.robovm.compiler.clazz.Clazz;

/**
 * Called by the {@link ClassCompiler} to report the result of a class
 * compilation.
 */
public interface ClassCompilerListener {

    /**
     * Notifies that the specified class was compiled successfully.
     * 
     * @param clazz the compiled class.
     */
    void success(Clazz clazz);

    /**
     * Notifies that the specified class couldn't be compiled.
     * 
     * @param clazz the compiled class.
     * @param t a {@link Throwable} which caused the compilation to fail.
     */
    void failure(Clazz clazz, Throwable t);
}
