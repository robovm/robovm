/*
 * Copyright (C) 2014 RoboVM AB
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
package org.robovm.compiler.plugin.objc;

import static org.robovm.compiler.Annotations.*;
import static soot.Modifier.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import org.robovm.compiler.Annotations;
import org.robovm.compiler.Annotations.Visibility;
import org.robovm.compiler.CompilerException;
import org.robovm.compiler.MarshalerLookup.MarshalSite;
import org.robovm.compiler.MarshalerLookup.MarshalerMethod;
import org.robovm.compiler.ModuleBuilder;
import org.robovm.compiler.Types;
import org.robovm.compiler.clazz.Clazz;
import org.robovm.compiler.config.Config;
import org.robovm.compiler.plugin.AbstractCompilerPlugin;
import org.robovm.compiler.plugin.CompilerPlugin;
import org.robovm.compiler.util.generic.SootMethodType;

import soot.Body;
import soot.BooleanType;
import soot.DoubleType;
import soot.FloatType;
import soot.Local;
import soot.LongType;
import soot.Modifier;
import soot.PatchingChain;
import soot.PrimType;
import soot.RefLikeType;
import soot.RefType;
import soot.Scene;
import soot.SootClass;
import soot.SootField;
import soot.SootFieldRef;
import soot.SootMethod;
import soot.SootMethodRef;
import soot.SootResolver;
import soot.Type;
import soot.Unit;
import soot.Value;
import soot.VoidType;
import soot.jimple.ClassConstant;
import soot.jimple.IntConstant;
import soot.jimple.InvokeExpr;
import soot.jimple.InvokeStmt;
import soot.jimple.Jimple;
import soot.jimple.StaticInvokeExpr;
import soot.jimple.Stmt;
import soot.jimple.StringConstant;
import soot.tagkit.AnnotationStringElem;
import soot.tagkit.AnnotationTag;
import soot.tagkit.SignatureTag;
import soot.util.Chain;

/**
 * {@link CompilerPlugin} which transforms Objective-C methods and properties to @Bridge
 * methods. Also adds corresponding @Callback methods for each method and
 * property.
 */
public class ObjCMemberPlugin extends AbstractCompilerPlugin {
    public static final String OBJC_ANNOTATIONS_PACKAGE = "org/robovm/objc/annotation";
    public static final String METHOD = "L" + OBJC_ANNOTATIONS_PACKAGE + "/Method;";
    public static final String PROPERTY = "L" + OBJC_ANNOTATIONS_PACKAGE + "/Property;";
    public static final String BIND_SELECTOR = "L" + OBJC_ANNOTATIONS_PACKAGE + "/BindSelector;";
    public static final String NOT_IMPLEMENTED = "L" + OBJC_ANNOTATIONS_PACKAGE + "/NotImplemented;";
    public static final String IBACTION = "L" + OBJC_ANNOTATIONS_PACKAGE + "/IBAction;";
    public static final String IBOUTLET = "L" + OBJC_ANNOTATIONS_PACKAGE + "/IBOutlet;";
    public static final String IBOUTLETCOLLECTION = "L" + OBJC_ANNOTATIONS_PACKAGE + "/IBOutletCollection;";
    public static final String NATIVE_CLASS = "L" + OBJC_ANNOTATIONS_PACKAGE + "/NativeClass;";
    public static final String TYPE_ENCODING = "L" + OBJC_ANNOTATIONS_PACKAGE + "/TypeEncoding;";
    public static final String SELECTOR = "org.robovm.objc.Selector";
    public static final String OBJC_SUPER = "org.robovm.objc.ObjCSuper";
    public static final String OBJC_CLASS = "org.robovm.objc.ObjCClass";
    public static final String OBJC_OBJECT = "org.robovm.objc.ObjCObject";
    public static final String OBJC_RUNTIME = "org.robovm.objc.ObjCRuntime";
    public static final String OBJC_EXTENSIONS = "org.robovm.objc.ObjCExtensions";
    public static final String NS_OBJECT = "org.robovm.apple.foundation.NSObject";
    public static final String NS_OBJECT$MARSHALER = "org.robovm.apple.foundation.NSObject$Marshaler";
    public static final String NS_STRING$AS_STRING_MARSHALER = "org.robovm.apple.foundation.NSString$AsStringMarshaler";
    public static final String $M = "org.robovm.objc.$M";
    public static final String UI_EVENT = "org.robovm.apple.uikit.UIEvent";
    public static final String NS_ARRAY = "org.robovm.apple.foundation.NSArray";

    private boolean initialized = false;
    private Config config;
    private SootClass org_robovm_objc_ObjCClass = null;
    private SootClass org_robovm_objc_ObjCSuper = null;
    private SootClass org_robovm_objc_ObjCObject = null;
    private SootClass org_robovm_objc_ObjCRuntime = null;
    private SootClass org_robovm_objc_ObjCExtensions = null;
    private SootClass org_robovm_objc_Selector = null;
    private SootClass java_lang_String = null;
    private SootClass java_lang_Class = null;
    private SootClass org_robovm_apple_foundation_NSObject = null;
    private SootClass org_robovm_objc_$M = null;
    private SootClass org_robovm_apple_uikit_UIEvent = null;
    private SootClass org_robovm_apple_foundation_NSArray = null;
    private SootClass org_robovm_apple_foundation_NSObject$Marshaler = null;
    private SootClass org_robovm_apple_foundation_NSString$AsStringMarshaler = null;
    private SootMethodRef org_robovm_objc_ObjCObject_getSuper = null;
    private SootFieldRef org_robovm_objc_ObjCObject_customClass = null;
    private SootMethodRef org_robovm_objc_ObjCClass_getByType = null;
    private SootMethodRef org_robovm_objc_ObjCRuntime_bind = null;
    private SootMethodRef org_robovm_objc_ObjCObject_updateStrongRef = null;
    private SootMethodRef org_robovm_objc_ObjCExtensions_updateStrongRef = null;
    private SootMethodRef org_robovm_objc_Selector_register = null;

    static SootMethod getOrCreateStaticInitializer(SootClass sootClass) {
        for (SootMethod m : sootClass.getMethods()) {
            if ("<clinit>".equals(m.getName())) {
                return m;
            }
        }
        SootMethod m = new SootMethod("<clinit>", Collections.emptyList(), VoidType.v(), STATIC);
        Body body = Jimple.v().newBody(m);
        body.getUnits().add(Jimple.v().newReturnVoidStmt());
        m.setActiveBody(body);
        sootClass.addMethod(m);
        return m;
    }

    private String getSelectorFieldName(String selectorName) {
        return "$sel$" + selectorName.replace(':', '$');
    }

    private SootField getSelectorField(String selectorName) {
        return new SootField(getSelectorFieldName(selectorName),
                org_robovm_objc_Selector.getType(), STATIC | PRIVATE | FINAL);
    }

    @SuppressWarnings("unchecked")
    private SootMethod getMsgSendMethod(String selectorName, SootMethod method, SootMethod annotatedMethod, boolean isCallback,
            Type receiverType, boolean extensions) {

        List<Type> paramTypes = new ArrayList<>();
        if (extensions) {
            paramTypes.add(method.getParameterType(0));
        } else if (method.isStatic()) {
            paramTypes.add(org_robovm_objc_ObjCClass.getType());
        } else {
            paramTypes.add(receiverType == null ? method.getDeclaringClass().getType() : receiverType);
        }
        paramTypes.add(org_robovm_objc_Selector.getType());
        if (extensions) {
            paramTypes.addAll(method.getParameterTypes().subList(1, method.getParameterTypes().size()));
        } else {
            paramTypes.addAll(method.getParameterTypes());
        }
        SootMethod m = new SootMethod((isCallback ? "$cb$" : "$m$") + selectorName.replace(':', '$'),
                paramTypes, method.getReturnType(), STATIC | PRIVATE | (isCallback ? 0 : NATIVE));
        copyAnnotations(annotatedMethod, m, extensions);
        
        createGenericSignatureForMsgSend(annotatedMethod, m, paramTypes, extensions);

        return m;
    }

