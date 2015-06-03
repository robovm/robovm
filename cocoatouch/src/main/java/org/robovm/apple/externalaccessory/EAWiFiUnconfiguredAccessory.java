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
package org.robovm.apple.externalaccessory;

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
import org.robovm.apple.foundation.*;
import org.robovm.apple.dispatch.*;
import org.robovm.apple.uikit.*;
/*</imports>*/

/*<javadoc>*/
/**
 * @since Available in iOS 8.0 and later.
 */
/*</javadoc>*/
/*<annotations>*/@Library("ExternalAccessory") @NativeClass/*</annotations>*/
/*<visibility>*/public/*</visibility>*/ class /*<name>*/EAWiFiUnconfiguredAccessory/*</name>*/ 
    extends /*<extends>*/NSObject/*</extends>*/ 
    /*<implements>*//*</implements>*/ {

    /*<ptr>*/public static class EAWiFiUnconfiguredAccessoryPtr extends Ptr<EAWiFiUnconfiguredAccessory, EAWiFiUnconfiguredAccessoryPtr> {}/*</ptr>*/
    /*<bind>*/static { ObjCRuntime.bind(EAWiFiUnconfiguredAccessory.class); }/*</bind>*/
    /*<constants>*//*</constants>*/
    /*<constructors>*/
    public EAWiFiUnconfiguredAccessory() {}
    protected EAWiFiUnconfiguredAccessory(SkipInit skipInit) { super(skipInit); }
    /*</constructors>*/
    /*<properties>*/
    @Property(selector = "name")
    public native String getName();
    @Property(selector = "manufacturer")
    public native String getManufacturer();
    @Property(selector = "model")
    public native String getModel();
    @Property(selector = "ssid")
    public native String getSsid();
    @Property(selector = "macAddress")
    public native String getMacAddress();
    @Property(selector = "properties")
    public native EAWiFiUnconfiguredAccessoryProperties getProperties();
    /*</properties>*/
    /*<members>*//*</members>*/
    /*<methods>*/
    
    /*</methods>*/
}
