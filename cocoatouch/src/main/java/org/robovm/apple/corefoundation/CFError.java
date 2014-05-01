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

/**
 *
 * <div class="javadoc"></div>
 */
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
    @GlobalValue(symbol="kCFErrorDomainPOSIX", optional=true)
    public static native CFString DomainPOSIX();
    @GlobalValue(symbol="kCFErrorDomainOSStatus", optional=true)
    public static native CFString DomainOSStatus();
    @GlobalValue(symbol="kCFErrorDomainMach", optional=true)
    public static native CFString DomainMach();
    @GlobalValue(symbol="kCFErrorDomainCocoa", optional=true)
    public static native CFString DomainCocoa();
    @GlobalValue(symbol="kCFErrorLocalizedDescriptionKey", optional=true)
    public static native CFString KeyLocalizedDescription();
    @GlobalValue(symbol="kCFErrorLocalizedFailureReasonKey", optional=true)
    public static native CFString KeyLocalizedFailureReason();
    @GlobalValue(symbol="kCFErrorLocalizedRecoverySuggestionKey", optional=true)
    public static native CFString KeyLocalizedRecoverySuggestion();
    @GlobalValue(symbol="kCFErrorDescriptionKey", optional=true)
    public static native CFString KeyDescription();
    @GlobalValue(symbol="kCFErrorUnderlyingErrorKey", optional=true)
    public static native CFString KeyUnderlyingError();
    @GlobalValue(symbol="kCFErrorURLKey", optional=true)
    public static native CFString KeyURL();
    @GlobalValue(symbol="kCFErrorFilePathKey", optional=true)
    public static native CFString KeyFilePath();
    
    @Bridge(symbol="CFErrorGetTypeID", optional=true)
    public static native @MachineSizedUInt long getClassTypeID();
    @Bridge(symbol="CFErrorCreate", optional=true)
    public static native CFError create(CFAllocator allocator, CFString domain, @MachineSizedSInt long code, CFDictionary userInfo);
    @Bridge(symbol="CFErrorCreateWithUserInfoKeysAndValues", optional=true)
    public static native CFError createWithUserInfoKeysAndValues(CFAllocator allocator, CFString domain, @MachineSizedSInt long code, VoidPtr.VoidPtrPtr userInfoKeys, VoidPtr.VoidPtrPtr userInfoValues, @MachineSizedSInt long numUserInfoValues);
    @Bridge(symbol="CFErrorGetDomain", optional=true)
    public native CFString getDomain();
    @Bridge(symbol="CFErrorGetCode", optional=true)
    public native @MachineSizedSInt long getCode();
    @Bridge(symbol="CFErrorCopyUserInfo", optional=true)
    public native CFDictionary copyUserInfo();
    @Bridge(symbol="CFErrorCopyDescription", optional=true)
    public native CFString copyDescription();
    @Bridge(symbol="CFErrorCopyFailureReason", optional=true)
    public native CFString copyFailureReason();
    @Bridge(symbol="CFErrorCopyRecoverySuggestion", optional=true)
    public native CFString copyRecoverySuggestion();
    /*</methods>*/
}
