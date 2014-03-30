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
package org.robovm.compiler.plugin.objc;

import static org.objectweb.asm.Opcodes.*;
import static org.robovm.compiler.Annotations.*;
import static org.robovm.compiler.Types.*;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.io.FileUtils;
import org.objectweb.asm.AnnotationVisitor;
import org.objectweb.asm.ClassReader;
import org.objectweb.asm.ClassVisitor;
import org.objectweb.asm.ClassWriter;
import org.objectweb.asm.Label;
import org.objectweb.asm.MethodVisitor;
import org.robovm.compiler.CompilerException;
import org.robovm.compiler.Mangler;
import org.robovm.compiler.Types;
import org.robovm.compiler.clazz.Clazz;
import org.robovm.compiler.config.Config;
import org.robovm.compiler.plugin.AbstractCompilerPlugin;
import org.robovm.compiler.plugin.CompilerPlugin;
import org.robovm.compiler.util.generic.GenericArrayType;
import org.robovm.compiler.util.generic.ParameterizedType;
import org.robovm.compiler.util.generic.SootClassType;
import org.robovm.compiler.util.generic.SootMethodType;
import org.robovm.compiler.util.generic.SootTypeType;
import org.robovm.compiler.util.generic.Type;
import org.robovm.compiler.util.generic.TypeVariable;
import org.robovm.compiler.util.generic.WildcardType;

import soot.BooleanType;
import soot.ByteType;
import soot.CharType;
import soot.DoubleType;
import soot.FloatType;
import soot.IntType;
import soot.LongType;
import soot.PrimType;
import soot.RefType;
import soot.ShortType;
import soot.SootClass;
import soot.SootMethod;
import soot.SootResolver;
import soot.VoidType;
import soot.tagkit.AnnotationClassElem;
import soot.tagkit.AnnotationTag;

/**
 * {@link CompilerPlugin} which generates wrapper @Callback methods for @Block
 * annotated method parameters.
 */
public class ObjCBlockPlugin extends AbstractCompilerPlugin {
    public static final String OBJC_PACKAGE = "org/robovm/objc";
    public static final String OBJC_ANNOTATIONS_PACKAGE = OBJC_PACKAGE + "/annotation";
    public static final String BLOCK = "L" + OBJC_ANNOTATIONS_PACKAGE + "/Block;";
    public static final String RUNNABLE_AS_OBJC_BLOCK_MARSHALER = 
            OBJC_PACKAGE + "/RunnableAsObjCBlockMarshaler";

    static Pattern BLOCK_ANNOTATION_PATTERN = Pattern.compile("@[\\w\\d_]+\\s*");
    static Map<String, String> BLOCK_ANNOTATIONS = new HashMap<>();

    static {
        BLOCK_ANNOTATIONS.put("@ByVal", BY_VAL);
        BLOCK_ANNOTATIONS.put("@ByRef", BY_REF);
        BLOCK_ANNOTATIONS.put("@Pointer", POINTER);
        BLOCK_ANNOTATIONS.put("@MachineSizedFloat", MACHINE_SIZED_FLOAT);
        BLOCK_ANNOTATIONS.put("@MachineSizedSInt", MACHINE_SIZED_S_INT);
        BLOCK_ANNOTATIONS.put("@MachineSizedUInt", MACHINE_SIZED_U_INT);
        BLOCK_ANNOTATIONS.put("@Block", BLOCK);
    }
    
    private SootClass org_robovm_objc_ObjCBlock = null;
    private SootClass java_lang_Boolean = null;
    private SootClass java_lang_Byte = null;
    private SootClass java_lang_Short = null;
    private SootClass java_lang_Character = null;
    private SootClass java_lang_Integer = null;
    private SootClass java_lang_Long = null;
    private SootClass java_lang_Float = null;
    private SootClass java_lang_Double = null;

    private boolean initialized = false;
    
    private void init() {
        if (initialized) {
            return;
        }
        SootResolver r = SootResolver.v();
        org_robovm_objc_ObjCBlock = r.makeClassRef("org.robovm.objc.ObjCBlock");
        java_lang_Boolean = r.makeClassRef("java.lang.Boolean");
        java_lang_Byte = r.makeClassRef("java.lang.Byte");
        java_lang_Short = r.makeClassRef("java.lang.Short");
        java_lang_Character = r.makeClassRef("java.lang.Character");
        java_lang_Integer = r.makeClassRef("java.lang.Integer");
        java_lang_Long = r.makeClassRef("java.lang.Long");
        java_lang_Float = r.makeClassRef("java.lang.Float");
        java_lang_Double = r.makeClassRef("java.lang.Double");
    }
    
