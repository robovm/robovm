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
package org.robovm.compiler;

import static org.robovm.compiler.Access.*;
import static org.robovm.compiler.Functions.*;
import static org.robovm.compiler.Types.*;
import static org.robovm.compiler.llvm.FunctionAttribute.*;
import static org.robovm.compiler.llvm.Linkage.*;

import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.robovm.compiler.clazz.Clazz;
import org.robovm.compiler.config.Config;
import org.robovm.compiler.llvm.Function;
import org.robovm.compiler.llvm.FunctionAttribute;
import org.robovm.compiler.llvm.FunctionDeclaration;
import org.robovm.compiler.llvm.FunctionRef;
import org.robovm.compiler.llvm.FunctionType;
import org.robovm.compiler.llvm.Global;
import org.robovm.compiler.llvm.Linkage;
import org.robovm.compiler.llvm.NullConstant;
import org.robovm.compiler.llvm.Ret;
import org.robovm.compiler.llvm.StructureType;
import org.robovm.compiler.llvm.Unreachable;
import org.robovm.compiler.llvm.Value;
import org.robovm.compiler.trampoline.Anewarray;
import org.robovm.compiler.trampoline.Checkcast;
import org.robovm.compiler.trampoline.FieldAccessor;
import org.robovm.compiler.trampoline.GetField;
import org.robovm.compiler.trampoline.Instanceof;
import org.robovm.compiler.trampoline.Invoke;
import org.robovm.compiler.trampoline.Invokeinterface;
import org.robovm.compiler.trampoline.Invokespecial;
import org.robovm.compiler.trampoline.Invokevirtual;
import org.robovm.compiler.trampoline.LdcClass;
import org.robovm.compiler.trampoline.Multianewarray;
import org.robovm.compiler.trampoline.New;
import org.robovm.compiler.trampoline.PutField;
import org.robovm.compiler.trampoline.Trampoline;

import soot.ClassMember;
import soot.IntType;
import soot.Modifier;
import soot.SootClass;
import soot.SootField;
import soot.SootMethod;

/**
 * @author niklas
 *
 */
public class TrampolineCompiler {
    public static final String ATTEMPT_TO_WRITE_TO_FINAL_FIELD = "Attempt to write to final field %s.%s from class %s";
    public static final String EXPECTED_INTERFACE_BUT_FOUND_CLASS = "Expected interface but found class %s";
    public static final String EXPECTED_NON_STATIC_METHOD = "Expected non-static method %s.%s%s";
    public static final String EXPECTED_STATIC_METHOD = "Expected static method %s.%s%s";
    public static final String EXPECTED_CLASS_BUT_FOUND_INTERFACE = "Expected class but found interface %s";
    public static final String EXPECTED_NON_STATIC_FIELD = "Expected non-static field %s.%s";
    public static final String EXPECTED_STATIC_FIELD = "Expected static field %s.%s";
    public static final String NO_SUCH_FIELD_ERROR = "%s.%s";
    public static final String NO_SUCH_METHOD_ERROR = "%s.%s%s";
    
    private final Config config;
    private ModuleBuilder mb;
    private Set<String> dependencies;

    public TrampolineCompiler(Config config) {
        this.config = config;
    }
    
    public Set<String> getDependencies() {
        return dependencies;
    }

    private Linkage aliasLinkage() {
        /*
         * The {@link Linkage} used for alias functions used to be _private but
         * for some reason LLVM's assembler doesn't resolve private symbols
         * correctly when there is debug info in the module which causes weird
         * crashes. See #559.
         */
        return config.isDebug() ? external : _private;
    }

    private FunctionAttribute shouldInline() {
        /*
         * Alias function used to be inlined always before we had debug builds.
         * The problem with debug builds is that when the function is inlined we
         * lose line number information for that line which. So now we don't
         * inline alias functions in debug builds. See #559.
         */
        return config.isDebug() ? noinline : alwaysinline;
    }

