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
 *   @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIStoryboardPopoverSegue_Class/Reference/Reference.html">UIStoryboardPopoverSegue Class Reference</a>
 *   @since Available in iOS 5.0 and later.
 * </div>
 */
/*<library>*/@Library("UIKit")/*</library>*/
@NativeClass public class /*<name>*/ UIStoryboardPopoverSegue /*</name>*/ 
    extends /*<extends>*/ UIStoryboardSegue /*</extends>*/ 
    /*<implements>*/ /*</implements>*/ {

    static {
        ObjCRuntime.bind(/*<name>*/ UIStoryboardPopoverSegue /*</name>*/.class);
    }

    private static final ObjCClass objCClass = ObjCClass.getByType(/*<name>*/ UIStoryboardPopoverSegue /*</name>*/.class);

    /*<constructors>*/
    protected UIStoryboardPopoverSegue(SkipInit skipInit) { super(skipInit); }
    public UIStoryboardPopoverSegue() {}
    
    /*</constructors>*/
    /*<properties>*/
    
    private static final Selector popoverController = Selector.register("popoverController");
    @Bridge private native static UIPopoverController objc_getPopoverController(UIStoryboardPopoverSegue __self__, Selector __cmd__);
    @Bridge private native static UIPopoverController objc_getPopoverControllerSuper(ObjCSuper __super__, Selector __cmd__);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIStoryboardPopoverSegue_Class/Reference/Reference.html#//apple_ref/occ/instp/UIStoryboardPopoverSegue/popoverController">@property(nonatomic, retain, readonly) UIPopoverController *popoverController</a>
     * @since Available in iOS 5.0 and later.
     */
    public UIPopoverController getPopoverController() {
        if (customClass) { return objc_getPopoverControllerSuper(getSuper(), popoverController); } else { return objc_getPopoverController(this, popoverController); }
    }
    /*</properties>*/
    /*<methods>*/
    
    /*</methods>*/
    /*<callbacks>*/
    static class Callbacks {
        @Callback @BindSelector("popoverController") public static UIPopoverController getPopoverController(UIStoryboardPopoverSegue __self__, Selector __cmd__) { return __self__.getPopoverController(); }
    }
    /*</callbacks>*/

}
