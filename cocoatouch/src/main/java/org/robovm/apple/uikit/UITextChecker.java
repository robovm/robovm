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
/**
 * @since Available in iOS 3.2 and later.
 */
/*</javadoc>*/
/*<annotations>*/@Library("UIKit") @NativeClass/*</annotations>*/
/*<visibility>*/public/*</visibility>*/ class /*<name>*/UITextChecker/*</name>*/ 
    extends /*<extends>*/NSObject/*</extends>*/ 
    /*<implements>*//*</implements>*/ {

    /*<ptr>*/public static class UITextCheckerPtr extends Ptr<UITextChecker, UITextCheckerPtr> {}/*</ptr>*/
    /*<bind>*/static { ObjCRuntime.bind(UITextChecker.class); }/*</bind>*/
    /*<constants>*//*</constants>*/
    /*<constructors>*/
    public UITextChecker() {}
    protected UITextChecker(SkipInit skipInit) { super(skipInit); }
    /*</constructors>*/
    /*<properties>*/
    
    /*</properties>*/
    /*<members>*//*</members>*/
    /*<methods>*/
    @Method(selector = "rangeOfMisspelledWordInString:range:startingAt:wrap:language:")
    public native @ByVal NSRange getRangeOfMisspelledWordInString(String stringToCheck, @ByVal NSRange range, @MachineSizedSInt long startingOffset, boolean wrapFlag, String language);
    @Method(selector = "guessesForWordRange:inString:language:")
    public native @org.robovm.rt.bro.annotation.Marshaler(NSArray.AsStringListMarshaler.class) List<String> getGuessesForWord(@ByVal NSRange range, String string, String language);
    @Method(selector = "completionsForPartialWordRange:inString:language:")
    public native @org.robovm.rt.bro.annotation.Marshaler(NSArray.AsStringListMarshaler.class) List<String> getCompletionsForPartialWord(@ByVal NSRange range, String string, String language);
    @Method(selector = "ignoreWord:")
    public native void ignoreWord(String wordToIgnore);
    @Method(selector = "ignoredWords")
    public native @org.robovm.rt.bro.annotation.Marshaler(NSArray.AsStringListMarshaler.class) List<String> getIgnoredWords();
    @Method(selector = "setIgnoredWords:")
    public native void setIgnoredWords(@org.robovm.rt.bro.annotation.Marshaler(NSArray.AsStringListMarshaler.class) List<String> words);
    @Method(selector = "learnWord:")
    public static native void learnWord(String word);
    @Method(selector = "hasLearnedWord:")
    public static native boolean hasLearnedWord(String word);
    @Method(selector = "unlearnWord:")
    public static native void unlearnWord(String word);
    @Method(selector = "availableLanguages")
    public static native @org.robovm.rt.bro.annotation.Marshaler(NSArray.AsStringListMarshaler.class) List<String> getAvailableLanguages();
    /*</methods>*/
}
