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
package org.robovm.apple.uikit;

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
import org.robovm.apple.coreanimation.*;
import org.robovm.apple.coregraphics.*;
import org.robovm.apple.coredata.*;
import org.robovm.apple.coreimage.*;
import org.robovm.apple.coretext.*;
import org.robovm.apple.corelocation.*;
/*</imports>*/

/*<javadoc>*/
/*</javadoc>*/
/**
 * @since Available in iOS 7.0 and later.
 */
@Marshaler(NSTextTabOptions.Marshaler.class)
/*<annotations>*/@Library("UIKit")/*</annotations>*/
/*<visibility>*/public/*</visibility>*/ class /*<name>*/NSTextTabOptions/*</name>*/ 
    extends /*<extends>*/CocoaUtility/*</extends>*/ 
    /*<implements>*//*</implements>*/ {

    public static class Marshaler {
        @SuppressWarnings("unchecked")
        @MarshalsPointer
        public static NSTextTabOptions toObject(Class<NSTextTabOptions> cls, long handle, long flags) {
            NSDictionary<NSString, NSObject> o = (NSDictionary<NSString, NSObject>) NSObject.Marshaler.toObject(NSDictionary.class, handle, flags);
            if (o == null) {
                return null;
            }
            return new NSTextTabOptions(o);
        }
        @MarshalsPointer
        public static long toNative(NSTextTabOptions o, long flags) {
            if (o == null) {
                return 0L;
            }
            return NSObject.Marshaler.toNative(o.data, flags);
        }
    }
    
    /*<ptr>*/
    /*</ptr>*/
    private NSDictionary<NSString, NSObject> data;
    
    protected NSTextTabOptions(NSDictionary<NSString, NSObject> data) {
        this.data = data;
    }
    public NSTextTabOptions() {
        this.data = new NSMutableDictionary<>();
    }
    /*<bind>*/static { Bro.bind(NSTextTabOptions.class); }/*</bind>*/
    /*<constants>*//*</constants>*/
    /*<constructors>*//*</constructors>*/
    /*<properties>*//*</properties>*/
    /*<members>*//*</members>*/
    /**
     * @since Available in iOS 7.0 and later.
     */
    public NSCharacterSet getTabColumnTerminatingCharacters() {
        if (data.containsKey(TabColumnTerminatorsAttribute())) {
            NSCharacterSet val = (NSCharacterSet) data.get(TabColumnTerminatorsAttribute());
            return val;
        }
        return null;
    }
    /**
     * @since Available in iOS 7.0 and later.
     */
    public NSTextTabOptions setTabColumnTerminatingCharacters(NSCharacterSet characters) {
        data.put(TabColumnTerminatorsAttribute(), characters);
        return this;
    }
    /*<methods>*/
    /**
     * @since Available in iOS 7.0 and later.
     */
    @GlobalValue(symbol="NSTabColumnTerminatorsAttributeName", optional=true)
    protected static native NSString TabColumnTerminatorsAttribute();
    /*</methods>*/
    
    @Override
    public String toString() {
        if (data != null) return data.toString();
        return super.toString();
    }
}
