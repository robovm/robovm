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
 * @since Available in iOS 9.0 and later.
 */
/*</javadoc>*/
/*<annotations>*/@Library("Foundation") @NativeClass/*</annotations>*/
/*<visibility>*/public/*</visibility>*/ class /*<name>*/NSBundleResourceRequest/*</name>*/ 
    extends /*<extends>*/NSObject/*</extends>*/ 
    /*<implements>*/implements NSProgressReporting/*</implements>*/ {

    public static class Notifications {
        /**
         * @since Available in iOS 9.0 and later.
         */
        public static NSObject observeLowDiskSpace(final Runnable block) {
            return NSNotificationCenter.getDefaultCenter().addObserver(LowDiskSpaceNotification(), null, NSOperationQueue.getMainQueue(), new VoidBlock1<NSNotification>() {
                @Override
                public void invoke(NSNotification a) {
                    block.run();
                }
            });
        }
    }
    
    /*<ptr>*/public static class NSBundleResourceRequestPtr extends Ptr<NSBundleResourceRequest, NSBundleResourceRequestPtr> {}/*</ptr>*/
    /*<bind>*/static { ObjCRuntime.bind(NSBundleResourceRequest.class); }/*</bind>*/
    /*<constants>*//*</constants>*/
    /*<constructors>*/
    public NSBundleResourceRequest() {}
    protected NSBundleResourceRequest(SkipInit skipInit) { super(skipInit); }
    public NSBundleResourceRequest(@org.robovm.rt.bro.annotation.Marshaler(NSSet.AsStringSetMarshaler.class) Set<String> tags) { super((SkipInit) null); initObject(init(tags)); }
    public NSBundleResourceRequest(@org.robovm.rt.bro.annotation.Marshaler(NSSet.AsStringSetMarshaler.class) Set<String> tags, NSBundle bundle) { super((SkipInit) null); initObject(init(tags, bundle)); }
    /*</constructors>*/
    /*<properties>*/
    @Property(selector = "loadingPriority")
    public native double getLoadingPriority();
    @Property(selector = "setLoadingPriority:")
    public native void setLoadingPriority(double v);
    @Property(selector = "tags")
    public native @org.robovm.rt.bro.annotation.Marshaler(NSSet.AsStringSetMarshaler.class) Set<String> getTags();
    @Property(selector = "bundle")
    public native NSBundle getBundle();
    @Property(selector = "progress")
    public native NSProgress getProgress();
    /*</properties>*/
    /*<members>*//*</members>*/
    /*<methods>*/
    /**
     * @since Available in iOS 9.0 and later.
     */
    @GlobalValue(symbol="NSBundleResourceRequestLowDiskSpaceNotification", optional=true)
    public static native NSString LowDiskSpaceNotification();
    /**
     * @since Available in iOS 9.0 and later.
     */
    @GlobalValue(symbol="NSBundleResourceRequestLoadingPriorityUrgent", optional=true)
    public static native double getUrgentLoadingPriority();
    
    @Method(selector = "initWithTags:")
    protected native @Pointer long init(@org.robovm.rt.bro.annotation.Marshaler(NSSet.AsStringSetMarshaler.class) Set<String> tags);
    @Method(selector = "initWithTags:bundle:")
    protected native @Pointer long init(@org.robovm.rt.bro.annotation.Marshaler(NSSet.AsStringSetMarshaler.class) Set<String> tags, NSBundle bundle);
    @Method(selector = "beginAccessingResourcesWithCompletionHandler:")
    public native void beginAccessingResources(@Block VoidBlock1<NSError> completionHandler);
    @Method(selector = "conditionallyBeginAccessingResourcesWithCompletionHandler:")
    public native void conditionallyBeginAccessingResources(@Block VoidBooleanBlock completionHandler);
    @Method(selector = "endAccessingResources")
    public native void endAccessingResources();
    /*</methods>*/
}
