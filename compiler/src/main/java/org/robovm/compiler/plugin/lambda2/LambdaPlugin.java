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
package org.robovm.compiler.plugin.lambda2;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.apache.commons.io.FileUtils;
import org.robovm.compiler.CompilerException;
import org.robovm.compiler.ModuleBuilder;
import org.robovm.compiler.Types;
import org.robovm.compiler.clazz.Clazz;
import org.robovm.compiler.config.Config;
import org.robovm.compiler.plugin.AbstractCompilerPlugin;

import soot.Body;
import soot.Local;
import soot.Modifier;
import soot.PatchingChain;
import soot.RefType;
import soot.Scene;
import soot.SootClass;
import soot.SootField;
import soot.SootMethod;
import soot.SootMethodHandle;
import soot.SootMethodRef;
import soot.SootMethodType;
import soot.SootResolver;
import soot.Type;
import soot.Unit;
import soot.Value;
import soot.jimple.ClassConstant;
import soot.jimple.Constant;
import soot.jimple.ConstantSwitch;
import soot.jimple.DefinitionStmt;
import soot.jimple.DynamicInvokeExpr;
import soot.jimple.IntConstant;
import soot.jimple.Jimple;
import soot.jimple.NullConstant;
import soot.util.Switch;

public class LambdaPlugin extends AbstractCompilerPlugin {
    static final int BRIDGE = 0x00000040;
    static final int SYNTHETIC = 0x00001000;
    
    private static int FLAG_MARKERS = 2;
    private static int FLAG_BRIDGES = 4;

    final Map<SootClass, LambdaClassGenerator> generators = new HashMap<SootClass, LambdaClassGenerator>();

    private static boolean isLambdaBootstrapMethod(SootMethodRef methodRef) {
        return methodRef.declaringClass().getName().equals("java.lang.invoke.LambdaMetafactory")
                && (methodRef.name().equals("metafactory") || methodRef.name().equals("altMetafactory"));
    }

    @Override
    public void afterClass(Config config, Clazz clazz, ModuleBuilder moduleBuilder) throws IOException {
        synchronized (generators) {
            generators.remove(clazz.getSootClass());
        }
    }

    @Override
    public void beforeClass(Config config, Clazz clazz, ModuleBuilder moduleBuilder) throws IOException {
        SootClass sootClass = clazz.getSootClass();

        for (SootMethod method : sootClass.getMethods()) {
            transformMethod(config, clazz, sootClass, method, moduleBuilder);
        }
    }

