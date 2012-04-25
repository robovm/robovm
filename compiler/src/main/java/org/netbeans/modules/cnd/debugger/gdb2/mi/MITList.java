/*
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS HEADER.
 *
 * Copyright 1997-2010 Oracle and/or its affiliates. All rights reserved.
 *
 * Oracle and Java are registered trademarks of Oracle and/or its affiliates.
 * Other names may be trademarks of their respective owners.
 *
 * The contents of this file are subject to the terms of either the GNU
 * General Public License Version 2 only ("GPL") or the Common
 * Development and Distribution License("CDDL") (collectively, the
 * "License"). You may not use this file except in compliance with the
 * License. You can obtain a copy of the License at
 * http://www.netbeans.org/cddl-gplv2.html
 * or nbbuild/licenses/CDDL-GPL-2-CP. See the License for the
 * specific language governing permissions and limitations under the
 * License.  When distributing the software, include this License Header
 * Notice in each file and include the License file at
 * nbbuild/licenses/CDDL-GPL-2-CP.  Oracle designates this
 * particular file as subject to the "Classpath" exception as provided
 * by Oracle in the GPL Version 2 section of the License file that
 * accompanied this code. If applicable, add the following below the
 * License Header, with the fields enclosed by brackets [] replaced by
 * your own identifying information:
 * "Portions Copyrighted [year] [name of copyright owner]"
 *
 * Contributor(s):
 *
 * The Original Software is NetBeans. The Initial Developer of the Original
 * Software is Sun Microsystems, Inc. Portions Copyright 1997-2007 Sun
 * Microsystems, Inc. All Rights Reserved.
 *
 * If you wish your version of this file to be governed by only the CDDL
 * or only the GPL Version 2, indicate your decision by adding
 * "[Contributor] elects to include this software in this distribution
 * under the [CDDL or GPL Version 2] license." If you do not indicate a
 * single choice of license, a recipient has the option to distribute
 * your version of this file under either the CDDL, the GPL Version 2 or
 * to extend the choice of license to its licensees as provided above.
 * However, if you add GPL Version 2 code and therefore, elected the GPL
 * Version 2 license, then the option applies only if the new code is
 * made subject to such option by the copyright holder.
 */

package org.netbeans.modules.cnd.debugger.gdb2.mi;

import java.util.ArrayList;
import java.util.Iterator;


/**
 * Representation of a 'tuple/list' combo sub-tree from the MI spec.
 * A list can be one of:
 * <ul>
 * <li>"[]" 
 * <li>"[" tlist-item ( "," tlist-item )* "]" 
 * <li>"{" tlist-item ( "," tlist-item )* "}"
 * </ul>
 */

public class MITList extends MIValue implements Iterable<MITListItem> {

    private final ArrayList<MITListItem> list;
    private final boolean isList;
    private final boolean topLevel;

    private boolean sawResults;
    private boolean sawValues;

    MITList(boolean isList, boolean topLevel) {
        list = new ArrayList<MITListItem>();
        this.isList = isList;
        this.topLevel = topLevel;
    } 

    public String toString() {
        String s = new String();
        if (!topLevel)
            s += isList()? "[": "{"; // NOI18N
        for (int vx = 0; vx < list.size(); vx++) {
            /* OLD
            if (isValueList) {
                MIValue value = (MIValue) list.get(vx);
                s += value.toString();
            } else {
                MIResult result = (MIResult) list.get(vx);
                s += result.toString();
            }
            */
            MITListItem item = list.get(vx);
            s += item.toString();

            if (vx+1 < list.size())
                s += ","; // NOI18N
        }
        if (!topLevel)
            s += isList()? "]": "}"; // NOI18N
        return s;
    }


    // interface MIValue
    public boolean isList() {
        return isList;
    }

    @Override
    public boolean isTList() {
        return true;
    }

    public Iterator<MITListItem> iterator() {
        return list.iterator();
    }

    /* OLD
    // interface MIValue
    @Deprecated
    public MIList asList() {
        return null;
    }
    */

    @Override
    public MITList asTList() { return this; }

    @Override
    public MITList asTuple() { return this; }

    @Override
    public MITList asList() { return this; }



    /**
     * Return true if list is of this form:
     * <br>
     * "[]"
     */

    public boolean isEmpty() {
        return list.isEmpty();
    } 

    /**
     * return size of list
     */
    public int size() {
        return list.size();
    }

    public boolean isValueList() {
        return sawValues;
    } 

    public boolean isResultList() {
        return sawResults;
    } 

    /**
     * return one entry by index
     */
    public MITListItem get(int x) {
        return list.get(x);
    }

    void add(MIResult result) {
        assert !sawValues : "Adding results to a value list";
        sawResults = true;
        list.add(result);
    }

    void add(MIValue value) {
        assert !sawResults : "Adding values to a result list";
        sawValues = true;
        list.add(value);
    }

    /**
     * Retrieve the value of the given variable in this tuple.
     *
     * Will return null if no match for variable is found.
     */

    public MIValue valueOf(String variable) {
        assert sawResults : "Getting value from a result list";
        for (int vx = 0; vx < list.size(); vx++) {
            MIResult result = (MIResult) list.get(vx);
            if (result.matches(variable))
                return result.value();
        }
        return null;
    }
}
