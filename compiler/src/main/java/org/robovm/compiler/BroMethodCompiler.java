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

import static org.robovm.compiler.Bro.*;

import java.util.ArrayList;

import org.robovm.compiler.llvm.Argument;
import org.robovm.compiler.llvm.Function;
import org.robovm.compiler.llvm.Type;
import org.robovm.compiler.llvm.Value;
import org.robovm.compiler.llvm.VariableRef;

/**
 * 
 */
public abstract class BroMethodCompiler extends AbstractMethodCompiler {

    public BroMethodCompiler(Config config) {
        super(config);
    }

    protected Value marshalBridgeArgs(Function fn) {
        Type[] parameterTypes = fn.getType().getParameterTypes();
        String[] parameterNames = fn.getParameterNames();
        ArrayList<Argument> args = new ArrayList<Argument>();
        for (int i = 0; i < parameterTypes.length; i++) {
            args.add(new Argument(new VariableRef(parameterNames[i], parameterTypes[i])));
        }
        
        // Remove Env* from args
        args.remove(0);
        if (!sootMethod.isStatic()) {
            // Remove receiver from args
            args.remove(0);
        }
        
        for (int i = 0; i < sootMethod.getParameterCount(); i++) {
            soot.Type type = sootMethod.getParameterType(i);
            if (needsMarshaler(type)) {
                //callMarshaler();
            }
        }
        
        return null;
    }
    
    

}
