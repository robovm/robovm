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

import static org.robovm.compiler.Annotations.*;
import static org.robovm.compiler.Types.*;
import static org.robovm.compiler.llvm.Type.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.robovm.compiler.clazz.Clazz;
import org.robovm.compiler.config.Arch;
import org.robovm.compiler.config.Config;
import org.robovm.compiler.llvm.Type;
import org.robovm.compiler.trampoline.Invokestatic;

import soot.ArrayType;
import soot.BooleanType;
import soot.ByteType;
import soot.CharType;
import soot.DoubleType;
import soot.FloatType;
import soot.IntType;
import soot.LongType;
import soot.PrimType;
import soot.RefLikeType;
import soot.RefType;
import soot.Scene;
import soot.ShortType;
import soot.SootClass;
import soot.SootMethod;
import soot.SootResolver;
import soot.VoidType;
import soot.tagkit.AnnotationArrayElem;
import soot.tagkit.AnnotationClassElem;
import soot.tagkit.AnnotationElem;
import soot.tagkit.AnnotationLongElem;
import soot.tagkit.AnnotationTag;
import soot.tagkit.InnerClassTag;
import soot.tagkit.Tag;

/**
 * Looks up Bro marshalers.
 */
public class MarshalerLookup {
    private final String BUILTIN_MARSHALERS = "org/robovm/rt/bro/BuiltinMarshalers";
    
    private final Config config;
    private final Map<String, List<Marshaler>> cache = new HashMap<>();
    private boolean searchBuiltins = true;

    public MarshalerLookup(Config config) {
        this.config = config;
    }
    
    /**
     * Sets whether a call to {@link #findMarshalers(Clazz)} should include
     * the builtin marshalers.
     * 
     * @param b {@code true} to include builtins, {@code false} otherwise.
     * @return this {@link MarshalerLookup}.
     */
    public MarshalerLookup searchBuiltins(boolean b) {
        this.searchBuiltins = b;
        return this;
    }
    
    public Marshaler findMarshalers(MarshalSite marshalSite) {
        soot.Type type = marshalSite.getType();
        SootClass sc = null;
        if (type instanceof RefType) {
            sc = ((RefType) type).getSootClass();
        } else if (type instanceof ArrayType && ((ArrayType) type).baseType instanceof RefType) {
            sc = ((RefType) ((ArrayType) type).baseType).getSootClass();
        }
        
        List<Marshaler> result = new ArrayList<>();
        Set<String> visited = new HashSet<>();
        Set<String> seen = new HashSet<>();
        if (sc != null) {
            findMarshalers(sc, result, visited, seen, false);
        }
        findMarshalers(marshalSite.method.getDeclaringClass(), result, visited, seen, searchBuiltins);
        
        for (Marshaler marshaler : result) {
            if (marshaler.canMarshal(marshalSite)) {
                return marshaler;
            }
        }

        return null;
    }
    
    public List<Marshaler> findMarshalers(SootClass sc) {
        List<Marshaler> result = new ArrayList<>();
        Set<String> visited = new HashSet<>();
        Set<String> seen = new HashSet<>();
        findMarshalers(sc, result, visited, seen, searchBuiltins);
        return result;
    }
    
    private void findMarshalers(SootClass sc, List<Marshaler> result, 
            Set<String> visited, Set<String> seen, boolean searchBuiltins) {
        
        findMarshalersOnClasses(sc, result, visited, seen);
        findMarshalersOnInterfaces(sc, result, visited, seen);
        findMarshalersOnOuterClasses(sc, result, visited, seen);
        
        if (searchBuiltins) {
            Clazz builtinMarshalersClazz = config.getClazzes().load(BUILTIN_MARSHALERS);
            if (builtinMarshalersClazz != null) {
                findMarshalersOnClasses(builtinMarshalersClazz.getSootClass(), 
                        result, visited, seen);
            }
        }
    }

