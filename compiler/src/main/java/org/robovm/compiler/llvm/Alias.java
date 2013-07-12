/*
 * Copyright (C) 2012 Trillian AB
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
public class Alias {
    private final String name;
    private final Linkage linkage;
    private final Constant value;

    public Alias(String name, Constant aliasee) {
        this(name, null, aliasee);
    }
    
    public Alias(String name, Linkage linkage, Constant aliasee) {
        this.name = name;
        this.linkage = linkage;
        this.value = aliasee;
    }
    
    public AliasRef ref() {
        return new AliasRef(this);
    }
    
    public String getName() {
        return name;
    }
    
    public Type getType() {
        return value.getType();
    }
    
    public String getDefinition() {
        StringBuilder sb = new StringBuilder();
        sb.append('@');
        sb.append(name);
        sb.append(" = alias ");
        if (linkage != null) {
            sb.append(linkage);
            sb.append(' ');
        }
        sb.append(value.getType());
        sb.append(' ');
        sb.append(value);
        return sb.toString();
    }
    
    @Override
    public String toString() {
        return name;
    }
}
