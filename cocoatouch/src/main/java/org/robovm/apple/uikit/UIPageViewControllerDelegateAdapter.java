/*
 * Copyright (C) 2014 Trillian Mobile AB
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
package org.robovm.apple.uikit;

/*<imports>*/
import java.io.*;
import java.nio.*;
import java.util.*;
import org.robovm.objc.*;
import org.robovm.objc.annotation.*;
import org.robovm.objc.block.*;
import org.robovm.rt.*;
import org.robovm.rt.bro.*;
import org.robovm.rt.bro.annotation.*;
import org.robovm.rt.bro.ptr.*;
import org.robovm.apple.foundation.*;
import org.robovm.apple.coreanimation.*;
import org.robovm.apple.coregraphics.*;
import org.robovm.apple.coredata.*;
import org.robovm.apple.coreimage.*;
/*</imports>*/

/*<javadoc>*/
/*</javadoc>*/
/*<annotations>*//*</annotations>*/
/*<visibility>*/public/*</visibility>*/ class /*<name>*/UIPageViewControllerDelegateAdapter/*</name>*/ 
    extends /*<extends>*/NSObject/*</extends>*/ 
    /*<implements>*/implements UIPageViewControllerDelegate/*</implements>*/ {

    /*<ptr>*/
    /*</ptr>*/
    /*<bind>*/
    /*</bind>*/
    /*<constants>*//*</constants>*/
    /*<constructors>*//*</constructors>*/
    /*<properties>*/
    
    /*</properties>*/
    /*<members>*//*</members>*/
    /*<methods>*/
    /**
     * @since Available in iOS 6.0 and later.
     */
    @NotImplemented("pageViewController:willTransitionToViewControllers:")
    public void willTransition(UIPageViewController pageViewController, NSArray<UIViewController> pendingViewControllers) { throw new UnsupportedOperationException(); }
    @NotImplemented("pageViewController:didFinishAnimating:previousViewControllers:transitionCompleted:")
    public void didFinishAnimating(UIPageViewController pageViewController, boolean finished, NSArray<UIViewController> previousViewControllers, boolean completed) { throw new UnsupportedOperationException(); }
    @NotImplemented("pageViewController:spineLocationForInterfaceOrientation:")
    public UIPageViewControllerSpineLocation getSpineLocation(UIPageViewController pageViewController, UIInterfaceOrientation orientation) { throw new UnsupportedOperationException(); }
    /**
     * @since Available in iOS 7.0 and later.
     */
    @NotImplemented("pageViewControllerSupportedInterfaceOrientations:")
    public @MachineSizedUInt long getSupportedInterfaceOrientations(UIPageViewController pageViewController) { throw new UnsupportedOperationException(); }
    /**
     * @since Available in iOS 7.0 and later.
     */
    @NotImplemented("pageViewControllerPreferredInterfaceOrientationForPresentation:")
    public UIInterfaceOrientation getPreferredInterfaceOrientation(UIPageViewController pageViewController) { throw new UnsupportedOperationException(); }
    /*</methods>*/
}
