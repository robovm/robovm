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
import org.robovm.apple.security.*;
/*</imports>*/

/*<javadoc>*/
/**
 * @since Available in iOS 2.0 and later.
 */
/*</javadoc>*/
/*<annotations>*/@Library("Foundation") @NativeClass/*</annotations>*/
/*<visibility>*/public/*</visibility>*/ class /*<name>*/NSOperation/*</name>*/ 
    extends /*<extends>*/NSObject/*</extends>*/ 
    /*<implements>*//*</implements>*/ {

    /*<ptr>*/public static class NSOperationPtr extends Ptr<NSOperation, NSOperationPtr> {}/*</ptr>*/
    /*<bind>*/static { ObjCRuntime.bind(NSOperation.class); }/*</bind>*/
    /*<constants>*//*</constants>*/
    /*<constructors>*/
    public NSOperation() {}
    protected NSOperation(SkipInit skipInit) { super(skipInit); }
    /*</constructors>*/
    /*<properties>*/
    
    /*</properties>*/
    /*<members>*//*</members>*/
    /*<methods>*/
    @Method(selector = "start")
    public native void start();
    @Method(selector = "main")
    public native void main();
    @Method(selector = "isCancelled")
    public native boolean isCancelled();
    @Method(selector = "cancel")
    public native void cancel();
    @Method(selector = "isExecuting")
    public native boolean isExecuting();
    @Method(selector = "isFinished")
    public native boolean isFinished();
    @Method(selector = "isConcurrent")
    public native boolean isConcurrent();
    @Method(selector = "isReady")
    public native boolean isReady();
    @Method(selector = "addDependency:")
    public native void addDependency(NSOperation op);
    @Method(selector = "removeDependency:")
    public native void removeDependency(NSOperation op);
    @Method(selector = "dependencies")
    public native NSArray<NSOperation> getDependencies();
    @Method(selector = "queuePriority")
    public native NSOperationQueuePriority getQueuePriority();
    @Method(selector = "setQueuePriority:")
    public native void setQueuePriority(NSOperationQueuePriority p);
    /**
     * @since Available in iOS 4.0 and later.
     */
    @Method(selector = "completionBlock")
    public native @Block Runnable getCompletionBlock();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @Method(selector = "setCompletionBlock:")
    public native void setCompletionBlock(@Block Runnable block);
    /**
     * @since Available in iOS 4.0 and later.
     */
    @Method(selector = "waitUntilFinished")
    public native void waitUntilFinished();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @Method(selector = "threadPriority")
    public native double getThreadPriority();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @Method(selector = "setThreadPriority:")
    public native void setThreadPriority(double p);
    /*</methods>*/
}
