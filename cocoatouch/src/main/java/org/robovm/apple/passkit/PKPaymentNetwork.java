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
package org.robovm.apple.passkit;

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
import org.robovm.apple.addressbook.*;
/*</imports>*/

/*<javadoc>*/
/*</javadoc>*/
@Marshaler(PKPaymentNetwork.Marshaler.class)
/*<annotations>*/@Library("PassKit")/*</annotations>*/
/*<visibility>*/public/*</visibility>*/ class /*<name>*/PKPaymentNetwork/*</name>*/ 
    extends /*<extends>*/CocoaUtility/*</extends>*/ 
    /*<implements>*//*</implements>*/ {

    public static class Marshaler {
        @MarshalsPointer
        public static PKPaymentNetwork toObject(Class<PKPaymentNetwork> cls, long handle, long flags) {
            NSString o = (NSString) NSObject.Marshaler.toObject(NSString.class, handle, flags);
            if (o == null) {
                return null;
            }
            return PKPaymentNetwork.valueOf(o);
        }
        @MarshalsPointer
        public static long toNative(PKPaymentNetwork o, long flags) {
            if (o == null) {
                return 0L;
            }
            return NSObject.Marshaler.toNative(o.value(), flags);
        }
    }
    
    public static class AsListMarshaler {
        @SuppressWarnings("unchecked")
        @MarshalsPointer
        public static List<PKPaymentNetwork> toObject(Class<? extends NSObject> cls, long handle, long flags) {
            NSArray<NSString> o = (NSArray<NSString>) NSObject.Marshaler.toObject(cls, handle, flags);
            if (o == null) {
                return null;
            }
            List<PKPaymentNetwork> list = new ArrayList<>();
            for (NSString str : o) {
                list.add(PKPaymentNetwork.valueOf(str));
            }
            return list;
        }
        @MarshalsPointer
        public static long toNative(List<PKPaymentNetwork> l, long flags) {
            if (l == null) {
                return 0L;
            }
            NSMutableArray<NSString> array = new NSMutableArray<>();
            for (PKPaymentNetwork i : l) {
                array.add(i.value());
            }
            return NSObject.Marshaler.toNative(array, flags);
        }
    }
    
    /*<ptr>*/
    /*</ptr>*/
    /*<bind>*/static { Bro.bind(PKPaymentNetwork.class); }/*</bind>*/
    /*<constants>*//*</constants>*/
    public static final PKPaymentNetwork Amex = new PKPaymentNetwork("AmexValue");
    public static final PKPaymentNetwork MasterCard = new PKPaymentNetwork("MasterCardValue");
    public static final PKPaymentNetwork Visa = new PKPaymentNetwork("VisaValue");
    
    private static PKPaymentNetwork[] values = new PKPaymentNetwork[] {Amex, MasterCard, Visa};
    private final LazyGlobalValue<NSString> lazyGlobalValue;
    
    private PKPaymentNetwork(String getterName) {
        lazyGlobalValue = new LazyGlobalValue<>(getClass(), getterName);
    }
    /*<constructors>*//*</constructors>*/
    /*<properties>*//*</properties>*/
    /*<members>*//*</members>*/
    public NSString value() {
        return lazyGlobalValue.value();
    }
    
    public static PKPaymentNetwork valueOf(NSString value) {
        for (PKPaymentNetwork v : values) {
            if (v.value().equals(value)) {
                return v;
            }
        }
        throw new IllegalArgumentException("No constant with value " + value + " found in " 
            + /*<name>*/PKPaymentNetwork/*</name>*/.class.getName());
    }
    /*<methods>*/
    @GlobalValue(symbol="PKPaymentNetworkAmex", optional=true)
    protected static native NSString AmexValue();
    @GlobalValue(symbol="PKPaymentNetworkMasterCard", optional=true)
    protected static native NSString MasterCardValue();
    @GlobalValue(symbol="PKPaymentNetworkVisa", optional=true)
    protected static native NSString VisaValue();
    /*</methods>*/
}
