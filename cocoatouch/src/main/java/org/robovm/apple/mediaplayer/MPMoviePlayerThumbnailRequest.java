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
package org.robovm.apple.mediaplayer;

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
import org.robovm.apple.coregraphics.*;
/*</imports>*/

/*<javadoc>*/
/*</javadoc>*/
@Marshaler(MPMoviePlayerThumbnailRequest.Marshaler.class)
/*<annotations>*/@Library("MediaPlayer")/*</annotations>*/
/*<visibility>*/public/*</visibility>*/ class /*<name>*/MPMoviePlayerThumbnailRequest/*</name>*/ 
    extends /*<extends>*/Object/*</extends>*/ 
    /*<implements>*//*</implements>*/ {
    
    public static class Marshaler {
        @SuppressWarnings("unchecked")
        @MarshalsPointer
        public static MPMoviePlayerThumbnailRequest toObject(Class<MPMoviePlayerThumbnailRequest> cls, long handle, long flags) {
            NSDictionary<NSString, ?> o = (NSDictionary<NSString, ?>) NSObject.Marshaler.toObject(NSDictionary.class, handle, flags);
            if (o == null) {
                return null;
            }
            return new MPMoviePlayerThumbnailRequest(o);
        }
        @MarshalsPointer
        public static long toNative(MPMoviePlayerThumbnailRequest o, long flags) {
            if (o == null) {
                return 0L;
            }
            return NSObject.Marshaler.toNative(o.data, flags);
        }
    }
    
    /*<ptr>*/
    /*</ptr>*/
    private NSDictionary<NSString, ?> data;
    
    protected MPMoviePlayerThumbnailRequest(NSDictionary<NSString, ?> data) {
        this.data = data;
    }
    
    /*<bind>*/static { Bro.bind(MPMoviePlayerThumbnailRequest.class); }/*</bind>*/
    /*<constants>*//*</constants>*/
    /*<constructors>*//*</constructors>*/
    /*<properties>*//*</properties>*/
    /*<members>*//*</members>*/
    /**
     * @since Available in iOS 3.2 and later.
     */
    public UIImage getImage() {
        if (data.containsKey(ImageKey())) {
            UIImage val = (UIImage)data.get(ImageKey());
            return val;
        }
        return null;
    }
    /**
     * @since Available in iOS 3.2 and later.
     */
    public double getTime() {
        if (data.containsKey(TimeKey())) {
            NSNumber val = (NSNumber)data.get(TimeKey());
            return val.doubleValue();
        }
        return 0;
    }
    /**
     * @since Available in iOS 3.2 and later.
     */
    public NSError getError() {
        if (data.containsKey(ErrorKey())) {
            NSError val = (NSError)data.get(ErrorKey());
            return val;
        }
        return null;
    }
    /*<methods>*/
    /**
     * @since Available in iOS 3.2 and later.
     */
    @GlobalValue(symbol="MPMoviePlayerThumbnailImageKey", optional=true)
    protected static native NSString ImageKey();
    /**
     * @since Available in iOS 3.2 and later.
     */
    @GlobalValue(symbol="MPMoviePlayerThumbnailTimeKey", optional=true)
    protected static native NSString TimeKey();
    /**
     * @since Available in iOS 3.2 and later.
     */
    @GlobalValue(symbol="MPMoviePlayerThumbnailErrorKey", optional=true)
    protected static native NSString ErrorKey();
    /*</methods>*/
}
