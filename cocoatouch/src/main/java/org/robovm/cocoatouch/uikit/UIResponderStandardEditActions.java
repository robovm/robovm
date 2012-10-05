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
 *   @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIResponderStandardEditActions_Protocol/UIResponderStandardEditActions.html">UIResponderStandardEditActions Protocol Reference</a>
 * </div>
 */
@Protocol
public interface /*<name>*/ UIResponderStandardEditActions /*</name>*/ /*<implements>*/ /*</implements>*/ {

    /*<properties>*/
    
    /*</properties>*/
    /*<methods>*/
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIResponderStandardEditActions_Protocol/UIResponderStandardEditActions.html#//apple_ref/occ/instm/NSObject/copy:">- (void)copy:(id)sender</a>
     * @since Available in iOS 3.0 and later.
     */
    void copy(NSObject sender);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIResponderStandardEditActions_Protocol/UIResponderStandardEditActions.html#//apple_ref/occ/instm/NSObject/cut:">- (void)cut:(id)sender</a>
     * @since Available in iOS 3.0 and later.
     */
    void cut(NSObject sender);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIResponderStandardEditActions_Protocol/UIResponderStandardEditActions.html#//apple_ref/occ/instm/NSObject/delete:">- (void)delete:(id)sender</a>
     * @since Available in iOS 3.2 and later.
     */
    void delete(NSObject sender);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIResponderStandardEditActions_Protocol/UIResponderStandardEditActions.html#//apple_ref/occ/instm/NSObject/makeTextWritingDirectionLeftToRight:">- (void)makeTextWritingDirectionLeftToRight:(id)sender</a>
     * @since Available in iOS 5.0 and later.
     */
    void makeTextWritingDirectionLeftToRight(NSObject sender);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIResponderStandardEditActions_Protocol/UIResponderStandardEditActions.html#//apple_ref/occ/instm/NSObject/makeTextWritingDirectionRightToLeft:">- (void)makeTextWritingDirectionRightToLeft:(id)sender</a>
     * @since Available in iOS 5.0 and later.
     */
    void makeTextWritingDirectionRightToLeft(NSObject sender);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIResponderStandardEditActions_Protocol/UIResponderStandardEditActions.html#//apple_ref/occ/instm/NSObject/paste:">- (void)paste:(id)sender</a>
     * @since Available in iOS 3.0 and later.
     */
    void paste(NSObject sender);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIResponderStandardEditActions_Protocol/UIResponderStandardEditActions.html#//apple_ref/occ/instm/NSObject/select:">- (void)select:(id)sender</a>
     * @since Available in iOS 3.0 and later.
     */
    void select(NSObject sender);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIResponderStandardEditActions_Protocol/UIResponderStandardEditActions.html#//apple_ref/occ/instm/NSObject/selectAll:">- (void)selectAll:(id)sender</a>
     * @since Available in iOS 3.0 and later.
     */
    void selectAll(NSObject sender);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIResponderStandardEditActions_Protocol/UIResponderStandardEditActions.html#//apple_ref/occ/instm/NSObject/toggleBoldface:">- (void)toggleBoldface:(id)sender</a>
     * @since Available in iOS 6.0 and later.
     */
    void toggleBoldface(NSObject sender);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIResponderStandardEditActions_Protocol/UIResponderStandardEditActions.html#//apple_ref/occ/instm/NSObject/toggleItalics:">- (void)toggleItalics:(id)sender</a>
     * @since Available in iOS 6.0 and later.
     */
    void toggleItalics(NSObject sender);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIResponderStandardEditActions_Protocol/UIResponderStandardEditActions.html#//apple_ref/occ/instm/NSObject/toggleUnderline:">- (void)toggleUnderline:(id)sender</a>
     * @since Available in iOS 6.0 and later.
     */
    void toggleUnderline(NSObject sender);
    /*</methods>*/

}
