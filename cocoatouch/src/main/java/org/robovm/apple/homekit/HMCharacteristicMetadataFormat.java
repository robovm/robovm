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
package org.robovm.apple.homekit;

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
/*</imports>*/

/*<javadoc>*/
/*</javadoc>*/
@Marshaler(HMCharacteristicMetadataFormat.Marshaler.class)
/*<annotations>*/@Library("HomeKit")/*</annotations>*/
/*<visibility>*/public/*</visibility>*/ class /*<name>*/HMCharacteristicMetadataFormat/*</name>*/ 
    extends /*<extends>*/CocoaUtility/*</extends>*/ 
    /*<implements>*//*</implements>*/ {
    
    public static class Marshaler {
        @MarshalsPointer
        public static HMCharacteristicMetadataFormat toObject(Class<HMCharacteristicMetadataFormat> cls, long handle, long flags) {
            NSString o = (NSString) NSObject.Marshaler.toObject(NSString.class, handle, flags);
            if (o == null) {
                return null;
            }
            return HMCharacteristicMetadataFormat.valueOf(o);
        }
        @MarshalsPointer
        public static long toNative(HMCharacteristicMetadataFormat o, long flags) {
            if (o == null) {
                return 0L;
            }
            return NSObject.Marshaler.toNative(o.value(), flags);
        }
    }

    /*<ptr>*/
    /*</ptr>*/
    /*<bind>*/static { Bro.bind(HMCharacteristicMetadataFormat.class); }/*</bind>*/
    /*<constants>*//*</constants>*/
    /**
     * @since Available in iOS 8.0 and later.
     */
    public static final HMCharacteristicMetadataFormat Bool = new HMCharacteristicMetadataFormat("BoolValue");
    /**
     * @since Available in iOS 8.0 and later.
     */
    public static final HMCharacteristicMetadataFormat Int = new HMCharacteristicMetadataFormat("IntValue");
    /**
     * @since Available in iOS 8.0 and later.
     */
    public static final HMCharacteristicMetadataFormat Float = new HMCharacteristicMetadataFormat("FloatValue");
    /**
     * @since Available in iOS 8.0 and later.
     */
    public static final HMCharacteristicMetadataFormat String = new HMCharacteristicMetadataFormat("StringValue");
    /**
     * @since Available in iOS 8.0 and later.
     */
    public static final HMCharacteristicMetadataFormat Array = new HMCharacteristicMetadataFormat("ArrayValue");
    /**
     * @since Available in iOS 8.0 and later.
     */
    public static final HMCharacteristicMetadataFormat Dictionary = new HMCharacteristicMetadataFormat("DictionaryValue");
    /**
     * @since Available in iOS 8.0 and later.
     */
    public static final HMCharacteristicMetadataFormat UInt8 = new HMCharacteristicMetadataFormat("UInt8Value");
    /**
     * @since Available in iOS 8.0 and later.
     */
    public static final HMCharacteristicMetadataFormat UInt16 = new HMCharacteristicMetadataFormat("UInt16Value");
    /**
     * @since Available in iOS 8.0 and later.
     */
    public static final HMCharacteristicMetadataFormat UInt32 = new HMCharacteristicMetadataFormat("UInt32Value");
    /**
     * @since Available in iOS 8.0 and later.
     */
    public static final HMCharacteristicMetadataFormat UInt64 = new HMCharacteristicMetadataFormat("UInt64Value");
    /**
     * @since Available in iOS 8.0 and later.
     */
    public static final HMCharacteristicMetadataFormat Data = new HMCharacteristicMetadataFormat("DataValue");
    /**
     * @since Available in iOS 8.0 and later.
     */
    public static final HMCharacteristicMetadataFormat TLV8 = new HMCharacteristicMetadataFormat("TLV8Value");
    
    private static HMCharacteristicMetadataFormat[] values = new HMCharacteristicMetadataFormat[] {Bool, Int, Float, String, 
        Array, Dictionary, UInt8, UInt16, UInt32, UInt64, Data, TLV8};
    private final LazyGlobalValue<NSString> lazyGlobalValue;
    
    private HMCharacteristicMetadataFormat(String getterName) {
        lazyGlobalValue = new LazyGlobalValue<>(getClass(), getterName);
    }
    /*<constructors>*//*</constructors>*/
    /*<properties>*//*</properties>*/
    /*<members>*//*</members>*/
    public NSString value() {
        return lazyGlobalValue.value();
    }
    
    public static HMCharacteristicMetadataFormat valueOf(NSString value) {
        for (HMCharacteristicMetadataFormat v : values) {
            if (v.value().equals(value)) {
                return v;
            }
        }
        throw new IllegalArgumentException("No constant with value " + value + " found in " 
            + /*<name>*/HMCharacteristicMetadataFormat/*</name>*/.class.getName());
    }
    /*<methods>*/
    /**
     * @since Available in iOS 8.0 and later.
     */
    @GlobalValue(symbol="HMCharacteristicMetadataFormatBool", optional=true)
    protected static native NSString BoolValue();
    /**
     * @since Available in iOS 8.0 and later.
     */
    @GlobalValue(symbol="HMCharacteristicMetadataFormatInt", optional=true)
    protected static native NSString IntValue();
    /**
     * @since Available in iOS 8.0 and later.
     */
    @GlobalValue(symbol="HMCharacteristicMetadataFormatFloat", optional=true)
    protected static native NSString FloatValue();
    /**
     * @since Available in iOS 8.0 and later.
     */
    @GlobalValue(symbol="HMCharacteristicMetadataFormatString", optional=true)
    protected static native NSString StringValue();
    /**
     * @since Available in iOS 8.0 and later.
     */
    @GlobalValue(symbol="HMCharacteristicMetadataFormatArray", optional=true)
    protected static native NSString ArrayValue();
    /**
     * @since Available in iOS 8.0 and later.
     */
    @GlobalValue(symbol="HMCharacteristicMetadataFormatDictionary", optional=true)
    protected static native NSString DictionaryValue();
    /**
     * @since Available in iOS 8.0 and later.
     */
    @GlobalValue(symbol="HMCharacteristicMetadataFormatUInt8", optional=true)
    protected static native NSString UInt8Value();
    /**
     * @since Available in iOS 8.0 and later.
     */
    @GlobalValue(symbol="HMCharacteristicMetadataFormatUInt16", optional=true)
    protected static native NSString UInt16Value();
    /**
     * @since Available in iOS 8.0 and later.
     */
    @GlobalValue(symbol="HMCharacteristicMetadataFormatUInt32", optional=true)
    protected static native NSString UInt32Value();
    /**
     * @since Available in iOS 8.0 and later.
     */
    @GlobalValue(symbol="HMCharacteristicMetadataFormatUInt64", optional=true)
    protected static native NSString UInt64Value();
    /**
     * @since Available in iOS 8.0 and later.
     */
    @GlobalValue(symbol="HMCharacteristicMetadataFormatData", optional=true)
    protected static native NSString DataValue();
    /**
     * @since Available in iOS 8.0 and later.
     */
    @GlobalValue(symbol="HMCharacteristicMetadataFormatTLV8", optional=true)
    protected static native NSString TLV8Value();
    /*</methods>*/
}
