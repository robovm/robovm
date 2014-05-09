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
package org.robovm.apple.mediaplayer;

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
import org.robovm.apple.uikit.*;
import org.robovm.apple.coregraphics.*;
/*</imports>*/

/**
 *
 * <div class="javadoc"></div>
 */
/*<annotations>*/@Library("MediaPlayer") @NativeClass/*</annotations>*/
/*<visibility>*/public/*</visibility>*/ class /*<name>*/MPVolumeView/*</name>*/ 
    extends /*<extends>*/UIView/*</extends>*/ 
    /*<implements>*/implements NSCoding/*</implements>*/ {

    /*<ptr>*/public static class MPVolumeViewPtr extends Ptr<MPVolumeView, MPVolumeViewPtr> {}/*</ptr>*/
    /*<bind>*/static { ObjCRuntime.bind(MPVolumeView.class); }/*</bind>*/
    /*<constants>*//*</constants>*/
    /*<constructors>*/
    public MPVolumeView() {}
    protected MPVolumeView(SkipInit skipInit) { super(skipInit); }
    /*</constructors>*/
    /*<properties>*/
    @Property(selector = "showsVolumeSlider")
    public native boolean isShowsVolumeSlider();
    @Property(selector = "setShowsVolumeSlider:")
    public native void setShowsVolumeSlider(boolean v);
    @Property(selector = "showsRouteButton")
    public native boolean isShowsRouteButton();
    @Property(selector = "setShowsRouteButton:")
    public native void setShowsRouteButton(boolean v);
    @Property(selector = "areWirelessRoutesAvailable")
    public native boolean isWirelessRoutesAvailable();
    @Property(selector = "isWirelessRouteActive")
    public native boolean isWirelessRouteActive();
    @Property(selector = "volumeWarningSliderImage")
    public native UIImage getVolumeWarningSliderImage();
    @Property(selector = "setVolumeWarningSliderImage:")
    public native void setVolumeWarningSliderImage(UIImage v);
    /*</properties>*/
    /*<members>*//*</members>*/
    /*<methods>*/
    @GlobalValue(symbol="MPVolumeViewWirelessRoutesAvailableDidChangeNotification", optional=true)
    public static native String NotificationWirelessRoutesAvailableDidChange();
    @GlobalValue(symbol="MPVolumeViewWirelessRouteActiveDidChangeNotification", optional=true)
    public static native String NotificationWirelessRouteActiveDidChange();
    
    @Method(selector = "sizeThatFits:")
    public native @ByVal CGSize calculateSizeThatFits(@ByVal CGSize size);
    @Method(selector = "setMinimumVolumeSliderImage:forState:")
    public native void setMinimumVolumeSliderImage(UIImage image, UIControlState state);
    @Method(selector = "setMaximumVolumeSliderImage:forState:")
    public native void setMaximumVolumeSliderImage(UIImage image, UIControlState state);
    @Method(selector = "setVolumeThumbImage:forState:")
    public native void setVolumeThumbImage(UIImage image, UIControlState state);
    @Method(selector = "minimumVolumeSliderImageForState:")
    public native UIImage getMinimumVolumeSliderImage(UIControlState state);
    @Method(selector = "maximumVolumeSliderImageForState:")
    public native UIImage getMaximumVolumeSliderImage(UIControlState state);
    @Method(selector = "volumeThumbImageForState:")
    public native UIImage getVolumeThumbImage(UIControlState state);
    @Method(selector = "volumeSliderRectForBounds:")
    public native @ByVal CGRect getVolumeSliderRect(@ByVal CGRect bounds);
    @Method(selector = "volumeThumbRectForBounds:volumeSliderRect:value:")
    public native @ByVal CGRect getVolumeThumbRect(@ByVal CGRect bounds, @ByVal CGRect rect, float value);
    @Method(selector = "setRouteButtonImage:forState:")
    public native void setRouteButtonImage(UIImage image, UIControlState state);
    @Method(selector = "routeButtonImageForState:")
    public native UIImage getRouteButtonImage(UIControlState state);
    @Method(selector = "routeButtonRectForBounds:")
    public native @ByVal CGRect getRouteButtonRect(@ByVal CGRect bounds);
    /*</methods>*/
}
