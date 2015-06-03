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
package org.robovm.apple.mapkit;

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
import org.robovm.apple.coregraphics.*;
import org.robovm.apple.corelocation.*;
import org.robovm.apple.uikit.*;
import org.robovm.apple.dispatch.*;
/*</imports>*/

/*<javadoc>*/
/*</javadoc>*/
/*<annotations>*/@Library("MapKit")/*</annotations>*/
/*<visibility>*/public/*</visibility>*/ class /*<name>*/MKDirectionsMode/*</name>*/ 
    extends /*<extends>*/CocoaUtility/*</extends>*/ 
    /*<implements>*//*</implements>*/ {

    /*<ptr>*/
    /*</ptr>*/
    /*<bind>*/static { Bro.bind(MKDirectionsMode.class); }/*</bind>*/
    /*<constants>*//*</constants>*/
    /**
     * @since Available in iOS 6.0 and later.
     */
    public static final MKDirectionsMode Driving = new MKDirectionsMode("DrivingValue");
    /**
     * @since Available in iOS 6.0 and later.
     */
    public static final MKDirectionsMode Walking = new MKDirectionsMode("WalkingValue");
    private static MKDirectionsMode[] values = new MKDirectionsMode[] {Driving, Walking};
    
    private final LazyGlobalValue<NSString> lazyGlobalValue;
    
    private MKDirectionsMode(String getterName) {
        lazyGlobalValue = new LazyGlobalValue<>(getClass(), getterName);
    }
    /*<constructors>*//*</constructors>*/
    /*<properties>*//*</properties>*/
    /*<members>*//*</members>*/
    public NSString value() {
        return lazyGlobalValue.value();
    }
    
    public static MKDirectionsMode valueOf(NSString value) {
        for (MKDirectionsMode v : values) {
            if (v.value().equals(value)) {
                return v;
            }
        }
        throw new IllegalArgumentException("No constant with value " + value + " found in " 
            + /*<name>*/MKDirectionsMode/*</name>*/.class.getName());
    }
    /*<methods>*/
    /**
     * @since Available in iOS 6.0 and later.
     */
    @GlobalValue(symbol="MKLaunchOptionsDirectionsModeDriving", optional=true)
    protected static native NSString DrivingValue();
    /**
     * @since Available in iOS 6.0 and later.
     */
    @GlobalValue(symbol="MKLaunchOptionsDirectionsModeWalking", optional=true)
    protected static native NSString WalkingValue();
    /*</methods>*/
}
