/*
 * Copyright (C) 2013-2015 Trillian Mobile AB
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
/*<visibility>*/public/*</visibility>*/ class /*<name>*/MKMapSnapshotOptions/*</name>*/ 
    extends /*<extends>*/NSObject/*</extends>*/ 
    /*<implements>*//*</implements>*/ {

    /*<ptr>*/public static class MKMapSnapshotOptionsPtr extends Ptr<MKMapSnapshotOptions, MKMapSnapshotOptionsPtr> {}/*</ptr>*/
    /*<bind>*/static { ObjCRuntime.bind(MKMapSnapshotOptions.class); }/*</bind>*/
    /*<constants>*//*</constants>*/
    /*<constructors>*/
    public MKMapSnapshotOptions() {}
    protected MKMapSnapshotOptions(SkipInit skipInit) { super(skipInit); }
    /*</constructors>*/
    /*<properties>*/
    @Property(selector = "camera")
    public native MKMapCamera getCamera();
    @Property(selector = "setCamera:")
    public native void setCamera(MKMapCamera v);
    @Property(selector = "mapRect")
    public native @ByVal MKMapRect getMapRect();
    @Property(selector = "setMapRect:")
    public native void setMapRect(@ByVal MKMapRect v);
    @Property(selector = "region")
    public native @ByVal MKCoordinateRegion getRegion();
    @Property(selector = "setRegion:")
    public native void setRegion(@ByVal MKCoordinateRegion v);
    @Property(selector = "mapType")
    public native MKMapType getMapType();
    @Property(selector = "setMapType:")
    public native void setMapType(MKMapType v);
    @Property(selector = "showsPointsOfInterest")
    public native boolean showsPointsOfInterest();
    @Property(selector = "setShowsPointsOfInterest:")
    public native void setShowsPointsOfInterest(boolean v);
    @Property(selector = "showsBuildings")
    public native boolean showsBuildings();
    @Property(selector = "setShowsBuildings:")
    public native void setShowsBuildings(boolean v);
    @Property(selector = "size")
    public native @ByVal CGSize getSize();
    @Property(selector = "setSize:")
    public native void setSize(@ByVal CGSize v);
    @Property(selector = "scale")
    public native @MachineSizedFloat double getScale();
    @Property(selector = "setScale:")
    public native void setScale(@MachineSizedFloat double v);
    /*</properties>*/
    /*<members>*//*</members>*/
    /*<methods>*/
    
    /*</methods>*/
}
