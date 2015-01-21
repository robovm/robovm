/*
 * Copyright (C) 2015 Trillian Mobile AB
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
package org.robovm.compiler.llvm;

/**
 * {@link Metadata} used to attach debug info to instructions.
 */
public class DebugMetadata extends Metadata {
    private final MetadataNode value;

    public DebugMetadata(MetadataNode value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return "!dbg " + value;
    }
    
}