    private void transformMethod(Config config, Clazz clazz, SootClass sootClass,
            SootMethod method, ModuleBuilder moduleBuilder) throws IOException {

        if (!method.isConcrete()) {
            return;
        }

        int tmpCounter = 0;
        Body body = method.retrieveActiveBody();
        PatchingChain<Unit> units = body.getUnits();
        for (Unit unit = units.getFirst(); unit != null; unit = body.getUnits().getSuccOf(unit)) {
            if (unit instanceof DefinitionStmt) {
                if (((DefinitionStmt) unit).getRightOp() instanceof DynamicInvokeExpr) {
                    DynamicInvokeExpr expr = (DynamicInvokeExpr) ((DefinitionStmt) unit).getRightOp();

                    if (isLambdaBootstrapMethod(expr.getBootstrapMethodRef())) {
                        LambdaClassGenerator generator = null;
                        synchronized (generators) {
                            generator = generators.get(sootClass);
                            if (generator == null) {
                                generator = new LambdaClassGenerator();
                                generators.put(sootClass, generator);
                            }
                        }

                        List<Value> bsmArgs = expr.getBootstrapArgs();
                        SootClass caller = sootClass;
                        String invokedName = expr.getMethodRef().name();
                        SootMethodRef invokedType = expr.getMethodRef();
                        SootMethodType samMethodType = (SootMethodType) bsmArgs.get(0);
                        SootMethodHandle implMethod = (SootMethodHandle) bsmArgs.get(1);
                        SootMethodType instantiatedMethodType = (SootMethodType) bsmArgs.get(2);

                        try {
                            LambdaClass callSite = null;
                            List<Type> markerInterfaces = new ArrayList<>();
                            List<SootMethodType> bridgeMethods = new ArrayList<>();
                            if (expr.getBootstrapMethodRef().name().equals("altMetafactory")) {
                                int flags = ((IntConstant) bsmArgs.get(3)).value;
                                int bsmArgsIdx = 4;
                                if ((flags & FLAG_MARKERS) > 0) {
                                    int count = ((IntConstant) bsmArgs.get(bsmArgsIdx++)).value;
                                    for (int i = 0; i < count; i++) {
                                        Object value = bsmArgs.get(bsmArgsIdx++);
                                        if (value instanceof Type) {
                                            markerInterfaces.add((Type) value);
                                        } else if (value instanceof ClassConstant) {
                                            String className = ((ClassConstant) value).getValue().replace('/', '.');
                                            markerInterfaces.add(SootResolver.v()
                                                    .resolveClass(className, SootClass.HIERARCHY).getType());
                                        }
                                    }
                                }
                                if ((flags & FLAG_BRIDGES) > 0) {
                                    int count = ((IntConstant) bsmArgs.get(bsmArgsIdx++)).value;
                                    for (int i = 0; i < count; i++) {
                                        bridgeMethods.add((SootMethodType) bsmArgs.get(bsmArgsIdx++));
                                    }
                                }
                            }
                            
                            // search for additional bridge methods in the
                            // interface we implement. Javac
                            // may not emit them in the invoke dynamic call
                            // see issue #1087
                            if (bridgeMethods.size() == 0) {
                                SootClass targetType = SootResolver.v().resolveClass(
                                        invokedType.returnType().toString().replace('/', '.'), SootClass.SIGNATURES);
                                String samDescriptor = Types.getDescriptor(samMethodType.getParameterTypes(),
                                        samMethodType.getReturnType());
                                for (SootMethod targetTypeMethod : targetType.getMethods()) {
                                    boolean isBridgeMethod = targetTypeMethod.getName().equals(invokedName);
                                    isBridgeMethod &= targetTypeMethod.getName().equals(invokedName);
                                    isBridgeMethod &= targetTypeMethod.getParameterCount() == samMethodType.getParameterTypes().size();
                                    isBridgeMethod &= ((targetTypeMethod.getModifiers() & BRIDGE) != 0);
                                    isBridgeMethod &= ((targetTypeMethod.getModifiers() & SYNTHETIC) != 0);
                                    if(isBridgeMethod) {
                                        String targetTypeMethodDesc = Types.getDescriptor(targetTypeMethod);
                                        if (!targetTypeMethodDesc.equals(samDescriptor)) {
                                            bridgeMethods.add(new BridgeMethodType(targetTypeMethod.getReturnType(),
                                                    targetTypeMethod.getParameterTypes()));
                                        }
                                    }
                                }
                            }
                            
                            // generate the lambda class
                            callSite = generator.generate(caller, invokedName, invokedType, samMethodType, implMethod,
                                    instantiatedMethodType, markerInterfaces, bridgeMethods);
                            File f = clazz.getPath().getGeneratedClassFile(callSite.getLambdaClassName());
                            FileUtils.writeByteArrayToFile(f, callSite.getClassData());
                            // The lambda class is created after the caller is
                            // compiled.
                            // This prevents the triggering of a recompile of
                            // the caller.
                            f.setLastModified(clazz.lastModified());

                            SootClass lambdaClass = SootResolver.v()
                                    .makeClassRef(callSite.getLambdaClassName().replace('/', '.'));

                            Local l = (Local) ((DefinitionStmt) unit).getLeftOp();
                            Type samType = callSite.getTargetMethodReturnType();
                            LinkedList<Unit> newUnits = new LinkedList<>();
                            if (callSite.getTargetMethodName().equals("<init>")) {
                                // Constant lambda. Create an instance once and
                                // reuse for
                                // every call.
                                String fieldName = lambdaClass.getName()
                                        .substring(lambdaClass.getName().lastIndexOf('.') + 1);
                                SootField field = new SootField(fieldName, lambdaClass.getType(),
                                        Modifier.STATIC | Modifier.PRIVATE | Modifier.TRANSIENT
                                                | 0x1000 /* SYNTHETIC */);
                                method.getDeclaringClass().addField(field);
                                // l = LambdaClass.lambdaField
                                newUnits.add(
                                        Jimple.v().newAssignStmt(l, Jimple.v().newStaticFieldRef(field.makeRef())));
                                // if l != null goto succOfInvokedynamic
                                newUnits.add(Jimple.v().newIfStmt(Jimple.v().newNeExpr(l, NullConstant.v()),
                                        units.getSuccOf(unit)));
                                // $tmpX = new LambdaClass()
                                Local tmp = Jimple.v().newLocal("$tmp" + (tmpCounter++), lambdaClass.getType());
                                body.getLocals().add(tmp);
                                newUnits.add(
                                        Jimple.v().newAssignStmt(tmp, Jimple.v().newNewExpr(lambdaClass.getType())));
                                newUnits.add(Jimple.v().newInvokeStmt(Jimple.v().newSpecialInvokeExpr(tmp,
                                        Scene.v().makeConstructorRef(lambdaClass, Collections.<Type> emptyList()))));
                                // LambdaClass.lambdaField = $tmpX
                                newUnits.add(
                                        Jimple.v().newAssignStmt(Jimple.v().newStaticFieldRef(field.makeRef()), tmp));
                                // l = $tmpX
                                newUnits.add(Jimple.v().newAssignStmt(l, tmp));
                            } else {
                                // Static factory method returns the lambda to
                                // use.
                                newUnits.add(Jimple.v().newAssignStmt(l,
                                        Jimple.v().newStaticInvokeExpr(
                                                Scene.v().makeMethodRef(lambdaClass,
                                                        callSite.getTargetMethodName(),
                                                        callSite.getTargetMethodParameters(),
                                                        samType, true),
                                                expr.getArgs())));
                            }
                            units.insertAfter(newUnits, unit);
                            units.remove(unit);
                            unit = newUnits.getLast();

                        } catch (Throwable e) {
                            // TODO: Change the jimple of the method to throw a
                            // LambdaConversionException at runtime.
                            throw new CompilerException(e);
                        }
                    }
                }
            }
        }
    }
    
    static class BridgeMethodType extends Constant implements SootMethodType {
        private static final long serialVersionUID = 1L;

        private final Type returnType;
        private final List<Type> parameterTypes;

        public BridgeMethodType(Type returnType, List<Type> parameterTypes) {
            this.returnType = returnType;
            this.parameterTypes = parameterTypes;
        }

        public Type getReturnType() {
            return returnType;
        }

        public List<Type> getParameterTypes() {
            return Collections.unmodifiableList(parameterTypes);
        }

        @Override
        public Type getType() {
            return RefType.v("java.lang.invoke.MethodType");
        }

        @Override
        public void apply(Switch sw) {
            ((ConstantSwitch) sw).defaultCase(this);
        }

        @Override
        public String toString() {
            StringBuilder sb = new StringBuilder();

            sb.append('(');
            for (Iterator<Type> it = parameterTypes.iterator(); it.hasNext();) {
                sb.append(it.next());
                if (it.hasNext()) {
                    sb.append(',');
                }
            }
            return sb.append(')').append(returnType).toString();
        }
    }
}
