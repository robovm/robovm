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
 *   @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIKeyInput_Protocol/Reference/Reference.html">UIKeyInput Protocol Reference</a>
 *   @since Available in iOS 3.2 and later.
 * </div>
 */
public interface /*<name>*/ UIKeyInput /*</name>*/ /*<implements>*/ extends UITextInputTraits, NSObjectProtocol /*</implements>*/ {

    /*<properties>*/
    
    /*</properties>*/
    /*<methods>*/
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIKeyInput_Protocol/Reference/Reference.html#//apple_ref/occ/intfm/UIKeyInput/deleteBackward">- (void)deleteBackward</a>
     * @since Available in iOS 3.2 and later.
     */
    void deleteBackward();
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIKeyInput_Protocol/Reference/Reference.html#//apple_ref/occ/intfm/UIKeyInput/hasText">- (BOOL)hasText</a>
     * @since Available in iOS 3.2 and later.
     */
    boolean hasText();
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIKeyInput_Protocol/Reference/Reference.html#//apple_ref/occ/intfm/UIKeyInput/insertText:">- (void)insertText:(NSString *)text</a>
     * @since Available in iOS 3.2 and later.
     */
    void insertText(String text);
    /*</methods>*/
    /*<adapter>*/
    public static class Adapter extends UITextInputTraits.Adapter implements UIKeyInput {
        @NotImplemented("deleteBackward") public void deleteBackward() { throw new UnsupportedOperationException(); }
        @NotImplemented("hasText") public boolean hasText() { throw new UnsupportedOperationException(); }
        @NotImplemented("insertText:") public void insertText(String text) { throw new UnsupportedOperationException(); }
    }
    /*</adapter>*/
    /*<callbacks>*/
    static class Callbacks {
        @Callback @BindSelector("deleteBackward") public static void deleteBackward(UIKeyInput __self__, Selector __cmd__) { __self__.deleteBackward(); }
        @Callback @BindSelector("hasText") public static boolean hasText(UIKeyInput __self__, Selector __cmd__) { return __self__.hasText(); }
        @Callback @BindSelector("insertText:") public static void insertText(UIKeyInput __self__, Selector __cmd__, String text) { __self__.insertText(text); }
    }
    /*</callbacks>*/

}