    public MarshalerMethod findMarshalerMethod(MarshalSite marshalSite) {
        int pidx = marshalSite.paramIdx;
        if (pidx != MarshalSite.RECEIVER) {
            // Use @Marshaler annotation on method or parameter at paramIndex if there is one 
            AnnotationTag anno = pidx == MarshalSite.RETURN_TYPE
                    ? getMarshalerAnnotation(marshalSite.method) 
                    : getMarshalerAnnotation(marshalSite.method, pidx);
            if (anno != null) {
                AnnotationClassElem elem = (AnnotationClassElem) getElemByName(anno, "value");
                String name = getInternalNameFromDescriptor(elem.getDesc());
                Clazz marshalerClazz = config.getClazzes().load(name);
                if (marshalerClazz != null) {
                    Marshaler marshaler = new Marshaler(marshalerClazz);
                    if (marshaler.canMarshal(marshalSite)) {
                        return marshaler.getMarshalerMethod(marshalSite);
                    }
                }
                throw new IllegalArgumentException(String.format(
                        "@Marshaler %s specified for %s of %s method %s can " 
                                + "not be used to marshal %s",
                        name.replace('/', '.'), 
                        (pidx == MarshalSite.RETURN_TYPE ? "return type" : "parameter " + (pidx + 1)), 
                        marshalSite.callTypeName, marshalSite.method, marshalSite.type));
            }
        }
        
        Marshaler marshaler = findMarshalers(marshalSite);
        if (marshaler != null) {
            return marshaler.getMarshalerMethod(marshalSite);
        }
        throw new IllegalArgumentException(String.format(
                "No @Marshaler found for %s of %s method %s",
                (pidx == MarshalSite.RECEIVER ? "receiver" 
                        : (pidx == MarshalSite.RETURN_TYPE ? "return type" 
                                : "parameter " + (pidx + 1))),
                marshalSite.callTypeName, marshalSite.method));
    }

    private void findMarshalersOnClasses(SootClass sc, List<Marshaler> result, 
            Set<String> visited, Set<String> seen) {

        SootResolver.v().bringToHierarchy(sc);
        
        result.addAll(findMarshalersOn(sc, visited, seen));
        if (sc.hasSuperclass()) {
            findMarshalersOnClasses(sc.getSuperclass(), result, visited, seen);
        }
    }
    
    private void findMarshalersOnInterfaces(SootClass sc, List<Marshaler> result, 
            Set<String> visited, Set<String> seen) {
        
        SootResolver.v().bringToHierarchy(sc);
        
        for (SootClass ifs : sc.getInterfaces()) {
            result.addAll(findMarshalersOn(ifs, visited, seen));
        }
        for (SootClass ifs : sc.getInterfaces()) {
            findMarshalersOnInterfaces(ifs, result, visited, seen);
        }
        
        if (sc.hasSuperclass()) {
            findMarshalersOnInterfaces(sc.getSuperclass(), result, visited, seen);
        }
    }

    private void findMarshalersOnOuterClasses(SootClass sc, List<Marshaler> result, 
            Set<String> visited, Set<String> seen) {

        SootClass outer = getOuterClass(sc);
        if (outer != null) {
            findMarshalersOnClasses(outer, result, visited, seen);
            findMarshalersOnInterfaces(outer, result, visited, seen);
            findMarshalersOnOuterClasses(outer, result, visited, seen);
        }
    }

