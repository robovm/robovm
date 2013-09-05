/*
 * Copyright (C) 2012 Trillian AB
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
package org.robovm.compiler;

import static org.robovm.compiler.Bro.*;
import static org.robovm.compiler.Mangler.*;
import static org.robovm.compiler.Types.*;
import static org.robovm.compiler.llvm.FunctionAttribute.*;
import static org.robovm.compiler.llvm.Linkage.*;
import static org.robovm.compiler.llvm.Type.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.robovm.compiler.clazz.Clazz;
import org.robovm.compiler.clazz.ClazzInfo;
import org.robovm.compiler.clazz.ClazzInfo.MethodInfo;
import org.robovm.compiler.llvm.Function;
import org.robovm.compiler.llvm.FunctionAttribute;
import org.robovm.compiler.llvm.FunctionRef;
import org.robovm.compiler.llvm.FunctionType;
import org.robovm.compiler.llvm.Linkage;
import org.robovm.compiler.llvm.Type;
import org.robovm.compiler.trampoline.Trampoline;

import soot.SootClass;
import soot.SootField;
import soot.SootMethod;

/**
 * Builds {@link Function} objects. Always adds {@link FunctionAttribute#nounwind}
 * to the function's attributes.
 */
public class FunctionBuilder {
    private FunctionType type;
    private String name;
    private Linkage linkage = null;
    private List<FunctionAttribute> attributes = new ArrayList<FunctionAttribute>();
    private String section = null;
    private List<String> parameterNames = new ArrayList<String>();
    
    public FunctionBuilder() {
        this((String) null, (FunctionType) null);
    }
    
    public FunctionBuilder(Trampoline t) {
        this(t.getFunctionRef());
    }
    
    public FunctionBuilder(FunctionRef ref) {
        this(ref.getName(), ref.getType());
    }
    
    public FunctionBuilder(SootMethod method) {
        this(mangleMethod(method), getFunctionType(method));
    }
    
    public FunctionBuilder(ClazzInfo ci, MethodInfo mi) {
        this(mangleMethod(ci.getInternalName(), mi.getName(), mi.getDesc()), getFunctionType(mi.getDesc(), mi.isStatic()));
    }
    
    public FunctionBuilder(String name, FunctionType type) {
        this.name = name;
        this.type = type;
        attributes.add(FunctionAttribute.nounwind);
    }

    public FunctionBuilder name(String name) {
        this.name = name;
        return this;
    }
    
    public FunctionBuilder suffix(String suffix) {
        this.name += suffix;
        return this;
    }
    
    public FunctionBuilder type(FunctionType type) {
        this.type = type;
        return this;
    }
    
    public FunctionBuilder attrib(FunctionAttribute a) {
        attributes.add(a);
        return this;
    }
    
    public FunctionBuilder attribs(FunctionAttribute ... a) {
        attributes.addAll(Arrays.asList(a));
        return this;
    }
    
    public FunctionBuilder paramName(String p) {
        parameterNames.add(p);
        return this;
    }
    
    public FunctionBuilder paramNames(String ... p) {
        parameterNames.addAll(Arrays.asList(p));
        return this;
    }
    
    public FunctionBuilder linkage(Linkage linkage) {
        this.linkage = linkage;
        return this;
    }

    public FunctionBuilder section(String section) {
        this.section = section;
        return this;
    }
    
    public Function build() {
        Type[] ptypes = type.getParameterTypes();
        for (int i = parameterNames.size(); i < ptypes.length; i++) {
            parameterNames.add("p" + i);
        }
        return new Function(linkage, 
                attributes.toArray(new FunctionAttribute[attributes.size()]), 
                section, name, type, parameterNames.toArray(new String[parameterNames.size()]));
    }
    
    public static Function allocator(SootClass sootClass) {
        return new FunctionBuilder(mangleClass(sootClass), new FunctionType(OBJECT_PTR, ENV_PTR))
                .suffix("_allocator").linkage(_private).attribs(alwaysinline, optsize).build();
    }
    
    public static Function instanceOf(Clazz clazz) {
        return instanceOf(clazz.getInternalName());
    }
    public static Function instanceOf(SootClass sootClass) {
        return instanceOf(getInternalName(sootClass));
    }
    public static Function instanceOf(String internalName) {
        return new FunctionBuilder(mangleClass(internalName), new FunctionType(I32, ENV_PTR, OBJECT_PTR))
                .suffix("_instanceof").linkage(external).attribs(alwaysinline, optsize).build();
    }
    
