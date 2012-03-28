/*
 * Copyright (C) 2011 The NullVM Open Source Project
 *
 * TODO: Insert proper license header.
 */
package org.nullvm.compiler;

import static org.nullvm.compiler.Functions.*;
import static org.nullvm.compiler.Mangler.*;
import static org.nullvm.compiler.Types.*;
import static org.nullvm.compiler.llvm.Type.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.nullvm.compiler.llvm.Add;
import org.nullvm.compiler.llvm.Alloca;
import org.nullvm.compiler.llvm.And;
import org.nullvm.compiler.llvm.ArrayType;
import org.nullvm.compiler.llvm.Ashr;
import org.nullvm.compiler.llvm.BasicBlock;
import org.nullvm.compiler.llvm.BasicBlockRef;
import org.nullvm.compiler.llvm.Bitcast;
import org.nullvm.compiler.llvm.Br;
import org.nullvm.compiler.llvm.Call;
import org.nullvm.compiler.llvm.Constant;
import org.nullvm.compiler.llvm.ConstantTrunc;
import org.nullvm.compiler.llvm.Fadd;
import org.nullvm.compiler.llvm.Fdiv;
import org.nullvm.compiler.llvm.FloatingPointConstant;
import org.nullvm.compiler.llvm.FloatingPointType;
import org.nullvm.compiler.llvm.Fmul;
import org.nullvm.compiler.llvm.Fpext;
import org.nullvm.compiler.llvm.Fptrunc;
import org.nullvm.compiler.llvm.Frem;
import org.nullvm.compiler.llvm.Fsub;
import org.nullvm.compiler.llvm.Function;
import org.nullvm.compiler.llvm.FunctionAttribute;
import org.nullvm.compiler.llvm.FunctionRef;
import org.nullvm.compiler.llvm.FunctionType;
import org.nullvm.compiler.llvm.Getelementptr;
import org.nullvm.compiler.llvm.GlobalRef;
import org.nullvm.compiler.llvm.Icmp;
import org.nullvm.compiler.llvm.Icmp.Condition;
import org.nullvm.compiler.llvm.Instruction;
import org.nullvm.compiler.llvm.IntegerConstant;
import org.nullvm.compiler.llvm.IntegerType;
import org.nullvm.compiler.llvm.Invoke;
import org.nullvm.compiler.llvm.Label;
import org.nullvm.compiler.llvm.Landingpad;
import org.nullvm.compiler.llvm.Landingpad.Catch;
import org.nullvm.compiler.llvm.Linkage;
import org.nullvm.compiler.llvm.Load;
import org.nullvm.compiler.llvm.Lshr;
import org.nullvm.compiler.llvm.Mul;
import org.nullvm.compiler.llvm.NullConstant;
import org.nullvm.compiler.llvm.Or;
import org.nullvm.compiler.llvm.PointerType;
import org.nullvm.compiler.llvm.Ret;
import org.nullvm.compiler.llvm.Sext;
import org.nullvm.compiler.llvm.Shl;
import org.nullvm.compiler.llvm.Sitofp;
import org.nullvm.compiler.llvm.Store;
import org.nullvm.compiler.llvm.StructureType;
import org.nullvm.compiler.llvm.Sub;
import org.nullvm.compiler.llvm.Switch;
import org.nullvm.compiler.llvm.Trunc;
import org.nullvm.compiler.llvm.Type;
import org.nullvm.compiler.llvm.Unreachable;
import org.nullvm.compiler.llvm.Value;
import org.nullvm.compiler.llvm.Variable;
import org.nullvm.compiler.llvm.VariableRef;
import org.nullvm.compiler.llvm.Xor;
import org.nullvm.compiler.llvm.Zext;
import org.nullvm.compiler.trampoline.Anewarray;
import org.nullvm.compiler.trampoline.Checkcast;
import org.nullvm.compiler.trampoline.ExceptionMatch;
import org.nullvm.compiler.trampoline.GetField;
import org.nullvm.compiler.trampoline.GetStatic;
import org.nullvm.compiler.trampoline.Instanceof;
import org.nullvm.compiler.trampoline.Invokeinterface;
import org.nullvm.compiler.trampoline.Invokespecial;
import org.nullvm.compiler.trampoline.Invokestatic;
import org.nullvm.compiler.trampoline.Invokevirtual;
import org.nullvm.compiler.trampoline.LdcClass;
import org.nullvm.compiler.trampoline.LdcString;
import org.nullvm.compiler.trampoline.Multianewarray;
import org.nullvm.compiler.trampoline.New;
import org.nullvm.compiler.trampoline.PutField;
import org.nullvm.compiler.trampoline.PutStatic;
import org.nullvm.compiler.trampoline.Trampoline;

import soot.Body;
import soot.CharType;
import soot.Immediate;
import soot.Local;
import soot.Modifier;
import soot.NullType;
import soot.PackManager;
import soot.PatchingChain;
import soot.PrimType;
import soot.RefLikeType;
import soot.SootClass;
import soot.SootField;
import soot.SootFieldRef;
import soot.SootMethod;
import soot.SootMethodRef;
import soot.Trap;
import soot.Unit;
import soot.jimple.AddExpr;
import soot.jimple.AndExpr;
import soot.jimple.ArrayRef;
import soot.jimple.BinopExpr;
import soot.jimple.CastExpr;
import soot.jimple.CaughtExceptionRef;
import soot.jimple.CmpExpr;
import soot.jimple.CmpgExpr;
import soot.jimple.CmplExpr;
import soot.jimple.ConditionExpr;
import soot.jimple.DefinitionStmt;
import soot.jimple.DivExpr;
import soot.jimple.EnterMonitorStmt;
import soot.jimple.EqExpr;
import soot.jimple.ExitMonitorStmt;
import soot.jimple.Expr;
import soot.jimple.FieldRef;
import soot.jimple.GeExpr;
import soot.jimple.GotoStmt;
import soot.jimple.GtExpr;
import soot.jimple.IfStmt;
import soot.jimple.InstanceFieldRef;
import soot.jimple.InstanceInvokeExpr;
import soot.jimple.InstanceOfExpr;
import soot.jimple.InterfaceInvokeExpr;
import soot.jimple.InvokeExpr;
import soot.jimple.InvokeStmt;
import soot.jimple.LeExpr;
import soot.jimple.LengthExpr;
import soot.jimple.LookupSwitchStmt;
import soot.jimple.LtExpr;
import soot.jimple.MulExpr;
import soot.jimple.NeExpr;
import soot.jimple.NegExpr;
import soot.jimple.NewArrayExpr;
import soot.jimple.NewExpr;
import soot.jimple.NewMultiArrayExpr;
import soot.jimple.OrExpr;
import soot.jimple.ParameterRef;
import soot.jimple.RemExpr;
import soot.jimple.ReturnStmt;
import soot.jimple.ReturnVoidStmt;
import soot.jimple.ShlExpr;
import soot.jimple.ShrExpr;
import soot.jimple.SpecialInvokeExpr;
import soot.jimple.StaticFieldRef;
import soot.jimple.StaticInvokeExpr;
import soot.jimple.Stmt;
import soot.jimple.SubExpr;
import soot.jimple.TableSwitchStmt;
import soot.jimple.ThisRef;
import soot.jimple.ThrowStmt;
import soot.jimple.UshrExpr;
import soot.jimple.VirtualInvokeExpr;
import soot.jimple.XorExpr;
import soot.jimple.toolkits.annotation.tags.ArrayCheckTag;
import soot.jimple.toolkits.annotation.tags.NullCheckTag;
import soot.tagkit.DoubleConstantValueTag;
import soot.tagkit.FloatConstantValueTag;
import soot.tagkit.IntegerConstantValueTag;
import soot.tagkit.LongConstantValueTag;
import soot.tagkit.StringConstantValueTag;
import soot.tagkit.Tag;
import soot.util.Chain;

