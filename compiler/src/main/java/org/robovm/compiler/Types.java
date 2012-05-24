/*
 * Copyright (C) 2012 RoboVM
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

import static org.robovm.compiler.Annotations.*;
import static org.robovm.compiler.llvm.Type.*;

import java.nio.CharBuffer;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.robovm.compiler.llvm.AggregateType;
import org.robovm.compiler.llvm.ArrayType;
import org.robovm.compiler.llvm.Bitcast;
import org.robovm.compiler.llvm.Constant;
import org.robovm.compiler.llvm.ConstantAdd;
import org.robovm.compiler.llvm.ConstantAnd;
import org.robovm.compiler.llvm.ConstantGetelementptr;
import org.robovm.compiler.llvm.ConstantPtrtoint;
import org.robovm.compiler.llvm.ConstantSub;
import org.robovm.compiler.llvm.ConstantXor;
import org.robovm.compiler.llvm.Function;
import org.robovm.compiler.llvm.FunctionType;
import org.robovm.compiler.llvm.Getelementptr;
import org.robovm.compiler.llvm.IntegerConstant;
import org.robovm.compiler.llvm.IntegerType;
import org.robovm.compiler.llvm.NullConstant;
import org.robovm.compiler.llvm.OpaqueType;
import org.robovm.compiler.llvm.PointerType;
import org.robovm.compiler.llvm.StructureType;
import org.robovm.compiler.llvm.Type;
import org.robovm.compiler.llvm.Value;
import org.robovm.compiler.llvm.Variable;

import soot.BooleanType;
import soot.ByteType;
import soot.CharType;
import soot.FloatType;
import soot.IntType;
import soot.LongType;
import soot.PrimType;
import soot.RefType;
import soot.ShortType;
import soot.SootClass;
import soot.SootField;
import soot.SootMethod;
import soot.SootMethodRef;
import soot.VoidType;
import soot.jimple.toolkits.typing.fast.BottomType;

/**
 * @author niklas
 *
 */
public class Types {

    public static final StructureType GATEWAY_FRAME = new StructureType("GatewayFrame", I8_PTR, I8_PTR, I8_PTR);
    public static final Type GATEWAY_FRAME_PTR = new PointerType(GATEWAY_FRAME);
    public static final Type ENV_PTR = new PointerType(new StructureType("Env", I8_PTR, I8_PTR, I8_PTR, 
            I8_PTR, I8_PTR, I8_PTR, I8_PTR, I8_PTR, I32));
    // Dummy Class type definition. The real one is in header.ll
    public static final StructureType CLASS = new StructureType("Class", I8_PTR);
    public static final Type CLASS_PTR = new PointerType(CLASS);
    // Dummy Object type definition. The real one is in header.ll
    public static final StructureType OBJECT = new StructureType("Object", CLASS_PTR);
    public static final Type OBJECT_PTR = new PointerType(OBJECT);
    public static final Type METHOD_PTR = new PointerType(new OpaqueType("Method"));
    public static final Type FIELD_PTR = new PointerType(new OpaqueType("Field"));
    
    public static Type getType(String desc) {
        switch (desc.charAt(0)) {
        case 'Z': return I8;
        case 'B': return I8;
        case 'S': return I16;
        case 'C': return I16;
        case 'I': return I32;
        case 'J': return I64;
        case 'F': return FLOAT;
        case 'D': return DOUBLE;
        case 'V': return VOID;
        case 'L': return OBJECT_PTR;
        case '[': return OBJECT_PTR;
        }
        throw new IllegalArgumentException();
    }
    
    private static Type getType(CharBuffer desc) {
        switch (desc.get()) {
        case 'Z': return I8;
        case 'B': return I8;
        case 'S': return I16;
        case 'C': return I16;
        case 'I': return I32;
        case 'J': return I64;
        case 'F': return FLOAT;
        case 'D': return DOUBLE;
        case 'V': return VOID;
        case 'L':
            while (desc.get() != ';')
                ;
            return OBJECT_PTR;
        case '[':
            getType(desc);
            return OBJECT_PTR;
        }
        throw new IllegalArgumentException();
    }
    