    @Override
    public void beforeClass(Config config, Clazz clazz) throws IOException {
        init();
        SootClass sootClass = clazz.getSootClass();
        if (!sootClass.isInterface()) {
            Map<String, Integer> blockTypeIds = new HashMap<>();
            for (SootMethod method : sootClass.getMethods()) {
                if (method.isNative() 
                    && (hasBridgeAnnotation(method)
                        || hasAnnotation(method, ObjCMemberPlugin.METHOD)
                        || hasAnnotation(method, ObjCMemberPlugin.PROPERTY))
                    || hasCallbackAnnotation(method)) {
                    
                    int[] indexes = getBlockParameterIndexes(method);
                    if (indexes != null || hasAnnotation(method, BLOCK)) {
                        transformMethod(config, clazz, method, indexes, blockTypeIds);
                    }
                }
            }
        }
    }

    private int[] getBlockParameterIndexes(SootMethod method) {
        int[] indexes = null;
        int paramCount = method.getParameterCount();
        int idxCount = 0;
        for (int i = 0; i < paramCount; i++) {
            if (hasParameterAnnotation(method, i, BLOCK)) {
                if (indexes == null) {
                    indexes = new int[paramCount];
                    Arrays.fill(indexes, -1);
                }
                indexes[idxCount++] = i;
            }
        }
        return indexes;
    }
    
    private void transformMethod(Config config, Clazz clazz, SootMethod blockMethod, 
            int[] blockParamIndexes, Map<String, Integer> blockTypeIds) throws IOException {
        
        SootMethodType blockMethodType = new SootMethodType(blockMethod);
        if (blockParamIndexes != null) {
            Type[] genericParameterTypes = blockMethodType.getGenericParameterTypes();
            for (int i = 0; i < blockParamIndexes.length; i++) {
                int idx = blockParamIndexes[i];
                if (idx == -1) {
                    break;
                }
                
                SootMethod targetMethod = getBlockTargetMethod(blockMethod, idx);
                soot.Type[] actualTypes = resolveTargetMethodSignature(
                        blockMethod, targetMethod, genericParameterTypes[idx]);
                soot.Type[] unboxedTypes = unboxTypes(actualTypes);
                String[][] targetMethodAnnotations = 
                    parseTargetMethodAnnotations(targetMethod, 
                        readStringElem(
                            getParameterAnnotation(blockMethod, idx, BLOCK), "value", ""));
                
                // Create the marshaler class associated with this block type
                String marshaler = createBlockMarshaler(config, clazz, 
                        targetMethod, actualTypes, unboxedTypes, blockTypeIds, 
                        targetMethodAnnotations);
                addMarshalerAnnotation(blockMethod, idx, marshaler);
            }
        }
        if (hasAnnotation(blockMethod, BLOCK)) {
            SootMethod targetMethod = getBlockTargetMethod(blockMethod);
            soot.Type[] actualTypes = resolveTargetMethodSignature(
                    blockMethod, targetMethod, blockMethodType.getGenericReturnType());
            soot.Type[] unboxedTypes = unboxTypes(actualTypes);
            String[][] targetMethodAnnotations = 
                    parseTargetMethodAnnotations(targetMethod, 
                        readStringElem(
                            getAnnotation(blockMethod, BLOCK), "value", ""));
            String marshaler = createBlockMarshaler(config, clazz, targetMethod, 
                    actualTypes, unboxedTypes, blockTypeIds, targetMethodAnnotations);
            addMarshalerAnnotation(blockMethod, marshaler);
        }
    }

    private static int parseAnnotations(SootMethod m, String originalValue, String value, TreeSet<String> values) {
        Matcher matcher = BLOCK_ANNOTATION_PATTERN.matcher(value);
        int pos = 0;
        while (matcher.find()) {
            if (matcher.start() != pos) {
                break;
            }
            String anno = BLOCK_ANNOTATIONS.get(matcher.group().trim());
            if (anno == null) {
                throw new CompilerException("Unsupported annotation \"" 
                        + matcher.group().trim() + "\" in @Block annotation value \"" 
                        + originalValue + "\" on method " + m);
            }
            values.add(anno);
            pos = matcher.end();
        }
        return pos;
    }
    
    protected static String[][] parseTargetMethodAnnotations(SootMethod m, String value) {
        return parseTargetMethodAnnotations(m, m.getParameterCount(), value);
    }
    
