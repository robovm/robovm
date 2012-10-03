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

@Protocol
public interface /*<name>*/ UIApplicationDelegate /*</name>*/ /*<implements>*/ /*</implements>*/ {

    /*<properties>*/
    @Bind("window") @Type("UIWindow *") UIWindow getWindow();
    @Bind("setWindow:") void setWindow(@Type("UIWindow *") UIWindow v);
    /*</properties>*/
    /*<methods>*/
    @Bind("applicationDidBecomeActive:") @Type("void") void didBecomeActive(@Type("UIApplication *") UIApplication application);
    @Bind("application:didChangeStatusBarFrame:") @Type("void") void didChangStatusBarFrame(@Type("UIApplication *") UIApplication application, @Type("CGRect") CGRect oldStatusBarFrame);
    @Bind("application:didChangeStatusBarOrientation:") @Type("void") void didChangStatusBarOrientation(@Type("UIApplication *") UIApplication application, @Type("UIInterfaceOrientation") UIInterfaceOrientation oldStatusBarOrientation);
    @Bind("application:didDecodeRestorableStateWithCoder:") @Type("void") void didDecodeRestorableState(@Type("UIApplication *") UIApplication application, @Type("NSCoder *") NSCoder coder);
    @Bind("applicationDidEnterBackground:") @Type("void") void didEnterBackground(@Type("UIApplication *") UIApplication application);
    @Bind("application:didFailToRegisterForRemoteNotificationsWithError:") @Type("void") void didFailToRegisterForRemoteNotifications(@Type("UIApplication *") UIApplication application, @Type("NSError *") NSError error);
    @Bind("application:didFinishLaunchingWithOptions:") @Type("BOOL") boolean didFinishLaunching(@Type("UIApplication *") UIApplication application, @Type("NSDictionary *") NSDictionary launchOptions);
    @Bind("applicationDidFinishLaunching:") @Type("void") void didFinishLaunching(@Type("UIApplication *") UIApplication application);
    @Bind("application:didReceiveLocalNotification:") @Type("void") void didReceiveLocalNotification(@Type("UIApplication *") UIApplication application, @Type("UILocalNotification *") UILocalNotification notification);
    @Bind("applicationDidReceiveMemoryWarning:") @Type("void") void didReceiveMemoryWarning(@Type("UIApplication *") UIApplication application);
    @Bind("application:didReceiveRemoteNotification:") @Type("void") void didReceiveRemoteNotification(@Type("UIApplication *") UIApplication application, @Type("NSDictionary *") NSDictionary userInfo);
    @Bind("application:didRegisterForRemoteNotificationsWithDeviceToken:") @Type("void") void didRegisterForRemoteNotifications(@Type("UIApplication *") UIApplication application, @Type("NSData *") NSData deviceToken);
    @Bind("application:supportedInterfaceOrientationsForWindow:") @Type("NSUInteger") int getSupportedInterfaceOrientations(@Type("UIApplication *") UIApplication application, @Type("UIWindow *") UIWindow window);
    @Bind("application:viewControllerWithRestorationIdentifierPath:coder:") @Type("UIViewController *") UIViewController getViewController(@Type("UIApplication *") UIApplication application, @Type("NSArray *") NSArray identifierComponents, @Type("NSCoder *") NSCoder coder);
    @Bind("application:handleOpenURL:") @Type("BOOL") boolean handleOpenURL(@Type("UIApplication *") UIApplication application, @Type("NSURL *") NSURL url);
    @Bind("application:openURL:sourceApplication:annotation:") @Type("BOOL") boolean openURL(@Type("UIApplication *") UIApplication application, @Type("NSURL *") NSURL url, @Type("NSString *") String sourceApplication, @Type("id") NSObject annotation);
    @Bind("applicationProtectedDataDidBecomeAvailable:") @Type("void") void protectedDataDidBecomeAvailable(@Type("UIApplication *") UIApplication application);
    @Bind("applicationProtectedDataWillBecomeUnavailable:") @Type("void") void protectedDataWillBecomeUnavailable(@Type("UIApplication *") UIApplication application);
    @Bind("application:shouldRestoreApplicationState:") @Type("BOOL") boolean shouldRestoreApplicationState(@Type("UIApplication *") UIApplication application, @Type("NSCoder *") NSCoder coder);
    @Bind("application:shouldSaveApplicationState:") @Type("BOOL") boolean shouldSaveApplicationState(@Type("UIApplication *") UIApplication application, @Type("NSCoder *") NSCoder coder);
    @Bind("applicationSignificantTimeChange:") @Type("void") void significantTimeChange(@Type("UIApplication *") UIApplication application);
    @Bind("application:willChangeStatusBarFrame:") @Type("void") void willChangeStatusBarFrame(@Type("UIApplication *") UIApplication application, @Type("CGRect") CGRect newStatusBarFrame);
    @Bind("application:willChangeStatusBarOrientation:duration:") @Type("void") void willChangeStatusBarOrientation(@Type("UIApplication *") UIApplication application, @Type("UIInterfaceOrientation") UIInterfaceOrientation newStatusBarOrientation, @Type("NSTimeInterval") double duration);
    @Bind("application:willEncodeRestorableStateWithCoder:") @Type("void") void willEncodeRestorableState(@Type("UIApplication *") UIApplication application, @Type("NSCoder *") NSCoder coder);
    @Bind("applicationWillEnterForeground:") @Type("void") void willEnterForeground(@Type("UIApplication *") UIApplication application);
    @Bind("application:willFinishLaunchingWithOptions:") @Type("BOOL") boolean willFinishLaunching(@Type("UIApplication *") UIApplication application, @Type("NSDictionary *") NSDictionary launchOptions);
    @Bind("applicationWillResignActive:") @Type("void") void willResignActive(@Type("UIApplication *") UIApplication application);
    @Bind("applicationWillTerminate:") @Type("void") void willTerminate(@Type("UIApplication *") UIApplication application);
    /*</methods>*/

}
