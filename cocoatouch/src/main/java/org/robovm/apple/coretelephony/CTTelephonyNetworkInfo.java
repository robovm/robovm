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
package org.robovm.apple.coretelephony;

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
import org.robovm.apple.corefoundation.*;
/*</imports>*/

/*<javadoc>*/
/**
 * @since Available in iOS 4.0 and later.
 */
/*</javadoc>*/
/*<annotations>*/@Library("CoreTelephony") @NativeClass/*</annotations>*/
/*<visibility>*/public/*</visibility>*/ class /*<name>*/CTTelephonyNetworkInfo/*</name>*/ 
    extends /*<extends>*/NSObject/*</extends>*/ 
    /*<implements>*//*</implements>*/ {

    public static class Notifications {
        public static NSObject observeRadioAccessTechnologyDidChange(CTTelephonyNetworkInfo object, final VoidBlock1<CTTelephonyNetworkInfo> block) {
            return NSNotificationCenter.getDefaultCenter().addObserver(RadioAccessTechnologyDidChangeNotification(), object, NSOperationQueue.getMainQueue(), new VoidBlock1<NSNotification>() {
                @Override
                public void invoke(NSNotification a) {
                    block.invoke((CTTelephonyNetworkInfo) a.getObject());
                }
            });
        }
    }
    /*<ptr>*/public static class CTTelephonyNetworkInfoPtr extends Ptr<CTTelephonyNetworkInfo, CTTelephonyNetworkInfoPtr> {}/*</ptr>*/
    /*<bind>*/static { ObjCRuntime.bind(CTTelephonyNetworkInfo.class); }/*</bind>*/
    /*<constants>*//*</constants>*/
    /*<constructors>*/
    public CTTelephonyNetworkInfo() {}
    protected CTTelephonyNetworkInfo(SkipInit skipInit) { super(skipInit); }
    /*</constructors>*/
    /*<properties>*/
    /**
     * @since Available in iOS 4.0 and later.
     */
    @Property(selector = "subscriberCellularProvider")
    public native CTCarrier getSubscriberCellularProvider();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @Property(selector = "subscriberCellularProviderDidUpdateNotifier")
    public native @Block VoidBlock1<CTCarrier> getSubscriberCellularProviderDidUpdateNotifier();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @Property(selector = "setSubscriberCellularProviderDidUpdateNotifier:")
    public native void setSubscriberCellularProviderDidUpdateNotifier(@Block VoidBlock1<CTCarrier> v);
    /**
     * @since Available in iOS 7.0 and later.
     */
    @Property(selector = "currentRadioAccessTechnology")
    public native CTRadioAccessTechnology getCurrentRadioAccessTechnology();
    /*</properties>*/
    /*<members>*//*</members>*/
    /*<methods>*/
    /**
     * @since Available in iOS 7.0 and later.
     */
    @GlobalValue(symbol="CTRadioAccessTechnologyDidChangeNotification", optional=true)
    public static native NSString RadioAccessTechnologyDidChangeNotification();
    
    
    /*</methods>*/
}
