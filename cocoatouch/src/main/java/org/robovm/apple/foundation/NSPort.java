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
package org.robovm.apple.foundation;

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
import org.robovm.apple.corefoundation.*;
import org.robovm.apple.security.*;
/*</imports>*/

/**
 *
 * <div class="javadoc"></div>
 */
/*<annotations>*/@Library("Foundation") @NativeClass/*</annotations>*/
/*<visibility>*/public/*</visibility>*/ class /*<name>*/NSPort/*</name>*/ 
    extends /*<extends>*/NSObject/*</extends>*/ 
    /*<implements>*/implements NSCoding/*</implements>*/ {

    /*<ptr>*/public static class NSPortPtr extends Ptr<NSPort, NSPortPtr> {}/*</ptr>*/
    /*<bind>*/static { ObjCRuntime.bind(NSPort.class); }/*</bind>*/
    /*<constants>*//*</constants>*/
    /*<constructors>*/
    public NSPort() {}
    protected NSPort(SkipInit skipInit) { super(skipInit); }
    /*</constructors>*/
    /*<properties>*/
    
    /*</properties>*/
    /*<members>*//*</members>*/
    /*<methods>*/
    @GlobalValue(symbol="NSPortDidBecomeInvalidNotification")
    public static native String NotificationDidBecomeInvalid();
    
    @Method(selector = "invalidate")
    public native void invalidate();
    @Method(selector = "isValid")
    public native boolean isValid();
    @Method(selector = "setDelegate:")
    public native void setDelegate(NSPortDelegate anObject);
    @Method(selector = "delegate")
    public native NSPortDelegate delegate();
    @Method(selector = "scheduleInRunLoop:forMode:")
    public native void scheduleInRunLoop$forMode$(NSRunLoop runLoop, String mode);
    @Method(selector = "removeFromRunLoop:forMode:")
    public native void removeFromRunLoop$forMode$(NSRunLoop runLoop, String mode);
    @Method(selector = "reservedSpaceLength")
    public native @MachineSizedUInt long reservedSpaceLength();
    @Method(selector = "sendBeforeDate:components:from:reserved:")
    public native boolean sendBeforeDate$components$from$reserved$(NSDate limitDate, NSMutableArray<?> components, NSPort receivePort, @MachineSizedUInt long headerSpaceReserved);
    @Method(selector = "sendBeforeDate:msgid:components:from:reserved:")
    public native boolean sendBeforeDate$msgid$components$from$reserved$(NSDate limitDate, @MachineSizedUInt long msgID, NSMutableArray<?> components, NSPort receivePort, @MachineSizedUInt long headerSpaceReserved);
    @Method(selector = "port")
    public static native NSPort port();
    @Method(selector = "encodeWithCoder:")
    public native void encode(NSCoder aCoder);
    /*</methods>*/
}
