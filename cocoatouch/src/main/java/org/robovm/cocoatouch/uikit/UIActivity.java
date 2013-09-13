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
 * <div class="javadoc">
 *   @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIActivity_Class/Reference/Reference.html">UIActivity Class Reference</a>
 *   @since Available in iOS 6.0 and later.
 * </div>
 */
/*<library>*/@Library("UIKit")/*</library>*/
@NativeClass public class /*<name>*/ UIActivity /*</name>*/ 
    extends /*<extends>*/ NSObject /*</extends>*/ 
    /*<implements>*/ /*</implements>*/ {

    static {
        ObjCRuntime.bind(/*<name>*/ UIActivity /*</name>*/.class);
    }

    private static final ObjCClass objCClass = ObjCClass.getByType(/*<name>*/ UIActivity /*</name>*/.class);

    /*<constructors>*/
    protected UIActivity(SkipInit skipInit) { super(skipInit); }
    public UIActivity() {}
    
    /*</constructors>*/
    /*<properties>*/
    
    /*</properties>*/
    /*<methods>*/
    
    private static final Selector canPerformWithActivityItems$ = Selector.register("canPerformWithActivityItems:");
    @Bridge private native static boolean objc_canPerform(UIActivity __self__, Selector __cmd__, NSArray activityItems);
    @Bridge private native static boolean objc_canPerformSuper(ObjCSuper __super__, Selector __cmd__, NSArray activityItems);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIActivity_Class/Reference/Reference.html#//apple_ref/occ/instm/UIActivity/canPerformWithActivityItems:">- (BOOL)canPerformWithActivityItems:(NSArray *)activityItems</a>
     * @since Available in iOS 6.0 and later.
     */
    public boolean canPerform(NSArray activityItems) {
        if (customClass) { return objc_canPerformSuper(getSuper(), canPerformWithActivityItems$, activityItems); } else { return objc_canPerform(this, canPerformWithActivityItems$, activityItems); }
    }
    
    private static final Selector activityDidFinish$ = Selector.register("activityDidFinish:");
    @Bridge private native static void objc_didFinish(UIActivity __self__, Selector __cmd__, boolean completed);
    @Bridge private native static void objc_didFinishSuper(ObjCSuper __super__, Selector __cmd__, boolean completed);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIActivity_Class/Reference/Reference.html#//apple_ref/occ/instm/UIActivity/activityDidFinish:">- (void)activityDidFinish:(BOOL)completed</a>
     * @since Available in iOS 6.0 and later.
     */
    public void didFinish(boolean completed) {
        if (customClass) { objc_didFinishSuper(getSuper(), activityDidFinish$, completed); } else { objc_didFinish(this, activityDidFinish$, completed); }
    }
    
    private static final Selector activityImage = Selector.register("activityImage");
    @Bridge private native static UIImage objc_getImage(UIActivity __self__, Selector __cmd__);
    @Bridge private native static UIImage objc_getImageSuper(ObjCSuper __super__, Selector __cmd__);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIActivity_Class/Reference/Reference.html#//apple_ref/occ/instm/UIActivity/activityImage">- (UIImage *)activityImage</a>
     * @since Available in iOS 6.0 and later.
     */
    public UIImage getImage() {
        if (customClass) { return objc_getImageSuper(getSuper(), activityImage); } else { return objc_getImage(this, activityImage); }
    }
    
    private static final Selector activityTitle = Selector.register("activityTitle");
    @Bridge private native static String objc_getTitle(UIActivity __self__, Selector __cmd__);
    @Bridge private native static String objc_getTitleSuper(ObjCSuper __super__, Selector __cmd__);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIActivity_Class/Reference/Reference.html#//apple_ref/occ/instm/UIActivity/activityTitle">- (NSString *)activityTitle</a>
     * @since Available in iOS 6.0 and later.
     */
    public String getTitle() {
        if (customClass) { return objc_getTitleSuper(getSuper(), activityTitle); } else { return objc_getTitle(this, activityTitle); }
    }
    
    private static final Selector activityType = Selector.register("activityType");
    @Bridge private native static String objc_getType(UIActivity __self__, Selector __cmd__);
    @Bridge private native static String objc_getTypeSuper(ObjCSuper __super__, Selector __cmd__);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIActivity_Class/Reference/Reference.html#//apple_ref/occ/instm/UIActivity/activityType">- (NSString *)activityType</a>
     * @since Available in iOS 6.0 and later.
     */
    public String getType() {
        if (customClass) { return objc_getTypeSuper(getSuper(), activityType); } else { return objc_getType(this, activityType); }
    }
    
    private static final Selector activityViewController = Selector.register("activityViewController");
    @Bridge private native static UIViewController objc_getViewController(UIActivity __self__, Selector __cmd__);
    @Bridge private native static UIViewController objc_getViewControllerSuper(ObjCSuper __super__, Selector __cmd__);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIActivity_Class/Reference/Reference.html#//apple_ref/occ/instm/UIActivity/activityViewController">- (UIViewController *)activityViewController</a>
     * @since Available in iOS 6.0 and later.
     */
    public UIViewController getViewController() {
        if (customClass) { return objc_getViewControllerSuper(getSuper(), activityViewController); } else { return objc_getViewController(this, activityViewController); }
    }
    
    private static final Selector performActivity = Selector.register("performActivity");
    @Bridge private native static void objc_perform(UIActivity __self__, Selector __cmd__);
    @Bridge private native static void objc_performSuper(ObjCSuper __super__, Selector __cmd__);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIActivity_Class/Reference/Reference.html#//apple_ref/occ/instm/UIActivity/performActivity">- (void)performActivity</a>
     * @since Available in iOS 6.0 and later.
     */
    public void perform() {
        if (customClass) { objc_performSuper(getSuper(), performActivity); } else { objc_perform(this, performActivity); }
    }
    
    private static final Selector prepareWithActivityItems$ = Selector.register("prepareWithActivityItems:");
    @Bridge private native static void objc_prepare(UIActivity __self__, Selector __cmd__, NSArray activityItems);
    @Bridge private native static void objc_prepareSuper(ObjCSuper __super__, Selector __cmd__, NSArray activityItems);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIActivity_Class/Reference/Reference.html#//apple_ref/occ/instm/UIActivity/prepareWithActivityItems:">- (void)prepareWithActivityItems:(NSArray *)activityItems</a>
     * @since Available in iOS 6.0 and later.
     */
    public void prepare(NSArray activityItems) {
        if (customClass) { objc_prepareSuper(getSuper(), prepareWithActivityItems$, activityItems); } else { objc_prepare(this, prepareWithActivityItems$, activityItems); }
    }
    /*</methods>*/
    /*<callbacks>*/
    static class Callbacks {
        @Callback @BindSelector("canPerformWithActivityItems:") public static boolean canPerform(UIActivity __self__, Selector __cmd__, NSArray activityItems) { return __self__.canPerform(activityItems); }
        @Callback @BindSelector("activityDidFinish:") public static void didFinish(UIActivity __self__, Selector __cmd__, boolean completed) { __self__.didFinish(completed); }
        @Callback @BindSelector("activityImage") public static UIImage getImage(UIActivity __self__, Selector __cmd__) { return __self__.getImage(); }
        @Callback @BindSelector("activityTitle") public static String getTitle(UIActivity __self__, Selector __cmd__) { return __self__.getTitle(); }
        @Callback @BindSelector("activityType") public static String getType(UIActivity __self__, Selector __cmd__) { return __self__.getType(); }
        @Callback @BindSelector("activityViewController") public static UIViewController getViewController(UIActivity __self__, Selector __cmd__) { return __self__.getViewController(); }
        @Callback @BindSelector("performActivity") public static void perform(UIActivity __self__, Selector __cmd__) { __self__.perform(); }
        @Callback @BindSelector("prepareWithActivityItems:") public static void prepare(UIActivity __self__, Selector __cmd__, NSArray activityItems) { __self__.prepare(activityItems); }
    }
    /*</callbacks>*/

}
