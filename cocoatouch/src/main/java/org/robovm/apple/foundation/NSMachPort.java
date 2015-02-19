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
import org.robovm.apple.uikit.*;
import org.robovm.apple.coreanimation.*;
import org.robovm.apple.coredata.*;
import org.robovm.apple.coregraphics.*;
import org.robovm.apple.coremedia.*;
import org.robovm.apple.security.*;
import org.robovm.apple.dispatch.*;
/*</imports>*/

/*<javadoc>*/

/*</javadoc>*/
/*<annotations>*/@Library("Foundation") @NativeClass/*</annotations>*/
/*<visibility>*/public/*</visibility>*/ class /*<name>*/NSMachPort/*</name>*/ 
    extends /*<extends>*/NSPort/*</extends>*/ 
    /*<implements>*//*</implements>*/ {

    /*<ptr>*/public static class NSMachPortPtr extends Ptr<NSMachPort, NSMachPortPtr> {}/*</ptr>*/
    /*<bind>*/static { ObjCRuntime.bind(NSMachPort.class); }/*</bind>*/
    /*<constants>*//*</constants>*/
    /*<constructors>*/
    public NSMachPort() {}
    protected NSMachPort(SkipInit skipInit) { super(skipInit); }
    public NSMachPort(int machPort) { super((SkipInit) null); initObject(init(machPort)); }
    /**
     * @since Available in iOS 2.0 and later.
     */
    public NSMachPort(int machPort, NSMachPortRights f) { super((SkipInit) null); initObject(init(machPort, f)); }
    /*</constructors>*/
    /*<properties>*/
    @Property(selector = "machPort")
    public native int getMachPort();
    /*</properties>*/
    /*<members>*//*</members>*/
    public void scheduleInRunLoop(NSRunLoop aRunLoop, NSRunLoopMode mode) {
        scheduleInRunLoop(aRunLoop, mode.value());
    }
    public void removeFromRunLoop(NSRunLoop aRunLoop, NSRunLoopMode mode) {
        removeFromRunLoop(aRunLoop, mode.value());
    }
    /*<methods>*/
    @Method(selector = "initWithMachPort:")
    protected native @Pointer long init(int machPort);
    @Method(selector = "setDelegate:")
    public native void setDelegate(NSMachPortDelegate anObject);
    @Method(selector = "delegate")
    public native NSMachPortDelegate getMachDelegate();
    /**
     * @since Available in iOS 2.0 and later.
     */
    @Method(selector = "initWithMachPort:options:")
    protected native @Pointer long init(int machPort, NSMachPortRights f);
    @Method(selector = "scheduleInRunLoop:forMode:")
    public native void scheduleInRunLoop(NSRunLoop runLoop, String mode);
    @Method(selector = "removeFromRunLoop:forMode:")
    public native void removeFromRunLoop(NSRunLoop runLoop, String mode);
    @Method(selector = "portWithMachPort:")
    public static native NSPort create(int machPort);
    /**
     * @since Available in iOS 2.0 and later.
     */
    @Method(selector = "portWithMachPort:options:")
    public static native NSPort create(int machPort, NSMachPortRights f);
    /*</methods>*/
}
