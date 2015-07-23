package org.robovm.compiler.plugin.lambda;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.robovm.compiler.CompilerException;
import org.robovm.compiler.ModuleBuilder;
import org.robovm.compiler.clazz.Clazz;
import org.robovm.compiler.config.Config;
import org.robovm.compiler.plugin.AbstractCompilerPlugin;
import org.robovm.compiler.plugin.lambda.java.lang.invoke.LambdaConversionException;
import org.robovm.compiler.plugin.lambda.java.lang.invoke.LambdaMetafactory;

import soot.Body;
import soot.Local;
import soot.Modifier;
import soot.PatchingChain;
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
import soot.jimple.DefinitionStmt;
import soot.jimple.DynamicInvokeExpr;
import soot.jimple.IntConstant;
import soot.jimple.Jimple;
import soot.jimple.NullConstant;

public class LambdaPlugin extends AbstractCompilerPlugin {

    private static boolean isLambdaBootstrapMethod(SootMethodRef methodRef) {
        return methodRef.declaringClass().getName().equals("java.lang.invoke.LambdaMetafactory")
                && (methodRef.name().equals("metafactory") || methodRef.name().equals("altMetafactory"));
    }

    private static SMethodType toSMethodType(SootMethodRef ref) {
        return new SMethodType(SootSClass.forType(ref.returnType()), SootSClass.forTypes(ref.parameterTypes()));
    }

    private static SMethodType toSMethodType(SootMethodType t) {
        return new SMethodType(SootSClass.forType(t.getReturnType()), SootSClass.forTypes(t.getParameterTypes()));
    }

    private static SMethodHandle toSMethodHandle(SootMethodHandle h) {
        return new SMethodHandle(
                toSMethodType(h.getMethodType()),
                new SMethodHandleInfo(
                        SootSClass.forType(h.getMethodRef().declaringClass().getType()),
                        h.getMethodRef().name(),
                        toSMethodType(h.getMethodRef()), h.getReferenceKind()));
    }

    private static soot.Type toSootType(SClass<?> type) {
        if (type instanceof SootSClass) {
            return ((SootSClass) type).type;
        }
        SootSClassLookup lookup = (SootSClassLookup) SClass.getLookup();
        return ((SootSClass) lookup.lookup(type.getDescriptor())).type;
    }

    private static List<soot.Type> toSootTypes(List<SClass<?>> types) {
        List<soot.Type> result = new ArrayList<>();
        for (SClass<?> type : types) {
            result.add(toSootType(type));
        }
        return result;
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

        SClass.setLookup(new SootSClassLookup());

        int tmpCounter = 0;
        Body body = method.retrieveActiveBody();
        PatchingChain<Unit> units = body.getUnits();
        for (Unit unit = units.getFirst(); unit != null; unit = body.getUnits().getSuccOf(unit)) {
            if (unit instanceof DefinitionStmt) {
                if (((DefinitionStmt) unit).getRightOp() instanceof DynamicInvokeExpr) {
                    DynamicInvokeExpr expr = (DynamicInvokeExpr) ((DefinitionStmt) unit).getRightOp();

                    if (isLambdaBootstrapMethod(expr.getBootstrapMethodRef())) {
                        List<Value> bsmArgs = expr.getBootstrapArgs();
                        SMethodHandles.Lookup caller = new SMethodHandles.Lookup(
                                SootSClass.forType(sootClass.getType()));
                        String invokedName = expr.getMethodRef().name();
                        SMethodType invokedType = toSMethodType(expr.getMethodRef());
                        SMethodType samMethodType = toSMethodType((SootMethodType) bsmArgs.get(0));
                        SMethodHandle implMethod = toSMethodHandle((SootMethodHandle) bsmArgs.get(1));
                        SMethodType instantiatedMethodType = toSMethodType((SootMethodType) bsmArgs.get(2));

                        try {
                            SCallSite callSite = null;
                            if (expr.getBootstrapMethodRef().name().equals("altMetafactory")) {
                                callSite = altMetafactory(caller, invokedName, invokedType, samMethodType, implMethod,
                                        instantiatedMethodType, bsmArgs);
                            } else {
                                callSite = LambdaMetafactory.metafactory(caller, invokedName, invokedType,
                                        samMethodType, implMethod, instantiatedMethodType);
                            }

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
                            Type samType = toSootType(callSite.getTargetMethodType().returnType());
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
                                newUnits.add(Jimple.v().newInvokeStmt(Jimple.v().newSpecialInvokeExpr(tmp, Scene.v()
                                        .makeConstructorRef(lambdaClass, Collections.<soot.Type> emptyList()))));
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
                                                        toSootTypes(callSite.getTargetMethodType().parameterList()),
                                                        samType, true),
                                                expr.getArgs())));
                            }
                            units.insertAfter(newUnits, unit);
                            units.remove(unit);
                            unit = newUnits.getLast();

                        } catch (LambdaConversionException e) {
                            // TODO: Change the jimple of the method to throw a
                            // LambdaConversionException at runtime.
                            throw new CompilerException(e);
                        }
                    }
                }
            }
        }
    }

    private SCallSite altMetafactory(SMethodHandles.Lookup caller, String invokedName, SMethodType invokedType,
            SMethodType samMethodType, SMethodHandle implMethod, SMethodType instantiatedMethodType,
            List<Value> bsmArgs)
                    throws LambdaConversionException {

        int flags = ((IntConstant) bsmArgs.get(3)).value;
        List<Object> args = new ArrayList<>();
        args.add(samMethodType);
        args.add(implMethod);
        args.add(instantiatedMethodType);
        args.add(flags);
        int bsmArgsIdx = 4;
        if ((flags & LambdaMetafactory.FLAG_MARKERS) > 0) {
            int count = ((IntConstant) bsmArgs.get(bsmArgsIdx++)).value;
            args.add(count);
            for (int i = 0; i < count; i++) {
                Value value = bsmArgs.get(bsmArgsIdx++);
                Object arg = null;
                if (value instanceof Type) {
                    arg = SootSClass.forType((soot.Type) value);
                } else if (value instanceof ClassConstant) {
                    arg = SootSClass.forType(((ClassConstant) value).getValue());
                } else {
                    throw new CompilerException("Unknown marker interface type found in Jimple: " + value.getClass());
                }
                args.add(arg);
            }
        }
        if ((flags & LambdaMetafactory.FLAG_BRIDGES) > 0) {
            int count = ((IntConstant) bsmArgs.get(bsmArgsIdx++)).value;
            args.add(count);
            for (int i = 0; i < count; i++) {
                args.add(toSMethodType((SootMethodType) bsmArgs.get(bsmArgsIdx++)));
            }
        }
        return LambdaMetafactory.altMetafactory(caller, invokedName, invokedType, args.toArray());
    }

}