    private List<Marshaler> findMarshalersOn(SootClass sc, Set<String> visited, Set<String> seen) {
        
        String internalName = getInternalName(sc);
        if (visited.contains(internalName)) {
            return Collections.emptyList();
        }
        visited.contains(internalName);
        
        List<Marshaler> all = cache.get(sc.getName());
        if (all == null) {
            all = new ArrayList<>();
            for (AnnotationTag tag : getMarshalerAnnotations(sc)) {
                AnnotationClassElem elem = (AnnotationClassElem) getElemByName(tag, "value");
                String name = getInternalNameFromDescriptor(elem.getDesc());
                Clazz marshalerClazz = config.getClazzes().load(name);
                if (marshalerClazz != null) {
                    all.add(new Marshaler(marshalerClazz));
                }
            }
            cache.put(sc.getName(), all);
        }
        
        List<Marshaler> result = new ArrayList<>();
        for (Marshaler m : all) {
            String name = m.clazz.getInternalName();
            if (!seen.contains(name)) {
                seen.add(name);
                result.add(m);
            }
        }

        return result;
    }
    
    private SootClass getOuterClass(SootClass clazz) {
        String name = getInternalName(clazz);
        for (Tag tag : clazz.getTags()) {
            if (tag instanceof InnerClassTag) {
                InnerClassTag innerClassTag = (InnerClassTag) tag;
                String inner = innerClassTag.getInnerClass();
                String outer = innerClassTag.getOuterClass();
                if (inner != null && outer != null && inner.equals(name)) {
                    return config.getClazzes().load(outer).getSootClass();
                }
            }
        }
        return null;
    }

    private Set<Long> getSupportedCallTypes(AnnotationTag anno) {
        AnnotationArrayElem el = 
                (AnnotationArrayElem) getElemByName(anno, "supportedCallTypes");
        if (el == null) {
            return new HashSet<Long>(Arrays.asList(
                Bro.MarshalerFlags.CALL_TYPE_BRIDGE,
                Bro.MarshalerFlags.CALL_TYPE_CALLBACK,
                Bro.MarshalerFlags.CALL_TYPE_STRUCT_MEMBER,
                Bro.MarshalerFlags.CALL_TYPE_GLOBAL_VALUE,
                Bro.MarshalerFlags.CALL_TYPE_PTR
            ));
        }
        ArrayList<AnnotationElem> values = ((AnnotationArrayElem) el).getValues();
        Set<Long> callTypes = new HashSet<>();
        for (AnnotationElem value : values) {
            callTypes.add(((AnnotationLongElem) value).getValue());
        }
        return callTypes;
    }

    private soot.Type getBaseType(SootMethod m, AnnotationTag anno) {
        AnnotationClassElem el = (AnnotationClassElem) getElemByName(anno, "baseType");
        if (el != null) {
            switch (el.getDesc().charAt(0)) {
            case 'Z': return BooleanType.v();
            case 'B': return ByteType.v();
            case 'S': return ShortType.v();
            case 'C': return CharType.v();
            case 'I': return IntType.v();
            case 'J': return LongType.v();
            case 'F': return FloatType.v();
            case 'D': return DoubleType.v();
            }
            return null;
        }
        soot.Type t = m.getReturnType();
        if (t == VoidType.v()) {
            t = m.getParameterType(0);
        }
        if (t instanceof RefType) {
            SootClass c = ((RefType) t).getSootClass();
            if (isInstanceOfClass(c, "java.nio.ByteBuffer")) {
                return ByteType.v();
            } else if (isInstanceOfClass(c, "java.nio.ShortBuffer")) {
                return ShortType.v();
            } else if (isInstanceOfClass(c, "java.nio.CharBuffer")) {
                return CharType.v();
            } else if (isInstanceOfClass(c, "java.nio.IntBuffer")) {
                return IntType.v();
            } else if (isInstanceOfClass(c, "java.nio.LongBuffer")) {
                return LongType.v();
            } else if (isInstanceOfClass(c, "java.nio.FloatBuffer")) {
                return FloatType.v();
            } else if (isInstanceOfClass(c, "java.nio.DoubleBuffer")) {
                return DoubleType.v();
            } else if (isInstanceOfClass(c, "org.robovm.rt.bro.Struct")) {
                return config.getClazzes().load("org/robovm/rt/bro/Struct")
                            .getSootClass().getType();
            }
        } else if (t instanceof ArrayType) {
            ArrayType arrayType = (ArrayType) t;
            if (arrayType.baseType instanceof PrimType 
                    || isInstanceOfClass(arrayType.baseType, "org.robovm.rt.bro.Struct")) {
                return arrayType.baseType;
            }
        }
        return null;
    }
    
