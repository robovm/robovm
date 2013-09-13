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
 *   @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIStoryboard_Class/Reference/Reference.html">UIStoryboard Class Reference</a>
 *   @since Available in iOS 5.0 and later.
 * </div>
 */
/*<library>*/@Library("UIKit")/*</library>*/
@NativeClass public class /*<name>*/ UIStoryboard /*</name>*/ 
    extends /*<extends>*/ NSObject /*</extends>*/ 
    /*<implements>*/ /*</implements>*/ {

    static {
        ObjCRuntime.bind(/*<name>*/ UIStoryboard /*</name>*/.class);
    }

    private static final ObjCClass objCClass = ObjCClass.getByType(/*<name>*/ UIStoryboard /*</name>*/.class);

    /*<constructors>*/
    protected UIStoryboard(SkipInit skipInit) { super(skipInit); }
    public UIStoryboard() {}
    
    /*</constructors>*/
    /*<properties>*/
    
    /*</properties>*/
    /*<methods>*/
    
    private static final Selector storyboardWithName$bundle$ = Selector.register("storyboardWithName:bundle:");
    @Bridge private native static UIStoryboard objc_fromName(ObjCClass __self__, Selector __cmd__, String name, NSBundle storyboardBundleOrNil);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIStoryboard_Class/Reference/Reference.html#//apple_ref/occ/clm/UIStoryboard/storyboardWithName:bundle:">+ (UIStoryboard *)storyboardWithName:(NSString *)name bundle:(NSBundle *)storyboardBundleOrNil</a>
     * @since Available in iOS 5.0 and later.
     */
    public static UIStoryboard fromName(String name, NSBundle storyboardBundleOrNil) {
        return objc_fromName(objCClass, storyboardWithName$bundle$, name, storyboardBundleOrNil);
    }
    
    private static final Selector instantiateInitialViewController = Selector.register("instantiateInitialViewController");
    @Bridge private native static NSObject objc_instantiateInitialViewController(UIStoryboard __self__, Selector __cmd__);
    @Bridge private native static NSObject objc_instantiateInitialViewControllerSuper(ObjCSuper __super__, Selector __cmd__);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIStoryboard_Class/Reference/Reference.html#//apple_ref/occ/instm/UIStoryboard/instantiateInitialViewController">- (id)instantiateInitialViewController</a>
     * @since Available in iOS 5.0 and later.
     */
    public NSObject instantiateInitialViewController() {
        if (customClass) { return objc_instantiateInitialViewControllerSuper(getSuper(), instantiateInitialViewController); } else { return objc_instantiateInitialViewController(this, instantiateInitialViewController); }
    }
    
    private static final Selector instantiateViewControllerWithIdentifier$ = Selector.register("instantiateViewControllerWithIdentifier:");
    @Bridge private native static NSObject objc_instantiateViewController(UIStoryboard __self__, Selector __cmd__, String identifier);
    @Bridge private native static NSObject objc_instantiateViewControllerSuper(ObjCSuper __super__, Selector __cmd__, String identifier);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIStoryboard_Class/Reference/Reference.html#//apple_ref/occ/instm/UIStoryboard/instantiateViewControllerWithIdentifier:">- (id)instantiateViewControllerWithIdentifier:(NSString *)identifier</a>
     * @since Available in iOS 5.0 and later.
     */
    public NSObject instantiateViewController(String identifier) {
        if (customClass) { return objc_instantiateViewControllerSuper(getSuper(), instantiateViewControllerWithIdentifier$, identifier); } else { return objc_instantiateViewController(this, instantiateViewControllerWithIdentifier$, identifier); }
    }
    /*</methods>*/
    /*<callbacks>*/
    static class Callbacks {
        @Callback @BindSelector("instantiateInitialViewController") public static NSObject instantiateInitialViewController(UIStoryboard __self__, Selector __cmd__) { return __self__.instantiateInitialViewController(); }
        @Callback @BindSelector("instantiateViewControllerWithIdentifier:") public static NSObject instantiateViewController(UIStoryboard __self__, Selector __cmd__, String identifier) { return __self__.instantiateViewController(identifier); }
    }
    /*</callbacks>*/

}
