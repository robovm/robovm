/*
 * Copyright (C) 2014 Trillian Mobile AB
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
package org.robovm.compiler.plugin.lambda;

import soot.BooleanType;
import soot.ByteType;
import soot.CharType;
import soot.DoubleType;
import soot.FloatType;
import soot.IntType;
import soot.LongType;
import soot.ShortType;
import soot.SootResolver;
import soot.VoidType;

public class SootSClassLookup implements SClassLookup {

    private soot.Type resolve(String descriptor) {
        switch (descriptor.charAt(0)) {
        case 'Z':
            return BooleanType.v();
        case 'B':
            return ByteType.v();
        case 'S':
            return ShortType.v();
        case 'C':
            return CharType.v();
        case 'I':
            return IntType.v();
        case 'J':
            return LongType.v();
        case 'F':
            return FloatType.v();
        case 'D':
            return DoubleType.v();
        case 'V':
            return VoidType.v();
        case '[':
            return resolve(descriptor.substring(1)).makeArrayType();
        default: // 'L'
            String className = descriptor.substring(1, descriptor.length() - 1).replace('/', '.');
            return SootResolver.v().makeClassRef(className).getType();
        }
    }

    @Override
    public SClass lookup(String descriptor) {
        return new SootSClass(resolve(descriptor));
    }
}
