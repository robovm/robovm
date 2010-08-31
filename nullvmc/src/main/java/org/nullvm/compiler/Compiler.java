/*
 * Copyright (C) 2009 Niklas Therning <niklas(a)therning.org>
 * This file is part of JTouch.
 *
 * JTouch is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * JTouch is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with JTouch.  If not, see <http://www.gnu.org/licenses/>.
 */
package org.nullvm.compiler;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.Writer;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.commons.io.IOUtils;
import org.objectweb.asm.ClassReader;
import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;
import org.objectweb.asm.Type;
import org.objectweb.asm.commons.JSRInlinerAdapter;
import org.objectweb.asm.tree.AbstractInsnNode;
import org.objectweb.asm.tree.ClassNode;
import org.objectweb.asm.tree.FieldInsnNode;
import org.objectweb.asm.tree.FieldNode;
import org.objectweb.asm.tree.LdcInsnNode;
import org.objectweb.asm.tree.MethodInsnNode;
import org.objectweb.asm.tree.MethodNode;
import org.objectweb.asm.tree.MultiANewArrayInsnNode;
import org.objectweb.asm.tree.TryCatchBlockNode;
import org.objectweb.asm.tree.TypeInsnNode;

/**
 *
 * @version $Id$
 */
public class Compiler {
    private ClassNode classNode;
    private Set<String> strings = new HashSet<String>();
    private PrintWriter out;
    
    public void compile(File input, File output) throws IOException {
        InputStream in = null;
        OutputStream out = null;
        try {
            in = new FileInputStream(input);
            out = new FileOutputStream(output);
            compile(in, out);
        } finally {
            IOUtils.closeQuietly(in);
            IOUtils.closeQuietly(out);
        }
    }
    
    public void compile(InputStream in, OutputStream out) throws IOException {
        compile(new ClassReader(in), out);
    }
    
    public void compile(ClassReader cr, OutputStream out) throws IOException {
        ClassNode cn = new ClassNode() {
            @Override
            public MethodVisitor visitMethod(int access, String name, String desc, String signature, String[] exceptions) {
                MethodVisitor mv = super.visitMethod(access, name, desc, signature, exceptions);
                return new JSRInlinerAdapter(mv, access, name, desc, signature, exceptions);
            }
        };
        cr.accept(cn, 0);
        compile(cn, new PrintWriter(new OutputStreamWriter(out, "UTF-8")));
    }
    
