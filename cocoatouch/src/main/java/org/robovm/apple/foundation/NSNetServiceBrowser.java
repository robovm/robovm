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
    @Property(selector = "setDelegate:")
    public native void setDelegate(NSNetServiceBrowserDelegate v);
    @Property(selector = "includesPeerToPeer")
    public native boolean isIncludesPeerToPeer();
    @Property(selector = "setIncludesPeerToPeer:")
    public native void setIncludesPeerToPeer(boolean v);
    /*</properties>*/
    /*<members>*//*</members>*/
    /*<methods>*/
    @Method(selector = "scheduleInRunLoop:forMode:")
    public native void scheduleInRunLoop$forMode$(NSRunLoop aRunLoop, String mode);
    @Method(selector = "removeFromRunLoop:forMode:")
    public native void removeFromRunLoop$forMode$(NSRunLoop aRunLoop, String mode);
    @Method(selector = "searchForBrowsableDomains")
    public native void searchForBrowsableDomains();
    @Method(selector = "searchForRegistrationDomains")
    public native void searchForRegistrationDomains();
    @Method(selector = "searchForServicesOfType:inDomain:")
    public native void searchForServicesOfType$inDomain$(String type, String domainString);
    @Method(selector = "stop")
    public native void stop();
    /*</methods>*/
}