    protected static String[][] parseTargetMethodAnnotations(SootMethod m, int paramCount, String value) {
        String originalValue = value;
        value = value.trim();
        String[][] result = new String[paramCount + 1][];
        if (value.length() == 0) {
            Arrays.fill(result, new String[0]);
        } else {
            TreeSet<String> values = new TreeSet<>();
            int pos = parseAnnotations(m, originalValue, value, values);
            result[0] = new String[values.size()];
            values.toArray(result[0]);
            
            if (pos < value.length()) {
                if (value.charAt(pos) != '(') {
                    throw new CompilerException("Error in @Block annotation value \"" 
                            + originalValue + "\" on method " + m 
                            + ". Expected '(' but got '" 
                            + value.charAt(pos) + "'.");
                }
                if (pos + 1 == value.length()) {
                    throw new CompilerException("Error in @Block annotation value \"" 
                            + originalValue + "\" on method " + m 
                            + ". Expected a ')' at end of value but "
                            + "got end of string.");
                }
                value = value.substring(pos + 1).trim();
                if (value.charAt(value.length() - 1) != ')') {
                    throw new CompilerException("Error in @Block annotation value \"" 
                            + originalValue + "\" on method " + m 
                            + ". Expected a ')' at end of value but got '" 
                            + value.charAt(value.length() - 1) + "'.");
                }
                value = value.substring(0, value.length() - 1).trim();
                
                if (value.length() > 0 || paramCount > 0) {
                    String[] parts = value.split(",", paramCount + 1);
                    if (parts.length != paramCount) {
                        throw new CompilerException("Error in @Block annotation value \"" 
                                + originalValue + "\" on method " + m 
                                + ". Expected " + paramCount + " parameters");                    
                    }
                    for (int i = 0; i < parts.length; i++) {
                        String p = parts[i].trim();
                        values = new TreeSet<>();
                        pos = parseAnnotations(m, originalValue, p, values);
                        if (pos != p.length()) {
                            throw new CompilerException("Error in @Block annotation value \"" 
                                    + originalValue + "\" on method " + m 
                                    + ". Expected a ',' after parameter " 
                                    + (i + 1) + " but got '" + p.charAt(pos) + "'.");
                        }
                        result[i + 1] = new String[values.size()];
                        values.toArray(result[i + 1]);
                    }
                }
            }
        }
        return result;
    }

    static void addMarshalerAnnotation(SootMethod method, String marshalerName) {
        AnnotationTag annotationTag = new AnnotationTag(MARSHALER, 1);
        annotationTag.addElem(new AnnotationClassElem("L" + marshalerName + ";", 'c', "value"));
        addRuntimeVisibleAnnotation(method, annotationTag);
    }

    static void addMarshalerAnnotation(SootMethod method, int paramIndex, String marshalerName) {
        AnnotationTag annotationTag = new AnnotationTag(MARSHALER, 1);
        annotationTag.addElem(new AnnotationClassElem("L" + marshalerName + ";", 'c', "value"));
        addRuntimeVisibleParameterAnnotation(method, paramIndex, annotationTag);
    }

    private String getBlockMarshalerName(Clazz clazz, int id) {
        return clazz.getInternalName() + "$$BlockMarshaler" + id;
    }

