/* Soot - a J*va Optimization Framework
 * Copyright (C) 2003 John Jorgensen
 *
 * This library is free software; you can redistribute it and/or
 * modify it under the terms of the GNU Library General Public
 * License as published by the Free Software Foundation; either
 * version 2 of the License, or (at your option) any later version.
 *
 * This library is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
 * Library General Public License for more details.
 *
 * You should have received a copy of the GNU Library General Public
 * License along with this library; if not, write to the
 * Free Software Foundation, Inc., 59 Temple Place - Suite 330,
 * Boston, MA 02111-1307, USA.
 */

package soot.toolkits.exceptions;

import java.util.Iterator;
import soot.*;
import soot.jimple.*;

/**
 * A {@link ThrowAnalysis} which returns the set of runtime exceptions
 * and errors that might be thrown by the bytecode instructions
 * represented by a unit, as indicated by the Java Virtual Machine
 * specification.  I.e. this analysis is based entirely on the
 * &ldquo;opcode&rdquo; of the unit, the types of its arguments, and
 * the values of constant arguments.
 * 
 * <p>The <code>mightThrow</code> methods could be declared static.
 * They are left virtual to facilitate testing. For example,
 * to verify that the expressions in a method call are actually being
 * examined, a test case can override the mightThrow(SootMethod)
 * with an implementation which returns the empty set instead of
 * all possible exceptions.
 */
public class UnitThrowAnalysis extends AbstractThrowAnalysis {

    // Cache the response to mightThrowImplicitly():
    private final ThrowableSet implicitThrowExceptions 
	= ThrowableSet.Manager.v().VM_ERRORS
	.add(ThrowableSet.Manager.v().NULL_POINTER_EXCEPTION)
	.add(ThrowableSet.Manager.v().ILLEGAL_MONITOR_STATE_EXCEPTION);

    /**
     * Constructs a <code>UnitThrowAnalysis</code> for inclusion in 
     * Soot's global variable manager, {@link G}.
     *
     * @param g guarantees that the constructor may only be called 
     * from {@link Singletons}.
     */
    public UnitThrowAnalysis(Singletons.Global g) {}

    /**
     * A protected constructor for use by unit tests.
     */
    protected UnitThrowAnalysis() {}

    /**
     * Returns the single instance of <code>UnitThrowAnalysis</code>.
     *
     * @return Soot's <code>UnitThrowAnalysis</code>.
     */
    public static UnitThrowAnalysis v() { return G.v().soot_toolkits_exceptions_UnitThrowAnalysis(); }


    public ThrowableSet mightThrow(Unit u) {
	UnitSwitch sw = new UnitSwitch();
	u.apply(sw);
	return sw.getResult();
    }

    public ThrowableSet mightThrowImplicitly(ThrowStmt t) {
	return implicitThrowExceptions;
    }
	
    
    ThrowableSet mightThrow(Value v) {
	ValueSwitch sw = new ValueSwitch();
	v.apply(sw);
	return sw.getResult();
    }


    /**
     * Returns the set of types that might be thrown as a result of
     * calling the specified method.
     *
     * @param m method whose exceptions are to be returned.
     *
     * @return a representation of the set of {@link
     * java.lang.Throwable Throwable} types that <code>m</code> might
     * throw.
     */
    ThrowableSet mightThrow(SootMethod m) {
	// In the absence of an interprocedural analysis,
	// m could throw anything.
	return ThrowableSet.Manager.v().ALL_THROWABLES;
    }


    private static final IntConstant INT_CONSTANT_ZERO = IntConstant.v(0);
    private static final LongConstant LONG_CONSTANT_ZERO = LongConstant.v(0);


    protected class UnitSwitch implements /*InstSwitch,*/ StmtSwitch {

	private final ThrowableSet.Manager mgr = ThrowableSet.Manager.v();

	// Asynchronous errors are always possible:
	private ThrowableSet result = mgr.VM_ERRORS;
	
	ThrowableSet getResult() {
	    return result;
	}