/**
 *
 * @version $Id$
 */
public class MethodCompiler extends AbstractMethodCompiler {

    private Function function;
    private Set<Unit> jumpTargets;
    private Set<Unit> trapHandlers;
    private Map<Unit, List<Trap>> trapsAt;
    private Set<List<Trap>> recordedTraps;
    
    private Variable dims;
    
    public MethodCompiler(Config config) {
        super(config);
    }
    
    protected void doCompile(ModuleBuilder moduleBuilder, SootMethod method) {
        jumpTargets = new HashSet<Unit>();
        trapHandlers = new HashSet<Unit>();
        trapsAt = new HashMap<Unit, List<Trap>>();
        recordedTraps = new HashSet<List<Trap>>();
        
        function = createFunction(method, Linkage.external, FunctionAttribute.noinline);
        moduleBuilder.addFunction(function);
        
        Body body = method.retrieveActiveBody();
        PackManager.v().getPack("jtp").apply(body);
        PackManager.v().getPack("jop").apply(body);
        PackManager.v().getPack("jap").apply(body);
        
        Set<String> seen = new HashSet<String>();
        for (Unit unit : body.getUnits()) {
            if (unit instanceof DefinitionStmt) {
                DefinitionStmt stmt = (DefinitionStmt) unit;
                if (stmt.getLeftOp() instanceof Local) {
                    Local local = (Local) stmt.getLeftOp();
                    if (!seen.contains(local.getName())) {
                        Type type = getLocalType(local.getType());
                        function.add(new Alloca(function.newVariable(local.getName(), type), type));
                        seen.add(local.getName());
                    }
                }
            }
        }
        
        if ("<clinit>".equals(method.getName())) {
            initializeClassFields();
        }
        
        PatchingChain<Unit> units = body.getUnits();
        
        int multiANewArrayMaxDims = 0;
        for (Unit unit : units) {
            if (unit instanceof DefinitionStmt) {
                DefinitionStmt stmt = (DefinitionStmt) unit;
                if (stmt.getRightOp() instanceof NewMultiArrayExpr) {
                    NewMultiArrayExpr expr = (NewMultiArrayExpr) stmt.getRightOp();
                    multiANewArrayMaxDims = Math.max(multiANewArrayMaxDims, expr.getSizeCount());
                }
            }
        }
        
        dims = null;
        if (multiANewArrayMaxDims > 0) {
            dims = function.newVariable("dims", new PointerType(new ArrayType(multiANewArrayMaxDims, I32)));
            function.add(new Alloca(dims, new ArrayType(multiANewArrayMaxDims, I32)));
        }
        
        for (Unit unit : units) {
            for (Unit u : units) {
                if (u instanceof GotoStmt) {
                    Unit target = ((GotoStmt) u).getTarget();
                    if (target == unit) {
                        jumpTargets.add(unit);
                    }
                } else if (u instanceof IfStmt) {
                    Unit target = ((IfStmt) u).getTarget();
                    if (target == unit || unit == units.getSuccOf(u)) {
                        jumpTargets.add(unit);
                    }
                } else if (u instanceof LookupSwitchStmt) {
                    LookupSwitchStmt stmt = (LookupSwitchStmt) u;
                    if (unit == stmt.getDefaultTarget() || stmt.getTargets().contains(unit)) {
                        jumpTargets.add(unit);
                    }
                } else if (u instanceof TableSwitchStmt) {
                    TableSwitchStmt stmt = (TableSwitchStmt) u;
                    if (unit == stmt.getDefaultTarget() || stmt.getTargets().contains(unit)) {
                        jumpTargets.add(unit);
                    }
                }
            }
            for (Trap trap : body.getTraps()) {
                Unit beginUnit = trap.getBeginUnit();
                Unit endUnit = trap.getEndUnit();
                if (beginUnit != endUnit && unit == trap.getHandlerUnit()) {
                    trapHandlers.add(unit);
                }
            }
        }
        
        for (Unit unit : units) {
            if (jumpTargets.contains(unit) || trapHandlers.contains(unit)) {
                BasicBlock oldBlock = function.getCurrentBasicBlock();
                function.newBasicBlock(new Label(unit));
                if (oldBlock != null) {
                    Instruction last = oldBlock.last();
                    if (last == null || !isTerminator(last)) {
                        oldBlock.add(new Br(function.newBasicBlockRef(new Label(unit))));
                    }
                }
            }
            if (unit instanceof DefinitionStmt) {
                assign((DefinitionStmt) unit);
            } else if (unit instanceof ReturnStmt) {
                return_((ReturnStmt) unit);
            } else if (unit instanceof ReturnVoidStmt) {
                returnVoid();
            } else if (unit instanceof IfStmt) {
                if_((IfStmt) unit);
            } else if (unit instanceof LookupSwitchStmt) {
                lookupSwitch((LookupSwitchStmt) unit);
            } else if (unit instanceof TableSwitchStmt) {
                tableSwitch((TableSwitchStmt) unit);
            } else if (unit instanceof GotoStmt) {
                goto_((GotoStmt) unit);
            } else if (unit instanceof ThrowStmt) {
                throw_((ThrowStmt) unit);
            } else if (unit instanceof InvokeStmt) {
                invoke((InvokeStmt) unit);
            } else if (unit instanceof EnterMonitorStmt) {
                enterMonitor((EnterMonitorStmt) unit);
            } else if (unit instanceof ExitMonitorStmt) {
                exitMonitor((ExitMonitorStmt) unit);
            } else {
                throw new IllegalArgumentException("Unknown Unit type: " + unit.getClass());
            }
        }

        next: for (List<Trap> traps : recordedTraps) {
            BasicBlock bb = function.newBasicBlock(new Label(traps));
            Variable result = function.newVariable(new StructureType(I8_PTR, I32));
            bb.add(new Landingpad(result, NVM_BC_PERSONALITY, new Catch(new NullConstant(I8_PTR))));
            Label directCatchLabel = new Label(bb.getLabel()); // The label throw statements within try-catch blocks jump to
            bb.add(new Br(function.newBasicBlockRef(directCatchLabel)));
            bb = function.newBasicBlock(directCatchLabel);
            for (Trap trap : traps) {
                String exName = trap.getException().getName();
                if ("java.lang.Throwable".equals(exName)) {
                    bb.add(new Br(function.newBasicBlockRef(new Label(trap.getHandlerUnit()))));
                    continue next;
                }
                Trampoline trampoline = new ExceptionMatch(this.className, getInternalName(trap.getException()));
                trampolines.add(trampoline);
                Variable v = function.newVariable(I32);
                bb.add(new Call(v, trampoline.getFunctionRef(), ENV));
                Variable cond = function.newVariable(I1);
                bb.add(new Trunc(cond, new VariableRef(v), I1));
                BasicBlockRef falseBlock = function.newBasicBlockRef(new Label());
                bb.add(new Br(new VariableRef(cond), function.newBasicBlockRef(new Label(trap.getHandlerUnit())), falseBlock));
                bb = function.newBasicBlock(falseBlock.getLabel());
            }
            
            bb.add(new Call(NVM_BC_RETHROW, ENV));
            bb.add(new Unreachable());
        }
    }

