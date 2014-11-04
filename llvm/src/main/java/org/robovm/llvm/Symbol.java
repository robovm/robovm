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
package org.robovm.llvm;

/**
 * 
 */
public class Symbol {
    private final String name;
    private final long address;
    private final long size;
    private final long fileOffset;

    Symbol(String name, long address, long size, long fileOffset) {
        this.name = name;
        this.address = address;
        this.size = size;
        this.fileOffset = fileOffset;
    }

    public String getName() {
        return name;
    }

    public long getAddress() {
        return address;
    }

    public long getSize() {
        return size;
    }

    public long getFileOffset() {
        return fileOffset;
    }

    @Override
    public String toString() {
        return String.format("Symbol [name=%s, address=%s, size=%s, fileOffset=%s]", name, address, size, fileOffset);
    }
}
