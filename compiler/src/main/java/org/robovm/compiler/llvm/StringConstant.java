/*
 * Copyright (C) 2012 Trillian Mobile AB
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
        escape(sb, bytes);
        sb.append('"');
        return sb.toString();
    }

    static void escape(StringBuilder sb, byte[] bytes) {
        for (int i = 0; i < bytes.length; i++) {
            int b = bytes[i] & 0xff;
            if (b < ' ' || b > '~' || b == '"' || b == '\\') {
                sb.append(String.format("\\%02X", b));
            } else {
                sb.append((char) b);
            }
        }
    }
}
