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
/*</imports>*/

/*<javadoc>*/
/*</javadoc>*/
/*<annotations>*/@Library("CoreMedia")/*</annotations>*/
/*<visibility>*/public/*</visibility>*/ class /*<name>*/CMClockOrTimebase/*</name>*/ 
    extends /*<extends>*/CFType/*</extends>*/ 
    /*<implements>*//*</implements>*/ {

    /*<ptr>*/public static class CMClockOrTimebasePtr extends Ptr<CMClockOrTimebase, CMClockOrTimebasePtr> {}/*</ptr>*/
    /*<bind>*/static { Bro.bind(CMClockOrTimebase.class); }/*</bind>*/
    /*<constants>*//*</constants>*/
    /*<constructors>*//*</constructors>*/
    /*<properties>*//*</properties>*/
    /*<members>*//*</members>*/
    public @ByVal CMTime getAnchorTime() {
        CMTime.CMTimePtr ptr = new CMTime.CMTimePtr();
        getRelativeRateAndAnchorTime(this, null, ptr, null);
        return ptr.get();
    }
    
    public @ByVal CMTime getRelativeAnchorTime() {
        CMTime.CMTimePtr ptr = new CMTime.CMTimePtr();
        getRelativeRateAndAnchorTime(this, null, null, ptr);
        return ptr.get();
    }
    
    public @ByVal CMTime convertTime(@ByVal CMTime time, CMClockOrTimebase toClockOrTimebase) {
        return convertTime(time, this, toClockOrTimebase);
    }
    /*<methods>*/
    /**
     * @since Available in iOS 6.0 and later.
     */
    @Bridge(symbol="CMSyncGetRelativeRate", optional=true)
    public native double getRelativeRate(CMClockOrTimebase relativeToClockOrTimebase);
    /**
     * @since Available in iOS 6.0 and later.
     */
    @Bridge(symbol="CMSyncGetRelativeRateAndAnchorTime", optional=true)
    protected native int getRelativeRateAndAnchorTime(CMClockOrTimebase relativeToClockOrTimebase, DoublePtr outRelativeRate, CMTime.CMTimePtr outOfClockOrTimebaseAnchorTime, CMTime.CMTimePtr outRelativeToClockOrTimebaseAnchorTime);
    /**
     * @since Available in iOS 6.0 and later.
     */
    @Bridge(symbol="CMSyncConvertTime", optional=true)
    protected static native @ByVal CMTime convertTime(@ByVal CMTime time, CMClockOrTimebase fromClockOrTimebase, CMClockOrTimebase toClockOrTimebase);
    /**
     * @since Available in iOS 6.0 and later.
     */
    @Bridge(symbol="CMSyncMightDrift", optional=true)
    public native boolean mightDrift(CMClockOrTimebase clockOrTimebase2);
    /**
     * @since Available in iOS 6.0 and later.
     */
    @Bridge(symbol="CMSyncGetTime", optional=true)
    public native @ByVal CMTime getTime();
    /*</methods>*/
}
