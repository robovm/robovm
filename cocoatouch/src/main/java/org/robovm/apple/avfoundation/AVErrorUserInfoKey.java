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
package org.robovm.apple.avfoundation;

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
import org.robovm.apple.dispatch.*;
import org.robovm.apple.coreanimation.*;
import org.robovm.apple.corefoundation.*;
import org.robovm.apple.coregraphics.*;
import org.robovm.apple.coreaudio.*;
import org.robovm.apple.coremedia.*;
import org.robovm.apple.corevideo.*;
import org.robovm.apple.audiotoolbox.*;
import org.robovm.apple.mediatoolbox.*;
/*</imports>*/

/*<javadoc>*/
/*</javadoc>*/
@Marshaler(AVErrorUserInfoKey.Marshaler.class)
/*<annotations>*/@Library("AVFoundation")/*</annotations>*/
/*<visibility>*/public/*</visibility>*/ class /*<name>*/AVErrorUserInfoKey/*</name>*/ 
    extends /*<extends>*/NSErrorUserInfoKey/*</extends>*/ 
    /*<implements>*//*</implements>*/ {

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
    
    /*<ptr>*/
    /*</ptr>*/
    /*<bind>*/static { Bro.bind(AVErrorUserInfoKey.class); }/*</bind>*/
    /*<constants>*//*</constants>*/
    /**
     * @since Available in iOS 4.0 and later.
     */
    public static final AVErrorUserInfoKey Device = new AVErrorUserInfoKey("DeviceValue");
    /**
     * @since Available in iOS 4.0 and later.
     */
    public static final AVErrorUserInfoKey Time = new AVErrorUserInfoKey("TimeValue");
    /**
     * @since Available in iOS 4.0 and later.
     */
    public static final AVErrorUserInfoKey FileSize = new AVErrorUserInfoKey("FileSizeValue");
    /**
     * @since Available in iOS 4.0 and later.
     */
    public static final AVErrorUserInfoKey PID = new AVErrorUserInfoKey("PIDValue");
    /**
     * @since Available in iOS 4.0 and later.
     */
    public static final AVErrorUserInfoKey RecordingSuccessfullyFinished = new AVErrorUserInfoKey("RecordingSuccessfullyFinishedValue");
    /**
     * @since Available in iOS 4.3 and later.
     */
    public static final AVErrorUserInfoKey MediaType = new AVErrorUserInfoKey("MediaTypeValue");
    /**
     * @since Available in iOS 4.3 and later.
     */
    public static final AVErrorUserInfoKey MediaSubType = new AVErrorUserInfoKey("MediaSubTypeValue");
    /**
     * @since Available in iOS 8.0 and later.
     */
    public static final AVErrorUserInfoKey PresentationTimeStamp = new AVErrorUserInfoKey("PresentationTimeStampValue");
    /**
     * @since Available in iOS 8.0 and later.
     */
    public static final AVErrorUserInfoKey PersistentTrackID = new AVErrorUserInfoKey("PersistentTrackIDValue");
    /**
     * @since Available in iOS 8.0 and later.
     */
    public static final AVErrorUserInfoKey FileType = new AVErrorUserInfoKey("FileTypeValue");
    
    private static AVErrorUserInfoKey[] values = new AVErrorUserInfoKey[] {Device, Time, FileSize, PID, RecordingSuccessfullyFinished, 
        MediaType, MediaSubType, PresentationTimeStamp, PersistentTrackID, FileType};
    private final LazyGlobalValue<NSString> lazyGlobalValue;
    
    private AVErrorUserInfoKey(String getterName) {
        lazyGlobalValue = new LazyGlobalValue<>(getClass(), getterName);
    }
    /*<constructors>*//*</constructors>*/
    /*<properties>*//*</properties>*/
    /*<members>*//*</members>*/
    public NSString value() {
        return lazyGlobalValue.value();
    }
    
    public static AVErrorUserInfoKey valueOf(NSString value) {
        for (AVErrorUserInfoKey v : values) {
            if (v.value().equals(value)) {
                return v;
            }
        }
        throw new IllegalArgumentException("No constant with value " + value + " found in " 
            + /*<name>*/AVErrorUserInfoKey/*</name>*/.class.getName());
    }
    /*<methods>*/
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="AVErrorDeviceKey", optional=true)
    protected static native NSString DeviceValue();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="AVErrorTimeKey", optional=true)
    protected static native NSString TimeValue();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="AVErrorFileSizeKey", optional=true)
    protected static native NSString FileSizeValue();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="AVErrorPIDKey", optional=true)
    protected static native NSString PIDValue();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="AVErrorRecordingSuccessfullyFinishedKey", optional=true)
    protected static native NSString RecordingSuccessfullyFinishedValue();
    /**
     * @since Available in iOS 4.3 and later.
     */
    @GlobalValue(symbol="AVErrorMediaTypeKey", optional=true)
    protected static native NSString MediaTypeValue();
    /**
     * @since Available in iOS 4.3 and later.
     */
    @GlobalValue(symbol="AVErrorMediaSubTypeKey", optional=true)
    protected static native NSString MediaSubTypeValue();
    /**
     * @since Available in iOS 8.0 and later.
     */
    @GlobalValue(symbol="AVErrorPresentationTimeStampKey", optional=true)
    protected static native NSString PresentationTimeStampValue();
    /**
     * @since Available in iOS 8.0 and later.
     */
    @GlobalValue(symbol="AVErrorPersistentTrackIDKey", optional=true)
    protected static native NSString PersistentTrackIDValue();
    /**
     * @since Available in iOS 8.0 and later.
     */
    @GlobalValue(symbol="AVErrorFileTypeKey", optional=true)
    protected static native NSString FileTypeValue();
    /*</methods>*/
}
