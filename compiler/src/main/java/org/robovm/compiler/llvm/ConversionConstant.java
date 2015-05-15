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
public abstract class ConversionConstant extends Constant {
    private final String name;
    private final Constant cst;
    private final Type type;

    protected ConversionConstant(String name, Constant cst, Type type) {
        this.name = name;
        this.cst = cst;
        this.type = type;
    }
    
    @Override
    public Type getType() {
        return type;
    }

    @Override
    public String toString() {
        return name + " (" + cst.getType() + " " + cst + " to " + type + ")";
    }
}
