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
    @Bind("sectionIndexTitles") public native @Type("NSArray *") NSArray getSectionIndexTitles();
    @Bind("sectionTitles") public native @Type("NSArray *") NSArray getSectionTitles();
    /*</properties>*/
    /*<methods>*/
    @Bind("currentCollation") public native static @Type("id") NSObject getCurrentCollation();
    @Bind("sectionForObject:collationStringSelector:") public native @Type("NSInteger") int getSectionForObject(@Type("id") NSObject object, @Type("SEL") Selector selector);
    @Bind("sectionForSectionIndexTitleAtIndex:") public native @Type("NSInteger") int getSectionForSectionIndexTitle(@Type("NSInteger") int indexTitleIndex);
    @Bind("sortedArrayFromArray:collationStringSelector:") public native @Type("NSArray *") NSArray sortArray(@Type("NSArray *") NSArray array, @Type("SEL") Selector selector);
    /*</methods>*/

}
