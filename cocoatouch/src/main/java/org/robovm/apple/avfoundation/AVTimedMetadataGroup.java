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
 * @since Available in iOS 4.3 and later.
 */
/*</javadoc>*/
/*<annotations>*/@Library("AVFoundation") @NativeClass/*</annotations>*/
/*<visibility>*/public/*</visibility>*/ class /*<name>*/AVTimedMetadataGroup/*</name>*/ 
    extends /*<extends>*/NSObject/*</extends>*/ 
    /*<implements>*//*</implements>*/ {

    /*<ptr>*/public static class AVTimedMetadataGroupPtr extends Ptr<AVTimedMetadataGroup, AVTimedMetadataGroupPtr> {}/*</ptr>*/
    /*<bind>*/static { ObjCRuntime.bind(AVTimedMetadataGroup.class); }/*</bind>*/
    /*<constants>*//*</constants>*/
    /*<constructors>*/
    public AVTimedMetadataGroup() {}
    protected AVTimedMetadataGroup(SkipInit skipInit) { super(skipInit); }
    public AVTimedMetadataGroup(NSArray<AVMetadataItem> items, @ByVal CMTimeRange timeRange) { super((SkipInit) null); initObject(init(items, timeRange)); }
    /**
     * @since Available in iOS 8.0 and later.
     */
    public AVTimedMetadataGroup(CMSampleBuffer sampleBuffer) { super((SkipInit) null); initObject(init(sampleBuffer)); }
    /*</constructors>*/
    /*<properties>*/
    @Property(selector = "timeRange")
    public native @ByVal CMTimeRange getTimeRange();
    @Property(selector = "items")
    public native NSArray<AVMetadataItem> getItems();
    /*</properties>*/
    /*<members>*//*</members>*/
    /*<methods>*/
    @Method(selector = "initWithItems:timeRange:")
    protected native @Pointer long init(NSArray<AVMetadataItem> items, @ByVal CMTimeRange timeRange);
    /**
     * @since Available in iOS 8.0 and later.
     */
    @Method(selector = "initWithSampleBuffer:")
    protected native @Pointer long init(CMSampleBuffer sampleBuffer);
    /**
     * @since Available in iOS 8.0 and later.
     */
    @Method(selector = "copyFormatDescription")
    public native @org.robovm.rt.bro.annotation.Marshaler(CFType.NoRetainMarshaler.class) CMFormatDescription getFormatDescription();
    /*</methods>*/
}
