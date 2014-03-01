/*
 * Copyright (C) 2014 Trillian AB
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
import static soot.tagkit.AnnotationConstants.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

import org.robovm.compiler.CompilerException;
import org.robovm.compiler.clazz.Clazz;
import org.robovm.compiler.config.Config;
import org.robovm.compiler.plugin.AbstractCompilerPlugin;
import org.robovm.compiler.plugin.CompilerPlugin;

import soot.Body;
import soot.BooleanType;
import soot.Local;
import soot.Modifier;
import soot.PatchingChain;
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
import soot.jimple.Jimple;
import soot.jimple.StaticInvokeExpr;
import soot.jimple.Stmt;
import soot.jimple.StringConstant;
import soot.tagkit.AnnotationStringElem;
import soot.tagkit.AnnotationTag;
import soot.tagkit.Tag;
import soot.tagkit.VisibilityAnnotationTag;
import soot.tagkit.VisibilityParameterAnnotationTag;
import soot.util.Chain;

/**
 * {@link CompilerPlugin} which transforms Objective-C methods and properties
 * to @Bridge methods. Also adds corresponding @Callback methods for each
 * method and property.
 */
public class ObjCMemberPlugin extends AbstractCompilerPlugin {
    public static final String OBJC_ANNOTATIONS_PACKAGE = "org/robovm/objc/annotation";
    public static final String METHOD = "L" + OBJC_ANNOTATIONS_PACKAGE + "/Method;";
    public static final String PROPERTY = "L" + OBJC_ANNOTATIONS_PACKAGE + "/Property;";
    public static final String BIND_SELECTOR = "L" + OBJC_ANNOTATIONS_PACKAGE + "/BindSelector;";
    public static final String SELECTOR = "org.robovm.objc.Selector";
    public static final String OBJC_SUPER = "org.robovm.objc.ObjCSuper";
    public static final String OBJC_CLASS = "org.robovm.objc.ObjCClass";
    public static final String OBJC_OBJECT = "org.robovm.objc.ObjCObject";

    private boolean initialized = false;
    private SootClass org_robovm_objc_ObjCClass = null;
    private SootClass org_robovm_objc_ObjCSuper = null;
    private SootClass org_robovm_objc_ObjCObject = null;
    private SootClass org_robovm_objc_Selector = null;
    private SootClass java_lang_String = null;
    private SootClass java_lang_Class = null;
    private SootMethodRef org_robovm_objc_Selector_register = null;
    private SootMethodRef org_robovm_objc_ObjCObject_getSuper = null;
    private SootFieldRef org_robovm_objc_ObjCObject_customClass = null;
    private SootMethodRef org_robovm_objc_ObjCClass_getByType = null;
    
