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

    /*<constructors>*/
    public UIPrintFormatter() {}
    
    /*</constructors>*/
    /*<properties>*/
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIPrintFormatter_Class/Reference/Reference.html#//apple_ref/occ/instp/UIPrintFormatter/contentInsets">@property(nonatomic) UIEdgeInsets contentInsets</a>
     * @since Available in iOS 4.2 and later.
     */
    @Bind("contentInsets") public native @Type("UIEdgeInsets") UIEdgeInsets getContentInsets();
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIPrintFormatter_Class/Reference/Reference.html#//apple_ref/occ/instp/UIPrintFormatter/contentInsets">@property(nonatomic) UIEdgeInsets contentInsets</a>
     * @since Available in iOS 4.2 and later.
     */
    @Bind("setContentInsets:") public native void setContentInsets(@Type("UIEdgeInsets") UIEdgeInsets v);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIPrintFormatter_Class/Reference/Reference.html#//apple_ref/occ/instp/UIPrintFormatter/maximumContentHeight">@property(nonatomic) CGFloat maximumContentHeight</a>
     * @since Available in iOS 4.2 and later.
     */
    @Bind("maximumContentHeight") public native @Type("CGFloat") float getMaximumContentHeight();
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIPrintFormatter_Class/Reference/Reference.html#//apple_ref/occ/instp/UIPrintFormatter/maximumContentHeight">@property(nonatomic) CGFloat maximumContentHeight</a>
     * @since Available in iOS 4.2 and later.
     */
    @Bind("setMaximumContentHeight:") public native void setMaximumContentHeight(@Type("CGFloat") float v);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIPrintFormatter_Class/Reference/Reference.html#//apple_ref/occ/instp/UIPrintFormatter/maximumContentWidth">@property(nonatomic) CGFloat maximumContentWidth</a>
     * @since Available in iOS 4.2 and later.
     */
    @Bind("maximumContentWidth") public native @Type("CGFloat") float getMaximumContentWidth();
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIPrintFormatter_Class/Reference/Reference.html#//apple_ref/occ/instp/UIPrintFormatter/maximumContentWidth">@property(nonatomic) CGFloat maximumContentWidth</a>
     * @since Available in iOS 4.2 and later.
     */
    @Bind("setMaximumContentWidth:") public native void setMaximumContentWidth(@Type("CGFloat") float v);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIPrintFormatter_Class/Reference/Reference.html#//apple_ref/occ/instp/UIPrintFormatter/pageCount">@property(nonatomic, readonly) NSInteger pageCount</a>
     * @since Available in iOS 4.2 and later.
     */
    @Bind("pageCount") public native @Type("NSInteger") int getPageCount();
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIPrintFormatter_Class/Reference/Reference.html#//apple_ref/occ/instp/UIPrintFormatter/printPageRenderer">@property(readonly) UIPrintPageRenderer *printPageRenderer</a>
     * @since Available in iOS 4.2 and later.
     */
    @Bind("printPageRenderer") public native @Type("UIPrintPageRenderer *") UIPrintPageRenderer getPrintPageRenderer();
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIPrintFormatter_Class/Reference/Reference.html#//apple_ref/occ/instp/UIPrintFormatter/startPage">@property(nonatomic) NSInteger startPage</a>
     * @since Available in iOS 4.2 and later.
     */
    @Bind("startPage") public native @Type("NSInteger") int getStartPage();
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIPrintFormatter_Class/Reference/Reference.html#//apple_ref/occ/instp/UIPrintFormatter/startPage">@property(nonatomic) NSInteger startPage</a>
     * @since Available in iOS 4.2 and later.
     */
    @Bind("setStartPage:") public native void setStartPage(@Type("NSInteger") int v);
    /*</properties>*/
    /*<methods>*/
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIPrintFormatter_Class/Reference/Reference.html#//apple_ref/occ/instm/UIPrintFormatter/drawInRect:forPageAtIndex:">- (void)drawInRect:(CGRect)rect forPageAtIndex:(NSInteger)pageIndex</a>
     * @since Available in iOS 4.2 and later.
     */
    @Bind("drawInRect:forPageAtIndex:") public native @Type("void") void draw(@Type("CGRect") CGRect rect, @Type("NSInteger") int pageIndex);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIPrintFormatter_Class/Reference/Reference.html#//apple_ref/occ/instm/UIPrintFormatter/rectForPageAtIndex:">- (CGRect)rectForPageAtIndex:(NSIndex)pageIndex</a>
     * @since Available in iOS 4.2 and later.
     */
    @Bind("rectForPageAtIndex:") public native @Type("CGRect") CGRect getRectForPage(@Type("NSIndex") int pageIndex);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIPrintFormatter_Class/Reference/Reference.html#//apple_ref/occ/instm/UIPrintFormatter/removeFromPrintPageRenderer">- (void)removeFromPrintPageRenderer</a>
     * @since Available in iOS 4.2 and later.
     */
    @Bind("removeFromPrintPageRenderer") public native @Type("void") void removeFromPrintPageRenderer();
    /*</methods>*/

}
