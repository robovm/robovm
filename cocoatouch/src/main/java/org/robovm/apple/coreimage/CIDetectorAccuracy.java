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
package org.robovm.apple.coreimage;

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
import org.robovm.apple.coregraphics.*;
import org.robovm.apple.foundation.*;
import org.robovm.apple.corefoundation.*;
import org.robovm.apple.opengles.*;
import org.robovm.apple.corevideo.*;
import org.robovm.apple.imageio.*;
/*</imports>*/

/*<javadoc>*/
/*</javadoc>*/
@Marshaler(CIDetectorAccuracy.Marshaler.class)
/*<annotations>*/@Library("CoreImage")/*</annotations>*/
/*<visibility>*/public/*</visibility>*/ class /*<name>*/CIDetectorAccuracy/*</name>*/ 
    extends /*<extends>*/CocoaUtility/*</extends>*/ 
    /*<implements>*//*</implements>*/ {
    
    public static class Marshaler {
        @MarshalsPointer
        public static CIDetectorAccuracy toObject(Class<CIDetectorAccuracy> cls, long handle, long flags) {
            NSString o = (NSString) NSObject.Marshaler.toObject(NSString.class, handle, flags);
            if (o == null) {
                return null;
            }
            return CIDetectorAccuracy.valueOf(o);
        }
        @MarshalsPointer
        public static long toNative(CIDetectorAccuracy o, long flags) {
            if (o == null) {
                return 0L;
            }
            return NSObject.Marshaler.toNative(o.value(), flags);
        }
    }

    /*<ptr>*/
    /*</ptr>*/
    /*<bind>*/static { Bro.bind(CIDetectorAccuracy.class); }/*</bind>*/
    /*<constants>*//*</constants>*/
    /**
     * @since Available in iOS 5.0 and later.
     */
    public static final CIDetectorAccuracy Low = new CIDetectorAccuracy("LowValue");
    /**
     * @since Available in iOS 5.0 and later.
     */
    public static final CIDetectorAccuracy High = new CIDetectorAccuracy("HighValue");
    
    private static CIDetectorAccuracy[] values = new CIDetectorAccuracy[] {Low, High};
    private final LazyGlobalValue<NSString> lazyGlobalValue;
    
    private CIDetectorAccuracy(String getterName) {
        lazyGlobalValue = new LazyGlobalValue<>(getClass(), getterName);
    }
    /*<constructors>*//*</constructors>*/
    /*<properties>*//*</properties>*/
    /*<members>*//*</members>*/
    public NSString value() {
        return lazyGlobalValue.value();
    }
    
    public static CIDetectorAccuracy valueOf(NSString value) {
        for (CIDetectorAccuracy v : values) {
            if (v.value().equals(value)) {
                return v;
            }
        }
        throw new IllegalArgumentException("No constant with value " + value + " found in " 
            + /*<name>*/CIDetectorAccuracy/*</name>*/.class.getName());
    }
    /*<methods>*/
    /**
     * @since Available in iOS 5.0 and later.
     */
    @GlobalValue(symbol="CIDetectorAccuracyLow", optional=true)
    protected static native NSString LowValue();
    /**
     * @since Available in iOS 5.0 and later.
     */
    @GlobalValue(symbol="CIDetectorAccuracyHigh", optional=true)
    protected static native NSString HighValue();
    /*</methods>*/
}
