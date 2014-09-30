/* Soot - a J*va Optimization Framework
 * Copyright (C) 1999 Patrick Lam
 * Copyright (C) 2004 Ondrej Lhotak
 *
 * This library is free software; you can redistribute it and/or
 * modify it under the terms of the GNU Lesser General Public
 * License as published by the Free Software Foundation; either
 * version 2.1 of the License, or (at your option) any later version.
 *
 * This library is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
 * Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public
 * License along with this library; if not, write to the
 * Free Software Foundation, Inc., 59 Temple Place - Suite 330,
 * Boston, MA 02111-1307, USA.
 */

/*
 * Modified by the Sable Research Group and others 1997-1999.  
 * See the 'credits' file distributed with Soot for the complete list of
 * contributors.  (Soot is distributed at http://www.sable.mcgill.ca/soot)
 */






package soot.jimple.internal;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import soot.RefType;
import soot.SootClass;
import soot.SootMethod;
import soot.SootMethodRef;
import soot.Unit;
import soot.UnitPrinter;
import soot.Value;
import soot.ValueBox;
import soot.jimple.DynamicInvokeExpr;
import soot.jimple.ExprSwitch;
import soot.jimple.InvokeExpr;
import soot.jimple.Jimple;
import soot.tagkit.Tag;
import soot.util.Switch;
@SuppressWarnings({"serial","unchecked","rawtypes"})
public class JDynamicInvokeExpr extends AbstractInvokeExpr  implements DynamicInvokeExpr
{
	protected SootMethodRef bsmRef;
	protected ValueBox[] bsmArgBoxes;
	
    public JDynamicInvokeExpr(SootMethodRef bootstrapMethodRef, List<Value> bootstrapArgs, SootMethodRef methodRef, List<Value> methodArgs)
    {
		if(!methodRef.getSignature().startsWith("<"+SootClass.INVOKEDYNAMIC_DUMMY_CLASS_NAME+": "))
    		throw new IllegalArgumentException("Receiver type of JDynamicInvokeExpr must be "+SootClass.INVOKEDYNAMIC_DUMMY_CLASS_NAME+"!");
		if(!bootstrapMethodRef.returnType().equals(RefType.v("java.lang.invoke.CallSite"))) {
    		throw new IllegalArgumentException("Return type of bootstrap method must be java.lang.invoke.CallSite!");
		}
		
		
    	this.bsmRef = bootstrapMethodRef;
        this.methodRef = methodRef;
        this.bsmArgBoxes = new ValueBox[bootstrapArgs.size()];
        this.argBoxes = new ValueBox[methodArgs.size()];

        for(int i = 0; i < bootstrapArgs.size(); i++)
        {
            // RoboVM note: Changed to handle InvokeExpr values in bootstrap args
            Value v = bootstrapArgs.get(i);
            if (v instanceof InvokeExpr) {
                this.bsmArgBoxes[i] = Jimple.v().newInvokeExprBox(v); 
            } else {
                this.bsmArgBoxes[i] = Jimple.v().newImmediateBox(v);
            }
        }
        for(int i = 0; i < methodArgs.size(); i++)
        {
        	this.argBoxes[i] = Jimple.v().newImmediateBox((Value) methodArgs.get(i));	
        }
    }
    
    public int getBootstrapArgCount()
    {
        return bsmArgBoxes.length;
    }

    public Value getBootstrapArg(int index)
    {
        return bsmArgBoxes[index].getValue();
    }

    
    public Object clone() 
    {
        ArrayList clonedBsmArgs = new ArrayList(getBootstrapArgCount());
        for(int i = 0; i < getBootstrapArgCount(); i++) {
            clonedBsmArgs.add(i, getBootstrapArg(i));
        }
        
        ArrayList clonedArgs = new ArrayList(getArgCount());
        for(int i = 0; i < getArgCount(); i++) {
            clonedArgs.add(i, getArg(i));
        }
        
        return new  JDynamicInvokeExpr(bsmRef, clonedBsmArgs, methodRef, clonedArgs);
    }
    
