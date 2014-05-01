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
public class Load extends UnaryOpInstruction {
    private final boolean _volatile;
    private final Ordering ordering;
    private final int alignment;

    public Load(Variable result, Value op) {
        this(result, op, false, null, -1);
    }
    
    public Load(Variable result, Value op, boolean _volatile) {
        this(result, op, _volatile, null, -1);
    }

    public Load(Variable result, Value op, boolean _volatile, Ordering ordering, int alignment) {
        super(result, op);
        this._volatile = _volatile;
        this.ordering = ordering;
        this.alignment = alignment;
    }
    
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(result);
        sb.append(" = load ");
        if (_volatile) {
            sb.append("volatile ");
        }
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
