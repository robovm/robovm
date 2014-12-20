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
package org.robovm.apple.avfoundation;

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
import org.robovm.apple.foundation.*;
import org.robovm.apple.dispatch.*;
import org.robovm.apple.coreanimation.*;
import org.robovm.apple.corefoundation.*;
import org.robovm.apple.coregraphics.*;
import org.robovm.apple.coreaudio.*;
import org.robovm.apple.coremedia.*;
import org.robovm.apple.corevideo.*;
import org.robovm.apple.audiotoolbox.*;
import org.robovm.apple.mediatoolbox.*;
/*</imports>*/

/*<javadoc>*/
/**
 * @since Available in iOS 4.1 and later.
 */
/*</javadoc>*/
/*<annotations>*/@Library("AVFoundation") @NativeClass/*</annotations>*/
/*<visibility>*/public/*</visibility>*/ class /*<name>*/AVAssetReader/*</name>*/ 
    extends /*<extends>*/NSObject/*</extends>*/ 
    /*<implements>*//*</implements>*/ {

    /*<ptr>*/public static class AVAssetReaderPtr extends Ptr<AVAssetReader, AVAssetReaderPtr> {}/*</ptr>*/
    /*<bind>*/static { ObjCRuntime.bind(AVAssetReader.class); }/*</bind>*/
    /*<constants>*//*</constants>*/
    /*<constructors>*/
    public AVAssetReader() {}
    protected AVAssetReader(SkipInit skipInit) { super(skipInit); }
    /*</constructors>*/
    /**
     * 
     * @param asset
     * @throws NSErrorException
     */
    public AVAssetReader(AVAsset asset) throws NSErrorException {
        super((SkipInit)null);
        NSError.NSErrorPtr err = new NSError.NSErrorPtr();
        initObject(init(asset, err));
        if (err.get() != null) {
            throw new NSErrorException(err.get());
        }
    }
    /*<properties>*/
    @Property(selector = "asset")
    public native AVAsset getAsset();
    @Property(selector = "status")
    public native AVAssetReaderStatus getStatus();
    @Property(selector = "error")
    public native NSError getError();
    @Property(selector = "timeRange")
    public native @ByVal CMTimeRange getTimeRange();
    @Property(selector = "setTimeRange:")
    public native void setTimeRange(@ByVal CMTimeRange v);
    @Property(selector = "outputs")
    public native NSArray<AVAssetReaderOutput> getOutputs();
    /*</properties>*/
    /*<members>*//*</members>*/
    /**
     * 
     * @param asset
     * @return
     * @throws NSErrorException
     */
    public static AVAssetReader create(AVAsset asset) throws NSErrorException {
        NSError.NSErrorPtr err = new NSError.NSErrorPtr();
        AVAssetReader result = create(asset, err);
        if (err.get() != null) {
            throw new NSErrorException(err.get());
        }
        return result;
    }
    /*<methods>*/
    @Method(selector = "initWithAsset:error:")
    protected native @Pointer long init(AVAsset asset, NSError.NSErrorPtr outError);
    @Method(selector = "canAddOutput:")
    public native boolean canAddOutput(AVAssetReaderOutput output);
    @Method(selector = "addOutput:")
    public native void addOutput(AVAssetReaderOutput output);
    @Method(selector = "startReading")
    public native boolean startReading();
    @Method(selector = "cancelReading")
    public native void cancelReading();
    @Method(selector = "assetReaderWithAsset:error:")
    protected static native AVAssetReader create(AVAsset asset, NSError.NSErrorPtr outError);
    /*</methods>*/
}
