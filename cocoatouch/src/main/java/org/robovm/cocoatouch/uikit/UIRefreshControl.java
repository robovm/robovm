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
 *   @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIRefreshControl_class/Reference/Reference.html">UIRefreshControl Class Reference</a>
 *   @since Available in iOS 6.0 and later.
 * </div>
 */
/*<library>*/@Library("UIKit")/*</library>*/
@NativeClass public class /*<name>*/ UIRefreshControl /*</name>*/ 
    extends /*<extends>*/ UIControl /*</extends>*/ 
    /*<implements>*/ /*</implements>*/ {

    static {
        ObjCRuntime.bind(/*<name>*/ UIRefreshControl /*</name>*/.class);
    }

    private static final ObjCClass objCClass = ObjCClass.getByType(/*<name>*/ UIRefreshControl /*</name>*/.class);

    public UIRefreshControl(CGRect aRect) {
        super(aRect);
    }
    /*<constructors>*/
    protected UIRefreshControl(SkipInit skipInit) { super(skipInit); }
    public UIRefreshControl() {}
    
    /*</constructors>*/
    /*<properties>*/
    
    private static final Selector attributedTitle = Selector.register("attributedTitle");
    @Bridge private native static NSAttributedString objc_getAttributedTitle(UIRefreshControl __self__, Selector __cmd__);
    @Bridge private native static NSAttributedString objc_getAttributedTitleSuper(ObjCSuper __super__, Selector __cmd__);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIRefreshControl_class/Reference/Reference.html#//apple_ref/occ/instp/UIRefreshControl/attributedTitle">@property (nonatomic, retain) NSAttributedString *attributedTitle</a>
     * @since Available in iOS 6.0 and later.
     */
    public NSAttributedString getAttributedTitle() {
        if (customClass) { return objc_getAttributedTitleSuper(getSuper(), attributedTitle); } else { return objc_getAttributedTitle(this, attributedTitle); }
    }
    
    private static final Selector setAttributedTitle$ = Selector.register("setAttributedTitle:");
    @Bridge private native static void objc_setAttributedTitle(UIRefreshControl __self__, Selector __cmd__, NSAttributedString attributedTitle);
    @Bridge private native static void objc_setAttributedTitleSuper(ObjCSuper __super__, Selector __cmd__, NSAttributedString attributedTitle);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIRefreshControl_class/Reference/Reference.html#//apple_ref/occ/instp/UIRefreshControl/attributedTitle">@property (nonatomic, retain) NSAttributedString *attributedTitle</a>
     * @since Available in iOS 6.0 and later.
     */
    public void setAttributedTitle(NSAttributedString attributedTitle) {
        if (customClass) { objc_setAttributedTitleSuper(getSuper(), setAttributedTitle$, attributedTitle); } else { objc_setAttributedTitle(this, setAttributedTitle$, attributedTitle); }
    }
    
    private static final Selector isRefreshing = Selector.register("isRefreshing");
    @Bridge private native static boolean objc_isRefreshing(UIRefreshControl __self__, Selector __cmd__);
    @Bridge private native static boolean objc_isRefreshingSuper(ObjCSuper __super__, Selector __cmd__);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIRefreshControl_class/Reference/Reference.html#//apple_ref/occ/instp/UIRefreshControl/refreshing">@property (nonatomic, readonly, getter=isRefreshing) BOOL refreshing</a>
     * @since Available in iOS 6.0 and later.
     */
    public boolean isRefreshing() {
        if (customClass) { return objc_isRefreshingSuper(getSuper(), isRefreshing); } else { return objc_isRefreshing(this, isRefreshing); }
    }
    
    private static final Selector tintColor = Selector.register("tintColor");
    @Bridge private native static UIColor objc_getTintColor(UIRefreshControl __self__, Selector __cmd__);
    @Bridge private native static UIColor objc_getTintColorSuper(ObjCSuper __super__, Selector __cmd__);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIRefreshControl_class/Reference/Reference.html#//apple_ref/occ/instp/UIRefreshControl/tintColor">@property (nonatomic, retain) UIColor *tintColor</a>
     * @since Available in iOS 6.0 and later.
     */
    public UIColor getTintColor() {
        if (customClass) { return objc_getTintColorSuper(getSuper(), tintColor); } else { return objc_getTintColor(this, tintColor); }
    }
    
    private static final Selector setTintColor$ = Selector.register("setTintColor:");
    @Bridge private native static void objc_setTintColor(UIRefreshControl __self__, Selector __cmd__, UIColor tintColor);
    @Bridge private native static void objc_setTintColorSuper(ObjCSuper __super__, Selector __cmd__, UIColor tintColor);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIRefreshControl_class/Reference/Reference.html#//apple_ref/occ/instp/UIRefreshControl/tintColor">@property (nonatomic, retain) UIColor *tintColor</a>
     * @since Available in iOS 6.0 and later.
     */
    public void setTintColor(UIColor tintColor) {
        if (customClass) { objc_setTintColorSuper(getSuper(), setTintColor$, tintColor); } else { objc_setTintColor(this, setTintColor$, tintColor); }
    }
    /*</properties>*/
    /*<methods>*/
    
    private static final Selector beginRefreshing = Selector.register("beginRefreshing");
    @Bridge private native static void objc_beginRefreshing(UIRefreshControl __self__, Selector __cmd__);
    @Bridge private native static void objc_beginRefreshingSuper(ObjCSuper __super__, Selector __cmd__);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIRefreshControl_class/Reference/Reference.html#//apple_ref/occ/instm/UIRefreshControl/beginRefreshing">- (void)beginRefreshing</a>
     * @since Available in iOS 6.0 and later.
     */
    public void beginRefreshing() {
        if (customClass) { objc_beginRefreshingSuper(getSuper(), beginRefreshing); } else { objc_beginRefreshing(this, beginRefreshing); }
    }
    
    private static final Selector endRefreshing = Selector.register("endRefreshing");
    @Bridge private native static void objc_endRefreshing(UIRefreshControl __self__, Selector __cmd__);
    @Bridge private native static void objc_endRefreshingSuper(ObjCSuper __super__, Selector __cmd__);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIRefreshControl_class/Reference/Reference.html#//apple_ref/occ/instm/UIRefreshControl/endRefreshing">- (void)endRefreshing</a>
     * @since Available in iOS 6.0 and later.
     */
    public void endRefreshing() {
        if (customClass) { objc_endRefreshingSuper(getSuper(), endRefreshing); } else { objc_endRefreshing(this, endRefreshing); }
    }
    /*</methods>*/
    /*<callbacks>*/
    static class Callbacks {
        @Callback @BindSelector("attributedTitle") public static NSAttributedString getAttributedTitle(UIRefreshControl __self__, Selector __cmd__) { return __self__.getAttributedTitle(); }
        @Callback @BindSelector("setAttributedTitle:") public static void setAttributedTitle(UIRefreshControl __self__, Selector __cmd__, NSAttributedString attributedTitle) { __self__.setAttributedTitle(attributedTitle); }
        @Callback @BindSelector("isRefreshing") public static boolean isRefreshing(UIRefreshControl __self__, Selector __cmd__) { return __self__.isRefreshing(); }
        @Callback @BindSelector("tintColor") public static UIColor getTintColor(UIRefreshControl __self__, Selector __cmd__) { return __self__.getTintColor(); }
        @Callback @BindSelector("setTintColor:") public static void setTintColor(UIRefreshControl __self__, Selector __cmd__, UIColor tintColor) { __self__.setTintColor(tintColor); }
        @Callback @BindSelector("beginRefreshing") public static void beginRefreshing(UIRefreshControl __self__, Selector __cmd__) { __self__.beginRefreshing(); }
        @Callback @BindSelector("endRefreshing") public static void endRefreshing(UIRefreshControl __self__, Selector __cmd__) { __self__.endRefreshing(); }
    }
    /*</callbacks>*/

}
