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
 *   @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIKeyInput_Protocol/Reference/Reference.html">UIKeyInput Protocol Reference</a>
 *   @since Available in iOS 3.2 and later.
 * </div>
 */
@Protocol
public interface /*<name>*/ UIKeyInput /*</name>*/ /*<implements>*/ extends UITextInputTraits /*</implements>*/ {

    /*<properties>*/
    
    /*</properties>*/
    /*<methods>*/
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIKeyInput_Protocol/Reference/Reference.html#//apple_ref/occ/intfm/UIKeyInput/deleteBackward">- (void)deleteBackward</a>
     * @since Available in iOS 3.2 and later.
     */
    @Bind("deleteBackward") @Type("void") void deleteBackward();
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIKeyInput_Protocol/Reference/Reference.html#//apple_ref/occ/intfm/UIKeyInput/hasText">- (BOOL)hasText</a>
     * @since Available in iOS 3.2 and later.
     */
    @Bind("hasText") @Type("BOOL") boolean hasText();
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIKeyInput_Protocol/Reference/Reference.html#//apple_ref/occ/intfm/UIKeyInput/insertText:">- (void)insertText:(NSString *)text</a>
     * @since Available in iOS 3.2 and later.
     */
    @Bind("insertText:") @Type("void") void insertText(@Type("NSString *") String text);
    /*</methods>*/

}
