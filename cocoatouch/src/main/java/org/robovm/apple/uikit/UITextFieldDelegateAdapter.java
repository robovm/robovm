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
/*<visibility>*/public/*</visibility>*/ class /*<name>*/UITextFieldDelegateAdapter/*</name>*/ 
    extends /*<extends>*/NSObject/*</extends>*/ 
    /*<implements>*/implements UITextFieldDelegate/*</implements>*/ {

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
    @NotImplemented("textFieldShouldBeginEditing:")
    public boolean shouldBeginEditing(UITextField textField) { throw new UnsupportedOperationException(); }
    @NotImplemented("textFieldDidBeginEditing:")
    public void didBeginEditing(UITextField textField) { throw new UnsupportedOperationException(); }
    @NotImplemented("textFieldShouldEndEditing:")
    public boolean shouldEndEditing(UITextField textField) { throw new UnsupportedOperationException(); }
    @NotImplemented("textFieldDidEndEditing:")
    public void didEndEditing(UITextField textField) { throw new UnsupportedOperationException(); }
    @NotImplemented("textField:shouldChangeCharactersInRange:replacementString:")
    public boolean shouldChangeCharacters(UITextField textField, @ByVal NSRange range, String string) { throw new UnsupportedOperationException(); }
    @NotImplemented("textFieldShouldClear:")
    public boolean shouldClear(UITextField textField) { throw new UnsupportedOperationException(); }
    @NotImplemented("textFieldShouldReturn:")
    public boolean shouldReturn(UITextField textField) { throw new UnsupportedOperationException(); }
    /*</methods>*/
}
