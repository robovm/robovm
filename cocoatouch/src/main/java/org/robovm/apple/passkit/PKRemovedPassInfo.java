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
package org.robovm.apple.passkit;

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
import org.robovm.apple.uikit.*;
/*</imports>*/

/*<javadoc>*/
/*</javadoc>*/
/*<annotations>*/@Library("PassKit")/*</annotations>*/
/*<visibility>*/public/*</visibility>*/ class /*<name>*/PKRemovedPassInfo/*</name>*/ 
    extends /*<extends>*/Object/*</extends>*/ 
    /*<implements>*//*</implements>*/ {

    /*<ptr>*/
    /*</ptr>*/
    /*<bind>*/static { Bro.bind(PKRemovedPassInfo.class); }/*</bind>*/
    /*<constants>*//*</constants>*/
    private NSDictionary<NSString, ?> data;
    
    protected PKRemovedPassInfo (NSDictionary<NSString, ?> data) {
        this.data = data;
    }
    /*<constructors>*//*</constructors>*/
    /*<properties>*//*</properties>*/
    /*<members>*//*</members>*/
    /**
     * @since Available in iOS 6.0 and later.
     */
    public String getPassTypeIdentifier() {
        if (data.containsKey(PassTypeIdentifierKey())) {
            NSString val = (NSString)data.get(PassTypeIdentifierKey());
            return val.toString();
        }
        return null;
    }
    /**
     * @since Available in iOS 6.0 and later.
     */
    public String getSerialNumber() {
        if (data.containsKey(SerialNumberKey())) {
            NSString val = (NSString)data.get(SerialNumberKey());
            return val.toString();
        }
        return null;
    }
    /*<methods>*/
    /**
     * @since Available in iOS 6.0 and later.
     */
    @GlobalValue(symbol="PKPassLibraryPassTypeIdentifierUserInfoKey", optional=true)
    protected static native NSString PassTypeIdentifierKey();
    /**
     * @since Available in iOS 6.0 and later.
     */
    @GlobalValue(symbol="PKPassLibrarySerialNumberUserInfoKey", optional=true)
    protected static native NSString SerialNumberKey();
    /*</methods>*/
}
