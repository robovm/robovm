/*
 * Copyright (C) 2013-2015 Trillian Mobile AB
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
package org.robovm.apple.security;

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
import org.robovm.apple.dispatch.*;
import org.robovm.apple.foundation.*;
import org.robovm.apple.corefoundation.*;
/*</imports>*/

/*<javadoc>*/
/*</javadoc>*/
/*<annotations>*/@Library("Security")/*</annotations>*/
/*<visibility>*/public/*</visibility>*/ class /*<name>*/SecItem/*</name>*/ 
    extends /*<extends>*/CocoaUtility/*</extends>*/ 
    /*<implements>*//*</implements>*/ {

    /*<ptr>*/
    /*</ptr>*/
    /*<bind>*/static { Bro.bind(SecItem.class); }/*</bind>*/
    /*<constants>*//*</constants>*/
    /*<constructors>*//*</constructors>*/
    /*<properties>*//*</properties>*/
    /*<members>*//*</members>*/
    /**
     * @since Available in iOS 2.0 and later.
     */
    public static CFType getMatching(SecQuery query) {
        CFType.CFTypePtr ptr = new CFType.CFTypePtr();
        getMatching(query, ptr);
        return ptr.get();
    }
    /**
     * @since Available in iOS 2.0 and later.
     */
    public static CFType add(SecAttributes attributes) {
        CFType.CFTypePtr ptr = new CFType.CFTypePtr();
        add(attributes, ptr);
        return ptr.get();
    }
    /*<methods>*/
    /**
     * @since Available in iOS 2.0 and later.
     */
    @Bridge(symbol="SecItemCopyMatching", optional=true)
    protected static native OSStatus getMatching(SecQuery query, CFType.CFTypePtr result);
    /**
     * @since Available in iOS 2.0 and later.
     */
    @Bridge(symbol="SecItemAdd", optional=true)
    protected static native OSStatus add(SecAttributes attributes, CFType.CFTypePtr result);
    /**
     * @since Available in iOS 2.0 and later.
     */
    @Bridge(symbol="SecItemUpdate", optional=true)
    public static native OSStatus update(SecQuery query, SecAttributes attributesToUpdate);
    /**
     * @since Available in iOS 2.0 and later.
     */
    @Bridge(symbol="SecItemDelete", optional=true)
    public static native OSStatus delete(SecQuery query);
    /*</methods>*/
}
