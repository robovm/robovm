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
package org.robovm.compiler.plugin.annotation;

import static org.objectweb.asm.Opcodes.*;
import static org.robovm.compiler.Annotations.*;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.objectweb.asm.ClassWriter;
import org.objectweb.asm.FieldVisitor;
import org.objectweb.asm.Label;
import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Type;
import org.robovm.compiler.ModuleBuilder;
import org.robovm.compiler.Types;
import org.robovm.compiler.clazz.Clazz;
import org.robovm.compiler.config.Config;
import org.robovm.compiler.plugin.AbstractCompilerPlugin;
import org.robovm.compiler.plugin.CompilerPlugin;

import soot.PrimType;
import soot.SootClass;
import soot.SootMethod;
import soot.tagkit.AnnotationClassElem;
import soot.tagkit.AnnotationDefaultTag;
import soot.tagkit.AnnotationDoubleElem;
import soot.tagkit.AnnotationEnumElem;
import soot.tagkit.AnnotationFloatElem;
import soot.tagkit.AnnotationIntElem;
import soot.tagkit.AnnotationLongElem;
import soot.tagkit.AnnotationStringElem;
import soot.tagkit.AnnotationTag;

/**
 * {@link CompilerPlugin} which generates implementation classes for runtime 
 * visible annotations.
 */
public class AnnotationImplPlugin extends AbstractCompilerPlugin {
    private static final int MOD_ANNOTATION = 0x00002000;

    public static final String IMPL_CLASS_NAME_SUFFIX = "$Impl";
    private static final String BASE_CLASS = "org/robovm/rt/annotation/Annotation";

    private boolean initialized = false;

    private void init() {
        if (initialized) {
            return;
        }
        initialized = true;
    }

    private void generateMemberFieldsAndAccessorMethods(Clazz clazz, ClassWriter cw) throws IOException {
        String implName = clazz.getInternalName() + IMPL_CLASS_NAME_SUFFIX;
        SootClass sootClass = clazz.getSootClass();
        List<SootMethod> methods = sootClass.getMethods();
        
        for (SootMethod method : methods) {
            String fieldName = getFieldName(method);
            soot.Type type = method.getReturnType();
            String typeDesc = Types.getDescriptor(type);
            
            // Add the field. Values are always stored as Object. If there was
            // an error in the annotation in the class file this will be an
            // Exception which will get thrown by the accessor method.
            cw.visitField(ACC_PRIVATE, fieldName, "Ljava/lang/Object;", null, null)
                .visitEnd();
            
            // Add the public accessor method
            MethodVisitor mv = cw.visitMethod(ACC_PUBLIC, method.getName(), 
                    Types.getDescriptor(method), null, null);
            mv.visitCode();
            // v = validate(<field>, <memberName>)
            mv.visitVarInsn(ALOAD, 0);
            mv.visitInsn(DUP);
            mv.visitFieldInsn(GETFIELD, implName, fieldName, "Ljava/lang/Object;");
            mv.visitLdcInsn(method.getName());
            mv.visitMethodInsn(INVOKESPECIAL, BASE_CLASS, "validate", "(Ljava/lang/Object;Ljava/lang/String;)Ljava/lang/Object;");
            
            // return v (unboxed if needed)

            int retOp = 0;
            switch (typeDesc.charAt(0)) {
            case 'Z': 
            case 'B':
            case 'S':
            case 'C':
            case 'I':
                retOp = IRETURN;
                break;
            case 'J':
                retOp = LRETURN;
                break;
            case 'F':
                retOp = FRETURN;
                break;
            case 'D':
                retOp = DRETURN;
                break;
            default:
                retOp = ARETURN;
                break;
            } 
            
            unboxIfNeeded(mv, type);
            if (!(type instanceof PrimType)) {
                // Reference type
                mv.visitTypeInsn(CHECKCAST, Types.getInternalName(type));
            }
            mv.visitInsn(retOp);
            
            mv.visitMaxs(0, 0);
            mv.visitEnd();
        }
    }

