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
package org.robovm.compiler.util.generic;

import soot.SootClass;
import soot.SootMethod;
import soot.SootResolver;
import soot.tagkit.EnclosingMethodTag;
import soot.tagkit.InnerClassTag;
import soot.tagkit.SignatureTag;
import soot.tagkit.Tag;

/**
 * {@link Type} implementation which wraps a {@link SootClass} and contains a
 * subset of the methods implemented by {@link Class}.
 */
public class SootClassType extends SootBaseType implements GenericDeclaration {

    private final SootClass sootClass;

    public SootClassType(String name) {
        this(SootResolver.v().makeClassRef(name));
    }
    
    public SootClassType(SootClass sootClass) {
        this.sootClass = sootClass;
    }

    public SootClass getSootClass() {
        return sootClass;
    }
    
    private boolean isAssignableFrom(SootClass sub) {
        if (this.sootClass.equals(sub)) {
            return true;
        }
        if (sootClass.isInterface()) {
            SootClass c = sub;
            while (c != null) {
                for (SootClass ifs : c.getInterfaces()) {
                    if (isAssignableFrom(ifs)) {
                        return true;
                    }
                }
                c = c.hasSuperclass() ? c.getSuperclass() : null;
            }
        } else if (sub.hasSuperclass()) {
            return isAssignableFrom(sub.getSuperclass());
        }
        return false;
    }

    public boolean isAssignableFrom(SootClassType c) {
        return isAssignableFrom(c.sootClass);
    }
    
    @SuppressWarnings("unchecked")
    public TypeVariable<?>[] getTypeParameters() {
        GenericSignatureParser parser = new GenericSignatureParser();
        parser.parseForClass(this, (SignatureTag) sootClass.getTag("SignatureTag"));
        return parser.formalTypeParameters.clone();
    }
    
    public Type[] getGenericInterfaces() {
        GenericSignatureParser parser = new GenericSignatureParser();
        parser.parseForClass(this, (SignatureTag) sootClass.getTag("SignatureTag"));
        return parser.interfaceTypes.getResolvedTypes();
    }

    public Type getGenericSuperclass() {
        GenericSignatureParser parser = new GenericSignatureParser();
        parser.parseForClass(this, (SignatureTag) sootClass.getTag("SignatureTag"));
        return Types.getType(parser.superclassType);
    }

    private SootMethodType getEnclosingMethod(boolean constructor) {
        EnclosingMethodTag emTag = (EnclosingMethodTag) sootClass.getTag("EnclosingMethodTag");
        if (emTag != null) {
            String clsName = emTag.getEnclosingClass();
            String name = emTag.getEnclosingMethod();
            String desc = emTag.getEnclosingMethodSig();
            if (clsName != null && name != null && desc != null) {
                if ((constructor && name.equals("<init>")) 
                        || (!constructor && !name.equals("<init>") && !name.equals("<clinit>"))) {
                    SootClass cls = SootResolver.v().makeClassRef(clsName);
                    if (!cls.isPhantom()) {
                        for (SootMethod m : cls.getMethods()) {
                            String mDesc = org.robovm.compiler.Types.getDescriptor(m);
                            if (m.getName().equals(name) && desc.equals(mDesc)) {
                                return new SootMethodType(m);
                            }
                        }
                    }
                }
            }
        }
        return null;
    }

    public SootMethodType getEnclosingMethod() {
        return getEnclosingMethod(false);
    }

    public SootMethodType getEnclosingConstructor() {
        return getEnclosingMethod(true);
    }

    public SootClassType getEnclosingClass() {
        EnclosingMethodTag emTag = (EnclosingMethodTag) sootClass.getTag("EnclosingMethodTag");
        if (emTag == null) {
            return getDeclaringClass();
        }
        return new SootClassType(emTag.getEnclosingClass().replace('/', '.'));
    }

    public SootClassType getSuperclass() {
        if (sootClass.isPhantom()) {
            return new SootClassType("java.lang.Object");
        }
        return sootClass.hasSuperclass() ? new SootClassType(sootClass.getSuperclass()) : null;
    }

    public SootClassType[] getInterfaces() {
        if (sootClass.isPhantom()) {
            return new SootClassType[0];
        }
        return wrapClasses(sootClass.getInterfaces());
    }

    public SootClassType getDeclaringClass() {
        for (Tag tag : sootClass.getTags()) {
            if (tag instanceof InnerClassTag) {
                InnerClassTag icTag = (InnerClassTag) tag;
                if (icTag.getInnerClass() != null && icTag.getOuterClass() != null) {
                    String innerName = icTag.getInnerClass().replace('/', '.');
                    if (sootClass.getName().equals(innerName)) {
                        return new SootClassType(icTag.getOuterClass().replace('/', '.'));
                    }
                }
            }
        }
        return null;
    }
    
    @Override
    public String toString() {
        return sootClass.getName();
    }
  
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((sootClass == null) ? 0 : sootClass.hashCode());
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
        SootClassType other = (SootClassType) obj;
        if (sootClass == null) {
            if (other.sootClass != null) {
                return false;
            }
        } else if (!sootClass.equals(other.sootClass)) {
            return false;
        }
        return true;
    }

    private String toSimpleClassTypeSignature() {
        StringBuilder sb = new StringBuilder();
        SootClassType declaringClass = getDeclaringClass();
        if (declaringClass != null && declaringClass.sootClass.hasTag("SignatureTag")) {
            sb.append(declaringClass.toSimpleClassTypeSignature());
            sb.append('.');
            String innerName = sootClass.getName().substring(declaringClass.sootClass.getName().length() + 1);
            sb.append(innerName);
        } else {
            sb.append(sootClass.getName().replace('.', '/'));
        }
        TypeVariable<?>[] typeArgs = getTypeParameters();
        if (typeArgs.length > 0) {
            sb.append("<");
            for (TypeVariable<?> ta : typeArgs) {
                sb.append(ta.toGenericSignature());
            }
            sb.append(">");
        }
        return sb.toString();
    }
    
    @Override
    public String toGenericSignature() {
        return "L" + toSimpleClassTypeSignature() + ";";
    }
}
