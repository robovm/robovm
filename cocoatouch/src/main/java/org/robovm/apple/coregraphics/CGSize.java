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
package org.robovm.apple.coregraphics;

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
import org.robovm.apple.foundation.*;
import org.robovm.apple.uikit.*;
/*</imports>*/

/*<javadoc>*/

/*</javadoc>*/
/*<annotations>*/@Library("CoreGraphics")/*</annotations>*/
/*<visibility>*/public/*</visibility>*/ class /*<name>*/CGSize/*</name>*/ 
    extends /*<extends>*/Struct<CGSize>/*</extends>*/ 
    /*<implements>*//*</implements>*/ {

    /*<ptr>*/public static class CGSizePtr extends Ptr<CGSize, CGSizePtr> {}/*</ptr>*/
    /*<bind>*/static { Bro.bind(CGSize.class); }/*</bind>*/
    /*<constants>*//*</constants>*/
    /*<constructors>*/
    public CGSize() {}
    public CGSize(@MachineSizedFloat double width, @MachineSizedFloat double height) {
        this.setWidth(width);
        this.setHeight(height);
    }
    /*</constructors>*/
    /*<properties>*//*</properties>*/
    /*<members>*/
    @StructMember(0) public native @MachineSizedFloat double getWidth();
    @StructMember(0) public native CGSize setWidth(@MachineSizedFloat double width);
    @StructMember(1) public native @MachineSizedFloat double getHeight();
    @StructMember(1) public native CGSize setHeight(@MachineSizedFloat double height);
    /*</members>*/

    public static CGSize fromString(String string) {
        return UIGeometry.stringToCGSize(string);
    }
    
    public static CGSize fromDictionary(NSDictionary<NSString, NSNumber> dict) {
        CGSize s = new CGSize();
        if (!fromDictionary(dict, s)) {
            throw new IllegalArgumentException("Failed to create CGSize from dictionary " + dict);
        }
        return s;
    }

    @Override
    public boolean equals(Object obj) {
        return obj instanceof CGSize && equalToSize(this, (CGSize) obj);
    }
    
    @Override
    public String toString() {
        return UIGeometry.toString(this);
    }
    
    /*<methods>*/
    /**
     * @since Available in iOS 2.0 and later.
     */
    @GlobalValue(symbol="CGSizeZero", optional=true)
    public static native @ByVal CGSize Zero();
    
    /**
     * @since Available in iOS 2.0 and later.
     */
    public boolean equalToSize(CGSize size2) { return equalToSize(this, size2); }
    @Bridge(symbol="CGSizeEqualToSize", optional=true)
    private static native boolean equalToSize(@ByVal CGSize size1, @ByVal CGSize size2);
    /**
     * @since Available in iOS 2.0 and later.
     */
    public NSDictionary<NSString, NSNumber> toDictionary() { return toDictionary(this); }
    @Bridge(symbol="CGSizeCreateDictionaryRepresentation", optional=true)
    private static native NSDictionary<NSString, NSNumber> toDictionary(@ByVal CGSize size);
    /**
     * @since Available in iOS 2.0 and later.
     */
    @Bridge(symbol="CGSizeMakeWithDictionaryRepresentation", optional=true)
    private static native boolean fromDictionary(NSDictionary<NSString, NSNumber> dict, CGSize size);
    /**
     * @since Available in iOS 2.0 and later.
     */
    public CGSize apply(CGAffineTransform t) { return apply(this, t); }
    @Bridge(symbol="CGSizeApplyAffineTransform", optional=true)
    private static native @ByVal CGSize apply(@ByVal CGSize size, @ByVal CGAffineTransform t);
    /*</methods>*/
}
