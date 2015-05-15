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
/*<visibility>*/public/*</visibility>*/ class /*<name>*/CMClock/*</name>*/ 
    extends /*<extends>*/CMClockOrTimebase/*</extends>*/ 
    /*<implements>*//*</implements>*/ {

    /*<ptr>*/public static class CMClockPtr extends Ptr<CMClock, CMClockPtr> {}/*</ptr>*/
    /*<bind>*/static { Bro.bind(CMClock.class); }/*</bind>*/
    /*<constants>*//*</constants>*/
    /*<constructors>*/
    protected CMClock() {}
    /*</constructors>*/
    /*<properties>*//*</properties>*/
    /*<members>*//*</members>*/
    /**
     * @throws OSStatusException 
     * @since Available in iOS 6.0 and later.
     */
    public static CMClock createAudioClock() throws OSStatusException {
        return createAudioClock(null);
    }
    /**
     * @throws OSStatusException 
     * @since Available in iOS 6.0 and later.
     */
    public static CMClock createAudioClock(CFAllocator allocator) throws OSStatusException {
        CMClockPtr ptr = new CMClockPtr();
        OSStatus status = createAudioClock0(allocator, ptr);
        OSStatusException.throwIfNecessary(status);
        return ptr.get();
    }
    /**
     * @throws OSStatusException 
     * @since Available in iOS 6.0 and later.
     */
    public CMTime getAnchorTime() throws OSStatusException {
        CMTime.CMTimePtr ptr = new CMTime.CMTimePtr();
        OSStatus status = getAnchorTime0(ptr, null);
        OSStatusException.throwIfNecessary(status);
        return ptr.get();
    }
    /**
     * @throws OSStatusException 
     * @since Available in iOS 6.0 and later.
     */
    public CMTime getAnchorReferenceTime() throws OSStatusException {
        CMTime.CMTimePtr ptr = new CMTime.CMTimePtr();
        OSStatus status = getAnchorTime0(null, ptr);
        OSStatusException.throwIfNecessary(status);
        return ptr.get();
    }
    /*<methods>*/
    /**
     * @since Available in iOS 6.0 and later.
     */
    @Bridge(symbol="CMClockGetTypeID", optional=true)
    public static native @MachineSizedUInt long getClassTypeID();
    /**
     * @since Available in iOS 6.0 and later.
     */
    @Bridge(symbol="CMClockGetHostTimeClock", optional=true)
    public static native CMClock getHostTimeClock();
    /**
     * @since Available in iOS 6.0 and later.
     */
    @Bridge(symbol="CMClockConvertHostTimeToSystemUnits", optional=true)
    public static native long convertHostTimeToSystemUnits(@ByVal CMTime hostTime);
    /**
     * @since Available in iOS 6.0 and later.
     */
    @Bridge(symbol="CMClockMakeHostTimeFromSystemUnits", optional=true)
    public static native @ByVal CMTime createHostTimeFromSystemUnits(long hostTime);
    /**
     * @since Available in iOS 6.0 and later.
     */
    @Bridge(symbol="CMClockGetTime", optional=true)
    public native @ByVal CMTime getTime();
    /**
     * @since Available in iOS 6.0 and later.
     */
    @Bridge(symbol="CMClockGetAnchorTime", optional=true)
    protected native OSStatus getAnchorTime0(CMTime.CMTimePtr outClockTime, CMTime.CMTimePtr outReferenceClockTime);
    /**
     * @since Available in iOS 6.0 and later.
     */
    @Bridge(symbol="CMClockMightDrift", optional=true)
    public native boolean mightDrift(CMClock otherClock);
    /**
     * @since Available in iOS 6.0 and later.
     */
    @Bridge(symbol="CMClockInvalidate", optional=true)
    public native void invalidate();
    /**
     * @since Available in iOS 6.0 and later.
     */
    @Bridge(symbol="CMAudioClockCreate", optional=true)
    protected static native OSStatus createAudioClock0(CFAllocator allocator, CMClock.CMClockPtr clockOut);
    /*</methods>*/
}