    private String createBlockMarshaler(Config config, Clazz clazz,
            final SootMethod targetMethod, soot.Type[] actualTypes, soot.Type[] unboxedTypes,
            Map<String, Integer> blockTypeIds, String[][] targetMethodAnnotations) throws IOException {
        
        if (targetMethod.getDeclaringClass().getName().equals("java.lang.Runnable") 
                && targetMethod.getName().equals("run")) {
            return RUNNABLE_AS_OBJC_BLOCK_MARSHALER;
        }
        
        String targetMethodKey = getTargetMethodKey(targetMethod, actualTypes, 
                targetMethodAnnotations);
        Integer id = blockTypeIds.get(targetMethodKey);
        if (id != null) {
            // Already generated
            return getBlockMarshalerName(clazz, id);
        }
        
        id = blockTypeIds.size();
        blockTypeIds.put(targetMethodKey, id);
        
        final String blockMarshalerName = getBlockMarshalerName(clazz, id);
        final String targetInterfaceName = Types.getInternalName(targetMethod.getDeclaringClass());

        // We use RunnableAsObjCBlockMarshaler as template
        Clazz templateMarshaler = config.getClazzes().load(RUNNABLE_AS_OBJC_BLOCK_MARSHALER);
        
        final Set<String> usedBoxMethods = new HashSet<>();
        final Set<String> usedUnboxMethods = new HashSet<>();
        
        ClassWriter cw = new ClassWriter(ClassWriter.COMPUTE_MAXS);
        generateTargetMethod(blockMarshalerName, targetMethod, actualTypes, 
                unboxedTypes, usedBoxMethods, usedUnboxMethods, cw);
        generateBridgeMethod(unboxedTypes, targetMethodAnnotations, cw);
        generateCallbackMethod(blockMarshalerName, targetMethod, actualTypes, 
                unboxedTypes, usedBoxMethods, usedUnboxMethods, targetMethodAnnotations, cw);
        ClassReader classReader = new ClassReader(templateMarshaler.getBytes());
        classReader.accept(new ClassVisitor(ASM4, cw) {
            @Override
            public void visit(int version, int access, String name, String signature, String superName,
                    String[] interfaces) {
                super.visit(version, access, blockMarshalerName, signature, 
                        superName, new String[] {targetInterfaceName});
            }
            
            @Override
            public void visitInnerClass(String name, String outerName, String innerName, int access) {
                // Ignore
            }

            @Override
            public void visitSource(String source, String debug) {
                // Ignore
            }
            
            @Override
            public MethodVisitor visitMethod(int access, String name, String desc, 
                    String signature, String[] exceptions) {
                
                switch (name) {
                case "run":
                case "invoke":
                case "invoked":
                    // Skip all these
                    return null;
                case "box":
                    if (!usedBoxMethods.contains(desc)) {
                        return null;
                    }
                    break;
                case "unbox":
                    if (!usedUnboxMethods.contains(desc)) {
                        return null;
                    }
                    break;
                }

                desc = desc.replace("java/lang/Runnable", targetInterfaceName);
                signature = null;
                
                // Return a MethodVisitor which changes all occurrences of 
                // RunnableAsObjCBlockMarshaler to the blockMarshalerName
                return new MethodVisitor(ASM4, super.visitMethod(access, name, desc, signature, exceptions)) {
                    @Override
                    public void visitLdcInsn(Object cst) {
                        if (cst instanceof org.objectweb.asm.Type) {
                            if (((org.objectweb.asm.Type) cst).getSort() == org.objectweb.asm.Type.OBJECT) {
                                String internalName = ((org.objectweb.asm.Type) cst).getInternalName();
                                if (RUNNABLE_AS_OBJC_BLOCK_MARSHALER.equals(internalName)) {
                                    cst = org.objectweb.asm.Type.getObjectType(blockMarshalerName);
                                }
                            }
                        }
                        super.visitLdcInsn(cst);
                    }
                    
                    @Override
                    public void visitTypeInsn(int opcode, String type) {
                        if (RUNNABLE_AS_OBJC_BLOCK_MARSHALER.equals(type)) {
                            type = blockMarshalerName;
                        } else if ("java/lang/Runnable".equals(type)) {
                            type = targetInterfaceName;
                        }
                        super.visitTypeInsn(opcode, type);
                    }
                    
                    @Override
                    public void visitFieldInsn(int opcode, String owner, String name, String desc) {
                        if (RUNNABLE_AS_OBJC_BLOCK_MARSHALER.equals(owner)) {
                            owner = blockMarshalerName;
                        }
                        super.visitFieldInsn(opcode, owner, name, desc);
                    }
                    
                    @Override
                    public void visitMethodInsn(int opcode, String owner, String name, String desc) {
                        if (RUNNABLE_AS_OBJC_BLOCK_MARSHALER.equals(owner)) {
                            owner = blockMarshalerName;
                        }
                        super.visitMethodInsn(opcode, owner, name, desc);
                    }
                    
                    @Override
                    public void visitLocalVariable(String name, String desc, String signature, Label start, Label end,
                            int index) {
                        // Ignored
                    }
                    
                    @Override
                    public void visitLineNumber(int line, Label start) {
                        // Ignored
                    }
                };
            }
        }, 0);
        
        cw.visitInnerClass(blockMarshalerName, clazz.getInternalName(), 
                blockMarshalerName.substring(clazz.getInternalName().length() + 1), 
                ACC_PUBLIC + ACC_STATIC);

        cw.visitEnd();
        
        File f = clazz.getPath().getGeneratedClassFile(blockMarshalerName);
        FileUtils.writeByteArrayToFile(f, cw.toByteArray());
        // The marshaler class is created after the class is compiled.
        // This prevents the triggering of a recompile of the class.
        f.setLastModified(clazz.lastModified());

        return blockMarshalerName;
    }

    private void generateBridgeMethod(soot.Type[] unboxedTypes, 
            String[][] targetMethodAnnotations, ClassWriter cw) {
        
        List<soot.Type> paramTypes = new ArrayList<>();
        paramTypes.add(LongType.v());
        paramTypes.add(org_robovm_objc_ObjCBlock.getType());
        paramTypes.addAll(Arrays.asList(unboxedTypes).subList(1, unboxedTypes.length));

        String name = "invoke";
        String desc = getDescriptor(paramTypes, unboxedTypes[0]);
        
        MethodVisitor mv = cw.visitMethod(ACC_PRIVATE | ACC_STATIC | ACC_NATIVE, name, desc, null, null);
        AnnotationVisitor av = mv.visitAnnotation(BRIDGE, true);
        av.visit("dynamic", true);
        av.visitEnd();
        for (String s : targetMethodAnnotations[0]) {
            mv.visitAnnotation(s, true).visitEnd();
        }
        for (int i = 1; i < targetMethodAnnotations.length; i++) {
            for (String s : targetMethodAnnotations[i]) {
                // We add 2 parameters first so annotations for the first 
                // parameter must be added at index 2.
                mv.visitParameterAnnotation(i + 1, s, true).visitEnd();
            }
        }

        mv.visitParameterAnnotation(0, POINTER, true).visitEnd();
        mv.visitEnd();
    }
    
