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

import org.robovm.llvm.binding.ContextRef;
import org.robovm.llvm.binding.LLVM;

/**
 * 
 */
public class Context implements AutoCloseable {
    private ContextRef ref;

    public Context() {
        ref = LLVM.ContextCreate();
        if (ref == null) {
            throw new LlvmException("Failed to create Context");
        }
    }

    private Context(ContextRef contextRef) {
        this.ref = contextRef;
    }
    
    protected final void checkDisposed() {
        if (ref == null) {
            throw new LlvmException("Already disposed");
        }
    }
    
    protected ContextRef getRef() {
        checkDisposed();
        return ref;
    }

    public synchronized void dispose() {
        LLVM.ContextDispose(getRef());
        ref = null;
    }

    @Override
    public void close() {
        dispose();
    }
    
    public static Context getGlobalContext() {
        return new Context(LLVM.GetGlobalContext());
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
        Context other = (Context) obj;
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
