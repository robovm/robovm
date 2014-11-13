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
package org.robovm.apple.foundation;

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
import org.robovm.apple.corefoundation.*;
import org.robovm.apple.uikit.*;
import org.robovm.apple.coreanimation.*;
import org.robovm.apple.coregraphics.*;
import org.robovm.apple.coremedia.*;
import org.robovm.apple.security.*;
import org.robovm.apple.dispatch.*;
/*</imports>*/

/*<javadoc>*/
/*</javadoc>*/
@Marshaler(NSItemProviderOptions.Marshaler.class)
/*<annotations>*/@Library("Foundation")/*</annotations>*/
/*<visibility>*/public/*</visibility>*/ class /*<name>*/NSItemProviderOptions/*</name>*/ 
    extends /*<extends>*/Object/*</extends>*/ 
    /*<implements>*//*</implements>*/ {

    public static class Marshaler {
        @SuppressWarnings("unchecked")
        @MarshalsPointer
        public static NSItemProviderOptions toObject(Class<NSItemProviderOptions> cls, long handle, long flags) {
            NSDictionary<NSString, NSObject> o = (NSDictionary<NSString, NSObject>) NSObject.Marshaler.toObject(NSDictionary.class, handle, flags);
            if (o == null) {
                return null;
            }
            return new NSItemProviderOptions(o);
        }
        @MarshalsPointer
        public static long toNative(NSItemProviderOptions o, long flags) {
            if (o == null) {
                return 0L;
            }
            return NSObject.Marshaler.toNative(o.data, flags);
        }
    }
    
    /*<ptr>*/
    /*</ptr>*/
    private NSDictionary<NSString, NSObject> data;
    
    protected NSItemProviderOptions(NSDictionary<NSString, NSObject> data) {
        this.data = data;
    }
    /*<bind>*/static { Bro.bind(NSItemProviderOptions.class); }/*</bind>*/
    /*<constants>*//*</constants>*/
    /*<constructors>*//*</constructors>*/
    /*<properties>*//*</properties>*/
    /*<members>*//*</members>*/
    public NSDictionary<NSString, NSObject> getDictionary() {
        return data;
    }
    
    public NSObject get(String key) {
        if (contains(key)) {
            return data.get(new NSString(key));
        }
        return null;
    }
    public NSItemProviderOptions put(String key, NSObject value) {
        data.put(new NSString(key), value);
        return this;
    }
    public boolean contains(String key) {
        return data.containsKey(new NSString(key));
    }
    
    
    /**
     * @since Available in iOS 8.0 and later.
     */
    public CGSize getPreferredImageSize() {
        if (data.containsKey(PreferredImageSizeKey())) {
            NSValue val = (NSValue)data.get(PreferredImageSizeKey());
            return val.sizeValue();
        }
        return null;
    }
    /**
     * @since Available in iOS 8.0 and later.
     */
    public NSItemProviderOptions setPreferredImageSize(CGSize size) {
        data.put(PreferredImageSizeKey(), NSValue.valueOf(size));
        return this;
    }
    /*<methods>*/
    /**
     * @since Available in iOS 8.0 and later.
     */
    @GlobalValue(symbol="NSItemProviderPreferredImageSizeKey", optional=true)
    protected static native NSString PreferredImageSizeKey();
    /*</methods>*/
    @Override
    public String toString() {
        if (data != null) return data.toString();
        return super.toString();
    }
}
