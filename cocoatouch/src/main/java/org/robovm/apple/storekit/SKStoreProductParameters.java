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
package org.robovm.apple.storekit;

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
import org.robovm.apple.uikit.*;
import org.robovm.apple.accounts.*;
/*</imports>*/

/*<javadoc>*/
/*</javadoc>*/
@Marshaler(SKStoreProductParameters.Marshaler.class)
/*<annotations>*/@Library("StoreKit")/*</annotations>*/
/*<visibility>*/public/*</visibility>*/ class /*<name>*/SKStoreProductParameters/*</name>*/ 
    extends /*<extends>*/Object/*</extends>*/ 
    /*<implements>*//*</implements>*/ {

    public static class Marshaler {
        @SuppressWarnings("unchecked")
        @MarshalsPointer
        public static SKStoreProductParameters toObject(Class<SKStoreProductParameters> cls, long handle, long flags) {
            NSDictionary<NSString, NSObject> o = (NSDictionary<NSString, NSObject>) NSObject.Marshaler.toObject(NSDictionary.class, handle, flags);
            if (o == null) {
                return null;
            }
            return new SKStoreProductParameters(o);
        }
        @MarshalsPointer
        public static long toNative(SKStoreProductParameters o, long flags) {
            if (o == null) {
                return 0L;
            }
            return NSObject.Marshaler.toNative(o.data, flags);
        }
    }
    
    /*<ptr>*/
    /*</ptr>*/
    private NSDictionary<NSString, NSObject> data;
    
    protected SKStoreProductParameters(NSDictionary<NSString, NSObject> data) {
        this.data = data;
    }
    public SKStoreProductParameters() {
        data = new NSMutableDictionary<>();
    }
    /**
     * @since Available in iOS 6.0 and later.
     */
    public SKStoreProductParameters(String iTunesItemIdentifier) {
        data = new NSMutableDictionary<>();
        setITunesItemIdentifier(iTunesItemIdentifier);
    }
    /*<bind>*/static { Bro.bind(SKStoreProductParameters.class); }/*</bind>*/
    /*<constants>*//*</constants>*/
    /*<constructors>*//*</constructors>*/
    /*<properties>*//*</properties>*/
    /*<members>*//*</members>*/
    public NSDictionary<NSString, NSObject> getDictionary() {
        return data;
    }
    
    /**
     * @since Available in iOS 6.0 and later.
     */
    public String getITunesItemIdentifier() {
        if (data.containsKey(ITunesItemIdentifierKey())) {
            NSString val = (NSString)data.get(ITunesItemIdentifierKey());
            return val.toString();
        }
        return null;
    }
    public SKStoreProductParameters setITunesItemIdentifier(String identifier) {
        data.put(ITunesItemIdentifierKey(), new NSString(identifier));
        return this;
    }
    /*<methods>*/
    /**
     * @since Available in iOS 6.0 and later.
     */
    @GlobalValue(symbol="SKStoreProductParameterITunesItemIdentifier", optional=true)
    protected static native NSString ITunesItemIdentifierKey();
    /*</methods>*/
    
    @Override
    public String toString() {
        if (data != null) return data.toString();
        return super.toString();
    }
}
