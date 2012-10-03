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
public class /*<name>*/ UINavigationItem /*</name>*/ 
    extends /*<extends>*/ NSObject /*</extends>*/ 
    /*<implements>*/ /*</implements>*/ {

    static {
        ObjCRuntime.bind(/*<name>*/ UINavigationItem /*</name>*/.class);
    }

    /*<constructors>*/
    public UINavigationItem() {}
    @Bind("initWithTitle:") public UINavigationItem(@Type("NSString *") String title) {}
    /*</constructors>*/
    /*<properties>*/
    @Bind("backBarButtonItem") public native @Type("UIBarButtonItem *") UIBarButtonItem getBackBarButtonItem();
    @Bind("setBackBarButtonItem:") public native void setBackBarButtonItem(@Type("UIBarButtonItem *") UIBarButtonItem v);
    @Bind("hidesBackButton") public native @Type("BOOL") boolean isHidesBackButton();
    @Bind("setHidesBackButton:") public native void setHidesBackButton(@Type("BOOL") boolean v);
    @Bind("leftBarButtonItem") public native @Type("UIBarButtonItem *") UIBarButtonItem getLeftBarButtonItem();
    @Bind("setLeftBarButtonItem:") public native void setLeftBarButtonItem(@Type("UIBarButtonItem *") UIBarButtonItem v);
    @Bind("leftBarButtonItems") public native @Type("NSArray *") NSArray getLeftBarButtonItems();
    @Bind("setLeftBarButtonItems:") public native void setLeftBarButtonItems(@Type("NSArray *") NSArray v);
    @Bind("leftItemsSupplementBackButton") public native @Type("BOOL") boolean isLeftItemsSupplementBackButton();
    @Bind("setLeftItemsSupplementBackButton:") public native void setLeftItemsSupplementBackButton(@Type("BOOL") boolean v);
    @Bind("prompt") public native @Type("NSString *") String getPrompt();
    @Bind("setPrompt:") public native void setPrompt(@Type("NSString *") String v);
    @Bind("rightBarButtonItem") public native @Type("UIBarButtonItem *") UIBarButtonItem getRightBarButtonItem();
    @Bind("setRightBarButtonItem:") public native void setRightBarButtonItem(@Type("UIBarButtonItem *") UIBarButtonItem v);
    @Bind("rightBarButtonItems") public native @Type("NSArray *") NSArray getRightBarButtonItems();
    @Bind("setRightBarButtonItems:") public native void setRightBarButtonItems(@Type("NSArray *") NSArray v);
    @Bind("title") public native @Type("NSString *") String getTitle();
    @Bind("setTitle:") public native void setTitle(@Type("NSString *") String v);
    @Bind("titleView") public native @Type("UIView *") UIView getTitleView();
    @Bind("setTitleView:") public native void setTitleView(@Type("UIView *") UIView v);
    /*</properties>*/
    /*<methods>*/
    @Bind("setHidesBackButton:animated:") public native @Type("void") void setHidesBackButton(@Type("BOOL") boolean hidesBackButton, @Type("BOOL") boolean animated);
    @Bind("setLeftBarButtonItem:animated:") public native @Type("void") void setLeftBarButtonItem(@Type("UIBarButtonItem *") UIBarButtonItem item, @Type("BOOL") boolean animated);
    @Bind("setLeftBarButtonItems:animated:") public native @Type("void") void setLeftBarButtonItems(@Type("NSArray *") NSArray items, @Type("BOOL") boolean animated);
    @Bind("setRightBarButtonItem:animated:") public native @Type("void") void setRightBarButtonItem(@Type("UIBarButtonItem *") UIBarButtonItem item, @Type("BOOL") boolean animated);
    @Bind("setRightBarButtonItems:animated:") public native @Type("void") void setRightBarButtonItems(@Type("NSArray *") NSArray items, @Type("BOOL") boolean animated);
    /*</methods>*/

}
