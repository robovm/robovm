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
package org.robovm.apple.avfoundation;

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
import org.robovm.apple.corefoundation.*;
import org.robovm.apple.dispatch.*;
import org.robovm.apple.coreanimation.*;
import org.robovm.apple.coregraphics.*;
import org.robovm.apple.coreaudio.*;
import org.robovm.apple.coremedia.*;
import org.robovm.apple.corevideo.*;
import org.robovm.apple.mediatoolbox.*;
import org.robovm.apple.audiotoolbox.*;
import org.robovm.apple.audiounit.*;
/*</imports>*/

/*<javadoc>*/
/*</javadoc>*/
/*<annotations>*/@Library("AVFoundation")/*</annotations>*/
@Marshaler(/*<name>*/AVAudioSessionRouteChangeNotification/*</name>*/.Marshaler.class)
/*<visibility>*/public/*</visibility>*/ class /*<name>*/AVAudioSessionRouteChangeNotification/*</name>*/ 
    extends /*<extends>*/NSDictionaryWrapper/*</extends>*/
    /*<implements>*//*</implements>*/ {

    /*<marshalers>*/
    public static class Marshaler {
        @MarshalsPointer
        public static AVAudioSessionRouteChangeNotification toObject(Class<AVAudioSessionRouteChangeNotification> cls, long handle, long flags) {
            NSDictionary<NSString, NSObject> o = (NSDictionary<NSString, NSObject>) NSObject.Marshaler.toObject(NSDictionary.class, handle, flags);
            if (o == null) {
                return null;
            }
            return new AVAudioSessionRouteChangeNotification(o);
        }
        @MarshalsPointer
        public static long toNative(AVAudioSessionRouteChangeNotification o, long flags) {
            if (o == null) {
                return 0L;
            }
            return NSObject.Marshaler.toNative(o.data, flags);
        }
    }
    public static class AsListMarshaler {
        @MarshalsPointer
        public static List<AVAudioSessionRouteChangeNotification> toObject(Class<? extends NSObject> cls, long handle, long flags) {
            NSArray<NSDictionary<NSString, NSObject>> o = (NSArray<NSDictionary<NSString, NSObject>>) NSObject.Marshaler.toObject(cls, handle, flags);
            if (o == null) {
                return null;
            }
            List<AVAudioSessionRouteChangeNotification> list = new ArrayList<>();
            for (int i = 0; i < o.size(); i++) {
                list.add(new AVAudioSessionRouteChangeNotification(o.get(i)));
            }
            return list;
        }
        @MarshalsPointer
        public static long toNative(List<AVAudioSessionRouteChangeNotification> l, long flags) {
            if (l == null) {
                return 0L;
            }
            NSArray<NSDictionary<NSString, NSObject>> array = new NSMutableArray<>();
            for (AVAudioSessionRouteChangeNotification i : l) {
                array.add(i.getDictionary());
            }
            return NSObject.Marshaler.toNative(array, flags);
        }
    }
    /*</marshalers>*/

    /*<constructors>*/
    AVAudioSessionRouteChangeNotification(NSDictionary<NSString, NSObject> data) {
        super(data);
    }
    public AVAudioSessionRouteChangeNotification() {}
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
    public AVAudioSessionRouteChangeNotification set(NSString key, NSObject value) {
        data.put(key, value);
        return this;
    }
    

    /**
     * @since Available in iOS 6.0 and later.
     */
    public AVAudioSessionRouteChangeReason getReason() {
        if (has(Keys.Reason())) {
            NSNumber val = (NSNumber) get(Keys.Reason());
            return AVAudioSessionRouteChangeReason.valueOf(val.longValue());
        }
        return null;
    }
    /**
     * @since Available in iOS 6.0 and later.
     */
    public AVAudioSessionRouteChangeNotification setReason(AVAudioSessionRouteChangeReason reason) {
        set(Keys.Reason(), NSNumber.valueOf(reason.value()));
        return this;
    }
    /**
     * @since Available in iOS 6.0 and later.
     */
    public AVAudioSessionRouteDescription getPreviousRoute() {
        if (has(Keys.PreviousRoute())) {
            AVAudioSessionRouteDescription val = (AVAudioSessionRouteDescription) get(Keys.PreviousRoute());
            return val;
        }
        return null;
    }
    /**
     * @since Available in iOS 6.0 and later.
     */
    public AVAudioSessionRouteChangeNotification setPreviousRoute(AVAudioSessionRouteDescription previousRoute) {
        set(Keys.PreviousRoute(), previousRoute);
        return this;
    }
    /*</methods>*/
    
    /*<keys>*/
    @Library("AVFoundation")
    public static class Keys {
        static { Bro.bind(Keys.class); }
        /**
         * @since Available in iOS 6.0 and later.
         */
        @GlobalValue(symbol="AVAudioSessionRouteChangeReasonKey", optional=true)
        public static native NSString Reason();
        /**
         * @since Available in iOS 6.0 and later.
         */
        @GlobalValue(symbol="AVAudioSessionRouteChangePreviousRouteKey", optional=true)
        public static native NSString PreviousRoute();
    }
    /*</keys>*/
}
