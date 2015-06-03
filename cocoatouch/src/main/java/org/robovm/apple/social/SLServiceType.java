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
package org.robovm.apple.social;

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
import org.robovm.apple.uikit.*;
import org.robovm.apple.coregraphics.*;
import org.robovm.apple.accounts.*;
/*</imports>*/

/*<javadoc>*/
/*</javadoc>*/
@Marshaler(SLServiceType.Marshaler.class)
/*<annotations>*/@Library("Social")/*</annotations>*/
/*<visibility>*/public/*</visibility>*/ class /*<name>*/SLServiceType/*</name>*/ 
    extends /*<extends>*/CocoaUtility/*</extends>*/ 
    /*<implements>*//*</implements>*/ {

    public static class Marshaler {
        @MarshalsPointer
        public static SLServiceType toObject(Class<SLServiceType> cls, long handle, long flags) {
            NSString o = (NSString) NSObject.Marshaler.toObject(NSString.class, handle, flags);
            if (o == null) {
                return null;
            }
            return SLServiceType.valueOf(o);
        }
        @MarshalsPointer
        public static long toNative(SLServiceType o, long flags) {
            if (o == null) {
                return 0L;
            }
            return NSObject.Marshaler.toNative(o.value(), flags);
        }
    }
    
    /*<ptr>*/
    /*</ptr>*/
    /*<bind>*/static { Bro.bind(SLServiceType.class); }/*</bind>*/
    /*<constants>*//*</constants>*/
    /**
     * @since Available in iOS 6.0 and later.
     */
    public static final SLServiceType Twitter = new SLServiceType("TwitterValue");
    /**
     * @since Available in iOS 6.0 and later.
     */
    public static final SLServiceType Facebook = new SLServiceType("FacebookValue");
    /**
     * @since Available in iOS 6.0 and later.
     */
    public static final SLServiceType SinaWeibo = new SLServiceType("SinaWeiboValue");
    /**
     * @since Available in iOS 7.0 and later.
     */
    public static final SLServiceType TencentWeibo = new SLServiceType("TencentWeiboValue");
    private static SLServiceType[] values = new SLServiceType[] {Twitter, Facebook, SinaWeibo, TencentWeibo};
    
    private final LazyGlobalValue<NSString> lazyGlobalValue;
    
    private SLServiceType (String getterName) {
        lazyGlobalValue = new LazyGlobalValue<>(getClass(), getterName);
    }
    /*<constructors>*//*</constructors>*/
    /*<properties>*//*</properties>*/
    /*<members>*//*</members>*/
    public NSString value() {
        return lazyGlobalValue.value();
    }
    
    public static SLServiceType valueOf(NSString value) {
        for (SLServiceType v : values) {
            if (v.value().equals(value)) {
                return v;
            }
        }
        throw new IllegalArgumentException("No constant with value " + value + " found in " 
            + /*<name>*/SLServiceType/*</name>*/.class.getName());
    }
    /*<methods>*/
    /**
     * @since Available in iOS 6.0 and later.
     */
    @GlobalValue(symbol="SLServiceTypeTwitter", optional=true)
    protected static native NSString TwitterValue();
    /**
     * @since Available in iOS 6.0 and later.
     */
    @GlobalValue(symbol="SLServiceTypeFacebook", optional=true)
    protected static native NSString FacebookValue();
    /**
     * @since Available in iOS 6.0 and later.
     */
    @GlobalValue(symbol="SLServiceTypeSinaWeibo", optional=true)
    protected static native NSString SinaWeiboValue();
    /**
     * @since Available in iOS 7.0 and later.
     */
    @GlobalValue(symbol="SLServiceTypeTencentWeibo", optional=true)
    protected static native NSString TencentWeiboValue();
    /*</methods>*/
}