    private void createGenericSignatureForMsgSend(SootMethod annotatedMethod, SootMethod m, List<Type> paramTypes, boolean extensions) {
        SignatureTag tag = (SignatureTag) annotatedMethod.getTag(SignatureTag.class.getSimpleName());
        if (tag != null) {
            String signature = null;
            if (extensions) {
                // We need to insert a Selector as parameter 2.
                SootMethodType type = new SootMethodType(annotatedMethod);
                StringBuilder sb = new StringBuilder("(");
                org.robovm.compiler.util.generic.Type[] genericParameterTypes = type.getGenericParameterTypes();
                sb.append(genericParameterTypes[0].toGenericSignature());
                sb.append(Types.getDescriptor(org_robovm_objc_Selector.getType()));
                for (int i = 1; i < genericParameterTypes.length; i++) {
                    sb.append(genericParameterTypes[i].toGenericSignature());
                }
                sb.append(")");
                sb.append(type.getGenericReturnType().toGenericSignature());
                signature = sb.toString();
            } else {
                // For non extensions we just need to prepend the receiver type
                // and Selector to the generic signature.
                signature = "(" + Types.getDescriptor(paramTypes.get(0)) + Types.getDescriptor(paramTypes.get(1))
                        + tag.getSignature().substring(1);
            }
            m.addTag(new SignatureTag(signature));
        }
    }

    private static void copyAnnotations(SootMethod fromMethod, SootMethod toMethod, boolean extensions) {
        Annotations.copyAnnotations(fromMethod, toMethod, Visibility.RuntimeVisible);
        if (extensions) {
            copyParameterAnnotations(fromMethod, toMethod, 0, 1, 0, Visibility.RuntimeVisible);
            if (fromMethod.getParameterCount() > 1) {
                copyParameterAnnotations(fromMethod, toMethod, 1, fromMethod.getParameterCount(), 1, Visibility.RuntimeVisible);
            }
        } else {
            copyParameterAnnotations(fromMethod, toMethod, 0, fromMethod.getParameterCount(), 2, Visibility.RuntimeVisible);
        }
    }

    private SootMethod getMsgSendMethod(String selectorName, SootMethod method, boolean extensions) {
        return getMsgSendMethod(selectorName, method, method, false, null, extensions);
    }

    @SuppressWarnings("unchecked")
    private SootMethod getMsgSendSuperMethod(String selectorName, SootMethod method) {
        List<Type> paramTypes = new ArrayList<>();
        paramTypes.add(org_robovm_objc_ObjCSuper.getType());
        paramTypes.add(org_robovm_objc_Selector.getType());
        paramTypes.addAll(method.getParameterTypes());
        SootMethod m = new SootMethod("$m$super$" + selectorName.replace(':', '$'),
                paramTypes, method.getReturnType(), STATIC | PRIVATE | NATIVE);
        copyAnnotations(method, m, false);
        createGenericSignatureForMsgSend(method, m, paramTypes, false);
        return m;
    }

    private SootMethod getCallbackMethod(String selectorName, SootMethod method, SootMethod annotatedMethod, Type receiverType) {
        return getMsgSendMethod(selectorName, method, annotatedMethod, true, receiverType, false);
    }

    private void addBindCall(SootClass sootClass) {
        Jimple j = Jimple.v();

        SootMethod clinit = getOrCreateStaticInitializer(sootClass);
        Body body = clinit.retrieveActiveBody();

        String internalName = sootClass.getName().replace('.', '/');
        ClassConstant c = ClassConstant.v(internalName);

        Chain<Unit> units = body.getUnits();

        // Don't call bind if there's already a call in the static initializer
        for (Unit unit : units) {
            if (unit instanceof InvokeStmt) {
                InvokeStmt stmt = (InvokeStmt) unit;
                if (stmt.getInvokeExpr() instanceof StaticInvokeExpr) {
                    StaticInvokeExpr expr = (StaticInvokeExpr) stmt.getInvokeExpr();
                    SootMethodRef ref = expr.getMethodRef();
                    if (ref.isStatic() && ref.declaringClass().equals(org_robovm_objc_ObjCRuntime)
                            && ref.name().equals("bind")) {
                        if (ref.parameterTypes().isEmpty() || expr.getArg(0).equals(c)) {
                            return;
                        }
                    }
                }
            }
        }

        // Call ObjCRuntime.bind(<class>)
        units.insertBefore(
                j.newInvokeStmt(
                        j.newStaticInvokeExpr(
                                org_robovm_objc_ObjCRuntime_bind,
                                ClassConstant.v(internalName))),
                units.getLast()
                );
    }

    private void addObjCClassField(SootClass sootClass) {
        Jimple j = Jimple.v();

        SootMethod clinit = getOrCreateStaticInitializer(sootClass);
        Body body = clinit.retrieveActiveBody();

        Local objCClass = Jimple.v().newLocal("$objCClass", org_robovm_objc_ObjCClass.getType());
        body.getLocals().add(objCClass);

        Chain<Unit> units = body.getUnits();

        SootField f = new SootField("$objCClass", org_robovm_objc_ObjCClass.getType(),
                STATIC | PRIVATE | FINAL);
        sootClass.addField(f);

        units.insertBefore(
                Arrays.<Unit> asList(
                        j.newAssignStmt(
                                objCClass,
                                j.newStaticInvokeExpr(
                                        org_robovm_objc_ObjCClass_getByType,
                                        ClassConstant.v(sootClass.getName().replace('.', '/')))),
                        j.newAssignStmt(
                                j.newStaticFieldRef(f.makeRef()),
                                objCClass)
                ),
                units.getLast()
                );
    }

    private void registerSelectors(SootClass sootClass, Set<String> selectors) {
        Jimple j = Jimple.v();

        SootMethod clinit = getOrCreateStaticInitializer(sootClass);
        Body body = clinit.retrieveActiveBody();

        Local sel = Jimple.v().newLocal("$sel", org_robovm_objc_Selector.getType());
        body.getLocals().add(sel);

        Chain<Unit> units = body.getUnits();

        for (String selectorName : selectors) {
            SootField f = getSelectorField(selectorName);
            sootClass.addField(f);

            units.insertBefore(
                    Arrays.<Unit> asList(
                            j.newAssignStmt(
                                    sel,
                                    j.newStaticInvokeExpr(
                                            org_robovm_objc_Selector_register,
                                            StringConstant.v(selectorName))),
                            j.newAssignStmt(
                                    j.newStaticFieldRef(f.makeRef()),
                                    sel)
                            ),
                    units.getLast()
                    );
        }
    }

