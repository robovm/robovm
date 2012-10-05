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
 * <div class="javadoc">
 *   @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIActivityItemProvider_Class/Reference/Reference.html">UIActivityItemProvider Class Reference</a>
 *   @since Available in iOS 6.0 and later.
 * </div>
 */
/*<library>*/@Library("UIKit")/*</library>*/
public class /*<name>*/ UIActivityItemProvider /*</name>*/ 
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
    @Bridge(symbol = "objc_msgSend") private native static NSObject objc_initWithPlaceholderItem(UIActivityItemProvider __self__, Selector __cmd__, NSObject placeholderItem);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIActivityItemProvider_Class/Reference/Reference.html#//apple_ref/occ/instm/UIActivityItemProvider/initWithPlaceholderItem:">- (id)initWithPlaceholderItem:(id)placeholderItem</a>
     * @since Available in iOS 6.0 and later.
     */
    public UIActivityItemProvider(NSObject placeholderItem) {
        super((SkipInit) null);
        objc_initWithPlaceholderItem(this, initWithPlaceholderItem$, placeholderItem);
    }
    /*</constructors>*/
    /*<properties>*/
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIActivityItemProvider_Class/Reference/Reference.html#//apple_ref/occ/instp/UIActivityItemProvider/activityType">@property(nonatomic,readonly) NSString *activityType</a>
     * @since Available in iOS 6.0 and later.
     */
    @Bind("activityType") public native String getActivityType();
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIActivityItemProvider_Class/Reference/Reference.html#//apple_ref/occ/instp/UIActivityItemProvider/placeholderItem">@property(nonatomic,retain,readonly) id placeholderItem</a>
     * @since Available in iOS 6.0 and later.
     */
    @Bind("placeholderItem") public native NSObject getPlaceholderItem();
    /*</properties>*/
    /*<methods>*/
    
    private static final Selector item = Selector.register("item");
    @Bridge(symbol = "objc_msgSend") private native static NSObject objc_getItem(UIActivityItemProvider __self__, Selector __cmd__);
    @Bridge(symbol = "objc_msgSendSuper") private native static NSObject objc_getItemSuper(ObjCSuper __super__, UIActivityItemProvider __self__, Selector __cmd__);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIActivityItemProvider_Class/Reference/Reference.html#//apple_ref/occ/instm/UIActivityItemProvider/item">- (id)item</a>
     * @since Available in iOS 6.0 and later.
     */
    public NSObject getItem() {
        if (customClass) { return objc_getItemSuper(getSuper(), this, item); } else { return objc_getItem(this, item); }
    }
    /*</methods>*/

}
