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
@Marshaler(MPMoviePlayerFullscreenAnimation.Marshaler.class)
/*<annotations>*/@Library("MediaPlayer")/*</annotations>*/
/*<visibility>*/public/*</visibility>*/ class /*<name>*/MPMoviePlayerFullscreenAnimation/*</name>*/ 
    extends /*<extends>*/CocoaUtility/*</extends>*/ 
    /*<implements>*//*</implements>*/ {

    public static class Marshaler {
        @SuppressWarnings("unchecked")
        @MarshalsPointer
        public static MPMoviePlayerFullscreenAnimation toObject(Class<MPMoviePlayerFullscreenAnimation> cls, long handle, long flags) {
            NSDictionary<NSString, NSObject> o = (NSDictionary<NSString, NSObject>) NSObject.Marshaler.toObject(NSDictionary.class, handle, flags);
            if (o == null) {
                return null;
            }
            return new MPMoviePlayerFullscreenAnimation(o);
        }
        @MarshalsPointer
        public static long toNative(MPMoviePlayerFullscreenAnimation o, long flags) {
            if (o == null) {
                return 0L;
            }
            return NSObject.Marshaler.toNative(o.data, flags);
        }
    }
    
    /*<ptr>*/
    /*</ptr>*/
    private NSDictionary<NSString, NSObject> data;
    
    protected MPMoviePlayerFullscreenAnimation(NSDictionary<NSString, NSObject> data) {
        this.data = data;
    }
    /*<bind>*/static { Bro.bind(MPMoviePlayerFullscreenAnimation.class); }/*</bind>*/
    /*<constants>*//*</constants>*/
    /*<constructors>*//*</constructors>*/
    /*<properties>*//*</properties>*/
    /*<members>*//*</members>*/
    public NSDictionary<NSString, NSObject> getDictionary() {
    	return data;
	}
    
    /**
     * @since Available in iOS 3.2 and later.
     */
    public double getDuration() {
        if (data.containsKey(DurationUserInfoKey())) {
            NSNumber val = (NSNumber)data.get(DurationUserInfoKey());
            return val.doubleValue();
        }
        return 0;
    }
    /**
     * @since Available in iOS 3.2 and later.
     */
    public UIViewAnimationCurve getCurve() {
        if (data.containsKey(DurationUserInfoKey())) {
            NSNumber val = (NSNumber)data.get(DurationUserInfoKey());
            return UIViewAnimationCurve.valueOf(val.intValue());
        }
        return null;
    }
    /*<methods>*/
    /**
     * @since Available in iOS 3.2 and later.
     */
    @GlobalValue(symbol="MPMoviePlayerFullscreenAnimationDurationUserInfoKey", optional=true)
    protected static native NSString DurationUserInfoKey();
    /**
     * @since Available in iOS 3.2 and later.
     */
    @GlobalValue(symbol="MPMoviePlayerFullscreenAnimationCurveUserInfoKey", optional=true)
    protected static native NSString CurveUserInfoKey();
    /*</methods>*/
    
    @Override
    public String toString() {
        if (data != null) return data.toString();
        return super.toString();
    }
}
