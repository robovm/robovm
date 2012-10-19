/*
 * Copyright (C) 2012 RoboVM
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
import org.robovm.objc.bind.*;
import org.robovm.objc.block.*;
import org.robovm.rt.bro.annotation.*;
import org.robovm.rt.bro.ptr.*;
/*</imports>*/

/**
 *
 *
 * <div class="javadoc">
 *   @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIApplicationDelegate_Protocol/Reference/Reference.html">UIApplicationDelegate Protocol Reference</a>
 *   @since Available in iOS 2.0 and later.
 * </div>
 */
public interface /*<name>*/ UIApplicationDelegate /*</name>*/ /*<implements>*/ extends ObjCProtocol /*</implements>*/ {

    /*<properties>*/
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIApplicationDelegate_Protocol/Reference/Reference.html#//apple_ref/occ/intfp/UIApplicationDelegate/window">@property(nonatomic, retain) UIWindow *window</a>
     * @since Available in iOS 5.0 and later.
     */
    @Bind("window") UIWindow getWindow();
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIApplicationDelegate_Protocol/Reference/Reference.html#//apple_ref/occ/intfp/UIApplicationDelegate/window">@property(nonatomic, retain) UIWindow *window</a>
     * @since Available in iOS 5.0 and later.
     */
    @Bind("setWindow:") void setWindow(UIWindow v);
    /*</properties>*/
    /*<methods>*/
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIApplicationDelegate_Protocol/Reference/Reference.html#//apple_ref/occ/intfm/UIApplicationDelegate/applicationDidBecomeActive:">- (void)applicationDidBecomeActive:(UIApplication *)application</a>
     * @since Available in iOS 2.0 and later.
     */
    void didBecomeActive(UIApplication application);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIApplicationDelegate_Protocol/Reference/Reference.html#//apple_ref/occ/intfm/UIApplicationDelegate/application:didChangeStatusBarFrame:">- (void)application:(UIApplication *)application didChangeStatusBarFrame:(CGRect)oldStatusBarFrame</a>
     * @since Available in iOS 2.0 and later.
     */
    void didChangStatusBarFrame(UIApplication application, CGRect oldStatusBarFrame);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIApplicationDelegate_Protocol/Reference/Reference.html#//apple_ref/occ/intfm/UIApplicationDelegate/application:didChangeStatusBarOrientation:">- (void)application:(UIApplication *)application didChangeStatusBarOrientation:(UIInterfaceOrientation)oldStatusBarOrientation</a>
     * @since Available in iOS 2.0 and later.
     */
    void didChangStatusBarOrientation(UIApplication application, UIInterfaceOrientation oldStatusBarOrientation);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIApplicationDelegate_Protocol/Reference/Reference.html#//apple_ref/occ/intfm/UIApplicationDelegate/application:didDecodeRestorableStateWithCoder:">- (void)application:(UIApplication *)application didDecodeRestorableStateWithCoder:(NSCoder *)coder</a>
     * @since Available in iOS 6.0 and later.
     */
    void didDecodeRestorableState(UIApplication application, NSCoder coder);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIApplicationDelegate_Protocol/Reference/Reference.html#//apple_ref/occ/intfm/UIApplicationDelegate/applicationDidEnterBackground:">- (void)applicationDidEnterBackground:(UIApplication *)application</a>
     * @since Available in iOS 4.0 and later.
     */
    void didEnterBackground(UIApplication application);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIApplicationDelegate_Protocol/Reference/Reference.html#//apple_ref/occ/intfm/UIApplicationDelegate/application:didFailToRegisterForRemoteNotificationsWithError:">- (void)application:(UIApplication *)application didFailToRegisterForRemoteNotificationsWithError:(NSError *)error</a>
     * @since Available in iOS 3.0 and later.
     */
    void didFailToRegisterForRemoteNotifications(UIApplication application, NSError error);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIApplicationDelegate_Protocol/Reference/Reference.html#//apple_ref/occ/intfm/UIApplicationDelegate/application:didFinishLaunchingWithOptions:">- (BOOL)application:(UIApplication *)application didFinishLaunchingWithOptions:(NSDictionary *)launchOptions</a>
     * @since Available in iOS 3.0 and later.
     */
    boolean didFinishLaunching(UIApplication application, NSDictionary launchOptions);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIApplicationDelegate_Protocol/Reference/Reference.html#//apple_ref/occ/intfm/UIApplicationDelegate/applicationDidFinishLaunching:">- (void)applicationDidFinishLaunching:(UIApplication *)application</a>
     * @since Available in iOS 2.0 and later.
     */
    void didFinishLaunching(UIApplication application);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIApplicationDelegate_Protocol/Reference/Reference.html#//apple_ref/occ/intfm/UIApplicationDelegate/application:didReceiveLocalNotification:">- (void)application:(UIApplication *)application didReceiveLocalNotification:(UILocalNotification *)notification</a>
     * @since Available in iOS 4.0 and later.
     */
    void didReceiveLocalNotification(UIApplication application, UILocalNotification notification);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIApplicationDelegate_Protocol/Reference/Reference.html#//apple_ref/occ/intfm/UIApplicationDelegate/applicationDidReceiveMemoryWarning:">- (void)applicationDidReceiveMemoryWarning:(UIApplication *)application</a>
     * @since Available in iOS 2.0 and later.
     */
    void didReceiveMemoryWarning(UIApplication application);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIApplicationDelegate_Protocol/Reference/Reference.html#//apple_ref/occ/intfm/UIApplicationDelegate/application:didReceiveRemoteNotification:">- (void)application:(UIApplication *)application didReceiveRemoteNotification:(NSDictionary *)userInfo</a>
     * @since Available in iOS 3.0 and later.
     */
    void didReceiveRemoteNotification(UIApplication application, NSDictionary userInfo);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIApplicationDelegate_Protocol/Reference/Reference.html#//apple_ref/occ/intfm/UIApplicationDelegate/application:didRegisterForRemoteNotificationsWithDeviceToken:">- (void)application:(UIApplication *)application didRegisterForRemoteNotificationsWithDeviceToken:(NSData *)deviceToken</a>
     * @since Available in iOS 3.0 and later.
     */
    void didRegisterForRemoteNotifications(UIApplication application, NSData deviceToken);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIApplicationDelegate_Protocol/Reference/Reference.html#//apple_ref/occ/intfm/UIApplicationDelegate/application:supportedInterfaceOrientationsForWindow:">- (NSUInteger)application:(UIApplication *)application supportedInterfaceOrientationsForWindow:(UIWindow *)window</a>
     * @since Available in iOS 6.0 and later.
     */
    int getSupportedInterfaceOrientations(UIApplication application, UIWindow window);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIApplicationDelegate_Protocol/Reference/Reference.html#//apple_ref/occ/intfm/UIApplicationDelegate/application:viewControllerWithRestorationIdentifierPath:coder:">- (UIViewController *)application:(UIApplication *)application viewControllerWithRestorationIdentifierPath:(NSArray *)identifierComponents coder:(NSCoder *)coder</a>
     * @since Available in iOS 6.0 and later.
     */
    UIViewController getViewController(UIApplication application, NSArray identifierComponents, NSCoder coder);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIApplicationDelegate_Protocol/Reference/Reference.html#//apple_ref/occ/intfm/UIApplicationDelegate/application:handleOpenURL:">- (BOOL)application:(UIApplication *)application handleOpenURL:(NSURL *)url</a>
     * @since Available in iOS 2.0 and later.
     */
    boolean handleOpenURL(UIApplication application, NSURL url);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIApplicationDelegate_Protocol/Reference/Reference.html#//apple_ref/occ/intfm/UIApplicationDelegate/application:openURL:sourceApplication:annotation:">- (BOOL)application:(UIApplication *)application openURL:(NSURL *)url sourceApplication:(NSString *)sourceApplication annotation:(id)annotation</a>
     * @since Available in iOS 4.2 and later.
     */
    boolean openURL(UIApplication application, NSURL url, String sourceApplication, NSObject annotation);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIApplicationDelegate_Protocol/Reference/Reference.html#//apple_ref/occ/intfm/UIApplicationDelegate/applicationProtectedDataDidBecomeAvailable:">- (void)applicationProtectedDataDidBecomeAvailable:(UIApplication *)application</a>
     * @since Available in iOS 4.0 and later.
     */
    void protectedDataDidBecomeAvailable(UIApplication application);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIApplicationDelegate_Protocol/Reference/Reference.html#//apple_ref/occ/intfm/UIApplicationDelegate/applicationProtectedDataWillBecomeUnavailable:">- (void)applicationProtectedDataWillBecomeUnavailable:(UIApplication *)application</a>
     * @since Available in iOS 4.0 and later.
     */
    void protectedDataWillBecomeUnavailable(UIApplication application);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIApplicationDelegate_Protocol/Reference/Reference.html#//apple_ref/occ/intfm/UIApplicationDelegate/application:shouldRestoreApplicationState:">- (BOOL)application:(UIApplication *)application shouldRestoreApplicationState:(NSCoder *)coder</a>
     * @since Available in iOS 6.0 and later.
     */
    boolean shouldRestoreApplicationState(UIApplication application, NSCoder coder);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIApplicationDelegate_Protocol/Reference/Reference.html#//apple_ref/occ/intfm/UIApplicationDelegate/application:shouldSaveApplicationState:">- (BOOL)application:(UIApplication *)application shouldSaveApplicationState:(NSCoder *)coder</a>
     * @since Available in iOS 6.0 and later.
     */
    boolean shouldSaveApplicationState(UIApplication application, NSCoder coder);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIApplicationDelegate_Protocol/Reference/Reference.html#//apple_ref/occ/intfm/UIApplicationDelegate/applicationSignificantTimeChange:">- (void)applicationSignificantTimeChange:(UIApplication *)application</a>
     * @since Available in iOS 2.0 and later.
     */
    void significantTimeChange(UIApplication application);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIApplicationDelegate_Protocol/Reference/Reference.html#//apple_ref/occ/intfm/UIApplicationDelegate/application:willChangeStatusBarFrame:">- (void)application:(UIApplication *)application willChangeStatusBarFrame:(CGRect)newStatusBarFrame</a>
     * @since Available in iOS 2.0 and later.
     */
    void willChangeStatusBarFrame(UIApplication application, CGRect newStatusBarFrame);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIApplicationDelegate_Protocol/Reference/Reference.html#//apple_ref/occ/intfm/UIApplicationDelegate/application:willChangeStatusBarOrientation:duration:">- (void)application:(UIApplication *)application willChangeStatusBarOrientation:(UIInterfaceOrientation)newStatusBarOrientation duration:(NSTimeInterval)duration</a>
     * @since Available in iOS 2.0 and later.
     */
    void willChangeStatusBarOrientation(UIApplication application, UIInterfaceOrientation newStatusBarOrientation, double duration);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIApplicationDelegate_Protocol/Reference/Reference.html#//apple_ref/occ/intfm/UIApplicationDelegate/application:willEncodeRestorableStateWithCoder:">- (void)application:(UIApplication *)application willEncodeRestorableStateWithCoder:(NSCoder *)coder</a>
     * @since Available in iOS 6.0 and later.
     */
    void willEncodeRestorableState(UIApplication application, NSCoder coder);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIApplicationDelegate_Protocol/Reference/Reference.html#//apple_ref/occ/intfm/UIApplicationDelegate/applicationWillEnterForeground:">- (void)applicationWillEnterForeground:(UIApplication *)application</a>
     * @since Available in iOS 4.0 and later.
     */
    void willEnterForeground(UIApplication application);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIApplicationDelegate_Protocol/Reference/Reference.html#//apple_ref/occ/intfm/UIApplicationDelegate/application:willFinishLaunchingWithOptions:">- (BOOL)application:(UIApplication *)application willFinishLaunchingWithOptions:(NSDictionary *)launchOptions</a>
     * @since Available in iOS 6.0 and later.
     */
    boolean willFinishLaunching(UIApplication application, NSDictionary launchOptions);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIApplicationDelegate_Protocol/Reference/Reference.html#//apple_ref/occ/intfm/UIApplicationDelegate/applicationWillResignActive:">- (void)applicationWillResignActive:(UIApplication *)application</a>
     * @since Available in iOS 2.0 and later.
     */
    void willResignActive(UIApplication application);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIApplicationDelegate_Protocol/Reference/Reference.html#//apple_ref/occ/intfm/UIApplicationDelegate/applicationWillTerminate:">- (void)applicationWillTerminate:(UIApplication *)application</a>
     * @since Available in iOS 2.0 and later.
     */
    void willTerminate(UIApplication application);
    /*</methods>*/

}
