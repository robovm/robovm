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
import org.robovm.apple.corelocation.*;
/*</imports>*/

/*<javadoc>*/
/*</javadoc>*/
@Marshaler(UITextInputTextStyle.Marshaler.class)
/*<annotations>*/@Library("UIKit")/*</annotations>*/
/*<visibility>*/public/*</visibility>*/ class /*<name>*/UITextInputTextStyle/*</name>*/ 
    extends /*<extends>*/CocoaUtility/*</extends>*/ 
    /*<implements>*//*</implements>*/ {

    public static class Marshaler {
        @SuppressWarnings("unchecked")
        @MarshalsPointer
        public static UITextInputTextStyle toObject(Class<UITextInputTextStyle> cls, long handle, long flags) {
            NSDictionary<NSString, NSObject> o = (NSDictionary<NSString, NSObject>) NSObject.Marshaler.toObject(NSDictionary.class, handle, flags);
            if (o == null) {
                return null;
            }
            return new UITextInputTextStyle(o);
        }
        @MarshalsPointer
        public static long toNative(UITextInputTextStyle o, long flags) {
            if (o == null) {
                return 0L;
            }
            return NSObject.Marshaler.toNative(o.data, flags);
        }
    }
    
    /*<ptr>*/
    /*</ptr>*/
    private NSDictionary<NSString, NSObject> data;
    
    protected UITextInputTextStyle(NSDictionary<NSString, NSObject> data) {
        this.data = data;
    }
    public UITextInputTextStyle() {
        this.data = new NSMutableDictionary<>();
    }
    /*<bind>*/static { Bro.bind(UITextInputTextStyle.class); }/*</bind>*/
    /*<constants>*//*</constants>*/
    /*<constructors>*//*</constructors>*/
    /*<properties>*//*</properties>*/
    /*<members>*//*</members>*/
    public NSDictionary<NSString, NSObject> getDictionary() {
        return data;
    }
    
    public UIColor getBackgroundColor() {
        if (data.containsKey(BackgroundColorKey())) {
            UIColor val = (UIColor)data.get(BackgroundColorKey());
            return val;
        }
        return null;
    }
    public UITextInputTextStyle setBackgroundColor(UIColor color) {
        data.put(BackgroundColorKey(), color);
        return this;
    }
    public UIColor getTextColor() {
        if (data.containsKey(ColorKey())) {
            UIColor val = (UIColor)data.get(ColorKey());
            return val;
        }
        return null;
    }
    public UITextInputTextStyle setTextColor(UIColor color) {
        data.put(ColorKey(), color);
        return this;
    }
    public UIFont getFont() {
        if (data.containsKey(FontKey())) {
            UIFont val = (UIFont)data.get(FontKey());
            return val;
        }
        return null;
    }
    public UITextInputTextStyle setFont(UIFont font) {
        data.put(FontKey(), font);
        return this;
    }
    /*<methods>*/
    /**
     * @since Available in iOS 3.2 and later.
     * @deprecated Deprecated in iOS 8.0.
     */
    @Deprecated
    @GlobalValue(symbol="UITextInputTextBackgroundColorKey", optional=true)
    protected static native NSString BackgroundColorKey();
    /**
     * @since Available in iOS 3.2 and later.
     * @deprecated Deprecated in iOS 8.0.
     */
    @Deprecated
    @GlobalValue(symbol="UITextInputTextColorKey", optional=true)
    protected static native NSString ColorKey();
    /**
     * @since Available in iOS 3.2 and later.
     * @deprecated Deprecated in iOS 8.0.
     */
    @Deprecated
    @GlobalValue(symbol="UITextInputTextFontKey", optional=true)
    protected static native NSString FontKey();
    /*</methods>*/
    
    @Override
    public String toString() {
        if (data != null) return data.toString();
        return super.toString();
    }
}
