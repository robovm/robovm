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
 *   @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIActivityViewController_Class/Reference/Reference.html">UIActivityViewController Class Reference</a>
 *   @since Available in iOS 6.0 and later.
 * </div>
 */
/*<library>*/@Library("UIKit")/*</library>*/
public class /*<name>*/ UIActivityViewController /*</name>*/ 
    extends /*<extends>*/ UIViewController /*</extends>*/ 
    /*<implements>*/ /*</implements>*/ {

    static {
        ObjCRuntime.bind(/*<name>*/ UIActivityViewController /*</name>*/.class);
    }

    /*<constructors>*/
    public UIActivityViewController() {}
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIActivityViewController_Class/Reference/Reference.html#//apple_ref/occ/instm/UIActivityViewController/initWithActivityItems:applicationActivities:">- (id)initWithActivityItems:(NSArray *)activityItems applicationActivities:(NSArray *)applicationActivities</a>
     * @since Available in iOS 6.0 and later.
     */
    @Bind("initWithActivityItems:applicationActivities:") public UIActivityViewController(@Type("NSArray *") NSArray activityItems, @Type("NSArray *") NSArray applicationActivities) {}
    /*</constructors>*/
    /*<properties>*/
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIActivityViewController_Class/Reference/Reference.html#//apple_ref/occ/instp/UIActivityViewController/excludedActivityTypes">@property(nonatomic,copy) NSArray *excludedActivityTypes</a>
     * @since Available in iOS 6.0 and later.
     */
    @Bind("excludedActivityTypes") public native @Type("NSArray *") NSArray getExcludedActivityTypes();
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIActivityViewController_Class/Reference/Reference.html#//apple_ref/occ/instp/UIActivityViewController/excludedActivityTypes">@property(nonatomic,copy) NSArray *excludedActivityTypes</a>
     * @since Available in iOS 6.0 and later.
     */
    @Bind("setExcludedActivityTypes:") public native void setExcludedActivityTypes(@Type("NSArray *") NSArray v);
    /*</properties>*/
    /*<methods>*/
    
    /*</methods>*/

}
