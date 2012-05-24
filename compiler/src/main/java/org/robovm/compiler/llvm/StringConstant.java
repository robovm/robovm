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
public class StringConstant extends Constant {
    private final ArrayType type;
    private final byte[] bytes;

    public StringConstant(byte[] bytes) {
        this.bytes = bytes;
        this.type = new ArrayType(bytes.length, Type.I8);
    }
    
    @Override
    public Type getType() {
        return type;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("c\"");
        for (int i = 0; i < bytes.length; i++) {
            int b = bytes[i] & 0xff;
            if (b < ' ' || b > '~' || b == '"' || b == '\\') {
                sb.append(String.format("\\%02X", b));
            } else {
                sb.append((char) b);
            }
        }
        sb.append('"');
        return sb.toString();
    }
}
