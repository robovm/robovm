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
package org.robovm.apple.foundation;

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
import org.robovm.apple.corefoundation.*;
import org.robovm.apple.uikit.*;
import org.robovm.apple.coreanimation.*;
import org.robovm.apple.coredata.*;
import org.robovm.apple.coregraphics.*;
import org.robovm.apple.coremedia.*;
import org.robovm.apple.security.*;
import org.robovm.apple.dispatch.*;
/*</imports>*/

/*<javadoc>*/

/*</javadoc>*/
/*<annotations>*/@Library("Foundation")/*</annotations>*/
/*<visibility>*/public/*</visibility>*/ class /*<name>*/NSRange/*</name>*/ 
    extends /*<extends>*/Struct<NSRange>/*</extends>*/ 
    /*<implements>*//*</implements>*/ {

    /*<ptr>*/public static class NSRangePtr extends Ptr<NSRange, NSRangePtr> {}/*</ptr>*/
    /*<bind>*/static { Bro.bind(NSRange.class); }/*</bind>*/
    /*<constants>*//*</constants>*/
    /*<constructors>*/
    public NSRange() {}
    public NSRange(@MachineSizedUInt long location, @MachineSizedUInt long length) {
        this.setLocation(location);
        this.setLength(length);
    }
    /*</constructors>*/
    /*<properties>*//*</properties>*/
    /*<members>*/
    @StructMember(0) public native @MachineSizedUInt long getLocation();
    @StructMember(0) public native NSRange setLocation(@MachineSizedUInt long location);
    @StructMember(1) public native @MachineSizedUInt long getLength();
    @StructMember(1) public native NSRange setLength(@MachineSizedUInt long length);
    /*</members>*/
    public long getMaxRange() {
        return getLocation() + getLength();
    }
    
    public boolean isLocationInRange(long location) {
        return (!(location < getLocation()) && (location - getLocation()) < getLength());
    }
    
    public boolean equalsTo(NSRange other) {
        return getLocation() == other.getLocation() && getLength() == other.getLength();
    }
    @Override
    public boolean equals(Object obj) {
        return obj instanceof NSRange && equalsTo((NSRange)obj);
    }
    /*<methods>*/
    @Bridge(symbol="NSUnionRange", optional=true)
    public static native @ByVal NSRange union(@ByVal NSRange range1, @ByVal NSRange range2);
    @Bridge(symbol="NSIntersectionRange", optional=true)
    public static native @ByVal NSRange intersection(@ByVal NSRange range1, @ByVal NSRange range2);
    @Bridge(symbol="NSStringFromRange", optional=true)
    protected static native String toString(@ByVal NSRange range);
    @Bridge(symbol="NSRangeFromString", optional=true)
    public static native @ByVal NSRange fromString(String aString);
    /*</methods>*/
}
