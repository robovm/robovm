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
package org.robovm.apple.mediaplayer;

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
import org.robovm.apple.uikit.*;
import org.robovm.apple.coregraphics.*;
/*</imports>*/

/*<javadoc>*/
/*</javadoc>*/
/*<annotations>*/@Library("MediaPlayer")/*</annotations>*/
@Marshaler(/*<name>*/MPMoviePlayerFullscreenAnimation/*</name>*/.Marshaler.class)
/*<visibility>*/public/*</visibility>*/ class /*<name>*/MPMoviePlayerFullscreenAnimation/*</name>*/ 
    extends /*<extends>*/NSDictionaryWrapper/*</extends>*/
    /*<implements>*//*</implements>*/ {

    /*<marshalers>*/
    public static class Marshaler {
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
    public static class AsListMarshaler {
        @MarshalsPointer
        public static List<MPMoviePlayerFullscreenAnimation> toObject(Class<? extends NSObject> cls, long handle, long flags) {
            NSArray<NSDictionary<NSString, NSObject>> o = (NSArray<NSDictionary<NSString, NSObject>>) NSObject.Marshaler.toObject(NSArray.class, handle, flags);
            if (o == null) {
                return null;
            }
            List<MPMoviePlayerFullscreenAnimation> list = new ArrayList<>();
            for (int i = 0; i < o.size(); i++) {
                list.add(new MPMoviePlayerFullscreenAnimation(o.get(i)));
            }
            return list;
        }
        @MarshalsPointer
        public static long toNative(List<MPMoviePlayerFullscreenAnimation> l, long flags) {
            if (l == null) {
                return 0L;
            }
            NSArray<NSDictionary<NSString, NSObject>> array = new NSMutableArray<>();
            for (MPMoviePlayerFullscreenAnimation i : l) {
                array.add(i.getDictionary());
            }
            return NSObject.Marshaler.toNative(array, flags);
        }
    }
    /*</marshalers>*/

    /*<constructors>*/
    MPMoviePlayerFullscreenAnimation(NSDictionary<NSString, NSObject> data) {
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
     * @since Available in iOS 3.2 and later.
     */
    public double getDuration() {
        if (has(Keys.Duration())) {
            NSNumber val = (NSNumber) get(Keys.Duration());
            return val.doubleValue();
        }
        return 0;
    }
    /**
     * @since Available in iOS 3.2 and later.
     */
    public UIViewAnimationCurve getCurve() {
        if (has(Keys.Curve())) {
            NSNumber val = (NSNumber) get(Keys.Curve());
            return UIViewAnimationCurve.valueOf(val.longValue());
        }
        return null;
    }
    /*</methods>*/
    
    /*<keys>*/
    @Library("MediaPlayer")
    public static class Keys {
        static { Bro.bind(Keys.class); }
        /**
         * @since Available in iOS 3.2 and later.
         */
        @GlobalValue(symbol="MPMoviePlayerFullscreenAnimationDurationUserInfoKey", optional=true)
        public static native NSString Duration();
        /**
         * @since Available in iOS 3.2 and later.
         */
        @GlobalValue(symbol="MPMoviePlayerFullscreenAnimationCurveUserInfoKey", optional=true)
        public static native NSString Curve();
    }
    /*</keys>*/
}
