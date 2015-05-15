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
/*<visibility>*/public/*</visibility>*/ class /*<name>*/MusicPlayer/*</name>*/ 
    extends /*<extends>*/NativeObject/*</extends>*/ 
    /*<implements>*//*</implements>*/ {

    /*<ptr>*/public static class MusicPlayerPtr extends Ptr<MusicPlayer, MusicPlayerPtr> {}/*</ptr>*/
    /*<bind>*/static { Bro.bind(MusicPlayer.class); }/*</bind>*/
    /*<constants>*//*</constants>*/
    /*<constructors>*/
    protected MusicPlayer() {}
    /*</constructors>*/
    /*<properties>*//*</properties>*/
    /*<members>*//*</members>*/
    /**
     * @throws OSStatusException 
     * @since Available in iOS 5.0 and later.
     */
    public static MusicPlayer create() throws OSStatusException {
        MusicPlayer.MusicPlayerPtr ptr = new MusicPlayer.MusicPlayerPtr();
        OSStatus status = create0(ptr);
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
    public void setSequence(MusicSequence sequence) throws OSStatusException {
        OSStatus status = setSequence0(sequence);
        OSStatusException.throwIfNecessary(status);
    }
    /**
     * @throws OSStatusException 
     * @since Available in iOS 5.0 and later.
     */
    public MusicSequence getSequence() throws OSStatusException {
        MusicSequence.MusicSequencePtr ptr = new MusicSequence.MusicSequencePtr();
        OSStatus status = getSequence0(ptr);
        OSStatusException.throwIfNecessary(status);
        return ptr.get();
    }
    /**
     * @throws OSStatusException 
     * @since Available in iOS 5.0 and later.
     */
    public void setTime(double time) throws OSStatusException {
        OSStatus status = setTime0(time);
        OSStatusException.throwIfNecessary(status);
    }
    /**
     * @throws OSStatusException 
     * @since Available in iOS 5.0 and later.
     */
    public double getTime() throws OSStatusException {
        DoublePtr ptr = new DoublePtr();
        OSStatus status = getTime0(ptr);
        OSStatusException.throwIfNecessary(status);
        return ptr.get();
    }
    /**
     * @throws OSStatusException 
     * @since Available in iOS 5.0 and later.
     */
    public long getHostTimeForBeats(double beats) throws OSStatusException {
        LongPtr ptr = new LongPtr();
        OSStatus status = getHostTimeForBeats0(beats, ptr);
        OSStatusException.throwIfNecessary(status);
        return ptr.get();
    }
    /**
     * @throws OSStatusException 
     * @since Available in iOS 5.0 and later.
     */
    public double getBeatsForHostTime(long hostTime) throws OSStatusException {
        DoublePtr ptr = new DoublePtr();
        OSStatus status = getBeatsForHostTime0(hostTime, ptr);
        OSStatusException.throwIfNecessary(status);
        return ptr.get();
    }
    /**
     * @throws OSStatusException 
     * @since Available in iOS 5.0 and later.
     */
    public void preroll() throws OSStatusException {
        OSStatus status = preroll0();
        OSStatusException.throwIfNecessary(status);
    }
    /**
     * @throws OSStatusException 
     * @since Available in iOS 5.0 and later.
     */
    public void start() throws OSStatusException {
        OSStatus status = start0();
        OSStatusException.throwIfNecessary(status);
    }
    /**
     * @throws OSStatusException 
     * @since Available in iOS 5.0 and later.
     */
    public void stop() throws OSStatusException {
        OSStatus status = stop0();
        OSStatusException.throwIfNecessary(status);
    }
    /**
     * @throws OSStatusException 
     * @since Available in iOS 5.0 and later.
     */
    public boolean isPlaying() throws OSStatusException {
        BooleanPtr ptr = new BooleanPtr();
        OSStatus status = isPlaying0(ptr);
        OSStatusException.throwIfNecessary(status);
        return ptr.get();
    }
    /**
     * @throws OSStatusException 
     * @since Available in iOS 5.0 and later.
     */
    public void setPlayRateScalar(double scaleRate) throws OSStatusException {
        OSStatus status = setPlayRateScalar0(scaleRate);
        OSStatusException.throwIfNecessary(status);
    }
    /**
     * @throws OSStatusException 
     * @since Available in iOS 5.0 and later.
     */
    public double getPlayRateScalar() throws OSStatusException {
        DoublePtr ptr = new DoublePtr();
        OSStatus status = getPlayRateScalar0(ptr);
        OSStatusException.throwIfNecessary(status);
        return ptr.get();
    }
    /*<methods>*/
    /**
     * @since Available in iOS 5.0 and later.
     */
    @Bridge(symbol="NewMusicPlayer", optional=true)
    protected static native OSStatus create0(MusicPlayer.MusicPlayerPtr outPlayer);
    /**
     * @since Available in iOS 5.0 and later.
     */
    @Bridge(symbol="DisposeMusicPlayer", optional=true)
    protected native OSStatus dispose0();
    /**
     * @since Available in iOS 5.0 and later.
     */
    @Bridge(symbol="MusicPlayerSetSequence", optional=true)
    protected native OSStatus setSequence0(MusicSequence inSequence);
    /**
     * @since Available in iOS 5.0 and later.
     */
    @Bridge(symbol="MusicPlayerGetSequence", optional=true)
    protected native OSStatus getSequence0(MusicSequence.MusicSequencePtr outSequence);
    /**
     * @since Available in iOS 5.0 and later.
     */
    @Bridge(symbol="MusicPlayerSetTime", optional=true)
    protected native OSStatus setTime0(double inTime);
    /**
     * @since Available in iOS 5.0 and later.
     */
    @Bridge(symbol="MusicPlayerGetTime", optional=true)
    protected native OSStatus getTime0(DoublePtr outTime);
    /**
     * @since Available in iOS 5.0 and later.
     */
    @Bridge(symbol="MusicPlayerGetHostTimeForBeats", optional=true)
    protected native OSStatus getHostTimeForBeats0(double inBeats, LongPtr outHostTime);
    /**
     * @since Available in iOS 5.0 and later.
     */
    @Bridge(symbol="MusicPlayerGetBeatsForHostTime", optional=true)
    protected native OSStatus getBeatsForHostTime0(long inHostTime, DoublePtr outBeats);
    /**
     * @since Available in iOS 5.0 and later.
     */
    @Bridge(symbol="MusicPlayerPreroll", optional=true)
    protected native OSStatus preroll0();
    /**
     * @since Available in iOS 5.0 and later.
     */
    @Bridge(symbol="MusicPlayerStart", optional=true)
    protected native OSStatus start0();
    /**
     * @since Available in iOS 5.0 and later.
     */
    @Bridge(symbol="MusicPlayerStop", optional=true)
    protected native OSStatus stop0();
    /**
     * @since Available in iOS 5.0 and later.
     */
    @Bridge(symbol="MusicPlayerIsPlaying", optional=true)
    protected native OSStatus isPlaying0(BooleanPtr outIsPlaying);
    /**
     * @since Available in iOS 5.0 and later.
     */
    @Bridge(symbol="MusicPlayerSetPlayRateScalar", optional=true)
    protected native OSStatus setPlayRateScalar0(double inScaleRate);
    /**
     * @since Available in iOS 5.0 and later.
     */
    @Bridge(symbol="MusicPlayerGetPlayRateScalar", optional=true)
    protected native OSStatus getPlayRateScalar0(DoublePtr outScaleRate);
    /*</methods>*/
}
