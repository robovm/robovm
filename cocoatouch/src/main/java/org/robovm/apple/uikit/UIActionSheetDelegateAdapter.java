/*
 * Copyright (C) 2013-2015 RoboVM AB
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
import org.robovm.rt.annotation.*;
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
/*<visibility>*/public/*</visibility>*/ class /*<name>*/UIActionSheetDelegateAdapter/*</name>*/ 
    extends /*<extends>*/NSObject/*</extends>*/ 
    /*<implements>*/implements UIActionSheetDelegate/*</implements>*/ {

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
     * @since Available in iOS 2.0 and later.
     * @deprecated Deprecated in iOS 8.3.
     */
    @Deprecated
    @NotImplemented("actionSheet:clickedButtonAtIndex:")
    public void clicked(UIActionSheet actionSheet, @MachineSizedSInt long buttonIndex) {}
    /**
     * @since Available in iOS 2.0 and later.
     * @deprecated Deprecated in iOS 8.3.
     */
    @Deprecated
    @NotImplemented("actionSheetCancel:")
    public void cancel(UIActionSheet actionSheet) {}
    /**
     * @since Available in iOS 2.0 and later.
     * @deprecated Deprecated in iOS 8.3.
     */
    @Deprecated
    @NotImplemented("willPresentActionSheet:")
    public void willPresent(UIActionSheet actionSheet) {}
    /**
     * @since Available in iOS 2.0 and later.
     * @deprecated Deprecated in iOS 8.3.
     */
    @Deprecated
    @NotImplemented("didPresentActionSheet:")
    public void didPresent(UIActionSheet actionSheet) {}
    /**
     * @since Available in iOS 2.0 and later.
     * @deprecated Deprecated in iOS 8.3.
     */
    @Deprecated
    @NotImplemented("actionSheet:willDismissWithButtonIndex:")
    public void willDismiss(UIActionSheet actionSheet, @MachineSizedSInt long buttonIndex) {}
    /**
     * @since Available in iOS 2.0 and later.
     * @deprecated Deprecated in iOS 8.3.
     */
    @Deprecated
    @NotImplemented("actionSheet:didDismissWithButtonIndex:")
    public void didDismiss(UIActionSheet actionSheet, @MachineSizedSInt long buttonIndex) {}
    /*</methods>*/
}
