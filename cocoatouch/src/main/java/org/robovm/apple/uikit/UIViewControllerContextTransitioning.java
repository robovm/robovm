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
/*</imports>*/

/*<javadoc>*/

/*</javadoc>*/
/*<annotations>*//*</annotations>*/
/*<visibility>*/public/*</visibility>*/ interface /*<name>*/UIViewControllerContextTransitioning/*</name>*/ 
    /*<implements>*/extends NSObjectProtocol/*</implements>*/ {

    /*<ptr>*/
    /*</ptr>*/
    /*<bind>*/
    /*</bind>*/
    /*<constants>*//*</constants>*/
    /*<properties>*/
    
    /*</properties>*/
    /*<methods>*/
    @Method(selector = "containerView")
    UIView getContainerView();
    @Method(selector = "isAnimated")
    boolean isAnimated();
    @Method(selector = "isInteractive")
    boolean isInteractive();
    @Method(selector = "transitionWasCancelled")
    boolean transitionWasCancelled();
    @Method(selector = "presentationStyle")
    UIModalPresentationStyle getPresentationStyle();
    @Method(selector = "updateInteractiveTransition:")
    void updateInteractiveTransition(@MachineSizedFloat double percentComplete);
    @Method(selector = "finishInteractiveTransition")
    void finishInteractiveTransition();
    @Method(selector = "cancelInteractiveTransition")
    void cancelInteractiveTransition();
    @Method(selector = "completeTransition:")
    void completeTransition(boolean didComplete);
    @Method(selector = "viewControllerForKey:")
    UIViewController getViewController(UITransitionContextViewControllerType key);
    @Method(selector = "initialFrameForViewController:")
    @ByVal CGRect getInitialFrame(UIViewController vc);
    @Method(selector = "finalFrameForViewController:")
    @ByVal CGRect getFinalFrame(UIViewController vc);
    /*</methods>*/
    /*<adapter>*/
    /*</adapter>*/
}
