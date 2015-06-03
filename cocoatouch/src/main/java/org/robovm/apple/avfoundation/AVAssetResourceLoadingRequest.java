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
 * @since Available in iOS 6.0 and later.
 */
/*</javadoc>*/
/*<annotations>*/@Library("AVFoundation") @NativeClass/*</annotations>*/
/*<visibility>*/public/*</visibility>*/ class /*<name>*/AVAssetResourceLoadingRequest/*</name>*/ 
    extends /*<extends>*/NSObject/*</extends>*/ 
    /*<implements>*//*</implements>*/ {

    /*<ptr>*/public static class AVAssetResourceLoadingRequestPtr extends Ptr<AVAssetResourceLoadingRequest, AVAssetResourceLoadingRequestPtr> {}/*</ptr>*/
    /*<bind>*/static { ObjCRuntime.bind(AVAssetResourceLoadingRequest.class); }/*</bind>*/
    /*<constants>*//*</constants>*/
    /*<constructors>*/
    public AVAssetResourceLoadingRequest() {}
    protected AVAssetResourceLoadingRequest(SkipInit skipInit) { super(skipInit); }
    /*</constructors>*/
    /*<properties>*/
    @Property(selector = "request")
    public native NSURLRequest getRequest();
    @Property(selector = "isFinished")
    public native boolean isFinished();
    /**
     * @since Available in iOS 7.0 and later.
     */
    @Property(selector = "isCancelled")
    public native boolean isCancelled();
    /**
     * @since Available in iOS 7.0 and later.
     */
    @Property(selector = "contentInformationRequest")
    public native AVAssetResourceLoadingContentInformationRequest getContentInformationRequest();
    /**
     * @since Available in iOS 7.0 and later.
     */
    @Property(selector = "dataRequest")
    public native AVAssetResourceLoadingDataRequest getDataRequest();
    /**
     * @since Available in iOS 7.0 and later.
     */
    @Property(selector = "response")
    public native NSURLResponse getResponse();
    /**
     * @since Available in iOS 7.0 and later.
     */
    @Property(selector = "setResponse:")
    public native void setResponse(NSURLResponse v);
    /**
     * @since Available in iOS 7.0 and later.
     */
    @Property(selector = "redirect")
    public native NSURLRequest getRedirect();
    /**
     * @since Available in iOS 7.0 and later.
     */
    @Property(selector = "setRedirect:")
    public native void setRedirect(NSURLRequest v);
    /*</properties>*/
    /*<members>*//*</members>*/
    /*<methods>*/
    /**
     * @since Available in iOS 7.0 and later.
     */
    @Method(selector = "finishLoading")
    public native void finishLoading();
    @Method(selector = "finishLoadingWithError:")
    public native void finishLoading(NSError error);
    public NSData getStreamingContentKeyRequestData(NSData appIdentifier, NSData contentIdentifier, NSDictionary<NSString, ?> options) throws NSErrorException {
       NSError.NSErrorPtr ptr = new NSError.NSErrorPtr();
       NSData result = getStreamingContentKeyRequestData(appIdentifier, contentIdentifier, options, ptr);
       if (ptr.get() != null) { throw new NSErrorException(ptr.get()); }
       return result;
    }
    @Method(selector = "streamingContentKeyRequestDataForApp:contentIdentifier:options:error:")
    private native NSData getStreamingContentKeyRequestData(NSData appIdentifier, NSData contentIdentifier, NSDictionary<NSString, ?> options, NSError.NSErrorPtr outError);
    /**
     * @since Available in iOS 6.0 and later.
     * @deprecated Deprecated in iOS 7.0.
     */
    @Deprecated
    @Method(selector = "finishLoadingWithResponse:data:redirect:")
    public native void finishLoading(NSURLResponse response, NSData data, NSURLRequest redirect);
    /*</methods>*/
}
