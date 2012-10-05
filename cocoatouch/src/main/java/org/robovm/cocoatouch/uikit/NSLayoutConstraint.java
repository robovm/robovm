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
 *   @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../../../AppKit/Reference/NSLayoutConstraint_Class/NSLayoutConstraint/NSLayoutConstraint.html">NSLayoutConstraint Class Reference</a>
 *   @since Available in iOS 6.0 and later.
 * </div>
 */
/*<library>*/@Library("UIKit")/*</library>*/
public class /*<name>*/ NSLayoutConstraint /*</name>*/ 
    extends /*<extends>*/ NSObject /*</extends>*/ 
    /*<implements>*/ /*</implements>*/ {

    static {
        ObjCRuntime.bind(/*<name>*/ NSLayoutConstraint /*</name>*/.class);
    }

    private static final ObjCClass objCClass = ObjCClass.getByType(/*<name>*/ NSLayoutConstraint /*</name>*/.class);

    /*<constructors>*/
    protected NSLayoutConstraint(SkipInit skipInit) { super(skipInit); }
    public NSLayoutConstraint() {}
    
    /*</constructors>*/
    /*<properties>*/
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../../../AppKit/Reference/NSLayoutConstraint_Class/NSLayoutConstraint/NSLayoutConstraint.html#//apple_ref/occ/instp/NSLayoutConstraint/constant">@property CGFloat constant</a>
     * @since Available in iOS 6.0 and later.
     */
    @Bind("constant") public native float getConstant();
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../../../AppKit/Reference/NSLayoutConstraint_Class/NSLayoutConstraint/NSLayoutConstraint.html#//apple_ref/occ/instp/NSLayoutConstraint/constant">@property CGFloat constant</a>
     * @since Available in iOS 6.0 and later.
     */
    @Bind("setConstant:") public native void setConstant(float v);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../../../AppKit/Reference/NSLayoutConstraint_Class/NSLayoutConstraint/NSLayoutConstraint.html#//apple_ref/occ/instp/NSLayoutConstraint/firstAttribute">@property (readonly) NSLayoutAttribute firstAttribute</a>
     * @since Available in iOS 6.0 and later.
     */
    @Bind("firstAttribute") public native NSLayoutAttribute getFirstAttribute();
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../../../AppKit/Reference/NSLayoutConstraint_Class/NSLayoutConstraint/NSLayoutConstraint.html#//apple_ref/occ/instp/NSLayoutConstraint/firstItem">@property (readonly, assign) id firstItem</a>
     * @since Available in iOS 6.0 and later.
     */
    @Bind("firstItem") public native NSObject getFirstItem();
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../../../AppKit/Reference/NSLayoutConstraint_Class/NSLayoutConstraint/NSLayoutConstraint.html#//apple_ref/occ/instp/NSLayoutConstraint/multiplier">@property (readonly) CGFloat multiplier</a>
     * @since Available in iOS 6.0 and later.
     */
    @Bind("multiplier") public native float getMultiplier();
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../../../AppKit/Reference/NSLayoutConstraint_Class/NSLayoutConstraint/NSLayoutConstraint.html#//apple_ref/occ/instp/NSLayoutConstraint/priority">@property NSLayoutPriority priority;</a>
     * @since Available in iOS 6.0 and later.
     */
    @Bind("priority") public native UILayoutPriority getPriority();
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../../../AppKit/Reference/NSLayoutConstraint_Class/NSLayoutConstraint/NSLayoutConstraint.html#//apple_ref/occ/instp/NSLayoutConstraint/priority">@property NSLayoutPriority priority;</a>
     * @since Available in iOS 6.0 and later.
     */
    @Bind("setPriority:") public native void setPriority(UILayoutPriority v);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../../../AppKit/Reference/NSLayoutConstraint_Class/NSLayoutConstraint/NSLayoutConstraint.html#//apple_ref/occ/instp/NSLayoutConstraint/relation">@property (readonly) NSLayoutRelation relation</a>
     * @since Available in iOS 6.0 and later.
     */
    @Bind("relation") public native NSLayoutRelation getRelation();
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../../../AppKit/Reference/NSLayoutConstraint_Class/NSLayoutConstraint/NSLayoutConstraint.html#//apple_ref/occ/instp/NSLayoutConstraint/secondAttribute">@property (readonly) NSLayoutAttribute secondAttribute</a>
     * @since Available in iOS 6.0 and later.
     */
    @Bind("secondAttribute") public native NSLayoutAttribute getSecondAttribute();
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../../../AppKit/Reference/NSLayoutConstraint_Class/NSLayoutConstraint/NSLayoutConstraint.html#//apple_ref/occ/instp/NSLayoutConstraint/secondItem">@property (readonly, assign) id secondItem</a>
     * @since Available in iOS 6.0 and later.
     */
    @Bind("secondItem") public native NSObject getSecondItem();
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../../../AppKit/Reference/NSLayoutConstraint_Class/NSLayoutConstraint/NSLayoutConstraint.html#//apple_ref/occ/instp/NSLayoutConstraint/shouldBeArchived">@property BOOL shouldBeArchived;</a>
     * @since Available in iOS 6.0 and later.
     */
    @Bind("shouldBeArchived") public native boolean isShouldBeArchived();
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../../../AppKit/Reference/NSLayoutConstraint_Class/NSLayoutConstraint/NSLayoutConstraint.html#//apple_ref/occ/instp/NSLayoutConstraint/shouldBeArchived">@property BOOL shouldBeArchived;</a>
     * @since Available in iOS 6.0 and later.
     */
    @Bind("setShouldBeArchived:") public native void setShouldBeArchived(boolean v);
    /*</properties>*/
    /*<methods>*/
    
    private static final Selector constraintWithItem$attribute$relatedBy$toItem$attribute$multiplier$constant$ = Selector.register("constraintWithItem:attribute:relatedBy:toItem:attribute:multiplier:constant:");
    @Bridge(symbol = "objc_msgSend") private native static NSObject objc_create(ObjCClass __self__, Selector __cmd__, NSObject view1, NSLayoutAttribute attr1, NSLayoutRelation relation, NSObject view2, NSLayoutAttribute attr2, float multiplier, float c);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../../../AppKit/Reference/NSLayoutConstraint_Class/NSLayoutConstraint/NSLayoutConstraint.html#//apple_ref/occ/clm/NSLayoutConstraint/constraintWithItem:attribute:relatedBy:toItem:attribute:multiplier:constant:">+ (id)constraintWithItem:(id)view1 attribute:(NSLayoutAttribute)attr1 relatedBy:(NSLayoutRelation)relation toItem:(id)view2 attribute:(NSLayoutAttribute)attr2 multiplier:(CGFloat)multiplier constant:(CGFloat)c</a>
     * @since Available in iOS 6.0 and later.
     */
    public static NSObject create(NSObject view1, NSLayoutAttribute attr1, NSLayoutRelation relation, NSObject view2, NSLayoutAttribute attr2, float multiplier, float c) {
        return objc_create(objCClass, constraintWithItem$attribute$relatedBy$toItem$attribute$multiplier$constant$, view1, attr1, relation, view2, attr2, multiplier, c);
    }
    
    private static final Selector constraintsWithVisualFormat$options$metrics$views$ = Selector.register("constraintsWithVisualFormat:options:metrics:views:");
    @Bridge(symbol = "objc_msgSend") private native static NSArray objc_fromVisualFormat(ObjCClass __self__, Selector __cmd__, String format, NSLayoutFormatOptions opts, NSDictionary metrics, NSDictionary views);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../../../AppKit/Reference/NSLayoutConstraint_Class/NSLayoutConstraint/NSLayoutConstraint.html#//apple_ref/occ/clm/NSLayoutConstraint/constraintsWithVisualFormat:options:metrics:views:">+ (NSArray *)constraintsWithVisualFormat:(NSString *)format  options:(NSLayoutFormatOptions)opts  metrics:(NSDictionary *)metrics  views:(NSDictionary *)views</a>
     * @since Available in iOS 6.0 and later.
     */
    public static NSArray fromVisualFormat(String format, NSLayoutFormatOptions opts, NSDictionary metrics, NSDictionary views) {
        return objc_fromVisualFormat(objCClass, constraintsWithVisualFormat$options$metrics$views$, format, opts, metrics, views);
    }
    /*</methods>*/

}
