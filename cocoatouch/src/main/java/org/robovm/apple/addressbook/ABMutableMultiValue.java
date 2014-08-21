/*
 * Copyright (C) 2014 Trillian Mobile AB
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.robovm.apple.addressbook;

/*<imports>*/
import java.io.*;
import java.nio.*;
import java.util.*;
import org.robovm.objc.*;
import org.robovm.objc.annotation.*;
import org.robovm.objc.block.*;
import org.robovm.rt.*;
import org.robovm.rt.bro.*;
import org.robovm.rt.bro.annotation.*;
import org.robovm.rt.bro.ptr.*;
import org.robovm.apple.foundation.*;
import org.robovm.apple.corefoundation.*;
/*</imports>*/

/*<javadoc>*/
/*</javadoc>*/
/*<annotations>*/@Library("AddressBook")/*</annotations>*/
/*<visibility>*/public/*</visibility>*/ class /*<name>*/ABMutableMultiValue/*</name>*/ 
    extends /*<extends>*/ABMultiValue/*</extends>*/ 
    /*<implements>*//*</implements>*/ {

    /*<ptr>*/
    /*</ptr>*/
    /*<bind>*/static { Bro.bind(ABMutableMultiValue.class); }/*</bind>*/
    /*<constants>*//*</constants>*/
    /*<constructors>*//*</constructors>*/
    /*<properties>*//*</properties>*/
    /*<members>*//*</members>*/
    /**
     * 
     * @param value
     * @param label
     * @return the identifier of this value.
     */
    public int addValue(CFType value, String label) {
        IntPtr ptr = new IntPtr();
        addValue(value, label, ptr);
        return ptr.get();
    }
    /**
     * 
     * @param value
     * @param label
     * @param index
     * @return the identifier of this value.
     */
    public int insertValue(CFType value, String label, @MachineSizedSInt long index) {
        IntPtr ptr = new IntPtr();
        insertValue(value, label, index, ptr);
        return ptr.get();
    }
    /*<methods>*/
    @Bridge(symbol="ABMultiValueCreateMutable", optional=true)
    public static native ABMutableMultiValue create(ABPropertyType type);
    @Bridge(symbol="ABMultiValueCreateMutableCopy", optional=true)
    public static native ABMutableMultiValue create(ABMultiValue multiValue);
    @Bridge(symbol="ABMultiValueAddValueAndLabel", optional=true)
    protected native boolean addValue(CFType value, String label, IntPtr outIdentifier);
    @Bridge(symbol="ABMultiValueInsertValueAndLabelAtIndex", optional=true)
    protected native boolean insertValue(CFType value, String label, @MachineSizedSInt long index, IntPtr outIdentifier);
    @Bridge(symbol="ABMultiValueRemoveValueAndLabelAtIndex", optional=true)
    public native boolean removeValue(@MachineSizedSInt long index);
    @Bridge(symbol="ABMultiValueReplaceValueAtIndex", optional=true)
    public native boolean replaceValue(CFType value, @MachineSizedSInt long index);
    @Bridge(symbol="ABMultiValueReplaceLabelAtIndex", optional=true)
    public native boolean replaceLabel(String label, @MachineSizedSInt long index);
    /*</methods>*/
}
