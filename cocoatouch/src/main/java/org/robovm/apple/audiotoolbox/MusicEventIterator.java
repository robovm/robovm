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
import org.robovm.apple.audiounit.*;
import org.robovm.apple.coreaudio.*;
import org.robovm.apple.coremedia.*;
import org.robovm.apple.coremidi.*;
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
    /**
     * @throws OSStatusException 
     * @since Available in iOS 5.0 and later.
     */
    public static MusicEventIterator create(MusicTrack track) throws OSStatusException {
        MusicEventIterator.MusicEventIteratorPtr ptr = new MusicEventIterator.MusicEventIteratorPtr();
        OSStatus status = create0(track, ptr);
        OSStatusException.throwIfNecessary(status);
        return ptr.get();
    }
    /**
     * @throws OSStatusException 
     * @since Available in iOS 5.0 and later.
     */
    public void dispose() throws OSStatusException {
        OSStatus status = dispose0();
        OSStatusException.throwIfNecessary(status);
    }
    /**
     * @throws OSStatusException 
     * @since Available in iOS 5.0 and later.
     */
    public void seek(double timeStamp) throws OSStatusException {
        OSStatus status = seek0(timeStamp);
        OSStatusException.throwIfNecessary(status);
    }
    /**
     * @throws OSStatusException 
     * @since Available in iOS 5.0 and later.
     */
    public void nextEvent() throws OSStatusException {
        OSStatus status = nextEvent0();
        OSStatusException.throwIfNecessary(status);
    }
    /**
     * @throws OSStatusException 
     * @since Available in iOS 5.0 and later.
     */
    public void previousEvent() throws OSStatusException {
        OSStatus status = previousEvent0();
        OSStatusException.throwIfNecessary(status);
    }

    private double eventTime;
    private MusicEventType eventType;
    private VoidPtr eventData;
    private int eventDataSize;
    
    /**
     * @throws OSStatusException 
     * @since Available in iOS 5.0 and later.
     */
    public void fetchEventInfo() throws OSStatusException {
        DoublePtr timeStampPtr = new DoublePtr();
        IntPtr eventTypePtr = new IntPtr();
        VoidPtr.VoidPtrPtr eventDataPtr = new VoidPtr.VoidPtrPtr();
        IntPtr eventDataSizePtr = new IntPtr();
        OSStatus status = getEventInfo0(timeStampPtr, eventTypePtr, eventDataPtr, eventDataSizePtr);
        OSStatusException.throwIfNecessary(status);
        
        eventTime = timeStampPtr.get();
        eventType = MusicEventType.valueOf(eventTypePtr.get());
        eventData = eventDataPtr.get();
        eventDataSize = eventDataSizePtr.get();
    }
    public double getEventTime() {
        return eventTime;
    }
    public MusicEventType getEventType() {
        return eventType;
    }
    public <T extends Struct<T>> T getEventData(Class<T> type) {
        T data = Struct.allocate(type);
        data.update(eventData.as(type));
        return data;
    }
    public int getEventDataSize() {
        return eventDataSize;
    }
    
    /**
     * @throws OSStatusException 
     * @since Available in iOS 5.0 and later.
     */
    public <T extends Struct<T>> void setEventData(MusicEventType type, T data) throws OSStatusException {
        OSStatus status = setEventInfo0(type, data.as(VoidPtr.class));
        OSStatusException.throwIfNecessary(status);
    }
    /**
     * @throws OSStatusException 
     * @since Available in iOS 5.0 and later.
     */
    public void setEventTime(double timeStamp) throws OSStatusException {
        OSStatus status = setEventTime0(timeStamp);
        OSStatusException.throwIfNecessary(status);
    }
    /**
     * @throws OSStatusException 
     * @since Available in iOS 5.0 and later.
     */
    public void deleteEvent() throws OSStatusException {
        OSStatus status = deleteEvent0();
        OSStatusException.throwIfNecessary(status);
    }
    /**
     * @throws OSStatusException 
     * @since Available in iOS 5.0 and later.
     */
    public boolean hasPreviousEvent() throws OSStatusException {
        BooleanPtr ptr = new BooleanPtr();
        OSStatus status = hasPreviousEvent0(ptr);
        OSStatusException.throwIfNecessary(status);
        return ptr.get();
    }
    /**
     * @throws OSStatusException 
     * @since Available in iOS 5.0 and later.
     */
    public boolean hasNextEvent() throws OSStatusException {
        BooleanPtr ptr = new BooleanPtr();
        OSStatus status = hasNextEvent0(ptr);
        OSStatusException.throwIfNecessary(status);
        return ptr.get(); 
    }
    /**
     * @throws OSStatusException 
     * @since Available in iOS 5.0 and later.
     */
    public boolean hasCurrentEvent() throws OSStatusException {
        BooleanPtr ptr = new BooleanPtr();
        OSStatus status = hasCurrentEvent0(ptr);
        OSStatusException.throwIfNecessary(status);
        return ptr.get(); 
    }
    /*<methods>*/
    /**
     * @since Available in iOS 5.0 and later.
     */
    @Bridge(symbol="NewMusicEventIterator", optional=true)
    protected static native OSStatus create0(MusicTrack inTrack, MusicEventIterator.MusicEventIteratorPtr outIterator);
    /**
     * @since Available in iOS 5.0 and later.
     */
    @Bridge(symbol="DisposeMusicEventIterator", optional=true)
    protected native OSStatus dispose0();
    /**
     * @since Available in iOS 5.0 and later.
     */
    @Bridge(symbol="MusicEventIteratorSeek", optional=true)
    protected native OSStatus seek0(double inTimeStamp);
    /**
     * @since Available in iOS 5.0 and later.
     */
    @Bridge(symbol="MusicEventIteratorNextEvent", optional=true)
    protected native OSStatus nextEvent0();
    /**
     * @since Available in iOS 5.0 and later.
     */
    @Bridge(symbol="MusicEventIteratorPreviousEvent", optional=true)
    protected native OSStatus previousEvent0();
    /**
     * @since Available in iOS 5.0 and later.
     */
    @Bridge(symbol="MusicEventIteratorGetEventInfo", optional=true)
    protected native OSStatus getEventInfo0(DoublePtr outTimeStamp, IntPtr outEventType, VoidPtr.VoidPtrPtr outEventData, IntPtr outEventDataSize);
    /**
     * @since Available in iOS 5.0 and later.
     */
    @Bridge(symbol="MusicEventIteratorSetEventInfo", optional=true)
    protected native OSStatus setEventInfo0(MusicEventType inEventType, VoidPtr inEventData);
    /**
     * @since Available in iOS 5.0 and later.
     */
    @Bridge(symbol="MusicEventIteratorSetEventTime", optional=true)
    protected native OSStatus setEventTime0(double inTimeStamp);
    /**
     * @since Available in iOS 5.0 and later.
     */
    @Bridge(symbol="MusicEventIteratorDeleteEvent", optional=true)
    protected native OSStatus deleteEvent0();
    /**
     * @since Available in iOS 5.0 and later.
     */
    @Bridge(symbol="MusicEventIteratorHasPreviousEvent", optional=true)
    protected native OSStatus hasPreviousEvent0(BooleanPtr outHasPrevEvent);
    /**
     * @since Available in iOS 5.0 and later.
     */
    @Bridge(symbol="MusicEventIteratorHasNextEvent", optional=true)
    protected native OSStatus hasNextEvent0(BooleanPtr outHasNextEvent);
    /**
     * @since Available in iOS 5.0 and later.
     */
    @Bridge(symbol="MusicEventIteratorHasCurrentEvent", optional=true)
    protected native OSStatus hasCurrentEvent0(BooleanPtr outHasCurEvent);
    /*</methods>*/
}
