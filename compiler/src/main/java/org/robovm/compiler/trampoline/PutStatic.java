/*
 * Copyright (C) 2012 Trillian AB
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
package org.robovm.compiler.trampoline;

import static org.robovm.compiler.Types.*;
import static org.robovm.compiler.llvm.Type.*;

import org.robovm.compiler.llvm.FunctionType;


/**
 *
 * @version $Id$
 */
public class PutStatic extends FieldAccessor {
    private static final long serialVersionUID = 1L;
    
    public PutStatic(String callingClass, String targetClass, String fieldName, String fieldDesc) {
        super(callingClass, targetClass, fieldName, fieldDesc);
    }

    @Override
    public boolean isGetter() {
        return false;
    }
    
    @Override
    public boolean isStatic() {
        return true;
    }

    @Override
    public FunctionType getFunctionType() {
        return new FunctionType(VOID, ENV_PTR, getType(fieldDesc));
    }
}
