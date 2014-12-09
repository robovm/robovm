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
/*</javadoc>*/
/*<annotations>*/@Library("MapKit")/*</annotations>*/
/*<visibility>*/public final/*</visibility>*/ class /*<name>*/NSValueExtensions/*</name>*/ 
    extends /*<extends>*/NSExtensions/*</extends>*/ 
    /*<implements>*//*</implements>*/ {

    /*<ptr>*/
    /*</ptr>*/
    /*<bind>*/static { ObjCRuntime.bind(NSValueExtensions.class); }/*</bind>*/
    /*<constants>*//*</constants>*/
    /*<constructors>*/
    private NSValueExtensions() {}
    /*</constructors>*/
    /*<properties>*/
    @Property(selector = "MKCoordinateValue")
    public static native @ByVal CLLocationCoordinate2D getMKCoordinateValue(NSValue thiz);
    @Property(selector = "MKCoordinateSpanValue")
    public static native @ByVal MKCoordinateSpan getMKCoordinateSpanValue(NSValue thiz);
    /*</properties>*/
    /*<members>*//*</members>*/
    /*<methods>*/
    @Method(selector = "valueWithMKCoordinate:")
    protected static native NSValue create(ObjCClass clazz, @ByVal CLLocationCoordinate2D coordinate);
    public static NSValue create(@ByVal CLLocationCoordinate2D coordinate) { return create(ObjCClass.getByType(NSValue.class), coordinate); }
    @Method(selector = "valueWithMKCoordinateSpan:")
    protected static native NSValue create(ObjCClass clazz, @ByVal MKCoordinateSpan span);
    public static NSValue create(@ByVal MKCoordinateSpan span) { return create(ObjCClass.getByType(NSValue.class), span); }
    /*</methods>*/
}
