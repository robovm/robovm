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
public class /*<name>*/ UIActionSheet /*</name>*/ 
    extends /*<extends>*/ UIView /*</extends>*/ 
    /*<implements>*/ /*</implements>*/ {

    static {
        ObjCRuntime.bind(/*<name>*/ UIActionSheet /*</name>*/.class);
    }

    /*<constructors>*/
    public UIActionSheet() {}
    
    /*</constructors>*/
    /*<properties>*/
    @Bind("actionSheetStyle") public native @Type("UIActionSheetStyle") UIActionSheetStyle getActionSheetStyle();
    @Bind("setActionSheetStyle:") public native void setActionSheetStyle(@Type("UIActionSheetStyle") UIActionSheetStyle v);
    @Bind("cancelButtonIndex") public native @Type("NSInteger") int getCancelButtonIndex();
    @Bind("setCancelButtonIndex:") public native void setCancelButtonIndex(@Type("NSInteger") int v);
    @Bind("delegate") public native @Type("id<UIActionSheetDelegate>") UIActionSheetDelegate getDelegate();
    @Bind("setDelegate:") public native void setDelegate(@Type("id<UIActionSheetDelegate>") UIActionSheetDelegate v);
    @Bind("destructiveButtonIndex") public native @Type("NSInteger") int getDestructiveButtonIndex();
    @Bind("setDestructiveButtonIndex:") public native void setDestructiveButtonIndex(@Type("NSInteger") int v);
    @Bind("firstOtherButtonIndex") public native @Type("NSInteger") int getFirstOtherButtonIndex();
    @Bind("numberOfButtons") public native @Type("NSInteger") int getNumberOfButtons();
    @Bind("title") public native @Type("NSString *") String getTitle();
    @Bind("setTitle:") public native void setTitle(@Type("NSString *") String v);
    @Bind("isVisible") public native @Type("BOOL") boolean isVisible();
    /*</properties>*/
    /*<methods>*/
    @Bind("addButtonWithTitle:") public native @Type("NSInteger") int addButton(@Type("NSString *") String title);
    @Bind("dismissWithClickedButtonIndex:animated:") public native @Type("void") void dismiss(@Type("NSInteger") int buttonIndex, @Type("BOOL") boolean animated);
    @Bind("buttonTitleAtIndex:") public native @Type("NSString *") String getButtonTitle(@Type("NSInteger") int buttonIndex);
    @Bind("showFromBarButtonItem:animated:") public native @Type("void") void showFrom(@Type("UIBarButtonItem *") UIBarButtonItem item, @Type("BOOL") boolean animated);
    @Bind("showFromToolbar:") public native @Type("void") void showFrom(@Type("UIToolbar *") UIToolbar view);
    @Bind("showFromTabBar:") public native @Type("void") void showFrom(@Type("UITabBar *") UITabBar view);
    @Bind("showFromRect:inView:animated:") public native @Type("void") void showFrom(@Type("CGRect") CGRect rect, @Type("UIView *") UIView view, @Type("BOOL") boolean animated);
    @Bind("showInView:") public native @Type("void") void showInView(@Type("UIView *") UIView view);
    /*</methods>*/

}
