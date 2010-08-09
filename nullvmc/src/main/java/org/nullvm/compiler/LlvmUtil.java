package org.nullvm.compiler;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import org.objectweb.asm.Opcodes;
import org.objectweb.asm.Type;
import org.objectweb.asm.tree.AnnotationNode;
import org.objectweb.asm.tree.ClassNode;
import org.objectweb.asm.tree.LabelNode;
import org.objectweb.asm.tree.LocalVariableNode;
import org.objectweb.asm.tree.MethodNode;
import org.objectweb.asm.tree.analysis.BasicValue;

public class LlvmUtil {

    public static String join(List<String> l) {
        return join(l, ", ");
    }
    
    public static String join(List<String> l, String separator) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < l.size(); i++) {
            sb.append(l.get(i));
            if (i < l.size() - 1) {
                sb.append(separator);
            }
        }
        return sb.toString();
    }
    
    public static String getStringType(String s) {
        try {
            return String.format("[%d x i8]", s.getBytes("UTF8").length + 1);
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException(e);
        }
    }
    
    public static String getStringVar(String s) {
        // Seems like @ is copied as is to the assembler file which produces an error
        // This is a work around
        return "@\"'" + escapeString(s, true) + "'\"";
    }
    
    public static String getStringValue(String s) {
        return "c\"" + escapeString(s, false) + "\\00\"";
    }
    
    public static String escapeString(String s, boolean var) {
        try {
            byte[] bytes = s.getBytes("UTF8");
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < bytes.length; i++) {
                byte b = bytes[i];
                if (b < ' ' || b > '~' || b == '"' || b == '\\' || b == '%' || b == '@') {
                    if (!var) {
                        sb.append(String.format("\\%02X", b));
                    } else {
                        sb.append(String.format("%%%02X", b));
                    }
                } else {
                    sb.append((char) b);
                }
            }
            return sb.toString();
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException(e);
        }
    }
    
    public static String getStringReference(String s) {
        return String.format("getelementptr (%s* %s, i32 0, i32 0)", getStringType(s), getStringVar(s));
    }
    
