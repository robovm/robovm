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
/*<visibility>*/public/*</visibility>*/ class /*<name>*/UIApplicationDelegateAdapter/*</name>*/ 
    extends /*<extends>*/NSObject/*</extends>*/ 
    /*<implements>*/implements UIApplicationDelegate/*</implements>*/ {

    /*<ptr>*/
    /*</ptr>*/
    /*<bind>*/
    /*</bind>*/
    /*<constants>*//*</constants>*/
    /*<constructors>*//*</constructors>*/
    /*<properties>*/
    @NotImplemented("window")
    public UIWindow getWindow() { throw new UnsupportedOperationException(); }
    @NotImplemented("setWindow:")
    public void setWindow(UIWindow v) { throw new UnsupportedOperationException(); }
    /*</properties>*/
    /*<members>*//*</members>*/
    /*<methods>*/
    @NotImplemented("applicationDidFinishLaunching:")
    public void didFinishLaunching(UIApplication application) { throw new UnsupportedOperationException(); }
    @NotImplemented("application:willFinishLaunchingWithOptions:")
    public boolean willFinishLaunching(UIApplication application, NSDictionary<?, ?> launchOptions) { throw new UnsupportedOperationException(); }
    @NotImplemented("application:didFinishLaunchingWithOptions:")
    public boolean didFinishLaunching(UIApplication application, NSDictionary<?, ?> launchOptions) { throw new UnsupportedOperationException(); }
    @NotImplemented("applicationDidBecomeActive:")
    public void didBecomeActive(UIApplication application) { throw new UnsupportedOperationException(); }
    @NotImplemented("applicationWillResignActive:")
    public void willResignActive(UIApplication application) { throw new UnsupportedOperationException(); }
    @NotImplemented("application:handleOpenURL:")
    public boolean handleOpenURL(UIApplication application, NSURL url) { throw new UnsupportedOperationException(); }
    @NotImplemented("application:openURL:sourceApplication:annotation:")
    public boolean openURL(UIApplication application, NSURL url, String sourceApplication, NSObject annotation) { throw new UnsupportedOperationException(); }
    @NotImplemented("applicationDidReceiveMemoryWarning:")
    public void didReceiveMemoryWarning(UIApplication application) { throw new UnsupportedOperationException(); }
    @NotImplemented("applicationWillTerminate:")
    public void willTerminate(UIApplication application) { throw new UnsupportedOperationException(); }
    @NotImplemented("applicationSignificantTimeChange:")
    public void significantTimeChange(UIApplication application) { throw new UnsupportedOperationException(); }
    @NotImplemented("application:willChangeStatusBarOrientation:duration:")
    public void willChangeStatusBarOrientation(UIApplication application, UIInterfaceOrientation newStatusBarOrientation, double duration) { throw new UnsupportedOperationException(); }
    @NotImplemented("application:didChangeStatusBarOrientation:")
    public void didChangStatusBarOrientation(UIApplication application, UIInterfaceOrientation oldStatusBarOrientation) { throw new UnsupportedOperationException(); }
    @NotImplemented("application:willChangeStatusBarFrame:")
    public void willChangeStatusBarFrame(UIApplication application, @ByVal CGRect newStatusBarFrame) { throw new UnsupportedOperationException(); }
    @NotImplemented("application:didChangeStatusBarFrame:")
    public void didChangStatusBarFrame(UIApplication application, @ByVal CGRect oldStatusBarFrame) { throw new UnsupportedOperationException(); }
    @NotImplemented("application:didRegisterForRemoteNotificationsWithDeviceToken:")
    public void didRegisterForRemoteNotifications(UIApplication application, NSData deviceToken) { throw new UnsupportedOperationException(); }
    @NotImplemented("application:didFailToRegisterForRemoteNotificationsWithError:")
    public void didFailToRegisterForRemoteNotifications(UIApplication application, NSError error) { throw new UnsupportedOperationException(); }
    @NotImplemented("application:didReceiveRemoteNotification:")
    public void didReceiveRemoteNotification(UIApplication application, NSDictionary<?, ?> userInfo) { throw new UnsupportedOperationException(); }
    @NotImplemented("application:didReceiveLocalNotification:")
    public void didReceiveLocalNotification(UIApplication application, UILocalNotification notification) { throw new UnsupportedOperationException(); }
    @NotImplemented("application:didReceiveRemoteNotification:fetchCompletionHandler:")
    public void application$didReceiveRemoteNotification$fetchCompletionHandler$(UIApplication application, NSDictionary<?, ?> userInfo, ObjCBlock completionHandler) { throw new UnsupportedOperationException(); }
    @NotImplemented("application:performFetchWithCompletionHandler:")
    public void application$performFetchWithCompletionHandler$(UIApplication application, ObjCBlock completionHandler) { throw new UnsupportedOperationException(); }
    @NotImplemented("application:handleEventsForBackgroundURLSession:completionHandler:")
    public void application$handleEventsForBackgroundURLSession$completionHandler$(UIApplication application, String identifier, @Block Runnable completionHandler) { throw new UnsupportedOperationException(); }
    @NotImplemented("applicationDidEnterBackground:")
    public void didEnterBackground(UIApplication application) { throw new UnsupportedOperationException(); }
    @NotImplemented("applicationWillEnterForeground:")
    public void willEnterForeground(UIApplication application) { throw new UnsupportedOperationException(); }
    @NotImplemented("applicationProtectedDataWillBecomeUnavailable:")
    public void protectedDataWillBecomeUnavailable(UIApplication application) { throw new UnsupportedOperationException(); }
    @NotImplemented("applicationProtectedDataDidBecomeAvailable:")
    public void protectedDataDidBecomeAvailable(UIApplication application) { throw new UnsupportedOperationException(); }
    @NotImplemented("application:supportedInterfaceOrientationsForWindow:")
    public @MachineSizedUInt long getSupportedInterfaceOrientations(UIApplication application, UIWindow window) { throw new UnsupportedOperationException(); }
    @NotImplemented("application:viewControllerWithRestorationIdentifierPath:coder:")
    public UIViewController getViewController(UIApplication application, NSArray<?> identifierComponents, NSCoder coder) { throw new UnsupportedOperationException(); }
    @NotImplemented("application:shouldSaveApplicationState:")
    public boolean shouldSaveApplicationState(UIApplication application, NSCoder coder) { throw new UnsupportedOperationException(); }
    @NotImplemented("application:shouldRestoreApplicationState:")
    public boolean shouldRestoreApplicationState(UIApplication application, NSCoder coder) { throw new UnsupportedOperationException(); }
    @NotImplemented("application:willEncodeRestorableStateWithCoder:")
    public void willEncodeRestorableState(UIApplication application, NSCoder coder) { throw new UnsupportedOperationException(); }
    @NotImplemented("application:didDecodeRestorableStateWithCoder:")
    public void didDecodeRestorableState(UIApplication application, NSCoder coder) { throw new UnsupportedOperationException(); }
    /*</methods>*/
}
