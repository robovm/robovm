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
/*</imports>*/

/*<javadoc>*/
/*</javadoc>*/
/**
 * @since Available in iOS 7.0 and later.
 */
@Marshaler(NSURLProperties.Marshaler.class)
/*<annotations>*/@Library("Foundation")/*</annotations>*/
/*<visibility>*/public/*</visibility>*/ class NSURLProperties 
    extends /*<extends>*/Object/*</extends>*/ 
    /*<implements>*//*</implements>*/ {

    public static class Marshaler {
        @SuppressWarnings("unchecked")
        @MarshalsPointer
        public static NSURLProperties toObject(Class<NSURLProperties> cls, long handle, long flags) {
            NSDictionary<NSString, NSObject> o = (NSDictionary<NSString, NSObject>) NSObject.Marshaler.toObject(NSDictionary.class, handle, flags);
            if (o == null) {
                return null;
            }
            return new NSURLProperties(o);
        }
        @MarshalsPointer
        public static long toNative(NSURLProperties o, long flags) {
            if (o == null) {
                return 0L;
            }
            return NSObject.Marshaler.toNative(o.data, flags);
        }
    }
    
    /*<ptr>*/
    /*</ptr>*/
    private NSDictionary<NSString, NSObject> data;
    
    protected NSURLProperties(NSDictionary<NSString, NSObject> data) {
        this.data = data;
    }
    /*<bind>*/static { Bro.bind(NSURLProperties.class); }/*</bind>*/
    /*<constants>*//*</constants>*/
    /*<constructors>*//*</constructors>*/
    /*<properties>*//*</properties>*/
    /*<members>*//*</members>*/
    public NSDictionary<NSString, NSObject> getDictionary() {
        return data;
    }
    
    public NSObject get(String property) {
        return data.get(new NSString(property));
    }
    public NSObject get(NSURLFileSystemProperty property) {
        return data.get(property.value());
    }
    public NSObject get(NSURLFileProperty property) {
        return data.get(property.value());
    }
    public NSObject get(NSURLVolumeProperty property) {
        return data.get(property.value());
    }
    public NSObject get(NSURLUbiquitousItemProperty property) {
        return data.get(property.value());
    }
    
    public boolean contains(String property) {
        return data.containsKey(new NSString(property));
    }
    public boolean contains(NSURLFileSystemProperty property) {
        return data.containsKey(property.value());
    }
    public boolean contains(NSURLFileProperty property) {
        return data.containsKey(property.value());
    }
    public boolean contains(NSURLVolumeProperty property) {
        return data.containsKey(property.value());
    }
    public boolean contains(NSURLUbiquitousItemProperty property) {
        return data.containsKey(property.value());
    }
    
    public NSURLProperties put(String property, NSObject value) {
        data.put(new NSString(property), value);
        return this;
    }
    public NSURLProperties put(NSURLFileSystemProperty property, NSObject value) {
        data.put(property.value(), value);
        return this;
    }
    public NSURLProperties put(NSURLFileProperty property, NSObject value) {
        data.put(property.value(), value);
        return this;
    }
    public NSURLProperties put(NSURLVolumeProperty property, NSObject value) {
        data.put(property.value(), value);
        return this;
    }
    public NSURLProperties put(NSURLUbiquitousItemProperty property, NSObject value) {
        data.put(property.value(), value);
        return this;
    }
    
    
    /* NSURLFileSystemProperty */
    // TODO
    /* NSURLFileProperty */
    
    /* NSURLVolumeProperty */
    
    /* NSURLUbiquitousItemProperty */
    
    /*<methods>*/
    /*</methods>*/
    @Override
    public String toString() {
        if (data != null) return data.toString();
        return super.toString();
    }
}