    private void initializeClassFields() {
        for (SootField field : sootMethod.getDeclaringClass().getFields()) {
            if (!field.isStatic()) {
                continue;
            }
            String fieldName = field.getName();
            String fieldDesc = getDescriptor(field.getType());
            for (Tag tag : field.getTags()) {
                Value value = null;
                if (tag instanceof DoubleConstantValueTag) {
                    DoubleConstantValueTag dtag = (DoubleConstantValueTag) tag;
                    value = new FloatingPointConstant(dtag.getDoubleValue());
                } else if (tag instanceof FloatConstantValueTag) {
                    FloatConstantValueTag ftag = (FloatConstantValueTag) tag;
                    value = new FloatingPointConstant(ftag.getFloatValue());
                } else if (tag instanceof IntegerConstantValueTag) {
                    IntegerConstantValueTag itag = (IntegerConstantValueTag) tag;
                    value = new IntegerConstant(itag.getIntValue());
                    IntegerType type = (IntegerType) getType(field.getType());
                    if (type.getBits() < 32) {
                        value = new ConstantTrunc((Constant) value, type);
                    }
                } else if (tag instanceof LongConstantValueTag) {
                    LongConstantValueTag ltag = (LongConstantValueTag) tag;
                    value = new IntegerConstant(ltag.getLongValue());
                } else if (tag instanceof StringConstantValueTag) {
                    String s = ((StringConstantValueTag) tag).getStringValue();
                    Trampoline trampoline = new LdcString(className, s);
                    trampolines.add(trampoline);
                    value = call(trampoline.getFunctionRef(), ENV);
                }
                
                if (value != null) {
                    FunctionRef fn = new FunctionRef(mangleField(this.className, fieldName, fieldDesc) + "_setter", 
                            new FunctionType(VOID, ENV_PTR, getType(fieldDesc)));
                    call(fn, ENV, value);
                }
            }
        }
    }

    
    private static boolean isTerminator(Instruction instr) {
        return instr instanceof Ret || instr instanceof Br 
            || instr instanceof Invoke || instr instanceof Unreachable 
            || instr instanceof Switch;
    }

    private Value immediate(Unit unit, Immediate v) {
        // v is either a soot.Local or a soot.jimple.Constant
        if (v instanceof soot.Local) {
            Local local = (Local) v;
            Type type = getLocalType(v.getType());
            VariableRef var = new VariableRef(local.getName(), new PointerType(type));
            Variable tmp = function.newVariable(type);
            function.add(new Load(tmp, var));
            return new VariableRef(tmp);
        } else if (v instanceof soot.jimple.IntConstant) {
            return new IntegerConstant(((soot.jimple.IntConstant) v).value);
        } else if (v instanceof soot.jimple.LongConstant) {
            return new IntegerConstant(((soot.jimple.LongConstant) v).value);
        } else if (v instanceof soot.jimple.FloatConstant) {
            return new FloatingPointConstant(((soot.jimple.FloatConstant) v).value);
        } else if (v instanceof soot.jimple.DoubleConstant) {
            return new FloatingPointConstant(((soot.jimple.DoubleConstant) v).value);
        } else if (v instanceof soot.jimple.NullConstant) {
            return new NullConstant(OBJECT_PTR);
        } else if (v instanceof soot.jimple.StringConstant) {
            String s = ((soot.jimple.StringConstant) v).value;
            Trampoline trampoline = new LdcString(className, s);
            trampolines.add(trampoline);
            return callOrInvoke(unit, trampoline.getFunctionRef(), ENV);
        } else if (v instanceof soot.jimple.ClassConstant) {
            // ClassConstant is either the internal name of a class or the descriptor of an array
            String targetClassName = ((soot.jimple.ClassConstant) v).getValue();
            if (isArray(targetClassName) && isPrimitiveComponentType(targetClassName)) {
                String primitiveDesc = targetClassName.substring(1);
                Variable result = function.newVariable(OBJECT_PTR);
                function.add(new Load(result, new GlobalRef("array_" + primitiveDesc, OBJECT_PTR)));
                return result.ref();
            } else {
                FunctionRef fn = null;
                if (targetClassName.equals(this.className)) {
                    fn = new FunctionRef(mangleClass(sootMethod.getDeclaringClass()) + "_ldc", 
                            new FunctionType(OBJECT_PTR, ENV_PTR));
                } else {
                    Trampoline trampoline = new LdcClass(className, ((soot.jimple.ClassConstant) v).getValue());
                    trampolines.add(trampoline);
                    fn = trampoline.getFunctionRef();
                }
                return callOrInvoke(unit, fn, ENV);
            }
        }
        throw new IllegalArgumentException("Unknown Immediate type: " + v.getClass());
    }

