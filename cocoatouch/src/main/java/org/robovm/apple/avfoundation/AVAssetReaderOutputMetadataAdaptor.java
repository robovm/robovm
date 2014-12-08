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
 * @since Available in iOS 8.0 and later.
 */
/*</javadoc>*/
/*<annotations>*/@Library("AVFoundation") @NativeClass/*</annotations>*/
/*<visibility>*/public/*</visibility>*/ class /*<name>*/AVAssetReaderOutputMetadataAdaptor/*</name>*/ 
    extends /*<extends>*/NSObject/*</extends>*/ 
    /*<implements>*//*</implements>*/ {

    /*<ptr>*/public static class AVAssetReaderOutputMetadataAdaptorPtr extends Ptr<AVAssetReaderOutputMetadataAdaptor, AVAssetReaderOutputMetadataAdaptorPtr> {}/*</ptr>*/
    /*<bind>*/static { ObjCRuntime.bind(AVAssetReaderOutputMetadataAdaptor.class); }/*</bind>*/
    /*<constants>*//*</constants>*/
    /*<constructors>*/
    public AVAssetReaderOutputMetadataAdaptor() {}
    protected AVAssetReaderOutputMetadataAdaptor(SkipInit skipInit) { super(skipInit); }
    public AVAssetReaderOutputMetadataAdaptor(AVAssetReaderTrackOutput trackOutput) { super((SkipInit) null); initObject(initWithAssetReaderTrackOutput$(trackOutput)); }
    /*</constructors>*/
    /*<properties>*/
    @Property(selector = "assetReaderTrackOutput")
    public native AVAssetReaderTrackOutput getAssetReaderTrackOutput();
    /*</properties>*/
    /*<members>*//*</members>*/
    /*<methods>*/
    @Method(selector = "initWithAssetReaderTrackOutput:")
    protected native @Pointer long initWithAssetReaderTrackOutput$(AVAssetReaderTrackOutput trackOutput);
    @Method(selector = "nextTimedMetadataGroup")
    public native AVTimedMetadataGroup nextTimedMetadataGroup();
    @Method(selector = "assetReaderOutputMetadataAdaptorWithAssetReaderTrackOutput:")
    public static native AVAssetReaderOutputMetadataAdaptor assetReaderOutputMetadataAdaptorWithAssetReaderTrackOutput$(AVAssetReaderTrackOutput trackOutput);
    /*</methods>*/
}
