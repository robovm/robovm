/*
 * Copyright (C) 2011 The NullVM Open Source Project
 *
 * TODO: Insert proper license header.
 */
package org.nullvm.compiler.llvm;

/**
 *
 * @version $Id$
 */
public class ConstantGetelementptr extends Constant {
    private final Constant cst;
    private final int[] idx;

    public ConstantGetelementptr(Constant cst, int ... idx) {
        if (!cst.isPointer()) {
            throw new IllegalArgumentException("PointerType expected");
        }
        if (!(((PointerType) cst.getType()).getBase() instanceof AggregateType)) {
            throw new IllegalArgumentException("PointerType should point to AggregateType");
        }
        if (idx == null || idx.length == 0) {
            throw new IllegalArgumentException("No indexes");
        }
        this.cst = cst;
        this.idx = idx;
    }
    
    @Override
    public Type getType() {
        Type t = (AggregateType) ((PointerType) cst.getType()).getBase();
        for (int i = 1; i < idx.length; i++) {
            t = ((AggregateType) t).getTypeAt(idx[i]);
        }
        return new PointerType(t);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("getelementptr (");
        sb.append(cst.getType());
        sb.append(' ');
        sb.append(cst);
        sb.append(' ');
        for (int i = 0; i < idx.length; i++) {
            sb.append(", i32 ");
            sb.append(idx[i]);
        }
        sb.append(")");
        return sb.toString();
    }
}
