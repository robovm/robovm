/*
 * Copyright (C) 2012 Trillian AB
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
package org.robovm.cocoatouch.uikit;

/*<imports>*/
import org.robovm.cocoatouch.coreanimation.*;
import org.robovm.cocoatouch.coredata.*;
import org.robovm.cocoatouch.coregraphics.*;
import org.robovm.cocoatouch.coreimage.*;
import org.robovm.cocoatouch.foundation.*;
import java.util.*;
import org.robovm.objc.*;
import org.robovm.objc.annotation.*;
import org.robovm.objc.block.*;
import org.robovm.rt.bro.*;
import org.robovm.rt.bro.annotation.*;
import org.robovm.rt.bro.ptr.*;
/*</imports>*/

/**
 *
 *
 * <div class="javadoc">
 *   @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIApplication_Class/Reference/Reference.html">UIApplication Class Reference</a>
 *   @since Available in iOS 2.0 and later.
 * </div>
 */
/*<library>*/@Library("UIKit")/*</library>*/
@NativeClass public class /*<name>*/ UIApplication /*</name>*/ 
    extends /*<extends>*/ UIResponder /*</extends>*/ 
    /*<implements>*/ /*</implements>*/ {

    static {
        ObjCRuntime.bind(/*<name>*/ UIApplication /*</name>*/.class);
    }

    private static final ObjCClass objCClass = ObjCClass.getByType(/*<name>*/ UIApplication /*</name>*/.class);

    @Bridge private native static int UIApplicationMain(int argc, Ptr<BytePtr> argv, 
            String principalClassName, String delegateClassName);
    
    public static <P extends UIApplication, D extends NSObject & UIApplicationDelegate> 
        void main(String[] args, Class<P> principalClass, Class<D> delegateClass) {
        
        int argc = args.length;
        Ptr<BytePtr> argv = null;
        if (argc > 0) {
            argv = Ptr.newPtr(BytePtr.class, argc);
            for (int i = 0; i < argc; i++) {
                // TODO: Encoding?
                BytePtr arg = BytePtr.toBytePtrAsciiZ(args[i]);
                argv.next(i).set(arg);
            }
        }
        String principalClassName = null;
        if (principalClass != null) {
            principalClassName = ObjCClass.getByType(principalClass).getName();
        }
        String delegateClassName = null;
        if (delegateClass != null) {
            delegateClassName = ObjCClass.getByType(delegateClass).getName();            
        }
        UIApplicationMain(argc, argv, principalClassName, delegateClassName);
    }
    
    /*<constructors>*/
    protected UIApplication(SkipInit skipInit) { super(skipInit); }
    public UIApplication() {}
    
    /*</constructors>*/
    /*<properties>*/
    
    private static final Selector applicationIconBadgeNumber = Selector.register("applicationIconBadgeNumber");
    @Bridge private native static int objc_getApplicationIconBadgeNumber(UIApplication __self__, Selector __cmd__);
    @Bridge private native static int objc_getApplicationIconBadgeNumberSuper(ObjCSuper __super__, Selector __cmd__);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIApplication_Class/Reference/Reference.html#//apple_ref/occ/instp/UIApplication/applicationIconBadgeNumber">@property(nonatomic) NSInteger applicationIconBadgeNumber</a>
     * @since Available in iOS 2.0 and later.
     */
    public int getApplicationIconBadgeNumber() {
        if (customClass) { return objc_getApplicationIconBadgeNumberSuper(getSuper(), applicationIconBadgeNumber); } else { return objc_getApplicationIconBadgeNumber(this, applicationIconBadgeNumber); }
    }
    
    private static final Selector setApplicationIconBadgeNumber$ = Selector.register("setApplicationIconBadgeNumber:");
    @Bridge private native static void objc_setApplicationIconBadgeNumber(UIApplication __self__, Selector __cmd__, int applicationIconBadgeNumber);
    @Bridge private native static void objc_setApplicationIconBadgeNumberSuper(ObjCSuper __super__, Selector __cmd__, int applicationIconBadgeNumber);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIApplication_Class/Reference/Reference.html#//apple_ref/occ/instp/UIApplication/applicationIconBadgeNumber">@property(nonatomic) NSInteger applicationIconBadgeNumber</a>
     * @since Available in iOS 2.0 and later.
     */
    public void setApplicationIconBadgeNumber(int applicationIconBadgeNumber) {
        if (customClass) { objc_setApplicationIconBadgeNumberSuper(getSuper(), setApplicationIconBadgeNumber$, applicationIconBadgeNumber); } else { objc_setApplicationIconBadgeNumber(this, setApplicationIconBadgeNumber$, applicationIconBadgeNumber); }
    }
    
    private static final Selector applicationState = Selector.register("applicationState");
    @Bridge private native static UIApplicationState objc_getApplicationState(UIApplication __self__, Selector __cmd__);
    @Bridge private native static UIApplicationState objc_getApplicationStateSuper(ObjCSuper __super__, Selector __cmd__);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIApplication_Class/Reference/Reference.html#//apple_ref/occ/instp/UIApplication/applicationState">@property(nonatomic, readonly) UIApplicationState applicationState</a>
     * @since Available in iOS 4.0 and later.
     */
    public UIApplicationState getApplicationState() {
        if (customClass) { return objc_getApplicationStateSuper(getSuper(), applicationState); } else { return objc_getApplicationState(this, applicationState); }
    }
    
    private static final Selector applicationSupportsShakeToEdit = Selector.register("applicationSupportsShakeToEdit");
    @Bridge private native static boolean objc_isApplicationSupportsShakeToEdit(UIApplication __self__, Selector __cmd__);
    @Bridge private native static boolean objc_isApplicationSupportsShakeToEditSuper(ObjCSuper __super__, Selector __cmd__);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIApplication_Class/Reference/Reference.html#//apple_ref/occ/instp/UIApplication/applicationSupportsShakeToEdit">@property(nonatomic) BOOL applicationSupportsShakeToEdit</a>
     * @since Available in iOS 3.0 and later.
     */
    public boolean isApplicationSupportsShakeToEdit() {
        if (customClass) { return objc_isApplicationSupportsShakeToEditSuper(getSuper(), applicationSupportsShakeToEdit); } else { return objc_isApplicationSupportsShakeToEdit(this, applicationSupportsShakeToEdit); }
    }
    
    private static final Selector setApplicationSupportsShakeToEdit$ = Selector.register("setApplicationSupportsShakeToEdit:");
    @Bridge private native static void objc_setApplicationSupportsShakeToEdit(UIApplication __self__, Selector __cmd__, boolean applicationSupportsShakeToEdit);
    @Bridge private native static void objc_setApplicationSupportsShakeToEditSuper(ObjCSuper __super__, Selector __cmd__, boolean applicationSupportsShakeToEdit);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIApplication_Class/Reference/Reference.html#//apple_ref/occ/instp/UIApplication/applicationSupportsShakeToEdit">@property(nonatomic) BOOL applicationSupportsShakeToEdit</a>
     * @since Available in iOS 3.0 and later.
     */
    public void setApplicationSupportsShakeToEdit(boolean applicationSupportsShakeToEdit) {
        if (customClass) { objc_setApplicationSupportsShakeToEditSuper(getSuper(), setApplicationSupportsShakeToEdit$, applicationSupportsShakeToEdit); } else { objc_setApplicationSupportsShakeToEdit(this, setApplicationSupportsShakeToEdit$, applicationSupportsShakeToEdit); }
    }
    
    private static final Selector backgroundTimeRemaining = Selector.register("backgroundTimeRemaining");
    @Bridge private native static double objc_getBackgroundTimeRemaining(UIApplication __self__, Selector __cmd__);
    @Bridge private native static double objc_getBackgroundTimeRemainingSuper(ObjCSuper __super__, Selector __cmd__);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIApplication_Class/Reference/Reference.html#//apple_ref/occ/instp/UIApplication/backgroundTimeRemaining">@property(nonatomic, readonly) NSTimeInterval backgroundTimeRemaining</a>
     * @since Available in iOS 4.0 and later.
     */
    public double getBackgroundTimeRemaining() {
        if (customClass) { return objc_getBackgroundTimeRemainingSuper(getSuper(), backgroundTimeRemaining); } else { return objc_getBackgroundTimeRemaining(this, backgroundTimeRemaining); }
    }
    
    private static final Selector delegate = Selector.register("delegate");
    @Bridge private native static UIApplicationDelegate objc_getDelegate(UIApplication __self__, Selector __cmd__);
    @Bridge private native static UIApplicationDelegate objc_getDelegateSuper(ObjCSuper __super__, Selector __cmd__);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIApplication_Class/Reference/Reference.html#//apple_ref/occ/instp/UIApplication/delegate">@property(nonatomic, assign) id&amp;lt;UIApplicationDelegate&amp;gt; delegate</a>
     * @since Available in iOS 2.0 and later.
     */
    public UIApplicationDelegate getDelegate() {
        if (customClass) { return objc_getDelegateSuper(getSuper(), delegate); } else { return objc_getDelegate(this, delegate); }
    }
    
    private static final Selector setDelegate$ = Selector.register("setDelegate:");
    @Bridge private native static void objc_setDelegate(UIApplication __self__, Selector __cmd__, UIApplicationDelegate delegate);
    @Bridge private native static void objc_setDelegateSuper(ObjCSuper __super__, Selector __cmd__, UIApplicationDelegate delegate);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIApplication_Class/Reference/Reference.html#//apple_ref/occ/instp/UIApplication/delegate">@property(nonatomic, assign) id&amp;lt;UIApplicationDelegate&amp;gt; delegate</a>
     * @since Available in iOS 2.0 and later.
     */
    public void setDelegate(UIApplicationDelegate delegate) {
        if (customClass) { objc_setDelegateSuper(getSuper(), setDelegate$, delegate); } else { objc_setDelegate(this, setDelegate$, delegate); }
    }
    
    private static final Selector isIdleTimerDisabled = Selector.register("isIdleTimerDisabled");
    @Bridge private native static boolean objc_isIdleTimerDisabled(UIApplication __self__, Selector __cmd__);
    @Bridge private native static boolean objc_isIdleTimerDisabledSuper(ObjCSuper __super__, Selector __cmd__);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIApplication_Class/Reference/Reference.html#//apple_ref/occ/instp/UIApplication/idleTimerDisabled">@property(nonatomic, getter=isIdleTimerDisabled) BOOL idleTimerDisabled</a>
     * @since Available in iOS 2.0 and later.
     */
    public boolean isIdleTimerDisabled() {
        if (customClass) { return objc_isIdleTimerDisabledSuper(getSuper(), isIdleTimerDisabled); } else { return objc_isIdleTimerDisabled(this, isIdleTimerDisabled); }
    }
    
    private static final Selector setIdleTimerDisabled$ = Selector.register("setIdleTimerDisabled:");
    @Bridge private native static void objc_setIdleTimerDisabled(UIApplication __self__, Selector __cmd__, boolean idleTimerDisabled);
    @Bridge private native static void objc_setIdleTimerDisabledSuper(ObjCSuper __super__, Selector __cmd__, boolean idleTimerDisabled);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIApplication_Class/Reference/Reference.html#//apple_ref/occ/instp/UIApplication/idleTimerDisabled">@property(nonatomic, getter=isIdleTimerDisabled) BOOL idleTimerDisabled</a>
     * @since Available in iOS 2.0 and later.
     */
    public void setIdleTimerDisabled(boolean idleTimerDisabled) {
        if (customClass) { objc_setIdleTimerDisabledSuper(getSuper(), setIdleTimerDisabled$, idleTimerDisabled); } else { objc_setIdleTimerDisabled(this, setIdleTimerDisabled$, idleTimerDisabled); }
    }
    
    private static final Selector keyWindow = Selector.register("keyWindow");
    @Bridge private native static UIWindow objc_getKeyWindow(UIApplication __self__, Selector __cmd__);
    @Bridge private native static UIWindow objc_getKeyWindowSuper(ObjCSuper __super__, Selector __cmd__);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIApplication_Class/Reference/Reference.html#//apple_ref/occ/instp/UIApplication/keyWindow">@property(nonatomic, readonly) UIWindow *keyWindow</a>
     * @since Available in iOS 2.0 and later.
     */
    public UIWindow getKeyWindow() {
        if (customClass) { return objc_getKeyWindowSuper(getSuper(), keyWindow); } else { return objc_getKeyWindow(this, keyWindow); }
    }
    
    private static final Selector isNetworkActivityIndicatorVisible = Selector.register("isNetworkActivityIndicatorVisible");
    @Bridge private native static boolean objc_isNetworkActivityIndicatorVisible(UIApplication __self__, Selector __cmd__);
    @Bridge private native static boolean objc_isNetworkActivityIndicatorVisibleSuper(ObjCSuper __super__, Selector __cmd__);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIApplication_Class/Reference/Reference.html#//apple_ref/occ/instp/UIApplication/networkActivityIndicatorVisible">@property(nonatomic, getter=isNetworkActivityIndicatorVisible) BOOL networkActivityIndicatorVisible</a>
     * @since Available in iOS 2.0 and later.
     */
    public boolean isNetworkActivityIndicatorVisible() {
        if (customClass) { return objc_isNetworkActivityIndicatorVisibleSuper(getSuper(), isNetworkActivityIndicatorVisible); } else { return objc_isNetworkActivityIndicatorVisible(this, isNetworkActivityIndicatorVisible); }
    }
    
    private static final Selector setNetworkActivityIndicatorVisible$ = Selector.register("setNetworkActivityIndicatorVisible:");
    @Bridge private native static void objc_setNetworkActivityIndicatorVisible(UIApplication __self__, Selector __cmd__, boolean networkActivityIndicatorVisible);
    @Bridge private native static void objc_setNetworkActivityIndicatorVisibleSuper(ObjCSuper __super__, Selector __cmd__, boolean networkActivityIndicatorVisible);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIApplication_Class/Reference/Reference.html#//apple_ref/occ/instp/UIApplication/networkActivityIndicatorVisible">@property(nonatomic, getter=isNetworkActivityIndicatorVisible) BOOL networkActivityIndicatorVisible</a>
     * @since Available in iOS 2.0 and later.
     */
    public void setNetworkActivityIndicatorVisible(boolean networkActivityIndicatorVisible) {
        if (customClass) { objc_setNetworkActivityIndicatorVisibleSuper(getSuper(), setNetworkActivityIndicatorVisible$, networkActivityIndicatorVisible); } else { objc_setNetworkActivityIndicatorVisible(this, setNetworkActivityIndicatorVisible$, networkActivityIndicatorVisible); }
    }
    
    private static final Selector isProtectedDataAvailable = Selector.register("isProtectedDataAvailable");
    @Bridge private native static boolean objc_isProtectedDataAvailable(UIApplication __self__, Selector __cmd__);
    @Bridge private native static boolean objc_isProtectedDataAvailableSuper(ObjCSuper __super__, Selector __cmd__);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIApplication_Class/Reference/Reference.html#//apple_ref/occ/instp/UIApplication/protectedDataAvailable">@property(nonatomic, readonly, getter=isProtectedDataAvailable) BOOL protectedDataAvailable</a>
     * @since Available in iOS 4.0 and later.
     */
    public boolean isProtectedDataAvailable() {
        if (customClass) { return objc_isProtectedDataAvailableSuper(getSuper(), isProtectedDataAvailable); } else { return objc_isProtectedDataAvailable(this, isProtectedDataAvailable); }
    }
    
    private static final Selector scheduledLocalNotifications = Selector.register("scheduledLocalNotifications");
    @Bridge private native static NSArray objc_getScheduledLocalNotifications(UIApplication __self__, Selector __cmd__);
    @Bridge private native static NSArray objc_getScheduledLocalNotificationsSuper(ObjCSuper __super__, Selector __cmd__);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIApplication_Class/Reference/Reference.html#//apple_ref/occ/instp/UIApplication/scheduledLocalNotifications">@property(nonatomic, copy) NSArray *scheduledLocalNotifications</a>
     * @since Available in iOS 4.0 and later.
     */
    public NSArray getScheduledLocalNotifications() {
        if (customClass) { return objc_getScheduledLocalNotificationsSuper(getSuper(), scheduledLocalNotifications); } else { return objc_getScheduledLocalNotifications(this, scheduledLocalNotifications); }
    }
    
    private static final Selector setScheduledLocalNotifications$ = Selector.register("setScheduledLocalNotifications:");
    @Bridge private native static void objc_setScheduledLocalNotifications(UIApplication __self__, Selector __cmd__, NSArray scheduledLocalNotifications);
    @Bridge private native static void objc_setScheduledLocalNotificationsSuper(ObjCSuper __super__, Selector __cmd__, NSArray scheduledLocalNotifications);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIApplication_Class/Reference/Reference.html#//apple_ref/occ/instp/UIApplication/scheduledLocalNotifications">@property(nonatomic, copy) NSArray *scheduledLocalNotifications</a>
     * @since Available in iOS 4.0 and later.
     */
    public void setScheduledLocalNotifications(NSArray scheduledLocalNotifications) {
        if (customClass) { objc_setScheduledLocalNotificationsSuper(getSuper(), setScheduledLocalNotifications$, scheduledLocalNotifications); } else { objc_setScheduledLocalNotifications(this, setScheduledLocalNotifications$, scheduledLocalNotifications); }
    }
    
    private static final Selector statusBarFrame = Selector.register("statusBarFrame");
    @Bridge private native static @ByVal CGRect objc_getStatusBarFrame(UIApplication __self__, Selector __cmd__);
    @Bridge private native static @ByVal CGRect objc_getStatusBarFrameSuper(ObjCSuper __super__, Selector __cmd__);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIApplication_Class/Reference/Reference.html#//apple_ref/occ/instp/UIApplication/statusBarFrame">@property(nonatomic, readonly) CGRect statusBarFrame</a>
     * @since Available in iOS 2.0 and later.
     */
    public CGRect getStatusBarFrame() {
        if (customClass) { return objc_getStatusBarFrameSuper(getSuper(), statusBarFrame); } else { return objc_getStatusBarFrame(this, statusBarFrame); }
    }
    
    private static final Selector isStatusBarHidden = Selector.register("isStatusBarHidden");
    @Bridge private native static boolean objc_isStatusBarHidden(UIApplication __self__, Selector __cmd__);
    @Bridge private native static boolean objc_isStatusBarHiddenSuper(ObjCSuper __super__, Selector __cmd__);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIApplication_Class/Reference/Reference.html#//apple_ref/occ/instp/UIApplication/statusBarHidden">@property(nonatomic, getter=isStatusBarHidden) BOOL statusBarHidden</a>
     * @since Available in iOS 2.0 and later.
     */
    public boolean isStatusBarHidden() {
        if (customClass) { return objc_isStatusBarHiddenSuper(getSuper(), isStatusBarHidden); } else { return objc_isStatusBarHidden(this, isStatusBarHidden); }
    }
    
    private static final Selector setStatusBarHidden$ = Selector.register("setStatusBarHidden:");
    @Bridge private native static void objc_setStatusBarHidden(UIApplication __self__, Selector __cmd__, boolean statusBarHidden);
    @Bridge private native static void objc_setStatusBarHiddenSuper(ObjCSuper __super__, Selector __cmd__, boolean statusBarHidden);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIApplication_Class/Reference/Reference.html#//apple_ref/occ/instp/UIApplication/statusBarHidden">@property(nonatomic, getter=isStatusBarHidden) BOOL statusBarHidden</a>
     * @since Available in iOS 2.0 and later.
     */
    public void setStatusBarHidden(boolean statusBarHidden) {
        if (customClass) { objc_setStatusBarHiddenSuper(getSuper(), setStatusBarHidden$, statusBarHidden); } else { objc_setStatusBarHidden(this, setStatusBarHidden$, statusBarHidden); }
    }
    
    private static final Selector statusBarOrientation = Selector.register("statusBarOrientation");
    @Bridge private native static UIInterfaceOrientation objc_getStatusBarOrientation(UIApplication __self__, Selector __cmd__);
    @Bridge private native static UIInterfaceOrientation objc_getStatusBarOrientationSuper(ObjCSuper __super__, Selector __cmd__);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIApplication_Class/Reference/Reference.html#//apple_ref/occ/instp/UIApplication/statusBarOrientation">@property(nonatomic) UIInterfaceOrientation statusBarOrientation</a>
     * @since Available in iOS 2.0 and later.
     */
    public UIInterfaceOrientation getStatusBarOrientation() {
        if (customClass) { return objc_getStatusBarOrientationSuper(getSuper(), statusBarOrientation); } else { return objc_getStatusBarOrientation(this, statusBarOrientation); }
    }
    
    private static final Selector setStatusBarOrientation$ = Selector.register("setStatusBarOrientation:");
    @Bridge private native static void objc_setStatusBarOrientation(UIApplication __self__, Selector __cmd__, UIInterfaceOrientation statusBarOrientation);
    @Bridge private native static void objc_setStatusBarOrientationSuper(ObjCSuper __super__, Selector __cmd__, UIInterfaceOrientation statusBarOrientation);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIApplication_Class/Reference/Reference.html#//apple_ref/occ/instp/UIApplication/statusBarOrientation">@property(nonatomic) UIInterfaceOrientation statusBarOrientation</a>
     * @since Available in iOS 2.0 and later.
     */
    public void setStatusBarOrientation(UIInterfaceOrientation statusBarOrientation) {
        if (customClass) { objc_setStatusBarOrientationSuper(getSuper(), setStatusBarOrientation$, statusBarOrientation); } else { objc_setStatusBarOrientation(this, setStatusBarOrientation$, statusBarOrientation); }
    }
    
    private static final Selector statusBarOrientationAnimationDuration = Selector.register("statusBarOrientationAnimationDuration");
    @Bridge private native static double objc_getStatusBarOrientationAnimationDuration(UIApplication __self__, Selector __cmd__);
    @Bridge private native static double objc_getStatusBarOrientationAnimationDurationSuper(ObjCSuper __super__, Selector __cmd__);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIApplication_Class/Reference/Reference.html#//apple_ref/occ/instp/UIApplication/statusBarOrientationAnimationDuration">@property(nonatomic, readonly) NSTimeInterval statusBarOrientationAnimationDuration</a>
     * @since Available in iOS 2.0 and later.
     */
    public double getStatusBarOrientationAnimationDuration() {
        if (customClass) { return objc_getStatusBarOrientationAnimationDurationSuper(getSuper(), statusBarOrientationAnimationDuration); } else { return objc_getStatusBarOrientationAnimationDuration(this, statusBarOrientationAnimationDuration); }
    }
    
    private static final Selector statusBarStyle = Selector.register("statusBarStyle");
    @Bridge private native static UIStatusBarStyle objc_getStatusBarStyle(UIApplication __self__, Selector __cmd__);
    @Bridge private native static UIStatusBarStyle objc_getStatusBarStyleSuper(ObjCSuper __super__, Selector __cmd__);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIApplication_Class/Reference/Reference.html#//apple_ref/occ/instp/UIApplication/statusBarStyle">@property(nonatomic) UIStatusBarStyle statusBarStyle</a>
     * @since Available in iOS 2.0 and later.
     */
    public UIStatusBarStyle getStatusBarStyle() {
        if (customClass) { return objc_getStatusBarStyleSuper(getSuper(), statusBarStyle); } else { return objc_getStatusBarStyle(this, statusBarStyle); }
    }
    
    private static final Selector setStatusBarStyle$ = Selector.register("setStatusBarStyle:");
    @Bridge private native static void objc_setStatusBarStyle(UIApplication __self__, Selector __cmd__, UIStatusBarStyle statusBarStyle);
    @Bridge private native static void objc_setStatusBarStyleSuper(ObjCSuper __super__, Selector __cmd__, UIStatusBarStyle statusBarStyle);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIApplication_Class/Reference/Reference.html#//apple_ref/occ/instp/UIApplication/statusBarStyle">@property(nonatomic) UIStatusBarStyle statusBarStyle</a>
     * @since Available in iOS 2.0 and later.
     */
    public void setStatusBarStyle(UIStatusBarStyle statusBarStyle) {
        if (customClass) { objc_setStatusBarStyleSuper(getSuper(), setStatusBarStyle$, statusBarStyle); } else { objc_setStatusBarStyle(this, setStatusBarStyle$, statusBarStyle); }
    }
    
    private static final Selector userInterfaceLayoutDirection = Selector.register("userInterfaceLayoutDirection");
    @Bridge private native static UIUserInterfaceLayoutDirection objc_getUserInterfaceLayoutDirection(UIApplication __self__, Selector __cmd__);
    @Bridge private native static UIUserInterfaceLayoutDirection objc_getUserInterfaceLayoutDirectionSuper(ObjCSuper __super__, Selector __cmd__);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIApplication_Class/Reference/Reference.html#//apple_ref/occ/instp/UIApplication/userInterfaceLayoutDirection">@property(nonatomic, readonly) UIUserInterfaceLayoutDirection userInterfaceLayoutDirection</a>
     * @since Available in iOS 5.0 and later.
     */
    public UIUserInterfaceLayoutDirection getUserInterfaceLayoutDirection() {
        if (customClass) { return objc_getUserInterfaceLayoutDirectionSuper(getSuper(), userInterfaceLayoutDirection); } else { return objc_getUserInterfaceLayoutDirection(this, userInterfaceLayoutDirection); }
    }
    
    private static final Selector windows = Selector.register("windows");
    @Bridge private native static NSArray objc_getWindows(UIApplication __self__, Selector __cmd__);
    @Bridge private native static NSArray objc_getWindowsSuper(ObjCSuper __super__, Selector __cmd__);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIApplication_Class/Reference/Reference.html#//apple_ref/occ/instp/UIApplication/windows">@property(nonatomic, readonly) NSArray *windows</a>
     * @since Available in iOS 2.0 and later.
     */
    public NSArray getWindows() {
        if (customClass) { return objc_getWindowsSuper(getSuper(), windows); } else { return objc_getWindows(this, windows); }
    }
    /*</properties>*/
    /*<methods>*/
    
    private static final Selector sharedApplication = Selector.register("sharedApplication");
    @Bridge private native static UIApplication objc_getSharedApplication(ObjCClass __self__, Selector __cmd__);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIApplication_Class/Reference/Reference.html#//apple_ref/occ/clm/UIApplication/sharedApplication">+ (UIApplication *)sharedApplication</a>
     * @since Available in iOS 2.0 and later.
     */
    public static UIApplication getSharedApplication() {
        return objc_getSharedApplication(objCClass, sharedApplication);
    }
    
    private static final Selector beginBackgroundTaskWithExpirationHandler$ = Selector.register("beginBackgroundTaskWithExpirationHandler:");
    @Bridge private native static int objc_beginBackgroundTask(UIApplication __self__, Selector __cmd__, VoidBlock handler);
    @Bridge private native static int objc_beginBackgroundTaskSuper(ObjCSuper __super__, Selector __cmd__, VoidBlock handler);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIApplication_Class/Reference/Reference.html#//apple_ref/occ/instm/UIApplication/beginBackgroundTaskWithExpirationHandler:">- (UIBackgroundTaskIdentifier)beginBackgroundTaskWithExpirationHandler:(void (^)(void))handler</a>
     * @since Available in iOS 4.0 and later.
     */
    public int beginBackgroundTask(VoidBlock handler) {
        if (customClass) { return objc_beginBackgroundTaskSuper(getSuper(), beginBackgroundTaskWithExpirationHandler$, handler); } else { return objc_beginBackgroundTask(this, beginBackgroundTaskWithExpirationHandler$, handler); }
    }
    
    private static final Selector beginIgnoringInteractionEvents = Selector.register("beginIgnoringInteractionEvents");
    @Bridge private native static void objc_beginIgnoringInteractionEvents(UIApplication __self__, Selector __cmd__);
    @Bridge private native static void objc_beginIgnoringInteractionEventsSuper(ObjCSuper __super__, Selector __cmd__);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIApplication_Class/Reference/Reference.html#//apple_ref/occ/instm/UIApplication/beginIgnoringInteractionEvents">- (void)beginIgnoringInteractionEvents</a>
     * @since Available in iOS 2.0 and later.
     */
    public void beginIgnoringInteractionEvents() {
        if (customClass) { objc_beginIgnoringInteractionEventsSuper(getSuper(), beginIgnoringInteractionEvents); } else { objc_beginIgnoringInteractionEvents(this, beginIgnoringInteractionEvents); }
    }
    
    private static final Selector beginReceivingRemoteControlEvents = Selector.register("beginReceivingRemoteControlEvents");
    @Bridge private native static void objc_beginReceivingRemoteControlEvents(UIApplication __self__, Selector __cmd__);
    @Bridge private native static void objc_beginReceivingRemoteControlEventsSuper(ObjCSuper __super__, Selector __cmd__);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIApplication_Class/Reference/Reference.html#//apple_ref/occ/instm/UIApplication/beginReceivingRemoteControlEvents">- (void)beginReceivingRemoteControlEvents</a>
     * @since Available in iOS 4.0 and later.
     */
    public void beginReceivingRemoteControlEvents() {
        if (customClass) { objc_beginReceivingRemoteControlEventsSuper(getSuper(), beginReceivingRemoteControlEvents); } else { objc_beginReceivingRemoteControlEvents(this, beginReceivingRemoteControlEvents); }
    }
    
    private static final Selector canOpenURL$ = Selector.register("canOpenURL:");
    @Bridge private native static boolean objc_canOpenURL(UIApplication __self__, Selector __cmd__, NSURL url);
    @Bridge private native static boolean objc_canOpenURLSuper(ObjCSuper __super__, Selector __cmd__, NSURL url);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIApplication_Class/Reference/Reference.html#//apple_ref/occ/instm/UIApplication/canOpenURL:">- (BOOL)canOpenURL:(NSURL *)url</a>
     * @since Available in iOS 3.0 and later.
     */
    public boolean canOpenURL(NSURL url) {
        if (customClass) { return objc_canOpenURLSuper(getSuper(), canOpenURL$, url); } else { return objc_canOpenURL(this, canOpenURL$, url); }
    }
    
    private static final Selector cancelAllLocalNotifications = Selector.register("cancelAllLocalNotifications");
    @Bridge private native static void objc_cancelAllLocalNotifications(UIApplication __self__, Selector __cmd__);
    @Bridge private native static void objc_cancelAllLocalNotificationsSuper(ObjCSuper __super__, Selector __cmd__);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIApplication_Class/Reference/Reference.html#//apple_ref/occ/instm/UIApplication/cancelAllLocalNotifications">- (void)cancelAllLocalNotifications</a>
     * @since Available in iOS 4.0 and later.
     */
    public void cancelAllLocalNotifications() {
        if (customClass) { objc_cancelAllLocalNotificationsSuper(getSuper(), cancelAllLocalNotifications); } else { objc_cancelAllLocalNotifications(this, cancelAllLocalNotifications); }
    }
    
    private static final Selector cancelLocalNotification$ = Selector.register("cancelLocalNotification:");
    @Bridge private native static void objc_cancelLocalNotification(UIApplication __self__, Selector __cmd__, UILocalNotification notification);
    @Bridge private native static void objc_cancelLocalNotificationSuper(ObjCSuper __super__, Selector __cmd__, UILocalNotification notification);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIApplication_Class/Reference/Reference.html#//apple_ref/occ/instm/UIApplication/cancelLocalNotification:">- (void)cancelLocalNotification:(UILocalNotification *)notification</a>
     * @since Available in iOS 4.0 and later.
     */
    public void cancelLocalNotification(UILocalNotification notification) {
        if (customClass) { objc_cancelLocalNotificationSuper(getSuper(), cancelLocalNotification$, notification); } else { objc_cancelLocalNotification(this, cancelLocalNotification$, notification); }
    }
    
    private static final Selector clearKeepAliveTimeout = Selector.register("clearKeepAliveTimeout");
    @Bridge private native static void objc_clearKeepAliveTimeout(UIApplication __self__, Selector __cmd__);
    @Bridge private native static void objc_clearKeepAliveTimeoutSuper(ObjCSuper __super__, Selector __cmd__);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIApplication_Class/Reference/Reference.html#//apple_ref/occ/instm/UIApplication/clearKeepAliveTimeout">- (void)clearKeepAliveTimeout</a>
     * @since Available in iOS 4.0 and later.
     */
    public void clearKeepAliveTimeout() {
        if (customClass) { objc_clearKeepAliveTimeoutSuper(getSuper(), clearKeepAliveTimeout); } else { objc_clearKeepAliveTimeout(this, clearKeepAliveTimeout); }
    }
    
    private static final Selector completeStateRestoration = Selector.register("completeStateRestoration");
    @Bridge private native static void objc_completeStateRestoration(UIApplication __self__, Selector __cmd__);
    @Bridge private native static void objc_completeStateRestorationSuper(ObjCSuper __super__, Selector __cmd__);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIApplication_Class/Reference/Reference.html#//apple_ref/occ/instm/UIApplication/completeStateRestoration">- (void)completeStateRestoration</a>
     * @since Available in iOS 6.0 and later.
     */
    public void completeStateRestoration() {
        if (customClass) { objc_completeStateRestorationSuper(getSuper(), completeStateRestoration); } else { objc_completeStateRestoration(this, completeStateRestoration); }
    }
    
    private static final Selector endBackgroundTask$ = Selector.register("endBackgroundTask:");
    @Bridge private native static void objc_endBackgroundTask(UIApplication __self__, Selector __cmd__, int identifier);
    @Bridge private native static void objc_endBackgroundTaskSuper(ObjCSuper __super__, Selector __cmd__, int identifier);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIApplication_Class/Reference/Reference.html#//apple_ref/occ/instm/UIApplication/endBackgroundTask:">- (void)endBackgroundTask:(UIBackgroundTaskIdentifier)identifier</a>
     * @since Available in iOS 4.0 and later.
     */
    public void endBackgroundTask(int identifier) {
        if (customClass) { objc_endBackgroundTaskSuper(getSuper(), endBackgroundTask$, identifier); } else { objc_endBackgroundTask(this, endBackgroundTask$, identifier); }
    }
    
    private static final Selector endIgnoringInteractionEvents = Selector.register("endIgnoringInteractionEvents");
    @Bridge private native static void objc_endIgnoringInteractionEvents(UIApplication __self__, Selector __cmd__);
    @Bridge private native static void objc_endIgnoringInteractionEventsSuper(ObjCSuper __super__, Selector __cmd__);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIApplication_Class/Reference/Reference.html#//apple_ref/occ/instm/UIApplication/endIgnoringInteractionEvents">- (void)endIgnoringInteractionEvents</a>
     * @since Available in iOS 2.0 and later.
     */
    public void endIgnoringInteractionEvents() {
        if (customClass) { objc_endIgnoringInteractionEventsSuper(getSuper(), endIgnoringInteractionEvents); } else { objc_endIgnoringInteractionEvents(this, endIgnoringInteractionEvents); }
    }
    
    private static final Selector endReceivingRemoteControlEvents = Selector.register("endReceivingRemoteControlEvents");
    @Bridge private native static void objc_endReceivingRemoteControlEvents(UIApplication __self__, Selector __cmd__);
    @Bridge private native static void objc_endReceivingRemoteControlEventsSuper(ObjCSuper __super__, Selector __cmd__);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIApplication_Class/Reference/Reference.html#//apple_ref/occ/instm/UIApplication/endReceivingRemoteControlEvents">- (void)endReceivingRemoteControlEvents</a>
     * @since Available in iOS 4.0 and later.
     */
    public void endReceivingRemoteControlEvents() {
        if (customClass) { objc_endReceivingRemoteControlEventsSuper(getSuper(), endReceivingRemoteControlEvents); } else { objc_endReceivingRemoteControlEvents(this, endReceivingRemoteControlEvents); }
    }
    
    private static final Selector extendStateRestoration = Selector.register("extendStateRestoration");
    @Bridge private native static void objc_extendStateRestoration(UIApplication __self__, Selector __cmd__);
    @Bridge private native static void objc_extendStateRestorationSuper(ObjCSuper __super__, Selector __cmd__);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIApplication_Class/Reference/Reference.html#//apple_ref/occ/instm/UIApplication/extendStateRestoration">- (void)extendStateRestoration</a>
     * @since Available in iOS 6.0 and later.
     */
    public void extendStateRestoration() {
        if (customClass) { objc_extendStateRestorationSuper(getSuper(), extendStateRestoration); } else { objc_extendStateRestoration(this, extendStateRestoration); }
    }
    
    private static final Selector enabledRemoteNotificationTypes = Selector.register("enabledRemoteNotificationTypes");
    @Bridge private native static UIRemoteNotificationType objc_getEnabledRemoteNotificationTypes(UIApplication __self__, Selector __cmd__);
    @Bridge private native static UIRemoteNotificationType objc_getEnabledRemoteNotificationTypesSuper(ObjCSuper __super__, Selector __cmd__);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIApplication_Class/Reference/Reference.html#//apple_ref/occ/instm/UIApplication/enabledRemoteNotificationTypes">- (UIRemoteNotificationType)enabledRemoteNotificationTypes</a>
     * @since Available in iOS 3.0 and later.
     */
    public UIRemoteNotificationType getEnabledRemoteNotificationTypes() {
        if (customClass) { return objc_getEnabledRemoteNotificationTypesSuper(getSuper(), enabledRemoteNotificationTypes); } else { return objc_getEnabledRemoteNotificationTypes(this, enabledRemoteNotificationTypes); }
    }
    
    private static final Selector supportedInterfaceOrientationsForWindow$ = Selector.register("supportedInterfaceOrientationsForWindow:");
    @Bridge private native static int objc_getSupportedInterfaceOrientations(UIApplication __self__, Selector __cmd__, UIWindow window);
    @Bridge private native static int objc_getSupportedInterfaceOrientationsSuper(ObjCSuper __super__, Selector __cmd__, UIWindow window);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIApplication_Class/Reference/Reference.html#//apple_ref/occ/instm/UIApplication/supportedInterfaceOrientationsForWindow:">- (NSUInteger)supportedInterfaceOrientationsForWindow:(UIWindow *)window</a>
     * @since Available in iOS 6.0 and later.
     */
    public int getSupportedInterfaceOrientations(UIWindow window) {
        if (customClass) { return objc_getSupportedInterfaceOrientationsSuper(getSuper(), supportedInterfaceOrientationsForWindow$, window); } else { return objc_getSupportedInterfaceOrientations(this, supportedInterfaceOrientationsForWindow$, window); }
    }
    
    private static final Selector isIgnoringInteractionEvents = Selector.register("isIgnoringInteractionEvents");
    @Bridge private native static boolean objc_isIgnoringInteractionEvents(UIApplication __self__, Selector __cmd__);
    @Bridge private native static boolean objc_isIgnoringInteractionEventsSuper(ObjCSuper __super__, Selector __cmd__);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIApplication_Class/Reference/Reference.html#//apple_ref/occ/instm/UIApplication/isIgnoringInteractionEvents">- (BOOL)isIgnoringInteractionEvents</a>
     * @since Available in iOS 2.0 and later.
     */
    public boolean isIgnoringInteractionEvents() {
        if (customClass) { return objc_isIgnoringInteractionEventsSuper(getSuper(), isIgnoringInteractionEvents); } else { return objc_isIgnoringInteractionEvents(this, isIgnoringInteractionEvents); }
    }
    
    private static final Selector openURL$ = Selector.register("openURL:");
    @Bridge private native static boolean objc_openURL(UIApplication __self__, Selector __cmd__, NSURL url);
    @Bridge private native static boolean objc_openURLSuper(ObjCSuper __super__, Selector __cmd__, NSURL url);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIApplication_Class/Reference/Reference.html#//apple_ref/occ/instm/UIApplication/openURL:">- (BOOL)openURL:(NSURL *)url</a>
     * @since Available in iOS 2.0 and later.
     */
    public boolean openURL(NSURL url) {
        if (customClass) { return objc_openURLSuper(getSuper(), openURL$, url); } else { return objc_openURL(this, openURL$, url); }
    }
    
    private static final Selector presentLocalNotificationNow$ = Selector.register("presentLocalNotificationNow:");
    @Bridge private native static void objc_presentLocalNotificationNow(UIApplication __self__, Selector __cmd__, UILocalNotification notification);
    @Bridge private native static void objc_presentLocalNotificationNowSuper(ObjCSuper __super__, Selector __cmd__, UILocalNotification notification);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIApplication_Class/Reference/Reference.html#//apple_ref/occ/instm/UIApplication/presentLocalNotificationNow:">- (void)presentLocalNotificationNow:(UILocalNotification *)notification</a>
     * @since Available in iOS 4.0 and later.
     */
    public void presentLocalNotificationNow(UILocalNotification notification) {
        if (customClass) { objc_presentLocalNotificationNowSuper(getSuper(), presentLocalNotificationNow$, notification); } else { objc_presentLocalNotificationNow(this, presentLocalNotificationNow$, notification); }
    }
    
    private static final Selector registerForRemoteNotificationTypes$ = Selector.register("registerForRemoteNotificationTypes:");
    @Bridge private native static void objc_registerForRemoteNotificationTypes(UIApplication __self__, Selector __cmd__, UIRemoteNotificationType types);
    @Bridge private native static void objc_registerForRemoteNotificationTypesSuper(ObjCSuper __super__, Selector __cmd__, UIRemoteNotificationType types);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIApplication_Class/Reference/Reference.html#//apple_ref/occ/instm/UIApplication/registerForRemoteNotificationTypes:">- (void)registerForRemoteNotificationTypes:(UIRemoteNotificationType)types</a>
     * @since Available in iOS 3.0 and later.
     */
    public void registerForRemoteNotificationTypes(UIRemoteNotificationType types) {
        if (customClass) { objc_registerForRemoteNotificationTypesSuper(getSuper(), registerForRemoteNotificationTypes$, types); } else { objc_registerForRemoteNotificationTypes(this, registerForRemoteNotificationTypes$, types); }
    }
    
    private static final Selector scheduleLocalNotification$ = Selector.register("scheduleLocalNotification:");
    @Bridge private native static void objc_scheduleLocalNotification(UIApplication __self__, Selector __cmd__, UILocalNotification notification);
    @Bridge private native static void objc_scheduleLocalNotificationSuper(ObjCSuper __super__, Selector __cmd__, UILocalNotification notification);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIApplication_Class/Reference/Reference.html#//apple_ref/occ/instm/UIApplication/scheduleLocalNotification:">- (void)scheduleLocalNotification:(UILocalNotification *)notification</a>
     * @since Available in iOS 4.0 and later.
     */
    public void scheduleLocalNotification(UILocalNotification notification) {
        if (customClass) { objc_scheduleLocalNotificationSuper(getSuper(), scheduleLocalNotification$, notification); } else { objc_scheduleLocalNotification(this, scheduleLocalNotification$, notification); }
    }
    
    private static final Selector sendAction$to$from$forEvent$ = Selector.register("sendAction:to:from:forEvent:");
    @Bridge private native static boolean objc_sendAction(UIApplication __self__, Selector __cmd__, Selector action, NSObject target, NSObject sender, UIEvent event);
    @Bridge private native static boolean objc_sendActionSuper(ObjCSuper __super__, Selector __cmd__, Selector action, NSObject target, NSObject sender, UIEvent event);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIApplication_Class/Reference/Reference.html#//apple_ref/occ/instm/UIApplication/sendAction:to:from:forEvent:">- (BOOL)sendAction:(SEL)action to:(id)target from:(id)sender forEvent:(UIEvent *)event</a>
     * @since Available in iOS 2.0 and later.
     */
    public boolean sendAction(Selector action, NSObject target, NSObject sender, UIEvent event) {
        if (customClass) { return objc_sendActionSuper(getSuper(), sendAction$to$from$forEvent$, action, target, sender, event); } else { return objc_sendAction(this, sendAction$to$from$forEvent$, action, target, sender, event); }
    }
    
    private static final Selector sendEvent$ = Selector.register("sendEvent:");
    @Bridge private native static void objc_sendEvent(UIApplication __self__, Selector __cmd__, UIEvent event);
    @Bridge private native static void objc_sendEventSuper(ObjCSuper __super__, Selector __cmd__, UIEvent event);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIApplication_Class/Reference/Reference.html#//apple_ref/occ/instm/UIApplication/sendEvent:">- (void)sendEvent:(UIEvent *)event</a>
     * @since Available in iOS 2.0 and later.
     */
    public void sendEvent(UIEvent event) {
        if (customClass) { objc_sendEventSuper(getSuper(), sendEvent$, event); } else { objc_sendEvent(this, sendEvent$, event); }
    }
    
    private static final Selector setKeepAliveTimeout$handler$ = Selector.register("setKeepAliveTimeout:handler:");
    @Bridge private native static boolean objc_setKeepAliveTimeout(UIApplication __self__, Selector __cmd__, double timeout, VoidBlock keepAliveHandler);
    @Bridge private native static boolean objc_setKeepAliveTimeoutSuper(ObjCSuper __super__, Selector __cmd__, double timeout, VoidBlock keepAliveHandler);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIApplication_Class/Reference/Reference.html#//apple_ref/occ/instm/UIApplication/setKeepAliveTimeout:handler:">- (BOOL)setKeepAliveTimeout:(NSTimeInterval)timeout handler:(void (^)(void))keepAliveHandler</a>
     * @since Available in iOS 4.0 and later.
     */
    public boolean setKeepAliveTimeout(double timeout, VoidBlock keepAliveHandler) {
        if (customClass) { return objc_setKeepAliveTimeoutSuper(getSuper(), setKeepAliveTimeout$handler$, timeout, keepAliveHandler); } else { return objc_setKeepAliveTimeout(this, setKeepAliveTimeout$handler$, timeout, keepAliveHandler); }
    }
    
    private static final Selector setNewsstandIconImage$ = Selector.register("setNewsstandIconImage:");
    @Bridge private native static void objc_setNewsstandIconImage(UIApplication __self__, Selector __cmd__, UIImage image);
    @Bridge private native static void objc_setNewsstandIconImageSuper(ObjCSuper __super__, Selector __cmd__, UIImage image);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIApplication_Class/Reference/Reference.html#//apple_ref/occ/instm/UIApplication/setNewsstandIconImage:">- (void)setNewsstandIconImage:(UIImage *)image</a>
     * @since Available in iOS 5.0 and later.
     */
    public void setNewsstandIconImage(UIImage image) {
        if (customClass) { objc_setNewsstandIconImageSuper(getSuper(), setNewsstandIconImage$, image); } else { objc_setNewsstandIconImage(this, setNewsstandIconImage$, image); }
    }
    
    private static final Selector setStatusBarHidden$withAnimation$ = Selector.register("setStatusBarHidden:withAnimation:");
    @Bridge private native static void objc_setStatusBarHidden(UIApplication __self__, Selector __cmd__, boolean hidden, UIStatusBarAnimation animation);
    @Bridge private native static void objc_setStatusBarHiddenSuper(ObjCSuper __super__, Selector __cmd__, boolean hidden, UIStatusBarAnimation animation);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIApplication_Class/Reference/Reference.html#//apple_ref/occ/instm/UIApplication/setStatusBarHidden:withAnimation:">- (void)setStatusBarHidden:(BOOL)hidden withAnimation:(UIStatusBarAnimation)animation</a>
     * @since Available in iOS 3.2 and later.
     */
    public void setStatusBarHidden(boolean hidden, UIStatusBarAnimation animation) {
        if (customClass) { objc_setStatusBarHiddenSuper(getSuper(), setStatusBarHidden$withAnimation$, hidden, animation); } else { objc_setStatusBarHidden(this, setStatusBarHidden$withAnimation$, hidden, animation); }
    }
    
    private static final Selector setStatusBarOrientation$animated$ = Selector.register("setStatusBarOrientation:animated:");
    @Bridge private native static void objc_setStatusBarOrientation(UIApplication __self__, Selector __cmd__, UIInterfaceOrientation interfaceOrientation, boolean animated);
    @Bridge private native static void objc_setStatusBarOrientationSuper(ObjCSuper __super__, Selector __cmd__, UIInterfaceOrientation interfaceOrientation, boolean animated);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIApplication_Class/Reference/Reference.html#//apple_ref/occ/instm/UIApplication/setStatusBarOrientation:animated:">- (void)setStatusBarOrientation:(UIInterfaceOrientation)interfaceOrientation animated:(BOOL)animated</a>
     * @since Available in iOS 2.0 and later.
     */
    public void setStatusBarOrientation(UIInterfaceOrientation interfaceOrientation, boolean animated) {
        if (customClass) { objc_setStatusBarOrientationSuper(getSuper(), setStatusBarOrientation$animated$, interfaceOrientation, animated); } else { objc_setStatusBarOrientation(this, setStatusBarOrientation$animated$, interfaceOrientation, animated); }
    }
    
    private static final Selector setStatusBarStyle$animated$ = Selector.register("setStatusBarStyle:animated:");
    @Bridge private native static void objc_setStatusBarStyle(UIApplication __self__, Selector __cmd__, UIStatusBarStyle statusBarStyle, boolean animated);
    @Bridge private native static void objc_setStatusBarStyleSuper(ObjCSuper __super__, Selector __cmd__, UIStatusBarStyle statusBarStyle, boolean animated);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIApplication_Class/Reference/Reference.html#//apple_ref/occ/instm/UIApplication/setStatusBarStyle:animated:">- (void)setStatusBarStyle:(UIStatusBarStyle)statusBarStyle animated:(BOOL)animated</a>
     * @since Available in iOS 2.0 and later.
     */
    public void setStatusBarStyle(UIStatusBarStyle statusBarStyle, boolean animated) {
        if (customClass) { objc_setStatusBarStyleSuper(getSuper(), setStatusBarStyle$animated$, statusBarStyle, animated); } else { objc_setStatusBarStyle(this, setStatusBarStyle$animated$, statusBarStyle, animated); }
    }
    
    private static final Selector unregisterForRemoteNotifications = Selector.register("unregisterForRemoteNotifications");
    @Bridge private native static void objc_unregisterForRemoteNotifications(UIApplication __self__, Selector __cmd__);
    @Bridge private native static void objc_unregisterForRemoteNotificationsSuper(ObjCSuper __super__, Selector __cmd__);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIApplication_Class/Reference/Reference.html#//apple_ref/occ/instm/UIApplication/unregisterForRemoteNotifications">- (void)unregisterForRemoteNotifications</a>
     * @since Available in iOS 3.0 and later.
     */
    public void unregisterForRemoteNotifications() {
        if (customClass) { objc_unregisterForRemoteNotificationsSuper(getSuper(), unregisterForRemoteNotifications); } else { objc_unregisterForRemoteNotifications(this, unregisterForRemoteNotifications); }
    }
    /*</methods>*/
    /*<callbacks>*/
    static class Callbacks {
        @Callback @BindSelector("applicationIconBadgeNumber") public static int getApplicationIconBadgeNumber(UIApplication __self__, Selector __cmd__) { return __self__.getApplicationIconBadgeNumber(); }
        @Callback @BindSelector("setApplicationIconBadgeNumber:") public static void setApplicationIconBadgeNumber(UIApplication __self__, Selector __cmd__, int applicationIconBadgeNumber) { __self__.setApplicationIconBadgeNumber(applicationIconBadgeNumber); }
        @Callback @BindSelector("applicationState") public static UIApplicationState getApplicationState(UIApplication __self__, Selector __cmd__) { return __self__.getApplicationState(); }
        @Callback @BindSelector("applicationSupportsShakeToEdit") public static boolean isApplicationSupportsShakeToEdit(UIApplication __self__, Selector __cmd__) { return __self__.isApplicationSupportsShakeToEdit(); }
        @Callback @BindSelector("setApplicationSupportsShakeToEdit:") public static void setApplicationSupportsShakeToEdit(UIApplication __self__, Selector __cmd__, boolean applicationSupportsShakeToEdit) { __self__.setApplicationSupportsShakeToEdit(applicationSupportsShakeToEdit); }
        @Callback @BindSelector("backgroundTimeRemaining") public static double getBackgroundTimeRemaining(UIApplication __self__, Selector __cmd__) { return __self__.getBackgroundTimeRemaining(); }
        @Callback @BindSelector("delegate") public static UIApplicationDelegate getDelegate(UIApplication __self__, Selector __cmd__) { return __self__.getDelegate(); }
        @Callback @BindSelector("setDelegate:") public static void setDelegate(UIApplication __self__, Selector __cmd__, UIApplicationDelegate delegate) { __self__.setDelegate(delegate); }
        @Callback @BindSelector("isIdleTimerDisabled") public static boolean isIdleTimerDisabled(UIApplication __self__, Selector __cmd__) { return __self__.isIdleTimerDisabled(); }
        @Callback @BindSelector("setIdleTimerDisabled:") public static void setIdleTimerDisabled(UIApplication __self__, Selector __cmd__, boolean idleTimerDisabled) { __self__.setIdleTimerDisabled(idleTimerDisabled); }
        @Callback @BindSelector("keyWindow") public static UIWindow getKeyWindow(UIApplication __self__, Selector __cmd__) { return __self__.getKeyWindow(); }
        @Callback @BindSelector("isNetworkActivityIndicatorVisible") public static boolean isNetworkActivityIndicatorVisible(UIApplication __self__, Selector __cmd__) { return __self__.isNetworkActivityIndicatorVisible(); }
        @Callback @BindSelector("setNetworkActivityIndicatorVisible:") public static void setNetworkActivityIndicatorVisible(UIApplication __self__, Selector __cmd__, boolean networkActivityIndicatorVisible) { __self__.setNetworkActivityIndicatorVisible(networkActivityIndicatorVisible); }
        @Callback @BindSelector("isProtectedDataAvailable") public static boolean isProtectedDataAvailable(UIApplication __self__, Selector __cmd__) { return __self__.isProtectedDataAvailable(); }
        @Callback @BindSelector("scheduledLocalNotifications") public static NSArray getScheduledLocalNotifications(UIApplication __self__, Selector __cmd__) { return __self__.getScheduledLocalNotifications(); }
        @Callback @BindSelector("setScheduledLocalNotifications:") public static void setScheduledLocalNotifications(UIApplication __self__, Selector __cmd__, NSArray scheduledLocalNotifications) { __self__.setScheduledLocalNotifications(scheduledLocalNotifications); }
        @Callback @BindSelector("statusBarFrame") public static @ByVal CGRect getStatusBarFrame(UIApplication __self__, Selector __cmd__) { return __self__.getStatusBarFrame(); }
        @Callback @BindSelector("isStatusBarHidden") public static boolean isStatusBarHidden(UIApplication __self__, Selector __cmd__) { return __self__.isStatusBarHidden(); }
        @Callback @BindSelector("setStatusBarHidden:") public static void setStatusBarHidden(UIApplication __self__, Selector __cmd__, boolean statusBarHidden) { __self__.setStatusBarHidden(statusBarHidden); }
        @Callback @BindSelector("statusBarOrientation") public static UIInterfaceOrientation getStatusBarOrientation(UIApplication __self__, Selector __cmd__) { return __self__.getStatusBarOrientation(); }
        @Callback @BindSelector("setStatusBarOrientation:") public static void setStatusBarOrientation(UIApplication __self__, Selector __cmd__, UIInterfaceOrientation statusBarOrientation) { __self__.setStatusBarOrientation(statusBarOrientation); }
        @Callback @BindSelector("statusBarOrientationAnimationDuration") public static double getStatusBarOrientationAnimationDuration(UIApplication __self__, Selector __cmd__) { return __self__.getStatusBarOrientationAnimationDuration(); }
        @Callback @BindSelector("statusBarStyle") public static UIStatusBarStyle getStatusBarStyle(UIApplication __self__, Selector __cmd__) { return __self__.getStatusBarStyle(); }
        @Callback @BindSelector("setStatusBarStyle:") public static void setStatusBarStyle(UIApplication __self__, Selector __cmd__, UIStatusBarStyle statusBarStyle) { __self__.setStatusBarStyle(statusBarStyle); }
        @Callback @BindSelector("userInterfaceLayoutDirection") public static UIUserInterfaceLayoutDirection getUserInterfaceLayoutDirection(UIApplication __self__, Selector __cmd__) { return __self__.getUserInterfaceLayoutDirection(); }
        @Callback @BindSelector("windows") public static NSArray getWindows(UIApplication __self__, Selector __cmd__) { return __self__.getWindows(); }
        @Callback @BindSelector("beginBackgroundTaskWithExpirationHandler:") public static int beginBackgroundTask(UIApplication __self__, Selector __cmd__, VoidBlock handler) { return __self__.beginBackgroundTask(handler); }
        @Callback @BindSelector("beginIgnoringInteractionEvents") public static void beginIgnoringInteractionEvents(UIApplication __self__, Selector __cmd__) { __self__.beginIgnoringInteractionEvents(); }
        @Callback @BindSelector("beginReceivingRemoteControlEvents") public static void beginReceivingRemoteControlEvents(UIApplication __self__, Selector __cmd__) { __self__.beginReceivingRemoteControlEvents(); }
        @Callback @BindSelector("canOpenURL:") public static boolean canOpenURL(UIApplication __self__, Selector __cmd__, NSURL url) { return __self__.canOpenURL(url); }
        @Callback @BindSelector("cancelAllLocalNotifications") public static void cancelAllLocalNotifications(UIApplication __self__, Selector __cmd__) { __self__.cancelAllLocalNotifications(); }
        @Callback @BindSelector("cancelLocalNotification:") public static void cancelLocalNotification(UIApplication __self__, Selector __cmd__, UILocalNotification notification) { __self__.cancelLocalNotification(notification); }
        @Callback @BindSelector("clearKeepAliveTimeout") public static void clearKeepAliveTimeout(UIApplication __self__, Selector __cmd__) { __self__.clearKeepAliveTimeout(); }
        @Callback @BindSelector("completeStateRestoration") public static void completeStateRestoration(UIApplication __self__, Selector __cmd__) { __self__.completeStateRestoration(); }
        @Callback @BindSelector("endBackgroundTask:") public static void endBackgroundTask(UIApplication __self__, Selector __cmd__, int identifier) { __self__.endBackgroundTask(identifier); }
        @Callback @BindSelector("endIgnoringInteractionEvents") public static void endIgnoringInteractionEvents(UIApplication __self__, Selector __cmd__) { __self__.endIgnoringInteractionEvents(); }
        @Callback @BindSelector("endReceivingRemoteControlEvents") public static void endReceivingRemoteControlEvents(UIApplication __self__, Selector __cmd__) { __self__.endReceivingRemoteControlEvents(); }
        @Callback @BindSelector("extendStateRestoration") public static void extendStateRestoration(UIApplication __self__, Selector __cmd__) { __self__.extendStateRestoration(); }
        @Callback @BindSelector("enabledRemoteNotificationTypes") public static UIRemoteNotificationType getEnabledRemoteNotificationTypes(UIApplication __self__, Selector __cmd__) { return __self__.getEnabledRemoteNotificationTypes(); }
        @Callback @BindSelector("supportedInterfaceOrientationsForWindow:") public static int getSupportedInterfaceOrientations(UIApplication __self__, Selector __cmd__, UIWindow window) { return __self__.getSupportedInterfaceOrientations(window); }
        @Callback @BindSelector("isIgnoringInteractionEvents") public static boolean isIgnoringInteractionEvents(UIApplication __self__, Selector __cmd__) { return __self__.isIgnoringInteractionEvents(); }
        @Callback @BindSelector("openURL:") public static boolean openURL(UIApplication __self__, Selector __cmd__, NSURL url) { return __self__.openURL(url); }
        @Callback @BindSelector("presentLocalNotificationNow:") public static void presentLocalNotificationNow(UIApplication __self__, Selector __cmd__, UILocalNotification notification) { __self__.presentLocalNotificationNow(notification); }
        @Callback @BindSelector("registerForRemoteNotificationTypes:") public static void registerForRemoteNotificationTypes(UIApplication __self__, Selector __cmd__, UIRemoteNotificationType types) { __self__.registerForRemoteNotificationTypes(types); }
        @Callback @BindSelector("scheduleLocalNotification:") public static void scheduleLocalNotification(UIApplication __self__, Selector __cmd__, UILocalNotification notification) { __self__.scheduleLocalNotification(notification); }
        @Callback @BindSelector("sendAction:to:from:forEvent:") public static boolean sendAction(UIApplication __self__, Selector __cmd__, Selector action, NSObject target, NSObject sender, UIEvent event) { return __self__.sendAction(action, target, sender, event); }
        @Callback @BindSelector("sendEvent:") public static void sendEvent(UIApplication __self__, Selector __cmd__, UIEvent event) { __self__.sendEvent(event); }
        @Callback @BindSelector("setKeepAliveTimeout:handler:") public static boolean setKeepAliveTimeout(UIApplication __self__, Selector __cmd__, double timeout, VoidBlock keepAliveHandler) { return __self__.setKeepAliveTimeout(timeout, keepAliveHandler); }
        @Callback @BindSelector("setNewsstandIconImage:") public static void setNewsstandIconImage(UIApplication __self__, Selector __cmd__, UIImage image) { __self__.setNewsstandIconImage(image); }
        @Callback @BindSelector("setStatusBarHidden:withAnimation:") public static void setStatusBarHidden(UIApplication __self__, Selector __cmd__, boolean hidden, UIStatusBarAnimation animation) { __self__.setStatusBarHidden(hidden, animation); }
        @Callback @BindSelector("setStatusBarOrientation:animated:") public static void setStatusBarOrientation(UIApplication __self__, Selector __cmd__, UIInterfaceOrientation interfaceOrientation, boolean animated) { __self__.setStatusBarOrientation(interfaceOrientation, animated); }
        @Callback @BindSelector("setStatusBarStyle:animated:") public static void setStatusBarStyle(UIApplication __self__, Selector __cmd__, UIStatusBarStyle statusBarStyle, boolean animated) { __self__.setStatusBarStyle(statusBarStyle, animated); }
        @Callback @BindSelector("unregisterForRemoteNotifications") public static void unregisterForRemoteNotifications(UIApplication __self__, Selector __cmd__) { __self__.unregisterForRemoteNotifications(); }
    }
    /*</callbacks>*/

}