    private void generateSetDefaultsMethod(Clazz clazz, ClassWriter cw) {
        String implName = clazz.getInternalName() + IMPL_CLASS_NAME_SUFFIX;
        SootClass sootClass = clazz.getSootClass();
        List<SootMethod> methods = sootClass.getMethods();
        // Generate the $setDefaults() method which is called from the constructor
        // to set any default values
        MethodVisitor mv = cw.visitMethod(ACC_PRIVATE, "$setDefaults", "()V", null, null);
        mv.visitCode();
        for (SootMethod method : methods) {
            AnnotationDefaultTag defTag = (AnnotationDefaultTag) method.getTag(AnnotationDefaultTag.class.getSimpleName());
            String fieldName = getFieldName(method);
            if (defTag == null) {
                // No default value. Set field to super.NO_VALUE.
                mv.visitVarInsn(ALOAD, 0);
                mv.visitFieldInsn(GETSTATIC, BASE_CLASS, "NO_VALUE", "Ljava/lang/Object;");
                mv.visitFieldInsn(PUTFIELD, implName, fieldName, "Ljava/lang/Object;");
            } else {
                soot.Type type = method.getReturnType();
                String typeDesc = Types.getDescriptor(type);

                Object v = null;
                if (type instanceof PrimType) {
                    switch (typeDesc.charAt(0)) {
                    case 'Z':
                    case 'B':
                    case 'S':
                    case 'C':
                    case 'I':
                        v = ((AnnotationIntElem) defTag.getDefaultVal()).getValue();
                        break;
                    case 'J':
                        v = ((AnnotationLongElem) defTag.getDefaultVal()).getValue();
                        break;
                    case 'F':
                        v = ((AnnotationFloatElem) defTag.getDefaultVal()).getValue();
                        break;
                    case 'D':
                        v = ((AnnotationDoubleElem) defTag.getDefaultVal()).getValue();
                        break;
                    }
                } else if ("Ljava/lang/Class;".equals(typeDesc)) {
                    v = Type.getType(((AnnotationClassElem) defTag.getDefaultVal()).getDesc());
                    if (((Type) v).getDescriptor().length() != 1) {
                        // Only use a simple LDC for primitive classes (e.g. byte.class).
                        // Other classes may not be available at runtime. By falling back
                        // to Method.getDefaultValue() below we will get the proper
                        // exception at runtime.
                        v = null;
                    }
                } else if ("Ljava/lang/String;".equals(typeDesc)) {
                    v = ((AnnotationStringElem) defTag.getDefaultVal()).getValue();
                }
                if (v != null) {
                    mv.visitVarInsn(ALOAD, 0);
                    if (v instanceof Type && ((Type) v).getDescriptor().length() == 1) {
                        // LDC of primitive type class such as byte.class
                        switch (((Type) v).getDescriptor().charAt(0)) {
                        case 'V':
                            mv.visitFieldInsn(GETSTATIC, "java/lang/Void", "TYPE", "Ljava/lang/Class;");
                            break;
                        case 'Z':
                            mv.visitFieldInsn(GETSTATIC, "java/lang/Boolean", "TYPE", "Ljava/lang/Class;");
                            break;
                        case 'B':
                            mv.visitFieldInsn(GETSTATIC, "java/lang/Byte", "TYPE", "Ljava/lang/Class;");
                            break;
                        case 'S':
                            mv.visitFieldInsn(GETSTATIC, "java/lang/Short", "TYPE", "Ljava/lang/Class;");
                            break;
                        case 'C':
                            mv.visitFieldInsn(GETSTATIC, "java/lang/Character", "TYPE", "Ljava/lang/Class;");
                            break;
                        case 'I':
                            mv.visitFieldInsn(GETSTATIC, "java/lang/Integer", "TYPE", "Ljava/lang/Class;");
                            break;
                        case 'J':
                            mv.visitFieldInsn(GETSTATIC, "java/lang/Long", "TYPE", "Ljava/lang/Class;");
                            break;
                        case 'F':
                            mv.visitFieldInsn(GETSTATIC, "java/lang/Float", "TYPE", "Ljava/lang/Class;");
                            break;
                        case 'D':
                            mv.visitFieldInsn(GETSTATIC, "java/lang/Double", "TYPE", "Ljava/lang/Class;");
                            break;
                        }
                    } else {
                        mv.visitLdcInsn(v);
                    }
                    boxIfNeeded(mv, type);
                    mv.visitFieldInsn(PUTFIELD, implName, fieldName, "Ljava/lang/Object;");
                } else {
                    // Must be class, enum, array type or annotation. Fall back to super.getDefaultValue(<memberName>).
                    mv.visitVarInsn(ALOAD, 0);
                    mv.visitInsn(DUP);
                    mv.visitLdcInsn(method.getName());
                    mv.visitMethodInsn(INVOKESPECIAL, BASE_CLASS, "getDefaultValue", "(Ljava/lang/String;)Ljava/lang/Object;");
                    mv.visitFieldInsn(PUTFIELD, implName, fieldName, "Ljava/lang/Object;");
                }
            }
        }
        mv.visitInsn(RETURN);
        mv.visitMaxs(0, 0);
        mv.visitEnd();
    }
    
