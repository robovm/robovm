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
/*<visibility>*/public/*</visibility>*/ class /*<name>*/UIAccessibilityNotification/*</name>*/ 
    extends /*<extends>*/CocoaUtility/*</extends>*/ 
    /*<implements>*//*</implements>*/ {

    /*<ptr>*/
    /*</ptr>*/
    /*<bind>*/static { Bro.bind(UIAccessibilityNotification.class); }/*</bind>*/
    /*<constants>*//*</constants>*/
    public static final UIAccessibilityNotification ScreenChanged = new UIAccessibilityNotification("ScreenChangedNotification");
    public static final UIAccessibilityNotification LayoutChanged = new UIAccessibilityNotification("LayoutChangedNotification");
    /**
     * @since Available in iOS 4.0 and later.
     */
    public static final UIAccessibilityNotification Announcement = new UIAccessibilityNotification("AnnouncementNotification");
    /**
     * @since Available in iOS 4.2 and later.
     */
    public static final UIAccessibilityNotification PageScrolled = new UIAccessibilityNotification("PageScrolledNotification");
    /**
     * @since Available in iOS 8.0 and later.
     */
    public static final UIAccessibilityNotification PauseAssistiveTechnology = new UIAccessibilityNotification("PauseAssistiveTechnologyNotification");
    /**
     * @since Available in iOS 8.0 and later.
     */
    public static final UIAccessibilityNotification ResumeAssistiveTechnology = new UIAccessibilityNotification("ResumeAssistiveTechnologyNotification");
    
    private static UIAccessibilityNotification[] values = new UIAccessibilityNotification[] {ScreenChanged, LayoutChanged, Announcement, PageScrolled, 
        PauseAssistiveTechnology, ResumeAssistiveTechnology};
    private final LazyGlobalValue<Integer> lazyGlobalValue;
    
    private UIAccessibilityNotification(String getterName) {
        lazyGlobalValue = new LazyGlobalValue<>(getClass(), getterName);
    }
    /*<constructors>*//*</constructors>*/
    /*<properties>*//*</properties>*/
    /*<members>*//*</members>*/
    public Integer value() {
        return lazyGlobalValue.value();
    }
    
    public static UIAccessibilityNotification valueOf(Integer value) {
        for (UIAccessibilityNotification v : values) {
            if (v.value() == value) {
                return v;
            }
        }
        throw new IllegalArgumentException("No constant with value " + value + " found in " 
            + /*<name>*/UIAccessibilityNotification/*</name>*/.class.getName());
    }
    /*<methods>*/
    @GlobalValue(symbol="UIAccessibilityScreenChangedNotification", optional=true)
    protected static native int ScreenChangedNotification();
    @GlobalValue(symbol="UIAccessibilityLayoutChangedNotification", optional=true)
    protected static native int LayoutChangedNotification();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="UIAccessibilityAnnouncementNotification", optional=true)
    protected static native int AnnouncementNotification();
    /**
     * @since Available in iOS 4.2 and later.
     */
    @GlobalValue(symbol="UIAccessibilityPageScrolledNotification", optional=true)
    protected static native int PageScrolledNotification();
    /**
     * @since Available in iOS 8.0 and later.
     */
    @GlobalValue(symbol="UIAccessibilityPauseAssistiveTechnologyNotification", optional=true)
    protected static native int PauseAssistiveTechnologyNotification();
    /**
     * @since Available in iOS 8.0 and later.
     */
    @GlobalValue(symbol="UIAccessibilityResumeAssistiveTechnologyNotification", optional=true)
    protected static native int ResumeAssistiveTechnologyNotification();
    /*</methods>*/
}
