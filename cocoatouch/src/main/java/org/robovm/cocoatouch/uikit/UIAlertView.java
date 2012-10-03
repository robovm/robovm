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
public class /*<name>*/ UIAlertView /*</name>*/ 
    extends /*<extends>*/ UIView /*</extends>*/ 
    /*<implements>*/ /*</implements>*/ {

    static {
        ObjCRuntime.bind(/*<name>*/ UIAlertView /*</name>*/.class);
    }

    /*<constructors>*/
    public UIAlertView() {}
    
    /*</constructors>*/
    /*<properties>*/
    @Bind("alertViewStyle") public native @Type("UIAlertViewStyle") UIAlertViewStyle getAlertViewStyle();
    @Bind("setAlertViewStyle:") public native void setAlertViewStyle(@Type("UIAlertViewStyle") UIAlertViewStyle v);
    @Bind("cancelButtonIndex") public native @Type("NSInteger") int getCancelButtonIndex();
    @Bind("setCancelButtonIndex:") public native void setCancelButtonIndex(@Type("NSInteger") int v);
    @Bind("delegate") public native @Type("id") UIAlertViewDelegate getDelegate();
    @Bind("setDelegate:") public native void setDelegate(@Type("id") UIAlertViewDelegate v);
    @Bind("firstOtherButtonIndex") public native @Type("NSInteger") int getFirstOtherButtonIndex();
    @Bind("message") public native @Type("NSString *") String getMessage();
    @Bind("setMessage:") public native void setMessage(@Type("NSString *") String v);
    @Bind("numberOfButtons") public native @Type("NSInteger") int getNumberOfButtons();
    @Bind("title") public native @Type("NSString *") String getTitle();
    @Bind("setTitle:") public native void setTitle(@Type("NSString *") String v);
    @Bind("isVisible") public native @Type("BOOL") boolean isVisible();
    /*</properties>*/
    /*<methods>*/
    @Bind("addButtonWithTitle:") public native @Type("NSInteger") int addButton(@Type("NSString *") String title);
    @Bind("dismissWithClickedButtonIndex:animated:") public native @Type("void") void dismiss(@Type("NSInteger") int buttonIndex, @Type("BOOL") boolean animated);
    @Bind("buttonTitleAtIndex:") public native @Type("NSString *") String getButtonTitle(@Type("NSInteger") int buttonIndex);
    @Bind("textFieldAtIndex:") public native @Type("UITextField *") UITextField getTextField(@Type("NSInteger") int textFieldIndex);
    @Bind("show") public native @Type("void") void show();
    /*</methods>*/

}