    public void compile(ModuleBuilder mb, Trampoline t) {
        this.mb = mb;
        this.dependencies = new HashSet<String>();
        
        /*
         * Check if the target class exists and is accessible. Also check that
         * field accesses and method calls are compatible with the target 
         * field/method and that the field/method is accessible to the caller.
         * If any of the tests fail the weak trampoline function created by the
         * ClassCompiler will be overridden with a function which throws an
         * appropriate exception.
         */
        Function errorFn = new FunctionBuilder(t).linkage(external).build();
        if (!checkClassExists(errorFn, t) || !checkClassAccessible(errorFn, t)) {
            mb.addFunction(errorFn);
            return;
        }

        if (t instanceof New) {
            SootClass target = config.getClazzes().load(t.getTarget()).getSootClass();
            if (target.isAbstract() || target.isInterface()) {
                call(errorFn, BC_THROW_INSTANTIATION_ERROR, errorFn.getParameterRef(0), mb.getString(t.getTarget().replace('/', '.')));
                errorFn.add(new Unreachable());
                mb.addFunction(errorFn);
                return;
            }
            String fnName = Symbols.clinitWrapperSymbol(Symbols.allocatorSymbol(t.getTarget()));
            alias(t, fnName);
        } else if (t instanceof Instanceof) {
            if (isArray(t.getTarget())) {
                FunctionRef fnRef = createInstanceofArray((Instanceof) t);
                alias(t, fnRef.getName());
            } else {
                String fnName = Symbols.instanceofSymbol(t.getTarget());
                alias(t, fnName);
            }
        } else if (t instanceof Checkcast) {
            if (isArray(t.getTarget())) {
                FunctionRef fnRef = createCheckcastArray((Checkcast) t);
                alias(t, fnRef.getName());
            } else {
                String fnName = Symbols.checkcastSymbol(t.getTarget());
                alias(t, fnName);
            }
        } else if (t instanceof LdcClass) {
            if (isArray(t.getTarget())) {
                FunctionRef fnRef = createLdcArray((LdcClass) t);
                alias(t, fnRef.getName());
            } else {
                String fnName = Symbols.ldcExternalSymbol(t.getTarget());
                alias(t, fnName);
            }
        } else if (t instanceof Anewarray) {
            FunctionRef fnRef = createAnewarray((Anewarray) t);
            alias(t, fnRef.getName());
        } else if (t instanceof Multianewarray) {
            FunctionRef fnRef = createMultianewarray((Multianewarray) t);
            alias(t, fnRef.getName());
        } else if (t instanceof FieldAccessor) {
            SootField field = resolveField(errorFn, (FieldAccessor) t);
            if (field != null) {
                dependencies.add(getInternalName(field.getDeclaringClass()));
            }
            if (field == null || !checkMemberAccessible(errorFn, t, field)) {
                mb.addFunction(errorFn);
                return;
            }
            Clazz caller = config.getClazzes().load(t.getCallingClass());
            Clazz target = config.getClazzes().load(t.getTarget());
            if (!((FieldAccessor) t).isGetter() &&  field.isFinal() && caller != target) {
                // Only the class declaring a final field may write to it.
                // (Actually only <init>/<clinit> methods may write to it but we 
                // don't know which method is accessing the field at this point)
                throwIllegalAccessError(errorFn, ATTEMPT_TO_WRITE_TO_FINAL_FIELD, 
                        target, field.getName(), caller);
                mb.addFunction(errorFn);
                return;
            }
            if (!field.isStatic()) {
                createInlinedAccessorForInstanceField((FieldAccessor) t, field);   
            } else {
                createTrampolineAliasForField((FieldAccessor) t, field);
            }
        } else if (t instanceof Invokeinterface) {
            SootMethod rm = resolveInterfaceMethod(errorFn, (Invokeinterface) t);
            if (rm != null) {
                dependencies.add(getInternalName(rm.getDeclaringClass()));
            }
            if (rm == null || !checkMemberAccessible(errorFn, t, rm)) {
                mb.addFunction(errorFn);
                return;
            }
            createTrampolineAliasForMethod((Invoke) t, rm);
        } else if (t instanceof Invoke) {
            SootMethod method = resolveMethod(errorFn, (Invoke) t);
            if (method != null) {
                dependencies.add(getInternalName(method.getDeclaringClass()));
            }
            if (method == null || !checkMemberAccessible(errorFn, t, method)) {
                mb.addFunction(errorFn);
                return;
            }
            if (t instanceof Invokespecial && method.isAbstract()) {
                call(errorFn, BC_THROW_ABSTRACT_METHOD_ERROR, errorFn.getParameterRef(0), 
                        mb.getString(String.format(NO_SUCH_METHOD_ERROR, 
                                method.getDeclaringClass(), method.getName(), 
                                getDescriptor(method))));
                errorFn.add(new Unreachable());
                mb.addFunction(errorFn);
                return;
            }
            createTrampolineAliasForMethod((Invoke) t, method);
        }
    }