	public void caseAssignStmt(AssignStmt s) {
	    Value lhs = s.getLeftOp();
	    if (lhs instanceof ArrayRef && 
		(lhs.getType() instanceof UnknownType ||
		 lhs.getType() instanceof RefType)) {
		// This corresponds to an aastore byte code.
		result = result.add(mgr.ARRAY_STORE_EXCEPTION);
	    }
	    result = result.add(mightThrow(s.getLeftOp()));
	    result = result.add(mightThrow(s.getRightOp()));
	}

	public void caseBreakpointStmt(BreakpointStmt s) {}

	public void caseEnterMonitorStmt(EnterMonitorStmt s) {
	    result = result.add(mgr.NULL_POINTER_EXCEPTION);
	    result = result.add(mightThrow(s.getOp()));
	}

	public void caseExitMonitorStmt(ExitMonitorStmt s) {
	    result = result.add(mgr.ILLEGAL_MONITOR_STATE_EXCEPTION);
	    result = result.add(mgr.NULL_POINTER_EXCEPTION);
	    result = result.add(mightThrow(s.getOp()));
	}

	public void caseGotoStmt(GotoStmt s) {
	}

	public void caseIdentityStmt(IdentityStmt s) {}
	// Perhaps IdentityStmt shouldn't even return VM_ERRORS,
	// since it corresponds to no bytecode instructions whatsoever.

	public void caseIfStmt(IfStmt s) {
	    result = result.add(mightThrow(s.getCondition()));
	}

	public void caseInvokeStmt(InvokeStmt s) {
	    result = result.add(mightThrow(s.getInvokeExpr()));
	}

	public void caseLookupSwitchStmt(LookupSwitchStmt s) {
	    result = result.add(mightThrow(s.getKey()));
	}
	    
	public void caseNopStmt(NopStmt s) {
	}

	public void caseRetStmt(RetStmt s) {
	    // Soot should never produce any RetStmt, since
	    // it implements jsr with gotos.
	}

	public void caseReturnStmt(ReturnStmt s) {
	    result = result.add(mgr.ILLEGAL_MONITOR_STATE_EXCEPTION);
	    result = result.add(mightThrow(s.getOp()));
	}

	public void caseReturnVoidStmt(ReturnVoidStmt s) {
	    result = result.add(mgr.ILLEGAL_MONITOR_STATE_EXCEPTION);
	}

	public void caseTableSwitchStmt(TableSwitchStmt s) {
	    result = result.add(mightThrow(s.getKey()));
	}

	public void caseThrowStmt(ThrowStmt s) {
	    result = mightThrowImplicitly(s);
	    result = result.add(mightThrowExplicitly(s));
	}

	public void defaultCase(Object obj) {
	}
    }


    protected class ValueSwitch implements JimpleValueSwitch {

	private final ThrowableSet.Manager mgr = 
	    ThrowableSet.Manager.v();

	// Asynchronous errors are always possible:
	private ThrowableSet result = mgr.VM_ERRORS;
	
	ThrowableSet getResult() {
	    return result;
	}


	// Declared by ConstantSwitch interface:

	public void caseDoubleConstant(DoubleConstant c) {
	}

	public void caseFloatConstant(FloatConstant c) {
	}

	public void caseIntConstant(IntConstant c) {
	}

	public void caseLongConstant(LongConstant c) {
	}

	public void caseNullConstant(NullConstant c) {
	}

	public void caseStringConstant(StringConstant c) {
	}

	public void caseClassConstant(ClassConstant c) {
	}


	// Declared by ExprSwitch interface:

	public void caseAddExpr(AddExpr expr) {
	    caseBinopExpr(expr);
	}

	public void caseAndExpr(AndExpr expr) {
	    caseBinopExpr(expr);
	}

	public void caseCmpExpr(CmpExpr expr) {
	    caseBinopExpr(expr);
	}

	public void caseCmpgExpr(CmpgExpr expr) {
	    caseBinopExpr(expr);
	}

	public void caseCmplExpr(CmplExpr expr) {
	    caseBinopExpr(expr);
	}

	public void caseDivExpr(DivExpr expr) {
	    caseBinopDivExpr(expr);
	}

