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
public class /*<name>*/ UIPrintFormatter /*</name>*/ 
    extends /*<extends>*/ NSObject /*</extends>*/ 
    /*<implements>*/ /*</implements>*/ {

    static {
        ObjCRuntime.bind(/*<name>*/ UIPrintFormatter /*</name>*/.class);
    }

    /*<constructors>*/
    public UIPrintFormatter() {}
    
    /*</constructors>*/
    /*<properties>*/
    @Bind("contentInsets") public native @Type("UIEdgeInsets") UIEdgeInsets getContentInsets();
    @Bind("setContentInsets:") public native void setContentInsets(@Type("UIEdgeInsets") UIEdgeInsets v);
    @Bind("maximumContentHeight") public native @Type("CGFloat") float getMaximumContentHeight();
    @Bind("setMaximumContentHeight:") public native void setMaximumContentHeight(@Type("CGFloat") float v);
    @Bind("maximumContentWidth") public native @Type("CGFloat") float getMaximumContentWidth();
    @Bind("setMaximumContentWidth:") public native void setMaximumContentWidth(@Type("CGFloat") float v);
    @Bind("pageCount") public native @Type("NSInteger") int getPageCount();
    @Bind("printPageRenderer") public native @Type("UIPrintPageRenderer *") UIPrintPageRenderer getPrintPageRenderer();
    @Bind("startPage") public native @Type("NSInteger") int getStartPage();
    @Bind("setStartPage:") public native void setStartPage(@Type("NSInteger") int v);
    /*</properties>*/
    /*<methods>*/
    @Bind("drawInRect:forPageAtIndex:") public native @Type("void") void draw(@Type("CGRect") CGRect rect, @Type("NSInteger") int pageIndex);
    @Bind("rectForPageAtIndex:") public native @Type("CGRect") CGRect getRectForPage(@Type("NSIndex") int pageIndex);
    @Bind("removeFromPrintPageRenderer") public native @Type("void") void removeFromPrintPageRenderer();
    /*</methods>*/

}