    private void generateCallbackMethod(String owner, SootMethod targetMethod, 
            soot.Type[] actualTypes, soot.Type[] unboxedTypes, 
            Set<String> usedBoxMethods, Set<String> usedUnboxMethods,
            String[][] targetMethodAnnotations, ClassWriter cw) {
        
        String targetInterfaceName = Types.getInternalName(targetMethod.getDeclaringClass());

        List<soot.Type> paramTypes = new ArrayList<>();
        paramTypes.add(org_robovm_objc_ObjCBlock.getType());
        paramTypes.addAll(Arrays.asList(unboxedTypes).subList(1, unboxedTypes.length));
        
        String name = "invoked";
        String desc = getDescriptor(paramTypes, unboxedTypes[0]);
        
        MethodVisitor mv = cw.visitMethod(ACC_PRIVATE | ACC_STATIC, name, desc, null, null);
        mv.visitAnnotation(CALLBACK, true).visitEnd();

        for (String s : targetMethodAnnotations[0]) {
            mv.visitAnnotation(s, true).visitEnd();
        }
        for (int i = 1; i < targetMethodAnnotations.length; i++) {
            for (String s : targetMethodAnnotations[i]) {
                // We add 1 parameter first so annotations for the first 
                // parameter should be added at index 1.
                mv.visitParameterAnnotation(i, s, true).visitEnd();
            }
        }

        mv.visitCode();
        
        mv.visitVarInsn(ALOAD, 0);
        mv.visitMethodInsn(INVOKEVIRTUAL, "org/robovm/objc/ObjCBlock", "object", "()Ljava/lang/Object;");
        mv.visitTypeInsn(CHECKCAST, targetInterfaceName);
        
        for (int i = 1, var = 1; i < actualTypes.length; i++, var++) {
            soot.Type from = unboxedTypes[i];
            if (from == LongType.v()) {
                mv.visitVarInsn(LLOAD, var);
                var++; // longs need 2 slots
            } else if (from == FloatType.v()) {
                mv.visitVarInsn(FLOAD, var);
            } else if (from == DoubleType.v()) {
                mv.visitVarInsn(DLOAD, var);
                var++; // doubles need 2 slots
            } else if (from instanceof PrimType) {
                // boolean, byte, short, char and int are loaded using ILOAD
                mv.visitVarInsn(ILOAD, var);
            } else {
                // Reference
                mv.visitVarInsn(ALOAD, var);
            }
            
            soot.Type to = actualTypes[i];
            if (from != to) {
                // Box the value on the top of the stack.
                String boxDesc = getDescriptor(Collections.singletonList(from), to);
                usedBoxMethods.add(boxDesc);
                mv.visitMethodInsn(INVOKESTATIC, owner, "box", boxDesc);
            }
        }
        
        // Now the receiver and all arguments are on the stack (boxed if needed). 
        // Call the target method.
        
        mv.visitMethodInsn(INVOKEINTERFACE, targetInterfaceName, 
                targetMethod.getName(), getDescriptor(targetMethod));

        if (unboxedTypes[0] != actualTypes[0]) {
            mv.visitTypeInsn(CHECKCAST, getInternalName(actualTypes[0]));
            // Unbox the value on the top of the stack.
            String unboxDesc = getDescriptor(Collections.singletonList(actualTypes[0]), unboxedTypes[0]);
            usedUnboxMethods.add(unboxDesc);
            mv.visitMethodInsn(INVOKESTATIC, owner, "unbox", unboxDesc);
        }
        
        if (unboxedTypes[0] == VoidType.v()) {
            mv.visitInsn(RETURN);
        } else if (unboxedTypes[0] == LongType.v()) {
            mv.visitInsn(LRETURN);
        } else if (unboxedTypes[0] == FloatType.v()) {
            mv.visitInsn(FRETURN);
        } else if (unboxedTypes[0] == DoubleType.v()) {
            mv.visitInsn(DRETURN);
        } else if (unboxedTypes[0] instanceof PrimType) {
            mv.visitInsn(IRETURN);
        } else {
            mv.visitInsn(ARETURN);
        }
        
        mv.visitMaxs(0, 0);
        mv.visitEnd();
    }
    
