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
/*</javadoc>*/
@Marshaler(AVAudioSessionOrientation.Marshaler.class)
/*<annotations>*/@Library("AVFoundation")/*</annotations>*/
/*<visibility>*/public/*</visibility>*/ class /*<name>*/AVAudioSessionOrientation/*</name>*/ 
    extends /*<extends>*/Object/*</extends>*/ 
    /*<implements>*//*</implements>*/ {
    
    public static class Marshaler {
        @MarshalsPointer
        public static AVAudioSessionOrientation toObject(Class<AVAudioSessionOrientation> cls, long handle, long flags) {
            NSString o = (NSString) NSObject.Marshaler.toObject(NSString.class, handle, flags);
            if (o == null) {
                return null;
            }
            return AVAudioSessionOrientation.valueOf(o);
        }
        @MarshalsPointer
        public static long toNative(AVAudioSessionOrientation o, long flags) {
            if (o == null) {
                return 0L;
            }
            return NSObject.Marshaler.toNative(o.value(), flags);
        }
    }

    /*<ptr>*/
    /*</ptr>*/
    /*<bind>*/static { Bro.bind(AVAudioSessionOrientation.class); }/*</bind>*/
    /*<constants>*//*</constants>*/
    /**
     * @since Available in iOS 7.0 and later.
     */
    public static final AVAudioSessionOrientation Top = new AVAudioSessionOrientation("TopValue");
    /**
     * @since Available in iOS 7.0 and later.
     */
    public static final AVAudioSessionOrientation Bottom = new AVAudioSessionOrientation("BottomValue");
    /**
     * @since Available in iOS 7.0 and later.
     */
    public static final AVAudioSessionOrientation Front = new AVAudioSessionOrientation("FrontValue");
    /**
     * @since Available in iOS 7.0 and later.
     */
    public static final AVAudioSessionOrientation Back = new AVAudioSessionOrientation("BackValue");
    /**
     * @since Available in iOS 8.0 and later.
     */
    public static final AVAudioSessionOrientation Left = new AVAudioSessionOrientation("LeftValue");
    /**
     * @since Available in iOS 8.0 and later.
     */
    public static final AVAudioSessionOrientation Right = new AVAudioSessionOrientation("RightValue");
    
    private static AVAudioSessionOrientation[] values = new AVAudioSessionOrientation[] {Top, Bottom, Front, Back,
        Left, Right};
    private final LazyGlobalValue<NSString> lazyGlobalValue;
    
    private AVAudioSessionOrientation(String getterName) {
        lazyGlobalValue = new LazyGlobalValue<>(getClass(), getterName);
    }
    /*<constructors>*//*</constructors>*/
    /*<properties>*//*</properties>*/
    /*<members>*//*</members>*/
    public NSString value() {
        return lazyGlobalValue.value();
    }
    
    public static AVAudioSessionOrientation valueOf(NSString value) {
        for (AVAudioSessionOrientation v : values) {
            if (v.value().equals(value)) {
                return v;
            }
        }
        throw new IllegalArgumentException("No constant with value " + value + " found in " 
            + /*<name>*/AVAudioSessionOrientation/*</name>*/.class.getName());
    }
    /*<methods>*/
    /**
     * @since Available in iOS 7.0 and later.
     */
    @GlobalValue(symbol="AVAudioSessionOrientationTop", optional=true)
    protected static native NSString TopValue();
    /**
     * @since Available in iOS 7.0 and later.
     */
    @GlobalValue(symbol="AVAudioSessionOrientationBottom", optional=true)
    protected static native NSString BottomValue();
    /**
     * @since Available in iOS 7.0 and later.
     */
    @GlobalValue(symbol="AVAudioSessionOrientationFront", optional=true)
    protected static native NSString FrontValue();
    /**
     * @since Available in iOS 7.0 and later.
     */
    @GlobalValue(symbol="AVAudioSessionOrientationBack", optional=true)
    protected static native NSString BackValue();
    /**
     * @since Available in iOS 8.0 and later.
     */
    @GlobalValue(symbol="AVAudioSessionOrientationLeft", optional=true)
    protected static native NSString LeftValue();
    /**
     * @since Available in iOS 8.0 and later.
     */
    @GlobalValue(symbol="AVAudioSessionOrientationRight", optional=true)
    protected static native NSString RightValue();
    /*</methods>*/
}
