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

import java.io.PrintWriter;
import java.io.Writer;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;
import java.util.Set;
import java.util.Stack;
import java.util.TreeSet;

import org.apache.commons.lang.ArrayUtils;
import org.objectweb.asm.Label;
import org.objectweb.asm.Opcodes;
import org.objectweb.asm.Type;
import org.objectweb.asm.commons.EmptyVisitor;
import org.objectweb.asm.tree.AbstractInsnNode;
import org.objectweb.asm.tree.ClassNode;
import org.objectweb.asm.tree.FieldInsnNode;
import org.objectweb.asm.tree.InsnList;
import org.objectweb.asm.tree.JumpInsnNode;
import org.objectweb.asm.tree.LabelNode;
import org.objectweb.asm.tree.LookupSwitchInsnNode;
import org.objectweb.asm.tree.MethodNode;
import org.objectweb.asm.tree.MultiANewArrayInsnNode;
import org.objectweb.asm.tree.TableSwitchInsnNode;
import org.objectweb.asm.tree.TryCatchBlockNode;
import org.objectweb.asm.tree.analysis.Analyzer;
import org.objectweb.asm.tree.analysis.AnalyzerException;
import org.objectweb.asm.tree.analysis.BasicInterpreter;
import org.objectweb.asm.tree.analysis.BasicValue;
import org.objectweb.asm.tree.analysis.Frame;

import com.sun.org.apache.bcel.internal.generic.GETSTATIC;

/**
 *
 * @version $Id$
 */
public class LlvmMethodCompiler {
    private ClassNode classNode;
    private MethodNode methodNode;
    
    private static int jsrLabels = 0;

    static String[] opcodeNames = {
        "NOP", "ACONST_NULL", "ICONST_M1", "ICONST_0", "ICONST_1", "ICONST_2",
        "ICONST_3", "ICONST_4", "ICONST_5", "LCONST_0", "LCONST_1", "FCONST_0",
        "FCONST_1", "FCONST_2", "DCONST_0", "DCONST_1", "BIPUSH", "SIPUSH",
        "LDC", "LDC_W", "LDC2_W", "ILOAD", "LLOAD", "FLOAD", "DLOAD", "ALOAD",
        "ILOAD_0", "ILOAD_1", "ILOAD_2", "ILOAD_3", "LLOAD_0", "LLOAD_1", "LLOAD_2",
        "LLOAD_3", "FLOAD_0", "FLOAD_1", "FLOAD_2", "FLOAD_3", "DLOAD_0", "DLOAD_1",
        "DLOAD_2", "DLOAD_3", "ALOAD_0", "ALOAD_1", "ALOAD_2", "ALOAD_3", "IALOAD",
        "LALOAD", "FALOAD", "DALOAD", "AALOAD", "BALOAD", "CALOAD", "SALOAD",
        "ISTORE", "LSTORE", "FSTORE", "DSTORE", "ASTORE", "ISTORE_0", "ISTORE_1",
        "ISTORE_2", "ISTORE_3", "LSTORE_0", "LSTORE_1", "LSTORE_2", "LSTORE_3",
        "FSTORE_0", "FSTORE_1", "FSTORE_2", "FSTORE_3", "DSTORE_0", "DSTORE_1",
        "DSTORE_2", "DSTORE_3", "ASTORE_0", "ASTORE_1", "ASTORE_2", "ASTORE_3",
        "IASTORE", "LASTORE", "FASTORE", "DASTORE", "AASTORE", "BASTORE", "CASTORE",
        "SASTORE", "POP", "POP2", "DUP", "DUP_X1", "DUP_X2", "DUP2", "DUP2_X1",
        "DUP2_X2", "SWAP", "IADD", "LADD", "FADD", "DADD", "ISUB", "LSUB", "FSUB",
        "DSUB", "IMUL", "LMUL", "FMUL", "DMUL", "IDIV", "LDIV", "FDIV", "DDIV",
        "IREM", "LREM", "FREM", "DREM", "INEG", "LNEG", "FNEG", "DNEG", "ISHL",
        "LSHL", "ISHR", "LSHR", "IUSHR", "LUSHR", "IAND", "LAND", "IOR", "LOR",
        "IXOR", "LXOR", "IINC", "I2L", "I2F", "I2D", "L2I", "L2F", "L2D", "F2I",
        "F2L", "F2D", "D2I", "D2L", "D2F", "I2B", "I2C", "I2S", "LCMP", "FCMPL",
        "FCMPG", "DCMPL", "DCMPG", "IFEQ", "IFNE", "IFLT", "IFGE", "IFGT", "IFLE",
        "IF_ICMPEQ", "IF_ICMPNE", "IF_ICMPLT", "IF_ICMPGE", "IF_ICMPGT",
        "IF_ICMPLE", "IF_ACMPEQ", "IF_ACMPNE", "GOTO", "JSR", "RET", "TABLESWITCH",
        "LOOKUPSWITCH", "IRETURN", "LRETURN", "FRETURN", "DRETURN", "ARETURN",
        "RETURN", "GETSTATIC", "PUTSTATIC", "GETFIELD", "PUTFIELD", "INVOKEVIRTUAL",
        "INVOKESPECIAL", "INVOKESTATIC", "INVOKEINTERFACE", "_OPCODE_UNDEFINED",
        "NEW", "NEWARRAY", "ANEWARRAY", "ARRAYLENGTH", "ATHROW", "CHECKCAST",
        "INSTANCEOF", "MONITORENTER", "MONITOREXIT", "WIDE", "MULTIANEWARRAY",
        "IFNULL", "IFNONNULL", "GOTO_W", "JSR_W",

        "BREAKPOINT",
        "UNKNOWN_0xCB", "UNKNOWN_0xCC", "UNKNOWN_0xCD", "UNKNOWN_0xCE",
        "UNKNOWN_0xCF", "UNKNOWN_0xD0", "UNKNOWN_0xD1", "UNKNOWN_0xD2",
        "UNKNOWN_0xD3", "UNKNOWN_0xD4", "UNKNOWN_0xD5", "UNKNOWN_0xD6",
        "UNKNOWN_0xD7", "UNKNOWN_0xD8", "UNKNOWN_0xD9", "UNKNOWN_0xDA",
        "UNKNOWN_0xDB", "UNKNOWN_0xDC", "UNKNOWN_0xDD", "UNKNOWN_0xDE",
        "UNKNOWN_0xDF", "UNKNOWN_0xE0", "UNKNOWN_0xE1", "UNKNOWN_0xE2",
        "UNKNOWN_0xE3", "UNKNOWN_0xE4", "UNKNOWN_0xE5", "UNKNOWN_0xE6",
        "UNKNOWN_0xE7", "UNKNOWN_0xE8", "UNKNOWN_0xE9", "UNKNOWN_0xEA",
        "UNKNOWN_0xEB", "UNKNOWN_0xEC", "UNKNOWN_0xED", "UNKNOWN_0xEE",
        "UNKNOWN_0xEF", "UNKNOWN_0xF0", "UNKNOWN_0xF1", "UNKNOWN_0xF2",
        "UNKNOWN_0xF3", "UNKNOWN_0xF4", "UNKNOWN_0xF5", "UNKNOWN_0xF6",
        "UNKNOWN_0xF7", "UNKNOWN_0xF8", "UNKNOWN_0xF9", "UNKNOWN_0xFA",
        "UNKNOWN_0xFB", "UNKNOWN_0xFC", "UNKNOWN_0xFD", "UNKNOWN_0xFE",
        "UNKNOWN_0xFF"
    };
    
    public LlvmMethodCompiler(ClassNode classNode, MethodNode methodNode) {
        this.classNode = classNode;
        this.methodNode = methodNode;
    }
    
    public void write(Writer w) {
        PrintWriter out = w instanceof PrintWriter ? (PrintWriter) w : new PrintWriter(w);
       
        try {
        
            out.println(LlvmUtil.javaMethodToLlvmFunctionDefinition(classNode, methodNode) + " {");
            if ((methodNode.access & Opcodes.ACC_ABSTRACT) != 0) {
                // TODO: Pass method name?
                out.println("    call void @nvmThrowAbstractMethodError()");
                out.println("    unreachable");
            } else {
                
                BasicInterpreter interpreter = new BasicInterpreter();
                Analyzer analyzer = new Analyzer(interpreter);
                analyzer.analyze(classNode.name, methodNode);
                
                Frame[] frames = analyzer.getFrames();
                Set<Var> stackVars = new TreeSet<Var>();
                Set<Var> localVars = new TreeSet<Var>();
                for (Frame frame : frames) {
                    if (frame != null) {
                        for (int i = 0, slot = 0; i < frame.getStackSize(); i++) {
                            BasicValue value = (BasicValue) frame.getStack(i);
                            Type t = value.getType();
                            if (t == null) {
                                if (value == BasicValue.UNINITIALIZED_VALUE) {
                                    stackVars.add(new Var("s" + slot, "%jobject*"));
                                } else {
                                    // RETURNADDRESS
                                    stackVars.add(new Var("s" + slot, "i8*"));
                                }
                            } else {
                                stackVars.add(new Var("s" + slot, LlvmUtil.javaLocalVarTypeToLlvmType(t)));
                            }
                            slot += value.getSize();
                        }
                        for (int i = 0; i < frame.getLocals();) {
                            BasicValue value = (BasicValue) frame.getLocal(i);
                            Type t = value.getType();
                            if (t == null) {
                                if (value == BasicValue.UNINITIALIZED_VALUE) {
                                    localVars.add(new Var("v" + i, "%jobject*"));
                                } else {
                                    // RETURNADDRESS
                                    localVars.add(new Var("v" + i, "i8*"));
                                }
                            } else {
                                localVars.add(new Var("v" + i, LlvmUtil.javaLocalVarTypeToLlvmType(value.getType())));
                            }
                            i += value.getSize();
                        }                        
                    }
                }
                
                for (Var v : stackVars) {
                    out.format("    %s = alloca %s\n", v, v.getType());
                }
                for (Var v : localVars) {
                    out.format("    %s = alloca %s\n", v, v.getType());
                }
                
                Type[] args = Type.getArgumentTypes(methodNode.desc);
                int slot = 0;
                int first = 0;
                if ((methodNode.access & Opcodes.ACC_STATIC) == 0) {
                    Var src = new Var("arg0", "%jobject*");
                    Var dest = new Var("v" + slot, "%jobject*");
                    out.format("    store %s %s, %s* %s\n", dest.getType(), src, dest.getType(), dest);
                    slot++;
                    first++;
                }
                for (int i = 0; i < args.length; i++) {
                    Type t = args[i];
                    String argName = "arg" + (i + first);
                    Var dest = new Var("v" + slot, LlvmUtil.javaLocalVarTypeToLlvmType(t));
                    Var src = new Var(argName, LlvmUtil.javaTypeToLlvmType(t));
                    if (t.getSort() == Type.CHAR || t.getSort() == Type.BOOLEAN) {
                        Var tmp = new Var(argName, "i32");
                        out.format("    %s = zext %s %s to i32\n", tmp, src.getType(), src);
                        src = tmp;
                    } else if (t.getSort() == Type.BYTE || t.getSort() == Type.SHORT) {
                        Var tmp = new Var(argName, "i32");
                        out.format("    %s = sext %s %s to i32\n", tmp, src.getType(), src);
                        src = tmp;
                    }
                    out.format("    store %s %s, %s* %s\n", dest.getType(), src, dest.getType(), dest);
                    slot += t.getSize();
                }
            
                MethodVisitor methodVisitor = new MethodVisitor(classNode, methodNode, frames, out);
                methodVisitor.write();
            }
            
            out.println("}");
            
        } catch (AnalyzerException ae) {
            throw new RuntimeException(ae);
        } finally {
            out.flush();
        }
    }
    