    public static Type getLocalType(soot.Type sootType) {
        Type t = getType(sootType);
        if (t instanceof IntegerType && ((IntegerType) t).getBits() < 32) {
            return I32;
        }
        return t;
    }
    
    public static Type getType(soot.Type sootType) {
        if (sootType.equals(soot.BooleanType.v())) {
            return Type.I8;
        } else if (sootType.equals(soot.ByteType.v())) {
            return Type.I8;
        } else if (sootType.equals(soot.ShortType.v())) {
            return Type.I16;
        } else if (sootType.equals(soot.CharType.v())) {
            return Type.I16;
        } else if (sootType.equals(soot.IntType.v())) {
            return Type.I32;
        } else if (sootType.equals(soot.LongType.v())) {
            return Type.I64;
        } else if (sootType.equals(soot.FloatType.v())) {
            return Type.FLOAT;
        } else if (sootType.equals(soot.DoubleType.v())) {
            return Type.DOUBLE;
        } else if (sootType.equals(soot.VoidType.v())) {
            return Type.VOID;
        } else if (sootType instanceof soot.RefLikeType || sootType.equals(BottomType.v())) {
            return OBJECT_PTR;
        } else {
            throw new IllegalArgumentException("Unknown Type: " + sootType);
        }
    }
    
    public static boolean isUnsigned(soot.Type type) {
        return type.equals(CharType.v());
    }

    public static String getInternalName(soot.Type t) {
        if (t instanceof soot.ArrayType) {
            return getDescriptor(t);
        } else if (t instanceof soot.RefType) {
            RefType rt = (RefType) t;
            return rt.getClassName().replace('.', '/');
        } else {
            throw new IllegalArgumentException();
        }
    }
    
    public static String getInternalName(SootClass sc) {
        return sc.getName().replace('.', '/');
    }
    
    public static String getDescriptor(soot.Type t) {
        if (t instanceof PrimType) {
            if (t.equals(BooleanType.v())) {
                return "Z";
            } else if (t.equals(ByteType.v())) {
                return "B";
            } else if (t.equals(ShortType.v())) {
                return "S";
            } else if (t.equals(CharType.v())) {
                return "C";
            } else if (t.equals(IntType.v())) {
                return "I";
            } else if (t.equals(LongType.v())) {
                return "J";
            } else if (t.equals(FloatType.v())) {
                return "F";
            } else {
                // DoubleType
                return "D";
            }
        } else if (t.equals(VoidType.v())) {
            return "V";
        } else if (t instanceof soot.ArrayType) {
            soot.ArrayType at = (soot.ArrayType) t;
            return "[" + getDescriptor(at.getElementType());
        } else {
            // RefType
            RefType rt = (RefType) t;
            return "L" + rt.getClassName().replace('.', '/') + ";";
        }
    }
    
    public static String getDescriptor(SootField field) {
        return getDescriptor(field.getType());
    }
    
    public static String getDescriptor(SootMethod method) {
        return getDescriptor(method.makeRef());
    }
    
    @SuppressWarnings("unchecked")
    public static String getDescriptor(SootMethodRef methodRef) {
        StringBuilder sb = new StringBuilder();
        sb.append('(');
        for (soot.Type t : (List<soot.Type>) methodRef.parameterTypes()) {
            sb.append(getDescriptor(t));
        }
        sb.append(')');
        sb.append(getDescriptor(methodRef.returnType()));
        return sb.toString();
    }
    
    public static boolean isArray(String descriptor) {
        return descriptor.charAt(0) == '[';
    }
    
    public static boolean isPrimitiveComponentType(String descriptor) {
        if (!isArray(descriptor)) {
            throw new IllegalArgumentException("Not an array");
        }
        return descriptor.length() == 2;
    }
    
