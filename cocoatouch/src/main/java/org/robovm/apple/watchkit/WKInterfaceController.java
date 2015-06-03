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
package org.robovm.apple.watchkit;

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
import org.robovm.apple.uikit.*;
import org.robovm.apple.coregraphics.*;
import org.robovm.apple.mapkit.*;
import org.robovm.apple.corelocation.*;
/*</imports>*/

/*<javadoc>*/
/**
 * @since Available in iOS 8.2 and later.
 */
/*</javadoc>*/
/*<annotations>*/@Library("WatchKit") @NativeClass/*</annotations>*/
/*<visibility>*/public/*</visibility>*/ class /*<name>*/WKInterfaceController/*</name>*/ 
    extends /*<extends>*/NSObject/*</extends>*/ 
    /*<implements>*//*</implements>*/ {

    /*<ptr>*/public static class WKInterfaceControllerPtr extends Ptr<WKInterfaceController, WKInterfaceControllerPtr> {}/*</ptr>*/
    /*<bind>*/static { ObjCRuntime.bind(WKInterfaceController.class); }/*</bind>*/
    /*<constants>*//*</constants>*/
    /*<constructors>*/
    public WKInterfaceController() {}
    protected WKInterfaceController(SkipInit skipInit) { super(skipInit); }
    /*</constructors>*/
    /*<properties>*/
    @Property(selector = "contentFrame")
    public native @ByVal CGRect getContentFrame();
    /*</properties>*/
    /*<members>*//*</members>*/
    /*<methods>*/
    @Method(selector = "awakeWithContext:")
    public native void awake(NSObject context);
    @Method(selector = "willActivate")
    public native void willActivate();
    @Method(selector = "didDeactivate")
    public native void didDeactivate();
    @Method(selector = "table:didSelectRowAtIndex:")
    public native void didSelectRow(WKInterfaceTable table, @MachineSizedSInt long rowIndex);
    @Method(selector = "handleActionWithIdentifier:forRemoteNotification:")
    public native void handleAction(String identifier, UIRemoteNotification remoteNotification);
    @Method(selector = "handleActionWithIdentifier:forLocalNotification:")
    public native void handleAction(String identifier, UILocalNotification localNotification);
    @Method(selector = "handleUserActivity:")
    public native void handleUserActivity(NSDictionary<?, ?> userInfo);
    @Method(selector = "setTitle:")
    public native void setTitle(String title);
    @Method(selector = "pushControllerWithName:context:")
    public native void pushController(String name, NSObject context);
    @Method(selector = "popController")
    public native void popController();
    @Method(selector = "popToRootController")
    public native void popToRootController();
    @Method(selector = "becomeCurrentPage")
    public native void becomeCurrentPage();
    @Method(selector = "presentControllerWithName:context:")
    public native void presentController(String name, NSObject context);
    @Method(selector = "presentControllerWithNames:contexts:")
    public native void presentController(@org.robovm.rt.bro.annotation.Marshaler(NSArray.AsStringListMarshaler.class) List<String> names, NSArray<NSObject> contexts);
    @Method(selector = "dismissController")
    public native void dismissController();
    @Method(selector = "presentTextInputControllerWithSuggestions:allowedInputMode:completion:")
    public native void presentTextInputController(@org.robovm.rt.bro.annotation.Marshaler(NSArray.AsStringListMarshaler.class) List<String> suggestions, WKTextInputMode inputMode, @Block VoidBlock1<NSArray<?>> completion);
    @Method(selector = "dismissTextInputController")
    public native void dismissTextInputController();
    @Method(selector = "contextForSegueWithIdentifier:")
    public native NSObject getContextForSegue(String segueIdentifier);
    @Method(selector = "contextsForSegueWithIdentifier:")
    public native NSArray<NSObject> getContextsForSegue(String segueIdentifier);
    @Method(selector = "contextForSegueWithIdentifier:inTable:rowIndex:")
    public native NSObject getContextForSegue(String segueIdentifier, WKInterfaceTable table, @MachineSizedSInt long rowIndex);
    @Method(selector = "contextsForSegueWithIdentifier:inTable:rowIndex:")
    public native NSArray<NSObject> getContextsForSegue(String segueIdentifier, WKInterfaceTable table, @MachineSizedSInt long rowIndex);
    @Method(selector = "addMenuItemWithImage:title:action:")
    public native void addMenuItem(UIImage image, String title, Selector action);
    @Method(selector = "addMenuItemWithImageNamed:title:action:")
    public native void addMenuItem(String imageName, String title, Selector action);
    @Method(selector = "addMenuItemWithItemIcon:title:action:")
    public native void addMenuItem(WKMenuItemIcon itemIcon, String title, Selector action);
    @Method(selector = "clearAllMenuItems")
    public native void clearAllMenuItems();
    @Method(selector = "updateUserActivity:userInfo:webpageURL:")
    public native void updateUserActivity(String type, NSDictionary<?, ?> userInfo, NSURL webpageURL);
    @Method(selector = "invalidateUserActivity")
    public native void invalidateUserActivity();
    @Method(selector = "reloadRootControllersWithNames:contexts:")
    public static native void reloadRootControllers(@org.robovm.rt.bro.annotation.Marshaler(NSArray.AsStringListMarshaler.class) List<String> names, NSArray<NSObject> contexts);
    @Method(selector = "openParentApplication:reply:")
    public static native boolean openParentApplication(NSDictionary<?, ?> userInfo, @Block VoidBlock2<NSDictionary<?, ?>, NSError> reply);
    /*</methods>*/
}