//    public static int getSize(Type t) {
//        if (t.getSort() == Type.OBJECT || t.getSort() == Type.ARRAY) {
//            return "getelementptr i32* null i32 1";
//        }
//        // Native type
//        switch (t.getSort()) {
//        case Type.BOOLEAN:
//            return "i1";
//        case Type.BYTE:
//            return "i8";
//        case Type.CHAR:
//            return "i16";
//        case Type.DOUBLE:
//            return "double";
//        case Type.FLOAT:
//            return "float";
//        case Type.INT:
//            return "i32";
//        case Type.LONG:
//            return "i64";
//        case Type.SHORT:
//            return "i16";
//        }
//        return "void";
//    }
    
    public static String javaTypeToLlvmType(Type t) {
        if (t.getSort() == Type.OBJECT || t.getSort() == Type.ARRAY) {
            return "%jobject*";
        }
        // Native type
        switch (t.getSort()) {
        case Type.BOOLEAN:
            return "i8";
        case Type.BYTE:
            return "i8";
        case Type.CHAR:
            return "i16";
        case Type.DOUBLE:
            return "double";
        case Type.FLOAT:
            return "float";
        case Type.INT:
            return "i32";
        case Type.LONG:
            return "i64";
        case Type.SHORT:
            return "i16";
        }
        return "void";
    }
    
    public static String javaLocalVarTypeToLlvmType(Type t) {
        if (t.getSort() == Type.OBJECT || t.getSort() == Type.ARRAY) {
            return "%jobject*";
        }
        // Native type
        switch (t.getSort()) {
        case Type.BOOLEAN:
        case Type.BYTE:
        case Type.CHAR:
        case Type.SHORT:
        case Type.INT:
            return "i32";
        case Type.DOUBLE:
            return "double";
        case Type.FLOAT:
            return "float";
        case Type.LONG:
            return "i64";
        }
        return "void";
    }
    
    public static String javaTypeToLlvmType(BasicValue value) {
        if (value == BasicValue.UNINITIALIZED_VALUE || value == BasicValue.REFERENCE_VALUE) {
            return "%jobject*";
        }
        if (value == BasicValue.RETURNADDRESS_VALUE) {
            return "i8*";
        }
        // Native type
        switch (value.getType().getSort()) {
        case Type.BOOLEAN:
        case Type.BYTE:
        case Type.CHAR:
        case Type.SHORT:
        case Type.INT:
            return "i32";
        case Type.DOUBLE:
            return "double";
        case Type.FLOAT:
            return "float";
        case Type.LONG:
            return "i64";
        }
        return "void";
    }
    
    public static String javaMethodToObjCFunctionPointer(String desc, boolean ztatic, String varName) {
        String returnType = javaTypeToLlvmType(Type.getReturnType(desc));
        List<String> args = new ArrayList<String>();
        if (ztatic) {
            args.add("Class");
        } else {
            args.add("JObject*");
        }
        args.add("SEL");
        int i = ztatic ? 0 : 1;
        for (Type arg : Type.getArgumentTypes(desc)) {
            args.add(javaTypeToLlvmType(arg));
            i++;
        }
        return String.format("%s (*%s)(%s)", returnType, varName, join(args));
    }
    

    public static String mangleMethod(ClassNode classNode, MethodNode node) {
        return mangleMethod(classNode.name, node.name, node.desc);
    }
    
    public static String mangleMethod(String owner, String name, String desc) {
        Type[] args = Type.getArgumentTypes(desc);
        StringBuilder sb = new StringBuilder();
        sb.append("jm_");
        sb.append(mangleString(owner));
        sb.append("_");
        sb.append(mangleString(name));
        if (args.length > 0) {
            sb.append("__");
            for (Type arg : args) {
                sb.append(mangleString(arg.getDescriptor()));
            }
        }
        sb.append("__");
        sb.append(mangleString(Type.getReturnType(desc).getDescriptor()));
        return sb.toString();
    }
    
    public static String mangleNativeMethodShort(ClassNode classNode, MethodNode node) {
        return mangleNativeMethod(classNode.name, node.name, null);
    }
    
    public static String mangleNativeMethodLong(ClassNode classNode, MethodNode node) {
        return mangleNativeMethod(classNode.name, node.name, node.desc);
    }
    
    public static String mangleNativeMethod(String owner, String name, String desc) {
        Type[] args = desc != null ? Type.getArgumentTypes(desc) : new Type[0];
        StringBuilder sb = new StringBuilder();
        sb.append("Java_");
        sb.append(mangleString(owner));
        sb.append("_");
        sb.append(mangleString(name));
        if (args.length > 0) {
            sb.append("__");
            for (Type arg : args) {
                sb.append(mangleString(arg.getDescriptor()));
            }
        }
        return sb.toString();
    }
    
    public static String mangleString(String name) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < name.length(); i++) {
            char c = name.charAt(i);
            if (c == '_') {
                sb.append("_1");
            } else if (c == ';') {
                sb.append("_2");
            } else if (c == '[') {
                sb.append("_3");
            } else if (c == '/') {
                sb.append("_");
            } else if (c == '<') {
                sb.append("__");
            } else if (c == '>') {
                sb.append("__");
            } else if (c > 0x7f) {
                sb.append(String.format("_0%04x", (int) c));
            } else {
                sb.append(c);
            }
        }
        return sb.toString();
    }
    
    public static String javaMethodToLlvmFunctionDeclaration(ClassNode classNode, MethodNode methodNode) {
        return "declare " + javaMethodToLlvmFunction(mangleMethod(classNode.name, methodNode.name, methodNode.desc), methodNode.desc, (methodNode.access & Opcodes.ACC_STATIC) > 0, true);
    }
    
    public static String javaMethodToLlvmFunctionDefinition(ClassNode classNode, MethodNode methodNode) {
        return "define " + javaMethodToLlvmFunction(mangleMethod(classNode.name, methodNode.name, methodNode.desc), methodNode.desc, (methodNode.access & Opcodes.ACC_STATIC) > 0, false);
    }
    
    public static String functionDefinition(String name, String desc, boolean ztatic) {
        return javaMethodToLlvmFunction(name, desc, ztatic, false);
    }
    
    public static String functionType(String desc, boolean ztatic) {
        return javaMethodToLlvmFunction(null, desc, ztatic, true);
    }
    
    public static String nativeFunctionDefinition(String name, String desc, boolean ztatic) {
        return javaMethodToLlvmNativeFunction(name, desc, ztatic, false);
    }
    
    public static String nativeFunctionType(String desc, boolean ztatic) {
        return javaMethodToLlvmNativeFunction(null, desc, ztatic, true);
    }

    private static String javaMethodToLlvmNativeFunction(String name, String desc, boolean ztatic, boolean omitArgNames) {
        String returnType = javaTypeToLlvmType(Type.getReturnType(desc));
        List<String> args = nativeDescToCallArgs(desc, ztatic, omitArgNames);
        if (name != null) {
            return String.format("%s @%s(%s)", returnType, name, join(args));
        } else {
            return String.format("%s (%s)", returnType, join(args));
        }
    }

    
    private static String javaMethodToLlvmFunction(String name, String desc, boolean ztatic, boolean omitArgNames) {
        String returnType = javaTypeToLlvmType(Type.getReturnType(desc));
        List<String> args = descToCallArgs(desc, ztatic, omitArgNames);
        if (name != null) {
            return String.format("%s @%s(%s)", returnType, name, join(args));
        } else {
            return String.format("%s (%s)", returnType, join(args));
        }
    }

    public static List<String> nativeDescToCallArgs(String desc, boolean ztatic,
            boolean omitArgNames) {
        
        ArrayList<String> args = new ArrayList<String>();
        args.add("i8*" + (omitArgNames ? "" : " %env"));
        if (ztatic) {
            args.add("%jclass*" + (omitArgNames ? "" : " %clazz"));
        }
        args.addAll(descToCallArgs(desc, ztatic, omitArgNames));
        return args;
    }
    public static List<String> descToCallArgs(String desc, boolean ztatic,
            boolean omitArgNames) {
        
        List<String> args = new ArrayList<String>();
        if (!ztatic) {
            args.add("%jobject*" + (omitArgNames ? "" : " " + new Var("arg0", "%jobject*")));
        }
        int i = ztatic ? 0 : 1;
        for (Type arg : Type.getArgumentTypes(desc)) {
            args.add(LlvmUtil.javaTypeToLlvmType(arg) + (omitArgNames ? "" : " " + new Var("arg" + i, LlvmUtil.javaTypeToLlvmType(arg))));
            i++; // += arg.getSize();
        }
        return args;
    }
    
    public static String javaMethodToLlvmFunctionType(MethodNode methodNode) {
        return javaMethodToLlvmFunctionType(methodNode.name, methodNode.desc, (methodNode.access & Opcodes.ACC_STATIC) > 0);
    }
    
    public static String javaMethodToLlvmFunctionType(String name, String desc, boolean ztatic) {
        return javaMethodToLlvmFunction(null, desc, ztatic, true) + "*";
    }
    
    public static String varListToParameterList(List<Var> vars) {
        List<String> l = new ArrayList<String>();
        for (int i = 0; i < vars.size(); i++) {
            Var v = vars.get(i);
            l.add(v.getType() + " " + v.toString());
        }
        return join(l);
    }
    
    public static List<String> getMethodArgNames(MethodNode method) {
        if (method.localVariables != null && method.instructions.getFirst() instanceof LabelNode) {
            LabelNode first = (LabelNode) method.instructions.getFirst();
            List<LocalVariableNode> variableNodes = new ArrayList<LocalVariableNode>();
            for (LocalVariableNode n : (List<LocalVariableNode>) method.localVariables) {
                if (n.start == first) {
                    variableNodes.add(n);
                }
            }
            Collections.sort(variableNodes, new Comparator<LocalVariableNode>() {
                public int compare(LocalVariableNode o1, LocalVariableNode o2) {
                    return new Integer(o1.index).compareTo(o2.index);
                }
            });
//            if ((method.access & Opcodes.ACC_STATIC) == 0) {
//                variableNodes = variableNodes.subList(1, variableNodes.size());
//            }
            List<String> argNames = new ArrayList<String>();
            for (LocalVariableNode n : variableNodes) {
                argNames.add(n.name);
            }
            return argNames;
        }
        return new ArrayList<String>();
    }
    
    public static boolean isNative(MethodNode node) {
        if ((node.access & Opcodes.ACC_NATIVE) != 0) {
            return true;
        }
        if (node.invisibleAnnotations == null) {
            return false;
        }
        for (AnnotationNode n : (List<AnnotationNode>) node.invisibleAnnotations) {
            if (n.desc.equals(Type.getDescriptor(Native.class))) {
                return true;
            }
        }
        return false;
    }
    
    public static MethodNode findMethodNode(ClassNode classNode, String name, String desc) {
        for (MethodNode tmp : (List<MethodNode>) classNode.methods) {
            if (tmp.name.equals(name) && tmp.desc.equals(desc)) {
                return tmp;
            }
        }
        return null;
    }
}
