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
import org.robovm.cocoatouch.coredata.*;
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

/**
 *
 *
 * <div class="javadoc">
 *   @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIPageControl_Class/Reference/Reference.html">UIPageControl Class Reference</a>
 *   @since Available in iOS 2.0 and later.
 * </div>
 */
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
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIPageControl_Class/Reference/Reference.html#//apple_ref/occ/instp/UIPageControl/currentPage">@property(nonatomic) NSInteger currentPage</a>
     * @since Available in iOS 2.0 and later.
     */
    @Bind("currentPage") public native @Type("NSInteger") int getCurrentPage();
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIPageControl_Class/Reference/Reference.html#//apple_ref/occ/instp/UIPageControl/currentPage">@property(nonatomic) NSInteger currentPage</a>
     * @since Available in iOS 2.0 and later.
     */
    @Bind("setCurrentPage:") public native void setCurrentPage(@Type("NSInteger") int v);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIPageControl_Class/Reference/Reference.html#//apple_ref/occ/instp/UIPageControl/currentPageIndicatorTintColor">@property(nonatomic,retain) UIColor *currentPageIndicatorTintColor</a>
     * @since Available in iOS 6.0 and later.
     */
    @Bind("currentPageIndicatorTintColor") public native @Type("UIColor *") UIColor getCurrentPageIndicatorTintColor();
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIPageControl_Class/Reference/Reference.html#//apple_ref/occ/instp/UIPageControl/currentPageIndicatorTintColor">@property(nonatomic,retain) UIColor *currentPageIndicatorTintColor</a>
     * @since Available in iOS 6.0 and later.
     */
    @Bind("setCurrentPageIndicatorTintColor:") public native void setCurrentPageIndicatorTintColor(@Type("UIColor *") UIColor v);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIPageControl_Class/Reference/Reference.html#//apple_ref/occ/instp/UIPageControl/defersCurrentPageDisplay">@property(nonatomic) BOOL defersCurrentPageDisplay</a>
     * @since Available in iOS 2.0 and later.
     */
    @Bind("defersCurrentPageDisplay") public native @Type("BOOL") boolean isDefersCurrentPageDisplay();
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIPageControl_Class/Reference/Reference.html#//apple_ref/occ/instp/UIPageControl/defersCurrentPageDisplay">@property(nonatomic) BOOL defersCurrentPageDisplay</a>
     * @since Available in iOS 2.0 and later.
     */
    @Bind("setDefersCurrentPageDisplay:") public native void setDefersCurrentPageDisplay(@Type("BOOL") boolean v);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIPageControl_Class/Reference/Reference.html#//apple_ref/occ/instp/UIPageControl/hidesForSinglePage">@property(nonatomic) BOOL hidesForSinglePage</a>
     * @since Available in iOS 2.0 and later.
     */
    @Bind("hidesForSinglePage") public native @Type("BOOL") boolean isHidesForSinglePage();
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIPageControl_Class/Reference/Reference.html#//apple_ref/occ/instp/UIPageControl/hidesForSinglePage">@property(nonatomic) BOOL hidesForSinglePage</a>
     * @since Available in iOS 2.0 and later.
     */
    @Bind("setHidesForSinglePage:") public native void setHidesForSinglePage(@Type("BOOL") boolean v);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIPageControl_Class/Reference/Reference.html#//apple_ref/occ/instp/UIPageControl/numberOfPages">@property(nonatomic) NSInteger numberOfPages</a>
     * @since Available in iOS 2.0 and later.
     */
    @Bind("numberOfPages") public native @Type("NSInteger") int getNumberOfPages();
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIPageControl_Class/Reference/Reference.html#//apple_ref/occ/instp/UIPageControl/numberOfPages">@property(nonatomic) NSInteger numberOfPages</a>
     * @since Available in iOS 2.0 and later.
     */
    @Bind("setNumberOfPages:") public native void setNumberOfPages(@Type("NSInteger") int v);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIPageControl_Class/Reference/Reference.html#//apple_ref/occ/instp/UIPageControl/pageIndicatorTintColor">@property(nonatomic,retain) UIColor *pageIndicatorTintColor</a>
     * @since Available in iOS 6.0 and later.
     */
    @Bind("pageIndicatorTintColor") public native @Type("UIColor *") UIColor getPageIndicatorTintColor();
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIPageControl_Class/Reference/Reference.html#//apple_ref/occ/instp/UIPageControl/pageIndicatorTintColor">@property(nonatomic,retain) UIColor *pageIndicatorTintColor</a>
     * @since Available in iOS 6.0 and later.
     */
    @Bind("setPageIndicatorTintColor:") public native void setPageIndicatorTintColor(@Type("UIColor *") UIColor v);
    /*</properties>*/
    /*<methods>*/
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIPageControl_Class/Reference/Reference.html#//apple_ref/occ/instm/UIPageControl/sizeForNumberOfPages:">- (CGSize)sizeForNumberOfPages:(NSInteger)pageCount</a>
     * @since Available in iOS 2.0 and later.
     */
    @Bind("sizeForNumberOfPages:") public native @Type("CGSize") CGSize getSizeForNumberOfPages(@Type("NSInteger") int pageCount);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIPageControl_Class/Reference/Reference.html#//apple_ref/occ/instm/UIPageControl/updateCurrentPageDisplay">- (void)updateCurrentPageDisplay</a>
     * @since Available in iOS 2.0 and later.
     */
    @Bind("updateCurrentPageDisplay") public native @Type("void") void updateCurrentPageDisplay();
    /*</methods>*/

}