    private void init(Config config) {
        if (initialized) {
            return;
        }
        this.config = config;
        if (config.getClazzes().load(OBJC_OBJECT.replace('.', '/')) == null) {
            initialized = true;
            return;
        }
        SootResolver r = SootResolver.v();
        // These have to be resolved to HIERARCHY so that isPhantom() works
        // properly
        org_robovm_objc_ObjCObject = r.resolveClass(OBJC_OBJECT, SootClass.HIERARCHY);
        org_robovm_objc_ObjCExtensions = r.resolveClass(OBJC_EXTENSIONS, SootClass.HIERARCHY);
        // These only have to be DANGLING
        org_robovm_objc_ObjCClass = r.makeClassRef(OBJC_CLASS);
        org_robovm_objc_ObjCSuper = r.makeClassRef(OBJC_SUPER);
        org_robovm_objc_ObjCRuntime = r.makeClassRef(OBJC_RUNTIME);
        org_robovm_objc_Selector = r.makeClassRef(SELECTOR);
        org_robovm_apple_foundation_NSObject = r.makeClassRef(NS_OBJECT);
        org_robovm_apple_foundation_NSObject$Marshaler = r.makeClassRef(NS_OBJECT$MARSHALER);
        org_robovm_apple_foundation_NSString$AsStringMarshaler = r.makeClassRef(NS_STRING$AS_STRING_MARSHALER);
        org_robovm_objc_$M = r.makeClassRef($M);
        org_robovm_apple_uikit_UIEvent = r.makeClassRef(UI_EVENT);
        org_robovm_apple_foundation_NSArray = r.makeClassRef(NS_ARRAY);
        SootClass java_lang_Object = r.makeClassRef("java.lang.Object");
        java_lang_String = r.makeClassRef("java.lang.String");
        java_lang_Class = r.makeClassRef("java.lang.Class");

        org_robovm_objc_Selector_register =
                Scene.v().makeMethodRef(
                        org_robovm_objc_Selector,
                        "register",
                        Arrays.<Type> asList(java_lang_String.getType()),
                        org_robovm_objc_Selector.getType(), true);
        org_robovm_objc_ObjCObject_getSuper =
                Scene.v().makeMethodRef(
                        org_robovm_objc_ObjCObject,
                        "getSuper",
                        Collections.<Type> emptyList(),
                        org_robovm_objc_ObjCSuper.getType(), false);
        org_robovm_objc_ObjCObject_updateStrongRef =
                Scene.v().makeMethodRef(
                        org_robovm_objc_ObjCObject,
                        "updateStrongRef",
                        Arrays.<Type> asList(
                                java_lang_Object.getType(),
                                java_lang_Object.getType()),
                        VoidType.v(), false);
        org_robovm_objc_ObjCClass_getByType =
                Scene.v().makeMethodRef(
                        org_robovm_objc_ObjCClass,
                        "getByType",
                        Arrays.<Type> asList(java_lang_Class.getType()),
                        org_robovm_objc_ObjCClass.getType(), true);
        org_robovm_objc_ObjCRuntime_bind =
                Scene.v().makeMethodRef(
                        org_robovm_objc_ObjCRuntime,
                        "bind",
                        Arrays.<Type> asList(java_lang_Class.getType()),
                        VoidType.v(), true);
        org_robovm_objc_ObjCObject_customClass =
                Scene.v().makeFieldRef(
                        org_robovm_objc_ObjCObject,
                        "customClass", BooleanType.v(), false);
        org_robovm_objc_ObjCExtensions_updateStrongRef =
                Scene.v().makeMethodRef(
                        org_robovm_objc_ObjCExtensions,
                        "updateStrongRef",
                        Arrays.<Type> asList(
                                org_robovm_objc_ObjCObject.getType(),
                                java_lang_Object.getType(),
                                java_lang_Object.getType()),
                        VoidType.v(), true);
        initialized = true;
    }

    private boolean isAssignable(SootClass cls, SootClass type) {
        if (type == null || type.isPhantom()) {
            return false;
        }
        while (cls != type && cls.hasSuperclass()) {
            cls = cls.getSuperclass();
        }
        return cls == type;
    }

    private boolean isObjCObject(SootClass cls) {
        return isAssignable(cls, org_robovm_objc_ObjCObject);
    }

    private boolean isObjCExtensions(SootClass cls) {
        return isAssignable(cls, org_robovm_objc_ObjCExtensions);
    }

    private boolean isNSObject(Type type) {
        return (type instanceof RefType)
                && isAssignable(((RefType) type).getSootClass(), org_robovm_apple_foundation_NSObject);
    }

    private boolean isUIEvent(Type type) {
        return (type instanceof RefType)
                && isAssignable(((RefType) type).getSootClass(), org_robovm_apple_uikit_UIEvent);
    }

    private boolean isSelector(Type type) {
        return (type instanceof RefType)
                && isAssignable(((RefType) type).getSootClass(), org_robovm_objc_Selector);
    }

    private boolean isNSArray(Type type) {
        return (type instanceof RefType)
                && isAssignable(((RefType) type).getSootClass(), org_robovm_apple_foundation_NSArray);
    }

    private boolean isNSObject$Marshaler_toNative(SootMethod method) {
        return method.getDeclaringClass().getType().equals(org_robovm_apple_foundation_NSObject$Marshaler.getType())
                && method.getName().equals("toNative") && method.getParameterCount() == 2
                && method.getParameterType(0).equals(org_robovm_apple_foundation_NSObject.getType())
                && method.getParameterType(1).equals(LongType.v())
                && method.getReturnType().equals(LongType.v());
    }

    private boolean isNSObject$Marshaler_toObject(SootMethod method) {
        return method.getDeclaringClass().getType().equals(org_robovm_apple_foundation_NSObject$Marshaler.getType())
                && method.getName().equals("toObject") && method.getParameterCount() == 3
                && method.getParameterType(0).equals(java_lang_Class.getType())
                && method.getParameterType(1).equals(LongType.v())
                && method.getParameterType(2).equals(LongType.v())
                && method.getReturnType().equals(org_robovm_apple_foundation_NSObject.getType());
    }

    private boolean isNSString$AsStringMarshaler_toNative(SootMethod method) {
        return method.getDeclaringClass().getType().equals(org_robovm_apple_foundation_NSString$AsStringMarshaler.getType())
                && method.getName().equals("toNative") && method.getParameterCount() == 2
                && method.getParameterType(0).equals(java_lang_String.getType())
                && method.getParameterType(1).equals(LongType.v())
                && method.getReturnType().equals(LongType.v());
    }

    private boolean isNSString$AsStringMarshaler_toObject(SootMethod method) {
        return method.getDeclaringClass().getType().equals(org_robovm_apple_foundation_NSString$AsStringMarshaler.getType())
                && method.getName().equals("toObject") && method.getParameterCount() == 3
                && method.getParameterType(0).equals(java_lang_Class.getType())
                && method.getParameterType(1).equals(LongType.v())
                && method.getParameterType(2).equals(LongType.v())
                && method.getReturnType().equals(java_lang_String.getType());
    }

    private boolean isCustomClass(SootClass cls) {
        return !hasAnnotation(cls, NATIVE_CLASS) && isNSObject(cls.getType());
    }

    @Override
    public void beforeClass(Config config, Clazz clazz, ModuleBuilder moduleBuilder) {
        init(config);
        SootClass sootClass = clazz.getSootClass();
        boolean extensions = false;
        if (!sootClass.isInterface()
                && (isObjCObject(sootClass) || (extensions = isObjCExtensions(sootClass)))) {

            Set<String> selectors = new TreeSet<>();
            Set<String> overridables = new HashSet<>();
            for (SootMethod method : sootClass.getMethods()) {
                if (!"<clinit>".equals(method.getName()) && !"<init>".equals(method.getName())) {
                    transformMethod(config, clazz, sootClass, method, selectors, overridables, extensions);
                }
            }
            addBindCall(sootClass);
            if (!extensions) {
                addObjCClassField(sootClass);
            }
            registerSelectors(sootClass, selectors);
        }
    }

    private static <E> List<E> l(E head, List<E> tail) {
        LinkedList<E> l = new LinkedList<>(tail);
        l.addFirst(head);
        return l;
    }

    private boolean isOverridable(SootMethod method) {
        if (method.isStatic() || method.isPrivate()
                || (method.getModifiers() & Modifier.FINAL) != 0
                || (method.getDeclaringClass().getModifiers() & Modifier.FINAL) != 0) {
            return false;
        }
        return true;
    }

    private boolean checkOverridable(Set<String> overridables, String selectorName, SootMethod method) {
        boolean b = isOverridable(method);
        if (b && overridables.contains(selectorName)) {
            throw new CompilerException("Found multiple overridable @Method or @Property "
                    + "methods in " + method.getDeclaringClass() + " with the selector '"
                    + selectorName + "'.");
        }
        return b;
    }

