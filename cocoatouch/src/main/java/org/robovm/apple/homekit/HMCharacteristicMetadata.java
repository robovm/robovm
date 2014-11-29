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
/*<visibility>*/public/*</visibility>*/ class /*<name>*/HMCharacteristicMetadata/*</name>*/ 
    extends /*<extends>*/NSObject/*</extends>*/ 
    /*<implements>*//*</implements>*/ {

    /*<ptr>*/public static class HMCharacteristicMetadataPtr extends Ptr<HMCharacteristicMetadata, HMCharacteristicMetadataPtr> {}/*</ptr>*/
    /*<bind>*/static { ObjCRuntime.bind(HMCharacteristicMetadata.class); }/*</bind>*/
    /*<constants>*//*</constants>*/
    /*<constructors>*/
    public HMCharacteristicMetadata() {}
    protected HMCharacteristicMetadata(SkipInit skipInit) { super(skipInit); }
    /*</constructors>*/
    /*<properties>*/
    @Property(selector = "minimumValue")
    public native NSNumber getMinimumValue();
    @Property(selector = "maximumValue")
    public native NSNumber getMaximumValue();
    @Property(selector = "stepValue")
    public native NSNumber getStepValue();
    @Property(selector = "maxLength")
    public native NSNumber getMaxLength();
    @Property(selector = "format")
    public native HMCharacteristicMetadataFormat getFormat();
    @Property(selector = "units")
    public native HMCharacteristicMetadataUnits getUnits();
    @Property(selector = "manufacturerDescription")
    public native String getManufacturerDescription();
    /*</properties>*/
    /*<members>*//*</members>*/
    /*<methods>*/
    
    /*</methods>*/
}
