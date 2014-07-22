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
package org.robovm.apple.audiotoolbox;

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
import org.robovm.apple.corefoundation.*;
import org.robovm.apple.coregraphics.*;
import org.robovm.apple.opengles.*;
import org.robovm.apple.coreaudio.*;
import org.robovm.apple.coremedia.*;
/*</imports>*/

/*<javadoc>*/
/*</javadoc>*/
/*<annotations>*/@Library("AudioToolbox")/*</annotations>*/
/*<visibility>*/public/*</visibility>*/ class /*<name>*/MusicEventIterator/*</name>*/ 
    extends /*<extends>*/NativeObject/*</extends>*/ 
    /*<implements>*//*</implements>*/ {

    /*<ptr>*/public static class MusicEventIteratorPtr extends Ptr<MusicEventIterator, MusicEventIteratorPtr> {}/*</ptr>*/
    /*<bind>*/static { Bro.bind(MusicEventIterator.class); }/*</bind>*/
    /*<constants>*//*</constants>*/
    /*<constructors>*/
    protected MusicEventIterator() {}
    /*</constructors>*/
    /*<properties>*//*</properties>*/
    /*<members>*//*</members>*/
    /*<methods>*/
    /**
     * @since Available in iOS 5.0 and later.
     */
    @Bridge(symbol="NewMusicEventIterator", optional=true)
    public static native int create(MusicTrack inTrack, MusicEventIterator.MusicEventIteratorPtr outIterator);
    /**
     * @since Available in iOS 5.0 and later.
     */
    @Bridge(symbol="DisposeMusicEventIterator", optional=true)
    public native int dispose();
    /**
     * @since Available in iOS 5.0 and later.
     */
    @Bridge(symbol="MusicEventIteratorSeek", optional=true)
    public native int seek(double inTimeStamp);
    /**
     * @since Available in iOS 5.0 and later.
     */
    @Bridge(symbol="MusicEventIteratorNextEvent", optional=true)
    public native int nextEvent();
    /**
     * @since Available in iOS 5.0 and later.
     */
    @Bridge(symbol="MusicEventIteratorPreviousEvent", optional=true)
    public native int previousEvent();
    /**
     * @since Available in iOS 5.0 and later.
     */
    @Bridge(symbol="MusicEventIteratorGetEventInfo", optional=true)
    public native int getEventInfo(DoublePtr outTimeStamp, MachineSizedUIntPtr outEventType, VoidPtr.VoidPtrPtr outEventData, IntPtr outEventDataSize);
    /**
     * @since Available in iOS 5.0 and later.
     */
    @Bridge(symbol="MusicEventIteratorSetEventInfo", optional=true)
    public native int setEventInfo(MusicEventType inEventType, VoidPtr inEventData);
    /**
     * @since Available in iOS 5.0 and later.
     */
    @Bridge(symbol="MusicEventIteratorSetEventTime", optional=true)
    public native int setEventTime(double inTimeStamp);
    /**
     * @since Available in iOS 5.0 and later.
     */
    @Bridge(symbol="MusicEventIteratorDeleteEvent", optional=true)
    public native int deleteEvent();
    /**
     * @since Available in iOS 5.0 and later.
     */
    @Bridge(symbol="MusicEventIteratorHasPreviousEvent", optional=true)
    public native int hasPreviousEvent(BytePtr outHasPrevEvent);
    /**
     * @since Available in iOS 5.0 and later.
     */
    @Bridge(symbol="MusicEventIteratorHasNextEvent", optional=true)
    public native int hasNextEvent(BytePtr outHasNextEvent);
    /**
     * @since Available in iOS 5.0 and later.
     */
    @Bridge(symbol="MusicEventIteratorHasCurrentEvent", optional=true)
    public native int hasCurrentEvent(BytePtr outHasCurEvent);
    /*</methods>*/
}