    private void transformMethod(Config config, Clazz clazz, SootClass sootClass,
            SootMethod method, Set<String> selectors, Set<String> overridables, boolean extensions) {

        AnnotationTag annotation = getAnnotation(method, METHOD);
        if (annotation != null) {
            if (extensions && !(method.isStatic() && method.isNative())) {
                throw new CompilerException("Objective-C @Method method "
                        + method + " in extension class must be static and native.");
            }

            transformObjCMethod(annotation, sootClass, method, selectors, overridables, extensions);
            return;
        }

        annotation = getAnnotation(method, IBACTION);
        if (annotation != null) {
            if (method.isStatic() || method.isNative()) {
                throw new CompilerException("Objective-C @IBAction method "
                        + method + " must not be static or native.");
            }
            int paramCount = method.getParameterCount();
            Type param1 = paramCount > 0 ? method.getParameterType(0) : null;
            Type param2 = paramCount > 1 ? method.getParameterType(1) : null;
            if (method.getReturnType() != VoidType.v() || paramCount > 2
                    || (param1 != null && (!isNSObject(param1) && !isNSObject(param1)))
                    || (param2 != null && (!isUIEvent(param2) || isNSObject(param1)))) {
                throw new CompilerException("Objective-C @IBAction method "
                        + method + " does not have a supported signature. @IBAction methods"
                        + " must return void and either take no arguments, 1 argument of type NSObject"
                        + ", or 2 arguments of types NSObject and UIEvent.");
            }

            transformObjCMethod(annotation, sootClass, method, selectors, overridables, extensions);
            return;
        }

        annotation = getAnnotation(method, PROPERTY);
        if (annotation != null) {
            if (extensions && !(method.isStatic() && method.isNative())) {
                throw new CompilerException("Objective-C @Property method "
                        + method + " in extension class must be static and native.");
            }

            transformObjCProperty(annotation, "@Property", sootClass, method, selectors, overridables, extensions);
            return;
        }
        annotation = getAnnotation(method, IBOUTLET);
        if (annotation != null) {
            if (method.isStatic()) {
                throw new CompilerException("Objective-C @IBOutlet method "
                        + method + " must not be static.");
            }

            transformObjCProperty(annotation, "@IBOutlet", sootClass, method, selectors, overridables, extensions);
            return;
        }
        annotation = getAnnotation(method, IBOUTLETCOLLECTION);
        if (annotation != null) {
            if (method.isStatic()) {
                throw new CompilerException("Objective-C @IBOutletCollection method "
                        + method + " must not be static.");
            }
            if (method.getReturnType() != VoidType.v() && !isNSArray(method.getReturnType())
                    || method.getReturnType() == VoidType.v() && method.getParameterCount() == 1 && !isNSArray(method
                            .getParameterType(0))) {
                throw new CompilerException("Objective-C @IBOutletCollection method " + method
                        + " does not have a supported signature. "
                        + "@IBOutletCollection getter methods must return NSArray. "
                        + "@IBOutletCollection setter methods must have 1 parameter of type NSArray.");
            }

            transformObjCProperty(annotation, "@IBOutletCollection", sootClass, method, selectors, overridables,
                    extensions);
            return;
        }
        
        if (!method.isStatic() && !method.isNative() && !method.isAbstract() && !method.isPrivate() 
                && isCustomClass(sootClass) && !hasAnnotation(method, NOT_IMPLEMENTED)) {
            /*
             * Create a @Callback for this method if it overrides a
             * @Method/@Property method in a superclass/interface.
             */
            List<SootMethod> superMethods = findOverriddenMethods(sootClass, method);
            for (SootMethod superMethod : superMethods) {
                if (createCustomClassCallbackIfNeeded(sootClass, method, superMethod)) {
                    break;
                }
            }
        }
    }

    private boolean createCustomClassCallbackIfNeeded(SootClass sootClass, SootMethod method, SootMethod superMethod) {
        AnnotationTag annotation = getAnnotation(superMethod, METHOD);
        if (annotation != null) {
            createCallback(sootClass, method, superMethod, getObjCMethodSelectorName(annotation, superMethod, false),
                    getReceiverType(sootClass));
            return true;
        } else {
            annotation = getAnnotation(superMethod, PROPERTY);
            if (annotation != null) {
                boolean isGetter = method.getReturnType() != VoidType.v();
                createCallback(sootClass, method, superMethod, getObjCPropertySelectorName(annotation, superMethod, isGetter),
                        getReceiverType(sootClass));
                return true;
            }
        }
        return false;
    }

    private List<SootMethod> findOverriddenMethods(SootClass sootClass, SootMethod method) {
        SootClass supercls = sootClass.getSuperclass();
        while (!supercls.getType().equals(org_robovm_objc_ObjCObject.getType())) {
            try {
                SootMethod m = supercls.getMethod(method.getName(), method.getParameterTypes(),
                        method.getReturnType());
                if (overrides(method, m) && !hasAnnotation(m, NOT_IMPLEMENTED)) {
                    return Collections.singletonList(m);
                }
            } catch (RuntimeException e) {
                // Soot throws RuntimeException if method not found
            }
            supercls = supercls.getSuperclass();
        }

        /*
         * Method doesn't override any method in superclasses. Check interfaces
         * as well. There may be several methods in interfaces which this method
         * overrides. We have to return all of them as we cannot assume that
         * first one found has the @Method or @Property annotation.
         */
        List<SootMethod> candidates = new ArrayList<>();
        findOverriddenMethodsOnInterfaces(sootClass, method, candidates);

        return candidates;
    }

    private void findOverriddenMethodsOnInterfaces(SootClass sootClass, SootMethod method, List<SootMethod> candidates) {
        if (sootClass.isInterface()) {
            try {
                candidates.add(sootClass.getMethod(method.getName(), method.getParameterTypes(),
                        method.getReturnType()));
            } catch (RuntimeException e) {
                // Soot throws RuntimeException if method not found
            }
        }

        for (SootClass interfaze : sootClass.getInterfaces()) {
            findOverriddenMethodsOnInterfaces(interfaze, method, candidates);
        }
        if (!sootClass.isInterface() && sootClass.hasSuperclass()
                && !sootClass.getSuperclass().getType().equals(org_robovm_objc_ObjCObject.getType())) {
            findOverriddenMethodsOnInterfaces(sootClass.getSuperclass(), method, candidates);
        }
    }

    /**
     * Returns {@code true} if subMethod overrides superMethod. The signatures
     * are assumed to be the same when this is called.
     */
    private boolean overrides(SootMethod subMethod, SootMethod superMethod) {
        if (!superMethod.isPrivate() && !superMethod.isStatic()) {
            if (!superMethod.isPublic() && !superMethod.isProtected()) {
                /*
                 * Package private. The class of the methods must be in the same
                 * package.
                 */
                String package1 = superMethod.getDeclaringClass().getPackageName();
                String package2 = subMethod.getDeclaringClass().getPackageName();
                return package1.equals(package2);
            }
            
            /*
             * superMethod is public or protected. subMethod must be overriding
             * superMethod or else it would violate the JVM spec since an
             * overriding method must not have a more restrictive visibility
             * than the method it overrides.
             */
            return true;
        }
        return false;
    }

    private void transformObjCMethod(AnnotationTag annotation, SootClass sootClass,
            SootMethod method, Set<String> selectors, Set<String> overridables, boolean extensions) {

        // Determine the selector
        String selectorName = getObjCMethodSelectorName(annotation, method, extensions);

        // Create the @Bridge and @Callback methods needed for this selector
        if (!extensions && isCustomClass(sootClass)) {
            createCallback(sootClass, method, method, selectorName, getReceiverType(sootClass));
        }
        if (method.isNative()) {
            if (checkOverridable(overridables, selectorName, method)) {
                overridables.add(selectorName);
            }
            selectors.add(selectorName);
            createBridge(sootClass, method, selectorName, false, extensions);
        }
    }

