/*
 * Copyright (C) 2012 RoboVM
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
package org.robovm.cocoatouch.uikit;

/*<imports>*/
import org.robovm.cocoatouch.coreanimation.*;
import org.robovm.cocoatouch.coregraphics.*;
import org.robovm.cocoatouch.coreimage.*;
import org.robovm.cocoatouch.foundation.*;
import java.util.*;
import org.robovm.objc.*;
import org.robovm.objc.bind.*;
import org.robovm.objc.block.*;
import org.robovm.rt.bro.annotation.*;
import org.robovm.rt.bro.ptr.*;
/*</imports>*/

/*<library>*/@Library("UIKit")/*</library>*/
public class /*<name>*/ UIScreen /*</name>*/ 
    extends /*<extends>*/ NSObject /*</extends>*/ 
    /*<implements>*/ /*</implements>*/ {

    static {
        ObjCRuntime.bind(/*<name>*/ UIScreen /*</name>*/.class);
    }

    /*<constructors>*/
    public UIScreen() {}
    
    /*</constructors>*/
    /*<properties>*/
    @Bind("applicationFrame") public native @Type("CGRect") CGRect getApplicationFrame();
    @Bind("availableModes") public native @Type("NSArray *") NSArray getAvailableModes();
    @Bind("bounds") public native @Type("CGRect") CGRect getBounds();
    @Bind("brightness") public native @Type("CGFloat") float getBrightness();
    @Bind("setBrightness:") public native void setBrightness(@Type("CGFloat") float v);
    @Bind("currentMode") public native @Type("UIScreenMode *") UIScreenMode getCurrentMode();
    @Bind("setCurrentMode:") public native void setCurrentMode(@Type("UIScreenMode *") UIScreenMode v);
    @Bind("mirroredScreen") public native @Type("UIScreen *") UIScreen getMirroredScreen();
    @Bind("overscanCompensation") public native @Type("UIScreenOverscanCompensation") UIScreenOverscanCompensation getOverscanCompensation();
    @Bind("setOverscanCompensation:") public native void setOverscanCompensation(@Type("UIScreenOverscanCompensation") UIScreenOverscanCompensation v);
    @Bind("preferredMode") public native @Type("UIScreenMode *") UIScreenMode getPreferredMode();
    @Bind("scale") public native @Type("CGFloat") float getScale();
    @Bind("wantsSoftwareDimming") public native @Type("BOOL") boolean isWantsSoftwareDimming();
    @Bind("setWantsSoftwareDimming:") public native void setWantsSoftwareDimming(@Type("BOOL") boolean v);
    /*</properties>*/
    /*<methods>*/
    @Bind("mainScreen") public native static @Type("UIScreen *") UIScreen getMainScreen();
    @Bind("screens") public native static @Type("NSArray *") NSArray getScreens();
    @Bind("displayLinkWithTarget:selector:") public native @Type("CADisplayLink *") CADisplayLink createDisplayLink(@Type("id") NSObject target, @Type("SEL") Selector sel);
    /*</methods>*/

}
