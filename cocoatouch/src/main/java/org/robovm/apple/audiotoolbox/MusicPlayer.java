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
    /*<methods>*/
    /**
     * @since Available in iOS 5.0 and later.
     */
    @Bridge(symbol="NewMusicPlayer", optional=true)
    public static native int create(MusicPlayer.MusicPlayerPtr outPlayer);
    /**
     * @since Available in iOS 5.0 and later.
     */
    @Bridge(symbol="DisposeMusicPlayer", optional=true)
    public native int dispose();
    /**
     * @since Available in iOS 5.0 and later.
     */
    @Bridge(symbol="MusicPlayerSetSequence", optional=true)
    public native int setSequence(MusicSequence inSequence);
    /**
     * @since Available in iOS 5.0 and later.
     */
    @Bridge(symbol="MusicPlayerGetSequence", optional=true)
    public native int getSequence(MusicSequence.MusicSequencePtr outSequence);
    /**
     * @since Available in iOS 5.0 and later.
     */
    @Bridge(symbol="MusicPlayerSetTime", optional=true)
    public native int setTime(double inTime);
    /**
     * @since Available in iOS 5.0 and later.
     */
    @Bridge(symbol="MusicPlayerGetTime", optional=true)
    public native int getTime(DoublePtr outTime);
    /**
     * @since Available in iOS 5.0 and later.
     */
    @Bridge(symbol="MusicPlayerGetHostTimeForBeats", optional=true)
    public native int getHostTimeForBeats(double inBeats, LongPtr outHostTime);
    /**
     * @since Available in iOS 5.0 and later.
     */
    @Bridge(symbol="MusicPlayerGetBeatsForHostTime", optional=true)
    public native int getBeatsForHostTime(long inHostTime, DoublePtr outBeats);
    /**
     * @since Available in iOS 5.0 and later.
     */
    @Bridge(symbol="MusicPlayerPreroll", optional=true)
    public native int preroll();
    /**
     * @since Available in iOS 5.0 and later.
     */
    @Bridge(symbol="MusicPlayerStart", optional=true)
    public native int start();
    /**
     * @since Available in iOS 5.0 and later.
     */
    @Bridge(symbol="MusicPlayerStop", optional=true)
    public native int stop();
    /**
     * @since Available in iOS 5.0 and later.
     */
    @Bridge(symbol="MusicPlayerIsPlaying", optional=true)
    public native int isPlaying(BytePtr outIsPlaying);
    /**
     * @since Available in iOS 5.0 and later.
     */
    @Bridge(symbol="MusicPlayerSetPlayRateScalar", optional=true)
    public native int setPlayRateScalar(double inScaleRate);
    /**
     * @since Available in iOS 5.0 and later.
     */
    @Bridge(symbol="MusicPlayerGetPlayRateScalar", optional=true)
    public native int getPlayRateScalar(DoublePtr outScaleRate);
    /*</methods>*/
}