    private Type getReceiverType(SootClass sootClass) {
        Type receiverType = ObjCProtocolProxyPlugin.isObjCProxy(sootClass)
                ? sootClass.getInterfaces().getFirst().getType()
                : sootClass.getType();
        return receiverType;
    }

    private void transformObjCProperty(AnnotationTag annotation, String javaAnnotation, SootClass sootClass,
            SootMethod method, Set<String> selectors, Set<String> overridables, boolean extensions) {

        int getterParamCount = extensions ? 1 : 0;
        int setterParamCount = extensions ? 2 : 1;
        if (method.getReturnType() != VoidType.v() && method.getParameterCount() != getterParamCount
                || method.getReturnType() == VoidType.v() && method.getParameterCount() != setterParamCount) {

            if (!extensions) {
                throw new CompilerException("Objective-C " + getAnnotationName(annotation) + " method " + method
                        + " does not have a supported signature. " + getAnnotationName(annotation) + " getter methods"
                        + " must take 0 arguments and must not return void. " + getAnnotationName(annotation)
                        + " setter methods must take 1 argument and return void.");
            }
            throw new CompilerException("Objective-C " + getAnnotationName(annotation) + " method " + method 
                    + " in extension class does not have a supported signature. " + getAnnotationName(annotation)
                    + " getter methods in extension classes" + " must take 1 argument (the 'this' reference) and " 
                    + "must not return void. " + getAnnotationName(annotation) + " setter methods in extension classes "
                    + "must take 2 arguments (first is the 'this' reference) and return void.");
        }

        boolean isGetter = method.getReturnType() != VoidType.v();

        // Determine the selector
        String selectorName = getObjCPropertySelectorName(annotation, method, isGetter);

        // Create the @Bridge and @Callback methods needed for this selector
        if (!extensions && isCustomClass(sootClass)) {
            createCallback(sootClass, method, method, selectorName, getReceiverType(sootClass));
        }
        if (method.isNative()) {
            if (checkOverridable(overridables, selectorName, method)) {
                overridables.add(selectorName);
            }
            selectors.add(selectorName);
            boolean strongRefSetter = !isGetter && readBooleanElem(annotation, "strongRef", false);
            createBridge(sootClass, method, selectorName, strongRefSetter, extensions);
        }
    }

    private String getAnnotationName(AnnotationTag annotation) {
        // annotation.getType() returns the descriptor Lcom/example/ClassName;
        String n = annotation.getType();
        n = n.substring(1, n.length() - 1);
        return "@" + n.substring(n.lastIndexOf('/') + 1);
    }

    private String getObjCMethodSelectorName(AnnotationTag annotation, SootMethod method, boolean extensions) {
        String selectorName = readStringElem(annotation, "selector", "").trim();
        if (selectorName.length() == 0) {
            int argCount = method.getParameterCount();
            if (IBACTION.equals(annotation.getType()) && argCount == 2) {
                selectorName = method.getName() + ":withEvent:";
            } else {
                StringBuilder sb = new StringBuilder(method.getName());
                for (int i = extensions ? 1 : 0; i < argCount; i++) {
                    sb.append(':');
                }
                selectorName = sb.toString();
            }
        }
        return selectorName;
    }

    private String getObjCPropertySelectorName(AnnotationTag annotation, SootMethod method,
            boolean isGetter) {
        String selectorName = readStringElem(annotation, "selector", "").trim();
        if (selectorName.length() == 0) {
            String methodName = method.getName();
            if (!(isGetter && methodName.startsWith("get") && methodName.length() > 3)
                    && !(isGetter && methodName.startsWith("is") && methodName.length() > 2)
                    && !(!isGetter && methodName.startsWith("set") && methodName.length() > 3)) {
                throw new CompilerException("Invalid Objective-C " + getAnnotationName(annotation) + " method name "
                        + method + ". " + getAnnotationName(annotation) + " methods without an explicit selector value "
                        + "must follow the Java beans property method naming convention.");
            }
            selectorName = methodName;
            if (isGetter) {
                selectorName = methodName.startsWith("is")
                        ? methodName.substring(2)
                        : methodName.substring(3);
                selectorName = selectorName.substring(0, 1).toLowerCase()
                        + selectorName.substring(1);
            } else {
                selectorName += ":";
            }
        }
        return selectorName;
    }

    private void createCallback(SootClass sootClass, SootMethod method, SootMethod annotatedMethod, String selectorName, Type receiverType) {
        Jimple j = Jimple.v();

        SootMethod callbackMethod = getCallbackMethod(selectorName, method, annotatedMethod, receiverType);
        sootClass.addMethod(callbackMethod);
        addCallbackAnnotation(callbackMethod);
        addBindSelectorAnnotation(callbackMethod, selectorName);
        if (!hasAnnotation(annotatedMethod, TYPE_ENCODING) && (isCustomClass(sootClass)
                || ObjCProtocolProxyPlugin.isObjCProxy(sootClass))) {
            String encoding = generateTypeEncoding(callbackMethod);
            try {
                addTypeEncodingAnnotation(callbackMethod, encoding);
            } catch (IllegalArgumentException e) {
                throw new CompilerException("Failed to determine method type encoding for method "
                        + method + ": " + e.getMessage());
            }
        }

        Body body = j.newBody(callbackMethod);
        callbackMethod.setActiveBody(body);
        PatchingChain<Unit> units = body.getUnits();

        Local thiz = null;
        if (!method.isStatic()) {
            thiz = j.newLocal("$this", receiverType);
            body.getLocals().add(thiz);
            units.add(j.newIdentityStmt(thiz, j.newParameterRef(receiverType, 0)));
        }
        LinkedList<Value> args = new LinkedList<>();
        for (int i = 0; i < method.getParameterCount(); i++) {
            Type t = method.getParameterType(i);
            Local p = j.newLocal("$p" + i, t);
            body.getLocals().add(p);
            units.add(j.newIdentityStmt(p, j.newParameterRef(t, i + 2)));
            args.add(p);
        }

        Local ret = null;
        if (method.getReturnType() != VoidType.v()) {
            ret = j.newLocal("$ret", method.getReturnType());
            body.getLocals().add(ret);
        }

        SootMethodRef targetMethod = method.makeRef();
        if (((RefType) receiverType).getSootClass().isInterface()) {
            @SuppressWarnings("unchecked") List<Type> parameterTypes = method.getParameterTypes();
            targetMethod = Scene.v().makeMethodRef(
                    ((RefType) receiverType).getSootClass(),
                    method.getName(),
                    parameterTypes,
                    method.getReturnType(), false);
        }

        InvokeExpr expr = method.isStatic()
                ? j.newStaticInvokeExpr(targetMethod, args)
                : (((RefType) receiverType).getSootClass().isInterface()
                        ? j.newInterfaceInvokeExpr(thiz, targetMethod, args)
                        : j.newVirtualInvokeExpr(thiz, targetMethod, args));
        units.add(
                ret == null
                        ? j.newInvokeStmt(expr)
                        : j.newAssignStmt(ret, expr)
                );

        if (ret != null) {
            units.add(j.newReturnStmt(ret));
        } else {
            units.add(j.newReturnVoidStmt());
        }
    }

    private String generateTypeEncoding(SootMethod method) {
        TypeEncoder encoder = new TypeEncoder();
        return encoder.encode(method, !config.getArch().is32Bit());
    }

