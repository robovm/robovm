/*
 * Copyright (C) 2013-2015 Trillian Mobile AB
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
package org.robovm.apple.networkextension;

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
/*</imports>*/

/*<javadoc>*/
/**
 * @since Available in iOS 8.0 and later.
 */
/*</javadoc>*/
/*<annotations>*/@Library("NetworkExtension") @NativeClass/*</annotations>*/
/*<visibility>*/public/*</visibility>*/ class /*<name>*/NEVPNManager/*</name>*/ 
    extends /*<extends>*/NSObject/*</extends>*/ 
    /*<implements>*//*</implements>*/ {

    public static class Notifications {
        /**
         * @since Available in iOS 8.0 and later.
         */
        public static NSObject observeConfigurationChange(final Runnable block) {
            return NSNotificationCenter.getDefaultCenter().addObserver(ConfigurationChangeNotification(), null, NSOperationQueue.getMainQueue(), new VoidBlock1<NSNotification>() {
                @Override
                public void invoke(NSNotification a) {
                    block.run();
                }
            });
        }
    }
    
    /*<ptr>*/public static class NEVPNManagerPtr extends Ptr<NEVPNManager, NEVPNManagerPtr> {}/*</ptr>*/
    /*<bind>*/static { ObjCRuntime.bind(NEVPNManager.class); }/*</bind>*/
    /*<constants>*//*</constants>*/
    /*<constructors>*/
    public NEVPNManager() {}
    protected NEVPNManager(SkipInit skipInit) { super(skipInit); }
    /*</constructors>*/
    /*<properties>*/
    /**
     * @since Available in iOS 8.0 and later.
     */
    @Property(selector = "onDemandRules")
    public native NSArray<NEOnDemandRule> getOnDemandRules();
    /**
     * @since Available in iOS 8.0 and later.
     */
    @Property(selector = "setOnDemandRules:")
    public native void setOnDemandRules(NSArray<NEOnDemandRule> v);
    /**
     * @since Available in iOS 8.0 and later.
     */
    @Property(selector = "isOnDemandEnabled")
    public native boolean isOnDemandEnabled();
    /**
     * @since Available in iOS 8.0 and later.
     */
    @Property(selector = "setOnDemandEnabled:")
    public native void setOnDemandEnabled(boolean v);
    /**
     * @since Available in iOS 8.0 and later.
     */
    @Property(selector = "localizedDescription")
    public native String getLocalizedDescription();
    /**
     * @since Available in iOS 8.0 and later.
     */
    @Property(selector = "setLocalizedDescription:")
    public native void setLocalizedDescription(String v);
    /**
     * @since Available in iOS 8.0 and later.
     */
    @Property(selector = "protocol")
    public native NEVPNProtocol getProtocol();
    /**
     * @since Available in iOS 8.0 and later.
     */
    @Property(selector = "setProtocol:")
    public native void setProtocol(NEVPNProtocol v);
    /**
     * @since Available in iOS 8.0 and later.
     */
    @Property(selector = "connection")
    public native NEVPNConnection getConnection();
    /**
     * @since Available in iOS 8.0 and later.
     */
    @Property(selector = "isEnabled")
    public native boolean isEnabled();
    /**
     * @since Available in iOS 8.0 and later.
     */
    @Property(selector = "setEnabled:")
    public native void setEnabled(boolean v);
    /*</properties>*/
    /*<members>*//*</members>*/
    /*<methods>*/
    /**
     * @since Available in iOS 8.0 and later.
     */
    @GlobalValue(symbol="NEVPNConfigurationChangeNotification", optional=true)
    public static native NSString ConfigurationChangeNotification();
    
    /**
     * @since Available in iOS 8.0 and later.
     */
    @Method(selector = "loadFromPreferencesWithCompletionHandler:")
    public native void loadFromPreferences(@Block VoidBlock1<NSError> completionHandler);
    /**
     * @since Available in iOS 8.0 and later.
     */
    @Method(selector = "removeFromPreferencesWithCompletionHandler:")
    public native void removeFromPreferences(@Block VoidBlock1<NSError> completionHandler);
    /**
     * @since Available in iOS 8.0 and later.
     */
    @Method(selector = "saveToPreferencesWithCompletionHandler:")
    public native void saveToPreferences(@Block VoidBlock1<NSError> completionHandler);
    /**
     * @since Available in iOS 8.0 and later.
     */
    @Method(selector = "sharedManager")
    public static native NEVPNManager getSharedManager();
    /*</methods>*/
}
