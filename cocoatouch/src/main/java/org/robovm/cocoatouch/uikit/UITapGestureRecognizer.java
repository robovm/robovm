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
 *   @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UITapGestureRecognizer_Class/Reference/Reference.html">UITapGestureRecognizer Class Reference</a>
 *   @since Available in iOS 3.2 and later.
 * </div>
 */
/*<library>*/@Library("UIKit")/*</library>*/
public class /*<name>*/ UITapGestureRecognizer /*</name>*/ 
    extends /*<extends>*/ UIGestureRecognizer /*</extends>*/ 
    /*<implements>*/ /*</implements>*/ {

    static {
        ObjCRuntime.bind(/*<name>*/ UITapGestureRecognizer /*</name>*/.class);
    }

    private static final ObjCClass objCClass = ObjCClass.getByType(/*<name>*/ UITapGestureRecognizer /*</name>*/.class);

    /*<constructors>*/
    protected UITapGestureRecognizer(SkipInit skipInit) { super(skipInit); }
    public UITapGestureRecognizer() {}
    
    /*</constructors>*/
    /*<properties>*/
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UITapGestureRecognizer_Class/Reference/Reference.html#//apple_ref/occ/instp/UITapGestureRecognizer/numberOfTapsRequired">@property(nonatomic) NSUInteger numberOfTapsRequired</a>
     * @since Available in iOS 3.2 and later.
     */
    @Bind("numberOfTapsRequired") public native int getNumberOfTapsRequired();
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UITapGestureRecognizer_Class/Reference/Reference.html#//apple_ref/occ/instp/UITapGestureRecognizer/numberOfTapsRequired">@property(nonatomic) NSUInteger numberOfTapsRequired</a>
     * @since Available in iOS 3.2 and later.
     */
    @Bind("setNumberOfTapsRequired:") public native void setNumberOfTapsRequired(int v);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UITapGestureRecognizer_Class/Reference/Reference.html#//apple_ref/occ/instp/UITapGestureRecognizer/numberOfTouchesRequired">@property(nonatomic) NSUInteger numberOfTouchesRequired</a>
     * @since Available in iOS 3.2 and later.
     */
    @Bind("numberOfTouchesRequired") public native int getNumberOfTouchesRequired();
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UITapGestureRecognizer_Class/Reference/Reference.html#//apple_ref/occ/instp/UITapGestureRecognizer/numberOfTouchesRequired">@property(nonatomic) NSUInteger numberOfTouchesRequired</a>
     * @since Available in iOS 3.2 and later.
     */
    @Bind("setNumberOfTouchesRequired:") public native void setNumberOfTouchesRequired(int v);
    /*</properties>*/
    /*<methods>*/
    
    /*</methods>*/

}
