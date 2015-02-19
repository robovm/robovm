/*
 * Copyright (C) 2013-2015 Trillian Mobile AB
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
package org.robovm.apple.photosui;

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
import org.robovm.apple.photos.*;
import org.robovm.apple.uikit.*;
/*</imports>*/

/*<javadoc>*/
/*</javadoc>*/
/*<annotations>*//*</annotations>*/
/*<visibility>*/public/*</visibility>*/ class /*<name>*/PHContentEditingControllerAdapter/*</name>*/ 
    extends /*<extends>*/NSObject/*</extends>*/ 
    /*<implements>*/implements PHContentEditingController/*</implements>*/ {

    /*<ptr>*/
    /*</ptr>*/
    /*<bind>*/
    /*</bind>*/
    /*<constants>*//*</constants>*/
    /*<constructors>*//*</constructors>*/
    /*<properties>*/
    @NotImplemented("shouldShowCancelConfirmation")
    public boolean shouldShowCancelConfirmation() { throw new UnsupportedOperationException(); }
    /*</properties>*/
    /*<members>*//*</members>*/
    /*<methods>*/
    @NotImplemented("canHandleAdjustmentData:")
    public boolean canHandleAdjustmentData(PHAdjustmentData adjustmentData) { throw new UnsupportedOperationException(); }
    @NotImplemented("startContentEditingWithInput:placeholderImage:")
    public void startContentEditing(PHContentEditingInput contentEditingInput, UIImage placeholderImage) { throw new UnsupportedOperationException(); }
    @NotImplemented("finishContentEditingWithCompletionHandler:")
    public void finishContentEditing(@Block VoidBlock1<PHContentEditingOutput> completionHandler) { throw new UnsupportedOperationException(); }
    @NotImplemented("cancelContentEditing")
    public void cancelContentEditing() { throw new UnsupportedOperationException(); }
    /*</methods>*/
}
