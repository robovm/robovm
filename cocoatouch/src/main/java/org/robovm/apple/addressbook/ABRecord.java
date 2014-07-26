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
/*<visibility>*/public/*</visibility>*/ class /*<name>*/ABRecord/*</name>*/ 
    extends /*<extends>*/CFType/*</extends>*/ 
    /*<implements>*//*</implements>*/ {

    /*<ptr>*/
    /*</ptr>*/
    /*<bind>*/static { Bro.bind(ABRecord.class); }/*</bind>*/
    /*<constants>*//*</constants>*/
    /*<constructors>*//*</constructors>*/
    /*<properties>*//*</properties>*/
    /*<members>*//*</members>*/
    public boolean setValue(int property, CFType value) {
        return setValue(property, value, null);
    }
    
    public boolean removeValue(int property) {
        return removeValue(property, null);
    }
    /*<methods>*/
    @Bridge(symbol="ABRecordGetRecordID", optional=true)
    public native int getRecordID();
    @Bridge(symbol="ABRecordGetRecordType", optional=true)
    public native ABRecordType getRecordType();
    @Bridge(symbol="ABRecordCopyValue", optional=true)
    public native CFType getValue(int property);
    @Bridge(symbol="ABRecordSetValue", optional=true)
    protected native boolean setValue(int property, CFType value, NSError.NSErrorPtr error);
    @Bridge(symbol="ABRecordRemoveValue", optional=true)
    protected native boolean removeValue(int property, NSError.NSErrorPtr error);
    @Bridge(symbol="ABRecordCopyCompositeName", optional=true)
    public native String getCompositeName();
    /*</methods>*/
}
