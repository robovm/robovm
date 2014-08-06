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
package org.robovm.apple.corebluetooth;

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
import org.robovm.apple.corefoundation.*;
import org.robovm.apple.dispatch.*;
/*</imports>*/

/*<javadoc>*/
/**
 * @since Available in iOS 5.0 and later.
 */
/*</javadoc>*/
/*<annotations>*/@Library("CoreBluetooth") @NativeClass/*</annotations>*/
/*<visibility>*/public/*</visibility>*/ class /*<name>*/CBCharacteristic/*</name>*/ 
    extends /*<extends>*/NSObject/*</extends>*/ 
    /*<implements>*//*</implements>*/ {

    /*<ptr>*/public static class CBCharacteristicPtr extends Ptr<CBCharacteristic, CBCharacteristicPtr> {}/*</ptr>*/
    /*<bind>*/static { ObjCRuntime.bind(CBCharacteristic.class); }/*</bind>*/
    /*<constants>*//*</constants>*/
    /*<constructors>*/
    public CBCharacteristic() {}
    protected CBCharacteristic(SkipInit skipInit) { super(skipInit); }
    /*</constructors>*/
    /*<properties>*/
    @Property(selector = "service")
    public native CBService getService();
    @Property(selector = "UUID")
    public native CBUUID getUUID();
    @Property(selector = "properties")
    public native CBCharacteristicProperties getProperties();
    @Property(selector = "value")
    public native NSData getValue();
    @Property(selector = "descriptors")
    public native NSArray<CBDescriptor> getDescriptors();
    @Property(selector = "isBroadcasted")
    public native boolean isBroadcasted();
    @Property(selector = "isNotifying")
    public native boolean isNotifying();
    /*</properties>*/
    /*<members>*//*</members>*/
    /*<methods>*/
    
    /*</methods>*/
}
