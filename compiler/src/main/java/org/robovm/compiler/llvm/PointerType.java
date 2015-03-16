/*
 * Copyright (C) 2012 RoboVM AB
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
public class PointerType extends UserType {

    private final Type base;

    public PointerType(Type base) {
        this.base = base;
    }
    
    public PointerType(String alias, Type base) {
        super(alias);
        this.base = base;
    }
    
    public Type getBase() {
        return base;
    }
    
    @Override
    public String getDefinition() {
        return base.toString() + "*";
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = super.hashCode();
        result = prime * result + ((base == null) ? 0 : base.hashCode());
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
        PointerType other = (PointerType) obj;
        if (base == null) {
            if (other.base != null) {
                return false;
            }
        } else if (!base.equals(other.base)) {
            return false;
        }
        return true;
    }

}
