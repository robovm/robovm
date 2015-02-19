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
package org.robovm.apple.homekit;

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
/*</imports>*/

/*<javadoc>*/
/**
 * @since Available in iOS 8.0 and later.
 */
/*</javadoc>*/
/*<annotations>*/@Library("HomeKit") @NativeClass/*</annotations>*/
/*<visibility>*/public/*</visibility>*/ class /*<name>*/HMCharacteristic/*</name>*/ 
    extends /*<extends>*/NSObject/*</extends>*/ 
    /*<implements>*//*</implements>*/ {

    /*<ptr>*/public static class HMCharacteristicPtr extends Ptr<HMCharacteristic, HMCharacteristicPtr> {}/*</ptr>*/
    /*<bind>*/static { ObjCRuntime.bind(HMCharacteristic.class); }/*</bind>*/
    /*<constants>*//*</constants>*/
    /*<constructors>*/
    public HMCharacteristic() {}
    protected HMCharacteristic(SkipInit skipInit) { super(skipInit); }
    /*</constructors>*/
    /*<properties>*/
    @Property(selector = "characteristicType")
    public native HMCharacteristicType getCharacteristicType();
    @Property(selector = "service")
    public native HMService getService();
    @Property(selector = "properties")
    public native @org.robovm.rt.bro.annotation.Marshaler(HMCharacteristicProperty.AsListMarshaler.class) List<HMCharacteristicProperty> getProperties();
    @Property(selector = "metadata")
    public native HMCharacteristicMetadata getMetadata();
    @Property(selector = "value")
    public native NSObject getValue();
    @Property(selector = "isNotificationEnabled")
    public native boolean isNotificationEnabled();
    /*</properties>*/
    /*<members>*//*</members>*/
    /*<methods>*/
    @Method(selector = "writeValue:completionHandler:")
    public native void writeValue(NSObject value, @Block VoidBlock1<NSError> completion);
    @Method(selector = "readValueWithCompletionHandler:")
    public native void readValue(@Block VoidBlock1<NSError> completion);
    @Method(selector = "enableNotification:completionHandler:")
    public native void enableNotification(boolean enable, @Block VoidBlock1<NSError> completion);
    @Method(selector = "updateAuthorizationData:completionHandler:")
    public native void updateAuthorizationData(NSData data, @Block VoidBlock1<NSError> completion);
    /*</methods>*/
}
