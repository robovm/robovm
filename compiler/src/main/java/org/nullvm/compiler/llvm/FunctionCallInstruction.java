/*
 * Copyright (C) 2011 The NullVM Open Source Project
 *
 * TODO: Insert proper license header.
 */
package org.nullvm.compiler.llvm;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;


/**
 *
 * @version $Id$
 */
public abstract class FunctionCallInstruction extends Instruction {
    private final String name;
    private final Variable result;
    private final Value function;
    private final Argument[] args;
    
    protected FunctionCallInstruction(String name, Value function, Value ... args) {
        this(name, null, function, args);
    }
    
    protected FunctionCallInstruction(String name, Value function, Argument ... args) {
        this(name, null, function, args);
    }
    
    protected FunctionCallInstruction(String name, Variable result, Value function, Value ... args) {
        this(name, result, function, valuesToArgs(args));
    }
    
    protected FunctionCallInstruction(String name, Variable result, Value function, Argument ... args) {
        if (!function.isFunction()) {
            throw new IllegalArgumentException("Function type expected");
        }
        this.name = name;
        this.result = result;
        this.function = function;
        this.args = args;
    }
    
    private static Argument[] valuesToArgs(Value[] values) {
        Argument[] arguments = new Argument[values.length];
        for (int i = 0; i < values.length; i++) {
            arguments[i] = new Argument(values[i]);
        }
        return arguments;
    }
    
    @Override
    public Set<Variable> getWritesTo() {
        if (result != null) {
            return Collections.singleton(result);
        }
        return super.getWritesTo();
    }

    @Override
    public Set<VariableRef> getReadsFrom() {
        Set<VariableRef> s = new HashSet<VariableRef>();
        for (Argument a : args) {
            if (a.getValue() instanceof VariableRef) {
                s.add((VariableRef) a.getValue());
            }
        }
        return s;
    }
    
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + Arrays.hashCode(args);
        result = prime * result
                + ((function == null) ? 0 : function.hashCode());
        result = prime * result + ((name == null) ? 0 : name.hashCode());
        result = prime * result
                + ((this.result == null) ? 0 : this.result.hashCode());
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
        FunctionCallInstruction other = (FunctionCallInstruction) obj;
        if (!Arrays.equals(args, other.args)) {
            return false;
        }
        if (function == null) {
            if (other.function != null) {
                return false;
            }
        } else if (!function.equals(other.function)) {
            return false;
        }
        if (name == null) {
            if (other.name != null) {
                return false;
            }
        } else if (!name.equals(other.name)) {
            return false;
        }
        if (result == null) {
            if (other.result != null) {
                return false;
            }
        } else if (!result.equals(other.result)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        if (result != null) {
            sb.append(result.toString());
            sb.append(" = ");
        }
        sb.append(name);
        sb.append(' ');
        FunctionType ftype = (FunctionType) function.getType();
        sb.append(ftype.isVarargs() ? ftype.getDefinition() : ftype.getReturnType().toString());
        sb.append(" ");
        sb.append(function.toString());
        sb.append('(');
        for (int i = 0; i < args.length; i++) {
            if (i > 0) {
                sb.append(", ");
            }
            sb.append(args[i].getType());
            sb.append(' ');
            sb.append(args[i]);
        }
        sb.append(')');
        return sb.toString();
    }
}
