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

/**
 *
 * <div class="javadoc"></div>
 */
/*<annotations>*//*</annotations>*/
/*<visibility>*/public/*</visibility>*/ class /*<name>*/UIViewControllerTransitionCoordinatorContextAdapter/*</name>*/ 
    extends /*<extends>*/NSObject/*</extends>*/ 
    /*<implements>*/implements UIViewControllerTransitionCoordinatorContext/*</implements>*/ {

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
    @NotImplemented("isAnimated")
    public boolean isAnimated() { throw new UnsupportedOperationException(); }
    @NotImplemented("presentationStyle")
    public UIModalPresentationStyle getPresentationStyle() { throw new UnsupportedOperationException(); }
    @NotImplemented("initiallyInteractive")
    public boolean isInitiallyInteractive() { throw new UnsupportedOperationException(); }
    @NotImplemented("isInteractive")
    public boolean isInteractive() { throw new UnsupportedOperationException(); }
    @NotImplemented("isCancelled")
    public boolean isCancelled() { throw new UnsupportedOperationException(); }
    @NotImplemented("transitionDuration")
    public double getTransitionDuration() { throw new UnsupportedOperationException(); }
    @NotImplemented("percentComplete")
    public @MachineSizedFloat double getPercentComplete() { throw new UnsupportedOperationException(); }
    @NotImplemented("completionVelocity")
    public @MachineSizedFloat double getCompletionVelocity() { throw new UnsupportedOperationException(); }
    @NotImplemented("completionCurve")
    public UIViewAnimationCurve getCompletionCurve() { throw new UnsupportedOperationException(); }
    @NotImplemented("viewControllerForKey:")
    public UIViewController getViewController(String key) { throw new UnsupportedOperationException(); }
    @NotImplemented("containerView")
    public UIView getContainerView() { throw new UnsupportedOperationException(); }
    /*</methods>*/
}
