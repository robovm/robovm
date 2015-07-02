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
package org.robovm.apple.photos;

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
import org.robovm.apple.coregraphics.*;
import org.robovm.apple.uikit.*;
import org.robovm.apple.corelocation.*;
import org.robovm.apple.avfoundation.*;
/*</imports>*/

/*<javadoc>*/
/*</javadoc>*/
/*<annotations>*/@Library("Photos")/*</annotations>*/
@Marshaler(/*<name>*/PHImageRequestResult/*</name>*/.Marshaler.class)
/*<visibility>*/public/*</visibility>*/ class /*<name>*/PHImageRequestResult/*</name>*/ 
    extends /*<extends>*/NSDictionaryWrapper/*</extends>*/
    /*<implements>*//*</implements>*/ {

    /*<marshalers>*/
    public static class Marshaler {
        @MarshalsPointer
        public static PHImageRequestResult toObject(Class<PHImageRequestResult> cls, long handle, long flags) {
            NSDictionary<NSString, NSObject> o = (NSDictionary<NSString, NSObject>) NSObject.Marshaler.toObject(NSDictionary.class, handle, flags);
            if (o == null) {
                return null;
            }
            return new PHImageRequestResult(o);
        }
        @MarshalsPointer
        public static long toNative(PHImageRequestResult o, long flags) {
            if (o == null) {
                return 0L;
            }
            return NSObject.Marshaler.toNative(o.data, flags);
        }
    }
    public static class AsListMarshaler {
        @MarshalsPointer
        public static List<PHImageRequestResult> toObject(Class<? extends NSObject> cls, long handle, long flags) {
            NSArray<NSDictionary<NSString, NSObject>> o = (NSArray<NSDictionary<NSString, NSObject>>) NSObject.Marshaler.toObject(NSArray.class, handle, flags);
            if (o == null) {
                return null;
            }
            List<PHImageRequestResult> list = new ArrayList<>();
            for (int i = 0; i < o.size(); i++) {
                list.add(new PHImageRequestResult(o.get(i)));
            }
            return list;
        }
        @MarshalsPointer
        public static long toNative(List<PHImageRequestResult> l, long flags) {
            if (l == null) {
                return 0L;
            }
            NSArray<NSDictionary<NSString, NSObject>> array = new NSMutableArray<>();
            for (PHImageRequestResult i : l) {
                array.add(i.getDictionary());
            }
            return NSObject.Marshaler.toNative(array, flags);
        }
    }
    /*</marshalers>*/

    /*<constructors>*/
    PHImageRequestResult(NSDictionary<NSString, NSObject> data) {
        super(data);
    }
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
    

    /**
     * @since Available in iOS 8.0 and later.
     */
    public boolean isResultInCloud() {
        if (has(Keys.ResultIsInCloud())) {
            NSNumber val = (NSNumber) get(Keys.ResultIsInCloud());
            return val.booleanValue();
        }
        return false;
    }
    /**
     * @since Available in iOS 8.0 and later.
     */
    public boolean isResultDegraded() {
        if (has(Keys.ResultIsDegraded())) {
            NSNumber val = (NSNumber) get(Keys.ResultIsDegraded());
            return val.booleanValue();
        }
        return false;
    }
    /**
     * @since Available in iOS 8.0 and later.
     */
    public long getResultRequestID() {
        if (has(Keys.ResultRequestID())) {
            NSNumber val = (NSNumber) get(Keys.ResultRequestID());
            return val.longValue();
        }
        return 0;
    }
    /**
     * @since Available in iOS 8.0 and later.
     */
    public boolean isCancelled() {
        if (has(Keys.Cancelled())) {
            NSNumber val = (NSNumber) get(Keys.Cancelled());
            return val.booleanValue();
        }
        return false;
    }
    /**
     * @since Available in iOS 8.0 and later.
     */
    public NSError getError() {
        if (has(Keys.Error())) {
            NSError val = (NSError) get(Keys.Error());
            return val;
        }
        return null;
    }
    /*</methods>*/
    
    /*<keys>*/
    @Library("Photos")
    public static class Keys {
        static { Bro.bind(Keys.class); }
        /**
         * @since Available in iOS 8.0 and later.
         */
        @GlobalValue(symbol="PHImageResultIsInCloudKey", optional=true)
        public static native NSString ResultIsInCloud();
        /**
         * @since Available in iOS 8.0 and later.
         */
        @GlobalValue(symbol="PHImageResultIsDegradedKey", optional=true)
        public static native NSString ResultIsDegraded();
        /**
         * @since Available in iOS 8.0 and later.
         */
        @GlobalValue(symbol="PHImageResultRequestIDKey", optional=true)
        public static native NSString ResultRequestID();
        /**
         * @since Available in iOS 8.0 and later.
         */
        @GlobalValue(symbol="PHImageCancelledKey", optional=true)
        public static native NSString Cancelled();
        /**
         * @since Available in iOS 8.0 and later.
         */
        @GlobalValue(symbol="PHImageErrorKey", optional=true)
        public static native NSString Error();
    }
    /*</keys>*/
}
