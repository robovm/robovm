/*
 * Copyright (C) 2012 RoboVM
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
package org.robovm.cocoatouch.uikit;

/*<imports>*/
import org.robovm.cocoatouch.coreanimation.*;
import org.robovm.cocoatouch.coregraphics.*;
import org.robovm.cocoatouch.coreimage.*;
import org.robovm.cocoatouch.foundation.*;
import java.util.*;
import org.robovm.objc.*;
import org.robovm.objc.bind.*;
import org.robovm.objc.block.*;
import org.robovm.rt.bro.annotation.*;
import org.robovm.rt.bro.ptr.*;
/*</imports>*/

@Protocol
public interface /*<name>*/ UITextInputTraits /*</name>*/ /*<implements>*/ /*</implements>*/ {

    /*<properties>*/
    @Bind("autocapitalizationType") @Type("UITextAutocapitalizationType") UITextAutocapitalizationType getAutocapitalizationType();
    @Bind("setAutocapitalizationType:") void setAutocapitalizationType(@Type("UITextAutocapitalizationType") UITextAutocapitalizationType v);
    @Bind("autocorrectionType") @Type("UITextAutocorrectionType") UITextAutocorrectionType getAutocorrectionType();
    @Bind("setAutocorrectionType:") void setAutocorrectionType(@Type("UITextAutocorrectionType") UITextAutocorrectionType v);
    @Bind("enablesReturnKeyAutomatically") @Type("BOOL") boolean isEnablesReturnKeyAutomatically();
    @Bind("setEnablesReturnKeyAutomatically:") void setEnablesReturnKeyAutomatically(@Type("BOOL") boolean v);
    @Bind("keyboardAppearance") @Type("UIKeyboardAppearance") UIKeyboardAppearance getKeyboardAppearance();
    @Bind("setKeyboardAppearance:") void setKeyboardAppearance(@Type("UIKeyboardAppearance") UIKeyboardAppearance v);
    @Bind("keyboardType") @Type("UIKeyboardType") UIKeyboardType getKeyboardType();
    @Bind("setKeyboardType:") void setKeyboardType(@Type("UIKeyboardType") UIKeyboardType v);
    @Bind("returnKeyType") @Type("UIReturnKeyType") UIReturnKeyType getReturnKeyType();
    @Bind("setReturnKeyType:") void setReturnKeyType(@Type("UIReturnKeyType") UIReturnKeyType v);
    @Bind("isSecureTextEntry") @Type("BOOL") boolean isSecureTextEntry();
    @Bind("setSecureTextEntry:") void setSecureTextEntry(@Type("BOOL") boolean v);
    @Bind("spellCheckingType") @Type("UITextSpellCheckingType") UITextSpellCheckingType getSpellCheckingType();
    @Bind("setSpellCheckingType:") void setSpellCheckingType(@Type("UITextSpellCheckingType") UITextSpellCheckingType v);
    /*</properties>*/
    /*<methods>*/
    
    /*</methods>*/

}