    public boolean equivTo(Object o)
    {
        if (o instanceof JDynamicInvokeExpr)
        {
            JDynamicInvokeExpr ie = (JDynamicInvokeExpr)o;
            if (!(getMethod().equals(ie.getMethod()) && 
                    bsmArgBoxes.length == ie.bsmArgBoxes.length))
                return false;
            int i = 0;
            for (ValueBox element : bsmArgBoxes) {
				if (!(element.getValue().equivTo(ie.getBootstrapArg(i))))
                    return false;
				i++;
			}
            if (!(getMethod().equals(ie.getMethod()) && 
                    argBoxes.length == ie.argBoxes.length))
                return false;
            i = 0;
            for (ValueBox element : argBoxes) {
				if (!(element.getValue().equivTo(ie.getArg(i))))
                    return false;
				i++;
			}
            if(!methodRef.equals(ie.methodRef)) return false;
            if(!bsmRef.equals(ie.bsmRef)) return false;
            return true;
        }
        return false;
    }
    
    public SootMethod getBootstrapMethod()
    {
        return bsmRef.resolve();
    }


    /** Returns a hash code for this object, consistent with structural equality. */
    public int equivHashCode() 
    {
        return getBootstrapMethod().equivHashCode() * getMethod().equivHashCode() * 17;
    }

    public String toString()
    {
        StringBuffer buffer = new StringBuffer();

        buffer.append(Jimple.DYNAMICINVOKE);
        buffer.append(" \"");
        buffer.append(methodRef.name()); //quoted method name (can be any UTF8 string)
        buffer.append("\" <");
        buffer.append(SootMethod.getSubSignature(""/* no method name here*/, methodRef.parameterTypes(), methodRef.returnType()));
        buffer.append(">(");

        for(int i = 0; i < argBoxes.length; i++)
        {
            if(i != 0)
                buffer.append(", ");

            buffer.append(argBoxes[i].getValue().toString());
        }

        buffer.append(") ");

        buffer.append(bsmRef.getSignature());
        buffer.append("(");
        for(int i = 0; i < bsmArgBoxes.length; i++)
        {
            if(i != 0)
                buffer.append(", ");

            buffer.append(bsmArgBoxes[i].getValue().toString());
        }
        buffer.append(")");

        return buffer.toString();
    }
    
    public void toString(UnitPrinter up)
    {
        up.literal(Jimple.DYNAMICINVOKE);        
        up.literal(" \"" + methodRef.name() + "\" <" + SootMethod.getSubSignature(""/* no method name here*/, methodRef.parameterTypes(), methodRef.returnType()) +">(");        
        
        for(int i = 0; i < argBoxes.length; i++)
        {
            if(i != 0)
                up.literal(", ");
                
            argBoxes[i].toString(up);
        }

        up.literal(") ");
        up.methodRef(bsmRef);
        up.literal("(");
        
        for(int i = 0; i < bsmArgBoxes.length; i++)
        {
            if(i != 0)
                up.literal(", ");
                
            bsmArgBoxes[i].toString(up);
        }

        up.literal(")");
    }

    
    public void apply(Switch sw)
    {
        ((ExprSwitch) sw).caseDynamicInvokeExpr(this);
    }
    
    public List getUseBoxes()
    { 
    	//we do not include the bootstrap-method arguments here because they are static arguments
        List list = new ArrayList();

        for (ValueBox element : argBoxes) {
            list.addAll(element.getValue().getUseBoxes());
            list.add(element);
        }

        return list;
    }
    
    
    public SootMethodRef getBootstrapMethodRef() {
		return bsmRef;
	}
    
    public List getBootstrapArgs()
    {
        List l = new ArrayList();
        for (ValueBox element : bsmArgBoxes)
			l.add(element.getValue());

        return l;
    }
}