    private Value widenToI32Value(Value value, boolean unsigned) {
        Type type = value.getType();
        if (type instanceof IntegerType && ((IntegerType) type).getBits() < 32) {
            Variable t = function.newVariable(I32);
            if (unsigned) {
                function.add(new Zext(t, value, I32));
            } else {
                function.add(new Sext(t, value, I32));
            }
            return t.ref();
        } else {
            return value;
        }
    }
    
    private Value narrowFromI32Value(Type type, Value value) {
        if (value.getType() == I32 && ((IntegerType) type).getBits() < 32) {
            Variable t = function.newVariable(type);
            function.add(new Trunc(t, value, type));
            value = t.ref();
        }
        return value;
    }
    
    private Value call(Value fn, Value ... args) {
        return Functions.call(this.function, fn, args);
    }
    
    private Value callOrInvoke(Unit unit, Value fn, Value ... args) {
        Variable result = null;
        Type returnType = ((FunctionType) fn.getType()).getReturnType();
        if (returnType != VOID) {
            result = this.function.newVariable(returnType);
        }
        List<Trap> traps = getTrapsAt(unit);
        if (!traps.isEmpty()) {
            Label label = new Label();
            BasicBlockRef to = function.newBasicBlockRef(label);
            BasicBlockRef unwind = function.newBasicBlockRef(new Label(traps));
            function.add(new Invoke(result, fn, to, unwind, args));
            function.newBasicBlock(label);
            recordedTraps.add(traps);
        } else {
            function.add(new Call(result, fn, args));
        }
        return result == null ? null : result.ref();
    }
    
    private boolean canAccessDirectly(FieldRef ref) {
        SootClass sootClass = this.sootMethod.getDeclaringClass();
        SootFieldRef fieldRef = ref.getFieldRef();
        if (!fieldRef.declaringClass().equals(sootClass)) {
            return false;
        }

        try {
            SootField field = sootClass.getField(fieldRef.name(), fieldRef.type());
            /* 
             * The field exists.
             */
            if (field.isStatic()) {
                // Static fields have to be accessed using getstatic/putstatic.
                // If not we want an exception to be thrown so we need a trampoline.
                return ref instanceof StaticFieldRef;
            }
            // Instance fields have to be accessed using getfield/putfield.
            // If not we want an exception to be thrown so we need a trampoline.
            return ref instanceof InstanceFieldRef;
        } catch (RuntimeException e) {
            // SootClass.getField(...) throws RuntimeException if the field
            // isn't declared in the class.
            return false;
        }            
    }
    
    private boolean canCallDirectly(InvokeExpr expr) {
        if (expr instanceof InterfaceInvokeExpr) {
            // Never possible
            return false;
        }
        SootClass sootClass = this.sootMethod.getDeclaringClass();
        SootMethodRef methodRef = expr.getMethodRef();
        if (!methodRef.declaringClass().equals(sootClass)) {
            return false;
        }
        try {
            SootMethod method = sootClass.getMethod(methodRef.name(), 
                    methodRef.parameterTypes(), methodRef.returnType());
            if (method.isAbstract()) {
                return false;
            }
            /*
             * The method exists and isn't abstract. Non virtual (invokespecial) 
             * as well as static calls and calls to final methods can be done directly.
             */
            if (method.isStatic()) {
                // Static methods must be called using invokestatic. If not we 
                // want an exception to be thrown so we need a trampoline.
                return expr instanceof StaticInvokeExpr;
            }
            if (expr instanceof SpecialInvokeExpr) {
                return true;
            }
            if (expr instanceof VirtualInvokeExpr) {
                // Either the class or the method must be final or 
                // the method must be private
                return Modifier.isFinal(sootClass.getModifiers()) 
                        || Modifier.isFinal(method.getModifiers()) 
                        || method.isPrivate();
            }
            return false;
        } catch (RuntimeException e) {
            // SootClass.getMethod(...) throws RuntimeException if the method
            // isn't declared in the class.
            return false;
        }
    }
    
    @SuppressWarnings("unchecked")
    private Value invokeExpr(Stmt stmt, InvokeExpr expr) {
        SootMethodRef methodRef = expr.getMethodRef();
        ArrayList<Value> args = new ArrayList<Value>();
        args.add(ENV);
        if (!(expr instanceof StaticInvokeExpr)) {
            Value base = immediate(stmt, (Immediate) ((InstanceInvokeExpr) expr).getBase());
            checkNull(stmt, base);
            args.add(base);
        }
        int i = 0;
        for (soot.Value sootArg : (List<soot.Value>) expr.getArgs())  {
            Value arg = immediate(stmt, (Immediate) sootArg);
            args.add(narrowFromI32Value(getType(methodRef.parameterType(i)), arg));
            i++;
        }
        FunctionRef functionRef = null;
        Value result = null;
        if (canCallDirectly(expr)) {
            SootMethod method = this.sootMethod.getDeclaringClass().getMethod(methodRef.name(), 
                    methodRef.parameterTypes(), methodRef.returnType());
            String functionName = mangleMethod(method);
            if (method.isSynchronized()) {
                functionName += "_synchronized";
            }
            functionRef = new FunctionRef(functionName, getFunctionType(method));
        } else {
            Trampoline trampoline = null;
            String targetClassName = getInternalName(methodRef.declaringClass());
            String methodName = methodRef.name();
            String methodDesc = getDescriptor(methodRef);
            if (expr instanceof SpecialInvokeExpr) {
                soot.Type runtimeType = ((SpecialInvokeExpr) expr).getBase().getType();
                String runtimeClassName = runtimeType == NullType.v() ? targetClassName : getInternalName(runtimeType);
                trampoline = new Invokespecial(this.className, targetClassName, methodName, methodDesc, runtimeClassName);
            } else if (expr instanceof StaticInvokeExpr) {
                trampoline = new Invokestatic(this.className, targetClassName, methodName, methodDesc);
            } else if (expr instanceof VirtualInvokeExpr) {
                soot.Type runtimeType = ((VirtualInvokeExpr) expr).getBase().getType();
                String runtimeClassName = runtimeType == NullType.v() ? targetClassName : getInternalName(runtimeType);
                trampoline = new Invokevirtual(this.className, targetClassName, methodName, methodDesc, runtimeClassName);
            } else if (expr instanceof InterfaceInvokeExpr) {
                trampoline = new Invokeinterface(this.className, targetClassName, methodName, methodDesc);
            }
            trampolines.add(trampoline);
            functionRef = trampoline.getFunctionRef();
        }
        result = callOrInvoke(stmt, functionRef, args.toArray(new Value[0]));
        if (result != null) {
            return widenToI32Value(result, methodRef.returnType().equals(CharType.v()));
        } else {
            return null;
        }
    }

