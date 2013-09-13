/*
 * Copyright (C) 2012 Trillian AB
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
import org.robovm.cocoatouch.coredata.*;
import org.robovm.cocoatouch.coregraphics.*;
import org.robovm.cocoatouch.coreimage.*;
import org.robovm.cocoatouch.foundation.*;
import java.util.*;
import org.robovm.objc.*;
import org.robovm.objc.annotation.*;
import org.robovm.objc.block.*;
import org.robovm.rt.bro.*;
import org.robovm.rt.bro.annotation.*;
import org.robovm.rt.bro.ptr.*;
/*</imports>*/

/**
 *
 *
 * <div class="javadoc">
 *   @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UITextInputTraits_Protocol/Reference/UITextInputTraits.html">UITextInputTraits Protocol Reference</a>
 *   @since Available in iOS 2.0 and later.
 * </div>
 */
public interface /*<name>*/ UITextInputTraits /*</name>*/ /*<implements>*/ extends NSObjectProtocol /*</implements>*/ {

    /*<properties>*/
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UITextInputTraits_Protocol/Reference/UITextInputTraits.html#//apple_ref/occ/intfp/UITextInputTraits/autocapitalizationType">@property(nonatomic) UITextAutocapitalizationType autocapitalizationType</a>
     * @since Available in iOS 2.0 and later.
     */
    UITextAutocapitalizationType getAutocapitalizationType();
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UITextInputTraits_Protocol/Reference/UITextInputTraits.html#//apple_ref/occ/intfp/UITextInputTraits/autocapitalizationType">@property(nonatomic) UITextAutocapitalizationType autocapitalizationType</a>
     * @since Available in iOS 2.0 and later.
     */
    void setAutocapitalizationType(UITextAutocapitalizationType v);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UITextInputTraits_Protocol/Reference/UITextInputTraits.html#//apple_ref/occ/intfp/UITextInputTraits/autocorrectionType">@property(nonatomic) UITextAutocorrectionType autocorrectionType</a>
     * @since Available in iOS 2.0 and later.
     */
    UITextAutocorrectionType getAutocorrectionType();
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UITextInputTraits_Protocol/Reference/UITextInputTraits.html#//apple_ref/occ/intfp/UITextInputTraits/autocorrectionType">@property(nonatomic) UITextAutocorrectionType autocorrectionType</a>
     * @since Available in iOS 2.0 and later.
     */
    void setAutocorrectionType(UITextAutocorrectionType v);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UITextInputTraits_Protocol/Reference/UITextInputTraits.html#//apple_ref/occ/intfp/UITextInputTraits/enablesReturnKeyAutomatically">@property(nonatomic) BOOL enablesReturnKeyAutomatically</a>
     * @since Available in iOS 2.0 and later.
     */
    boolean isEnablesReturnKeyAutomatically();
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UITextInputTraits_Protocol/Reference/UITextInputTraits.html#//apple_ref/occ/intfp/UITextInputTraits/enablesReturnKeyAutomatically">@property(nonatomic) BOOL enablesReturnKeyAutomatically</a>
     * @since Available in iOS 2.0 and later.
     */
    void setEnablesReturnKeyAutomatically(boolean v);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UITextInputTraits_Protocol/Reference/UITextInputTraits.html#//apple_ref/occ/intfp/UITextInputTraits/keyboardAppearance">@property(nonatomic) UIKeyboardAppearance keyboardAppearance</a>
     * @since Available in iOS 2.0 and later.
     */
    UIKeyboardAppearance getKeyboardAppearance();
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UITextInputTraits_Protocol/Reference/UITextInputTraits.html#//apple_ref/occ/intfp/UITextInputTraits/keyboardAppearance">@property(nonatomic) UIKeyboardAppearance keyboardAppearance</a>
     * @since Available in iOS 2.0 and later.
     */
    void setKeyboardAppearance(UIKeyboardAppearance v);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UITextInputTraits_Protocol/Reference/UITextInputTraits.html#//apple_ref/occ/intfp/UITextInputTraits/keyboardType">@property(nonatomic) UIKeyboardType keyboardType</a>
     * @since Available in iOS 2.0 and later.
     */
    UIKeyboardType getKeyboardType();
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UITextInputTraits_Protocol/Reference/UITextInputTraits.html#//apple_ref/occ/intfp/UITextInputTraits/keyboardType">@property(nonatomic) UIKeyboardType keyboardType</a>
     * @since Available in iOS 2.0 and later.
     */
    void setKeyboardType(UIKeyboardType v);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UITextInputTraits_Protocol/Reference/UITextInputTraits.html#//apple_ref/occ/intfp/UITextInputTraits/returnKeyType">@property(nonatomic) UIReturnKeyType returnKeyType</a>
     * @since Available in iOS 2.0 and later.
     */
    UIReturnKeyType getReturnKeyType();
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UITextInputTraits_Protocol/Reference/UITextInputTraits.html#//apple_ref/occ/intfp/UITextInputTraits/returnKeyType">@property(nonatomic) UIReturnKeyType returnKeyType</a>
     * @since Available in iOS 2.0 and later.
     */
    void setReturnKeyType(UIReturnKeyType v);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UITextInputTraits_Protocol/Reference/UITextInputTraits.html#//apple_ref/occ/intfp/UITextInputTraits/secureTextEntry">@property(nonatomic, getter=isSecureTextEntry) BOOL secureTextEntry</a>
     * @since Available in iOS 2.0 and later.
     */
    boolean isSecureTextEntry();
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UITextInputTraits_Protocol/Reference/UITextInputTraits.html#//apple_ref/occ/intfp/UITextInputTraits/secureTextEntry">@property(nonatomic, getter=isSecureTextEntry) BOOL secureTextEntry</a>
     * @since Available in iOS 2.0 and later.
     */
    void setSecureTextEntry(boolean v);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UITextInputTraits_Protocol/Reference/UITextInputTraits.html#//apple_ref/occ/intfp/UITextInputTraits/spellCheckingType">@property(nonatomic) UITextSpellCheckingType spellCheckingType</a>
     * @since Available in iOS 5.0 and later.
     */
    UITextSpellCheckingType getSpellCheckingType();
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UITextInputTraits_Protocol/Reference/UITextInputTraits.html#//apple_ref/occ/intfp/UITextInputTraits/spellCheckingType">@property(nonatomic) UITextSpellCheckingType spellCheckingType</a>
     * @since Available in iOS 5.0 and later.
     */
    void setSpellCheckingType(UITextSpellCheckingType v);
    /*</properties>*/
    /*<methods>*/
    
