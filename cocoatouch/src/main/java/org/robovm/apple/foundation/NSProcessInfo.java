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
import org.robovm.apple.coreanimation.*;
import org.robovm.apple.coregraphics.*;
import org.robovm.apple.coremedia.*;
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
    protected NSProcessInfo(SkipInit skipInit) { super(skipInit); }
    /*</constructors>*/
    /*<properties>*/
    
    /*</properties>*/
    /*<members>*//*</members>*/
    /*<methods>*/
    @Method(selector = "environment")
    public native @org.robovm.rt.bro.annotation.Marshaler(NSDictionary.AsStringMapMarshaler.class) Map<String, NSObject> getEnvironment();
    @Method(selector = "arguments")
    public native @org.robovm.rt.bro.annotation.Marshaler(NSArray.AsStringListMarshaler.class) List<String> getArguments();
    @Method(selector = "hostName")
    public native String getHostName();
    @Method(selector = "processName")
    public native String getProcessName();
    @Method(selector = "processIdentifier")
    public native int getProcessIdentifier();
    @Method(selector = "setProcessName:")
    public native void setProcessName(String newName);
    @Method(selector = "globallyUniqueString")
    public native String getGloballyUniqueString();
    @Method(selector = "operatingSystem")
    public native NSOperatingSystem getOperatingSystem();
    @Method(selector = "operatingSystemName")
    public native String getOperatingSystemName();
    @Method(selector = "operatingSystemVersionString")
    public native String getOperatingSystemVersionString();
    /**
     * @since Available in iOS 2.0 and later.
     */
    @Method(selector = "processorCount")
    public native @MachineSizedUInt long getProcessorCount();
    /**
     * @since Available in iOS 2.0 and later.
     */
    @Method(selector = "activeProcessorCount")
    public native @MachineSizedUInt long getActiveProcessorCount();
    /**
     * @since Available in iOS 2.0 and later.
     */
    @Method(selector = "physicalMemory")
    public native long getPhysicalMemory();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @Method(selector = "systemUptime")
    public native double getSystemUptime();
    @Method(selector = "processInfo")
    public static native NSProcessInfo getSharedProcessInfo();
    /**
     * @since Available in iOS 7.0 and later.
     */
    @Method(selector = "beginActivityWithOptions:reason:")
    public native NSObject beginActivity(NSActivityOptions options, String reason);
    /**
     * @since Available in iOS 7.0 and later.
     */
    @Method(selector = "endActivity:")
    public native void endActivity(NSObject activity);
    /**
     * @since Available in iOS 7.0 and later.
     */
    @Method(selector = "performActivityWithOptions:reason:usingBlock:")
    public native void performActivity(NSActivityOptions options, String reason, @Block Runnable block);
    /*</methods>*/
}
