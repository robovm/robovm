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
    
    private static final Selector URL = Selector.register("URL");
    @Bridge(symbol = "objc_msgSend") private native static NSURL objc_getURL(UIDocumentInteractionController __self__, Selector __cmd__);
    @Bridge(symbol = "objc_msgSendSuper") private native static NSURL objc_getURLSuper(ObjCSuper __super__, UIDocumentInteractionController __self__, Selector __cmd__);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIDocumentInteractionController_class/Reference/Reference.html#//apple_ref/occ/instp/UIDocumentInteractionController/URL">@property(nonatomic,retain) NSURL *URL</a>
     * @since Available in iOS 3.2 and later.
     */
    public NSURL getURL() {
        if (customClass) { return objc_getURLSuper(getSuper(), this, URL); } else { return objc_getURL(this, URL); }
    }
    
    private static final Selector setURL$ = Selector.register("setURL:");
    @Bridge(symbol = "objc_msgSend") private native static void objc_setURL(UIDocumentInteractionController __self__, Selector __cmd__, NSURL URL);
    @Bridge(symbol = "objc_msgSendSuper") private native static void objc_setURLSuper(ObjCSuper __super__, UIDocumentInteractionController __self__, Selector __cmd__, NSURL URL);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIDocumentInteractionController_class/Reference/Reference.html#//apple_ref/occ/instp/UIDocumentInteractionController/URL">@property(nonatomic,retain) NSURL *URL</a>
     * @since Available in iOS 3.2 and later.
     */
    public void setURL(NSURL URL) {
        if (customClass) { objc_setURLSuper(getSuper(), this, setURL$, URL); } else { objc_setURL(this, setURL$, URL); }
    }
    
    private static final Selector UTI = Selector.register("UTI");
    @Bridge(symbol = "objc_msgSend") private native static String objc_getUTI(UIDocumentInteractionController __self__, Selector __cmd__);
    @Bridge(symbol = "objc_msgSendSuper") private native static String objc_getUTISuper(ObjCSuper __super__, UIDocumentInteractionController __self__, Selector __cmd__);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIDocumentInteractionController_class/Reference/Reference.html#//apple_ref/occ/instp/UIDocumentInteractionController/UTI">@property(nonatomic,copy) NSString *UTI</a>
     * @since Available in iOS 3.2 and later.
     */
    public String getUTI() {
        if (customClass) { return objc_getUTISuper(getSuper(), this, UTI); } else { return objc_getUTI(this, UTI); }
    }
    
    private static final Selector setUTI$ = Selector.register("setUTI:");
    @Bridge(symbol = "objc_msgSend") private native static void objc_setUTI(UIDocumentInteractionController __self__, Selector __cmd__, String UTI);
    @Bridge(symbol = "objc_msgSendSuper") private native static void objc_setUTISuper(ObjCSuper __super__, UIDocumentInteractionController __self__, Selector __cmd__, String UTI);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIDocumentInteractionController_class/Reference/Reference.html#//apple_ref/occ/instp/UIDocumentInteractionController/UTI">@property(nonatomic,copy) NSString *UTI</a>
     * @since Available in iOS 3.2 and later.
     */
    public void setUTI(String UTI) {
        if (customClass) { objc_setUTISuper(getSuper(), this, setUTI$, UTI); } else { objc_setUTI(this, setUTI$, UTI); }
    }
    
    private static final Selector annotation = Selector.register("annotation");
    @Bridge(symbol = "objc_msgSend") private native static NSObject objc_getAnnotation(UIDocumentInteractionController __self__, Selector __cmd__);
    @Bridge(symbol = "objc_msgSendSuper") private native static NSObject objc_getAnnotationSuper(ObjCSuper __super__, UIDocumentInteractionController __self__, Selector __cmd__);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIDocumentInteractionController_class/Reference/Reference.html#//apple_ref/occ/instp/UIDocumentInteractionController/annotation">@property(nonatomic,retain) id annotation</a>
     * @since Available in iOS 3.2 and later.
     */
    public NSObject getAnnotation() {
        if (customClass) { return objc_getAnnotationSuper(getSuper(), this, annotation); } else { return objc_getAnnotation(this, annotation); }
    }
    
    private static final Selector setAnnotation$ = Selector.register("setAnnotation:");
    @Bridge(symbol = "objc_msgSend") private native static void objc_setAnnotation(UIDocumentInteractionController __self__, Selector __cmd__, NSObject annotation);
    @Bridge(symbol = "objc_msgSendSuper") private native static void objc_setAnnotationSuper(ObjCSuper __super__, UIDocumentInteractionController __self__, Selector __cmd__, NSObject annotation);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIDocumentInteractionController_class/Reference/Reference.html#//apple_ref/occ/instp/UIDocumentInteractionController/annotation">@property(nonatomic,retain) id annotation</a>
     * @since Available in iOS 3.2 and later.
     */
    public void setAnnotation(NSObject annotation) {
        if (customClass) { objc_setAnnotationSuper(getSuper(), this, setAnnotation$, annotation); } else { objc_setAnnotation(this, setAnnotation$, annotation); }
    }
    
    private static final Selector delegate = Selector.register("delegate");
    @Bridge(symbol = "objc_msgSend") private native static UIDocumentInteractionControllerDelegate objc_getDelegate(UIDocumentInteractionController __self__, Selector __cmd__);
    @Bridge(symbol = "objc_msgSendSuper") private native static UIDocumentInteractionControllerDelegate objc_getDelegateSuper(ObjCSuper __super__, UIDocumentInteractionController __self__, Selector __cmd__);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIDocumentInteractionController_class/Reference/Reference.html#//apple_ref/occ/instp/UIDocumentInteractionController/delegate">@property(nonatomic,assign) id&amp;lt;UIDocumentInteractionControllerDelegate&amp;gt; delegate</a>
     * @since Available in iOS 3.2 and later.
     */
    public UIDocumentInteractionControllerDelegate getDelegate() {
        if (customClass) { return objc_getDelegateSuper(getSuper(), this, delegate); } else { return objc_getDelegate(this, delegate); }
    }
    
    private static final Selector setDelegate$ = Selector.register("setDelegate:");
    @Bridge(symbol = "objc_msgSend") private native static void objc_setDelegate(UIDocumentInteractionController __self__, Selector __cmd__, UIDocumentInteractionControllerDelegate delegate);
    @Bridge(symbol = "objc_msgSendSuper") private native static void objc_setDelegateSuper(ObjCSuper __super__, UIDocumentInteractionController __self__, Selector __cmd__, UIDocumentInteractionControllerDelegate delegate);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIDocumentInteractionController_class/Reference/Reference.html#//apple_ref/occ/instp/UIDocumentInteractionController/delegate">@property(nonatomic,assign) id&amp;lt;UIDocumentInteractionControllerDelegate&amp;gt; delegate</a>
     * @since Available in iOS 3.2 and later.
     */
    public void setDelegate(UIDocumentInteractionControllerDelegate delegate) {
        if (customClass) { objc_setDelegateSuper(getSuper(), this, setDelegate$, delegate); } else { objc_setDelegate(this, setDelegate$, delegate); }
    }
    
    private static final Selector gestureRecognizers = Selector.register("gestureRecognizers");
    @Bridge(symbol = "objc_msgSend") private native static NSArray objc_getGestureRecognizers(UIDocumentInteractionController __self__, Selector __cmd__);
    @Bridge(symbol = "objc_msgSendSuper") private native static NSArray objc_getGestureRecognizersSuper(ObjCSuper __super__, UIDocumentInteractionController __self__, Selector __cmd__);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIDocumentInteractionController_class/Reference/Reference.html#//apple_ref/occ/instp/UIDocumentInteractionController/gestureRecognizers">@property(nonatomic,readonly) NSArray *gestureRecognizers</a>
     * @since Available in iOS 3.2 and later.
     */
    public NSArray getGestureRecognizers() {
        if (customClass) { return objc_getGestureRecognizersSuper(getSuper(), this, gestureRecognizers); } else { return objc_getGestureRecognizers(this, gestureRecognizers); }
    }
    
    private static final Selector icons = Selector.register("icons");
    @Bridge(symbol = "objc_msgSend") private native static NSArray objc_getIcons(UIDocumentInteractionController __self__, Selector __cmd__);
    @Bridge(symbol = "objc_msgSendSuper") private native static NSArray objc_getIconsSuper(ObjCSuper __super__, UIDocumentInteractionController __self__, Selector __cmd__);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIDocumentInteractionController_class/Reference/Reference.html#//apple_ref/occ/instp/UIDocumentInteractionController/icons">@property(nonatomic,readonly) NSArray *icons</a>
     * @since Available in iOS 3.2 and later.
     */
    public NSArray getIcons() {
        if (customClass) { return objc_getIconsSuper(getSuper(), this, icons); } else { return objc_getIcons(this, icons); }
    }
    
    private static final Selector name = Selector.register("name");
    @Bridge(symbol = "objc_msgSend") private native static String objc_getName(UIDocumentInteractionController __self__, Selector __cmd__);
    @Bridge(symbol = "objc_msgSendSuper") private native static String objc_getNameSuper(ObjCSuper __super__, UIDocumentInteractionController __self__, Selector __cmd__);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIDocumentInteractionController_class/Reference/Reference.html#//apple_ref/occ/instp/UIDocumentInteractionController/name">@property(nonatomic,copy) NSString *name</a>
     * @since Available in iOS 3.2 and later.
     */
    public String getName() {
        if (customClass) { return objc_getNameSuper(getSuper(), this, name); } else { return objc_getName(this, name); }
    }
    
    private static final Selector setName$ = Selector.register("setName:");
    @Bridge(symbol = "objc_msgSend") private native static void objc_setName(UIDocumentInteractionController __self__, Selector __cmd__, String name);
    @Bridge(symbol = "objc_msgSendSuper") private native static void objc_setNameSuper(ObjCSuper __super__, UIDocumentInteractionController __self__, Selector __cmd__, String name);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIDocumentInteractionController_class/Reference/Reference.html#//apple_ref/occ/instp/UIDocumentInteractionController/name">@property(nonatomic,copy) NSString *name</a>
     * @since Available in iOS 3.2 and later.
     */
    public void setName(String name) {
        if (customClass) { objc_setNameSuper(getSuper(), this, setName$, name); } else { objc_setName(this, setName$, name); }
    }
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
    /*<callbacks>*/
    static class Callbacks {
        @Callback @BindSelector("dismissMenuAnimated:") public static void dismissMenu(UIDocumentInteractionController __self__, Selector __cmd__, boolean animated) { __self__.dismissMenu(animated); }
        @Callback @BindSelector("dismissPreviewAnimated:") public static void dismissPreview(UIDocumentInteractionController __self__, Selector __cmd__, boolean animated) { __self__.dismissPreview(animated); }
        @Callback @BindSelector("presentOpenInMenuFromBarButtonItem:animated:") public static boolean presentOpenInMenu(UIDocumentInteractionController __self__, Selector __cmd__, UIBarButtonItem item, boolean animated) { return __self__.presentOpenInMenu(item, animated); }
        @Callback @BindSelector("presentOpenInMenuFromRect:inView:animated:") public static boolean presentOpenInMenu(UIDocumentInteractionController __self__, Selector __cmd__, CGRect rect, UIView view, boolean animated) { return __self__.presentOpenInMenu(rect, view, animated); }
        @Callback @BindSelector("presentOptionsMenuFromBarButtonItem:animated:") public static boolean presentOptionsMenu(UIDocumentInteractionController __self__, Selector __cmd__, UIBarButtonItem item, boolean animated) { return __self__.presentOptionsMenu(item, animated); }
        @Callback @BindSelector("presentOptionsMenuFromRect:inView:animated:") public static boolean presentOptionsMenu(UIDocumentInteractionController __self__, Selector __cmd__, CGRect rect, UIView view, boolean animated) { return __self__.presentOptionsMenu(rect, view, animated); }
        @Callback @BindSelector("presentPreviewAnimated:") public static boolean presentPreview(UIDocumentInteractionController __self__, Selector __cmd__, boolean animated) { return __self__.presentPreview(animated); }
    }
    /*</callbacks>*/

}
