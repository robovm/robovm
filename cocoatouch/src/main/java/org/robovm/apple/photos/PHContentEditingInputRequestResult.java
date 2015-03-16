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
@Marshaler(PHContentEditingInputRequestResult.Marshaler.class)
/*<annotations>*/@Library("Photos")/*</annotations>*/
/*<visibility>*/public/*</visibility>*/ class /*<name>*/PHContentEditingInputRequestResult/*</name>*/ 
    extends /*<extends>*/CocoaUtility/*</extends>*/ 
    /*<implements>*//*</implements>*/ {

    public static class Marshaler {
        @SuppressWarnings("unchecked")
        @MarshalsPointer
        public static PHContentEditingInputRequestResult toObject(Class<PHContentEditingInputRequestResult> cls, long handle, long flags) {
            NSDictionary<NSString, NSObject> o = (NSDictionary<NSString, NSObject>) NSObject.Marshaler.toObject(NSDictionary.class, handle, flags);
            if (o == null) {
                return null;
            }
            return new PHContentEditingInputRequestResult(o);
        }
        @MarshalsPointer
        public static long toNative(PHContentEditingInputRequestResult o, long flags) {
            if (o == null) {
                return 0L;
            }
            return NSObject.Marshaler.toNative(o.data, flags);
        }
    }
    
    /*<ptr>*/
    /*</ptr>*/
    private NSDictionary<NSString, NSObject> data;
    
    protected PHContentEditingInputRequestResult(NSDictionary<NSString, NSObject> data) {
        this.data = data;
    }
    /*<bind>*/static { Bro.bind(PHContentEditingInputRequestResult.class); }/*</bind>*/
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
    @GlobalValue(symbol="PHContentEditingInputResultIsInCloudKey", optional=true)
    protected static native NSString ResultIsInCloudKey();
    /**
     * @since Available in iOS 8.0 and later.
     */
    @GlobalValue(symbol="PHContentEditingInputCancelledKey", optional=true)
    protected static native NSString CancelledKey();
    /**
     * @since Available in iOS 8.0 and later.
     */
    @GlobalValue(symbol="PHContentEditingInputErrorKey", optional=true)
    protected static native NSString ErrorKey();
    /*</methods>*/
    @Override
    public String toString() {
        if (data != null) return data.toString();
        return super.toString();
    }
}
