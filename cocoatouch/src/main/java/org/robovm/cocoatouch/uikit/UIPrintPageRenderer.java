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
 *   @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../../../iPhone/Reference/UIPrintPageRenderer_Class/Reference/Reference.html">UIPrintPageRenderer Class Reference</a>
 *   @since Available in iOS 4.2 and later.
 * </div>
 */
/*<library>*/@Library("UIKit")/*</library>*/
public class /*<name>*/ UIPrintPageRenderer /*</name>*/ 
    extends /*<extends>*/ NSObject /*</extends>*/ 
    /*<implements>*/ /*</implements>*/ {

    static {
        ObjCRuntime.bind(/*<name>*/ UIPrintPageRenderer /*</name>*/.class);
    }

    /*<constructors>*/
    public UIPrintPageRenderer() {}
    
    /*</constructors>*/
    /*<properties>*/
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../../../iPhone/Reference/UIPrintPageRenderer_Class/Reference/Reference.html#//apple_ref/occ/instp/UIPrintPageRenderer/footerHeight">@property CGFloat footerHeight</a>
     * @since Available in iOS 4.2 and later.
     */
    @Bind("footerHeight") public native @Type("CGFloat") float getFooterHeight();
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../../../iPhone/Reference/UIPrintPageRenderer_Class/Reference/Reference.html#//apple_ref/occ/instp/UIPrintPageRenderer/footerHeight">@property CGFloat footerHeight</a>
     * @since Available in iOS 4.2 and later.
     */
    @Bind("setFooterHeight:") public native void setFooterHeight(@Type("CGFloat") float v);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../../../iPhone/Reference/UIPrintPageRenderer_Class/Reference/Reference.html#//apple_ref/occ/instp/UIPrintPageRenderer/headerHeight">@property CGFloat headerHeight</a>
     * @since Available in iOS 4.2 and later.
     */
    @Bind("headerHeight") public native @Type("CGFloat") float getHeaderHeight();
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../../../iPhone/Reference/UIPrintPageRenderer_Class/Reference/Reference.html#//apple_ref/occ/instp/UIPrintPageRenderer/headerHeight">@property CGFloat headerHeight</a>
     * @since Available in iOS 4.2 and later.
     */
    @Bind("setHeaderHeight:") public native void setHeaderHeight(@Type("CGFloat") float v);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../../../iPhone/Reference/UIPrintPageRenderer_Class/Reference/Reference.html#//apple_ref/occ/instp/UIPrintPageRenderer/paperRect">@property(readonly) CGRect paperRect</a>
     * @since Available in iOS 4.2 and later.
     */
    @Bind("paperRect") public native @Type("CGRect") CGRect getPaperRect();
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../../../iPhone/Reference/UIPrintPageRenderer_Class/Reference/Reference.html#//apple_ref/occ/instp/UIPrintPageRenderer/printFormatters">@property(copy) NSArray *printFormatters</a>
     * @since Available in iOS 4.2 and later.
     */
    @Bind("printFormatters") public native @Type("NSArray *") NSArray getPrintFormatters();
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../../../iPhone/Reference/UIPrintPageRenderer_Class/Reference/Reference.html#//apple_ref/occ/instp/UIPrintPageRenderer/printFormatters">@property(copy) NSArray *printFormatters</a>
     * @since Available in iOS 4.2 and later.
     */
    @Bind("setPrintFormatters:") public native void setPrintFormatters(@Type("NSArray *") NSArray v);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../../../iPhone/Reference/UIPrintPageRenderer_Class/Reference/Reference.html#//apple_ref/occ/instp/UIPrintPageRenderer/printableRect">@property(readonly) CGRect printableRect</a>
     * @since Available in iOS 4.2 and later.
     */
    @Bind("printableRect") public native @Type("CGRect") CGRect getPrintableRect();
    /*</properties>*/
    /*<methods>*/
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../../../iPhone/Reference/UIPrintPageRenderer_Class/Reference/Reference.html#//apple_ref/occ/instm/UIPrintPageRenderer/addPrintFormatter:startingAtPageAtIndex:">- (void)addPrintFormatter:(UIPrintFormatter *)formatter startingAtPageAtIndex:(NSInteger)pageIndex</a>
     * @since Available in iOS 4.2 and later.
     */
    @Bind("addPrintFormatter:startingAtPageAtIndex:") public native @Type("void") void addPrintFormatter(@Type("UIPrintFormatter *") UIPrintFormatter formatter, @Type("NSInteger") int pageIndex);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../../../iPhone/Reference/UIPrintPageRenderer_Class/Reference/Reference.html#//apple_ref/occ/instm/UIPrintPageRenderer/drawContentForPageAtIndex:inRect:">- (void)drawContentForPageAtIndex:(NSInteger)index inRect:(CGRect)contentRect</a>
     * @since Available in iOS 4.2 and later.
     */
    @Bind("drawContentForPageAtIndex:inRect:") public native @Type("void") void drawContent(@Type("NSInteger") int index, @Type("CGRect") CGRect contentRect);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../../../iPhone/Reference/UIPrintPageRenderer_Class/Reference/Reference.html#//apple_ref/occ/instm/UIPrintPageRenderer/drawFooterForPageAtIndex:inRect:">- (void)drawFooterForPageAtIndex:(NSInteger)index inRect:(CGRect)footerRect</a>
     * @since Available in iOS 4.2 and later.
     */
    @Bind("drawFooterForPageAtIndex:inRect:") public native @Type("void") void drawFooter(@Type("NSInteger") int index, @Type("CGRect") CGRect footerRect);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../../../iPhone/Reference/UIPrintPageRenderer_Class/Reference/Reference.html#//apple_ref/occ/instm/UIPrintPageRenderer/drawHeaderForPageAtIndex:inRect:">- (void)drawHeaderForPageAtIndex:(NSInteger)index inRect:(CGRect)headerRect</a>
     * @since Available in iOS 4.2 and later.
     */
    @Bind("drawHeaderForPageAtIndex:inRect:") public native @Type("void") void drawHeader(@Type("NSInteger") int index, @Type("CGRect") CGRect headerRect);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../../../iPhone/Reference/UIPrintPageRenderer_Class/Reference/Reference.html#//apple_ref/occ/instm/UIPrintPageRenderer/drawPageAtIndex:inRect:">- (void)drawPageAtIndex:(NSInteger)index inRect:(CGRect)pageRect</a>
     * @since Available in iOS 4.2 and later.
     */
    @Bind("drawPageAtIndex:inRect:") public native @Type("void") void drawPage(@Type("NSInteger") int index, @Type("CGRect") CGRect pageRect);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../../../iPhone/Reference/UIPrintPageRenderer_Class/Reference/Reference.html#//apple_ref/occ/instm/UIPrintPageRenderer/drawPrintFormatter:forPageAtIndex:">- (void)drawPrintFormatter:(UIPrintFormatter *)printFormatter forPageAtIndex:(NSInteger)index</a>
     * @since Available in iOS 4.2 and later.
     */
    @Bind("drawPrintFormatter:forPageAtIndex:") public native @Type("void") void drawPrintFormatter(@Type("UIPrintFormatter *") UIPrintFormatter printFormatter, @Type("NSInteger") int index);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../../../iPhone/Reference/UIPrintPageRenderer_Class/Reference/Reference.html#//apple_ref/occ/instm/UIPrintPageRenderer/numberOfPages">- (NSInteger)numberOfPages</a>
     * @since Available in iOS 4.2 and later.
     */
    @Bind("numberOfPages") public native @Type("NSInteger") int getNumberOfPages();
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../../../iPhone/Reference/UIPrintPageRenderer_Class/Reference/Reference.html#//apple_ref/occ/instm/UIPrintPageRenderer/printFormattersForPageAtIndex:">- (NSArray *)printFormattersForPageAtIndex:(NSInteger)pageIndex</a>
     * @since Available in iOS 4.2 and later.
     */
    @Bind("printFormattersForPageAtIndex:") public native @Type("NSArray *") NSArray getPrintFormatters(@Type("NSInteger") int pageIndex);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../../../iPhone/Reference/UIPrintPageRenderer_Class/Reference/Reference.html#//apple_ref/occ/instm/UIPrintPageRenderer/prepareForDrawingPages:">- (void)prepareForDrawingPages:(NSRange)range</a>
     * @since Available in iOS 4.2 and later.
     */
    @Bind("prepareForDrawingPages:") public native @Type("void") void prepareForDrawingPages(@Type("NSRange") NSRange range);
    /*</methods>*/

}