    @SuppressWarnings("unchecked")
    private void compile(ClassNode cn, Writer w) {
        this.strings = new HashSet<String>();
        this.classNode = cn;
        this.out = w instanceof PrintWriter ? (PrintWriter) w : new PrintWriter(w);
        
        boolean hasNativeMethods = false;
        boolean hasCLInit = false;
        for (MethodNode node : (List<MethodNode>) classNode.methods) {
            if (LlvmUtil.isNative(node)) {
                hasNativeMethods = true;
            }
            if ((node.access & Opcodes.ACC_STATIC) != 0 && "<clinit>".equals(node.name)) {
                hasCLInit = true;
            }
        }

        out.println("%Env = type opaque");
        out.println("%Class = type opaque");
        out.println("%Object = type opaque");
        out.println("%GetPutStaticCommon = type {void ()*, i8*, i8*, i8*, i8*}");
        out.println("%GetStatic = type {void ()*, %GetPutStaticCommon*, %Class**, i8*}");
        out.println("%PutStatic = type {void ()*, %GetPutStaticCommon*, %Class**, i8*}");
        out.println("%InvokeVirtualCommon = type {void ()*, i8*, i8*, i8*, i8*, i32}");
        out.println("%InvokeVirtual = type {void ()*, %InvokeVirtualCommon*, %Class**, i32}");
        out.println("%InvokeSpecialCommon = type {void ()*, i8*, i8*, i8*, i8*}");
        out.println("%InvokeSpecial = type {void ()*, %InvokeSpecialCommon*, %Class**}");
        out.println("%InvokeStaticCommon = type {void ()*, i8*, i8*, i8*, i8*}");
        out.println("%InvokeStatic = type {void ()*, %InvokeStaticCommon*, %Class**}");
        out.println("%InvokeInterfaceCommon = type {void ()*, i8*, i8*, i8*, i8*}");
        out.println("%InvokeInterface = type {void ()*, %InvokeInterfaceCommon*, %Class**}");
        
        out.println("declare %Class* @_nvmBcAllocateClass(%Env*, i8*, i8*, i32, i32, i32)");
        out.println("declare void @_nvmBcAddInterface(%Env*, %Class*, i8*)");
        out.println("declare void @_nvmBcAddMethod(%Env*, %Class*, i8*, i8*, i32, i8*)");
        out.println("declare void @_nvmBcAddField(%Env*, %Class*, i8*, i8*, i32, i32)");
        out.println("declare void @_nvmBcRegisterClass(%Env*, %Class*)");
        out.println("declare %Class* @_nvmBcFindClass(%Env*, i8*, %Class*)");
        out.println("declare void @_nvmBcThrow(%Env*, %Object*)");
        out.println("declare void @_nvmBcThrowNullPointerException(%Env*)");
        out.println("declare void @_nvmBcThrowArrayIndexOutOfBoundsException(%Env*, i32)");
        
        out.println("declare %Object* @_nvmBcNewBooleanArray(%Env*, i32)");
        out.println("declare %Object* @_nvmBcNewByteArray(%Env*, i32)");
        out.println("declare %Object* @_nvmBcNewCharArray(%Env*, i32)");
        out.println("declare %Object* @_nvmBcNewShortArray(%Env*, i32)");
        out.println("declare %Object* @_nvmBcNewIntArray(%Env*, i32)");
        out.println("declare %Object* @_nvmBcNewLongArray(%Env*, i32)");
        out.println("declare %Object* @_nvmBcNewFloatArray(%Env*, i32)");
        out.println("declare %Object* @_nvmBcNewDoubleArray(%Env*, i32)");
        out.println("declare %Object* @_nvmBcNewObjectArray(%Env*, i32, i8*, %Class*)");
        out.println("declare %Object* @_nvmBcNewMultiArray(%Env*, i32, i32*, i8*, %Class*)");
        
        out.println("declare %Object* @_nvmBcNewStringAscii(%Env*, i8*)");
        out.println("declare %Object* @j_ldc_class(i8*)");

        out.println("declare void @_nvmBcGetStatic()");
        out.println("declare void @_nvmBcPutStatic()");
        
        out.println("declare i8* @_nvmBcGetClassFieldGetter(%Env*, i8*, i8*, i8*, %Class*, i8*)");
        out.println("declare i8* @_nvmBcGetClassFieldSetter(%Env*, i8*, i8*, i8*, %Class*, i8*)");
        out.println("declare i8* @_nvmBcGetInstanceFieldGetter(%Env*, i8*, i8*, i8*, %Class*, i8*)");
        out.println("declare i8* @_nvmBcGetInstanceFieldSetter(%Env*, i8*, i8*, i8*, %Class*, i8*)");
        
        out.println("declare i8* @_nvmBcGetCheckcastFunction(%Env*, i8*, %Class*, i8*)");
        out.println("declare i8* @_nvmBcGetInstanceofFunction(%Env*, i8*, %Class*, i8*)");
        out.println("declare i8* @_nvmBcGetAllocateObjectFunction(%Env*, i8*, %Class*, i8*)");
        out.println("declare i8* @_nvmBcGetNativeMethod(%Env*, i8*, i8*, i8*)");
        
        out.println("declare void @_nvmBcResolveGetPutStaticCommon()");
        out.println("declare void @_nvmBcResolveGetStatic()");
        out.println("declare void @_nvmBcResolvePutStatic()");
        
        out.println("declare void @_nvmBcResolveMethodForInvokeStaticCommon()");
        out.println("declare void @_nvmBcResolveMethodForInvokeStatic0()");
        out.println("declare void @_nvmBcResolveMethodForInvokeVirtualCommon()");
        out.println("declare void @_nvmBcResolveMethodForInvokeVirtual0()");
        out.println("declare void @_nvmBcResolveMethodForInvokeSpecialCommon()");
        out.println("declare void @_nvmBcResolveMethodForInvokeSpecial0()");
        out.println("declare void @_nvmBcResolveMethodForInvokeInterfaceCommon()");
        out.println("declare void @_nvmBcResolveMethodForInvokeInterface0()");
        
        out.println("declare i32 @j_catch_match(%Object*, %Class*)");
        out.println("declare %Object* @j_get_throwable(i8*)");
        out.println("declare i8* @llvm.eh.exception() nounwind");
        out.println("declare i64 @llvm.eh.selector.i64(i8*, i8*, ...) nounwind");
        out.println("declare i32 @j_eh_personality(i32, i32, i64, i8*, i8*)");
        out.println("declare i32 @j_eh_match_throwable(%Object*, %Class*)");
        out.println("declare void @j_eh_resume_unwind(i8*)");
        out.println("declare void @j_monitorenter(%Object*)");
        out.println("declare void @j_monitorexit(%Object*)");
        out.println("declare i32 @j_arraylength(%Object*)");
        out.println("declare i32 @j_iaload(%Object* %o, i32 %index)");
        out.println("declare void @j_iastore(%Object* %o, i32 %index, i32 %value)");
        out.println("declare i32 @j_baload(%Object* %o, i32 %index)");
        out.println("declare void @j_bastore(%Object* %o, i32 %index, i32 %value)");
        out.println("declare i32 @j_saload(%Object* %o, i32 %index)");
        out.println("declare void @j_sastore(%Object* %o, i32 %index, i32 %value)");
        out.println("declare i32 @j_caload(%Object* %o, i32 %index)");
        out.println("declare void @j_castore(%Object* %o, i32 %index, i32 %value)");
        out.println("declare float @j_faload(%Object* %o, i32 %index)");
        out.println("declare void @j_fastore(%Object* %o, i32 %index, float %value)");
        out.println("declare i64 @j_laload(%Object* %o, i32 %index)");
        out.println("declare void @j_lastore(%Object* %o, i32 %index, i64 %value)");
        out.println("declare double @j_daload(%Object* %o, i32 %index)");
        out.println("declare void @j_dastore(%Object* %o, i32 %index, double %value)");
        out.println("declare %Object* @j_aaload(%Object* %o, i32 %index)");
        out.println("declare void @j_aastore(%Object* %o, i32 %index, %Object* %value)");
        out.println();
        
        List<FieldNode> classFields = new ArrayList<FieldNode>();
        List<FieldNode> instanceFields = new ArrayList<FieldNode>();

        for (FieldNode fieldNode : (List<FieldNode>) classNode.fields) {
            if ((fieldNode.access & Opcodes.ACC_STATIC) != 0) {
                classFields.add(fieldNode);
            } else {
                instanceFields.add(fieldNode);
            }
        }
        
        if (!classFields.isEmpty()) {
            List<String> names = new ArrayList<String>();
            List<String> types = new ArrayList<String>();
            for (FieldNode fieldNode : classFields) {
                names.add(Type.getType(fieldNode.desc).getClassName() + " " + fieldNode.name);
                types.add(LlvmUtil.javaTypeToLlvmType(Type.getType(fieldNode.desc)));
            }
            out.format("; {%s}\n", LlvmUtil.join(names));
            out.format("%%ClassFields = type {%s}\n", LlvmUtil.join(types));
        }
        
        if (!instanceFields.isEmpty()) {
            List<String> names = new ArrayList<String>();
            List<String> types = new ArrayList<String>();
            for (FieldNode fieldNode : instanceFields) {
                names.add(Type.getType(fieldNode.desc).getClassName() + " " + fieldNode.name);
                types.add(LlvmUtil.javaTypeToLlvmType(Type.getType(fieldNode.desc)));
            }
            out.format("; {%s}\n", LlvmUtil.join(names));
            out.format("%%InstanceFields = type {%s}\n", LlvmUtil.join(types));
        }
        
        out.println();
        
        out.println("; Strings");
        writeStringDefinition(out, classNode.name);
        if (classNode.superName != null) {
            writeStringDefinition(out, classNode.superName);
            writeStringDefinition(out, LlvmUtil.mangleString(classNode.superName));
        }
        for (int i = 0; i < classNode.interfaces.size(); i++) {
            writeStringDefinition(out, (String) classNode.interfaces.get(i));
            writeStringDefinition(out, LlvmUtil.mangleString((String) classNode.interfaces.get(i)));
        }
        for (FieldNode fieldNode : classFields) {
            writeStringDefinition(out, fieldNode.name);
            writeStringDefinition(out, fieldNode.desc);
        }
        for (FieldNode fieldNode : instanceFields) {
            writeStringDefinition(out, fieldNode.name);
            writeStringDefinition(out, fieldNode.desc);
        }
        for (MethodNode node : (List<MethodNode>) classNode.methods) {
            writeStringDefinition(out, node.name);
            writeStringDefinition(out, node.desc);
            if (!LlvmUtil.isNative(node)) {
                for (AbstractInsnNode insnNode : node.instructions.toArray()) {
                    if (insnNode instanceof MethodInsnNode) {
                        MethodInsnNode n = (MethodInsnNode) insnNode;
                        writeStringDefinition(out, n.owner);
                        writeStringDefinition(out, LlvmUtil.mangleString(n.owner));
                        writeStringDefinition(out, n.name);
                        writeStringDefinition(out, n.desc);
                    } else if (insnNode instanceof FieldInsnNode) {
                        FieldInsnNode n = (FieldInsnNode) insnNode;
                        writeStringDefinition(out, n.owner);
                        writeStringDefinition(out, LlvmUtil.mangleString(n.owner));
                        writeStringDefinition(out, n.name);
                        writeStringDefinition(out, n.desc);
                    } else if (insnNode instanceof TypeInsnNode) {
                        TypeInsnNode n = (TypeInsnNode) insnNode;
                        writeStringDefinition(out, n.desc);
                        writeStringDefinition(out, LlvmUtil.mangleString(n.desc));
                        if (n.getOpcode() == Opcodes.ANEWARRAY) {
                            writeStringDefinition(out, "[" + n.desc);
                        }
                    } else if (insnNode instanceof LdcInsnNode) {
                        LdcInsnNode n = (LdcInsnNode) insnNode;
                        if (n.cst instanceof String) {
                            writeStringDefinition(out, (String) n.cst);
                        }
                        if (n.cst instanceof Type) {
                            writeStringDefinition(out, ((Type) n.cst).getDescriptor());
                        }
                    } else if (insnNode instanceof MultiANewArrayInsnNode) {
                        MultiANewArrayInsnNode n = (MultiANewArrayInsnNode) insnNode;
                        writeStringDefinition(out, n.desc);
                    }
                }
                
                for (TryCatchBlockNode n : (List<TryCatchBlockNode>) node.tryCatchBlocks) {
                    if (n.type != null) {
                        writeStringDefinition(out, n.type);
                        writeStringDefinition(out, LlvmUtil.mangleString(n.type));
                    }
                }
            } else {
                writeStringDefinition(out, LlvmUtil.mangleNativeMethodShort(classNode, node));
                writeStringDefinition(out, LlvmUtil.mangleNativeMethodLong(classNode, node));
            }
        }
        out.println();
        
        out.println("; Field accessors");
        Set<String> accessors = new HashSet<String>();
        for (MethodNode node : (List<MethodNode>) classNode.methods) {
            if (!LlvmUtil.isNative(node)) {
                for (AbstractInsnNode insnNode : node.instructions.toArray()) {
                    if (insnNode instanceof FieldInsnNode) {
                        FieldInsnNode n = (FieldInsnNode) insnNode;
                        String fieldName = LlvmUtil.mangleString(n.owner) + "_" + LlvmUtil.mangleString(n.name) + "__" + LlvmUtil.mangleString(n.desc);
                        String llvmType = LlvmUtil.javaTypeToLlvmType(Type.getType(n.desc));
                        if (n.getOpcode() == Opcodes.PUTSTATIC) {
                            String setter = "PUTSTATIC_" + fieldName;
                            if (!accessors.contains(setter)) {
                                out.format("define private void @F_%s(%%Env* %%env, %s %%v) {\n", setter, llvmType);
                                out.format("    %%caller = load %%Class** @clazz\n");
                                out.format("    %%functionPtr = bitcast void (%%Env*, %s)** @%s to i8*\n", llvmType, setter);
                                out.format("    %%tmp0 = call i8* @_nvmBcGetClassFieldSetter(%%Env* %%env, i8* %s, i8* %s, i8* %s, %%Class* %%caller, i8* %%functionPtr)\n", 
                                        LlvmUtil.getStringReference(n.owner),
                                        LlvmUtil.getStringReference(n.name), LlvmUtil.getStringReference(n.desc));
                                out.format("    %%setter = bitcast i8* %%tmp0 to void (%%Env*, %s)*\n", llvmType);
                                out.format("    call void %%setter(%%Env* %%env, %s %%v)\n", llvmType);
                                out.format("    ret void\n");
                                out.format("}\n");
                                out.format("@%s = private global void (%%Env*, %s)* @F_%s\n", setter, llvmType, setter);
                                accessors.add(setter);
                            }
                        } else if (n.getOpcode() == Opcodes.GETSTATIC) {
                            String getter = "GETSTATIC_" + fieldName;
                            if (!accessors.contains(getter)) {
                                out.format("define private %s @F_%s(%%Env* %%env) {\n", llvmType, getter);
                                out.format("    %%caller = load %%Class** @clazz\n");
                                out.format("    %%functionPtr = bitcast %s (%%Env*)** @%s to i8*\n", llvmType, getter);
                                out.format("    %%tmp0 = call i8* @_nvmBcGetClassFieldGetter(%%Env* %%env, i8* %s, i8* %s, i8* %s, %%Class* %%caller, i8* %%functionPtr)\n", 
                                        LlvmUtil.getStringReference(n.owner),
                                        LlvmUtil.getStringReference(n.name), LlvmUtil.getStringReference(n.desc));
                                out.format("    %%getter = bitcast i8* %%tmp0 to %s (%%Env*)*\n", llvmType);
                                out.format("    %%res = call %s %%getter(%%Env* %%env)\n", llvmType);
                                out.format("    ret %s %%res\n", llvmType);
                                out.format("}\n");
                                out.format("@%s = private global %s (%%Env*)* @F_%s\n", getter, llvmType, getter);
                                accessors.add(getter);
                            }
                        } else if (n.getOpcode() == Opcodes.PUTFIELD) {
                            String setter = "PUTFIELD_" + fieldName;
                            if (!accessors.contains(setter)) {
                                out.format("define private void @F_%s(%%Env* %%env, %%Object* %%o, %s %%v) {\n", setter, llvmType);
                                out.format("    %%caller = load %%Class** @clazz\n");
                                out.format("    %%functionPtr = bitcast void (%%Env*, %%Object*, %s)** @%s to i8*\n", llvmType, setter);
                                out.format("    %%tmp0 = call i8* @_nvmBcGetInstanceFieldSetter(%%Env* %%env, i8* %s, i8* %s, i8* %s, %%Class* %%caller, i8* %%functionPtr)\n", 
                                        LlvmUtil.getStringReference(n.owner), 
                                        LlvmUtil.getStringReference(n.name), LlvmUtil.getStringReference(n.desc));
                                out.format("    %%setter = bitcast i8* %%tmp0 to void (%%Env*, %%Object*, %s)*\n", llvmType);
                                out.format("    call void %%setter(%%Env* %%env, %%Object* %%o, %s %%v)\n", llvmType);
                                out.format("    ret void\n");
                                out.format("}\n");
                                out.format("@%s = private global void (%%Env*, %%Object*, %s)* @F_%s\n", setter, llvmType, setter);
                                accessors.add(setter);
                            }
                        } else if (n.getOpcode() == Opcodes.GETFIELD) {
                            String getter = "GETFIELD_" + fieldName;
                            if (!accessors.contains(getter)) {
                                out.format("define private %s @F_%s(%%Env* %%env, %%Object* %%o) {\n", llvmType, getter);
                                out.format("    %%caller = load %%Class** @clazz\n");
                                out.format("    %%functionPtr = bitcast %s (%%Env*, %%Object*)** @%s to i8*\n", llvmType, getter);
                                out.format("    %%tmp0 = call i8* @_nvmBcGetInstanceFieldGetter(%%Env* %%env, i8* %s, i8* %s, i8* %s, %%Class* %%caller, i8* %%functionPtr)\n", 
                                        LlvmUtil.getStringReference(n.owner),
                                        LlvmUtil.getStringReference(n.name), LlvmUtil.getStringReference(n.desc));
                                out.format("    %%getter = bitcast i8* %%tmp0 to %s (%%Env*, %%Object*)*\n", llvmType);
                                out.format("    %%res = call %s %%getter(%%Env* %%env, %%Object* %%o)\n", llvmType);
                                out.format("    ret %s %%res\n", llvmType);
                                out.format("}\n");
                                out.format("@%s = private global %s (%%Env*, %%Object*)* @F_%s\n", getter, llvmType, getter);
                                accessors.add(getter);
                            }
                        }
                    }
                }
            }
        }
        out.println();
        
        out.println("; Method lookup function pointers");
        Set<String> functions = new HashSet<String>();
        for (MethodNode node : (List<MethodNode>) classNode.methods) {
            if (!LlvmUtil.isNative(node)) {
                for (AbstractInsnNode insnNode : node.instructions.toArray()) {
                    if (insnNode instanceof MethodInsnNode) {
                        MethodInsnNode n = (MethodInsnNode) insnNode;
                        if (n.owner.equals(classNode.name)) {
                            MethodNode mnode = LlvmUtil.findMethodNode(classNode, n.name, n.desc);
                            if (mnode != null && ((classNode.access & Opcodes.ACC_STATIC) > 0 || "<init>".equals(mnode.name) || (mnode.access & Opcodes.ACC_PRIVATE) > 0 || (mnode.access & Opcodes.ACC_FINAL) > 0 || (n.getOpcode() == Opcodes.INVOKESTATIC && (mnode.access & Opcodes.ACC_STATIC) > 0))) {
                                // Constructors as well as private, final and static methods of the current class will be called directly
                                continue;
                            }
                        }
                        
                        String prefix = (new String[] {"InvokeVirtual", "InvokeSpecial", "InvokeStatic", "InvokeInterface"})[n.getOpcode() - Opcodes.INVOKEVIRTUAL];
                        String mangledMethod = LlvmUtil.mangleMethod(n.owner, n.name, n.desc);
                        String varName = prefix + "_" + mangledMethod;
                        if (!functions.contains(varName)) {
                        
                            switch (n.getOpcode()) { 
                            case Opcodes.INVOKESTATIC:
                                out.format("@%s_Common = linker_private global %%InvokeStaticCommon {void ()* @_nvmBcResolveMethodForInvokeStaticCommon, i8* %s, i8* %s, i8* %s, i8* null}\n", 
                                        varName, LlvmUtil.getStringReference(n.owner), 
                                        LlvmUtil.getStringReference(n.name), LlvmUtil.getStringReference(n.desc));
                                out.format("@%s = private global %%InvokeStatic {void ()* @_nvmBcResolveMethodForInvokeStatic0, %%InvokeStaticCommon* @%s_Common, %%Class** @clazz}\n", 
                                        varName, varName);
                                break;
                            case Opcodes.INVOKEVIRTUAL:
                                out.format("@%s_Common = linker_private global %%InvokeVirtualCommon {void ()* @_nvmBcResolveMethodForInvokeVirtualCommon, i8* %s, i8* %s, i8* %s, i8* null, i32 0}\n", 
                                        varName, LlvmUtil.getStringReference(n.owner), 
                                        LlvmUtil.getStringReference(n.name), LlvmUtil.getStringReference(n.desc));
                                out.format("@%s = private global %%InvokeVirtual {void ()* @_nvmBcResolveMethodForInvokeVirtual0, %%InvokeVirtualCommon* @%s_Common, %%Class** @clazz, i32 0}\n", 
                                        varName, varName);
                                break;
                            case Opcodes.INVOKEINTERFACE:
                                out.format("@%s_Common = linker_private global %%InvokeInterfaceCommon {void ()* @_nvmBcResolveMethodForInvokeInterfaceCommon, i8* %s, i8* %s, i8* %s, i8* null}\n", 
                                        varName, LlvmUtil.getStringReference(n.owner), 
                                        LlvmUtil.getStringReference(n.name), LlvmUtil.getStringReference(n.desc));
                                out.format("@%s = private global %%InvokeInterface {void ()* @_nvmBcResolveMethodForInvokeInterface0, %%InvokeInterfaceCommon* @%s_Common, %%Class** @clazz}\n", 
                                        varName, varName);
                                break;
                            case Opcodes.INVOKESPECIAL:
                                out.format("@%s_Common = linker_private global %%InvokeSpecialCommon {void ()* @_nvmBcResolveMethodForInvokeSpecialCommon, i8* %s, i8* %s, i8* %s, i8* null}\n", 
                                        varName, LlvmUtil.getStringReference(n.owner), 
                                        LlvmUtil.getStringReference(n.name), LlvmUtil.getStringReference(n.desc));
                                out.format("@%s = private global %%InvokeSpecial {void ()* @_nvmBcResolveMethodForInvokeSpecial0, %%InvokeStaticCommon* @%s_Common, %%Class** @clazz}\n", 
                                        varName, varName);
                                break;
                            default:
                                throw new RuntimeException();
                            }
                            
                            functions.add(varName);
                        }
                    }
                }
            }
        }
        
        out.println("; CHECKCAST / INSTANCEOF functions");
        for (MethodNode node : (List<MethodNode>) classNode.methods) {
            if (!LlvmUtil.isNative(node)) {
                for (AbstractInsnNode insnNode : node.instructions.toArray()) {
                    if (insnNode instanceof TypeInsnNode) {
                        TypeInsnNode n = (TypeInsnNode) insnNode;
                        if (n.getOpcode() != Opcodes.CHECKCAST && n.getOpcode() != Opcodes.INSTANCEOF) {
                            continue;
                        }
                        
                        String function = LlvmMethodCompiler.opcodeNames[n.getOpcode()] + "_" + LlvmUtil.mangleString(n.desc);
                        String lookupMethod = n.getOpcode() == Opcodes.CHECKCAST ? "_nvmBcGetCheckcastFunction" : "_nvmBcGetInstanceofFunction";
                        String type = n.getOpcode() == Opcodes.CHECKCAST ? "void" : "i32";
                        if (!functions.contains(function)) {
                            out.format("define private %s @_%s(%%Env* %%env, %%Object* %%o) {\n", type, function);
                            out.format("    %%caller = load %%Class** @clazz\n");
                            out.format("    %%functionPtr = bitcast %s (%%Env*, %%Object*)** @%s to i8*\n", type, function);
                            out.format("    %%tmp0 = call i8* @%s(%%Env* %%env, i8* %s, %%Class* %%caller, i8* %%functionPtr)\n",
                                    lookupMethod, LlvmUtil.getStringReference(n.desc));
                            out.format("    %%function = bitcast i8* %%tmp0 to %s (%%Env*, %%Object*)*\n", type);
                            if (n.getOpcode() == Opcodes.CHECKCAST) {
                                out.format("    call void %%function(%%Env* %%env, %%Object* %%o)\n");
                                out.format("    ret void\n");
                            } else {
                                out.format("    %%res = call i32 %%function(%%Env* %%env, %%Object* %%o)\n");
                                out.format("    ret i32 %%res\n");
                            }
                            out.format("}\n");
                            out.format("@%s = private global %s (%%Env*, %%Object*)* @_%s\n", function, type, function);
                            functions.add(function);
                        }
                    }
                }
            }
        }
        
        out.println("; NEW functions");
        for (MethodNode node : (List<MethodNode>) classNode.methods) {
            if (!LlvmUtil.isNative(node)) {
                for (AbstractInsnNode insnNode : node.instructions.toArray()) {
                    if (insnNode instanceof TypeInsnNode) {
                        TypeInsnNode n = (TypeInsnNode) insnNode;
                        if (n.getOpcode() != Opcodes.NEW) {
                            continue;
                        }
                        
                        String function = "NEW_" + LlvmUtil.mangleString(n.desc);
                        String lookupMethod = "_nvmBcGetAllocateObjectFunction";
                        String type = "%Object*";
                        if (!functions.contains(function)) {
                            out.format("define private %s @_%s(%%Env* %%env) {\n", type, function);
                            out.format("    %%caller = load %%Class** @clazz\n");
                            out.format("    %%functionPtr = bitcast %s (%%Env*)** @%s to i8*\n", type, function);
                            out.format("    %%tmp0 = call i8* @%s(%%Env* %%env, i8* %s, %%Class* %%caller, i8* %%functionPtr)\n",
                                    lookupMethod, LlvmUtil.getStringReference(n.desc));
                            out.format("    %%function = bitcast i8* %%tmp0 to %s (%%Env*)*\n", type);
                            out.format("    %%res = call %%Object* %%function(%%Env* %%env)\n");
                            out.format("    ret %%Object* %%res\n");
                            out.format("}\n");
                            out.format("@%s = private global %s (%%Env*)* @_%s\n", function, type, function);
                            functions.add(function);
                        }
                    }
                }
            }
        }
        
        out.println("; Function declarations");
        
        for (MethodNode node : (List<MethodNode>) classNode.methods) {
            out.println(LlvmUtil.javaMethodToLlvmFunctionDeclaration(classNode, node));
        }
        out.println();
        
        Set<String> throwables = new HashSet<String>();
        for (MethodNode node : (List<MethodNode>) classNode.methods) {
            if (!LlvmUtil.isNative(node)) {
                for (TryCatchBlockNode n : (List<TryCatchBlockNode>) node.tryCatchBlocks) {
                    if (n.type != null && !throwables.contains(n.type)) {
                        out.format("@\"%s_%%Class*\" = private global %%Class* null\n", LlvmUtil.mangleString(n.type));
                        throwables.add(n.type);
                    }
                }
            }
        }
        
        for (MethodNode node : (List<MethodNode>) classNode.methods) {
            if (!LlvmUtil.isNative(node)) {
                if ((node.access & Opcodes.ACC_ABSTRACT) == 0) {
                    new LlvmMethodCompiler(classNode, node).write(out);
                }
            } else {
                Var env = new Var("env", "%Env*");
                String function = LlvmUtil.mangleMethod(classNode, node);
                boolean ztatic = (node.access & Opcodes.ACC_STATIC) > 0;
                String llvmReturnType = LlvmUtil.javaTypeToLlvmType(Type.getReturnType(node.desc));
                out.format("define private %s {\n", LlvmUtil.nativeFunctionDefinition("_" + function, node.desc, ztatic));
                out.format("    %%functionPtr = bitcast %s** @PTR_%s to i8*\n", LlvmUtil.nativeFunctionType(node.desc, ztatic), function);
                out.format("    %%tmp0 = call i8* @_nvmBcGetNativeMethod(%%Env* %s, i8* %s, i8* %s, i8* %%functionPtr)\n",
                        env,
                        LlvmUtil.getStringReference(LlvmUtil.mangleNativeMethodShort(classNode, node)), 
                        LlvmUtil.getStringReference(LlvmUtil.mangleNativeMethodLong(classNode, node)));
                out.format("    %%function = bitcast i8* %%tmp0 to %s*\n", LlvmUtil.nativeFunctionType(node.desc, ztatic));
                List<String> args = LlvmUtil.nativeDescToCallArgs(node.desc, ztatic, false);
                if (Type.getReturnType(node.desc) == Type.VOID_TYPE) {
                    out.format("    call void %%function(%s)\n", LlvmUtil.join(args));
                    out.format("    ret void\n");
                } else {
                    out.format("    %%res = call %s %%function(%s)\n", llvmReturnType, LlvmUtil.join(args));
                    out.format("    ret %s %%res\n", llvmReturnType);
                }
                out.format("}\n");
                out.format("@PTR_%s = private global %s* @_%s\n", function, LlvmUtil.nativeFunctionType(node.desc, ztatic), function);
                out.format("define %s {\n", LlvmUtil.functionDefinition(LlvmUtil.mangleMethod(classNode, node), node.desc, ztatic));
                out.format("    %%clazz = load %%Class** @clazz\n");
                out.format("    %%function = load %s** @PTR_%s\n", LlvmUtil.nativeFunctionType(node.desc, ztatic), function);
                if (Type.getReturnType(node.desc) == Type.VOID_TYPE) {
                    out.format("    call void %%function(%s)\n", LlvmUtil.join(args));
                    out.format("    ret void\n");
                } else {
                    out.format("    %%res = call %s %%function(%s)\n", llvmReturnType, LlvmUtil.join(args));
                    out.format("    ret %s %%res\n", llvmReturnType);
                }
                out.format("}\n");
            }
        }
        out.println();
        
        out.println("@clazz = private global %Class* null");
        
        out.format( "define %%Class* @\"NullVM_%s\"(%%Env* %%env) {\n", LlvmUtil.mangleString(classNode.name));
        if (!classFields.isEmpty()) {
            out.format("    %%ClassDataSize = getelementptr %%ClassFields* null, i32 1\n"); 
            out.format("    %%ClassDataSizeI = ptrtoint %%ClassFields* %%ClassDataSize to i32\n"); 
        } else {
            out.format("    %%ClassDataSizeI = bitcast i32 0 to i32\n"); 
        }
        if (!instanceFields.isEmpty()) {
            out.format("    %%InstanceDataSize = getelementptr %%InstanceFields* null, i32 1\n"); 
            out.format("    %%InstanceDataSizeI = ptrtoint %%InstanceFields* %%InstanceDataSize to i32\n"); 
        } else {
            out.format("    %%InstanceDataSizeI = bitcast i32 0 to i32\n"); 
        }
//        if (classNode.superName != null) {
//            // TODO: We need to check that the super class is accessible to the new class.
//            out.format("    %%superclazz = call %%Class* @_nvmBcFindClass(%%Env* %%env, i8* %s)\n", 
//                    LlvmUtil.getStringReference(classNode.superName));
//        } else {
//            out.format("    %%superclazz = inttoptr i32 0 to %%Class*\n");
//        }
//        if (!classNode.interfaces.isEmpty()) {
//            out.format("    %%interfacesArray = alloca [%d x %%Class*]\n", classNode.interfaces.size() + 1);
//            for (int i = 0; i < classNode.interfaces.size(); i++) {
//                String interfaze = (String) classNode.interfaces.get(i);
//                out.format("    %%interface%d = call %%Class* @_nvmBcFindClass(%%Env* %%env, i8* %s)\n", i, 
//                        LlvmUtil.getStringReference(interfaze));
//                out.format("    %%interfacePtr%d = getelementptr [%d x %%Class*]* %%interfacesArray, i32 0, i32 %d\n", i, classNode.interfaces.size() + 1, i);
//                out.format("    store %%Class* %%interface%d, %%Class** %%interfacePtr%d\n", i, i);
//            }
//            out.format("    %%interfacePtrLast = getelementptr [%d x %%Class*]* %%interfacesArray, i32 0, i32 %d\n", classNode.interfaces.size() + 1, classNode.interfaces.size());
//            out.format("    store %%Class* null, %%Class** %%interfacePtrLast\n");
//            out.format("    %%interfaces = bitcast [%d x %%Class*]* %%interfacesArray to %%Class**\n", classNode.interfaces.size() + 1);
//        } else {
//            out.format("    %%interfaces = inttoptr i32 0 to %%Class**\n");
//        }
        out.format("    %%clazz = call %%Class* @_nvmBcAllocateClass(%%Env* %%env, i8* %s, i8* %s, i32 %d, i32 %%ClassDataSizeI, i32 %%InstanceDataSizeI)\n", 
                LlvmUtil.getStringReference(classNode.name), classNode.superName != null ? LlvmUtil.getStringReference(classNode.superName) : "null", classNode.access);
        
        for (int i = 0; i < classNode.interfaces.size(); i++) {
            String interfaze = (String) classNode.interfaces.get(i);
            out.format("    call void @_nvmBcAddInterface(%%Env* %%env, %%Class* %%clazz, i8* %s)\n", LlvmUtil.getStringReference(interfaze));
        }
        
        for (int i = 0; i < classNode.methods.size(); i++) {
            MethodNode node = (MethodNode) classNode.methods.get(classNode.methods.size() - i - 1);
            if ((node.access & Opcodes.ACC_ABSTRACT) != 0) {
                out.format("    %%FuncPtr%d = inttoptr i32 0 to i8*\n", i);
            } else {
                out.format("    %%FuncPtr%d = bitcast %s @%s to i8*\n", i, 
                        LlvmUtil.javaMethodToLlvmFunctionType(node), LlvmUtil.mangleMethod(classNode, node)); 
            }
            out.format("    call void @_nvmBcAddMethod(%%Env* %%env, %%Class* %%clazz, i8* %s, i8* %s, i32 %d, i8* %%FuncPtr%d)\n", 
                    LlvmUtil.getStringReference(node.name), LlvmUtil.getStringReference(node.desc), 
                    node.access, i);
        }

        int classFieldCounter = 0;
        int instanceFieldCounter = 0;
        for (int i = 0; i < classNode.fields.size(); i++) {
            FieldNode node = (FieldNode) classNode.fields.get(i);
            Type t = Type.getType(node.desc);
            String llvmType = LlvmUtil.javaTypeToLlvmType(t);
            if ((node.access & Opcodes.ACC_STATIC) != 0) {
                out.format("    %%ClassFieldOffset%d = getelementptr %%ClassFields* null, i32 0, i32 %d\n", i, classFieldCounter++); 
                out.format("    %%ClassFieldOffset%dI = ptrtoint %s* %%ClassFieldOffset%d to i32\n", i, llvmType, i); 
                out.format("    call void @_nvmBcAddField(%%Env* %%env, %%Class* %%clazz, i8* %s, i8* %s, i32 %d, i32 %%ClassFieldOffset%dI)\n", 
                        LlvmUtil.getStringReference(node.name), LlvmUtil.getStringReference(node.desc), 
                        node.access, i);
            } else {
                out.format("    %%InstanceFieldOffset%d = getelementptr %%InstanceFields* null, i32 0, i32 %d\n", i, instanceFieldCounter++); 
                out.format("    %%InstanceFieldOffset%dI = ptrtoint %s* %%InstanceFieldOffset%d to i32\n", i, llvmType, i); 
                out.format("    call void @_nvmBcAddField(%%Env* %%env, %%Class* %%clazz, i8* %s, i8* %s, i32 %d, i32 %%InstanceFieldOffset%dI)\n", 
                        LlvmUtil.getStringReference(node.name), LlvmUtil.getStringReference(node.desc), 
                        node.access, i);
            }
        }
        
        int i = 0;
        for (String throwable : throwables) {
            Var tmp = new Var("throwable" + i++, "%Class*");
            out.format("    %s = call %%Class* @_nvmBcFindClass(%%Env* %%env, i8* %s, %%Class* %%clazz)\n", tmp, LlvmUtil.getStringReference(throwable));
            out.format("    store %%Class* %s, %%Class** @\"%s_%%Class*\"\n", tmp, LlvmUtil.mangleString(throwable));
        }
        
        out.println("    call void @_nvmBcRegisterClass(%Env* %env, %Class* %clazz)");
        out.println("    store %Class* %clazz, %Class** @clazz");
        out.println("    ret %Class* %clazz");
        out.println("}\n");
        
        out.flush();
    }

    private void writeStringDefinition(PrintWriter out, String s) {
        if (!strings.contains(s)) {
            out.format("%s = linker_private constant %s %s\n", LlvmUtil.getStringVar(s), 
                    LlvmUtil.getStringType(s), LlvmUtil.getStringValue(s));
            strings.add(s);
        }
    }
}
