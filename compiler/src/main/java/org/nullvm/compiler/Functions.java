/**
 * 
 */
package org.nullvm.compiler;

import static org.nullvm.compiler.Mangler.*;
import static org.nullvm.compiler.Types.*;
import static org.nullvm.compiler.llvm.Type.*;

import java.util.List;

import org.nullvm.compiler.llvm.Argument;
import org.nullvm.compiler.llvm.BasicBlockRef;
import org.nullvm.compiler.llvm.Call;
import org.nullvm.compiler.llvm.Function;
import org.nullvm.compiler.llvm.FunctionAttribute;
import org.nullvm.compiler.llvm.FunctionRef;
import org.nullvm.compiler.llvm.FunctionType;
import org.nullvm.compiler.llvm.Invoke;
import org.nullvm.compiler.llvm.Linkage;
import org.nullvm.compiler.llvm.PointerType;
import org.nullvm.compiler.llvm.Type;
import org.nullvm.compiler.llvm.Value;
import org.nullvm.compiler.llvm.Variable;

import soot.SootClass;
import soot.SootMethod;

/**
 * @author niklas
 *
 */
public class Functions {

    public static final FunctionRef NVM_BC_INITIALIZE_CLASS = new FunctionRef("_nvmBcInitializeClass", new FunctionType(VOID, ENV_PTR, I8_PTR_PTR));
    public static final FunctionRef NVM_BC_ALLOCATE = new FunctionRef("_nvmBcAllocate", new FunctionType(OBJECT_PTR, ENV_PTR, I8_PTR_PTR));
    public static final FunctionRef NVM_BC_LDC_ARRAY_BOOT_CLASS = new FunctionRef("_nvmBcLdcArrayBootClass", new FunctionType(OBJECT_PTR, ENV_PTR, new PointerType(OBJECT_PTR), I8_PTR));
    public static final FunctionRef NVM_BC_LDC_ARRAY_CLASS = new FunctionRef("_nvmBcLdcArrayClass", new FunctionType(OBJECT_PTR, ENV_PTR, new PointerType(OBJECT_PTR), I8_PTR));
    public static final FunctionRef NVM_BC_NEW_OBJECT_ARRAY = new FunctionRef("_nvmBcNewObjectArray", new FunctionType(OBJECT_PTR, ENV_PTR, I32, OBJECT_PTR));

    public static final FunctionRef NVM_BC_PERSONALITY = new FunctionRef("_nvmBcPersonality", new FunctionType(I8_PTR));
    public static final FunctionRef NVM_BC_EXCEPTION_MATCH = new FunctionRef("_nvmBcExceptionMatch", new FunctionType(I32, ENV_PTR, CLASS_PTR));
    public static final FunctionRef NVM_BC_EXCEPTION_CLEAR = new FunctionRef("_nvmBcExceptionClear", new FunctionType(OBJECT_PTR, ENV_PTR));
    public static final FunctionRef NVM_BC_EXCEPTION_SET = new FunctionRef("_nvmBcExceptionSet", new FunctionType(VOID, ENV_PTR, OBJECT_PTR));
    public static final FunctionRef NVM_BC_THROW = new FunctionRef("_nvmBcThrow", new FunctionType(VOID, ENV_PTR, OBJECT_PTR));
    public static final FunctionRef NVM_BC_RETHROW = new FunctionRef("_nvmBcRethrow", new FunctionType(VOID, ENV_PTR));
    public static final FunctionRef NVM_BC_THROW_IF_EXCEPTION_OCCURRED = new FunctionRef("_nvmBcThrowIfExceptionOccurred", new FunctionType(VOID, ENV_PTR));
    public static final FunctionRef NVM_BC_THROW_UNSATISIFED_LINK_ERROR = new FunctionRef("_nvmBcThrowUnsatisfiedLinkError", new FunctionType(VOID, ENV_PTR));
    public static final FunctionRef NVM_BC_THROW_NO_CLASS_DEF_FOUND_ERROR = new FunctionRef("_nvmBcThrowNoClassDefFoundError", new FunctionType(VOID, ENV_PTR, I8_PTR));
    public static final FunctionRef NVM_BC_THROW_NO_SUCH_FIELD_ERROR = new FunctionRef("_nvmBcThrowNoSuchFieldError", new FunctionType(VOID, ENV_PTR, I8_PTR));
    public static final FunctionRef NVM_BC_THROW_NO_SUCH_METHOD_ERROR = new FunctionRef("_nvmBcThrowNoSuchMethodError", new FunctionType(VOID, ENV_PTR, I8_PTR));
    public static final FunctionRef NVM_BC_THROW_ILLEGAL_ACCESS_ERROR = new FunctionRef("_nvmBcThrowIllegalAccessError", new FunctionType(VOID, ENV_PTR, I8_PTR));
    public static final FunctionRef NVM_BC_THROW_INSTANTIATION_ERROR = new FunctionRef("_nvmBcThrowInstantiationError", new FunctionType(VOID, ENV_PTR, I8_PTR));
    public static final FunctionRef NVM_BC_THROW_INCOMPATIBLE_CLASS_CHANGE_ERROR = new FunctionRef("_nvmBcThrowIncompatibleClassChangeError", new FunctionType(VOID, ENV_PTR, I8_PTR));
    public static final FunctionRef NVM_BC_THROW_ABSTRACT_METHOD_ERROR = new FunctionRef("_nvmBcThrowAbstractMethodError", new FunctionType(VOID, ENV_PTR, I8_PTR));
    
