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
public class /*<name>*/ UIDocumentInteractionController /*</name>*/ 
    extends /*<extends>*/ NSObject /*</extends>*/ 
    /*<implements>*/ /*</implements>*/ {

    static {
        ObjCRuntime.bind(/*<name>*/ UIDocumentInteractionController /*</name>*/.class);
    }

    /*<constructors>*/
    public UIDocumentInteractionController() {}
    
    /*</constructors>*/
    /*<properties>*/
    @Bind("URL") public native @Type("NSURL *") NSURL getURL();
    @Bind("setURL:") public native void setURL(@Type("NSURL *") NSURL v);
    @Bind("UTI") public native @Type("NSString *") String getUTI();
    @Bind("setUTI:") public native void setUTI(@Type("NSString *") String v);
    @Bind("annotation") public native @Type("id") NSObject getAnnotation();
    @Bind("setAnnotation:") public native void setAnnotation(@Type("id") NSObject v);
    @Bind("delegate") public native @Type("id<UIDocumentInteractionControllerDelegate>") UIDocumentInteractionControllerDelegate getDelegate();
    @Bind("setDelegate:") public native void setDelegate(@Type("id<UIDocumentInteractionControllerDelegate>") UIDocumentInteractionControllerDelegate v);
    @Bind("gestureRecognizers") public native @Type("NSArray *") NSArray getGestureRecognizers();
    @Bind("icons") public native @Type("NSArray *") NSArray getIcons();
    @Bind("name") public native @Type("NSString *") String getName();
    @Bind("setName:") public native void setName(@Type("NSString *") String v);
    /*</properties>*/
    /*<methods>*/
    @Bind("interactionControllerWithURL:") public native static @Type("UIDocumentInteractionController *") UIDocumentInteractionController fromURL(@Type("NSURL *") NSURL url);
    @Bind("dismissMenuAnimated:") public native @Type("void") void dismissMenu(@Type("BOOL") boolean animated);
    @Bind("dismissPreviewAnimated:") public native @Type("void") void dismissPreview(@Type("BOOL") boolean animated);
    @Bind("presentOpenInMenuFromBarButtonItem:animated:") public native @Type("BOOL") boolean presentOpenInMenu(@Type("UIBarButtonItem *") UIBarButtonItem item, @Type("BOOL") boolean animated);
    @Bind("presentOpenInMenuFromRect:inView:animated:") public native @Type("BOOL") boolean presentOpenInMenu(@Type("CGRect") CGRect rect, @Type("UIView *") UIView view, @Type("BOOL") boolean animated);
    @Bind("presentOptionsMenuFromBarButtonItem:animated:") public native @Type("BOOL") boolean presentOptionsMenu(@Type("UIBarButtonItem *") UIBarButtonItem item, @Type("BOOL") boolean animated);
    @Bind("presentOptionsMenuFromRect:inView:animated:") public native @Type("BOOL") boolean presentOptionsMenu(@Type("CGRect") CGRect rect, @Type("UIView *") UIView view, @Type("BOOL") boolean animated);
    @Bind("presentPreviewAnimated:") public native @Type("BOOL") boolean presentPreview(@Type("BOOL") boolean animated);
    /*</methods>*/

}
