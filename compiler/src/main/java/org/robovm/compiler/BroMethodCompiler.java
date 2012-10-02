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
package org.robovm.compiler;

import static org.robovm.compiler.Functions.*;

import org.robovm.compiler.llvm.Function;
import org.robovm.compiler.llvm.FunctionRef;
import org.robovm.compiler.llvm.Value;
import org.robovm.compiler.trampoline.LdcClass;
import org.robovm.compiler.trampoline.Trampoline;

/**
 * 
 */
public abstract class BroMethodCompiler extends AbstractMethodCompiler {

    public BroMethodCompiler(Config config) {
        super(config);
    }

    protected Value ldcClass(Function fn, String name) {
        FunctionRef ldcClassFn = null;
        if (name.equals(this.className)) {
            ldcClassFn = FunctionBuilder.ldcInternal(this.className).ref();
        } else {
            Trampoline trampoline = new LdcClass(this.className, name);
            trampolines.add(trampoline);
            ldcClassFn = trampoline.getFunctionRef();
        }
        return call(fn, ldcClassFn, fn.getParameterRef(0));
    }
}
