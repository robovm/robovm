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
public class /*<name>*/ UISimpleTextPrintFormatter /*</name>*/ 
    extends /*<extends>*/ UIPrintFormatter /*</extends>*/ 
    /*<implements>*/ /*</implements>*/ {

    static {
        ObjCRuntime.bind(/*<name>*/ UISimpleTextPrintFormatter /*</name>*/.class);
    }

    /*<constructors>*/
    public UISimpleTextPrintFormatter() {}
    @Bind("initWithText:") public UISimpleTextPrintFormatter(@Type("NSString *") String text) {}
    /*</constructors>*/
    /*<properties>*/
    @Bind("color") public native @Type("UIColor *") UIColor getColor();
    @Bind("setColor:") public native void setColor(@Type("UIColor *") UIColor v);
    @Bind("font") public native @Type("UIFont *") UIFont getFont();
    @Bind("setFont:") public native void setFont(@Type("UIFont *") UIFont v);
    @Bind("lineBreakMode") public native @Type("UILineBreakMode") UILineBreakMode getLineBreakMode();
    @Bind("setLineBreakMode:") public native void setLineBreakMode(@Type("UILineBreakMode") UILineBreakMode v);
    @Bind("text") public native @Type("NSString *") String getText();
    @Bind("setText:") public native void setText(@Type("NSString *") String v);
    @Bind("textAlignment") public native @Type("UITextAlignment") UITextAlignment getTextAlignment();
    @Bind("setTextAlignment:") public native void setTextAlignment(@Type("UITextAlignment") UITextAlignment v);
    /*</properties>*/
    /*<methods>*/
    
    /*</methods>*/

}