	public void caseEqExpr(EqExpr expr) {
	    caseBinopExpr(expr);
	}

	public void caseNeExpr(NeExpr expr) {
	    caseBinopExpr(expr);
	}

	public void caseGeExpr(GeExpr expr) {
	    caseBinopExpr(expr);
	}

	public void caseGtExpr(GtExpr expr) {
	    caseBinopExpr(expr);
	}

	public void caseLeExpr(LeExpr expr) {
	    caseBinopExpr(expr);
	}

	public void caseLtExpr(LtExpr expr) {
	    caseBinopExpr(expr);
	}

	public void caseMulExpr(MulExpr expr) {
	    caseBinopExpr(expr);
	}

	public void caseOrExpr(OrExpr expr) {
	    caseBinopExpr(expr);
	}

	public void caseRemExpr(RemExpr expr) {
	    caseBinopDivExpr(expr);
	}

	public void caseShlExpr(ShlExpr expr) {
	    caseBinopExpr(expr);
	}

	public void caseShrExpr(ShrExpr expr) {
	    caseBinopExpr(expr);
	}

	public void caseUshrExpr(UshrExpr expr) {
	    caseBinopExpr(expr);
	}

	public void caseSubExpr(SubExpr expr) {
	    caseBinopExpr(expr);
	}

	public void caseXorExpr(XorExpr expr) {
	    caseBinopExpr(expr);
	}

	public void caseInterfaceInvokeExpr(InterfaceInvokeExpr expr) {
	    caseInstanceInvokeExpr(expr);
	}

	public void caseSpecialInvokeExpr(SpecialInvokeExpr expr) {
	    caseInstanceInvokeExpr(expr);
	}

	public void caseStaticInvokeExpr(StaticInvokeExpr expr) {
	    result = result.add(mgr.INITIALIZATION_ERRORS);
	    for (int i = 0; i < expr.getArgCount(); i++) {
		result = result.add(mightThrow(expr.getArg(i)));
	    }
	    result = result.add(mightThrow(expr.getMethod()));
	}

	public void caseVirtualInvokeExpr(VirtualInvokeExpr expr) {
	    caseInstanceInvokeExpr(expr);
	}

	public void caseCastExpr(CastExpr expr) {
	    result = result.add(mgr.RESOLVE_CLASS_ERRORS);
	    Type fromType = expr.getOp().getType();
	    Type toType = expr.getCastType();
	    if (toType instanceof RefLikeType) {
		// fromType might still be unknown when we are called,
		// but toType will have a value.
		FastHierarchy h = Scene.v().getOrMakeFastHierarchy();
		if (fromType == null || fromType instanceof UnknownType ||
		    ((! (fromType instanceof NullType)) &&
		     (! h.canStoreType(fromType, toType)))) {
		    result = result.add(mgr.CLASS_CAST_EXCEPTION);
		}
	    }
	    result = result.add(mightThrow(expr.getOp()));
	}
		
	public void caseInstanceOfExpr(InstanceOfExpr expr) {
	    result = result.add(mgr.RESOLVE_CLASS_ERRORS);
	    result = result.add(mightThrow(expr.getOp()));
	}

	public void caseNewArrayExpr(NewArrayExpr expr) {
	    if (expr.getBaseType() instanceof RefLikeType) {
		result = result.add(mgr.RESOLVE_CLASS_ERRORS);
	    }
	    Value count = expr.getSize();
	    if ((! (count instanceof IntConstant)) ||
		(((IntConstant) count).lessThan(INT_CONSTANT_ZERO)
		                      .equals(INT_CONSTANT_ZERO))) {
		result = result.add(mgr.NEGATIVE_ARRAY_SIZE_EXCEPTION);
	    }
	    result = result.add(mightThrow(count));
	}

	public void caseNewMultiArrayExpr(NewMultiArrayExpr expr) {
	    result = result.add(mgr.RESOLVE_CLASS_ERRORS);
	    for (int i = 0; i < expr.getSizeCount(); i++) {
		Value count = expr.getSize(i);
		if ((! (count instanceof IntConstant)) ||
		    (((IntConstant) count).lessThan(INT_CONSTANT_ZERO)
		                          .equals(INT_CONSTANT_ZERO))) {
		    result = result.add(mgr.NEGATIVE_ARRAY_SIZE_EXCEPTION);
		}
		result = result.add(mightThrow(count));
	    }
	}

