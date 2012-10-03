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
public class /*<name>*/ UIPrintInteractionController /*</name>*/ 
    extends /*<extends>*/ NSObject /*</extends>*/ 
    /*<implements>*/ /*</implements>*/ {

    static {
        ObjCRuntime.bind(/*<name>*/ UIPrintInteractionController /*</name>*/.class);
    }

    /*<constructors>*/
    public UIPrintInteractionController() {}
    
    /*</constructors>*/
    /*<properties>*/
    @Bind("delegate") public native @Type("id<UIPrintInteractionControllerDelegate>") UIPrintInteractionControllerDelegate getDelegate();
    @Bind("setDelegate:") public native void setDelegate(@Type("id<UIPrintInteractionControllerDelegate>") UIPrintInteractionControllerDelegate v);
    @Bind("printFormatter") public native @Type("UIPrintFormatter *") UIPrintFormatter getPrintFormatter();
    @Bind("setPrintFormatter:") public native void setPrintFormatter(@Type("UIPrintFormatter *") UIPrintFormatter v);
    @Bind("printInfo") public native @Type("UIPrintInfo *") UIPrintInfo getPrintInfo();
    @Bind("setPrintInfo:") public native void setPrintInfo(@Type("UIPrintInfo *") UIPrintInfo v);
    @Bind("printPageRenderer") public native @Type("UIPrintPageRenderer") UIPrintPageRenderer getPrintPageRenderer();
    @Bind("setPrintPageRenderer:") public native void setPrintPageRenderer(@Type("UIPrintPageRenderer") UIPrintPageRenderer v);
    @Bind("printPaper") public native @Type("UIPrintPaper *") UIPrintPaper getPrintPaper();
    @Bind("printingItem") public native @Type("id") NSObject getPrintingItem();
    @Bind("setPrintingItem:") public native void setPrintingItem(@Type("id") NSObject v);
    @Bind("printingItems") public native @Type("NSArray *") NSArray getPrintingItems();
    @Bind("setPrintingItems:") public native void setPrintingItems(@Type("NSArray *") NSArray v);
    @Bind("showsPageRange") public native @Type("BOOL") boolean isShowsPageRange();
    @Bind("setShowsPageRange:") public native void setShowsPageRange(@Type("BOOL") boolean v);
    /*</properties>*/
    /*<methods>*/
    @Bind("canPrintData:") public native static @Type("BOOL") boolean canPrint(@Type("NSData *") NSData data);
    @Bind("canPrintURL:") public native static @Type("BOOL") boolean canPrint(@Type("NSURL *") NSURL url);
    @Bind("printableUTIs") public native static @Type("NSSet *") NSSet getPrintableUTIs();
    @Bind("sharedPrintController") public native static @Type("UIPrintInteractionController *") UIPrintInteractionController getSharedPrintController();
    @Bind("isPrintingAvailable") public native static @Type("BOOL") boolean isPrintingAvailable();
    @Bind("dismissAnimated:") public native @Type("void") void dismiss(@Type("BOOL") boolean animated);
    @Bind("presentAnimated:completionHandler:") public native @Type("void") void present(@Type("BOOL") boolean animated, @Type("UIPrintInteractionCompletionHandler") UIPrintInteractionCompletionHandler completion);
    @Bind("presentFromBarButtonItem:animated:completionHandler:") public native @Type("void") void presentFromBarButtonItem(@Type("UIBarButtonItem *") UIBarButtonItem item, @Type("BOOL") boolean animated, @Type("UIPrintInteractionCompletionHandler") UIPrintInteractionCompletionHandler completion);
    @Bind("presentFromRect:inView:animated:completionHandler:") public native @Type("void") void presentFromRectInView(@Type("CGRect") CGRect rect, @Type("UIView *") UIView view, @Type("BOOL") boolean animated, @Type("UIPrintInteractionCompletionHandler") UIPrintInteractionCompletionHandler completion);
    /*</methods>*/

}
