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
package org.robovm.apple.corebluetooth;

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
import org.robovm.apple.corefoundation.*;
import org.robovm.apple.dispatch.*;
/*</imports>*/

/*<javadoc>*/
/**
 * @since Available in iOS 6.0 and later.
 */
/*</javadoc>*/
/*<annotations>*/@Library("CoreBluetooth") @NativeClass/*</annotations>*/
/*<visibility>*/public/*</visibility>*/ class /*<name>*/CBMutableService/*</name>*/ 
    extends /*<extends>*/CBService/*</extends>*/ 
    /*<implements>*//*</implements>*/ {

    /*<ptr>*/public static class CBMutableServicePtr extends Ptr<CBMutableService, CBMutableServicePtr> {}/*</ptr>*/
    /*<bind>*/static { ObjCRuntime.bind(CBMutableService.class); }/*</bind>*/
    /*<constants>*//*</constants>*/
    /*<constructors>*/
    public CBMutableService() {}
    protected CBMutableService(SkipInit skipInit) { super(skipInit); }
    public CBMutableService(CBUUID UUID, boolean isPrimary) { super((SkipInit) null); initObject(init(UUID, isPrimary)); }
    /*</constructors>*/
    /*<properties>*/
    @Property(selector = "UUID")
    public native CBUUID getUUID();
    @Property(selector = "setUUID:")
    public native void setUUID(CBUUID v);
    @Property(selector = "isPrimary")
    public native boolean isPrimary();
    @Property(selector = "setIsPrimary:")
    public native void setPrimary(boolean v);
    @Property(selector = "includedServices")
    public native NSArray<CBService> getIncludedServices();
    @Property(selector = "setIncludedServices:")
    public native void setIncludedServices(NSArray<CBService> v);
    @Property(selector = "characteristics")
    public native NSArray<CBCharacteristic> getCharacteristics();
    @Property(selector = "setCharacteristics:")
    public native void setCharacteristics(NSArray<CBCharacteristic> v);
    /*</properties>*/
    /*<members>*//*</members>*/
    /*<methods>*/
    @Method(selector = "initWithType:primary:")
    protected native @Pointer long init(CBUUID UUID, boolean isPrimary);
    /*</methods>*/
}
