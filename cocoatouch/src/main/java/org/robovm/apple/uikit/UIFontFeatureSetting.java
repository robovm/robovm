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
@Marshaler(/*<name>*/UIFontFeatureSetting/*</name>*/.Marshaler.class)
/*<visibility>*/public/*</visibility>*/ class /*<name>*/UIFontFeatureSetting/*</name>*/ 
    extends /*<extends>*/NSDictionaryWrapper/*</extends>*/
    /*<implements>*//*</implements>*/ {

    /*<marshalers>*/
    public static class Marshaler {
        @MarshalsPointer
        public static UIFontFeatureSetting toObject(Class<UIFontFeatureSetting> cls, long handle, long flags) {
            NSDictionary<NSString, NSObject> o = (NSDictionary<NSString, NSObject>) NSObject.Marshaler.toObject(NSDictionary.class, handle, flags);
            if (o == null) {
                return null;
            }
            return new UIFontFeatureSetting(o);
        }
        @MarshalsPointer
        public static long toNative(UIFontFeatureSetting o, long flags) {
            if (o == null) {
                return 0L;
            }
            return NSObject.Marshaler.toNative(o.data, flags);
        }
    }
    public static class AsListMarshaler {
        @MarshalsPointer
        public static List<UIFontFeatureSetting> toObject(Class<? extends NSObject> cls, long handle, long flags) {
            NSArray<NSDictionary<NSString, NSObject>> o = (NSArray<NSDictionary<NSString, NSObject>>) NSObject.Marshaler.toObject(NSArray.class, handle, flags);
            if (o == null) {
                return null;
            }
            List<UIFontFeatureSetting> list = new ArrayList<>();
            for (int i = 0; i < o.size(); i++) {
                list.add(new UIFontFeatureSetting(o.get(i)));
            }
            return list;
        }
        @MarshalsPointer
        public static long toNative(List<UIFontFeatureSetting> l, long flags) {
            if (l == null) {
                return 0L;
            }
            NSArray<NSDictionary<NSString, NSObject>> array = new NSMutableArray<>();
            for (UIFontFeatureSetting i : l) {
                array.add(i.getDictionary());
            }
            return NSObject.Marshaler.toNative(array, flags);
        }
    }
    /*</marshalers>*/

    /*<constructors>*/
    UIFontFeatureSetting(NSDictionary<NSString, NSObject> data) {
        super(data);
    }
    public UIFontFeatureSetting() {}
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
    public UIFontFeatureSetting set(NSString key, NSObject value) {
        data.put(key, value);
        return this;
    }
    

    /**
     * @since Available in iOS 7.0 and later.
     */
    public long getTypeIdentifier() {
        if (has(Keys.TypeIdentifier())) {
            NSNumber val = (NSNumber) get(Keys.TypeIdentifier());
            return val.longValue();
        }
        return 0;
    }
    /**
     * @since Available in iOS 7.0 and later.
     */
    public UIFontFeatureSetting setTypeIdentifier(long typeIdentifier) {
        set(Keys.TypeIdentifier(), NSNumber.valueOf(typeIdentifier));
        return this;
    }
    /**
     * @since Available in iOS 7.0 and later.
     */
    public long getSelectorIdentifier() {
        if (has(Keys.SelectorIdentifier())) {
            NSNumber val = (NSNumber) get(Keys.SelectorIdentifier());
            return val.longValue();
        }
        return 0;
    }
    /**
     * @since Available in iOS 7.0 and later.
     */
    public UIFontFeatureSetting setSelectorIdentifier(long selectorIdentifier) {
        set(Keys.SelectorIdentifier(), NSNumber.valueOf(selectorIdentifier));
        return this;
    }
    /*</methods>*/
    
    /*<keys>*/
    @Library("UIKit")
    public static class Keys {
        static { Bro.bind(Keys.class); }
        /**
         * @since Available in iOS 7.0 and later.
         */
        @GlobalValue(symbol="UIFontFeatureTypeIdentifierKey", optional=true)
        public static native NSString TypeIdentifier();
        /**
         * @since Available in iOS 7.0 and later.
         */
        @GlobalValue(symbol="UIFontFeatureSelectorIdentifierKey", optional=true)
        public static native NSString SelectorIdentifier();
    }
    /*</keys>*/
}
