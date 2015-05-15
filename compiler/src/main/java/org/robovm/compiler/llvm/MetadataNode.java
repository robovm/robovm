/*
 * Copyright (C) 2014 RoboVM AB
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

import java.util.List;

/**
 *
 * @version $Id$
 */
public class MetadataNode extends Metadata {
    private final Value[] values;

    public MetadataNode(Value ... values) {
        this.values = values;
    }

    public MetadataNode(List<? extends Value> values) {
        this.values = values.toArray(new Value[values.size()]);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("!{");
        for (int i = 0; i < values.length; i++) {
            if (i > 0) {
                sb.append(", ");
            }
            if (values[i] == null) {
                sb.append("null");
            } else {
                if (values[i].getType() != Type.METADATA) {
                    sb.append(values[i].getType());
                    sb.append(' ');
                }
                sb.append(values[i]);
            }
        }
        sb.append('}');
        return sb.toString();
    }
}