    private void checkNull(Stmt stmt, Value base) {
        NullCheckTag nullCheckTag = (NullCheckTag) stmt.getTag("NullCheckTag");
        if (nullCheckTag == null || nullCheckTag.needCheck()) {
            callOrInvoke(stmt, CHECK_NULL, ENV, base);
        }
    }
    
    private void checkBounds(Stmt stmt, Value base, Value index) {
        ArrayCheckTag arrayCheckTag = (ArrayCheckTag) stmt.getTag("ArrayCheckTag");
        if (arrayCheckTag == null || arrayCheckTag.isCheckLower()) {
            callOrInvoke(stmt, CHECK_LOWER, ENV, base, index);
        }
        if (arrayCheckTag == null || arrayCheckTag.isCheckUpper()) {
            callOrInvoke(stmt, CHECK_UPPER, ENV, base, index);
        }
    }
    
    private List<Trap> getTrapsAt(Unit u) {
        List<Trap> result = this.trapsAt.get(u);
        if (result == null) {
            Body body = sootMethod.getActiveBody();
            Chain<Trap> traps = body.getTraps();
            if (traps.isEmpty()) {
                result = Collections.emptyList();
            } else {
                result = new ArrayList<Trap>();
                PatchingChain<Unit> units = body.getUnits();
                for (Trap trap : traps) {
                    Unit beginUnit = trap.getBeginUnit();
                    Unit endUnit = trap.getEndUnit();
                    if (beginUnit != endUnit && u != endUnit) {
                        if (u == beginUnit || (units.follows(u, beginUnit) && units.follows(endUnit, u))) {
                            result.add(trap);
                        }
                    }
                }
            }
            this.trapsAt.put(u, result);
        }
        return result;
    }

    
    private void assign(DefinitionStmt stmt) {
        /*
         * leftOp is either a Local, an ArrayRef or a FieldRef
         * rightOp is either a Local, a Ref, or an Expr
         */

        soot.Value rightOp = stmt.getRightOp();
        Value result;

        if (rightOp instanceof Immediate) {
            Immediate immediate = (Immediate) rightOp;
            result = immediate(stmt, immediate);
        } else if (rightOp instanceof ThisRef) {
            result = new VariableRef("this", OBJECT_PTR);
        } else if (rightOp instanceof ParameterRef) {
            ParameterRef ref = (ParameterRef) rightOp;
            Value p = new VariableRef("p" + ref.getIndex(), getType(ref.getType()));
            result = widenToI32Value(p, isUnsigned(ref.getType()));
        } else if (rightOp instanceof CaughtExceptionRef) {
            result = call(NVM_BC_EXCEPTION_CLEAR, ENV);
        } else if (rightOp instanceof ArrayRef) {
            ArrayRef ref = (ArrayRef) rightOp;
            VariableRef base = (VariableRef) immediate(stmt, (Immediate) ref.getBase());
            Value index = immediate(stmt, (Immediate) ref.getIndex());
            checkNull(stmt, base);
            checkBounds(stmt, base, index);
            result = callOrInvoke(stmt, getArrayLoad(ref.getType()), base, index);
            result = widenToI32Value(result, isUnsigned(ref.getType()));
        } else if (rightOp instanceof InstanceFieldRef) {
            InstanceFieldRef ref = (InstanceFieldRef) rightOp;
            Value base = immediate(stmt, (Immediate) ref.getBase());
            checkNull(stmt, base);
            FunctionRef fn = null;
            if (canAccessDirectly(ref)) {
                fn = new FunctionRef(mangleField(ref.getFieldRef()) + "_getter", 
                        new FunctionType(getType(ref.getType()), ENV_PTR, OBJECT_PTR));
            } else {
                soot.Type runtimeType = ref.getBase().getType();
                String targetClassName = getInternalName(ref.getFieldRef().declaringClass());
                String runtimeClassName = runtimeType == NullType.v() ? targetClassName : getInternalName(runtimeType);
                Trampoline trampoline = new GetField(this.className, targetClassName, 
                        ref.getFieldRef().name(), getDescriptor(ref.getFieldRef().type()), runtimeClassName);
                trampolines.add(trampoline);
                fn = trampoline.getFunctionRef();
            }
            result = callOrInvoke(stmt, fn, ENV, base);
            result = widenToI32Value(result, isUnsigned(ref.getType()));
        } else if (rightOp instanceof StaticFieldRef) {
            StaticFieldRef ref = (StaticFieldRef) rightOp;
            FunctionRef fn = null;
            if (canAccessDirectly(ref)) {
                fn = new FunctionRef(mangleField(ref.getFieldRef()) + "_getter", 
                        new FunctionType(getType(ref.getType()), ENV_PTR));
            } else {
                String targetClassName = getInternalName(ref.getFieldRef().declaringClass());
                Trampoline trampoline = new GetStatic(this.className, targetClassName, 
                        ref.getFieldRef().name(), getDescriptor(ref.getFieldRef().type()));
                trampolines.add(trampoline);
                fn = trampoline.getFunctionRef();
            }
            result = callOrInvoke(stmt, fn, ENV);
            result = widenToI32Value(result, isUnsigned(ref.getType()));
        } else if (rightOp instanceof Expr) {
            if (rightOp instanceof BinopExpr) {
                BinopExpr expr = (BinopExpr) rightOp;
                Type rightType = getLocalType(expr.getType());
                Variable resultVar = function.newVariable(rightType);
                result = resultVar.ref();
                Value op1 = immediate(stmt, (Immediate) expr.getOp1());
                Value op2 = immediate(stmt, (Immediate) expr.getOp2());
                if (rightOp instanceof AddExpr) {
                    if (rightType instanceof IntegerType) {
                        function.add(new Add(resultVar, op1, op2));
                    } else {
                        function.add(new Fadd(resultVar, op1, op2));
                    }
                } else if (rightOp instanceof AndExpr) {
                    function.add(new And(resultVar, op1, op2));
                } else if (rightOp instanceof CmpExpr) {
                    Variable t1 = function.newVariable(I1);
                    Variable t2 = function.newVariable(I1);
                    Variable t3 = function.newVariable(resultVar.getType());
                    Variable t4 = function.newVariable(resultVar.getType());
                    function.add(new Icmp(t1, Condition.slt, op1, op2));
                    function.add(new Icmp(t2, Condition.sgt, op1, op2));
                    function.add(new Zext(t3, new VariableRef(t1), resultVar.getType()));
                    function.add(new Zext(t4, new VariableRef(t2), resultVar.getType()));
                    function.add(new Sub(resultVar, new VariableRef(t4), new VariableRef(t3)));
                } else if (rightOp instanceof DivExpr) {
                    if (rightType instanceof IntegerType) {
                        FunctionRef f = rightType == I64 ? LDIV : IDIV;
                        result = callOrInvoke(stmt, f, ENV, op1, op2);
                    } else {
                        // float or double
                        function.add(new Fdiv(resultVar, op1, op2));
                    }
                } else if (rightOp instanceof MulExpr) {
                    if (rightType instanceof IntegerType) {
                        function.add(new Mul(resultVar, op1, op2));
                    } else {
                        function.add(new Fmul(resultVar, op1, op2));
                    }
                } else if (rightOp instanceof OrExpr) {
                    function.add(new Or(resultVar, op1, op2));
                } else if (rightOp instanceof RemExpr) {
                    if (rightType instanceof IntegerType) {
                        FunctionRef f = rightType == I64 ? LREM : IREM;
                        result = callOrInvoke(stmt, f, ENV, op1, op2);
                    } else {
                        // float or double
                        function.add(new Frem(resultVar, op1, op2));
                    }
                } else if (rightOp instanceof ShlExpr || rightOp instanceof ShrExpr || rightOp instanceof UshrExpr) {
                    IntegerType type = (IntegerType) op1.getType();
                    int bits = type.getBits();
                    Variable t = function.newVariable(op2.getType());
                    function.add(new And(t, op2, new IntegerConstant(bits - 1, (IntegerType) op2.getType())));
                    Value shift = t.ref();
                    if (((IntegerType) shift.getType()).getBits() < bits) {
                        Variable tmp = function.newVariable(type);
                        function.add(new Zext(tmp, shift, type));
                        shift = tmp.ref();
                    }
                    if (rightOp instanceof ShlExpr) {
                        function.add(new Shl(resultVar, op1, shift));
                    } else if (rightOp instanceof ShrExpr) {
                        function.add(new Ashr(resultVar, op1, shift));
                    } else {
                        function.add(new Lshr(resultVar, op1, shift));
                    }
                } else if (rightOp instanceof SubExpr) {
                    if (rightType instanceof IntegerType) {
                        function.add(new Sub(resultVar, op1, op2));
                    } else {
                        function.add(new Fsub(resultVar, op1, op2));
                    }
                } else if (rightOp instanceof XorExpr) {
                    function.add(new Xor(resultVar, op1, op2));
                } else if (rightOp instanceof XorExpr) {
                    function.add(new Xor(resultVar, op1, op2));
                } else if (rightOp instanceof CmplExpr) {
                    FunctionRef f = op1.getType() == FLOAT ? FCMPL : DCMPL;
                    function.add(new Call(resultVar, f, op1, op2));
                } else if (rightOp instanceof CmpgExpr) {
                    FunctionRef f = op1.getType() == FLOAT ? FCMPG : DCMPG;
                    function.add(new Call(resultVar, f, op1, op2));
                } else {
                    throw new IllegalArgumentException("Unknown type for rightOp: " + rightOp.getClass());
                }
            } else if (rightOp instanceof CastExpr) {
                Value op = immediate(stmt, (Immediate) ((CastExpr) rightOp).getOp());
                soot.Type sootTargetType = ((CastExpr) rightOp).getCastType();
                soot.Type sootSourceType = ((CastExpr) rightOp).getOp().getType();
                if (sootTargetType instanceof PrimType) {
                    Type targetType = getType(sootTargetType);
                    Type sourceType = getType(sootSourceType);
                    if (targetType instanceof IntegerType && sourceType instanceof IntegerType) {
                        // op is at least I32 and has already been widened if source type had fewer bits then I32
                        IntegerType toType = (IntegerType) targetType;
                        IntegerType fromType = (IntegerType) op.getType();
                        Variable v = function.newVariable(toType);
                        if (fromType.getBits() < toType.getBits()) {
                            // Widening
                            if (isUnsigned(sootSourceType)) {
                                function.add(new Zext(v, op, toType));
                            } else {
                                function.add(new Sext(v, op, toType));
                            }
                        } else if (fromType.getBits() == toType.getBits()) {
                            function.add(new Bitcast(v, op, toType));
                        } else {
                            // Narrow
                            function.add(new Trunc(v, op, toType));
                        }
                        result = widenToI32Value(v.ref(), isUnsigned(sootTargetType));
                    } else if (targetType instanceof FloatingPointType && sourceType instanceof IntegerType) {
                        // we always to a signed conversion since if op is char it has already been zero extended to I32
                        Variable v = function.newVariable(targetType);
                        function.add(new Sitofp(v, op, targetType));
                        result = v.ref();
                    } else if (targetType instanceof FloatingPointType && sourceType instanceof FloatingPointType) {
                        Variable v = function.newVariable(targetType);
                        if (targetType == FLOAT && sourceType == DOUBLE) {
                            function.add(new Fptrunc(v, op, targetType));
                        } else if (targetType == DOUBLE && sourceType == FLOAT) {
                            function.add(new Fpext(v, op, targetType));
                        } else {
                            function.add(new Bitcast(v, op, targetType));
                        }
                        result = v.ref();
                    } else {
                        // F2I, F2L, D2I, D2L
                        FunctionRef f = null;
                        if (targetType == I32 && sourceType == FLOAT) {
                            f = F2I;
                        } else if (targetType == I64 && sourceType == FLOAT) {
                            f = F2L;
                        } else if (targetType == I32 && sourceType == DOUBLE) {
                            f = D2I;
                        } else if (targetType == I64 && sourceType == DOUBLE) {
                            f = D2L;
                        } else {
                            throw new IllegalArgumentException();
                        }
                        Variable v = function.newVariable(targetType);
                        function.add(new Call(v, f, op));
                        result = v.ref();
                    }
//                } else if (sootTargetType instanceof soot.ArrayType) {
//                    soot.Type elementType = ((soot.ArrayType) sootTargetType).getElementType();
//                    if (elementType instanceof PrimType) {
//                        
//                    }
                } else {
                    String targetClassName = getInternalName(sootTargetType);
                    FunctionRef fn = null;
                    if (targetClassName.equals(this.className)) {
                        fn = new FunctionRef(mangleClass(this.className) + "_checkcast", 
                                new FunctionType(OBJECT_PTR, ENV_PTR, OBJECT_PTR));
                    } else {
                        Trampoline trampoline = new Checkcast(this.className, targetClassName);
                        trampolines.add(trampoline);
                        fn = trampoline.getFunctionRef();                        
                    }
                    result = callOrInvoke(stmt, fn, ENV, op);
                }
            } else if (rightOp instanceof InstanceOfExpr) {
                Value op = immediate(stmt, (Immediate) ((InstanceOfExpr) rightOp).getOp());
                soot.Type checkType = ((InstanceOfExpr) rightOp).getCheckType();
                String targetClassName = getInternalName(checkType);
                FunctionRef fn = null;
                if (targetClassName.equals(this.className)) {
                    fn = new FunctionRef(mangleClass(this.className) + "_instanceof", 
                            new FunctionType(I32, ENV_PTR, OBJECT_PTR));
                } else {
                    Trampoline trampoline = new Instanceof(this.className, targetClassName);
                    trampolines.add(trampoline);
                    fn = trampoline.getFunctionRef();
                }
                result = callOrInvoke(stmt, fn, ENV, op);
            } else if (rightOp instanceof NewExpr) {
                String targetClassName = getInternalName(((NewExpr) rightOp).getBaseType());
                FunctionRef fn = null;
                if (targetClassName.equals(this.className)) {
                    fn = new FunctionRef(mangleClass(this.className) + "_allocator", 
                            new FunctionType(OBJECT_PTR, ENV_PTR));
                } else {
                    Trampoline trampoline = new New(this.className, targetClassName);
                    trampolines.add(trampoline);
                    fn = trampoline.getFunctionRef();
                }
                result = callOrInvoke(stmt, fn, ENV);
            } else if (rightOp instanceof NewArrayExpr) {
                NewArrayExpr expr = (NewArrayExpr) rightOp;
                Value size = immediate(stmt, (Immediate) expr.getSize());
                if (expr.getBaseType() instanceof PrimType) {
                    result = callOrInvoke(stmt, getNewArray(expr.getBaseType()), ENV, size);
                } else {
                    String targetClassName = getInternalName(expr.getType());
                    Trampoline trampoline = new Anewarray(this.className, targetClassName);
                    trampolines.add(trampoline);
                    result = callOrInvoke(stmt, trampoline.getFunctionRef(), ENV, size);
                }
            } else if (rightOp instanceof NewMultiArrayExpr) {
                NewMultiArrayExpr expr = (NewMultiArrayExpr) rightOp;
                for (int i = 0; i < expr.getSizeCount(); i++) {
                    Value size = immediate(stmt, (Immediate) expr.getSize(i));
                    Variable ptr = function.newVariable(new PointerType(I32));
                    function.add(new Getelementptr(ptr, dims.ref(), 0, i));
                    function.add(new Store(size, ptr.ref()));
                }
                Variable dimsI32 = function.newVariable(new PointerType(I32));
                function.add(new Bitcast(dimsI32, dims.ref(), dimsI32.getType()));
                String targetClassName = getInternalName(expr.getType());
                Trampoline trampoline = new Multianewarray(this.className, targetClassName);
                trampolines.add(trampoline);
                result = callOrInvoke(stmt, trampoline.getFunctionRef(), ENV, new IntegerConstant(expr.getSizeCount()), dimsI32.ref());
            } else if (rightOp instanceof InvokeExpr) {
                result = invokeExpr(stmt, (InvokeExpr) rightOp);
            } else if (rightOp instanceof LengthExpr) {
                Value op = immediate(stmt, (Immediate) ((LengthExpr) rightOp).getOp());
                checkNull(stmt, op);
                Variable v = function.newVariable(I32);
                function.add(new Call(v, ARRAY_LENGTH, op));
                result = v.ref();
            } else if (rightOp instanceof NegExpr) {
                NegExpr expr = (NegExpr) rightOp;
                Value op = immediate(stmt, (Immediate) expr.getOp());
                Type rightType = op.getType();
                Variable v = function.newVariable(op.getType());
                if (rightType instanceof IntegerType) {
                    function.add(new Sub(v, new IntegerConstant(0, (IntegerType) rightType), op));
                } else {
                    function.add(new Fmul(v, new FloatingPointConstant(-1.0, (FloatingPointType) rightType), op));
                }
                result = v.ref();
            } else {
                throw new IllegalArgumentException("Unknown type for rightOp: " + rightOp.getClass());
            }
        } else {
            throw new IllegalArgumentException("Unknown type for rightOp: " + rightOp.getClass());
        }

        soot.Value leftOp = stmt.getLeftOp();

        if (leftOp instanceof Local) {
            Local local = (Local) leftOp;
            VariableRef v = new VariableRef(local.getName(), new PointerType(getLocalType(leftOp.getType())));
            function.add(new Store(result, v));
        } else {
            Type leftType = getType(leftOp.getType());
            Value narrowedResult = narrowFromI32Value(leftType, result);
            if (leftOp instanceof ArrayRef) {
                ArrayRef ref = (ArrayRef) leftOp;
                VariableRef base = (VariableRef) immediate(stmt, (Immediate) ref.getBase());
                Value index = immediate(stmt, (Immediate) ref.getIndex());
                checkNull(stmt, base);
                checkBounds(stmt, base, index);
                if (leftOp.getType() instanceof RefLikeType) {
                    callOrInvoke(stmt, NVM_BC_SET_OBJECT_ARRAY_ELEMENT, ENV, base, index, narrowedResult);
                } else {
                    callOrInvoke(stmt, getArrayStore(leftOp.getType()), base, index, narrowedResult);
                }
            } else if (leftOp instanceof InstanceFieldRef) {
                InstanceFieldRef ref = (InstanceFieldRef) leftOp;
                Value base = immediate(stmt, (Immediate) ref.getBase());
                checkNull(stmt, base);
                FunctionRef fn = null;
                if (canAccessDirectly(ref)) {
                    fn = new FunctionRef(mangleField(ref.getFieldRef()) + "_setter", 
                            new FunctionType(VOID, ENV_PTR, OBJECT_PTR, getType(ref.getType())));
                } else {
                    soot.Type runtimeType = ref.getBase().getType();
                    String targetClassName = getInternalName(ref.getFieldRef().declaringClass());
                    String runtimeClassName = runtimeType == NullType.v() ? targetClassName : getInternalName(runtimeType);
                    Trampoline trampoline = new PutField(this.className, targetClassName, 
                            ref.getFieldRef().name(), getDescriptor(ref.getFieldRef().type()), runtimeClassName);
                    trampolines.add(trampoline);
                    fn = trampoline.getFunctionRef();
                }
                callOrInvoke(stmt, fn, ENV, base, narrowedResult);
            } else if (leftOp instanceof StaticFieldRef) {
                StaticFieldRef ref = (StaticFieldRef) leftOp;
                FunctionRef fn = null;
                if (canAccessDirectly(ref)) {
                    fn = new FunctionRef(mangleField(ref.getFieldRef()) + "_setter", 
                            new FunctionType(VOID, ENV_PTR, getType(ref.getType())));
                } else {
                    String targetClassName = getInternalName(ref.getFieldRef().declaringClass());
                    Trampoline trampoline = new PutStatic(this.className, targetClassName, 
                            ref.getFieldRef().name(), getDescriptor(ref.getFieldRef().type()));
                    trampolines.add(trampoline);
                    fn = trampoline.getFunctionRef();
                }
                callOrInvoke(stmt, fn, ENV, narrowedResult);
            } else {
                throw new IllegalArgumentException("Unknown type for leftOp: " + leftOp.getClass());
            }
        }
    }

