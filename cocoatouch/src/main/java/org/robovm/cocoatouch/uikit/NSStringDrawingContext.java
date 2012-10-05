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
 *   @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../NSStringDrawingContext_class/Reference/Reference.html">NSStringDrawingContext Class Reference</a>
 *   @since Available in iOS 6.0 and later.
 * </div>
 */
/*<library>*/@Library("UIKit")/*</library>*/
public class /*<name>*/ NSStringDrawingContext /*</name>*/ 
    extends /*<extends>*/ NSObject /*</extends>*/ 
    /*<implements>*/ /*</implements>*/ {

    static {
        ObjCRuntime.bind(/*<name>*/ NSStringDrawingContext /*</name>*/.class);
    }

    private static final ObjCClass objCClass = ObjCClass.getByType(/*<name>*/ NSStringDrawingContext /*</name>*/.class);

    /*<constructors>*/
    protected NSStringDrawingContext(SkipInit skipInit) { super(skipInit); }
    public NSStringDrawingContext() {}
    
    /*</constructors>*/
    /*<properties>*/
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../NSStringDrawingContext_class/Reference/Reference.html#//apple_ref/occ/instp/NSStringDrawingContext/actualScaleFactor">@property(nonatomic, readonly) CGFloat actualScaleFactor</a>
     * @since Available in iOS 6.0 and later.
     */
    @Bind("actualScaleFactor") public native float getActualScaleFactor();
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../NSStringDrawingContext_class/Reference/Reference.html#//apple_ref/occ/instp/NSStringDrawingContext/actualTrackingAdjustment">@property(nonatomic, readonly) CGFloat actualTrackingAdjustment</a>
     * @since Available in iOS 6.0 and later.
     */
    @Bind("actualTrackingAdjustment") public native float getActualTrackingAdjustment();
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../NSStringDrawingContext_class/Reference/Reference.html#//apple_ref/occ/instp/NSStringDrawingContext/minimumScaleFactor">@property(nonatomic) CGFloat minimumScaleFactor</a>
     * @since Available in iOS 6.0 and later.
     */
    @Bind("minimumScaleFactor") public native float getMinimumScaleFactor();
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../NSStringDrawingContext_class/Reference/Reference.html#//apple_ref/occ/instp/NSStringDrawingContext/minimumScaleFactor">@property(nonatomic) CGFloat minimumScaleFactor</a>
     * @since Available in iOS 6.0 and later.
     */
    @Bind("setMinimumScaleFactor:") public native void setMinimumScaleFactor(float v);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../NSStringDrawingContext_class/Reference/Reference.html#//apple_ref/occ/instp/NSStringDrawingContext/minimumTrackingAdjustment">@property(nonatomic) CGFloat minimumTrackingAdjustment</a>
     * @since Available in iOS 6.0 and later.
     */
    @Bind("minimumTrackingAdjustment") public native float getMinimumTrackingAdjustment();
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../NSStringDrawingContext_class/Reference/Reference.html#//apple_ref/occ/instp/NSStringDrawingContext/minimumTrackingAdjustment">@property(nonatomic) CGFloat minimumTrackingAdjustment</a>
     * @since Available in iOS 6.0 and later.
     */
    @Bind("setMinimumTrackingAdjustment:") public native void setMinimumTrackingAdjustment(float v);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../NSStringDrawingContext_class/Reference/Reference.html#//apple_ref/occ/instp/NSStringDrawingContext/totalBounds">@property(nonatomic, readonly) CGRect totalBounds</a>
     * @since Available in iOS 6.0 and later.
     */
    @Bind("totalBounds") public native CGRect getTotalBounds();
    /*</properties>*/
    /*<methods>*/
    
    /*</methods>*/

}
