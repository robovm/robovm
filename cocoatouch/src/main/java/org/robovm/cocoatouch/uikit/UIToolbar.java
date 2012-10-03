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
public class /*<name>*/ UIToolbar /*</name>*/ 
    extends /*<extends>*/ UIView /*</extends>*/ 
    /*<implements>*/ /*</implements>*/ {

    static {
        ObjCRuntime.bind(/*<name>*/ UIToolbar /*</name>*/.class);
    }

    /*<constructors>*/
    public UIToolbar() {}
    
    /*</constructors>*/
    /*<properties>*/
    @Bind("barStyle") public native @Type("UIBarStyle") UIBarStyle getBarStyle();
    @Bind("setBarStyle:") public native void setBarStyle(@Type("UIBarStyle") UIBarStyle v);
    @Bind("items") public native @Type("NSArray *") NSArray getItems();
    @Bind("setItems:") public native void setItems(@Type("NSArray *") NSArray v);
    @Bind("tintColor") public native @Type("UIColor *") UIColor getTintColor();
    @Bind("setTintColor:") public native void setTintColor(@Type("UIColor *") UIColor v);
    @Bind("isTranslucent") public native @Type("BOOL") boolean isTranslucent();
    @Bind("setTranslucent:") public native void setTranslucent(@Type("BOOL") boolean v);
    /*</properties>*/
    /*<methods>*/
    @Bind("backgroundImageForToolbarPosition:barMetrics:") public native @Type("UIImage *") UIImage getBackgroundImage(@Type("UIToolbarPosition") UIToolbarPosition topOrBottom, @Type("UIBarMetrics") UIBarMetrics barMetrics);
    @Bind("setBackgroundImage:forToolbarPosition:barMetrics:") public native @Type("void") void setBackgroundImage(@Type("UIImage *") UIImage backgroundImage, @Type("UIToolbarPosition") UIToolbarPosition topOrBottom, @Type("UIBarMetrics") UIBarMetrics barMetrics);
    @Bind("setItems:animated:") public native @Type("void") void setItems(@Type("NSArray *") NSArray items, @Type("BOOL") boolean animated);
    @Bind("setShadowImage:forToolbarPosition:") public native @Type("void") void setShadowImage(@Type("UIImage *") UIImage shadowImage, @Type("UIToolbarPosition") UIToolbarPosition topOrBottom);
    @Bind("shadowImageForToolbarPosition:") public native @Type("UIImage *") UIImage shadowImageForToolbarPosition(@Type("UIToolbarPosition") UIToolbarPosition topOrBottom);
    /*</methods>*/

}
