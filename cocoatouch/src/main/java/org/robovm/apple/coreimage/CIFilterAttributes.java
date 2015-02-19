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
@Marshaler(CIFilterAttributes.Marshaler.class)
/*<annotations>*/@Library("CoreImage")/*</annotations>*/
/*<visibility>*/public/*</visibility>*/ class /*<name>*/CIFilterAttributes/*</name>*/ 
    extends /*<extends>*/CocoaUtility/*</extends>*/ 
    /*<implements>*//*</implements>*/ {

    public static class Marshaler {
        @SuppressWarnings("unchecked")
        @MarshalsPointer
        public static CIFilterAttributes toObject(Class<CIFilterAttributes> cls, long handle, long flags) {
            NSDictionary<NSString, NSObject> o = (NSDictionary<NSString, NSObject>) NSObject.Marshaler.toObject(NSDictionary.class, handle, flags);
            if (o == null) {
                return null;
            }
            return new CIFilterAttributes(o);
        }
        @MarshalsPointer
        public static long toNative(CIFilterAttributes o, long flags) {
            if (o == null) {
                return 0L;
            }
            return NSObject.Marshaler.toNative(o.data, flags);
        }
    }
    
    /*<ptr>*/
    /*</ptr>*/
    private NSDictionary<NSString, NSObject> data;
    
    protected CIFilterAttributes(NSDictionary<NSString, NSObject> data) {
        this.data = data;
    }
    /*<bind>*/static { Bro.bind(CIFilterAttributes.class); }/*</bind>*/
    /*<constants>*//*</constants>*/
    /*<constructors>*//*</constructors>*/
    /*<properties>*//*</properties>*/
    /*<members>*//*</members>*/
    public NSDictionary<NSString, NSObject> getDictionary() {
        return data;
    }
    
    
    @SuppressWarnings("unchecked")
    public CIFilterAttribute getAttribute(String name) {
        NSString str = new NSString(name);
        if (data.containsKey(str)) {
            NSDictionary<NSString, NSObject> val = (NSDictionary<NSString, NSObject>)data.get(str);
            return new CIFilterAttribute(val);
        }
        return null;
    }
    
    public String getName() {
        if (data.containsKey(NameKey())) {
            NSString val = (NSString)data.get(NameKey());
            return val.toString();
        }
        return null;
    }
    public String getDisplayName() {
        if (data.containsKey(DisplayNameKey())) {
            NSString val = (NSString)data.get(DisplayNameKey());
            return val.toString();
        }
        return null;
    }
    @SuppressWarnings("unchecked")
    public List<CIFilterCategory> getCategories() {
        if (data.containsKey(CategoriesKey())) {
            NSArray<NSString> val = (NSArray<NSString>)data.get(CategoriesKey());
            List<CIFilterCategory> list = new ArrayList<>();
            for (NSString str : val) {
                list.add(CIFilterCategory.valueOf(str));
            }
            return list;
        }
        return null;
    }
    
    /*<methods>*/
    @GlobalValue(symbol="kCIAttributeFilterName", optional=true)
    protected static native NSString NameKey();
    @GlobalValue(symbol="kCIAttributeFilterDisplayName", optional=true)
    protected static native NSString DisplayNameKey();
    @GlobalValue(symbol="kCIAttributeFilterCategories", optional=true)
    protected static native NSString CategoriesKey();
    /*</methods>*/
    @Override
    public String toString() {
        if (data != null) return data.toString();
        return super.toString();
    }
}
