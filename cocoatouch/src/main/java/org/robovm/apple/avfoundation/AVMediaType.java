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
import org.robovm.apple.audiounit.*;
/*</imports>*/

/*<javadoc>*/
/*</javadoc>*/
@Marshaler(AVMediaType.Marshaler.class)
/*<annotations>*/@Library("AVFoundation")/*</annotations>*/
/*<visibility>*/public/*</visibility>*/ class /*<name>*/AVMediaType/*</name>*/ 
    extends /*<extends>*/CocoaUtility/*</extends>*/ 
    /*<implements>*//*</implements>*/ {

    public static class Marshaler {
        @MarshalsPointer
        public static AVMediaType toObject(Class<AVMediaType> cls, long handle, long flags) {
            NSString o = (NSString) NSObject.Marshaler.toObject(NSString.class, handle, flags);
            if (o == null) {
                return null;
            }
            return AVMediaType.valueOf(o);
        }
        @MarshalsPointer
        public static long toNative(AVMediaType o, long flags) {
            if (o == null) {
                return 0L;
            }
            return NSObject.Marshaler.toNative(o.value(), flags);
        }
    }
    
    public static class AsListMarshaler {
        @SuppressWarnings("unchecked")
        @MarshalsPointer
        public static List<AVMediaType> toObject(Class<? extends NSObject> cls, long handle, long flags) {
            NSArray<NSString> o = (NSArray<NSString>) NSObject.Marshaler.toObject(cls, handle, flags);
            if (o == null) {
                return null;
            }
            List<AVMediaType> list = new ArrayList<>();
            for (NSString str : o) {
                list.add(AVMediaType.valueOf(str));
            }
            return list;
        }
        @MarshalsPointer
        public static long toNative(List<AVMediaType> l, long flags) {
            if (l == null) {
                return 0L;
            }
            NSMutableArray<NSString> array = new NSMutableArray<>();
            for (AVMediaType i : l) {
                array.add(i.value());
            }
            return NSObject.Marshaler.toNative(array, flags);
        }
    }
    
    /*<ptr>*/
    /*</ptr>*/
    /*<bind>*/static { Bro.bind(AVMediaType.class); }/*</bind>*/
    /*<constants>*//*</constants>*/
    /**
     * @since Available in iOS 4.0 and later.
     */
    public static final AVMediaType Video = new AVMediaType("VideoValue");
    /**
     * @since Available in iOS 4.0 and later.
     */
    public static final AVMediaType Audio = new AVMediaType("AudioValue");
    /**
     * @since Available in iOS 4.0 and later.
     */
    public static final AVMediaType Text = new AVMediaType("TextValue");
    /**
     * @since Available in iOS 4.0 and later.
     */
    public static final AVMediaType ClosedCaption = new AVMediaType("ClosedCaptionValue");
    /**
     * @since Available in iOS 4.0 and later.
     */
    public static final AVMediaType Subtitle = new AVMediaType("SubtitleValue");
    /**
     * @since Available in iOS 4.0 and later.
     */
    public static final AVMediaType Timecode = new AVMediaType("TimecodeValue");
    /**
     * @since Available in iOS 4.0 and later.
     */
    public static final AVMediaType Muxed = new AVMediaType("MuxedValue");
    /**
     * @since Available in iOS 6.0 and later.
     */
    public static final AVMediaType Metadata = new AVMediaType("MetadataValue");
    
    private static AVMediaType[] values = new AVMediaType[] {Video, Audio, Text, ClosedCaption, Subtitle, Timecode, 
        Muxed, Metadata};
    private final LazyGlobalValue<NSString> lazyGlobalValue;
    
    private AVMediaType(String getterName) {
        lazyGlobalValue = new LazyGlobalValue<>(getClass(), getterName);
    }
    /*<constructors>*//*</constructors>*/
    /*<properties>*//*</properties>*/
    /*<members>*//*</members>*/
    public NSString value() {
        return lazyGlobalValue.value();
    }
    
    public static AVMediaType valueOf(NSString value) {
        for (AVMediaType v : values) {
            if (v.value().equals(value)) {
                return v;
            }
        }
        throw new IllegalArgumentException("No constant with value " + value + " found in " 
            + /*<name>*/AVMediaType/*</name>*/.class.getName());
    }
    /*<methods>*/
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="AVMediaTypeVideo", optional=true)
    protected static native NSString VideoValue();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="AVMediaTypeAudio", optional=true)
    protected static native NSString AudioValue();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="AVMediaTypeText", optional=true)
    protected static native NSString TextValue();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="AVMediaTypeClosedCaption", optional=true)
    protected static native NSString ClosedCaptionValue();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="AVMediaTypeSubtitle", optional=true)
    protected static native NSString SubtitleValue();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="AVMediaTypeTimecode", optional=true)
    protected static native NSString TimecodeValue();
    /**
     * @since Available in iOS 6.0 and later.
     */
    @GlobalValue(symbol="AVMediaTypeMetadata", optional=true)
    protected static native NSString MetadataValue();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="AVMediaTypeMuxed", optional=true)
    protected static native NSString MuxedValue();
    /*</methods>*/
}
