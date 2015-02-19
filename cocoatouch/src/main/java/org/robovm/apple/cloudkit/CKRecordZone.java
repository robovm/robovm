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
package org.robovm.apple.cloudkit;

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
import org.robovm.apple.foundation.*;
import org.robovm.apple.corelocation.*;
/*</imports>*/

/*<javadoc>*/
/**
 * @since Available in iOS 8.0 and later.
 */
/*</javadoc>*/
/*<annotations>*/@Library("CloudKit") @NativeClass/*</annotations>*/
/*<visibility>*/public/*</visibility>*/ class /*<name>*/CKRecordZone/*</name>*/ 
    extends /*<extends>*/NSObject/*</extends>*/ 
    /*<implements>*//*</implements>*/ {

    /*<ptr>*/public static class CKRecordZonePtr extends Ptr<CKRecordZone, CKRecordZonePtr> {}/*</ptr>*/
    /*<bind>*/static { ObjCRuntime.bind(CKRecordZone.class); }/*</bind>*/
    /*<constants>*//*</constants>*/
    /*<constructors>*/
    public CKRecordZone() {}
    protected CKRecordZone(SkipInit skipInit) { super(skipInit); }
    public CKRecordZone(String zoneName) { super((SkipInit) null); initObject(init(zoneName)); }
    public CKRecordZone(CKRecordZoneID zoneID) { super((SkipInit) null); initObject(init(zoneID)); }
    /*</constructors>*/
    /*<properties>*/
    @Property(selector = "zoneID")
    public native CKRecordZoneID getZoneID();
    @Property(selector = "capabilities")
    public native CKRecordZoneCapabilities getCapabilities();
    /*</properties>*/
    /*<members>*//*</members>*/
    /*<methods>*/
    /**
     * @since Available in iOS 8.0 and later.
     */
    @GlobalValue(symbol="CKRecordZoneDefaultName", optional=true)
    public static native String getDefaultName();
    
    @Method(selector = "initWithZoneName:")
    protected native @Pointer long init(String zoneName);
    @Method(selector = "initWithZoneID:")
    protected native @Pointer long init(CKRecordZoneID zoneID);
    @Method(selector = "defaultRecordZone")
    public static native CKRecordZone getDefaultRecordZone();
    /*</methods>*/
}