    private SootMethod findStrongRefGetter(SootClass sootClass,
            final SootMethod method, boolean extensions) {

        AnnotationTag annotation = getAnnotation(method, PROPERTY);
        if (annotation == null) {
            annotation = getAnnotation(method, IBOUTLET);
        }
        if (annotation == null) {
            annotation = getAnnotation(method, IBOUTLETCOLLECTION);
        }

        String setterPropName = readStringElem(annotation, "name", "").trim();
        if (setterPropName.length() == 0) {
            String methodName = method.getName();
            if (!methodName.startsWith("set") || methodName.length() == 3) {
                throw new CompilerException("Failed to determine the property "
                        + "name from the @Property method " + method
                        + ". Either specify the name explicitly in the @Property "
                        + "annotation or rename the method according to the Java "
                        + "beans property setter method naming convention.");
            }
            setterPropName = methodName.substring(3);
            setterPropName = setterPropName.substring(0, 1).toLowerCase() + setterPropName.substring(1);
        }

        int paramCount = extensions ? 1 : 0;
        Type propType = method.getParameterType(extensions ? 1 : 0);
        for (SootMethod m : sootClass.getMethods()) {
            if (m != method && method.isStatic() == m.isStatic()
                    && m.getParameterCount() == paramCount && m.getReturnType().equals(propType)) {

                AnnotationTag propertyAnno = getAnnotation(m, PROPERTY);
                if (propertyAnno != null) {
                    String getterPropName = readStringElem(propertyAnno, "name", "").trim();
                    if (getterPropName.length() == 0) {
                        String methodName = m.getName();
                        if (!methodName.startsWith("get") || methodName.length() == 3) {
                            // Not a candidate. No name set and not a Java beans
                            // style getter
                            continue;
                        }
                        getterPropName = methodName.substring(3);
                        getterPropName = getterPropName.substring(0, 1).toLowerCase() + getterPropName.substring(1);
                    }
                    if (setterPropName.equals(getterPropName)) {
                        return m;
                    }
                }
            }
        }

        throw new CompilerException("Failed to determine the getter method "
                + "corresponding to the strong ref @Property setter method " + method
                + ". The getter must either specify the name explicitly in the @Property "
                + "annotation or be named according to the Java "
                + "beans property getter method naming convention.");
    }

    /**
     * Takes a method returned by
     * {@link #getMsgSendMethod(String, SootMethod, boolean)} and returns a
     * {@link SootMethodRef} to it or to a matching method in the {@code $M}
     * class.
     */
    private SootMethodRef getGenericMsgSendReplacementMethod(SootMethod method) {
        if (method.getParameterCount() == 2) {
            if (isNSObject(method.getParameterType(0)) && isSelector(method.getParameterType(1))
                    && !hasAnnotation(method, MARSHALER)) {
                /*
                 * This is a no args ObjC method (only takes self and a
                 * selector). If it doesn't use any special marshaler for self
                 * and it returns a primitive, an NSObject using the default
                 * marshaler or a String marshaled from an NSString we can
                 * replace it with a call to $M.
                 */
                MarshalerMethod param0MarshalerMethod = config.getMarshalerLookup().findMarshalerMethod(
                        new MarshalSite(method, 0));
                if (isNSObject$Marshaler_toNative(param0MarshalerMethod.getMethod())) {
                    // self uses the default marshaler
                    List<Type> paramTypes = Arrays.<Type> asList(org_robovm_apple_foundation_NSObject.getType(),
                            org_robovm_objc_Selector.getType());
                    if (method.getReturnType() == VoidType.v() || method.getReturnType() instanceof PrimType) {
                        // Primitive return type or void
                        String prefix = getPrimitiveReturnTypeModifier(method);
                        return Scene.v().makeMethodRef(org_robovm_objc_$M, prefix + "_objc_msgSend",
                                paramTypes, method.getReturnType(), true);
                    } else if (isNSObject(method.getReturnType())) {
                        MarshalerMethod retMarshalerMethod = config.getMarshalerLookup().findMarshalerMethod(
                                new MarshalSite(method));
                        if (isNSObject$Marshaler_toObject(retMarshalerMethod.getMethod())) {
                            // Returns NSObject using the default marshaler
                            return Scene.v().makeMethodRef(org_robovm_objc_$M, "object_objc_msgSend",
                                    paramTypes, org_robovm_apple_foundation_NSObject.getType(), true);
                        }
                    } else if (method.getReturnType().equals(java_lang_String.getType())) {
                        MarshalerMethod retMarshalerMethod = config.getMarshalerLookup().findMarshalerMethod(
                                new MarshalSite(method));
                        if (isNSString$AsStringMarshaler_toObject(retMarshalerMethod.getMethod())) {
                            // Returns String marshaled using
                            // NSSring$AsStringMarshaler
                            return Scene.v().makeMethodRef(org_robovm_objc_$M, "string_objc_msgSend",
                                    paramTypes, method.getReturnType(), true);
                        }
                    }
                }
            }
        } else if (method.getParameterCount() == 3 && method.getReturnType() == VoidType.v()) {
            if (isNSObject(method.getParameterType(0)) && isSelector(method.getParameterType(1))
                    && !hasParameterAnnotation(method, 1, MARSHALER)) {
                /*
                 * This is a 1 arg ObjC method with no return type (void) (takes
                 * self, a selector and a third arg). If it doesn't use any
                 * special marshaler for self and the third arg is a primitive,
                 * an NSObject using the default marshaler or a String marshaled
                 * from an NSString we can replace it with a call to $M.
                 */
                MarshalerMethod param0MarshalerMethod = config.getMarshalerLookup().findMarshalerMethod(
                        new MarshalSite(method, 0));
                if (isNSObject$Marshaler_toNative(param0MarshalerMethod.getMethod())) {
                    // self uses the default marshaler
                    List<Type> paramTypes = Arrays.<Type> asList(org_robovm_apple_foundation_NSObject.getType(),
                            org_robovm_objc_Selector.getType(), method.getParameterType(2));
                    if (method.getParameterType(2) instanceof PrimType) {
                        // Arg is a primitive
                        String suffix = getPrimitiveParameterTypeModifier(method, 2);
                        return Scene.v().makeMethodRef(org_robovm_objc_$M, "objc_msgSend_" + suffix,
                                paramTypes, method.getReturnType(), true);
                    } else if (isNSObject(method.getParameterType(2))) {
                        MarshalerMethod param2MarshalerMethod = config.getMarshalerLookup().findMarshalerMethod(
                                new MarshalSite(method, 2));
                        if (isNSObject$Marshaler_toNative(param2MarshalerMethod.getMethod())) {
                            // Arg is an NSObject using the default marshaler
                            paramTypes = Arrays.<Type> asList(org_robovm_apple_foundation_NSObject.getType(),
                                    org_robovm_objc_Selector.getType(), org_robovm_apple_foundation_NSObject.getType());
                            return Scene.v().makeMethodRef(org_robovm_objc_$M, "objc_msgSend_object",
                                    paramTypes, method.getReturnType(), true);
                        }
                    } else if (method.getParameterType(2).equals(java_lang_String.getType())) {
                        MarshalerMethod param2MarshalerMethod = config.getMarshalerLookup().findMarshalerMethod(
                                new MarshalSite(method, 2));
                        if (isNSString$AsStringMarshaler_toNative(param2MarshalerMethod.getMethod())) {
                            // Arg is a String marshaled using
                            // NSSring@AsStringMarshaler
                            return Scene.v().makeMethodRef(org_robovm_objc_$M, "objc_msgSend_string",
                                    paramTypes, method.getReturnType(), true);
                        }
                    }
                }
            }
        }
        return method.makeRef();
    }
    
