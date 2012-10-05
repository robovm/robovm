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
 *
 * <div class="javadoc">
 *   @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../../../iPhone/Reference/UILocalizedIndexedCollation_Class/UILocalizedIndexedCollation.html">UILocalizedIndexedCollation Class Reference</a>
 *   @since Available in iOS 3.0 and later.
 * </div>
 */
/*<library>*/@Library("UIKit")/*</library>*/
public class /*<name>*/ UILocalizedIndexedCollation /*</name>*/ 
    extends /*<extends>*/ NSObject /*</extends>*/ 
    /*<implements>*/ /*</implements>*/ {

    static {
        ObjCRuntime.bind(/*<name>*/ UILocalizedIndexedCollation /*</name>*/.class);
    }

    /*<constructors>*/
    public UILocalizedIndexedCollation() {}
    
    /*</constructors>*/
    /*<properties>*/
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../../../iPhone/Reference/UILocalizedIndexedCollation_Class/UILocalizedIndexedCollation.html#//apple_ref/occ/instp/UILocalizedIndexedCollation/sectionIndexTitles">@property(nonatomic, readonly) NSArray *sectionIndexTitles</a>
     * @since Available in iOS 3.0 and later.
     */
    @Bind("sectionIndexTitles") public native @Type("NSArray *") NSArray getSectionIndexTitles();
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../../../iPhone/Reference/UILocalizedIndexedCollation_Class/UILocalizedIndexedCollation.html#//apple_ref/occ/instp/UILocalizedIndexedCollation/sectionTitles">@property(nonatomic, readonly) NSArray *sectionTitles</a>
     * @since Available in iOS 3.0 and later.
     */
    @Bind("sectionTitles") public native @Type("NSArray *") NSArray getSectionTitles();
    /*</properties>*/
    /*<methods>*/
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../../../iPhone/Reference/UILocalizedIndexedCollation_Class/UILocalizedIndexedCollation.html#//apple_ref/occ/clm/UILocalizedIndexedCollation/currentCollation">+ (id)currentCollation</a>
     * @since Available in iOS 3.0 and later.
     */
    @Bind("currentCollation") public native static @Type("id") NSObject getCurrentCollation();
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../../../iPhone/Reference/UILocalizedIndexedCollation_Class/UILocalizedIndexedCollation.html#//apple_ref/occ/instm/UILocalizedIndexedCollation/sectionForObject:collationStringSelector:">- (NSInteger)sectionForObject:(id)object collationStringSelector:(SEL)selector</a>
     * @since Available in iOS 3.0 and later.
     */
    @Bind("sectionForObject:collationStringSelector:") public native @Type("NSInteger") int getSectionForObject(@Type("id") NSObject object, @Type("SEL") Selector selector);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../../../iPhone/Reference/UILocalizedIndexedCollation_Class/UILocalizedIndexedCollation.html#//apple_ref/occ/instm/UILocalizedIndexedCollation/sectionForSectionIndexTitleAtIndex:">- (NSInteger)sectionForSectionIndexTitleAtIndex:(NSInteger)indexTitleIndex</a>
     * @since Available in iOS 3.0 and later.
     */
    @Bind("sectionForSectionIndexTitleAtIndex:") public native @Type("NSInteger") int getSectionForSectionIndexTitle(@Type("NSInteger") int indexTitleIndex);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../../../iPhone/Reference/UILocalizedIndexedCollation_Class/UILocalizedIndexedCollation.html#//apple_ref/occ/instm/UILocalizedIndexedCollation/sortedArrayFromArray:collationStringSelector:">- (NSArray *)sortedArrayFromArray:(NSArray *)array collationStringSelector:(SEL)selector</a>
     * @since Available in iOS 3.0 and later.
     */
    @Bind("sortedArrayFromArray:collationStringSelector:") public native @Type("NSArray *") NSArray sortArray(@Type("NSArray *") NSArray array, @Type("SEL") Selector selector);
    /*</methods>*/

}
