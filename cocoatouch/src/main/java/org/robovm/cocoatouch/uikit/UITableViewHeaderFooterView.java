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
 *   @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UITableViewHeaderFooterView_class/Reference/Reference.html">UITableViewHeaderFooterView Class Reference</a>
 *   @since Available in iOS 6.0 and later.
 * </div>
 */
/*<library>*/@Library("UIKit")/*</library>*/
@NativeClass public class /*<name>*/ UITableViewHeaderFooterView /*</name>*/ 
    extends /*<extends>*/ UIView /*</extends>*/ 
    /*<implements>*/ /*</implements>*/ {

    static {
        ObjCRuntime.bind(/*<name>*/ UITableViewHeaderFooterView /*</name>*/.class);
    }

    private static final ObjCClass objCClass = ObjCClass.getByType(/*<name>*/ UITableViewHeaderFooterView /*</name>*/.class);

    public UITableViewHeaderFooterView(CGRect aRect) {
        super(aRect);
    }
    /*<constructors>*/
    protected UITableViewHeaderFooterView(SkipInit skipInit) { super(skipInit); }
    public UITableViewHeaderFooterView() {}
    
    private static final Selector initWithReuseIdentifier$ = Selector.register("initWithReuseIdentifier:");
    @Bridge private native static @Pointer long objc_initWithReuseIdentifier(UITableViewHeaderFooterView __self__, Selector __cmd__, String reuseIdentifier);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UITableViewHeaderFooterView_class/Reference/Reference.html#//apple_ref/occ/instm/UITableViewHeaderFooterView/initWithReuseIdentifier:">- (id)initWithReuseIdentifier:(NSString *)reuseIdentifier</a>
     * @since Available in iOS 6.0 and later.
     */
    public UITableViewHeaderFooterView(String reuseIdentifier) {
        super((SkipInit) null);
        initObject(objc_initWithReuseIdentifier(this, initWithReuseIdentifier$, reuseIdentifier));
    }
    /*</constructors>*/
    /*<properties>*/
    
    private static final Selector backgroundView = Selector.register("backgroundView");
    @Bridge private native static UIView objc_getBackgroundView(UITableViewHeaderFooterView __self__, Selector __cmd__);
    @Bridge private native static UIView objc_getBackgroundViewSuper(ObjCSuper __super__, Selector __cmd__);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UITableViewHeaderFooterView_class/Reference/Reference.html#//apple_ref/occ/instp/UITableViewHeaderFooterView/backgroundView">@property(nonatomic, retain) UIView *backgroundView</a>
     * @since Available in iOS 6.0 and later.
     */
    public UIView getBackgroundView() {
        if (customClass) { return objc_getBackgroundViewSuper(getSuper(), backgroundView); } else { return objc_getBackgroundView(this, backgroundView); }
    }
    
    private static final Selector setBackgroundView$ = Selector.register("setBackgroundView:");
    @Bridge private native static void objc_setBackgroundView(UITableViewHeaderFooterView __self__, Selector __cmd__, UIView backgroundView);
    @Bridge private native static void objc_setBackgroundViewSuper(ObjCSuper __super__, Selector __cmd__, UIView backgroundView);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UITableViewHeaderFooterView_class/Reference/Reference.html#//apple_ref/occ/instp/UITableViewHeaderFooterView/backgroundView">@property(nonatomic, retain) UIView *backgroundView</a>
     * @since Available in iOS 6.0 and later.
     */
    public void setBackgroundView(UIView backgroundView) {
        if (customClass) { objc_setBackgroundViewSuper(getSuper(), setBackgroundView$, backgroundView); } else { objc_setBackgroundView(this, setBackgroundView$, backgroundView); }
    }
    
    private static final Selector contentView = Selector.register("contentView");
    @Bridge private native static UIView objc_getContentView(UITableViewHeaderFooterView __self__, Selector __cmd__);
    @Bridge private native static UIView objc_getContentViewSuper(ObjCSuper __super__, Selector __cmd__);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UITableViewHeaderFooterView_class/Reference/Reference.html#//apple_ref/occ/instp/UITableViewHeaderFooterView/contentView">@property(nonatomic, readonly, retain) UIView *contentView</a>
     * @since Available in iOS 6.0 and later.
     */
    public UIView getContentView() {
        if (customClass) { return objc_getContentViewSuper(getSuper(), contentView); } else { return objc_getContentView(this, contentView); }
    }
    
    private static final Selector detailTextLabel = Selector.register("detailTextLabel");
    @Bridge private native static UILabel objc_getDetailTextLabel(UITableViewHeaderFooterView __self__, Selector __cmd__);
    @Bridge private native static UILabel objc_getDetailTextLabelSuper(ObjCSuper __super__, Selector __cmd__);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UITableViewHeaderFooterView_class/Reference/Reference.html#//apple_ref/occ/instp/UITableViewHeaderFooterView/detailTextLabel">@property(nonatomic, readonly, retain) UILabel *detailTextLabel</a>
     * @since Available in iOS 6.0 and later.
     */
    public UILabel getDetailTextLabel() {
        if (customClass) { return objc_getDetailTextLabelSuper(getSuper(), detailTextLabel); } else { return objc_getDetailTextLabel(this, detailTextLabel); }
    }
    
    private static final Selector reuseIdentifier = Selector.register("reuseIdentifier");
    @Bridge private native static String objc_getReuseIdentifier(UITableViewHeaderFooterView __self__, Selector __cmd__);
    @Bridge private native static String objc_getReuseIdentifierSuper(ObjCSuper __super__, Selector __cmd__);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UITableViewHeaderFooterView_class/Reference/Reference.html#//apple_ref/occ/instp/UITableViewHeaderFooterView/reuseIdentifier">@property(nonatomic, readonly, copy) NSString *reuseIdentifier</a>
     * @since Available in iOS 6.0 and later.
     */
    public String getReuseIdentifier() {
        if (customClass) { return objc_getReuseIdentifierSuper(getSuper(), reuseIdentifier); } else { return objc_getReuseIdentifier(this, reuseIdentifier); }
    }
    
    private static final Selector textLabel = Selector.register("textLabel");
    @Bridge private native static UILabel objc_getTextLabel(UITableViewHeaderFooterView __self__, Selector __cmd__);
    @Bridge private native static UILabel objc_getTextLabelSuper(ObjCSuper __super__, Selector __cmd__);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UITableViewHeaderFooterView_class/Reference/Reference.html#//apple_ref/occ/instp/UITableViewHeaderFooterView/textLabel">@property(nonatomic, readonly, retain) UILabel *textLabel</a>
     * @since Available in iOS 6.0 and later.
     */
    public UILabel getTextLabel() {
        if (customClass) { return objc_getTextLabelSuper(getSuper(), textLabel); } else { return objc_getTextLabel(this, textLabel); }
    }
    
    private static final Selector tintColor = Selector.register("tintColor");
    @Bridge private native static UIColor objc_getTintColor(UITableViewHeaderFooterView __self__, Selector __cmd__);
    @Bridge private native static UIColor objc_getTintColorSuper(ObjCSuper __super__, Selector __cmd__);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UITableViewHeaderFooterView_class/Reference/Reference.html#//apple_ref/occ/instp/UITableViewHeaderFooterView/tintColor">@property(nonatomic, retain) UIColor *tintColor</a>
     * @since Available in iOS 6.0 and later.
     */
    public UIColor getTintColor() {
        if (customClass) { return objc_getTintColorSuper(getSuper(), tintColor); } else { return objc_getTintColor(this, tintColor); }
    }
    
    private static final Selector setTintColor$ = Selector.register("setTintColor:");
    @Bridge private native static void objc_setTintColor(UITableViewHeaderFooterView __self__, Selector __cmd__, UIColor tintColor);
    @Bridge private native static void objc_setTintColorSuper(ObjCSuper __super__, Selector __cmd__, UIColor tintColor);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UITableViewHeaderFooterView_class/Reference/Reference.html#//apple_ref/occ/instp/UITableViewHeaderFooterView/tintColor">@property(nonatomic, retain) UIColor *tintColor</a>
     * @since Available in iOS 6.0 and later.
     */
    public void setTintColor(UIColor tintColor) {
        if (customClass) { objc_setTintColorSuper(getSuper(), setTintColor$, tintColor); } else { objc_setTintColor(this, setTintColor$, tintColor); }
    }
    /*</properties>*/
    /*<methods>*/
    
    private static final Selector prepareForReuse = Selector.register("prepareForReuse");
    @Bridge private native static void objc_prepareForReuse(UITableViewHeaderFooterView __self__, Selector __cmd__);
    @Bridge private native static void objc_prepareForReuseSuper(ObjCSuper __super__, Selector __cmd__);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UITableViewHeaderFooterView_class/Reference/Reference.html#//apple_ref/occ/instm/UITableViewHeaderFooterView/prepareForReuse">- (void)prepareForReuse</a>
     * @since Available in iOS 6.0 and later.
     */
    public void prepareForReuse() {
        if (customClass) { objc_prepareForReuseSuper(getSuper(), prepareForReuse); } else { objc_prepareForReuse(this, prepareForReuse); }
    }
    /*</methods>*/
    /*<callbacks>*/
    static class Callbacks {
        @Callback @BindSelector("backgroundView") public static UIView getBackgroundView(UITableViewHeaderFooterView __self__, Selector __cmd__) { return __self__.getBackgroundView(); }
        @Callback @BindSelector("setBackgroundView:") public static void setBackgroundView(UITableViewHeaderFooterView __self__, Selector __cmd__, UIView backgroundView) { __self__.setBackgroundView(backgroundView); }
        @Callback @BindSelector("contentView") public static UIView getContentView(UITableViewHeaderFooterView __self__, Selector __cmd__) { return __self__.getContentView(); }
        @Callback @BindSelector("detailTextLabel") public static UILabel getDetailTextLabel(UITableViewHeaderFooterView __self__, Selector __cmd__) { return __self__.getDetailTextLabel(); }
        @Callback @BindSelector("reuseIdentifier") public static String getReuseIdentifier(UITableViewHeaderFooterView __self__, Selector __cmd__) { return __self__.getReuseIdentifier(); }
        @Callback @BindSelector("textLabel") public static UILabel getTextLabel(UITableViewHeaderFooterView __self__, Selector __cmd__) { return __self__.getTextLabel(); }
        @Callback @BindSelector("tintColor") public static UIColor getTintColor(UITableViewHeaderFooterView __self__, Selector __cmd__) { return __self__.getTintColor(); }
        @Callback @BindSelector("setTintColor:") public static void setTintColor(UITableViewHeaderFooterView __self__, Selector __cmd__, UIColor tintColor) { __self__.setTintColor(tintColor); }
        @Callback @BindSelector("prepareForReuse") public static void prepareForReuse(UITableViewHeaderFooterView __self__, Selector __cmd__) { __self__.prepareForReuse(); }
    }
    /*</callbacks>*/

}
