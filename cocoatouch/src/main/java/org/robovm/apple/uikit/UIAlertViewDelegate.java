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
/*<visibility>*/public/*</visibility>*/ interface /*<name>*/UIAlertViewDelegate/*</name>*/ 
    /*<implements>*/extends NSObjectProtocol/*</implements>*/ {

    /*<ptr>*/
    /*</ptr>*/
    /*<bind>*/
    /*</bind>*/
    /*<constants>*//*</constants>*/
    /*<properties>*/
    
    /*</properties>*/
    /*<methods>*/
    @Method(selector = "alertView:clickedButtonAtIndex:")
    void clicked(UIAlertView alertView, @MachineSizedSInt long buttonIndex);
    @Method(selector = "alertViewCancel:")
    void cancel(UIAlertView alertView);
    @Method(selector = "willPresentAlertView:")
    void willPresent(UIAlertView alertView);
    @Method(selector = "didPresentAlertView:")
    void didPresent(UIAlertView alertView);
    @Method(selector = "alertView:willDismissWithButtonIndex:")
    void willDismiss(UIAlertView alertView, @MachineSizedSInt long buttonIndex);
    @Method(selector = "alertView:didDismissWithButtonIndex:")
    void didDismiss(UIAlertView alertView, @MachineSizedSInt long buttonIndex);
    @Method(selector = "alertViewShouldEnableFirstOtherButton:")
    boolean shouldEnableFirstOtherButton(UIAlertView alertView);
    /*</methods>*/
    /*<adapter>*/
    /*</adapter>*/
}
