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
public class /*<name>*/ UIPageControl /*</name>*/ 
    extends /*<extends>*/ UIControl /*</extends>*/ 
    /*<implements>*/ /*</implements>*/ {

    static {
        ObjCRuntime.bind(/*<name>*/ UIPageControl /*</name>*/.class);
    }

    /*<constructors>*/
    public UIPageControl() {}
    
    /*</constructors>*/
    /*<properties>*/
    @Bind("currentPage") public native @Type("NSInteger") int getCurrentPage();
    @Bind("setCurrentPage:") public native void setCurrentPage(@Type("NSInteger") int v);
    @Bind("currentPageIndicatorTintColor") public native @Type("UIColor *") UIColor getCurrentPageIndicatorTintColor();
    @Bind("setCurrentPageIndicatorTintColor:") public native void setCurrentPageIndicatorTintColor(@Type("UIColor *") UIColor v);
    @Bind("defersCurrentPageDisplay") public native @Type("BOOL") boolean isDefersCurrentPageDisplay();
    @Bind("setDefersCurrentPageDisplay:") public native void setDefersCurrentPageDisplay(@Type("BOOL") boolean v);
    @Bind("hidesForSinglePage") public native @Type("BOOL") boolean isHidesForSinglePage();
    @Bind("setHidesForSinglePage:") public native void setHidesForSinglePage(@Type("BOOL") boolean v);
    @Bind("numberOfPages") public native @Type("NSInteger") int getNumberOfPages();
    @Bind("setNumberOfPages:") public native void setNumberOfPages(@Type("NSInteger") int v);
    @Bind("pageIndicatorTintColor") public native @Type("UIColor *") UIColor getPageIndicatorTintColor();
    @Bind("setPageIndicatorTintColor:") public native void setPageIndicatorTintColor(@Type("UIColor *") UIColor v);
    /*</properties>*/
    /*<methods>*/
    @Bind("sizeForNumberOfPages:") public native @Type("CGSize") CGSize getSizeForNumberOfPages(@Type("NSInteger") int pageCount);
    @Bind("updateCurrentPageDisplay") public native @Type("void") void updateCurrentPageDisplay();
    /*</methods>*/

}
