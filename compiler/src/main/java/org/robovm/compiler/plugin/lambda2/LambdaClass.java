package org.robovm.compiler.plugin.lambda2;

import soot.SootMethodType;
import soot.Type;

import java.util.List;

/**
 * Created by badlogic on 09/07/15.
 */
public class LambdaClass {
    private final String lambdaClassName;
    private final byte[] classData;
    private final String targetMethodName;
    private final List<Type> targetMethodParameters;
    private final Type targetMethodReturnType;

    public LambdaClass(String lambdaClassName, byte[] classData, String targetMethodName, List<Type> targetMethodParameters, Type targetMethodReturnType) {
        this.lambdaClassName = lambdaClassName;
        this.classData = classData;
        this.targetMethodName = targetMethodName;
        this.targetMethodParameters = targetMethodParameters;
        this.targetMethodReturnType = targetMethodReturnType;
    }

    public String getLambdaClassName() {
        return lambdaClassName;
    }

    public byte[] getClassData() {
        return classData;
    }

    public String getTargetMethodName() {
        return targetMethodName;
    }

    public List<Type> getTargetMethodParameters() {
        return targetMethodParameters;
    }

    public Type getTargetMethodReturnType() {
        return targetMethodReturnType;
    }
}
