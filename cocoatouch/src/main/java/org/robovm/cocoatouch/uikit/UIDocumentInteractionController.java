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
 *   @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIDocumentInteractionController_class/Reference/Reference.html">UIDocumentInteractionController Class Reference</a>
 *   @since Available in iOS 3.2 and later.
 * </div>
 */
/*<library>*/@Library("UIKit")/*</library>*/
public class /*<name>*/ UIDocumentInteractionController /*</name>*/ 
    extends /*<extends>*/ NSObject /*</extends>*/ 
    /*<implements>*/ /*</implements>*/ {

    static {
        ObjCRuntime.bind(/*<name>*/ UIDocumentInteractionController /*</name>*/.class);
    }

    private static final ObjCClass objCClass = ObjCClass.getByType(/*<name>*/ UIDocumentInteractionController /*</name>*/.class);

    /*<constructors>*/
    protected UIDocumentInteractionController(SkipInit skipInit) { super(skipInit); }
    public UIDocumentInteractionController() {}
    
    /*</constructors>*/
    /*<properties>*/
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIDocumentInteractionController_class/Reference/Reference.html#//apple_ref/occ/instp/UIDocumentInteractionController/URL">@property(nonatomic,retain) NSURL *URL</a>
     * @since Available in iOS 3.2 and later.
     */
    @Bind("URL") public native NSURL getURL();
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIDocumentInteractionController_class/Reference/Reference.html#//apple_ref/occ/instp/UIDocumentInteractionController/URL">@property(nonatomic,retain) NSURL *URL</a>
     * @since Available in iOS 3.2 and later.
     */
    @Bind("setURL:") public native void setURL(NSURL v);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIDocumentInteractionController_class/Reference/Reference.html#//apple_ref/occ/instp/UIDocumentInteractionController/UTI">@property(nonatomic,copy) NSString *UTI</a>
     * @since Available in iOS 3.2 and later.
     */
    @Bind("UTI") public native String getUTI();
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIDocumentInteractionController_class/Reference/Reference.html#//apple_ref/occ/instp/UIDocumentInteractionController/UTI">@property(nonatomic,copy) NSString *UTI</a>
     * @since Available in iOS 3.2 and later.
     */
    @Bind("setUTI:") public native void setUTI(String v);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIDocumentInteractionController_class/Reference/Reference.html#//apple_ref/occ/instp/UIDocumentInteractionController/annotation">@property(nonatomic,retain) id annotation</a>
     * @since Available in iOS 3.2 and later.
     */
    @Bind("annotation") public native NSObject getAnnotation();
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIDocumentInteractionController_class/Reference/Reference.html#//apple_ref/occ/instp/UIDocumentInteractionController/annotation">@property(nonatomic,retain) id annotation</a>
     * @since Available in iOS 3.2 and later.
     */
    @Bind("setAnnotation:") public native void setAnnotation(NSObject v);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIDocumentInteractionController_class/Reference/Reference.html#//apple_ref/occ/instp/UIDocumentInteractionController/delegate">@property(nonatomic,assign) id&amp;lt;UIDocumentInteractionControllerDelegate&amp;gt; delegate</a>
     * @since Available in iOS 3.2 and later.
     */
    @Bind("delegate") public native UIDocumentInteractionControllerDelegate getDelegate();
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIDocumentInteractionController_class/Reference/Reference.html#//apple_ref/occ/instp/UIDocumentInteractionController/delegate">@property(nonatomic,assign) id&amp;lt;UIDocumentInteractionControllerDelegate&amp;gt; delegate</a>
     * @since Available in iOS 3.2 and later.
     */
    @Bind("setDelegate:") public native void setDelegate(UIDocumentInteractionControllerDelegate v);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIDocumentInteractionController_class/Reference/Reference.html#//apple_ref/occ/instp/UIDocumentInteractionController/gestureRecognizers">@property(nonatomic,readonly) NSArray *gestureRecognizers</a>
     * @since Available in iOS 3.2 and later.
     */
    @Bind("gestureRecognizers") public native NSArray getGestureRecognizers();
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIDocumentInteractionController_class/Reference/Reference.html#//apple_ref/occ/instp/UIDocumentInteractionController/icons">@property(nonatomic,readonly) NSArray *icons</a>
     * @since Available in iOS 3.2 and later.
     */
    @Bind("icons") public native NSArray getIcons();
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIDocumentInteractionController_class/Reference/Reference.html#//apple_ref/occ/instp/UIDocumentInteractionController/name">@property(nonatomic,copy) NSString *name</a>
     * @since Available in iOS 3.2 and later.
     */
    @Bind("name") public native String getName();
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIDocumentInteractionController_class/Reference/Reference.html#//apple_ref/occ/instp/UIDocumentInteractionController/name">@property(nonatomic,copy) NSString *name</a>
     * @since Available in iOS 3.2 and later.
     */
    @Bind("setName:") public native void setName(String v);
    /*</properties>*/
    /*<methods>*/
    
    private static final Selector interactionControllerWithURL$ = Selector.register("interactionControllerWithURL:");
    @Bridge(symbol = "objc_msgSend") private native static UIDocumentInteractionController objc_fromURL(ObjCClass __self__, Selector __cmd__, NSURL url);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIDocumentInteractionController_class/Reference/Reference.html#//apple_ref/occ/clm/UIDocumentInteractionController/interactionControllerWithURL:">+ (UIDocumentInteractionController *)interactionControllerWithURL:(NSURL *)url</a>
     * @since Available in iOS 3.2 and later.
     */
    public static UIDocumentInteractionController fromURL(NSURL url) {
        return objc_fromURL(objCClass, interactionControllerWithURL$, url);
    }
    
    private static final Selector dismissMenuAnimated$ = Selector.register("dismissMenuAnimated:");
    @Bridge(symbol = "objc_msgSend") private native static void objc_dismissMenu(UIDocumentInteractionController __self__, Selector __cmd__, boolean animated);
    @Bridge(symbol = "objc_msgSendSuper") private native static void objc_dismissMenuSuper(ObjCSuper __super__, UIDocumentInteractionController __self__, Selector __cmd__, boolean animated);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIDocumentInteractionController_class/Reference/Reference.html#//apple_ref/occ/instm/UIDocumentInteractionController/dismissMenuAnimated:">- (void)dismissMenuAnimated:(BOOL)animated</a>
     * @since Available in iOS 3.2 and later.
     */
    public void dismissMenu(boolean animated) {
        if (customClass) { objc_dismissMenuSuper(getSuper(), this, dismissMenuAnimated$, animated); } else { objc_dismissMenu(this, dismissMenuAnimated$, animated); }
    }
    
    private static final Selector dismissPreviewAnimated$ = Selector.register("dismissPreviewAnimated:");
    @Bridge(symbol = "objc_msgSend") private native static void objc_dismissPreview(UIDocumentInteractionController __self__, Selector __cmd__, boolean animated);
    @Bridge(symbol = "objc_msgSendSuper") private native static void objc_dismissPreviewSuper(ObjCSuper __super__, UIDocumentInteractionController __self__, Selector __cmd__, boolean animated);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIDocumentInteractionController_class/Reference/Reference.html#//apple_ref/occ/instm/UIDocumentInteractionController/dismissPreviewAnimated:">- (void)dismissPreviewAnimated:(BOOL)animated</a>
     * @since Available in iOS 3.2 and later.
     */
    public void dismissPreview(boolean animated) {
        if (customClass) { objc_dismissPreviewSuper(getSuper(), this, dismissPreviewAnimated$, animated); } else { objc_dismissPreview(this, dismissPreviewAnimated$, animated); }
    }
    
    private static final Selector presentOpenInMenuFromBarButtonItem$animated$ = Selector.register("presentOpenInMenuFromBarButtonItem:animated:");
    @Bridge(symbol = "objc_msgSend") private native static boolean objc_presentOpenInMenu(UIDocumentInteractionController __self__, Selector __cmd__, UIBarButtonItem item, boolean animated);
    @Bridge(symbol = "objc_msgSendSuper") private native static boolean objc_presentOpenInMenuSuper(ObjCSuper __super__, UIDocumentInteractionController __self__, Selector __cmd__, UIBarButtonItem item, boolean animated);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIDocumentInteractionController_class/Reference/Reference.html#//apple_ref/occ/instm/UIDocumentInteractionController/presentOpenInMenuFromBarButtonItem:animated:">- (BOOL)presentOpenInMenuFromBarButtonItem:(UIBarButtonItem *)item animated:(BOOL)animated</a>
     * @since Available in iOS 3.2 and later.
     */
    public boolean presentOpenInMenu(UIBarButtonItem item, boolean animated) {
        if (customClass) { return objc_presentOpenInMenuSuper(getSuper(), this, presentOpenInMenuFromBarButtonItem$animated$, item, animated); } else { return objc_presentOpenInMenu(this, presentOpenInMenuFromBarButtonItem$animated$, item, animated); }
    }
    
    private static final Selector presentOpenInMenuFromRect$inView$animated$ = Selector.register("presentOpenInMenuFromRect:inView:animated:");
    @Bridge(symbol = "objc_msgSend") private native static boolean objc_presentOpenInMenu(UIDocumentInteractionController __self__, Selector __cmd__, CGRect rect, UIView view, boolean animated);
    @Bridge(symbol = "objc_msgSendSuper") private native static boolean objc_presentOpenInMenuSuper(ObjCSuper __super__, UIDocumentInteractionController __self__, Selector __cmd__, CGRect rect, UIView view, boolean animated);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIDocumentInteractionController_class/Reference/Reference.html#//apple_ref/occ/instm/UIDocumentInteractionController/presentOpenInMenuFromRect:inView:animated:">- (BOOL)presentOpenInMenuFromRect:(CGRect)rect inView:(UIView *)view animated:(BOOL)animated</a>
     * @since Available in iOS 3.2 and later.
     */
    public boolean presentOpenInMenu(CGRect rect, UIView view, boolean animated) {
        if (customClass) { return objc_presentOpenInMenuSuper(getSuper(), this, presentOpenInMenuFromRect$inView$animated$, rect, view, animated); } else { return objc_presentOpenInMenu(this, presentOpenInMenuFromRect$inView$animated$, rect, view, animated); }
    }
    
    private static final Selector presentOptionsMenuFromBarButtonItem$animated$ = Selector.register("presentOptionsMenuFromBarButtonItem:animated:");
    @Bridge(symbol = "objc_msgSend") private native static boolean objc_presentOptionsMenu(UIDocumentInteractionController __self__, Selector __cmd__, UIBarButtonItem item, boolean animated);
    @Bridge(symbol = "objc_msgSendSuper") private native static boolean objc_presentOptionsMenuSuper(ObjCSuper __super__, UIDocumentInteractionController __self__, Selector __cmd__, UIBarButtonItem item, boolean animated);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIDocumentInteractionController_class/Reference/Reference.html#//apple_ref/occ/instm/UIDocumentInteractionController/presentOptionsMenuFromBarButtonItem:animated:">- (BOOL)presentOptionsMenuFromBarButtonItem:(UIBarButtonItem *)item animated:(BOOL)animated</a>
     * @since Available in iOS 3.2 and later.
     */
    public boolean presentOptionsMenu(UIBarButtonItem item, boolean animated) {
        if (customClass) { return objc_presentOptionsMenuSuper(getSuper(), this, presentOptionsMenuFromBarButtonItem$animated$, item, animated); } else { return objc_presentOptionsMenu(this, presentOptionsMenuFromBarButtonItem$animated$, item, animated); }
    }
    
    private static final Selector presentOptionsMenuFromRect$inView$animated$ = Selector.register("presentOptionsMenuFromRect:inView:animated:");
    @Bridge(symbol = "objc_msgSend") private native static boolean objc_presentOptionsMenu(UIDocumentInteractionController __self__, Selector __cmd__, CGRect rect, UIView view, boolean animated);
    @Bridge(symbol = "objc_msgSendSuper") private native static boolean objc_presentOptionsMenuSuper(ObjCSuper __super__, UIDocumentInteractionController __self__, Selector __cmd__, CGRect rect, UIView view, boolean animated);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIDocumentInteractionController_class/Reference/Reference.html#//apple_ref/occ/instm/UIDocumentInteractionController/presentOptionsMenuFromRect:inView:animated:">- (BOOL)presentOptionsMenuFromRect:(CGRect)rect inView:(UIView *)view animated:(BOOL)animated</a>
     * @since Available in iOS 3.2 and later.
     */
    public boolean presentOptionsMenu(CGRect rect, UIView view, boolean animated) {
        if (customClass) { return objc_presentOptionsMenuSuper(getSuper(), this, presentOptionsMenuFromRect$inView$animated$, rect, view, animated); } else { return objc_presentOptionsMenu(this, presentOptionsMenuFromRect$inView$animated$, rect, view, animated); }
    }
    
    private static final Selector presentPreviewAnimated$ = Selector.register("presentPreviewAnimated:");
    @Bridge(symbol = "objc_msgSend") private native static boolean objc_presentPreview(UIDocumentInteractionController __self__, Selector __cmd__, boolean animated);
    @Bridge(symbol = "objc_msgSendSuper") private native static boolean objc_presentPreviewSuper(ObjCSuper __super__, UIDocumentInteractionController __self__, Selector __cmd__, boolean animated);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIDocumentInteractionController_class/Reference/Reference.html#//apple_ref/occ/instm/UIDocumentInteractionController/presentPreviewAnimated:">- (BOOL)presentPreviewAnimated:(BOOL)animated</a>
     * @since Available in iOS 3.2 and later.
     */
    public boolean presentPreview(boolean animated) {
        if (customClass) { return objc_presentPreviewSuper(getSuper(), this, presentPreviewAnimated$, animated); } else { return objc_presentPreview(this, presentPreviewAnimated$, animated); }
    }
    /*</methods>*/

}
