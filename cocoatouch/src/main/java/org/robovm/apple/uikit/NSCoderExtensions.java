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
/*</imports>*/

/**
 *
 * <div class="javadoc"></div>
 */
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
    public static native void encodeCGPoint$forKey$(NSCoder thiz, @ByVal CGPoint point, String key);
    @Method(selector = "encodeCGSize:forKey:")
    public static native void encodeCGSize$forKey$(NSCoder thiz, @ByVal CGSize size, String key);
    @Method(selector = "encodeCGRect:forKey:")
    public static native void encodeCGRect$forKey$(NSCoder thiz, @ByVal CGRect rect, String key);
    @Method(selector = "encodeCGAffineTransform:forKey:")
    public static native void encodeCGAffineTransform$forKey$(NSCoder thiz, @ByVal CGAffineTransform transform, String key);
    @Method(selector = "encodeUIEdgeInsets:forKey:")
    public static native void encodeUIEdgeInsets$forKey$(NSCoder thiz, @ByVal UIEdgeInsets insets, String key);
    @Method(selector = "encodeUIOffset:forKey:")
    public static native void encodeUIOffset$forKey$(NSCoder thiz, @ByVal UIOffset offset, String key);
    @Method(selector = "decodeCGPointForKey:")
    public static native @ByVal CGPoint decodeCGPointForKey$(NSCoder thiz, String key);
    @Method(selector = "decodeCGSizeForKey:")
    public static native @ByVal CGSize decodeCGSizeForKey$(NSCoder thiz, String key);
    @Method(selector = "decodeCGRectForKey:")
    public static native @ByVal CGRect decodeCGRectForKey$(NSCoder thiz, String key);
    @Method(selector = "decodeCGAffineTransformForKey:")
    public static native @ByVal CGAffineTransform decodeCGAffineTransformForKey$(NSCoder thiz, String key);
    @Method(selector = "decodeUIEdgeInsetsForKey:")
    public static native @ByVal UIEdgeInsets decodeUIEdgeInsetsForKey$(NSCoder thiz, String key);
    @Method(selector = "decodeUIOffsetForKey:")
    public static native @ByVal UIOffset decodeUIOffsetForKey$(NSCoder thiz, String key);
    /*</methods>*/
}
