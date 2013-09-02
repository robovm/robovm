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
package org.robovm.compiler;

import static org.robovm.compiler.Types.*;
import static org.robovm.compiler.llvm.Type.*;

import java.util.HashMap;
import java.util.Map;

import org.robovm.compiler.llvm.FunctionRef;
import org.robovm.compiler.llvm.FunctionType;

import soot.SootFieldRef;
import soot.SootMethod;
import soot.SootMethodRef;
import soot.jimple.DefinitionStmt;
import soot.jimple.InvokeExpr;
import soot.jimple.StaticFieldRef;
import soot.jimple.Stmt;

/**
 * Contains intrinsic functions. These are functions that will replace calls
 * to specific methods in the runtime package to speed things up.
 */
public class Intrinsics {

    private static final Map<String, FunctionRef> SIMPLE_INTRINSICS;
    
    static {
        SIMPLE_INTRINSICS = new HashMap<String, FunctionRef>();
        SIMPLE_INTRINSICS.put("java/lang/Class/getSuperclass()Ljava/lang/Class;", 
                new FunctionRef("intrinsics.java_lang_Class_getSuperclass", 
                        new FunctionType(OBJECT_PTR, ENV_PTR, OBJECT_PTR)));
        SIMPLE_INTRINSICS.put("java/lang/Class/getComponentType()Ljava/lang/Class;", 
                new FunctionRef("intrinsics.java_lang_Class_getComponentType", 
                        new FunctionType(OBJECT_PTR, ENV_PTR, OBJECT_PTR)));
        SIMPLE_INTRINSICS.put("java/lang/Class/isArray()Z", 
                new FunctionRef("intrinsics.java_lang_Class_isArray", 
                        new FunctionType(I8, ENV_PTR, OBJECT_PTR)));
        SIMPLE_INTRINSICS.put("java/lang/Class/isPrimitive()Z", 
                new FunctionRef("intrinsics.java_lang_Class_isPrimitive", 
                        new FunctionType(I8, ENV_PTR, OBJECT_PTR)));
        SIMPLE_INTRINSICS.put("java/lang/Object/getClass()Ljava/lang/Class;", 
                new FunctionRef("intrinsics.java_lang_Object_getClass", 
                        new FunctionType(OBJECT_PTR, ENV_PTR, OBJECT_PTR)));
        SIMPLE_INTRINSICS.put("java/lang/Math/abs(F)F", 
                new FunctionRef("intrinsics.java_lang_Math_abs_F", 
                        new FunctionType(FLOAT, ENV_PTR, FLOAT)));
        SIMPLE_INTRINSICS.put("java/lang/Math/abs(D)D", 
                new FunctionRef("intrinsics.java_lang_Math_abs_D", 
                        new FunctionType(DOUBLE, ENV_PTR, DOUBLE)));
        SIMPLE_INTRINSICS.put("java/lang/Math/sqrt(D)D", 
                new FunctionRef("intrinsics.java_lang_Math_sqrt", 
                        new FunctionType(DOUBLE, ENV_PTR, DOUBLE)));
        SIMPLE_INTRINSICS.put("java/lang/Math/cos(D)D", 
                new FunctionRef("intrinsics.java_lang_Math_cos", 
                        new FunctionType(DOUBLE, ENV_PTR, DOUBLE)));
        SIMPLE_INTRINSICS.put("java/lang/Math/sin(D)D", 
                new FunctionRef("intrinsics.java_lang_Math_sin", 
                        new FunctionType(DOUBLE, ENV_PTR, DOUBLE)));
        SIMPLE_INTRINSICS.put("org/robovm/rt/VM/getArrayValuesAddress(Ljava/lang/Object;)J", 
                new FunctionRef("intrinsics.org_robovm_rt_VM_getArrayValuesAddress", 
                        new FunctionType(I64, ENV_PTR, OBJECT_PTR)));
    }
    