    private void alias(Trampoline t, String fnName) {
        FunctionRef aliasee = new FunctionRef(fnName, t.getFunctionType());
        if (!mb.hasSymbol(fnName)) {
            mb.addFunctionDeclaration(new FunctionDeclaration(aliasee));
        }
        Function fn = new FunctionBuilder(t).linkage(aliasLinkage()).attribs(shouldInline(), optsize).build();
        Value result = call(fn, aliasee, fn.getParameterRefs());
        fn.add(new Ret(result));
        mb.addFunction(fn);
    }
    
    private void createTrampolineAliasForField(FieldAccessor t, SootField field) {
        String fnName = t.isGetter() ? Symbols.getterSymbol(field) : Symbols.setterSymbol(field);
        if (t.isStatic()) {
            fnName = Symbols.clinitWrapperSymbol(fnName);
        }
        alias(t, fnName);
    }

    private void createInlinedAccessorForInstanceField(FieldAccessor t, SootField field) {
        Function fn = new FunctionBuilder(t).linkage(aliasLinkage()).attribs(shouldInline(), optsize).build();

        List<SootField> classFields = Collections.emptyList();
        StructureType classType = new StructureType();
        List<SootField> instanceFields = getInstanceFields(config.getOs(), config.getArch(), field.getDeclaringClass());
        StructureType instanceType = getInstanceType(config.getOs(), config.getArch(), field.getDeclaringClass());
        if (t.isGetter()) {
            ClassCompiler.createFieldGetter(fn, field, classFields, classType, instanceFields, instanceType);
        } else {
            ClassCompiler.createFieldSetter(fn, field, classFields, classType, instanceFields, instanceType);
        }
        
        mb.addFunction(fn);
    }
    
    private void createTrampolineAliasForMethod(Invoke t, SootMethod rm) {
        String fnName = null;
        if (t instanceof Invokeinterface) {
            fnName = Symbols.lookupWrapperSymbol(rm);
        } else if (t instanceof Invokevirtual 
                && !Modifier.isFinal(rm.getDeclaringClass().getModifiers()) 
                && !Modifier.isFinal(rm.getModifiers())
                && !Modifier.isPrivate(rm.getModifiers())) {
            fnName = Symbols.lookupWrapperSymbol(rm);
        } else if (rm.isSynchronized()) {
            fnName = Symbols.synchronizedWrapperSymbol(rm);
        } else {
            fnName = Symbols.methodSymbol(rm);
        }
        if (t.isStatic()) {
            fnName = Symbols.clinitWrapperSymbol(fnName);
        }
        alias(t, fnName);
    }
    
    private Value callLdcArray(Function function, String targetClass) {
        FunctionRef fnRef = createLdcArray(targetClass);
        return call(function, fnRef, function.getParameterRef(0));
    }
    
    private FunctionRef createLdcArray(LdcClass t) {
        return createLdcArray(t.getTarget());
    }

    private FunctionRef createLdcArray(String targetClass) {
        if (isPrimitiveComponentType(targetClass)) {
            throw new IllegalArgumentException();
        }
        String fnName = Symbols.arrayldcSymbol(targetClass);
        FunctionRef fnRef = new FunctionRef(fnName, new FunctionType(OBJECT_PTR, ENV_PTR));
        if (!mb.hasSymbol(fnName)) {
            Function fn = new FunctionBuilder(fnRef).name(fnName).linkage(weak).build();
            Global g = new Global(Symbols.arrayPtrSymbol(targetClass), weak, new NullConstant(OBJECT_PTR));
            if (!mb.hasSymbol(g.getName())) {
                mb.addGlobal(g);
            }
            FunctionRef ldcArrayClassFn = BC_LDC_ARRAY_BOOT_CLASS;
            if (!isPrimitiveBaseType(targetClass)) {
                Clazz baseType = config.getClazzes().load(getBaseType(targetClass));
                if (!baseType.isInBootClasspath()) {
                    ldcArrayClassFn = BC_LDC_ARRAY_CLASS;
                }
            }
            Value arrayClass = call(fn, ldcArrayClassFn, fn.getParameterRef(0), g.ref(), mb.getString(targetClass));
            fn.add(new Ret(arrayClass));
            mb.addFunction(fn);
        }
        return fnRef;
    }