    private static class MethodVisitor extends EmptyVisitor {
        private ClassNode classNode;
        private MethodNode methodNode;
        private PrintWriter out;
        private final Stack<Var> stack = new Stack<Var>();
        private int tmpCounter = 0;
        private Set<Label> labels = new HashSet<Label>();
    
        private final Frame[] frames;
        private Frame currentFrame;
        private int frameIndex = -1;
        private List<TryCatchBlockNode> currentTryCatchBlocks = new ArrayList<TryCatchBlockNode>();
//        private Stack<TryCatchBlockNode> exceptionHandlers = new Stack<TryCatchBlockNode>();
        private Map<Label, Stack<TryCatchBlockNode>> exceptionHandlerStacks = new HashMap<Label, Stack<TryCatchBlockNode>>();
        private Set<LandingPad> landingPads = new HashSet<LandingPad>();
        private LandingPad currentLandingPad;
        private Var throwablePtr = null;
        private int pc = 0;
        private Var multiANewArrayLengths;

        
        public MethodVisitor(ClassNode classNode, MethodNode methodNode, Frame[] frames, PrintWriter out) {
            this.classNode = classNode;
            this.methodNode = methodNode;
            this.frames = frames;
            this.out = out;
            
            for (AbstractInsnNode o : methodNode.instructions.toArray()) {
                if (o instanceof JumpInsnNode) {
                    JumpInsnNode node = (JumpInsnNode) o;
                    labels.add(node.label.getLabel());
                } else if (o instanceof TableSwitchInsnNode) {
                    TableSwitchInsnNode node = (TableSwitchInsnNode) o;
                    labels.add(node.dflt.getLabel());
                    for (LabelNode labelNode : (List<LabelNode>) node.labels) {
                        labels.add(labelNode.getLabel());
                    }
                } else if (o instanceof LookupSwitchInsnNode) {
                    LookupSwitchInsnNode node = (LookupSwitchInsnNode) o;
                    labels.add(node.dflt.getLabel());
                    for (LabelNode labelNode : (List<LabelNode>) node.labels) {
                        labels.add(labelNode.getLabel());
                    }
                }
            }
            
            for (TryCatchBlockNode n : (List<TryCatchBlockNode>) methodNode.tryCatchBlocks) {
                labels.add(n.start.getLabel());
                labels.add(n.end.getLabel());
                labels.add(n.handler.getLabel());
            }
        }

        public void write() {
            
            // Make a slot on the stack for a Throwable if there are try-catch blocks for this method
            if (!methodNode.tryCatchBlocks.isEmpty()) {
                throwablePtr = new Var("throwable", "%jobject*");
                out.format("    %s = alloca %s\n", throwablePtr, throwablePtr.getType());
            }
            
            Set<String> accessors = new HashSet<String>();
            for (AbstractInsnNode insn = methodNode.instructions.getFirst(); insn != null; insn = insn.getNext()) {
                if (insn instanceof FieldInsnNode) {
                    FieldInsnNode n = (FieldInsnNode) insn;
                    String fieldName = LlvmUtil.mangleString(n.owner) + "_" + LlvmUtil.mangleString(n.name) + "__" + LlvmUtil.mangleString(n.desc);
                    String llvmType = LlvmUtil.javaTypeToLlvmType(Type.getType(n.desc));

                    if (n.getOpcode() == Opcodes.PUTSTATIC) {
                        String setter = "PUTSTATIC_" + fieldName;
                        if (!accessors.contains(setter)) {
                            Var v = new Var(setter, String.format("void (%s)*", llvmType));
                            out.format("    %s = load %s* @%s\n", v, v.getType(), setter);
                            accessors.add(setter);
                        }
                    } else if (n.getOpcode() == Opcodes.GETSTATIC) {
                        String getter = "GETSTATIC_" + fieldName;
                        if (!accessors.contains(getter)) {
                            Var v = new Var(getter, String.format("%s ()*", llvmType));
                            out.format("    %s = load %s* @%s\n", v, v.getType(), getter);
                            accessors.add(getter);
                        }
                    } else if (n.getOpcode() == Opcodes.PUTFIELD) {
                        String setter = "PUTFIELD_" + fieldName;
                        if (!accessors.contains(setter)) {
                            Var v = new Var(setter, String.format("void (%%jobject*,%s)*", llvmType));
                            out.format("    %s = load %s* @%s\n", v, v.getType(), setter);
                            accessors.add(setter);
                        }
                    } else if (n.getOpcode() == Opcodes.GETFIELD) {
                        String getter = "GETFIELD_" + fieldName;
                        if (!accessors.contains(getter)) {
                            Var v = new Var(getter, String.format("%s (%%jobject*)*", llvmType));
                            out.format("    %s = load %s* @%s\n", v, v.getType(), getter);
                            accessors.add(getter);
                        }
                    }
                }
            }
            
            // Allocate storage for the lengths array of any MULTIANEWARRAY instruction
            int maxDims = -1;
            for (AbstractInsnNode insn = methodNode.instructions.getFirst(); insn != null; insn = insn.getNext()) {
                if (insn instanceof MultiANewArrayInsnNode) {
                    MultiANewArrayInsnNode n = (MultiANewArrayInsnNode) insn;
                    maxDims = Math.max(maxDims, n.dims);
                }
            }
            
            if (maxDims != -1) {
                multiANewArrayLengths = tmp("multiANewArrayLengths", String.format("[%d x i32]", maxDims));
                out.format("    %s = alloca %s\n", multiANewArrayLengths, multiANewArrayLengths.getType());
            }
            
            for (AbstractInsnNode insn = methodNode.instructions.getFirst(); insn != null; insn = insn.getNext()) {
                if (nextFrame() != null) {
                    // Only reachable code has frames
                    if (insn.getOpcode() != -1) {
                        out.format("    ; %s\n", opcodeNames[insn.getOpcode()]);
                    }
                    if (insn instanceof LabelNode) {
                        List<TryCatchBlockNode> newCurrentTryCatchBlocks = new ArrayList<TryCatchBlockNode>();
                        for (TryCatchBlockNode node : (List<TryCatchBlockNode>) methodNode.tryCatchBlocks) {
                            int start = methodNode.instructions.indexOf(node.start);
                            int end = methodNode.instructions.indexOf(node.end);
                            if (pc >= start && pc < end) {
                                newCurrentTryCatchBlocks.add(node);
                            }
                        }
                        if (!currentTryCatchBlocks.equals(newCurrentTryCatchBlocks)) {
                            currentTryCatchBlocks = newCurrentTryCatchBlocks;
                            if (!currentTryCatchBlocks.isEmpty()) {
                                LandingPad lp = new LandingPad(currentTryCatchBlocks, methodNode, frames);
                                landingPads.add(lp);
                                currentLandingPad = lp;
                            } else {
                                currentLandingPad = null;
                            }
                        }
                    }
                    insn.accept(this);
                }
                pc++;
            }
            
            // Write out all landing pads
            for (LandingPad lpad : landingPads) {
                out.format("%s:\n", lpad.getLabel());

                Var ehptr = tmp("ehptr", "i8*");
                out.format("    %s = call i8* @llvm.eh.exception()\n", ehptr);
                Var throwable = tmpr("throwable");
                out.format("    %s = call %%jobject* @j_get_throwable(i8* %s)\n",
                        throwable, ehptr);

                Var sel = tmp("sel", "i64");
                out.format("    %s = call i64 (i8*, i8*, ...)* @llvm.eh.selector.i64(i8* %s, "
                        + "i8* bitcast (i32 (i32, i32, i64, i8*, i8*)* @j_eh_personality to i8*), i32 1)\n",
                        sel, ehptr);

                out.format("    store %s %s, %s* %s\n", throwablePtr.getType(), throwable, throwablePtr.getType(), throwablePtr);
                  
                out.format("    br label %%%sMatch\n", lpad.getLabel());
                out.format("%sMatch:\n", lpad.getLabel());

                boolean alwaysMatches = false;
                for (LandingPadHandler handler : lpad.getHandlers()) {
                    String type = handler.getType();
                    if (type == null || type.equals("java/lang/Throwable")) {
                        out.format("    br label %%%s\n", handler.getLabel());
                        alwaysMatches = true;
                        break; // Skip the rest since this handler will match anything that can be thrown.
                    } else {
                        Var condi32 = tmpi("cond");
                        Var cond = tmp("cond", "i1");
                        Var clazz = tmp("clazz", "%jclass*");
                        out.format("    %s = load %%jclass** @\"%s_%%jclass*\"\n", clazz, LlvmUtil.mangleString(type));
                        throwable = tmpr("throwable");
                        out.format("    %s = load %s* %s\n", throwable, throwablePtr.getType(), throwablePtr);
                        out.format("    %s = call i32 @j_eh_match_throwable(%%jobject* %s, %%jclass* %s)\n", condi32, throwable, clazz);
                        out.format("    %s = trunc i32 %s to i1\n", cond, condi32);
                        out.format("    br i1 %s, label %%%s%s, label %%%sNot%s\n", cond, 
                                lpad.getLabel(), LlvmUtil.mangleString(type), lpad.getLabel(), LlvmUtil.mangleString(type));
                        
                        out.format("%s%s:\n", lpad.getLabel(), LlvmUtil.mangleString(type));
                        throwable = tmpr("throwable");
                        out.format("    %s = load %s* %s\n", throwable, throwablePtr.getType(), throwablePtr);
                        setFrame(handler.getFrame());
                        stack.pop(); // Remove top of stack to make room for the actual throwable
                        push(throwable);
                        out.format("    br label %%%s\n", handler.getLabel());
                        
                        out.format("%sNot%s:\n", lpad.getLabel(), LlvmUtil.mangleString(type));
                    }
                }

                if (!alwaysMatches) {
                    // If we end up here none of the types match. Rethrow the exception.
                    throwable = tmpr("throwable");
                    out.format("    %s = load %s* %s\n", throwable, throwablePtr.getType(), throwablePtr);
                    out.format("    call void @nvmThrow(%%jobject* %s)\n", throwable);
                    out.format("    unreachable\n");
                }
            }
        }
        
        private Frame nextFrame() {
            return setFrame(frames[++frameIndex]);
        }
        
        private Frame setFrame(Frame frame) {
            currentFrame = frame;
            stack.clear();
            if (currentFrame != null) {
                for (int i = 0, slot = 0; i < currentFrame.getStackSize(); i++) {
                    BasicValue value = (BasicValue) currentFrame.getStack(i);
                    Var v = new Var("s" + slot, LlvmUtil.javaTypeToLlvmType(value));
                    stack.push(v);
                    if (value.getSize() > 1) {
                        stack.push(null);
                    }
                    slot += value.getSize();
                }
            }
            return currentFrame;
        }
        
        private Var tmpi(String name) {
            return tmp(name, "i32");
        }
        
        private Var tmpl(String name) {
            return tmp(name, "i64");
        }
        
        private Var tmpf(String name) {
            return tmp(name, "float");
        }
        
        private Var tmpd(String name) {
            return tmp(name, "double");
        }
        
        private Var tmpr(String name) {
            return tmp(name, "%jobject*");
        }
        
        private Var tmp(String name, String type) {
            return new Var(name + "_" + tmpCounter++, type);
        }
        
        private Var pop(String tmpVarName) {
            Var v = stack.pop();
            Var tmp = tmp(tmpVarName, v.getType());
            out.format("    %s = load %s* %s\n", tmp, v.getType(), v);
            return tmp;
        }
        
        private Var pop2(String tmpVarName) {
            stack.pop();
            Var v = stack.pop();
            Var tmp = tmp(tmpVarName, v.getType());
            out.format("    %s = load %s* %s\n", tmp, v.getType(), v);
            return tmp;
        }
        
        private boolean isSingleWordOnTopOfStack() {
            return stack.peek() != null;
        }
        
        private void push(Var var) {
            Var v = new Var("s" + stack.size(), var.getType());
            stack.push(v);
            out.format("    store %s %s, %s* %s\n", var.getType(), var, var.getType(), v);
        }
        
