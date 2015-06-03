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
/*<annotations>*//*</annotations>*/
/*<visibility>*/public/*</visibility>*/ interface /*<name>*/UIAccessibility/*</name>*/ 
    /*<implements>*/extends NSObjectProtocol/*</implements>*/ {

    public static class Notifications {
        /**
         * @since Available in iOS 6.0 and later.
         */
        public static NSObject observeAnnouncementDidFinish(final VoidBlock2<String, Boolean> block) {
            return NSNotificationCenter.getDefaultCenter().addObserver(UIAccessibilityGlobals.AnnouncementDidFinishNotification(), null, NSOperationQueue.getMainQueue(), new VoidBlock1<NSNotification>() {
                @Override
                public void invoke(NSNotification a) {
                    NSDictionary<NSString, NSObject> data = a.getUserInfo();
                    NSString string = (NSString)data.get(UIAccessibilityGlobals.AnnouncementKeyStringValue());
                    NSNumber successful = (NSNumber)data.get(UIAccessibilityGlobals.AnnouncementKeyWasSuccessful());
                    block.invoke(string.toString(), successful.booleanValue());
                }
            });
        }
        /**
         * @since Available in iOS 5.0 and later.
         */
        public static NSObject observeMonoAudioStatusDidChange(final Runnable block) {
            return NSNotificationCenter.getDefaultCenter().addObserver(UIAccessibilityGlobals.MonoAudioStatusDidChangeNotification(), null, NSOperationQueue.getMainQueue(), new VoidBlock1<NSNotification>() {
                @Override
                public void invoke(NSNotification a) {
                    block.run();
                }
            });
        }
        /**
         * @since Available in iOS 5.0 and later.
         */
        public static NSObject observeClosedCaptioningStatusDidChange(final Runnable block) {
            return NSNotificationCenter.getDefaultCenter().addObserver(UIAccessibilityGlobals.ClosedCaptioningStatusDidChangeNotification(), null, NSOperationQueue.getMainQueue(), new VoidBlock1<NSNotification>() {
                @Override
                public void invoke(NSNotification a) {
                    block.run();
                }
            });
        }
        /**
         * @since Available in iOS 6.0 and later.
         */
        public static NSObject observeInvertColorsStatusDidChange(final Runnable block) {
            return NSNotificationCenter.getDefaultCenter().addObserver(UIAccessibilityGlobals.InvertColorsStatusDidChangeNotification(), null, NSOperationQueue.getMainQueue(), new VoidBlock1<NSNotification>() {
                @Override
                public void invoke(NSNotification a) {
                    block.run();
                }
            });
        }
        /**
         * @since Available in iOS 6.0 and later.
         */
        public static NSObject observeGuidedAccessStatusDidChange(final Runnable block) {
            return NSNotificationCenter.getDefaultCenter().addObserver(UIAccessibilityGlobals.GuidedAccessStatusDidChangeNotification(), null, NSOperationQueue.getMainQueue(), new VoidBlock1<NSNotification>() {
                @Override
                public void invoke(NSNotification a) {
                    block.run();
                }
            });
        }
        /**
         * @since Available in iOS 4.0 and later.
         */
        public static NSObject observeVoiceOverStatusChanged(final Runnable block) {
            return NSNotificationCenter.getDefaultCenter().addObserver(UIAccessibilityGlobals.VoiceOverStatusChangedNotification(), null, NSOperationQueue.getMainQueue(), new VoidBlock1<NSNotification>() {
                @Override
                public void invoke(NSNotification a) {
                    block.run();
                }
            });
        }
        /**
         * @since Available in iOS 8.0 and later.
         */
        public static NSObject observeBoldTextStatusDidChange(final Runnable block) {
            return NSNotificationCenter.getDefaultCenter().addObserver(UIAccessibilityGlobals.BoldTextStatusDidChangeNotification(), null, NSOperationQueue.getMainQueue(), new VoidBlock1<NSNotification>() {
                @Override
                public void invoke(NSNotification a) {
                    block.run();
                }
            });
        }
        /**
         * @since Available in iOS 8.0 and later.
         */
        public static NSObject observeGrayscaleStatusDidChange(final Runnable block) {
            return NSNotificationCenter.getDefaultCenter().addObserver(UIAccessibilityGlobals.GrayscaleStatusDidChangeNotification(), null, NSOperationQueue.getMainQueue(), new VoidBlock1<NSNotification>() {
                @Override
                public void invoke(NSNotification a) {
                    block.run();
                }
            });
        }
        /**
         * @since Available in iOS 8.0 and later.
         */
        public static NSObject observeReduceTransparencyStatusDidChange(final Runnable block) {
            return NSNotificationCenter.getDefaultCenter().addObserver(UIAccessibilityGlobals.ReduceTransparencyStatusDidChangeNotification(), null, NSOperationQueue.getMainQueue(), new VoidBlock1<NSNotification>() {
                @Override
                public void invoke(NSNotification a) {
                    block.run();
                }
            });
        }
        /**
         * @since Available in iOS 8.0 and later.
         */
        public static NSObject observeReduceMotionStatusDidChange(final Runnable block) {
            return NSNotificationCenter.getDefaultCenter().addObserver(UIAccessibilityGlobals.ReduceMotionStatusDidChangeNotification(), null, NSOperationQueue.getMainQueue(), new VoidBlock1<NSNotification>() {
                @Override
                public void invoke(NSNotification a) {
                    block.run();
                }
            });
        }
        /**
         * @since Available in iOS 8.0 and later.
         */
        public static NSObject observeDarkerSystemColorsStatusDidChange(final Runnable block) {
            return NSNotificationCenter.getDefaultCenter().addObserver(UIAccessibilityGlobals.DarkerSystemColorsStatusDidChangeNotification(), null, NSOperationQueue.getMainQueue(), new VoidBlock1<NSNotification>() {
                @Override
                public void invoke(NSNotification a) {
                    block.run();
                }
            });
        }
        /**
         * @since Available in iOS 8.0 and later.
         */
        public static NSObject observeSwitchControlStatusDidChange(final Runnable block) {
            return NSNotificationCenter.getDefaultCenter().addObserver(UIAccessibilityGlobals.SwitchControlStatusDidChangeNotification(), null, NSOperationQueue.getMainQueue(), new VoidBlock1<NSNotification>() {
                @Override
                public void invoke(NSNotification a) {
                    block.run();
                }
            });
        }
        /**
         * @since Available in iOS 8.0 and later.
         */
        public static NSObject observeSpeakSelectionStatusDidChange(final Runnable block) {
            return NSNotificationCenter.getDefaultCenter().addObserver(UIAccessibilityGlobals.SpeakSelectionStatusDidChangeNotification(), null, NSOperationQueue.getMainQueue(), new VoidBlock1<NSNotification>() {
                @Override
                public void invoke(NSNotification a) {
                    block.run();
                }
            });
        }
        /**
         * @since Available in iOS 8.0 and later.
         */
        public static NSObject observeSpeakScreenStatusDidChange(final Runnable block) {
            return NSNotificationCenter.getDefaultCenter().addObserver(UIAccessibilityGlobals.SpeakScreenStatusDidChangeNotification(), null, NSOperationQueue.getMainQueue(), new VoidBlock1<NSNotification>() {
                @Override
                public void invoke(NSNotification a) {
                    block.run();
                }
            });
        }
    }
    /*<ptr>*/
    /*</ptr>*/
    /*<bind>/*</bind>*/
    /*<constants>*//*</constants>*/
    /*<properties>*/
    @Property(selector = "isAccessibilityElement")
    boolean isAccessibilityElement();
    @Property(selector = "setIsAccessibilityElement:")
    void setAccessibilityElement(boolean v);
    @Property(selector = "accessibilityLabel")
    String getAccessibilityLabel();
    @Property(selector = "setAccessibilityLabel:")
    void setAccessibilityLabel(String v);
    @Property(selector = "accessibilityHint")
    String getAccessibilityHint();
    @Property(selector = "setAccessibilityHint:")
    void setAccessibilityHint(String v);
    @Property(selector = "accessibilityValue")
    String getAccessibilityValue();
    @Property(selector = "setAccessibilityValue:")
    void setAccessibilityValue(String v);
    @Property(selector = "accessibilityTraits")
    UIAccessibilityTraits getAccessibilityTraits();
    @Property(selector = "setAccessibilityTraits:")
    void setAccessibilityTraits(UIAccessibilityTraits v);
    @Property(selector = "accessibilityFrame")
    @ByVal CGRect getAccessibilityFrame();
    @Property(selector = "setAccessibilityFrame:")
    void setAccessibilityFrame(@ByVal CGRect v);
    /**
     * @since Available in iOS 7.0 and later.
     */
    @Property(selector = "accessibilityPath")
    UIBezierPath getAccessibilityPath();
    /**
     * @since Available in iOS 7.0 and later.
     */
    @Property(selector = "setAccessibilityPath:")
    void setAccessibilityPath(UIBezierPath v);
    /**
     * @since Available in iOS 5.0 and later.
     */
    @Property(selector = "accessibilityActivationPoint")
    @ByVal CGPoint getAccessibilityActivationPoint();
    /**
     * @since Available in iOS 5.0 and later.
     */
    @Property(selector = "setAccessibilityActivationPoint:")
    void setAccessibilityActivationPoint(@ByVal CGPoint v);
    @Property(selector = "accessibilityLanguage")
    String getAccessibilityLanguage();
    @Property(selector = "setAccessibilityLanguage:")
    void setAccessibilityLanguage(String v);
    /**
     * @since Available in iOS 5.0 and later.
     */
    @Property(selector = "accessibilityElementsHidden")
    boolean areAccessibilityElementsHidden();
    /**
     * @since Available in iOS 5.0 and later.
     */
    @Property(selector = "setAccessibilityElementsHidden:")
    void setAccessibilityElementsHidden(boolean v);
    /**
     * @since Available in iOS 5.0 and later.
     */
    @Property(selector = "accessibilityViewIsModal")
    boolean isAccessibilityViewModal();
    /**
     * @since Available in iOS 5.0 and later.
     */
    @Property(selector = "setAccessibilityViewIsModal:")
    void setAccessibilityViewModal(boolean v);
    /**
     * @since Available in iOS 6.0 and later.
     */
    @Property(selector = "shouldGroupAccessibilityChildren")
    boolean shouldGroupAccessibilityChildren();
    /**
     * @since Available in iOS 6.0 and later.
     */
    @Property(selector = "setShouldGroupAccessibilityChildren:")
    void setShouldGroupAccessibilityChildren(boolean v);
    /**
     * @since Available in iOS 8.0 and later.
     */
    @Property(selector = "accessibilityNavigationStyle")
    UIAccessibilityNavigationStyle getAccessibilityNavigationStyle();
    /**
     * @since Available in iOS 8.0 and later.
     */
    @Property(selector = "setAccessibilityNavigationStyle:")
    void setAccessibilityNavigationStyle(UIAccessibilityNavigationStyle v);
    /*</properties>*/
    /*<methods>*/
    
    /*</methods>*/
    /*<adapter>*/
    /*</adapter>*/
}
