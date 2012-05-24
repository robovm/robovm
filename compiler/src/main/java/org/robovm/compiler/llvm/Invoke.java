/*
 * Copyright (C) 2012 RoboVM
 *
 * TODO: Insert proper license header.
 */
package org.robovm.compiler.llvm;


/**
 *
 * @version $Id$
 */
public class Invoke extends FunctionCallInstruction {
    private final BasicBlockRef to;
    private final BasicBlockRef unwind;
    
    public Invoke(Value function, BasicBlockRef to, BasicBlockRef unwind, Value ... args) {
        super("invoke", function, args);
        this.to = to;
        this.unwind = unwind;
    }
    
    public Invoke(Variable result, Value function, BasicBlockRef to, BasicBlockRef unwind, Value ... args) {
        super("invoke", result, function, args);
        this.to = to;
        this.unwind = unwind;
    }

    public Invoke(Value function, BasicBlockRef to, BasicBlockRef unwind, Argument... args) {
        super("invoke", function, args);
        this.to = to;
        this.unwind = unwind;
    }

    public Invoke(Variable result, Value function, BasicBlockRef to, BasicBlockRef unwind, Argument... args) {
        super("invoke", result, function, args);
        this.to = to;
        this.unwind = unwind;
    }

    
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = super.hashCode();
        result = prime * result + ((to == null) ? 0 : to.hashCode());
        result = prime * result + ((unwind == null) ? 0 : unwind.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!super.equals(obj)) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        Invoke other = (Invoke) obj;
        if (to == null) {
            if (other.to != null) {
                return false;
            }
        } else if (!to.equals(other.to)) {
            return false;
        }
        if (unwind == null) {
            if (other.unwind != null) {
                return false;
            }
        } else if (!unwind.equals(other.unwind)) {
            return false;
        }
        return true;
    }
    
    @Override
    public String toString() {
        String s = super.toString();
        return s + " to label %" + to + " unwind label %" + unwind;
    }
}