    public static class MarshalSite {
        public static final int RETURN_TYPE = -1;
        public static final int RECEIVER = -2;
        
        private final SootMethod method;
        private final int paramIdx;
        private final long callType;
        private final String callTypeName;
        private final soot.Type type;
        private final boolean array;
        
        public MarshalSite(SootMethod method) {
            this(method, RETURN_TYPE);
        }
        
        public MarshalSite(SootMethod method, int paramIdx) {
            this.method = method;
            this.paramIdx = paramIdx;
            this.type = paramIdx == RETURN_TYPE ? method.getReturnType() :
                    (paramIdx == RECEIVER ? method.getDeclaringClass().getType()
                    : method.getParameterType(paramIdx));
            
            if (hasBridgeAnnotation(method)) {
                callType = Bro.MarshalerFlags.CALL_TYPE_BRIDGE;
                callTypeName = "@Bridge";
                array = false;
            } else if (hasCallbackAnnotation(method)) {
                callType = Bro.MarshalerFlags.CALL_TYPE_CALLBACK;
                callTypeName = "@Callback";
                array = false;
            } else if (hasStructMemberAnnotation(method)) {
                callType = Bro.MarshalerFlags.CALL_TYPE_STRUCT_MEMBER;
                callTypeName = "@StructMember";
                array = paramIdx == RETURN_TYPE ? hasArrayAnnotation(method) 
                        : hasArrayAnnotation(method, paramIdx);
            } else if (hasGlobalValueAnnotation(method)) {
                callType = Bro.MarshalerFlags.CALL_TYPE_GLOBAL_VALUE;
                callTypeName = "@GlobalValue";
                array = paramIdx == RETURN_TYPE ? hasArrayAnnotation(method) 
                        : hasArrayAnnotation(method, paramIdx);
            } else {
                throw new IllegalArgumentException();
            }
        }
        
        public SootMethod getMethod() {
            return method;
        }
        
        public long getCallType() {
            return callType;
        }
        
        public soot.Type getType() {
            return type;
        }
        
        public boolean isToNative() {
            if (callType == Bro.MarshalerFlags.CALL_TYPE_CALLBACK) {
                return paramIdx == RETURN_TYPE;
            } else {
                return paramIdx != RETURN_TYPE;
            }
        }
        
        public boolean isArray() {
            return array;
        }
    }
    
    public abstract class MarshalerMethod {
        protected final SootMethod method;
        protected final Set<Long> supportedCallTypes;
        MarshalerMethod(SootMethod method, Set<Long> supportedCallTypes) {
            this.method = method;
            this.supportedCallTypes = supportedCallTypes;
        }
        public SootMethod getMethod() {
            return method;
        }
        public boolean supportsCallType(long callType) {
            return supportedCallTypes.contains(callType);
        }
        public Invokestatic getInvokeStatic(SootClass caller) {
            return new Invokestatic(
                    getInternalName(caller), 
                    getInternalName(method.getDeclaringClass()), 
                    method.getName(),
                    getDescriptor(method));
        }
    }

