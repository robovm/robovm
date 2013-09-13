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
 *   @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIActivityItemProvider_Class/Reference/Reference.html">UIActivityItemProvider Class Reference</a>
 *   @since Available in iOS 6.0 and later.
 * </div>
 */
/*<library>*/@Library("UIKit")/*</library>*/
@NativeClass public class /*<name>*/ UIActivityItemProvider /*</name>*/ 
    extends /*<extends>*/ NSOperation /*</extends>*/ 
    /*<implements>*/ /*</implements>*/ {

    static {
        ObjCRuntime.bind(/*<name>*/ UIActivityItemProvider /*</name>*/.class);
    }

    private static final ObjCClass objCClass = ObjCClass.getByType(/*<name>*/ UIActivityItemProvider /*</name>*/.class);

    /*<constructors>*/
    protected UIActivityItemProvider(SkipInit skipInit) { super(skipInit); }
    public UIActivityItemProvider() {}
    
    private static final Selector initWithPlaceholderItem$ = Selector.register("initWithPlaceholderItem:");
    @Bridge private native static @Pointer long objc_initWithPlaceholderItem(UIActivityItemProvider __self__, Selector __cmd__, NSObject placeholderItem);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIActivityItemProvider_Class/Reference/Reference.html#//apple_ref/occ/instm/UIActivityItemProvider/initWithPlaceholderItem:">- (id)initWithPlaceholderItem:(id)placeholderItem</a>
     * @since Available in iOS 6.0 and later.
     */
    public UIActivityItemProvider(NSObject placeholderItem) {
        super((SkipInit) null);
        initObject(objc_initWithPlaceholderItem(this, initWithPlaceholderItem$, placeholderItem));
    }
    /*</constructors>*/
    /*<properties>*/
    
    private static final Selector activityType = Selector.register("activityType");
    @Bridge private native static String objc_getActivityType(UIActivityItemProvider __self__, Selector __cmd__);
    @Bridge private native static String objc_getActivityTypeSuper(ObjCSuper __super__, Selector __cmd__);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIActivityItemProvider_Class/Reference/Reference.html#//apple_ref/occ/instp/UIActivityItemProvider/activityType">@property(nonatomic,readonly) NSString *activityType</a>
     * @since Available in iOS 6.0 and later.
     */
    public String getActivityType() {
        if (customClass) { return objc_getActivityTypeSuper(getSuper(), activityType); } else { return objc_getActivityType(this, activityType); }
    }
    
    private static final Selector placeholderItem = Selector.register("placeholderItem");
    @Bridge private native static NSObject objc_getPlaceholderItem(UIActivityItemProvider __self__, Selector __cmd__);
    @Bridge private native static NSObject objc_getPlaceholderItemSuper(ObjCSuper __super__, Selector __cmd__);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIActivityItemProvider_Class/Reference/Reference.html#//apple_ref/occ/instp/UIActivityItemProvider/placeholderItem">@property(nonatomic,retain,readonly) id placeholderItem</a>
     * @since Available in iOS 6.0 and later.
     */
    public NSObject getPlaceholderItem() {
        if (customClass) { return objc_getPlaceholderItemSuper(getSuper(), placeholderItem); } else { return objc_getPlaceholderItem(this, placeholderItem); }
    }
    /*</properties>*/
    /*<methods>*/
    
    private static final Selector item = Selector.register("item");
    @Bridge private native static NSObject objc_getItem(UIActivityItemProvider __self__, Selector __cmd__);
    @Bridge private native static NSObject objc_getItemSuper(ObjCSuper __super__, Selector __cmd__);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIActivityItemProvider_Class/Reference/Reference.html#//apple_ref/occ/instm/UIActivityItemProvider/item">- (id)item</a>
     * @since Available in iOS 6.0 and later.
     */
    public NSObject getItem() {
        if (customClass) { return objc_getItemSuper(getSuper(), item); } else { return objc_getItem(this, item); }
    }
    /*</methods>*/
    /*<callbacks>*/
    static class Callbacks {
        @Callback @BindSelector("activityType") public static String getActivityType(UIActivityItemProvider __self__, Selector __cmd__) { return __self__.getActivityType(); }
        @Callback @BindSelector("placeholderItem") public static NSObject getPlaceholderItem(UIActivityItemProvider __self__, Selector __cmd__) { return __self__.getPlaceholderItem(); }
        @Callback @BindSelector("item") public static NSObject getItem(UIActivityItemProvider __self__, Selector __cmd__) { return __self__.getItem(); }
    }
    /*</callbacks>*/

}
