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
 *   @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UITextViewDelegate_Protocol/Reference/UITextViewDelegate.html">UITextViewDelegate Protocol Reference</a>
 *   @since Available in iOS 2.0 and later.
 * </div>
 */
public interface /*<name>*/ UITextViewDelegate /*</name>*/ /*<implements>*/ extends UIScrollViewDelegate, NSObjectProtocol /*</implements>*/ {

    /*<properties>*/
    
    /*</properties>*/
    /*<methods>*/
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UITextViewDelegate_Protocol/Reference/UITextViewDelegate.html#//apple_ref/occ/intfm/UITextViewDelegate/textViewDidBeginEditing:">- (void)textViewDidBeginEditing:(UITextView *)textView</a>
     * @since Available in iOS 2.0 and later.
     */
    void didBeginEditing(UITextView textView);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UITextViewDelegate_Protocol/Reference/UITextViewDelegate.html#//apple_ref/occ/intfm/UITextViewDelegate/textViewDidChange:">- (void)textViewDidChange:(UITextView *)textView</a>
     * @since Available in iOS 2.0 and later.
     */
    void didChange(UITextView textView);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UITextViewDelegate_Protocol/Reference/UITextViewDelegate.html#//apple_ref/occ/intfm/UITextViewDelegate/textViewDidChangeSelection:">- (void)textViewDidChangeSelection:(UITextView *)textView</a>
     * @since Available in iOS 2.0 and later.
     */
    void didChangeSelection(UITextView textView);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UITextViewDelegate_Protocol/Reference/UITextViewDelegate.html#//apple_ref/occ/intfm/UITextViewDelegate/textViewDidEndEditing:">- (void)textViewDidEndEditing:(UITextView *)textView</a>
     * @since Available in iOS 2.0 and later.
     */
    void didEndEditing(UITextView textView);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UITextViewDelegate_Protocol/Reference/UITextViewDelegate.html#//apple_ref/occ/intfm/UITextViewDelegate/textViewShouldBeginEditing:">- (BOOL)textViewShouldBeginEditing:(UITextView *)textView</a>
     * @since Available in iOS 2.0 and later.
     */
    boolean shouldBeginEditing(UITextView textView);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UITextViewDelegate_Protocol/Reference/UITextViewDelegate.html#//apple_ref/occ/intfm/UITextViewDelegate/textView:shouldChangeTextInRange:replacementText:">- (BOOL)textView:(UITextView *)textView shouldChangeTextInRange:(NSRange)range replacementText:(NSString *)text</a>
     * @since Available in iOS 2.0 and later.
     */
    boolean shouldChangeCharacters(UITextView textView, NSRange range, String text);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UITextViewDelegate_Protocol/Reference/UITextViewDelegate.html#//apple_ref/occ/intfm/UITextViewDelegate/textViewShouldEndEditing:">- (BOOL)textViewShouldEndEditing:(UITextView *)textView</a>
     * @since Available in iOS 2.0 and later.
     */
    boolean shouldEndEditing(UITextView textView);
    /*</methods>*/
    /*<adapter>*/
    public static class Adapter extends UIScrollViewDelegate.Adapter implements UITextViewDelegate {
        @NotImplemented("textViewDidBeginEditing:") public void didBeginEditing(UITextView textView) { throw new UnsupportedOperationException(); }
        @NotImplemented("textViewDidChange:") public void didChange(UITextView textView) { throw new UnsupportedOperationException(); }
        @NotImplemented("textViewDidChangeSelection:") public void didChangeSelection(UITextView textView) { throw new UnsupportedOperationException(); }
        @NotImplemented("textViewDidEndEditing:") public void didEndEditing(UITextView textView) { throw new UnsupportedOperationException(); }
        @NotImplemented("textViewShouldBeginEditing:") public boolean shouldBeginEditing(UITextView textView) { throw new UnsupportedOperationException(); }
        @NotImplemented("textView:shouldChangeTextInRange:replacementText:") public boolean shouldChangeCharacters(UITextView textView, NSRange range, String text) { throw new UnsupportedOperationException(); }
        @NotImplemented("textViewShouldEndEditing:") public boolean shouldEndEditing(UITextView textView) { throw new UnsupportedOperationException(); }
    }
    /*</adapter>*/
    /*<callbacks>*/
    static class Callbacks {
        @Callback @BindSelector("textViewDidBeginEditing:") public static void didBeginEditing(UITextViewDelegate __self__, Selector __cmd__, UITextView textView) { __self__.didBeginEditing(textView); }
        @Callback @BindSelector("textViewDidChange:") public static void didChange(UITextViewDelegate __self__, Selector __cmd__, UITextView textView) { __self__.didChange(textView); }
        @Callback @BindSelector("textViewDidChangeSelection:") public static void didChangeSelection(UITextViewDelegate __self__, Selector __cmd__, UITextView textView) { __self__.didChangeSelection(textView); }
        @Callback @BindSelector("textViewDidEndEditing:") public static void didEndEditing(UITextViewDelegate __self__, Selector __cmd__, UITextView textView) { __self__.didEndEditing(textView); }
        @Callback @BindSelector("textViewShouldBeginEditing:") public static boolean shouldBeginEditing(UITextViewDelegate __self__, Selector __cmd__, UITextView textView) { return __self__.shouldBeginEditing(textView); }
        @Callback @BindSelector("textView:shouldChangeTextInRange:replacementText:") public static boolean shouldChangeCharacters(UITextViewDelegate __self__, Selector __cmd__, UITextView textView, @ByVal NSRange range, String text) { return __self__.shouldChangeCharacters(textView, range, text); }
        @Callback @BindSelector("textViewShouldEndEditing:") public static boolean shouldEndEditing(UITextViewDelegate __self__, Selector __cmd__, UITextView textView) { return __self__.shouldEndEditing(textView); }
    }
    /*</callbacks>*/

}
