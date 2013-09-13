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
 *   @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIViewPrintFormatter_Class/Reference/Reference.html">UIViewPrintFormatter Class Reference</a>
 *   @since Available in iOS 4.2 and later.
 * </div>
 */
/*<library>*/@Library("UIKit")/*</library>*/
@NativeClass public class /*<name>*/ UIViewPrintFormatter /*</name>*/ 
    extends /*<extends>*/ UIPrintFormatter /*</extends>*/ 
    /*<implements>*/ /*</implements>*/ {

    static {
        ObjCRuntime.bind(/*<name>*/ UIViewPrintFormatter /*</name>*/.class);
    }

    private static final ObjCClass objCClass = ObjCClass.getByType(/*<name>*/ UIViewPrintFormatter /*</name>*/.class);

    /*<constructors>*/
    protected UIViewPrintFormatter(SkipInit skipInit) { super(skipInit); }
    public UIViewPrintFormatter() {}
    
    /*</constructors>*/
    /*<properties>*/
    
    private static final Selector view = Selector.register("view");
    @Bridge private native static UIView objc_getView(UIViewPrintFormatter __self__, Selector __cmd__);
    @Bridge private native static UIView objc_getViewSuper(ObjCSuper __super__, Selector __cmd__);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIViewPrintFormatter_Class/Reference/Reference.html#//apple_ref/occ/instp/UIViewPrintFormatter/view">@property(nonatomic, readonly) UIView *view</a>
     * @since Available in iOS 4.2 and later.
     */
    public UIView getView() {
        if (customClass) { return objc_getViewSuper(getSuper(), view); } else { return objc_getView(this, view); }
    }
    /*</properties>*/
    /*<methods>*/
    
    /*</methods>*/
    /*<callbacks>*/
    static class Callbacks {
        @Callback @BindSelector("view") public static UIView getView(UIViewPrintFormatter __self__, Selector __cmd__) { return __self__.getView(); }
    }
    /*</callbacks>*/

}