    public static final FunctionRef NVM_BC_NEW_BOOLEAN_ARRAY = new FunctionRef("_nvmBcNewBooleanArray", new FunctionType(OBJECT_PTR, ENV_PTR, I32));
    public static final FunctionRef NVM_BC_NEW_BYTE_ARRAY = new FunctionRef("_nvmBcNewByteArray", new FunctionType(OBJECT_PTR, ENV_PTR, I32));
    public static final FunctionRef NVM_BC_NEW_CHAR_ARRAY = new FunctionRef("_nvmBcNewCharArray", new FunctionType(OBJECT_PTR, ENV_PTR, I32));
    public static final FunctionRef NVM_BC_NEW_SHORT_ARRAY = new FunctionRef("_nvmBcNewShortArray", new FunctionType(OBJECT_PTR, ENV_PTR, I32));
    public static final FunctionRef NVM_BC_NEW_INT_ARRAY = new FunctionRef("_nvmBcNewIntArray", new FunctionType(OBJECT_PTR, ENV_PTR, I32));
    public static final FunctionRef NVM_BC_NEW_LONG_ARRAY = new FunctionRef("_nvmBcNewLongArray", new FunctionType(OBJECT_PTR, ENV_PTR, I32));
    public static final FunctionRef NVM_BC_NEW_FLOAT_ARRAY = new FunctionRef("_nvmBcNewFloatArray", new FunctionType(OBJECT_PTR, ENV_PTR, I32));
    public static final FunctionRef NVM_BC_NEW_DOUBLE_ARRAY = new FunctionRef("_nvmBcNewDoubleArray", new FunctionType(OBJECT_PTR, ENV_PTR, I32));
    public static final FunctionRef NVM_BC_MONITOR_ENTER = new FunctionRef("_nvmBcMonitorEnter", new FunctionType(VOID, ENV_PTR, OBJECT_PTR));
    public static final FunctionRef NVM_BC_MONITOR_EXIT = new FunctionRef("_nvmBcMonitorExit", new FunctionType(VOID, ENV_PTR, OBJECT_PTR));
    public static final FunctionRef NVM_BC_LDC_STRING = new FunctionRef("_nvmBcLdcString", new FunctionType(OBJECT_PTR, ENV_PTR, I8_PTR));
    public static final FunctionRef NVM_BC_LOOKUP_VIRTUAL_METHOD = new FunctionRef("_nvmBcLookupVirtualMethod", new FunctionType(I8_PTR, ENV_PTR, OBJECT_PTR, I8_PTR, I8_PTR));
    public static final FunctionRef NVM_BC_LOOKUP_INTERFACE_METHOD = new FunctionRef("_nvmBcLookupInterfaceMethod", new FunctionType(I8_PTR, ENV_PTR, I8_PTR_PTR, OBJECT_PTR, I8_PTR, I8_PTR));
    public static final FunctionRef NVM_BC_CHECKCAST = new FunctionRef("_nvmBcCheckcast", new FunctionType(OBJECT_PTR, ENV_PTR, I8_PTR_PTR, OBJECT_PTR));
    public static final FunctionRef NVM_BC_CHECKCAST_ARRAY = new FunctionRef("_nvmBcCheckcastArray", new FunctionType(OBJECT_PTR, ENV_PTR, OBJECT_PTR, OBJECT_PTR));
    public static final FunctionRef NVM_BC_INSTANCEOF = new FunctionRef("_nvmBcInstanceof", new FunctionType(I32, ENV_PTR, I8_PTR_PTR, OBJECT_PTR));
    public static final FunctionRef NVM_BC_INSTANCEOF_ARRAY = new FunctionRef("_nvmBcInstanceofArray", new FunctionType(I32, ENV_PTR, OBJECT_PTR, OBJECT_PTR));
    public static final FunctionRef NVM_BC_NEW_MULTI_ARRAY = new FunctionRef("_nvmBcNewMultiArray", new FunctionType(OBJECT_PTR, ENV_PTR, I32, new PointerType(I32), OBJECT_PTR));
    public static final FunctionRef NVM_BC_SET_OBJECT_ARRAY_ELEMENT = new FunctionRef("_nvmBcSetObjectArrayElement", new FunctionType(VOID, ENV_PTR, OBJECT_PTR, I32, OBJECT_PTR));
    public static final FunctionRef NVM_BC_RESOLVE_NATIVE = new FunctionRef("_nvmBcResolveNative", new FunctionType(I8_PTR, ENV_PTR, OBJECT_PTR, I8_PTR, I8_PTR, I8_PTR, I8_PTR, I8_PTR, I8_PTR));
    public static final FunctionRef NVM_BC_PUSH_NATIVE_FRAME = new FunctionRef("_nvmBcPushNativeFrame", new FunctionType(VOID, ENV_PTR, I8_PTR));
    public static final FunctionRef NVM_BC_POP_NATIVE_FRAME = new FunctionRef("_nvmBcPopNativeFrame", new FunctionType(VOID, ENV_PTR));
    public static final FunctionRef NVM_BC_ATTACH_THREAD_FROM_CALLBACK = new FunctionRef("_nvmBcAttachThreadFromCallback", new FunctionType(ENV_PTR));
    public static final FunctionRef NVM_BC_DETACH_THREAD_FROM_CALLBACK = new FunctionRef("_nvmBcDetachThreadFromCallback", new FunctionType(VOID, ENV_PTR));
    public static final FunctionRef NVM_BC_NEW_STRUCT = new FunctionRef("_nvmBcNewStruct", new FunctionType(OBJECT_PTR, ENV_PTR, I8_PTR, CLASS_PTR, I8_PTR));
    public static final FunctionRef NVM_BC_GET_STRUCT_HANDLE = new FunctionRef("_nvmBcGetStructHandle", new FunctionType(I8_PTR, ENV_PTR, OBJECT_PTR));
    public static final FunctionRef NVM_BC_BY_VALUE_GET_STRUCT_HANDLE = new FunctionRef("_nvmBcByValueGetStructHandle", new FunctionType(I8_PTR, ENV_PTR, OBJECT_PTR));
    public static final FunctionRef NVM_BC_COPY_STRUCT = new FunctionRef("_nvmBcCopyStruct", new FunctionType(VOID, ENV_PTR, OBJECT_PTR, I8_PTR, I32));

