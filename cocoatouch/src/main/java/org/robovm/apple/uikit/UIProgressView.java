/*
 * Copyright (C) 2014 Trillian AB
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
package org.robovm.apple.uikit;

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
import org.robovm.apple.coreanimation.*;
import org.robovm.apple.coregraphics.*;
import org.robovm.apple.coredata.*;
import org.robovm.apple.coreimage.*;
/*</imports>*/

/**
 *
 * <div class="javadoc"></div>
 */
/*<annotations>*/@Library("UIKit") @NativeClass/*</annotations>*/
/*<visibility>*/public/*</visibility>*/ class /*<name>*/UIProgressView/*</name>*/ 
    extends /*<extends>*/UIView/*</extends>*/ 
    /*<implements>*//*</implements>*/ {

    /*<ptr>*/public static class UIProgressViewPtr extends Ptr<UIProgressView, UIProgressViewPtr> {}/*</ptr>*/
    /*<bind>*/static { ObjCRuntime.bind(UIProgressView.class); }/*</bind>*/
    /*<constants>*//*</constants>*/
    /*<constructors>*/
    public UIProgressView() {}
    protected UIProgressView(SkipInit skipInit) { super(skipInit); }
    public UIProgressView(UIProgressViewStyle style) { super((SkipInit) null); initObject(initWithProgressViewStyle$(style)); }
    /*</constructors>*/
    /*<properties>*/
    @Property(selector = "progressViewStyle")
    public native UIProgressViewStyle getProgressViewStyle();
    @Property(selector = "setProgressViewStyle:")
    public native void setProgressViewStyle(UIProgressViewStyle v);
    @Property(selector = "progress")
    public native float getProgress();
    @Property(selector = "setProgress:")
    public native void setProgress(float v);
    @Property(selector = "progressTintColor")
    public native UIColor getProgressTintColor();
    @Property(selector = "setProgressTintColor:")
    public native void setProgressTintColor(UIColor v);
    @Property(selector = "trackTintColor")
    public native UIColor getTrackTintColor();
    @Property(selector = "setTrackTintColor:")
    public native void setTrackTintColor(UIColor v);
    @Property(selector = "progressImage")
    public native UIImage getProgressImage();
    @Property(selector = "setProgressImage:")
    public native void setProgressImage(UIImage v);
    @Property(selector = "trackImage")
    public native UIImage getTrackImage();
    @Property(selector = "setTrackImage:")
    public native void setTrackImage(UIImage v);
    /*</properties>*/
    /*<members>*//*</members>*/
    /*<methods>*/
    @Method(selector = "initWithProgressViewStyle:")
    protected native @Pointer long initWithProgressViewStyle$(UIProgressViewStyle style);
    @Method(selector = "setProgress:animated:")
    public native void setProgress$animated$(float progress, boolean animated);
    /*</methods>*/
}
