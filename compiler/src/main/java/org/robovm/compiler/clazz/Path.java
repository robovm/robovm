/*
 * Copyright (C) 2012 Trillian AB
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

import java.io.File;
import java.util.Set;

/**
 *
 * @version $Id$
 */
public interface Path {
    int getIndex();
    File getFile();
    Set<Clazz> listClasses();
    
    /**
     * Loads a generated class associated with this {@link Path}.
     * 
     * @return the {@link Clazz} or {@code null} if not found.
     */
    Clazz loadGeneratedClass(String internalName);
    
    /**
     * Returns the {@link File} with the path where the generated
     * class file with the specified internal name should be saved.
     * 
     * @return the {@link File} for the specified generated class.
     */
    File getGeneratedClassFile(String internalName);
    
    boolean hasChangedSince(long timestamp);
    boolean isInBootClasspath();
}
