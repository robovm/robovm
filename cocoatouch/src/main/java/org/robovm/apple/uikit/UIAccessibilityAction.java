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
/*<visibility>*/public/*</visibility>*/ interface /*<name>*/UIAccessibilityAction/*</name>*/ 
    /*<implements>*/extends NSObjectProtocol/*</implements>*/ {

    /*<ptr>*/
    /*</ptr>*/
    /*<bind>*/
    /*</bind>*/
    /*<constants>*//*</constants>*/
    /*<properties>*/
    
    /*</properties>*/
    /*<methods>*/
    /**
     * @since Available in iOS 7.0 and later.
     */
    @Method(selector = "accessibilityActivate")
    boolean activate();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @Method(selector = "accessibilityIncrement")
    void increment();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @Method(selector = "accessibilityDecrement")
    void decrement();
    /**
     * @since Available in iOS 4.2 and later.
     */
    @Method(selector = "accessibilityScroll:")
    boolean scroll(UIAccessibilityScrollDirection direction);
    /**
     * @since Available in iOS 5.0 and later.
     */
    @Method(selector = "accessibilityPerformEscape")
    boolean performEscape();
    /**
     * @since Available in iOS 6.0 and later.
     */
    @Method(selector = "accessibilityPerformMagicTap")
    boolean performMagicTap();
    /*</methods>*/
    /*<adapter>*/
    /*</adapter>*/
}
