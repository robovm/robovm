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
 *   @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIPrintInteractionController_Class/Reference/Reference.html">UIPrintInteractionController Class Reference</a>
 *   @since Available in iOS 4.2 and later.
 * </div>
 */
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
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIPrintInteractionController_Class/Reference/Reference.html#//apple_ref/occ/instp/UIPrintInteractionController/delegate">@property(nonatomic, assign) id&amp;lt;UIPrintInteractionControllerDelegate&amp;gt; delegate</a>
     * @since Available in iOS 4.2 and later.
     */
    @Bind("delegate") public native @Type("id<UIPrintInteractionControllerDelegate>") UIPrintInteractionControllerDelegate getDelegate();
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIPrintInteractionController_Class/Reference/Reference.html#//apple_ref/occ/instp/UIPrintInteractionController/delegate">@property(nonatomic, assign) id&amp;lt;UIPrintInteractionControllerDelegate&amp;gt; delegate</a>
     * @since Available in iOS 4.2 and later.
     */
    @Bind("setDelegate:") public native void setDelegate(@Type("id<UIPrintInteractionControllerDelegate>") UIPrintInteractionControllerDelegate v);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIPrintInteractionController_Class/Reference/Reference.html#//apple_ref/occ/instp/UIPrintInteractionController/printFormatter">@property(nonatomic, retain) UIPrintFormatter *printFormatter</a>
     * @since Available in iOS 4.2 and later.
     */
    @Bind("printFormatter") public native @Type("UIPrintFormatter *") UIPrintFormatter getPrintFormatter();
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIPrintInteractionController_Class/Reference/Reference.html#//apple_ref/occ/instp/UIPrintInteractionController/printFormatter">@property(nonatomic, retain) UIPrintFormatter *printFormatter</a>
     * @since Available in iOS 4.2 and later.
     */
    @Bind("setPrintFormatter:") public native void setPrintFormatter(@Type("UIPrintFormatter *") UIPrintFormatter v);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIPrintInteractionController_Class/Reference/Reference.html#//apple_ref/occ/instp/UIPrintInteractionController/printInfo">@property(nonatomic, retain) UIPrintInfo *printInfo</a>
     * @since Available in iOS 4.2 and later.
     */
    @Bind("printInfo") public native @Type("UIPrintInfo *") UIPrintInfo getPrintInfo();
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIPrintInteractionController_Class/Reference/Reference.html#//apple_ref/occ/instp/UIPrintInteractionController/printInfo">@property(nonatomic, retain) UIPrintInfo *printInfo</a>
     * @since Available in iOS 4.2 and later.
     */
    @Bind("setPrintInfo:") public native void setPrintInfo(@Type("UIPrintInfo *") UIPrintInfo v);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIPrintInteractionController_Class/Reference/Reference.html#//apple_ref/occ/instp/UIPrintInteractionController/printPageRenderer">@property(nonatomic, retain) UIPrintPageRenderer printPageRenderer</a>
     * @since Available in iOS 4.2 and later.
     */
    @Bind("printPageRenderer") public native @Type("UIPrintPageRenderer") UIPrintPageRenderer getPrintPageRenderer();
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIPrintInteractionController_Class/Reference/Reference.html#//apple_ref/occ/instp/UIPrintInteractionController/printPageRenderer">@property(nonatomic, retain) UIPrintPageRenderer printPageRenderer</a>
     * @since Available in iOS 4.2 and later.
     */
    @Bind("setPrintPageRenderer:") public native void setPrintPageRenderer(@Type("UIPrintPageRenderer") UIPrintPageRenderer v);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIPrintInteractionController_Class/Reference/Reference.html#//apple_ref/occ/instp/UIPrintInteractionController/printPaper">@property(nonatomic, readonly) UIPrintPaper *printPaper</a>
     * @since Available in iOS 4.2 and later.
     */
    @Bind("printPaper") public native @Type("UIPrintPaper *") UIPrintPaper getPrintPaper();
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIPrintInteractionController_Class/Reference/Reference.html#//apple_ref/occ/instp/UIPrintInteractionController/printingItem">@property(nonatomic, copy) id printingItem</a>
     * @since Available in iOS 4.2 and later.
     */
    @Bind("printingItem") public native @Type("id") NSObject getPrintingItem();
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIPrintInteractionController_Class/Reference/Reference.html#//apple_ref/occ/instp/UIPrintInteractionController/printingItem">@property(nonatomic, copy) id printingItem</a>
     * @since Available in iOS 4.2 and later.
     */
    @Bind("setPrintingItem:") public native void setPrintingItem(@Type("id") NSObject v);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIPrintInteractionController_Class/Reference/Reference.html#//apple_ref/occ/instp/UIPrintInteractionController/printingItems">@property(nonatomic, copy) NSArray *printingItems</a>
     * @since Available in iOS 4.2 and later.
     */
    @Bind("printingItems") public native @Type("NSArray *") NSArray getPrintingItems();
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIPrintInteractionController_Class/Reference/Reference.html#//apple_ref/occ/instp/UIPrintInteractionController/printingItems">@property(nonatomic, copy) NSArray *printingItems</a>
     * @since Available in iOS 4.2 and later.
     */
    @Bind("setPrintingItems:") public native void setPrintingItems(@Type("NSArray *") NSArray v);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIPrintInteractionController_Class/Reference/Reference.html#//apple_ref/occ/instp/UIPrintInteractionController/showsPageRange">@property(nonatomic) BOOL showsPageRange</a>
     * @since Available in iOS 4.2 and later.
     */
    @Bind("showsPageRange") public native @Type("BOOL") boolean isShowsPageRange();
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIPrintInteractionController_Class/Reference/Reference.html#//apple_ref/occ/instp/UIPrintInteractionController/showsPageRange">@property(nonatomic) BOOL showsPageRange</a>
     * @since Available in iOS 4.2 and later.
     */
    @Bind("setShowsPageRange:") public native void setShowsPageRange(@Type("BOOL") boolean v);
    /*</properties>*/
    /*<methods>*/
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIPrintInteractionController_Class/Reference/Reference.html#//apple_ref/occ/clm/UIPrintInteractionController/canPrintData:">+ (BOOL)canPrintData:(NSData *)data</a>
     * @since Available in iOS 4.2 and later.
     */
    @Bind("canPrintData:") public native static @Type("BOOL") boolean canPrint(@Type("NSData *") NSData data);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIPrintInteractionController_Class/Reference/Reference.html#//apple_ref/occ/clm/UIPrintInteractionController/canPrintURL:">+ (BOOL)canPrintURL:(NSURL *)url</a>
     * @since Available in iOS 4.2 and later.
     */
    @Bind("canPrintURL:") public native static @Type("BOOL") boolean canPrint(@Type("NSURL *") NSURL url);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIPrintInteractionController_Class/Reference/Reference.html#//apple_ref/occ/clm/UIPrintInteractionController/printableUTIs">+ (NSSet *)printableUTIs</a>
     * @since Available in iOS 4.2 and later.
     */
    @Bind("printableUTIs") public native static @Type("NSSet *") NSSet getPrintableUTIs();
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIPrintInteractionController_Class/Reference/Reference.html#//apple_ref/occ/clm/UIPrintInteractionController/sharedPrintController">+ (UIPrintInteractionController *)sharedPrintController</a>
     * @since Available in iOS 4.2 and later.
     */
    @Bind("sharedPrintController") public native static @Type("UIPrintInteractionController *") UIPrintInteractionController getSharedPrintController();
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIPrintInteractionController_Class/Reference/Reference.html#//apple_ref/occ/clm/UIPrintInteractionController/isPrintingAvailable">+ (BOOL)isPrintingAvailable</a>
     * @since Available in iOS 4.2 and later.
     */
    @Bind("isPrintingAvailable") public native static @Type("BOOL") boolean isPrintingAvailable();
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIPrintInteractionController_Class/Reference/Reference.html#//apple_ref/occ/instm/UIPrintInteractionController/dismissAnimated:">- (void)dismissAnimated:(BOOL)animated</a>
     * @since Available in iOS 4.2 and later.
     */
    @Bind("dismissAnimated:") public native @Type("void") void dismiss(@Type("BOOL") boolean animated);
    /*</methods>*/

}
