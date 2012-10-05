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
 *   @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../../../Cocoa/Reference/ApplicationKit/Classes/NSShadow_Class/Reference/Reference.html">NSShadow Class Reference</a>
 *   @since Available in iOS 6.0 and later.
 * </div>
 */
/*<library>*/@Library("UIKit")/*</library>*/
public class /*<name>*/ NSShadow /*</name>*/ 
    extends /*<extends>*/ NSObject /*</extends>*/ 
    /*<implements>*/ /*</implements>*/ {

    static {
        ObjCRuntime.bind(/*<name>*/ NSShadow /*</name>*/.class);
    }

    /*<constructors>*/
    public NSShadow() {}
    
    /*</constructors>*/
    /*<properties>*/
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../../../Cocoa/Reference/ApplicationKit/Classes/NSShadow_Class/Reference/Reference.html#//apple_ref/occ/instp/NSShadow/shadowBlurRadius">@property (nonatomic, assign) CGFloat shadowBlurRadius</a>
     * @since Available in iOS 6.0 and later.
     */
    @Bind("shadowBlurRadius") public native @Type("CGFloat") float getShadowBlurRadius();
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../../../Cocoa/Reference/ApplicationKit/Classes/NSShadow_Class/Reference/Reference.html#//apple_ref/occ/instp/NSShadow/shadowBlurRadius">@property (nonatomic, assign) CGFloat shadowBlurRadius</a>
     * @since Available in iOS 6.0 and later.
     */
    @Bind("setShadowBlurRadius:") public native void setShadowBlurRadius(@Type("CGFloat") float v);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../../../Cocoa/Reference/ApplicationKit/Classes/NSShadow_Class/Reference/Reference.html#//apple_ref/occ/instp/NSShadow/shadowColor">@property (nonatomic, retain) id shadowColor</a>
     * @since Available in iOS 6.0 and later.
     */
    @Bind("shadowColor") public native @Type("id") NSObject getShadowColor();
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../../../Cocoa/Reference/ApplicationKit/Classes/NSShadow_Class/Reference/Reference.html#//apple_ref/occ/instp/NSShadow/shadowColor">@property (nonatomic, retain) id shadowColor</a>
     * @since Available in iOS 6.0 and later.
     */
    @Bind("setShadowColor:") public native void setShadowColor(@Type("id") NSObject v);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../../../Cocoa/Reference/ApplicationKit/Classes/NSShadow_Class/Reference/Reference.html#//apple_ref/occ/instp/NSShadow/shadowOffset">@property (nonatomic, assign) CGSize shadowOffset</a>
     * @since Available in iOS 6.0 and later.
     */
    @Bind("shadowOffset") public native @Type("CGSize") CGSize getShadowOffset();
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../../../Cocoa/Reference/ApplicationKit/Classes/NSShadow_Class/Reference/Reference.html#//apple_ref/occ/instp/NSShadow/shadowOffset">@property (nonatomic, assign) CGSize shadowOffset</a>
     * @since Available in iOS 6.0 and later.
     */
    @Bind("setShadowOffset:") public native void setShadowOffset(@Type("CGSize") CGSize v);
    /*</properties>*/
    /*<methods>*/
    
    /*</methods>*/

}