    private FunctionRef createInstanceofArray(Instanceof t) {
        String fnName = Symbols.arrayinstanceofSymbol(t.getTarget());
        if (!mb.hasSymbol(fnName)) {
            Function fn = new FunctionBuilder(t).name(fnName).linkage(weak).build();
            Value arrayClass = callLdcArray(fn, t.getTarget());
            Value result = call(fn, BC_INSTANCEOF_ARRAY, fn.getParameterRef(0), arrayClass, fn.getParameterRef(1));
            fn.add(new Ret(result));
            mb.addFunction(fn);
        }
        return new FunctionRef(fnName, t.getFunctionType());
    }

    private FunctionRef createCheckcastArray(Checkcast t) {
        String fnName = Symbols.arraycheckcastSymbol(t.getTarget());
        if (!mb.hasSymbol(fnName)) {
            Function fn = new FunctionBuilder(t).name(fnName).linkage(weak).build();
            Value arrayClass = callLdcArray(fn, t.getTarget());
            Value result = call(fn, BC_CHECKCAST_ARRAY, fn.getParameterRef(0), arrayClass, fn.getParameterRef(1));
            fn.add(new Ret(result));
            mb.addFunction(fn);
        }
        return new FunctionRef(fnName, t.getFunctionType());
    }

    private FunctionRef createAnewarray(Anewarray t) {
        String fnName = Symbols.anewarraySymbol(t.getTarget());
        if (!mb.hasSymbol(fnName)) {
            Function fn = new FunctionBuilder(t).name(fnName).linkage(weak).build();
            Value arrayClass = callLdcArray(fn, t.getTarget());
            Value result = call(fn, BC_NEW_OBJECT_ARRAY, fn.getParameterRef(0), 
                  fn.getParameterRef(1), arrayClass);
            fn.add(new Ret(result));
            mb.addFunction(fn);
        }
        return new FunctionRef(fnName, t.getFunctionType());
    }

    private FunctionRef createMultianewarray(Multianewarray t) {
        String fnName = Symbols.multianewarraySymbol(t.getTarget());
        if (!mb.hasSymbol(fnName)) {
            Function fn = new FunctionBuilder(t).name(fnName).linkage(weak).build();
            Value arrayClass = callLdcArray(fn, t.getTarget());
            Value result = call(fn, BC_NEW_MULTI_ARRAY, fn.getParameterRef(0), 
                  fn.getParameterRef(1), fn.getParameterRef(2), arrayClass);
            fn.add(new Ret(result));
            mb.addFunction(fn);
        }
        return new FunctionRef(fnName, t.getFunctionType());
    }

    private boolean checkClassExists(Function f, Trampoline t) {
        String targetClassName = t.getTarget();
        if (isArray(targetClassName)) {
            if (isPrimitiveBaseType(targetClassName)) {
                return true;
            }
            targetClassName = getBaseType(targetClassName);
        }
        
        Clazz target = config.getClazzes().load(targetClassName);
        if (target != null) {
            Clazz caller = config.getClazzes().load(t.getCallingClass());
            // If caller is in the bootclasspath it only sees classes in the bootclasspath
            if (!caller.isInBootClasspath() || target.isInBootClasspath()) {
                return true;
            }
        }
        
        call(f, BC_THROW_NO_CLASS_DEF_FOUND_ERROR, f.getParameterRef(0), 
                mb.getString(t.getTarget()));
        f.add(new Unreachable());
        return false;
    }

    private boolean checkClassAccessible(Function f, Trampoline t) {
        Clazz caller = config.getClazzes().load(t.getCallingClass());
        
        String targetClassName = t.getTarget();
        if (isArray(targetClassName)) {
            if (isPrimitiveBaseType(targetClassName)) {
                return true;
            }
            targetClassName = getBaseType(targetClassName);
        }
        Clazz target = config.getClazzes().load(targetClassName);
        if (Access.checkClassAccessible(target, caller)) {
            return true;
        }
        throwIllegalAccessError(f, ILLEGAL_ACCESS_ERROR_CLASS, 
                target, caller);
        f.add(new Unreachable());
        return false;
    }

