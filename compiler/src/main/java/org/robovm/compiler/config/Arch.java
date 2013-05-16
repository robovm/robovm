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

import java.io.File;

import org.robovm.compiler.CompilerException;

/**
 * @author niklas
 *
 */
public enum Arch {
    x86("i386", "i386"), 
    armv6("armv6", "armv6"),
    armv7("armv7", "armv7"),
    thumbv6("thumbv6", "armv6"),
    thumbv7("thumbv7", "armv7");
    
    private final String llvmName;
    private final String clangName;
    
    private Arch(String llvmName, String clangName) {
        this.llvmName = llvmName;
        this.clangName = clangName;
    }
    
    public String getLlvmName() {
        return llvmName;
    }
    
    public String getClangName() {
        return clangName;
    }
    
    public boolean isArm() {
        switch (this) {
        case armv6:
        case armv7:
        case thumbv6:
        case thumbv7:
            return true;
        default:
            return false;
        }
    }
    
    public static Arch getDefaultArch(File llvmHomeDir) {
        String host = OS.getHost(llvmHomeDir);
        if (host.matches("^(x86|i\\d86).*")) {
            return Arch.x86;
        }
        if (host.matches("^armv7.*")) {
            return Arch.armv7;
        }
        if (host.matches("^arm.*")) {
            return Arch.armv6;
        }
        throw new CompilerException("Unrecognized arch in Host string: " + host);
    }    
}
