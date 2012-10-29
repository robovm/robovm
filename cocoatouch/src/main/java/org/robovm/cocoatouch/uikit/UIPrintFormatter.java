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
import org.robovm.objc.annotation.*;
import org.robovm.objc.block.*;
import org.robovm.rt.bro.annotation.*;
import org.robovm.rt.bro.ptr.*;
/*</imports>*/

/**
 *
 *
 * <div class="javadoc">
 *   @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIPrintFormatter_Class/Reference/Reference.html">UIPrintFormatter Class Reference</a>
 *   @since Available in iOS 4.2 and later.
 * </div>
 */
/*<library>*/@Library("UIKit")/*</library>*/
public class /*<name>*/ UIPrintFormatter /*</name>*/ 
    extends /*<extends>*/ NSObject /*</extends>*/ 
    /*<implements>*/ /*</implements>*/ {

    static {
        ObjCRuntime.bind(/*<name>*/ UIPrintFormatter /*</name>*/.class);
    }

    private static final ObjCClass objCClass = ObjCClass.getByType(/*<name>*/ UIPrintFormatter /*</name>*/.class);

    /*<constructors>*/
    protected UIPrintFormatter(SkipInit skipInit) { super(skipInit); }
    public UIPrintFormatter() {}
    
    /*</constructors>*/
    /*<properties>*/
    
    private static final Selector contentInsets = Selector.register("contentInsets");
    @Bridge(symbol = "objc_msgSend") private native static UIEdgeInsets objc_getContentInsets(UIPrintFormatter __self__, Selector __cmd__);
    @Bridge(symbol = "objc_msgSendSuper") private native static UIEdgeInsets objc_getContentInsetsSuper(ObjCSuper __super__, UIPrintFormatter __self__, Selector __cmd__);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIPrintFormatter_Class/Reference/Reference.html#//apple_ref/occ/instp/UIPrintFormatter/contentInsets">@property(nonatomic) UIEdgeInsets contentInsets</a>
     * @since Available in iOS 4.2 and later.
     */
    public UIEdgeInsets getContentInsets() {
        if (customClass) { return objc_getContentInsetsSuper(getSuper(), this, contentInsets); } else { return objc_getContentInsets(this, contentInsets); }
    }
    
    private static final Selector setContentInsets$ = Selector.register("setContentInsets:");
    @Bridge(symbol = "objc_msgSend") private native static void objc_setContentInsets(UIPrintFormatter __self__, Selector __cmd__, UIEdgeInsets contentInsets);
    @Bridge(symbol = "objc_msgSendSuper") private native static void objc_setContentInsetsSuper(ObjCSuper __super__, UIPrintFormatter __self__, Selector __cmd__, UIEdgeInsets contentInsets);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIPrintFormatter_Class/Reference/Reference.html#//apple_ref/occ/instp/UIPrintFormatter/contentInsets">@property(nonatomic) UIEdgeInsets contentInsets</a>
     * @since Available in iOS 4.2 and later.
     */
    public void setContentInsets(UIEdgeInsets contentInsets) {
        if (customClass) { objc_setContentInsetsSuper(getSuper(), this, setContentInsets$, contentInsets); } else { objc_setContentInsets(this, setContentInsets$, contentInsets); }
    }
    
    private static final Selector maximumContentHeight = Selector.register("maximumContentHeight");
    @Bridge(symbol = "objc_msgSend") private native static float objc_getMaximumContentHeight(UIPrintFormatter __self__, Selector __cmd__);
    @Bridge(symbol = "objc_msgSendSuper") private native static float objc_getMaximumContentHeightSuper(ObjCSuper __super__, UIPrintFormatter __self__, Selector __cmd__);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIPrintFormatter_Class/Reference/Reference.html#//apple_ref/occ/instp/UIPrintFormatter/maximumContentHeight">@property(nonatomic) CGFloat maximumContentHeight</a>
     * @since Available in iOS 4.2 and later.
     */
    public float getMaximumContentHeight() {
        if (customClass) { return objc_getMaximumContentHeightSuper(getSuper(), this, maximumContentHeight); } else { return objc_getMaximumContentHeight(this, maximumContentHeight); }
    }
    
    private static final Selector setMaximumContentHeight$ = Selector.register("setMaximumContentHeight:");
    @Bridge(symbol = "objc_msgSend") private native static void objc_setMaximumContentHeight(UIPrintFormatter __self__, Selector __cmd__, float maximumContentHeight);
    @Bridge(symbol = "objc_msgSendSuper") private native static void objc_setMaximumContentHeightSuper(ObjCSuper __super__, UIPrintFormatter __self__, Selector __cmd__, float maximumContentHeight);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIPrintFormatter_Class/Reference/Reference.html#//apple_ref/occ/instp/UIPrintFormatter/maximumContentHeight">@property(nonatomic) CGFloat maximumContentHeight</a>
     * @since Available in iOS 4.2 and later.
     */
    public void setMaximumContentHeight(float maximumContentHeight) {
        if (customClass) { objc_setMaximumContentHeightSuper(getSuper(), this, setMaximumContentHeight$, maximumContentHeight); } else { objc_setMaximumContentHeight(this, setMaximumContentHeight$, maximumContentHeight); }
    }
    
    private static final Selector maximumContentWidth = Selector.register("maximumContentWidth");
    @Bridge(symbol = "objc_msgSend") private native static float objc_getMaximumContentWidth(UIPrintFormatter __self__, Selector __cmd__);
    @Bridge(symbol = "objc_msgSendSuper") private native static float objc_getMaximumContentWidthSuper(ObjCSuper __super__, UIPrintFormatter __self__, Selector __cmd__);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIPrintFormatter_Class/Reference/Reference.html#//apple_ref/occ/instp/UIPrintFormatter/maximumContentWidth">@property(nonatomic) CGFloat maximumContentWidth</a>
     * @since Available in iOS 4.2 and later.
     */
    public float getMaximumContentWidth() {
        if (customClass) { return objc_getMaximumContentWidthSuper(getSuper(), this, maximumContentWidth); } else { return objc_getMaximumContentWidth(this, maximumContentWidth); }
    }
    
    private static final Selector setMaximumContentWidth$ = Selector.register("setMaximumContentWidth:");
    @Bridge(symbol = "objc_msgSend") private native static void objc_setMaximumContentWidth(UIPrintFormatter __self__, Selector __cmd__, float maximumContentWidth);
    @Bridge(symbol = "objc_msgSendSuper") private native static void objc_setMaximumContentWidthSuper(ObjCSuper __super__, UIPrintFormatter __self__, Selector __cmd__, float maximumContentWidth);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIPrintFormatter_Class/Reference/Reference.html#//apple_ref/occ/instp/UIPrintFormatter/maximumContentWidth">@property(nonatomic) CGFloat maximumContentWidth</a>
     * @since Available in iOS 4.2 and later.
     */
    public void setMaximumContentWidth(float maximumContentWidth) {
        if (customClass) { objc_setMaximumContentWidthSuper(getSuper(), this, setMaximumContentWidth$, maximumContentWidth); } else { objc_setMaximumContentWidth(this, setMaximumContentWidth$, maximumContentWidth); }
    }
    
    private static final Selector pageCount = Selector.register("pageCount");
    @Bridge(symbol = "objc_msgSend") private native static int objc_getPageCount(UIPrintFormatter __self__, Selector __cmd__);
    @Bridge(symbol = "objc_msgSendSuper") private native static int objc_getPageCountSuper(ObjCSuper __super__, UIPrintFormatter __self__, Selector __cmd__);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIPrintFormatter_Class/Reference/Reference.html#//apple_ref/occ/instp/UIPrintFormatter/pageCount">@property(nonatomic, readonly) NSInteger pageCount</a>
     * @since Available in iOS 4.2 and later.
     */
    public int getPageCount() {
        if (customClass) { return objc_getPageCountSuper(getSuper(), this, pageCount); } else { return objc_getPageCount(this, pageCount); }
    }
    
    private static final Selector printPageRenderer = Selector.register("printPageRenderer");
    @Bridge(symbol = "objc_msgSend") private native static UIPrintPageRenderer objc_getPrintPageRenderer(UIPrintFormatter __self__, Selector __cmd__);
    @Bridge(symbol = "objc_msgSendSuper") private native static UIPrintPageRenderer objc_getPrintPageRendererSuper(ObjCSuper __super__, UIPrintFormatter __self__, Selector __cmd__);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIPrintFormatter_Class/Reference/Reference.html#//apple_ref/occ/instp/UIPrintFormatter/printPageRenderer">@property(readonly) UIPrintPageRenderer *printPageRenderer</a>
     * @since Available in iOS 4.2 and later.
     */
    public UIPrintPageRenderer getPrintPageRenderer() {
        if (customClass) { return objc_getPrintPageRendererSuper(getSuper(), this, printPageRenderer); } else { return objc_getPrintPageRenderer(this, printPageRenderer); }
    }
    
    private static final Selector startPage = Selector.register("startPage");
    @Bridge(symbol = "objc_msgSend") private native static int objc_getStartPage(UIPrintFormatter __self__, Selector __cmd__);
    @Bridge(symbol = "objc_msgSendSuper") private native static int objc_getStartPageSuper(ObjCSuper __super__, UIPrintFormatter __self__, Selector __cmd__);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIPrintFormatter_Class/Reference/Reference.html#//apple_ref/occ/instp/UIPrintFormatter/startPage">@property(nonatomic) NSInteger startPage</a>
     * @since Available in iOS 4.2 and later.
     */
    public int getStartPage() {
        if (customClass) { return objc_getStartPageSuper(getSuper(), this, startPage); } else { return objc_getStartPage(this, startPage); }
    }
    
    private static final Selector setStartPage$ = Selector.register("setStartPage:");
    @Bridge(symbol = "objc_msgSend") private native static void objc_setStartPage(UIPrintFormatter __self__, Selector __cmd__, int startPage);
    @Bridge(symbol = "objc_msgSendSuper") private native static void objc_setStartPageSuper(ObjCSuper __super__, UIPrintFormatter __self__, Selector __cmd__, int startPage);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIPrintFormatter_Class/Reference/Reference.html#//apple_ref/occ/instp/UIPrintFormatter/startPage">@property(nonatomic) NSInteger startPage</a>
     * @since Available in iOS 4.2 and later.
     */
    public void setStartPage(int startPage) {
        if (customClass) { objc_setStartPageSuper(getSuper(), this, setStartPage$, startPage); } else { objc_setStartPage(this, setStartPage$, startPage); }
    }
    /*</properties>*/
    /*<methods>*/
    
    private static final Selector drawInRect$forPageAtIndex$ = Selector.register("drawInRect:forPageAtIndex:");
    @Bridge(symbol = "objc_msgSend") private native static void objc_draw(UIPrintFormatter __self__, Selector __cmd__, CGRect rect, int pageIndex);
    @Bridge(symbol = "objc_msgSendSuper") private native static void objc_drawSuper(ObjCSuper __super__, UIPrintFormatter __self__, Selector __cmd__, CGRect rect, int pageIndex);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIPrintFormatter_Class/Reference/Reference.html#//apple_ref/occ/instm/UIPrintFormatter/drawInRect:forPageAtIndex:">- (void)drawInRect:(CGRect)rect forPageAtIndex:(NSInteger)pageIndex</a>
     * @since Available in iOS 4.2 and later.
     */
    public void draw(CGRect rect, int pageIndex) {
        if (customClass) { objc_drawSuper(getSuper(), this, drawInRect$forPageAtIndex$, rect, pageIndex); } else { objc_draw(this, drawInRect$forPageAtIndex$, rect, pageIndex); }
    }
    
    private static final Selector rectForPageAtIndex$ = Selector.register("rectForPageAtIndex:");
    @Bridge(symbol = "objc_msgSend") private native static CGRect objc_getRectForPage(UIPrintFormatter __self__, Selector __cmd__, int pageIndex);
    @Bridge(symbol = "objc_msgSendSuper") private native static CGRect objc_getRectForPageSuper(ObjCSuper __super__, UIPrintFormatter __self__, Selector __cmd__, int pageIndex);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIPrintFormatter_Class/Reference/Reference.html#//apple_ref/occ/instm/UIPrintFormatter/rectForPageAtIndex:">- (CGRect)rectForPageAtIndex:(NSIndex)pageIndex</a>
     * @since Available in iOS 4.2 and later.
     */
    public CGRect getRectForPage(int pageIndex) {
        if (customClass) { return objc_getRectForPageSuper(getSuper(), this, rectForPageAtIndex$, pageIndex); } else { return objc_getRectForPage(this, rectForPageAtIndex$, pageIndex); }
    }
    
    private static final Selector removeFromPrintPageRenderer = Selector.register("removeFromPrintPageRenderer");
    @Bridge(symbol = "objc_msgSend") private native static void objc_removeFromPrintPageRenderer(UIPrintFormatter __self__, Selector __cmd__);
    @Bridge(symbol = "objc_msgSendSuper") private native static void objc_removeFromPrintPageRendererSuper(ObjCSuper __super__, UIPrintFormatter __self__, Selector __cmd__);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIPrintFormatter_Class/Reference/Reference.html#//apple_ref/occ/instm/UIPrintFormatter/removeFromPrintPageRenderer">- (void)removeFromPrintPageRenderer</a>
     * @since Available in iOS 4.2 and later.
     */
    public void removeFromPrintPageRenderer() {
        if (customClass) { objc_removeFromPrintPageRendererSuper(getSuper(), this, removeFromPrintPageRenderer); } else { objc_removeFromPrintPageRenderer(this, removeFromPrintPageRenderer); }
    }
    /*</methods>*/
    /*<callbacks>*/
    static class Callbacks {
        @Callback @BindSelector("contentInsets") public static UIEdgeInsets getContentInsets(UIPrintFormatter __self__, Selector __cmd__) { return __self__.getContentInsets(); }
        @Callback @BindSelector("setContentInsets:") public static void setContentInsets(UIPrintFormatter __self__, Selector __cmd__, UIEdgeInsets contentInsets) { __self__.setContentInsets(contentInsets); }
        @Callback @BindSelector("maximumContentHeight") public static float getMaximumContentHeight(UIPrintFormatter __self__, Selector __cmd__) { return __self__.getMaximumContentHeight(); }
        @Callback @BindSelector("setMaximumContentHeight:") public static void setMaximumContentHeight(UIPrintFormatter __self__, Selector __cmd__, float maximumContentHeight) { __self__.setMaximumContentHeight(maximumContentHeight); }
        @Callback @BindSelector("maximumContentWidth") public static float getMaximumContentWidth(UIPrintFormatter __self__, Selector __cmd__) { return __self__.getMaximumContentWidth(); }
        @Callback @BindSelector("setMaximumContentWidth:") public static void setMaximumContentWidth(UIPrintFormatter __self__, Selector __cmd__, float maximumContentWidth) { __self__.setMaximumContentWidth(maximumContentWidth); }
        @Callback @BindSelector("pageCount") public static int getPageCount(UIPrintFormatter __self__, Selector __cmd__) { return __self__.getPageCount(); }
        @Callback @BindSelector("printPageRenderer") public static UIPrintPageRenderer getPrintPageRenderer(UIPrintFormatter __self__, Selector __cmd__) { return __self__.getPrintPageRenderer(); }
        @Callback @BindSelector("startPage") public static int getStartPage(UIPrintFormatter __self__, Selector __cmd__) { return __self__.getStartPage(); }
        @Callback @BindSelector("setStartPage:") public static void setStartPage(UIPrintFormatter __self__, Selector __cmd__, int startPage) { __self__.setStartPage(startPage); }
        @Callback @BindSelector("drawInRect:forPageAtIndex:") public static void draw(UIPrintFormatter __self__, Selector __cmd__, CGRect rect, int pageIndex) { __self__.draw(rect, pageIndex); }
        @Callback @BindSelector("rectForPageAtIndex:") public static CGRect getRectForPage(UIPrintFormatter __self__, Selector __cmd__, int pageIndex) { return __self__.getRectForPage(pageIndex); }
        @Callback @BindSelector("removeFromPrintPageRenderer") public static void removeFromPrintPageRenderer(UIPrintFormatter __self__, Selector __cmd__) { __self__.removeFromPrintPageRenderer(); }
    }
    /*</callbacks>*/

}
