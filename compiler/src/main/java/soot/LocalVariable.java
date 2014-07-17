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
package soot;

import soot.jimple.Jimple;

/**
 * Added in RoboVM to keep track of local variable tables.
 */
public class LocalVariable {
    private String name;
    private int index;
    private UnitBox startUnit;
    private UnitBox endUnit;

    public LocalVariable(String name, int index, Unit startUnit, Unit endUnit) {
        this.index = index;
        this.name = name;
        this.startUnit = Jimple.v().newStmtBox(startUnit);
        this.endUnit = Jimple.v().newStmtBox(endUnit);
    }

    public String getName() {
        return name;
    }
    public int getIndex() {
        return index;
    }
    public Unit getStartUnit() {
        return startUnit.getUnit();
    }
    public Unit getEndUnit() {
        return endUnit.getUnit();
    }

    @Override
    public String toString() {
        return "LocalVariable [name=" + name + ", index=" + index 
                + ", startUnit=" + startUnit.getUnit() 
                + ", endUnit=" + (endUnit == null ? null : endUnit.getUnit())
                + "]";
    }
}
