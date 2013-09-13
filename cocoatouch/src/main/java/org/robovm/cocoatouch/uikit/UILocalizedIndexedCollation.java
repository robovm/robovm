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
 *   @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../../../iPhone/Reference/UILocalizedIndexedCollation_Class/UILocalizedIndexedCollation.html">UILocalizedIndexedCollation Class Reference</a>
 *   @since Available in iOS 3.0 and later.
 * </div>
 */
/*<library>*/@Library("UIKit")/*</library>*/
@NativeClass public class /*<name>*/ UILocalizedIndexedCollation /*</name>*/ 
    extends /*<extends>*/ NSObject /*</extends>*/ 
    /*<implements>*/ /*</implements>*/ {

    static {
        ObjCRuntime.bind(/*<name>*/ UILocalizedIndexedCollation /*</name>*/.class);
    }

    private static final ObjCClass objCClass = ObjCClass.getByType(/*<name>*/ UILocalizedIndexedCollation /*</name>*/.class);

    /*<constructors>*/
    protected UILocalizedIndexedCollation(SkipInit skipInit) { super(skipInit); }
    public UILocalizedIndexedCollation() {}
    
    /*</constructors>*/
    /*<properties>*/
    
    private static final Selector sectionIndexTitles = Selector.register("sectionIndexTitles");
    @Bridge private native static NSArray objc_getSectionIndexTitles(UILocalizedIndexedCollation __self__, Selector __cmd__);
    @Bridge private native static NSArray objc_getSectionIndexTitlesSuper(ObjCSuper __super__, Selector __cmd__);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../../../iPhone/Reference/UILocalizedIndexedCollation_Class/UILocalizedIndexedCollation.html#//apple_ref/occ/instp/UILocalizedIndexedCollation/sectionIndexTitles">@property(nonatomic, readonly) NSArray *sectionIndexTitles</a>
     * @since Available in iOS 3.0 and later.
     */
    public NSArray getSectionIndexTitles() {
        if (customClass) { return objc_getSectionIndexTitlesSuper(getSuper(), sectionIndexTitles); } else { return objc_getSectionIndexTitles(this, sectionIndexTitles); }
    }
    
    private static final Selector sectionTitles = Selector.register("sectionTitles");
    @Bridge private native static NSArray objc_getSectionTitles(UILocalizedIndexedCollation __self__, Selector __cmd__);
    @Bridge private native static NSArray objc_getSectionTitlesSuper(ObjCSuper __super__, Selector __cmd__);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../../../iPhone/Reference/UILocalizedIndexedCollation_Class/UILocalizedIndexedCollation.html#//apple_ref/occ/instp/UILocalizedIndexedCollation/sectionTitles">@property(nonatomic, readonly) NSArray *sectionTitles</a>
     * @since Available in iOS 3.0 and later.
     */
    public NSArray getSectionTitles() {
        if (customClass) { return objc_getSectionTitlesSuper(getSuper(), sectionTitles); } else { return objc_getSectionTitles(this, sectionTitles); }
    }
    /*</properties>*/
    /*<methods>*/
    
    private static final Selector currentCollation = Selector.register("currentCollation");
    @Bridge private native static NSObject objc_getCurrentCollation(ObjCClass __self__, Selector __cmd__);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../../../iPhone/Reference/UILocalizedIndexedCollation_Class/UILocalizedIndexedCollation.html#//apple_ref/occ/clm/UILocalizedIndexedCollation/currentCollation">+ (id)currentCollation</a>
     * @since Available in iOS 3.0 and later.
     */
    public static NSObject getCurrentCollation() {
        return objc_getCurrentCollation(objCClass, currentCollation);
    }
    
    private static final Selector sectionForObject$collationStringSelector$ = Selector.register("sectionForObject:collationStringSelector:");
    @Bridge private native static int objc_getSectionForObject(UILocalizedIndexedCollation __self__, Selector __cmd__, NSObject object, Selector selector);
    @Bridge private native static int objc_getSectionForObjectSuper(ObjCSuper __super__, Selector __cmd__, NSObject object, Selector selector);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../../../iPhone/Reference/UILocalizedIndexedCollation_Class/UILocalizedIndexedCollation.html#//apple_ref/occ/instm/UILocalizedIndexedCollation/sectionForObject:collationStringSelector:">- (NSInteger)sectionForObject:(id)object collationStringSelector:(SEL)selector</a>
     * @since Available in iOS 3.0 and later.
     */
    public int getSectionForObject(NSObject object, Selector selector) {
        if (customClass) { return objc_getSectionForObjectSuper(getSuper(), sectionForObject$collationStringSelector$, object, selector); } else { return objc_getSectionForObject(this, sectionForObject$collationStringSelector$, object, selector); }
    }
    
    private static final Selector sectionForSectionIndexTitleAtIndex$ = Selector.register("sectionForSectionIndexTitleAtIndex:");
    @Bridge private native static int objc_getSectionForSectionIndexTitle(UILocalizedIndexedCollation __self__, Selector __cmd__, int indexTitleIndex);
    @Bridge private native static int objc_getSectionForSectionIndexTitleSuper(ObjCSuper __super__, Selector __cmd__, int indexTitleIndex);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../../../iPhone/Reference/UILocalizedIndexedCollation_Class/UILocalizedIndexedCollation.html#//apple_ref/occ/instm/UILocalizedIndexedCollation/sectionForSectionIndexTitleAtIndex:">- (NSInteger)sectionForSectionIndexTitleAtIndex:(NSInteger)indexTitleIndex</a>
     * @since Available in iOS 3.0 and later.
     */
    public int getSectionForSectionIndexTitle(int indexTitleIndex) {
        if (customClass) { return objc_getSectionForSectionIndexTitleSuper(getSuper(), sectionForSectionIndexTitleAtIndex$, indexTitleIndex); } else { return objc_getSectionForSectionIndexTitle(this, sectionForSectionIndexTitleAtIndex$, indexTitleIndex); }
    }
    
    private static final Selector sortedArrayFromArray$collationStringSelector$ = Selector.register("sortedArrayFromArray:collationStringSelector:");
    @Bridge private native static NSArray objc_sortArray(UILocalizedIndexedCollation __self__, Selector __cmd__, NSArray array, Selector selector);
    @Bridge private native static NSArray objc_sortArraySuper(ObjCSuper __super__, Selector __cmd__, NSArray array, Selector selector);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../../../iPhone/Reference/UILocalizedIndexedCollation_Class/UILocalizedIndexedCollation.html#//apple_ref/occ/instm/UILocalizedIndexedCollation/sortedArrayFromArray:collationStringSelector:">- (NSArray *)sortedArrayFromArray:(NSArray *)array collationStringSelector:(SEL)selector</a>
     * @since Available in iOS 3.0 and later.
     */
    public NSArray sortArray(NSArray array, Selector selector) {
        if (customClass) { return objc_sortArraySuper(getSuper(), sortedArrayFromArray$collationStringSelector$, array, selector); } else { return objc_sortArray(this, sortedArrayFromArray$collationStringSelector$, array, selector); }
    }
    /*</methods>*/
    /*<callbacks>*/
    static class Callbacks {
        @Callback @BindSelector("sectionIndexTitles") public static NSArray getSectionIndexTitles(UILocalizedIndexedCollation __self__, Selector __cmd__) { return __self__.getSectionIndexTitles(); }
        @Callback @BindSelector("sectionTitles") public static NSArray getSectionTitles(UILocalizedIndexedCollation __self__, Selector __cmd__) { return __self__.getSectionTitles(); }
        @Callback @BindSelector("sectionForObject:collationStringSelector:") public static int getSectionForObject(UILocalizedIndexedCollation __self__, Selector __cmd__, NSObject object, Selector selector) { return __self__.getSectionForObject(object, selector); }
        @Callback @BindSelector("sectionForSectionIndexTitleAtIndex:") public static int getSectionForSectionIndexTitle(UILocalizedIndexedCollation __self__, Selector __cmd__, int indexTitleIndex) { return __self__.getSectionForSectionIndexTitle(indexTitleIndex); }
        @Callback @BindSelector("sortedArrayFromArray:collationStringSelector:") public static NSArray sortArray(UILocalizedIndexedCollation __self__, Selector __cmd__, NSArray array, Selector selector) { return __self__.sortArray(array, selector); }
    }
    /*</callbacks>*/

}
