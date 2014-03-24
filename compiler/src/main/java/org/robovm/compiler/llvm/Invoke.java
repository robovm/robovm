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