    public class PointerMarshalerMethod extends MarshalerMethod {
        private boolean hasSearchedForAfterBridgeCallMethod = false;
        private SootMethod afterBridgeCallMethod = null;
        private boolean hasSearchedForAfterCallbackCallMethod = false;
        private SootMethod afterCallbackCallMethod = null;
        PointerMarshalerMethod(SootMethod method, Set<Long> supportedCallTypes) {
            super(method, supportedCallTypes);
        }
        public SootMethod getAfterBridgeCallMethod() {
            if (hasSearchedForAfterBridgeCallMethod) {
                return afterBridgeCallMethod;
            }
            hasSearchedForAfterBridgeCallMethod = true;
            List<soot.Type> paramTypes = Arrays.asList(method.getParameterType(0), LongType.v(), LongType.v());
            for (SootMethod m : method.getDeclaringClass().getMethods()) {
                if (hasAfterBridgeCallAnnotation(m)) {
                    if (m.getReturnType() == VoidType.v() 
                            && m.getParameterTypes().equals(paramTypes)) {
                        afterBridgeCallMethod = m;
                        break;
                    }
                }
            }
            return afterBridgeCallMethod;
        }
        public SootMethod getAfterCallbackCallMethod() {
            if (hasSearchedForAfterCallbackCallMethod) {
                return afterCallbackCallMethod;
            }
            hasSearchedForAfterCallbackCallMethod = true;
            List<soot.Type> paramTypes = Arrays.asList(LongType.v(), method.getReturnType(), LongType.v());
            for (SootMethod m : method.getDeclaringClass().getMethods()) {
                if (hasAfterCallbackCallAnnotation(m)) {
                    if (m.getReturnType() == VoidType.v() 
                            && m.getParameterTypes().equals(paramTypes)) {
                        afterCallbackCallMethod = m;
                        break;
                    }
                }
            }
            return afterCallbackCallMethod;
        }
    }
    public class ValueMarshalerMethod extends MarshalerMethod {
        ValueMarshalerMethod(SootMethod method, Set<Long> supportedCallTypes) {
            super(method, supportedCallTypes);
        }
        public Type getNativeType(Arch arch) {
            if (method.getReturnType() instanceof PrimType) {
                if (hasPointerAnnotation(method)) {
                    return I8_PTR;
                }
                if (arch.is32Bit() && (hasMachineSizedSIntAnnotation(method) 
                        || hasMachineSizedUIntAnnotation(method))) {
                    return I32;
                }
                if (arch.is32Bit() && (hasMachineSizedFloatAnnotation(method))) {
                    return FLOAT;
                }
                if (!arch.is32Bit() && (hasMachineSizedFloatAnnotation(method))) {
                    return DOUBLE;
                }
                return Types.getType(method.getReturnType());
            } else {
                if (hasPointerAnnotation(method, 1)) {
                    return I8_PTR;
                }
                if (arch.is32Bit() && (hasMachineSizedSIntAnnotation(method, 1) 
                        || hasMachineSizedUIntAnnotation(method, 1))) {
                    return I32;
                }
                if (arch.is32Bit() && (hasMachineSizedFloatAnnotation(method, 1))) {
                    return FLOAT;
                }
                if (!arch.is32Bit() && (hasMachineSizedFloatAnnotation(method, 1))) {
                    return DOUBLE;
                }
                return Types.getType(method.getParameterType(1));
            }
        }
    }
    public class ArrayMarshalerMethod extends MarshalerMethod {
        private final soot.Type baseType;
        ArrayMarshalerMethod(SootMethod method, Set<Long> supportedCallTypes, soot.Type baseType) {
            super(method, supportedCallTypes);
            this.baseType = baseType;
        }
        public soot.Type getBaseType() {
            return baseType;
        }
    }
    
    public class Marshaler {
        private final Clazz clazz;
        private Map<String, MarshalerMethod> toObjectMethods = null;
        private Map<String, MarshalerMethod> toNativeMethods = null;
        private Map<Integer, Map<String, ArrayMarshalerMethod>> toArrayObjectMethods = null;
        private Map<Integer, Map<String, ArrayMarshalerMethod>> toNativeArrayMethods = null;
        
        private Marshaler(Clazz clazz) {
            this.clazz = clazz;
        }

