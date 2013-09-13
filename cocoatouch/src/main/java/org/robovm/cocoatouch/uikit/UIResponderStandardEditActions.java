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
 *   @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIResponderStandardEditActions_Protocol/UIResponderStandardEditActions.html">UIResponderStandardEditActions Protocol Reference</a>
 *   @since Available in iOS 3.0 and later.
 * </div>
 */
public interface /*<name>*/ UIResponderStandardEditActions /*</name>*/ /*<implements>*/ extends NSObjectProtocol /*</implements>*/ {

    /*<properties>*/
    
    /*</properties>*/
    /*<methods>*/
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIResponderStandardEditActions_Protocol/UIResponderStandardEditActions.html#//apple_ref/occ/instm/NSObject/copy:">- (void)copy:(id)sender</a>
     * @since Available in iOS 3.0 and later.
     */
    void copy(NSObject sender);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIResponderStandardEditActions_Protocol/UIResponderStandardEditActions.html#//apple_ref/occ/instm/NSObject/cut:">- (void)cut:(id)sender</a>
     * @since Available in iOS 3.0 and later.
     */
    void cut(NSObject sender);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIResponderStandardEditActions_Protocol/UIResponderStandardEditActions.html#//apple_ref/occ/instm/NSObject/delete:">- (void)delete:(id)sender</a>
     * @since Available in iOS 3.2 and later.
     */
    void delete(NSObject sender);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIResponderStandardEditActions_Protocol/UIResponderStandardEditActions.html#//apple_ref/occ/instm/NSObject/makeTextWritingDirectionLeftToRight:">- (void)makeTextWritingDirectionLeftToRight:(id)sender</a>
     * @since Available in iOS 5.0 and later.
     */
    void makeTextWritingDirectionLeftToRight(NSObject sender);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIResponderStandardEditActions_Protocol/UIResponderStandardEditActions.html#//apple_ref/occ/instm/NSObject/makeTextWritingDirectionRightToLeft:">- (void)makeTextWritingDirectionRightToLeft:(id)sender</a>
     * @since Available in iOS 5.0 and later.
     */
    void makeTextWritingDirectionRightToLeft(NSObject sender);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIResponderStandardEditActions_Protocol/UIResponderStandardEditActions.html#//apple_ref/occ/instm/NSObject/paste:">- (void)paste:(id)sender</a>
     * @since Available in iOS 3.0 and later.
     */
    void paste(NSObject sender);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIResponderStandardEditActions_Protocol/UIResponderStandardEditActions.html#//apple_ref/occ/instm/NSObject/select:">- (void)select:(id)sender</a>
     * @since Available in iOS 3.0 and later.
     */
    void select(NSObject sender);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIResponderStandardEditActions_Protocol/UIResponderStandardEditActions.html#//apple_ref/occ/instm/NSObject/selectAll:">- (void)selectAll:(id)sender</a>
     * @since Available in iOS 3.0 and later.
     */
    void selectAll(NSObject sender);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIResponderStandardEditActions_Protocol/UIResponderStandardEditActions.html#//apple_ref/occ/instm/NSObject/toggleBoldface:">- (void)toggleBoldface:(id)sender</a>
     * @since Available in iOS 6.0 and later.
     */
    void toggleBoldface(NSObject sender);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIResponderStandardEditActions_Protocol/UIResponderStandardEditActions.html#//apple_ref/occ/instm/NSObject/toggleItalics:">- (void)toggleItalics:(id)sender</a>
     * @since Available in iOS 6.0 and later.
     */
    void toggleItalics(NSObject sender);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIResponderStandardEditActions_Protocol/UIResponderStandardEditActions.html#//apple_ref/occ/instm/NSObject/toggleUnderline:">- (void)toggleUnderline:(id)sender</a>
     * @since Available in iOS 6.0 and later.
     */
    void toggleUnderline(NSObject sender);
    /*</methods>*/
    /*<adapter>*/
    public static class Adapter extends NSObject implements UIResponderStandardEditActions {
        @NotImplemented("copy:") public void copy(NSObject sender) { throw new UnsupportedOperationException(); }
        @NotImplemented("cut:") public void cut(NSObject sender) { throw new UnsupportedOperationException(); }
        @NotImplemented("delete:") public void delete(NSObject sender) { throw new UnsupportedOperationException(); }
        @NotImplemented("makeTextWritingDirectionLeftToRight:") public void makeTextWritingDirectionLeftToRight(NSObject sender) { throw new UnsupportedOperationException(); }
        @NotImplemented("makeTextWritingDirectionRightToLeft:") public void makeTextWritingDirectionRightToLeft(NSObject sender) { throw new UnsupportedOperationException(); }
        @NotImplemented("paste:") public void paste(NSObject sender) { throw new UnsupportedOperationException(); }
        @NotImplemented("select:") public void select(NSObject sender) { throw new UnsupportedOperationException(); }
        @NotImplemented("selectAll:") public void selectAll(NSObject sender) { throw new UnsupportedOperationException(); }
        @NotImplemented("toggleBoldface:") public void toggleBoldface(NSObject sender) { throw new UnsupportedOperationException(); }
        @NotImplemented("toggleItalics:") public void toggleItalics(NSObject sender) { throw new UnsupportedOperationException(); }
        @NotImplemented("toggleUnderline:") public void toggleUnderline(NSObject sender) { throw new UnsupportedOperationException(); }
    }
    /*</adapter>*/
    /*<callbacks>*/
    static class Callbacks {
        @Callback @BindSelector("copy:") public static void copy(UIResponderStandardEditActions __self__, Selector __cmd__, NSObject sender) { __self__.copy(sender); }
        @Callback @BindSelector("cut:") public static void cut(UIResponderStandardEditActions __self__, Selector __cmd__, NSObject sender) { __self__.cut(sender); }
        @Callback @BindSelector("delete:") public static void delete(UIResponderStandardEditActions __self__, Selector __cmd__, NSObject sender) { __self__.delete(sender); }
        @Callback @BindSelector("makeTextWritingDirectionLeftToRight:") public static void makeTextWritingDirectionLeftToRight(UIResponderStandardEditActions __self__, Selector __cmd__, NSObject sender) { __self__.makeTextWritingDirectionLeftToRight(sender); }
        @Callback @BindSelector("makeTextWritingDirectionRightToLeft:") public static void makeTextWritingDirectionRightToLeft(UIResponderStandardEditActions __self__, Selector __cmd__, NSObject sender) { __self__.makeTextWritingDirectionRightToLeft(sender); }
        @Callback @BindSelector("paste:") public static void paste(UIResponderStandardEditActions __self__, Selector __cmd__, NSObject sender) { __self__.paste(sender); }
        @Callback @BindSelector("select:") public static void select(UIResponderStandardEditActions __self__, Selector __cmd__, NSObject sender) { __self__.select(sender); }
        @Callback @BindSelector("selectAll:") public static void selectAll(UIResponderStandardEditActions __self__, Selector __cmd__, NSObject sender) { __self__.selectAll(sender); }
        @Callback @BindSelector("toggleBoldface:") public static void toggleBoldface(UIResponderStandardEditActions __self__, Selector __cmd__, NSObject sender) { __self__.toggleBoldface(sender); }
        @Callback @BindSelector("toggleItalics:") public static void toggleItalics(UIResponderStandardEditActions __self__, Selector __cmd__, NSObject sender) { __self__.toggleItalics(sender); }
        @Callback @BindSelector("toggleUnderline:") public static void toggleUnderline(UIResponderStandardEditActions __self__, Selector __cmd__, NSObject sender) { __self__.toggleUnderline(sender); }
    }
    /*</callbacks>*/

}