    public static final FunctionRef LLVM_FRAMEADDRESS = new FunctionRef("llvm.frameaddress", new FunctionType(I8_PTR, I32));

    public static final FunctionRef CHECK_NULL = new FunctionRef("checknull", new FunctionType(VOID, ENV_PTR, OBJECT_PTR));
    public static final FunctionRef CHECK_LOWER = new FunctionRef("checklower", new FunctionType(VOID, ENV_PTR, OBJECT_PTR, I32));
    public static final FunctionRef CHECK_UPPER = new FunctionRef("checkupper", new FunctionType(VOID, ENV_PTR, OBJECT_PTR, I32));
    public static final FunctionRef ARRAY_LENGTH = new FunctionRef("arraylength", new FunctionType(I32, OBJECT_PTR));
    public static final FunctionRef BALOAD = new FunctionRef("baload", new FunctionType(I8, OBJECT_PTR, I32));
    public static final FunctionRef SALOAD = new FunctionRef("saload", new FunctionType(I16, OBJECT_PTR, I32));
    public static final FunctionRef CALOAD = new FunctionRef("caload", new FunctionType(I16, OBJECT_PTR, I32));
    public static final FunctionRef IALOAD = new FunctionRef("iaload", new FunctionType(I32, OBJECT_PTR, I32));
    public static final FunctionRef LALOAD = new FunctionRef("laload", new FunctionType(I64, OBJECT_PTR, I32));
    public static final FunctionRef FALOAD = new FunctionRef("faload", new FunctionType(FLOAT, OBJECT_PTR, I32));
    public static final FunctionRef DALOAD = new FunctionRef("daload", new FunctionType(DOUBLE, OBJECT_PTR, I32));
    public static final FunctionRef AALOAD = new FunctionRef("aaload", new FunctionType(OBJECT_PTR, OBJECT_PTR, I32));
    public static final FunctionRef BASTORE = new FunctionRef("bastore", new FunctionType(VOID, OBJECT_PTR, I32, I8));
    public static final FunctionRef SASTORE = new FunctionRef("sastore", new FunctionType(VOID, OBJECT_PTR, I32, I16));
    public static final FunctionRef CASTORE = new FunctionRef("castore", new FunctionType(VOID, OBJECT_PTR, I32, I16));
    public static final FunctionRef IASTORE = new FunctionRef("iastore", new FunctionType(VOID, OBJECT_PTR, I32, I32));
    public static final FunctionRef LASTORE = new FunctionRef("lastore", new FunctionType(VOID, OBJECT_PTR, I32, I64));
    public static final FunctionRef FASTORE = new FunctionRef("fastore", new FunctionType(VOID, OBJECT_PTR, I32, FLOAT));
    public static final FunctionRef DASTORE = new FunctionRef("dastore", new FunctionType(VOID, OBJECT_PTR, I32, DOUBLE));
    public static final FunctionRef AASTORE = new FunctionRef("aastore", new FunctionType(VOID, OBJECT_PTR, I32, OBJECT_PTR));
    public static final FunctionRef F2I = new FunctionRef("f2i", new FunctionType(I32, FLOAT));
    public static final FunctionRef F2L = new FunctionRef("f2l", new FunctionType(I64, FLOAT));
    public static final FunctionRef D2I = new FunctionRef("d2i", new FunctionType(I32, DOUBLE));
    public static final FunctionRef D2L = new FunctionRef("d2l", new FunctionType(I64, DOUBLE));
    public static final FunctionRef IDIV = new FunctionRef("idiv", new FunctionType(I32, ENV_PTR, I32, I32));
    public static final FunctionRef LDIV = new FunctionRef("ldiv", new FunctionType(I64, ENV_PTR, I64, I64));
    public static final FunctionRef IREM = new FunctionRef("irem", new FunctionType(I32, ENV_PTR, I32, I32));
    public static final FunctionRef LREM = new FunctionRef("lrem", new FunctionType(I64, ENV_PTR, I64, I64));
    public static final FunctionRef FCMPL = new FunctionRef("fcmpl", new FunctionType(I32, FLOAT, FLOAT));
    public static final FunctionRef FCMPG = new FunctionRef("fcmpg", new FunctionType(I32, FLOAT, FLOAT));
    public static final FunctionRef DCMPL = new FunctionRef("dcmpl", new FunctionType(I32, DOUBLE, DOUBLE));
    public static final FunctionRef DCMPG = new FunctionRef("dcmpg", new FunctionType(I32, DOUBLE, DOUBLE));

