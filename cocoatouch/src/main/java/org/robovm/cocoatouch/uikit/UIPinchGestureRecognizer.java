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
 *   @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIPinchGestureRecognizer_Class/Reference/Reference.html">UIPinchGestureRecognizer Class Reference</a>
 *   @since Available in iOS 3.2 and later.
 * </div>
 */
/*<library>*/@Library("UIKit")/*</library>*/
public class /*<name>*/ UIPinchGestureRecognizer /*</name>*/ 
    extends /*<extends>*/ UIGestureRecognizer /*</extends>*/ 
    /*<implements>*/ /*</implements>*/ {

    static {
        ObjCRuntime.bind(/*<name>*/ UIPinchGestureRecognizer /*</name>*/.class);
    }

    /*<constructors>*/
    public UIPinchGestureRecognizer() {}
    
    /*</constructors>*/
    /*<properties>*/
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIPinchGestureRecognizer_Class/Reference/Reference.html#//apple_ref/occ/instp/UIPinchGestureRecognizer/scale">@property(nonatomic) CGFloat scale</a>
     * @since Available in iOS 3.2 and later.
     */
    @Bind("scale") public native @Type("CGFloat") float getScale();
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIPinchGestureRecognizer_Class/Reference/Reference.html#//apple_ref/occ/instp/UIPinchGestureRecognizer/scale">@property(nonatomic) CGFloat scale</a>
     * @since Available in iOS 3.2 and later.
     */
    @Bind("setScale:") public native void setScale(@Type("CGFloat") float v);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIPinchGestureRecognizer_Class/Reference/Reference.html#//apple_ref/occ/instp/UIPinchGestureRecognizer/velocity">@property(nonatomic, readonly) CGFloat velocity</a>
     * @since Available in iOS 3.2 and later.
     */
    @Bind("velocity") public native @Type("CGFloat") float getVelocity();
    /*</properties>*/
    /*<methods>*/
    
    /*</methods>*/

}
