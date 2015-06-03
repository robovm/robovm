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
import org.robovm.apple.corefoundation.*;
import org.robovm.apple.coregraphics.*;
import org.robovm.apple.corelocation.*;
import org.robovm.apple.uikit.*;
import org.robovm.apple.dispatch.*;
/*</imports>*/

/*<javadoc>*/
/**
 * @since Available in iOS 7.0 and later.
 */
/*</javadoc>*/
/*<annotations>*/@Library("MapKit") @NativeClass/*</annotations>*/
/*<visibility>*/public/*</visibility>*/ class /*<name>*/MKDirections/*</name>*/ 
    extends /*<extends>*/NSObject/*</extends>*/ 
    /*<implements>*//*</implements>*/ {

    /*<ptr>*/public static class MKDirectionsPtr extends Ptr<MKDirections, MKDirectionsPtr> {}/*</ptr>*/
    /*<bind>*/static { ObjCRuntime.bind(MKDirections.class); }/*</bind>*/
    /*<constants>*//*</constants>*/
    /*<constructors>*/
    public MKDirections() {}
    protected MKDirections(SkipInit skipInit) { super(skipInit); }
    public MKDirections(MKDirectionsRequest request) { super((SkipInit) null); initObject(init(request)); }
    /*</constructors>*/
    /*<properties>*/
    @Property(selector = "isCalculating")
    public native boolean isCalculating();
    /*</properties>*/
    /*<members>*//*</members>*/
    /*<methods>*/
    @Method(selector = "initWithRequest:")
    protected native @Pointer long init(MKDirectionsRequest request);
    @Method(selector = "calculateDirectionsWithCompletionHandler:")
    public native void calculateDirections(@Block VoidBlock2<MKDirectionsResponse, NSError> completionHandler);
    @Method(selector = "calculateETAWithCompletionHandler:")
    public native void calculateETA(@Block VoidBlock2<MKETAResponse, NSError> completionHandler);
    @Method(selector = "cancel")
    public native void cancel();
    /*</methods>*/
}
