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

/*</javadoc>*/
/*<annotations>*//*</annotations>*/
/*<visibility>*/public/*</visibility>*/ interface /*<name>*/UIApplicationDelegate/*</name>*/ 
    /*<implements>*/extends NSObjectProtocol/*</implements>*/ {

    /*<ptr>*/
    /*</ptr>*/
    /*<bind>*/
    /*</bind>*/
    /*<constants>*//*</constants>*/
    /*<properties>*/
    /**
     * @since Available in iOS 5.0 and later.
     */
    @Property(selector = "window")
    UIWindow getWindow();
    /**
     * @since Available in iOS 5.0 and later.
     */
    @Property(selector = "setWindow:")
    void setWindow(UIWindow v);
    /*</properties>*/
    /*<methods>*/
    @Method(selector = "applicationDidFinishLaunching:")
    void didFinishLaunching(UIApplication application);
    /**
     * @since Available in iOS 6.0 and later.
     */
    @Method(selector = "application:willFinishLaunchingWithOptions:")
    boolean willFinishLaunching(UIApplication application, UIApplicationLaunchOptions launchOptions);
    /**
     * @since Available in iOS 3.0 and later.
     */
    @Method(selector = "application:didFinishLaunchingWithOptions:")
    boolean didFinishLaunching(UIApplication application, UIApplicationLaunchOptions launchOptions);
    @Method(selector = "applicationDidBecomeActive:")
    void didBecomeActive(UIApplication application);
    @Method(selector = "applicationWillResignActive:")
    void willResignActive(UIApplication application);
    @Method(selector = "application:handleOpenURL:")
    boolean handleOpenURL(UIApplication application, NSURL url);
    /**
     * @since Available in iOS 4.2 and later.
     */
    @Method(selector = "application:openURL:sourceApplication:annotation:")
    boolean openURL(UIApplication application, NSURL url, String sourceApplication, NSPropertyList annotation);
    @Method(selector = "applicationDidReceiveMemoryWarning:")
    void didReceiveMemoryWarning(UIApplication application);
    @Method(selector = "applicationWillTerminate:")
    void willTerminate(UIApplication application);
    @Method(selector = "applicationSignificantTimeChange:")
    void significantTimeChange(UIApplication application);
    @Method(selector = "application:willChangeStatusBarOrientation:duration:")
    void willChangeStatusBarOrientation(UIApplication application, UIInterfaceOrientation newStatusBarOrientation, double duration);
    @Method(selector = "application:didChangeStatusBarOrientation:")
    void didChangStatusBarOrientation(UIApplication application, UIInterfaceOrientation oldStatusBarOrientation);
    @Method(selector = "application:willChangeStatusBarFrame:")
    void willChangeStatusBarFrame(UIApplication application, @ByVal CGRect newStatusBarFrame);
    @Method(selector = "application:didChangeStatusBarFrame:")
    void didChangStatusBarFrame(UIApplication application, @ByVal CGRect oldStatusBarFrame);
    /**
     * @since Available in iOS 8.0 and later.
     */
    @Method(selector = "application:didRegisterUserNotificationSettings:")
    void didRegisterUserNotificationSettings(UIApplication application, UIUserNotificationSettings notificationSettings);
    /**
     * @since Available in iOS 3.0 and later.
     */
    @Method(selector = "application:didRegisterForRemoteNotificationsWithDeviceToken:")
    void didRegisterForRemoteNotifications(UIApplication application, NSData deviceToken);
    /**
     * @since Available in iOS 3.0 and later.
     */
    @Method(selector = "application:didFailToRegisterForRemoteNotificationsWithError:")
    void didFailToRegisterForRemoteNotifications(UIApplication application, NSError error);
    /**
     * @since Available in iOS 3.0 and later.
     */
    @Method(selector = "application:didReceiveRemoteNotification:")
    void didReceiveRemoteNotification(UIApplication application, UIRemoteNotification userInfo);
    /**
     * @since Available in iOS 4.0 and later.
     */
    @Method(selector = "application:didReceiveLocalNotification:")
    void didReceiveLocalNotification(UIApplication application, UILocalNotification notification);
    /**
     * @since Available in iOS 8.0 and later.
     */
    @Method(selector = "application:handleActionWithIdentifier:forLocalNotification:completionHandler:")
    void handleLocalNotificationAction(UIApplication application, String identifier, UILocalNotification notification, @Block Runnable completionHandler);
    /**
     * @since Available in iOS 8.0 and later.
     */
    @Method(selector = "application:handleActionWithIdentifier:forRemoteNotification:completionHandler:")
    void handleRemoteNotificationAction(UIApplication application, String identifier, UIRemoteNotification userInfo, @Block Runnable completionHandler);
    /**
     * @since Available in iOS 7.0 and later.
     */
    @Method(selector = "application:didReceiveRemoteNotification:fetchCompletionHandler:")
    void didReceiveRemoteNotification(UIApplication application, UIRemoteNotification userInfo, @Block VoidBlock1<UIBackgroundFetchResult> completionHandler);
    /**
     * @since Available in iOS 7.0 and later.
     */
    @Method(selector = "application:performFetchWithCompletionHandler:")
    void performFetch(UIApplication application, @Block VoidBlock1<UIBackgroundFetchResult> completionHandler);
    /**
     * @since Available in iOS 7.0 and later.
     */
    @Method(selector = "application:handleEventsForBackgroundURLSession:completionHandler:")
    void handleEventsForBackgroundURLSession(UIApplication application, String identifier, @Block Runnable completionHandler);
    /**
     * @since Available in iOS 4.0 and later.
     */
    @Method(selector = "applicationDidEnterBackground:")
    void didEnterBackground(UIApplication application);
    /**
     * @since Available in iOS 4.0 and later.
     */
    @Method(selector = "applicationWillEnterForeground:")
    void willEnterForeground(UIApplication application);
    /**
     * @since Available in iOS 4.0 and later.
     */
    @Method(selector = "applicationProtectedDataWillBecomeUnavailable:")
    void protectedDataWillBecomeUnavailable(UIApplication application);
    /**
     * @since Available in iOS 4.0 and later.
     */
    @Method(selector = "applicationProtectedDataDidBecomeAvailable:")
    void protectedDataDidBecomeAvailable(UIApplication application);
    /**
     * @since Available in iOS 6.0 and later.
     */
    @Method(selector = "application:supportedInterfaceOrientationsForWindow:")
    @MachineSizedUInt long getSupportedInterfaceOrientations(UIApplication application, UIWindow window);
    /**
     * @since Available in iOS 8.0 and later.
     */
    @Method(selector = "application:shouldAllowExtensionPointIdentifier:")
    boolean shouldAllowExtensionPointIdentifier(UIApplication application, UIApplicationExtensionPointIdentifier extensionPointIdentifier);
    /**
     * @since Available in iOS 6.0 and later.
     */
    @Method(selector = "application:viewControllerWithRestorationIdentifierPath:coder:")
    UIViewController getViewController(UIApplication application, @org.robovm.rt.bro.annotation.Marshaler(NSArray.AsStringListMarshaler.class) List<String> identifierComponents, NSCoder coder);
    /**
     * @since Available in iOS 6.0 and later.
     */
    @Method(selector = "application:shouldSaveApplicationState:")
    boolean shouldSaveApplicationState(UIApplication application, NSCoder coder);
    /**
     * @since Available in iOS 6.0 and later.
     */
    @Method(selector = "application:shouldRestoreApplicationState:")
    boolean shouldRestoreApplicationState(UIApplication application, NSCoder coder);
    /**
     * @since Available in iOS 6.0 and later.
     */
    @Method(selector = "application:willEncodeRestorableStateWithCoder:")
    void willEncodeRestorableState(UIApplication application, NSCoder coder);
    /**
     * @since Available in iOS 6.0 and later.
     */
    @Method(selector = "application:didDecodeRestorableStateWithCoder:")
    void didDecodeRestorableState(UIApplication application, NSCoder coder);
    /**
     * @since Available in iOS 8.0 and later.
     */
    @Method(selector = "application:willContinueUserActivityWithType:")
    boolean willContinueUserActivity(UIApplication application, String userActivityType);
    /**
     * @since Available in iOS 8.0 and later.
     */
    @Method(selector = "application:continueUserActivity:restorationHandler:")
    boolean continueUserActivity(UIApplication application, NSUserActivity userActivity, @Block VoidBlock1<NSArray<UIResponder>> restorationHandler);
    /**
     * @since Available in iOS 8.0 and later.
     */
    @Method(selector = "application:didFailToContinueUserActivityWithType:error:")
    void didFailToContinueUserActivity(UIApplication application, String userActivityType, NSError error);
    /**
     * @since Available in iOS 8.0 and later.
     */
    @Method(selector = "application:didUpdateUserActivity:")
    void didUpdateUserActivity(UIApplication application, NSUserActivity userActivity);
    /*</methods>*/
    /*<adapter>*/
    /*</adapter>*/
}
