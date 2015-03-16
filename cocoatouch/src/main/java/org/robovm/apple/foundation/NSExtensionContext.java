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
package org.robovm.apple.foundation;

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
import org.robovm.apple.corefoundation.*;
import org.robovm.apple.uikit.*;
import org.robovm.apple.coreanimation.*;
import org.robovm.apple.coredata.*;
import org.robovm.apple.coregraphics.*;
import org.robovm.apple.coremedia.*;
import org.robovm.apple.security.*;
import org.robovm.apple.dispatch.*;
/*</imports>*/

/*<javadoc>*/
/**
 * @since Available in iOS 8.0 and later.
 */
/*</javadoc>*/
/*<annotations>*/@Library("Foundation") @NativeClass/*</annotations>*/
/*<visibility>*/public/*</visibility>*/ class /*<name>*/NSExtensionContext/*</name>*/ 
    extends /*<extends>*/NSObject/*</extends>*/ 
    /*<implements>*//*</implements>*/ {

    public static class Notifications {
        /**
         * @since Available in iOS 8.2 and later.
         */
        public static NSObject observeHostWillEnterForeground(NSExtensionContext object, final VoidBlock1<NSExtensionContext> block) {
            return NSNotificationCenter.getDefaultCenter().addObserver(HostWillEnterForegroundNotification(), object, NSOperationQueue.getMainQueue(), new VoidBlock1<NSNotification>() {
                @Override
                public void invoke(NSNotification a) {
                    block.invoke((NSExtensionContext) a.getObject());
                }
            });
        }
        /**
         * @since Available in iOS 8.2 and later.
         */
        public static NSObject observeHostDidEnterBackground(NSExtensionContext object, final VoidBlock1<NSExtensionContext> block) {
            return NSNotificationCenter.getDefaultCenter().addObserver(HostDidEnterBackgroundNotification(), object, NSOperationQueue.getMainQueue(), new VoidBlock1<NSNotification>() {
                @Override
                public void invoke(NSNotification a) {
                    block.invoke((NSExtensionContext) a.getObject());
                }
            });
        }
        /**
         * @since Available in iOS 8.2 and later.
         */
        public static NSObject observeHostWillResignActive(NSExtensionContext object, final VoidBlock1<NSExtensionContext> block) {
            return NSNotificationCenter.getDefaultCenter().addObserver(HostWillResignActiveNotification(), object, NSOperationQueue.getMainQueue(), new VoidBlock1<NSNotification>() {
                @Override
                public void invoke(NSNotification a) {
                    block.invoke((NSExtensionContext) a.getObject());
                }
            });
        }
        /**
         * @since Available in iOS 8.2 and later.
         */
        public static NSObject observeHostDidBecomeActive(NSExtensionContext object, final VoidBlock1<NSExtensionContext> block) {
            return NSNotificationCenter.getDefaultCenter().addObserver(HostDidBecomeActiveNotification(), object, NSOperationQueue.getMainQueue(), new VoidBlock1<NSNotification>() {
                @Override
                public void invoke(NSNotification a) {
                    block.invoke((NSExtensionContext) a.getObject());
                }
            });
        }
    }
    
    /*<ptr>*/public static class NSExtensionContextPtr extends Ptr<NSExtensionContext, NSExtensionContextPtr> {}/*</ptr>*/
    /*<bind>*/static { ObjCRuntime.bind(NSExtensionContext.class); }/*</bind>*/
    /*<constants>*//*</constants>*/
    /*<constructors>*/
    public NSExtensionContext() {}
    protected NSExtensionContext(SkipInit skipInit) { super(skipInit); }
    /*</constructors>*/
    /*<properties>*/
    @Property(selector = "inputItems")
    public native NSArray<NSExtensionItem> getInputItems();
    /*</properties>*/
    /*<members>*//*</members>*/
    /*<methods>*/
    /**
     * @since Available in iOS 8.2 and later.
     */
    @GlobalValue(symbol="NSExtensionHostWillEnterForegroundNotification", optional=true)
    public static native NSString HostWillEnterForegroundNotification();
    /**
     * @since Available in iOS 8.2 and later.
     */
    @GlobalValue(symbol="NSExtensionHostDidEnterBackgroundNotification", optional=true)
    public static native NSString HostDidEnterBackgroundNotification();
    /**
     * @since Available in iOS 8.2 and later.
     */
    @GlobalValue(symbol="NSExtensionHostWillResignActiveNotification", optional=true)
    public static native NSString HostWillResignActiveNotification();
    /**
     * @since Available in iOS 8.2 and later.
     */
    @GlobalValue(symbol="NSExtensionHostDidBecomeActiveNotification", optional=true)
    public static native NSString HostDidBecomeActiveNotification();
    
    @Method(selector = "completeRequestReturningItems:completionHandler:")
    public native void completeRequest(NSArray<NSExtensionItem> items, @Block VoidBooleanBlock completionHandler);
    @Method(selector = "cancelRequestWithError:")
    public native void cancelRequest(NSError error);
    @Method(selector = "openURL:completionHandler:")
    public native void openURL(NSURL URL, @Block VoidBooleanBlock completionHandler);
    /*</methods>*/
}
