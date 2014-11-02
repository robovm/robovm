/*
 * Copyright (C) 2008 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.robovm.compiler.util.generic;


public final class ImplForType implements ParameterizedType {
    private final ListOfTypes args;
    private final ImplForType ownerType0; // Potentially unresolved.
    private Type ownerTypeRes;
    private SootClassType rawType; // Already resolved.
    private final String rawTypeName;


    public ImplForType(ImplForType ownerType, String rawTypeName,
            ListOfTypes args) {
        this.ownerType0 = ownerType;
        this.rawTypeName = rawTypeName;
        this.args = args;
    }


    public Type[] getActualTypeArguments() {
        // ASSUMPTION: args is never null!!!
        return args.getResolvedTypes().clone();
    }

    public Type getOwnerType() {
        if (ownerTypeRes == null) {
            if (ownerType0 != null) {
                ownerTypeRes = ownerType0.getResolvedType();
            } else {
                ownerTypeRes = getRawType().getDeclaringClass();
            }
        }
        return ownerTypeRes;
    }

    public SootClassType getRawType() {
        if (rawType == null) {
            rawType = new SootClassType(rawTypeName);
        }
        return rawType;
    }


    Type getResolvedType() {
        if (args.getResolvedTypes().length == 0) {
            return getRawType();
        } else {
            return this;
        }
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(rawTypeName);
        if (args.length() > 0) {
            sb.append("<").append(args).append(">");
        }
        return sb.toString();
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof ImplForType)) {
            return false;
        }
        ImplForType that = (ImplForType) obj;
        return this.args.equals(that.args) && this.getRawType().equals(that.getRawType());
    }
    
    private String getSignature() {
        StringBuilder sb = new StringBuilder();
        Type declaringClass = getOwnerType();
        if (declaringClass instanceof ImplForType) {
            ImplForType owner = (ImplForType) declaringClass;
            sb.append(owner.getSignature());
            sb.append('.');
            String innerName = rawTypeName.substring(owner.rawTypeName.length() + 1);
            sb.append(innerName);
        } else {
            sb.append(rawTypeName.replace('.', '/'));
        }
        Type[] typeArgs = getActualTypeArguments();
        if (typeArgs.length > 0) {
            sb.append("<");
            for (Type ta : typeArgs) {
                sb.append(ta.toGenericSignature());
            }
            sb.append(">");
        }
        return sb.toString();
    }
    
    @Override
    public String toGenericSignature() {
        return "L" + getSignature() + ";";
    }
}
