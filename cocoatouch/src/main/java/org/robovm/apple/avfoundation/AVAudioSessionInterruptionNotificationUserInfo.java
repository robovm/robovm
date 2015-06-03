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
@Marshaler(AVAudioSessionInterruptionNotificationUserInfo.Marshaler.class)
/*<annotations>*/@Library("AVFoundation")/*</annotations>*/
/*<visibility>*/public/*</visibility>*/ class /*<name>*/AVAudioSessionInterruptionNotificationUserInfo/*</name>*/ 
    extends /*<extends>*/CocoaUtility/*</extends>*/ 
    /*<implements>*//*</implements>*/ {

    public static class Marshaler {
        @SuppressWarnings("unchecked")
        @MarshalsPointer
        public static AVAudioSessionInterruptionNotificationUserInfo toObject(Class<AVAudioSessionInterruptionNotificationUserInfo> cls, long handle, long flags) {
            NSDictionary<NSString, NSObject> o = (NSDictionary<NSString, NSObject>) NSObject.Marshaler.toObject(NSDictionary.class, handle, flags);
            if (o == null) {
                return null;
            }
            return new AVAudioSessionInterruptionNotificationUserInfo(o);
        }
        @MarshalsPointer
        public static long toNative(AVAudioSessionInterruptionNotificationUserInfo o, long flags) {
            if (o == null) {
                return 0L;
            }
            return NSObject.Marshaler.toNative(o.data, flags);
        }
    }
    
    /*<ptr>*/
    /*</ptr>*/
    private NSDictionary<NSString, NSObject> data;
    
    protected AVAudioSessionInterruptionNotificationUserInfo(NSDictionary<NSString, NSObject> data) {
        this.data = data;
    }
    /*<bind>*/static { Bro.bind(AVAudioSessionInterruptionNotificationUserInfo.class); }/*</bind>*/
    /*<constants>*//*</constants>*/
    /*<constructors>*//*</constructors>*/
    /*<properties>*//*</properties>*/
    /*<members>*//*</members>*/
    public NSDictionary<NSString, NSObject> getDictionary() {
        return data;
    }
    
    /**
     * @since Available in iOS 6.0 and later.
     */
    public AVAudioSessionInterruptionType getType() {
        if (data.containsKey(TypeKey())) {
            NSNumber val = (NSNumber) data.get(TypeKey());
            return AVAudioSessionInterruptionType.valueOf(val.longValue());
        }
        return null;
    }
    /**
     * @since Available in iOS 6.0 and later.
     */
    public AVAudioSessionInterruptionOptions getOptions() {
        if (data.containsKey(OptionKey())) {
            NSNumber val = (NSNumber) data.get(OptionKey());
            return new AVAudioSessionInterruptionOptions(val.longValue());
        }
        return null;
    }
    /*<methods>*/
    /**
     * @since Available in iOS 6.0 and later.
     */
    @GlobalValue(symbol="AVAudioSessionInterruptionTypeKey", optional=true)
    protected static native NSString TypeKey();
    /**
     * @since Available in iOS 6.0 and later.
     */
    @GlobalValue(symbol="AVAudioSessionInterruptionOptionKey", optional=true)
    protected static native NSString OptionKey();
    /*</methods>*/
    @Override
    public String toString() {
        if (data != null) return data.toString();
        return super.toString();
    }
}