    private void generateTargetMethod(String owner, SootMethod targetMethod, 
            soot.Type[] actualTypes, soot.Type[] unboxedTypes, 
            Set<String> usedBoxMethods, Set<String> usedUnboxMethods, ClassWriter cw) {

        String name = targetMethod.getName();
        String desc = getDescriptor(targetMethod);
        
        MethodVisitor mv = cw.visitMethod(ACC_PUBLIC, name, desc, null, null);
        mv.visitCode();
        
        mv.visitVarInsn(ALOAD, 0);
        mv.visitFieldInsn(GETFIELD, owner, "objCBlock", "L" + getInternalName(org_robovm_objc_ObjCBlock) + ";");
        mv.visitMethodInsn(INVOKEVIRTUAL, getInternalName(org_robovm_objc_ObjCBlock), "invoke", "()J");
        mv.visitVarInsn(ALOAD, 0);
        mv.visitFieldInsn(GETFIELD, owner, "objCBlock", "L" + getInternalName(org_robovm_objc_ObjCBlock) + ";");
        
        for (int i = 1, var = 1; i < actualTypes.length; i++, var++) {
            soot.Type from = actualTypes[i];
            if (from == LongType.v()) {
                mv.visitVarInsn(LLOAD, var);
                var++; // longs need 2 slots
            } else if (from == FloatType.v()) {
                mv.visitVarInsn(FLOAD, var);
            } else if (from == DoubleType.v()) {
                mv.visitVarInsn(DLOAD, var);
                var++; // doubles need 2 slots
            } else if (from instanceof PrimType) {
                // boolean, byte, short, char and int are loaded using ILOAD
                mv.visitVarInsn(ILOAD, var);
            } else {
                // Reference
                mv.visitVarInsn(ALOAD, var);
            }
            
            soot.Type to = unboxedTypes[i];
            if (from != to) {
                mv.visitTypeInsn(CHECKCAST, getInternalName(from));
                // Unbox the value on the top of the stack.
                String unboxDesc = getDescriptor(Collections.singletonList(from), to);
                usedUnboxMethods.add(unboxDesc);
                mv.visitMethodInsn(INVOKESTATIC, owner, "unbox", unboxDesc);
            }
        }
        
        // Now the function pointer, block and all arguments are on the stack 
        // (unboxed if needed). Call the invoke() bridge method.
        
        List<soot.Type> paramTypes = new ArrayList<>();
        paramTypes.add(LongType.v());
        paramTypes.add(org_robovm_objc_ObjCBlock.getType());
        paramTypes.addAll(Arrays.asList(unboxedTypes).subList(1, unboxedTypes.length));

        mv.visitMethodInsn(INVOKESTATIC, owner, 
                "invoke", getDescriptor(paramTypes, unboxedTypes[0]));
        
        if (unboxedTypes[0] != actualTypes[0]) {
            // Box the value on the top of the stack.
            String boxDesc = getDescriptor(Collections.singletonList(unboxedTypes[0]), actualTypes[0]);
            usedBoxMethods.add(boxDesc);
            mv.visitMethodInsn(INVOKESTATIC, owner, "box", boxDesc);
        }
        
        if (actualTypes[0] == VoidType.v()) {
            mv.visitInsn(RETURN);
        } else if (actualTypes[0] == LongType.v()) {
            mv.visitInsn(LRETURN);
        } else if (actualTypes[0] == FloatType.v()) {
            mv.visitInsn(FRETURN);
        } else if (actualTypes[0] == DoubleType.v()) {
            mv.visitInsn(DRETURN);
        } else if (actualTypes[0] instanceof PrimType) {
            mv.visitInsn(IRETURN);
        } else {
            mv.visitInsn(ARETURN);
        }
        
        mv.visitMaxs(0, 0);
        mv.visitEnd();
    }
    
    protected String getTargetMethodKey(SootMethod targetMethod, soot.Type[] actualTypes, 
            String[][] targetMethodAnnotations) {
        
        StringBuilder key = new StringBuilder(
            Mangler.mangleMethod(Types.getInternalName(targetMethod.getDeclaringClass()), 
                targetMethod.getName(), 
                Arrays.asList(actualTypes).subList(1, actualTypes.length), actualTypes[0]));
        
        for (String[] a : targetMethodAnnotations) {
            key.append(',');
            for (String s : a) {
                key.append(s);
            }
        }
        
        return key.toString();
    }
    
    private soot.Type unboxType(soot.Type t) {
        if (t instanceof RefType) {
            SootClass c = ((RefType) t).getSootClass();
            if (c.equals(java_lang_Boolean)) {
                return BooleanType.v();
            }
            if (c.equals(java_lang_Byte)) {
                return ByteType.v();
            }
            if (c.equals(java_lang_Short)) {
                return ShortType.v();
            }
            if (c.equals(java_lang_Character)) {
                return CharType.v();
            }
            if (c.equals(java_lang_Integer)) {
                return IntType.v();
            }
            if (c.equals(java_lang_Long)) {
                return LongType.v();
            }
            if (c.equals(java_lang_Float)) {
                return FloatType.v();
            }
            if (c.equals(java_lang_Double)) {
                return DoubleType.v();
            }
        }
        return t;
    }
    
    private soot.Type[] unboxTypes(soot.Type[] actualTypes) {
        soot.Type[] unboxed = actualTypes;
        for (int i = 0; i < actualTypes.length; i++) {
            soot.Type t = unboxType(actualTypes[i]);
            if (t != actualTypes[i]) {
                if (unboxed == actualTypes) {
                    unboxed = new soot.Type[actualTypes.length];
                    System.arraycopy(actualTypes, 0, unboxed, 0, actualTypes.length);
                }
                unboxed[i] = t;
            }
        }
        return unboxed;
    }

    private static List<SootMethod> collectAbstractMethods(SootClass interfaze) {
        ArrayList<SootMethod> result = new ArrayList<>();
        for (SootMethod m : interfaze.getMethods()) {
            if (m.isAbstract()) {
                result.add(m);
            }
        }
        for (SootClass c : interfaze.getInterfaces()) {
            result.addAll(collectAbstractMethods(c));
        }
        return result;
    }
    