    private static final FunctionRef LDC_PRIM_Z = new FunctionRef("intrinsics.ldc_prim_Z", new FunctionType(OBJECT_PTR, ENV_PTR));
    private static final FunctionRef LDC_PRIM_B = new FunctionRef("intrinsics.ldc_prim_B", new FunctionType(OBJECT_PTR, ENV_PTR));
    private static final FunctionRef LDC_PRIM_C = new FunctionRef("intrinsics.ldc_prim_C", new FunctionType(OBJECT_PTR, ENV_PTR));
    private static final FunctionRef LDC_PRIM_S = new FunctionRef("intrinsics.ldc_prim_S", new FunctionType(OBJECT_PTR, ENV_PTR));
    private static final FunctionRef LDC_PRIM_I = new FunctionRef("intrinsics.ldc_prim_I", new FunctionType(OBJECT_PTR, ENV_PTR));
    private static final FunctionRef LDC_PRIM_J = new FunctionRef("intrinsics.ldc_prim_J", new FunctionType(OBJECT_PTR, ENV_PTR));
    private static final FunctionRef LDC_PRIM_F = new FunctionRef("intrinsics.ldc_prim_F", new FunctionType(OBJECT_PTR, ENV_PTR));
    private static final FunctionRef LDC_PRIM_D = new FunctionRef("intrinsics.ldc_prim_D", new FunctionType(OBJECT_PTR, ENV_PTR));
    
    public static FunctionRef getIntrinsic(SootMethod currMethod, Stmt stmt, InvokeExpr expr) {
        SootMethodRef methodRef = expr.getMethodRef();
        FunctionRef fref = SIMPLE_INTRINSICS.get(getInternalName(methodRef.declaringClass()) + "/" 
                                + methodRef.name() + getDescriptor(methodRef));
        if (fref != null) {
            return fref;
        }
        
        if (methodRef.name().startsWith("memmove") 
                && "org.robovm.rt.VM".equals(methodRef.declaringClass().getName())) {
            
            return new FunctionRef("intrinsics.org_robovm_rt_VM_" + methodRef.name(), 
                    new FunctionType(VOID, ENV_PTR, I64, I64, I64));
        }

        if ("arraycopy".equals(methodRef.name()) 
                && "java.lang.System".equals(methodRef.declaringClass().getName())
                && "_getChars".equals(currMethod.getName())
                && "java.lang.String".equals(currMethod.getDeclaringClass().getName())) {
            
            return new FunctionRef("intrinsics.java_lang_System_arraycopy_C", 
                    new FunctionType(VOID, ENV_PTR, OBJECT_PTR, I32, OBJECT_PTR, I32, I32));
        }

        return null;
    }

    public static FunctionRef getIntrinsic(SootMethod currMethod, DefinitionStmt stmt) {
        soot.Value rightOp = stmt.getRightOp();
        if (rightOp instanceof StaticFieldRef) {
            SootFieldRef fieldRef = ((StaticFieldRef) rightOp).getFieldRef();
            if ("TYPE".equals(fieldRef.name())) {
                String declClass = fieldRef.declaringClass().getName();
                if ("java.lang.Boolean".equals(declClass)) {
                    return LDC_PRIM_Z;
                }
                if ("java.lang.Byte".equals(declClass)) {
                    return LDC_PRIM_B;
                }
                if ("java.lang.Character".equals(declClass)) {
                    return LDC_PRIM_C;
                }
                if ("java.lang.Short".equals(declClass)) {
                    return LDC_PRIM_S;
                }
                if ("java.lang.Integer".equals(declClass)) {
                    return LDC_PRIM_I;
                }
                if ("java.lang.Long".equals(declClass)) {
                    return LDC_PRIM_J;
                }
                if ("java.lang.Float".equals(declClass)) {
                    return LDC_PRIM_F;
                }
                if ("java.lang.Double".equals(declClass)) {
                    return LDC_PRIM_D;
                }
            }
        }
        
        return null;
    }

}