    /**
     * Takes a method returned by
     * {@link #getMsgSendSuperMethod(String, SootMethod)} and returns a
     * {@link SootMethodRef} to it or to a matching method in the {@code $M}
     * class.
     */
    private SootMethodRef getGenericMsgSendSuperReplacementMethod(SootMethod method) {
        if (method.getParameterCount() == 2) {
            /*
             * This is a no args ObjC method (only takes super and a selector).
             * If it returns a primitive, an NSObject using the default
             * marshaler or a String marshaled from an NSString we can replace
             * it with a call to $M.
             */
            if (method.getParameterType(0).equals(org_robovm_objc_ObjCSuper.getType())
                    && isSelector(method.getParameterType(1)) && !hasAnnotation(method, MARSHALER)) {
                if (method.getReturnType() == VoidType.v() || method.getReturnType() instanceof PrimType) {
                    // Primitive return type or void
                    String prefix = getPrimitiveReturnTypeModifier(method);
                    return Scene.v().makeMethodRef(org_robovm_objc_$M, prefix + "_objc_msgSendSuper",
                            method.getParameterTypes(), method.getReturnType(), true);
                } else if (isNSObject(method.getReturnType())) {
                    MarshalerMethod retMarshalerMethod = config.getMarshalerLookup().findMarshalerMethod(
                            new MarshalSite(method));
                    if (isNSObject$Marshaler_toObject(retMarshalerMethod.getMethod())) {
                        // Returns NSObject using the default marshaler
                        return Scene.v().makeMethodRef(org_robovm_objc_$M, "object_objc_msgSendSuper",
                                method.getParameterTypes(), org_robovm_apple_foundation_NSObject.getType(), true);
                    }
                } else if (method.getReturnType().equals(java_lang_String.getType())) {
                    MarshalerMethod retMarshalerMethod = config.getMarshalerLookup().findMarshalerMethod(
                            new MarshalSite(method));
                    if (isNSString$AsStringMarshaler_toObject(retMarshalerMethod.getMethod())) {
                        // Returns String marshaled using
                        // NSSring$AsStringMarshaler
                        return Scene.v().makeMethodRef(org_robovm_objc_$M, "string_objc_msgSendSuper",
                                method.getParameterTypes(), method.getReturnType(), true);
                    }
                }
            }
        } else if (method.getParameterCount() == 3 && method.getReturnType() == VoidType.v()) {
            if (method.getParameterType(0).equals(org_robovm_objc_ObjCSuper.getType())
                    && isSelector(method.getParameterType(1)) && !hasParameterAnnotation(method, 1, MARSHALER)) {
                /*
                 * This is a 1 arg ObjC method with no return type (void) (takes
                 * super, a selector and a third arg). If the third arg is a
                 * primitive, an NSObject using the default marshaler or a
                 * String marshaled from an NSString we can replace it with a
                 * call to $M.
                 */
                if (method.getParameterType(2) instanceof PrimType) {
                    // Arg is a primitive
                    String suffix = getPrimitiveParameterTypeModifier(method, 2);
                    return Scene.v().makeMethodRef(org_robovm_objc_$M, "objc_msgSendSuper_" + suffix,
                            method.getParameterTypes(), method.getReturnType(), true);
                } else if (isNSObject(method.getParameterType(2))) {
                    MarshalerMethod param2MarshalerMethod = config.getMarshalerLookup().findMarshalerMethod(
                            new MarshalSite(method, 2));
                    if (isNSObject$Marshaler_toNative(param2MarshalerMethod.getMethod())) {
                        // Arg is an NSObject using the default marshaler
                        List<Type> paramTypes = Arrays.<Type> asList(org_robovm_objc_ObjCSuper.getType(),
                                org_robovm_objc_Selector.getType(), org_robovm_apple_foundation_NSObject.getType());
                        return Scene.v().makeMethodRef(org_robovm_objc_$M, "objc_msgSendSuper_object",
                                paramTypes, method.getReturnType(), true);
                    }
                } else if (method.getParameterType(2).equals(java_lang_String.getType())) {
                    MarshalerMethod param2MarshalerMethod = config.getMarshalerLookup().findMarshalerMethod(
                            new MarshalSite(method, 2));
                    if (isNSString$AsStringMarshaler_toNative(param2MarshalerMethod.getMethod())) {
                        // Arg is a String marshaled using
                        // NSSring@AsStringMarshaler
                        return Scene.v().makeMethodRef(org_robovm_objc_$M, "objc_msgSendSuper_string",
                                method.getParameterTypes(), method.getReturnType(), true);
                    }
                }
            }
        }
        return method.makeRef();
    }

    private String getPrimitiveReturnTypeModifier(SootMethod method) {
        String mod = method.getReturnType().toString();
        if (method.getReturnType() == LongType.v() && hasPointerAnnotation(method)) {
            mod = "ptr";
        } else if ((method.getReturnType() == FloatType.v() || method.getReturnType() == DoubleType.v())
                && hasMachineSizedFloatAnnotation(method)) {
            mod = "m" + mod;
        } else if (method.getReturnType() == LongType.v() && hasMachineSizedSIntAnnotation(method)) {
            mod = "msint";
        } else if (method.getReturnType() == LongType.v() && hasMachineSizedUIntAnnotation(method)) {
            mod = "muint";
        }
        return mod;
    }

    private String getPrimitiveParameterTypeModifier(SootMethod method, int paramIdx) {
        String mod = method.getParameterType(paramIdx).toString();
        if (method.getParameterType(paramIdx) == LongType.v() && hasPointerAnnotation(method, paramIdx)) {
            mod = "ptr";
        } else if ((method.getParameterType(paramIdx) == FloatType.v() || method.getParameterType(paramIdx) == DoubleType.v())
                && hasMachineSizedFloatAnnotation(method, paramIdx)) {
            mod = "m" + mod;
        } else if (method.getParameterType(paramIdx) == LongType.v() && hasMachineSizedSIntAnnotation(method, paramIdx)) {
            mod = "msint";
        } else if (method.getParameterType(paramIdx) == LongType.v() && hasMachineSizedUIntAnnotation(method, paramIdx)) {
            mod = "muint";
        }
        return mod;
    }