    protected static SootMethod getBlockTargetMethod(SootMethod method, int paramIndex) {
        soot.Type type = method.getParameterType(paramIndex);
        if (!(type instanceof RefType)) {
            throw new CompilerException("@Block annotated parameter " + (paramIndex + 1) 
                    + " of method " + method + " must be of interface type");
        }
        SootClass blockType = ((RefType) type).getSootClass();
        if (!blockType.isInterface()) {
            throw new CompilerException("@Block annotated parameter " + (paramIndex + 1) 
                    + " of method " + method + " must be of interface type");
        }
        List<SootMethod> allMethods = collectAbstractMethods(blockType);
        if (allMethods.isEmpty()) {
            throw new CompilerException("No abstract method found in interface " 
                    + blockType + " used in @Block annotated parameter " + (paramIndex + 1) 
                    + " of method " + method);
        }
        if (allMethods.size() > 1) {
            throw new CompilerException("More than 1 abstract method found in interface " 
                    + blockType + " used in @Block annotated parameter " + (paramIndex + 1) 
                    + " of method " + method);
        }
        
        return allMethods.get(0);
    }
    
    protected static SootMethod getBlockTargetMethod(SootMethod method) {
        soot.Type type = method.getReturnType();
        if (!(type instanceof RefType)) {
            throw new CompilerException("@Block annotated return type of method " 
                    + method + " must be of interface type");
        }
        SootClass blockType = ((RefType) type).getSootClass();
        if (!blockType.isInterface()) {
            throw new CompilerException("@Block annotated parameter return type " 
                    + "of method " + method + " must be of interface type");
        }
        List<SootMethod> allMethods = collectAbstractMethods(blockType);
        if (allMethods.isEmpty()) {
            throw new CompilerException("No abstract method found in interface " 
                    + blockType + " used in @Block annotated return type of method " + method);
        }
        if (allMethods.size() > 1) {
            throw new CompilerException("More than 1 abstract method found in interface " 
                    + blockType + " used in @Block annotated return type of method " + method);
        }
        
        return allMethods.get(0);
    }
   
    
    protected static soot.Type[] resolveTargetMethodSignature(SootMethod blockMethod, 
            SootMethod targetMethod, Type blockParamType) {
        
        if (targetMethod.getTag("SignatureTag") == null) {
            // Not a generic method.
            soot.Type[] result = new soot.Type[targetMethod.getParameterCount() + 1];
            result[0] = targetMethod.getReturnType();
            for (int i = 1; i < result.length; i++) {
                result[i] = targetMethod.getParameterType(i - 1);
            }
            return result;
        }
        
        SootClassType base = new SootClassType(targetMethod.getDeclaringClass());
        TypeVariable<SootClassType>[] typeParameters = base.getTypeParameters();
        SootClassType offspring = null;
        Type[] actualArgs = null;
        if (blockParamType instanceof SootClassType) {
            offspring = (SootClassType) blockParamType;
            actualArgs = new Type[0];
        } else if (blockParamType instanceof ParameterizedType) {
            offspring = (SootClassType) ((ParameterizedType) blockParamType).getRawType();
            actualArgs = ((ParameterizedType) blockParamType).getActualTypeArguments();
        }
        
        Type[] resolvedArgs = resolveActualTypeArgs(offspring, base, actualArgs);
        
        soot.Type[] result = new soot.Type[targetMethod.getParameterCount() + 1];
        SootMethodType targetMethodType = new SootMethodType(targetMethod);
        result[0] = resolveMethodType(blockMethod, -1, targetMethodType.getGenericReturnType(), 
                resolvedArgs, typeParameters);
        Type[] genericParameterTypes = targetMethodType.getGenericParameterTypes();
        for (int i = 1; i < result.length; i++) {
            result[i] = resolveMethodType(blockMethod, i - 1,
                    genericParameterTypes[i - 1],
                    resolvedArgs, typeParameters);
        }
        
        return result;
    }
    
    private static soot.Type resolveMethodType(SootMethod blockMethod, int paramIndex,
            Type t, Type[] resolvedArgs, 
            TypeVariable<SootClassType>[] typeParameters) {
        
        if (t instanceof SootClassType) {
            return ((SootClassType) t).getSootClass().getType();
        }
        if (t instanceof SootTypeType) {
            return ((SootTypeType) t).getSootType();
        }
        if (t instanceof TypeVariable) {
            int idx = indexOf(((TypeVariable<?>) t).getName(), typeParameters);
            if (idx != -1) {
                Type u = resolvedArgs[idx];
                if (u instanceof TypeVariable) {
                    if (((TypeVariable<?>) t).getName().equals(((TypeVariable<?>) u).getName())) {
                        return resolveMethodType(blockMethod, paramIndex,
                                ((TypeVariable<?>) t).getBounds()[0], 
                                resolvedArgs, typeParameters);
                    }
                }
                return resolveMethodType(blockMethod, paramIndex, resolvedArgs[idx], 
                        resolvedArgs, typeParameters);
            }
            throw new CompilerException("Unresolved type variable " + t 
                    + " in " 
                    + (paramIndex == -1 ? "return type" : "parameter " + (paramIndex + 1)) 
                    + " of @Block method " + blockMethod);
        }
        if (t instanceof WildcardType) {
            Type[] upperBounds = ((WildcardType) t).getUpperBounds();
            return resolveMethodType(blockMethod, paramIndex, upperBounds[0], 
                    resolvedArgs, typeParameters);
        }
        if (t instanceof ParameterizedType) {
            return resolveMethodType(blockMethod, paramIndex, 
                    ((ParameterizedType) t).getRawType(), 
                    resolvedArgs, typeParameters);
        }
        if (t instanceof GenericArrayType) {
            soot.Type componentType = resolveMethodType(blockMethod, paramIndex,
                    ((GenericArrayType) t).getGenericComponentType(), 
                    resolvedArgs, typeParameters);
            return componentType.makeArrayType();
        }
        
        throw new CompilerException("Unresolved type " + t 
                + " in " 
                + (paramIndex == -1 ? "return type" : "parameter " + (paramIndex + 1)) 
                + " of @Block method " + blockMethod);
    }
    
