/*
 * Copyright (C) 2014 Trillian AB
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

/**
 *
 * <div class="javadoc"></div>
 */
/*<annotations>*/@Library("CoreGraphics")/*</annotations>*/
/*<visibility>*/public/*</visibility>*/ class /*<name>*/CGPoint/*</name>*/ 
    extends /*<extends>*/Struct<CGPoint>/*</extends>*/ 
    /*<implements>*//*</implements>*/ {

    /*<ptr>*/public static class CGPointPtr extends Ptr<CGPoint, CGPointPtr> {}/*</ptr>*/
    /*<bind>*/static { Bro.bind(CGPoint.class); }/*</bind>*/
    /*<constants>*//*</constants>*/
    /*<constructors>*/
    public CGPoint() {}
    public CGPoint(@MachineSizedFloat double x, @MachineSizedFloat double y) {
        this.x(x);
        this.y(y);
    }
    /*</constructors>*/
    /*<properties>*//*</properties>*/
    /*<members>*/
    @StructMember(0) public native @MachineSizedFloat double x();
    @StructMember(0) public native CGPoint x(@MachineSizedFloat double x);
    @StructMember(1) public native @MachineSizedFloat double y();
    @StructMember(1) public native CGPoint y(@MachineSizedFloat double y);
    /*</members>*/
    
    public CGPoint applyAffineTransform(CGAffineTransform t) {
        return applyAffineTransform(this, t);
    }
    
    @Override
    public boolean equals(Object obj) {
        return obj instanceof CGPoint && equalToPoint(this, (CGPoint) obj);
    }
    
    /*<methods>*/
    @GlobalValue(symbol="CGPointZero")
    public static native @ByVal CGPoint Zero();
    
    @Bridge(symbol="CGPointEqualToPoint")
    protected static native boolean equalToPoint(@ByVal CGPoint point1, @ByVal CGPoint point2);
    @Bridge(symbol="CGPointApplyAffineTransform")
    protected static native @ByVal CGPoint applyAffineTransform(@ByVal CGPoint point, @ByVal CGAffineTransform t);
    /*</methods>*/
}
