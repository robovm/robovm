/*
 * Copyright (C) 2014 Trillian AB
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
    @GlobalValue(symbol="kCFErrorDomainPOSIX")
    public static native CFString DomainPOSIX();
    @GlobalValue(symbol="kCFErrorDomainOSStatus")
    public static native CFString DomainOSStatus();
    @GlobalValue(symbol="kCFErrorDomainMach")
    public static native CFString DomainMach();
    @GlobalValue(symbol="kCFErrorDomainCocoa")
    public static native CFString DomainCocoa();
    @GlobalValue(symbol="kCFErrorLocalizedDescriptionKey")
    public static native CFString KeyLocalizedDescription();
    @GlobalValue(symbol="kCFErrorLocalizedFailureReasonKey")
    public static native CFString KeyLocalizedFailureReason();
    @GlobalValue(symbol="kCFErrorLocalizedRecoverySuggestionKey")
    public static native CFString KeyLocalizedRecoverySuggestion();
    @GlobalValue(symbol="kCFErrorDescriptionKey")
    public static native CFString KeyDescription();
    @GlobalValue(symbol="kCFErrorUnderlyingErrorKey")
    public static native CFString KeyUnderlyingError();
    @GlobalValue(symbol="kCFErrorURLKey")
    public static native CFString KeyURL();
    @GlobalValue(symbol="kCFErrorFilePathKey")
    public static native CFString KeyFilePath();
    
    @Bridge(symbol="CFErrorGetTypeID")
    public static native @MachineSizedUInt long getClassTypeID();
    @Bridge(symbol="CFErrorCreate")
    public static native CFError create(CFAllocator allocator, CFString domain, @MachineSizedSInt long code, CFDictionary userInfo);
    @Bridge(symbol="CFErrorCreateWithUserInfoKeysAndValues")
    public static native CFError createWithUserInfoKeysAndValues(CFAllocator allocator, CFString domain, @MachineSizedSInt long code, VoidPtr.VoidPtrPtr userInfoKeys, VoidPtr.VoidPtrPtr userInfoValues, @MachineSizedSInt long numUserInfoValues);
    @Bridge(symbol="CFErrorGetDomain")
    public native CFString getDomain();
    @Bridge(symbol="CFErrorGetCode")
    public native @MachineSizedSInt long getCode();
    @Bridge(symbol="CFErrorCopyUserInfo")
    public native CFDictionary copyUserInfo();
    @Bridge(symbol="CFErrorCopyDescription")
    public native CFString copyDescription();
    @Bridge(symbol="CFErrorCopyFailureReason")
    public native CFString copyFailureReason();
    @Bridge(symbol="CFErrorCopyRecoverySuggestion")
    public native CFString copyRecoverySuggestion();
    /*</methods>*/
}
