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
 *   @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UITableViewHeaderFooterView_class/Reference/Reference.html">UITableViewHeaderFooterView Class Reference</a>
 *   @since Available in iOS 6.0 and later.
 * </div>
 */
/*<library>*/@Library("UIKit")/*</library>*/
public class /*<name>*/ UITableViewHeaderFooterView /*</name>*/ 
    extends /*<extends>*/ UIView /*</extends>*/ 
    /*<implements>*/ /*</implements>*/ {

    static {
        ObjCRuntime.bind(/*<name>*/ UITableViewHeaderFooterView /*</name>*/.class);
    }

    /*<constructors>*/
    public UITableViewHeaderFooterView() {}
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UITableViewHeaderFooterView_class/Reference/Reference.html#//apple_ref/occ/instm/UITableViewHeaderFooterView/initWithReuseIdentifier:">- (id)initWithReuseIdentifier:(NSString *)reuseIdentifier</a>
     * @since Available in iOS 6.0 and later.
     */
    @Bind("initWithReuseIdentifier:") public UITableViewHeaderFooterView(@Type("NSString *") String reuseIdentifier) {}
    /*</constructors>*/
    /*<properties>*/
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UITableViewHeaderFooterView_class/Reference/Reference.html#//apple_ref/occ/instp/UITableViewHeaderFooterView/backgroundView">@property(nonatomic, retain) UIView *backgroundView</a>
     * @since Available in iOS 6.0 and later.
     */
    @Bind("backgroundView") public native @Type("UIView *") UIView getBackgroundView();
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UITableViewHeaderFooterView_class/Reference/Reference.html#//apple_ref/occ/instp/UITableViewHeaderFooterView/backgroundView">@property(nonatomic, retain) UIView *backgroundView</a>
     * @since Available in iOS 6.0 and later.
     */
    @Bind("setBackgroundView:") public native void setBackgroundView(@Type("UIView *") UIView v);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UITableViewHeaderFooterView_class/Reference/Reference.html#//apple_ref/occ/instp/UITableViewHeaderFooterView/contentView">@property(nonatomic, readonly, retain) UIView *contentView</a>
     * @since Available in iOS 6.0 and later.
     */
    @Bind("contentView") public native @Type("UIView *") UIView getContentView();
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UITableViewHeaderFooterView_class/Reference/Reference.html#//apple_ref/occ/instp/UITableViewHeaderFooterView/detailTextLabel">@property(nonatomic, readonly, retain) UILabel *detailTextLabel</a>
     * @since Available in iOS 6.0 and later.
     */
    @Bind("detailTextLabel") public native @Type("UILabel *") UILabel getDetailTextLabel();
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UITableViewHeaderFooterView_class/Reference/Reference.html#//apple_ref/occ/instp/UITableViewHeaderFooterView/reuseIdentifier">@property(nonatomic, readonly, copy) NSString *reuseIdentifier</a>
     * @since Available in iOS 6.0 and later.
     */
    @Bind("reuseIdentifier") public native @Type("NSString *") String getReuseIdentifier();
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UITableViewHeaderFooterView_class/Reference/Reference.html#//apple_ref/occ/instp/UITableViewHeaderFooterView/textLabel">@property(nonatomic, readonly, retain) UILabel *textLabel</a>
     * @since Available in iOS 6.0 and later.
     */
    @Bind("textLabel") public native @Type("UILabel *") UILabel getTextLabel();
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UITableViewHeaderFooterView_class/Reference/Reference.html#//apple_ref/occ/instp/UITableViewHeaderFooterView/tintColor">@property(nonatomic, retain) UIColor *tintColor</a>
     * @since Available in iOS 6.0 and later.
     */
    @Bind("tintColor") public native @Type("UIColor *") UIColor getTintColor();
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UITableViewHeaderFooterView_class/Reference/Reference.html#//apple_ref/occ/instp/UITableViewHeaderFooterView/tintColor">@property(nonatomic, retain) UIColor *tintColor</a>
     * @since Available in iOS 6.0 and later.
     */
    @Bind("setTintColor:") public native void setTintColor(@Type("UIColor *") UIColor v);
    /*</properties>*/
    /*<methods>*/
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UITableViewHeaderFooterView_class/Reference/Reference.html#//apple_ref/occ/instm/UITableViewHeaderFooterView/prepareForReuse">- (void)prepareForReuse</a>
     * @since Available in iOS 6.0 and later.
     */
    @Bind("prepareForReuse") public native @Type("void") void prepareForReuse();
    /*</methods>*/

}
