/*
 * Copyright (C) 2014 RoboVM AB
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
import org.robovm.llvm.binding.SectionIteratorRef;

/**
 * 
 */
public class SectionIterator implements AutoCloseable {
    protected ObjectFile objectFile;
    protected SectionIteratorRef ref;

    SectionIterator(ObjectFile objectFile, SectionIteratorRef ref) {
        this.objectFile = objectFile;
        this.ref = ref;
    }

    protected final void checkDisposed() {
        if (ref == null) {
            throw new LlvmException("Already disposed");
        }
    }
    
    public synchronized void dispose() {
        LLVM.DisposeSectionIterator(getRef());
        ref = null;
    }
    
    @Override
    public void close() {
        dispose();
    }
    
    protected SectionIteratorRef getRef() {
        return ref;
    }
    
    public String getName() {
        return LLVM.GetSectionName(getRef());
    }

    public long getAddress() {
        return LLVM.GetSectionAddress(getRef());
    }

    public long getSize() {
        return LLVM.GetSectionSize(getRef());
    }

    public long copyContents(byte[] dest) {
        return LLVM.CopySectionContents(getRef(), dest);
    }
    
    public void next() {
        LLVM.MoveToNextSection(getRef());
    }
    
    public boolean hasNext() {
        return !LLVM.IsSectionIteratorAtEnd(objectFile.getRef(), getRef());
    }
}