    private String getFieldName(SootMethod method) {
        String fieldName = "m$" + method.getName();
        return fieldName;
    }

    private void generateConstructor(Clazz clazz, ClassWriter cw) {
        String implName = clazz.getInternalName() + IMPL_CLASS_NAME_SUFFIX;
        
        // Default constructor
        MethodVisitor mv = cw.visitMethod(ACC_PUBLIC, "<init>", "()V", null, null);
        mv.visitCode();
        mv.visitVarInsn(ALOAD, 0);
        mv.visitLdcInsn(Type.getObjectType(clazz.getInternalName()));
        mv.visitMethodInsn(INVOKESPECIAL, BASE_CLASS, "<init>", "(Ljava/lang/Class;)V");
        mv.visitVarInsn(ALOAD, 0);
        mv.visitMethodInsn(INVOKESPECIAL, implName, "$setDefaults", "()V");
        mv.visitInsn(RETURN);
        mv.visitMaxs(0, 0);
        mv.visitEnd();
    }

    private void generateAnnotationTypeMethod(Clazz clazz, ClassWriter cw) {
        // Class<? extends Annotation> annotationType();
        MethodVisitor mv = cw.visitMethod(ACC_PUBLIC, "annotationType", "()Ljava/lang/Class;", null, null);
        mv.visitCode();
        mv.visitLdcInsn(Type.getObjectType(clazz.getInternalName()));
        mv.visitInsn(ARETURN);
        mv.visitMaxs(0, 0);
        mv.visitEnd();
    }

    private void generateMembersToStringMethod(Clazz clazz, ClassWriter cw) {
        String implName = clazz.getInternalName() + IMPL_CLASS_NAME_SUFFIX;
        
        // void membersToString(StringBuilder sb);
        MethodVisitor mv = cw.visitMethod(ACC_PROTECTED, "membersToString", "(Ljava/lang/StringBuilder;)V", null, null);
        mv.visitCode();
        boolean first = true;
        for (SootMethod method : clazz.getSootClass().getMethods()) {
            String fieldName = getFieldName(method);
            
            mv.visitVarInsn(ALOAD, 0);
            mv.visitVarInsn(ALOAD, 1);
            mv.visitVarInsn(ALOAD, 0);
            mv.visitFieldInsn(GETFIELD, implName, fieldName, "Ljava/lang/Object;");
            mv.visitLdcInsn(method.getName());
            mv.visitInsn(first ? ICONST_1 : ICONST_0);
            mv.visitMethodInsn(INVOKESPECIAL, BASE_CLASS,  "memberToString", "(Ljava/lang/StringBuilder;Ljava/lang/Object;Ljava/lang/String;Z)V");
            
            first = false;
        }
        mv.visitInsn(RETURN);
        mv.visitMaxs(0, 0);
        mv.visitEnd();
    }

    private void generateFastEqualsMethod(Clazz clazz, ClassWriter cw) {
        String implName = clazz.getInternalName() + IMPL_CLASS_NAME_SUFFIX;
        
        // boolean fastEquals(Object that);
        // that is known to of the same class as us.
        MethodVisitor mv = cw.visitMethod(ACC_PROTECTED, "fastEquals", "(Ljava/lang/Object;)Z", null, null);
        mv.visitCode();
        mv.visitVarInsn(ALOAD, 1);
        mv.visitTypeInsn(CHECKCAST, implName);
        mv.visitVarInsn(ASTORE, 1);
        
        for (SootMethod method : clazz.getSootClass().getMethods()) {
            String fieldName = getFieldName(method);
            
            mv.visitVarInsn(ALOAD, 0);
            mv.visitInsn(DUP);
            mv.visitFieldInsn(GETFIELD, implName, fieldName, "Ljava/lang/Object;");
            mv.visitVarInsn(ALOAD, 1);
            mv.visitFieldInsn(GETFIELD, implName, fieldName, "Ljava/lang/Object;");
            mv.visitMethodInsn(INVOKESPECIAL, BASE_CLASS, "memberEquals", "(Ljava/lang/Object;Ljava/lang/Object;)Z");
            Label l1 = new Label();
            mv.visitJumpInsn(IFNE, l1);
            mv.visitInsn(ICONST_0);
            mv.visitInsn(IRETURN);
            mv.visitLabel(l1);
        }
        
        // return true;
        mv.visitInsn(ICONST_1);
        mv.visitInsn(IRETURN);
        mv.visitMaxs(0, 0);
        mv.visitEnd();
    }

