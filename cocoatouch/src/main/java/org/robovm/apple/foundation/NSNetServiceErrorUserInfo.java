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
package org.robovm.apple.foundation;

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
import org.robovm.apple.corefoundation.*;
import org.robovm.apple.uikit.*;
import org.robovm.apple.coretext.*;
import org.robovm.apple.coreanimation.*;
import org.robovm.apple.coredata.*;
import org.robovm.apple.coregraphics.*;
import org.robovm.apple.coremedia.*;
import org.robovm.apple.security.*;
import org.robovm.apple.dispatch.*;
/*</imports>*/

/*<javadoc>*/
/*</javadoc>*/
/*<annotations>*/@Library("Foundation")/*</annotations>*/
@Marshaler(/*<name>*/NSNetServiceErrorUserInfo/*</name>*/.Marshaler.class)
/*<visibility>*/public/*</visibility>*/ class /*<name>*/NSNetServiceErrorUserInfo/*</name>*/ 
    extends /*<extends>*/NSDictionaryWrapper/*</extends>*/
    /*<implements>*//*</implements>*/ {

    /*<marshalers>*/
    public static class Marshaler {
        @MarshalsPointer
        public static NSNetServiceErrorUserInfo toObject(Class<NSNetServiceErrorUserInfo> cls, long handle, long flags) {
            NSDictionary o = (NSDictionary) NSObject.Marshaler.toObject(NSDictionary.class, handle, flags);
            if (o == null) {
                return null;
            }
            return new NSNetServiceErrorUserInfo(o);
        }
        @MarshalsPointer
        public static long toNative(NSNetServiceErrorUserInfo o, long flags) {
            if (o == null) {
                return 0L;
            }
            return NSObject.Marshaler.toNative(o.data, flags);
        }
    }
    public static class AsListMarshaler {
        @MarshalsPointer
        public static List<NSNetServiceErrorUserInfo> toObject(Class<? extends NSObject> cls, long handle, long flags) {
            NSArray<NSDictionary> o = (NSArray<NSDictionary>) NSObject.Marshaler.toObject(NSArray.class, handle, flags);
            if (o == null) {
                return null;
            }
            List<NSNetServiceErrorUserInfo> list = new ArrayList<>();
            for (int i = 0; i < o.size(); i++) {
                list.add(new NSNetServiceErrorUserInfo(o.get(i)));
            }
            return list;
        }
        @MarshalsPointer
        public static long toNative(List<NSNetServiceErrorUserInfo> l, long flags) {
            if (l == null) {
                return 0L;
            }
            NSArray<NSDictionary> array = new NSMutableArray<>();
            for (NSNetServiceErrorUserInfo i : l) {
                array.add(i.getDictionary());
            }
            return NSObject.Marshaler.toNative(array, flags);
        }
    }
    /*</marshalers>*/

    /*<constructors>*/
    NSNetServiceErrorUserInfo(NSDictionary data) {
        super(data);
    }
    public NSNetServiceErrorUserInfo() {}
    /*</constructors>*/

    /*<methods>*/
    public boolean has(NSString key) {
        return data.containsKey(key);
    }
    public NSObject get(NSString key) {
        if (has(key)) {
            return data.get(key);
        }
        return null;
    }
    public NSNetServiceErrorUserInfo set(NSString key, NSObject value) {
        data.put(key, value);
        return this;
    }
    

    public NSNetServiceErrorCode getErrorCode() {
        if (has(Keys.ErrorCode())) {
            NSNumber val = (NSNumber) get(Keys.ErrorCode());
            return NSNetServiceErrorCode.valueOf(val.longValue());
        }
        return null;
    }
    public NSNetServiceErrorUserInfo setErrorCode(NSNetServiceErrorCode errorCode) {
        set(Keys.ErrorCode(), NSNumber.valueOf(errorCode.value()));
        return this;
    }
    public String getErrorDomain() {
        if (has(Keys.ErrorDomain())) {
            NSString val = (NSString) get(Keys.ErrorDomain());
            return val.toString();
        }
        return null;
    }
    public NSNetServiceErrorUserInfo setErrorDomain(String errorDomain) {
        set(Keys.ErrorDomain(), new NSString(errorDomain));
        return this;
    }
    /*</methods>*/
    
    /*<keys>*/
    @Library("Foundation")
    public static class Keys {
        static { Bro.bind(Keys.class); }
        @GlobalValue(symbol="NSNetServicesErrorCode", optional=true)
        public static native NSString ErrorCode();
        @GlobalValue(symbol="NSNetServicesErrorDomain", optional=true)
        public static native NSString ErrorDomain();
    }
    /*</keys>*/
}