    private static int indexOf(String name, TypeVariable<SootClassType>[] typeParameters) {
        for (int i = 0; i < typeParameters.length; i++) {
            if (name.equals(typeParameters[i].getName())) {
                return i;
            }
        }
        return -1;
    }
    
    /**
     * Resolves the actual generic type arguments for a base class, as viewed from a subclass or implementation.
     * 
     * @param offspring class or interface subclassing or extending the base type
     * @param base base class
     * @param actualArgs the actual type arguments passed to the offspring class
     * @return actual generic type arguments, must match the type parameters of the offspring class. If omitted, the
     * type parameters will be used instead.
     * 
     * This code was copied from
     * http://stackoverflow.com/questions/17297308/how-do-i-resolve-the-actual-type-for-a-generic-return-type-using-reflection
     * and changed slightly.
     */
    protected static Type[] resolveActualTypeArgs(SootClassType offspring, SootClassType base, 
            Type... actualArgs) {

        TypeVariable<SootClassType>[] typeParameters = offspring.getTypeParameters();
        //  If actual types are omitted, the type parameters will be used instead.
        if (actualArgs.length == 0) {
            actualArgs = typeParameters;
        }
        // map type parameters into the actual types
        Map<String, Type> typeVariables = new HashMap<>();
        for (int i = 0; i < actualArgs.length; i++) {
            TypeVariable<?> typeVariable = (TypeVariable<?>) typeParameters[i];
            Type t = actualArgs[i];
            if (t instanceof WildcardType) {
                // If actual arg is ? it will have an upper bound of java.lang.Object but the
                // TypeVariable could specify a more specific type.
                Type upper = ((WildcardType) t).getUpperBounds()[0];
                if (upper instanceof SootClassType) {
                    if ("java.lang.Object".equals(((SootClassType) upper).getSootClass().getName())) {
                        actualArgs[i] = typeVariable.getBounds()[0];
                    }
                }
            }
            typeVariables.put(typeVariable.getName(), actualArgs[i]);
        }

        // Find direct ancestors (superclass, interfaces)
        List<Type> ancestors = new LinkedList<Type>();
        if (offspring.getGenericSuperclass() != null) {
            ancestors.add(offspring.getGenericSuperclass());
        }
        for (Type t : offspring.getGenericInterfaces()) {
            ancestors.add(t);
        }

        // Recurse into ancestors (superclass, interfaces)
        for (Type type : ancestors) {
            if (type instanceof SootClassType) {
                // ancestor is non-parameterized. Recurse only if it matches the base class.
                SootClassType ancestorClass = (SootClassType) type;
                if (base.isAssignableFrom(ancestorClass)) {
                    Type[] result = resolveActualTypeArgs(ancestorClass, base);
                    if (result != null) {
                        return result;
                    }
                }
            }
            if (type instanceof ParameterizedType) {
                // ancestor is parameterized. Recurse only if the raw type matches the base class.
                ParameterizedType parameterizedType = (ParameterizedType) type;
                Type rawType = parameterizedType.getRawType();
                if (rawType instanceof SootClassType) {
                    SootClassType rawTypeClass = (SootClassType) rawType;
                    if (base.isAssignableFrom(rawTypeClass)) {

                        // loop through all type arguments and replace type variables with the actually known types
                        List<Type> resolvedTypes = new LinkedList<Type>();
                        for (Type t : parameterizedType.getActualTypeArguments()) {
                            if (t instanceof TypeVariable<?>) {
                                Type resolvedType = typeVariables.get(((TypeVariable<?>) t).getName());
                                resolvedTypes.add(resolvedType != null ? resolvedType : t);
                            } else {
                                resolvedTypes.add(t);
                            }
                        }

                        Type[] result = resolveActualTypeArgs(rawTypeClass, base, 
                                resolvedTypes.toArray(new Type[] {}));
                        if (result != null) {
                            return result;
                        }
                    }
                }
            }
        }

        // we have a result if we reached the base class.
        return offspring.equals(base) ? actualArgs : null;
    }
}
