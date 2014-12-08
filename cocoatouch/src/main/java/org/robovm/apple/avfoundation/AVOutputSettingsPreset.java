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
@Marshaler(AVOutputSettingsPreset.Marshaler.class)
/*<annotations>*/@Library("AVFoundation")/*</annotations>*/
/*<visibility>*/public/*</visibility>*/ class /*<name>*/AVOutputSettingsPreset/*</name>*/ 
    extends /*<extends>*/Object/*</extends>*/ 
    /*<implements>*//*</implements>*/ {

    public static class Marshaler {
        @MarshalsPointer
        public static AVOutputSettingsPreset toObject(Class<AVOutputSettingsPreset> cls, long handle, long flags) {
            NSString o = (NSString) NSObject.Marshaler.toObject(NSString.class, handle, flags);
            if (o == null) {
                return null;
            }
            return AVOutputSettingsPreset.valueOf(o);
        }
        @MarshalsPointer
        public static long toNative(AVOutputSettingsPreset o, long flags) {
            if (o == null) {
                return 0L;
            }
            return NSObject.Marshaler.toNative(o.value(), flags);
        }
    }
    
    public static class AsListMarshaler {
        @SuppressWarnings("unchecked")
        @MarshalsPointer
        public static List<AVOutputSettingsPreset> toObject(Class<? extends NSObject> cls, long handle, long flags) {
            NSArray<NSString> o = (NSArray<NSString>) NSObject.Marshaler.toObject(cls, handle, flags);
            if (o == null) {
                return null;
            }
            List<AVOutputSettingsPreset> list = new ArrayList<>();
            for (NSString str : o) {
                list.add(AVOutputSettingsPreset.valueOf(str));
            }
            return list;
        }
        @MarshalsPointer
        public static long toNative(List<AVOutputSettingsPreset> l, long flags) {
            if (l == null) {
                return 0L;
            }
            NSMutableArray<NSString> array = new NSMutableArray<>();
            for (AVOutputSettingsPreset i : l) {
                array.add(i.value());
            }
            return NSObject.Marshaler.toNative(array, flags);
        }
    }
    
    /*<ptr>*/
    /*</ptr>*/
    /*<bind>*/static { Bro.bind(AVOutputSettingsPreset.class); }/*</bind>*/
    /*<constants>*//*</constants>*/
    /**
     * @since Available in iOS 7.0 and later.
     */
    public static final AVOutputSettingsPreset Size_640x480 = new AVOutputSettingsPreset("_640x480Value");
    /**
     * @since Available in iOS 7.0 and later.
     */
    public static final AVOutputSettingsPreset Size_960x540 = new AVOutputSettingsPreset("_960x540Value");
    /**
     * @since Available in iOS 7.0 and later.
     */
    public static final AVOutputSettingsPreset Size_1280x720 = new AVOutputSettingsPreset("_1280x720Value");
    /**
     * @since Available in iOS 7.0 and later.
     */
    public static final AVOutputSettingsPreset Size_1920x1080 = new AVOutputSettingsPreset("_1920x1080Value");
    
    private static AVOutputSettingsPreset[] values = new AVOutputSettingsPreset[] {Size_640x480, Size_960x540, Size_1280x720, Size_1920x1080};
    private final LazyGlobalValue<NSString> lazyGlobalValue;
    
    private AVOutputSettingsPreset(String getterName) {
        lazyGlobalValue = new LazyGlobalValue<>(getClass(), getterName);
    }
    /*<constructors>*//*</constructors>*/
    /*<properties>*//*</properties>*/
    /*<members>*//*</members>*/
    public NSString value() {
        return lazyGlobalValue.value();
    }
    
    public static AVOutputSettingsPreset valueOf(NSString value) {
        for (AVOutputSettingsPreset v : values) {
            if (v.value().equals(value)) {
                return v;
            }
        }
        throw new IllegalArgumentException("No constant with value " + value + " found in " 
            + /*<name>*/AVOutputSettingsPreset/*</name>*/.class.getName());
    }
    /*<methods>*/
    /**
     * @since Available in iOS 7.0 and later.
     */
    @GlobalValue(symbol="AVOutputSettingsPreset640x480", optional=true)
    protected static native NSString _640x480Value();
    /**
     * @since Available in iOS 7.0 and later.
     */
    @GlobalValue(symbol="AVOutputSettingsPreset960x540", optional=true)
    protected static native NSString _960x540Value();
    /**
     * @since Available in iOS 7.0 and later.
     */
    @GlobalValue(symbol="AVOutputSettingsPreset1280x720", optional=true)
    protected static native NSString _1280x720Value();
    /**
     * @since Available in iOS 7.0 and later.
     */
    @GlobalValue(symbol="AVOutputSettingsPreset1920x1080", optional=true)
    protected static native NSString _1920x1080Value();
    /*</methods>*/
}