        private void push2(Var var) {
            Var v = new Var("s" + stack.size(), var.getType());
            stack.push(v);
            stack.push(null);
            out.format("    store %s %s, %s* %s\n", var.getType(), var, var.getType(), v);
        }
        
        private void store(Var op, int slot) {
            Var v = new Var("v" + slot, op.getType());
            out.format("    store %s %s, %s* %s\n", op.getType(), op, op.getType(), v);
        }
        
        private Var loadi(int slot) {
            return load("i32", slot);
        }
        
        private Var loadl(int slot) {
            return load("i64", slot);
        }
        
        private Var loadf(int slot) {
            return load("float", slot);
        }

        private Var loadd(int slot) {
            return load("double", slot);
        }

        private Var loadr(int slot) {
            return load("%jobject*", slot);
        }
        
        private Var load(String type, int slot) {
            Var v = new Var("v" + slot, type);
            Var res = tmp(v.getName(), v.getType());
            out.format("    %s = load %s* %s\n", res, type, v);
            return res;
        }
        
        private void checkNull(Var op) {
            String successLabel = String.format("CheckNullNotNull%d", pc);
            String failureLabel = String.format("CheckNullIsNull%d", pc);
            Var cond = tmp("cond", "i1");
            out.format("    %s = icmp ne %%jobject* %s, inttoptr (i32 0 to %%jobject*)\n", cond, op);
            out.format("    br i1 %s, label %%%s, label %%%s\n", cond, successLabel, failureLabel);
            out.format("%s:\n", failureLabel);
            if (currentTryCatchBlocks.isEmpty()) {
                out.format("    call void @nvmThrowNullPointerException()\n");
                out.format("    unreachable\n", successLabel);
            } else {
                out.format("    invoke void @nvmThrowNullPointerException() to label %%%s unwind label %%%s\n", successLabel, currentLandingPad.getLabel());
            }

            out.format("%s:\n", successLabel);
        }
        
        private void checkBounds(Var length, Var index) {
            String successLabel = String.format("CheckBoundsSuccess%d", pc);
            String failureLabel = String.format("CheckBoundsFailure%d", pc);
            Var cond = tmp("cond", "i1");
            out.format("    %s = icmp ult i32 %s, %s\n", cond, index, length);
            out.format("    br i1 %s, label %%%s, label %%%s\n", cond, successLabel, failureLabel);
            out.format("%s:\n", failureLabel);
            if (currentTryCatchBlocks.isEmpty()) {
                out.format("    call void @nvmThrowArrayIndexOutOfBoundsException(i32 %s)\n", index);
                out.format("    unreachable\n");
            } else {
                out.format("    invoke void @nvmThrowArrayIndexOutOfBoundsException(i32 %s) to label %%%s unwind label %%%s\n", index, successLabel, currentLandingPad.getLabel());
            }
            out.format("%s:\n", successLabel);
        }
        
