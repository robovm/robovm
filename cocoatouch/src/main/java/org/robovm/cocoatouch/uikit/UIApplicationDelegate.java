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
@Protocol
public interface /*<name>*/ UIApplicationDelegate /*</name>*/ /*<implements>*/ /*</implements>*/ {

    /*<properties>*/
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIApplicationDelegate_Protocol/Reference/Reference.html#//apple_ref/occ/intfp/UIApplicationDelegate/window">@property(nonatomic, retain) UIWindow *window</a>
     * @since Available in iOS 5.0 and later.
     */
    @Bind("window") @Type("UIWindow *") UIWindow getWindow();
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIApplicationDelegate_Protocol/Reference/Reference.html#//apple_ref/occ/intfp/UIApplicationDelegate/window">@property(nonatomic, retain) UIWindow *window</a>
     * @since Available in iOS 5.0 and later.
     */
    @Bind("setWindow:") void setWindow(@Type("UIWindow *") UIWindow v);
    /*</properties>*/
    /*<methods>*/
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIApplicationDelegate_Protocol/Reference/Reference.html#//apple_ref/occ/intfm/UIApplicationDelegate/applicationDidBecomeActive:">- (void)applicationDidBecomeActive:(UIApplication *)application</a>
     * @since Available in iOS 2.0 and later.
     */
    @Bind("applicationDidBecomeActive:") @Type("void") void didBecomeActive(@Type("UIApplication *") UIApplication application);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIApplicationDelegate_Protocol/Reference/Reference.html#//apple_ref/occ/intfm/UIApplicationDelegate/application:didChangeStatusBarFrame:">- (void)application:(UIApplication *)application didChangeStatusBarFrame:(CGRect)oldStatusBarFrame</a>
     * @since Available in iOS 2.0 and later.
     */
    @Bind("application:didChangeStatusBarFrame:") @Type("void") void didChangStatusBarFrame(@Type("UIApplication *") UIApplication application, @Type("CGRect") CGRect oldStatusBarFrame);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIApplicationDelegate_Protocol/Reference/Reference.html#//apple_ref/occ/intfm/UIApplicationDelegate/application:didChangeStatusBarOrientation:">- (void)application:(UIApplication *)application didChangeStatusBarOrientation:(UIInterfaceOrientation)oldStatusBarOrientation</a>
     * @since Available in iOS 2.0 and later.
     */
    @Bind("application:didChangeStatusBarOrientation:") @Type("void") void didChangStatusBarOrientation(@Type("UIApplication *") UIApplication application, @Type("UIInterfaceOrientation") UIInterfaceOrientation oldStatusBarOrientation);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIApplicationDelegate_Protocol/Reference/Reference.html#//apple_ref/occ/intfm/UIApplicationDelegate/application:didDecodeRestorableStateWithCoder:">- (void)application:(UIApplication *)application didDecodeRestorableStateWithCoder:(NSCoder *)coder</a>
     * @since Available in iOS 6.0 and later.
     */
    @Bind("application:didDecodeRestorableStateWithCoder:") @Type("void") void didDecodeRestorableState(@Type("UIApplication *") UIApplication application, @Type("NSCoder *") NSCoder coder);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIApplicationDelegate_Protocol/Reference/Reference.html#//apple_ref/occ/intfm/UIApplicationDelegate/applicationDidEnterBackground:">- (void)applicationDidEnterBackground:(UIApplication *)application</a>
     * @since Available in iOS 4.0 and later.
     */
    @Bind("applicationDidEnterBackground:") @Type("void") void didEnterBackground(@Type("UIApplication *") UIApplication application);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIApplicationDelegate_Protocol/Reference/Reference.html#//apple_ref/occ/intfm/UIApplicationDelegate/application:didFailToRegisterForRemoteNotificationsWithError:">- (void)application:(UIApplication *)application didFailToRegisterForRemoteNotificationsWithError:(NSError *)error</a>
     * @since Available in iOS 3.0 and later.
     */
    @Bind("application:didFailToRegisterForRemoteNotificationsWithError:") @Type("void") void didFailToRegisterForRemoteNotifications(@Type("UIApplication *") UIApplication application, @Type("NSError *") NSError error);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIApplicationDelegate_Protocol/Reference/Reference.html#//apple_ref/occ/intfm/UIApplicationDelegate/application:didFinishLaunchingWithOptions:">- (BOOL)application:(UIApplication *)application didFinishLaunchingWithOptions:(NSDictionary *)launchOptions</a>
     * @since Available in iOS 3.0 and later.
     */
    @Bind("application:didFinishLaunchingWithOptions:") @Type("BOOL") boolean didFinishLaunching(@Type("UIApplication *") UIApplication application, @Type("NSDictionary *") NSDictionary launchOptions);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIApplicationDelegate_Protocol/Reference/Reference.html#//apple_ref/occ/intfm/UIApplicationDelegate/applicationDidFinishLaunching:">- (void)applicationDidFinishLaunching:(UIApplication *)application</a>
     * @since Available in iOS 2.0 and later.
     */
    @Bind("applicationDidFinishLaunching:") @Type("void") void didFinishLaunching(@Type("UIApplication *") UIApplication application);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIApplicationDelegate_Protocol/Reference/Reference.html#//apple_ref/occ/intfm/UIApplicationDelegate/application:didReceiveLocalNotification:">- (void)application:(UIApplication *)application didReceiveLocalNotification:(UILocalNotification *)notification</a>
     * @since Available in iOS 4.0 and later.
     */
    @Bind("application:didReceiveLocalNotification:") @Type("void") void didReceiveLocalNotification(@Type("UIApplication *") UIApplication application, @Type("UILocalNotification *") UILocalNotification notification);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIApplicationDelegate_Protocol/Reference/Reference.html#//apple_ref/occ/intfm/UIApplicationDelegate/applicationDidReceiveMemoryWarning:">- (void)applicationDidReceiveMemoryWarning:(UIApplication *)application</a>
     * @since Available in iOS 2.0 and later.
     */
    @Bind("applicationDidReceiveMemoryWarning:") @Type("void") void didReceiveMemoryWarning(@Type("UIApplication *") UIApplication application);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIApplicationDelegate_Protocol/Reference/Reference.html#//apple_ref/occ/intfm/UIApplicationDelegate/application:didReceiveRemoteNotification:">- (void)application:(UIApplication *)application didReceiveRemoteNotification:(NSDictionary *)userInfo</a>
     * @since Available in iOS 3.0 and later.
     */
    @Bind("application:didReceiveRemoteNotification:") @Type("void") void didReceiveRemoteNotification(@Type("UIApplication *") UIApplication application, @Type("NSDictionary *") NSDictionary userInfo);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIApplicationDelegate_Protocol/Reference/Reference.html#//apple_ref/occ/intfm/UIApplicationDelegate/application:didRegisterForRemoteNotificationsWithDeviceToken:">- (void)application:(UIApplication *)application didRegisterForRemoteNotificationsWithDeviceToken:(NSData *)deviceToken</a>
     * @since Available in iOS 3.0 and later.
     */
    @Bind("application:didRegisterForRemoteNotificationsWithDeviceToken:") @Type("void") void didRegisterForRemoteNotifications(@Type("UIApplication *") UIApplication application, @Type("NSData *") NSData deviceToken);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIApplicationDelegate_Protocol/Reference/Reference.html#//apple_ref/occ/intfm/UIApplicationDelegate/application:supportedInterfaceOrientationsForWindow:">- (NSUInteger)application:(UIApplication *)application supportedInterfaceOrientationsForWindow:(UIWindow *)window</a>
     * @since Available in iOS 6.0 and later.
     */
    @Bind("application:supportedInterfaceOrientationsForWindow:") @Type("NSUInteger") int getSupportedInterfaceOrientations(@Type("UIApplication *") UIApplication application, @Type("UIWindow *") UIWindow window);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIApplicationDelegate_Protocol/Reference/Reference.html#//apple_ref/occ/intfm/UIApplicationDelegate/application:viewControllerWithRestorationIdentifierPath:coder:">- (UIViewController *)application:(UIApplication *)application viewControllerWithRestorationIdentifierPath:(NSArray *)identifierComponents coder:(NSCoder *)coder</a>
     * @since Available in iOS 6.0 and later.
     */
    @Bind("application:viewControllerWithRestorationIdentifierPath:coder:") @Type("UIViewController *") UIViewController getViewController(@Type("UIApplication *") UIApplication application, @Type("NSArray *") NSArray identifierComponents, @Type("NSCoder *") NSCoder coder);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIApplicationDelegate_Protocol/Reference/Reference.html#//apple_ref/occ/intfm/UIApplicationDelegate/application:handleOpenURL:">- (BOOL)application:(UIApplication *)application handleOpenURL:(NSURL *)url</a>
     * @since Available in iOS 2.0 and later.
     */
    @Bind("application:handleOpenURL:") @Type("BOOL") boolean handleOpenURL(@Type("UIApplication *") UIApplication application, @Type("NSURL *") NSURL url);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIApplicationDelegate_Protocol/Reference/Reference.html#//apple_ref/occ/intfm/UIApplicationDelegate/application:openURL:sourceApplication:annotation:">- (BOOL)application:(UIApplication *)application openURL:(NSURL *)url sourceApplication:(NSString *)sourceApplication annotation:(id)annotation</a>
     * @since Available in iOS 4.2 and later.
     */
    @Bind("application:openURL:sourceApplication:annotation:") @Type("BOOL") boolean openURL(@Type("UIApplication *") UIApplication application, @Type("NSURL *") NSURL url, @Type("NSString *") String sourceApplication, @Type("id") NSObject annotation);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIApplicationDelegate_Protocol/Reference/Reference.html#//apple_ref/occ/intfm/UIApplicationDelegate/applicationProtectedDataDidBecomeAvailable:">- (void)applicationProtectedDataDidBecomeAvailable:(UIApplication *)application</a>
     * @since Available in iOS 4.0 and later.
     */
    @Bind("applicationProtectedDataDidBecomeAvailable:") @Type("void") void protectedDataDidBecomeAvailable(@Type("UIApplication *") UIApplication application);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIApplicationDelegate_Protocol/Reference/Reference.html#//apple_ref/occ/intfm/UIApplicationDelegate/applicationProtectedDataWillBecomeUnavailable:">- (void)applicationProtectedDataWillBecomeUnavailable:(UIApplication *)application</a>
     * @since Available in iOS 4.0 and later.
     */
    @Bind("applicationProtectedDataWillBecomeUnavailable:") @Type("void") void protectedDataWillBecomeUnavailable(@Type("UIApplication *") UIApplication application);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIApplicationDelegate_Protocol/Reference/Reference.html#//apple_ref/occ/intfm/UIApplicationDelegate/application:shouldRestoreApplicationState:">- (BOOL)application:(UIApplication *)application shouldRestoreApplicationState:(NSCoder *)coder</a>
     * @since Available in iOS 6.0 and later.
     */
    @Bind("application:shouldRestoreApplicationState:") @Type("BOOL") boolean shouldRestoreApplicationState(@Type("UIApplication *") UIApplication application, @Type("NSCoder *") NSCoder coder);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIApplicationDelegate_Protocol/Reference/Reference.html#//apple_ref/occ/intfm/UIApplicationDelegate/application:shouldSaveApplicationState:">- (BOOL)application:(UIApplication *)application shouldSaveApplicationState:(NSCoder *)coder</a>
     * @since Available in iOS 6.0 and later.
     */
    @Bind("application:shouldSaveApplicationState:") @Type("BOOL") boolean shouldSaveApplicationState(@Type("UIApplication *") UIApplication application, @Type("NSCoder *") NSCoder coder);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIApplicationDelegate_Protocol/Reference/Reference.html#//apple_ref/occ/intfm/UIApplicationDelegate/applicationSignificantTimeChange:">- (void)applicationSignificantTimeChange:(UIApplication *)application</a>
     * @since Available in iOS 2.0 and later.
     */
    @Bind("applicationSignificantTimeChange:") @Type("void") void significantTimeChange(@Type("UIApplication *") UIApplication application);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIApplicationDelegate_Protocol/Reference/Reference.html#//apple_ref/occ/intfm/UIApplicationDelegate/application:willChangeStatusBarFrame:">- (void)application:(UIApplication *)application willChangeStatusBarFrame:(CGRect)newStatusBarFrame</a>
     * @since Available in iOS 2.0 and later.
     */
    @Bind("application:willChangeStatusBarFrame:") @Type("void") void willChangeStatusBarFrame(@Type("UIApplication *") UIApplication application, @Type("CGRect") CGRect newStatusBarFrame);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIApplicationDelegate_Protocol/Reference/Reference.html#//apple_ref/occ/intfm/UIApplicationDelegate/application:willChangeStatusBarOrientation:duration:">- (void)application:(UIApplication *)application willChangeStatusBarOrientation:(UIInterfaceOrientation)newStatusBarOrientation duration:(NSTimeInterval)duration</a>
     * @since Available in iOS 2.0 and later.
     */
    @Bind("application:willChangeStatusBarOrientation:duration:") @Type("void") void willChangeStatusBarOrientation(@Type("UIApplication *") UIApplication application, @Type("UIInterfaceOrientation") UIInterfaceOrientation newStatusBarOrientation, @Type("NSTimeInterval") double duration);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIApplicationDelegate_Protocol/Reference/Reference.html#//apple_ref/occ/intfm/UIApplicationDelegate/application:willEncodeRestorableStateWithCoder:">- (void)application:(UIApplication *)application willEncodeRestorableStateWithCoder:(NSCoder *)coder</a>
     * @since Available in iOS 6.0 and later.
     */
    @Bind("application:willEncodeRestorableStateWithCoder:") @Type("void") void willEncodeRestorableState(@Type("UIApplication *") UIApplication application, @Type("NSCoder *") NSCoder coder);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIApplicationDelegate_Protocol/Reference/Reference.html#//apple_ref/occ/intfm/UIApplicationDelegate/applicationWillEnterForeground:">- (void)applicationWillEnterForeground:(UIApplication *)application</a>
     * @since Available in iOS 4.0 and later.
     */
    @Bind("applicationWillEnterForeground:") @Type("void") void willEnterForeground(@Type("UIApplication *") UIApplication application);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIApplicationDelegate_Protocol/Reference/Reference.html#//apple_ref/occ/intfm/UIApplicationDelegate/application:willFinishLaunchingWithOptions:">- (BOOL)application:(UIApplication *)application willFinishLaunchingWithOptions:(NSDictionary *)launchOptions</a>
     * @since Available in iOS 6.0 and later.
     */
    @Bind("application:willFinishLaunchingWithOptions:") @Type("BOOL") boolean willFinishLaunching(@Type("UIApplication *") UIApplication application, @Type("NSDictionary *") NSDictionary launchOptions);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIApplicationDelegate_Protocol/Reference/Reference.html#//apple_ref/occ/intfm/UIApplicationDelegate/applicationWillResignActive:">- (void)applicationWillResignActive:(UIApplication *)application</a>
     * @since Available in iOS 2.0 and later.
     */
    @Bind("applicationWillResignActive:") @Type("void") void willResignActive(@Type("UIApplication *") UIApplication application);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIApplicationDelegate_Protocol/Reference/Reference.html#//apple_ref/occ/intfm/UIApplicationDelegate/applicationWillTerminate:">- (void)applicationWillTerminate:(UIApplication *)application</a>
     * @since Available in iOS 2.0 and later.
     */
    @Bind("applicationWillTerminate:") @Type("void") void willTerminate(@Type("UIApplication *") UIApplication application);
    /*</methods>*/

}