    private void return_(ReturnStmt stmt) {
        /*
         * op is an Immediate.
         */
        Value op = immediate(stmt, (Immediate) stmt.getOp());
        Value value = narrowFromI32Value(function.getType().getReturnType(), op);
        function.add(new Ret(value));
    }
    
    private void returnVoid() {
        function.add(new Ret());
    }
    
    private void if_(IfStmt stmt) {
        ConditionExpr condition = (ConditionExpr) stmt.getCondition();
        Value op1 = immediate(stmt, (Immediate) condition.getOp1());
        Value op2 = immediate(stmt, (Immediate) condition.getOp2());
        Icmp.Condition c = null;
        if (condition instanceof EqExpr) {
            c = Icmp.Condition.eq;
        } else if (condition instanceof NeExpr) {
            c = Icmp.Condition.ne;
        } else if (condition instanceof GtExpr) {
            c = Icmp.Condition.sgt;
        } else if (condition instanceof LtExpr) {
            c = Icmp.Condition.slt;
        } else if (condition instanceof GeExpr) {
            c = Icmp.Condition.sge;
        } else if (condition instanceof LeExpr) {
            c = Icmp.Condition.sle;
        }
        Variable result = function.newVariable(Type.I1);
        function.add(new Icmp(result, c, op1, op2));
        Unit nextUnit = sootMethod.getActiveBody().getUnits().getSuccOf(stmt);
        function.add(new Br(new VariableRef(result), 
                function.newBasicBlockRef(new Label(stmt.getTarget())), 
                function.newBasicBlockRef(new Label(nextUnit))));
    }
    
