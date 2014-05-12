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
 * @since Available in iOS 7.0 and later.
 */
/*</javadoc>*/
/*<annotations>*/@Library("Foundation") @NativeClass/*</annotations>*/
/*<visibility>*/public/*</visibility>*/ class /*<name>*/NSURLSession/*</name>*/ 
    extends /*<extends>*/NSObject/*</extends>*/ 
    /*<implements>*//*</implements>*/ {

    /*<ptr>*/public static class NSURLSessionPtr extends Ptr<NSURLSession, NSURLSessionPtr> {}/*</ptr>*/
    /*<bind>*/static { ObjCRuntime.bind(NSURLSession.class); }/*</bind>*/
    /*<constants>*//*</constants>*/
    /*<constructors>*/
    public NSURLSession() {}
    protected NSURLSession(SkipInit skipInit) { super(skipInit); }
    /*</constructors>*/
    /*<properties>*/
    @Property(selector = "delegateQueue")
    public native NSOperationQueue getDelegateQueue();
    @Property(selector = "delegate")
    public native NSURLSessionDelegate getDelegate();
    @Property(selector = "configuration")
    public native NSURLSessionConfiguration getConfiguration();
    @Property(selector = "sessionDescription")
    public native String getSessionDescription();
    @Property(selector = "setSessionDescription:")
    public native void setSessionDescription(String v);
    /*</properties>*/
    /*<members>*//*</members>*/
    /*<methods>*/
    @Method(selector = "finishTasksAndInvalidate")
    public native void finishTasksAndInvalidate();
    @Method(selector = "invalidateAndCancel")
    public native void invalidateAndCancel();
    @Method(selector = "resetWithCompletionHandler:")
    public native void resetWithCompletionHandler$(@Block Runnable completionHandler);
    @Method(selector = "flushWithCompletionHandler:")
    public native void flushWithCompletionHandler$(@Block Runnable completionHandler);
    @Method(selector = "getTasksWithCompletionHandler:")
    public native void getTasks(@Block VoidBlock3<NSArray<NSURLSessionDataTask>, NSArray<NSURLSessionUploadTask>, NSArray<NSURLSessionDownloadTask>> completionHandler);
    @Method(selector = "dataTaskWithRequest:")
    public native NSURLSessionDataTask dataTaskWithRequest$(NSURLRequest request);
    @Method(selector = "dataTaskWithURL:")
    public native NSURLSessionDataTask dataTaskWithURL$(NSURL url);
    @Method(selector = "uploadTaskWithRequest:fromFile:")
    public native NSURLSessionUploadTask uploadTaskWithRequest$fromFile$(NSURLRequest request, NSURL fileURL);
    @Method(selector = "uploadTaskWithRequest:fromData:")
    public native NSURLSessionUploadTask uploadTaskWithRequest$fromData$(NSURLRequest request, NSData bodyData);
    @Method(selector = "uploadTaskWithStreamedRequest:")
    public native NSURLSessionUploadTask uploadTaskWithStreamedRequest$(NSURLRequest request);
    @Method(selector = "downloadTaskWithRequest:")
    public native NSURLSessionDownloadTask downloadTaskWithRequest$(NSURLRequest request);
    @Method(selector = "downloadTaskWithURL:")
    public native NSURLSessionDownloadTask downloadTaskWithURL$(NSURL url);
    @Method(selector = "downloadTaskWithResumeData:")
    public native NSURLSessionDownloadTask downloadTaskWithResumeData$(NSData resumeData);
    @Method(selector = "sharedSession")
    public static native NSURLSession sharedSession();
    @Method(selector = "sessionWithConfiguration:")
    public static native NSURLSession sessionWithConfiguration$(NSURLSessionConfiguration configuration);
    @Method(selector = "sessionWithConfiguration:delegate:delegateQueue:")
    public static native NSURLSession sessionWithConfiguration$delegate$delegateQueue$(NSURLSessionConfiguration configuration, NSURLSessionDelegate delegate, NSOperationQueue queue);
    @Method(selector = "dataTaskWithRequest:completionHandler:")
    public native NSURLSessionDataTask dataTaskWithRequest$completionHandler$(NSURLRequest request, @Block VoidBlock3<NSData, NSURLResponse, NSError> completionHandler);
    @Method(selector = "dataTaskWithURL:completionHandler:")
    public native NSURLSessionDataTask dataTaskWithURL$completionHandler$(NSURL url, @Block VoidBlock3<NSData, NSURLResponse, NSError> completionHandler);
    @Method(selector = "uploadTaskWithRequest:fromFile:completionHandler:")
    public native NSURLSessionUploadTask uploadTaskWithRequest$fromFile$completionHandler$(NSURLRequest request, NSURL fileURL, @Block VoidBlock3<NSData, NSURLResponse, NSError> completionHandler);
    @Method(selector = "uploadTaskWithRequest:fromData:completionHandler:")
    public native NSURLSessionUploadTask uploadTaskWithRequest$fromData$completionHandler$(NSURLRequest request, NSData bodyData, @Block VoidBlock3<NSData, NSURLResponse, NSError> completionHandler);
    @Method(selector = "downloadTaskWithRequest:completionHandler:")
    public native NSURLSessionDownloadTask downloadTaskWithRequest$completionHandler$(NSURLRequest request, @Block VoidBlock3<NSURL, NSURLResponse, NSError> completionHandler);
    @Method(selector = "downloadTaskWithURL:completionHandler:")
    public native NSURLSessionDownloadTask downloadTaskWithURL$completionHandler$(NSURL url, @Block VoidBlock3<NSURL, NSURLResponse, NSError> completionHandler);
    @Method(selector = "downloadTaskWithResumeData:completionHandler:")
    public native NSURLSessionDownloadTask downloadTaskWithResumeData$completionHandler$(NSData resumeData, @Block VoidBlock3<NSURL, NSURLResponse, NSError> completionHandler);
    /**
     * @since Available in iOS 7.0 and later.
     * @deprecated Deprecated in iOS 7.0.
     */
    @Deprecated
    @Method(selector = "dataTaskWithHTTPGetRequest:")
    public native NSURLSessionDataTask dataTaskWithHTTPGetRequest$(NSURL url);
    /**
     * @since Available in iOS 7.0 and later.
     * @deprecated Deprecated in iOS 7.0.
     */
    @Deprecated
    @Method(selector = "dataTaskWithHTTPGetRequest:completionHandler:")
    public native NSURLSessionDataTask dataTaskWithHTTPGetRequest$completionHandler$(NSURL url, @Block VoidBlock3<NSData, NSURLResponse, NSError> completionHandler);
    /*</methods>*/
}
