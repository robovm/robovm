/*
 * Copyright (C) 2013 RoboVM AB
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
import org.robovm.llvm.binding.TargetDataRef;

/**
 * 
 */
public class DataLayout implements AutoCloseable {
    protected TargetDataRef ref;

    DataLayout(TargetDataRef ref) {
        this.ref = ref;
    }
    
    protected final void checkDisposed() {
        if (ref == null) {
            throw new LlvmException("Already disposed");
        }
    }
    
    public synchronized void dispose() {
        checkDisposed();
        LLVM.DisposeTargetData(ref);
        ref = null;
    }

    @Override
    public void close() {
        dispose();
    }

    public long getTypeAllocSize(Type type) {
        checkDisposed();
        return LLVM.ABISizeOfType(ref, type.ref).longValue();
    }

    public int getABITypeAlignment(Type type) {
        checkDisposed();
        return LLVM.ABIAlignmentOfType(ref, type.ref);
    }

    public long getTypeStoreSize(Type type) {
        checkDisposed();
        return LLVM.StoreSizeOfType(ref, type.ref).longValue();
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
        DataLayout other = (DataLayout) obj;
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