    private SootMethod getOrCreateStaticInitializer(SootClass sootClass) {
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
    private SootMethod getMsgSendMethod(String selectorName, SootMethod method, boolean isCallback) {
        List<Type> paramTypes = new ArrayList<>();
        if (method.isStatic()) {
            paramTypes.add(org_robovm_objc_ObjCClass.getType());
        } else {
            paramTypes.add(method.getDeclaringClass().getType());
        }
        paramTypes.add(org_robovm_objc_Selector.getType());
        paramTypes.addAll(method.getParameterTypes());
        SootMethod m = new SootMethod((isCallback ? "$cb$": "$m$") + selectorName.replace(':', '$'), 
                paramTypes, method.getReturnType(), STATIC | PRIVATE | (isCallback ? 0 : NATIVE));
        copyAnnotations(method, m, 2);
        return m;
    }

    private SootMethod getMsgSendMethod(String selectorName, SootMethod method) {
        return getMsgSendMethod(selectorName, method, false);
    }
    
    @SuppressWarnings("unchecked")
    private SootMethod getMsgSendSuperMethod(String selectorName, SootMethod method) {
        List<Type> paramTypes = new ArrayList<>();
        paramTypes.add(org_robovm_objc_ObjCSuper.getType());
        paramTypes.add(org_robovm_objc_Selector.getType());
        paramTypes.addAll(method.getParameterTypes());
        SootMethod m = new SootMethod("$m$super$" + selectorName.replace(':', '$'), 
                paramTypes, method.getReturnType(), STATIC | PRIVATE | NATIVE);
        copyAnnotations(method, m, 2);
        return m;
    }

    private void copyAnnotations(SootMethod fromMethod, SootMethod toMethod, int shift) {
        // Copy annotations
        for (Tag tag : fromMethod.getTags()) {
            if (tag instanceof VisibilityAnnotationTag 
                    && ((VisibilityAnnotationTag) tag).getVisibility() == RUNTIME_VISIBLE) {
                VisibilityAnnotationTag copy = new VisibilityAnnotationTag(RUNTIME_VISIBLE);
                for (AnnotationTag annoTag : ((VisibilityAnnotationTag) tag).getAnnotations()) {
                    copy.addAnnotation(annoTag);
                }
                toMethod.addTag(copy);
            } else if (tag instanceof VisibilityParameterAnnotationTag 
                    && ((VisibilityParameterAnnotationTag) tag).getKind() == RUNTIME_VISIBLE) {
                toMethod.addTag(tag);
            }
        }
        
        // Shift parameter annotations if there are any
        VisibilityParameterAnnotationTag vpaTag = 
            (VisibilityParameterAnnotationTag) 
                toMethod.getTag(VisibilityParameterAnnotationTag.class.getSimpleName());
        
        if (vpaTag != null && vpaTag.getVisibilityAnnotations() != null) {
            toMethod.removeTag(vpaTag.getName());
            List<VisibilityAnnotationTag> tags = new ArrayList<>(vpaTag.getVisibilityAnnotations());
            for (int i = 0; i < shift; i++) {
                tags.add(0, new VisibilityAnnotationTag(RUNTIME_VISIBLE));
            }
            vpaTag = new VisibilityParameterAnnotationTag(tags.size(), RUNTIME_VISIBLE);
            for (VisibilityAnnotationTag tag : tags) {
                vpaTag.addVisibilityAnnotation(tag);
            }
            toMethod.addTag(vpaTag);
        }
    }

    private SootMethod getCallbackMethod(String selectorName, SootMethod method) {
        return getMsgSendMethod(selectorName, method, true);
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
            Arrays.<Unit>asList(
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
    
    private void registerSelectors(SootClass sootClass, List<String> selectors) {
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
                Arrays.<Unit>asList(
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
    
    private void init() {
        if (initialized) {
            return;
        }
        SootResolver r = SootResolver.v();
        org_robovm_objc_ObjCClass = r.makeClassRef(OBJC_CLASS);
        org_robovm_objc_ObjCSuper = r.makeClassRef(OBJC_SUPER);
        org_robovm_objc_ObjCObject = r.makeClassRef(OBJC_OBJECT);
        org_robovm_objc_Selector = r.makeClassRef(SELECTOR);
        java_lang_String = r.makeClassRef("java.lang.String");
        java_lang_Class = r.makeClassRef("java.lang.Class");
        org_robovm_objc_Selector_register =
            Scene.v().makeMethodRef(
                org_robovm_objc_Selector,
                "register",
                Arrays.<Type>asList(java_lang_String.getType()),
                org_robovm_objc_Selector.getType(), true);
        org_robovm_objc_ObjCObject_getSuper = 
            Scene.v().makeMethodRef(
                org_robovm_objc_ObjCObject, 
                "getSuper", 
                Collections.<Type>emptyList(), 
                org_robovm_objc_ObjCSuper.getType(), false);
        org_robovm_objc_ObjCClass_getByType =
            Scene.v().makeMethodRef(
                org_robovm_objc_ObjCClass,
                "getByType",
                Arrays.<Type>asList(java_lang_Class.getType()),
                org_robovm_objc_ObjCClass.getType(), true);
        org_robovm_objc_ObjCObject_customClass =
            Scene.v().makeFieldRef(
                org_robovm_objc_ObjCObject, 
                "customClass", BooleanType.v(), false);
        initialized = true;
    }
    
    private boolean isObjCObject(SootClass cls) {
        if (org_robovm_objc_ObjCObject.isPhantom()) {
            return false;
        }
        while (cls != org_robovm_objc_ObjCObject && cls.hasSuperclass()) {
            cls = cls.getSuperclass();
        }
        return cls == org_robovm_objc_ObjCObject;
    }
    
    
    @Override
    public void beforeClass(Config config, Clazz clazz) {
        init();
        SootClass sootClass = clazz.getSootClass();
        if (!sootClass.isInterface() && isObjCObject(sootClass)) {
            List<String> selectors = new ArrayList<>();
            for (SootMethod method : sootClass.getMethods()) {
                if (!"<clinit>".equals(method.getName()) && !"<init>".equals(method.getName())) {
                    transformMethod(config, clazz, sootClass, method, selectors);
                }
            }
            addObjCClassField(sootClass);
            registerSelectors(sootClass, selectors);
        }
    }
    
    private static <E> List<E> l(E head, List<E> tail) {
        LinkedList<E> l = new LinkedList<>(tail);
        l.addFirst(head);
        return l;
    }
    
    private void transformMethod(Config config, Clazz clazz, SootClass sootClass, 
            SootMethod method, List<String> selectors) {
        
        AnnotationTag methodAnno = getAnnotation(method, METHOD);
        if (methodAnno != null) {
            
            // Determine the selector
            String selectorName = readStringElem(methodAnno, "selector", "").trim();
            if (selectorName.length() == 0) {
                StringBuilder sb = new StringBuilder(method.getName());
                int argCount = method.getParameterCount();
                for (int i = 0; i < argCount; i++) {
                    sb.append(':');
                }
                selectorName = sb.toString();
            }

            // Create the @Bridge and @Callback methods needed for this selector
            createCallback(sootClass, method, selectorName);
            if (method.isNative()) {
                selectors.add(selectorName);
                createBridge(sootClass, method, selectorName);
            }
        } else {
            AnnotationTag propertyAnno = getAnnotation(method, PROPERTY);
            if (propertyAnno != null) {
                
                // Validate
                String methodName = method.getName();
                if (!(methodName.startsWith("get") && methodName.length() > 3)
                        && !(methodName.startsWith("is") && methodName.length() > 2)
                        && !(methodName.startsWith("set") && methodName.length() > 3)) {
                    throw new CompilerException("Invalid Objective-C @Property method name " + method);
                }
                
                boolean isGetter = !methodName.startsWith("set");
                if (isGetter && method.getParameterCount() != 0) {
                    throw new CompilerException("Objective-C @Property getter method " + method 
                            + " must take 0 arguments");
                }
                if (isGetter && method.getReturnType() == VoidType.v()) {
                    throw new CompilerException("Objective-C @Property getter method " + method 
                            + " must not return void");
                }
                if (!isGetter && method.getParameterCount() != 1) {
                    throw new CompilerException("Objective-C @Property setter method " + method 
                            + " must take 1 argument");
                }
                if (!isGetter && method.getReturnType() != VoidType.v()) {
                    throw new CompilerException("Objective-C @Property setter method " + method 
                            + " must return void");
                }
                
                // Determine the selector
                String selectorName = readStringElem(propertyAnno, "selector", "").trim();
                if (selectorName.length() == 0) {
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

                // Create the @Bridge and @Callback methods needed for this selector
                createCallback(sootClass, method, selectorName);
                if (method.isNative()) {
                    selectors.add(selectorName);
                    createBridge(sootClass, method, selectorName);
                }
            }
        }
    }

    private void createCallback(SootClass sootClass, SootMethod method, String selectorName) {
        Jimple j = Jimple.v();
        
        SootMethod callbackMethod = getCallbackMethod(selectorName, method);
        sootClass.addMethod(callbackMethod);
        addCallbackAnnotation(callbackMethod);
        addBindSelectorAnnotation(callbackMethod, selectorName);
        
        Body body = j.newBody(callbackMethod);
        callbackMethod.setActiveBody(body);
        PatchingChain<Unit> units = body.getUnits();

        Local thiz = null;
        if (!method.isStatic()) {
            thiz = j.newLocal("$this", sootClass.getType());
            body.getLocals().add(thiz);
            units.add(j.newIdentityStmt(thiz, j.newParameterRef(sootClass.getType(), 0)));
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

        InvokeExpr expr = method.isStatic()
            ? j.newStaticInvokeExpr(method.makeRef(), args)
            : j.newVirtualInvokeExpr(thiz, method.makeRef(), args);
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

    private void createBridge(SootClass sootClass, SootMethod method, String selectorName) {
        Jimple j = Jimple.v();
        
        SootMethod msgSendMethod = getMsgSendMethod(selectorName, method);
        sootClass.addMethod(msgSendMethod);
        addBridgeAnnotation(msgSendMethod);
        
        SootMethod msgSendSuperMethod = null;
        if (!method.isStatic()) {
            msgSendSuperMethod = getMsgSendSuperMethod(selectorName, method);
            sootClass.addMethod(msgSendSuperMethod);
            addBridgeAnnotation(msgSendSuperMethod);
        }
        
        method.setModifiers(method.getModifiers() & ~NATIVE);
        
        Body body = j.newBody(method);
        method.setActiveBody(body);
        PatchingChain<Unit> units = body.getUnits();
        if (!method.isStatic()) {
            Local thiz = j.newLocal("$this", sootClass.getType());
            body.getLocals().add(thiz);
            units.add(j.newIdentityStmt(thiz, j.newThisRef(sootClass.getType())));
        }
        LinkedList<Value> args = new LinkedList<>();
        for (int i = 0; i < method.getParameterCount(); i++) {
            Type t = method.getParameterType(i);
            Local p = j.newLocal("$p" + i, t);
            body.getLocals().add(p);
            units.add(j.newIdentityStmt(p, j.newParameterRef(t, i)));
            args.add(p);
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
        
        Local objCClass = null;
        if (method.isStatic()) {
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
        
        Local customClass = null;
        if (!Modifier.isFinal(sootClass.getModifiers()) && !method.isStatic()) {
            customClass = j.newLocal("$customClass", BooleanType.v());
            body.getLocals().add(customClass);
            units.add(
                j.newAssignStmt(
                    customClass,
                    j.newInstanceFieldRef(
                        body.getThisLocal(), 
                        org_robovm_objc_ObjCObject_customClass)
                )
            );
        }

        Local ret = null;
        if (method.getReturnType() != VoidType.v()) {
            ret = j.newLocal("$ret", method.getReturnType());
            body.getLocals().add(ret);
        }
        
        StaticInvokeExpr invokeMsgSendExpr = 
            j.newStaticInvokeExpr(
                msgSendMethod.makeRef(),
                l(method.isStatic() ? objCClass : body.getThisLocal(), args));
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
                    msgSendMethod.makeRef(),
                    l(zuper, args));
            units.add(
                ret == null
                    ? j.newInvokeStmt(invokeMsgSendSuperExpr)
                    : j.newAssignStmt(ret, invokeMsgSendSuperExpr)
            );
            if (ret != null) {
                units.add(j.newReturnStmt(ret));
            } else {
                units.add(j.newReturnVoidStmt());
            }
        }

        units.add(invokeMsgSendStmt);
        if (ret != null) {
            units.add(j.newReturnStmt(ret));
        } else {
            units.add(j.newReturnVoidStmt());
        }
    }

    private VisibilityAnnotationTag getOrCreateRuntimeVisibilityAnnotationTag(SootMethod m) {
        for (Tag tag : m.getTags()) {
            if (tag instanceof VisibilityAnnotationTag) {
                if (((VisibilityAnnotationTag) tag).getVisibility() == RUNTIME_VISIBLE) {
                    return (VisibilityAnnotationTag) tag;
                }
            }
        }
        VisibilityAnnotationTag tag = new VisibilityAnnotationTag(RUNTIME_VISIBLE);
        m.addTag(tag);
        return tag;
    }
    
    private void addBridgeAnnotation(SootMethod method) {
        VisibilityAnnotationTag tag = getOrCreateRuntimeVisibilityAnnotationTag(method);
        tag.addAnnotation(new AnnotationTag(BRIDGE, 0));
    }

    private void addCallbackAnnotation(SootMethod method) {
        VisibilityAnnotationTag tag = getOrCreateRuntimeVisibilityAnnotationTag(method);
        tag.addAnnotation(new AnnotationTag(CALLBACK, 0));
    }

    private void addBindSelectorAnnotation(SootMethod method, String selectorName) {
        VisibilityAnnotationTag vaTag = getOrCreateRuntimeVisibilityAnnotationTag(method);
        AnnotationTag annotationTag = new AnnotationTag(BIND_SELECTOR, 1);
        annotationTag.addElem(new AnnotationStringElem(selectorName, 's', "value"));
        vaTag.addAnnotation(annotationTag);
    }

}
