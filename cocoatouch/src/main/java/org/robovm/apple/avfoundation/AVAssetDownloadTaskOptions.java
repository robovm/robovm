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
import org.robovm.apple.coreimage.*;
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
@Marshaler(/*<name>*/AVAssetDownloadTaskOptions/*</name>*/.Marshaler.class)
/*<visibility>*/public/*</visibility>*/ class /*<name>*/AVAssetDownloadTaskOptions/*</name>*/ 
    extends /*<extends>*/NSDictionaryWrapper/*</extends>*/
    /*<implements>*//*</implements>*/ {

    /*<marshalers>*/
    public static class Marshaler {
        @MarshalsPointer
        public static AVAssetDownloadTaskOptions toObject(Class<AVAssetDownloadTaskOptions> cls, long handle, long flags) {
            NSDictionary o = (NSDictionary) NSObject.Marshaler.toObject(NSDictionary.class, handle, flags);
            if (o == null) {
                return null;
            }
            return new AVAssetDownloadTaskOptions(o);
        }
        @MarshalsPointer
        public static long toNative(AVAssetDownloadTaskOptions o, long flags) {
            if (o == null) {
                return 0L;
            }
            return NSObject.Marshaler.toNative(o.data, flags);
        }
    }
    public static class AsListMarshaler {
        @MarshalsPointer
        public static List<AVAssetDownloadTaskOptions> toObject(Class<? extends NSObject> cls, long handle, long flags) {
            NSArray<NSDictionary> o = (NSArray<NSDictionary>) NSObject.Marshaler.toObject(NSArray.class, handle, flags);
            if (o == null) {
                return null;
            }
            List<AVAssetDownloadTaskOptions> list = new ArrayList<>();
            for (int i = 0; i < o.size(); i++) {
                list.add(new AVAssetDownloadTaskOptions(o.get(i)));
            }
            return list;
        }
        @MarshalsPointer
        public static long toNative(List<AVAssetDownloadTaskOptions> l, long flags) {
            if (l == null) {
                return 0L;
            }
            NSArray<NSDictionary> array = new NSMutableArray<>();
            for (AVAssetDownloadTaskOptions i : l) {
                array.add(i.getDictionary());
            }
            return NSObject.Marshaler.toNative(array, flags);
        }
    }
    /*</marshalers>*/

    /*<constructors>*/
    AVAssetDownloadTaskOptions(NSDictionary data) {
        super(data);
    }
    public AVAssetDownloadTaskOptions() {}
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
    public AVAssetDownloadTaskOptions set(NSString key, NSObject value) {
        data.put(key, value);
        return this;
    }
    

    /**
     * @since Available in iOS 9.0 and later.
     */
    public long getMinimumRequiredMediaBitrate() {
        if (has(Keys.MinimumRequiredMediaBitrate())) {
            NSNumber val = (NSNumber) get(Keys.MinimumRequiredMediaBitrate());
            return val.longValue();
        }
        return 0;
    }
    /**
     * @since Available in iOS 9.0 and later.
     */
    public AVAssetDownloadTaskOptions setMinimumRequiredMediaBitrate(long minimumRequiredMediaBitrate) {
        set(Keys.MinimumRequiredMediaBitrate(), NSNumber.valueOf(minimumRequiredMediaBitrate));
        return this;
    }
    /**
     * @since Available in iOS 9.0 and later.
     */
    public AVMediaSelection getMediaSelection() {
        if (has(Keys.MediaSelection())) {
            AVMediaSelection val = (AVMediaSelection) get(Keys.MediaSelection());
            return val;
        }
        return null;
    }
    /**
     * @since Available in iOS 9.0 and later.
     */
    public AVAssetDownloadTaskOptions setMediaSelection(AVMediaSelection mediaSelection) {
        set(Keys.MediaSelection(), mediaSelection);
        return this;
    }
    /*</methods>*/
    
    /*<keys>*/
    @Library("AVFoundation")
    public static class Keys {
        static { Bro.bind(Keys.class); }
        /**
         * @since Available in iOS 9.0 and later.
         */
        @GlobalValue(symbol="AVAssetDownloadTaskMinimumRequiredMediaBitrateKey", optional=true)
        public static native NSString MinimumRequiredMediaBitrate();
        /**
         * @since Available in iOS 9.0 and later.
         */
        @GlobalValue(symbol="AVAssetDownloadTaskMediaSelectionKey", optional=true)
        public static native NSString MediaSelection();
    }
    /*</keys>*/
}
