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
 *   @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UICollectionViewCell_class/Reference/Reference.html">UICollectionViewCell Class Reference</a>
 *   @since Available in iOS 6.0 and later.
 * </div>
 */
/*<library>*/@Library("UIKit")/*</library>*/
@NativeClass public class /*<name>*/ UICollectionViewCell /*</name>*/ 
    extends /*<extends>*/ UICollectionReusableView /*</extends>*/ 
    /*<implements>*/ /*</implements>*/ {

    static {
        ObjCRuntime.bind(/*<name>*/ UICollectionViewCell /*</name>*/.class);
    }

    private static final ObjCClass objCClass = ObjCClass.getByType(/*<name>*/ UICollectionViewCell /*</name>*/.class);

    public UICollectionViewCell(CGRect aRect) {
        super(aRect);
    }
    /*<constructors>*/
    protected UICollectionViewCell(SkipInit skipInit) { super(skipInit); }
    public UICollectionViewCell() {}
    
    /*</constructors>*/
    /*<properties>*/
    
    private static final Selector backgroundView = Selector.register("backgroundView");
    @Bridge private native static UIView objc_getBackgroundView(UICollectionViewCell __self__, Selector __cmd__);
    @Bridge private native static UIView objc_getBackgroundViewSuper(ObjCSuper __super__, Selector __cmd__);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UICollectionViewCell_class/Reference/Reference.html#//apple_ref/occ/instp/UICollectionViewCell/backgroundView">@property (nonatomic, retain) UIView *backgroundView</a>
     * @since Available in iOS 6.0 and later.
     */
    public UIView getBackgroundView() {
        if (customClass) { return objc_getBackgroundViewSuper(getSuper(), backgroundView); } else { return objc_getBackgroundView(this, backgroundView); }
    }
    
    private static final Selector setBackgroundView$ = Selector.register("setBackgroundView:");
    @Bridge private native static void objc_setBackgroundView(UICollectionViewCell __self__, Selector __cmd__, UIView backgroundView);
    @Bridge private native static void objc_setBackgroundViewSuper(ObjCSuper __super__, Selector __cmd__, UIView backgroundView);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UICollectionViewCell_class/Reference/Reference.html#//apple_ref/occ/instp/UICollectionViewCell/backgroundView">@property (nonatomic, retain) UIView *backgroundView</a>
     * @since Available in iOS 6.0 and later.
     */
    public void setBackgroundView(UIView backgroundView) {
        if (customClass) { objc_setBackgroundViewSuper(getSuper(), setBackgroundView$, backgroundView); } else { objc_setBackgroundView(this, setBackgroundView$, backgroundView); }
    }
    
    private static final Selector contentView = Selector.register("contentView");
    @Bridge private native static UIView objc_getContentView(UICollectionViewCell __self__, Selector __cmd__);
    @Bridge private native static UIView objc_getContentViewSuper(ObjCSuper __super__, Selector __cmd__);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UICollectionViewCell_class/Reference/Reference.html#//apple_ref/occ/instp/UICollectionViewCell/contentView">@property (nonatomic, readonly) UIView *contentView</a>
     * @since Available in iOS 6.0 and later.
     */
    public UIView getContentView() {
        if (customClass) { return objc_getContentViewSuper(getSuper(), contentView); } else { return objc_getContentView(this, contentView); }
    }
    
    private static final Selector isHighlighted = Selector.register("isHighlighted");
    @Bridge private native static boolean objc_isHighlighted(UICollectionViewCell __self__, Selector __cmd__);
    @Bridge private native static boolean objc_isHighlightedSuper(ObjCSuper __super__, Selector __cmd__);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UICollectionViewCell_class/Reference/Reference.html#//apple_ref/occ/instp/UICollectionViewCell/highlighted">@property (nonatomic, getter=isHighlighted) BOOL highlighted</a>
     * @since Available in iOS 6.0 and later.
     */
    public boolean isHighlighted() {
        if (customClass) { return objc_isHighlightedSuper(getSuper(), isHighlighted); } else { return objc_isHighlighted(this, isHighlighted); }
    }
    
    private static final Selector setHighlighted$ = Selector.register("setHighlighted:");
    @Bridge private native static void objc_setHighlighted(UICollectionViewCell __self__, Selector __cmd__, boolean highlighted);
    @Bridge private native static void objc_setHighlightedSuper(ObjCSuper __super__, Selector __cmd__, boolean highlighted);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UICollectionViewCell_class/Reference/Reference.html#//apple_ref/occ/instp/UICollectionViewCell/highlighted">@property (nonatomic, getter=isHighlighted) BOOL highlighted</a>
     * @since Available in iOS 6.0 and later.
     */
    public void setHighlighted(boolean highlighted) {
        if (customClass) { objc_setHighlightedSuper(getSuper(), setHighlighted$, highlighted); } else { objc_setHighlighted(this, setHighlighted$, highlighted); }
    }
    
    private static final Selector isSelected = Selector.register("isSelected");
    @Bridge private native static boolean objc_isSelected(UICollectionViewCell __self__, Selector __cmd__);
    @Bridge private native static boolean objc_isSelectedSuper(ObjCSuper __super__, Selector __cmd__);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UICollectionViewCell_class/Reference/Reference.html#//apple_ref/occ/instp/UICollectionViewCell/selected">@property (nonatomic, getter=isSelected) BOOL selected</a>
     * @since Available in iOS 6.0 and later.
     */
    public boolean isSelected() {
        if (customClass) { return objc_isSelectedSuper(getSuper(), isSelected); } else { return objc_isSelected(this, isSelected); }
    }
    
    private static final Selector setSelected$ = Selector.register("setSelected:");
    @Bridge private native static void objc_setSelected(UICollectionViewCell __self__, Selector __cmd__, boolean selected);
    @Bridge private native static void objc_setSelectedSuper(ObjCSuper __super__, Selector __cmd__, boolean selected);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UICollectionViewCell_class/Reference/Reference.html#//apple_ref/occ/instp/UICollectionViewCell/selected">@property (nonatomic, getter=isSelected) BOOL selected</a>
     * @since Available in iOS 6.0 and later.
     */
    public void setSelected(boolean selected) {
        if (customClass) { objc_setSelectedSuper(getSuper(), setSelected$, selected); } else { objc_setSelected(this, setSelected$, selected); }
    }
    
    private static final Selector selectedBackgroundView = Selector.register("selectedBackgroundView");
    @Bridge private native static UIView objc_getSelectedBackgroundView(UICollectionViewCell __self__, Selector __cmd__);
    @Bridge private native static UIView objc_getSelectedBackgroundViewSuper(ObjCSuper __super__, Selector __cmd__);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UICollectionViewCell_class/Reference/Reference.html#//apple_ref/occ/instp/UICollectionViewCell/selectedBackgroundView">@property (nonatomic, retain) UIView *selectedBackgroundView</a>
     * @since Available in iOS 6.0 and later.
     */
    public UIView getSelectedBackgroundView() {
        if (customClass) { return objc_getSelectedBackgroundViewSuper(getSuper(), selectedBackgroundView); } else { return objc_getSelectedBackgroundView(this, selectedBackgroundView); }
    }
    
    private static final Selector setSelectedBackgroundView$ = Selector.register("setSelectedBackgroundView:");
    @Bridge private native static void objc_setSelectedBackgroundView(UICollectionViewCell __self__, Selector __cmd__, UIView selectedBackgroundView);
    @Bridge private native static void objc_setSelectedBackgroundViewSuper(ObjCSuper __super__, Selector __cmd__, UIView selectedBackgroundView);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UICollectionViewCell_class/Reference/Reference.html#//apple_ref/occ/instp/UICollectionViewCell/selectedBackgroundView">@property (nonatomic, retain) UIView *selectedBackgroundView</a>
     * @since Available in iOS 6.0 and later.
     */
    public void setSelectedBackgroundView(UIView selectedBackgroundView) {
        if (customClass) { objc_setSelectedBackgroundViewSuper(getSuper(), setSelectedBackgroundView$, selectedBackgroundView); } else { objc_setSelectedBackgroundView(this, setSelectedBackgroundView$, selectedBackgroundView); }
    }
    /*</properties>*/
    /*<methods>*/
    
    /*</methods>*/
    /*<callbacks>*/
    static class Callbacks {
        @Callback @BindSelector("backgroundView") public static UIView getBackgroundView(UICollectionViewCell __self__, Selector __cmd__) { return __self__.getBackgroundView(); }
        @Callback @BindSelector("setBackgroundView:") public static void setBackgroundView(UICollectionViewCell __self__, Selector __cmd__, UIView backgroundView) { __self__.setBackgroundView(backgroundView); }
        @Callback @BindSelector("contentView") public static UIView getContentView(UICollectionViewCell __self__, Selector __cmd__) { return __self__.getContentView(); }
        @Callback @BindSelector("isHighlighted") public static boolean isHighlighted(UICollectionViewCell __self__, Selector __cmd__) { return __self__.isHighlighted(); }
        @Callback @BindSelector("setHighlighted:") public static void setHighlighted(UICollectionViewCell __self__, Selector __cmd__, boolean highlighted) { __self__.setHighlighted(highlighted); }
        @Callback @BindSelector("isSelected") public static boolean isSelected(UICollectionViewCell __self__, Selector __cmd__) { return __self__.isSelected(); }
        @Callback @BindSelector("setSelected:") public static void setSelected(UICollectionViewCell __self__, Selector __cmd__, boolean selected) { __self__.setSelected(selected); }
        @Callback @BindSelector("selectedBackgroundView") public static UIView getSelectedBackgroundView(UICollectionViewCell __self__, Selector __cmd__) { return __self__.getSelectedBackgroundView(); }
        @Callback @BindSelector("setSelectedBackgroundView:") public static void setSelectedBackgroundView(UICollectionViewCell __self__, Selector __cmd__, UIView selectedBackgroundView) { __self__.setSelectedBackgroundView(selectedBackgroundView); }
    }
    /*</callbacks>*/

}
