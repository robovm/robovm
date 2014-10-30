/*
 * Copyright (C) 2014 Trillian Mobile AB
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
package org.robovm.compiler.util.generic;

import java.lang.reflect.Method;

import soot.SootMethod;
import soot.tagkit.SignatureTag;

/**
 * {@link Type} implementation which wraps a {@link SootMethod} and contains a
 * subset of the methods implemented by {@link Method}.
 */
public class SootMethodType extends SootBaseType implements GenericDeclaration {
    private final SootMethod sootMethod;

    public SootMethodType(SootMethod sootMethod) {
        this.sootMethod = sootMethod;
    }

    public SootMethod getSootMethod() {
        return sootMethod;
    }
    
    @SuppressWarnings("unchecked")
    public TypeVariable<SootMethodType>[] getTypeParameters() {
        GenericSignatureParser parser = new GenericSignatureParser();
        parser.parseForMethod(this, (SignatureTag) sootMethod.getTag("SignatureTag"), getExceptionTypes());
        return parser.formalTypeParameters;
    }

    public Type[] getGenericParameterTypes() {
        GenericSignatureParser parser = new GenericSignatureParser();
        parser.parseForMethod(this, (SignatureTag) sootMethod.getTag("SignatureTag"), getExceptionTypes());
        return parser.parameterTypes.getResolvedTypes();
    }

    public Type getGenericReturnType() {
        GenericSignatureParser parser = new GenericSignatureParser();
        parser.parseForMethod(this, (SignatureTag) sootMethod.getTag("SignatureTag"), getExceptionTypes());
        return Types.getType(parser.returnType);
    }

    public Type[] getParameterTypes() {
        return wrapTypes(sootMethod.getParameterTypes());
    }

    public SootClassType[] getExceptionTypes() {
        return wrapClasses(sootMethod.getExceptions());
    }

    public SootClassType getDeclaringClass() {
        return new SootClassType(sootMethod.getDeclaringClass());
    }

    public Type getReturnType() {
        return wrapType(sootMethod.getReturnType());
    }
    
    @Override
    public String toGenericSignature() {
        StringBuilder sb = new StringBuilder("(");
        for (Type t : getGenericParameterTypes()) {
            sb.append(t.toGenericSignature());
        }
        sb.append(")");
        sb.append(getGenericReturnType().toGenericSignature());
        return sb.toString();
    }
    
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((sootMethod == null) ? 0 : sootMethod.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        SootMethodType other = (SootMethodType) obj;
        if (sootMethod == null) {
            if (other.sootMethod != null) {
                return false;
            }
        } else if (!sootMethod.equals(other.sootMethod)) {
            return false;
        }
        return true;
    }
}
