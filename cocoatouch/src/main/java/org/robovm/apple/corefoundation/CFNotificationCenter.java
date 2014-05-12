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
/*<visibility>*/public/*</visibility>*/ class /*<name>*/CFNotificationCenter/*</name>*/ 
    extends /*<extends>*/CFType/*</extends>*/ 
    /*<implements>*//*</implements>*/ {

    /*<ptr>*/public static class CFNotificationCenterPtr extends Ptr<CFNotificationCenter, CFNotificationCenterPtr> {}/*</ptr>*/
    /*<bind>*/static { Bro.bind(CFNotificationCenter.class); }/*</bind>*/
    /*<constants>*//*</constants>*/
    /*<constructors>*/
    protected CFNotificationCenter() {}
    /*</constructors>*/
    /*<properties>*//*</properties>*/
    /*<members>*//*</members>*/
    /*<methods>*/
    @Bridge(symbol="CFNotificationCenterGetTypeID", optional=true)
    public static native @MachineSizedUInt long getClassTypeID();
    @Bridge(symbol="CFNotificationCenterGetLocalCenter", optional=true)
    public static native CFNotificationCenter getLocalCenter();
    @Bridge(symbol="CFNotificationCenterGetDarwinNotifyCenter", optional=true)
    public static native CFNotificationCenter getDarwinNotifyCenter();
    @Bridge(symbol="CFNotificationCenterAddObserver", optional=true)
    public native void addObserver(VoidPtr observer, FunctionPtr callBack, CFString name, VoidPtr object, CFNotificationSuspensionBehavior suspensionBehavior);
    @Bridge(symbol="CFNotificationCenterRemoveObserver", optional=true)
    public native void removeObserver(VoidPtr observer, CFString name, VoidPtr object);
    @Bridge(symbol="CFNotificationCenterRemoveEveryObserver", optional=true)
    public native void removeEveryObserver(VoidPtr observer);
    @Bridge(symbol="CFNotificationCenterPostNotification", optional=true)
    public native void postNotification(CFString name, VoidPtr object, CFDictionary userInfo, boolean deliverImmediately);
    @Bridge(symbol="CFNotificationCenterPostNotificationWithOptions", optional=true)
    public native void postNotificationWithOptions(CFString name, VoidPtr object, CFDictionary userInfo, CFNotificationPostingOptions options);
    /*</methods>*/
}
