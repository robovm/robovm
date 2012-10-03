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
public class /*<name>*/ UIWindow /*</name>*/ 
    extends /*<extends>*/ UIView /*</extends>*/ 
    /*<implements>*/ /*</implements>*/ {

    static {
        ObjCRuntime.bind(/*<name>*/ UIWindow /*</name>*/.class);
    }

    /*<constructors>*/
    public UIWindow() {}
    
    /*</constructors>*/
    /*<properties>*/
    @Bind("isKeyWindow") public native @Type("BOOL") boolean isKeyWindow();
    @Bind("rootViewController") public native @Type("UIViewController *") UIViewController getRootViewController();
    @Bind("setRootViewController:") public native void setRootViewController(@Type("UIViewController *") UIViewController v);
    @Bind("screen") public native @Type("UIScreen *") UIScreen getScreen();
    @Bind("setScreen:") public native void setScreen(@Type("UIScreen *") UIScreen v);
    @Bind("windowLevel") public native @Type("UIWindowLevel") float getWindowLevel();
    @Bind("setWindowLevel:") public native void setWindowLevel(@Type("UIWindowLevel") float v);
    /*</properties>*/
    /*<methods>*/
    @Bind("becomeKeyWindow") public native @Type("void") void becomeKeyWindow();
    @Bind("convertPoint:fromWindow:") public native @Type("CGPoint") CGPoint convertPointFromWindow(@Type("CGPoint") CGPoint point, @Type("UIWindow *") UIWindow window);
    @Bind("convertPoint:toWindow:") public native @Type("CGPoint") CGPoint convertPointToWindow(@Type("CGPoint") CGPoint point, @Type("UIWindow *") UIWindow window);
    @Bind("convertRect:fromWindow:") public native @Type("CGRect") CGRect convertRectFromWindow(@Type("CGRect") CGRect rect, @Type("UIWindow *") UIWindow window);
    @Bind("convertRect:toWindow:") public native @Type("CGRect") CGRect convertRectToWindow(@Type("CGRect") CGRect rect, @Type("UIWindow *") UIWindow window);
    @Bind("makeKeyAndVisible") public native @Type("void") void makeKeyAndVisible();
    @Bind("makeKeyWindow") public native @Type("void") void makeKeyWindow();
    @Bind("resignKeyWindow") public native @Type("void") void resignKeyWindow();
    @Bind("sendEvent:") public native @Type("void") void sendEvent(@Type("UIEvent *") UIEvent event);
    /*</methods>*/

}
