/*
 * Copyright (C) 2012 RoboVM AB
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

import java.nio.ByteOrder;

import org.robovm.compiler.CompilerException;
import org.robovm.llvm.Target;

/**
 * @author niklas
 *
 */
public enum Arch {
    x86("i386", "i386", "penryn", true, false),
    x86_64("x86_64", "x86_64", "penryn", false, false),
    thumbv7("thumbv7", "armv7", true, true),
    arm64("arm64", "arm64", false, true);
    
    private final String llvmName;
    private final String clangName;
    private final String llvmCpu;
    private final boolean is32Bit;
    private final boolean isArm;
    private final ByteOrder byteOrder;
    
    private Arch(String llvmName, String clangName, boolean is32Bit, boolean isArm) {
        this(llvmName, clangName, "generic", is32Bit, isArm);
    }

    private Arch(String llvmName, String clangName, String llvmCpu, boolean is32Bit, boolean isArm) {
        this(llvmName, clangName, llvmCpu, is32Bit, isArm, ByteOrder.LITTLE_ENDIAN);
    }
    
    private Arch(String llvmName, String clangName, String llvmCpu, boolean is32Bit, boolean isArm, ByteOrder byteOrder) {
        this.llvmName = llvmName;
        this.clangName = clangName;
        this.llvmCpu = llvmCpu;
        this.is32Bit = is32Bit;
        this.isArm = isArm;
        this.byteOrder = byteOrder;
    }
    
    public String getLlvmName() {
        return llvmName;
    }
    
    public String getClangName() {
        return clangName;
    }
    
    public String getLlvmCpu() {
        return llvmCpu;
    }
    
    public boolean isArm() {
        return isArm;
    }
    
    public boolean is32Bit() {
        return is32Bit;
    }
    
    public ByteOrder getByteOrder() {
        return byteOrder;
    }
    
    public static Arch getDefaultArch() {
        String hostTriple = Target.getHostTriple();
        if (hostTriple.matches("^(x86.64|amd64).*")) {
            return Arch.x86_64;
        }
        if (hostTriple.matches("^(x86|i\\d86).*")) {
            return Arch.x86;
        }
        throw new CompilerException("Unrecognized arch in host triple: " + hostTriple);
    }    
}