    public static boolean isPrimitiveBaseType(String descriptor) {
        if (!isArray(descriptor)) {
            throw new IllegalArgumentException("Not an array");
        }
        return descriptor.charAt(descriptor.length() - 1) != ';';
    }
    
    public static String getBaseType(String descriptor) {
        if (!isArray(descriptor) || descriptor.charAt(descriptor.length() - 1) != ';') {
            throw new IllegalArgumentException("Not an array or base type is primitive");
        }
        int start = descriptor.lastIndexOf('[');
        if (start == -1) {
            return descriptor;
        }
        if (descriptor.charAt(start + 1) == 'L') {
            return descriptor.substring(start + 2, descriptor.length() - 1);
        } else {
            return String.valueOf(descriptor.charAt(start + 1));
        }
    }
    
    public static FunctionType getFunctionType(SootMethod method) {
        return getFunctionType(method.makeRef());
    }
    
    @SuppressWarnings("unchecked")
    public static FunctionType getFunctionType(SootMethodRef methodRef) {
        Type returnType = getType(methodRef.returnType());
        Type[] paramTypes = new Type[(methodRef.isStatic() ? 1 : 2) + methodRef.parameterTypes().size()];
        int i = 0;
        paramTypes[i++] = ENV_PTR;
        if (!methodRef.isStatic()) {
            paramTypes[i++] = OBJECT_PTR;
        }
        for (soot.Type t : (List<soot.Type>) methodRef.parameterTypes()) {
            paramTypes[i++] = getType(t);
        }
        return new FunctionType(returnType, paramTypes);
    }

    public static FunctionType getFunctionType(String methodDesc, boolean ztatic) {
        return getFunctionType(methodDesc, ztatic, false);
    }
    
    public static FunctionType getNativeFunctionType(String methodDesc, boolean ztatic) {
        return getFunctionType(methodDesc, ztatic, true);
    }
    
    private static FunctionType getFunctionType(String methodDesc, boolean ztatic, boolean nativ) {
        List<Type> paramTypes = new ArrayList<Type>();
        paramTypes.add(ENV_PTR);
        if (!ztatic) {
            paramTypes.add(OBJECT_PTR);
        } else if (nativ) {
            paramTypes.add(OBJECT_PTR);
        }
        CharBuffer buffer = CharBuffer.wrap(methodDesc);
        buffer.get(); // Skip initial (
        while (buffer.get(buffer.position()) != ')') {
            paramTypes.add(getType(buffer));
        }
        buffer.get(); // Skip ending )
        Type returnType = getType(buffer);
        return new FunctionType(returnType, paramTypes.toArray(new Type[paramTypes.size()]));
    }
    
    public static FunctionType getBridgeFunctionType(SootMethod method) {
        return getBridgeOrCallbackFunctionType("@Bridge", method);
    }
    
    public static FunctionType getCallbackFunctionType(SootMethod method) {
        return getBridgeOrCallbackFunctionType("@Callback", method);
    }
    
    @SuppressWarnings("unchecked")
    private static FunctionType getBridgeOrCallbackFunctionType(String anno, SootMethod method) {
        soot.Type sootRetType = method.getReturnType();
        Type returnType = isStruct(sootRetType) ? getStructType(sootRetType) : getType(sootRetType);
        if (hasPointerAnnotation(method)) {
            if (!sootRetType.equals(LongType.v()) && !isStruct(sootRetType)) {
                throw new IllegalArgumentException(anno + " annotated method " 
                        + method.getName() + " must return long or Struct when annotated with @Pointer");
            }
            returnType = I8_PTR;
        }
        
        Type[] paramTypes = new Type[method.getParameterTypes().size()];
        int i = 0;
        for (soot.Type t : (List<soot.Type>) method.getParameterTypes()) {
            paramTypes[i++] = isStruct(t) ? new PointerType(getStructType(t)) : getType(t);
        }
        
        for (i = 0; i < paramTypes.length; i++) {
            if (hasStructRetAnnotation(method, i)) {
                if (i > 0) {
                    throw new IllegalArgumentException("Parameter " + (i + 1) 
                            + " of " + anno + " annotated method " 
                            + method.getName() 
                            + " cannot be annotated with @StructRet. Only first parameter can have this annotation.");
                }
                if (!hasPointerAnnotation(method, i)) {
                    throw new IllegalArgumentException("Parameter " + (i + 1) 
                            + " of " + anno + " annotated method " 
                            + method.getName() 
                            + " must be annotated with @Pointer when annotated with @StructRet.");
                }
            }
            soot.Type t = method.getParameterType(i);
            if (hasPointerAnnotation(method, i)) {
                if (!t.equals(LongType.v()) && !isStruct(t)) {
                    throw new IllegalArgumentException("Parameter " + (i + 1) 
                            + " of " + anno + " annotated method " 
                            + method.getName() 
                            + " must be of type long or Struct when annotated with @Pointer");
                }
                paramTypes[i] = I8_PTR;
            }
        }
        
        return new FunctionType(returnType, paramTypes);
    }
    
