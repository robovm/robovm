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
/*<visibility>*/public/*</visibility>*/ class /*<name>*/ABMultiValue/*</name>*/ 
    extends /*<extends>*/CFType/*</extends>*/ 
    /*<implements>*//*</implements>*/ {

    /*<ptr>*/
    /*</ptr>*/
    /*<bind>*/static { Bro.bind(ABMultiValue.class); }/*</bind>*/
    /*<constants>*/
    public static final int InvalidIdentifier = -1;
    /*</constants>*/
    /*<constructors>*//*</constructors>*/
    /*<properties>*//*</properties>*/
    /*<members>*//*</members>*/
    /*<methods>*/
    @Bridge(symbol="ABMultiValueGetPropertyType", optional=true)
    public native ABPropertyType getPropertyType();
    @Bridge(symbol="ABMultiValueGetCount", optional=true)
    public native @MachineSizedSInt long getCount();
    @Bridge(symbol="ABMultiValueCopyValueAtIndex", optional=true)
    public native @org.robovm.rt.bro.annotation.Marshaler(CFType.NoRetainMarshaler.class) CFType getValue(@MachineSizedSInt long index);
    @Bridge(symbol="ABMultiValueCopyArrayOfAllValues", optional=true)
    public native @org.robovm.rt.bro.annotation.Marshaler(CFType.AsListMarshaler.class) List<CFType> getAllValues();
    @Bridge(symbol="ABMultiValueCopyLabelAtIndex", optional=true)
    public native @org.robovm.rt.bro.annotation.Marshaler(CFType.NoRetainMarshaler.class) CFString getLabel(@MachineSizedSInt long index);
    @Bridge(symbol="ABMultiValueGetIndexForIdentifier", optional=true)
    public native @MachineSizedSInt long indexOf(int identifier);
    @Bridge(symbol="ABMultiValueGetIdentifierAtIndex", optional=true)
    public native int getIdentifier(@MachineSizedSInt long index);
    @Bridge(symbol="ABMultiValueGetFirstIndexOfValue", optional=true)
    public native @MachineSizedSInt long indexOf(CFType value);
    /*</methods>*/
}
