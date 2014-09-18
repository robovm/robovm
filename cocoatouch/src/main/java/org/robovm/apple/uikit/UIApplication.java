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
/**
 * @since Available in iOS 2.0 and later.
 */
/*</javadoc>*/
/*<annotations>*/@Library("UIKit") @NativeClass/*</annotations>*/
/*<visibility>*/public/*</visibility>*/ class /*<name>*/UIApplication/*</name>*/ 
    extends /*<extends>*/UIResponder/*</extends>*/ 
    /*<implements>*/implements UIActionSheetDelegate/*</implements>*/ {

    public static class Notifications {
        /**
         * @since Available in iOS 4.0 and later.
         */
        public static NSObject observeDidEnterBackground(final Runnable block) {
            return NSNotificationCenter.getDefaultCenter().addObserver(DidEnterBackgroundNotification(), null, NSOperationQueue.getMainQueue(), new VoidBlock1<NSNotification>() {
                @Override
                public void invoke(NSNotification a) {
                    block.run();
                }
            });
        }
        /**
         * @since Available in iOS 4.0 and later.
         */
        public static NSObject observeWillEnterForeground(final Runnable block) {
            return NSNotificationCenter.getDefaultCenter().addObserver(WillEnterForegroundNotification(), null, NSOperationQueue.getMainQueue(), new VoidBlock1<NSNotification>() {
                @Override
                public void invoke(NSNotification a) {
                    block.run();
                }
            });
        }
        public static NSObject observeDidFinishLaunching(final VoidBlock1<UIApplicationLaunchOptions> block) {
            return NSNotificationCenter.getDefaultCenter().addObserver(DidFinishLaunchingNotification(), null, NSOperationQueue.getMainQueue(), new VoidBlock1<NSNotification>() {
                @Override
                public void invoke(NSNotification a) {
                    if (a.getUserInfo() != null) {
                        block.invoke(new UIApplicationLaunchOptions(a.getUserInfo()));
                    } else {
                        block.invoke(null);
                    }
                }
            });
        }
        public static NSObject observeDidBecomeActive(final Runnable block) {
            return NSNotificationCenter.getDefaultCenter().addObserver(DidBecomeActiveNotification(), null, NSOperationQueue.getMainQueue(), new VoidBlock1<NSNotification>() {
                @Override
                public void invoke(NSNotification a) {
                    block.run();
                }
            });
        }
        public static NSObject observeWillResignActive(final Runnable block) {
            return NSNotificationCenter.getDefaultCenter().addObserver(WillResignActiveNotification(), null, NSOperationQueue.getMainQueue(), new VoidBlock1<NSNotification>() {
                @Override
                public void invoke(NSNotification a) {
                    block.run();
                }
            });
        }
        public static NSObject observeDidReceiveMemoryWarning(final Runnable block) {
            return NSNotificationCenter.getDefaultCenter().addObserver(DidReceiveMemoryWarningNotification(), null, NSOperationQueue.getMainQueue(), new VoidBlock1<NSNotification>() {
                @Override
                public void invoke(NSNotification a) {
                    block.run();
                }
            });
        }
        public static NSObject observeWillTerminate(final Runnable block) {
            return NSNotificationCenter.getDefaultCenter().addObserver(WillTerminateNotification(), null, NSOperationQueue.getMainQueue(), new VoidBlock1<NSNotification>() {
                @Override
                public void invoke(NSNotification a) {
                    block.run();
                }
            });
        }
        public static NSObject observeSignificantTimeChange(final Runnable block) {
            return NSNotificationCenter.getDefaultCenter().addObserver(SignificantTimeChangeNotification(), null, NSOperationQueue.getMainQueue(), new VoidBlock1<NSNotification>() {
                @Override
                public void invoke(NSNotification a) {
                    block.run();
                }
            });
        }
        public static NSObject observeWillChangeStatusBarOrientation(final VoidBlock1<UIInterfaceOrientation> block) {
            return NSNotificationCenter.getDefaultCenter().addObserver(WillChangeStatusBarOrientationNotification(), null, NSOperationQueue.getMainQueue(), new VoidBlock1<NSNotification>() {
                @Override
                public void invoke(NSNotification a) {
                    NSNumber val = (NSNumber)a.getUserInfo().get(StatusBarOrientationUserInfoKey());
                    block.invoke(UIInterfaceOrientation.valueOf(val.intValue()));
                }
            });
        }
        public static NSObject observeDidChangeStatusBarOrientation(final VoidBlock1<UIInterfaceOrientation> block) {
            return NSNotificationCenter.getDefaultCenter().addObserver(DidChangeStatusBarOrientationNotification(), null, NSOperationQueue.getMainQueue(), new VoidBlock1<NSNotification>() {
                @Override
                public void invoke(NSNotification a) {
                    NSNumber val = (NSNumber)a.getUserInfo().get(StatusBarOrientationUserInfoKey());
                    block.invoke(UIInterfaceOrientation.valueOf(val.intValue()));
                }
            });
        }
        public static NSObject observeWillChangeStatusBarFrame(final VoidBlock1<CGRect> block) {
            return NSNotificationCenter.getDefaultCenter().addObserver(WillChangeStatusBarFrameNotification(), null, NSOperationQueue.getMainQueue(), new VoidBlock1<NSNotification>() {
                @Override
                public void invoke(NSNotification a) {
                    NSValue val = (NSValue)a.getUserInfo().get(StatusBarFrameUserInfoKey());
                    block.invoke(NSValueExtensions.getRectValue(val));
                }
            });
        }
        public static NSObject observeDidChangeStatusBarFrame(final VoidBlock1<CGRect> block) {
            return NSNotificationCenter.getDefaultCenter().addObserver(DidChangeStatusBarFrameNotification(), null, NSOperationQueue.getMainQueue(), new VoidBlock1<NSNotification>() {
                @Override
                public void invoke(NSNotification a) {
                    NSValue val = (NSValue)a.getUserInfo().get(StatusBarFrameUserInfoKey());
                    block.invoke(NSValueExtensions.getRectValue(val));
                }
            });
        }
        /**
         * @since Available in iOS 7.0 and later.
         */
        public static NSObject observeBackgroundRefreshStatusDidChange(final Runnable block) {
            return NSNotificationCenter.getDefaultCenter().addObserver(BackgroundRefreshStatusDidChangeNotification(), null, NSOperationQueue.getMainQueue(), new VoidBlock1<NSNotification>() {
                @Override
                public void invoke(NSNotification a) {
                    block.run();
                }
            });
        }
        /**
         * @since Available in iOS 4.0 and later.
         */
        public static NSObject observeProtectedDataWillBecomeUnavailable(final Runnable block) {
            return NSNotificationCenter.getDefaultCenter().addObserver(ProtectedDataWillBecomeUnavailableNotification(), null, NSOperationQueue.getMainQueue(), new VoidBlock1<NSNotification>() {
                @Override
                public void invoke(NSNotification a) {
                    block.run();
                }
            });
        }
        /**
         * @since Available in iOS 4.0 and later.
         */
        public static NSObject observeProtectedDataDidBecomeAvailable(final Runnable block) {
            return NSNotificationCenter.getDefaultCenter().addObserver(ProtectedDataDidBecomeAvailableNotification(), null, NSOperationQueue.getMainQueue(), new VoidBlock1<NSNotification>() {
                @Override
                public void invoke(NSNotification a) {
                    block.run();
                }
            });
        }
        /**
         * @since Available in iOS 7.0 and later.
         */
        public static NSObject observeContentSizeCategoryDidChange(final VoidBlock1<UIContentSizeCategory> block) {
            return NSNotificationCenter.getDefaultCenter().addObserver(ContentSizeCategoryDidChangeNotification(), null, NSOperationQueue.getMainQueue(), new VoidBlock1<NSNotification>() {
                @Override
                public void invoke(NSNotification a) {
                    NSString val = (NSString)a.getUserInfo().get(ContentSizeCategoryNewValueKey());
                    block.invoke(UIContentSizeCategory.valueOf(val));
                }
            });
        }
        /**
         * @since Available in iOS 7.0 and later.
         */
        public static NSObject observeUserDidTakeScreenshot(final Runnable block) {
            return NSNotificationCenter.getDefaultCenter().addObserver(UserDidTakeScreenshotNotification(), null, NSOperationQueue.getMainQueue(), new VoidBlock1<NSNotification>() {
                @Override
                public void invoke(NSNotification a) {
                    block.run();
                }
            });
        }
    }
    /*<ptr>*/public static class UIApplicationPtr extends Ptr<UIApplication, UIApplicationPtr> {}/*</ptr>*/
    /*<bind>*/static { ObjCRuntime.bind(UIApplication.class); }/*</bind>*/
    /*<constants>*//*</constants>*/
    /*<constructors>*/
    public UIApplication() {}
    protected UIApplication(SkipInit skipInit) { super(skipInit); }
    /*</constructors>*/
    /*<properties>*/
    @Property(selector = "delegate")
    public native UIApplicationDelegate getDelegate();
    @Property(selector = "setDelegate:", strongRef = true)
    public native void setDelegate(UIApplicationDelegate v);
    @Property(selector = "isIdleTimerDisabled")
    public native boolean isIdleTimerDisabled();
    @Property(selector = "setIdleTimerDisabled:")
    public native void setIdleTimerDisabled(boolean v);
    @Property(selector = "keyWindow")
    public native UIWindow getKeyWindow();
    @Property(selector = "windows")
    public native NSArray<UIWindow> getWindows();
    @Property(selector = "isNetworkActivityIndicatorVisible")
    public native boolean isNetworkActivityIndicatorVisible();
    @Property(selector = "setNetworkActivityIndicatorVisible:")
    public native void setNetworkActivityIndicatorVisible(boolean v);
    @Property(selector = "statusBarStyle")
    public native UIStatusBarStyle getStatusBarStyle();
    @Property(selector = "setStatusBarStyle:")
    public native void setStatusBarStyle(UIStatusBarStyle v);
    @Property(selector = "isStatusBarHidden")
    public native boolean isStatusBarHidden();
    @Property(selector = "setStatusBarHidden:")
    public native void setStatusBarHidden(boolean v);
    @Property(selector = "statusBarOrientation")
    public native UIInterfaceOrientation getStatusBarOrientation();
    @Property(selector = "setStatusBarOrientation:")
    public native void setStatusBarOrientation(UIInterfaceOrientation v);
    @Property(selector = "statusBarOrientationAnimationDuration")
    public native double getStatusBarOrientationAnimationDuration();
    @Property(selector = "statusBarFrame")
    public native @ByVal CGRect getStatusBarFrame();
    @Property(selector = "applicationIconBadgeNumber")
    public native @MachineSizedSInt long getApplicationIconBadgeNumber();
    @Property(selector = "setApplicationIconBadgeNumber:")
    public native void setApplicationIconBadgeNumber(@MachineSizedSInt long v);
    /**
     * @since Available in iOS 3.0 and later.
     */
    @Property(selector = "applicationSupportsShakeToEdit")
    public native boolean isApplicationSupportsShakeToEdit();
    /**
     * @since Available in iOS 3.0 and later.
     */
    @Property(selector = "setApplicationSupportsShakeToEdit:")
    public native void setApplicationSupportsShakeToEdit(boolean v);
    /**
     * @since Available in iOS 4.0 and later.
     */
    @Property(selector = "applicationState")
    public native UIApplicationState getApplicationState();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @Property(selector = "backgroundTimeRemaining")
    public native double getBackgroundTimeRemaining();
    /**
     * @since Available in iOS 7.0 and later.
     */
    @Property(selector = "backgroundRefreshStatus")
    public native UIBackgroundRefreshStatus getBackgroundRefreshStatus();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @Property(selector = "isProtectedDataAvailable")
    public native boolean isProtectedDataAvailable();
    /**
     * @since Available in iOS 5.0 and later.
     */
    @Property(selector = "userInterfaceLayoutDirection")
    public native UIUserInterfaceLayoutDirection getUserInterfaceLayoutDirection();
    /**
     * @since Available in iOS 7.0 and later.
     */
    @Property(selector = "preferredContentSizeCategory")
    public native String getPreferredContentSizeCategory();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @Property(selector = "scheduledLocalNotifications")
    public native NSArray<UILocalNotification> getScheduledLocalNotifications();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @Property(selector = "setScheduledLocalNotifications:")
    public native void setScheduledLocalNotifications(NSArray<UILocalNotification> v);
    /**
     * @since Available in iOS 2.0 and later.
     * @deprecated Deprecated in iOS 3.0.
     */
    @Deprecated
    @Property(selector = "isProximitySensingEnabled")
    public native boolean isProximitySensingEnabled();
    /**
     * @since Available in iOS 2.0 and later.
     * @deprecated Deprecated in iOS 3.0.
     */
    @Deprecated
    @Property(selector = "setProximitySensingEnabled:")
    public native void setProximitySensingEnabled(boolean v);
    /*</properties>*/
    /*<members>*//*</members>*/
    
    public static <P extends UIApplication, D extends NSObject & UIApplicationDelegate> 
        void main(String[] args, Class<P> principalClass, Class<D> delegateClass) {
        
        int argc = args.length;
        BytePtr.BytePtrPtr argv = null;
        if (argc > 0) {
            argv = Struct.allocate(BytePtr.BytePtrPtr.class, argc);
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
        main(argc, argv, principalClassName, delegateClassName);
    }
    
    /*<methods>*/
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="UIMinimumKeepAliveTimeout", optional=true)
    public static native double MinimumKeepAliveTimeout();
    /**
     * @since Available in iOS 7.0 and later.
     */
    @GlobalValue(symbol="UIApplicationBackgroundFetchIntervalMinimum", optional=true)
    public static native double BackgroundFetchIntervalMinimum();
    /**
     * @since Available in iOS 7.0 and later.
     */
    @GlobalValue(symbol="UIApplicationBackgroundFetchIntervalNever", optional=true)
    public static native double BackgroundFetchIntervalNever();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="UIApplicationDidEnterBackgroundNotification", optional=true)
    public static native NSString DidEnterBackgroundNotification();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="UIApplicationWillEnterForegroundNotification", optional=true)
    public static native NSString WillEnterForegroundNotification();
    @GlobalValue(symbol="UIApplicationDidFinishLaunchingNotification", optional=true)
    public static native NSString DidFinishLaunchingNotification();
    @GlobalValue(symbol="UIApplicationDidBecomeActiveNotification", optional=true)
    public static native NSString DidBecomeActiveNotification();
    @GlobalValue(symbol="UIApplicationWillResignActiveNotification", optional=true)
    public static native NSString WillResignActiveNotification();
    @GlobalValue(symbol="UIApplicationDidReceiveMemoryWarningNotification", optional=true)
    public static native NSString DidReceiveMemoryWarningNotification();
    @GlobalValue(symbol="UIApplicationWillTerminateNotification", optional=true)
    public static native NSString WillTerminateNotification();
    @GlobalValue(symbol="UIApplicationSignificantTimeChangeNotification", optional=true)
    public static native NSString SignificantTimeChangeNotification();
    @GlobalValue(symbol="UIApplicationWillChangeStatusBarOrientationNotification", optional=true)
    public static native NSString WillChangeStatusBarOrientationNotification();
    @GlobalValue(symbol="UIApplicationDidChangeStatusBarOrientationNotification", optional=true)
    public static native NSString DidChangeStatusBarOrientationNotification();
    @GlobalValue(symbol="UIApplicationStatusBarOrientationUserInfoKey", optional=true)
    protected static native NSString StatusBarOrientationUserInfoKey();
    @GlobalValue(symbol="UIApplicationWillChangeStatusBarFrameNotification", optional=true)
    public static native NSString WillChangeStatusBarFrameNotification();
    @GlobalValue(symbol="UIApplicationDidChangeStatusBarFrameNotification", optional=true)
    public static native NSString DidChangeStatusBarFrameNotification();
    @GlobalValue(symbol="UIApplicationStatusBarFrameUserInfoKey", optional=true)
    protected static native NSString StatusBarFrameUserInfoKey();
    /**
     * @since Available in iOS 7.0 and later.
     */
    @GlobalValue(symbol="UIApplicationBackgroundRefreshStatusDidChangeNotification", optional=true)
    public static native NSString BackgroundRefreshStatusDidChangeNotification();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="UIApplicationProtectedDataWillBecomeUnavailable", optional=true)
    public static native NSString ProtectedDataWillBecomeUnavailableNotification();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="UIApplicationProtectedDataDidBecomeAvailable", optional=true)
    public static native NSString ProtectedDataDidBecomeAvailableNotification();
    /**
     * @since Available in iOS 7.0 and later.
     */
    @GlobalValue(symbol="UIContentSizeCategoryDidChangeNotification", optional=true)
    public static native NSString ContentSizeCategoryDidChangeNotification();
    /**
     * @since Available in iOS 7.0 and later.
     */
    @GlobalValue(symbol="UIContentSizeCategoryNewValueKey", optional=true)
    protected static native NSString ContentSizeCategoryNewValueKey();
    /**
     * @since Available in iOS 7.0 and later.
     */
    @GlobalValue(symbol="UIApplicationUserDidTakeScreenshotNotification", optional=true)
    public static native NSString UserDidTakeScreenshotNotification();
    
    @Bridge(symbol="UIApplicationMain", optional=true)
    protected static native int main(int argc, BytePtr.BytePtrPtr argv, String principalClassName, String delegateClassName);
    
    @Method(selector = "beginIgnoringInteractionEvents")
    public native void beginIgnoringInteractionEvents();
    @Method(selector = "endIgnoringInteractionEvents")
    public native void endIgnoringInteractionEvents();
    @Method(selector = "isIgnoringInteractionEvents")
    public native boolean isIgnoringInteractionEvents();
    @Method(selector = "openURL:")
    public native boolean openURL(NSURL url);
    /**
     * @since Available in iOS 3.0 and later.
     */
    @Method(selector = "canOpenURL:")
    public native boolean canOpenURL(NSURL url);
    @Method(selector = "sendEvent:")
    public native void sendEvent(UIEvent event);
    @Method(selector = "sendAction:to:from:forEvent:")
    public native boolean sendAction(Selector action, NSObject target, NSObject sender, UIEvent event);
    @Method(selector = "setStatusBarStyle:animated:")
    public native void setStatusBarStyle(UIStatusBarStyle statusBarStyle, boolean animated);
    /**
     * @since Available in iOS 3.2 and later.
     */
    @Method(selector = "setStatusBarHidden:withAnimation:")
    public native void setStatusBarHidden(boolean hidden, UIStatusBarAnimation animation);
    @Method(selector = "setStatusBarOrientation:animated:")
    public native void setStatusBarOrientation(UIInterfaceOrientation interfaceOrientation, boolean animated);
    /**
     * @since Available in iOS 6.0 and later.
     */
    @Method(selector = "supportedInterfaceOrientationsForWindow:")
    public native @MachineSizedUInt long getSupportedInterfaceOrientations(UIWindow window);
    /**
     * @since Available in iOS 4.0 and later.
     */
    @Method(selector = "beginBackgroundTaskWithExpirationHandler:")
    public native @MachineSizedUInt long beginBackgroundTask(@Block Runnable handler);
    /**
     * @since Available in iOS 7.0 and later.
     */
    @Method(selector = "beginBackgroundTaskWithName:expirationHandler:")
    public native @MachineSizedUInt long beginBackgroundTask(String taskName, @Block Runnable handler);
    /**
     * @since Available in iOS 4.0 and later.
     */
    @Method(selector = "endBackgroundTask:")
    public native void endBackgroundTask(@MachineSizedUInt long identifier);
    /**
     * @since Available in iOS 7.0 and later.
     */
    @Method(selector = "setMinimumBackgroundFetchInterval:")
    public native void setMinimumBackgroundFetchInterval(double minimumBackgroundFetchInterval);
    /**
     * @since Available in iOS 4.0 and later.
     */
    @Method(selector = "setKeepAliveTimeout:handler:")
    public native boolean setKeepAliveTimeout(double timeout, @Block Runnable keepAliveHandler);
    /**
     * @since Available in iOS 4.0 and later.
     */
    @Method(selector = "clearKeepAliveTimeout")
    public native void clearKeepAliveTimeout();
    @Method(selector = "sharedApplication")
    public static native UIApplication getSharedApplication();
    /**
     * @since Available in iOS 3.0 and later.
     */
    @Method(selector = "registerForRemoteNotificationTypes:")
    public native void registerForRemoteNotificationTypes(UIRemoteNotificationType types);
    /**
     * @since Available in iOS 3.0 and later.
     */
    @Method(selector = "unregisterForRemoteNotifications")
    public native void unregisterForRemoteNotifications();
    /**
     * @since Available in iOS 3.0 and later.
     */
    @Method(selector = "enabledRemoteNotificationTypes")
    public native UIRemoteNotificationType getEnabledRemoteNotificationTypes();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @Method(selector = "presentLocalNotificationNow:")
    public native void presentLocalNotificationNow(UILocalNotification notification);
    /**
     * @since Available in iOS 4.0 and later.
     */
    @Method(selector = "scheduleLocalNotification:")
    public native void scheduleLocalNotification(UILocalNotification notification);
    /**
     * @since Available in iOS 4.0 and later.
     */
    @Method(selector = "cancelLocalNotification:")
    public native void cancelLocalNotification(UILocalNotification notification);
    /**
     * @since Available in iOS 4.0 and later.
     */
    @Method(selector = "cancelAllLocalNotifications")
    public native void cancelAllLocalNotifications();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @Method(selector = "beginReceivingRemoteControlEvents")
    public native void beginReceivingRemoteControlEvents();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @Method(selector = "endReceivingRemoteControlEvents")
    public native void endReceivingRemoteControlEvents();
    @Method(selector = "setNewsstandIconImage:")
    public native void setNewsstandIconImage(UIImage image);
    /**
     * @since Available in iOS 6.0 and later.
     */
    @Method(selector = "extendStateRestoration")
    public native void extendStateRestoration();
    /**
     * @since Available in iOS 6.0 and later.
     */
    @Method(selector = "completeStateRestoration")
    public native void completeStateRestoration();
    /**
     * @since Available in iOS 7.0 and later.
     */
    @Method(selector = "ignoreSnapshotOnNextApplicationLaunch")
    public native void ignoreSnapshotOnNextApplicationLaunch();
    /**
     * @since Available in iOS 7.0 and later.
     */
    @Method(selector = "registerObjectForStateRestoration:restorationIdentifier:")
    public static native void registerObjectForStateRestoration(UIStateRestoring object, String restorationIdentifier);
    /**
     * @since Available in iOS 2.0 and later.
     * @deprecated Deprecated in iOS 3.2.
     */
    @Deprecated
    @Method(selector = "setStatusBarHidden:animated:")
    public native void setStatusBarHidden(boolean hidden, boolean animated);
    @Method(selector = "actionSheet:clickedButtonAtIndex:")
    public native void clicked(UIActionSheet actionSheet, @MachineSizedSInt long buttonIndex);
    @Method(selector = "actionSheetCancel:")
    public native void cancel(UIActionSheet actionSheet);
    @Method(selector = "willPresentActionSheet:")
    public native void willPresent(UIActionSheet actionSheet);
    @Method(selector = "didPresentActionSheet:")
    public native void didPresent(UIActionSheet actionSheet);
    @Method(selector = "actionSheet:willDismissWithButtonIndex:")
    public native void willDismiss(UIActionSheet actionSheet, @MachineSizedSInt long buttonIndex);
    @Method(selector = "actionSheet:didDismissWithButtonIndex:")
    public native void didDismiss(UIActionSheet actionSheet, @MachineSizedSInt long buttonIndex);
    /*</methods>*/
}
