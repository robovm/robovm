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
/*<annotations>*/@Library("AVFoundation") @StronglyLinked/*</annotations>*/
@Marshaler(/*<name>*/AVErrorUserInfoKey/*</name>*/.Marshaler.class)
/*<visibility>*/public/*</visibility>*/ class /*<name>*/AVErrorUserInfoKey/*</name>*/ 
    extends /*<extends>*/NSErrorUserInfoKey/*</extends>*/
    /*<implements>*//*</implements>*/ {

    static { Bro.bind(/*<name>*/AVErrorUserInfoKey/*</name>*/.class); }

    /*<marshalers>*/
    public static class Marshaler {
        @MarshalsPointer
        public static AVErrorUserInfoKey toObject(Class<AVErrorUserInfoKey> cls, long handle, long flags) {
            NSString o = (NSString) NSObject.Marshaler.toObject(NSString.class, handle, flags);
            if (o == null) {
                return null;
            }
            return AVErrorUserInfoKey.valueOf(o);
        }
        @MarshalsPointer
        public static long toNative(AVErrorUserInfoKey o, long flags) {
            if (o == null) {
                return 0L;
            }
            return NSObject.Marshaler.toNative(o.value(), flags);
        }
    }
    public static class AsListMarshaler {
        @SuppressWarnings("unchecked")
        @MarshalsPointer
        public static List<AVErrorUserInfoKey> toObject(Class<? extends NSObject> cls, long handle, long flags) {
            NSArray<NSString> o = (NSArray<NSString>) NSObject.Marshaler.toObject(cls, handle, flags);
            if (o == null) {
                return null;
            }
            List<AVErrorUserInfoKey> list = new ArrayList<>();
            for (int i = 0; i < o.size(); i++) {
                list.add(AVErrorUserInfoKey.valueOf(o.get(i)));
            }
            return list;
        }
        @MarshalsPointer
        public static long toNative(List<AVErrorUserInfoKey> l, long flags) {
            if (l == null) {
                return 0L;
            }
            NSArray<NSString> array = new NSMutableArray<>();
            for (AVErrorUserInfoKey i : l) {
                array.add(i.value());
            }
            return NSObject.Marshaler.toNative(array, flags);
        }
    }
    /*</marshalers>*/

    /*<constants>*/
    /**
     * @since Available in iOS 4.0 and later.
     */
    public static final AVErrorUserInfoKey Device = new AVErrorUserInfoKey("Device");
    /**
     * @since Available in iOS 4.0 and later.
     */
    public static final AVErrorUserInfoKey Time = new AVErrorUserInfoKey("Time");
    /**
     * @since Available in iOS 4.0 and later.
     */
    public static final AVErrorUserInfoKey FileSize = new AVErrorUserInfoKey("FileSize");
    /**
     * @since Available in iOS 4.0 and later.
     */
    public static final AVErrorUserInfoKey PID = new AVErrorUserInfoKey("PID");
    /**
     * @since Available in iOS 4.0 and later.
     */
    public static final AVErrorUserInfoKey RecordingSuccessfullyFinished = new AVErrorUserInfoKey("RecordingSuccessfullyFinished");
    /**
     * @since Available in iOS 4.3 and later.
     */
    public static final AVErrorUserInfoKey MediaType = new AVErrorUserInfoKey("MediaType");
    /**
     * @since Available in iOS 4.3 and later.
     */
    public static final AVErrorUserInfoKey MediaSubType = new AVErrorUserInfoKey("MediaSubType");
    /**
     * @since Available in iOS 8.0 and later.
     */
    public static final AVErrorUserInfoKey PresentationTimeStamp = new AVErrorUserInfoKey("PresentationTimeStamp");
    /**
     * @since Available in iOS 8.0 and later.
     */
    public static final AVErrorUserInfoKey PersistentTrackID = new AVErrorUserInfoKey("PersistentTrackID");
    /**
     * @since Available in iOS 8.0 and later.
     */
    public static final AVErrorUserInfoKey FileType = new AVErrorUserInfoKey("FileType");
    /*</constants>*/
    
    private static /*<name>*/AVErrorUserInfoKey/*</name>*/[] values = new /*<name>*/AVErrorUserInfoKey/*</name>*/[] {/*<value_list>*/Device, Time, FileSize, PID, RecordingSuccessfullyFinished, MediaType, MediaSubType, PresentationTimeStamp, PersistentTrackID, FileType/*</value_list>*/};
    
    /*<name>*/AVErrorUserInfoKey/*</name>*/ (String getterName) {
        super(Values.class, getterName);
    }
    
    public static /*<name>*/AVErrorUserInfoKey/*</name>*/ valueOf(/*<type>*/NSString/*</type>*/ value) {
        for (/*<name>*/AVErrorUserInfoKey/*</name>*/ v : values) {
            if (v.value().equals(value)) {
                return v;
            }
        }
        throw new IllegalArgumentException("No constant with value " + value + " found in " 
            + /*<name>*/AVErrorUserInfoKey/*</name>*/.class.getName());
    }
    
    /*<methods>*//*</methods>*/
    
    /*<annotations>*/@Library("AVFoundation") @StronglyLinked/*</annotations>*/
    public static class Values {
    	static { Bro.bind(Values.class); }

        /*<values>*/
        /**
         * @since Available in iOS 4.0 and later.
         */
        @GlobalValue(symbol="AVErrorDeviceKey", optional=true)
        public static native NSString Device();
        /**
         * @since Available in iOS 4.0 and later.
         */
        @GlobalValue(symbol="AVErrorTimeKey", optional=true)
        public static native NSString Time();
        /**
         * @since Available in iOS 4.0 and later.
         */
        @GlobalValue(symbol="AVErrorFileSizeKey", optional=true)
        public static native NSString FileSize();
        /**
         * @since Available in iOS 4.0 and later.
         */
        @GlobalValue(symbol="AVErrorPIDKey", optional=true)
        public static native NSString PID();
        /**
         * @since Available in iOS 4.0 and later.
         */
        @GlobalValue(symbol="AVErrorRecordingSuccessfullyFinishedKey", optional=true)
        public static native NSString RecordingSuccessfullyFinished();
        /**
         * @since Available in iOS 4.3 and later.
         */
        @GlobalValue(symbol="AVErrorMediaTypeKey", optional=true)
        public static native NSString MediaType();
        /**
         * @since Available in iOS 4.3 and later.
         */
        @GlobalValue(symbol="AVErrorMediaSubTypeKey", optional=true)
        public static native NSString MediaSubType();
        /**
         * @since Available in iOS 8.0 and later.
         */
        @GlobalValue(symbol="AVErrorPresentationTimeStampKey", optional=true)
        public static native NSString PresentationTimeStamp();
        /**
         * @since Available in iOS 8.0 and later.
         */
        @GlobalValue(symbol="AVErrorPersistentTrackIDKey", optional=true)
        public static native NSString PersistentTrackID();
        /**
         * @since Available in iOS 8.0 and later.
         */
        @GlobalValue(symbol="AVErrorFileTypeKey", optional=true)
        public static native NSString FileType();
        /*</values>*/
    }
}
