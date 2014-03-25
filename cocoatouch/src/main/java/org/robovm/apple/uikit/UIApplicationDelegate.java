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
/*</imports>*/

/**
 *
 * <div class="javadoc"></div>
 */
/*<annotations>*//*</annotations>*/
/*<visibility>*/public/*</visibility>*/ interface /*<name>*/UIApplicationDelegate/*</name>*/ 
    /*<implements>*/extends NSObjectProtocol/*</implements>*/ {

    /*<ptr>*/
    /*</ptr>*/
    /*<bind>*/
    /*</bind>*/
    /*<constants>*//*</constants>*/
    /*<properties>*/
    @Property(selector = "window")
    UIWindow getWindow();
    @Property(selector = "setWindow:")
    void setWindow(UIWindow v);
    /*</properties>*/
    /*<methods>*/
    @Method(selector = "applicationDidFinishLaunching:")
    void didFinishLaunching(UIApplication application);
    @Method(selector = "application:willFinishLaunchingWithOptions:")
    boolean willFinishLaunching(UIApplication application, NSDictionary<?, ?> launchOptions);
    @Method(selector = "application:didFinishLaunchingWithOptions:")
    boolean didFinishLaunching(UIApplication application, NSDictionary<?, ?> launchOptions);
    @Method(selector = "applicationDidBecomeActive:")
    void didBecomeActive(UIApplication application);
    @Method(selector = "applicationWillResignActive:")
    void willResignActive(UIApplication application);
    @Method(selector = "application:handleOpenURL:")
    boolean handleOpenURL(UIApplication application, NSURL url);
    @Method(selector = "application:openURL:sourceApplication:annotation:")
    boolean openURL(UIApplication application, NSURL url, String sourceApplication, NSObject annotation);
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
    @Method(selector = "application:didRegisterForRemoteNotificationsWithDeviceToken:")
    void didRegisterForRemoteNotifications(UIApplication application, NSData deviceToken);
    @Method(selector = "application:didFailToRegisterForRemoteNotificationsWithError:")
    void didFailToRegisterForRemoteNotifications(UIApplication application, NSError error);
    @Method(selector = "application:didReceiveRemoteNotification:")
    void didReceiveRemoteNotification(UIApplication application, NSDictionary<?, ?> userInfo);
    @Method(selector = "application:didReceiveLocalNotification:")
    void didReceiveLocalNotification(UIApplication application, UILocalNotification notification);
    @Method(selector = "application:didReceiveRemoteNotification:fetchCompletionHandler:")
    void didReceiveRemoteNotification(UIApplication application, NSDictionary<?, ?> userInfo, @Block VoidBlock1<UIBackgroundFetchResult> completionHandler);
    @Method(selector = "application:performFetchWithCompletionHandler:")
    void performFetch(UIApplication application, @Block VoidBlock1<UIBackgroundFetchResult> completionHandler);
    @Method(selector = "application:handleEventsForBackgroundURLSession:completionHandler:")
    void handleEventsForBackgroundURLSession(UIApplication application, String identifier, @Block Runnable completionHandler);
    @Method(selector = "applicationDidEnterBackground:")
    void didEnterBackground(UIApplication application);
    @Method(selector = "applicationWillEnterForeground:")
    void willEnterForeground(UIApplication application);
    @Method(selector = "applicationProtectedDataWillBecomeUnavailable:")
    void protectedDataWillBecomeUnavailable(UIApplication application);
    @Method(selector = "applicationProtectedDataDidBecomeAvailable:")
    void protectedDataDidBecomeAvailable(UIApplication application);
    @Method(selector = "application:supportedInterfaceOrientationsForWindow:")
    @MachineSizedUInt long getSupportedInterfaceOrientations(UIApplication application, UIWindow window);
    @Method(selector = "application:viewControllerWithRestorationIdentifierPath:coder:")
    UIViewController getViewController(UIApplication application, NSArray<?> identifierComponents, NSCoder coder);
    @Method(selector = "application:shouldSaveApplicationState:")
    boolean shouldSaveApplicationState(UIApplication application, NSCoder coder);
    @Method(selector = "application:shouldRestoreApplicationState:")
    boolean shouldRestoreApplicationState(UIApplication application, NSCoder coder);
    @Method(selector = "application:willEncodeRestorableStateWithCoder:")
    void willEncodeRestorableState(UIApplication application, NSCoder coder);
    @Method(selector = "application:didDecodeRestorableStateWithCoder:")
    void didDecodeRestorableState(UIApplication application, NSCoder coder);
    /*</methods>*/
    /*<adapter>*/
    /*</adapter>*/
}
