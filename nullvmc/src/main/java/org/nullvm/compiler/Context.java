/*
 * Copyright (C) 2010 The NullVM Open Source Project
 *
 * TODO: Insert proper license header.
 */
package org.nullvm.compiler;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.nullvm.compiler.llvm.BasicBlock;
import org.nullvm.compiler.llvm.Function;

import soot.Body;
import soot.PatchingChain;
import soot.SootMethod;
import soot.Trap;
import soot.Unit;
import soot.Value;
import soot.jimple.ArrayRef;
import soot.jimple.DefinitionStmt;
import soot.jimple.GotoStmt;
import soot.jimple.IfStmt;
import soot.jimple.InvokeExpr;
import soot.jimple.InvokeStmt;
import soot.jimple.LookupSwitchStmt;
import soot.jimple.TableSwitchStmt;
import soot.util.Chain;


/**
 *
 * @version $Id$
 */
public class Context {
    private Function currentFunction;
    private Unit currentUnit;
    private List<Trap> currentTraps;
    private Map<Unit, List<Trap>> traps = new HashMap<Unit, List<Trap>>();
    private SootMethod currentMethod;
    private Body currentBody;
    private Map<Unit, String> labels;
    private Set<List<Trap>> recordedTraps = new HashSet<List<Trap>>();
    
    public Function f() {
        return currentFunction;
    }

    public BasicBlock bb() {
        return f().getCurrentBasicBlock();
    }
    
    public void setCurrentFunction(Function currentFunction) {
        this.currentFunction = currentFunction;
    }

    public Unit getCurrentUnit() {
        return currentUnit;
    }

    public void setCurrentUnit(Unit currentUnit) {
        currentTraps = null;
        this.currentUnit = currentUnit;
    }

    public Unit getNextUnit() {
        return currentBody.getUnits().getSuccOf(currentUnit);
    }
    
    public SootMethod getCurrentMethod() {
        return currentMethod;
    }

    public void setCurrentMethod(SootMethod currentMethod) {
        this.currentMethod = currentMethod;
    }

    public Body getCurrentBody() {
        return currentBody;
    }

    public void setCurrentBody(Body currentBody) {
        this.currentBody = currentBody;
    }

//    public boolean hasLabel(Unit u) {
//        return getLabels(currentBody).containsKey(u);
//    }
//    
//    public String getLabel(Unit u) {
//        String s = getLabels(currentBody).get(u);
//        if (s == null) {
//            throw new IllegalArgumentException();
//        }
//        return s;
//    }
    
    public List<Trap> getCurrentTraps() {
        if (currentTraps == null) {
            currentTraps = getTrapsAt(currentUnit);
        }
        return currentTraps;
    }
    
    public Trap getCurrentTrap() {
        getCurrentTraps();
        return currentTraps.get(currentTraps.size() - 1);
    }

    public void recordCurrentTraps() {
        recordedTraps.add(getCurrentTraps());
    }
    
    public Set<List<Trap>> getRecordedTraps() {
        return recordedTraps;
    }
    
    public boolean isJumpTarget(Unit unit) {
        PatchingChain<Unit> units = currentBody.getUnits();
        for (Unit u : units) {
            if (u instanceof GotoStmt) {
                Unit target = ((GotoStmt) u).getTarget();
                if (target == unit) {
                    return true;
                }
            } else if (u instanceof IfStmt) {
                Unit target = ((IfStmt) u).getTarget();
                if (target == unit || unit == units.getSuccOf(u)) {
                    return true;
                }
            } else if (u instanceof LookupSwitchStmt) {
                LookupSwitchStmt stmt = (LookupSwitchStmt) u;
                if (unit == stmt.getDefaultTarget() || stmt.getTargets().contains(unit)) {
                    return true;
                }
            } else if (u instanceof TableSwitchStmt) {
                TableSwitchStmt stmt = (TableSwitchStmt) u;
                if (unit == stmt.getDefaultTarget() || stmt.getTargets().contains(unit)) {
                    return true;
                }
            }
        }
        return false;
    }
    
    public boolean isTrapHandler(Unit unit) {
        for (Trap trap : currentBody.getTraps()) {
            Unit beginUnit = trap.getBeginUnit();
            Unit endUnit = trap.getEndUnit();
            if (beginUnit != endUnit && unit == trap.getHandlerUnit()) {
                return true;
            }
        }
        return false;
    }
    
    private Map<Unit, String> getLabels(Body body) {
        if (labels != null) {
            return labels;
        }
        Set<Unit> labeledUnits = new HashSet<Unit>();
        PatchingChain<Unit> units = body.getUnits();
        labeledUnits.add(units.getFirst());
        for (Unit u : units) {
            if (u instanceof GotoStmt || u instanceof IfStmt) {
                Unit target = u instanceof GotoStmt ? ((GotoStmt) u).getTarget() : ((IfStmt) u).getTarget();
                labeledUnits.add(target);
                if (u != units.getLast()) {
                    labeledUnits.add(units.getSuccOf(u));
                }
            } else if (hasTrap(u)) {
                
                if (u instanceof InvokeStmt) {
                    labeledUnits.add(units.getSuccOf(u));
                } else if (u instanceof DefinitionStmt) {
                    DefinitionStmt stmt = (DefinitionStmt) u;
                    Value leftOp = stmt.getLeftOp();
                    Value rightOp = stmt.getRightOp();
                    if (rightOp instanceof InvokeExpr) {
                        labeledUnits.add(units.getSuccOf(u));
                    } else if (leftOp instanceof ArrayRef) {
                        
                    }
                }
                
            }
        }
        
        for (Trap trap : body.getTraps()) {
            if (trap.getBeginUnit() != trap.getEndUnit()) {
                labeledUnits.add(trap.getHandlerUnit());
            }
        }
        
        labels = new HashMap<Unit, String>();
        for (Unit u : units) {
            if (labeledUnits.contains(u)) {
                labels.put(u, "label" + labels.size());
            }
        }
        return labels;
    }
    
    public boolean hasTrap(Unit u) {
        return !getTrapsAt(u).isEmpty();
    }
    
    public boolean hasTrapForNullPointerEx(Unit u) {
        // TODO: Implement
        return !getTrapsAt(u).isEmpty();
    }
    
    public boolean hasTrapForArrayIndexOutOfBoundsEx(Unit u) {
        // TODO: Implement
        return !getTrapsAt(u).isEmpty();
    }
    
    public boolean hasTrapForArithmeticEx(Unit u) {
        // TODO: Implement
        return !getTrapsAt(u).isEmpty();
    }
    
    public List<Trap> getTrapsAt(Unit u) {
        List<Trap> result = this.traps.get(u);
        if (result == null) {
            Chain<Trap> traps = currentBody.getTraps();
            if (traps.isEmpty()) {
                result = Collections.emptyList();
            } else {
                result = new ArrayList<Trap>();
                PatchingChain<Unit> units = currentBody.getUnits();
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
            this.traps.put(u, result);
        }
        return result;
    }
}
