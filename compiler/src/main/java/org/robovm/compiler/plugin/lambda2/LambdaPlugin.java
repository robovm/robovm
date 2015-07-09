package org.robovm.compiler.plugin.lambda2;

import org.apache.commons.io.FileUtils;
import org.robovm.compiler.CompilerException;
import org.robovm.compiler.ModuleBuilder;
import org.robovm.compiler.clazz.Clazz;
import org.robovm.compiler.config.Config;
import org.robovm.compiler.plugin.AbstractCompilerPlugin;
import soot.*;
import soot.jimple.*;

import java.io.File;
import java.io.IOException;
import java.util.*;

public class LambdaPlugin extends AbstractCompilerPlugin {

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
                            if(generator == null) {
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
                            // TODO: handle altMetaFactory case
                            callSite = generator.generate(caller, invokedName, invokedType, samMethodType, implMethod, instantiatedMethodType);

                            File f = clazz.getPath().getGeneratedClassFile(callSite.getLambdaClassName());
                            FileUtils.writeByteArrayToFile(f, callSite.getClassData());
                            // The lambda class is created after the caller is compiled.
                            // This prevents the triggering of a recompile of the caller.
                            f.setLastModified(clazz.lastModified());

                            SootClass lambdaClass = SootResolver.v().makeClassRef(callSite.getLambdaClassName().replace('/', '.'));

                            Local l = (Local) ((DefinitionStmt) unit).getLeftOp();
                            Type samType = callSite.getTargetMethodReturnType();
                            LinkedList<Unit> newUnits = new LinkedList<>();
                            if (callSite.getTargetMethodName().equals("<init>")) {
                                // Constant lambda. Create an instance once and reuse for
                                // every call.
                                String fieldName = lambdaClass.getName().substring(lambdaClass.getName().lastIndexOf('.') + 1);
                                SootField field = new SootField(fieldName, lambdaClass.getType(),
                                        Modifier.STATIC | Modifier.PRIVATE | Modifier.TRANSIENT | 0x1000 /*SYNTHETIC*/);
                                method.getDeclaringClass().addField(field);
                                // l = LambdaClass.lambdaField
                                newUnits.add(Jimple.v().newAssignStmt(l, Jimple.v().newStaticFieldRef(field.makeRef())));
                                // if l != null goto succOfInvokedynamic
                                newUnits.add(Jimple.v().newIfStmt(Jimple.v().newNeExpr(l, NullConstant.v()), units.getSuccOf(unit)));
                                // $tmpX = new LambdaClass()
                                Local tmp = Jimple.v().newLocal("$tmp" + (tmpCounter++), lambdaClass.getType());
                                body.getLocals().add(tmp);
                                newUnits.add(Jimple.v().newAssignStmt(tmp, Jimple.v().newNewExpr(lambdaClass.getType())));
                                newUnits.add(Jimple.v().newInvokeStmt(Jimple.v().newSpecialInvokeExpr(tmp, Scene.v().makeConstructorRef(lambdaClass, Collections.<Type>emptyList()))));
                                // LambdaClass.lambdaField = $tmpX
                                newUnits.add(Jimple.v().newAssignStmt(Jimple.v().newStaticFieldRef(field.makeRef()), tmp));
                                // l = $tmpX
                                newUnits.add(Jimple.v().newAssignStmt(l, tmp));
                            } else {
                                // Static factory method returns the lambda to use.
                                // TODO: implement altMetaFactory behaviour
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
}