    public static StructureType getStructType(soot.Type t) {
        return getStructType(((RefType) t).getSootClass(), null);                
    }
    
    public static StructureType getStructType(SootClass clazz) {
        return getStructType(clazz, null);                
    }
    
    public static StructureType getStructType(SootClass clazz, Map<String, Integer> indexes) {
        List<Type> types = new ArrayList<Type>();
        Map<String, Type> members = new HashMap<String, Type>();
        int index = 0;
        for (SootMethod method : clazz.getMethods()) {
            if (isStructMember(method)) {
                
                boolean getter = method.getName().startsWith("get");
                boolean setter = method.getName().startsWith("set");
                if (!method.isNative() && !method.isStatic()) {
                    throw new IllegalArgumentException("@StructMember annotated method must be native and not static");
                }
                if (!getter && !setter || method.getName().length() == 3) {
                    throw new IllegalArgumentException("@StructMember annotated method has invalid name");
                }
                if (getter && method.getParameterCount() != 0) {
                    throw new IllegalArgumentException("@StructMember annotated getter method must have no arguments");
                }
                if (setter && method.getParameterCount() != 1) {
                    throw new IllegalArgumentException("@StructMember annotated setter method must take a single argument");
                }
                soot.Type sootType = getter ? method.getReturnType() : method.getParameterType(0);
                if (!(sootType instanceof PrimType || sootType instanceof RefType && isStruct(sootType))) {
                    if (getter) {
                        throw new IllegalArgumentException("@StructMember annotated getter method must return primitive or Struct type");
                    } else {
                        throw new IllegalArgumentException("@StructMember annotated setter method must take a single primitive or Struct type argument");
                    }
                }
                
                String name = method.getName().substring(3, 4).toLowerCase() + method.getName().substring(4);
                Type type = sootType instanceof PrimType ? getType(sootType) : I8_PTR;
                if (getter) {
                    if (hasPointerAnnotation(method)) {
                        if (!sootType.equals(LongType.v()) && !isStruct(sootType)) {
                            throw new IllegalArgumentException("@StructMember annotated getter method " 
                                    + method.getName() + " must return long or Struct when annotated with @Pointer");
                        }
                        type = I8_PTR; // NOTE: We use i8* instead of <StructType>* to support pointers to recursive structs 
                    } else if (isStruct(sootType)) {
                        try {
                            type = getStructType(sootType);
                        } catch (StackOverflowError e) {
                            throw new IllegalArgumentException("Struct type " + sootType + " refers to itself");
                        }
                    }
                } else {
                    if (hasPointerAnnotation(method, 0)) {
                        if (!sootType.equals(LongType.v()) && !isStruct(sootType)) {
                            throw new IllegalArgumentException("First parameter of @StructMember annotated setter method " 
                                    + method.getName() 
                                    + " must be of type long or Struct when annotated with @Pointer");
                        }
                        type = I8_PTR;
                    } else if (isStruct(sootType)) {
                        try {
                            type = getStructType(sootType);
                        } catch (StackOverflowError e) {
                            throw new IllegalArgumentException("Struct type " + sootType + " refers to itself");
                        }
                    }
                }
                if (members.containsKey(name)) { 
                    if (!members.get(name).equals(type)) {
                        throw new IllegalArgumentException("@StructMember annotated getter and setter methods for property " 
                                + name + " have different types");
                    }
                } else {
                    members.put(name, type);
                    types.add(type);
                    if (indexes != null) {
                        indexes.put(name, index);
                    }
                    index++;
                }
            }
        }
        if (!types.isEmpty()) {
            return new StructureType(types.toArray(new Type[types.size()]));
        }
        return null;
    }
    
    
    public static boolean isStruct(soot.Type t) {
        if (t instanceof RefType) {
            return isStruct(((RefType) t).getSootClass());
        }
        return false;
    }
    
