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

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import soot.jimple.Jimple;

/**
 * Added in RoboVM to keep track of local variable tables.
 */
public class LocalVariable {
    private String name;
    private int index;
    private UnitBox startUnit;
    private UnitBox endUnit;
    private String descriptor;
    private List<UnitBox> unitBoxes;

    public LocalVariable(String name, int index, Unit startUnit, Unit endUnit, String descriptor) {
        this.index = index;
        this.name = name;
        this.startUnit = Jimple.v().newStmtBox(startUnit);
        this.endUnit = endUnit != null ? Jimple.v().newStmtBox(endUnit) : null;
        this.descriptor = descriptor;
        
        List<UnitBox> boxes = new ArrayList<>();
        boxes.add(this.startUnit);
        if (this.endUnit != null) {
            boxes.add(this.endUnit);
        }
        unitBoxes = Collections.unmodifiableList(boxes);
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
        return endUnit != null ? endUnit.getUnit() : null;
    }
    public List<UnitBox> getUnitBoxes() {
        return unitBoxes;
    }
    public String getDescriptor() {
        return descriptor;
    }

    @Override
    public String toString() {
        return "LocalVariable [name=" + name + ", index=" + index + ", startUnit=" + getStartUnit() + ", endUnit=" + getEndUnit()
                + ", descriptor=" + descriptor + "]";
    }
}
