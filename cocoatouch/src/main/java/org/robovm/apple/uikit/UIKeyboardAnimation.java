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
@Marshaler(UIKeyboardAnimation.Marshaler.class)
/*<annotations>*/@Library("UIKit")/*</annotations>*/
/*<visibility>*/public/*</visibility>*/ class UIKeyboardAnimation 
    extends /*<extends>*/Object/*</extends>*/ 
    /*<implements>*//*</implements>*/ {

    public static class Marshaler {
        @SuppressWarnings("unchecked")
        @MarshalsPointer
        public static UIKeyboardAnimation toObject(Class<UIKeyboardAnimation> cls, long handle, long flags) {
            NSDictionary<NSString, NSObject> o = (NSDictionary<NSString, NSObject>) NSObject.Marshaler.toObject(NSDictionary.class, handle, flags);
            if (o == null) {
                return null;
            }
            return new UIKeyboardAnimation(o);
        }
        @MarshalsPointer
        public static long toNative(UIKeyboardAnimation o, long flags) {
            if (o == null) {
                return 0L;
            }
            return NSObject.Marshaler.toNative(o.data, flags);
        }
    }
    
    /*<ptr>*/
    /*</ptr>*/
    
    private NSDictionary<NSString, NSObject> data;
    
    protected UIKeyboardAnimation(NSDictionary<NSString, NSObject> data) {
        this.data = data;
    }
    /*<bind>*/static { Bro.bind(UIKeyboardAnimation.class); }/*</bind>*/
    /*<constants>*//*</constants>*/
    /*<constructors>*//*</constructors>*/
    /*<properties>*//*</properties>*/
    /*<members>*//*</members>*/
    public NSDictionary<NSString, NSObject> getDictionary() {
        return data;
    }
    
    /**
     * @since Available in iOS 3.2 and later.
     */
    public @ByVal CGRect getStartFrame() {
        if (data.containsKey(FrameBeginKey())) {
            NSValue val = (NSValue)data.get(FrameBeginKey());
            return NSValueExtensions.getRectValue(val);
        }
        return null;
    }
    /**
     * @since Available in iOS 3.2 and later.
     */
    public @ByVal CGRect getEndFrame() {
        if (data.containsKey(FrameEndKey())) {
            NSValue val = (NSValue)data.get(FrameEndKey());
            return NSValueExtensions.getRectValue(val);
        }
        return null;
    }
    /**
     * @since Available in iOS 3.0 and later.
     */
    public double getAnimationDuration() {
        if (data.containsKey(AnimationDurationKey())) {
            NSNumber val = (NSNumber)data.get(AnimationDurationKey());
            return val.doubleValue();
        }
        return 0;
    }
    /**
     * @since Available in iOS 3.0 and later.
     */
    public UIViewAnimationCurve getAnimationCurve() {
        if (data.containsKey(AnimationCurveKey())) {
            NSNumber val = (NSNumber)data.get(AnimationCurveKey());
            return UIViewAnimationCurve.valueOf(val.intValue());
        }
        return null;
    }
    /*<methods>*/
    /**
     * @since Available in iOS 3.2 and later.
     */
    @GlobalValue(symbol="UIKeyboardFrameBeginUserInfoKey", optional=true)
    protected static native NSString FrameBeginKey();
    /**
     * @since Available in iOS 3.2 and later.
     */
    @GlobalValue(symbol="UIKeyboardFrameEndUserInfoKey", optional=true)
    protected static native NSString FrameEndKey();
    /**
     * @since Available in iOS 3.0 and later.
     */
    @GlobalValue(symbol="UIKeyboardAnimationDurationUserInfoKey", optional=true)
    protected static native NSString AnimationDurationKey();
    /**
     * @since Available in iOS 3.0 and later.
     */
    @GlobalValue(symbol="UIKeyboardAnimationCurveUserInfoKey", optional=true)
    protected static native NSString AnimationCurveKey();
    /**
     * @since Available in iOS 2.0 and later.
     * @deprecated Deprecated in iOS 3.2.
     */
    @Deprecated
    @GlobalValue(symbol="UIKeyboardCenterBeginUserInfoKey", optional=true)
    protected static native NSString CenterBeginKey();
    /**
     * @since Available in iOS 2.0 and later.
     * @deprecated Deprecated in iOS 3.2.
     */
    @Deprecated
    @GlobalValue(symbol="UIKeyboardCenterEndUserInfoKey", optional=true)
    protected static native NSString CenterEndKey();
    /**
     * @since Available in iOS 2.0 and later.
     * @deprecated Deprecated in iOS 3.2.
     */
    @Deprecated
    @GlobalValue(symbol="UIKeyboardBoundsUserInfoKey", optional=true)
    protected static native NSString BoundsKey();
    /*</methods>*/
    
    @Override
    public String toString() {
        if (data != null) return data.toString();
        return super.toString();
    }
}
