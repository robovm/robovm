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
/*</imports>*/

/*<javadoc>*/
/*</javadoc>*/
/*<annotations>*/@Library("UIKit")/*</annotations>*/
/*<visibility>*/public final/*</visibility>*/ class /*<name>*/NSCoderExtensions/*</name>*/ 
    extends /*<extends>*/NSExtensions/*</extends>*/ 
    /*<implements>*//*</implements>*/ {

    /*<ptr>*/
    /*</ptr>*/
    /*<bind>*/static { ObjCRuntime.bind(NSCoderExtensions.class); }/*</bind>*/
    /*<constants>*//*</constants>*/
    /*<constructors>*/
    private NSCoderExtensions() {}
    /*</constructors>*/
    /*<properties>*/
    
    /*</properties>*/
    /*<members>*//*</members>*/
    /*<methods>*/
    @Method(selector = "encodeCGPoint:forKey:")
    public static native void encodeCGPoint(NSCoder thiz, @ByVal CGPoint point, String key);
    @Method(selector = "encodeCGVector:forKey:")
    public static native void encodeCGVector(NSCoder thiz, @ByVal CGVector vector, String key);
    @Method(selector = "encodeCGSize:forKey:")
    public static native void encodeCGSize(NSCoder thiz, @ByVal CGSize size, String key);
    @Method(selector = "encodeCGRect:forKey:")
    public static native void encodeCGRect(NSCoder thiz, @ByVal CGRect rect, String key);
    @Method(selector = "encodeCGAffineTransform:forKey:")
    public static native void encodeCGAffineTransform(NSCoder thiz, @ByVal CGAffineTransform transform, String key);
    @Method(selector = "encodeUIEdgeInsets:forKey:")
    public static native void encodeUIEdgeInsets(NSCoder thiz, @ByVal UIEdgeInsets insets, String key);
    /**
     * @since Available in iOS 5.0 and later.
     */
    @Method(selector = "encodeUIOffset:forKey:")
    public static native void encodeUIOffset(NSCoder thiz, @ByVal UIOffset offset, String key);
    @Method(selector = "decodeCGPointForKey:")
    public static native @ByVal CGPoint decodeCGPoint(NSCoder thiz, String key);
    @Method(selector = "decodeCGVectorForKey:")
    public static native @ByVal CGVector decodeCGVector(NSCoder thiz, String key);
    @Method(selector = "decodeCGSizeForKey:")
    public static native @ByVal CGSize decodeCGSize(NSCoder thiz, String key);
    @Method(selector = "decodeCGRectForKey:")
    public static native @ByVal CGRect decodeCGRect(NSCoder thiz, String key);
    @Method(selector = "decodeCGAffineTransformForKey:")
    public static native @ByVal CGAffineTransform decodeCGAffineTransform(NSCoder thiz, String key);
    @Method(selector = "decodeUIEdgeInsetsForKey:")
    public static native @ByVal UIEdgeInsets decodeUIEdgeInsets(NSCoder thiz, String key);
    /**
     * @since Available in iOS 5.0 and later.
     */
    @Method(selector = "decodeUIOffsetForKey:")
    public static native @ByVal UIOffset decodeUIOffset(NSCoder thiz, String key);
    /*</methods>*/
}