    public static Function checkcast(Clazz clazz) {
        return checkcast(clazz.getInternalName());
    }
    public static Function checkcast(SootClass sootClass) {
        return checkcast(getInternalName(sootClass));
    }
    public static Function checkcast(String internalName) {
        return new FunctionBuilder(mangleClass(internalName), new FunctionType(OBJECT_PTR, ENV_PTR, OBJECT_PTR))
                .suffix("_checkcast").linkage(external).attribs(alwaysinline, optsize).build();
    }

    public static Function trycatchEnter(SootClass sootClass) {
        return new FunctionBuilder(mangleClass(sootClass), new FunctionType(OBJECT_PTR, ENV_PTR))
                .suffix("_trycatchenter").linkage(external).attribs(alwaysinline, optsize).build();
    }
    
    public static Function ldcInternal(String internalName) {
        return new FunctionBuilder(mangleClass(internalName), new FunctionType(OBJECT_PTR, ENV_PTR))
                .suffix("_ldc").linkage(_private).attribs(alwaysinline, optsize).build();
    }
    
    public static Function ldcInternal(SootClass sootClass) {
        return ldcInternal(getInternalName(sootClass));
    }
    
    public static Function ldcExternal(SootClass sootClass) {
        return new FunctionBuilder(mangleClass(sootClass), new FunctionType(OBJECT_PTR, ENV_PTR))
                .suffix("_ldc_ext").linkage(external).attribs(noinline, optsize).build();
    }
    
    public static Function getter(SootField field) {
        String name = mangleField(field) + "_getter";
        if (field.isStatic()) {
            return new FunctionBuilder(name, new FunctionType(getType(field.getType()), ENV_PTR))
                .linkage(_private).attribs(alwaysinline, optsize).build();
        } else {
            return new FunctionBuilder(name, new FunctionType(getType(field.getType()), ENV_PTR, OBJECT_PTR))
                .linkage(field.isPrivate() ? _private : external).attribs(alwaysinline, optsize).build();
        }
    }
    
    public static Function setter(SootField field) {
        String name = mangleField(field) + "_setter";
        if (field.isStatic()) {
            return new FunctionBuilder(name, new FunctionType(VOID, ENV_PTR, getType(field.getType())))
                .linkage(_private).attribs(alwaysinline, optsize).build();
        } else {
            return new FunctionBuilder(name, new FunctionType(VOID, ENV_PTR, OBJECT_PTR, getType(field.getType())))
                .linkage(field.isPrivate() || field.isFinal() ? _private : external).attribs(alwaysinline, optsize).build();
        }
    }
    
    public static Function clinitWrapper(FunctionRef targetFn) {
        return new FunctionBuilder(targetFn).suffix("_clinit")
                .linkage(external).attribs(noinline, optsize).build();
    }
    
    public static Function callback(SootMethod method) {
        return new FunctionBuilder(method)
            .type(getCallbackFunctionType(method)).suffix("_callback")
            .linkage(external).attribs(noinline, optsize).build();
    }

    public static Function callback(SootMethod method, Type returnType) {
        FunctionType ft = getCallbackFunctionType(method);
        return new FunctionBuilder(method)
            .type(new FunctionType(returnType, ft.isVarargs(), ft.getParameterTypes())).suffix("_callback")
            .linkage(external).attribs(noinline, optsize).build();
    }

    public static Function lookup(SootMethod method, boolean isWeak) {
        return new FunctionBuilder(method).suffix("_lookup").linkage(isWeak ? weak : external).build();
    }

    public static Function lookup(ClazzInfo ci, MethodInfo mi, boolean isWeak) {
        return new FunctionBuilder(ci, mi).suffix("_lookup").linkage(isWeak ? weak : external).build();
    }

    public static Function structMember(SootMethod method) {
        return new FunctionBuilder(method).linkage(external).attribs(noinline, optsize).build();
    }
    
    public static Function structSizeOf(SootMethod method) {
        return new FunctionBuilder(method).linkage(external).attribs(noinline, optsize).build();
    }
    
    public static Function synchronizedWrapper(SootMethod method) {
        return new FunctionBuilder(method).suffix("_synchronized")
                .linkage(external).attribs(noinline, optsize).build();
    }
    
    public static Function method(SootMethod method) {
        return new FunctionBuilder(method).linkage(external)
                .attribs(noinline, optsize).build();
    }
    
    public static Function infoStruct(String internalName) {
        return new FunctionBuilder(mangleClass(internalName), new FunctionType(I8_PTR_PTR))
                .suffix("_info").linkage(external).attribs(alwaysinline, optsize).build();
    }
    
    public static Function infoStruct(SootClass sootClass) {
        return infoStruct(getInternalName(sootClass));
    }
}
