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
@Marshaler(AVPlayerItemLegibleOutputTextStylingResolution.Marshaler.class)
/*<annotations>*/@Library("AVFoundation")/*</annotations>*/
/*<visibility>*/public/*</visibility>*/ class /*<name>*/AVPlayerItemLegibleOutputTextStylingResolution/*</name>*/ 
    extends /*<extends>*/CocoaUtility/*</extends>*/ 
    /*<implements>*//*</implements>*/ {
    
    public static class Marshaler {
        @MarshalsPointer
        public static AVPlayerItemLegibleOutputTextStylingResolution toObject(Class<AVPlayerItemLegibleOutputTextStylingResolution> cls, long handle, long flags) {
            NSString o = (NSString) NSObject.Marshaler.toObject(NSString.class, handle, flags);
            if (o == null) {
                return null;
            }
            return AVPlayerItemLegibleOutputTextStylingResolution.valueOf(o);
        }
        @MarshalsPointer
        public static long toNative(AVPlayerItemLegibleOutputTextStylingResolution o, long flags) {
            if (o == null) {
                return 0L;
            }
            return NSObject.Marshaler.toNative(o.value(), flags);
        }
    }

    /*<ptr>*/
    /*</ptr>*/
    /*<bind>*/static { Bro.bind(AVPlayerItemLegibleOutputTextStylingResolution.class); }/*</bind>*/
    /*<constants>*//*</constants>*/
    /**
     * @since Available in iOS 7.0 and later.
     */
    public static final AVPlayerItemLegibleOutputTextStylingResolution Default = new AVPlayerItemLegibleOutputTextStylingResolution("DefaultValue");
    /**
     * @since Available in iOS 7.0 and later.
     */
    public static final AVPlayerItemLegibleOutputTextStylingResolution SourceAndRulesOnly = new AVPlayerItemLegibleOutputTextStylingResolution("SourceAndRulesOnlyValue");
    
    private static AVPlayerItemLegibleOutputTextStylingResolution[] values = new AVPlayerItemLegibleOutputTextStylingResolution[] {Default, SourceAndRulesOnly};
    private final LazyGlobalValue<NSString> lazyGlobalValue;
    
    private AVPlayerItemLegibleOutputTextStylingResolution(String getterName) {
        lazyGlobalValue = new LazyGlobalValue<>(getClass(), getterName);
    }
    /*<constructors>*//*</constructors>*/
    /*<properties>*//*</properties>*/
    /*<members>*//*</members>*/
    public NSString value() {
        return lazyGlobalValue.value();
    }
    
    public static AVPlayerItemLegibleOutputTextStylingResolution valueOf(NSString value) {
        for (AVPlayerItemLegibleOutputTextStylingResolution v : values) {
            if (v.value().equals(value)) {
                return v;
            }
        }
        throw new IllegalArgumentException("No constant with value " + value + " found in " 
            + /*<name>*/AVPlayerItemLegibleOutputTextStylingResolution/*</name>*/.class.getName());
    }
    /*<methods>*/
    /**
     * @since Available in iOS 7.0 and later.
     */
    @GlobalValue(symbol="AVPlayerItemLegibleOutputTextStylingResolutionDefault", optional=true)
    protected static native NSString DefaultValue();
    /**
     * @since Available in iOS 7.0 and later.
     */
    @GlobalValue(symbol="AVPlayerItemLegibleOutputTextStylingResolutionSourceAndRulesOnly", optional=true)
    protected static native NSString SourceAndRulesOnlyValue();
    /*</methods>*/
}