        @Override
        public void visitFieldInsn(int opcode, String owner, String name, String desc) {
            boolean ztatic = opcode == Opcodes.GETSTATIC || opcode == Opcodes.PUTSTATIC;
            
            Type t = Type.getType(desc);
            String llvmType = LlvmUtil.javaTypeToLlvmType(t);
            
            Var val = null;
            if (opcode == Opcodes.PUTSTATIC || opcode == Opcodes.PUTFIELD) {
                val = t.getSize() == 2 ? pop2("val") : pop("val");
                if (t.getSort() == Type.BOOLEAN || t.getSort() == Type.BYTE) {
                    Var tmp = tmp(val.getName(), "i8");
                    out.format("    %s = trunc i32 %s to i8\n", tmp, val);
                    val = tmp;
                } else if (t.getSort() == Type.SHORT || t.getSort() == Type.CHAR) {
                    Var tmp = tmp(val.getName(), "i16");
                    out.format("    %s = trunc i32 %s to i16\n", tmp, val);
                    val = tmp;
                }
            }
            
            Var obj = null;
            if (!ztatic) {
                obj = pop("obj");
                checkNull(obj);
            }
            
            Var res = null;
            String fieldName = LlvmUtil.mangleString(owner) + "_" + LlvmUtil.mangleString(name) + "__" + LlvmUtil.mangleString(desc);
            
            if (opcode == Opcodes.GETSTATIC) {
                Var v = new Var("GETSTATIC_" + fieldName, String.format("%s ()*", llvmType));
                res = tmp("res", llvmType);
                out.format("    %s = call %s %s()\n", res, llvmType, v);
            } else if (opcode == Opcodes.PUTSTATIC) {
                Var v = new Var("PUTSTATIC_" + fieldName, String.format("void (%s)*", llvmType));
                out.format("    call void %s(%s %s)\n", v, llvmType, val);
            } else if (opcode == Opcodes.GETFIELD) {
                Var v = new Var("GETFIELD_" + fieldName, String.format("%s (%%jobject*)*", llvmType));
                res = tmp("res", llvmType);
                out.format("    %s = call %s %s(%%jobject* %s)\n", res, llvmType, v, obj);
            } else if (opcode == Opcodes.PUTFIELD) {
                Var v = new Var("PUTFIELD_" + fieldName, String.format("void (%%jobject*,%s)*", llvmType));
                out.format("    call void %s(%%jobject* %s, %s %s)\n", v, obj, llvmType, val);
            }
            
            if (res != null) {
                if (t.getSort() == Type.BOOLEAN || t.getSort() == Type.CHAR) {
                    Var tmp = tmpi("res");
                    out.format("    %s = zext %s %s to i32\n", tmp, res.getType(), res);
                    res = tmp;
                } else if (t.getSort() == Type.BYTE || t.getSort() == Type.SHORT) {
                    Var tmp = tmpi("res");
                    out.format("    %s = sext %s %s to i32\n", tmp, res.getType(), res);
                    res = tmp;
                }
                if (t.getSize() == 2) {
                    push2(res);
                } else {
                    push(res);
                }
            }

            
//            Var address = tmp("address", llvmType + "*");
//            if (ztatic) {
//                Var tmpAddress = tmp("address", "i8*");
//                out.format("    %s = call i8* @j_get_class_field_address(%%jclass* %s, i8* %s, i8* %s, %%jclass* %s)\n",
//                        tmpAddress, clazz, LlvmUtil.getStringReference(name), 
//                        LlvmUtil.getStringReference(desc), clazz);
//                out.format("    %s = bitcast i8* %s to %s\n", address, tmpAddress, address.getType());
//            } else {
//                Var offset = tmpi("offset");
//                out.format("    %s = call i32 @j_get_instance_field_offset(%%jclass* %s, i8* %s, i8* %s, %%jclass* %s)\n",
//                        offset, clazz, LlvmUtil.getStringReference(name), 
//                        LlvmUtil.getStringReference(desc), clazz);
//                Var tmpAddress1 = tmp("tmpaddress1", "i8**");
//                out.format("    %s = getelementptr %%jobject* %s, i32 0, i32 1\n", 
//                        tmpAddress1, obj);
//                Var tmpAddress2 = tmp("tmpaddress2", "i8*");
//                out.format("    %s = load i8** %s\n", tmpAddress2, tmpAddress1);
//                Var addressI = tmp("tmpaddressI", "i64");
//                out.format("    %s = ptrtoint i8* %s to i64\n", addressI, tmpAddress2);
//                Var offset64 = tmp("offset", "i64");
//                out.format("    %s = sext i32 %s to i64\n", offset64, offset);
//                Var addressI2 = tmp("tmpaddressI2", "i64");
//                out.format("    %s = add i64 %s, %s\n", addressI2, addressI, offset64);
//                out.format("    %s = inttoptr i64 %s to %s*\n", address, addressI2, llvmType);;
//            }
            
//            if (opcode == Opcodes.PUTSTATIC || opcode == Opcodes.PUTFIELD) {
//                out.format("    store %s %s, %s* %s\n", llvmType, val, llvmType, address);
//            } else {
//                Var res = tmp("res", llvmType);
//                out.format("    %s = load %s* %s\n", res, llvmType, address);
//                
//            }
            
//            
//            default:
//                throw new RuntimeException("Opcode not implemented: " + opcodeNames[opcode]);
//            }
        }
        @Override
        public void visitInsn(int opcode) {
            switch (opcode) {
            case Opcodes.NOP:
                out.println("    ; nop");
                break;
            case Opcodes.ICONST_M1:
            case Opcodes.ICONST_0:
            case Opcodes.ICONST_1:
            case Opcodes.ICONST_2:
            case Opcodes.ICONST_3:
            case Opcodes.ICONST_4:
            case Opcodes.ICONST_5: {
                Var res = tmpi("res");
                out.format("    %s = bitcast i32 %d to i32\n", res, opcode - Opcodes.ICONST_0);
                push(res);
                break;
            }
            case Opcodes.LCONST_0:
            case Opcodes.LCONST_1: {
                Var res = tmpl("res");
                out.format("    %s = bitcast i64 %d to i64\n", res, opcode - Opcodes.LCONST_0);
                push2(res);
                break;
            }
            case Opcodes.FCONST_0:
            case Opcodes.FCONST_1:
            case Opcodes.FCONST_2: {
                Var res = tmpf("res");
                out.format("    %s = bitcast float %f to float\n", res, (float)  (opcode - Opcodes.FCONST_0));
                push(res);
                break;
            }
            case Opcodes.DCONST_0:
            case Opcodes.DCONST_1: {
                Var res = tmpd("res");
                out.format("    %s = bitcast double %f to double\n", res, (double)  (opcode - Opcodes.DCONST_0));
                push2(res);
                break;
            }
            case Opcodes.IADD: {
                Var op1 = pop("op1");
                Var op2 = pop("op2");
                Var res = tmpi("res");
                out.format("    %s = add i32 %s, %s\n", res, op2, op1);
                push(res);
                break;
            }
            case Opcodes.LADD: {
                Var op1 = pop2("op1");
                Var op2 = pop2("op2");
                Var res = tmpl("res");
                out.format("    %s = add i64 %s, %s\n", res, op2, op1);
                push2(res);
                break;
            }
            case Opcodes.FADD: {
                Var op1 = pop("op1");
                Var op2 = pop("op2");
                Var res = tmpf("res");
                out.format("    %s = add float %s, %s\n", res, op2, op1);
                push(res);
                break;
            }
            case Opcodes.DADD: {
                Var op1 = pop2("op1");
                Var op2 = pop2("op2");
                Var res = tmpd("res");
                out.format("    %s = add double %s, %s\n", res, op2, op1);
                push2(res);
                break;
            }
            case Opcodes.ISUB: {
                Var op1 = pop("op1");
                Var op2 = pop("op2");
                Var res = tmpi("res");
                out.format("    %s = sub i32 %s, %s\n", res, op2, op1);
                push(res);
                break;
            }
            case Opcodes.LSUB: {
                Var op1 = pop2("op1");
                Var op2 = pop2("op2");
                Var res = tmpl("res");
                out.format("    %s = sub i64 %s, %s\n", res, op2, op1);
                push2(res);
                break;
            }
            case Opcodes.FSUB: {
                Var op1 = pop("op1");
                Var op2 = pop("op2");
                Var res = tmpf("res");
                out.format("    %s = sub float %s, %s\n", res, op2, op1);
                push(res);
                break;
            }
            case Opcodes.DSUB: {
                Var op1 = pop2("op1");
                Var op2 = pop2("op2");
                Var res = tmpd("res");
                out.format("    %s = sub double %s, %s\n", res, op2, op1);
                push2(res);
                break;
            }
            case Opcodes.IMUL: {
                Var op1 = pop("op1");
                Var op2 = pop("op2");
                Var res = tmpi("res");
                out.format("    %s = mul i32 %s, %s\n", res, op2, op1);
                push(res);
                break;
            }
            case Opcodes.LMUL: {
                Var op1 = pop2("op1");
                Var op2 = pop2("op2");
                Var res = tmpl("res");
                out.format("    %s = mul i64 %s, %s\n", res, op2, op1);
                push2(res);
                break;
            }
            case Opcodes.FMUL: {
                Var op1 = pop("op1");
                Var op2 = pop("op2");
                Var res = tmpf("res");
                out.format("    %s = mul float %s, %s\n", res, op2, op1);
                push(res);
                break;
            }
            case Opcodes.DMUL: {
                Var op1 = pop2("op1");
                Var op2 = pop2("op2");
                Var res = tmpd("res");
                out.format("    %s = mul double %s, %s\n", res, op2, op1);
                push2(res);
                break;
            }
            case Opcodes.IDIV: {
                // TODO: Throw java.lang.ArithmeticException on division by zero
                Var op1 = pop("op1");
                Var op2 = pop("op2");
                Var res = tmpi("res");
                out.format("    %s = sdiv i32 %s, %s\n", res, op2, op1);
                push(res);
                break;
            }
            case Opcodes.LDIV: {
                // TODO: Throw java.lang.ArithmeticException on division by zero
                Var op1 = pop2("op1");
                Var op2 = pop2("op2");
                Var res = tmpl("res");
                out.format("    %s = sdiv i64 %s, %s\n", res, op2, op1);
                push2(res);
                break;
            }
            case Opcodes.FDIV: {
                Var op1 = pop("op1");
                Var op2 = pop("op2");
                Var res = tmpf("res");
                out.format("    %s = fdiv float %s, %s\n", res, op2, op1);
                push(res);
                break;
            }
            case Opcodes.DDIV: {
                Var op1 = pop2("op1");
                Var op2 = pop2("op2");
                Var res = tmpd("res");
                out.format("    %s = fdiv double %s, %s\n", res, op2, op1);
                push2(res);
                break;
            }
            case Opcodes.IREM: {
                // TODO: Throw java.lang.ArithmeticException on division by zero
                Var op1 = pop("op1");
                Var op2 = pop("op2");
                Var res = tmpi("res");
                out.format("    %s = srem i32 %s, %s\n", res, op2, op1);
                push(res);
                break;
            }
            case Opcodes.LREM: {
                // TODO: Throw java.lang.ArithmeticException on division by zero
                Var op1 = pop2("op1");
                Var op2 = pop2("op2");
                Var res = tmpl("res");
                out.format("    %s = srem i64 %s, %s\n", res, op2, op1);
                push2(res);
                break;
            }
            case Opcodes.FREM: {
                Var op1 = pop("op1");
                Var op2 = pop("op2");
                Var res = tmpf("res");
                out.format("    %s = frem float %s, %s\n", res, op2, op1);
                push(res);
                break;
            }
            case Opcodes.DREM: {
                Var op1 = pop2("op1");
                Var op2 = pop2("op2");
                Var res = tmpd("res");
                out.format("    %s = frem double %s, %s\n", res, op2, op1);
                push2(res);
                break;
            }
            case Opcodes.INEG: {
                Var op = pop("op");
                Var res = tmpi("res");
                out.format("    %s = sub i32 0, %s\n", res, op);
                push(res);
                break;
            }
            case Opcodes.LNEG: {
                Var op = pop2("op");
                Var res = tmpl("res");
                out.format("    %s = sub i64 0, %s\n", res, op);
                push2(res);
                break;
            }
            case Opcodes.FNEG: {
                Var op = pop("op");
                Var tmp1 = tmpf("tmp1");
                Var tmp2 = tmpf("tmp2");
                Var res = tmpf("res");
                out.format("    %s = bitcast float %s to i32\n", tmp1, op);
                out.format("    %s = xor i32 %s, %d\n", tmp2, tmp1, 0x80000000);
                out.format("    %s = bitcast i32 %s to float\n", res, tmp2);
                push(res);
                break;
            }
            case Opcodes.DNEG: {
                Var op = pop2("op");
                Var tmp1 = tmpd("tmp1");
                Var tmp2 = tmpd("tmp2");
                Var res = tmpd("res");
                out.format("    %s = bitcast double %s to i64\n", tmp1, op);
                out.format("    %s = xor i64 %s, %d\n", tmp2, tmp1, 0x8000000000000000L);
                out.format("    %s = bitcast i64 %s to double\n", res, tmp2);
                push2(res);
                break;
            }
            case Opcodes.ISHL: {
                Var op1 = pop("op1");
                Var op2 = pop("op2");
                Var tmp = tmpi("tmp");
                Var res = tmpi("res");
                out.format("    %s = and i32 31, %s\n", tmp, op1);
                out.format("    %s = shl i32 %s, %s\n", res, op2, tmp);
                push(res);
                break;
            }
            case Opcodes.LSHL: {
                Var op1 = pop("op1");
                Var op2 = pop2("op2");
                Var tmp1 = tmpi("tmp1");
                Var tmp2 = tmpl("tmp2");
                Var res = tmpl("res");
                out.format("    %s = and i32 63, %s\n", tmp1, op1);
                out.format("    %s = zext i32 %s to i64\n", tmp2, tmp1);
                out.format("    %s = shl i64 %s, %s\n", res, op2, tmp2);
                push2(res);
                break;
            }
            case Opcodes.ISHR: {
                Var op1 = pop("op1");
                Var op2 = pop("op2");
                Var tmp = tmpi("tmp");
                Var res = tmpi("res");
                out.format("    %s = and i32 31, %s\n", tmp, op1);
                out.format("    %s = ashr i32 %s, %s\n", res, op2, tmp);
                push(res);
                break;
            }
            case Opcodes.LSHR: {
                Var op1 = pop("op1");
                Var op2 = pop2("op2");
                Var tmp1 = tmpi("tmp1");
                Var tmp2 = tmpl("tmp1");
                Var res = tmpl("res");
                out.format("    %s = and i32 63, %s\n", tmp1, op1);
                out.format("    %s = zext i32 %s to i64\n", tmp2, tmp1);
                out.format("    %s = ashr i64 %s, %s\n", res, op2, tmp2);
                push2(res);
                break;
            }
            case Opcodes.IUSHR: {
                Var op1 = pop("op1");
                Var op2 = pop("op2");
                Var tmp = tmpi("tmp");
                Var res = tmpi("res");
                out.format("    %s = and i32 31, %s\n", tmp, op1);
                out.format("    %s = lshr i32 %s, %s\n", res, op2, tmp);
                push(res);
                break;
            }
            case Opcodes.LUSHR: {
                Var op1 = pop("op1");
                Var op2 = pop2("op2");
                Var tmp1 = tmpi("tmp1");
                Var tmp2 = tmpl("tmp2");
                Var res = tmpl("res");
                out.format("    %s = and i32 63, %s\n", tmp1, op1);
                out.format("    %s = zext i32 %s to i64\n", tmp2, tmp1);
                out.format("    %s = lshr i64 %s, %s\n", res, op2, tmp2);
                push2(res);
                break;
            }
            case Opcodes.IAND: {
                Var op1 = pop("op1");
                Var op2 = pop("op2");
                Var res = tmpi("res");
                out.format("    %s = and i32 %s, %s\n", res, op2, op1);
                push(res);
                break;
            }
            case Opcodes.LAND: {
                Var op1 = pop2("op1");
                Var op2 = pop2("op2");
                Var res = tmpl("res");
                out.format("    %s = and i64 %s, %s\n", res, op2, op1);
                push2(res);
                break;
            }
            case Opcodes.IOR: {
                Var op1 = pop("op1");
                Var op2 = pop("op2");
                Var res = tmpi("res");
                out.format("    %s = or i32 %s, %s\n", res, op2, op1);
                push(res);
                break;
            }
            case Opcodes.LOR: {
                Var op1 = pop2("op1");
                Var op2 = pop2("op2");
                Var res = tmpl("res");
                out.format("    %s = or i64 %s, %s\n", res, op2, op1);
                push2(res);
                break;
            }
            case Opcodes.IXOR: {
                Var op1 = pop("op1");
                Var op2 = pop("op2");
                Var res = tmpi("res");
                out.format("    %s = xor i32 %s, %s\n", res, op2, op1);
                push(res);
                break;
            }
            case Opcodes.LXOR: {
                Var op1 = pop2("op1");
                Var op2 = pop2("op2");
                Var res = tmpl("res");
                out.format("    %s = xor i64 %s, %s\n", res, op2, op1);
                push2(res);
                break;
            }
            case Opcodes.IRETURN: {
                Type retType = Type.getReturnType(methodNode.desc);
                Var op = pop("op");
                if (retType.getSort() != Type.INT) {
                    Var tmp = tmp("tmp", LlvmUtil.javaTypeToLlvmType(retType));;
                    out.format("    %s = trunc i32 %s to %s\n", tmp, op, LlvmUtil.javaTypeToLlvmType(retType));
                    out.format("    ret %s %s\n", LlvmUtil.javaTypeToLlvmType(retType), tmp);
                } else {
                    out.format("    ret %s %s\n", LlvmUtil.javaTypeToLlvmType(retType), op);
                }
                break;
            }
            case Opcodes.LRETURN: {
                Var op = pop2("op");
                out.format("    ret i64 %s\n", op);
                break;
            }
            case Opcodes.FRETURN: {
                Var op = pop("op");
                out.format("    ret float %s\n", op);
                break;
            }
            case Opcodes.DRETURN: {
                Var op = pop2("op");
                out.format("    ret double %s\n", op);
                break;
            }
            case Opcodes.I2L: {
                Var op = pop("op");
                Var res = tmpl("tmp");
                out.format("    %s = sext i32 %s to i64\n", res, op);
                push2(res);
                break;
            }
            case Opcodes.I2B: {
                Var op = pop("op");
                Var tmp = tmp("tmp", "i8");
                Var res = tmpi("res");
                out.format("    %s = trunc i32 %s to i8\n", tmp, op);
                out.format("    %s = sext i8 %s to i32\n", res, tmp);
                push(res);
                break;
            }
            case Opcodes.I2C: {
                Var op = pop("op");
                Var tmp = tmp("tmp", "i16");
                Var res = tmpi("res");
                out.format("    %s = trunc i32 %s to i16\n", tmp, op);
                out.format("    %s = zext i16 %s to i32\n", res, tmp);
                push(res);
                break;
            }
            case Opcodes.I2S: {
                Var op = pop("op");
                Var tmp = tmp("tmp", "i16");
                Var res = tmpi("res");
                out.format("    %s = trunc i32 %s to i16\n", tmp, op);
                out.format("    %s = sext i16 %s to i32\n", res, tmp);
                push(res);
                break;
            }
            case Opcodes.I2F: {
                Var op = pop("op");
                Var res = tmpf("res");
                out.format("    %s = sitofp i32 %s to float\n", res, op);
                push(res);
                break;
            }
            case Opcodes.I2D: {
                Var op = pop("op");
                Var res = tmpd("res");
                out.format("    %s = sitofp i32 %s to double\n", res, op);
                push2(res);
                break;
            }
            case Opcodes.L2I: {
                Var op = pop2("op");
                Var res = tmpi("res");
                out.format("    %s = trunc i64 %s to i32\n", res, op);
                push(res);
                break;
            }
            case Opcodes.L2F: {
                Var op = pop2("op");
                Var res = tmpf("res");
                out.format("    %s = sitofp i64 %s to float\n", res, op);
                push(res);
                break;
            }
            case Opcodes.L2D: {
                Var op = pop2("op");
                Var res = tmpd("res");
                out.format("    %s = sitofp i64 %s to double\n", res, op);
                push2(res);
                break;
            }
            case Opcodes.F2I: {
                Var op = pop("op");
                Var eq = tmp("eq", "i1");
                Var pinf = tmp("pinf", "i1");
                Var ninf = tmp("ninf", "i1");
                Var tmp1 = tmpi("tmp1");
                Var tmp2 = tmpi("tmp2");
                Var fptosi = tmpi("fptosi");
                Var res = tmpi("res");
                out.format("    %s = fcmp oeq float %s, %s\n", eq, op, op);
                out.format("    %s = fcmp oge float %s, %f\n", pinf, op, (float) Integer.MAX_VALUE);
                out.format("    %s = fcmp ole float %s, %f\n", ninf, op, (float) Integer.MIN_VALUE);
                out.format("    %s = fptosi float %s to i32\n", fptosi, op);
                out.format("    %s = select i1 %s, i32 %s, i32 %d\n", tmp1, eq, fptosi, 0);
                out.format("    %s = select i1 %s, i32 %d, i32 %s\n", tmp2, pinf, Integer.MAX_VALUE, tmp1);
                out.format("    %s = select i1 %s, i32 %d, i32 %s\n", res, ninf, Integer.MIN_VALUE, tmp2);
                push(res);
                break;
            }
            case Opcodes.F2L: {
                Var op = pop("op");
                Var eq = tmp("eq", "i1");
                Var pinf = tmp("pinf", "i1");
                Var ninf = tmp("ninf", "i1");
                Var tmp1 = tmpl("tmp1");
                Var tmp2 = tmpl("tmp2");
                Var fptosi = tmpl("fptosi");
                Var res = tmpl("res");
                out.format("    %s = fcmp oeq float %s, %s\n", eq, op, op);
                out.format("    %s = fcmp oge float %s, %f\n", pinf, op, (float) Long.MAX_VALUE);
                out.format("    %s = fcmp ole float %s, %f\n", ninf, op, (float) Long.MIN_VALUE);
                out.format("    %s = fptosi float %s to i64\n", fptosi, op);
                out.format("    %s = select i1 %s, i64 %s, i64 %d\n", tmp1, eq, fptosi, 0);
                out.format("    %s = select i1 %s, i64 %d, i64 %s\n", tmp2, pinf, Long.MAX_VALUE, tmp1);
                out.format("    %s = select i1 %s, i64 %d, i64 %s\n", res, ninf, Long.MIN_VALUE, tmp2);
                push2(res);
                break;
            }
            case Opcodes.F2D: {
                Var op = pop("op");
                Var res = tmpd("res");
                out.format("    %s = fpext float %s to double\n", res, op);
                push2(res);
                break;
            }
            case Opcodes.D2I: {
                Var op = pop2("op");
                Var eq = tmp("eq", "i1");
                Var pinf = tmp("pinf", "i1");
                Var ninf = tmp("ninf", "i1");
                Var tmp1 = tmpi("tmp1");
                Var tmp2 = tmpi("tmp2");
                Var fptosi = tmpi("fptosi");
                Var res = tmpi("res");
                out.format("    %s = fcmp oeq double %s, %s\n", eq, op, op);
                out.format("    %s = fcmp oge double %s, %f\n", pinf, op, (double) Integer.MAX_VALUE);
                out.format("    %s = fcmp ole double %s, %f\n", ninf, op, (double) Integer.MIN_VALUE);
                out.format("    %s = fptosi double %s to i32\n", fptosi, op);
                out.format("    %s = select i1 %s, i32 %s, i32 %d\n", tmp1, eq, fptosi, 0);
                out.format("    %s = select i1 %s, i32 %d, i32 %s\n", tmp2, pinf, Integer.MAX_VALUE, tmp1);
                out.format("    %s = select i1 %s, i32 %d, i32 %s\n", res, ninf, Integer.MIN_VALUE, tmp2);
                push(res);
                break;
            }
            case Opcodes.D2L: {
                Var op = pop2("op");
                Var eq = tmp("eq", "i1");
                Var pinf = tmp("pinf", "i1");
                Var ninf = tmp("ninf", "i1");
                Var tmp1 = tmpl("tmp1");
                Var tmp2 = tmpl("tmp2");
                Var fptosi = tmpl("fptosi");
                Var res = tmpl("res");
                out.format("    %s = fcmp oeq double %s, %s\n", eq, op, op);
                out.format("    %s = fcmp oge double %s, %f\n", pinf, op, (float) Long.MAX_VALUE);
                out.format("    %s = fcmp ole double %s, %f\n", ninf, op, (float) Long.MIN_VALUE);
                out.format("    %s = fptosi double %s to i64\n", fptosi, op);
                out.format("    %s = select i1 %s, i64 %s, i64 %d\n", tmp1, eq, fptosi, 0);
                out.format("    %s = select i1 %s, i64 %d, i64 %s\n", tmp2, pinf, Long.MAX_VALUE, tmp1);
                out.format("    %s = select i1 %s, i64 %d, i64 %s\n", res, ninf, Long.MIN_VALUE, tmp2);
                push2(res);
                break;
            }
            case Opcodes.D2F: {
                Var op = pop2("op");
                Var res = tmpf("res");
                out.format("    %s = fptrunc double %s to float\n", res, op);
                push(res);
                break;
            }
            case Opcodes.LCMP: {
                Var op1 = pop2("op1");
                Var op2 = pop2("op2");
                Var tmp1 = tmp("tmp1", "i1");
                Var tmp2 = tmp("tmp2", "i1");
                Var tmp3 = tmpi("tmp3");
                Var tmp4 = tmpi("tmp4");
                Var res = tmpi("res");
                out.format("    %s = icmp slt i64 %s, %s\n", tmp1, op2, op1);
                out.format("    %s = icmp sgt i64 %s, %s\n", tmp2, op2, op1);
                out.format("    %s = zext i1 %s to i32\n", tmp3, tmp1);
                out.format("    %s = zext i1 %s to i32\n", tmp4, tmp2);
                out.format("    %s = sub i32 %s, %s\n", res, tmp4, tmp3);
                push(res);
                break;
            }
            case Opcodes.FCMPL: {
                Var op1 = pop("op1");
                Var op2 = pop("op2");
                Var ugt = tmp("ugt", "i1"); // 1 if op1 > op2 or either is NaN
                Var olt = tmp("olt", "i1"); // 1 if op1 < op2
                Var ugt32 = tmpi("ugt32");
                Var olt32 = tmpi("olt32");
                Var res = tmpi("res");
                out.format("    %s = fcmp ugt float %s, %s\n", ugt, op1, op2);
                out.format("    %s = fcmp olt float %s, %s\n", olt, op1, op2);
                out.format("    %s = zext i1 %s to i32\n", ugt32, ugt);
                out.format("    %s = zext i1 %s to i32\n", olt32, olt);
                out.format("    %s = sub i32 %s, %s\n", res, olt32, ugt32);
                push(res);
                break;
            }
            case Opcodes.FCMPG: {
                Var op1 = pop("op1");
                Var op2 = pop("op2");
                Var ogt = tmp("ogt", "i1"); // 1 if op1 > op2
                Var ult = tmp("ult", "i1"); // 1 if op1 < op2 or either is NaN
                Var ogt32 = tmpi("ogt32");
                Var ult32 = tmpi("ult32");
                Var res = tmpi("res");
                out.format("    %s = fcmp ogt float %s, %s\n", ogt, op1, op2);
                out.format("    %s = fcmp ult float %s, %s\n", ult, op1, op2);
                out.format("    %s = zext i1 %s to i32\n", ogt32, ogt);
                out.format("    %s = zext i1 %s to i32\n", ult32, ult);
                out.format("    %s = sub i32 %s, %s\n", res, ult32, ogt32);
                push(res);
                break;
            }
            case Opcodes.DCMPL: {
                Var op1 = pop2("op1");
                Var op2 = pop2("op2");
                Var ugt = tmp("ugt", "i1"); // 1 if op1 > op2 or either is NaN
                Var olt = tmp("olt", "i1"); // 1 if op1 < op2
                Var ugt32 = tmpi("ugt32");
                Var olt32 = tmpi("olt32");
                Var res = tmpi("res");
                out.format("    %s = fcmp ugt double %s, %s\n", ugt, op1, op2);
                out.format("    %s = fcmp olt double %s, %s\n", olt, op1, op2);
                out.format("    %s = zext i1 %s to i32\n", ugt32, ugt);
                out.format("    %s = zext i1 %s to i32\n", olt32, olt);
                out.format("    %s = sub i32 %s, %s\n", res, olt32, ugt32);
                push(res);
                break;
            }
            case Opcodes.DCMPG: {
                Var op1 = pop2("op1");
                Var op2 = pop2("op2");
                Var ogt = tmp("ogt", "i1"); // 1 if op1 > op2
                Var ult = tmp("ult", "i1"); // 1 if op1 < op2 or either is NaN
                Var ogt32 = tmpi("ogt32");
                Var ult32 = tmpi("ult32");
                Var res = tmpi("res");
                out.format("    %s = fcmp ogt double %s, %s\n", ogt, op1, op2);
                out.format("    %s = fcmp ult double %s, %s\n", ult, op1, op2);
                out.format("    %s = zext i1 %s to i32\n", ogt32, ogt);
                out.format("    %s = zext i1 %s to i32\n", ult32, ult);
                out.format("    %s = sub i32 %s, %s\n", res, ult32, ogt32);
                push(res);
                break;
            }
            case Opcodes.RETURN:
                out.println("    ret void");
                break;
            case Opcodes.ARETURN: {
                Var op = pop("op");
                out.format("    ret %%jobject* %s\n", op);
                break;
            }
            case Opcodes.ACONST_NULL: {
                Var res = tmpr("res");
                out.format("    %s = inttoptr i32 0 to %%jobject*\n", res);
                push(res);
                break;
            }
            case Opcodes.ARRAYLENGTH: {
                Var o = pop("o");
                Var res = tmpi("res");
                checkNull(o);
                out.format("    %s = call i32 @j_arraylength(%%jobject* %s)\n", res, o);
                push(res);
                break;
            }
            case Opcodes.IALOAD: {
                Var index = pop("index");
                Var o = pop("o");
                Var res = tmpi("res");
                checkNull(o);
                Var length = tmpi("length");
                out.format("    %s = call i32 @j_arraylength(%%jobject* %s)\n", length, o);
                checkBounds(length, index);
                out.format("    %s = call i32 @j_iaload(%%jobject* %s, i32 %s)\n", res, o, index);
                push(res);
                break;
            }
            case Opcodes.IASTORE: {
                Var value = pop("value");
                Var index = pop("index");
                Var o = pop("o");
                checkNull(o);
                Var length = tmpi("length");
                out.format("    %s = call i32 @j_arraylength(%%jobject* %s)\n", length, o);
                checkBounds(length, index);
                out.format("    call void @j_iastore(%%jobject* %s, i32 %s, i32 %s)\n", o, index, value);
                break;
            }
            case Opcodes.FALOAD: {
                Var index = pop("index");
                Var o = pop("o");
                Var res = tmpf("res");
                checkNull(o);
                Var length = tmpi("length");
                out.format("    %s = call i32 @j_arraylength(%%jobject* %s)\n", length, o);
                checkBounds(length, index);
                out.format("    %s = call float @j_faload(%%jobject* %s, i32 %s)\n", res, o, index);
                push(res);
                break;
            }
            case Opcodes.FASTORE: {
                Var value = pop("value");
                Var index = pop("index");
                Var o = pop("o");
                checkNull(o);
                Var length = tmpi("length");
                out.format("    %s = call i32 @j_arraylength(%%jobject* %s)\n", length, o);
                checkBounds(length, index);
                out.format("    call void @j_fastore(%%jobject* %s, i32 %s, float %s)\n", o, index, value);
                break;
            }
            case Opcodes.BALOAD: {
                Var index = pop("index");
                Var o = pop("o");
                Var res = tmpi("res");
                checkNull(o);
                Var length = tmpi("length");
                out.format("    %s = call i32 @j_arraylength(%%jobject* %s)\n", length, o);
                checkBounds(length, index);
                out.format("    %s = call i32 @j_baload(%%jobject* %s, i32 %s)\n", res, o, index);
                push(res);
                break;
            }
            case Opcodes.BASTORE: {
                Var value = pop("value");
                Var index = pop("index");
                Var o = pop("o");
                checkNull(o);
                Var length = tmpi("length");
                out.format("    %s = call i32 @j_arraylength(%%jobject* %s)\n", length, o);
                checkBounds(length, index);
                out.format("    call void @j_bastore(%%jobject* %s, i32 %s, i32 %s)\n", o, index, value);
                break;
            }
            case Opcodes.CALOAD: {
                Var index = pop("index");
                Var o = pop("o");
                Var res = tmpi("res");
                checkNull(o);
                Var length = tmpi("length");
                out.format("    %s = call i32 @j_arraylength(%%jobject* %s)\n", length, o);
                checkBounds(length, index);
                out.format("    %s = call i32 @j_caload(%%jobject* %s, i32 %s)\n", res, o, index);
                push(res);
                break;
            }
            case Opcodes.CASTORE: {
                Var value = pop("value");
                Var index = pop("index");
                Var o = pop("o");
                checkNull(o);
                Var length = tmpi("length");
                out.format("    %s = call i32 @j_arraylength(%%jobject* %s)\n", length, o);
                checkBounds(length, index);
                out.format("    call void @j_castore(%%jobject* %s, i32 %s, i32 %s)\n", o, index, value);
                break;
            }
            case Opcodes.SALOAD: {
                Var index = pop("index");
                Var o = pop("o");
                Var res = tmpi("res");
                checkNull(o);
                Var length = tmpi("length");
                out.format("    %s = call i32 @j_arraylength(%%jobject* %s)\n", length, o);
                checkBounds(length, index);
                out.format("    %s = call i32 @j_saload(%%jobject* %s, i32 %s)\n", res, o, index);
                push(res);
                break;
            }
            case Opcodes.SASTORE: {
                Var value = pop("value");
                Var index = pop("index");
                Var o = pop("o");
                checkNull(o);
                Var length = tmpi("length");
                out.format("    %s = call i32 @j_arraylength(%%jobject* %s)\n", length, o);
                checkBounds(length, index);
                out.format("    call void @j_sastore(%%jobject* %s, i32 %s, i32 %s)\n", o, index, value);
                break;
            }
            case Opcodes.LALOAD: {
                Var index = pop("index");
                Var o = pop("o");
                Var res = tmpl("res");
                checkNull(o);
                Var length = tmpi("length");
                out.format("    %s = call i32 @j_arraylength(%%jobject* %s)\n", length, o);
                checkBounds(length, index);
                out.format("    %s = call i64 @j_laload(%%jobject* %s, i32 %s)\n", res, o, index);
                push2(res);
                break;
            }
            case Opcodes.LASTORE: {
                Var value = pop2("value");
                Var index = pop("index");
                Var o = pop("o");
                checkNull(o);
                Var length = tmpi("length");
                out.format("    %s = call i32 @j_arraylength(%%jobject* %s)\n", length, o);
                checkBounds(length, index);
                out.format("    call void @j_lastore(%%jobject* %s, i32 %s, i64 %s)\n", o, index, value);
                break;
            }
            case Opcodes.DALOAD: {
                Var index = pop("index");
                Var o = pop("o");
                Var res = tmpd("res");
                checkNull(o);
                Var length = tmpi("length");
                out.format("    %s = call i32 @j_arraylength(%%jobject* %s)\n", length, o);
                checkBounds(length, index);
                out.format("    %s = call double @j_daload(%%jobject* %s, i32 %s)\n", res, o, index);
                push2(res);
                break;
            }
            case Opcodes.DASTORE: {
                Var value = pop2("value");
                Var index = pop("index");
                Var o = pop("o");
                checkNull(o);
                Var length = tmpi("length");
                out.format("    %s = call i32 @j_arraylength(%%jobject* %s)\n", length, o);
                checkBounds(length, index);
                out.format("    call void @j_dastore(%%jobject* %s, i32 %s, double %s)\n", o, index, value);
                break;
            }
            case Opcodes.AALOAD: {
                Var index = pop("index");
                Var o = pop("o");
                Var res = tmpr("res");
                checkNull(o);
                Var length = tmpi("length");
                out.format("    %s = call i32 @j_arraylength(%%jobject* %s)\n", length, o);
                checkBounds(length, index);
                out.format("    %s = call %%jobject* @j_aaload(%%jobject* %s, i32 %s)\n", res, o, index);
                push(res);
                break;
            }
            case Opcodes.AASTORE: {
                // TODO: throw ArrayStoreException if value is not compatible with type of array
                Var value = pop("value");
                Var index = pop("index");
                Var o = pop("o");
                checkNull(o);
                Var length = tmpi("length");
                out.format("    %s = call i32 @j_arraylength(%%jobject* %s)\n", length, o);
                checkBounds(length, index);
                out.format("    call void @j_aastore(%%jobject* %s, i32 %s, %%jobject* %s)\n", o, index, value);
                break;
            }
            case Opcodes.POP:
                pop("op");
                out.println("    ; pop");
                break;
            case Opcodes.POP2:
                pop2("op");
                out.println("    ; pop2");
                break;
            case Opcodes.SWAP: {
                Var op1 = pop("op1");
                Var op2 = pop("op2");
                push(op1);
                push(op2);
                break;
            }                
            case Opcodes.DUP: {
                Var op = pop("op");
                push(op);
                push(op);
                break;
            }
            case Opcodes.DUP_X1: {
                Var op1 = pop("op1");
                Var op2 = pop("op2");
                push(op1);
                push(op2);
                push(op1);
                break;
            }
            case Opcodes.DUP_X2: {
                Var op1 = pop("op1");
                if (isSingleWordOnTopOfStack()) {
                    Var op2 = pop("op2");
                    Var op3 = pop("op3");
                    push(op1);
                    push(op3);
                    push(op2);
                    push(op1);
                } else {
                    Var op2 = pop2("op2");
                    push(op1);
                    push2(op2);
                    push(op1);
                }
                break;
            }
            case Opcodes.DUP2: {
                if (isSingleWordOnTopOfStack()) {
                    // Single slot values on stack
                    Var op1 = pop("op1");
                    Var op2 = pop("op2");
                    push(op2);
                    push(op1);
                    push(op2);
                    push(op1);
                } else {
                    Var op = pop2("op");
                    push2(op);
                    push2(op);
                }
                break;
            }
            case Opcodes.DUP2_X1: {
                if (isSingleWordOnTopOfStack()) {
                    // Single slot values on stack
                    Var op1 = pop("op1");
                    Var op2 = pop("op2");
                    Var op3 = pop("op3");
                    push(op2);
                    push(op1);
                    push(op3);
                    push(op2);
                    push(op1);
                } else {
                    Var op1 = pop2("op1");
                    Var op2 = pop("op2");
                    push2(op1);
                    push(op2);
                    push2(op1);
                }
                break;
            }
            case Opcodes.DUP2_X2: {
                if (isSingleWordOnTopOfStack()) {
                    // Single slot values on stack
                    Var op1 = pop("op1");
                    Var op2 = pop("op2");
                    if (isSingleWordOnTopOfStack()) {
                        Var op3 = pop("op3");
                        Var op4 = pop("op4");
                        push(op2);
                        push(op1);
                        push(op4);
                        push(op3);
                        push(op2);
                        push(op1);
                    } else {
                        Var op3 = pop2("op3");
                        push(op2);
                        push(op1);
                        push2(op3);
                        push(op2);
                        push(op1);
                    }
                } else {
                    Var op1 = pop2("op1");
                    if (isSingleWordOnTopOfStack()) {
                        Var op2 = pop("op2");
                        Var op3 = pop("op3");
                        push2(op1);
                        push(op3);
                        push(op2);
                        push2(op1);
                    } else {
                        Var op2 = pop2("op2");
                        push2(op1);
                        push2(op2);
                        push2(op1);
                    }
                }
                break;
            }
            case Opcodes.ATHROW: {
                Var throwable = pop("throwable");
                checkNull(throwable);
                if (currentTryCatchBlocks.isEmpty()) {
                    out.format("    call void @nvmThrow(%%jobject* %s)\n", throwable);
                    out.format("    unreachable\n");
                } else {
                    out.format("    store %s %s, %s* %s\n", throwablePtr.getType(), throwable, throwablePtr.getType(), throwablePtr);
                    out.format("    br label %%%sMatch\n", currentLandingPad.getLabel());
                }
                break;
            }
            case Opcodes.MONITORENTER: {
                Var o = pop("o");
                checkNull(o);
                out.format("    call void @j_monitorenter(%%jobject* %s)\n", o);
                break;
            }
            case Opcodes.MONITOREXIT: {
                Var o = pop("o");
                checkNull(o);
                out.format("    call void @j_monitorexit(%%jobject* %s)\n", o);
                break;
            }
            default:
                throw new RuntimeException("Opcode not implemented: " + opcodeNames[opcode]);
            }
        }
        @Override
        public void visitIntInsn(int opcode, int operand) {
            switch (opcode) {
            case Opcodes.BIPUSH:
            case Opcodes.SIPUSH: {
                Var res = tmpi("res");
                out.format("    %s = bitcast i32 %d to i32\n", res, operand);
                push(res);
                break;
            }
            case Opcodes.NEWARRAY: {
                // TODO: Throw NegativeArraySizeException if count < 0
                Var length = pop("length");
                Var res = tmpr("res");
                if (currentTryCatchBlocks.isEmpty()) {
                    out.format("    %s = call %%jobject* @nvmNewArray(i32 %d, i32 %s)\n", 
                            res, operand, length);
                } else {
                    String successLabel = String.format("NewArraySuccess%d", pc);
                    out.format("    %s = invoke %%jobject* @nvmNewArray(i32 %d, i32 %s) to label %%%s unwind label %%%s\n", 
                            res, operand, length, successLabel, currentLandingPad.getLabel());
                    out.format("%s:\n", successLabel);
                }
                push(res);
                break;
            }
            default:
                throw new RuntimeException("Opcode not implemented: " + opcodeNames[opcode]);
            }
        }
        @Override
        public void visitJumpInsn(int opcode, Label label) {
            if (opcode >= Opcodes.IFEQ && opcode <= Opcodes.IFLE) {
                String operator = (new String[] {"eq", "ne", "slt", "sge", "sgt", "sle"})[opcode - Opcodes.IFEQ];
                Var op = pop("op");
                Var cond = tmp("cond", "i1");
                String falseLabel = cond.getName() + "False";
                out.format("    %s = icmp %s i32 %s, 0\n", cond, operator, op);
                out.format("    br i1 %s, label %%%s, label %%%s\n", cond, label, falseLabel);
                out.format("%s:\n", falseLabel);
            } else if (opcode >= Opcodes.IF_ICMPEQ && opcode <= Opcodes.IF_ICMPLE) {
                String operator = (new String[] {"eq", "ne", "slt", "sge", "sgt", "sle"})[opcode - Opcodes.IF_ICMPEQ];
                Var op1 = pop("op1");
                Var op2 = pop("op2");
                Var cond = tmp("cond", "i1");
                String falseLabel = cond.getName() + "False";
                out.format("    %s = icmp %s i32 %s, %s\n", cond, operator, op2, op1);
                out.format("    br i1 %s, label %%%s, label %%%s\n", cond, label, falseLabel);
                out.format("%s:\n", falseLabel);
            } else if (opcode >= Opcodes.IF_ACMPEQ && opcode <= Opcodes.IF_ACMPNE) {
                String operator = (new String[] {"eq", "ne"})[opcode - Opcodes.IF_ACMPEQ];
                Var op1 = pop("op1");
                Var op2 = pop("op2");
                Var cond = tmp("cond", "i1");
                String falseLabel = cond.getName() + "False";
                out.format("    %s = icmp %s %%jobject* %s, %s\n", cond, operator, op2, op1);
                out.format("    br i1 %s, label %%%s, label %%%s\n", cond, label, falseLabel);
                out.format("%s:\n", falseLabel);
            } else if (opcode >= Opcodes.IFNULL && opcode <= Opcodes.IFNONNULL) {
                String operator = (new String[] {"eq", "ne"})[opcode - Opcodes.IFNULL];
                Var op = pop("op");
                Var cond = tmp("cond", "i1");
                String falseLabel = cond.getName() + "False";
                out.format("    %s = icmp %s %%jobject* %s, inttoptr (i32 0 to %%jobject*)\n", cond, operator, op);
                out.format("    br i1 %s, label %%%s, label %%%s\n", cond, label, falseLabel);
                out.format("%s:\n", falseLabel);
            } else if (opcode == Opcodes.GOTO) {
                out.format("    br label %%%s\n", label.toString());
//                out.format("    unreachable\n", label.toString());
//            } else if (opcode == Opcodes.JSR) {
//                String retLabel = tmp(label.toString(), "").getName() + "Return";
//                Var ret = tmp("ret", "i8*");
//                out.format("    %s = bitcast i8* blockaddress(@%s, %%%s) to i8*\n", ret, LlvmUtil.mangleMethod(classNode, methodNode), retLabel);
//                push(ret);
//                out.format("    br label %%%s\n", label.toString());
//                out.format("%s:\n", retLabel);
            } else {
                throw new RuntimeException("Opcode not implemented: " + opcodeNames[opcode]);
            }
        }
        @Override
        public void visitLdcInsn(Object cst) {
            if (cst instanceof Integer) {
                Var res = tmpi("res");
                out.format("    %s = bitcast i32 %d to i32\n", res, (Integer) cst);
                push(res);
            } else if (cst instanceof Long) {
                Var res = tmpl("res");
                out.format("    %s = bitcast i64 %d to i64\n", res, (Long) cst);
                push2(res);
            } else if (cst instanceof Float) {
                Var res = tmpf("res");
                int bits = Float.floatToIntBits((Float) cst);
                out.format("    %s = bitcast i32 %d to float\n", res, bits);
                push(res);
            } else if (cst instanceof Double) {
                Var res = tmpd("res");
                long bits = Double.doubleToLongBits((Double) cst);
                out.format("    %s = bitcast i64 %d to double\n", res, bits);
                push2(res);
            } else if (cst instanceof Type) {
                Type t = (Type) cst;
                Var res = tmpr("res");
                out.format("    %s = call %%jobject* @j_ldc_class(i8* %s)\n", res,
                        LlvmUtil.getStringReference(t.getDescriptor()));
                push(res);
            } else if (cst instanceof String) {
                // TODO: Unicode
                Var res = tmpr("res");
                out.format("    %s = call %%jobject* @j_ldc_string_asciiz(i8* %s)\n", res,
                        LlvmUtil.getStringReference((String) cst));
                push(res);
            } else {
                throw new RuntimeException("Unsupported type for LDC: " + cst.getClass());
            }
        }
        @Override
        public void visitLabel(Label label) {
            if (labels.contains(label)) {
                out.format("    br label %%%s\n", label.toString());
                out.format("%s:\n", label.toString());
                
//
//                // Pop all exception handlers which end at this label
//                while (!currentTryCatchBlocks.isEmpty() && currentTryCatchBlocks.peek().end.getLabel().equals(label)) {
//                    currentTryCatchBlocks.pop();
//                }
//                
//                // Get the types of exceptions handled at this label
//                List<String> types = new ArrayList<String>();
//                for (TryCatchBlockNode n : (List<TryCatchBlockNode>) methodNode.tryCatchBlocks) {
//                    if (n.handler.getLabel().equals(label) && !types.contains(n.type)) {
//                        types.add(n.type);
//                    }
//                }
//
//                if (!types.isEmpty()) {
//                    out.format("%sLandingPad:\n", label);
//                    
//                    Var ehptr = tmp("ehptr", "i8*");
//                    out.format("    %s = call i8* @llvm.eh.exception()\n", ehptr);
//                    Var throwable = tmpr("throwable");
//                    out.format("    %s = call %%jobject* @j_get_throwable(i8* %s)\n", throwable, ehptr);
//                    
//                    Var sel = tmp("sel", "i64");
//                    out.format("    %s = call i64 (i8*, i8*, ...)* @llvm.eh.selector.i64(i8* %s, " 
//                            + "i8* bitcast (i32 (i32, i32, i64, i8*, i8*)* @j_eh_personality to i8*), i32 0)\n",
//                            sel, ehptr);
//
//                    push(throwable);
//                    
//                    out.format("    br label %%%sFindMatch\n", label);
//                    out.format("%sMatch:\n", label);
//                    for (String type : types) {
//                        if (type != null) {
//                            Var condi32 = tmpi("cond");
//                            Var cond = tmp("cond", "i1");
//                            Var clazz = tmp("clazz", "%jclass*");
//                            out.format("    %s = load %%jclass** @\"%s_%%jclass*\"\n", clazz, LlvmUtil.mangleString(type));
//                            throwable = pop("throwable");
//                            out.format("    %s = call i32 @j_eh_match_throwable(%%jobject* %s, %%jclass* %s)\n", condi32, throwable, clazz);
//                            push(throwable);
//                            out.format("    %s = trunc i32 %s to i1\n", cond, condi32);
//                            out.format("    br i1 %s, label %%%s, label %%%sNot%s\n", cond, label, label, LlvmUtil.mangleString(type));
//                            out.format("%sNot%s:\n", label, LlvmUtil.mangleString(type));
//                        } else {
//                            out.format("    br label %%%s\n", label);
//                        }
//                    }
//
//                    // If we end up here none of the types match. 
//                    if (!currentTryCatchBlocks.isEmpty()) {
//                        // If there's an enclosing exception handler we jump to that.
//                        TryCatchBlockNode next = currentTryCatchBlocks.peek();
//                        out.format("    br label %%%sFindMatch\n", next.handler.getLabel());
//                    } else {
//                        // Otherwise we rethrow the exception
//                        throwable = pop("throwable");
//                        out.format("    call void @j_throw(%%jobject* %s)\n", throwable);
////                        out.format("    call void @j_eh_resume_unwind(i8* %s)\n", ehptr);
//                        out.format("    unreachable\n");
//                    }
//                }
//
//                // Push all exception handlers which start at this label
//                for (ListIterator<TryCatchBlockNode> it = methodNode.tryCatchBlocks.listIterator(methodNode.tryCatchBlocks.size()); it.hasPrevious();) {
//                    TryCatchBlockNode n = it.previous();
//                    if (n.start.getLabel().equals(label)) {
//                        currentTryCatchBlocks.push(n);
//                    }
//                }
////
////                
////                ArrayList<TryCatchBlockNode> tryCatchNodes = new ArrayList<TryCatchBlockNode>(methodNode.tryCatchBlocks);
////                Collections.reverse(tryCatchNodes);
////                
////                // Pop all exception handlers which end at this label
////                while (!exceptionHandlers.isEmpty() && exceptionHandlers.peek().end.getLabel().equals(label)) {
////                    exceptionHandlerStacks.put(exceptionHandlers.peek().handler.getLabel(), (Stack<TryCatchBlockNode>) exceptionHandlers.clone());
////                    exceptionHandlers.pop();
////                }
////                
////                // Push all exception handlers which start at this label
////                for (ListIterator<TryCatchBlockNode> it = methodNode.tryCatchBlocks.listIterator(methodNode.tryCatchBlocks.size() - 1); it.hasPrevious();) {
////                    TryCatchBlockNode n = it.previous();
////                    if (n.start.getLabel().equals(label)) {
////                        exceptionHandlers.push(n);
////                    }
////                }
////                
////                // emit code for the handler(s) which point to this label
////                for (TryCatchBlockNode n : tryCatchNodes) {
////                    if (n.handler.getLabel().equals(label)) {
////                        out.format("%sLandingPad:\n", label.toString());
////                        Stack<TryCatchBlockNode> handlers = exceptionHandlerStacks.get(label);
//////                        List<String> classes = new ArrayList<String>();
//////                        for (int i = handlers.size() - 1; i >= 0; i--) {
//////                            TryCatchBlockNode n2 = handlers.get(i);
//////                            if (n2.type != null) {
//////                                classes.add(String.format("%%jclass** @\"%s_%%jclass*\"", LlvmUtil.mangleString(n2.type)));
//////                            } else {
//////                                // Finally
//////                                classes.add("i32 0");
//////                            }
//////                        }
////                        Var ehptr = tmp("ehptr", "i8*");
////                        out.format("    %s = call i8* @llvm.eh.exception()\n", ehptr);
////                        Var throwable = tmpr("throwable");
////                        out.format("    %s = call %%jobject* @j_get_throwable(i8* %s)\n", throwable, ehptr);
////                        
////                        Var sel = tmp("sel", "i64");
////                        out.format("    %s = call i64 (i8*, i8*, ...)* @llvm.eh.selector.i64(i8* %s, " 
////                                + "i8* bitcast (i32 (i32, i32, i64, i8*, i8*)* @j_eh_personality to i8*), i32 0)\n",
////                                sel, ehptr);
////                        
////                        push(throwable);
////                        //out.format("    switch i64 %s, label %%%sDefault [\n", sel, label);
////                        for (int i = handlers.size() - 1; i >= 0; i--) {
////                            TryCatchBlockNode n2 = handlers.get(i);
////                            if (n2.type != null) {
////                                Var condi32 = tmpi("cond");
////                                Var cond = tmp("cond", "i1");
////                                Var clazz = tmp("clazz", "%jclass*");
////                                out.format("    %s = load %%jclass** @\"%s_%%jclass*\"", clazz, LlvmUtil.mangleString(n2.type));
////                                out.format("    %s = call i32 @j_eh_match_throwable(%%jobject* %s, %%jclass* %s)\n", condi32, throwable, clazz);
////                                out.format("    %s = trunc i32 %s to i1\n", cond, condi32);
////                                out.format("    br i1 %s, label %%%s, label %%%sFalse\n", cond, n2.handler.getLabel(), n2.handler.getLabel());
////                                //out.format("        i64 %d, label %%%s\n", i + 1, n2.handler.getLabel());
////                                out.format("%sFalse:\n", n2.handler.getLabel());
////                            } else {
////                                out.format("    br label %%%s\n", n2.handler.getLabel());
////                            }
////                        }
//////                        out.format("    ]\n");
////                        
//////                        out.format("%sDefault:\n", label.toString());
////                        pop("throwable");
////                        out.format("    call void @j_eh_resume_unwind(i8* %s)\n", ehptr);
////                        out.format("    unreachable\n");
////                    }
////                }
//                
//                out.format("%s:\n", label.toString());
            }
        }
        @Override
        public void visitMethodInsn(int opcode, String owner, String name, String desc) {
            Type[] args = Type.getArgumentTypes(desc);
            ArrayUtils.reverse(args);
            LinkedList<Var> argVars = new LinkedList<Var>();
            int n = args.length;
            for (Type arg : args) {
                Var p = null;
                if (arg.getSize() == 2) {
                    p = pop2("p" + (--n));
                } else {
                    p = pop("p" + (--n));
                }
                if (arg.getSort() == Type.BOOLEAN || arg.getSort() == Type.BYTE) {
                    Var tmp = tmp(p.getName(), "i8");
                    out.format("    %s = trunc i32 %s to i8\n", tmp, p);
                    p = tmp;
                } else if (arg.getSort() == Type.SHORT || arg.getSort() == Type.CHAR) {
                    Var tmp = tmp(p.getName(), "i16");
                    out.format("    %s = trunc i32 %s to i16\n", tmp, p);
                    p = tmp;
                }
                argVars.addFirst(p);
            }
            
            if (opcode != Opcodes.INVOKESTATIC) {
                Var obj = pop("obj");
                checkNull(obj);
                argVars.addFirst(obj);
            }

            String method = null;
            if (owner.equals(classNode.name)) {
                MethodNode mnode = LlvmUtil.findMethodNode(classNode, name, desc);
                if (mnode != null && ((classNode.access & Opcodes.ACC_STATIC) > 0 || "<init>".equals(mnode.name) || (mnode.access & Opcodes.ACC_PRIVATE) > 0 || (mnode.access & Opcodes.ACC_FINAL) > 0 || (opcode == Opcodes.INVOKESTATIC && (mnode.access & Opcodes.ACC_STATIC) > 0))) {
                    // Constructors as well as private, final and static methods of the current class will be called directly
                    method = "@" + LlvmUtil.mangleMethod(owner, name, desc);
                }
            }

            if (method == null) {
                String function = opcodeNames[opcode] + "_" + LlvmUtil.mangleMethod(owner, name, desc);
                Var v = tmp(function, LlvmUtil.functionType(desc, opcode == Opcodes.INVOKESTATIC));
                out.format("    %s = load %s** @%s\n", v, v.getType(), function);
                method = v.toString();
            }
            
            String successLabel = String.format("InvokeSuccess%d", pc);
            
            Type returnType = Type.getReturnType(desc);
            if (returnType.getSort() == Type.VOID) {
                if (currentTryCatchBlocks.isEmpty()) {
                    out.format("    call void %s(%s)\n", method, LlvmUtil.varListToParameterList(argVars));
                } else {
                    out.format("    invoke void %s(%s) to label %%%s unwind label %%%s\n", method, LlvmUtil.varListToParameterList(argVars), successLabel, currentLandingPad.getLabel());
                    out.format("%s:\n", successLabel);
                }
            } else {
                String llvmReturnType = LlvmUtil.javaTypeToLlvmType(returnType);
                Var res = tmp("res", llvmReturnType);
                if (currentTryCatchBlocks.isEmpty()) {
                    out.format("    %s = call %s %s(%s)\n", res, llvmReturnType, method, LlvmUtil.varListToParameterList(argVars));
                } else {
                    out.format("    %s = invoke %s %s(%s) to label %%%s unwind label %%%s\n", res, llvmReturnType, method, LlvmUtil.varListToParameterList(argVars), successLabel, currentLandingPad.getLabel());
                    out.format("%s:\n", successLabel);
                }
                if (returnType.getSort() == Type.BOOLEAN || returnType.getSort() == Type.CHAR) {
                    Var tmp = tmpi("res");
                    out.format("    %s = zext %s %s to i32\n", tmp, res.getType(), res);
                    res = tmp;
                } else if (returnType.getSort() == Type.BYTE || returnType.getSort() == Type.SHORT) {
                    Var tmp = tmpi("res");
                    out.format("    %s = sext %s %s to i32\n", tmp, res.getType(), res);
                    res = tmp;
                }
                if (returnType.getSize() == 2) {
                    push2(res);
                } else {
                    push(res);
                }
            }
        }
        
