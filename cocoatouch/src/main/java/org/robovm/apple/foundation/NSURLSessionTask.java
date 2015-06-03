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
 * @since Available in iOS 7.0 and later.
 */
/*</javadoc>*/
/*<annotations>*/@Library("Foundation") @NativeClass/*</annotations>*/
/*<visibility>*/public/*</visibility>*/ class /*<name>*/NSURLSessionTask/*</name>*/ 
    extends /*<extends>*/NSObject/*</extends>*/ 
    /*<implements>*//*</implements>*/ {

    /*<ptr>*/public static class NSURLSessionTaskPtr extends Ptr<NSURLSessionTask, NSURLSessionTaskPtr> {}/*</ptr>*/
    /*<bind>*/static { ObjCRuntime.bind(NSURLSessionTask.class); }/*</bind>*/
    /*<constants>*//*</constants>*/
    /*<constructors>*/
    public NSURLSessionTask() {}
    protected NSURLSessionTask(SkipInit skipInit) { super(skipInit); }
    /*</constructors>*/
    /*<properties>*/
    @Property(selector = "taskIdentifier")
    public native @MachineSizedUInt long getTaskIdentifier();
    @Property(selector = "originalRequest")
    public native NSURLRequest getOriginalRequest();
    @Property(selector = "currentRequest")
    public native NSURLRequest getCurrentRequest();
    @Property(selector = "response")
    public native NSURLResponse getResponse();
    @Property(selector = "countOfBytesReceived")
    public native long getCountOfBytesReceived();
    @Property(selector = "countOfBytesSent")
    public native long getCountOfBytesSent();
    @Property(selector = "countOfBytesExpectedToSend")
    public native long getCountOfBytesExpectedToSend();
    @Property(selector = "countOfBytesExpectedToReceive")
    public native long getCountOfBytesExpectedToReceive();
    @Property(selector = "taskDescription")
    public native String getTaskDescription();
    @Property(selector = "setTaskDescription:")
    public native void setTaskDescription(String v);
    @Property(selector = "state")
    public native NSURLSessionTaskState getState();
    @Property(selector = "error")
    public native NSError getError();
    /**
     * @since Available in iOS 8.0 and later.
     */
    @Property(selector = "priority")
    public native float getPriority();
    /**
     * @since Available in iOS 8.0 and later.
     */
    @Property(selector = "setPriority:")
    public native void setPriority(float v);
    /*</properties>*/
    /*<members>*//*</members>*/
    /*<methods>*/
    @Method(selector = "cancel")
    public native void cancel();
    @Method(selector = "suspend")
    public native void suspend();
    @Method(selector = "resume")
    public native void resume();
    /*</methods>*/
}
