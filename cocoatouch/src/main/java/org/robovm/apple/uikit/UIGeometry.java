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
package org.robovm.apple.uikit;

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
import org.robovm.apple.coreanimation.*;
import org.robovm.apple.coregraphics.*;
import org.robovm.apple.coredata.*;
import org.robovm.apple.coreimage.*;
import org.robovm.apple.coretext.*;
import org.robovm.apple.corelocation.*;
/*</imports>*/

/*<javadoc>*/
/*</javadoc>*/
/*<annotations>*/@Library("UIKit")/*</annotations>*/
@Marshaler(NSString.AsStringMarshaler.class)
/*<visibility>*/public/*</visibility>*/ class /*<name>*/UIGeometry/*</name>*/ 
    extends /*<extends>*/CocoaUtility/*</extends>*/ 
    /*<implements>*//*</implements>*/ {

    /*<ptr>*/
    /*</ptr>*/
    /*<bind>*/static { Bro.bind(UIGeometry.class); }/*</bind>*/
    /*<constants>*//*</constants>*/
    /*<constructors>*//*</constructors>*/
    /*<properties>*//*</properties>*/
    /*<members>*//*</members>*/
    /*<methods>*/
    @Bridge(symbol="NSStringFromCGPoint", optional=true)
    public static native String toString(@ByVal CGPoint point);
    @Bridge(symbol="NSStringFromCGVector", optional=true)
    public static native String toString(@ByVal CGVector vector);
    @Bridge(symbol="NSStringFromCGSize", optional=true)
    public static native String toString(@ByVal CGSize size);
    @Bridge(symbol="NSStringFromCGRect", optional=true)
    public static native String toString(@ByVal CGRect rect);
    @Bridge(symbol="NSStringFromCGAffineTransform", optional=true)
    public static native String toString(@ByVal CGAffineTransform transform);
    @Bridge(symbol="CGPointFromString", optional=true)
    public static native @ByVal CGPoint stringToCGPoint(String string);
    @Bridge(symbol="CGVectorFromString", optional=true)
    public static native @ByVal CGVector stringToCGVector(String string);
    @Bridge(symbol="CGSizeFromString", optional=true)
    public static native @ByVal CGSize stringToCGSize(String string);
    @Bridge(symbol="CGRectFromString", optional=true)
    public static native @ByVal CGRect stringToCGRect(String string);
    @Bridge(symbol="CGAffineTransformFromString", optional=true)
    public static native @ByVal CGAffineTransform stringToCGAffineTransform(String string);
    /*</methods>*/
}
