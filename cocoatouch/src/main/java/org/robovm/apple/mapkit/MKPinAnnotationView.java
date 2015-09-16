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
/**
 * @since Available in iOS 3.0 and later.
 */
/*</javadoc>*/
/*<annotations>*/@Library("MapKit") @NativeClass/*</annotations>*/
/*<visibility>*/public/*</visibility>*/ class /*<name>*/MKPinAnnotationView/*</name>*/ 
    extends /*<extends>*/MKAnnotationView/*</extends>*/ 
    /*<implements>*//*</implements>*/ {

    /*<ptr>*/public static class MKPinAnnotationViewPtr extends Ptr<MKPinAnnotationView, MKPinAnnotationViewPtr> {}/*</ptr>*/
    /*<bind>*/static { ObjCRuntime.bind(MKPinAnnotationView.class); }/*</bind>*/
    /*<constants>*//*</constants>*/
    /*<constructors>*/
    public MKPinAnnotationView() {}
    protected MKPinAnnotationView(SkipInit skipInit) { super(skipInit); }
    /*</constructors>*/
    public MKPinAnnotationView(MKAnnotation annotation, String reuseIdentifier) {
    	super(annotation, reuseIdentifier);
    }
    public MKPinAnnotationView(CGRect frame) {
    	super(frame);
    }
    /*<properties>*/
    /**
     * @since Available in iOS 9.0 and later.
     */
    @Property(selector = "pinTintColor")
    public native UIColor getPinTintColor();
    /**
     * @since Available in iOS 9.0 and later.
     */
    @Property(selector = "setPinTintColor:")
    public native void setPinTintColor(UIColor v);
    @Property(selector = "animatesDrop")
    public native boolean animatesDrop();
    @Property(selector = "setAnimatesDrop:")
    public native void setAnimatesDrop(boolean v);
    /**
     * @since Available in iOS 3.0 and later.
     * @deprecated Deprecated in iOS 9.0.
     */
    @Deprecated
    @Property(selector = "pinColor")
    public native MKPinAnnotationColor getPinColor();
    /**
     * @since Available in iOS 3.0 and later.
     * @deprecated Deprecated in iOS 9.0.
     */
    @Deprecated
    @Property(selector = "setPinColor:")
    public native void setPinColor(MKPinAnnotationColor v);
    /*</properties>*/
    /*<members>*//*</members>*/
    /*<methods>*/
    /**
     * @since Available in iOS 9.0 and later.
     */
    @Method(selector = "redPinColor")
    public static native UIColor getRedPinColor();
    /**
     * @since Available in iOS 9.0 and later.
     */
    @Method(selector = "greenPinColor")
    public static native UIColor getGreenPinColor();
    /**
     * @since Available in iOS 9.0 and later.
     */
    @Method(selector = "purplePinColor")
    public static native UIColor getPurplePinColor();
    /*</methods>*/
}
