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
 * @since Available in iOS 8.0 and later.
 */
/*</javadoc>*/
/*<annotations>*/@Library("AVFoundation") @NativeClass/*</annotations>*/
/*<visibility>*/public/*</visibility>*/ class /*<name>*/AVSampleBufferDisplayLayer/*</name>*/ 
    extends /*<extends>*/CALayer/*</extends>*/ 
    /*<implements>*//*</implements>*/ {
    
    public static class Notifications {
        /**
         * @since Available in iOS 8.0 and later.
         */
        public static NSObject observeFailedToDecode(AVSampleBufferDisplayLayer object, final VoidBlock2<AVSampleBufferDisplayLayer, NSError> block) {
            return NSNotificationCenter.getDefaultCenter().addObserver(FailedToDecodeNotification(), object, NSOperationQueue.getMainQueue(), new VoidBlock1<NSNotification>() {
                @Override
                public void invoke(NSNotification a) {
                    NSDictionary<NSString, NSObject> data = a.getUserInfo();
                    NSError error = null;
                    if (data.containsKey(FailedToDecodeNotificationErrorKey())) {
                        error = (NSError) data.get(FailedToDecodeNotificationErrorKey());
                    }
                    block.invoke((AVSampleBufferDisplayLayer)a.getObject(), error);
                }
            });
        }
    }

    /*<ptr>*/public static class AVSampleBufferDisplayLayerPtr extends Ptr<AVSampleBufferDisplayLayer, AVSampleBufferDisplayLayerPtr> {}/*</ptr>*/
    /*<bind>*/static { ObjCRuntime.bind(AVSampleBufferDisplayLayer.class); }/*</bind>*/
    /*<constants>*//*</constants>*/
    /*<constructors>*/
    public AVSampleBufferDisplayLayer() {}
    protected AVSampleBufferDisplayLayer(SkipInit skipInit) { super(skipInit); }
    /*</constructors>*/
    /*<properties>*/
    @Property(selector = "controlTimebase")
    public native CMTimebase getControlTimebase();
    @Property(selector = "setControlTimebase:")
    public native void setControlTimebase(CMTimebase v);
    @Property(selector = "videoGravity")
    public native AVLayerVideoGravity getVideoGravity();
    @Property(selector = "setVideoGravity:")
    public native void setVideoGravity(AVLayerVideoGravity v);
    /**
     * @since Available in iOS 8.0 and later.
     */
    @Property(selector = "status")
    public native AVQueuedSampleBufferRenderingStatus getStatus();
    /**
     * @since Available in iOS 8.0 and later.
     */
    @Property(selector = "error")
    public native NSError getError();
    @Property(selector = "isReadyForMoreMediaData")
    public native boolean isReadyForMoreMediaData();
    /*</properties>*/
    /*<members>*//*</members>*/
    /*<methods>*/
    /**
     * @since Available in iOS 8.0 and later.
     */
    @GlobalValue(symbol="AVSampleBufferDisplayLayerFailedToDecodeNotification", optional=true)
    public static native NSString FailedToDecodeNotification();
    /**
     * @since Available in iOS 8.0 and later.
     */
    @GlobalValue(symbol="AVSampleBufferDisplayLayerFailedToDecodeNotificationErrorKey", optional=true)
    protected static native NSString FailedToDecodeNotificationErrorKey();
    
    @Method(selector = "enqueueSampleBuffer:")
    public native void enqueueSampleBuffer(CMSampleBuffer sampleBuffer);
    @Method(selector = "flush")
    public native void flush();
    @Method(selector = "flushAndRemoveImage")
    public native void flushAndRemoveImage();
    @Method(selector = "requestMediaDataWhenReadyOnQueue:usingBlock:")
    public native void requestMediaDataWhenReadyOnQueue(DispatchQueue queue, @Block Runnable block);
    @Method(selector = "stopRequestingMediaData")
    public native void stopRequestingMediaData();
    /*</methods>*/
}
