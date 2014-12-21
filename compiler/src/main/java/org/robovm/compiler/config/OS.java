/*
 * Copyright (C) 2012 Trillian Mobile AB
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
        case arm64:
            return size <= 16;
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
        case x86_64:
            // Both Darwin and Linux follow the "System V Application Binary 
            // Interface AMD64 Architecture Processor Supplement" which states
            // that structs of two eightbytes (i.e. 16 bytes) or less are passed 
            // and returned in registers.
            return size <= 16;
        }
        throw new IllegalArgumentException("Unknown arch: " + arch);
    }

    /**
     * Returns whether aggregate types of the specified size should be converted
     * to an integer before being returned from a method.
     * 
     * @param arch the {@link Arch}.
     * @param size the size of the aggregate type.
     * @return <code>true</code> or <code>false</code>.
     */
    public boolean returnSmallAggregateAsInteger(Arch arch, int size) {
        /*
         * On x86_64 LLVm seems to do the right thing when we just use the
         * struct as is when returning it. On x86 LLVM will return floating
         * point members (e.g. CGPoint) on the x87 stack which isn't correct
         * according to the ABI. On thumbv7 we also return as integer though it
         * could be the case that LLVM actually does the right thing there.
         */
        switch (arch) {
        case x86_64:
            return false;
        case x86:
            return true;
        case thumbv7:
            return true;
        case arm64:
            return false;
        }
        throw new IllegalArgumentException("Unknown arch: " + arch);
    }

    /**
     * Returns whether aggregate types of the specified size should be passed
     * using LLVM {@code byval} semantics for this {@link OS} and the specified 
     * {@link Arch}.
     * 
     * @param arch the {@link Arch}.
     * @param size the size of the aggregate type.
     * @return <code>true</code> or <code>false</code>.
     */
    public boolean useByvalForAggregateOfSize(Arch arch, int size) {
        /*
         * On x86 and thumbv7 strcuts, regardless of size, are always passed by
         * value on the stack or in registers by pushing the struct elements one
         * by one to the next available register/stack slot. This is what
         * happens when we use 'byval' on those platforms.
         * 
         * On x86_64 structs passed using 'byval' are instead pushed onto the
         * stack even if there are registers available for parameters. This is
         * the behavior specified by the ABI for structs larger than 16 bytes.
         * For smaller structs we must not use 'byval' but instead pass them
         * directly.
         */
        switch (arch) {
        case x86_64:
            // Both Darwin and Linux follow the "System V Application Binary 
            // Interface AMD64 Architecture Processor Supplement" which states
            // that structs larger than two eightbytes (i.e. 16 bytes) must be 
            // passed on the stack even if there are unassigned registers which
            // can be used for parameter passing.
            return size > 16;
        case x86:
            // Always use byval.
            return true;
        case thumbv7:
            // Always use byval.
            return true;
        case arm64:
            return size > 16;
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
