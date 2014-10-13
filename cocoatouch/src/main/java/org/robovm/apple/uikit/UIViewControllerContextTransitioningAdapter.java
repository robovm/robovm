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
import org.robovm.apple.coretext.*;
import org.robovm.apple.corelocation.*;
/*</imports>*/

/*<javadoc>*/
/*</javadoc>*/
/*<annotations>*//*</annotations>*/
/*<visibility>*/public/*</visibility>*/ class /*<name>*/UIViewControllerContextTransitioningAdapter/*</name>*/ 
    extends /*<extends>*/NSObject/*</extends>*/ 
    /*<implements>*/implements UIViewControllerContextTransitioning/*</implements>*/ {

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
    @NotImplemented("containerView")
    public UIView getContainerView() { throw new UnsupportedOperationException(); }
    @NotImplemented("isAnimated")
    public boolean isAnimated() { throw new UnsupportedOperationException(); }
    @NotImplemented("isInteractive")
    public boolean isInteractive() { throw new UnsupportedOperationException(); }
    @NotImplemented("transitionWasCancelled")
    public boolean transitionWasCancelled() { throw new UnsupportedOperationException(); }
    @NotImplemented("presentationStyle")
    public UIModalPresentationStyle getPresentationStyle() { throw new UnsupportedOperationException(); }
    @NotImplemented("updateInteractiveTransition:")
    public void updateInteractiveTransition(@MachineSizedFloat double percentComplete) { throw new UnsupportedOperationException(); }
    @NotImplemented("finishInteractiveTransition")
    public void finishInteractiveTransition() { throw new UnsupportedOperationException(); }
    @NotImplemented("cancelInteractiveTransition")
    public void cancelInteractiveTransition() { throw new UnsupportedOperationException(); }
    @NotImplemented("completeTransition:")
    public void completeTransition(boolean didComplete) { throw new UnsupportedOperationException(); }
    @NotImplemented("viewControllerForKey:")
    public UIViewController getViewController(UITransitionContextViewControllerType key) { throw new UnsupportedOperationException(); }
    /**
     * @since Available in iOS 8.0 and later.
     */
    @NotImplemented("viewForKey:")
    public UIView viewForKey$(String key) { throw new UnsupportedOperationException(); }
    /**
     * @since Available in iOS 8.0 and later.
     */
    @NotImplemented("targetTransform")
    public @ByVal CGAffineTransform targetTransform() { throw new UnsupportedOperationException(); }
    @NotImplemented("initialFrameForViewController:")
    public @ByVal CGRect getInitialFrame(UIViewController vc) { throw new UnsupportedOperationException(); }
    @NotImplemented("finalFrameForViewController:")
    public @ByVal CGRect getFinalFrame(UIViewController vc) { throw new UnsupportedOperationException(); }
    /*</methods>*/
}