    private boolean checkMemberAccessible(Function f, Trampoline t, ClassMember member) {
        Clazz caller = config.getClazzes().load(t.getCallingClass());
        Clazz target = config.getClazzes().load(member.getDeclaringClass().getName().replace('.', '/'));

        String runtimeClassName = null;
        runtimeClassName = t instanceof Invokevirtual 
                ? ((Invokevirtual) t).getRuntimeClass() : runtimeClassName;
        runtimeClassName = t instanceof Invokespecial 
                ? ((Invokespecial) t).getRuntimeClass() : runtimeClassName;
        runtimeClassName = t instanceof GetField 
                ? ((GetField) t).getRuntimeClass() : runtimeClassName;
        runtimeClassName = t instanceof PutField 
                ? ((PutField) t).getRuntimeClass() : runtimeClassName;
                
        SootClass runtimeClass = null;
        if (runtimeClassName != null && !isArray(runtimeClassName)) {
            Clazz c = config.getClazzes().load(runtimeClassName);
            if (c == null) {
                // The runtime class type is not available. Classloading will fail earlier so let's
                // just return true here.
                return true;
            }
            runtimeClass = c.getSootClass();
        }
        
        if (Access.checkMemberAccessible(member, caller, target, runtimeClass)) {
            return true;
        }

        if (member instanceof SootMethod) {
            SootMethod method = (SootMethod) member;
            throwIllegalAccessError(f, ILLEGAL_ACCESS_ERROR_METHOD, 
                    method.getDeclaringClass(), method.getName(), 
                    getDescriptor(method), caller.getSootClass());
        } else {
            SootField field = (SootField) member;
            throwIllegalAccessError(f, ILLEGAL_ACCESS_ERROR_FIELD, 
                    field.getDeclaringClass(), field.getName(), caller.getSootClass());
        }
        f.add(new Unreachable());
        return false;
    }
    
    private void throwNoSuchMethodError(Function f, Invoke invoke) {
        call(f, BC_THROW_NO_SUCH_METHOD_ERROR, f.getParameterRef(0), 
                mb.getString(String.format(NO_SUCH_METHOD_ERROR, 
                        invoke.getTarget().replace('/', '.'), 
                        invoke.getMethodName(), invoke.getMethodDesc())));
        f.add(new Unreachable());
    }
    
    private void throwNoSuchFieldError(Function f, FieldAccessor accessor) {
        call(f, BC_THROW_NO_SUCH_FIELD_ERROR, f.getParameterRef(0), 
                mb.getString(String.format(NO_SUCH_FIELD_ERROR, 
                        accessor.getTarget().replace('/', '.'), 
                        accessor.getFieldName())));
        f.add(new Unreachable());
    }
    
    private void throwIncompatibleChangeError(Function f, String message, Object ... args) {
        call(f, BC_THROW_INCOMPATIBLE_CLASS_CHANGE_ERROR, f.getParameterRef(0), 
                mb.getString(String.format(message, args)));
        f.add(new Unreachable());
    }
    
    private void throwIllegalAccessError(Function f, String message, Object ... args) {
        call(f, BC_THROW_ILLEGAL_ACCESS_ERROR, f.getParameterRef(0), 
                mb.getString(String.format(message, args)));
        f.add(new Unreachable());
    }
    
    private SootField resolveField(Function f, FieldAccessor t) {
        SootClass target = config.getClazzes().load(t.getTarget()).getSootClass();
        String name = t.getFieldName();
        String desc = t.getFieldDesc();
        SootField field = resolveField(target, name, desc);
        if (field == null) {
            throwNoSuchFieldError(f, t);
            return null;
        }
        
        if (!field.isStatic() && t.isStatic()) {
            throwIncompatibleChangeError(f, EXPECTED_STATIC_FIELD, 
                    field.getDeclaringClass(), t.getFieldName());
            return null;
        }
        if (field.isStatic() && !t.isStatic()) {
            throwIncompatibleChangeError(f, EXPECTED_NON_STATIC_FIELD, 
                    field.getDeclaringClass(), t.getFieldName());
            return null;
        }
        return field;
    }
    
    private SootField resolveField(SootClass clazz, String name, String desc) {
        if (clazz != null && !clazz.isPhantom()) {
            SootField field = getField(clazz, name, desc);
            if (field != null) {
                return field;
            }
            for (SootClass interfaze : clazz.getInterfaces()) {
                field = resolveField(interfaze, name, desc);
                if (field != null) {
                    return field;
                }
            }
            if (!clazz.isInterface() && clazz.hasSuperclass()) {
                return resolveField(clazz.getSuperclass(), name, desc);
            }
        }
        return null;
    }
    
