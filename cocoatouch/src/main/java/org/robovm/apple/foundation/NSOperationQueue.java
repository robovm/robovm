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

/**
 *
 * <div class="javadoc"></div>
 */
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
    
    /*</properties>*/
    /*<members>*//*</members>*/
    /*<methods>*/
    @Method(selector = "addOperation:")
    public native void addOperation$(NSOperation op);
    @Method(selector = "addOperations:waitUntilFinished:")
    public native void addOperations$waitUntilFinished$(NSArray<?> ops, boolean wait);
    @Method(selector = "addOperationWithBlock:")
    public native void addOperationWithBlock$(@Block Runnable block);
    @Method(selector = "operations")
    public native NSArray<?> operations();
    @Method(selector = "operationCount")
    public native @MachineSizedUInt long operationCount();
    @Method(selector = "maxConcurrentOperationCount")
    public native @MachineSizedSInt long maxConcurrentOperationCount();
    @Method(selector = "setMaxConcurrentOperationCount:")
    public native void setMaxConcurrentOperationCount$(@MachineSizedSInt long cnt);
    @Method(selector = "setSuspended:")
    public native void setSuspended$(boolean b);
    @Method(selector = "isSuspended")
    public native boolean isSuspended();
    @Method(selector = "setName:")
    public native void setName$(String n);
    @Method(selector = "name")
    public native String name();
    @Method(selector = "cancelAllOperations")
    public native void cancelAllOperations();
    @Method(selector = "waitUntilAllOperationsAreFinished")
    public native void waitUntilAllOperationsAreFinished();
    @Method(selector = "currentQueue")
    public static native NSObject currentQueue();
    @Method(selector = "mainQueue")
    public static native NSObject mainQueue();
    /*</methods>*/
}