    private void boxIfNeeded(MethodVisitor mv, soot.Type type) {
        if (type instanceof PrimType) {
            String typeDesc = Types.getDescriptor(type);
            String wrapperType = null;
            switch (typeDesc.charAt(0)) {
            case 'Z':
                wrapperType = "java/lang/Boolean";
                break;
            case 'B':
                wrapperType = "java/lang/Byte";
                break;
            case 'S':
                wrapperType = "java/lang/Short";
                break;
            case 'C':
                wrapperType = "java/lang/Character";
                break;
            case 'I':
                wrapperType = "java/lang/Integer";
                break;
            case 'J':
                wrapperType = "java/lang/Long";
                break;
            case 'F':
                wrapperType = "java/lang/Float";
                break;
            case 'D':
                wrapperType = "java/lang/Double";
                break;
            }
            mv.visitMethodInsn(INVOKESTATIC, BASE_CLASS, "box", "(" + typeDesc + ")L" + wrapperType + ";");
        }
    }
    
    private void unboxIfNeeded(MethodVisitor mv, soot.Type type) {
        if (type instanceof PrimType) {
            String typeDesc = Types.getDescriptor(type);
            String wrapperType = null;
            switch (typeDesc.charAt(0)) {
            case 'Z': 
                wrapperType = "java/lang/Boolean";
                break;
            case 'B':
                wrapperType = "java/lang/Byte";
                break;
            case 'S':
                wrapperType = "java/lang/Short";
                break;
            case 'C':
                wrapperType = "java/lang/Character";
                break;
            case 'I':
                wrapperType = "java/lang/Integer";
                break;
            case 'J':
                wrapperType = "java/lang/Long";
                break;
            case 'F':
                wrapperType = "java/lang/Float";
                break;
            case 'D':
                wrapperType = "java/lang/Double";
                break;
            }
            mv.visitTypeInsn(CHECKCAST, wrapperType);
            mv.visitMethodInsn(INVOKESTATIC, BASE_CLASS, "unbox", "(L" + wrapperType + ";)" + typeDesc);
        }
    }
    
    private void generateSlowEqualsMethod(Clazz clazz, ClassWriter cw) {
        String implName = clazz.getInternalName() + IMPL_CLASS_NAME_SUFFIX;
        
        // boolean slowEquals(Object that);
        // that is known to be an instance of the same interface but not an instance of our impl class.
        MethodVisitor mv = cw.visitMethod(ACC_PROTECTED, "slowEquals", "(Ljava/lang/Object;)Z", null, null);
        mv.visitCode();
        mv.visitVarInsn(ALOAD, 1);
        mv.visitTypeInsn(CHECKCAST, clazz.getInternalName());
        mv.visitVarInsn(ASTORE, 1);
        
        Label l1 = new Label();
        for (SootMethod method : clazz.getSootClass().getMethods()) {
            String fieldName = getFieldName(method);
            soot.Type type = method.getReturnType();
            
            mv.visitVarInsn(ALOAD, 0);
            mv.visitInsn(DUP);
            mv.visitFieldInsn(GETFIELD, implName, fieldName, "Ljava/lang/Object;");
            mv.visitVarInsn(ALOAD, 1);
            mv.visitMethodInsn(INVOKEINTERFACE, clazz.getInternalName(), method.getName(), "()" + Types.getDescriptor(type));
            boxIfNeeded(mv, type);
            mv.visitMethodInsn(INVOKESPECIAL, BASE_CLASS, "memberEquals", "(Ljava/lang/Object;Ljava/lang/Object;)Z");
            mv.visitJumpInsn(IFEQ, l1);
        }
        
        // return true;
        mv.visitInsn(ICONST_1);
        mv.visitInsn(IRETURN);
        // return false
        mv.visitLabel(l1);
        mv.visitInsn(ICONST_0);
        mv.visitInsn(IRETURN);
        
        mv.visitMaxs(0, 0);
        mv.visitEnd();
    }
    
    private void generateHashCodeMethod(Clazz clazz, ClassWriter cw) {
        String implName = clazz.getInternalName() + IMPL_CLASS_NAME_SUFFIX;
        // int hashCode();
        MethodVisitor mv = cw.visitMethod(ACC_PUBLIC, "hashCode", "()I", null, null);
        mv.visitCode();
        mv.visitInsn(ICONST_0);
        for (SootMethod method : clazz.getSootClass().getMethods()) {
            String fieldName = getFieldName(method);
            
            mv.visitVarInsn(ALOAD, 0);
            mv.visitInsn(DUP);
            mv.visitFieldInsn(GETFIELD, implName, fieldName, "Ljava/lang/Object;");
            mv.visitLdcInsn(method.getName());
            mv.visitMethodInsn(INVOKESPECIAL, BASE_CLASS, "hash", "(Ljava/lang/Object;Ljava/lang/String;)I");
            mv.visitInsn(IADD);
        }
        mv.visitInsn(IRETURN);
        mv.visitMaxs(0, 0);
        mv.visitEnd();
    }
    
