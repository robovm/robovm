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

        out.println("%jclass = type opaque");
        out.println("%jobject = type {%jclass*,i8*}");
        out.println("declare %jclass* @nvmGetClass(i8*, i8*, %jclass*)");
        out.println("declare %jclass* @nvmAllocateClass(i8*, %jclass*, i32, i32, i32)");
        out.println("declare void @nvmAddInterface(%jclass*, i8*)");
        out.println("declare void @nvmAddMethod(%jclass*, i8*, i8*, i32, i8*)");
        out.println("declare void @nvmAddField(%jclass*, i8*, i8*, i32, i32)");
        out.println("declare void @nvmRegisterClass(%jclass*)");
        out.println("declare i8* @j_get_method_impl(%jclass*, i8*, i8*, %jclass*)");
        out.println("declare %jobject* @j_ldc_string_asciiz(i8*)");
        out.println("declare %jobject* @j_ldc_class(i8*)");
        out.println("declare %jobject* @nvmNewInstance(%jclass*)");
        out.println("declare void @nvmThrow(%jobject*)");
        out.println("declare void @nvmThrowAbstractMethodError()");
        out.println("declare void @nvmThrowNullPointerException()");
        out.println("declare void @nvmThrowArrayIndexOutOfBoundsException(i32)");
        out.println("declare void @nvmCheckcast(%jobject*, %jclass*)");
        out.println("declare i32 @nvmInstanceof(%jobject*, %jclass*)");
        out.println("declare i32 @j_catch_match(%jobject*, %jclass*)");
        out.println("declare %jobject* @j_get_throwable(i8*)");
        out.println("declare i8* @llvm.eh.exception() nounwind");
        out.println("declare i64 @llvm.eh.selector.i64(i8*, i8*, ...) nounwind");
        out.println("declare i32 @j_eh_personality(i32, i32, i64, i8*, i8*)");
        out.println("declare i32 @j_eh_match_throwable(%jobject*, %jclass*)");
        out.println("declare void @j_eh_resume_unwind(i8*)");
        out.println("declare void @j_monitorenter(%jobject*)");
        out.println("declare void @j_monitorexit(%jobject*)");
        out.println("declare %jobject* @nvmNewArray(i32, i32)");
        out.println("declare %jobject* @nvmANewArray(i8*, i32)");
        out.println("declare %jobject* @nvmMultiANewArray(i8*, i32, i32*)");
        out.println("declare i32 @j_arraylength(%jobject*)");
        out.println("declare i32 @j_iaload(%jobject* %o, i32 %index)");
        out.println("declare void @j_iastore(%jobject* %o, i32 %index, i32 %value)");
        out.println("declare i32 @j_baload(%jobject* %o, i32 %index)");
        out.println("declare void @j_bastore(%jobject* %o, i32 %index, i32 %value)");
        out.println("declare i32 @j_saload(%jobject* %o, i32 %index)");
        out.println("declare void @j_sastore(%jobject* %o, i32 %index, i32 %value)");
        out.println("declare i32 @j_caload(%jobject* %o, i32 %index)");
        out.println("declare void @j_castore(%jobject* %o, i32 %index, i32 %value)");
        out.println("declare float @j_faload(%jobject* %o, i32 %index)");
        out.println("declare void @j_fastore(%jobject* %o, i32 %index, float %value)");
        out.println("declare i64 @j_laload(%jobject* %o, i32 %index)");
        out.println("declare void @j_lastore(%jobject* %o, i32 %index, i64 %value)");
        out.println("declare double @j_daload(%jobject* %o, i32 %index)");
        out.println("declare void @j_dastore(%jobject* %o, i32 %index, double %value)");
        out.println("declare %jobject* @j_aaload(%jobject* %o, i32 %index)");
        out.println("declare void @j_aastore(%jobject* %o, i32 %index, %jobject* %value)");
        out.println("declare i8* @nvmGetClassFieldGetter(i8*, i8*, i8*, i8*, %jclass*, i8*)");
        out.println("declare i8* @nvmGetClassFieldSetter(i8*, i8*, i8*, i8*, %jclass*, i8*)");
        out.println("declare i8* @nvmGetInstanceFieldGetter(i8*, i8*, i8*, i8*, %jclass*, i8*)");
        out.println("declare i8* @nvmGetInstanceFieldSetter(i8*, i8*, i8*, i8*, %jclass*, i8*)");
        out.println("declare i8* @nvmGetInvokeStaticFunction(i8*, i8*, i8*, i8*, %jclass*, i8*)");
        out.println("declare i8* @nvmGetInvokeVirtualFunction(i8*, i8*, i8*, i8*, %jclass*, i8*)");
        out.println("declare i8* @nvmGetInvokeInterfaceFunction(i8*, i8*, i8*, i8*, %jclass*, i8*)");
        out.println("declare i8* @nvmGetInvokeSpecialFunction(i8*, i8*, i8*, i8*, %jclass*, i8*)");
        out.println("declare i8* @nvmGetNativeMethod(i8*, i8*, i8*)");
        out.println("declare i8* @nvmGetCheckcastFunction(i8*, i8*, %jclass*, i8*)");
        out.println("declare i8* @nvmGetInstanceofFunction(i8*, i8*, %jclass*, i8*)");
        out.println("declare i8* @nvmGetCurrentJNIEnv()");
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
                                out.format("define private void @F_%s(%s %%v) {\n", setter, llvmType);
                                out.format("    %%caller = load %%jclass** @clazz\n");
                                out.format("    %%functionPtr = bitcast void (%s)** @%s to i8*\n", llvmType, setter);
                                out.format("    %%tmp0 = call i8* @nvmGetClassFieldSetter(i8* %s, i8* %s, i8* %s, i8* %s, %%jclass* %%caller, i8* %%functionPtr)\n", 
                                        LlvmUtil.getStringReference(n.owner), LlvmUtil.getStringReference(LlvmUtil.mangleString(n.owner)),
                                        LlvmUtil.getStringReference(n.name), LlvmUtil.getStringReference(n.desc));
                                out.format("    %%setter = bitcast i8* %%tmp0 to void (%s)*\n", llvmType);
                                out.format("    call void %%setter(%s %%v)\n", llvmType);
                                out.format("    ret void\n");
                                out.format("}\n");
                                out.format("@%s = private global void (%s)* @F_%s\n", setter, llvmType, setter);
                                accessors.add(setter);
                            }
                        } else if (n.getOpcode() == Opcodes.GETSTATIC) {
                            String getter = "GETSTATIC_" + fieldName;
                            if (!accessors.contains(getter)) {
                                out.format("define private %s @F_%s() {\n", llvmType, getter);
                                out.format("    %%caller = load %%jclass** @clazz\n");
                                out.format("    %%functionPtr = bitcast %s ()** @%s to i8*\n", llvmType, getter);
                                out.format("    %%tmp0 = call i8* @nvmGetClassFieldGetter(i8* %s, i8* %s, i8* %s, i8* %s, %%jclass* %%caller, i8* %%functionPtr)\n", 
                                        LlvmUtil.getStringReference(n.owner), LlvmUtil.getStringReference(LlvmUtil.mangleString(n.owner)),
                                        LlvmUtil.getStringReference(n.name), LlvmUtil.getStringReference(n.desc));
                                out.format("    %%getter = bitcast i8* %%tmp0 to %s ()*\n", llvmType);
                                out.format("    %%res = call %s %%getter()\n", llvmType);
                                out.format("    ret %s %%res\n", llvmType);
                                out.format("}\n");
                                out.format("@%s = private global %s ()* @F_%s\n", getter, llvmType, getter);
                                accessors.add(getter);
                            }
                        } else if (n.getOpcode() == Opcodes.PUTFIELD) {
                            String setter = "PUTFIELD_" + fieldName;
                            if (!accessors.contains(setter)) {
                                out.format("define private void @F_%s(%%jobject* %%o, %s %%v) {\n", setter, llvmType);
                                out.format("    %%caller = load %%jclass** @clazz\n");
                                out.format("    %%functionPtr = bitcast void (%%jobject*,%s)** @%s to i8*\n", llvmType, setter);
                                out.format("    %%tmp0 = call i8* @nvmGetInstanceFieldSetter(i8* %s, i8* %s, i8* %s, i8* %s, %%jclass* %%caller, i8* %%functionPtr)\n", 
                                        LlvmUtil.getStringReference(n.owner), LlvmUtil.getStringReference(LlvmUtil.mangleString(n.owner)),
                                        LlvmUtil.getStringReference(n.name), LlvmUtil.getStringReference(n.desc));
                                out.format("    %%setter = bitcast i8* %%tmp0 to void (%%jobject*,%s)*\n", llvmType);
                                out.format("    call void %%setter(%%jobject* %%o, %s %%v)\n", llvmType);
                                out.format("    ret void\n");
                                out.format("}\n");
                                out.format("@%s = private global void (%%jobject*, %s)* @F_%s\n", setter, llvmType, setter);
                                accessors.add(setter);
                            }
                        } else if (n.getOpcode() == Opcodes.GETFIELD) {
                            String getter = "GETFIELD_" + fieldName;
                            if (!accessors.contains(getter)) {
                                out.format("define private %s @F_%s(%%jobject* %%o) {\n", llvmType, getter);
                                out.format("    %%caller = load %%jclass** @clazz\n");
                                out.format("    %%functionPtr = bitcast %s (%%jobject*)** @%s to i8*\n", llvmType, getter);
                                out.format("    %%tmp0 = call i8* @nvmGetInstanceFieldGetter(i8* %s, i8* %s, i8* %s, i8* %s, %%jclass* %%caller, i8* %%functionPtr)\n", 
                                        LlvmUtil.getStringReference(n.owner), LlvmUtil.getStringReference(LlvmUtil.mangleString(n.owner)),
                                        LlvmUtil.getStringReference(n.name), LlvmUtil.getStringReference(n.desc));
                                out.format("    %%getter = bitcast i8* %%tmp0 to %s (%%jobject*)*\n", llvmType);
                                out.format("    %%res = call %s %%getter(%%jobject* %%o)\n", llvmType);
                                out.format("    ret %s %%res\n", llvmType);
                                out.format("}\n");
                                out.format("@%s = private global %s (%%jobject*)* @F_%s\n", getter, llvmType, getter);
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
                        
                        String function = LlvmMethodCompiler.opcodeNames[n.getOpcode()] + "_" + LlvmUtil.mangleMethod(n.owner, n.name, n.desc);
                        String lookupMethod = null;
                        switch (n.getOpcode()) { 
                        case Opcodes.INVOKESTATIC: 
                            lookupMethod = "nvmGetInvokeStaticFunction";
                            break;
                        case Opcodes.INVOKEVIRTUAL:
                            lookupMethod = "nvmGetInvokeVirtualFunction";
                            break;
                        case Opcodes.INVOKEINTERFACE:
                            lookupMethod = "nvmGetInvokeInterfaceFunction";
                            break;
                        case Opcodes.INVOKESPECIAL:
                            lookupMethod = "nvmGetInvokeSpecialFunction";
                            break;
                        default:
                            throw new RuntimeException();
                        }
                        boolean ztatic = n.getOpcode() == Opcodes.INVOKESTATIC;
                        String llvmReturnType = LlvmUtil.javaTypeToLlvmType(Type.getReturnType(n.desc));
                        if (!functions.contains(function)) {
                            out.format("define private %s {\n", LlvmUtil.functionDefinition("_" + function, n.desc, ztatic));
                            out.format("    %%caller = load %%jclass** @clazz\n");
                            out.format("    %%functionPtr = bitcast %s** @%s to i8*\n", LlvmUtil.functionType(n.desc, ztatic), function);
                            out.format("    %%tmp0 = call i8* @%s(i8* %s, i8* %s, i8* %s, i8* %s, %%jclass* %%caller, i8* %%functionPtr)\n",
                                    lookupMethod, LlvmUtil.getStringReference(n.owner), LlvmUtil.getStringReference(LlvmUtil.mangleString(n.owner)),
                                    LlvmUtil.getStringReference(n.name), LlvmUtil.getStringReference(n.desc));
                            out.format("    %%function = bitcast i8* %%tmp0 to %s*\n", LlvmUtil.functionType(n.desc, ztatic));
                            if (Type.getReturnType(n.desc) == Type.VOID_TYPE) {
                                out.format("    call void %%function(%s)\n", LlvmUtil.join(LlvmUtil.descToCallArgs(n.desc, ztatic, false)));
                                out.format("    ret void\n");
                            } else {
                                out.format("    %%res = call %s %%function(%s)\n", llvmReturnType, LlvmUtil.join(LlvmUtil.descToCallArgs(n.desc, ztatic, false)));
                                out.format("    ret %s %%res\n", llvmReturnType);
                            }
                            out.format("}\n");
                            out.format("@%s = private global %s* @_%s\n", function, LlvmUtil.functionType(n.desc, ztatic), function);
                            functions.add(function);
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
                        String lookupMethod = n.getOpcode() == Opcodes.CHECKCAST ? "nvmGetCheckcastFunction" : "nvmGetInstanceofFunction";
                        String type = n.getOpcode() == Opcodes.CHECKCAST ? "void" : "i32";
                        if (!functions.contains(function)) {
                            out.format("define private %s @_%s(%%jobject* %%o) {\n", type, function);
                            out.format("    %%caller = load %%jclass** @clazz\n");
                            out.format("    %%functionPtr = bitcast %s (%%jobject*)** @%s to i8*\n", type, function);
                            out.format("    %%tmp0 = call i8* @%s(i8* %s, i8* %s, %%jclass* %%caller, i8* %%functionPtr)\n",
                                    lookupMethod, LlvmUtil.getStringReference(n.desc), LlvmUtil.getStringReference(LlvmUtil.mangleString(n.desc)));
                            out.format("    %%function = bitcast i8* %%tmp0 to %s (%%jobject*)*\n", type);
                            if (n.getOpcode() == Opcodes.CHECKCAST) {
                                out.format("    call void %%function(%%jobject* %%o)\n");
                                out.format("    ret void\n");
                            } else {
                                out.format("    %%res = call i32 %%function(%%jobject* %%o)\n");
                                out.format("    ret i32 %%res\n");
                            }
                            out.format("}\n");
                            out.format("@%s = private global %s (%%jobject*)* @_%s\n", function, type, function);
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
        
//        if (hasNativeMethods) {
//            out.format("#import \"%s.native.m\"\n", Util.mangleString(classNode.name));
//            out.println();
//        }
        
        Set<String> throwables = new HashSet<String>();
        for (MethodNode node : (List<MethodNode>) classNode.methods) {
            if (!LlvmUtil.isNative(node)) {
                for (TryCatchBlockNode n : (List<TryCatchBlockNode>) node.tryCatchBlocks) {
                    if (n.type != null && !throwables.contains(n.type)) {
                        out.format("@\"%s_%%jclass*\" = private global %%jclass* null\n", LlvmUtil.mangleString(n.type));
                        throwables.add(n.type);
                    }
                }
            }
        }
        
        for (MethodNode node : (List<MethodNode>) classNode.methods) {
            if (!LlvmUtil.isNative(node)) {
                new LlvmMethodCompiler(classNode, node).write(out);
            } else {
                String function = LlvmUtil.mangleMethod(classNode, node);
                boolean ztatic = (node.access & Opcodes.ACC_STATIC) > 0;
                String llvmReturnType = LlvmUtil.javaTypeToLlvmType(Type.getReturnType(node.desc));
                out.format("define private %s {\n", LlvmUtil.nativeFunctionDefinition("_" + function, node.desc, ztatic));
                out.format("    %%functionPtr = bitcast %s** @PTR_%s to i8*\n", LlvmUtil.nativeFunctionType(node.desc, ztatic), function);
                out.format("    %%tmp0 = call i8* @nvmGetNativeMethod(i8* %s, i8* %s, i8* %%functionPtr)\n",
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
                out.format("    %%clazz = load %%jclass** @clazz\n");
                out.format("    %%env = call i8* @nvmGetCurrentJNIEnv()\n");
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
        
//        for (int i = 0; i < classNode.interfaces.size(); i++) {
//            String interfaze = (String) classNode.interfaces.get(i);
//            out.format("@%s = private global [10 x i8] zeroinitializer\n", LlvmUtil.mangleString(interfaze));
//        }
        // @"IntTests" = linker_private constant [9 x i8] c"IntTests\00"
        out.println();
        
        out.println("@clazz = private global %jclass* null");
        
        // @"ObjectTests" = linker_private constant [12 x i8] c"ObjectTests\00"
        
        out.format( "define %%jclass* @\"jc_%s\"() {\n", LlvmUtil.mangleString(classNode.name));
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
        if (classNode.superName != null) {
            // TODO: We need to check that the super class is accessible to the new class.
            out.format("    %%superclazz = call %%jclass* @nvmGetClass(i8* %s, i8* %s, %%jclass* null)\n", 
                    LlvmUtil.getStringReference(classNode.superName), 
                    LlvmUtil.getStringReference(LlvmUtil.mangleString(classNode.superName)));
        } else {
            out.format("    %%superclazz = inttoptr i32 0 to %%jclass*\n");
        }
        out.format("    %%clazz = call %%jclass* @nvmAllocateClass(i8* %s, %%jclass* %%superclazz, i32 %d, i32 %%ClassDataSizeI, i32 %%InstanceDataSizeI)\n", 
                LlvmUtil.getStringReference(classNode.name), classNode.access);
        for (int i = 0; i < classNode.interfaces.size(); i++) {
            String interfaze = (String) classNode.interfaces.get(i);
            out.format("    call void @nvmAddInterface(%%jclass* %%clazz, i8* %s)\n", LlvmUtil.getStringReference(interfaze));
        }
        for (int i = 0; i < classNode.methods.size(); i++) {
            MethodNode node = (MethodNode) classNode.methods.get(classNode.methods.size() - i - 1);
            out.format("    %%FuncPtr%d = bitcast %s @%s to i8*\n", i, 
                    LlvmUtil.javaMethodToLlvmFunctionType(node), LlvmUtil.mangleMethod(classNode, node)); 
            out.format("    call void @nvmAddMethod(%%jclass* %%clazz, i8* %s, i8* %s, i32 %d, i8* %%FuncPtr%d)\n", 
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
                out.format("    call void @nvmAddField(%%jclass* %%clazz, i8* %s, i8* %s, i32 %d, i32 %%ClassFieldOffset%dI)\n", 
                        LlvmUtil.getStringReference(node.name), LlvmUtil.getStringReference(node.desc), 
                        node.access, i);
            } else {
                out.format("    %%InstanceFieldOffset%d = getelementptr %%InstanceFields* null, i32 0, i32 %d\n", i, instanceFieldCounter++); 
                out.format("    %%InstanceFieldOffset%dI = ptrtoint %s* %%InstanceFieldOffset%d to i32\n", i, llvmType, i); 
                out.format("    call void @nvmAddField(%%jclass* %%clazz, i8* %s, i8* %s, i32 %d, i32 %%InstanceFieldOffset%dI)\n", 
                        LlvmUtil.getStringReference(node.name), LlvmUtil.getStringReference(node.desc), 
                        node.access, i);
            }
        }
        
        int i = 0;
        for (String throwable : throwables) {
            Var tmp = new Var("throwable" + i++, "%jclass*");
            out.format("    %s = call %%jclass* @nvmGetClass(i8* %s, i8* %s, %%jclass* %%clazz)\n", 
                    tmp,
                    LlvmUtil.getStringReference(throwable), 
                    LlvmUtil.getStringReference(LlvmUtil.mangleString(throwable)));
            out.format("    store %%jclass* %s, %%jclass** @\"%s_%%jclass*\"\n", tmp, LlvmUtil.mangleString(throwable));
        }
        
        out.println("    call void @nvmRegisterClass(%jclass* %clazz)");
        out.println("    store %jclass* %clazz, %jclass** @clazz");
        out.println("    ret %jclass* %clazz");
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
