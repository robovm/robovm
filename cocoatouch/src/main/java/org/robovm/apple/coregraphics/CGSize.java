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
        this.width(width);
        this.height(height);
    }
    /*</constructors>*/
    /*<properties>*//*</properties>*/
    /*<members>*/
    @StructMember(0) public native @MachineSizedFloat double width();
    @StructMember(0) public native CGSize width(@MachineSizedFloat double width);
    @StructMember(1) public native @MachineSizedFloat double height();
    @StructMember(1) public native CGSize height(@MachineSizedFloat double height);
    /*</members>*/
    
    public CGSize applyAffineTransform(CGAffineTransform t) {
        return applyAffineTransform(this, t);
    }
    
    @Override
    public boolean equals(Object obj) {
        return obj instanceof CGSize && equalToSize(this, (CGSize) obj);
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
    @Bridge(symbol="CGSizeEqualToSize", optional=true)
    protected static native boolean equalToSize(@ByVal CGSize size1, @ByVal CGSize size2);
    /**
     * @since Available in iOS 2.0 and later.
     */
    @Bridge(symbol="CGSizeApplyAffineTransform", optional=true)
    protected static native @ByVal CGSize applyAffineTransform(@ByVal CGSize size, @ByVal CGAffineTransform t);
    /*</methods>*/
}