        @Override
        public void visitMultiANewArrayInsn(String desc, int dims) {
            for (int i = 0; i < dims; i++) {
                Var ptr = tmp("ptr", "i32*");
                Var length = pop("length" + (dims - i - 1));
                out.format("    %s = getelementptr %s* %s, i32 0, i32 %d\n", ptr, multiANewArrayLengths.getType(), multiANewArrayLengths, dims - i - 1);
                out.format("    store i32 %s, i32* %s\n", length, ptr);
            }
            
            Var lengthsi32 = tmp("lengths", "i32*");
            out.format("    %s = bitcast %s* %s to i32*\n", lengthsi32, multiANewArrayLengths.getType(), multiANewArrayLengths);
            Var res = tmpr("res");
            if (currentTryCatchBlocks.isEmpty()) {
                out.format("    %s = call %%jobject* @nvmMultiANewArray(i8* %s, i32 %d, i32* %s)\n", 
                        res, LlvmUtil.getStringReference(desc), dims, lengthsi32);
            } else {
                String successLabel = String.format("NewArraySuccess%d", pc);
                out.format("    %s = invoke %%jobject* @nvmMultiANewArray(i8* %s, i32 %d, i32* %s) to label %%%s unwind label %%%s\n", 
                        res, LlvmUtil.getStringReference(desc), dims, lengthsi32, successLabel, currentLandingPad.getLabel());
                out.format("%s:\n", successLabel);
            }
            push(res);
          
        }
        @Override
        public void visitLookupSwitchInsn(Label dflt, int[] keys, Label[] labels) {
            Var op = pop("op");
            out.format("    switch i32 %s, label %%%s [\n", op, dflt);
            for (int i = 0; i < keys.length; i++) {
                out.format("        i32 %d, label %%%s\n", keys[i], labels[i]);
            }
            out.format("    ]\n");
        }
        @Override
        public void visitTableSwitchInsn(int min, int max, Label dflt, Label[] labels) {
            Var op = pop("op");
            out.format("    switch i32 %s, label %%%s [\n", op, dflt);
            for (int i = min; i <= max; i++) {
                out.format("        i32 %d, label %%%s\n", i, labels[i - min]);
            }
            out.format("    ]\n");
        }
        @Override
        public void visitTypeInsn(int opcode, String type) {
            switch (opcode) {
            case Opcodes.NEW: {
                Var clazz = tmp("clazz", "%jclass*");
                out.format("    %s = call %%jclass* @nvmGetClass(i8* %s, i8* %s, %%jclass* null)\n", clazz, 
                        LlvmUtil.getStringReference(type), 
                        LlvmUtil.getStringReference(LlvmUtil.mangleString(type)));
                Var res = tmpr("res");
                out.format("    %s = call %%jobject* @nvmNewInstance(%%jclass* %s)\n", res, clazz);
                push(res);
                break;
            }
            case Opcodes.ANEWARRAY: {
                Var length = pop("length");
                Var res = tmpr("res");
                if (currentTryCatchBlocks.isEmpty()) {
                    out.format("    %s = call %%jobject* @nvmANewArray(i8* %s, i32 %s)\n", 
                            res, LlvmUtil.getStringReference(type), length);
                } else {
                    String successLabel = String.format("ANewArraySuccess%d", pc);
                    out.format("    %s = invoke %%jobject* @nvmANewArray(i8* %s, i32 %s) to label %%%s unwind label %%%s\n", 
                            res, LlvmUtil.getStringReference(type), length, successLabel, currentLandingPad.getLabel());
                    out.format("%s:\n", successLabel);
                }
                push(res);
                break;
            }
            case Opcodes.CHECKCAST: {
                Var obj = pop("obj");
                String function = "CHECKCAST_" + LlvmUtil.mangleString(type);
                Var v = tmp(function, "void (%jobject*)");
                out.format("    %s = load %s** @%s\n", v, v.getType(), function);
                if (currentTryCatchBlocks.isEmpty()) {
                    out.format("    call void %s(%%jobject* %s)\n", v, obj);
                } else {
                    String successLabel = String.format("CheckCastSuccess%d", pc);
                    out.format("    invoke void %s(%%jobject* %s) to label %%%s unwind label %%%s\n", v, obj, successLabel, currentLandingPad.getLabel());
                    out.format("%s:\n", successLabel);
                }
                push(obj);
                break;
            }
            case Opcodes.INSTANCEOF: {
                Var obj = pop("obj");
                String function = "INSTANCEOF_" + LlvmUtil.mangleString(type);
                Var v = tmp(function, "i32 (%jobject*)");
                out.format("    %s = load %s** @%s\n", v, v.getType(), function);
                Var res = tmpi("res");
                if (currentTryCatchBlocks.isEmpty()) {
                    out.format("    %s = call i32 %s(%%jobject* %s)\n", res, v, obj);
                } else {
                    String successLabel = String.format("InstanceOfSuccess%d", pc);
                    out.format("    %s = invoke i32 %s(%%jobject* %s) to label %%%s unwind label %%%s\n", res, v, obj, successLabel, currentLandingPad.getLabel());
                    out.format("%s:\n", successLabel);
                }
                push(res);
                break;
            }
            default:
                throw new RuntimeException("Opcode not implemented: " + opcodeNames[opcode]);
            }
        }
        @Override
        public void visitIincInsn(int var, int increment) {
            Var op = loadi(var);
            Var res = tmpi("res");
            out.format("    %s = add i32 %d, %s\n", res, increment, op);
            store(res, var);
        }
        @Override
        public void visitVarInsn(int opcode, int var) {
            switch (opcode) {
            case Opcodes.ALOAD:
                push(loadr(var));
                break;
            case Opcodes.ASTORE:
                store(pop("op"), var);
                break;
            case Opcodes.ILOAD:
                push(loadi(var));
                break;
            case Opcodes.ISTORE:
                store(pop("op"), var);
                break;
            case Opcodes.LLOAD:
                push2(loadl(var));
                break;
            case Opcodes.LSTORE:
                store(pop2("op"), var);
                break;
            case Opcodes.FLOAD:
                push(loadf(var));
                break;
            case Opcodes.FSTORE:
                store(pop("op"), var);
                break;
            case Opcodes.DLOAD:
                push2(loadd(var));
                break;
            case Opcodes.DSTORE:
                store(pop2("op"), var);
                break;
            case Opcodes.RET:
                Var ret = load("i8*", var);
                out.format("    indirectbr i8* %s\n", ret);
                break;
            default:
                throw new RuntimeException("Opcode not implemented: " + opcodeNames[opcode]);
            }
        }
       
 
    }
    
