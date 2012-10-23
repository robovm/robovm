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
import org.robovm.objc.block.*;
import org.robovm.rt.bro.annotation.*;
import org.robovm.rt.bro.ptr.*;
/*</imports>*/

/**
 *
 * <div class="javadoc">
 *   @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIActivityViewController_Class/Reference/Reference.html">UIActivityViewController Class Reference</a>
 *   @since Available in iOS 6.0 and later.
 * </div>
 */
/*<library>*/@Library("UIKit")/*</library>*/
public class /*<name>*/ UIActivityViewController /*</name>*/ 
    extends /*<extends>*/ UIViewController /*</extends>*/ 
    /*<implements>*/ /*</implements>*/ {

    static {
        ObjCRuntime.bind(/*<name>*/ UIActivityViewController /*</name>*/.class);
    }

    private static final ObjCClass objCClass = ObjCClass.getByType(/*<name>*/ UIActivityViewController /*</name>*/.class);

    /*<constructors>*/
    protected UIActivityViewController(SkipInit skipInit) { super(skipInit); }
    public UIActivityViewController() {}
    
    private static final Selector initWithActivityItems$applicationActivities$ = Selector.register("initWithActivityItems:applicationActivities:");
    @Bridge(symbol = "objc_msgSend") private native static NSObject objc_initWithActivityItems(UIActivityViewController __self__, Selector __cmd__, NSArray activityItems, NSArray applicationActivities);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIActivityViewController_Class/Reference/Reference.html#//apple_ref/occ/instm/UIActivityViewController/initWithActivityItems:applicationActivities:">- (id)initWithActivityItems:(NSArray *)activityItems applicationActivities:(NSArray *)applicationActivities</a>
     * @since Available in iOS 6.0 and later.
     */
    public UIActivityViewController(NSArray activityItems, NSArray applicationActivities) {
        super((SkipInit) null);
        objc_initWithActivityItems(this, initWithActivityItems$applicationActivities$, activityItems, applicationActivities);
    }
    /*</constructors>*/
    /*<properties>*/
    
    private static final Selector excludedActivityTypes = Selector.register("excludedActivityTypes");
    @Bridge(symbol = "objc_msgSend") private native static NSArray objc_getExcludedActivityTypes(UIActivityViewController __self__, Selector __cmd__);
    @Bridge(symbol = "objc_msgSendSuper") private native static NSArray objc_getExcludedActivityTypesSuper(ObjCSuper __super__, UIActivityViewController __self__, Selector __cmd__);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIActivityViewController_Class/Reference/Reference.html#//apple_ref/occ/instp/UIActivityViewController/excludedActivityTypes">@property(nonatomic,copy) NSArray *excludedActivityTypes</a>
     * @since Available in iOS 6.0 and later.
     */
    public NSArray getExcludedActivityTypes() {
        if (customClass) { return objc_getExcludedActivityTypesSuper(getSuper(), this, excludedActivityTypes); } else { return objc_getExcludedActivityTypes(this, excludedActivityTypes); }
    }
    
    private static final Selector setExcludedActivityTypes$ = Selector.register("setExcludedActivityTypes:");
    @Bridge(symbol = "objc_msgSend") private native static void objc_setExcludedActivityTypes(UIActivityViewController __self__, Selector __cmd__, NSArray excludedActivityTypes);
    @Bridge(symbol = "objc_msgSendSuper") private native static void objc_setExcludedActivityTypesSuper(ObjCSuper __super__, UIActivityViewController __self__, Selector __cmd__, NSArray excludedActivityTypes);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIActivityViewController_Class/Reference/Reference.html#//apple_ref/occ/instp/UIActivityViewController/excludedActivityTypes">@property(nonatomic,copy) NSArray *excludedActivityTypes</a>
     * @since Available in iOS 6.0 and later.
     */
    public void setExcludedActivityTypes(NSArray excludedActivityTypes) {
        if (customClass) { objc_setExcludedActivityTypesSuper(getSuper(), this, setExcludedActivityTypes$, excludedActivityTypes); } else { objc_setExcludedActivityTypes(this, setExcludedActivityTypes$, excludedActivityTypes); }
    }
    /*</properties>*/
    /*<methods>*/
    
    /*</methods>*/

}
