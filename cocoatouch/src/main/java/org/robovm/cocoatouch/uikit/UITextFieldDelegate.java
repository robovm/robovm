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
 *   @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UITextFieldDelegate_Protocol/UITextFieldDelegate/UITextFieldDelegate.html">UITextFieldDelegate Protocol Reference</a>
 *   @since Available in iOS 2.0 and later.
 * </div>
 */
public interface /*<name>*/ UITextFieldDelegate /*</name>*/ /*<implements>*/ extends NSObjectProtocol /*</implements>*/ {

    /*<properties>*/
    
    /*</properties>*/
    /*<methods>*/
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UITextFieldDelegate_Protocol/UITextFieldDelegate/UITextFieldDelegate.html#//apple_ref/occ/intfm/UITextFieldDelegate/textFieldDidBeginEditing:">- (void)textFieldDidBeginEditing:(UITextField *)textField</a>
     * @since Available in iOS 2.0 and later.
     */
    void didBeginEditing(UITextField textField);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UITextFieldDelegate_Protocol/UITextFieldDelegate/UITextFieldDelegate.html#//apple_ref/occ/intfm/UITextFieldDelegate/textFieldDidEndEditing:">- (void)textFieldDidEndEditing:(UITextField *)textField</a>
     * @since Available in iOS 2.0 and later.
     */
    void didEndEditing(UITextField textField);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UITextFieldDelegate_Protocol/UITextFieldDelegate/UITextFieldDelegate.html#//apple_ref/occ/intfm/UITextFieldDelegate/textFieldShouldBeginEditing:">- (BOOL)textFieldShouldBeginEditing:(UITextField *)textField</a>
     * @since Available in iOS 2.0 and later.
     */
    boolean shouldBeginEditing(UITextField textField);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UITextFieldDelegate_Protocol/UITextFieldDelegate/UITextFieldDelegate.html#//apple_ref/occ/intfm/UITextFieldDelegate/textField:shouldChangeCharactersInRange:replacementString:">- (BOOL)textField:(UITextField *)textField shouldChangeCharactersInRange:(NSRange)range replacementString:(NSString *)string</a>
     * @since Available in iOS 2.0 and later.
     */
    boolean shouldChangeCharacters(UITextField textField, NSRange range, String string);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UITextFieldDelegate_Protocol/UITextFieldDelegate/UITextFieldDelegate.html#//apple_ref/occ/intfm/UITextFieldDelegate/textFieldShouldClear:">- (BOOL)textFieldShouldClear:(UITextField *)textField</a>
     * @since Available in iOS 2.0 and later.
     */
    boolean shouldClear(UITextField textField);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UITextFieldDelegate_Protocol/UITextFieldDelegate/UITextFieldDelegate.html#//apple_ref/occ/intfm/UITextFieldDelegate/textFieldShouldEndEditing:">- (BOOL)textFieldShouldEndEditing:(UITextField *)textField</a>
     * @since Available in iOS 2.0 and later.
     */
    boolean shouldEndEditing(UITextField textField);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UITextFieldDelegate_Protocol/UITextFieldDelegate/UITextFieldDelegate.html#//apple_ref/occ/intfm/UITextFieldDelegate/textFieldShouldReturn:">- (BOOL)textFieldShouldReturn:(UITextField *)textField</a>
     * @since Available in iOS 2.0 and later.
     */
    boolean shouldReturn(UITextField textField);
    /*</methods>*/
    /*<adapter>*/
    public static class Adapter extends NSObject implements UITextFieldDelegate {
        @NotImplemented("textFieldDidBeginEditing:") public void didBeginEditing(UITextField textField) { throw new UnsupportedOperationException(); }
        @NotImplemented("textFieldDidEndEditing:") public void didEndEditing(UITextField textField) { throw new UnsupportedOperationException(); }
        @NotImplemented("textFieldShouldBeginEditing:") public boolean shouldBeginEditing(UITextField textField) { throw new UnsupportedOperationException(); }
        @NotImplemented("textField:shouldChangeCharactersInRange:replacementString:") public boolean shouldChangeCharacters(UITextField textField, NSRange range, String string) { throw new UnsupportedOperationException(); }
        @NotImplemented("textFieldShouldClear:") public boolean shouldClear(UITextField textField) { throw new UnsupportedOperationException(); }
        @NotImplemented("textFieldShouldEndEditing:") public boolean shouldEndEditing(UITextField textField) { throw new UnsupportedOperationException(); }
        @NotImplemented("textFieldShouldReturn:") public boolean shouldReturn(UITextField textField) { throw new UnsupportedOperationException(); }
    }
    /*</adapter>*/
    /*<callbacks>*/
    static class Callbacks {
        @Callback @BindSelector("textFieldDidBeginEditing:") public static void didBeginEditing(UITextFieldDelegate __self__, Selector __cmd__, UITextField textField) { __self__.didBeginEditing(textField); }
        @Callback @BindSelector("textFieldDidEndEditing:") public static void didEndEditing(UITextFieldDelegate __self__, Selector __cmd__, UITextField textField) { __self__.didEndEditing(textField); }
        @Callback @BindSelector("textFieldShouldBeginEditing:") public static boolean shouldBeginEditing(UITextFieldDelegate __self__, Selector __cmd__, UITextField textField) { return __self__.shouldBeginEditing(textField); }
        @Callback @BindSelector("textField:shouldChangeCharactersInRange:replacementString:") public static boolean shouldChangeCharacters(UITextFieldDelegate __self__, Selector __cmd__, UITextField textField, @ByVal NSRange range, String string) { return __self__.shouldChangeCharacters(textField, range, string); }
        @Callback @BindSelector("textFieldShouldClear:") public static boolean shouldClear(UITextFieldDelegate __self__, Selector __cmd__, UITextField textField) { return __self__.shouldClear(textField); }
        @Callback @BindSelector("textFieldShouldEndEditing:") public static boolean shouldEndEditing(UITextFieldDelegate __self__, Selector __cmd__, UITextField textField) { return __self__.shouldEndEditing(textField); }
        @Callback @BindSelector("textFieldShouldReturn:") public static boolean shouldReturn(UITextFieldDelegate __self__, Selector __cmd__, UITextField textField) { return __self__.shouldReturn(textField); }
    }
    /*</callbacks>*/

}
