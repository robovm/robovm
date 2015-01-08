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
package org.robovm.apple.corefoundation;

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
/*</imports>*/

/*<javadoc>*/
/*</javadoc>*/
/*<annotations>*/@Library("CoreFoundation")/*</annotations>*/
/*<visibility>*/public/*</visibility>*/ class /*<name>*/CFError/*</name>*/ 
    extends /*<extends>*/CFType/*</extends>*/ 
    /*<implements>*//*</implements>*/ {

    /*<ptr>*/public static class CFErrorPtr extends Ptr<CFError, CFErrorPtr> {}/*</ptr>*/
    /*<bind>*/static { Bro.bind(CFError.class); }/*</bind>*/
    /*<constants>*//*</constants>*/
    /*<constructors>*/
    protected CFError() {}
    /*</constructors>*/
    /*<properties>*//*</properties>*/
    /*<members>*//*</members>*/
    public static CFError create(String domain, @MachineSizedSInt long code, NSDictionary<NSString, NSObject> userInfo) {
        return create(null, domain, code, userInfo);
    }
    /*<methods>*/
    /**
     * @since Available in iOS 2.0 and later.
     */
    @Bridge(symbol="CFErrorGetTypeID", optional=true)
    public static native @MachineSizedUInt long getClassTypeID();
    /**
     * @since Available in iOS 2.0 and later.
     */
    @Bridge(symbol="CFErrorCreate", optional=true)
    protected static native CFError create(CFAllocator allocator, String domain, @MachineSizedSInt long code, NSDictionary<NSString, NSObject> userInfo);
    /**
     * @since Available in iOS 2.0 and later.
     */
    @Bridge(symbol="CFErrorGetDomain", optional=true)
    public native String getDomain();
    /**
     * @since Available in iOS 2.0 and later.
     */
    @Bridge(symbol="CFErrorGetCode", optional=true)
    public native @MachineSizedSInt long getCode();
    /**
     * @since Available in iOS 2.0 and later.
     */
    @Bridge(symbol="CFErrorCopyUserInfo", optional=true)
    public native CFDictionary getUserInfo();
    /**
     * @since Available in iOS 2.0 and later.
     */
    @Bridge(symbol="CFErrorCopyFailureReason", optional=true)
    public native String getFailureReason();
    /**
     * @since Available in iOS 2.0 and later.
     */
    @Bridge(symbol="CFErrorCopyRecoverySuggestion", optional=true)
    public native String getRecoverySuggestion();
    /*</methods>*/
}
