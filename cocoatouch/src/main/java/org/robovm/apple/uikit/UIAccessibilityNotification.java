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
/*<annotations>*/@Library("UIKit") @StronglyLinked/*</annotations>*/
@Marshaler(/*<name>*/UIAccessibilityNotification/*</name>*/.Marshaler.class)
/*<visibility>*/public/*</visibility>*/ class /*<name>*/UIAccessibilityNotification/*</name>*/ 
    extends /*<extends>*/GlobalValueEnumeration<Integer>/*</extends>*/
    /*<implements>*//*</implements>*/ {

    static { Bro.bind(/*<name>*/UIAccessibilityNotification/*</name>*/.class); }

    /*<marshalers>*/
    public static class Marshaler {
        @MarshalsPointer
        public static UIAccessibilityNotification toObject(Class<UIAccessibilityNotification> cls, long handle, long flags) {
            NSNumber o = (NSNumber) NSObject.Marshaler.toObject(NSNumber.class, handle, flags);
            if (o == null) {
                return null;
            }
            return UIAccessibilityNotification.valueOf(o.intValue());
        }
        @MarshalsPointer
        public static long toNative(UIAccessibilityNotification o, long flags) {
            if (o == null) {
                return 0L;
            }
            return NSObject.Marshaler.toNative(NSNumber.valueOf(o.value()), flags);
        }
    }
    public static class AsListMarshaler {
        @SuppressWarnings("unchecked")
        @MarshalsPointer
        public static List<UIAccessibilityNotification> toObject(Class<? extends NSObject> cls, long handle, long flags) {
            NSArray<NSNumber> o = (NSArray<NSNumber>) NSObject.Marshaler.toObject(NSArray.class, handle, flags);
            if (o == null) {
                return null;
            }
            List<UIAccessibilityNotification> list = new ArrayList<>();
            for (int i = 0; i < o.size(); i++) {
                list.add(UIAccessibilityNotification.valueOf(o.get(i).intValue()));
            }
            return list;
        }
        @MarshalsPointer
        public static long toNative(List<UIAccessibilityNotification> l, long flags) {
            if (l == null) {
                return 0L;
            }
            NSArray<NSNumber> array = new NSMutableArray<>();
            for (UIAccessibilityNotification o : l) {
                array.add(NSNumber.valueOf(o.value()));
            }
            return NSObject.Marshaler.toNative(array, flags);
        }
    }
    /*</marshalers>*/

    /*<constants>*/
    public static final UIAccessibilityNotification ScreenChangedNotification = new UIAccessibilityNotification("ScreenChangedNotification");
    public static final UIAccessibilityNotification LayoutChangedNotification = new UIAccessibilityNotification("LayoutChangedNotification");
    /**
     * @since Available in iOS 4.0 and later.
     */
    public static final UIAccessibilityNotification AnnouncementNotification = new UIAccessibilityNotification("AnnouncementNotification");
    /**
     * @since Available in iOS 4.2 and later.
     */
    public static final UIAccessibilityNotification PageScrolledNotification = new UIAccessibilityNotification("PageScrolledNotification");
    /**
     * @since Available in iOS 8.0 and later.
     */
    public static final UIAccessibilityNotification PauseAssistiveTechnologyNotification = new UIAccessibilityNotification("PauseAssistiveTechnologyNotification");
    /**
     * @since Available in iOS 8.0 and later.
     */
    public static final UIAccessibilityNotification ResumeAssistiveTechnologyNotification = new UIAccessibilityNotification("ResumeAssistiveTechnologyNotification");
    /*</constants>*/
    
    private static /*<name>*/UIAccessibilityNotification/*</name>*/[] values = new /*<name>*/UIAccessibilityNotification/*</name>*/[] {/*<value_list>*/ScreenChangedNotification, LayoutChangedNotification, AnnouncementNotification, PageScrolledNotification, PauseAssistiveTechnologyNotification, ResumeAssistiveTechnologyNotification/*</value_list>*/};
    
    /*<name>*/UIAccessibilityNotification/*</name>*/ (String getterName) {
        super(Values.class, getterName);
    }
    
    public static /*<name>*/UIAccessibilityNotification/*</name>*/ valueOf(/*<type>*/int/*</type>*/ value) {
        for (/*<name>*/UIAccessibilityNotification/*</name>*/ v : values) {
            if (v.value().equals(value)) {
                return v;
            }
        }
        throw new IllegalArgumentException("No constant with value " + value + " found in " 
            + /*<name>*/UIAccessibilityNotification/*</name>*/.class.getName());
    }
    
    /*<methods>*//*</methods>*/
    
    /*<annotations>*/@Library("UIKit") @StronglyLinked/*</annotations>*/
    public static class Values {
    	static { Bro.bind(Values.class); }

        /*<values>*/
        @GlobalValue(symbol="UIAccessibilityScreenChangedNotification", optional=true)
        public static native int ScreenChangedNotification();
        @GlobalValue(symbol="UIAccessibilityLayoutChangedNotification", optional=true)
        public static native int LayoutChangedNotification();
        /**
         * @since Available in iOS 4.0 and later.
         */
        @GlobalValue(symbol="UIAccessibilityAnnouncementNotification", optional=true)
        public static native int AnnouncementNotification();
        /**
         * @since Available in iOS 4.2 and later.
         */
        @GlobalValue(symbol="UIAccessibilityPageScrolledNotification", optional=true)
        public static native int PageScrolledNotification();
        /**
         * @since Available in iOS 8.0 and later.
         */
        @GlobalValue(symbol="UIAccessibilityPauseAssistiveTechnologyNotification", optional=true)
        public static native int PauseAssistiveTechnologyNotification();
        /**
         * @since Available in iOS 8.0 and later.
         */
        @GlobalValue(symbol="UIAccessibilityResumeAssistiveTechnologyNotification", optional=true)
        public static native int ResumeAssistiveTechnologyNotification();
        /*</values>*/
    }
}
