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
 *   @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UITextRange_Class/Reference/Reference.html">UITextRange Class Reference</a>
 *   @since Available in iOS 3.2 and later.
 * </div>
 */
/*<library>*/@Library("UIKit")/*</library>*/
@NativeClass public class /*<name>*/ UITextRange /*</name>*/ 
    extends /*<extends>*/ NSObject /*</extends>*/ 
    /*<implements>*/ /*</implements>*/ {

    static {
        ObjCRuntime.bind(/*<name>*/ UITextRange /*</name>*/.class);
    }

    private static final ObjCClass objCClass = ObjCClass.getByType(/*<name>*/ UITextRange /*</name>*/.class);

    /*<constructors>*/
    protected UITextRange(SkipInit skipInit) { super(skipInit); }
    public UITextRange() {}
    
    /*</constructors>*/
    /*<properties>*/
    
    private static final Selector isEmpty = Selector.register("isEmpty");
    @Bridge private native static boolean objc_isEmpty(UITextRange __self__, Selector __cmd__);
    @Bridge private native static boolean objc_isEmptySuper(ObjCSuper __super__, Selector __cmd__);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UITextRange_Class/Reference/Reference.html#//apple_ref/occ/instp/UITextRange/empty">@property(nonatomic, readonly, getter=isEmpty) BOOL empty</a>
     * @since Available in iOS 3.2 and later.
     */
    public boolean isEmpty() {
        if (customClass) { return objc_isEmptySuper(getSuper(), isEmpty); } else { return objc_isEmpty(this, isEmpty); }
    }
    
    private static final Selector end = Selector.register("end");
    @Bridge private native static UITextPosition objc_getEnd(UITextRange __self__, Selector __cmd__);
    @Bridge private native static UITextPosition objc_getEndSuper(ObjCSuper __super__, Selector __cmd__);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UITextRange_Class/Reference/Reference.html#//apple_ref/occ/instp/UITextRange/end">@property(nonatomic, readonly) UITextPosition *end</a>
     * @since Available in iOS 3.2 and later.
     */
    public UITextPosition getEnd() {
        if (customClass) { return objc_getEndSuper(getSuper(), end); } else { return objc_getEnd(this, end); }
    }
    
    private static final Selector start = Selector.register("start");
    @Bridge private native static UITextPosition objc_getStart(UITextRange __self__, Selector __cmd__);
    @Bridge private native static UITextPosition objc_getStartSuper(ObjCSuper __super__, Selector __cmd__);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UITextRange_Class/Reference/Reference.html#//apple_ref/occ/instp/UITextRange/start">@property(nonatomic, readonly) UITextPosition *start</a>
     * @since Available in iOS 3.2 and later.
     */
    public UITextPosition getStart() {
        if (customClass) { return objc_getStartSuper(getSuper(), start); } else { return objc_getStart(this, start); }
    }
    /*</properties>*/
    /*<methods>*/
    
    /*</methods>*/
    /*<callbacks>*/
    static class Callbacks {
        @Callback @BindSelector("isEmpty") public static boolean isEmpty(UITextRange __self__, Selector __cmd__) { return __self__.isEmpty(); }
        @Callback @BindSelector("end") public static UITextPosition getEnd(UITextRange __self__, Selector __cmd__) { return __self__.getEnd(); }
        @Callback @BindSelector("start") public static UITextPosition getStart(UITextRange __self__, Selector __cmd__) { return __self__.getStart(); }
    }
    /*</callbacks>*/

}
