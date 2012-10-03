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
public class /*<name>*/ UIMenuController /*</name>*/ 
    extends /*<extends>*/ NSObject /*</extends>*/ 
    /*<implements>*/ /*</implements>*/ {

    static {
        ObjCRuntime.bind(/*<name>*/ UIMenuController /*</name>*/.class);
    }

    /*<constructors>*/
    public UIMenuController() {}
    
    /*</constructors>*/
    /*<properties>*/
    @Bind("arrowDirection") public native @Type("UIMenuControllerArrowDirection") UIMenuControllerArrowDirection getArrowDirection();
    @Bind("setArrowDirection:") public native void setArrowDirection(@Type("UIMenuControllerArrowDirection") UIMenuControllerArrowDirection v);
    @Bind("menuFrame") public native @Type("CGRect") CGRect getMenuFrame();
    @Bind("menuItems") public native @Type("NSArray *") NSArray getMenuItems();
    @Bind("setMenuItems:") public native void setMenuItems(@Type("NSArray *") NSArray v);
    @Bind("isMenuVisible") public native @Type("BOOL") boolean isMenuVisible();
    @Bind("setMenuVisible:") public native void setMenuVisible(@Type("BOOL") boolean v);
    /*</properties>*/
    /*<methods>*/
    @Bind("sharedMenuController") public native static @Type("UIMenuController *") UIMenuController getSharedMenuController();
    @Bind("setMenuVisible:animated:") public native @Type("void") void setMenuVisible(@Type("BOOL") boolean menuVisible, @Type("BOOL") boolean animated);
    @Bind("setTargetRect:inView:") public native @Type("void") void setTargetRect(@Type("CGRect") CGRect targetRect, @Type("UIView *") UIView targetView);
    @Bind("update") public native @Type("void") void update();
    /*</methods>*/

}
