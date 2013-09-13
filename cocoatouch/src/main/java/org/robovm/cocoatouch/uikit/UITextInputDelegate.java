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
 *   @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UITextInputDelegate_Protocol/Reference/Reference.html">UITextInputDelegate Protocol Reference</a>
 *   @since Available in iOS 3.2 and later.
 * </div>
 */
public interface /*<name>*/ UITextInputDelegate /*</name>*/ /*<implements>*/ extends NSObjectProtocol /*</implements>*/ {

    /*<properties>*/
    
    /*</properties>*/
    /*<methods>*/
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UITextInputDelegate_Protocol/Reference/Reference.html#//apple_ref/occ/intfm/UITextInputDelegate/selectionDidChange:">- (void)selectionDidChange:(id &amp;lt;UITextInput&amp;gt;)textInput</a>
     * @since Available in iOS 3.2 and later.
     */
    void selectionDidChange(UITextInput textInput);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UITextInputDelegate_Protocol/Reference/Reference.html#//apple_ref/occ/intfm/UITextInputDelegate/selectionWillChange:">- (void)selectionWillChange:(id &amp;lt;UITextInput&amp;gt;)textInput</a>
     * @since Available in iOS 3.2 and later.
     */
    void selectionWillChange(UITextInput textInput);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UITextInputDelegate_Protocol/Reference/Reference.html#//apple_ref/occ/intfm/UITextInputDelegate/textDidChange:">- (void)textDidChange:(id &amp;lt;UITextInput&amp;gt;)textInput</a>
     * @since Available in iOS 3.2 and later.
     */
    void textDidChange(UITextInput textInput);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UITextInputDelegate_Protocol/Reference/Reference.html#//apple_ref/occ/intfm/UITextInputDelegate/textWillChange:">- (void)textWillChange:(id &amp;lt;UITextInput&amp;gt;)textInput</a>
     * @since Available in iOS 3.2 and later.
     */
    void textWillChange(UITextInput textInput);
    /*</methods>*/
    /*<adapter>*/
    public static class Adapter extends NSObject implements UITextInputDelegate {
        @NotImplemented("selectionDidChange:") public void selectionDidChange(UITextInput textInput) { throw new UnsupportedOperationException(); }
        @NotImplemented("selectionWillChange:") public void selectionWillChange(UITextInput textInput) { throw new UnsupportedOperationException(); }
        @NotImplemented("textDidChange:") public void textDidChange(UITextInput textInput) { throw new UnsupportedOperationException(); }
        @NotImplemented("textWillChange:") public void textWillChange(UITextInput textInput) { throw new UnsupportedOperationException(); }
    }
    /*</adapter>*/
    /*<callbacks>*/
    static class Callbacks {
        @Callback @BindSelector("selectionDidChange:") public static void selectionDidChange(UITextInputDelegate __self__, Selector __cmd__, UITextInput textInput) { __self__.selectionDidChange(textInput); }
        @Callback @BindSelector("selectionWillChange:") public static void selectionWillChange(UITextInputDelegate __self__, Selector __cmd__, UITextInput textInput) { __self__.selectionWillChange(textInput); }
        @Callback @BindSelector("textDidChange:") public static void textDidChange(UITextInputDelegate __self__, Selector __cmd__, UITextInput textInput) { __self__.textDidChange(textInput); }
        @Callback @BindSelector("textWillChange:") public static void textWillChange(UITextInputDelegate __self__, Selector __cmd__, UITextInput textInput) { __self__.textWillChange(textInput); }
    }
    /*</callbacks>*/

}