    public static FunctionRef getArrayLoad(soot.Type sootType) {
        if (sootType.equals(soot.BooleanType.v())) {
            return BALOAD;
        } else if (sootType.equals(soot.ByteType.v())) {
            return BALOAD;
        } else if (sootType.equals(soot.ShortType.v())) {
            return SALOAD;
        } else if (sootType.equals(soot.CharType.v())) {
            return CALOAD;
        } else if (sootType.equals(soot.IntType.v())) {
            return IALOAD;
        } else if (sootType.equals(soot.LongType.v())) {
            return LALOAD;
        } else if (sootType.equals(soot.FloatType.v())) {
            return FALOAD;
        } else if (sootType.equals(soot.DoubleType.v())) {
            return DALOAD;
        } else if (sootType instanceof soot.RefLikeType) {
            return AALOAD;
        } else {
            throw new IllegalArgumentException("Unknown Type: " + sootType);
        }
    }
    
    public static FunctionRef getArrayStore(soot.Type sootType) {
        if (sootType.equals(soot.BooleanType.v())) {
            return BASTORE;
        } else if (sootType.equals(soot.ByteType.v())) {
            return BASTORE;
        } else if (sootType.equals(soot.ShortType.v())) {
            return SASTORE;
        } else if (sootType.equals(soot.CharType.v())) {
            return CASTORE;
        } else if (sootType.equals(soot.IntType.v())) {
            return IASTORE;
        } else if (sootType.equals(soot.LongType.v())) {
            return LASTORE;
        } else if (sootType.equals(soot.FloatType.v())) {
            return FASTORE;
        } else if (sootType.equals(soot.DoubleType.v())) {
            return DASTORE;
        } else if (sootType instanceof soot.RefLikeType) {
            return AASTORE;
        } else {
            throw new IllegalArgumentException("Unknown Type: " + sootType);
        }
    }
    
