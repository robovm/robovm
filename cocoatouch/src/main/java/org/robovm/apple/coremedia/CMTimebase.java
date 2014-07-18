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
package org.robovm.apple.coremedia;

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
import org.robovm.apple.dispatch.*;
import org.robovm.apple.coreaudio.*;
import org.robovm.apple.coreanimation.*;
import org.robovm.apple.coregraphics.*;
import org.robovm.apple.corevideo.*;
/*</imports>*/

/*<javadoc>*/
/*</javadoc>*/
/*<annotations>*/@Library("CoreMedia")/*</annotations>*/
/*<visibility>*/public/*</visibility>*/ class /*<name>*/CMTimebase/*</name>*/ 
    extends /*<extends>*/CMClockOrTimebase/*</extends>*/ 
    /*<implements>*//*</implements>*/ {

    /*<ptr>*/public static class CMTimebasePtr extends Ptr<CMTimebase, CMTimebasePtr> {}/*</ptr>*/
    /*<bind>*/static { Bro.bind(CMTimebase.class); }/*</bind>*/
    /*<constants>*//*</constants>*/
    /*<constructors>*/
    protected CMTimebase() {}
    /*</constructors>*/
    /*<properties>*//*</properties>*/
    /*<members>*//*</members>*/ 
    public static CMTimebase create(CMClock masterClock) {
        CMTimebasePtr ptr = new CMTimebasePtr();
        create(null, masterClock, ptr);
        return ptr.get();
    }
    
    public static CMTimebase create(CMTimebase masterTimebase) {
        CMTimebasePtr ptr = new CMTimebasePtr();
        create(null, masterTimebase, ptr);
        return ptr.get();
    }   
    /*<methods>*/
    /**
     * @since Available in iOS 6.0 and later.
     */
    @Bridge(symbol="CMTimebaseGetTypeID", optional=true)
    public static native @MachineSizedUInt long getClassTypeID();
    /**
     * @since Available in iOS 6.0 and later.
     */
    @Bridge(symbol="CMTimebaseCreateWithMasterClock", optional=true)
    protected static native int create(CFAllocator allocator, CMClock masterClock, CMTimebase.CMTimebasePtr timebaseOut);
    /**
     * @since Available in iOS 6.0 and later.
     */
    @Bridge(symbol="CMTimebaseCreateWithMasterTimebase", optional=true)
    protected static native int create(CFAllocator allocator, CMTimebase masterTimebase, CMTimebase.CMTimebasePtr timebaseOut);
    /**
     * @since Available in iOS 6.0 and later.
     */
    @Bridge(symbol="CMTimebaseGetMasterTimebase", optional=true)
    public native CMTimebase getMasterTimebase();
    /**
     * @since Available in iOS 6.0 and later.
     */
    @Bridge(symbol="CMTimebaseGetMasterClock", optional=true)
    public native CMClock getMasterClock();
    /**
     * @since Available in iOS 6.0 and later.
     */
    @Bridge(symbol="CMTimebaseGetMaster", optional=true)
    public native CMTimebase getMaster();
    /**
     * @since Available in iOS 6.0 and later.
     */
    @Bridge(symbol="CMTimebaseGetUltimateMasterClock", optional=true)
    public native CMClock getUltimateMasterClock();
    /**
     * @since Available in iOS 6.0 and later.
     */
    @Bridge(symbol="CMTimebaseGetTime", optional=true)
    public native @ByVal CMTime getTime();
    /**
     * @since Available in iOS 6.0 and later.
     */
    @Bridge(symbol="CMTimebaseGetTimeWithTimeScale", optional=true)
    public native @ByVal CMTime getTime(int timescale, CMTimeRoundingMethod method);
    /**
     * @since Available in iOS 6.0 and later.
     */
    @Bridge(symbol="CMTimebaseSetTime", optional=true)
    public native CMTimebaseError setTime(@ByVal CMTime time);
    /**
     * @since Available in iOS 6.0 and later.
     */
    @Bridge(symbol="CMTimebaseSetAnchorTime", optional=true)
    public native CMTimebaseError setAnchorTime(@ByVal CMTime timebaseTime, @ByVal CMTime immediateMasterTime);
    /**
     * @since Available in iOS 6.0 and later.
     */
    @Bridge(symbol="CMTimebaseGetRate", optional=true)
    public native double getRate();
    /**
     * @since Available in iOS 6.0 and later.
     */
    @Bridge(symbol="CMTimebaseSetRate", optional=true)
    public native CMTimebaseError setRate(double rate);
    /**
     * @since Available in iOS 6.0 and later.
     */
    @Bridge(symbol="CMTimebaseSetRateAndAnchorTime", optional=true)
    public native CMTimebaseError setRateAndAnchorTime(double rate, @ByVal CMTime timebaseTime, @ByVal CMTime immediateMasterTime);
    /**
     * @since Available in iOS 6.0 and later.
     */
    @Bridge(symbol="CMTimebaseGetEffectiveRate", optional=true)
    public native double getEffectiveRate();
    /**
     * @since Available in iOS 6.0 and later.
     */
    @Bridge(symbol="CMTimebaseAddTimer", optional=true)
    public native CMTimebaseError addTimer(CFRunLoopTimer timer, CFRunLoop runloop);
    /**
     * @since Available in iOS 6.0 and later.
     */
    @Bridge(symbol="CMTimebaseRemoveTimer", optional=true)
    public native CMTimebaseError removeTimer(CFRunLoopTimer timer);
    /**
     * @since Available in iOS 6.0 and later.
     */
    @Bridge(symbol="CMTimebaseSetTimerNextFireTime", optional=true)
    public native CMTimebaseError setTimerNextFireTime(CFRunLoopTimer timer, @ByVal CMTime fireTime, int flags);
    /**
     * @since Available in iOS 6.0 and later.
     */
    @Bridge(symbol="CMTimebaseSetTimerToFireImmediately", optional=true)
    public native CMTimebaseError setTimerToFireImmediately(CFRunLoopTimer timer);
    /**
     * @since Available in iOS 6.0 and later.
     */
    @Bridge(symbol="CMTimebaseAddTimerDispatchSource", optional=true)
    public native CMTimebaseError addTimerDispatchSource(DispatchSource timerSource);
    /**
     * @since Available in iOS 6.0 and later.
     */
    @Bridge(symbol="CMTimebaseRemoveTimerDispatchSource", optional=true)
    public native CMTimebaseError removeTimerDispatchSource(DispatchSource timerSource);
    /**
     * @since Available in iOS 6.0 and later.
     */
    @Bridge(symbol="CMTimebaseSetTimerDispatchSourceNextFireTime", optional=true)
    public native CMTimebaseError setTimerDispatchSourceNextFireTime(DispatchSource timerSource, @ByVal CMTime fireTime, int flags);
    /**
     * @since Available in iOS 6.0 and later.
     */
    @Bridge(symbol="CMTimebaseSetTimerDispatchSourceToFireImmediately", optional=true)
    public native CMTimebaseError setTimerDispatchSourceToFireImmediately(DispatchSource timerSource);
    /**
     * @since Available in iOS 6.0 and later.
     */
    @Bridge(symbol="CMTimebaseNotificationBarrier", optional=true)
    public native CMTimebaseError notificationBarrier();
    /*</methods>*/
}
