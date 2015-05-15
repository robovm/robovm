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
package org.robovm.compiler.llvm;

/**
 *
 * @version $Id$
 */
public class UnnamedMetadata {
    private final int index;
    private Metadata value;

    public UnnamedMetadata(int index) {
        this.index = index;
    }

    public UnnamedMetadata(int index, Metadata value) {
        this.index = index;
        this.value = value;
    }

    public void setValue(Metadata value) {
        this.value = value;
    }
    
    public UnnamedMetadataRef ref() {
        return new UnnamedMetadataRef(this);
    }
    
    public int getIndex() {
        return index;
    }
    
    @Override
    public String toString() {
        return "!" + index;
    }
    
    public String getDefinition() {
        StringBuilder sb = new StringBuilder();
        sb.append('!');
        sb.append(index);
        sb.append(" = ");
        if (value.getType() != Type.METADATA) {
            sb.append(value.getType());
            sb.append(' ');
        }
        sb.append(value.toString());
        return sb.toString();
    }
}
