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
@Marshaler(NSFileSystemAttributes.Marshaler.class)
/*<annotations>*/@Library("Foundation")/*</annotations>*/
/*<visibility>*/public/*</visibility>*/ class /*<name>*/NSFileSystemAttributes/*</name>*/ 
    extends /*<extends>*/Object/*</extends>*/ 
    /*<implements>*//*</implements>*/ {
    
    public static class Marshaler {
        @SuppressWarnings("unchecked")
        @MarshalsPointer
        public static NSFileSystemAttributes toObject(Class<NSFileSystemAttributes> cls, long handle, long flags) {
            NSDictionary<NSString, NSObject> o = (NSDictionary<NSString, NSObject>) NSObject.Marshaler.toObject(NSDictionary.class, handle, flags);
            if (o == null) {
                return null;
            }
            return new NSFileSystemAttributes(o);
        }
        @MarshalsPointer
        public static long toNative(NSFileSystemAttributes o, long flags) {
            if (o == null) {
                return 0L;
            }
            return NSObject.Marshaler.toNative(o.data, flags);
        }
    }

    /*<ptr>*/
    /*</ptr>*/
    private NSDictionary<NSString, NSObject> data;
    
    protected NSFileSystemAttributes(NSDictionary<NSString, NSObject> data) {
        this.data = data;
    }
    public NSFileSystemAttributes() {
        this.data = new NSMutableDictionary<>();
    }
    /*<bind>*/static { Bro.bind(NSFileSystemAttributes.class); }/*</bind>*/
    /*<constants>*//*</constants>*/
    /*<constructors>*//*</constructors>*/
    /*<properties>*//*</properties>*/
    /*<members>*//*</members>*/
    public NSDictionary<NSString, NSObject> getDictionary() {
        return data;
    }
    
    public NSFileSystemAttributes set(NSFileSystemAttribute attribute, NSObject value) {
        data.put(attribute.value(), value);
        return this;
    }
    public NSObject get(NSFileSystemAttribute attribute) {
        return data.get(attribute.value());
    }
    public boolean contains(NSFileSystemAttribute attribute) {
        return data.containsKey(attribute.value());
    }
    
    
    public long getSize() {
        if (contains(NSFileSystemAttribute.Size)) {
            NSNumber val = (NSNumber)get(NSFileSystemAttribute.Size);
            return val.longValue();
        }
        return 0;
    }
    public NSFileSystemAttributes setSize(long size) {
        set(NSFileSystemAttribute.Size, NSNumber.valueOf(size));
        return this;
    }
    public long getFreeSize() {
        if (contains(NSFileSystemAttribute.FreeSize)) {
            NSNumber val = (NSNumber)get(NSFileSystemAttribute.FreeSize);
            return val.longValue();
        }
        return 0;
    }
    public NSFileSystemAttributes setFreeSize(long size) {
        set(NSFileSystemAttribute.FreeSize, NSNumber.valueOf(size));
        return this;
    }
    public long getNodes() {
        if (contains(NSFileSystemAttribute.Nodes)) {
            NSNumber val = (NSNumber)get(NSFileSystemAttribute.Nodes);
            return val.longValue();
        }
        return 0;
    }
    public NSFileSystemAttributes setNodes(long nodes) {
        set(NSFileSystemAttribute.Nodes, NSNumber.valueOf(nodes));
        return this;
    }
    public long getFreeNodes() {
        if (contains(NSFileSystemAttribute.FreeNodes)) {
            NSNumber val = (NSNumber)get(NSFileSystemAttribute.FreeNodes);
            return val.longValue();
        }
        return 0;
    }
    public NSFileSystemAttributes setFreeNodes(long nodes) {
        set(NSFileSystemAttribute.FreeNodes, NSNumber.valueOf(nodes));
        return this;
    }
    public long getSystemNumber() {
        if (contains(NSFileSystemAttribute.Number)) {
            NSNumber val = (NSNumber)get(NSFileSystemAttribute.Number);
            return val.longValue();
        }
        return 0;
    }
    public NSFileSystemAttributes setSystemNumber(long number) {
        set(NSFileSystemAttribute.Number, NSNumber.valueOf(number));
        return this;
    }
    /*<methods>*/
    /*</methods>*/
    
    @Override
    public String toString() {
        if (data != null) return data.toString();
        return super.toString();
    }
}
