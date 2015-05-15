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
@Marshaler(UIPasteboardChangedNotificationInfo.Marshaler.class)
/*<annotations>*/@Library("UIKit")/*</annotations>*/
/*<visibility>*/public/*</visibility>*/ class /*<name>*/UIPasteboardChangedNotificationInfo/*</name>*/ 
    extends /*<extends>*/CocoaUtility/*</extends>*/ 
    /*<implements>*//*</implements>*/ {

    public static class Marshaler {
        @SuppressWarnings("unchecked")
        @MarshalsPointer
        public static UIPasteboardChangedNotificationInfo toObject(Class<UIPasteboardChangedNotificationInfo> cls, long handle, long flags) {
            NSDictionary<NSString, NSObject> o = (NSDictionary<NSString, NSObject>) NSObject.Marshaler.toObject(NSDictionary.class, handle, flags);
            if (o == null) {
                return null;
            }
            return new UIPasteboardChangedNotificationInfo(o);
        }
        @MarshalsPointer
        public static long toNative(UIPasteboardChangedNotificationInfo o, long flags) {
            if (o == null) {
                return 0L;
            }
            return NSObject.Marshaler.toNative(o.data, flags);
        }
    }
    
    /*<ptr>*/
    /*</ptr>*/
    private NSDictionary<NSString, NSObject> data;
    
    protected UIPasteboardChangedNotificationInfo(NSDictionary<NSString, NSObject> data) {
        this.data = data;
    }
    /*<bind>*/static { Bro.bind(UIPasteboardChangedNotificationInfo.class); }/*</bind>*/
    /*<constants>*//*</constants>*/
    /*<constructors>*//*</constructors>*/
    /*<properties>*//*</properties>*/
    /*<members>*//*</members>*/
    public NSDictionary<NSString, NSObject> getDictionary() {
        return data;
    }
    
    @SuppressWarnings("unchecked")
    public List<String> getAddedTypes() {
        if (data.containsKey(TypesAddedKey())) {
            NSArray<NSString> val = (NSArray<NSString>)data.get(TypesAddedKey());
            return val.asStringList();
        }
        return null;
    }
    @SuppressWarnings("unchecked")
    public List<String> getRemovedTypes() {
        if (data.containsKey(TypesRemovedKey())) {
            NSArray<NSString> val = (NSArray<NSString>)data.get(TypesRemovedKey());
            return val.asStringList();
        }
        return null;
    }
    /*<methods>*/
    @GlobalValue(symbol="UIPasteboardChangedTypesAddedKey", optional=true)
    protected static native NSString TypesAddedKey();
    @GlobalValue(symbol="UIPasteboardChangedTypesRemovedKey", optional=true)
    protected static native NSString TypesRemovedKey();
    /*</methods>*/
    @Override
    public String toString() {
        if (data != null) return data.toString();
        return super.toString();
    }
}
