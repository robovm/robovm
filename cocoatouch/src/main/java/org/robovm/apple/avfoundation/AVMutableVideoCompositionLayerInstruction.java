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
 * @since Available in iOS 4.0 and later.
 */
/*</javadoc>*/
/*<annotations>*/@Library("AVFoundation") @NativeClass/*</annotations>*/
/*<visibility>*/public/*</visibility>*/ class /*<name>*/AVMutableVideoCompositionLayerInstruction/*</name>*/ 
    extends /*<extends>*/AVVideoCompositionLayerInstruction/*</extends>*/ 
    /*<implements>*//*</implements>*/ {

    /*<ptr>*/public static class AVMutableVideoCompositionLayerInstructionPtr extends Ptr<AVMutableVideoCompositionLayerInstruction, AVMutableVideoCompositionLayerInstructionPtr> {}/*</ptr>*/
    /*<bind>*/static { ObjCRuntime.bind(AVMutableVideoCompositionLayerInstruction.class); }/*</bind>*/
    /*<constants>*//*</constants>*/
    /*<constructors>*/
    public AVMutableVideoCompositionLayerInstruction() {}
    protected AVMutableVideoCompositionLayerInstruction(SkipInit skipInit) { super(skipInit); }
    /*</constructors>*/
    /*<properties>*/
    @Property(selector = "trackID")
    public native int getTrackID();
    @Property(selector = "setTrackID:")
    public native void setTrackID(int v);
    /*</properties>*/
    /*<members>*//*</members>*/
    public void setTransformRamp(Ramp<CGAffineTransform> ramp) {
        setTransformRamp(ramp.getStart(), ramp.getEnd(), ramp.getTimeRange());
    }
    public void setOpacityRamp(Ramp<Float> ramp) {
        setOpacityRamp(ramp.getStart(), ramp.getEnd(), ramp.getTimeRange());
    }
    public void setCropRectangleRamp(Ramp<CGRect> ramp) {
        setCropRectangleRamp(ramp.getStart(), ramp.getEnd(), ramp.getTimeRange());
    }
    /*<methods>*/
    @Method(selector = "setTransformRampFromStartTransform:toEndTransform:timeRange:")
    public native void setTransformRamp(@ByVal CGAffineTransform startTransform, @ByVal CGAffineTransform endTransform, @ByVal CMTimeRange timeRange);
    @Method(selector = "setTransform:atTime:")
    public native void setTransform(@ByVal CGAffineTransform transform, @ByVal CMTime time);
    @Method(selector = "setOpacityRampFromStartOpacity:toEndOpacity:timeRange:")
    public native void setOpacityRamp(float startOpacity, float endOpacity, @ByVal CMTimeRange timeRange);
    @Method(selector = "setOpacity:atTime:")
    public native void setOpacity(float opacity, @ByVal CMTime time);
    /**
     * @since Available in iOS 7.0 and later.
     */
    @Method(selector = "setCropRectangleRampFromStartCropRectangle:toEndCropRectangle:timeRange:")
    public native void setCropRectangleRamp(@ByVal CGRect startCropRectangle, @ByVal CGRect endCropRectangle, @ByVal CMTimeRange timeRange);
    /**
     * @since Available in iOS 7.0 and later.
     */
    @Method(selector = "setCropRectangle:atTime:")
    public native void setCropRectangle(@ByVal CGRect cropRectangle, @ByVal CMTime time);
    @Method(selector = "videoCompositionLayerInstructionWithAssetTrack:")
    public static native AVMutableVideoCompositionLayerInstruction create(AVAssetTrack track);
    @Method(selector = "videoCompositionLayerInstruction")
    public static native AVMutableVideoCompositionLayerInstruction create();
    /*</methods>*/
}