    public static FunctionRef getNewArray(soot.Type sootType) {
        if (sootType.equals(soot.BooleanType.v())) {
            return NVM_BC_NEW_BOOLEAN_ARRAY;
        } else if (sootType.equals(soot.ByteType.v())) {
            return NVM_BC_NEW_BYTE_ARRAY;
        } else if (sootType.equals(soot.ShortType.v())) {
            return NVM_BC_NEW_SHORT_ARRAY;
        } else if (sootType.equals(soot.CharType.v())) {
            return NVM_BC_NEW_CHAR_ARRAY;
        } else if (sootType.equals(soot.IntType.v())) {
            return NVM_BC_NEW_INT_ARRAY;
        } else if (sootType.equals(soot.LongType.v())) {
            return NVM_BC_NEW_LONG_ARRAY;
        } else if (sootType.equals(soot.FloatType.v())) {
            return NVM_BC_NEW_FLOAT_ARRAY;
        } else if (sootType.equals(soot.DoubleType.v())) {
            return NVM_BC_NEW_DOUBLE_ARRAY;
        } else {
            throw new IllegalArgumentException("Unknown Type: " + sootType);
        }
    }

    public static Function createFunction(SootMethod method, Linkage linkage, 
            FunctionAttribute ... functionAttributes) {
        return createFunction(mangleMethod(method.makeRef()), method, linkage, functionAttributes);
    }
    
    public static Function createFunction(String name, SootMethod method, Linkage linkage, 
            FunctionAttribute ... functionAttributes) {
        
        FunctionType functionType = getFunctionType(method.makeRef());
        String[] parameterNames = new String[functionType.getParameterTypes().length];
        int i = 0;
        parameterNames[i++] = "env";
        if (!method.isStatic()) {
            parameterNames[i++] = "this";
        }
        for (int j = 0; j < method.getParameterCount(); j++) {
            parameterNames[i++] = "p" + j;
        }
            
        return new Function(linkage, functionAttributes, name, functionType, parameterNames);
    }
    
    public static Value call(Function currentFunction, Value fn, List<Value> args) {
        return call(currentFunction, fn, args.toArray(new Value[args.size()]));
    }
    
    public static Value call(Function currentFunction, Value fn, Value ... args) {
        Variable result = null;
        Type returnType = ((FunctionType) fn.getType()).getReturnType();
        if (returnType != VOID) {
            result = currentFunction.newVariable(returnType);
        }
        currentFunction.add(new Call(result, fn, args));
        return result == null ? null : result.ref();
    }
    
    public static Value callWithArguments(Function currentFunction, Value fn, List<Argument> args) {
        return callWithArguments(currentFunction, fn, args.toArray(new Argument[args.size()]));
    }
    
    public static Value callWithArguments(Function currentFunction, Value fn, Argument ... args) {
        Variable result = null;
        Type returnType = ((FunctionType) fn.getType()).getReturnType();
        if (returnType != VOID) {
            result = currentFunction.newVariable(returnType);
        }
        currentFunction.add(new Call(result, fn, args));
        return result == null ? null : result.ref();
    }
    
    public static Value invoke(Function currentFunction, Value fn, 
            BasicBlockRef success, BasicBlockRef failure, List<Value> args) {
        
        return invoke(currentFunction, fn, success, failure, args.toArray(new Value[args.size()]));
    }
    
    public static Value invoke(Function currentFunction, Value fn, 
            BasicBlockRef success, BasicBlockRef failure, Value ... args) {
        
        Variable result = null;
        Type returnType = ((FunctionType) fn.getType()).getReturnType();
        if (returnType != VOID) {
            result = currentFunction.newVariable(returnType);
        }
        currentFunction.add(new Invoke(result, fn, success, failure, args));
        return result == null ? null : result.ref();
    }
    
    public static Value getInfoStruct(Function f, SootClass sootClass) {
        return call(f, getInfoStructFn(getInternalName(sootClass)));
    }
    
    public static FunctionRef getInfoStructFn(String internalName) {
        return new FunctionRef(mangleClass(internalName) + "_info", new FunctionType(I8_PTR_PTR));
    }
}
