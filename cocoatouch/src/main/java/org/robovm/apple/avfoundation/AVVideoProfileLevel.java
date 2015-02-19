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
/*</javadoc>*/
@Marshaler(AVVideoProfileLevel.Marshaler.class)
/*<annotations>*/@Library("AVFoundation")/*</annotations>*/
/*<visibility>*/public/*</visibility>*/ class /*<name>*/AVVideoProfileLevel/*</name>*/ 
    extends /*<extends>*/CocoaUtility/*</extends>*/ 
    /*<implements>*//*</implements>*/ {

    public static class Marshaler {
        @MarshalsPointer
        public static AVVideoProfileLevel toObject(Class<AVVideoProfileLevel> cls, long handle, long flags) {
            NSString o = (NSString) NSObject.Marshaler.toObject(NSString.class, handle, flags);
            if (o == null) {
                return null;
            }
            return AVVideoProfileLevel.valueOf(o);
        }
        @MarshalsPointer
        public static long toNative(AVVideoProfileLevel o, long flags) {
            if (o == null) {
                return 0L;
            }
            return NSObject.Marshaler.toNative(o.value(), flags);
        }
    }
    
    /*<ptr>*/
    /*</ptr>*/
    /*<bind>*/static { Bro.bind(AVVideoProfileLevel.class); }/*</bind>*/
    /*<constants>*//*</constants>*/
    /**
     * @since Available in iOS 4.0 and later.
     */
    public static final AVVideoProfileLevel H264Baseline30 = new AVVideoProfileLevel("H264Baseline30Value");
    /**
     * @since Available in iOS 4.0 and later.
     */
    public static final AVVideoProfileLevel H264Baseline31 = new AVVideoProfileLevel("H264Baseline31Value");
    /**
     * @since Available in iOS 5.0 and later.
     */
    public static final AVVideoProfileLevel H264Baseline41 = new AVVideoProfileLevel("H264Baseline41Value");
    /**
     * @since Available in iOS 4.0 and later.
     */
    public static final AVVideoProfileLevel H264Main30 = new AVVideoProfileLevel("H264Main30Value");
    /**
     * @since Available in iOS 4.0 and later.
     */
    public static final AVVideoProfileLevel H264Main31 = new AVVideoProfileLevel("H264Main31Value");
    /**
     * @since Available in iOS 5.0 and later.
     */
    public static final AVVideoProfileLevel H264Main32 = new AVVideoProfileLevel("H264Main32Value");
    /**
     * @since Available in iOS 5.0 and later.
     */
    public static final AVVideoProfileLevel H264Main41 = new AVVideoProfileLevel("H264Main41Value");
    /**
     * @since Available in iOS 6.0 and later.
     */
    public static final AVVideoProfileLevel H264High40 = new AVVideoProfileLevel("H264High40Value");
    /**
     * @since Available in iOS 6.0 and later.
     */
    public static final AVVideoProfileLevel H264High41 = new AVVideoProfileLevel("H264High41Value");
    /**
     * @since Available in iOS 7.0 and later.
     */
    public static final AVVideoProfileLevel H264MainAutoLevel = new AVVideoProfileLevel("H264MainAutoLevelValue");
    /**
     * @since Available in iOS 7.0 and later.
     */
    public static final AVVideoProfileLevel H264BaselineAutoLevel = new AVVideoProfileLevel("H264BaselineAutoLevelValue");
    /**
     * @since Available in iOS 7.0 and later.
     */
    public static final AVVideoProfileLevel H264HighAutoLevel = new AVVideoProfileLevel("H264HighAutoLevelValue");
    
    private static AVVideoProfileLevel[] values = new AVVideoProfileLevel[] {H264Baseline30, H264Baseline31, H264Baseline41, 
        H264Main30, H264Main31, H264Main32, H264Main41, H264High40, H264High41, H264BaselineAutoLevel, H264MainAutoLevel, H264HighAutoLevel};
    private final LazyGlobalValue<NSString> lazyGlobalValue;
    
    private AVVideoProfileLevel(String getterName) {
        lazyGlobalValue = new LazyGlobalValue<>(getClass(), getterName);
    }
    /*<constructors>*//*</constructors>*/
    /*<properties>*//*</properties>*/
    /*<members>*//*</members>*/
    public NSString value() {
        return lazyGlobalValue.value();
    }
    
    public static AVVideoProfileLevel valueOf(NSString value) {
        for (AVVideoProfileLevel v : values) {
            if (v.value().equals(value)) {
                return v;
            }
        }
        throw new IllegalArgumentException("No constant with value " + value + " found in " 
            + /*<name>*/AVVideoProfileLevel/*</name>*/.class.getName());
    }
    /*<methods>*/
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="AVVideoProfileLevelH264Baseline30", optional=true)
    protected static native NSString H264Baseline30Value();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="AVVideoProfileLevelH264Baseline31", optional=true)
    protected static native NSString H264Baseline31Value();
    /**
     * @since Available in iOS 5.0 and later.
     */
    @GlobalValue(symbol="AVVideoProfileLevelH264Baseline41", optional=true)
    protected static native NSString H264Baseline41Value();
    /**
     * @since Available in iOS 7.0 and later.
     */
    @GlobalValue(symbol="AVVideoProfileLevelH264BaselineAutoLevel", optional=true)
    protected static native NSString H264BaselineAutoLevelValue();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="AVVideoProfileLevelH264Main30", optional=true)
    protected static native NSString H264Main30Value();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="AVVideoProfileLevelH264Main31", optional=true)
    protected static native NSString H264Main31Value();
    /**
     * @since Available in iOS 5.0 and later.
     */
    @GlobalValue(symbol="AVVideoProfileLevelH264Main32", optional=true)
    protected static native NSString H264Main32Value();
    /**
     * @since Available in iOS 5.0 and later.
     */
    @GlobalValue(symbol="AVVideoProfileLevelH264Main41", optional=true)
    protected static native NSString H264Main41Value();
    /**
     * @since Available in iOS 7.0 and later.
     */
    @GlobalValue(symbol="AVVideoProfileLevelH264MainAutoLevel", optional=true)
    protected static native NSString H264MainAutoLevelValue();
    /**
     * @since Available in iOS 6.0 and later.
     */
    @GlobalValue(symbol="AVVideoProfileLevelH264High40", optional=true)
    protected static native NSString H264High40Value();
    /**
     * @since Available in iOS 6.0 and later.
     */
    @GlobalValue(symbol="AVVideoProfileLevelH264High41", optional=true)
    protected static native NSString H264High41Value();
    /**
     * @since Available in iOS 7.0 and later.
     */
    @GlobalValue(symbol="AVVideoProfileLevelH264HighAutoLevel", optional=true)
    protected static native NSString H264HighAutoLevelValue();
    /*</methods>*/
}
