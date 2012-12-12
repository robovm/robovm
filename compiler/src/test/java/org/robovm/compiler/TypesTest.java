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
package org.robovm.compiler;

import static org.junit.Assert.*;
import static org.robovm.compiler.Types.*;
import static org.robovm.compiler.llvm.Type.*;

import org.junit.Test;
import org.robovm.compiler.llvm.FunctionType;

/**
 * @author niklas
 *
 */
public class TypesTest {

    @Test
    public void testGetFunctionTypeFromDescriptor() {
        FunctionType type = null;
        
        type = getFunctionType("()V", true);
        assertEquals(VOID, type.getReturnType());
        assertEquals(1, type.getParameterTypes().length);
        assertEquals(ENV_PTR, type.getParameterTypes()[0]);
        
        type = getFunctionType("()V", false);
        assertEquals(VOID, type.getReturnType());
        assertEquals(2, type.getParameterTypes().length);
        assertEquals(ENV_PTR, type.getParameterTypes()[0]);
        assertEquals(OBJECT_PTR, type.getParameterTypes()[1]);
        
        type = getFunctionType("(Ljava/lang/Object;)Ljava/lang/String;", true);
        assertEquals(OBJECT_PTR, type.getReturnType());
        assertEquals(2, type.getParameterTypes().length);
        assertEquals(ENV_PTR, type.getParameterTypes()[0]);
        assertEquals(OBJECT_PTR, type.getParameterTypes()[1]);
        
        type = getFunctionType("(Ljava/lang/Object;)Ljava/lang/String;", false);
        assertEquals(OBJECT_PTR, type.getReturnType());
        assertEquals(3, type.getParameterTypes().length);
        assertEquals(ENV_PTR, type.getParameterTypes()[0]);
        assertEquals(OBJECT_PTR, type.getParameterTypes()[1]);
        assertEquals(OBJECT_PTR, type.getParameterTypes()[2]);
    }

}
