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
 *   @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../../../iPhone/Reference/UIPrintPageRenderer_Class/Reference/Reference.html">UIPrintPageRenderer Class Reference</a>
 *   @since Available in iOS 4.2 and later.
 * </div>
 */
/*<library>*/@Library("UIKit")/*</library>*/
@NativeClass public class /*<name>*/ UIPrintPageRenderer /*</name>*/ 
    extends /*<extends>*/ NSObject /*</extends>*/ 
    /*<implements>*/ /*</implements>*/ {

    static {
        ObjCRuntime.bind(/*<name>*/ UIPrintPageRenderer /*</name>*/.class);
    }

    private static final ObjCClass objCClass = ObjCClass.getByType(/*<name>*/ UIPrintPageRenderer /*</name>*/.class);

    /*<constructors>*/
    protected UIPrintPageRenderer(SkipInit skipInit) { super(skipInit); }
    public UIPrintPageRenderer() {}
    
    /*</constructors>*/
    /*<properties>*/
    
    private static final Selector footerHeight = Selector.register("footerHeight");
    @Bridge private native static float objc_getFooterHeight(UIPrintPageRenderer __self__, Selector __cmd__);
    @Bridge private native static float objc_getFooterHeightSuper(ObjCSuper __super__, Selector __cmd__);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../../../iPhone/Reference/UIPrintPageRenderer_Class/Reference/Reference.html#//apple_ref/occ/instp/UIPrintPageRenderer/footerHeight">@property CGFloat footerHeight</a>
     * @since Available in iOS 4.2 and later.
     */
    public float getFooterHeight() {
        if (customClass) { return objc_getFooterHeightSuper(getSuper(), footerHeight); } else { return objc_getFooterHeight(this, footerHeight); }
    }
    
    private static final Selector setFooterHeight$ = Selector.register("setFooterHeight:");
    @Bridge private native static void objc_setFooterHeight(UIPrintPageRenderer __self__, Selector __cmd__, float footerHeight);
    @Bridge private native static void objc_setFooterHeightSuper(ObjCSuper __super__, Selector __cmd__, float footerHeight);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../../../iPhone/Reference/UIPrintPageRenderer_Class/Reference/Reference.html#//apple_ref/occ/instp/UIPrintPageRenderer/footerHeight">@property CGFloat footerHeight</a>
     * @since Available in iOS 4.2 and later.
     */
    public void setFooterHeight(float footerHeight) {
        if (customClass) { objc_setFooterHeightSuper(getSuper(), setFooterHeight$, footerHeight); } else { objc_setFooterHeight(this, setFooterHeight$, footerHeight); }
    }
    
    private static final Selector headerHeight = Selector.register("headerHeight");
    @Bridge private native static float objc_getHeaderHeight(UIPrintPageRenderer __self__, Selector __cmd__);
    @Bridge private native static float objc_getHeaderHeightSuper(ObjCSuper __super__, Selector __cmd__);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../../../iPhone/Reference/UIPrintPageRenderer_Class/Reference/Reference.html#//apple_ref/occ/instp/UIPrintPageRenderer/headerHeight">@property CGFloat headerHeight</a>
     * @since Available in iOS 4.2 and later.
     */
    public float getHeaderHeight() {
        if (customClass) { return objc_getHeaderHeightSuper(getSuper(), headerHeight); } else { return objc_getHeaderHeight(this, headerHeight); }
    }
    
    private static final Selector setHeaderHeight$ = Selector.register("setHeaderHeight:");
    @Bridge private native static void objc_setHeaderHeight(UIPrintPageRenderer __self__, Selector __cmd__, float headerHeight);
    @Bridge private native static void objc_setHeaderHeightSuper(ObjCSuper __super__, Selector __cmd__, float headerHeight);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../../../iPhone/Reference/UIPrintPageRenderer_Class/Reference/Reference.html#//apple_ref/occ/instp/UIPrintPageRenderer/headerHeight">@property CGFloat headerHeight</a>
     * @since Available in iOS 4.2 and later.
     */
    public void setHeaderHeight(float headerHeight) {
        if (customClass) { objc_setHeaderHeightSuper(getSuper(), setHeaderHeight$, headerHeight); } else { objc_setHeaderHeight(this, setHeaderHeight$, headerHeight); }
    }
    
    private static final Selector paperRect = Selector.register("paperRect");
    @Bridge private native static @ByVal CGRect objc_getPaperRect(UIPrintPageRenderer __self__, Selector __cmd__);
    @Bridge private native static @ByVal CGRect objc_getPaperRectSuper(ObjCSuper __super__, Selector __cmd__);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../../../iPhone/Reference/UIPrintPageRenderer_Class/Reference/Reference.html#//apple_ref/occ/instp/UIPrintPageRenderer/paperRect">@property(readonly) CGRect paperRect</a>
     * @since Available in iOS 4.2 and later.
     */
    public CGRect getPaperRect() {
        if (customClass) { return objc_getPaperRectSuper(getSuper(), paperRect); } else { return objc_getPaperRect(this, paperRect); }
    }
    
    private static final Selector printFormatters = Selector.register("printFormatters");
    @Bridge private native static NSArray objc_getPrintFormatters(UIPrintPageRenderer __self__, Selector __cmd__);
    @Bridge private native static NSArray objc_getPrintFormattersSuper(ObjCSuper __super__, Selector __cmd__);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../../../iPhone/Reference/UIPrintPageRenderer_Class/Reference/Reference.html#//apple_ref/occ/instp/UIPrintPageRenderer/printFormatters">@property(copy) NSArray *printFormatters</a>
     * @since Available in iOS 4.2 and later.
     */
    public NSArray getPrintFormatters() {
        if (customClass) { return objc_getPrintFormattersSuper(getSuper(), printFormatters); } else { return objc_getPrintFormatters(this, printFormatters); }
    }
    
    private static final Selector setPrintFormatters$ = Selector.register("setPrintFormatters:");
    @Bridge private native static void objc_setPrintFormatters(UIPrintPageRenderer __self__, Selector __cmd__, NSArray printFormatters);
    @Bridge private native static void objc_setPrintFormattersSuper(ObjCSuper __super__, Selector __cmd__, NSArray printFormatters);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../../../iPhone/Reference/UIPrintPageRenderer_Class/Reference/Reference.html#//apple_ref/occ/instp/UIPrintPageRenderer/printFormatters">@property(copy) NSArray *printFormatters</a>
     * @since Available in iOS 4.2 and later.
     */
    public void setPrintFormatters(NSArray printFormatters) {
        if (customClass) { objc_setPrintFormattersSuper(getSuper(), setPrintFormatters$, printFormatters); } else { objc_setPrintFormatters(this, setPrintFormatters$, printFormatters); }
    }
    
    private static final Selector printableRect = Selector.register("printableRect");
    @Bridge private native static @ByVal CGRect objc_getPrintableRect(UIPrintPageRenderer __self__, Selector __cmd__);
    @Bridge private native static @ByVal CGRect objc_getPrintableRectSuper(ObjCSuper __super__, Selector __cmd__);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../../../iPhone/Reference/UIPrintPageRenderer_Class/Reference/Reference.html#//apple_ref/occ/instp/UIPrintPageRenderer/printableRect">@property(readonly) CGRect printableRect</a>
     * @since Available in iOS 4.2 and later.
     */
    public CGRect getPrintableRect() {
        if (customClass) { return objc_getPrintableRectSuper(getSuper(), printableRect); } else { return objc_getPrintableRect(this, printableRect); }
    }
    /*</properties>*/
    /*<methods>*/
    
    private static final Selector addPrintFormatter$startingAtPageAtIndex$ = Selector.register("addPrintFormatter:startingAtPageAtIndex:");
    @Bridge private native static void objc_addPrintFormatter(UIPrintPageRenderer __self__, Selector __cmd__, UIPrintFormatter formatter, int pageIndex);
    @Bridge private native static void objc_addPrintFormatterSuper(ObjCSuper __super__, Selector __cmd__, UIPrintFormatter formatter, int pageIndex);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../../../iPhone/Reference/UIPrintPageRenderer_Class/Reference/Reference.html#//apple_ref/occ/instm/UIPrintPageRenderer/addPrintFormatter:startingAtPageAtIndex:">- (void)addPrintFormatter:(UIPrintFormatter *)formatter startingAtPageAtIndex:(NSInteger)pageIndex</a>
     * @since Available in iOS 4.2 and later.
     */
    public void addPrintFormatter(UIPrintFormatter formatter, int pageIndex) {
        if (customClass) { objc_addPrintFormatterSuper(getSuper(), addPrintFormatter$startingAtPageAtIndex$, formatter, pageIndex); } else { objc_addPrintFormatter(this, addPrintFormatter$startingAtPageAtIndex$, formatter, pageIndex); }
    }
    
    private static final Selector drawContentForPageAtIndex$inRect$ = Selector.register("drawContentForPageAtIndex:inRect:");
    @Bridge private native static void objc_drawContent(UIPrintPageRenderer __self__, Selector __cmd__, int index, @ByVal CGRect contentRect);
    @Bridge private native static void objc_drawContentSuper(ObjCSuper __super__, Selector __cmd__, int index, @ByVal CGRect contentRect);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../../../iPhone/Reference/UIPrintPageRenderer_Class/Reference/Reference.html#//apple_ref/occ/instm/UIPrintPageRenderer/drawContentForPageAtIndex:inRect:">- (void)drawContentForPageAtIndex:(NSInteger)index inRect:(CGRect)contentRect</a>
     * @since Available in iOS 4.2 and later.
     */
    public void drawContent(int index, CGRect contentRect) {
        if (customClass) { objc_drawContentSuper(getSuper(), drawContentForPageAtIndex$inRect$, index, contentRect); } else { objc_drawContent(this, drawContentForPageAtIndex$inRect$, index, contentRect); }
    }
    
    private static final Selector drawFooterForPageAtIndex$inRect$ = Selector.register("drawFooterForPageAtIndex:inRect:");
    @Bridge private native static void objc_drawFooter(UIPrintPageRenderer __self__, Selector __cmd__, int index, @ByVal CGRect footerRect);
    @Bridge private native static void objc_drawFooterSuper(ObjCSuper __super__, Selector __cmd__, int index, @ByVal CGRect footerRect);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../../../iPhone/Reference/UIPrintPageRenderer_Class/Reference/Reference.html#//apple_ref/occ/instm/UIPrintPageRenderer/drawFooterForPageAtIndex:inRect:">- (void)drawFooterForPageAtIndex:(NSInteger)index inRect:(CGRect)footerRect</a>
     * @since Available in iOS 4.2 and later.
     */
    public void drawFooter(int index, CGRect footerRect) {
        if (customClass) { objc_drawFooterSuper(getSuper(), drawFooterForPageAtIndex$inRect$, index, footerRect); } else { objc_drawFooter(this, drawFooterForPageAtIndex$inRect$, index, footerRect); }
    }
    
    private static final Selector drawHeaderForPageAtIndex$inRect$ = Selector.register("drawHeaderForPageAtIndex:inRect:");
    @Bridge private native static void objc_drawHeader(UIPrintPageRenderer __self__, Selector __cmd__, int index, @ByVal CGRect headerRect);
    @Bridge private native static void objc_drawHeaderSuper(ObjCSuper __super__, Selector __cmd__, int index, @ByVal CGRect headerRect);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../../../iPhone/Reference/UIPrintPageRenderer_Class/Reference/Reference.html#//apple_ref/occ/instm/UIPrintPageRenderer/drawHeaderForPageAtIndex:inRect:">- (void)drawHeaderForPageAtIndex:(NSInteger)index inRect:(CGRect)headerRect</a>
     * @since Available in iOS 4.2 and later.
     */
    public void drawHeader(int index, CGRect headerRect) {
        if (customClass) { objc_drawHeaderSuper(getSuper(), drawHeaderForPageAtIndex$inRect$, index, headerRect); } else { objc_drawHeader(this, drawHeaderForPageAtIndex$inRect$, index, headerRect); }
    }
    
    private static final Selector drawPageAtIndex$inRect$ = Selector.register("drawPageAtIndex:inRect:");
    @Bridge private native static void objc_drawPage(UIPrintPageRenderer __self__, Selector __cmd__, int index, @ByVal CGRect pageRect);
    @Bridge private native static void objc_drawPageSuper(ObjCSuper __super__, Selector __cmd__, int index, @ByVal CGRect pageRect);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../../../iPhone/Reference/UIPrintPageRenderer_Class/Reference/Reference.html#//apple_ref/occ/instm/UIPrintPageRenderer/drawPageAtIndex:inRect:">- (void)drawPageAtIndex:(NSInteger)index inRect:(CGRect)pageRect</a>
     * @since Available in iOS 4.2 and later.
     */
    public void drawPage(int index, CGRect pageRect) {
        if (customClass) { objc_drawPageSuper(getSuper(), drawPageAtIndex$inRect$, index, pageRect); } else { objc_drawPage(this, drawPageAtIndex$inRect$, index, pageRect); }
    }
    
    private static final Selector drawPrintFormatter$forPageAtIndex$ = Selector.register("drawPrintFormatter:forPageAtIndex:");
    @Bridge private native static void objc_drawPrintFormatter(UIPrintPageRenderer __self__, Selector __cmd__, UIPrintFormatter printFormatter, int index);
    @Bridge private native static void objc_drawPrintFormatterSuper(ObjCSuper __super__, Selector __cmd__, UIPrintFormatter printFormatter, int index);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../../../iPhone/Reference/UIPrintPageRenderer_Class/Reference/Reference.html#//apple_ref/occ/instm/UIPrintPageRenderer/drawPrintFormatter:forPageAtIndex:">- (void)drawPrintFormatter:(UIPrintFormatter *)printFormatter forPageAtIndex:(NSInteger)index</a>
     * @since Available in iOS 4.2 and later.
     */
    public void drawPrintFormatter(UIPrintFormatter printFormatter, int index) {
        if (customClass) { objc_drawPrintFormatterSuper(getSuper(), drawPrintFormatter$forPageAtIndex$, printFormatter, index); } else { objc_drawPrintFormatter(this, drawPrintFormatter$forPageAtIndex$, printFormatter, index); }
    }
    
    private static final Selector numberOfPages = Selector.register("numberOfPages");
    @Bridge private native static int objc_getNumberOfPages(UIPrintPageRenderer __self__, Selector __cmd__);
    @Bridge private native static int objc_getNumberOfPagesSuper(ObjCSuper __super__, Selector __cmd__);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../../../iPhone/Reference/UIPrintPageRenderer_Class/Reference/Reference.html#//apple_ref/occ/instm/UIPrintPageRenderer/numberOfPages">- (NSInteger)numberOfPages</a>
     * @since Available in iOS 4.2 and later.
     */
    public int getNumberOfPages() {
        if (customClass) { return objc_getNumberOfPagesSuper(getSuper(), numberOfPages); } else { return objc_getNumberOfPages(this, numberOfPages); }
    }
    
    private static final Selector printFormattersForPageAtIndex$ = Selector.register("printFormattersForPageAtIndex:");
    @Bridge private native static NSArray objc_getPrintFormatters(UIPrintPageRenderer __self__, Selector __cmd__, int pageIndex);
    @Bridge private native static NSArray objc_getPrintFormattersSuper(ObjCSuper __super__, Selector __cmd__, int pageIndex);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../../../iPhone/Reference/UIPrintPageRenderer_Class/Reference/Reference.html#//apple_ref/occ/instm/UIPrintPageRenderer/printFormattersForPageAtIndex:">- (NSArray *)printFormattersForPageAtIndex:(NSInteger)pageIndex</a>
     * @since Available in iOS 4.2 and later.
     */
    public NSArray getPrintFormatters(int pageIndex) {
        if (customClass) { return objc_getPrintFormattersSuper(getSuper(), printFormattersForPageAtIndex$, pageIndex); } else { return objc_getPrintFormatters(this, printFormattersForPageAtIndex$, pageIndex); }
    }
    
    private static final Selector prepareForDrawingPages$ = Selector.register("prepareForDrawingPages:");
    @Bridge private native static void objc_prepareForDrawingPages(UIPrintPageRenderer __self__, Selector __cmd__, @ByVal NSRange range);
    @Bridge private native static void objc_prepareForDrawingPagesSuper(ObjCSuper __super__, Selector __cmd__, @ByVal NSRange range);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../../../iPhone/Reference/UIPrintPageRenderer_Class/Reference/Reference.html#//apple_ref/occ/instm/UIPrintPageRenderer/prepareForDrawingPages:">- (void)prepareForDrawingPages:(NSRange)range</a>
     * @since Available in iOS 4.2 and later.
     */
    public void prepareForDrawingPages(NSRange range) {
        if (customClass) { objc_prepareForDrawingPagesSuper(getSuper(), prepareForDrawingPages$, range); } else { objc_prepareForDrawingPages(this, prepareForDrawingPages$, range); }
    }
    /*</methods>*/
    /*<callbacks>*/
    static class Callbacks {
        @Callback @BindSelector("footerHeight") public static float getFooterHeight(UIPrintPageRenderer __self__, Selector __cmd__) { return __self__.getFooterHeight(); }
        @Callback @BindSelector("setFooterHeight:") public static void setFooterHeight(UIPrintPageRenderer __self__, Selector __cmd__, float footerHeight) { __self__.setFooterHeight(footerHeight); }
        @Callback @BindSelector("headerHeight") public static float getHeaderHeight(UIPrintPageRenderer __self__, Selector __cmd__) { return __self__.getHeaderHeight(); }
        @Callback @BindSelector("setHeaderHeight:") public static void setHeaderHeight(UIPrintPageRenderer __self__, Selector __cmd__, float headerHeight) { __self__.setHeaderHeight(headerHeight); }
        @Callback @BindSelector("paperRect") public static @ByVal CGRect getPaperRect(UIPrintPageRenderer __self__, Selector __cmd__) { return __self__.getPaperRect(); }
        @Callback @BindSelector("printFormatters") public static NSArray getPrintFormatters(UIPrintPageRenderer __self__, Selector __cmd__) { return __self__.getPrintFormatters(); }
        @Callback @BindSelector("setPrintFormatters:") public static void setPrintFormatters(UIPrintPageRenderer __self__, Selector __cmd__, NSArray printFormatters) { __self__.setPrintFormatters(printFormatters); }
        @Callback @BindSelector("printableRect") public static @ByVal CGRect getPrintableRect(UIPrintPageRenderer __self__, Selector __cmd__) { return __self__.getPrintableRect(); }
        @Callback @BindSelector("addPrintFormatter:startingAtPageAtIndex:") public static void addPrintFormatter(UIPrintPageRenderer __self__, Selector __cmd__, UIPrintFormatter formatter, int pageIndex) { __self__.addPrintFormatter(formatter, pageIndex); }
        @Callback @BindSelector("drawContentForPageAtIndex:inRect:") public static void drawContent(UIPrintPageRenderer __self__, Selector __cmd__, int index, @ByVal CGRect contentRect) { __self__.drawContent(index, contentRect); }
        @Callback @BindSelector("drawFooterForPageAtIndex:inRect:") public static void drawFooter(UIPrintPageRenderer __self__, Selector __cmd__, int index, @ByVal CGRect footerRect) { __self__.drawFooter(index, footerRect); }
        @Callback @BindSelector("drawHeaderForPageAtIndex:inRect:") public static void drawHeader(UIPrintPageRenderer __self__, Selector __cmd__, int index, @ByVal CGRect headerRect) { __self__.drawHeader(index, headerRect); }
        @Callback @BindSelector("drawPageAtIndex:inRect:") public static void drawPage(UIPrintPageRenderer __self__, Selector __cmd__, int index, @ByVal CGRect pageRect) { __self__.drawPage(index, pageRect); }
        @Callback @BindSelector("drawPrintFormatter:forPageAtIndex:") public static void drawPrintFormatter(UIPrintPageRenderer __self__, Selector __cmd__, UIPrintFormatter printFormatter, int index) { __self__.drawPrintFormatter(printFormatter, index); }
        @Callback @BindSelector("numberOfPages") public static int getNumberOfPages(UIPrintPageRenderer __self__, Selector __cmd__) { return __self__.getNumberOfPages(); }
        @Callback @BindSelector("printFormattersForPageAtIndex:") public static NSArray getPrintFormatters(UIPrintPageRenderer __self__, Selector __cmd__, int pageIndex) { return __self__.getPrintFormatters(pageIndex); }
        @Callback @BindSelector("prepareForDrawingPages:") public static void prepareForDrawingPages(UIPrintPageRenderer __self__, Selector __cmd__, @ByVal NSRange range) { __self__.prepareForDrawingPages(range); }
    }
    /*</callbacks>*/

}
