/*
 * Copyright (C) 2012 RoboVM
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
package org.robovm.compiler.trampoline;

import static org.robovm.compiler.Mangler.*;
import static org.robovm.compiler.Types.*;

import org.robovm.compiler.llvm.FunctionType;


/**
 *
 * @version $Id$
 */
public class LdcString extends Trampoline {
    private static final long serialVersionUID = 1L;
    
    public LdcString(String callingClass, String string) {
        super(callingClass, string);
    }

    @Override
    public FunctionType getFunctionType() {
        return new FunctionType(OBJECT_PTR, ENV_PTR);
    }
    
    @Override
    public String toString() {
        return getClass().getSimpleName() + "_" + mangleString(target);
    }
}