    private SootMethod resolveMethod(Function f, Invoke t) {
        SootClass target = config.getClazzes().load(t.getTarget()).getSootClass();
        String name = t.getMethodName();
        String desc = t.getMethodDesc();
        if ("<init>".equals(name) && t instanceof Invokespecial) {
            SootMethod method = getMethod(target, name, desc);
            if (method != null) {
                return method;
            }
        }
        if ("<clinit>".equals(name) || "<init>".equals(name)) {
            // This is not part of method resolution but we 
            // need to handle it somehow.
            throwNoSuchMethodError(f, t);
            return null;
        }
        SootMethod method = resolveMethod(target, name, desc);
        if (method == null) {
            throwNoSuchMethodError(f, t);
            return null;
        }
        if (t.isStatic() && !method.isStatic()) {
            throwIncompatibleChangeError(f, EXPECTED_STATIC_METHOD, 
                    target, name, desc);
            return null;
        } else if (!t.isStatic() && method.isStatic()) {
            throwIncompatibleChangeError(f, EXPECTED_NON_STATIC_METHOD, 
                    target, name, desc);
            return null;
        }        
        return method;
    }
    
    private SootMethod resolveMethod(SootClass clazz, String name, String desc) {
        if (clazz != null && !clazz.isPhantom()) {
            SootMethod method = getMethod(clazz, name, desc);
            if (method != null) {
                return method;
            }
            if (name.equals("sizeOf") && isStruct(clazz)) {
                method = new SootMethod("sizeOf", Collections.EMPTY_LIST, IntType.v(), 
                        Modifier.PUBLIC | Modifier.STATIC);
                method.setDeclaringClass(clazz);
                method.setDeclared(true);
                return method;
            }

            SootClass c = !clazz.isInterface() && clazz.hasSuperclass() ? clazz.getSuperclass() : null;
            while (c != null) {
                method = getMethod(c, name, desc);
                if (method != null) {
                    return method;
                }
                c = !c.isInterface() && c.hasSuperclass() ? c.getSuperclass() : null;
            }

            c = clazz;
            while (c != null) {
                for (SootClass interfaze : c.getInterfaces()) {
                    method = resolveInterfaceMethod(interfaze, name, desc);
                    if (method != null) {
                        return method;
                    }
                }
                c = !c.isInterface() && c.hasSuperclass() ? c.getSuperclass() : null;
            }
        }
        return null;
    }
    
    private SootMethod resolveInterfaceMethod(Function f, Invokeinterface t) {
        SootClass target = config.getClazzes().load(t.getTarget()).getSootClass();
        String name = t.getMethodName();
        String desc = t.getMethodDesc();
        if (!target.isInterface()) {
            throwIncompatibleChangeError(f, EXPECTED_INTERFACE_BUT_FOUND_CLASS, target);
            return null;
        }
        if ("<clinit>".equals(name) || "<init>".equals(name)) {
            // This is not part of interface method resolution but we 
            // need to handle it somehow.
            throwNoSuchMethodError(f, t);
            return null;
        }
        SootMethod method = resolveInterfaceMethod(target, name, desc);
        if (method == null) {
            SootClass javaLangObject = config.getClazzes().load("java/lang/Object").getSootClass();
            method = getMethod(javaLangObject, name, desc);
        }
        if (method == null) {
            throwNoSuchMethodError(f, t);
            return null;
        }
        if (method.isStatic()) {
            throwIncompatibleChangeError(f, EXPECTED_NON_STATIC_METHOD, 
                    target, name, desc);
            return null;
        }
        return method;
    }
    
    private SootMethod resolveInterfaceMethod(SootClass clazz, String name, String desc) {
        if (clazz != null && !clazz.isPhantom()) {
            SootMethod method = getMethod(clazz, name, desc);
            if (method != null) {
                return method;
            }

            for (SootClass interfaze : clazz.getInterfaces()) {
                method = resolveInterfaceMethod(interfaze, name, desc);
                if (method != null) {
                    return method;
                }
            }
        }
        return null;
    }
    
    private SootField getField(SootClass clazz, String name, String desc) {
        for (SootField f : clazz.getFields()) {
            if (name.equals(f.getName()) && desc.equals(getDescriptor(f))) {
                return f;
            }
        }
        return null;
    }
    
    private SootMethod getMethod(SootClass clazz, String name, String desc) {
        for (SootMethod m : clazz.getMethods()) {
            if (name.equals(m.getName()) && desc.equals(getDescriptor(m))) {
                return m;
            }
        }
        return null;
    }
}
