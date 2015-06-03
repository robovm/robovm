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
 * @since Available in iOS 2.0 and later.
 */
/*</javadoc>*/
/*<annotations>*/@Library("Foundation") @NativeClass/*</annotations>*/
/*<visibility>*/public/*</visibility>*/ class /*<name>*/NSOperationQueue/*</name>*/ 
    extends /*<extends>*/NSObject/*</extends>*/ 
    /*<implements>*//*</implements>*/ {

    /*<ptr>*/public static class NSOperationQueuePtr extends Ptr<NSOperationQueue, NSOperationQueuePtr> {}/*</ptr>*/
    /*<bind>*/static { ObjCRuntime.bind(NSOperationQueue.class); }/*</bind>*/
    /*<constants>*/
    public static final int DefaultMaxConcurrentOperationCount = -1;
    /*</constants>*/
    /*<constructors>*/
    public NSOperationQueue() {}
    protected NSOperationQueue(SkipInit skipInit) { super(skipInit); }
    /*</constructors>*/
    /*<properties>*/
    @Property(selector = "operations")
    public native NSArray<NSOperation> getOperations();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @Property(selector = "operationCount")
    public native @MachineSizedUInt long getOperationCount();
    @Property(selector = "maxConcurrentOperationCount")
    public native @MachineSizedSInt long getMaxConcurrentOperationCount();
    @Property(selector = "setMaxConcurrentOperationCount:")
    public native void setMaxConcurrentOperationCount(@MachineSizedSInt long v);
    @Property(selector = "isSuspended")
    public native boolean isSuspended();
    @Property(selector = "setSuspended:")
    public native void setSuspended(boolean v);
    /**
     * @since Available in iOS 4.0 and later.
     */
    @Property(selector = "name")
    public native String getName();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @Property(selector = "setName:")
    public native void setName(String v);
    /**
     * @since Available in iOS 8.0 and later.
     */
    @Property(selector = "qualityOfService")
    public native NSQualityOfService getQualityOfService();
    /**
     * @since Available in iOS 8.0 and later.
     */
    @Property(selector = "setQualityOfService:")
    public native void setQualityOfService(NSQualityOfService v);
    /**
     * @since Available in iOS 8.0 and later.
     */
    @WeaklyLinked
    @Property(selector = "underlyingQueue")
    public native DispatchQueue getUnderlyingQueue();
    /**
     * @since Available in iOS 8.0 and later.
     */
    @WeaklyLinked
    @Property(selector = "setUnderlyingQueue:")
    public native void setUnderlyingQueue(DispatchQueue v);
    /*</properties>*/
    /*<members>*//*</members>*/
    /*<methods>*/
    @Method(selector = "addOperation:")
    public native void addOperation(NSOperation op);
    /**
     * @since Available in iOS 4.0 and later.
     */
    @Method(selector = "addOperations:waitUntilFinished:")
    public native void addOperations(NSArray<NSOperation> ops, boolean wait);
    /**
     * @since Available in iOS 4.0 and later.
     */
    @Method(selector = "addOperationWithBlock:")
    public native void addOperation(@Block Runnable block);
    @Method(selector = "cancelAllOperations")
    public native void cancelAllOperations();
    @Method(selector = "waitUntilAllOperationsAreFinished")
    public native void waitUntilAllOperationsAreFinished();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @Method(selector = "currentQueue")
    public static native NSOperationQueue getCurrentQueue();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @Method(selector = "mainQueue")
    public static native NSOperationQueue getMainQueue();
    /*</methods>*/
}
