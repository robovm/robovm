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
 * <div class="javadoc">
 *   @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIApplicationDelegate_Protocol/Reference/Reference.html">UIApplicationDelegate Protocol Reference</a>
 *   @since Available in iOS 2.0 and later.
 * </div>
 */
public interface /*<name>*/ UIApplicationDelegate /*</name>*/ /*<implements>*/ extends NSObjectProtocol /*</implements>*/ {

    /*<properties>*/
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIApplicationDelegate_Protocol/Reference/Reference.html#//apple_ref/occ/intfp/UIApplicationDelegate/window">@property(nonatomic, retain) UIWindow *window</a>
     * @since Available in iOS 5.0 and later.
     */
    UIWindow getWindow();
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIApplicationDelegate_Protocol/Reference/Reference.html#//apple_ref/occ/intfp/UIApplicationDelegate/window">@property(nonatomic, retain) UIWindow *window</a>
     * @since Available in iOS 5.0 and later.
     */
    void setWindow(UIWindow v);
    /*</properties>*/
    /*<methods>*/
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIApplicationDelegate_Protocol/Reference/Reference.html#//apple_ref/occ/intfm/UIApplicationDelegate/applicationDidBecomeActive:">- (void)applicationDidBecomeActive:(UIApplication *)application</a>
     * @since Available in iOS 2.0 and later.
     */
    void didBecomeActive(UIApplication application);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIApplicationDelegate_Protocol/Reference/Reference.html#//apple_ref/occ/intfm/UIApplicationDelegate/application:didChangeStatusBarFrame:">- (void)application:(UIApplication *)application didChangeStatusBarFrame:(CGRect)oldStatusBarFrame</a>
     * @since Available in iOS 2.0 and later.
     */
    void didChangStatusBarFrame(UIApplication application, CGRect oldStatusBarFrame);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIApplicationDelegate_Protocol/Reference/Reference.html#//apple_ref/occ/intfm/UIApplicationDelegate/application:didChangeStatusBarOrientation:">- (void)application:(UIApplication *)application didChangeStatusBarOrientation:(UIInterfaceOrientation)oldStatusBarOrientation</a>
     * @since Available in iOS 2.0 and later.
     */
    void didChangStatusBarOrientation(UIApplication application, UIInterfaceOrientation oldStatusBarOrientation);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIApplicationDelegate_Protocol/Reference/Reference.html#//apple_ref/occ/intfm/UIApplicationDelegate/application:didDecodeRestorableStateWithCoder:">- (void)application:(UIApplication *)application didDecodeRestorableStateWithCoder:(NSCoder *)coder</a>
     * @since Available in iOS 6.0 and later.
     */
    void didDecodeRestorableState(UIApplication application, NSCoder coder);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIApplicationDelegate_Protocol/Reference/Reference.html#//apple_ref/occ/intfm/UIApplicationDelegate/applicationDidEnterBackground:">- (void)applicationDidEnterBackground:(UIApplication *)application</a>
     * @since Available in iOS 4.0 and later.
     */
    void didEnterBackground(UIApplication application);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIApplicationDelegate_Protocol/Reference/Reference.html#//apple_ref/occ/intfm/UIApplicationDelegate/application:didFailToRegisterForRemoteNotificationsWithError:">- (void)application:(UIApplication *)application didFailToRegisterForRemoteNotificationsWithError:(NSError *)error</a>
     * @since Available in iOS 3.0 and later.
     */
    void didFailToRegisterForRemoteNotifications(UIApplication application, NSError error);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIApplicationDelegate_Protocol/Reference/Reference.html#//apple_ref/occ/intfm/UIApplicationDelegate/application:didFinishLaunchingWithOptions:">- (BOOL)application:(UIApplication *)application didFinishLaunchingWithOptions:(NSDictionary *)launchOptions</a>
     * @since Available in iOS 3.0 and later.
     */
    boolean didFinishLaunching(UIApplication application, NSDictionary launchOptions);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIApplicationDelegate_Protocol/Reference/Reference.html#//apple_ref/occ/intfm/UIApplicationDelegate/applicationDidFinishLaunching:">- (void)applicationDidFinishLaunching:(UIApplication *)application</a>
     * @since Available in iOS 2.0 and later.
     */
    void didFinishLaunching(UIApplication application);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIApplicationDelegate_Protocol/Reference/Reference.html#//apple_ref/occ/intfm/UIApplicationDelegate/application:didReceiveLocalNotification:">- (void)application:(UIApplication *)application didReceiveLocalNotification:(UILocalNotification *)notification</a>
     * @since Available in iOS 4.0 and later.
     */
    void didReceiveLocalNotification(UIApplication application, UILocalNotification notification);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIApplicationDelegate_Protocol/Reference/Reference.html#//apple_ref/occ/intfm/UIApplicationDelegate/applicationDidReceiveMemoryWarning:">- (void)applicationDidReceiveMemoryWarning:(UIApplication *)application</a>
     * @since Available in iOS 2.0 and later.
     */
    void didReceiveMemoryWarning(UIApplication application);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIApplicationDelegate_Protocol/Reference/Reference.html#//apple_ref/occ/intfm/UIApplicationDelegate/application:didReceiveRemoteNotification:">- (void)application:(UIApplication *)application didReceiveRemoteNotification:(NSDictionary *)userInfo</a>
     * @since Available in iOS 3.0 and later.
     */
    void didReceiveRemoteNotification(UIApplication application, NSDictionary userInfo);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIApplicationDelegate_Protocol/Reference/Reference.html#//apple_ref/occ/intfm/UIApplicationDelegate/application:didRegisterForRemoteNotificationsWithDeviceToken:">- (void)application:(UIApplication *)application didRegisterForRemoteNotificationsWithDeviceToken:(NSData *)deviceToken</a>
     * @since Available in iOS 3.0 and later.
     */
    void didRegisterForRemoteNotifications(UIApplication application, NSData deviceToken);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIApplicationDelegate_Protocol/Reference/Reference.html#//apple_ref/occ/intfm/UIApplicationDelegate/application:supportedInterfaceOrientationsForWindow:">- (NSUInteger)application:(UIApplication *)application supportedInterfaceOrientationsForWindow:(UIWindow *)window</a>
     * @since Available in iOS 6.0 and later.
     */
    int getSupportedInterfaceOrientations(UIApplication application, UIWindow window);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIApplicationDelegate_Protocol/Reference/Reference.html#//apple_ref/occ/intfm/UIApplicationDelegate/application:viewControllerWithRestorationIdentifierPath:coder:">- (UIViewController *)application:(UIApplication *)application viewControllerWithRestorationIdentifierPath:(NSArray *)identifierComponents coder:(NSCoder *)coder</a>
     * @since Available in iOS 6.0 and later.
     */
    UIViewController getViewController(UIApplication application, NSArray identifierComponents, NSCoder coder);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIApplicationDelegate_Protocol/Reference/Reference.html#//apple_ref/occ/intfm/UIApplicationDelegate/application:handleOpenURL:">- (BOOL)application:(UIApplication *)application handleOpenURL:(NSURL *)url</a>
     * @since Available in iOS 2.0 and later.
     */
    boolean handleOpenURL(UIApplication application, NSURL url);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIApplicationDelegate_Protocol/Reference/Reference.html#//apple_ref/occ/intfm/UIApplicationDelegate/application:openURL:sourceApplication:annotation:">- (BOOL)application:(UIApplication *)application openURL:(NSURL *)url sourceApplication:(NSString *)sourceApplication annotation:(id)annotation</a>
     * @since Available in iOS 4.2 and later.
     */
    boolean openURL(UIApplication application, NSURL url, String sourceApplication, NSObject annotation);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIApplicationDelegate_Protocol/Reference/Reference.html#//apple_ref/occ/intfm/UIApplicationDelegate/applicationProtectedDataDidBecomeAvailable:">- (void)applicationProtectedDataDidBecomeAvailable:(UIApplication *)application</a>
     * @since Available in iOS 4.0 and later.
     */
    void protectedDataDidBecomeAvailable(UIApplication application);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIApplicationDelegate_Protocol/Reference/Reference.html#//apple_ref/occ/intfm/UIApplicationDelegate/applicationProtectedDataWillBecomeUnavailable:">- (void)applicationProtectedDataWillBecomeUnavailable:(UIApplication *)application</a>
     * @since Available in iOS 4.0 and later.
     */
    void protectedDataWillBecomeUnavailable(UIApplication application);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIApplicationDelegate_Protocol/Reference/Reference.html#//apple_ref/occ/intfm/UIApplicationDelegate/application:shouldRestoreApplicationState:">- (BOOL)application:(UIApplication *)application shouldRestoreApplicationState:(NSCoder *)coder</a>
     * @since Available in iOS 6.0 and later.
     */
    boolean shouldRestoreApplicationState(UIApplication application, NSCoder coder);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIApplicationDelegate_Protocol/Reference/Reference.html#//apple_ref/occ/intfm/UIApplicationDelegate/application:shouldSaveApplicationState:">- (BOOL)application:(UIApplication *)application shouldSaveApplicationState:(NSCoder *)coder</a>
     * @since Available in iOS 6.0 and later.
     */
    boolean shouldSaveApplicationState(UIApplication application, NSCoder coder);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIApplicationDelegate_Protocol/Reference/Reference.html#//apple_ref/occ/intfm/UIApplicationDelegate/applicationSignificantTimeChange:">- (void)applicationSignificantTimeChange:(UIApplication *)application</a>
     * @since Available in iOS 2.0 and later.
     */
    void significantTimeChange(UIApplication application);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIApplicationDelegate_Protocol/Reference/Reference.html#//apple_ref/occ/intfm/UIApplicationDelegate/application:willChangeStatusBarFrame:">- (void)application:(UIApplication *)application willChangeStatusBarFrame:(CGRect)newStatusBarFrame</a>
     * @since Available in iOS 2.0 and later.
     */
    void willChangeStatusBarFrame(UIApplication application, CGRect newStatusBarFrame);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIApplicationDelegate_Protocol/Reference/Reference.html#//apple_ref/occ/intfm/UIApplicationDelegate/application:willChangeStatusBarOrientation:duration:">- (void)application:(UIApplication *)application willChangeStatusBarOrientation:(UIInterfaceOrientation)newStatusBarOrientation duration:(NSTimeInterval)duration</a>
     * @since Available in iOS 2.0 and later.
     */
    void willChangeStatusBarOrientation(UIApplication application, UIInterfaceOrientation newStatusBarOrientation, double duration);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIApplicationDelegate_Protocol/Reference/Reference.html#//apple_ref/occ/intfm/UIApplicationDelegate/application:willEncodeRestorableStateWithCoder:">- (void)application:(UIApplication *)application willEncodeRestorableStateWithCoder:(NSCoder *)coder</a>
     * @since Available in iOS 6.0 and later.
     */
    void willEncodeRestorableState(UIApplication application, NSCoder coder);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIApplicationDelegate_Protocol/Reference/Reference.html#//apple_ref/occ/intfm/UIApplicationDelegate/applicationWillEnterForeground:">- (void)applicationWillEnterForeground:(UIApplication *)application</a>
     * @since Available in iOS 4.0 and later.
     */
    void willEnterForeground(UIApplication application);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIApplicationDelegate_Protocol/Reference/Reference.html#//apple_ref/occ/intfm/UIApplicationDelegate/application:willFinishLaunchingWithOptions:">- (BOOL)application:(UIApplication *)application willFinishLaunchingWithOptions:(NSDictionary *)launchOptions</a>
     * @since Available in iOS 6.0 and later.
     */
    boolean willFinishLaunching(UIApplication application, NSDictionary launchOptions);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIApplicationDelegate_Protocol/Reference/Reference.html#//apple_ref/occ/intfm/UIApplicationDelegate/applicationWillResignActive:">- (void)applicationWillResignActive:(UIApplication *)application</a>
     * @since Available in iOS 2.0 and later.
     */
    void willResignActive(UIApplication application);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIApplicationDelegate_Protocol/Reference/Reference.html#//apple_ref/occ/intfm/UIApplicationDelegate/applicationWillTerminate:">- (void)applicationWillTerminate:(UIApplication *)application</a>
     * @since Available in iOS 2.0 and later.
     */
    void willTerminate(UIApplication application);
    /*</methods>*/
    /*<adapter>*/
    public static class Adapter extends NSObject implements UIApplicationDelegate {
        @NotImplemented("window") public UIWindow getWindow() { throw new UnsupportedOperationException(); }
        @NotImplemented("setWindow:") public void setWindow(UIWindow window) { throw new UnsupportedOperationException(); }
        @NotImplemented("applicationDidBecomeActive:") public void didBecomeActive(UIApplication application) { throw new UnsupportedOperationException(); }
        @NotImplemented("application:didChangeStatusBarFrame:") public void didChangStatusBarFrame(UIApplication application, CGRect oldStatusBarFrame) { throw new UnsupportedOperationException(); }
        @NotImplemented("application:didChangeStatusBarOrientation:") public void didChangStatusBarOrientation(UIApplication application, UIInterfaceOrientation oldStatusBarOrientation) { throw new UnsupportedOperationException(); }
        @NotImplemented("application:didDecodeRestorableStateWithCoder:") public void didDecodeRestorableState(UIApplication application, NSCoder coder) { throw new UnsupportedOperationException(); }
        @NotImplemented("applicationDidEnterBackground:") public void didEnterBackground(UIApplication application) { throw new UnsupportedOperationException(); }
        @NotImplemented("application:didFailToRegisterForRemoteNotificationsWithError:") public void didFailToRegisterForRemoteNotifications(UIApplication application, NSError error) { throw new UnsupportedOperationException(); }
        @NotImplemented("application:didFinishLaunchingWithOptions:") public boolean didFinishLaunching(UIApplication application, NSDictionary launchOptions) { throw new UnsupportedOperationException(); }
        @NotImplemented("applicationDidFinishLaunching:") public void didFinishLaunching(UIApplication application) { throw new UnsupportedOperationException(); }
        @NotImplemented("application:didReceiveLocalNotification:") public void didReceiveLocalNotification(UIApplication application, UILocalNotification notification) { throw new UnsupportedOperationException(); }
        @NotImplemented("applicationDidReceiveMemoryWarning:") public void didReceiveMemoryWarning(UIApplication application) { throw new UnsupportedOperationException(); }
        @NotImplemented("application:didReceiveRemoteNotification:") public void didReceiveRemoteNotification(UIApplication application, NSDictionary userInfo) { throw new UnsupportedOperationException(); }
        @NotImplemented("application:didRegisterForRemoteNotificationsWithDeviceToken:") public void didRegisterForRemoteNotifications(UIApplication application, NSData deviceToken) { throw new UnsupportedOperationException(); }
        @NotImplemented("application:supportedInterfaceOrientationsForWindow:") public int getSupportedInterfaceOrientations(UIApplication application, UIWindow window) { throw new UnsupportedOperationException(); }
        @NotImplemented("application:viewControllerWithRestorationIdentifierPath:coder:") public UIViewController getViewController(UIApplication application, NSArray identifierComponents, NSCoder coder) { throw new UnsupportedOperationException(); }
        @NotImplemented("application:handleOpenURL:") public boolean handleOpenURL(UIApplication application, NSURL url) { throw new UnsupportedOperationException(); }
        @NotImplemented("application:openURL:sourceApplication:annotation:") public boolean openURL(UIApplication application, NSURL url, String sourceApplication, NSObject annotation) { throw new UnsupportedOperationException(); }
        @NotImplemented("applicationProtectedDataDidBecomeAvailable:") public void protectedDataDidBecomeAvailable(UIApplication application) { throw new UnsupportedOperationException(); }
        @NotImplemented("applicationProtectedDataWillBecomeUnavailable:") public void protectedDataWillBecomeUnavailable(UIApplication application) { throw new UnsupportedOperationException(); }
        @NotImplemented("application:shouldRestoreApplicationState:") public boolean shouldRestoreApplicationState(UIApplication application, NSCoder coder) { throw new UnsupportedOperationException(); }
        @NotImplemented("application:shouldSaveApplicationState:") public boolean shouldSaveApplicationState(UIApplication application, NSCoder coder) { throw new UnsupportedOperationException(); }
        @NotImplemented("applicationSignificantTimeChange:") public void significantTimeChange(UIApplication application) { throw new UnsupportedOperationException(); }
        @NotImplemented("application:willChangeStatusBarFrame:") public void willChangeStatusBarFrame(UIApplication application, CGRect newStatusBarFrame) { throw new UnsupportedOperationException(); }
        @NotImplemented("application:willChangeStatusBarOrientation:duration:") public void willChangeStatusBarOrientation(UIApplication application, UIInterfaceOrientation newStatusBarOrientation, double duration) { throw new UnsupportedOperationException(); }
        @NotImplemented("application:willEncodeRestorableStateWithCoder:") public void willEncodeRestorableState(UIApplication application, NSCoder coder) { throw new UnsupportedOperationException(); }
        @NotImplemented("applicationWillEnterForeground:") public void willEnterForeground(UIApplication application) { throw new UnsupportedOperationException(); }
        @NotImplemented("application:willFinishLaunchingWithOptions:") public boolean willFinishLaunching(UIApplication application, NSDictionary launchOptions) { throw new UnsupportedOperationException(); }
        @NotImplemented("applicationWillResignActive:") public void willResignActive(UIApplication application) { throw new UnsupportedOperationException(); }
        @NotImplemented("applicationWillTerminate:") public void willTerminate(UIApplication application) { throw new UnsupportedOperationException(); }
    }
    /*</adapter>*/
    /*<callbacks>*/
    static class Callbacks {
        @Callback @BindSelector("window") public static UIWindow getWindow(UIApplicationDelegate __self__, Selector __cmd__) { return __self__.getWindow(); }
        @Callback @BindSelector("setWindow:") public static void setWindow(UIApplicationDelegate __self__, Selector __cmd__, UIWindow window) { __self__.setWindow(window); }
        @Callback @BindSelector("applicationDidBecomeActive:") public static void didBecomeActive(UIApplicationDelegate __self__, Selector __cmd__, UIApplication application) { __self__.didBecomeActive(application); }
        @Callback @BindSelector("application:didChangeStatusBarFrame:") public static void didChangStatusBarFrame(UIApplicationDelegate __self__, Selector __cmd__, UIApplication application, @ByVal CGRect oldStatusBarFrame) { __self__.didChangStatusBarFrame(application, oldStatusBarFrame); }
        @Callback @BindSelector("application:didChangeStatusBarOrientation:") public static void didChangStatusBarOrientation(UIApplicationDelegate __self__, Selector __cmd__, UIApplication application, UIInterfaceOrientation oldStatusBarOrientation) { __self__.didChangStatusBarOrientation(application, oldStatusBarOrientation); }
        @Callback @BindSelector("application:didDecodeRestorableStateWithCoder:") public static void didDecodeRestorableState(UIApplicationDelegate __self__, Selector __cmd__, UIApplication application, NSCoder coder) { __self__.didDecodeRestorableState(application, coder); }
        @Callback @BindSelector("applicationDidEnterBackground:") public static void didEnterBackground(UIApplicationDelegate __self__, Selector __cmd__, UIApplication application) { __self__.didEnterBackground(application); }
        @Callback @BindSelector("application:didFailToRegisterForRemoteNotificationsWithError:") public static void didFailToRegisterForRemoteNotifications(UIApplicationDelegate __self__, Selector __cmd__, UIApplication application, NSError error) { __self__.didFailToRegisterForRemoteNotifications(application, error); }
        @Callback @BindSelector("application:didFinishLaunchingWithOptions:") public static boolean didFinishLaunching(UIApplicationDelegate __self__, Selector __cmd__, UIApplication application, NSDictionary launchOptions) { return __self__.didFinishLaunching(application, launchOptions); }
        @Callback @BindSelector("applicationDidFinishLaunching:") public static void didFinishLaunching(UIApplicationDelegate __self__, Selector __cmd__, UIApplication application) { __self__.didFinishLaunching(application); }
        @Callback @BindSelector("application:didReceiveLocalNotification:") public static void didReceiveLocalNotification(UIApplicationDelegate __self__, Selector __cmd__, UIApplication application, UILocalNotification notification) { __self__.didReceiveLocalNotification(application, notification); }
        @Callback @BindSelector("applicationDidReceiveMemoryWarning:") public static void didReceiveMemoryWarning(UIApplicationDelegate __self__, Selector __cmd__, UIApplication application) { __self__.didReceiveMemoryWarning(application); }
        @Callback @BindSelector("application:didReceiveRemoteNotification:") public static void didReceiveRemoteNotification(UIApplicationDelegate __self__, Selector __cmd__, UIApplication application, NSDictionary userInfo) { __self__.didReceiveRemoteNotification(application, userInfo); }
        @Callback @BindSelector("application:didRegisterForRemoteNotificationsWithDeviceToken:") public static void didRegisterForRemoteNotifications(UIApplicationDelegate __self__, Selector __cmd__, UIApplication application, NSData deviceToken) { __self__.didRegisterForRemoteNotifications(application, deviceToken); }
        @Callback @BindSelector("application:supportedInterfaceOrientationsForWindow:") public static int getSupportedInterfaceOrientations(UIApplicationDelegate __self__, Selector __cmd__, UIApplication application, UIWindow window) { return __self__.getSupportedInterfaceOrientations(application, window); }
        @Callback @BindSelector("application:viewControllerWithRestorationIdentifierPath:coder:") public static UIViewController getViewController(UIApplicationDelegate __self__, Selector __cmd__, UIApplication application, NSArray identifierComponents, NSCoder coder) { return __self__.getViewController(application, identifierComponents, coder); }
        @Callback @BindSelector("application:handleOpenURL:") public static boolean handleOpenURL(UIApplicationDelegate __self__, Selector __cmd__, UIApplication application, NSURL url) { return __self__.handleOpenURL(application, url); }
        @Callback @BindSelector("application:openURL:sourceApplication:annotation:") public static boolean openURL(UIApplicationDelegate __self__, Selector __cmd__, UIApplication application, NSURL url, String sourceApplication, NSObject annotation) { return __self__.openURL(application, url, sourceApplication, annotation); }
        @Callback @BindSelector("applicationProtectedDataDidBecomeAvailable:") public static void protectedDataDidBecomeAvailable(UIApplicationDelegate __self__, Selector __cmd__, UIApplication application) { __self__.protectedDataDidBecomeAvailable(application); }
        @Callback @BindSelector("applicationProtectedDataWillBecomeUnavailable:") public static void protectedDataWillBecomeUnavailable(UIApplicationDelegate __self__, Selector __cmd__, UIApplication application) { __self__.protectedDataWillBecomeUnavailable(application); }
        @Callback @BindSelector("application:shouldRestoreApplicationState:") public static boolean shouldRestoreApplicationState(UIApplicationDelegate __self__, Selector __cmd__, UIApplication application, NSCoder coder) { return __self__.shouldRestoreApplicationState(application, coder); }
        @Callback @BindSelector("application:shouldSaveApplicationState:") public static boolean shouldSaveApplicationState(UIApplicationDelegate __self__, Selector __cmd__, UIApplication application, NSCoder coder) { return __self__.shouldSaveApplicationState(application, coder); }
        @Callback @BindSelector("applicationSignificantTimeChange:") public static void significantTimeChange(UIApplicationDelegate __self__, Selector __cmd__, UIApplication application) { __self__.significantTimeChange(application); }
        @Callback @BindSelector("application:willChangeStatusBarFrame:") public static void willChangeStatusBarFrame(UIApplicationDelegate __self__, Selector __cmd__, UIApplication application, @ByVal CGRect newStatusBarFrame) { __self__.willChangeStatusBarFrame(application, newStatusBarFrame); }
        @Callback @BindSelector("application:willChangeStatusBarOrientation:duration:") public static void willChangeStatusBarOrientation(UIApplicationDelegate __self__, Selector __cmd__, UIApplication application, UIInterfaceOrientation newStatusBarOrientation, double duration) { __self__.willChangeStatusBarOrientation(application, newStatusBarOrientation, duration); }
        @Callback @BindSelector("application:willEncodeRestorableStateWithCoder:") public static void willEncodeRestorableState(UIApplicationDelegate __self__, Selector __cmd__, UIApplication application, NSCoder coder) { __self__.willEncodeRestorableState(application, coder); }
        @Callback @BindSelector("applicationWillEnterForeground:") public static void willEnterForeground(UIApplicationDelegate __self__, Selector __cmd__, UIApplication application) { __self__.willEnterForeground(application); }
        @Callback @BindSelector("application:willFinishLaunchingWithOptions:") public static boolean willFinishLaunching(UIApplicationDelegate __self__, Selector __cmd__, UIApplication application, NSDictionary launchOptions) { return __self__.willFinishLaunching(application, launchOptions); }
        @Callback @BindSelector("applicationWillResignActive:") public static void willResignActive(UIApplicationDelegate __self__, Selector __cmd__, UIApplication application) { __self__.willResignActive(application); }
        @Callback @BindSelector("applicationWillTerminate:") public static void willTerminate(UIApplicationDelegate __self__, Selector __cmd__, UIApplication application) { __self__.willTerminate(application); }
    }
    /*</callbacks>*/

}
