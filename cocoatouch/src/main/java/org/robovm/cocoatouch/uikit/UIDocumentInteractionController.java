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

    /*<constructors>*/
    public UIDocumentInteractionController() {}
    
    /*</constructors>*/
    /*<properties>*/
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIDocumentInteractionController_class/Reference/Reference.html#//apple_ref/occ/instp/UIDocumentInteractionController/URL">@property(nonatomic,retain) NSURL *URL</a>
     * @since Available in iOS 3.2 and later.
     */
    @Bind("URL") public native @Type("NSURL *") NSURL getURL();
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIDocumentInteractionController_class/Reference/Reference.html#//apple_ref/occ/instp/UIDocumentInteractionController/URL">@property(nonatomic,retain) NSURL *URL</a>
     * @since Available in iOS 3.2 and later.
     */
    @Bind("setURL:") public native void setURL(@Type("NSURL *") NSURL v);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIDocumentInteractionController_class/Reference/Reference.html#//apple_ref/occ/instp/UIDocumentInteractionController/UTI">@property(nonatomic,copy) NSString *UTI</a>
     * @since Available in iOS 3.2 and later.
     */
    @Bind("UTI") public native @Type("NSString *") String getUTI();
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIDocumentInteractionController_class/Reference/Reference.html#//apple_ref/occ/instp/UIDocumentInteractionController/UTI">@property(nonatomic,copy) NSString *UTI</a>
     * @since Available in iOS 3.2 and later.
     */
    @Bind("setUTI:") public native void setUTI(@Type("NSString *") String v);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIDocumentInteractionController_class/Reference/Reference.html#//apple_ref/occ/instp/UIDocumentInteractionController/annotation">@property(nonatomic,retain) id annotation</a>
     * @since Available in iOS 3.2 and later.
     */
    @Bind("annotation") public native @Type("id") NSObject getAnnotation();
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIDocumentInteractionController_class/Reference/Reference.html#//apple_ref/occ/instp/UIDocumentInteractionController/annotation">@property(nonatomic,retain) id annotation</a>
     * @since Available in iOS 3.2 and later.
     */
    @Bind("setAnnotation:") public native void setAnnotation(@Type("id") NSObject v);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIDocumentInteractionController_class/Reference/Reference.html#//apple_ref/occ/instp/UIDocumentInteractionController/delegate">@property(nonatomic,assign) id&amp;lt;UIDocumentInteractionControllerDelegate&amp;gt; delegate</a>
     * @since Available in iOS 3.2 and later.
     */
    @Bind("delegate") public native @Type("id<UIDocumentInteractionControllerDelegate>") UIDocumentInteractionControllerDelegate getDelegate();
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIDocumentInteractionController_class/Reference/Reference.html#//apple_ref/occ/instp/UIDocumentInteractionController/delegate">@property(nonatomic,assign) id&amp;lt;UIDocumentInteractionControllerDelegate&amp;gt; delegate</a>
     * @since Available in iOS 3.2 and later.
     */
    @Bind("setDelegate:") public native void setDelegate(@Type("id<UIDocumentInteractionControllerDelegate>") UIDocumentInteractionControllerDelegate v);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIDocumentInteractionController_class/Reference/Reference.html#//apple_ref/occ/instp/UIDocumentInteractionController/gestureRecognizers">@property(nonatomic,readonly) NSArray *gestureRecognizers</a>
     * @since Available in iOS 3.2 and later.
     */
    @Bind("gestureRecognizers") public native @Type("NSArray *") NSArray getGestureRecognizers();
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIDocumentInteractionController_class/Reference/Reference.html#//apple_ref/occ/instp/UIDocumentInteractionController/icons">@property(nonatomic,readonly) NSArray *icons</a>
     * @since Available in iOS 3.2 and later.
     */
    @Bind("icons") public native @Type("NSArray *") NSArray getIcons();
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIDocumentInteractionController_class/Reference/Reference.html#//apple_ref/occ/instp/UIDocumentInteractionController/name">@property(nonatomic,copy) NSString *name</a>
     * @since Available in iOS 3.2 and later.
     */
    @Bind("name") public native @Type("NSString *") String getName();
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIDocumentInteractionController_class/Reference/Reference.html#//apple_ref/occ/instp/UIDocumentInteractionController/name">@property(nonatomic,copy) NSString *name</a>
     * @since Available in iOS 3.2 and later.
     */
    @Bind("setName:") public native void setName(@Type("NSString *") String v);
    /*</properties>*/
    /*<methods>*/
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIDocumentInteractionController_class/Reference/Reference.html#//apple_ref/occ/clm/UIDocumentInteractionController/interactionControllerWithURL:">+ (UIDocumentInteractionController *)interactionControllerWithURL:(NSURL *)url</a>
     * @since Available in iOS 3.2 and later.
     */
    @Bind("interactionControllerWithURL:") public native static @Type("UIDocumentInteractionController *") UIDocumentInteractionController fromURL(@Type("NSURL *") NSURL url);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIDocumentInteractionController_class/Reference/Reference.html#//apple_ref/occ/instm/UIDocumentInteractionController/dismissMenuAnimated:">- (void)dismissMenuAnimated:(BOOL)animated</a>
     * @since Available in iOS 3.2 and later.
     */
    @Bind("dismissMenuAnimated:") public native @Type("void") void dismissMenu(@Type("BOOL") boolean animated);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIDocumentInteractionController_class/Reference/Reference.html#//apple_ref/occ/instm/UIDocumentInteractionController/dismissPreviewAnimated:">- (void)dismissPreviewAnimated:(BOOL)animated</a>
     * @since Available in iOS 3.2 and later.
     */
    @Bind("dismissPreviewAnimated:") public native @Type("void") void dismissPreview(@Type("BOOL") boolean animated);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIDocumentInteractionController_class/Reference/Reference.html#//apple_ref/occ/instm/UIDocumentInteractionController/presentOpenInMenuFromBarButtonItem:animated:">- (BOOL)presentOpenInMenuFromBarButtonItem:(UIBarButtonItem *)item animated:(BOOL)animated</a>
     * @since Available in iOS 3.2 and later.
     */
    @Bind("presentOpenInMenuFromBarButtonItem:animated:") public native @Type("BOOL") boolean presentOpenInMenu(@Type("UIBarButtonItem *") UIBarButtonItem item, @Type("BOOL") boolean animated);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIDocumentInteractionController_class/Reference/Reference.html#//apple_ref/occ/instm/UIDocumentInteractionController/presentOpenInMenuFromRect:inView:animated:">- (BOOL)presentOpenInMenuFromRect:(CGRect)rect inView:(UIView *)view animated:(BOOL)animated</a>
     * @since Available in iOS 3.2 and later.
     */
    @Bind("presentOpenInMenuFromRect:inView:animated:") public native @Type("BOOL") boolean presentOpenInMenu(@Type("CGRect") CGRect rect, @Type("UIView *") UIView view, @Type("BOOL") boolean animated);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIDocumentInteractionController_class/Reference/Reference.html#//apple_ref/occ/instm/UIDocumentInteractionController/presentOptionsMenuFromBarButtonItem:animated:">- (BOOL)presentOptionsMenuFromBarButtonItem:(UIBarButtonItem *)item animated:(BOOL)animated</a>
     * @since Available in iOS 3.2 and later.
     */
    @Bind("presentOptionsMenuFromBarButtonItem:animated:") public native @Type("BOOL") boolean presentOptionsMenu(@Type("UIBarButtonItem *") UIBarButtonItem item, @Type("BOOL") boolean animated);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIDocumentInteractionController_class/Reference/Reference.html#//apple_ref/occ/instm/UIDocumentInteractionController/presentOptionsMenuFromRect:inView:animated:">- (BOOL)presentOptionsMenuFromRect:(CGRect)rect inView:(UIView *)view animated:(BOOL)animated</a>
     * @since Available in iOS 3.2 and later.
     */
    @Bind("presentOptionsMenuFromRect:inView:animated:") public native @Type("BOOL") boolean presentOptionsMenu(@Type("CGRect") CGRect rect, @Type("UIView *") UIView view, @Type("BOOL") boolean animated);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIDocumentInteractionController_class/Reference/Reference.html#//apple_ref/occ/instm/UIDocumentInteractionController/presentPreviewAnimated:">- (BOOL)presentPreviewAnimated:(BOOL)animated</a>
     * @since Available in iOS 3.2 and later.
     */
    @Bind("presentPreviewAnimated:") public native @Type("BOOL") boolean presentPreview(@Type("BOOL") boolean animated);
    /*</methods>*/

}