        private boolean isToObject(SootMethod m, AnnotationTag pointerAnno, 
                AnnotationTag valueAnno, AnnotationTag arrayAnno) {

            // The method must be annotated
            if (pointerAnno == null && valueAnno == null && arrayAnno == null) {
                return false;
            }
            // Fail if there are several annotations
            if (pointerAnno != null && (valueAnno != null || arrayAnno != null) 
                    || (valueAnno != null && arrayAnno != null)) {
                return false;
            }
            
            // All toObject(...) methods take at least 3 parameters
            @SuppressWarnings("unchecked")
            List<soot.Type> paramTypes = m.getParameterTypes();
            if (paramTypes.size() < 3) {
                return false;
            }
            
            // All toObject(...) methods take a Class as first parameter
            if (!(paramTypes.get(0) instanceof RefType)) {
                return false;
            }
            SootClass sc = ((RefType) paramTypes.get(0)).getSootClass();
            if (!sc.getName().equals("java.lang.Class")) {
                return false;
            }
            
            soot.Type returnType = m.getReturnType();
            
            if (pointerAnno != null) {
                // T toObject(Class<?> cls, long handle, long flags)
                if (!(returnType instanceof RefLikeType)) {
                    return false;
                }
                if (paramTypes.size() == 3) {
                    if (paramTypes.get(1) == LongType.v() 
                            && paramTypes.get(2) == LongType.v()) {
                        return true;
                    }
                }
            } else if (valueAnno != null) {
                // T toObject(Class<?> cls, <primitive> value, long flags)
                if (!(returnType instanceof RefLikeType)) {
                    return false;
                }
                if (paramTypes.size() == 3) {
                    if (paramTypes.get(1) instanceof PrimType 
                            && paramTypes.get(2) == LongType.v()) {
                        if (hasPointerAnnotation(m, 1)) {
                            return paramTypes.get(1).equals(LongType.v());
                        }
                        if (hasMachineSizedFloatAnnotation(m, 1)) {
                            return paramTypes.get(1).equals(FloatType.v()) 
                                || paramTypes.get(1).equals(DoubleType.v());
                        }
                        if (hasMachineSizedSIntAnnotation(m, 1)) {
                            return paramTypes.get(1).equals(LongType.v());
                        }
                        if (hasMachineSizedUIntAnnotation(m, 1)) {
                            return paramTypes.get(1).equals(LongType.v());
                        }
                        return true;
                    }
                }
            } else if (arrayAnno != null) {
                // T toObject(Class<?> cls, long handle, long flags, int d1[, int d2[, int d3]])
                if (!(returnType instanceof RefLikeType)) {
                    return false;
                }
                if (getBaseType(m, arrayAnno) == null) {
                    return false;
                }
                if (paramTypes.size() > 3) {
                    if (paramTypes.get(1) == LongType.v() 
                            && paramTypes.get(2) == LongType.v()) {
                        for (int i = 3; i < paramTypes.size(); i++) {
                            if (paramTypes.get(i) != IntType.v()) {
                                return false;
                            }
                        }
                        return true;
                    }
                }
            }
            
            return false;
        }

