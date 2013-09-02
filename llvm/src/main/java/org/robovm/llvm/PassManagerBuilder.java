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

import org.robovm.llvm.binding.LLVM;
import org.robovm.llvm.binding.PassManagerBuilderRef;

/**
 * 
 */
public class PassManagerBuilder {
    protected PassManagerBuilderRef ref;

    public PassManagerBuilder() {
        ref = LLVM.PassManagerBuilderCreate();
        if (ref == null) {
            throw new LlvmException("Failed to create PassManagerBuilder");
        }
    }

    protected final void checkDisposed() {
        if (ref == null) {
            throw new LlvmException("Already disposed");
        }
    }

    public synchronized void dispose() {
        checkDisposed();
        LLVM.PassManagerBuilderDispose(ref);
        ref = null;
    }

    public void setDisableSimplifyLibCalls(boolean b) {
        checkDisposed();
        LLVM.PassManagerBuilderSetDisableSimplifyLibCalls(ref, b);
    }

    public void setDisableUnitAtATime(boolean b) {
        checkDisposed();
        LLVM.PassManagerBuilderSetDisableUnitAtATime(ref, b);
    }

    public void setDisableUnrollLoops(boolean b) {
        checkDisposed();
        LLVM.PassManagerBuilderSetDisableUnrollLoops(ref, b);
    }

    public void setSetOptLevel(int level) {
        checkDisposed();
        LLVM.PassManagerBuilderSetOptLevel(ref, level);
    }

    public void setSetSizeLevel(int level) {
        checkDisposed();
        LLVM.PassManagerBuilderSetSizeLevel(ref, level);
    }

    public void useInlinerWithThreshold(int threshold) {
        checkDisposed();
        LLVM.PassManagerBuilderUseInlinerWithThreshold(ref, threshold);
    }

    public void populateFunctionPassManager(PassManager passManager) {
        checkDisposed();
        LLVM.PassManagerBuilderPopulateFunctionPassManager(ref, passManager.ref);
    }

    public void populateModulePassManager(PassManager passManager) {
        checkDisposed();
        LLVM.PassManagerBuilderPopulateModulePassManager(ref, passManager.ref);
    }

    public void populateLTOPassManager(PassManager passManager, boolean internalize, boolean runInliner) {
        checkDisposed();
        LLVM.PassManagerBuilderPopulateLTOPassManager(ref, passManager.ref, internalize, runInliner);
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
        PassManagerBuilder other = (PassManagerBuilder) obj;
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
