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
package org.robovm.apple.coremedia;

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
import org.robovm.apple.corefoundation.*;
import org.robovm.apple.dispatch.*;
import org.robovm.apple.coreaudio.*;
import org.robovm.apple.coreanimation.*;
import org.robovm.apple.coregraphics.*;
import org.robovm.apple.corevideo.*;
import org.robovm.apple.audiotoolbox.*;
/*</imports>*/

/*<javadoc>*/
/*</javadoc>*/
@Marshaler(CMMetadataKeySpace.Marshaler.class)
/*<annotations>*/@Library("CoreMedia")/*</annotations>*/
/*<visibility>*/public/*</visibility>*/ class /*<name>*/CMMetadataKeySpace/*</name>*/ 
    extends /*<extends>*/Object/*</extends>*/ 
    /*<implements>*//*</implements>*/ {

    public static class Marshaler {
        @MarshalsPointer
        public static CMMetadataKeySpace toObject(Class<CMMetadataKeySpace> cls, long handle, long flags) {
            CFString o = (CFString) CFType.Marshaler.toObject(CFString.class, handle, flags);
            if (o == null) {
                return null;
            }
            return CMMetadataKeySpace.valueOf(o);
        }
        @MarshalsPointer
        public static long toNative(CMMetadataKeySpace o, long flags) {
            if (o == null) {
                return 0L;
            }
            return CFType.Marshaler.toNative(o.value(), flags);
        }
    }
    
    /*<ptr>*/
    /*</ptr>*/
    /*<bind>*/static { Bro.bind(CMMetadataKeySpace.class); }/*</bind>*/
    /*<constants>*//*</constants>*/
    /**
     * @since Available in iOS 8.0 and later.
     */
    public static final CMMetadataKeySpace QuickTimeUserData = new CMMetadataKeySpace("QuickTimeUserDataValue");
    /**
     * @since Available in iOS 8.0 and later.
     */
    public static final CMMetadataKeySpace ISOUserData = new CMMetadataKeySpace("ISOUserDataValue");
    /**
     * @since Available in iOS 8.0 and later.
     */
    public static final CMMetadataKeySpace QuickTimeMetadata = new CMMetadataKeySpace("QuickTimeMetadataValue");
    /**
     * @since Available in iOS 8.0 and later.
     */
    public static final CMMetadataKeySpace iTunes = new CMMetadataKeySpace("iTunesValue");
    /**
     * @since Available in iOS 8.0 and later.
     */
    public static final CMMetadataKeySpace ID3 = new CMMetadataKeySpace("ID3Value");
    /**
     * @since Available in iOS 8.0 and later.
     */
    public static final CMMetadataKeySpace Icy = new CMMetadataKeySpace("IcyValue");
    
    private static CMMetadataKeySpace[] values = new CMMetadataKeySpace[] {QuickTimeUserData, ISOUserData, QuickTimeMetadata, 
        iTunes, ID3, Icy};
    private final LazyGlobalValue<CFString> lazyGlobalValue;
    
    private CMMetadataKeySpace(String getterName) {
        lazyGlobalValue = new LazyGlobalValue<>(getClass(), getterName);
    }
    /*<constructors>*//*</constructors>*/
    /*<properties>*//*</properties>*/
    /*<members>*//*</members>*/
    public CFString value() {
        return lazyGlobalValue.value();
    }
    
    public static CMMetadataKeySpace valueOf(CFString value) {
        for (CMMetadataKeySpace v : values) {
            if (v.value().equals(value)) {
                return v;
            }
        }
        throw new IllegalArgumentException("No constant with value " + value + " found in " 
            + /*<name>*/CMMetadataKeySpace/*</name>*/.class.getName());
    }
    /*<methods>*/
    /**
     * @since Available in iOS 8.0 and later.
     */
    @GlobalValue(symbol="kCMMetadataKeySpace_QuickTimeUserData", optional=true)
    protected static native CFString QuickTimeUserDataValue();
    /**
     * @since Available in iOS 8.0 and later.
     */
    @GlobalValue(symbol="kCMMetadataKeySpace_ISOUserData", optional=true)
    protected static native CFString ISOUserDataValue();
    /**
     * @since Available in iOS 8.0 and later.
     */
    @GlobalValue(symbol="kCMMetadataKeySpace_QuickTimeMetadata", optional=true)
    protected static native CFString QuickTimeMetadataValue();
    /**
     * @since Available in iOS 8.0 and later.
     */
    @GlobalValue(symbol="kCMMetadataKeySpace_iTunes", optional=true)
    protected static native CFString iTunesValue();
    /**
     * @since Available in iOS 8.0 and later.
     */
    @GlobalValue(symbol="kCMMetadataKeySpace_ID3", optional=true)
    protected static native CFString ID3Value();
    /**
     * @since Available in iOS 8.0 and later.
     */
    @GlobalValue(symbol="kCMMetadataKeySpace_Icy", optional=true)
    protected static native CFString IcyValue();
    /*</methods>*/
}
