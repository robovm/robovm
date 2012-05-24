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
public class Load extends UnaryOpInstruction {
    private final Ordering ordering;
    private final int alignment;

    public Load(Variable result, Value op) {
        this(result, op, null, -1);
    }
    
    public Load(Variable result, Value op, Ordering ordering, int alignment) {
        super(result, op);
        this.ordering = ordering;
        this.alignment = alignment;
    }
    
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(result);
        sb.append(" = load ");
        if (ordering != null) {
            sb.append("atomic ");
        }
        sb.append(op.getType());
        sb.append(" ");
        sb.append(op);
        if (ordering != null) {
            sb.append(" ");
            sb.append(ordering);
        }
        if (alignment > 0) {
            sb.append(", align ");
            sb.append(alignment);            
        }
        return sb.toString();
    }
}
