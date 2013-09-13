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
 *   @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UITextInputMode_Class/Reference/Reference.html">UITextInputMode Class Reference</a>
 *   @since Available in iOS 4.2 and later.
 * </div>
 */
/*<library>*/@Library("UIKit")/*</library>*/
@NativeClass public class /*<name>*/ UITextInputMode /*</name>*/ 
    extends /*<extends>*/ NSObject /*</extends>*/ 
    /*<implements>*/ /*</implements>*/ {

    static {
        ObjCRuntime.bind(/*<name>*/ UITextInputMode /*</name>*/.class);
    }

    private static final ObjCClass objCClass = ObjCClass.getByType(/*<name>*/ UITextInputMode /*</name>*/.class);

    /*<constructors>*/
    protected UITextInputMode(SkipInit skipInit) { super(skipInit); }
    public UITextInputMode() {}
    
    /*</constructors>*/
    /*<properties>*/
    
    private static final Selector primaryLanguage = Selector.register("primaryLanguage");
    @Bridge private native static String objc_getPrimaryLanguage(UITextInputMode __self__, Selector __cmd__);
    @Bridge private native static String objc_getPrimaryLanguageSuper(ObjCSuper __super__, Selector __cmd__);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UITextInputMode_Class/Reference/Reference.html#//apple_ref/occ/instp/UITextInputMode/primaryLanguage">@property (nonatomic, readonly, retain) NSString *primaryLanguage</a>
     * @since Available in iOS 4.2 and later.
     */
    public String getPrimaryLanguage() {
        if (customClass) { return objc_getPrimaryLanguageSuper(getSuper(), primaryLanguage); } else { return objc_getPrimaryLanguage(this, primaryLanguage); }
    }
    /*</properties>*/
    /*<methods>*/
    
    private static final Selector activeInputModes = Selector.register("activeInputModes");
    @Bridge private native static NSArray objc_getActiveInputModes(ObjCClass __self__, Selector __cmd__);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UITextInputMode_Class/Reference/Reference.html#//apple_ref/occ/clm/UITextInputMode/activeInputModes">+ (NSArray *)activeInputModes</a>
     * @since Available in iOS 5.0 and later.
     */
    public static NSArray getActiveInputModes() {
        return objc_getActiveInputModes(objCClass, activeInputModes);
    }
    
    private static final Selector currentInputMode = Selector.register("currentInputMode");
    @Bridge private native static UITextInputMode objc_getCurrentInputMode(ObjCClass __self__, Selector __cmd__);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UITextInputMode_Class/Reference/Reference.html#//apple_ref/occ/clm/UITextInputMode/currentInputMode">+ (UITextInputMode *)currentInputMode</a>
     * @since Available in iOS 4.2 and later.
     */
    public static UITextInputMode getCurrentInputMode() {
        return objc_getCurrentInputMode(objCClass, currentInputMode);
    }
    /*</methods>*/
    /*<callbacks>*/
    static class Callbacks {
        @Callback @BindSelector("primaryLanguage") public static String getPrimaryLanguage(UITextInputMode __self__, Selector __cmd__) { return __self__.getPrimaryLanguage(); }
    }
    /*</callbacks>*/

}
