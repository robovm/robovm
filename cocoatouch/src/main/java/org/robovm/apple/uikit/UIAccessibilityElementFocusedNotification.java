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
package org.robovm.apple.uikit;

/*<imports>*/
import java.io.*;
import java.nio.*;
import java.util.*;
import org.robovm.objc.*;
import org.robovm.objc.annotation.*;
import org.robovm.objc.block.*;
import org.robovm.rt.*;
import org.robovm.rt.annotation.*;
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
/*<annotations>*/@Library("UIKit")/*</annotations>*/
@Marshaler(/*<name>*/UIAccessibilityElementFocusedNotification/*</name>*/.Marshaler.class)
/*<visibility>*/public/*</visibility>*/ class /*<name>*/UIAccessibilityElementFocusedNotification/*</name>*/ 
    extends /*<extends>*/NSDictionaryWrapper/*</extends>*/
    /*<implements>*//*</implements>*/ {

    /*<marshalers>*/
    public static class Marshaler {
        @MarshalsPointer
        public static UIAccessibilityElementFocusedNotification toObject(Class<UIAccessibilityElementFocusedNotification> cls, long handle, long flags) {
            NSDictionary o = (NSDictionary) NSObject.Marshaler.toObject(NSDictionary.class, handle, flags);
            if (o == null) {
                return null;
            }
            return new UIAccessibilityElementFocusedNotification(o);
        }
        @MarshalsPointer
        public static long toNative(UIAccessibilityElementFocusedNotification o, long flags) {
            if (o == null) {
                return 0L;
            }
            return NSObject.Marshaler.toNative(o.data, flags);
        }
    }
    public static class AsListMarshaler {
        @MarshalsPointer
        public static List<UIAccessibilityElementFocusedNotification> toObject(Class<? extends NSObject> cls, long handle, long flags) {
            NSArray<NSDictionary> o = (NSArray<NSDictionary>) NSObject.Marshaler.toObject(NSArray.class, handle, flags);
            if (o == null) {
                return null;
            }
            List<UIAccessibilityElementFocusedNotification> list = new ArrayList<>();
            for (int i = 0; i < o.size(); i++) {
                list.add(new UIAccessibilityElementFocusedNotification(o.get(i)));
            }
            return list;
        }
        @MarshalsPointer
        public static long toNative(List<UIAccessibilityElementFocusedNotification> l, long flags) {
            if (l == null) {
                return 0L;
            }
            NSArray<NSDictionary> array = new NSMutableArray<>();
            for (UIAccessibilityElementFocusedNotification i : l) {
                array.add(i.getDictionary());
            }
            return NSObject.Marshaler.toNative(array, flags);
        }
    }
    /*</marshalers>*/

    /*<constructors>*/
    UIAccessibilityElementFocusedNotification(NSDictionary data) {
        super(data);
    }
    /*</constructors>*/

    /*<methods>*/
    public boolean has(NSString key) {
        return data.containsKey(key);
    }
    public NSObject get(NSString key) {
        if (has(key)) {
            return data.get(key);
        }
        return null;
    }
    

    /**
     * @since Available in iOS 9.0 and later.
     */
    public NSObject getFocusedElement() {
        if (has(Keys.FocusedElement())) {
            NSObject val = (NSObject) get(Keys.FocusedElement());
            return val;
        }
        return null;
    }
    /**
     * @since Available in iOS 9.0 and later.
     */
    public NSObject getUnfocusedElement() {
        if (has(Keys.UnfocusedElement())) {
            NSObject val = (NSObject) get(Keys.UnfocusedElement());
            return val;
        }
        return null;
    }
    /**
     * @since Available in iOS 9.0 and later.
     */
    public String getAssistiveTechnology() {
        if (has(Keys.AssistiveTechnology())) {
            NSString val = (NSString) get(Keys.AssistiveTechnology());
            return val.toString();
        }
        return null;
    }
    /*</methods>*/
    
    /*<keys>*/
    @Library("UIKit")
    public static class Keys {
        static { Bro.bind(Keys.class); }
        /**
         * @since Available in iOS 9.0 and later.
         */
        @GlobalValue(symbol="UIAccessibilityFocusedElementKey", optional=true)
        public static native NSString FocusedElement();
        /**
         * @since Available in iOS 9.0 and later.
         */
        @GlobalValue(symbol="UIAccessibilityUnfocusedElementKey", optional=true)
        public static native NSString UnfocusedElement();
        /**
         * @since Available in iOS 9.0 and later.
         */
        @GlobalValue(symbol="UIAccessibilityAssistiveTechnologyKey", optional=true)
        public static native NSString AssistiveTechnology();
    }
    /*</keys>*/
}