    private void createBridge(SootClass sootClass, SootMethod method, String selectorName,
            boolean strongRefSetter, boolean extensions) {

        Jimple j = Jimple.v();

        boolean usingGenericInstanceMethod = false;
        
        SootMethod msgSendMethod = getMsgSendMethod(selectorName, method, extensions);
        /*
         * Add the method even if we might remove it later to make marshaler
         * lookup on the method work as expected.
         */
        sootClass.addMethod(msgSendMethod);
        addBridgeAnnotation(msgSendMethod);
        SootMethodRef msgSendMethodRef = getGenericMsgSendReplacementMethod(msgSendMethod);
        if (!msgSendMethodRef.declaringClass().getType().equals(msgSendMethod.getDeclaringClass().getType())) {
            /*
             * There's a generic objc_msgSend method we can use. Remove
             * msgSendMethod from the class.
             */
            sootClass.removeMethod(msgSendMethod);
            
            /*
             * Can we use a generic <name>_instance method from $M? If we can we
             * won't have to make a call to objc_msgSendSuper.
             */
            if (!method.isStatic()) {
                // Yes!
                msgSendMethodRef = Scene.v().makeMethodRef(msgSendMethodRef.declaringClass(),
                        msgSendMethodRef.name() + "_instance", msgSendMethodRef.parameterTypes(),
                        msgSendMethodRef.returnType(), true);
                usingGenericInstanceMethod = true;
            }
        }

        SootMethodRef msgSendSuperMethodRef = null;
        if (!usingGenericInstanceMethod && !extensions && !method.isStatic()) {
            SootMethod msgSendSuperMethod = getMsgSendSuperMethod(selectorName, method);
            /*
             * Add the method even if we might remove it later to make marshaler
             * lookup on the method work as expected.
             */
            sootClass.addMethod(msgSendSuperMethod);
            addBridgeAnnotation(msgSendSuperMethod);
            msgSendSuperMethodRef = getGenericMsgSendSuperReplacementMethod(msgSendSuperMethod);
            if (!msgSendSuperMethodRef.declaringClass().getType().equals(msgSendSuperMethod.getDeclaringClass().getType())) {
                /*
                 * There's a generic objc_msgSendSuper method we can use. Remove
                 * msgSendSuperMethod from the class.
                 */
                sootClass.removeMethod(msgSendSuperMethod);
            }
        }

        method.setModifiers(method.getModifiers() & ~NATIVE);

        Body body = j.newBody(method);
        method.setActiveBody(body);
        PatchingChain<Unit> units = body.getUnits();
        Local thiz = null;
        if (extensions) {
            thiz = j.newLocal("$this", method.getParameterType(0));
            body.getLocals().add(thiz);
            units.add(j.newIdentityStmt(thiz, j.newParameterRef(method.getParameterType(0), 0)));
        } else if (!method.isStatic()) {
            thiz = j.newLocal("$this", sootClass.getType());
            body.getLocals().add(thiz);
            units.add(j.newIdentityStmt(thiz, j.newThisRef(sootClass.getType())));
        }
        LinkedList<Value> args = new LinkedList<>();
        for (int i = extensions ? 1 : 0; i < method.getParameterCount(); i++) {
            Type t = method.getParameterType(i);
            Local p = j.newLocal("$p" + i, t);
            body.getLocals().add(p);
            units.add(j.newIdentityStmt(p, j.newParameterRef(t, i)));
            args.add(p);
        }

        Local objCClass = null;
        if (!extensions && method.isStatic()) {
            objCClass = j.newLocal("$objCClass", org_robovm_objc_ObjCClass.getType());
            body.getLocals().add(objCClass);
            units.add(
                    j.newAssignStmt(
                            objCClass,
                            j.newStaticFieldRef(
                                    Scene.v().makeFieldRef(
                                            sootClass,
                                            "$objCClass", org_robovm_objc_ObjCClass.getType(), true))
                            )
                    );
        }

        if (strongRefSetter) {
            Type propType = method.getParameterType(extensions ? 1 : 0);
            if (propType instanceof RefLikeType) {
                SootMethodRef getter = findStrongRefGetter(sootClass, method, extensions).makeRef();
                Local before = j.newLocal("$before", propType);
                body.getLocals().add(before);
                units.add(
                        j.newAssignStmt(
                                before,
                                extensions
                                        ? j.newStaticInvokeExpr(getter, thiz)
                                        : (objCClass != null
                                                ? j.newStaticInvokeExpr(getter)
                                                : j.newVirtualInvokeExpr(thiz, getter))
                                )
                        );
                Value after = args.get(0);
                if (extensions) {
                    units.add(
                            j.newInvokeStmt(
                                    j.newStaticInvokeExpr(
                                            org_robovm_objc_ObjCExtensions_updateStrongRef,
                                            Arrays.asList(thiz, before, after)))
                            );
                } else {
                    units.add(
                            j.newInvokeStmt(
                                    j.newVirtualInvokeExpr(
                                            objCClass != null ? objCClass : thiz,
                                            org_robovm_objc_ObjCObject_updateStrongRef,
                                            before, after))
                            );
                }
            }
        }

        Local sel = j.newLocal("$sel", org_robovm_objc_Selector.getType());
        body.getLocals().add(sel);
        // $sel = <selector>
        units.add(
                j.newAssignStmt(
                        sel,
                        j.newStaticFieldRef(
                                Scene.v().makeFieldRef(
                                        sootClass,
                                        getSelectorFieldName(selectorName),
                                        org_robovm_objc_Selector.getType(), true)))
                );
        args.addFirst(sel);

        Local customClass = null;
        if (!usingGenericInstanceMethod && !extensions && !Modifier.isFinal(sootClass.getModifiers()) && !method.isStatic()) {
            customClass = j.newLocal("$customClass", BooleanType.v());
            body.getLocals().add(customClass);
            units.add(
                    j.newAssignStmt(
                            customClass,
                            j.newInstanceFieldRef(
                                    thiz,
                                    org_robovm_objc_ObjCObject_customClass)
                            )
                    );
        }

        Local ret = null;
        if (method.getReturnType() != VoidType.v()) {
            ret = j.newLocal("$ret", msgSendMethodRef.returnType());
            body.getLocals().add(ret);
        }
        Local castRet = null;
        if (!msgSendMethodRef.returnType().equals(method.getReturnType())) {
            /*
             * We're calling a generic method in $M which returns an NSObject.
             * We need to cast that to the return type declared by the method
             * being generated.
             */
            castRet = j.newLocal("$castRet", method.getReturnType());
            body.getLocals().add(castRet);
        }

        StaticInvokeExpr invokeMsgSendExpr =
                j.newStaticInvokeExpr(
                        msgSendMethodRef,
                        l(thiz != null ? thiz : objCClass, args));
        Stmt invokeMsgSendStmt = ret == null
                ? j.newInvokeStmt(invokeMsgSendExpr)
                : j.newAssignStmt(ret, invokeMsgSendExpr);

        if (customClass != null) {
            // if $customClass == 0 goto <invokeMsgSendStmt>
            units.add(
                    j.newIfStmt(
                            j.newEqExpr(customClass, IntConstant.v(0)),
                            invokeMsgSendStmt)
                    );

            // $super = this.getSuper()
            Local zuper = j.newLocal("$super", org_robovm_objc_ObjCSuper.getType());
            body.getLocals().add(zuper);
            units.add(
                    j.newAssignStmt(
                            zuper,
                            j.newVirtualInvokeExpr(
                                    body.getThisLocal(),
                                    org_robovm_objc_ObjCObject_getSuper))
                    );
            StaticInvokeExpr invokeMsgSendSuperExpr =
                    j.newStaticInvokeExpr(
                            msgSendSuperMethodRef,
                            l(zuper, args));
            units.add(
                    ret == null
                            ? j.newInvokeStmt(invokeMsgSendSuperExpr)
                            : j.newAssignStmt(ret, invokeMsgSendSuperExpr)
                    );
            if (ret != null) {
                if (castRet != null) {
                    units.add(j.newAssignStmt(castRet, j.newCastExpr(ret, castRet.getType())));
                    units.add(j.newReturnStmt(castRet));
                } else {
                    units.add(j.newReturnStmt(ret));
                }
            } else {
                units.add(j.newReturnVoidStmt());
            }
        }

        units.add(invokeMsgSendStmt);
        if (ret != null) {
            if (castRet != null) {
                units.add(j.newAssignStmt(castRet, j.newCastExpr(ret, castRet.getType())));
                units.add(j.newReturnStmt(castRet));
            } else {
                units.add(j.newReturnStmt(ret));
            }
        } else {
            units.add(j.newReturnVoidStmt());
        }
    }

    static void addBridgeAnnotation(SootMethod method) {
        addRuntimeVisibleAnnotation(method, BRIDGE);
    }

    static void addCallbackAnnotation(SootMethod method) {
        addRuntimeVisibleAnnotation(method, CALLBACK);
    }

    static void addBindSelectorAnnotation(SootMethod method, String selectorName) {
        AnnotationTag annotationTag = new AnnotationTag(BIND_SELECTOR, 1);
        annotationTag.addElem(new AnnotationStringElem(selectorName, 's', "value"));
        addRuntimeVisibleAnnotation(method, annotationTag);
    }

    static void addNotImplementedAnnotation(SootMethod method, String selectorName) {
        AnnotationTag annotationTag = new AnnotationTag(NOT_IMPLEMENTED, 1);
        annotationTag.addElem(new AnnotationStringElem(selectorName, 's', "value"));
        addRuntimeVisibleAnnotation(method, annotationTag);
    }

    static void addTypeEncodingAnnotation(SootMethod method, String encoding) {
        AnnotationTag annotationTag = new AnnotationTag(TYPE_ENCODING, 1);
        annotationTag.addElem(new AnnotationStringElem(encoding, 's', "value"));
        addRuntimeVisibleAnnotation(method, annotationTag);
    }
}
