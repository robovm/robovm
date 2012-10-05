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

    private static final ObjCClass objCClass = ObjCClass.getByType(/*<name>*/ UIPrintInteractionController /*</name>*/.class);

    /*<constructors>*/
    protected UIPrintInteractionController(SkipInit skipInit) { super(skipInit); }
    public UIPrintInteractionController() {}
    
    /*</constructors>*/
    /*<properties>*/
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIPrintInteractionController_Class/Reference/Reference.html#//apple_ref/occ/instp/UIPrintInteractionController/delegate">@property(nonatomic, assign) id&amp;lt;UIPrintInteractionControllerDelegate&amp;gt; delegate</a>
     * @since Available in iOS 4.2 and later.
     */
    @Bind("delegate") public native UIPrintInteractionControllerDelegate getDelegate();
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIPrintInteractionController_Class/Reference/Reference.html#//apple_ref/occ/instp/UIPrintInteractionController/delegate">@property(nonatomic, assign) id&amp;lt;UIPrintInteractionControllerDelegate&amp;gt; delegate</a>
     * @since Available in iOS 4.2 and later.
     */
    @Bind("setDelegate:") public native void setDelegate(UIPrintInteractionControllerDelegate v);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIPrintInteractionController_Class/Reference/Reference.html#//apple_ref/occ/instp/UIPrintInteractionController/printFormatter">@property(nonatomic, retain) UIPrintFormatter *printFormatter</a>
     * @since Available in iOS 4.2 and later.
     */
    @Bind("printFormatter") public native UIPrintFormatter getPrintFormatter();
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIPrintInteractionController_Class/Reference/Reference.html#//apple_ref/occ/instp/UIPrintInteractionController/printFormatter">@property(nonatomic, retain) UIPrintFormatter *printFormatter</a>
     * @since Available in iOS 4.2 and later.
     */
    @Bind("setPrintFormatter:") public native void setPrintFormatter(UIPrintFormatter v);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIPrintInteractionController_Class/Reference/Reference.html#//apple_ref/occ/instp/UIPrintInteractionController/printInfo">@property(nonatomic, retain) UIPrintInfo *printInfo</a>
     * @since Available in iOS 4.2 and later.
     */
    @Bind("printInfo") public native UIPrintInfo getPrintInfo();
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIPrintInteractionController_Class/Reference/Reference.html#//apple_ref/occ/instp/UIPrintInteractionController/printInfo">@property(nonatomic, retain) UIPrintInfo *printInfo</a>
     * @since Available in iOS 4.2 and later.
     */
    @Bind("setPrintInfo:") public native void setPrintInfo(UIPrintInfo v);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIPrintInteractionController_Class/Reference/Reference.html#//apple_ref/occ/instp/UIPrintInteractionController/printPageRenderer">@property(nonatomic, retain) UIPrintPageRenderer printPageRenderer</a>
     * @since Available in iOS 4.2 and later.
     */
    @Bind("printPageRenderer") public native UIPrintPageRenderer getPrintPageRenderer();
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIPrintInteractionController_Class/Reference/Reference.html#//apple_ref/occ/instp/UIPrintInteractionController/printPageRenderer">@property(nonatomic, retain) UIPrintPageRenderer printPageRenderer</a>
     * @since Available in iOS 4.2 and later.
     */
    @Bind("setPrintPageRenderer:") public native void setPrintPageRenderer(UIPrintPageRenderer v);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIPrintInteractionController_Class/Reference/Reference.html#//apple_ref/occ/instp/UIPrintInteractionController/printPaper">@property(nonatomic, readonly) UIPrintPaper *printPaper</a>
     * @since Available in iOS 4.2 and later.
     */
    @Bind("printPaper") public native UIPrintPaper getPrintPaper();
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIPrintInteractionController_Class/Reference/Reference.html#//apple_ref/occ/instp/UIPrintInteractionController/printingItem">@property(nonatomic, copy) id printingItem</a>
     * @since Available in iOS 4.2 and later.
     */
    @Bind("printingItem") public native NSObject getPrintingItem();
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIPrintInteractionController_Class/Reference/Reference.html#//apple_ref/occ/instp/UIPrintInteractionController/printingItem">@property(nonatomic, copy) id printingItem</a>
     * @since Available in iOS 4.2 and later.
     */
    @Bind("setPrintingItem:") public native void setPrintingItem(NSObject v);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIPrintInteractionController_Class/Reference/Reference.html#//apple_ref/occ/instp/UIPrintInteractionController/printingItems">@property(nonatomic, copy) NSArray *printingItems</a>
     * @since Available in iOS 4.2 and later.
     */
    @Bind("printingItems") public native NSArray getPrintingItems();
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIPrintInteractionController_Class/Reference/Reference.html#//apple_ref/occ/instp/UIPrintInteractionController/printingItems">@property(nonatomic, copy) NSArray *printingItems</a>
     * @since Available in iOS 4.2 and later.
     */
    @Bind("setPrintingItems:") public native void setPrintingItems(NSArray v);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIPrintInteractionController_Class/Reference/Reference.html#//apple_ref/occ/instp/UIPrintInteractionController/showsPageRange">@property(nonatomic) BOOL showsPageRange</a>
     * @since Available in iOS 4.2 and later.
     */
    @Bind("showsPageRange") public native boolean isShowsPageRange();
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIPrintInteractionController_Class/Reference/Reference.html#//apple_ref/occ/instp/UIPrintInteractionController/showsPageRange">@property(nonatomic) BOOL showsPageRange</a>
     * @since Available in iOS 4.2 and later.
     */
    @Bind("setShowsPageRange:") public native void setShowsPageRange(boolean v);
    /*</properties>*/
    /*<methods>*/
    
    private static final Selector canPrintData$ = Selector.register("canPrintData:");
    @Bridge(symbol = "objc_msgSend") private native static boolean objc_canPrint(ObjCClass __self__, Selector __cmd__, NSData data);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIPrintInteractionController_Class/Reference/Reference.html#//apple_ref/occ/clm/UIPrintInteractionController/canPrintData:">+ (BOOL)canPrintData:(NSData *)data</a>
     * @since Available in iOS 4.2 and later.
     */
    public static boolean canPrint(NSData data) {
        return objc_canPrint(objCClass, canPrintData$, data);
    }
    
    private static final Selector canPrintURL$ = Selector.register("canPrintURL:");
    @Bridge(symbol = "objc_msgSend") private native static boolean objc_canPrint(ObjCClass __self__, Selector __cmd__, NSURL url);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIPrintInteractionController_Class/Reference/Reference.html#//apple_ref/occ/clm/UIPrintInteractionController/canPrintURL:">+ (BOOL)canPrintURL:(NSURL *)url</a>
     * @since Available in iOS 4.2 and later.
     */
    public static boolean canPrint(NSURL url) {
        return objc_canPrint(objCClass, canPrintURL$, url);
    }
    
    private static final Selector printableUTIs = Selector.register("printableUTIs");
    @Bridge(symbol = "objc_msgSend") private native static NSSet objc_getPrintableUTIs(ObjCClass __self__, Selector __cmd__);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIPrintInteractionController_Class/Reference/Reference.html#//apple_ref/occ/clm/UIPrintInteractionController/printableUTIs">+ (NSSet *)printableUTIs</a>
     * @since Available in iOS 4.2 and later.
     */
    public static NSSet getPrintableUTIs() {
        return objc_getPrintableUTIs(objCClass, printableUTIs);
    }
    
    private static final Selector sharedPrintController = Selector.register("sharedPrintController");
    @Bridge(symbol = "objc_msgSend") private native static UIPrintInteractionController objc_getSharedPrintController(ObjCClass __self__, Selector __cmd__);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIPrintInteractionController_Class/Reference/Reference.html#//apple_ref/occ/clm/UIPrintInteractionController/sharedPrintController">+ (UIPrintInteractionController *)sharedPrintController</a>
     * @since Available in iOS 4.2 and later.
     */
    public static UIPrintInteractionController getSharedPrintController() {
        return objc_getSharedPrintController(objCClass, sharedPrintController);
    }
    
    private static final Selector isPrintingAvailable = Selector.register("isPrintingAvailable");
    @Bridge(symbol = "objc_msgSend") private native static boolean objc_isPrintingAvailable(ObjCClass __self__, Selector __cmd__);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIPrintInteractionController_Class/Reference/Reference.html#//apple_ref/occ/clm/UIPrintInteractionController/isPrintingAvailable">+ (BOOL)isPrintingAvailable</a>
     * @since Available in iOS 4.2 and later.
     */
    public static boolean isPrintingAvailable() {
        return objc_isPrintingAvailable(objCClass, isPrintingAvailable);
    }
    
    private static final Selector dismissAnimated$ = Selector.register("dismissAnimated:");
    @Bridge(symbol = "objc_msgSend") private native static void objc_dismiss(UIPrintInteractionController __self__, Selector __cmd__, boolean animated);
    @Bridge(symbol = "objc_msgSendSuper") private native static void objc_dismissSuper(ObjCSuper __super__, UIPrintInteractionController __self__, Selector __cmd__, boolean animated);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIPrintInteractionController_Class/Reference/Reference.html#//apple_ref/occ/instm/UIPrintInteractionController/dismissAnimated:">- (void)dismissAnimated:(BOOL)animated</a>
     * @since Available in iOS 4.2 and later.
     */
    public void dismiss(boolean animated) {
        if (customClass) { objc_dismissSuper(getSuper(), this, dismissAnimated$, animated); } else { objc_dismiss(this, dismissAnimated$, animated); }
    }
    /*</methods>*/

}
