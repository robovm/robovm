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

/*<javadoc>*/

/*</javadoc>*/
/*<annotations>*/@Library("Foundation") @NativeClass/*</annotations>*/
/*<visibility>*/public/*</visibility>*/ class /*<name>*/NSProcessInfo/*</name>*/ 
    extends /*<extends>*/NSObject/*</extends>*/ 
    /*<implements>*//*</implements>*/ {

    /*<ptr>*/public static class NSProcessInfoPtr extends Ptr<NSProcessInfo, NSProcessInfoPtr> {}/*</ptr>*/
    /*<bind>*/static { ObjCRuntime.bind(NSProcessInfo.class); }/*</bind>*/
    /*<constants>*//*</constants>*/
    /*<constructors>*/
    public NSProcessInfo() {}
    protected NSProcessInfo(SkipInit skipInit) { super(skipInit); }
    /*</constructors>*/
    /*<properties>*/
    
    /*</properties>*/
    /*<members>*//*</members>*/
    /*<methods>*/
    @Method(selector = "environment")
    public native NSDictionary<?, ?> environment();
    @Method(selector = "arguments")
    public native NSArray<?> arguments();
    @Method(selector = "hostName")
    public native String hostName();
    @Method(selector = "processName")
    public native String processName();
    @Method(selector = "processIdentifier")
    public native int processIdentifier();
    @Method(selector = "setProcessName:")
    public native void setProcessName(String newName);
    @Method(selector = "globallyUniqueString")
    public native String globallyUniqueString();
    @Method(selector = "operatingSystem")
    public native NSOperatingSystem getOperatingSystem();
    @Method(selector = "operatingSystemName")
    public native String operatingSystemName();
    @Method(selector = "operatingSystemVersionString")
    public native String operatingSystemVersionString();
    /**
     * @since Available in iOS 2.0 and later.
     */
    @Method(selector = "processorCount")
    public native @MachineSizedUInt long processorCount();
    /**
     * @since Available in iOS 2.0 and later.
     */
    @Method(selector = "activeProcessorCount")
    public native @MachineSizedUInt long activeProcessorCount();
    /**
     * @since Available in iOS 2.0 and later.
     */
    @Method(selector = "physicalMemory")
    public native long physicalMemory();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @Method(selector = "systemUptime")
    public native double systemUptime();
    @Method(selector = "processInfo")
    public static native NSProcessInfo processInfo();
    /**
     * @since Available in iOS 7.0 and later.
     */
    @Method(selector = "beginActivityWithOptions:reason:")
    public native NSObject beginActivityWithOptions$reason$(NSActivityOptions options, String reason);
    /**
     * @since Available in iOS 7.0 and later.
     */
    @Method(selector = "endActivity:")
    public native void endActivity$(NSObject activity);
    /**
     * @since Available in iOS 7.0 and later.
     */
    @Method(selector = "performActivityWithOptions:reason:usingBlock:")
    public native void performActivityWithOptions$reason$usingBlock$(NSActivityOptions options, String reason, @Block Runnable block);
    /*</methods>*/
}
