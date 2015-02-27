/*
 * Copyright (C) 2013-2015 Trillian Mobile AB
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
/*<visibility>*/public/*</visibility>*/ class /*<name>*/AVAssetReaderOutput/*</name>*/ 
    extends /*<extends>*/NSObject/*</extends>*/ 
    /*<implements>*//*</implements>*/ {

    /*<ptr>*/public static class AVAssetReaderOutputPtr extends Ptr<AVAssetReaderOutput, AVAssetReaderOutputPtr> {}/*</ptr>*/
    /*<bind>*/static { ObjCRuntime.bind(AVAssetReaderOutput.class); }/*</bind>*/
    /*<constants>*//*</constants>*/
    /*<constructors>*/
    public AVAssetReaderOutput() {}
    protected AVAssetReaderOutput(SkipInit skipInit) { super(skipInit); }
    /*</constructors>*/
    /*<properties>*/
    @Property(selector = "mediaType")
    public native AVMediaType getMediaType();
    /**
     * @since Available in iOS 5.0 and later.
     */
    @Property(selector = "alwaysCopiesSampleData")
    public native boolean alwaysCopiesSampleData();
    /**
     * @since Available in iOS 5.0 and later.
     */
    @Property(selector = "setAlwaysCopiesSampleData:")
    public native void setAlwaysCopiesSampleData(boolean v);
    /**
     * @since Available in iOS 8.0 and later.
     */
    @Property(selector = "supportsRandomAccess")
    public native boolean supportsRandomAccess();
    /**
     * @since Available in iOS 8.0 and later.
     */
    @Property(selector = "setSupportsRandomAccess:")
    public native void setSupportsRandomAccess(boolean v);
    /*</properties>*/
    /*<members>*//*</members>*/
    /*<methods>*/
    @Method(selector = "copyNextSampleBuffer")
    public native @org.robovm.rt.bro.annotation.Marshaler(CFType.NoRetainMarshaler.class) CMSampleBuffer getNextSampleBuffer();
    /**
     * @since Available in iOS 8.0 and later.
     */
    @Method(selector = "resetForReadingTimeRanges:")
    public native void resetForReadingTimeRanges(@org.robovm.rt.bro.annotation.Marshaler(CMTimeRange.AsValuedListMarshaler.class) List<CMTimeRange> timeRanges);
    /**
     * @since Available in iOS 8.0 and later.
     */
    @Method(selector = "markConfigurationAsFinal")
    public native void markConfigurationAsFinal();
    /*</methods>*/
}
