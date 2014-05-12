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
    /*<methods>*/
    /**
     * @since Available in iOS 2.0 and later.
     */
    @GlobalValue(symbol="kCFErrorDomainPOSIX", optional=true)
    public static native CFString DomainPOSIX();
    /**
     * @since Available in iOS 2.0 and later.
     */
    @GlobalValue(symbol="kCFErrorDomainOSStatus", optional=true)
    public static native CFString DomainOSStatus();
    /**
     * @since Available in iOS 2.0 and later.
     */
    @GlobalValue(symbol="kCFErrorDomainMach", optional=true)
    public static native CFString DomainMach();
    /**
     * @since Available in iOS 2.0 and later.
     */
    @GlobalValue(symbol="kCFErrorDomainCocoa", optional=true)
    public static native CFString DomainCocoa();
    /**
     * @since Available in iOS 2.0 and later.
     */
    @GlobalValue(symbol="kCFErrorLocalizedDescriptionKey", optional=true)
    public static native CFString KeyLocalizedDescription();
    /**
     * @since Available in iOS 2.0 and later.
     */
    @GlobalValue(symbol="kCFErrorLocalizedFailureReasonKey", optional=true)
    public static native CFString KeyLocalizedFailureReason();
    /**
     * @since Available in iOS 2.0 and later.
     */
    @GlobalValue(symbol="kCFErrorLocalizedRecoverySuggestionKey", optional=true)
    public static native CFString KeyLocalizedRecoverySuggestion();
    /**
     * @since Available in iOS 2.0 and later.
     */
    @GlobalValue(symbol="kCFErrorDescriptionKey", optional=true)
    public static native CFString KeyDescription();
    /**
     * @since Available in iOS 2.0 and later.
     */
    @GlobalValue(symbol="kCFErrorUnderlyingErrorKey", optional=true)
    public static native CFString KeyUnderlyingError();
    /**
     * @since Available in iOS 5.0 and later.
     */
    @GlobalValue(symbol="kCFErrorURLKey", optional=true)
    public static native CFString KeyURL();
    /**
     * @since Available in iOS 5.0 and later.
     */
    @GlobalValue(symbol="kCFErrorFilePathKey", optional=true)
    public static native CFString KeyFilePath();
    
    /**
     * @since Available in iOS 2.0 and later.
     */
    @Bridge(symbol="CFErrorGetTypeID", optional=true)
    public static native @MachineSizedUInt long getClassTypeID();
    /**
     * @since Available in iOS 2.0 and later.
     */
    @Bridge(symbol="CFErrorCreate", optional=true)
    public static native CFError create(CFAllocator allocator, CFString domain, @MachineSizedSInt long code, CFDictionary userInfo);
    /**
     * @since Available in iOS 2.0 and later.
     */
    @Bridge(symbol="CFErrorCreateWithUserInfoKeysAndValues", optional=true)
    public static native CFError createWithUserInfoKeysAndValues(CFAllocator allocator, CFString domain, @MachineSizedSInt long code, VoidPtr.VoidPtrPtr userInfoKeys, VoidPtr.VoidPtrPtr userInfoValues, @MachineSizedSInt long numUserInfoValues);
    /**
     * @since Available in iOS 2.0 and later.
     */
    @Bridge(symbol="CFErrorGetDomain", optional=true)
    public native CFString getDomain();
    /**
     * @since Available in iOS 2.0 and later.
     */
    @Bridge(symbol="CFErrorGetCode", optional=true)
    public native @MachineSizedSInt long getCode();
    /**
     * @since Available in iOS 2.0 and later.
     */
    @Bridge(symbol="CFErrorCopyUserInfo", optional=true)
    public native CFDictionary copyUserInfo();
    /**
     * @since Available in iOS 2.0 and later.
     */
    @Bridge(symbol="CFErrorCopyDescription", optional=true)
    public native CFString copyDescription();
    /**
     * @since Available in iOS 2.0 and later.
     */
    @Bridge(symbol="CFErrorCopyFailureReason", optional=true)
    public native CFString copyFailureReason();
    /**
     * @since Available in iOS 2.0 and later.
     */
    @Bridge(symbol="CFErrorCopyRecoverySuggestion", optional=true)
    public native CFString copyRecoverySuggestion();
    /*</methods>*/
}