    public static boolean isStruct(SootClass sc) {
        SootClass clazz = sc;
        while (clazz.hasSuperclass()) {
            clazz = clazz.getSuperclass();
            if ("org.robovm.rt.bro.Struct".equals(clazz.getName())) {
                return true;
            }
        }
        return false;
    }
    
    public static boolean isBridge(SootMethod sm) {
        return hasBridgeAnnotation(sm);
    }
    
    public static boolean isCallback(SootMethod sm) {
        return hasCallbackAnnotation(sm);
    }
    
    public static boolean isStructMember(SootMethod sm) {
        return hasStructMemberAnnotation(sm);
    }
    
    public static Constant sizeof(AggregateType type) {
        return new ConstantPtrtoint(
                new ConstantGetelementptr(new NullConstant(
                        new PointerType(type)), 1), I32);
    }
    
    public static Constant offsetof(AggregateType type, int ... idx) {
        int[] i = new int[idx.length + 1];
        i[0] = 0;
        System.arraycopy(idx, 0, i, 1, idx.length);
        return new ConstantPtrtoint(
                new ConstantGetelementptr(new NullConstant(
                        new PointerType(type)), i), I32);
    }
    
    public static Constant alignedSizeof(AggregateType type) {
        // return (sizeof(type) + sizeof(i8*) - 1) & ~(sizeof(i8*) - 1);
        return new ConstantAnd(
                new ConstantAdd(
                        sizeof(type), 
                        new ConstantSub(sizeof(new ArrayType(1, I8_PTR)), new IntegerConstant(1))), 
                new ConstantXor(
                        new ConstantSub(sizeof(new ArrayType(1, I8_PTR)), new IntegerConstant(1)), 
                        new IntegerConstant(-1)));
    }
    
    public static Constant alignedOffset(SootClass clazz) {
        Constant offset = new IntegerConstant(0);
        if (!clazz.isInterface() && clazz.hasSuperclass()) {
            SootClass zuper = clazz.getSuperclass();
            if (!zuper.isPhantom() && !"java.lang.Object".equals(clazz.getSuperclass().getName())) {
                List<Type> types = new ArrayList<Type>();
                for (SootField f : zuper.getFields()) {
                    if (!f.isStatic()) {
                        types.add(getType(f.getType()));
                    }
                }
                if (types.size() > 0) {
                    offset = alignedSizeof(new StructureType(types.toArray(new Type[types.size()])));
                }
                offset = new ConstantAdd(alignedOffset(zuper), offset);
            }
        }
        return offset;
    }
    
    public static Value getFieldPtr(Function f, Value base, Constant offset, Type fieldType) {
        Variable baseI8Ptr = f.newVariable(I8_PTR);
        f.add(new Bitcast(baseI8Ptr, base, I8_PTR));
        Variable fieldI8Ptr = f.newVariable(I8_PTR);
        f.add(new Getelementptr(fieldI8Ptr, baseI8Ptr.ref(), offset));
        Variable fieldPtr = f.newVariable(new PointerType(fieldType));
        f.add(new Bitcast(fieldPtr, fieldI8Ptr.ref(), fieldPtr.getType()));
        return fieldPtr.ref();
    }
}
