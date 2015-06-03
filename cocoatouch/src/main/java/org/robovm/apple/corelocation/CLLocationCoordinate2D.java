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
package org.robovm.apple.corelocation;

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
import org.robovm.apple.addressbook.*;
import org.robovm.apple.corebluetooth.*;
/*</imports>*/

/*<javadoc>*/

/*</javadoc>*/
/*<annotations>*/@Library("CoreLocation")/*</annotations>*/
/*<visibility>*/public/*</visibility>*/ class /*<name>*/CLLocationCoordinate2D/*</name>*/ 
    extends /*<extends>*/Struct<CLLocationCoordinate2D>/*</extends>*/ 
    /*<implements>*//*</implements>*/ {

    /*<ptr>*/public static class CLLocationCoordinate2DPtr extends Ptr<CLLocationCoordinate2D, CLLocationCoordinate2DPtr> {}/*</ptr>*/
    /*<bind>*/static { Bro.bind(CLLocationCoordinate2D.class); }/*</bind>*/
    /*<constants>*//*</constants>*/
    /*<constructors>*/
    public CLLocationCoordinate2D() {}
    public CLLocationCoordinate2D(double latitude, double longitude) {
        this.setLatitude(latitude);
        this.setLongitude(longitude);
    }
    /*</constructors>*/
    /*<properties>*//*</properties>*/
    /*<members>*/
    @StructMember(0) public native double getLatitude();
    @StructMember(0) public native CLLocationCoordinate2D setLatitude(double latitude);
    @StructMember(1) public native double getLongitude();
    @StructMember(1) public native CLLocationCoordinate2D setLongitude(double longitude);
    /*</members>*/
    /*<methods>*/
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="kCLLocationCoordinate2DInvalid", optional=true)
    public static native @ByVal CLLocationCoordinate2D Invalid();
    
    /**
     * @since Available in iOS 4.0 and later.
     */
    @Bridge(symbol="CLLocationCoordinate2DIsValid", optional=true)
    private static native boolean isValid(@ByVal CLLocationCoordinate2D coord);
    /*</methods>*/
}
