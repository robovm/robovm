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
@Marshaler(/*<name>*/UIPageViewControllerOptions/*</name>*/.Marshaler.class)
/*<visibility>*/public/*</visibility>*/ class /*<name>*/UIPageViewControllerOptions/*</name>*/ 
    extends /*<extends>*/NSDictionaryWrapper/*</extends>*/
    /*<implements>*//*</implements>*/ {

    /*<marshalers>*/
    public static class Marshaler {
        @MarshalsPointer
        public static UIPageViewControllerOptions toObject(Class<UIPageViewControllerOptions> cls, long handle, long flags) {
            NSDictionary o = (NSDictionary) NSObject.Marshaler.toObject(NSDictionary.class, handle, flags);
            if (o == null) {
                return null;
            }
            return new UIPageViewControllerOptions(o);
        }
        @MarshalsPointer
        public static long toNative(UIPageViewControllerOptions o, long flags) {
            if (o == null) {
                return 0L;
            }
            return NSObject.Marshaler.toNative(o.data, flags);
        }
    }
    public static class AsListMarshaler {
        @MarshalsPointer
        public static List<UIPageViewControllerOptions> toObject(Class<? extends NSObject> cls, long handle, long flags) {
            NSArray<NSDictionary> o = (NSArray<NSDictionary>) NSObject.Marshaler.toObject(NSArray.class, handle, flags);
            if (o == null) {
                return null;
            }
            List<UIPageViewControllerOptions> list = new ArrayList<>();
            for (int i = 0; i < o.size(); i++) {
                list.add(new UIPageViewControllerOptions(o.get(i)));
            }
            return list;
        }
        @MarshalsPointer
        public static long toNative(List<UIPageViewControllerOptions> l, long flags) {
            if (l == null) {
                return 0L;
            }
            NSArray<NSDictionary> array = new NSMutableArray<>();
            for (UIPageViewControllerOptions i : l) {
                array.add(i.getDictionary());
            }
            return NSObject.Marshaler.toNative(array, flags);
        }
    }
    /*</marshalers>*/

    /*<constructors>*/
    UIPageViewControllerOptions(NSDictionary data) {
        super(data);
    }
    public UIPageViewControllerOptions() {}
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
    public UIPageViewControllerOptions set(NSString key, NSObject value) {
        data.put(key, value);
        return this;
    }
    

    public UIPageViewControllerSpineLocation getSpineLocation() {
        if (has(Keys.SpineLocation())) {
            NSNumber val = (NSNumber) get(Keys.SpineLocation());
            return UIPageViewControllerSpineLocation.valueOf(val.longValue());
        }
        return null;
    }
    public UIPageViewControllerOptions setSpineLocation(UIPageViewControllerSpineLocation spineLocation) {
        set(Keys.SpineLocation(), NSNumber.valueOf(spineLocation.value()));
        return this;
    }
    /**
     * @since Available in iOS 6.0 and later.
     */
    public double getInterPageSpacing() {
        if (has(Keys.InterPageSpacing())) {
            NSNumber val = (NSNumber) get(Keys.InterPageSpacing());
            return val.doubleValue();
        }
        return 0;
    }
    /**
     * @since Available in iOS 6.0 and later.
     */
    public UIPageViewControllerOptions setInterPageSpacing(double interPageSpacing) {
        set(Keys.InterPageSpacing(), NSNumber.valueOf(interPageSpacing));
        return this;
    }
    /*</methods>*/
    
    /*<keys>*/
    @Library("UIKit")
    public static class Keys {
        static { Bro.bind(Keys.class); }
        @GlobalValue(symbol="UIPageViewControllerOptionSpineLocationKey", optional=true)
        public static native NSString SpineLocation();
        /**
         * @since Available in iOS 6.0 and later.
         */
        @GlobalValue(symbol="UIPageViewControllerOptionInterPageSpacingKey", optional=true)
        public static native NSString InterPageSpacing();
    }
    /*</keys>*/
}
