/*
 * Copyright (C) 2012 Trillian AB
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
import org.robovm.objc.annotation.*;
import org.robovm.objc.block.*;
import org.robovm.rt.bro.*;
import org.robovm.rt.bro.annotation.*;
import org.robovm.rt.bro.ptr.*;
/*</imports>*/

/**
 *
 *
 * <div class="javadoc">
 *   @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIPageControl_Class/Reference/Reference.html">UIPageControl Class Reference</a>
 *   @since Available in iOS 2.0 and later.
 * </div>
 */
/*<library>*/@Library("UIKit")/*</library>*/
@NativeClass public class /*<name>*/ UIPageControl /*</name>*/ 
    extends /*<extends>*/ UIControl /*</extends>*/ 
    /*<implements>*/ /*</implements>*/ {

    static {
        ObjCRuntime.bind(/*<name>*/ UIPageControl /*</name>*/.class);
    }

    private static final ObjCClass objCClass = ObjCClass.getByType(/*<name>*/ UIPageControl /*</name>*/.class);

    public UIPageControl(CGRect aRect) {
        super(aRect);
    }
    /*<constructors>*/
    protected UIPageControl(SkipInit skipInit) { super(skipInit); }
    public UIPageControl() {}
    
    /*</constructors>*/
    /*<properties>*/
    
    private static final Selector currentPage = Selector.register("currentPage");
    @Bridge private native static int objc_getCurrentPage(UIPageControl __self__, Selector __cmd__);
    @Bridge private native static int objc_getCurrentPageSuper(ObjCSuper __super__, Selector __cmd__);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIPageControl_Class/Reference/Reference.html#//apple_ref/occ/instp/UIPageControl/currentPage">@property(nonatomic) NSInteger currentPage</a>
     * @since Available in iOS 2.0 and later.
     */
    public int getCurrentPage() {
        if (customClass) { return objc_getCurrentPageSuper(getSuper(), currentPage); } else { return objc_getCurrentPage(this, currentPage); }
    }
    
    private static final Selector setCurrentPage$ = Selector.register("setCurrentPage:");
    @Bridge private native static void objc_setCurrentPage(UIPageControl __self__, Selector __cmd__, int currentPage);
    @Bridge private native static void objc_setCurrentPageSuper(ObjCSuper __super__, Selector __cmd__, int currentPage);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIPageControl_Class/Reference/Reference.html#//apple_ref/occ/instp/UIPageControl/currentPage">@property(nonatomic) NSInteger currentPage</a>
     * @since Available in iOS 2.0 and later.
     */
    public void setCurrentPage(int currentPage) {
        if (customClass) { objc_setCurrentPageSuper(getSuper(), setCurrentPage$, currentPage); } else { objc_setCurrentPage(this, setCurrentPage$, currentPage); }
    }
    
    private static final Selector currentPageIndicatorTintColor = Selector.register("currentPageIndicatorTintColor");
    @Bridge private native static UIColor objc_getCurrentPageIndicatorTintColor(UIPageControl __self__, Selector __cmd__);
    @Bridge private native static UIColor objc_getCurrentPageIndicatorTintColorSuper(ObjCSuper __super__, Selector __cmd__);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIPageControl_Class/Reference/Reference.html#//apple_ref/occ/instp/UIPageControl/currentPageIndicatorTintColor">@property(nonatomic,retain) UIColor *currentPageIndicatorTintColor</a>
     * @since Available in iOS 6.0 and later.
     */
    public UIColor getCurrentPageIndicatorTintColor() {
        if (customClass) { return objc_getCurrentPageIndicatorTintColorSuper(getSuper(), currentPageIndicatorTintColor); } else { return objc_getCurrentPageIndicatorTintColor(this, currentPageIndicatorTintColor); }
    }
    
    private static final Selector setCurrentPageIndicatorTintColor$ = Selector.register("setCurrentPageIndicatorTintColor:");
    @Bridge private native static void objc_setCurrentPageIndicatorTintColor(UIPageControl __self__, Selector __cmd__, UIColor currentPageIndicatorTintColor);
    @Bridge private native static void objc_setCurrentPageIndicatorTintColorSuper(ObjCSuper __super__, Selector __cmd__, UIColor currentPageIndicatorTintColor);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIPageControl_Class/Reference/Reference.html#//apple_ref/occ/instp/UIPageControl/currentPageIndicatorTintColor">@property(nonatomic,retain) UIColor *currentPageIndicatorTintColor</a>
     * @since Available in iOS 6.0 and later.
     */
    public void setCurrentPageIndicatorTintColor(UIColor currentPageIndicatorTintColor) {
        if (customClass) { objc_setCurrentPageIndicatorTintColorSuper(getSuper(), setCurrentPageIndicatorTintColor$, currentPageIndicatorTintColor); } else { objc_setCurrentPageIndicatorTintColor(this, setCurrentPageIndicatorTintColor$, currentPageIndicatorTintColor); }
    }
    
    private static final Selector defersCurrentPageDisplay = Selector.register("defersCurrentPageDisplay");
    @Bridge private native static boolean objc_isDefersCurrentPageDisplay(UIPageControl __self__, Selector __cmd__);
    @Bridge private native static boolean objc_isDefersCurrentPageDisplaySuper(ObjCSuper __super__, Selector __cmd__);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIPageControl_Class/Reference/Reference.html#//apple_ref/occ/instp/UIPageControl/defersCurrentPageDisplay">@property(nonatomic) BOOL defersCurrentPageDisplay</a>
     * @since Available in iOS 2.0 and later.
     */
    public boolean isDefersCurrentPageDisplay() {
        if (customClass) { return objc_isDefersCurrentPageDisplaySuper(getSuper(), defersCurrentPageDisplay); } else { return objc_isDefersCurrentPageDisplay(this, defersCurrentPageDisplay); }
    }
    
    private static final Selector setDefersCurrentPageDisplay$ = Selector.register("setDefersCurrentPageDisplay:");
    @Bridge private native static void objc_setDefersCurrentPageDisplay(UIPageControl __self__, Selector __cmd__, boolean defersCurrentPageDisplay);
    @Bridge private native static void objc_setDefersCurrentPageDisplaySuper(ObjCSuper __super__, Selector __cmd__, boolean defersCurrentPageDisplay);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIPageControl_Class/Reference/Reference.html#//apple_ref/occ/instp/UIPageControl/defersCurrentPageDisplay">@property(nonatomic) BOOL defersCurrentPageDisplay</a>
     * @since Available in iOS 2.0 and later.
     */
    public void setDefersCurrentPageDisplay(boolean defersCurrentPageDisplay) {
        if (customClass) { objc_setDefersCurrentPageDisplaySuper(getSuper(), setDefersCurrentPageDisplay$, defersCurrentPageDisplay); } else { objc_setDefersCurrentPageDisplay(this, setDefersCurrentPageDisplay$, defersCurrentPageDisplay); }
    }
    
    private static final Selector hidesForSinglePage = Selector.register("hidesForSinglePage");
    @Bridge private native static boolean objc_isHidesForSinglePage(UIPageControl __self__, Selector __cmd__);
    @Bridge private native static boolean objc_isHidesForSinglePageSuper(ObjCSuper __super__, Selector __cmd__);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIPageControl_Class/Reference/Reference.html#//apple_ref/occ/instp/UIPageControl/hidesForSinglePage">@property(nonatomic) BOOL hidesForSinglePage</a>
     * @since Available in iOS 2.0 and later.
     */
    public boolean isHidesForSinglePage() {
        if (customClass) { return objc_isHidesForSinglePageSuper(getSuper(), hidesForSinglePage); } else { return objc_isHidesForSinglePage(this, hidesForSinglePage); }
    }
    
    private static final Selector setHidesForSinglePage$ = Selector.register("setHidesForSinglePage:");
    @Bridge private native static void objc_setHidesForSinglePage(UIPageControl __self__, Selector __cmd__, boolean hidesForSinglePage);
    @Bridge private native static void objc_setHidesForSinglePageSuper(ObjCSuper __super__, Selector __cmd__, boolean hidesForSinglePage);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIPageControl_Class/Reference/Reference.html#//apple_ref/occ/instp/UIPageControl/hidesForSinglePage">@property(nonatomic) BOOL hidesForSinglePage</a>
     * @since Available in iOS 2.0 and later.
     */
    public void setHidesForSinglePage(boolean hidesForSinglePage) {
        if (customClass) { objc_setHidesForSinglePageSuper(getSuper(), setHidesForSinglePage$, hidesForSinglePage); } else { objc_setHidesForSinglePage(this, setHidesForSinglePage$, hidesForSinglePage); }
    }
    
    private static final Selector numberOfPages = Selector.register("numberOfPages");
    @Bridge private native static int objc_getNumberOfPages(UIPageControl __self__, Selector __cmd__);
    @Bridge private native static int objc_getNumberOfPagesSuper(ObjCSuper __super__, Selector __cmd__);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIPageControl_Class/Reference/Reference.html#//apple_ref/occ/instp/UIPageControl/numberOfPages">@property(nonatomic) NSInteger numberOfPages</a>
     * @since Available in iOS 2.0 and later.
     */
    public int getNumberOfPages() {
        if (customClass) { return objc_getNumberOfPagesSuper(getSuper(), numberOfPages); } else { return objc_getNumberOfPages(this, numberOfPages); }
    }
    
    private static final Selector setNumberOfPages$ = Selector.register("setNumberOfPages:");
    @Bridge private native static void objc_setNumberOfPages(UIPageControl __self__, Selector __cmd__, int numberOfPages);
    @Bridge private native static void objc_setNumberOfPagesSuper(ObjCSuper __super__, Selector __cmd__, int numberOfPages);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIPageControl_Class/Reference/Reference.html#//apple_ref/occ/instp/UIPageControl/numberOfPages">@property(nonatomic) NSInteger numberOfPages</a>
     * @since Available in iOS 2.0 and later.
     */
    public void setNumberOfPages(int numberOfPages) {
        if (customClass) { objc_setNumberOfPagesSuper(getSuper(), setNumberOfPages$, numberOfPages); } else { objc_setNumberOfPages(this, setNumberOfPages$, numberOfPages); }
    }
    
    private static final Selector pageIndicatorTintColor = Selector.register("pageIndicatorTintColor");
    @Bridge private native static UIColor objc_getPageIndicatorTintColor(UIPageControl __self__, Selector __cmd__);
    @Bridge private native static UIColor objc_getPageIndicatorTintColorSuper(ObjCSuper __super__, Selector __cmd__);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIPageControl_Class/Reference/Reference.html#//apple_ref/occ/instp/UIPageControl/pageIndicatorTintColor">@property(nonatomic,retain) UIColor *pageIndicatorTintColor</a>
     * @since Available in iOS 6.0 and later.
     */
    public UIColor getPageIndicatorTintColor() {
        if (customClass) { return objc_getPageIndicatorTintColorSuper(getSuper(), pageIndicatorTintColor); } else { return objc_getPageIndicatorTintColor(this, pageIndicatorTintColor); }
    }
    
    private static final Selector setPageIndicatorTintColor$ = Selector.register("setPageIndicatorTintColor:");
    @Bridge private native static void objc_setPageIndicatorTintColor(UIPageControl __self__, Selector __cmd__, UIColor pageIndicatorTintColor);
    @Bridge private native static void objc_setPageIndicatorTintColorSuper(ObjCSuper __super__, Selector __cmd__, UIColor pageIndicatorTintColor);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIPageControl_Class/Reference/Reference.html#//apple_ref/occ/instp/UIPageControl/pageIndicatorTintColor">@property(nonatomic,retain) UIColor *pageIndicatorTintColor</a>
     * @since Available in iOS 6.0 and later.
     */
    public void setPageIndicatorTintColor(UIColor pageIndicatorTintColor) {
        if (customClass) { objc_setPageIndicatorTintColorSuper(getSuper(), setPageIndicatorTintColor$, pageIndicatorTintColor); } else { objc_setPageIndicatorTintColor(this, setPageIndicatorTintColor$, pageIndicatorTintColor); }
    }
    /*</properties>*/
    /*<methods>*/
    
    private static final Selector sizeForNumberOfPages$ = Selector.register("sizeForNumberOfPages:");
    @Bridge private native static @ByVal CGSize objc_getSizeForNumberOfPages(UIPageControl __self__, Selector __cmd__, int pageCount);
    @Bridge private native static @ByVal CGSize objc_getSizeForNumberOfPagesSuper(ObjCSuper __super__, Selector __cmd__, int pageCount);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIPageControl_Class/Reference/Reference.html#//apple_ref/occ/instm/UIPageControl/sizeForNumberOfPages:">- (CGSize)sizeForNumberOfPages:(NSInteger)pageCount</a>
     * @since Available in iOS 2.0 and later.
     */
    public CGSize getSizeForNumberOfPages(int pageCount) {
        if (customClass) { return objc_getSizeForNumberOfPagesSuper(getSuper(), sizeForNumberOfPages$, pageCount); } else { return objc_getSizeForNumberOfPages(this, sizeForNumberOfPages$, pageCount); }
    }
    
    private static final Selector updateCurrentPageDisplay = Selector.register("updateCurrentPageDisplay");
    @Bridge private native static void objc_updateCurrentPageDisplay(UIPageControl __self__, Selector __cmd__);
    @Bridge private native static void objc_updateCurrentPageDisplaySuper(ObjCSuper __super__, Selector __cmd__);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIPageControl_Class/Reference/Reference.html#//apple_ref/occ/instm/UIPageControl/updateCurrentPageDisplay">- (void)updateCurrentPageDisplay</a>
     * @since Available in iOS 2.0 and later.
     */
    public void updateCurrentPageDisplay() {
        if (customClass) { objc_updateCurrentPageDisplaySuper(getSuper(), updateCurrentPageDisplay); } else { objc_updateCurrentPageDisplay(this, updateCurrentPageDisplay); }
    }
    /*</methods>*/
    /*<callbacks>*/
    static class Callbacks {
        @Callback @BindSelector("currentPage") public static int getCurrentPage(UIPageControl __self__, Selector __cmd__) { return __self__.getCurrentPage(); }
        @Callback @BindSelector("setCurrentPage:") public static void setCurrentPage(UIPageControl __self__, Selector __cmd__, int currentPage) { __self__.setCurrentPage(currentPage); }
        @Callback @BindSelector("currentPageIndicatorTintColor") public static UIColor getCurrentPageIndicatorTintColor(UIPageControl __self__, Selector __cmd__) { return __self__.getCurrentPageIndicatorTintColor(); }
        @Callback @BindSelector("setCurrentPageIndicatorTintColor:") public static void setCurrentPageIndicatorTintColor(UIPageControl __self__, Selector __cmd__, UIColor currentPageIndicatorTintColor) { __self__.setCurrentPageIndicatorTintColor(currentPageIndicatorTintColor); }
        @Callback @BindSelector("defersCurrentPageDisplay") public static boolean isDefersCurrentPageDisplay(UIPageControl __self__, Selector __cmd__) { return __self__.isDefersCurrentPageDisplay(); }
        @Callback @BindSelector("setDefersCurrentPageDisplay:") public static void setDefersCurrentPageDisplay(UIPageControl __self__, Selector __cmd__, boolean defersCurrentPageDisplay) { __self__.setDefersCurrentPageDisplay(defersCurrentPageDisplay); }
        @Callback @BindSelector("hidesForSinglePage") public static boolean isHidesForSinglePage(UIPageControl __self__, Selector __cmd__) { return __self__.isHidesForSinglePage(); }
        @Callback @BindSelector("setHidesForSinglePage:") public static void setHidesForSinglePage(UIPageControl __self__, Selector __cmd__, boolean hidesForSinglePage) { __self__.setHidesForSinglePage(hidesForSinglePage); }
        @Callback @BindSelector("numberOfPages") public static int getNumberOfPages(UIPageControl __self__, Selector __cmd__) { return __self__.getNumberOfPages(); }
        @Callback @BindSelector("setNumberOfPages:") public static void setNumberOfPages(UIPageControl __self__, Selector __cmd__, int numberOfPages) { __self__.setNumberOfPages(numberOfPages); }
        @Callback @BindSelector("pageIndicatorTintColor") public static UIColor getPageIndicatorTintColor(UIPageControl __self__, Selector __cmd__) { return __self__.getPageIndicatorTintColor(); }
        @Callback @BindSelector("setPageIndicatorTintColor:") public static void setPageIndicatorTintColor(UIPageControl __self__, Selector __cmd__, UIColor pageIndicatorTintColor) { __self__.setPageIndicatorTintColor(pageIndicatorTintColor); }
        @Callback @BindSelector("sizeForNumberOfPages:") public static @ByVal CGSize getSizeForNumberOfPages(UIPageControl __self__, Selector __cmd__, int pageCount) { return __self__.getSizeForNumberOfPages(pageCount); }
        @Callback @BindSelector("updateCurrentPageDisplay") public static void updateCurrentPageDisplay(UIPageControl __self__, Selector __cmd__) { __self__.updateCurrentPageDisplay(); }
    }
    /*</callbacks>*/

}
