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
import org.robovm.apple.audiotoolbox.*;
/*</imports>*/

/*<javadoc>*/
/*</javadoc>*/
/*<annotations>*/@Library("CoreMedia")/*</annotations>*/
/*<visibility>*/public/*</visibility>*/ class /*<name>*/CMTimebase/*</name>*/ 
    extends /*<extends>*/CMClockOrTimebase/*</extends>*/ 
    /*<implements>*//*</implements>*/ {

    public static class Notifications {
        /**
         * @since Available in iOS 6.0 and later.
         */
        public static NSObject observeEffectiveRateChanged(CMTimebase object, final VoidBlock2<CMTimebase, CMTime> block) {
            return NSNotificationCenter.getDefaultCenter().addObserver(EffectiveRateChangedNotification(), object.as(NSObject.class), NSOperationQueue.getMainQueue(), new VoidBlock1<NSNotification>() {
                @Override
                public void invoke(NSNotification a) {
                    CMTimebase object = a.getObject() != null ? a.getObject().as(CMTimebase.class) : null;
                    CMTime time = null;
                    
                    NSDictionary<NSString, NSObject> data = a.getUserInfo();
                    if (data.containsKey(EventTimeNotificationKey())) {
                        time = data.get(EventTimeNotificationKey()).as(CMTime.class);
                    }
                    block.invoke(object, time);
                }
            });
        }
        /**
         * @since Available in iOS 6.0 and later.
         */
        public static NSObject observeTimeJumped(CMTimebase object, final VoidBlock2<CMTimebase, CMTime> block) {
            return NSNotificationCenter.getDefaultCenter().addObserver(TimeJumpedNotification(), object.as(NSObject.class), NSOperationQueue.getMainQueue(), new VoidBlock1<NSNotification>() {
                @Override
                public void invoke(NSNotification a) {
                    CMTimebase object = a.getObject() != null ? a.getObject().as(CMTimebase.class) : null;
                    CMTime time = null;
                    
                    NSDictionary<NSString, NSObject> data = a.getUserInfo();
                    if (data.containsKey(EventTimeNotificationKey())) {
                        time = data.get(EventTimeNotificationKey()).as(CMTime.class);
                    }
                    block.invoke(object, time);
                }
            });
        }
    }
    
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
    @GlobalValue(symbol="kCMTimebaseNotification_EffectiveRateChanged", optional=true)
    public static native NSString EffectiveRateChangedNotification();
    /**
     * @since Available in iOS 6.0 and later.
     */
    @GlobalValue(symbol="kCMTimebaseNotification_TimeJumped", optional=true)
    public static native NSString TimeJumpedNotification();
    /**
     * @since Available in iOS 7.0 and later.
     */
    @GlobalValue(symbol="kCMTimebaseNotificationKey_EventTime", optional=true)
    protected static native NSString EventTimeNotificationKey();
    
    /**
     * @since Available in iOS 6.0 and later.
     */
    @Bridge(symbol="CMTimebaseGetTypeID", optional=true)
    public static native @MachineSizedUInt long getClassTypeID();
    /**
     * @since Available in iOS 6.0 and later.
     */
    @Bridge(symbol="CMTimebaseCreateWithMasterClock", optional=true)
    protected static native OSStatus create(CFAllocator allocator, CMClock masterClock, CMTimebase.CMTimebasePtr timebaseOut);
    /**
     * @since Available in iOS 6.0 and later.
     */
    @Bridge(symbol="CMTimebaseCreateWithMasterTimebase", optional=true)
    protected static native OSStatus create(CFAllocator allocator, CMTimebase masterTimebase, CMTimebase.CMTimebasePtr timebaseOut);
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
    public native OSStatus setTime(@ByVal CMTime time);
    /**
     * @since Available in iOS 6.0 and later.
     */
    @Bridge(symbol="CMTimebaseSetAnchorTime", optional=true)
    public native OSStatus setAnchorTime(@ByVal CMTime timebaseTime, @ByVal CMTime immediateMasterTime);
    /**
     * @since Available in iOS 6.0 and later.
     */
    @Bridge(symbol="CMTimebaseGetRate", optional=true)
    public native double getRate();
    /**
     * @since Available in iOS 6.0 and later.
     */
    @Bridge(symbol="CMTimebaseSetRate", optional=true)
    public native OSStatus setRate(double rate);
    /**
     * @since Available in iOS 6.0 and later.
     */
    @Bridge(symbol="CMTimebaseSetRateAndAnchorTime", optional=true)
    public native OSStatus setRateAndAnchorTime(double rate, @ByVal CMTime timebaseTime, @ByVal CMTime immediateMasterTime);
    /**
     * @since Available in iOS 6.0 and later.
     */
    @Bridge(symbol="CMTimebaseGetEffectiveRate", optional=true)
    public native double getEffectiveRate();
    /**
     * @since Available in iOS 6.0 and later.
     */
    @Bridge(symbol="CMTimebaseAddTimer", optional=true)
    public native OSStatus addTimer(NSTimer timer, CFRunLoop runloop);
    /**
     * @since Available in iOS 6.0 and later.
     */
    @Bridge(symbol="CMTimebaseRemoveTimer", optional=true)
    public native OSStatus removeTimer(NSTimer timer);
    /**
     * @since Available in iOS 6.0 and later.
     */
    @Bridge(symbol="CMTimebaseSetTimerNextFireTime", optional=true)
    public native OSStatus setTimerNextFireTime(NSTimer timer, @ByVal CMTime fireTime, int flags);
    /**
     * @since Available in iOS 6.0 and later.
     */
    @Bridge(symbol="CMTimebaseSetTimerToFireImmediately", optional=true)
    public native OSStatus setTimerToFireImmediately(NSTimer timer);
    /**
     * @since Available in iOS 6.0 and later.
     */
    @Bridge(symbol="CMTimebaseAddTimerDispatchSource", optional=true)
    public native OSStatus addTimerDispatchSource(DispatchSource timerSource);
    /**
     * @since Available in iOS 6.0 and later.
     */
    @Bridge(symbol="CMTimebaseRemoveTimerDispatchSource", optional=true)
    public native OSStatus removeTimerDispatchSource(DispatchSource timerSource);
    /**
     * @since Available in iOS 6.0 and later.
     */
    @Bridge(symbol="CMTimebaseSetTimerDispatchSourceNextFireTime", optional=true)
    public native OSStatus setTimerDispatchSourceNextFireTime(DispatchSource timerSource, @ByVal CMTime fireTime, int flags);
    /**
     * @since Available in iOS 6.0 and later.
     */
    @Bridge(symbol="CMTimebaseSetTimerDispatchSourceToFireImmediately", optional=true)
    public native OSStatus setTimerDispatchSourceToFireImmediately(DispatchSource timerSource);
    /**
     * @since Available in iOS 6.0 and later.
     */
    @Bridge(symbol="CMTimebaseNotificationBarrier", optional=true)
    public native OSStatus notificationBarrier();
    /*</methods>*/
}
