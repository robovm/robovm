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

import java.util.List;

import soot.Type;

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
