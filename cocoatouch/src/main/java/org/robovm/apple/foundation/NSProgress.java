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
import org.robovm.apple.security.*;
/*</imports>*/

/*<javadoc>*/
/**
 * @since Available in iOS 7.0 and later.
 */
/*</javadoc>*/
/*<annotations>*/@Library("Foundation") @NativeClass/*</annotations>*/
/*<visibility>*/public/*</visibility>*/ class /*<name>*/NSProgress/*</name>*/ 
    extends /*<extends>*/NSObject/*</extends>*/ 
    /*<implements>*//*</implements>*/ {

    /*<ptr>*/public static class NSProgressPtr extends Ptr<NSProgress, NSProgressPtr> {}/*</ptr>*/
    /*<bind>*/static { ObjCRuntime.bind(NSProgress.class); }/*</bind>*/
    /*<constants>*//*</constants>*/
    /*<constructors>*/
    public NSProgress() {}
    protected NSProgress(SkipInit skipInit) { super(skipInit); }
    public NSProgress(NSProgress parentProgressOrNil, NSDictionary<?, ?> userInfoOrNil) { super((SkipInit) null); initObject(initWithParent$userInfo$(parentProgressOrNil, userInfoOrNil)); }
    /*</constructors>*/
    /*<properties>*/
    @Property(selector = "totalUnitCount")
    public native long getTotalUnitCount();
    @Property(selector = "setTotalUnitCount:")
    public native void setTotalUnitCount(long v);
    @Property(selector = "completedUnitCount")
    public native long getCompletedUnitCount();
    @Property(selector = "setCompletedUnitCount:")
    public native void setCompletedUnitCount(long v);
    @Property(selector = "localizedDescription")
    public native String getLocalizedDescription();
    @Property(selector = "setLocalizedDescription:")
    public native void setLocalizedDescription(String v);
    @Property(selector = "localizedAdditionalDescription")
    public native String getLocalizedAdditionalDescription();
    @Property(selector = "setLocalizedAdditionalDescription:")
    public native void setLocalizedAdditionalDescription(String v);
    @Property(selector = "isCancellable")
    public native boolean isCancellable();
    @Property(selector = "setCancellable:")
    public native void setCancellable(boolean v);
    @Property(selector = "isPausable")
    public native boolean isPausable();
    @Property(selector = "setPausable:")
    public native void setPausable(boolean v);
    @Property(selector = "isCancelled")
    public native boolean isCancelled();
    @Property(selector = "isPaused")
    public native boolean isPaused();
    @Property(selector = "cancellationHandler")
    public native @Block Runnable getCancellationHandler();
    @Property(selector = "setCancellationHandler:")
    public native void setCancellationHandler(@Block Runnable v);
    @Property(selector = "pausingHandler")
    public native @Block Runnable getPausingHandler();
    @Property(selector = "setPausingHandler:")
    public native void setPausingHandler(@Block Runnable v);
    @Property(selector = "isIndeterminate")
    public native boolean isIndeterminate();
    @Property(selector = "fractionCompleted")
    public native double getFractionCompleted();
    @Property(selector = "kind")
    public native String getKind();
    @Property(selector = "setKind:")
    public native void setKind(String v);
    /*</properties>*/
    /*<members>*//*</members>*/
    /*<methods>*/
    @Method(selector = "initWithParent:userInfo:")
    protected native @Pointer long initWithParent$userInfo$(NSProgress parentProgressOrNil, NSDictionary<?, ?> userInfoOrNil);
    @Method(selector = "becomeCurrentWithPendingUnitCount:")
    public native void becomeCurrentWithPendingUnitCount$(long unitCount);
    @Method(selector = "resignCurrent")
    public native void resignCurrent();
    @Method(selector = "setUserInfoObject:forKey:")
    public native void setUserInfoObject$forKey$(NSObject objectOrNil, String key);
    @Method(selector = "cancel")
    public native void cancel();
    @Method(selector = "pause")
    public native void pause();
    @Method(selector = "userInfo")
    public native NSDictionary<?, ?> userInfo();
    @Method(selector = "currentProgress")
    public static native NSProgress currentProgress();
    @Method(selector = "progressWithTotalUnitCount:")
    public static native NSProgress progressWithTotalUnitCount$(long unitCount);
    /*</methods>*/
}
