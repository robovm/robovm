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
package org.robovm.compiler.llvm;


/**
 *
 * @version $Id$
 */
public class Global {
    private final String name;
    private final Linkage linkage;
    private final Constant value;
    private final Type type;
    private final boolean constant;
    private final String section;

    public Global(String name, Type type) {
        this(name, Linkage.external, type, false);
    }
    
    public Global(String name, Type type, boolean constant) {
        this(name, Linkage.external, type, constant);
    }
    
    public Global(String name, Linkage linkage, Type type, boolean constant) {
        this.name = name;
        this.linkage = linkage;
        this.value = null;
        this.type = type;
        this.constant = constant;
        this.section = null;
    }
    
    public Global(String name, Constant value) {
        this(name, null, value, false, null);
    }
    
    public Global(String name, Constant value, boolean constant) {
        this(name, null, value, constant, null);
    }
    
    public Global(String name, Linkage linkage, Constant value) {
        this(name, linkage, value, false, null);
    }
    
    public Global(String name, Linkage linkage, Constant value, boolean constant) {
        this(name, linkage, value, constant, null);        
    }
    
    public Global(String name, Constant value, boolean constant, String section) {
        this(name, null, value, constant, section);        
    }
    
    public Global(String name, Linkage linkage, Constant value, boolean constant, String section) {
        this.name = name;
        this.linkage = linkage;
        this.value = value;
        this.type = value.getType();
        this.constant = constant;
        this.section = section;
    }
    
    public GlobalRef ref() {
        return new GlobalRef(this);
    }
    
    public String getName() {
        return name;
    }
    
    public PointerType getType() {
        return new PointerType(type);
    }
    
    public String getDefinition() {
        StringBuilder sb = new StringBuilder();
        sb.append('@');
        sb.append(name);
        sb.append(" = ");
        if (linkage != null) {
            sb.append(linkage);
            sb.append(' ');
        }
        if (constant) {
            sb.append("constant ");
        } else {
            sb.append("global ");
        }
        sb.append(type);
        if (value != null) {
            sb.append(' ');
            sb.append(value);
        }
        if (section != null) {
            sb.append(", section \"");
            sb.append(section);
            sb.append('"');
        }
        return sb.toString();
    }
    
    @Override
    public String toString() {
        return "@" + name;
    }
}