    private void lookupSwitch(LookupSwitchStmt stmt) {
        Map<IntegerConstant, BasicBlockRef> targets = new HashMap<IntegerConstant, BasicBlockRef>();
        for (int i = 0; i < stmt.getTargetCount(); i++) {
            int value = stmt.getLookupValue(i);
            Unit target = stmt.getTarget(i);
            targets.put(new IntegerConstant(value), function.newBasicBlockRef(new Label(target)));
        }
        BasicBlockRef def = function.newBasicBlockRef(new Label(stmt.getDefaultTarget()));
        Value key = immediate(stmt, (Immediate) stmt.getKey());
        function.add(new Switch(key, def, targets));
    }
    
    private void tableSwitch(TableSwitchStmt stmt) {
        Map<IntegerConstant, BasicBlockRef> targets = new HashMap<IntegerConstant, BasicBlockRef>();
        for (int i = stmt.getLowIndex(); i <= stmt.getHighIndex(); i++) {
            Unit target = stmt.getTarget(i - stmt.getLowIndex());
            targets.put(new IntegerConstant(i), function.newBasicBlockRef(new Label(target)));
        }
        BasicBlockRef def = function.newBasicBlockRef(new Label(stmt.getDefaultTarget()));
        Value key = immediate(stmt, (Immediate) stmt.getKey());
        function.add(new Switch(key, def, targets));
    }
    
