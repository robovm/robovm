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
package org.robovm.apple.homekit;

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
/*</imports>*/

/*<javadoc>*/
/**
 * @since Available in iOS 8.0 and later.
 */
/*</javadoc>*/
/*<annotations>*/@Library("HomeKit") @NativeClass/*</annotations>*/
/*<visibility>*/public/*</visibility>*/ class /*<name>*/HMCharacteristicWriteAction/*</name>*/ 
    extends /*<extends>*/HMAction/*</extends>*/ 
    /*<implements>*//*</implements>*/ {

    /*<ptr>*/public static class HMCharacteristicWriteActionPtr extends Ptr<HMCharacteristicWriteAction, HMCharacteristicWriteActionPtr> {}/*</ptr>*/
    /*<bind>*/static { ObjCRuntime.bind(HMCharacteristicWriteAction.class); }/*</bind>*/
    /*<constants>*//*</constants>*/
    /*<constructors>*/
    public HMCharacteristicWriteAction() {}
    protected HMCharacteristicWriteAction(SkipInit skipInit) { super(skipInit); }
    public HMCharacteristicWriteAction(HMCharacteristic characteristic, NSObject targetValue) { super((SkipInit) null); initObject(init(characteristic, targetValue)); }
    /*</constructors>*/
    /*<properties>*/
    @Property(selector = "characteristic")
    public native HMCharacteristic getCharacteristic();
    @Property(selector = "targetValue")
    public native NSObject getTargetValue();
    /*</properties>*/
    /*<members>*//*</members>*/
    /*<methods>*/
    @Method(selector = "initWithCharacteristic:targetValue:")
    protected native @Pointer long init(HMCharacteristic characteristic, NSObject targetValue);
    @Method(selector = "updateTargetValue:completionHandler:")
    public native void updateTargetValue(NSObject targetValue, @Block VoidBlock1<NSError> completion);
    /*</methods>*/
}
