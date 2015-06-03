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
package org.robovm.apple.coreanimation;

/*<imports>*/
import java.io.*;
import java.nio.*;
import java.util.*;
import org.robovm.objc.*;
import org.robovm.objc.annotation.*;
import org.robovm.objc.block.*;
import org.robovm.rt.*;
import org.robovm.rt.annotation.*;
import org.robovm.rt.bro.*;
import org.robovm.rt.bro.annotation.*;
import org.robovm.rt.bro.ptr.*;
import org.robovm.apple.foundation.*;
import org.robovm.apple.coregraphics.*;
import org.robovm.apple.coreimage.*;
import org.robovm.apple.coretext.*;
import org.robovm.apple.opengles.*;
import org.robovm.apple.metal.*;
/*</imports>*/

/*<javadoc>*/
/*</javadoc>*/
@Marshaler(CATextTruncationMode.Marshaler.class)
/*<annotations>*/@Library("QuartzCore")/*</annotations>*/
/*<visibility>*/public/*</visibility>*/ class /*<name>*/CATextTruncationMode/*</name>*/ 
    extends /*<extends>*/CocoaUtility/*</extends>*/ 
    /*<implements>*//*</implements>*/ {

    public static class Marshaler {
        @MarshalsPointer
        public static CATextTruncationMode toObject(Class<CATextTruncationMode> cls, long handle, long flags) {
            NSString o = (NSString) NSObject.Marshaler.toObject(NSString.class, handle, flags);
            if (o == null) {
                return null;
            }
            return CATextTruncationMode.valueOf(o);
        }
        @MarshalsPointer
        public static long toNative(CATextTruncationMode o, long flags) {
            if (o == null) {
                return 0L;
            }
            return NSObject.Marshaler.toNative(o.value(), flags);
        }
    }
    
    /*<ptr>*/
    /*</ptr>*/
    /*<bind>*/static { Bro.bind(CATextTruncationMode.class); }/*</bind>*/
    /*<constants>*//*</constants>*/
    /**
     * @since Available in iOS 3.2 and later.
     */
    public static final CATextTruncationMode None = new CATextTruncationMode("NoneValue");
    /**
     * @since Available in iOS 3.2 and later.
     */
    public static final CATextTruncationMode Start = new CATextTruncationMode("StartValue");
    /**
     * @since Available in iOS 3.2 and later.
     */
    public static final CATextTruncationMode End = new CATextTruncationMode("EndValue");
    /**
     * @since Available in iOS 3.2 and later.
     */
    public static final CATextTruncationMode Middle = new CATextTruncationMode("MiddleValue");
    
    private static CATextTruncationMode[] values = new CATextTruncationMode[] {None, Start, End, Middle};
    private final LazyGlobalValue<NSString> lazyGlobalValue;
    
    private CATextTruncationMode(String getterName) {
        lazyGlobalValue = new LazyGlobalValue<>(getClass(), getterName);
    }
    /*<constructors>*//*</constructors>*/
    /*<properties>*//*</properties>*/
    /*<members>*//*</members>*/
    public NSString value() {
        return lazyGlobalValue.value();
    }
    
    public static CATextTruncationMode valueOf(NSString value) {
        for (CATextTruncationMode v : values) {
            if (v.value().equals(value)) {
                return v;
            }
        }
        throw new IllegalArgumentException("No constant with value " + value + " found in " 
            + /*<name>*/CATextTruncationMode/*</name>*/.class.getName());
    }
    /*<methods>*/
    /**
     * @since Available in iOS 3.2 and later.
     */
    @GlobalValue(symbol="kCATruncationNone", optional=true)
    protected static native NSString NoneValue();
    /**
     * @since Available in iOS 3.2 and later.
     */
    @GlobalValue(symbol="kCATruncationStart", optional=true)
    protected static native NSString StartValue();
    /**
     * @since Available in iOS 3.2 and later.
     */
    @GlobalValue(symbol="kCATruncationEnd", optional=true)
    protected static native NSString EndValue();
    /**
     * @since Available in iOS 3.2 and later.
     */
    @GlobalValue(symbol="kCATruncationMiddle", optional=true)
    protected static native NSString MiddleValue();
    /*</methods>*/
}
