/*
 * Copyright (C) 2014 Trillian AB
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
package org.robovm.compiler.util.generic;

import java.util.Collection;

import soot.RefType;
import soot.SootClass;

/**
 * Abstract base type for the {@link Type} implementations which wraps Soot
 * types.
 */
public abstract class SootBaseType implements Type {

    protected Type wrapType(soot.Type t) {
        if (t instanceof RefType) {
            return new SootClassType(((RefType) t).getSootClass());
        } else {
            return new SootTypeType(t);
        }
    }

    protected Type[] wrapTypes(Collection<soot.Type> types) {
        Type[] result = new Type[types.size()];
        int i = 0;
        for (soot.Type t : types) {
            result[i++] = wrapType(t);
        }
        return result;
    }

    protected SootClassType[] wrapClasses(Collection<SootClass> classes) {
        SootClassType[] result = new SootClassType[classes.size()];
        int i = 0;
        for (SootClass c : classes) {
            result[i++] = new SootClassType(c);
        }
        return result;
    }

}
