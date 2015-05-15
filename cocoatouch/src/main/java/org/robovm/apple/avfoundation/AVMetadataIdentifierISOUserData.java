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
/*<annotations>*/@Library("AVFoundation")/*</annotations>*/
/*<visibility>*/public/*</visibility>*/ class AVMetadataIdentifierISOUserData 
    extends /*<extends>*/AVMetadataIdentifier/*</extends>*/ 
    /*<implements>*//*</implements>*/ {

    public static class Marshaler {
        @MarshalsPointer
        public static AVMetadataIdentifierISOUserData toObject(Class<AVMetadataIdentifierISOUserData> cls, long handle, long flags) {
            NSString o = (NSString) NSObject.Marshaler.toObject(NSString.class, handle, flags);
            if (o == null) {
                return null;
            }
            return AVMetadataIdentifierISOUserData.valueOf(o);
        }
        @MarshalsPointer
        public static long toNative(AVMetadataIdentifierISOUserData o, long flags) {
            if (o == null) {
                return 0L;
            }
            return NSObject.Marshaler.toNative(o.value(), flags);
        }
    }
    
    /*<ptr>*/
    /*</ptr>*/
    /*<bind>*/static { Bro.bind(AVMetadataIdentifierISOUserData.class); }/*</bind>*/
    /*<constants>*//*</constants>*/
    /**
     * @since Available in iOS 8.0 and later.
     */
    public static final AVMetadataIdentifierISOUserData Copyright = new AVMetadataIdentifierISOUserData("CopyrightValue");
    /**
     * @since Available in iOS 8.0 and later.
     */
    public static final AVMetadataIdentifierISOUserData TaggedCharacteristic = new AVMetadataIdentifierISOUserData("TaggedCharacteristicValue");
    
    private static AVMetadataIdentifierISOUserData[] values = new AVMetadataIdentifierISOUserData[] {Copyright, TaggedCharacteristic};
    private final LazyGlobalValue<NSString> lazyGlobalValue;
    
    private AVMetadataIdentifierISOUserData(String getterName) {
        lazyGlobalValue = new LazyGlobalValue<>(getClass(), getterName);
    }
    /*<constructors>*//*</constructors>*/
    /*<properties>*//*</properties>*/
    /*<members>*//*</members>*/
    public NSString value() {
        return lazyGlobalValue.value();
    }
    
    public static AVMetadataIdentifierISOUserData valueOf(NSString value) {
        for (AVMetadataIdentifierISOUserData v : values) {
            if (v.value().equals(value)) {
                return v;
            }
        }
        return null;
//        throw new IllegalArgumentException("No constant with value " + value + " found in " 
//            + /*<name>*/AVMetadataIdentifierISOUserData/*</name>*/.class.getName());
    }
    /*<methods>*/
    /**
     * @since Available in iOS 8.0 and later.
     */
    @GlobalValue(symbol="AVMetadataIdentifierISOUserDataCopyright", optional=true)
    protected static native NSString CopyrightValue();
    /**
     * @since Available in iOS 8.0 and later.
     */
    @GlobalValue(symbol="AVMetadataIdentifierISOUserDataTaggedCharacteristic", optional=true)
    protected static native NSString TaggedCharacteristicValue();
    /*</methods>*/
}
