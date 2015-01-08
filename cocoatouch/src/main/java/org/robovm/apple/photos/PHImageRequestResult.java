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
package org.robovm.apple.photos;

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
import org.robovm.apple.coregraphics.*;
import org.robovm.apple.corelocation.*;
import org.robovm.apple.uikit.*;
import org.robovm.apple.avfoundation.*;
/*</imports>*/

/*<javadoc>*/
/*</javadoc>*/
@Marshaler(PHImageRequestResult.Marshaler.class)
/*<annotations>*/@Library("Photos")/*</annotations>*/
/*<visibility>*/public/*</visibility>*/ class /*<name>*/PHImageRequestResult/*</name>*/ 
    extends /*<extends>*/Object/*</extends>*/ 
    /*<implements>*//*</implements>*/ {

    public static class Marshaler {
        @SuppressWarnings("unchecked")
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
    
    /*<ptr>*/
    /*</ptr>*/
    private NSDictionary<NSString, NSObject> data;
    
    protected PHImageRequestResult(NSDictionary<NSString, NSObject> data) {
        this.data = data;
    }
    /*<bind>*/static { Bro.bind(PHImageRequestResult.class); }/*</bind>*/
    /*<constants>*//*</constants>*/
    /*<constructors>*//*</constructors>*/
    /*<properties>*//*</properties>*/
    /*<members>*//*</members>*/
    public NSDictionary<NSString, NSObject> getDictionary() {
        return data;
    }
    
    
    /**
     * @since Available in iOS 8.0 and later.
     */
    public boolean isResultInCloud() {
        if (data.containsKey(ResultIsInCloudKey())) {
            NSNumber val = (NSNumber) data.get(ResultIsInCloudKey());
            return val.booleanValue();
        }
        return false;
    }
    /**
     * @since Available in iOS 8.0 and later.
     */
    public boolean isResultDegraded() {
        if (data.containsKey(ResultIsDegradedKey())) {
            NSNumber val = (NSNumber) data.get(ResultIsDegradedKey());
            return val.booleanValue();
        }
        return false;
    }
    /**
     * @since Available in iOS 8.0 and later.
     */
    public long getResultRequestID() {
        if (data.conformsToProtocol(ResultRequestIDKey())) {
            NSNumber val = (NSNumber) data.get(ResultRequestIDKey());
            return val.longValue();
        }
        return 0;
    }
    /**
     * @since Available in iOS 8.0 and later.
     */
    public boolean isCancelled() {
        if (data.containsKey(CancelledKey())) {
            NSNumber val = (NSNumber) data.get(CancelledKey());
            return val.booleanValue();
        }
        return false;
    }
    /**
     * @since Available in iOS 8.0 and later.
     */
    public NSError getError() {
        if (data.containsKey(ErrorKey())) {
            NSError val = (NSError) data.get(ErrorKey());
            return val;
        }
        return null;
    }
    /*<methods>*/
    /**
     * @since Available in iOS 8.0 and later.
     */
    @GlobalValue(symbol="PHImageResultIsInCloudKey", optional=true)
    protected static native NSString ResultIsInCloudKey();
    /**
     * @since Available in iOS 8.0 and later.
     */
    @GlobalValue(symbol="PHImageResultIsDegradedKey", optional=true)
    protected static native NSString ResultIsDegradedKey();
    /**
     * @since Available in iOS 8.0 and later.
     */
    @GlobalValue(symbol="PHImageResultRequestIDKey", optional=true)
    protected static native NSString ResultRequestIDKey();
    /**
     * @since Available in iOS 8.0 and later.
     */
    @GlobalValue(symbol="PHImageCancelledKey", optional=true)
    protected static native NSString CancelledKey();
    /**
     * @since Available in iOS 8.0 and later.
     */
    @GlobalValue(symbol="PHImageErrorKey", optional=true)
    protected static native NSString ErrorKey();
    /*</methods>*/
    @Override
    public String toString() {
        if (data != null) return data.toString();
        return super.toString();
    }
}
