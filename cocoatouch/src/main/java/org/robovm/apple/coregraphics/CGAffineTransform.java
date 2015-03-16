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
/*<visibility>*/public/*</visibility>*/ class /*<name>*/CGAffineTransform/*</name>*/ 
    extends /*<extends>*/Struct<CGAffineTransform>/*</extends>*/ 
    /*<implements>*//*</implements>*/ {

    /*<ptr>*/public static class CGAffineTransformPtr extends Ptr<CGAffineTransform, CGAffineTransformPtr> {}/*</ptr>*/
    /*<bind>*/static { Bro.bind(CGAffineTransform.class); }/*</bind>*/
    /*<constants>*//*</constants>*/
    /*<constructors>*/
    public CGAffineTransform() {}
    public CGAffineTransform(@MachineSizedFloat double a, @MachineSizedFloat double b, @MachineSizedFloat double c, @MachineSizedFloat double d, @MachineSizedFloat double tx, @MachineSizedFloat double ty) {
        this.setA(a);
        this.setB(b);
        this.setC(c);
        this.setD(d);
        this.setTx(tx);
        this.setTy(ty);
    }
    /*</constructors>*/
    /*<properties>*//*</properties>*/
    /*<members>*/
    @StructMember(0) public native @MachineSizedFloat double getA();
    @StructMember(0) public native CGAffineTransform setA(@MachineSizedFloat double a);
    @StructMember(1) public native @MachineSizedFloat double getB();
    @StructMember(1) public native CGAffineTransform setB(@MachineSizedFloat double b);
    @StructMember(2) public native @MachineSizedFloat double getC();
    @StructMember(2) public native CGAffineTransform setC(@MachineSizedFloat double c);
    @StructMember(3) public native @MachineSizedFloat double getD();
    @StructMember(3) public native CGAffineTransform setD(@MachineSizedFloat double d);
    @StructMember(4) public native @MachineSizedFloat double getTx();
    @StructMember(4) public native CGAffineTransform setTx(@MachineSizedFloat double tx);
    @StructMember(5) public native @MachineSizedFloat double getTy();
    @StructMember(5) public native CGAffineTransform setTy(@MachineSizedFloat double ty);
    /*</members>*/
    
    public static CGAffineTransform fromString(String string) {
        return UIGeometry.stringToCGAffineTransform(string);
    }
    
    @Override
    public boolean equals(Object obj) {
        return obj instanceof CGAffineTransform && equalsTo(this, (CGAffineTransform) obj);
    }
    
    @Override
    public String toString() {
        return UIGeometry.toString(this);
    }
    
    /*<methods>*/
    /**
     * @since Available in iOS 2.0 and later.
     */
    @GlobalValue(symbol="CGAffineTransformIdentity", optional=true)
    public static native @ByVal CGAffineTransform Identity();
    
    /**
     * @since Available in iOS 2.0 and later.
     */
    @Bridge(symbol="CGAffineTransformMakeTranslation", optional=true)
    public static native @ByVal CGAffineTransform createTranslation(@MachineSizedFloat double tx, @MachineSizedFloat double ty);
    /**
     * @since Available in iOS 2.0 and later.
     */
    @Bridge(symbol="CGAffineTransformMakeScale", optional=true)
    public static native @ByVal CGAffineTransform createScale(@MachineSizedFloat double sx, @MachineSizedFloat double sy);
    /**
     * @since Available in iOS 2.0 and later.
     */
    @Bridge(symbol="CGAffineTransformMakeRotation", optional=true)
    public static native @ByVal CGAffineTransform createRotation(@MachineSizedFloat double angle);
    /**
     * @since Available in iOS 2.0 and later.
     */
    public boolean isIdentity() { return isIdentity(this); }
    /**
     * @since Available in iOS 2.0 and later.
     */
    @Bridge(symbol="CGAffineTransformIsIdentity", optional=true)
    private static native boolean isIdentity(@ByVal CGAffineTransform t);
    /**
     * @since Available in iOS 2.0 and later.
     */
    public CGAffineTransform translate(@MachineSizedFloat double tx, @MachineSizedFloat double ty) { return translate(this, tx, ty); }
    /**
     * @since Available in iOS 2.0 and later.
     */
    @Bridge(symbol="CGAffineTransformTranslate", optional=true)
    private static native @ByVal CGAffineTransform translate(@ByVal CGAffineTransform t, @MachineSizedFloat double tx, @MachineSizedFloat double ty);
    /**
     * @since Available in iOS 2.0 and later.
     */
    public CGAffineTransform scale(@MachineSizedFloat double sx, @MachineSizedFloat double sy) { return scale(this, sx, sy); }
    /**
     * @since Available in iOS 2.0 and later.
     */
    @Bridge(symbol="CGAffineTransformScale", optional=true)
    private static native @ByVal CGAffineTransform scale(@ByVal CGAffineTransform t, @MachineSizedFloat double sx, @MachineSizedFloat double sy);
    /**
     * @since Available in iOS 2.0 and later.
     */
    public CGAffineTransform rotate(@MachineSizedFloat double angle) { return rotate(this, angle); }
    /**
     * @since Available in iOS 2.0 and later.
     */
    @Bridge(symbol="CGAffineTransformRotate", optional=true)
    private static native @ByVal CGAffineTransform rotate(@ByVal CGAffineTransform t, @MachineSizedFloat double angle);
    /**
     * @since Available in iOS 2.0 and later.
     */
    public CGAffineTransform invert() { return invert(this); }
    /**
     * @since Available in iOS 2.0 and later.
     */
    @Bridge(symbol="CGAffineTransformInvert", optional=true)
    private static native @ByVal CGAffineTransform invert(@ByVal CGAffineTransform t);
    /**
     * @since Available in iOS 2.0 and later.
     */
    public CGAffineTransform concat(CGAffineTransform t2) { return concat(this, t2); }
    /**
     * @since Available in iOS 2.0 and later.
     */
    @Bridge(symbol="CGAffineTransformConcat", optional=true)
    private static native @ByVal CGAffineTransform concat(@ByVal CGAffineTransform t1, @ByVal CGAffineTransform t2);
    /**
     * @since Available in iOS 2.0 and later.
     */
    public boolean equalsTo(CGAffineTransform t2) { return equalsTo(this, t2); }
    /**
     * @since Available in iOS 2.0 and later.
     */
    @Bridge(symbol="CGAffineTransformEqualToTransform", optional=true)
    private static native boolean equalsTo(@ByVal CGAffineTransform t1, @ByVal CGAffineTransform t2);
    /*</methods>*/
}
