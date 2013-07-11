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

import java.io.OutputStream;

import org.robovm.llvm.binding.CodeGenFileType;
import org.robovm.llvm.binding.LLVM;
import org.robovm.llvm.binding.MemoryBufferRef;
import org.robovm.llvm.binding.StringOut;
import org.robovm.llvm.binding.TargetMachineRef;

/**
 * 
 */
public class TargetMachine {
    protected TargetMachineRef ref;

    TargetMachine(TargetMachineRef ref) {
        this.ref = ref;
    }
    
    protected final void checkDisposed() {
        if (ref == null) {
            throw new LlvmException("Already disposed");
        }
    }
    
    public synchronized void dispose() {
        checkDisposed();
        LLVM.DisposeTargetMachine(ref);
        ref = null;
    }

    public Target getTarget() {
        checkDisposed();
        return new Target(LLVM.GetTargetMachineTarget(ref));
    }
    
    public DataLayout getDataLayout() {
        checkDisposed();
        return new DataLayout(LLVM.GetTargetMachineData(ref));
    }
    
    public TargetOptions getOptions() {
        return new TargetOptions(LLVM.GetTargetMachineTargetOptions(ref));
    }
    
    public String getTriple() {
        checkDisposed();
        return LLVM.GetTargetMachineTriple(ref);
    }

    public String getCPU() {
        checkDisposed();
        return LLVM.GetTargetMachineCPU(ref);
    }
    
    public String getFeatureString() {
        checkDisposed();
        return LLVM.GetTargetMachineFeatureString(ref);
    }
    
    public boolean hasMCRelaxAll() {
        return LLVM.TargetMachineHasMCRelaxAll(ref);
    }

    public void setMCRelaxAll(boolean value) {
        LLVM.TargetMachineSetMCRelaxAll(ref, value);
    }

    public boolean hasMCSaveTempLabels() {
        return LLVM.TargetMachineHasMCSaveTempLabels(ref);
    }

    public void setMCSaveTempLabels(boolean value) {
        LLVM.TargetMachineSetMCSaveTempLabels(ref, value);
    }

    public boolean hasMCNoExecStack() {
        return LLVM.TargetMachineHasMCNoExecStack(ref);
    }

    public void setMCNoExecStack(boolean value) {
        LLVM.TargetMachineSetMCNoExecStack(ref, value);
    }

    public boolean hasMCUseLoc() {
        return LLVM.TargetMachineHasMCUseLoc(ref);
    }

    public void setMCUseLoc(boolean value) {
        LLVM.TargetMachineSetMCUseLoc(ref, value);
    }

    public boolean hasMCUseCFI() {
        return LLVM.TargetMachineHasMCUseCFI(ref);
    }

    public void setMCUseCFI(boolean value) {
        LLVM.TargetMachineSetMCUseCFI(ref, value);
    }

    public boolean hasMCUseDwarfDirectory() {
        return LLVM.TargetMachineHasMCUseDwarfDirectory(ref);
    }

    public void setMCUseDwarfDirectory(boolean value) {
        LLVM.TargetMachineSetMCUseDwarfDirectory(ref, value);
    }

    public boolean getAsmVerbosityDefault() {
        return LLVM.TargetMachineGetAsmVerbosityDefault(ref);
    }

    public void setAsmVerbosityDefault(boolean value) {
        LLVM.TargetMachineSetAsmVerbosityDefault(ref, value);
    }

    public boolean getDataSections() {
        return LLVM.TargetMachineGetDataSections(ref);
    }

    public boolean getFunctionSections() {
        return LLVM.TargetMachineGetFunctionSections(ref);
    }

    public void setDataSections(boolean value) {
        LLVM.TargetMachineSetDataSections(ref, value);
    }

    public void setFunctionSections(boolean value) {
        LLVM.TargetMachineSetFunctionSections(ref, value);
    }
    
    public void emit(Module module, OutputStream out, CodeGenFileType fileType) {
        checkDisposed();
        module.checkDisposed();
        StringOut ErrorMessage = new StringOut();
        if (LLVM.TargetMachineEmitToOutputStream(ref, module.ref, out, fileType, ErrorMessage)) {
            // Returns true on failure!
            throw new LlvmException(ErrorMessage.getValue().trim());
        }
    }
    
    public void assemble(byte[] asm, String filename, OutputStream out) {
        MemoryBufferRef memoryBufferRef = LLVM.CreateMemoryBufferWithMemoryRangeCopy(asm, filename);
        if (memoryBufferRef == null) {
            throw new LlvmException("Failed to create memory buffer");
        }
        filename = filename == null ? "" : filename;
        StringOut errorMessage = new StringOut();
        // LLVMTargetMachineAssembleToOutputStream() takes ownership of the MemoryBuffer so there's no need for us
        // to dispose of it
        if (LLVM.TargetMachineAssembleToOutputStream(ref, memoryBufferRef, out, false, false, errorMessage) != 0) {
            String error = errorMessage.getValue() != null 
                    ? errorMessage.getValue().trim() 
                    : "Unknown error";
            throw new LlvmException(error);
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
        TargetMachine other = (TargetMachine) obj;
        if (ref == null) {
            if (other.ref != null) {
                return false;
            }
        } else if (!ref.equals(other.ref)) {
            return false;
        }
        return true;
    }
}
