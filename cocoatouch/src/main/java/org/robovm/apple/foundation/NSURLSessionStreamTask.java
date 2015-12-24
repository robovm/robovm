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
/*<visibility>*/public/*</visibility>*/ class /*<name>*/NSURLSessionStreamTask/*</name>*/ 
    extends /*<extends>*/NSURLSessionTask/*</extends>*/ 
    /*<implements>*//*</implements>*/ {

    /*<ptr>*/public static class NSURLSessionStreamTaskPtr extends Ptr<NSURLSessionStreamTask, NSURLSessionStreamTaskPtr> {}/*</ptr>*/
    /*<bind>*/static { ObjCRuntime.bind(NSURLSessionStreamTask.class); }/*</bind>*/
    /*<constants>*//*</constants>*/
    /*<constructors>*/
    public NSURLSessionStreamTask() {}
    protected NSURLSessionStreamTask(SkipInit skipInit) { super(skipInit); }
    /*</constructors>*/
    /*<properties>*/
    
    /*</properties>*/
    /*<members>*//*</members>*/
    /*<methods>*/
    @Method(selector = "readDataOfMinLength:maxLength:timeout:completionHandler:")
    public native void readData(@MachineSizedUInt long minBytes, @MachineSizedUInt long maxBytes, double timeout, @Block VoidBlock3<NSData, Boolean, NSError> completionHandler);
    @Method(selector = "writeData:timeout:completionHandler:")
    public native void writeData(NSData data, double timeout, @Block VoidBlock1<NSError> completionHandler);
    @Method(selector = "captureStreams")
    public native void captureStreams();
    @Method(selector = "closeWrite")
    public native void closeWrite();
    @Method(selector = "closeRead")
    public native void closeRead();
    @Method(selector = "startSecureConnection")
    public native void startSecureConnection();
    @Method(selector = "stopSecureConnection")
    public native void stopSecureConnection();
    /*</methods>*/
}