    public static class LandingPad {

        List<LandingPadHandler> handlers = new ArrayList<LandingPadHandler>();
        
        public LandingPad(List<TryCatchBlockNode> exceptionHandlers, MethodNode methodNode, Frame[] frames) {
            Set<String> types = new HashSet<String>();
            for (TryCatchBlockNode node : exceptionHandlers) {
                if (!types.contains(node.type)) {
                    int handlerIndex = methodNode.instructions.indexOf(node.handler);
                    handlers.add(new LandingPadHandler(node.type, node.handler.getLabel(), frames[handlerIndex]));
                    types.add(node.type);
                }
            }
        }
        
        public String getLabel() {
            StringBuilder sb = new StringBuilder();
            for (LandingPadHandler h : handlers) {
                sb.append(h.getLabel().toString());
            }
            sb.append("LandingPad");
            return sb.toString();
        }
        
        public List<LandingPadHandler> getHandlers() {
            return handlers;
        }

        @Override
        public int hashCode() {
            final int prime = 31;
            int result = 1;
            result = prime * result
                    + ((handlers == null) ? 0 : handlers.hashCode());
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
            LandingPad other = (LandingPad) obj;
            if (handlers == null) {
                if (other.handlers != null) {
                    return false;
                }
            } else if (!handlers.equals(other.handlers)) {
                return false;
            }
            return true;
        }
    }
    
    public static class LandingPadHandler {
        private final String type;
        private final Label label;
        private final Frame frame;
        public LandingPadHandler(String type, Label label, Frame frame) {
            this.type = type;
            this.label = label;
            this.frame = frame;
        }
        public String getType() {
            return type;
        }
        public Label getLabel() {
            return label;
        }
        public Frame getFrame() {
            return frame;
        }
        @Override
        public int hashCode() {
            final int prime = 31;
            int result = 1;
            result = prime * result + ((label == null) ? 0 : label.hashCode());
            result = prime * result + ((type == null) ? 0 : type.hashCode());
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
            LandingPadHandler other = (LandingPadHandler) obj;
            if (label == null) {
                if (other.label != null) {
                    return false;
                }
            } else if (!label.equals(other.label)) {
                return false;
            }
            if (type == null) {
                if (other.type != null) {
                    return false;
                }
            } else if (!type.equals(other.type)) {
                return false;
            }
            return true;
        }
    }
}
