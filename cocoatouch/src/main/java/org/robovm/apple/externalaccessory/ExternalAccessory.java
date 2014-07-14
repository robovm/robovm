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
package org.robovm.apple.externalaccessory;

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
/*</javadoc>*/
/*<annotations>*/@Library("ExternalAccessory") @Marshaler(NSString.AsStringMarshaler.class)/*</annotations>*/
/*<visibility>*/public/*</visibility>*/ class /*<name>*/ExternalAccessory/*</name>*/ 
    extends /*<extends>*/Object/*</extends>*/ 
    /*<implements>*//*</implements>*/ {

    /*<ptr>*/
    /*</ptr>*/
    /*<bind>*/static { Bro.bind(ExternalAccessory.class); }/*</bind>*/
    /*<constants>*/
    public static final int ConnectionIDNone = 0;
    /*</constants>*/
    /*<constructors>*//*</constructors>*/
    /*<properties>*//*</properties>*/
    /*<members>*//*</members>*/
    /*<methods>*/
    @GlobalValue(symbol="EABluetoothAccessoryPickerErrorDomain", optional=true)
    public static native NSString BluetoothAccessoryPickerErrorDomain();
    /**
     * @since Available in iOS 3.0 and later.
     */
    @GlobalValue(symbol="EAAccessoryDidConnectNotification", optional=true)
    public static native NSString AccessoryDidConnectNotification();
    /**
     * @since Available in iOS 3.0 and later.
     */
    @GlobalValue(symbol="EAAccessoryDidDisconnectNotification", optional=true)
    public static native NSString AccessoryDidDisconnectNotification();
    /**
     * @since Available in iOS 3.0 and later.
     */
    @GlobalValue(symbol="EAAccessoryKey", optional=true)
    public static native NSString AccessoryKey();
    /**
     * @since Available in iOS 6.0 and later.
     */
    @GlobalValue(symbol="EAAccessorySelectedKey", optional=true)
    public static native NSString AccessorySelectedKey();
    /*</methods>*/
}
