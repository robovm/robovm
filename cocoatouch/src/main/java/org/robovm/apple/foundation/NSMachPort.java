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
import org.robovm.apple.uikit.*;
import org.robovm.apple.coregraphics.*;
import org.robovm.apple.coremedia.*;
import org.robovm.apple.security.*;
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
    public NSMachPort(int machPort) { super((SkipInit) null); initObject(initWithMachPort$(machPort)); }
    /**
     * @since Available in iOS 2.0 and later.
     */
    public NSMachPort(int machPort, NSMachPortRights f) { super((SkipInit) null); initObject(initWithMachPort$options$(machPort, f)); }
    /*</constructors>*/
    /*<properties>*/
    
    /*</properties>*/
    /*<members>*//*</members>*/
    /*<methods>*/
    @Method(selector = "initWithMachPort:")
    protected native @Pointer long initWithMachPort$(int machPort);
    /**
     * @since Available in iOS 2.0 and later.
     */
    @Method(selector = "initWithMachPort:options:")
    protected native @Pointer long initWithMachPort$options$(int machPort, NSMachPortRights f);
    @Method(selector = "machPort")
    public native int machPort();
    @Method(selector = "scheduleInRunLoop:forMode:")
    public native void scheduleInRunLoop$forMode$(NSRunLoop runLoop, String mode);
    @Method(selector = "removeFromRunLoop:forMode:")
    public native void removeFromRunLoop$forMode$(NSRunLoop runLoop, String mode);
    @Method(selector = "portWithMachPort:")
    public static native NSPort portWithMachPort$(int machPort);
    /**
     * @since Available in iOS 2.0 and later.
     */
    @Method(selector = "portWithMachPort:options:")
    public static native NSPort portWithMachPort$options$(int machPort, NSMachPortRights f);
    /*</methods>*/
}
