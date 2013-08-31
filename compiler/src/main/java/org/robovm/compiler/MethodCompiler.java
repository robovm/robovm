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

import static org.robovm.compiler.Functions.*;
import static org.robovm.compiler.Mangler.*;
import static org.robovm.compiler.Types.*;
import static org.robovm.compiler.llvm.Type.*;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.TreeMap;

import org.robovm.compiler.config.Config;
import org.robovm.compiler.llvm.Add;
import org.robovm.compiler.llvm.Alloca;
import org.robovm.compiler.llvm.And;
import org.robovm.compiler.llvm.ArrayType;
import org.robovm.compiler.llvm.Ashr;
import org.robovm.compiler.llvm.BasicBlock;
import org.robovm.compiler.llvm.BasicBlockRef;
import org.robovm.compiler.llvm.Bitcast;
import org.robovm.compiler.llvm.Br;
import org.robovm.compiler.llvm.Call;
import org.robovm.compiler.llvm.Constant;
import org.robovm.compiler.llvm.ConstantBitcast;
import org.robovm.compiler.llvm.ConstantTrunc;
import org.robovm.compiler.llvm.Fadd;
import org.robovm.compiler.llvm.Fdiv;
import org.robovm.compiler.llvm.FloatingPointConstant;
import org.robovm.compiler.llvm.FloatingPointType;
import org.robovm.compiler.llvm.Fmul;
import org.robovm.compiler.llvm.Fpext;
import org.robovm.compiler.llvm.Fptrunc;
import org.robovm.compiler.llvm.Fsub;
import org.robovm.compiler.llvm.Function;
import org.robovm.compiler.llvm.FunctionRef;
import org.robovm.compiler.llvm.FunctionType;
import org.robovm.compiler.llvm.Getelementptr;
import org.robovm.compiler.llvm.Global;
import org.robovm.compiler.llvm.GlobalRef;
import org.robovm.compiler.llvm.Icmp;
import org.robovm.compiler.llvm.Icmp.Condition;
import org.robovm.compiler.llvm.Instruction;
import org.robovm.compiler.llvm.IntegerConstant;
import org.robovm.compiler.llvm.IntegerType;
import org.robovm.compiler.llvm.Invoke;
import org.robovm.compiler.llvm.Label;
import org.robovm.compiler.llvm.Load;
import org.robovm.compiler.llvm.Lshr;
import org.robovm.compiler.llvm.Mul;
import org.robovm.compiler.llvm.NullConstant;
import org.robovm.compiler.llvm.Or;
import org.robovm.compiler.llvm.PointerType;
import org.robovm.compiler.llvm.Ret;
import org.robovm.compiler.llvm.Sext;
import org.robovm.compiler.llvm.Shl;
import org.robovm.compiler.llvm.Sitofp;
import org.robovm.compiler.llvm.Store;
import org.robovm.compiler.llvm.StructureConstantBuilder;
import org.robovm.compiler.llvm.Sub;
import org.robovm.compiler.llvm.Switch;
import org.robovm.compiler.llvm.Trunc;
import org.robovm.compiler.llvm.Type;
import org.robovm.compiler.llvm.Unreachable;
import org.robovm.compiler.llvm.Value;
import org.robovm.compiler.llvm.Variable;
import org.robovm.compiler.llvm.VariableRef;
import org.robovm.compiler.llvm.Xor;
import org.robovm.compiler.llvm.Zext;
import org.robovm.compiler.trampoline.Anewarray;
import org.robovm.compiler.trampoline.Checkcast;
import org.robovm.compiler.trampoline.GetField;
import org.robovm.compiler.trampoline.GetStatic;
import org.robovm.compiler.trampoline.Instanceof;
import org.robovm.compiler.trampoline.Invokeinterface;
import org.robovm.compiler.trampoline.Invokespecial;
import org.robovm.compiler.trampoline.Invokestatic;
import org.robovm.compiler.trampoline.Invokevirtual;
import org.robovm.compiler.trampoline.LdcClass;
import org.robovm.compiler.trampoline.LdcString;
import org.robovm.compiler.trampoline.Multianewarray;
import org.robovm.compiler.trampoline.New;
import org.robovm.compiler.trampoline.PutField;
import org.robovm.compiler.trampoline.PutStatic;
import org.robovm.compiler.trampoline.Trampoline;

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
import soot.UnitBox;
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
import soot.jimple.Jimple;
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
import soot.jimple.NopStmt;
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
    private Map<Unit, List<Trap>> trapsAt;
    private Value env;
    
    private Variable dims;
    
    public MethodCompiler(Config config) {
        super(config);
    }
    
    protected void doCompile(ModuleBuilder moduleBuilder, SootMethod method) {
        function = FunctionBuilder.method(method);
        moduleBuilder.addFunction(function);
        
        env = function.getParameterRef(0);

        if (this.className.equals("java/lang/Object") && "<init>".equals(method.getName())) {
            // Compile Object.<init>(). JLS 12.6.1: "An object o is not finalizable until its constructor has invoked 
            // the constructor for Object on o and that invocation has completed successfully".
            // Object.<init>() calls register_finalizable() in header.ll which checks if the class of 'this' is finalizable.
            // If it is the object will be registered for finalization.
            compileObjectInit();
            return;
        }
        
        trapsAt = new HashMap<Unit, List<Trap>>();
        
        Body body = method.retrieveActiveBody();
        
        if (method.isStatic() && !body.getUnits().getFirst().getBoxesPointingToThis().isEmpty()) {
            // Fix for issue #1. This prevents an NPE in Soot's ArrayBoundsCheckerAnalysis. The NPE
            // occurs for static methods which start with a unit that is the target of some other
            // unit. We work around this by inserting a nop statement as the first unit in such 
            // methods. See http://www.sable.mcgill.ca/listarchives/soot-list/msg01397.html.
            Unit inserPoint = body.getUnits().getFirst();
            body.getUnits().getNonPatchingChain().insertBefore(Jimple.v().newNopStmt(), inserPoint);
        }
        
        PackManager.v().getPack("jtp").apply(body);
        PackManager.v().getPack("jop").apply(body);
        PackManager.v().getPack("jap").apply(body);

        PatchingChain<Unit> units = body.getUnits();
        Map<Unit, List<Unit>> branchTargets = getBranchTargets(body);
        Map<Unit, Integer> trapHandlers = getTrapHandlers(body);
        Map<Unit, Integer> selChanges = new HashMap<Unit, Integer>();
        
        int multiANewArrayMaxDims = 0;
        Set<Local> locals = new HashSet<Local>();
        boolean emitCheckStackOverflow = false;
        for (Unit unit : units) {
            if (unit instanceof DefinitionStmt) {
                DefinitionStmt stmt = (DefinitionStmt) unit;
                if (stmt.getLeftOp() instanceof Local) {
                    Local local = (Local) stmt.getLeftOp();
                    if (!locals.contains(local)) {
                        Type type = getLocalType(local.getType());
                        function.add(new Alloca(function.newVariable(local.getName(), type), type));
                        locals.add(local);
                    }
                }
                if (stmt.getRightOp() instanceof NewMultiArrayExpr) {
                    NewMultiArrayExpr expr = (NewMultiArrayExpr) stmt.getRightOp();
                    multiANewArrayMaxDims = Math.max(multiANewArrayMaxDims, expr.getSizeCount());
                }
                if (stmt.getRightOp() instanceof InvokeExpr) {
                	emitCheckStackOverflow = true;
                }
            }
            if (unit instanceof InvokeStmt) {
            	emitCheckStackOverflow = true;
            }
        }
        
        dims = null;
        if (multiANewArrayMaxDims > 0) {
            dims = function.newVariable("dims", new PointerType(new ArrayType(multiANewArrayMaxDims, I32)));
            function.add(new Alloca(dims, new ArrayType(multiANewArrayMaxDims, I32)));
        }
        
        if (emitCheckStackOverflow) {
        	call(CHECK_STACK_OVERFLOW);
        }
        
        Value trycatchContext = null;
        if (!body.getTraps().isEmpty()) {
            List<List<Trap>> recordedTraps = new ArrayList<List<Trap>>();
            for (Unit unit : units) {
                // Calculate the predecessor units of unit 
                Set<Unit> incoming = new HashSet<Unit>();
                if (units.getFirst() != unit && units.getPredOf(unit).fallsThrough()) {
                    incoming.add(units.getPredOf(unit));
                }
                if (branchTargets.keySet().contains(unit)) {
                    incoming.addAll(branchTargets.get(unit));
                }
                
                if (unit == units.getFirst() || trapHandlers.containsKey(unit) 
                        || trapsDiffer(unit, incoming)) {
                    
                    List<Trap> traps = getTrapsAt(unit);
                    if (traps.isEmpty()) {
                        selChanges.put(unit, 0);
                    } else {
                        int index = recordedTraps.indexOf(traps);
                        if (index == -1) {
                            index = recordedTraps.size();
                            recordedTraps.add(traps);
                        }
                        selChanges.put(unit, index + 1);
                    }
                }
            }
            
            StructureConstantBuilder landingPadsPtrs = new StructureConstantBuilder();
            for (List<Trap> traps : recordedTraps) {
                StructureConstantBuilder landingPads = new StructureConstantBuilder();
                for (Trap trap : traps) {
                    SootClass exClass = trap.getException();
                    StructureConstantBuilder landingPad = new StructureConstantBuilder();
                    if ("java.lang.Throwable".equals(exClass.getName()) || exClass.isPhantom()) {
                        landingPad.add(new NullConstant(I8_PTR));
                    } else {
                        catches.add(getInternalName(exClass));
                        Global g = new Global(mangleClass(exClass) + "_info_struct", I8_PTR, true);
                        if (!moduleBuilder.hasSymbol(g.getName())) {
                            moduleBuilder.addGlobal(g);
                        }
                        landingPad.add(g.ref());
                    }
                    landingPad.add(new IntegerConstant(trapHandlers.get(trap.getHandlerUnit()) + 1));
                    landingPads.add(landingPad.build());
                }
                landingPads.add(new StructureConstantBuilder().add(new NullConstant(I8_PTR)).add(new IntegerConstant(0)).build());
                Global g = moduleBuilder.newGlobal(landingPads.build(), true);
                landingPadsPtrs.add(new ConstantBitcast(g.ref(), I8_PTR));
            }
            Global g = moduleBuilder.newGlobal(landingPadsPtrs.build(), true);
            Variable ctx = function.newVariable(TRYCATCH_CONTEXT_PTR);
            Variable bcCtx = function.newVariable(BC_TRYCATCH_CONTEXT_PTR);
            function.add(new Alloca(bcCtx, BC_TRYCATCH_CONTEXT));
            Variable selPtr = function.newVariable(new PointerType(I32));
            function.add(new Getelementptr(selPtr, bcCtx.ref(), 0, 0, 1));
            function.add(new Store(new IntegerConstant(0), selPtr.ref()));        
            Variable bcCtxLandingPadsPtr = function.newVariable(I8_PTR_PTR);
            function.add(new Getelementptr(bcCtxLandingPadsPtr, bcCtx.ref(), 0, 1));
            function.add(new Store(new ConstantBitcast(g.ref(), I8_PTR), bcCtxLandingPadsPtr.ref()));
            function.add(new Bitcast(ctx, bcCtx.ref(), TRYCATCH_CONTEXT_PTR));
            trycatchContext = ctx.ref();
            Value result = call(RVM_TRYCATCH_ENTER, env, trycatchContext);
            Map<IntegerConstant, BasicBlockRef> alt = new TreeMap<IntegerConstant, BasicBlockRef>();
            for (Entry<Unit, Integer> entry : trapHandlers.entrySet()) {
                alt.put(new IntegerConstant(entry.getValue() + 1), function.newBasicBlockRef(new Label(entry.getKey())));
            }
            function.add(new Switch(result, function.newBasicBlockRef(new Label(units.getFirst())), alt));
            if (!branchTargets.containsKey(units.getFirst())) {
                function.newBasicBlock(new Label(units.getFirst()));
            }
        }
        
        if ("<clinit>".equals(method.getName())) {
            initializeClassFields();
        }
        
        for (Unit unit : units) {
            if (branchTargets.containsKey(unit) || trapHandlers.containsKey(unit)) {
                BasicBlock oldBlock = function.getCurrentBasicBlock();
                function.newBasicBlock(new Label(unit));
                if (oldBlock != null) {
                    Instruction last = oldBlock.last();
                    if (last == null || !isTerminator(last)) {
                        oldBlock.add(new Br(function.newBasicBlockRef(new Label(unit))));
                    }
                }
            }
            
            if (selChanges.containsKey(unit)) {
                int sel = selChanges.get(unit);
                // trycatchContext->sel = sel
                Variable selPtr = function.newVariable(new PointerType(I32));
                function.add(new Getelementptr(selPtr, trycatchContext, 0, 1));
                function.add(new Store(new IntegerConstant(sel), selPtr.ref()));
            }
            
            if (unit instanceof DefinitionStmt) {
                assign((DefinitionStmt) unit);
            } else if (unit instanceof ReturnStmt) {
                if (!body.getTraps().isEmpty()) {
                    trycatchLeave(function);
                }
                return_((ReturnStmt) unit);
            } else if (unit instanceof ReturnVoidStmt) {
                if (!body.getTraps().isEmpty()) {
                    trycatchLeave(function);
                }
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
            } else if (unit instanceof NopStmt) {
                // Ignore
            } else {
                throw new IllegalArgumentException("Unknown Unit type: " + unit.getClass());
            }
        }
    }

    private void compileObjectInit() {
        call(REGISTER_FINALIZABLE, env, function.getParameterRef(1));
        function.add(new Ret());
    }
    
    /**
     * Returns <code>true</code> if the {@link Trap}s at {@link Unit} <code>unit</code>
     * differ from any of those at the {@link Unit}s that branch to <code>unit</code>.
     */
    private boolean trapsDiffer(Unit unit, Collection<Unit> incomingUnits) {
        List<Trap> traps = getTrapsAt(unit);
        for (Unit incomingUnit : incomingUnits) {
            if (!traps.equals(getTrapsAt(incomingUnit))) {
                return true;
            }
        }
        return false;
    }
    
    private Map<Unit, List<Unit>> getBranchTargets(Body body) {
        Map<Unit, List<Unit>> result = new HashMap<Unit, List<Unit>>();
        for (Unit unit : body.getUnits()) {
            if (unit.branches()) {
                List<Unit> targetUnits = new ArrayList<Unit>();
                for (UnitBox ub : unit.getUnitBoxes()) {
                    targetUnits.add(ub.getUnit());
                }
                if (unit.fallsThrough()) {
                    targetUnits.add(body.getUnits().getSuccOf(unit));
                }
                for (Unit targetUnit : targetUnits) {
                    List<Unit> sourceUnits = result.get(targetUnit);
                    if (sourceUnits == null) {
                        sourceUnits = new ArrayList<Unit>();
                        result.put(targetUnit, sourceUnits);
                    }
                    sourceUnits.add(unit);
                }
            }
        }
        return result;
    }
    
    private Map<Unit, Integer> getTrapHandlers(Body body) {
        Map<Unit, Integer> trapHandlers = new HashMap<Unit, Integer>();
        for (Trap trap : body.getTraps()) {
            Unit beginUnit = trap.getBeginUnit();
            Unit endUnit = trap.getEndUnit();
            if (beginUnit != endUnit && !trapHandlers.containsKey(trap.getHandlerUnit())) {
                trapHandlers.put(trap.getHandlerUnit(), trapHandlers.size());
            }
        }
        return trapHandlers;
    }
        
    private void initializeClassFields() {
        for (SootField field : sootMethod.getDeclaringClass().getFields()) {
            if (!field.isStatic()) {
                continue;
            }
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
                    value = call(trampoline.getFunctionRef(), env);
                }
                
                if (value != null) {
                    FunctionRef fn = FunctionBuilder.setter(field).ref();
                    call(fn, env, value);
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
            function.add(new Load(tmp, var, !sootMethod.getActiveBody().getTraps().isEmpty()));
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
            return call(trampoline.getFunctionRef(), env);
        } else if (v instanceof soot.jimple.ClassConstant) {
            // ClassConstant is either the internal name of a class or the descriptor of an array
            String targetClassName = ((soot.jimple.ClassConstant) v).getValue();
            if (isArray(targetClassName) && isPrimitiveComponentType(targetClassName)) {
                String primitiveDesc = targetClassName.substring(1);
                Variable result = function.newVariable(OBJECT_PTR);
                function.add(new Load(result, new ConstantBitcast(
                        new GlobalRef("array_" + primitiveDesc, CLASS_PTR), new PointerType(OBJECT_PTR))));
                return result.ref();
            } else {
                FunctionRef fn = null;
                if (targetClassName.equals(this.className)) {
                    fn = FunctionBuilder.ldcInternal(sootMethod.getDeclaringClass()).ref();
                } else {
                    Trampoline trampoline = new LdcClass(className, ((soot.jimple.ClassConstant) v).getValue());
                    trampolines.add(trampoline);
                    fn = trampoline.getFunctionRef();
                }
                return call(fn, env);
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
    
//    private Value callOrInvoke(Unit unit, Value fn, Value ... args) {
//        Variable result = null;
//        Type returnType = ((FunctionType) fn.getType()).getReturnType();
//        if (returnType != VOID) {
//            result = this.function.newVariable(returnType);
//        }
//        List<Trap> traps = getTrapsAt(unit);
//        if (!traps.isEmpty()) {
//            Label label = new Label();
//            BasicBlockRef to = function.newBasicBlockRef(label);
//            BasicBlockRef unwind = function.newBasicBlockRef(new Label(traps));
//            function.add(new Invoke(result, fn, to, unwind, args));
//            function.newBasicBlock(label);
//            recordedTraps.add(traps);
//        } else {
//            function.add(new Call(result, fn, args));
//        }
//        return result == null ? null : result.ref();
//    }
    
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
        args.add(env);
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
        Value result = null;
        FunctionRef functionRef = Intrinsics.getIntrinsic(sootMethod, stmt, expr);
        if (functionRef == null) {
            if (canCallDirectly(expr)) {
                SootMethod method = this.sootMethod.getDeclaringClass().getMethod(methodRef.name(), 
                        methodRef.parameterTypes(), methodRef.returnType());
                if (method.isSynchronized()) {
                    functionRef = FunctionBuilder.synchronizedWrapper(method).ref();
                } else {
                    functionRef = FunctionBuilder.method(method).ref();
                }
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
        }
        result = call(functionRef, args.toArray(new Value[0]));
        if (result != null) {
            return widenToI32Value(result, methodRef.returnType().equals(CharType.v()));
        } else {
            return null;
        }
    }

    private void checkNull(Stmt stmt, Value base) {
        NullCheckTag nullCheckTag = (NullCheckTag) stmt.getTag("NullCheckTag");
        if (nullCheckTag == null || nullCheckTag.needCheck()) {
            call(CHECK_NULL, env, base);
        }
    }
    
    private void checkBounds(Stmt stmt, Value base, Value index) {
        ArrayCheckTag arrayCheckTag = (ArrayCheckTag) stmt.getTag("ArrayCheckTag");
        if (arrayCheckTag == null || arrayCheckTag.isCheckLower()) {
            call(CHECK_LOWER, env, base, index);
        }
        if (arrayCheckTag == null || arrayCheckTag.isCheckUpper()) {
            call(CHECK_UPPER, env, base, index);
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
            result = function.getParameterRef(1);
        } else if (rightOp instanceof ParameterRef) {
            ParameterRef ref = (ParameterRef) rightOp;
            int index = (sootMethod.isStatic() ? 1 : 2) + ref.getIndex();
            Value p = new VariableRef("p" + index, getType(ref.getType()));
            result = widenToI32Value(p, isUnsigned(ref.getType()));
        } else if (rightOp instanceof CaughtExceptionRef) {
            result = call(BC_EXCEPTION_CLEAR, env);
        } else if (rightOp instanceof ArrayRef) {
            ArrayRef ref = (ArrayRef) rightOp;
            VariableRef base = (VariableRef) immediate(stmt, (Immediate) ref.getBase());
            Value index = immediate(stmt, (Immediate) ref.getIndex());
            checkNull(stmt, base);
            checkBounds(stmt, base, index);
            result = call(getArrayLoad(ref.getType()), base, index);
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
            result = call(fn, env, base);
            result = widenToI32Value(result, isUnsigned(ref.getType()));
        } else if (rightOp instanceof StaticFieldRef) {
            StaticFieldRef ref = (StaticFieldRef) rightOp;
            FunctionRef fn = Intrinsics.getIntrinsic(sootMethod, stmt);
            if (fn == null) {
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
            }
            result = call(fn, env);
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
                        result = call(f, env, op1, op2);
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
                        result = call(f, env, op1, op2);
                    } else {
                        FunctionRef f = rightType == DOUBLE ? DREM : FREM;
                        result = call(f, env, op1, op2);
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
                } else {
                    if (sootTargetType instanceof soot.ArrayType 
                            && ((soot.ArrayType) sootTargetType).getElementType() instanceof PrimType) {
                        soot.Type primType = ((soot.ArrayType) sootTargetType).getElementType();
                        GlobalRef arrayClassPtr = new GlobalRef("array_" + getDescriptor(primType), CLASS_PTR);
                        Variable arrayClass = function.newVariable(CLASS_PTR);
                        function.add(new Load(arrayClass, arrayClassPtr));
                        result = call(CHECKCAST_PRIM_ARRAY, env, arrayClass.ref(), op);
                    } else {
                        String targetClassName = getInternalName(sootTargetType);
                        Trampoline trampoline = new Checkcast(this.className, targetClassName);
                        trampolines.add(trampoline);
                        result = call(trampoline.getFunctionRef(), env, op);
                    }
                }
            } else if (rightOp instanceof InstanceOfExpr) {
                Value op = immediate(stmt, (Immediate) ((InstanceOfExpr) rightOp).getOp());
                soot.Type checkType = ((InstanceOfExpr) rightOp).getCheckType();
                if (checkType instanceof soot.ArrayType 
                        && ((soot.ArrayType) checkType).getElementType() instanceof PrimType) {
                    soot.Type primType = ((soot.ArrayType) checkType).getElementType();
                    GlobalRef arrayClassPtr = new GlobalRef("array_" + getDescriptor(primType), CLASS_PTR);
                    Variable arrayClass = function.newVariable(CLASS_PTR);
                    function.add(new Load(arrayClass, arrayClassPtr));
                    result = call(INSTANCEOF_PRIM_ARRAY, env, arrayClass.ref(), op);
                } else {
                    String targetClassName = getInternalName(checkType);
                    Trampoline trampoline = new Instanceof(this.className, targetClassName);
                    trampolines.add(trampoline);
                    result = call(trampoline.getFunctionRef(), env, op);
                }
            } else if (rightOp instanceof NewExpr) {
                String targetClassName = getInternalName(((NewExpr) rightOp).getBaseType());
                FunctionRef fn = null;
                if (targetClassName.equals(this.className)) {
                    fn = FunctionBuilder.allocator(sootMethod.getDeclaringClass()).ref();
                } else {
                    Trampoline trampoline = new New(this.className, targetClassName);
                    trampolines.add(trampoline);
                    fn = trampoline.getFunctionRef();
                }
                result = call(fn, env);
            } else if (rightOp instanceof NewArrayExpr) {
                NewArrayExpr expr = (NewArrayExpr) rightOp;
                Value size = immediate(stmt, (Immediate) expr.getSize());
                if (expr.getBaseType() instanceof PrimType) {
                    result = call(getNewArray(expr.getBaseType()), env, size);
                } else {
                    String targetClassName = getInternalName(expr.getType());
                    Trampoline trampoline = new Anewarray(this.className, targetClassName);
                    trampolines.add(trampoline);
                    result = call(trampoline.getFunctionRef(), env, size);
                }
            } else if (rightOp instanceof NewMultiArrayExpr) {
                NewMultiArrayExpr expr = (NewMultiArrayExpr) rightOp;
                if (expr.getBaseType().numDimensions == 1 && expr.getBaseType().getElementType() instanceof PrimType) {
                    Value size = immediate(stmt, (Immediate) expr.getSize(0));
                    result = call(getNewArray(expr.getBaseType().getElementType()), env, size);
                } else {
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
                    result = call(trampoline.getFunctionRef(), env, new IntegerConstant(expr.getSizeCount()), dimsI32.ref());
                }
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
            function.add(new Store(result, v, !sootMethod.getActiveBody().getTraps().isEmpty()));
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
                    call(BC_SET_OBJECT_ARRAY_ELEMENT, env, base, index, narrowedResult);
                } else {
                    call(getArrayStore(leftOp.getType()), base, index, narrowedResult);
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
                call(fn, env, base, narrowedResult);
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
                call(fn, env, narrowedResult);
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
        call(BC_THROW, env, obj);
        function.add(new Unreachable());
    }
    
    private void invoke(InvokeStmt stmt) {
        invokeExpr(stmt, stmt.getInvokeExpr());
    }
    
    private void enterMonitor(EnterMonitorStmt stmt) {
        Value op = immediate(stmt, (Immediate) stmt.getOp());
        checkNull(stmt, op);
        call(MONITORENTER, env, op);
    }
    
    private void exitMonitor(ExitMonitorStmt stmt) {
        Value op = immediate(stmt, (Immediate) stmt.getOp());
        checkNull(stmt, op);
        call(MONITOREXIT, env, op);
    }
    
}
