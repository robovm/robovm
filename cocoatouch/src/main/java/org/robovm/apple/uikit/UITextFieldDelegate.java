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
/*<visibility>*/public/*</visibility>*/ interface /*<name>*/UITextFieldDelegate/*</name>*/ 
    /*<implements>*/extends NSObjectProtocol/*</implements>*/ {

    /*<ptr>*/
    /*</ptr>*/
    /*<bind>*/
    /*</bind>*/
    /*<constants>*//*</constants>*/
    /*<properties>*/
    
    /*</properties>*/
    /*<methods>*/
    @Method(selector = "textFieldShouldBeginEditing:")
    boolean shouldBeginEditing(UITextField textField);
    @Method(selector = "textFieldDidBeginEditing:")
    void didBeginEditing(UITextField textField);
    @Method(selector = "textFieldShouldEndEditing:")
    boolean shouldEndEditing(UITextField textField);
    @Method(selector = "textFieldDidEndEditing:")
    void didEndEditing(UITextField textField);
    @Method(selector = "textField:shouldChangeCharactersInRange:replacementString:")
    boolean shouldChangeCharacters(UITextField textField, @ByVal NSRange range, String string);
    @Method(selector = "textFieldShouldClear:")
    boolean shouldClear(UITextField textField);
    @Method(selector = "textFieldShouldReturn:")
    boolean shouldReturn(UITextField textField);
    /*</methods>*/
    /*<adapter>*/
    /*</adapter>*/
}