    private void generateFactoryMethod(Clazz clazz, ClassWriter cw) throws IOException {
        String implName = clazz.getInternalName() + IMPL_CLASS_NAME_SUFFIX;
        MethodVisitor mv = cw.visitMethod(ACC_PUBLIC + ACC_STATIC, "$create", 
                "()Ljava/lang/Object;", null, null);
        mv.visitCode();
        if (clazz.getSootClass().getMethodCount() == 0) {
            // This annotation has no members. Just call $createSingleton().
            mv.visitMethodInsn(INVOKESTATIC, implName, "$createSingleton", "()Ljava/lang/Object;");
        } else {
            mv.visitTypeInsn(NEW, implName);
            mv.visitInsn(DUP);
            mv.visitMethodInsn(INVOKESPECIAL, implName, "<init>", "()V");
        }
        mv.visitInsn(ARETURN);
        mv.visitMaxs(0, 0);
        mv.visitEnd();
    }
    
    private void generateSingletonFactoryMethod(Clazz clazz, ClassWriter cw) throws IOException {
        String implName = clazz.getInternalName() + IMPL_CLASS_NAME_SUFFIX;
        
        {
            FieldVisitor fv = cw.visitField(ACC_PRIVATE + ACC_FINAL + ACC_STATIC, 
                    "$instance", "L" + implName + ";", null, null);
            fv.visitEnd();
        }
        
        {
            MethodVisitor mv = cw.visitMethod(ACC_STATIC, "<clinit>", "()V", null, null);
            mv.visitCode();
            mv.visitTypeInsn(NEW, implName);
            mv.visitInsn(DUP);
            mv.visitMethodInsn(INVOKESPECIAL, implName, "<init>", "()V");
            mv.visitFieldInsn(PUTSTATIC, implName, "$instance", "L" + implName + ";");
            mv.visitInsn(RETURN);
            mv.visitMaxs(0, 0);
            mv.visitEnd();
        }
        
        {
            MethodVisitor mv = cw.visitMethod(ACC_PUBLIC + ACC_STATIC, "$createSingleton", 
                    "()Ljava/lang/Object;", null, null);
            mv.visitCode();
            mv.visitFieldInsn(GETSTATIC, implName, "$instance", "L" + implName + ";");
            mv.visitInsn(ARETURN);
            mv.visitMaxs(0, 0);
            mv.visitEnd();
        }
    }
    
    @Override
    public void beforeClass(Config config, Clazz clazz, ModuleBuilder moduleBuilder) {
        init();
        SootClass sootClass = clazz.getSootClass();
        if ((sootClass.getModifiers() & MOD_ANNOTATION) > 0) {
            
            try {
                String implInternalName = clazz.getInternalName() + IMPL_CLASS_NAME_SUFFIX;
                ClassWriter cw = new ClassWriter(ClassWriter.COMPUTE_MAXS | ClassWriter.COMPUTE_FRAMES);
                cw.visit(V1_7, ACC_SUPER + ACC_FINAL + ACC_SYNTHETIC + ACC_PUBLIC,
                        implInternalName, null,
                        BASE_CLASS, 
                        new String[] {clazz.getInternalName()});
                
                generateConstructor(clazz, cw);
                generateAnnotationTypeMethod(clazz, cw);
                generateMembersToStringMethod(clazz, cw);                
                generateFastEqualsMethod(clazz, cw);
                generateSlowEqualsMethod(clazz, cw);
                generateHashCodeMethod(clazz, cw);
                generateMemberFieldsAndAccessorMethods(clazz, cw);
                generateSetDefaultsMethod(clazz, cw);
                generateSingletonFactoryMethod(clazz, cw);
                generateFactoryMethod(clazz, cw);
                
                cw.visitEnd();
                
                File f = clazz.getPath().getGeneratedClassFile(implInternalName);
                FileUtils.writeByteArrayToFile(f, cw.toByteArray());
                // The impl class is created after the interface is compiled.
                // This prevents the triggering of a recompile of the interface.
                f.setLastModified(clazz.lastModified());

                // Add the impl class as a dependency for the annotation interface.
                // Important! This must be done AFTER the class file has been written.
                clazz.getClazzInfo().addDependency(implInternalName);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
