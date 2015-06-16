/*
 * Copyright (C) 2013-2015 RoboVM AB
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
import org.robovm.rt.annotation.*;
import org.robovm.rt.bro.*;
import org.robovm.rt.bro.annotation.*;
import org.robovm.rt.bro.ptr.*;
import org.robovm.apple.corefoundation.*;
import org.robovm.apple.uikit.*;
import org.robovm.apple.coretext.*;
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
/*<visibility>*/public/*</visibility>*/ class /*<name>*/NSNetServiceBrowser/*</name>*/ 
    extends /*<extends>*/NSObject/*</extends>*/ 
    /*<implements>*//*</implements>*/ {

    /*<ptr>*/public static class NSNetServiceBrowserPtr extends Ptr<NSNetServiceBrowser, NSNetServiceBrowserPtr> {}/*</ptr>*/
    /*<bind>*/static { ObjCRuntime.bind(NSNetServiceBrowser.class); }/*</bind>*/
    /*<constants>*//*</constants>*/
    /*<constructors>*/
    public NSNetServiceBrowser() {}
    protected NSNetServiceBrowser(SkipInit skipInit) { super(skipInit); }
    /*</constructors>*/
    /*<properties>*/
    @Property(selector = "delegate")
    public native NSNetServiceBrowserDelegate getDelegate();
    @Property(selector = "setDelegate:", strongRef = true)
    public native void setDelegate(NSNetServiceBrowserDelegate v);
    /**
     * @since Available in iOS 7.0 and later.
     */
    @Property(selector = "includesPeerToPeer")
    public native boolean includesPeerToPeer();
    /**
     * @since Available in iOS 7.0 and later.
     */
    @Property(selector = "setIncludesPeerToPeer:")
    public native void setIncludesPeerToPeer(boolean v);
    /*</properties>*/
    /*<members>*//*</members>*/
    public void scheduleInRunLoop(NSRunLoop aRunLoop, NSRunLoopMode mode) {
        scheduleInRunLoop(aRunLoop, mode.value().toString());
    }
    public void removeFromRunLoop(NSRunLoop aRunLoop, NSRunLoopMode mode) {
        removeFromRunLoop(aRunLoop, mode.value().toString());
    }
    /*<methods>*/
    @Method(selector = "scheduleInRunLoop:forMode:")
    public native void scheduleInRunLoop(NSRunLoop aRunLoop, String mode);
    @Method(selector = "removeFromRunLoop:forMode:")
    public native void removeFromRunLoop(NSRunLoop aRunLoop, String mode);
    @Method(selector = "searchForBrowsableDomains")
    public native void searchForBrowsableDomains();
    @Method(selector = "searchForRegistrationDomains")
    public native void searchForRegistrationDomains();
    @Method(selector = "searchForServicesOfType:inDomain:")
    public native void searchForServices(String type, String domainString);
    @Method(selector = "stop")
    public native void stop();
    /*</methods>*/
}
