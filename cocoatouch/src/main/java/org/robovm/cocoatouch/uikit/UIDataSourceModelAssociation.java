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
 * <div class="javadoc">
 *   @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIDataSourceModelAssociation_protocol/Reference/Reference.html">UIDataSourceModelAssociation Protocol Reference</a>
 *   @since Available in iOS 6.0 and later.
 * </div>
 */
public interface /*<name>*/ UIDataSourceModelAssociation /*</name>*/ /*<implements>*/ extends NSObjectProtocol /*</implements>*/ {

    /*<properties>*/
    
    /*</properties>*/
    /*<methods>*/
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIDataSourceModelAssociation_protocol/Reference/Reference.html#//apple_ref/occ/intfm/UIDataSourceModelAssociation/indexPathForElementWithModelIdentifier:inView:">- (NSIndexPath *) indexPathForElementWithModelIdentifier:(NSString *)identifier inView:(UIView *)view</a>
     * @since Available in iOS 6.0 and later.
     */
    NSIndexPath getElementIndexPath(String identifier, UIView view);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIDataSourceModelAssociation_protocol/Reference/Reference.html#//apple_ref/occ/intfm/UIDataSourceModelAssociation/modelIdentifierForElementAtIndexPath:inView:">- (NSString *) modelIdentifierForElementAtIndexPath:(NSIndexPath *)idx inView:(UIView *)view</a>
     * @since Available in iOS 6.0 and later.
     */
    String getElementModelIdentifier(NSIndexPath idx, UIView view);
    /*</methods>*/
    /*<adapter>*/
    public static class Adapter extends NSObject implements UIDataSourceModelAssociation {
        @NotImplemented("indexPathForElementWithModelIdentifier:inView:") public NSIndexPath getElementIndexPath(String identifier, UIView view) { throw new UnsupportedOperationException(); }
        @NotImplemented("modelIdentifierForElementAtIndexPath:inView:") public String getElementModelIdentifier(NSIndexPath idx, UIView view) { throw new UnsupportedOperationException(); }
    }
    /*</adapter>*/
    /*<callbacks>*/
    static class Callbacks {
        @Callback @BindSelector("indexPathForElementWithModelIdentifier:inView:") public static NSIndexPath getElementIndexPath(UIDataSourceModelAssociation __self__, Selector __cmd__, String identifier, UIView view) { return __self__.getElementIndexPath(identifier, view); }
        @Callback @BindSelector("modelIdentifierForElementAtIndexPath:inView:") public static String getElementModelIdentifier(UIDataSourceModelAssociation __self__, Selector __cmd__, NSIndexPath idx, UIView view) { return __self__.getElementModelIdentifier(idx, view); }
    }
    /*</callbacks>*/

}