	public void caseNewExpr(NewExpr expr) {
	    result = result.add(mgr.INITIALIZATION_ERRORS);
	    for (Iterator i = expr.getUseBoxes().iterator(); i.hasNext(); ) {
		ValueBox box = (ValueBox) i.next();
		result = result.add(mightThrow(box.getValue()));
	    }
	}

	public void caseLengthExpr(LengthExpr expr) {
	    result = result.add(mgr.NULL_POINTER_EXCEPTION);
	    result = result.add(mightThrow(expr.getOp()));
	}

	public void caseNegExpr(NegExpr expr) {
	    result = result.add(mightThrow(expr.getOp()));
	}


	// Declared by RefSwitch interface:

	public void caseArrayRef(ArrayRef ref) {
	    result = result.add(mgr.NULL_POINTER_EXCEPTION);
	    result = result.add(mgr.ARRAY_INDEX_OUT_OF_BOUNDS_EXCEPTION);
	    result = result.add(mightThrow(ref.getBase()));
	    result = result.add(mightThrow(ref.getIndex()));
	}

	public void caseStaticFieldRef(StaticFieldRef ref) {
	    result = result.add(mgr.INITIALIZATION_ERRORS);
	}

	public void caseInstanceFieldRef(InstanceFieldRef ref) {
	    result = result.add(mgr.RESOLVE_FIELD_ERRORS);
	    result = result.add(mgr.NULL_POINTER_EXCEPTION);
	    result = result.add(mightThrow(ref.getBase()));
	}

	public void caseParameterRef(ParameterRef v) {
	}

	public void caseCaughtExceptionRef(CaughtExceptionRef v) {
	}

	public void caseThisRef(ThisRef v) {
	}

	public void caseLocal(Local l) {
	}

	public void caseNewInvokeExpr(NewInvokeExpr e) {
	    caseStaticInvokeExpr(e);
	}

	public void defaultCase(Object obj) {
	}

	// The remaining cases are not declared by GrimpValueSwitch,
	// but are used to factor out code common to several cases.

	private void caseBinopExpr(BinopExpr expr) {
	    result = result.add(mightThrow(expr.getOp1()));
	    result = result.add(mightThrow(expr.getOp2()));
	}

	private void caseBinopDivExpr(BinopExpr expr) {
	    // Factors out code common to caseDivExpr and caseRemExpr.
	    // The checks against constant divisors would perhaps be
	    // better performed in a later pass, post-constant-propagation.
	    Value divisor = expr.getOp2();
	    Type divisorType = divisor.getType();
	    if (divisorType instanceof UnknownType) {
		result = result.add(mgr.ARITHMETIC_EXCEPTION);
	    } else if ((divisorType instanceof IntegerType) &&
		((! (divisor instanceof IntConstant)) ||
		 (((IntConstant) divisor).equals(INT_CONSTANT_ZERO)))) {
		result = result.add(mgr.ARITHMETIC_EXCEPTION);
	    } else if ((divisorType == LongType.v()) &&
		       ((! (divisor instanceof LongConstant)) ||
			(((LongConstant) divisor).equals(LONG_CONSTANT_ZERO)))) {
		result = result.add(mgr.ARITHMETIC_EXCEPTION);
	    }
	    caseBinopExpr(expr);
	}

	private void caseInstanceInvokeExpr(InstanceInvokeExpr expr) {
	    result = result.add(mgr.RESOLVE_METHOD_ERRORS);
	    result = result.add(mgr.NULL_POINTER_EXCEPTION);
	    for (int i = 0; i < expr.getArgCount(); i++) {
		result = result.add(mightThrow(expr.getArg(i)));
	    }
	    result = result.add(mightThrow(expr.getBase()));
	    result = result.add(mightThrow(expr.getMethod()));
	}
    }
}
