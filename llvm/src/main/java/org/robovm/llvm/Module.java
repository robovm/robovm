/*
 * Copyright (C) 2013 Trillian Mobile AB
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
import java.util.ArrayList;
import java.util.List;

import org.robovm.llvm.binding.LLVM;
import org.robovm.llvm.binding.MemoryBufferRef;
import org.robovm.llvm.binding.ModuleRef;
import org.robovm.llvm.binding.ModuleRefOut;
import org.robovm.llvm.binding.StringOut;
import org.robovm.llvm.binding.ValueRef;

/**
 * 
 */
public class Module implements AutoCloseable {
    private ModuleRef ref;

    private Module(ModuleRef moduleRef) {
        this.ref = moduleRef;
    }
    
    protected final void checkDisposed() {
        if (ref == null) {
            throw new LlvmException("Already disposed");
        }
    }

    protected ModuleRef getRef() {
        checkDisposed();
        return ref;
    }

    public synchronized void dispose() {
        LLVM.DisposeModule(getRef());
        ref = null;
    }

    @Override
    public void close() {
        dispose();
    }

    public Type getTypeByName(String name) {
        return new Type(LLVM.GetTypeByName(getRef(), name));
    }

    public Function getFunctionByName(String name) {
        ValueRef fref = LLVM.GetNamedFunction(getRef(), name);
        return fref != null ? new Function(fref) : null;
    }
    
    public Function[] getFunctions() {
        List<Function> result = new ArrayList<>();
        for (ValueRef fref = LLVM.GetFirstFunction(getRef()); fref != null; fref = LLVM.GetNextFunction(fref)) {
            result.add(new Function(fref));
        }
        return result.toArray(new Function[result.size()]);
    }
    
    public void writeBitcode(File file) {
        if (LLVM.WriteBitcodeToFile(getRef(), file.getAbsolutePath()) != 0) {
            throw new LlvmException("Write failed");
        }
    }
    
    public void link(Module other) {
        StringOut errorMessage = new StringOut();
        if (LLVM.LinkModules(getRef(), other.getRef(), 0, errorMessage)) {
            throw new LlvmException(errorMessage.getValue().trim());
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
        if (!LLVM.ParseIRInContext(context.getRef(), memoryBufferRef, moduleRefOut, errorMessage)) {
            return new Module(moduleRefOut.getValue());
        }
        throw new LlvmException(errorMessage.getValue().trim());
    }

    public static Module parseClangString(Context context, String buffer, String fileName, String triple) {
        StringOut errorMessage = new StringOut();
        ModuleRef ref = LLVM.ClangCompileFile(context.getRef(), buffer, fileName, triple, errorMessage);
        if (ref != null) {
            return new Module(ref);
        }
        throw new LlvmException(errorMessage.getValue().trim());
    }
}
