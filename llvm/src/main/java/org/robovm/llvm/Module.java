/*
 * Copyright (C) 2013 Trillian AB
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

import java.io.File;
import java.io.UnsupportedEncodingException;

import org.robovm.llvm.binding.LLVM;
import org.robovm.llvm.binding.MemoryBufferRef;
import org.robovm.llvm.binding.ModuleRef;
import org.robovm.llvm.binding.ModuleRefOut;
import org.robovm.llvm.binding.StringOut;

/**
 * 
 */
public class Module {
    protected ModuleRef ref;

    private Module(ModuleRef moduleRef) {
        this.ref = moduleRef;
    }
    
    protected final void checkDisposed() {
        if (ref == null) {
            throw new LlvmException("Already disposed");
        }
    }
    
    public synchronized void dispose() {
        checkDisposed();
        LLVM.DisposeModule(ref);
        ref = null;
    }
    
    public Type getTypeByName(String name) {
        checkDisposed();
        return new Type(LLVM.GetTypeByName(ref, name));
    }
    
    public void writeBitcode(File file) {
        checkDisposed();
        if (LLVM.WriteBitcodeToFile(ref, file.getAbsolutePath()) != 0) {
            throw new LlvmException("Write failed");
        }
    }
    
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((ref == null) ? 0 : ref.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        Module other = (Module) obj;
        if (ref == null) {
            if (other.ref != null) {
                return false;
            }
        } else if (!ref.equals(other.ref)) {
            return false;
        }
        return true;
    }

    public static Module parseIR(Context context, String ir, String filename) {
        try {
            return parseIR(context, ir.getBytes("utf-8"), filename);
        } catch (UnsupportedEncodingException e) {
            throw new LlvmException(e);
        }
    }
    
    public static Module parseIR(Context context, byte[] data, String filename) {
        filename = filename == null ? "" : filename;
        MemoryBufferRef memoryBufferRef = LLVM.CreateMemoryBufferWithMemoryRangeCopy(data, filename);
        if (memoryBufferRef == null) {
            throw new LlvmException("Failed to create memory buffer");
        }
        ModuleRefOut moduleRefOut = new ModuleRefOut();
        StringOut errorMessage = new StringOut();
        // LLVMParseIRInContext() takes ownership of the MemoryBuffer so there's no need for us
        // to dispose of it
        if (!LLVM.ParseIRInContext(context.ref, memoryBufferRef, moduleRefOut, errorMessage)) {
            return new Module(moduleRefOut.getValue());
        }
        throw new LlvmException(errorMessage.getValue().trim());
    }
}
