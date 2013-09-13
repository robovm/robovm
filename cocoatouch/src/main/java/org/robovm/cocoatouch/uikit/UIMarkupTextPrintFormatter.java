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
 *   @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIMarkupTextPrintFormatter_Class/Reference/Reference.html">UIMarkupTextPrintFormatter Class Reference</a>
 *   @since Available in iOS 4.2 and later.
 * </div>
 */
/*<library>*/@Library("UIKit")/*</library>*/
@NativeClass public class /*<name>*/ UIMarkupTextPrintFormatter /*</name>*/ 
    extends /*<extends>*/ UIPrintFormatter /*</extends>*/ 
    /*<implements>*/ /*</implements>*/ {

    static {
        ObjCRuntime.bind(/*<name>*/ UIMarkupTextPrintFormatter /*</name>*/.class);
    }

    private static final ObjCClass objCClass = ObjCClass.getByType(/*<name>*/ UIMarkupTextPrintFormatter /*</name>*/.class);

    /*<constructors>*/
    protected UIMarkupTextPrintFormatter(SkipInit skipInit) { super(skipInit); }
    public UIMarkupTextPrintFormatter() {}
    
    private static final Selector initWithMarkupText$ = Selector.register("initWithMarkupText:");
    @Bridge private native static @Pointer long objc_initWithMarkupText(UIMarkupTextPrintFormatter __self__, Selector __cmd__, String markupText);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIMarkupTextPrintFormatter_Class/Reference/Reference.html#//apple_ref/occ/instm/UIMarkupTextPrintFormatter/initWithMarkupText:">- (id)initWithMarkupText:(NSString *)markupText</a>
     * @since Available in iOS 4.2 and later.
     */
    public UIMarkupTextPrintFormatter(String markupText) {
        super((SkipInit) null);
        initObject(objc_initWithMarkupText(this, initWithMarkupText$, markupText));
    }
    /*</constructors>*/
    /*<properties>*/
    
    private static final Selector markupText = Selector.register("markupText");
    @Bridge private native static String objc_getMarkupText(UIMarkupTextPrintFormatter __self__, Selector __cmd__);
    @Bridge private native static String objc_getMarkupTextSuper(ObjCSuper __super__, Selector __cmd__);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIMarkupTextPrintFormatter_Class/Reference/Reference.html#//apple_ref/occ/instp/UIMarkupTextPrintFormatter/markupText">@property(nonatomic, copy) NSString *markupText</a>
     * @since Available in iOS 4.2 and later.
     */
    public String getMarkupText() {
        if (customClass) { return objc_getMarkupTextSuper(getSuper(), markupText); } else { return objc_getMarkupText(this, markupText); }
    }
    
    private static final Selector setMarkupText$ = Selector.register("setMarkupText:");
    @Bridge private native static void objc_setMarkupText(UIMarkupTextPrintFormatter __self__, Selector __cmd__, String markupText);
    @Bridge private native static void objc_setMarkupTextSuper(ObjCSuper __super__, Selector __cmd__, String markupText);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIMarkupTextPrintFormatter_Class/Reference/Reference.html#//apple_ref/occ/instp/UIMarkupTextPrintFormatter/markupText">@property(nonatomic, copy) NSString *markupText</a>
     * @since Available in iOS 4.2 and later.
     */
    public void setMarkupText(String markupText) {
        if (customClass) { objc_setMarkupTextSuper(getSuper(), setMarkupText$, markupText); } else { objc_setMarkupText(this, setMarkupText$, markupText); }
    }
    /*</properties>*/
    /*<methods>*/
    
    /*</methods>*/
    /*<callbacks>*/
    static class Callbacks {
        @Callback @BindSelector("markupText") public static String getMarkupText(UIMarkupTextPrintFormatter __self__, Selector __cmd__) { return __self__.getMarkupText(); }
        @Callback @BindSelector("setMarkupText:") public static void setMarkupText(UIMarkupTextPrintFormatter __self__, Selector __cmd__, String markupText) { __self__.setMarkupText(markupText); }
    }
    /*</callbacks>*/

}
