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
package org.robovm.apple.avfoundation;

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
import org.robovm.apple.dispatch.*;
import org.robovm.apple.coreanimation.*;
import org.robovm.apple.coreimage.*;
import org.robovm.apple.coregraphics.*;
import org.robovm.apple.coreaudio.*;
import org.robovm.apple.coremedia.*;
import org.robovm.apple.corevideo.*;
import org.robovm.apple.mediatoolbox.*;
import org.robovm.apple.audiotoolbox.*;
import org.robovm.apple.audiounit.*;
/*</imports>*/

/*<javadoc>*/
/**
 * @since Available in iOS 9.0 and later.
 */
/*</javadoc>*/
/*<annotations>*/@Library("AVFoundation") @NativeClass/*</annotations>*/
/*<visibility>*/public/*</visibility>*/ class /*<name>*/AVAssetDownloadURLSession/*</name>*/ 
    extends /*<extends>*/NSURLSession/*</extends>*/ 
    /*<implements>*//*</implements>*/ {

    /*<ptr>*/public static class AVAssetDownloadURLSessionPtr extends Ptr<AVAssetDownloadURLSession, AVAssetDownloadURLSessionPtr> {}/*</ptr>*/
    /*<bind>*/static { ObjCRuntime.bind(AVAssetDownloadURLSession.class); }/*</bind>*/
    /*<constants>*//*</constants>*/
    /*<constructors>*/
    public AVAssetDownloadURLSession() {}
    protected AVAssetDownloadURLSession(SkipInit skipInit) { super(skipInit); }
    public AVAssetDownloadURLSession(NSURLSessionConfiguration configuration, AVAssetDownloadDelegate delegate, NSOperationQueue delegateQueue) { super(create(configuration, delegate, delegateQueue)); retain(getHandle()); }
    public AVAssetDownloadURLSession(NSURLSessionConfiguration configuration) { super(create(configuration)); retain(getHandle()); }
    public AVAssetDownloadURLSession(NSURLSessionConfiguration configuration, NSURLSessionDelegate delegate, NSOperationQueue queue) { super(create(configuration, delegate, queue)); retain(getHandle()); }
    /*</constructors>*/
    /*<properties>*/
    
    /*</properties>*/
    /*<members>*//*</members>*/
    /*<methods>*/
    @Method(selector = "assetDownloadTaskWithURLAsset:destinationURL:options:")
    public native AVAssetDownloadTask newAssetDownloadTask(AVURLAsset URLAsset, NSURL destinationURL, AVAssetDownloadTaskOptions options);
    @Method(selector = "dataTaskWithRequest:")
    public native NSURLSessionDataTask newDataTask(NSURLRequest request);
    @Method(selector = "dataTaskWithURL:")
    public native NSURLSessionDataTask newDataTask(NSURL url);
    @Method(selector = "uploadTaskWithRequest:fromFile:")
    public native NSURLSessionUploadTask newUploadTask(NSURLRequest request, NSURL fileURL);
    @Method(selector = "uploadTaskWithRequest:fromData:")
    public native NSURLSessionUploadTask newUploadTask(NSURLRequest request, NSData bodyData);
    @Method(selector = "uploadTaskWithStreamedRequest:")
    public native NSURLSessionUploadTask newUploadTask(NSURLRequest request);
    @Method(selector = "downloadTaskWithRequest:")
    public native NSURLSessionDownloadTask newDownloadTask(NSURLRequest request);
    @Method(selector = "downloadTaskWithURL:")
    public native NSURLSessionDownloadTask newDownloadTask(NSURL url);
    @Method(selector = "downloadTaskWithResumeData:")
    public native NSURLSessionDownloadTask newDownloadTask(NSData resumeData);
    @Method(selector = "dataTaskWithRequest:completionHandler:")
    public native NSURLSessionDataTask newDataTask(NSURLRequest request, @Block VoidBlock3<NSData, NSURLResponse, NSError> completionHandler);
    @Method(selector = "dataTaskWithURL:completionHandler:")
    public native NSURLSessionDataTask newDataTask(NSURL url, @Block VoidBlock3<NSData, NSURLResponse, NSError> completionHandler);
    @Method(selector = "uploadTaskWithRequest:fromFile:completionHandler:")
    public native NSURLSessionUploadTask newUploadTask(NSURLRequest request, NSURL fileURL, @Block VoidBlock3<NSData, NSURLResponse, NSError> completionHandler);
    @Method(selector = "uploadTaskWithRequest:fromData:completionHandler:")
    public native NSURLSessionUploadTask newUploadTask(NSURLRequest request, NSData bodyData, @Block VoidBlock3<NSData, NSURLResponse, NSError> completionHandler);
    @Method(selector = "downloadTaskWithRequest:completionHandler:")
    public native NSURLSessionDownloadTask newDownloadTask(NSURLRequest request, @Block VoidBlock3<NSURL, NSURLResponse, NSError> completionHandler);
    @Method(selector = "downloadTaskWithURL:completionHandler:")
    public native NSURLSessionDownloadTask newDownloadTask(NSURL url, @Block VoidBlock3<NSURL, NSURLResponse, NSError> completionHandler);
    @Method(selector = "downloadTaskWithResumeData:completionHandler:")
    public native NSURLSessionDownloadTask newDownloadTask(NSData resumeData, @Block VoidBlock3<NSURL, NSURLResponse, NSError> completionHandler);
    @Method(selector = "sessionWithConfiguration:assetDownloadDelegate:delegateQueue:")
    protected static native @Pointer long create(NSURLSessionConfiguration configuration, AVAssetDownloadDelegate delegate, NSOperationQueue delegateQueue);
    @Method(selector = "sharedSession")
    public static native NSURLSession getSharedSession();
    @Method(selector = "sessionWithConfiguration:")
    protected static native @Pointer long create(NSURLSessionConfiguration configuration);
    @Method(selector = "sessionWithConfiguration:delegate:delegateQueue:")
    protected static native @Pointer long create(NSURLSessionConfiguration configuration, NSURLSessionDelegate delegate, NSOperationQueue queue);
    /*</methods>*/
}
