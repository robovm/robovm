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
 *   @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../../../AppKit/Reference/NSLayoutConstraint_Class/NSLayoutConstraint/NSLayoutConstraint.html">NSLayoutConstraint Class Reference</a>
 *   @since Available in iOS 6.0 and later.
 * </div>
 */
/*<library>*/@Library("UIKit")/*</library>*/
@NativeClass public class /*<name>*/ NSLayoutConstraint /*</name>*/ 
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
    
    private static final Selector constant = Selector.register("constant");
    @Bridge private native static float objc_getConstant(NSLayoutConstraint __self__, Selector __cmd__);
    @Bridge private native static float objc_getConstantSuper(ObjCSuper __super__, Selector __cmd__);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../../../AppKit/Reference/NSLayoutConstraint_Class/NSLayoutConstraint/NSLayoutConstraint.html#//apple_ref/occ/instp/NSLayoutConstraint/constant">@property CGFloat constant</a>
     * @since Available in iOS 6.0 and later.
     */
    public float getConstant() {
        if (customClass) { return objc_getConstantSuper(getSuper(), constant); } else { return objc_getConstant(this, constant); }
    }
    
    private static final Selector setConstant$ = Selector.register("setConstant:");
    @Bridge private native static void objc_setConstant(NSLayoutConstraint __self__, Selector __cmd__, float constant);
    @Bridge private native static void objc_setConstantSuper(ObjCSuper __super__, Selector __cmd__, float constant);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../../../AppKit/Reference/NSLayoutConstraint_Class/NSLayoutConstraint/NSLayoutConstraint.html#//apple_ref/occ/instp/NSLayoutConstraint/constant">@property CGFloat constant</a>
     * @since Available in iOS 6.0 and later.
     */
    public void setConstant(float constant) {
        if (customClass) { objc_setConstantSuper(getSuper(), setConstant$, constant); } else { objc_setConstant(this, setConstant$, constant); }
    }
    
    private static final Selector firstAttribute = Selector.register("firstAttribute");
    @Bridge private native static NSLayoutAttribute objc_getFirstAttribute(NSLayoutConstraint __self__, Selector __cmd__);
    @Bridge private native static NSLayoutAttribute objc_getFirstAttributeSuper(ObjCSuper __super__, Selector __cmd__);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../../../AppKit/Reference/NSLayoutConstraint_Class/NSLayoutConstraint/NSLayoutConstraint.html#//apple_ref/occ/instp/NSLayoutConstraint/firstAttribute">@property (readonly) NSLayoutAttribute firstAttribute</a>
     * @since Available in iOS 6.0 and later.
     */
    public NSLayoutAttribute getFirstAttribute() {
        if (customClass) { return objc_getFirstAttributeSuper(getSuper(), firstAttribute); } else { return objc_getFirstAttribute(this, firstAttribute); }
    }
    
    private static final Selector firstItem = Selector.register("firstItem");
    @Bridge private native static NSObject objc_getFirstItem(NSLayoutConstraint __self__, Selector __cmd__);
    @Bridge private native static NSObject objc_getFirstItemSuper(ObjCSuper __super__, Selector __cmd__);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../../../AppKit/Reference/NSLayoutConstraint_Class/NSLayoutConstraint/NSLayoutConstraint.html#//apple_ref/occ/instp/NSLayoutConstraint/firstItem">@property (readonly, assign) id firstItem</a>
     * @since Available in iOS 6.0 and later.
     */
    public NSObject getFirstItem() {
        if (customClass) { return objc_getFirstItemSuper(getSuper(), firstItem); } else { return objc_getFirstItem(this, firstItem); }
    }
    
    private static final Selector multiplier = Selector.register("multiplier");
    @Bridge private native static float objc_getMultiplier(NSLayoutConstraint __self__, Selector __cmd__);
    @Bridge private native static float objc_getMultiplierSuper(ObjCSuper __super__, Selector __cmd__);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../../../AppKit/Reference/NSLayoutConstraint_Class/NSLayoutConstraint/NSLayoutConstraint.html#//apple_ref/occ/instp/NSLayoutConstraint/multiplier">@property (readonly) CGFloat multiplier</a>
     * @since Available in iOS 6.0 and later.
     */
    public float getMultiplier() {
        if (customClass) { return objc_getMultiplierSuper(getSuper(), multiplier); } else { return objc_getMultiplier(this, multiplier); }
    }
    
    private static final Selector priority = Selector.register("priority");
    @Bridge private native static UILayoutPriority objc_getPriority(NSLayoutConstraint __self__, Selector __cmd__);
    @Bridge private native static UILayoutPriority objc_getPrioritySuper(ObjCSuper __super__, Selector __cmd__);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../../../AppKit/Reference/NSLayoutConstraint_Class/NSLayoutConstraint/NSLayoutConstraint.html#//apple_ref/occ/instp/NSLayoutConstraint/priority">@property NSLayoutPriority priority;</a>
     * @since Available in iOS 6.0 and later.
     */
    public UILayoutPriority getPriority() {
        if (customClass) { return objc_getPrioritySuper(getSuper(), priority); } else { return objc_getPriority(this, priority); }
    }
    
    private static final Selector setPriority$ = Selector.register("setPriority:");
    @Bridge private native static void objc_setPriority(NSLayoutConstraint __self__, Selector __cmd__, UILayoutPriority priority);
    @Bridge private native static void objc_setPrioritySuper(ObjCSuper __super__, Selector __cmd__, UILayoutPriority priority);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../../../AppKit/Reference/NSLayoutConstraint_Class/NSLayoutConstraint/NSLayoutConstraint.html#//apple_ref/occ/instp/NSLayoutConstraint/priority">@property NSLayoutPriority priority;</a>
     * @since Available in iOS 6.0 and later.
     */
    public void setPriority(UILayoutPriority priority) {
        if (customClass) { objc_setPrioritySuper(getSuper(), setPriority$, priority); } else { objc_setPriority(this, setPriority$, priority); }
    }
    
    private static final Selector relation = Selector.register("relation");
    @Bridge private native static NSLayoutRelation objc_getRelation(NSLayoutConstraint __self__, Selector __cmd__);
    @Bridge private native static NSLayoutRelation objc_getRelationSuper(ObjCSuper __super__, Selector __cmd__);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../../../AppKit/Reference/NSLayoutConstraint_Class/NSLayoutConstraint/NSLayoutConstraint.html#//apple_ref/occ/instp/NSLayoutConstraint/relation">@property (readonly) NSLayoutRelation relation</a>
     * @since Available in iOS 6.0 and later.
     */
    public NSLayoutRelation getRelation() {
        if (customClass) { return objc_getRelationSuper(getSuper(), relation); } else { return objc_getRelation(this, relation); }
    }
    
    private static final Selector secondAttribute = Selector.register("secondAttribute");
    @Bridge private native static NSLayoutAttribute objc_getSecondAttribute(NSLayoutConstraint __self__, Selector __cmd__);
    @Bridge private native static NSLayoutAttribute objc_getSecondAttributeSuper(ObjCSuper __super__, Selector __cmd__);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../../../AppKit/Reference/NSLayoutConstraint_Class/NSLayoutConstraint/NSLayoutConstraint.html#//apple_ref/occ/instp/NSLayoutConstraint/secondAttribute">@property (readonly) NSLayoutAttribute secondAttribute</a>
     * @since Available in iOS 6.0 and later.
     */
    public NSLayoutAttribute getSecondAttribute() {
        if (customClass) { return objc_getSecondAttributeSuper(getSuper(), secondAttribute); } else { return objc_getSecondAttribute(this, secondAttribute); }
    }
    
    private static final Selector secondItem = Selector.register("secondItem");
    @Bridge private native static NSObject objc_getSecondItem(NSLayoutConstraint __self__, Selector __cmd__);
    @Bridge private native static NSObject objc_getSecondItemSuper(ObjCSuper __super__, Selector __cmd__);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../../../AppKit/Reference/NSLayoutConstraint_Class/NSLayoutConstraint/NSLayoutConstraint.html#//apple_ref/occ/instp/NSLayoutConstraint/secondItem">@property (readonly, assign) id secondItem</a>
     * @since Available in iOS 6.0 and later.
     */
    public NSObject getSecondItem() {
        if (customClass) { return objc_getSecondItemSuper(getSuper(), secondItem); } else { return objc_getSecondItem(this, secondItem); }
    }
    
    private static final Selector shouldBeArchived = Selector.register("shouldBeArchived");
    @Bridge private native static boolean objc_isShouldBeArchived(NSLayoutConstraint __self__, Selector __cmd__);
    @Bridge private native static boolean objc_isShouldBeArchivedSuper(ObjCSuper __super__, Selector __cmd__);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../../../AppKit/Reference/NSLayoutConstraint_Class/NSLayoutConstraint/NSLayoutConstraint.html#//apple_ref/occ/instp/NSLayoutConstraint/shouldBeArchived">@property BOOL shouldBeArchived;</a>
     * @since Available in iOS 6.0 and later.
     */
    public boolean isShouldBeArchived() {
        if (customClass) { return objc_isShouldBeArchivedSuper(getSuper(), shouldBeArchived); } else { return objc_isShouldBeArchived(this, shouldBeArchived); }
    }
    
    private static final Selector setShouldBeArchived$ = Selector.register("setShouldBeArchived:");
    @Bridge private native static void objc_setShouldBeArchived(NSLayoutConstraint __self__, Selector __cmd__, boolean shouldBeArchived);
    @Bridge private native static void objc_setShouldBeArchivedSuper(ObjCSuper __super__, Selector __cmd__, boolean shouldBeArchived);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../../../AppKit/Reference/NSLayoutConstraint_Class/NSLayoutConstraint/NSLayoutConstraint.html#//apple_ref/occ/instp/NSLayoutConstraint/shouldBeArchived">@property BOOL shouldBeArchived;</a>
     * @since Available in iOS 6.0 and later.
     */
    public void setShouldBeArchived(boolean shouldBeArchived) {
        if (customClass) { objc_setShouldBeArchivedSuper(getSuper(), setShouldBeArchived$, shouldBeArchived); } else { objc_setShouldBeArchived(this, setShouldBeArchived$, shouldBeArchived); }
    }
    /*</properties>*/
    /*<methods>*/
    
    private static final Selector constraintWithItem$attribute$relatedBy$toItem$attribute$multiplier$constant$ = Selector.register("constraintWithItem:attribute:relatedBy:toItem:attribute:multiplier:constant:");
    @Bridge private native static NSObject objc_create(ObjCClass __self__, Selector __cmd__, NSObject view1, NSLayoutAttribute attr1, NSLayoutRelation relation, NSObject view2, NSLayoutAttribute attr2, float multiplier, float c);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../../../AppKit/Reference/NSLayoutConstraint_Class/NSLayoutConstraint/NSLayoutConstraint.html#//apple_ref/occ/clm/NSLayoutConstraint/constraintWithItem:attribute:relatedBy:toItem:attribute:multiplier:constant:">+ (id)constraintWithItem:(id)view1 attribute:(NSLayoutAttribute)attr1 relatedBy:(NSLayoutRelation)relation toItem:(id)view2 attribute:(NSLayoutAttribute)attr2 multiplier:(CGFloat)multiplier constant:(CGFloat)c</a>
     * @since Available in iOS 6.0 and later.
     */
    public static NSObject create(NSObject view1, NSLayoutAttribute attr1, NSLayoutRelation relation, NSObject view2, NSLayoutAttribute attr2, float multiplier, float c) {
        return objc_create(objCClass, constraintWithItem$attribute$relatedBy$toItem$attribute$multiplier$constant$, view1, attr1, relation, view2, attr2, multiplier, c);
    }
    
    private static final Selector constraintsWithVisualFormat$options$metrics$views$ = Selector.register("constraintsWithVisualFormat:options:metrics:views:");
    @Bridge private native static NSArray objc_fromVisualFormat(ObjCClass __self__, Selector __cmd__, String format, NSLayoutFormatOptions opts, NSDictionary metrics, NSDictionary views);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../../../AppKit/Reference/NSLayoutConstraint_Class/NSLayoutConstraint/NSLayoutConstraint.html#//apple_ref/occ/clm/NSLayoutConstraint/constraintsWithVisualFormat:options:metrics:views:">+ (NSArray *)constraintsWithVisualFormat:(NSString *)format  options:(NSLayoutFormatOptions)opts  metrics:(NSDictionary *)metrics  views:(NSDictionary *)views</a>
     * @since Available in iOS 6.0 and later.
     */
    public static NSArray fromVisualFormat(String format, NSLayoutFormatOptions opts, NSDictionary metrics, NSDictionary views) {
        return objc_fromVisualFormat(objCClass, constraintsWithVisualFormat$options$metrics$views$, format, opts, metrics, views);
    }
    /*</methods>*/
    /*<callbacks>*/
    static class Callbacks {
        @Callback @BindSelector("constant") public static float getConstant(NSLayoutConstraint __self__, Selector __cmd__) { return __self__.getConstant(); }
        @Callback @BindSelector("setConstant:") public static void setConstant(NSLayoutConstraint __self__, Selector __cmd__, float constant) { __self__.setConstant(constant); }
        @Callback @BindSelector("firstAttribute") public static NSLayoutAttribute getFirstAttribute(NSLayoutConstraint __self__, Selector __cmd__) { return __self__.getFirstAttribute(); }
        @Callback @BindSelector("firstItem") public static NSObject getFirstItem(NSLayoutConstraint __self__, Selector __cmd__) { return __self__.getFirstItem(); }
        @Callback @BindSelector("multiplier") public static float getMultiplier(NSLayoutConstraint __self__, Selector __cmd__) { return __self__.getMultiplier(); }
        @Callback @BindSelector("priority") public static UILayoutPriority getPriority(NSLayoutConstraint __self__, Selector __cmd__) { return __self__.getPriority(); }
        @Callback @BindSelector("setPriority:") public static void setPriority(NSLayoutConstraint __self__, Selector __cmd__, UILayoutPriority priority) { __self__.setPriority(priority); }
        @Callback @BindSelector("relation") public static NSLayoutRelation getRelation(NSLayoutConstraint __self__, Selector __cmd__) { return __self__.getRelation(); }
        @Callback @BindSelector("secondAttribute") public static NSLayoutAttribute getSecondAttribute(NSLayoutConstraint __self__, Selector __cmd__) { return __self__.getSecondAttribute(); }
        @Callback @BindSelector("secondItem") public static NSObject getSecondItem(NSLayoutConstraint __self__, Selector __cmd__) { return __self__.getSecondItem(); }
        @Callback @BindSelector("shouldBeArchived") public static boolean isShouldBeArchived(NSLayoutConstraint __self__, Selector __cmd__) { return __self__.isShouldBeArchived(); }
        @Callback @BindSelector("setShouldBeArchived:") public static void setShouldBeArchived(NSLayoutConstraint __self__, Selector __cmd__, boolean shouldBeArchived) { __self__.setShouldBeArchived(shouldBeArchived); }
    }
    /*</callbacks>*/

}
