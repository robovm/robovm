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
package org.robovm.compiler.llvm;

import org.robovm.llvm.Context;
import org.robovm.llvm.Module;
import org.robovm.llvm.Target;
import org.robovm.llvm.TargetMachine;

/**
 *
 * @version $Id$
 */
public class DataLayout {
    
    private final String triple;
    private final Target target;
    
    public DataLayout(String triple) {
        this.triple = triple;
        this.target = Target.lookupTarget(triple);
    }

    private org.robovm.llvm.Type getTypeRef(Type type) {
        Context context = null;
        Module module = null;
        try {
            String definition;
            if (type instanceof PrimitiveType) {
                definition = "{" + ((PrimitiveType) type).getName() + "}";
            } else if (type instanceof StructureType) {
                definition = ((StructureType) type).getDefinition();
            } else {
                definition = "{" + ((UserType) type).getDefinition() + "}";
            }
            context = new Context();
            module = Module.parseIR(context, "%t = type " + definition, null);
            return module.getTypeByName("t");
        } finally {
            if (module != null) {
                module.dispose();
            }
            if (context != null) {
                context.dispose();
            }
        }
    }
    
    public int getAllocSize(Type type) {
        TargetMachine targetMachine = null;
        try {
            targetMachine = target.createTargetMachine(triple);
            return (int) targetMachine.getDataLayout().getTypeAllocSize(getTypeRef(type));
        } finally {
            if (targetMachine != null) {
                targetMachine.dispose();
            }
        }
    }

    public int getAlignment(Type type) {
        TargetMachine targetMachine = null;
        try {
            targetMachine = target.createTargetMachine(triple);
            return (int) targetMachine.getDataLayout().getABITypeAlignment(getTypeRef(type));
        } finally {
            if (targetMachine != null) {
                targetMachine.dispose();
            }
        }
    }
    
    public int getStoreSize(Type type) {
        TargetMachine targetMachine = null;
        try {
            targetMachine = target.createTargetMachine(triple);
            return (int) targetMachine.getDataLayout().getTypeStoreSize(getTypeRef(type));
        } finally {
            if (targetMachine != null) {
                targetMachine.dispose();
            }
        }
    }
}
