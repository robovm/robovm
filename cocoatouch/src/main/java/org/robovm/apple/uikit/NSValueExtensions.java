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
/*<visibility>*/public final/*</visibility>*/ class /*<name>*/NSValueExtensions/*</name>*/ 
    extends /*<extends>*/NSCategory/*</extends>*/ 
    /*<implements>*//*</implements>*/ {

    /*<ptr>*/
    /*</ptr>*/
    /*<bind>*/static { ObjCRuntime.bind(NSValueExtensions.class); }/*</bind>*/
    /*<constants>*//*</constants>*/
    /*<constructors>*/
    private NSValueExtensions() {}
    /*</constructors>*/
    /*<properties>*/
    
    /*</properties>*/
    /*<members>*//*</members>*/
    /*<methods>*/
    @Method(selector = "CGPointValue")
    public static native @ByVal CGPoint CGPointValue(NSValue thiz);
    @Method(selector = "CGSizeValue")
    public static native @ByVal CGSize CGSizeValue(NSValue thiz);
    @Method(selector = "CGRectValue")
    public static native @ByVal CGRect CGRectValue(NSValue thiz);
    @Method(selector = "CGAffineTransformValue")
    public static native @ByVal CGAffineTransform CGAffineTransformValue(NSValue thiz);
    @Method(selector = "UIEdgeInsetsValue")
    public static native @ByVal UIEdgeInsets UIEdgeInsetsValue(NSValue thiz);
    @Method(selector = "UIOffsetValue")
    public static native @ByVal UIOffset UIOffsetValue(NSValue thiz);
    @Method(selector = "valueWithCGPoint:")
    public static native NSValue valueWithCGPoint$(NSValue thiz, @ByVal CGPoint point);
    @Method(selector = "valueWithCGSize:")
    public static native NSValue valueWithCGSize$(NSValue thiz, @ByVal CGSize size);
    @Method(selector = "valueWithCGRect:")
    public static native NSValue valueWithCGRect$(NSValue thiz, @ByVal CGRect rect);
    @Method(selector = "valueWithCGAffineTransform:")
    public static native NSValue valueWithCGAffineTransform$(NSValue thiz, @ByVal CGAffineTransform transform);
    @Method(selector = "valueWithUIEdgeInsets:")
    public static native NSValue valueWithUIEdgeInsets$(NSValue thiz, @ByVal UIEdgeInsets insets);
    @Method(selector = "valueWithUIOffset:")
    public static native NSValue valueWithUIOffset$(NSValue thiz, @ByVal UIOffset insets);
    /*</methods>*/
}
