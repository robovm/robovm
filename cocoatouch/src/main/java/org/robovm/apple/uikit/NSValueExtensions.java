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
/*</imports>*/

/*<javadoc>*/
/*</javadoc>*/
/*<annotations>*/@Library("UIKit")/*</annotations>*/
/*<visibility>*/public final/*</visibility>*/ class /*<name>*/NSValueExtensions/*</name>*/ 
    extends /*<extends>*/NSExtensions/*</extends>*/ 
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
    /**
     * @since Available in iOS 5.0 and later.
     */
    @Method(selector = "UIOffsetValue")
    public static native @ByVal UIOffset UIOffsetValue(NSValue thiz);
    @Method(selector = "valueWithCGPoint:")
    protected static native NSValue valueOf(ObjCClass clazz, @ByVal CGPoint point);
    public static NSValue valueOf(@ByVal CGPoint point) { return valueOf(ObjCClass.getByType(NSValue.class), point); }
    @Method(selector = "valueWithCGSize:")
    protected static native NSValue valueOf(ObjCClass clazz, @ByVal CGSize size);
    public static NSValue valueOf(@ByVal CGSize size) { return valueOf(ObjCClass.getByType(NSValue.class), size); }
    @Method(selector = "valueWithCGRect:")
    protected static native NSValue valueOf(ObjCClass clazz, @ByVal CGRect rect);
    public static NSValue valueOf(@ByVal CGRect rect) { return valueOf(ObjCClass.getByType(NSValue.class), rect); }
    @Method(selector = "valueWithCGAffineTransform:")
    protected static native NSValue valueOf(ObjCClass clazz, @ByVal CGAffineTransform transform);
    public static NSValue valueOf(@ByVal CGAffineTransform transform) { return valueOf(ObjCClass.getByType(NSValue.class), transform); }
    @Method(selector = "valueWithUIEdgeInsets:")
    protected static native NSValue valueOf(ObjCClass clazz, @ByVal UIEdgeInsets insets);
    public static NSValue valueOf(@ByVal UIEdgeInsets insets) { return valueOf(ObjCClass.getByType(NSValue.class), insets); }
    /**
     * @since Available in iOS 5.0 and later.
     */
    @Method(selector = "valueWithUIOffset:")
    protected static native NSValue valueOf(ObjCClass clazz, @ByVal UIOffset insets);
    public static NSValue valueOf(@ByVal UIOffset insets) { return valueOf(ObjCClass.getByType(NSValue.class), insets); }
    /*</methods>*/
}
