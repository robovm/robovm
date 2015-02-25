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
package org.robovm.apple.iad;

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
import org.robovm.apple.uikit.*;
import org.robovm.apple.addressbook.*;
import org.robovm.apple.mediaplayer.*;
import org.robovm.apple.coregraphics.*;
import org.robovm.apple.avkit.*;
/*</imports>*/

/*<javadoc>*/
/*</javadoc>*/
@Marshaler(ADBannerContentSizeIdentifier.Marshaler.class)
/*<annotations>*/@Library("iAd")/*</annotations>*/
/*<visibility>*/public/*</visibility>*/ class /*<name>*/ADBannerContentSizeIdentifier/*</name>*/ 
    extends /*<extends>*/CocoaUtility/*</extends>*/ 
    /*<implements>*//*</implements>*/ {

    public static class Marshaler {
        @MarshalsPointer
        public static ADBannerContentSizeIdentifier toObject(Class<ADBannerContentSizeIdentifier> cls, long handle, long flags) {
            NSString o = (NSString) NSObject.Marshaler.toObject(NSString.class, handle, flags);
            if (o == null) {
                return null;
            }
            return ADBannerContentSizeIdentifier.valueOf(o);
        }
        @MarshalsPointer
        public static long toNative(ADBannerContentSizeIdentifier o, long flags) {
            if (o == null) {
                return 0L;
            }
            return NSObject.Marshaler.toNative(o.value(), flags);
        }
    }
    
    public static class AsListMarshaler {
        @SuppressWarnings("unchecked")
        @MarshalsPointer
        public static List<ADBannerContentSizeIdentifier> toObject(Class<? extends NSObject> cls, long handle, long flags) {
            NSArray<NSString> o = (NSArray<NSString>) NSObject.Marshaler.toObject(cls, handle, flags);
            if (o == null) {
                return null;
            }
            List<ADBannerContentSizeIdentifier> list = new ArrayList<>();
            for (NSString str : o) {
                list.add(ADBannerContentSizeIdentifier.valueOf(str));
            }
            return list;
        }
        @MarshalsPointer
        public static long toNative(List<ADBannerContentSizeIdentifier> l, long flags) {
            if (l == null) {
                return 0L;
            }
            NSMutableArray<NSString> array = new NSMutableArray<>();
            for (ADBannerContentSizeIdentifier i : l) {
                array.add(i.value());
            }
            return NSObject.Marshaler.toNative(array, flags);
        }
    }
    
    /*<ptr>*/
    /*</ptr>*/
    /*<bind>*/static { Bro.bind(ADBannerContentSizeIdentifier.class); }/*</bind>*/
    /*<constants>*//*</constants>*/
    /**
     * @since Available in iOS 4.2 and later.
     * @deprecated Deprecated in iOS 6.0.
     */
    @Deprecated
    public static final ADBannerContentSizeIdentifier SizePortrait = new ADBannerContentSizeIdentifier("SizePortraitValue");
    /**
     * @since Available in iOS 4.2 and later.
     * @deprecated Deprecated in iOS 6.0.
     */
    @Deprecated
    public static final ADBannerContentSizeIdentifier SizeLandscape = new ADBannerContentSizeIdentifier("SizeLandscapeValue");
    
    private static ADBannerContentSizeIdentifier[] values = new ADBannerContentSizeIdentifier[] {SizePortrait, SizeLandscape};
    private final LazyGlobalValue<NSString> lazyGlobalValue;
    
    private ADBannerContentSizeIdentifier (String getterName) {
        lazyGlobalValue = new LazyGlobalValue<>(getClass(), getterName);
    }
    /*<constructors>*//*</constructors>*/
    /*<properties>*//*</properties>*/
    /*<members>*//*</members>*/
    public NSString value() {
        return lazyGlobalValue.value();
    }
    /**
     * @since Available in iOS 4.0 and later.
     * @deprecated Deprecated in iOS 6.0.
     */
    @Deprecated
    public @ByVal CGSize toCGSize() {
        return ADBannerView.getSizeForIdentifier(value());
    }
    
    public static ADBannerContentSizeIdentifier valueOf(NSString value) {
        for (ADBannerContentSizeIdentifier v : values) {
            if (v.value().equals(value)) {
                return v;
            }
        }
        throw new IllegalArgumentException("No constant with value " + value + " found in " 
            + /*<name>*/ADBannerContentSizeIdentifier/*</name>*/.class.getName());
    }
    /*<methods>*/
    /**
     * @since Available in iOS 4.2 and later.
     * @deprecated Deprecated in iOS 6.0.
     */
    @Deprecated
    @GlobalValue(symbol="ADBannerContentSizeIdentifierPortrait", optional=true)
    protected static native NSString SizePortraitValue();
    /**
     * @since Available in iOS 4.2 and later.
     * @deprecated Deprecated in iOS 6.0.
     */
    @Deprecated
    @GlobalValue(symbol="ADBannerContentSizeIdentifierLandscape", optional=true)
    protected static native NSString SizeLandscapeValue();
    /*</methods>*/
}
