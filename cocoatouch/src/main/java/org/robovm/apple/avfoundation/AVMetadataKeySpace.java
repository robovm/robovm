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
/*</javadoc>*/
@Marshaler(AVMetadataKeySpace.Marshaler.class)
/*<annotations>*/@Library("AVFoundation")/*</annotations>*/
/*<visibility>*/public/*</visibility>*/ class /*<name>*/AVMetadataKeySpace/*</name>*/ 
    extends /*<extends>*/CocoaUtility/*</extends>*/ 
    /*<implements>*//*</implements>*/ {

    public static class Marshaler {
        @MarshalsPointer
        public static AVMetadataKeySpace toObject(Class<AVMetadataKeySpace> cls, long handle, long flags) {
            NSString o = (NSString) NSObject.Marshaler.toObject(NSString.class, handle, flags);
            if (o == null) {
                return null;
            }
            return AVMetadataKeySpace.valueOf(o);
        }
        @MarshalsPointer
        public static long toNative(AVMetadataKeySpace o, long flags) {
            if (o == null) {
                return 0L;
            }
            return NSObject.Marshaler.toNative(o.value(), flags);
        }
    }
    
    /*<ptr>*/
    /*</ptr>*/
    /*<bind>*/static { Bro.bind(AVMetadataKeySpace.class); }/*</bind>*/
    /*<constants>*//*</constants>*/
    /**
     * @since Available in iOS 4.0 and later.
     */
    public static final AVMetadataKeySpace Common = new AVMetadataKeySpace("CommonValue");
    /**
     * @since Available in iOS 4.0 and later.
     */
    public static final AVMetadataKeySpace QuickTimeUserData = new AVMetadataKeySpace("QuickTimeUserDataValue");
    /**
     * @since Available in iOS 7.0 and later.
     */
    public static final AVMetadataKeySpace ISOUserData = new AVMetadataKeySpace("ISOUserDataValue");
    /**
     * @since Available in iOS 4.0 and later.
     */
    public static final AVMetadataKeySpace QuickTimeMetadata = new AVMetadataKeySpace("QuickTimeMetadataValue");
    /**
     * @since Available in iOS 4.0 and later.
     */
    public static final AVMetadataKeySpace iTunes = new AVMetadataKeySpace("iTunesValue");
    /**
     * @since Available in iOS 4.0 and later.
     */
    public static final AVMetadataKeySpace ID3 = new AVMetadataKeySpace("ID3Value");
    /**
     * @since Available in iOS 8.0 and later.
     */
    public static final AVMetadataKeySpace Icy = new AVMetadataKeySpace("IcyValue");
    
    private static AVMetadataKeySpace[] values = new AVMetadataKeySpace[] {Common, QuickTimeUserData, ISOUserData, 
        QuickTimeMetadata, iTunes, ID3, Icy};
    private final LazyGlobalValue<NSString> lazyGlobalValue;
    
    private AVMetadataKeySpace(String getterName) {
        lazyGlobalValue = new LazyGlobalValue<>(getClass(), getterName);
    }
    /*<constructors>*//*</constructors>*/
    /*<properties>*//*</properties>*/
    /*<members>*//*</members>*/
    public NSString value() {
        return lazyGlobalValue.value();
    }
    
    public static AVMetadataKeySpace valueOf(NSString value) {
        for (AVMetadataKeySpace v : values) {
            if (v.value().equals(value)) {
                return v;
            }
        }
        throw new IllegalArgumentException("No constant with value " + value + " found in " 
            + /*<name>*/AVMetadataKeySpace/*</name>*/.class.getName());
    }
    /*<methods>*/
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="AVMetadataKeySpaceCommon", optional=true)
    protected static native NSString CommonValue();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="AVMetadataKeySpaceQuickTimeUserData", optional=true)
    protected static native NSString QuickTimeUserDataValue();
    /**
     * @since Available in iOS 7.0 and later.
     */
    @GlobalValue(symbol="AVMetadataKeySpaceISOUserData", optional=true)
    protected static native NSString ISOUserDataValue();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="AVMetadataKeySpaceQuickTimeMetadata", optional=true)
    protected static native NSString QuickTimeMetadataValue();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="AVMetadataKeySpaceiTunes", optional=true)
    protected static native NSString iTunesValue();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="AVMetadataKeySpaceID3", optional=true)
    protected static native NSString ID3Value();
    /**
     * @since Available in iOS 8.0 and later.
     */
    @GlobalValue(symbol="AVMetadataKeySpaceIcy", optional=true)
    protected static native NSString IcyValue();
    /*</methods>*/
}
