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
/*<visibility>*/public/*</visibility>*/ class /*<name>*/MKMapSize/*</name>*/ 
    extends /*<extends>*/Struct<MKMapSize>/*</extends>*/ 
    /*<implements>*//*</implements>*/ {

    /*<ptr>*/public static class MKMapSizePtr extends Ptr<MKMapSize, MKMapSizePtr> {}/*</ptr>*/
    /*<bind>*/static { Bro.bind(MKMapSize.class); }/*</bind>*/
    /*<constants>*//*</constants>*/
    /*<constructors>*/
    public MKMapSize() {}
    public MKMapSize(double width, double height) {
        this.setWidth(width);
        this.setHeight(height);
    }
    /*</constructors>*/
    /*<properties>*//*</properties>*/
    public boolean equalsTo(MKMapSize other) {
        return getWidth() == other.getWidth() && getHeight() == other.getHeight();
    }
    public boolean equals(Object obj) {
        return obj instanceof MKMapSize && equalsTo((MKMapSize)obj);
    }
    
    public String toString() {
        return String.format("{%.1f, %.1f}", getWidth(), getHeight());
    }
    /*<members>*/
    @StructMember(0) public native double getWidth();
    @StructMember(0) public native MKMapSize setWidth(double width);
    @StructMember(1) public native double getHeight();
    @StructMember(1) public native MKMapSize setHeight(double height);
    /*</members>*/
    /*<methods>*/
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="MKMapSizeWorld", optional=true)
    public static native @ByVal MKMapSize World();
    /*</methods>*/
}
