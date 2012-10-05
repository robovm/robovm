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
 *   @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIActivity_Class/Reference/Reference.html">UIActivity Class Reference</a>
 *   @since Available in iOS 6.0 and later.
 * </div>
 */
/*<library>*/@Library("UIKit")/*</library>*/
public class /*<name>*/ UIActivity /*</name>*/ 
    extends /*<extends>*/ NSObject /*</extends>*/ 
    /*<implements>*/ /*</implements>*/ {

    static {
        ObjCRuntime.bind(/*<name>*/ UIActivity /*</name>*/.class);
    }

    /*<constructors>*/
    public UIActivity() {}
    
    /*</constructors>*/
    /*<properties>*/
    
    /*</properties>*/
    /*<methods>*/
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIActivity_Class/Reference/Reference.html#//apple_ref/occ/instm/UIActivity/canPerformWithActivityItems:">- (BOOL)canPerformWithActivityItems:(NSArray *)activityItems</a>
     * @since Available in iOS 6.0 and later.
     */
    @Bind("canPerformWithActivityItems:") public native @Type("BOOL") boolean canPerform(@Type("NSArray *") NSArray activityItems);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIActivity_Class/Reference/Reference.html#//apple_ref/occ/instm/UIActivity/activityDidFinish:">- (void)activityDidFinish:(BOOL)completed</a>
     * @since Available in iOS 6.0 and later.
     */
    @Bind("activityDidFinish:") public native @Type("void") void didFinish(@Type("BOOL") boolean completed);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIActivity_Class/Reference/Reference.html#//apple_ref/occ/instm/UIActivity/activityImage">- (UIImage *)activityImage</a>
     * @since Available in iOS 6.0 and later.
     */
    @Bind("activityImage") public native @Type("UIImage *") UIImage getImage();
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIActivity_Class/Reference/Reference.html#//apple_ref/occ/instm/UIActivity/activityTitle">- (NSString *)activityTitle</a>
     * @since Available in iOS 6.0 and later.
     */
    @Bind("activityTitle") public native @Type("NSString *") String getTitle();
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIActivity_Class/Reference/Reference.html#//apple_ref/occ/instm/UIActivity/activityType">- (NSString *)activityType</a>
     * @since Available in iOS 6.0 and later.
     */
    @Bind("activityType") public native @Type("NSString *") String getType();
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIActivity_Class/Reference/Reference.html#//apple_ref/occ/instm/UIActivity/activityViewController">- (UIViewController *)activityViewController</a>
     * @since Available in iOS 6.0 and later.
     */
    @Bind("activityViewController") public native @Type("UIViewController *") UIViewController getViewController();
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIActivity_Class/Reference/Reference.html#//apple_ref/occ/instm/UIActivity/performActivity">- (void)performActivity</a>
     * @since Available in iOS 6.0 and later.
     */
    @Bind("performActivity") public native @Type("void") void perform();
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIActivity_Class/Reference/Reference.html#//apple_ref/occ/instm/UIActivity/prepareWithActivityItems:">- (void)prepareWithActivityItems:(NSArray *)activityItems</a>
     * @since Available in iOS 6.0 and later.
     */
    @Bind("prepareWithActivityItems:") public native @Type("void") void prepare(@Type("NSArray *") NSArray activityItems);
    /*</methods>*/

}
