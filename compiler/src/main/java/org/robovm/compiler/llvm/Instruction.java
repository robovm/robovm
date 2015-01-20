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
package org.robovm.compiler.llvm;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;

/**
 *
 * @version $Id$
 */
public abstract class Instruction {
    BasicBlock basicBlock;
    private List<Metadata> metadata;
    private List<Object> attachments;

    public BasicBlock getBasicBlock() {
        return basicBlock;
    }
    
    public Set<Variable> getWritesTo() {
        return Collections.emptySet();
    }
    
    public Set<VariableRef> getReadsFrom() {
        return Collections.emptySet();
    }
    
    public List<Metadata> getMetadata() {
        return metadata == null ? Collections.<Metadata>emptyList() : metadata;
    }
    
    public Instruction addMetadata(Metadata md) {
        if (metadata == null) {
            metadata = new ArrayList<>();
        }
        metadata.add(md);
        return this;
    }
    
    public Instruction attach(Object o) {
        if (attachments == null) {
            attachments = new ArrayList<>();
        }
        attachments.add(o);
        return this;
    }
    
    public List<Object> getAttachments() {
        return attachments == null ? Collections.emptyList() : attachments;
    }
    
    @SuppressWarnings("unchecked")
    public <T> T getAttachment(Class<T> cls) {
        for (Object o : getAttachments()) {
            if (cls.isInstance(o)) {
                return (T) o;
            }
        }
        return null;
    }
    
    public Set<BasicBlockRef> getBranchTargets() {
        return Collections.emptySet();
    }
}
