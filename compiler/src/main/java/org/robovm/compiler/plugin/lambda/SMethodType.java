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

import java.util.List;

/**
 * 
 */
public class SMethodType {
    private final SClass<?> returnType;
    private final List<SClass<?>> parameters;
    
    public SMethodType(SClass<?> returnType, List<SClass<?>> parameters) {
        this.returnType = returnType;
        this.parameters = parameters;
    }

    public int parameterCount() {
        return parameters.size();
    }

    public SClass<?> parameterType(int i) {
        return parameters.get(i);
    }

    public SClass<?> returnType() {
        return returnType;
    }

    public String toMethodDescriptorString() {
        StringBuilder sb = new StringBuilder();
        sb.append("(");
        for (SClass<?> c : parameters) {
            sb.append(c.getDescriptor());
        }
        sb.append(")");
        sb.append(returnType.getDescriptor());
        return sb.toString();
    }

    public SMethodType changeReturnType(SClass<?> newReturnType) {
        return new SMethodType(newReturnType, parameters);
    }

    public List<SClass<?>> parameterList() {
        return parameters;
    }

    @Override
    public String toString() {
        return toMethodDescriptorString();
    }
}