        private boolean isToNative(SootMethod m, AnnotationTag pointerAnno, 
                AnnotationTag valueAnno, AnnotationTag arrayAnno) {

            // The method must be annotated
            if (pointerAnno == null && valueAnno == null && arrayAnno == null) {
                return false;
            }
            // Fail if there are several annotations
            if (pointerAnno != null && (valueAnno != null || arrayAnno != null) 
                    || (valueAnno != null && arrayAnno != null)) {
                return false;
            }
            
            // All toNative(...) methods take at least 2 parameters
            @SuppressWarnings("unchecked")
            List<soot.Type> paramTypes = m.getParameterTypes();
            if (paramTypes.size() < 2) {
                return false;
            }
            
            // All toNative(...) methods take a reference as first parameter
            if (!(paramTypes.get(0) instanceof RefLikeType)) {
                return false;
            }
            
            soot.Type returnType = m.getReturnType();
            
            if (pointerAnno != null) {
                // long toNative(T t, long flags)
                if (returnType != LongType.v()) {
                    return false;
                }
                if (paramTypes.size() == 2) {
                    if (paramTypes.get(1) == LongType.v()) {
                        return true;
                    }
                }
            } else if (valueAnno != null) {
                // <primitive> toNative(T value, long flags)
                if (!(returnType instanceof PrimType)) {
                    return false;
                }
                if (paramTypes.size() == 2) {
                    if (paramTypes.get(1) == LongType.v()) {
                        if (hasPointerAnnotation(m)) {
                            return returnType.equals(LongType.v());
                        }
                        if (hasMachineSizedFloatAnnotation(m)) {
                            return returnType.equals(FloatType.v()) 
                                || returnType.equals(DoubleType.v());
                        }
                        if (hasMachineSizedSIntAnnotation(m)) {
                            return returnType.equals(LongType.v());
                        }
                        if (hasMachineSizedUIntAnnotation(m)) {
                            return returnType.equals(LongType.v());
                        }
                        return true;
                    }
                }
            } else if (arrayAnno != null) {
                // void toNative(T t, long handle, long flags, int d1[, int d2[, int d3]])
                if (returnType != VoidType.v()) {
                    return false;
                }
                if (getBaseType(m, arrayAnno) == null) {
                    return false;
                }
                if (paramTypes.size() > 3) {
                    if (paramTypes.get(1) == LongType.v() 
                            && paramTypes.get(2) == LongType.v()) {
                        for (int i = 3; i < paramTypes.size(); i++) {
                            if (paramTypes.get(i) != IntType.v()) {
                                return false;
                            }
                        }
                        return true;
                    }
                }
            }
            
            return false;
        }
        
        private void buildMaps() {
            if (toObjectMethods == null && toNativeMethods == null 
                    && toArrayObjectMethods == null && toNativeArrayMethods == null) {
                
                toObjectMethods = new HashMap<>();
                toNativeMethods = new HashMap<>();
                toArrayObjectMethods = new HashMap<>();
                toNativeArrayMethods = new HashMap<>();
                
                for (SootMethod m : clazz.getSootClass().getMethods()) {
                    if (m.isStatic() && m.isPublic()) {
                        AnnotationTag pointerAnno = getMarshalsPointerAnnotation(m);
                        AnnotationTag valueAnno = getMarshalsValueAnnotation(m);
                        AnnotationTag arrayAnno = getMarshalsArrayAnnotation(m);
                        if (isToObject(m, pointerAnno, valueAnno, arrayAnno)) {
                            AnnotationTag anno = pointerAnno != null ? pointerAnno 
                                    : (valueAnno != null ? valueAnno : arrayAnno);
                            Set<Long> supportedCallTypes = getSupportedCallTypes(anno);
                            if (pointerAnno != null) {
                                toObjectMethods.put(getDescriptor(m.getReturnType()), 
                                        new PointerMarshalerMethod(m, supportedCallTypes));
                            } else if (valueAnno != null) {
                                toObjectMethods.put(getDescriptor(m.getReturnType()), 
                                        new ValueMarshalerMethod(m, supportedCallTypes));
                            } else {
                                int dimCount = m.getParameterCount() - 3;
                                Map<String, ArrayMarshalerMethod> map = toArrayObjectMethods.get(dimCount);
                                if (map == null) {
                                    map = new HashMap<>();
                                    toArrayObjectMethods.put(dimCount, map);
                                }
                                map.put(getDescriptor(m.getReturnType()), 
                                        new ArrayMarshalerMethod(m, supportedCallTypes, 
                                                getBaseType(m, arrayAnno)));
                            }
                        } else if (isToNative(m, pointerAnno, valueAnno, arrayAnno)) {
                            AnnotationTag anno = pointerAnno != null ? pointerAnno 
                                    : (valueAnno != null ? valueAnno : arrayAnno);
                            Set<Long> supportedCallTypes = getSupportedCallTypes(anno);
                            if (pointerAnno != null) {
                                toNativeMethods.put(getDescriptor(m.getParameterType(0)), 
                                        new PointerMarshalerMethod(m, supportedCallTypes));
                            } else if (valueAnno != null) {
                                toNativeMethods.put(getDescriptor(m.getParameterType(0)), 
                                        new ValueMarshalerMethod(m, supportedCallTypes));
                            } else {
                                int dimCount = m.getParameterCount() - 3;
                                Map<String, ArrayMarshalerMethod> map = toNativeArrayMethods.get(dimCount);
                                if (map == null) {
                                    map = new HashMap<>();
                                    toNativeArrayMethods.put(dimCount, map);
                                }
                                map.put(getDescriptor(m.getParameterType(0)), 
                                        new ArrayMarshalerMethod(m, supportedCallTypes, 
                                                getBaseType(m, arrayAnno)));
                            }

                        } else if (pointerAnno != null || valueAnno != null || arrayAnno != null) {
                            config.getLogger().warn("Ignoring invalid marshaler method %s", m);
                        }
                    }
                }
            }
        }
        
