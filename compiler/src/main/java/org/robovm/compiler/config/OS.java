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
package org.robovm.compiler.config;

import org.robovm.llvm.Target;

/**
 * @author niklas
 *
 */
public enum OS {
    linux, macosx, ios;
    
    public enum Family {linux, darwin}

    /**
     * Returns whether aggregate types of the specified size can be returned
     * in registers for this {@link OS} and the specified {@link Arch}.
     * 
     * @param arch the {@link Arch}.
     * @param size the size of the aggregate type.
     * @return <code>true</code> or <code>false</code>.
     */
    public boolean isReturnedInRegisters(Arch arch, int size) {
        switch (arch) {
        case thumbv7:
            // ARM's AAPCS is the basis of both the iOS and Linux (EABI) ABIs
            // and specifies that structs no larger than 4 bytes are returned
            // in r0.
            return size <= 4;
        case x86:
            // On Darwin structs of size 1, 2, 4 and 8 bytes are returned in eax:edx.
            // On Linux no structs are returned in registers.
            switch (this) {
            case macosx:
            case ios:
                return size == 1 || size == 2 || size == 4 || size == 8;
            case linux:
                return false;
            }
        }
        throw new IllegalArgumentException("Unknown arch: " + arch);
    }
    
    public Family getFamily() {
        return this == linux ? Family.linux : Family.darwin;
    }
    
    public static OS getDefaultOS() {
        String hostTriple = Target.getHostTriple();
        if (hostTriple.contains("linux")) {
            return OS.linux;
        }
        if (hostTriple.contains("darwin") || hostTriple.contains("apple")) {
            return OS.macosx;
        }
        throw new IllegalArgumentException("Unrecognized OS in host triple: " + hostTriple);
    }
}
