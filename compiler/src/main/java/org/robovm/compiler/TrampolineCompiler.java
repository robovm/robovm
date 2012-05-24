/**
 * 
 */
package org.robovm.compiler;

import static org.robovm.compiler.Access.*;
import static org.robovm.compiler.Functions.*;
import static org.robovm.compiler.Mangler.*;
import static org.robovm.compiler.Types.*;
import static org.robovm.compiler.llvm.FunctionAttribute.*;
import static org.robovm.compiler.llvm.Linkage.*;
import static org.robovm.compiler.llvm.Type.*;

import java.util.Collections;

import org.robovm.compiler.clazz.Clazz;
import org.robovm.compiler.llvm.Bitcast;
import org.robovm.compiler.llvm.Br;
import org.robovm.compiler.llvm.FloatingPointConstant;
import org.robovm.compiler.llvm.FloatingPointType;
import org.robovm.compiler.llvm.Function;
import org.robovm.compiler.llvm.FunctionAttribute;
import org.robovm.compiler.llvm.FunctionDeclaration;
import org.robovm.compiler.llvm.FunctionRef;
import org.robovm.compiler.llvm.FunctionType;
import org.robovm.compiler.llvm.Global;
import org.robovm.compiler.llvm.GlobalRef;
import org.robovm.compiler.llvm.Icmp;
import org.robovm.compiler.llvm.Icmp.Condition;
import org.robovm.compiler.llvm.IntegerConstant;
import org.robovm.compiler.llvm.IntegerType;
import org.robovm.compiler.llvm.Label;
import org.robovm.compiler.llvm.Load;
import org.robovm.compiler.llvm.NullConstant;
import org.robovm.compiler.llvm.PointerType;
import org.robovm.compiler.llvm.Ret;
import org.robovm.compiler.llvm.Unreachable;
import org.robovm.compiler.llvm.Value;
import org.robovm.compiler.llvm.Variable;
import org.robovm.compiler.trampoline.Anewarray;
import org.robovm.compiler.trampoline.Checkcast;
import org.robovm.compiler.trampoline.ExceptionMatch;
import org.robovm.compiler.trampoline.FieldAccessor;
import org.robovm.compiler.trampoline.GetField;
import org.robovm.compiler.trampoline.Instanceof;
import org.robovm.compiler.trampoline.Invoke;
import org.robovm.compiler.trampoline.Invokeinterface;
import org.robovm.compiler.trampoline.Invokespecial;
import org.robovm.compiler.trampoline.Invokevirtual;
import org.robovm.compiler.trampoline.LdcClass;
import org.robovm.compiler.trampoline.LdcString;
import org.robovm.compiler.trampoline.Multianewarray;
import org.robovm.compiler.trampoline.NativeCall;
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

    public TrampolineCompiler(Config config) {
        this.config = config;
    }
    
    public void compile(ModuleBuilder mb, Trampoline t) {
        this.mb = mb;
        
        if (t instanceof LdcString) {
            Function f = new Function(weak, t.getFunctionRef());
            mb.addFunction(f);
            Value result = call(f, BC_LDC_STRING, f.getParameterRef(0), 
                    mb.getString(t.getTarget()));
            f.add(new Ret(result));
            return;
        }
        
        /*
         * Check if the target class exists and is accessible. Also check that
         * field accesses and method calls are compatible with the target 
         * field/method and that the field/method is accessible to the caller.
         * If any of the tests fail the weak trampoline function created by the
         * ClassCompiler will be overridden with a function which throws an
         * appropriate exception.
         */
        Function f = new Function(external, t.getFunctionRef());
        if (!checkClassExists(f, t) || !checkClassAccessible(f, t)) {
            mb.addFunction(f);
            return;
        }

        if (t instanceof New) {
            SootClass target = config.getClazzes().load(t.getTarget()).getSootClass();
            if (target.isAbstract() || target.isInterface()) {
                call(f, BC_THROW_INSTANTIATION_ERROR, f.getParameterRef(0), mb.getString(t.getTarget()));
                f.add(new Unreachable());
                mb.addFunction(f);
                return;
            }
            String fnName = mangleClass(t.getTarget()) + "_allocator_clinit";
            alias(t, fnName);
        } else if (t instanceof ExceptionMatch) {
            String fnName = mangleClass(t.getTarget()) + "_exmatch";
            if (!mb.hasSymbol(fnName)) {
                Function fn = new Function(weak, t.getFunctionRef(), fnName);
                FunctionRef exInfoFn = getInfoStructFn(t.getTarget());
                Value exInfo = call(fn, exInfoFn);
                if (!mb.hasSymbol(exInfoFn.getName())) {
                    mb.addFunctionDeclaration(new FunctionDeclaration(exInfoFn));
                }
                Value result = call(fn, BC_EXCEPTION_MATCH, f.getParameterRef(0), exInfo);
                fn.add(new Ret(result));
                mb.addFunction(fn);
            }
            alias(t, fnName);
        } else if (t instanceof Instanceof) {
            if (isArray(t.getTarget())) {
                String fnName = "array_" + mangleClass(t.getTarget()) + "_instanceof";
                if (!mb.hasSymbol(fnName)) {
                    Function fn = new Function(weak, t.getFunctionRef(), fnName);
                    Value arrayClass = callLdcArray(fn, t.getTarget());
                    Value result = call(fn, BC_INSTANCEOF_ARRAY, fn.getParameterRef(0), arrayClass, fn.getParameterRef(1));
                    fn.add(new Ret(result));
                    mb.addFunction(fn);
                }
                alias(t, fnName);
            } else {
                String fnName = mangleClass(t.getTarget()) + "_instanceof_clinit";
                alias(t, fnName);
            }
        } else if (t instanceof Checkcast) {
            if (isArray(t.getTarget())) {
                String fnName = "array_" + mangleClass(t.getTarget()) + "_checkcast";
                if (!mb.hasSymbol(fnName)) {
                    Function fn = new Function(weak, t.getFunctionRef(), fnName);
                    Value arrayClass = callLdcArray(fn, t.getTarget());
                    Value result = call(fn, BC_CHECKCAST_ARRAY, fn.getParameterRef(0), arrayClass, fn.getParameterRef(1));
                    fn.add(new Ret(result));
                    mb.addFunction(fn);
                }
                alias(t, fnName);
            } else {
                String fnName = mangleClass(t.getTarget()) + "_checkcast_clinit";
                alias(t, fnName);
            }
        } else if (t instanceof LdcClass) {
            if (isArray(t.getTarget())) {
                FunctionRef fn = createLdcArray(t.getTarget());
                alias(t, fn.getName());
            } else {
                String fnName = mangleClass(t.getTarget()) + "_ldc_clinit";
                alias(t, fnName);
            }
        } else if (t instanceof Anewarray) {
            String fnName = "array_" + mangleClass(t.getTarget()) + "_new";
            if (!mb.hasSymbol(fnName)) {
                Function fn = new Function(weak, t.getFunctionRef(), fnName);
                Value arrayClass = callLdcArray(fn, t.getTarget());
                Value result = call(fn, BC_NEW_OBJECT_ARRAY, fn.getParameterRef(0), 
                      fn.getParameterRef(1), arrayClass);
                fn.add(new Ret(result));
                mb.addFunction(fn);
            }
            alias(t, fnName);
        } else if (t instanceof Multianewarray) {
            String fnName = "array_" + mangleClass(t.getTarget()) + "_multi";
            if (!mb.hasSymbol(fnName)) {
                Function fn = new Function(weak, t.getFunctionRef(), fnName);
                Value arrayClass = callLdcArray(fn, t.getTarget());
                Value result = call(fn, BC_NEW_MULTI_ARRAY, fn.getParameterRef(0), 
                      fn.getParameterRef(1), fn.getParameterRef(2), arrayClass);
                fn.add(new Ret(result));
                mb.addFunction(fn);
            }
            alias(t, fnName);
        } else if (t instanceof NativeCall) {
            Clazz target = config.getClazzes().load(t.getTarget());
            NativeCall nc = (NativeCall) t;
            String shortName = mangleNativeMethod(target.getInternalName(), nc.getMethodName());
            String longName = mangleNativeMethod(target.getInternalName(), nc.getMethodName(), nc.getMethodDesc());
            if (target.isInBootClasspath()) {
                Function fnLong = new Function(weak, longName, nc.getFunctionType());
                call(fnLong, BC_THROW_UNSATISIFED_LINK_ERROR, fnLong.getParameterRef(0));
                fnLong.add(new Unreachable());
                mb.addFunction(fnLong);
//                mb.addFunctionDeclaration(new FunctionDeclaration(fnLong.ref()));
                FunctionRef targetFn = fnLong.ref();
                if (!isLongNativeFunctionNameRequired(nc)) {
                    Function fnShort = new Function(weak, shortName, nc.getFunctionType());
                    Value resultInner = call(fnShort, fnLong.ref(), fnShort.getParameterRefs());
                    fnShort.add(new Ret(resultInner));
                    mb.addFunction(fnShort);
//                    mb.addFunctionDeclaration(new FunctionDeclaration(fnShort.ref()));
                    targetFn = fnShort.ref();
                }
                Function fn = new Function(external, nc.getFunctionRef());
                Value result = call(fn, targetFn, fn.getParameterRefs());
                fn.add(new Ret(result));
                mb.addFunction(fn);
            } else {
                Global g = new Global("native_" + mangleMethod(nc.getTarget(), 
                        nc.getMethodName(), nc.getMethodDesc()) + "_ptr", 
                        new NullConstant(I8_PTR));
                mb.addGlobal(g);
                Function fn = new Function(external, nc.getFunctionRef());
                FunctionRef ldcFn = new FunctionRef(mangleClass(nc.getTarget()) + "_ldc", 
                        new FunctionType(OBJECT_PTR, ENV_PTR));
                Value theClass = call(fn, ldcFn, fn.getParameterRef(0));
                Value implI8Ptr = call(fn, BC_RESOLVE_NATIVE, fn.getParameterRef(0), 
                      theClass,
                      mb.getString(nc.getMethodName()), 
                      mb.getString(nc.getMethodDesc()),
                      mb.getString(mangleNativeMethod(nc.getTarget(), nc.getMethodName())),
                      mb.getString(mangleNativeMethod(nc.getTarget(), nc.getMethodName(), nc.getMethodDesc())),
                      g.ref());
                Variable nullTest = fn.newVariable(I1);
                fn.add(new Icmp(nullTest, Condition.ne, implI8Ptr, new NullConstant(I8_PTR)));
                Label trueLabel = new Label();
                Label falseLabel = new Label();
                fn.add(new Br(nullTest.ref(), fn.newBasicBlockRef(trueLabel), fn.newBasicBlockRef(falseLabel)));
                fn.newBasicBlock(falseLabel);
                if (fn.getType().getReturnType() instanceof IntegerType) {
                    fn.add(new Ret(new IntegerConstant(0, (IntegerType) fn.getType().getReturnType())));
                } else if (fn.getType().getReturnType() instanceof FloatingPointType) {
                    fn.add(new Ret(new FloatingPointConstant(0.0, (FloatingPointType) fn.getType().getReturnType())));
                } else if (fn.getType().getReturnType() instanceof PointerType) {
                    fn.add(new Ret(new NullConstant((PointerType) fn.getType().getReturnType())));
                } else {
                    fn.add(new Ret());
                }
                fn.newBasicBlock(trueLabel);
                Variable impl = fn.newVariable(nc.getFunctionType());
                fn.add(new Bitcast(impl, implI8Ptr, impl.getType()));
                Value result = call(fn, impl.ref(), fn.getParameterRefs());
                fn.add(new Ret(result));
                mb.addFunction(fn);
            }
        } else if (t instanceof FieldAccessor) {
            SootField field = resolveField(f, (FieldAccessor) t);
            if (field == null || !checkMemberAccessible(f, t, field)) {
                mb.addFunction(f);
                return;
            }
            Clazz caller = config.getClazzes().load(t.getCallingClass());
            Clazz target = config.getClazzes().load(t.getTarget());
            if (!((FieldAccessor) t).isGetter() &&  field.isFinal() && caller != target) {
                // Only the class declaring a final field may write to it.
                // (Actually only <init>/<clinit> methods may write to it but we 
                // don't know which method is accessing the field at this point)
                throwIllegalAccessError(f, ATTEMPT_TO_WRITE_TO_FINAL_FIELD, 
                        target, field.getName(), caller);
                mb.addFunction(f);
                return;
            }
            createTrampolineAliasForField((FieldAccessor) t, field);
        } else if (t instanceof Invokeinterface) {
            SootMethod rm = resolveInterfaceMethod(f, (Invokeinterface) t);
            if (rm == null || !checkMemberAccessible(f, t, rm)) {
                mb.addFunction(f);
                return;
            }
            createTrampolineAliasForMethod((Invoke) t, rm);
        } else if (t instanceof Invoke) {
            SootMethod method = resolveMethod(f, (Invoke) t);
            if (method == null || !checkMemberAccessible(f, t, method)) {
                mb.addFunction(f);
                return;
            }
            if (t instanceof Invokespecial && method.isAbstract()) {
                call(f, BC_THROW_ABSTRACT_METHOD_ERROR, f.getParameterRef(0), 
                        mb.getString(String.format(NO_SUCH_METHOD_ERROR, 
                                method.getDeclaringClass(), method.getName(), 
                                getDescriptor(method))));
                f.add(new Unreachable());
                mb.addFunction(f);
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
        Function fn = new Function(_private, new FunctionAttribute[] {alwaysinline, optsize}, 
                t.getFunctionName(), t.getFunctionType());
        Value result = call(fn, aliasee, fn.getParameterRefs());
        fn.add(new Ret(result));
        mb.addFunction(fn);
    }
    
    private void createTrampolineAliasForField(FieldAccessor t, SootField field) {
        String fnName = mangleField(field);
        fnName += t.isGetter() ? "_getter" : "_setter";
        if (t.isStatic()) {
            fnName += "_clinit";
        }
        if (!mb.hasSymbol(fnName)) {
            
        }
        alias(t, fnName);
    }
    
    private void createTrampolineAliasForMethod(Invoke t, SootMethod rm) {
        String fnName = mangleMethod(rm);
        if (t instanceof Invokeinterface) {
            fnName += "_lookup";
        } else if (t instanceof Invokevirtual 
                && !Modifier.isFinal(rm.getDeclaringClass().getModifiers()) 
                && !Modifier.isFinal(rm.getModifiers())) {
            fnName += "_lookup";
        } else if (rm.isSynchronized()) {
            fnName += "_synchronized";
        }
        if (t.isStatic()) {
            fnName += "_clinit";
        }
        alias(t, fnName);
    }
    
    private boolean isLongNativeFunctionNameRequired(NativeCall nc) {
        if (nc.getMethodDesc().startsWith("()")) {
            // If the method takes no parameters the long and short names are the same
            return true;
        }
        Clazz target = config.getClazzes().load(nc.getTarget());
        int nativeCount = 0;
        for (SootMethod m : target.getSootClass().getMethods()) {
            if (m.isNative() && m.getName().equals(nc.getMethodName())) {
                nativeCount++;
            }
        }
        return nativeCount > 1;
    }
    
    private Value callLdcArray(Function function, String targetClass) {
        FunctionRef fnRef = createLdcArray(targetClass);
        return call(function, fnRef, function.getParameterRef(0));
    }
    
    private FunctionRef createLdcArray(String targetClass) {
        String fnName = "array_" + mangleClass(targetClass) + "_ldc";
        FunctionRef fnRef = new FunctionRef(fnName, new FunctionType(OBJECT_PTR, ENV_PTR));
        if (!mb.hasSymbol(fnName)) {
            Function fn = new Function(weak, fnRef, fnName);
            Value arrayClass = null;
            if (isPrimitiveComponentType(targetClass)) {
                String primitiveDesc = targetClass.substring(1);
                Variable result = fn.newVariable(OBJECT_PTR);
                fn.add(new Load(result, new GlobalRef("array_" + primitiveDesc, OBJECT_PTR)));
                arrayClass = result.ref();
            } else {
                Global g = new Global("array_" + mangleClass(targetClass) + "_ptr", weak, new NullConstant(OBJECT_PTR));
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
                arrayClass = call(fn, ldcArrayClassFn, fn.getParameterRef(0), g.ref(), mb.getString(targetClass));
            }
            fn.add(new Ret(arrayClass));
            mb.addFunction(fn);
        }
        return fnRef;
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
        if (Access.checkClassAccessible(target.getSootClass(), caller.getSootClass())) {
            return true;
        }
        throwIllegalAccessError(f, ILLEGAL_ACCESS_ERROR_CLASS, 
                target, caller);
        f.add(new Unreachable());
        return false;
    }

    private boolean checkMemberAccessible(Function f, Trampoline t, ClassMember member) {
        SootClass caller = config.getClazzes().load(t.getCallingClass()).getSootClass();

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
            runtimeClass = config.getClazzes().load(runtimeClassName).getSootClass();
        }
        
        if (Access.checkMemberAccessible(member, caller, runtimeClass)) {
            return true;
        }

        if (member instanceof SootMethod) {
            SootMethod method = (SootMethod) member;
            throwIllegalAccessError(f, ILLEGAL_ACCESS_ERROR_METHOD, 
                    method.getDeclaringClass(), method.getName(), 
                    getDescriptor(method), caller);
        } else {
            SootField field = (SootField) member;
            throwIllegalAccessError(f, ILLEGAL_ACCESS_ERROR_FIELD, 
                    field.getDeclaringClass(), field.getName(), caller);
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
        if (target.isInterface()) {
            throwIncompatibleChangeError(f, EXPECTED_CLASS_BUT_FOUND_INTERFACE, target);
            return null;
        }
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
