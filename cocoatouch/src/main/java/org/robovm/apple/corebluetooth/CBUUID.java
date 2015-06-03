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
 * @since Available in iOS 5.0 and later.
 */
/*</javadoc>*/
/*<annotations>*/@Library("CoreBluetooth") @NativeClass/*</annotations>*/
/*<visibility>*/public/*</visibility>*/ class /*<name>*/CBUUID/*</name>*/ 
    extends /*<extends>*/NSObject/*</extends>*/ 
    /*<implements>*//*</implements>*/ {

    /*<ptr>*/public static class CBUUIDPtr extends Ptr<CBUUID, CBUUIDPtr> {}/*</ptr>*/
    /*<bind>*/static { ObjCRuntime.bind(CBUUID.class); }/*</bind>*/
    /*<constants>*//*</constants>*/
    /*<constructors>*/
    public CBUUID() {}
    protected CBUUID(SkipInit skipInit) { super(skipInit); }
    /*</constructors>*/
    /*<properties>*/
    @Property(selector = "data")
    public native NSData getData();
    /**
     * @since Available in iOS 7.1 and later.
     */
    @Property(selector = "UUIDString")
    public native String getUUIDString();
    /*</properties>*/
    /*<members>*//*</members>*/
    public static CBUUID create(CBUUIDIdentifier identifier) {
        return create(identifier.value());
    }
    /*<methods>*/
    @Method(selector = "UUIDWithString:")
    public static native CBUUID create(String theString);
    @Method(selector = "UUIDWithData:")
    public static native CBUUID create(NSData theData);
    @WeaklyLinked
    @Method(selector = "UUIDWithCFUUID:")
    public static native CBUUID create(CFUUID theUUID);
    /**
     * @since Available in iOS 7.0 and later.
     */
    @Method(selector = "UUIDWithNSUUID:")
    public static native CBUUID create(NSUUID theUUID);
    /*</methods>*/
}