    /*</methods>*/
    /*<adapter>*/
    public static class Adapter extends NSObject implements UITextInputTraits {
        @NotImplemented("autocapitalizationType") public UITextAutocapitalizationType getAutocapitalizationType() { throw new UnsupportedOperationException(); }
        @NotImplemented("setAutocapitalizationType:") public void setAutocapitalizationType(UITextAutocapitalizationType autocapitalizationType) { throw new UnsupportedOperationException(); }
        @NotImplemented("autocorrectionType") public UITextAutocorrectionType getAutocorrectionType() { throw new UnsupportedOperationException(); }
        @NotImplemented("setAutocorrectionType:") public void setAutocorrectionType(UITextAutocorrectionType autocorrectionType) { throw new UnsupportedOperationException(); }
        @NotImplemented("enablesReturnKeyAutomatically") public boolean isEnablesReturnKeyAutomatically() { throw new UnsupportedOperationException(); }
        @NotImplemented("setEnablesReturnKeyAutomatically:") public void setEnablesReturnKeyAutomatically(boolean enablesReturnKeyAutomatically) { throw new UnsupportedOperationException(); }
        @NotImplemented("keyboardAppearance") public UIKeyboardAppearance getKeyboardAppearance() { throw new UnsupportedOperationException(); }
        @NotImplemented("setKeyboardAppearance:") public void setKeyboardAppearance(UIKeyboardAppearance keyboardAppearance) { throw new UnsupportedOperationException(); }
        @NotImplemented("keyboardType") public UIKeyboardType getKeyboardType() { throw new UnsupportedOperationException(); }
        @NotImplemented("setKeyboardType:") public void setKeyboardType(UIKeyboardType keyboardType) { throw new UnsupportedOperationException(); }
        @NotImplemented("returnKeyType") public UIReturnKeyType getReturnKeyType() { throw new UnsupportedOperationException(); }
        @NotImplemented("setReturnKeyType:") public void setReturnKeyType(UIReturnKeyType returnKeyType) { throw new UnsupportedOperationException(); }
        @NotImplemented("isSecureTextEntry") public boolean isSecureTextEntry() { throw new UnsupportedOperationException(); }
        @NotImplemented("setSecureTextEntry:") public void setSecureTextEntry(boolean secureTextEntry) { throw new UnsupportedOperationException(); }
        @NotImplemented("spellCheckingType") public UITextSpellCheckingType getSpellCheckingType() { throw new UnsupportedOperationException(); }
        @NotImplemented("setSpellCheckingType:") public void setSpellCheckingType(UITextSpellCheckingType spellCheckingType) { throw new UnsupportedOperationException(); }
    }
    /*</adapter>*/
    /*<callbacks>*/
    static class Callbacks {
        @Callback @BindSelector("autocapitalizationType") public static UITextAutocapitalizationType getAutocapitalizationType(UITextInputTraits __self__, Selector __cmd__) { return __self__.getAutocapitalizationType(); }
        @Callback @BindSelector("setAutocapitalizationType:") public static void setAutocapitalizationType(UITextInputTraits __self__, Selector __cmd__, UITextAutocapitalizationType autocapitalizationType) { __self__.setAutocapitalizationType(autocapitalizationType); }
        @Callback @BindSelector("autocorrectionType") public static UITextAutocorrectionType getAutocorrectionType(UITextInputTraits __self__, Selector __cmd__) { return __self__.getAutocorrectionType(); }
        @Callback @BindSelector("setAutocorrectionType:") public static void setAutocorrectionType(UITextInputTraits __self__, Selector __cmd__, UITextAutocorrectionType autocorrectionType) { __self__.setAutocorrectionType(autocorrectionType); }
        @Callback @BindSelector("enablesReturnKeyAutomatically") public static boolean isEnablesReturnKeyAutomatically(UITextInputTraits __self__, Selector __cmd__) { return __self__.isEnablesReturnKeyAutomatically(); }
        @Callback @BindSelector("setEnablesReturnKeyAutomatically:") public static void setEnablesReturnKeyAutomatically(UITextInputTraits __self__, Selector __cmd__, boolean enablesReturnKeyAutomatically) { __self__.setEnablesReturnKeyAutomatically(enablesReturnKeyAutomatically); }
        @Callback @BindSelector("keyboardAppearance") public static UIKeyboardAppearance getKeyboardAppearance(UITextInputTraits __self__, Selector __cmd__) { return __self__.getKeyboardAppearance(); }
        @Callback @BindSelector("setKeyboardAppearance:") public static void setKeyboardAppearance(UITextInputTraits __self__, Selector __cmd__, UIKeyboardAppearance keyboardAppearance) { __self__.setKeyboardAppearance(keyboardAppearance); }
        @Callback @BindSelector("keyboardType") public static UIKeyboardType getKeyboardType(UITextInputTraits __self__, Selector __cmd__) { return __self__.getKeyboardType(); }
        @Callback @BindSelector("setKeyboardType:") public static void setKeyboardType(UITextInputTraits __self__, Selector __cmd__, UIKeyboardType keyboardType) { __self__.setKeyboardType(keyboardType); }
        @Callback @BindSelector("returnKeyType") public static UIReturnKeyType getReturnKeyType(UITextInputTraits __self__, Selector __cmd__) { return __self__.getReturnKeyType(); }
        @Callback @BindSelector("setReturnKeyType:") public static void setReturnKeyType(UITextInputTraits __self__, Selector __cmd__, UIReturnKeyType returnKeyType) { __self__.setReturnKeyType(returnKeyType); }
        @Callback @BindSelector("isSecureTextEntry") public static boolean isSecureTextEntry(UITextInputTraits __self__, Selector __cmd__) { return __self__.isSecureTextEntry(); }
        @Callback @BindSelector("setSecureTextEntry:") public static void setSecureTextEntry(UITextInputTraits __self__, Selector __cmd__, boolean secureTextEntry) { __self__.setSecureTextEntry(secureTextEntry); }
        @Callback @BindSelector("spellCheckingType") public static UITextSpellCheckingType getSpellCheckingType(UITextInputTraits __self__, Selector __cmd__) { return __self__.getSpellCheckingType(); }
        @Callback @BindSelector("setSpellCheckingType:") public static void setSpellCheckingType(UITextInputTraits __self__, Selector __cmd__, UITextSpellCheckingType spellCheckingType) { __self__.setSpellCheckingType(spellCheckingType); }
    }
    /*</callbacks>*/

}
