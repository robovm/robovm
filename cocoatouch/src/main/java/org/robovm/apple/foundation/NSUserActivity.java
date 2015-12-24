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
import org.robovm.rt.annotation.*;
import org.robovm.rt.bro.*;
import org.robovm.rt.bro.annotation.*;
import org.robovm.rt.bro.ptr.*;
import org.robovm.apple.corefoundation.*;
import org.robovm.apple.uikit.*;
import org.robovm.apple.coretext.*;
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
/*<visibility>*/public/*</visibility>*/ class /*<name>*/NSUserActivity/*</name>*/ 
    extends /*<extends>*/NSObject/*</extends>*/ 
    /*<implements>*//*</implements>*/ {

    /*<ptr>*/public static class NSUserActivityPtr extends Ptr<NSUserActivity, NSUserActivityPtr> {}/*</ptr>*/
    /*<bind>*/static { ObjCRuntime.bind(NSUserActivity.class); }/*</bind>*/
    /*<constants>*//*</constants>*/
    /*<constructors>*/
    public NSUserActivity() {}
    protected NSUserActivity(SkipInit skipInit) { super(skipInit); }
    public NSUserActivity(String activityType) { super((SkipInit) null); initObject(init(activityType)); }
    /*</constructors>*/
    public NSUserActivity(NSUserActivityType activityType) {
        this(activityType.value().toString());
    }
    /*<properties>*/
    @Property(selector = "activityType")
    public native String getActivityType();
    @Property(selector = "title")
    public native String getTitle();
    @Property(selector = "setTitle:")
    public native void setTitle(String v);
    @Property(selector = "userInfo")
    public native NSDictionary<?, ?> getUserInfo();
    @Property(selector = "setUserInfo:")
    public native void setUserInfo(NSDictionary<?, ?> v);
    /**
     * @since Available in iOS 9.0 and later.
     */
    @Property(selector = "requiredUserInfoKeys")
    public native @org.robovm.rt.bro.annotation.Marshaler(NSSet.AsStringSetMarshaler.class) Set<String> getRequiredUserInfoKeys();
    /**
     * @since Available in iOS 9.0 and later.
     */
    @Property(selector = "setRequiredUserInfoKeys:")
    public native void setRequiredUserInfoKeys(@org.robovm.rt.bro.annotation.Marshaler(NSSet.AsStringSetMarshaler.class) Set<String> v);
    @Property(selector = "needsSave")
    public native boolean needsSave();
    @Property(selector = "setNeedsSave:")
    public native void setNeedsSave(boolean v);
    @Property(selector = "webpageURL")
    public native NSURL getWebpageURL();
    @Property(selector = "setWebpageURL:")
    public native void setWebpageURL(NSURL v);
    /**
     * @since Available in iOS 9.0 and later.
     */
    @Property(selector = "expirationDate")
    public native NSDate getExpirationDate();
    /**
     * @since Available in iOS 9.0 and later.
     */
    @Property(selector = "setExpirationDate:")
    public native void setExpirationDate(NSDate v);
    /**
     * @since Available in iOS 9.0 and later.
     */
    @Property(selector = "keywords")
    public native @org.robovm.rt.bro.annotation.Marshaler(NSSet.AsStringSetMarshaler.class) Set<String> getKeywords();
    /**
     * @since Available in iOS 9.0 and later.
     */
    @Property(selector = "setKeywords:")
    public native void setKeywords(@org.robovm.rt.bro.annotation.Marshaler(NSSet.AsStringSetMarshaler.class) Set<String> v);
    @Property(selector = "supportsContinuationStreams")
    public native boolean supportsContinuationStreams();
    @Property(selector = "setSupportsContinuationStreams:")
    public native void setSupportsContinuationStreams(boolean v);
    @Property(selector = "delegate")
    public native NSUserActivityDelegate getDelegate();
    @Property(selector = "setDelegate:", strongRef = true)
    public native void setDelegate(NSUserActivityDelegate v);
    /**
     * @since Available in iOS 9.0 and later.
     */
    @Property(selector = "isEligibleForHandoff")
    public native boolean isEligibleForHandoff();
    /**
     * @since Available in iOS 9.0 and later.
     */
    @Property(selector = "setEligibleForHandoff:")
    public native void setEligibleForHandoff(boolean v);
    /**
     * @since Available in iOS 9.0 and later.
     */
    @Property(selector = "isEligibleForSearch")
    public native boolean isEligibleForSearch();
    /**
     * @since Available in iOS 9.0 and later.
     */
    @Property(selector = "setEligibleForSearch:")
    public native void setEligibleForSearch(boolean v);
    /**
     * @since Available in iOS 9.0 and later.
     */
    @Property(selector = "isEligibleForPublicIndexing")
    public native boolean isEligibleForPublicIndexing();
    /**
     * @since Available in iOS 9.0 and later.
     */
    @Property(selector = "setEligibleForPublicIndexing:")
    public native void setEligibleForPublicIndexing(boolean v);
    /*</properties>*/
    /*<members>*//*</members>*/
    /*<methods>*/
    @Method(selector = "initWithActivityType:")
    protected native @Pointer long init(String activityType);
    @Method(selector = "addUserInfoEntriesFromDictionary:")
    public native void addUserInfoEntries(NSDictionary<?, ?> otherDictionary);
    @Method(selector = "becomeCurrent")
    public native void becomeCurrent();
    /**
     * @since Available in iOS 9.0 and later.
     */
    @Method(selector = "resignCurrent")
    public native void resignCurrent();
    @Method(selector = "invalidate")
    public native void invalidate();
    @Method(selector = "getContinuationStreamsWithCompletionHandler:")
    public native void getContinuationStreams(@Block VoidBlock3<NSInputStream, NSOutputStream, NSError> completionHandler);
    /*</methods>*/
}