    private void goto_(GotoStmt stmt) {
        function.add(new Br(function.newBasicBlockRef(new Label(stmt.getTarget()))));
    }
    
    private void throw_(ThrowStmt stmt) {
        Value obj = immediate(stmt, (Immediate) stmt.getOp());
        checkNull(stmt, obj);
        List<Trap> traps = getTrapsAt(stmt);
        if (!traps.isEmpty()) {
            function.add(new Call(NVM_BC_EXCEPTION_SET, ENV, obj));
            function.add(new Br(function.newBasicBlockRef(new Label(new Label(traps)))));
            recordedTraps.add(traps);
        } else {
            function.add(new Call(NVM_BC_THROW, ENV, obj));
            function.add(new Unreachable());
        }
    }
    
    private void invoke(InvokeStmt stmt) {
        invokeExpr(stmt, stmt.getInvokeExpr());
    }
    
    private void enterMonitor(EnterMonitorStmt stmt) {
        Value op = immediate(stmt, (Immediate) stmt.getOp());
        checkNull(stmt, op);
        callOrInvoke(stmt, NVM_BC_MONITOR_ENTER, ENV, op);
    }
    
    private void exitMonitor(ExitMonitorStmt stmt) {
        Value op = immediate(stmt, (Immediate) stmt.getOp());
        checkNull(stmt, op);
        callOrInvoke(stmt, NVM_BC_MONITOR_EXIT, ENV, op);
    }
    
}