        private soot.Type makeArrayType(soot.Type baseType, int numDimensions) {
            soot.Type newType = baseType;
            for (int i = 0; i < numDimensions; i++) {
                newType = newType.makeArrayType();
            }
            return newType;
        }
        
        private MarshalerMethod findMarshalerMethod(soot.Type type, Map<String, ? extends MarshalerMethod> map) {
            MarshalerMethod m = map.get(getDescriptor(type));
            if (m != null) {
                return m;
            }
            
            if (type instanceof RefType) {
                SootClass sc = ((RefType) type).getSootClass();
                if (sc.hasSuperclass()) {
                    m = findMarshalerMethod(sc.getSuperclass().getType(), map);
                    if (m != null) {
                        return m;
                    }
                }
                for (SootClass ifs : sc.getInterfaces()) {
                    m = findMarshalerMethod(ifs.getType(), map);
                    if (m != null) {
                        return m;
                    }                    
                }
            } else if (type instanceof ArrayType) {
                ArrayType arrayType = (ArrayType) type;
                if (arrayType.baseType instanceof RefType) {
                    SootClass sc = ((RefType) arrayType.baseType).getSootClass();
                    if (sc.hasSuperclass()) {
                        m = findMarshalerMethod(makeArrayType(sc.getSuperclass().getType(), 
                                arrayType.numDimensions), map);
                        if (m != null) {
                            return m;
                        }                    
                    }
                    for (SootClass ifs : sc.getInterfaces()) {
                        m = findMarshalerMethod(makeArrayType(ifs.getType(), 
                                arrayType.numDimensions), map);
                        if (m != null) {
                            return m;
                        }                    
                    }
                }
            }
            return null;
        }
        
        public Clazz getClazz() {
            return clazz;
        }
        
        public boolean canMarshal(MarshalSite marshalSite) {
            return getMarshalerMethod(marshalSite) != null;
        }

        public MarshalerMethod getMarshalerMethod(MarshalSite marshalSite) {
            buildMaps();
            Map<String, ? extends MarshalerMethod> map = null;
            if (marshalSite.isArray()) {
                int dimCount = Bro.getArrayDimensions(marshalSite.method, marshalSite.paramIdx).length;
                map = marshalSite.isToNative() ? toNativeArrayMethods.get(dimCount) 
                        : toArrayObjectMethods.get(dimCount);
                if (map == null) {
                    return null;
                }
            } else {
                map = marshalSite.isToNative() ? toNativeMethods : toObjectMethods;
            }
            MarshalerMethod m = findMarshalerMethod(marshalSite.getType(), map);
            if (m != null) {
                if (m.supportsCallType(marshalSite.getCallType())) {
                    return m;
                }
            }
            return null;
        }
    }
}
