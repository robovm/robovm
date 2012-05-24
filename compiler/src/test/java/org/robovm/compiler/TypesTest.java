/**
 * 
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
