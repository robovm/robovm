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
package org.robovm.apple.foundation;

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
import org.robovm.apple.corefoundation.*;
import org.robovm.apple.uikit.*;
import org.robovm.apple.coreanimation.*;
import org.robovm.apple.coredata.*;
import org.robovm.apple.coregraphics.*;
import org.robovm.apple.coremedia.*;
import org.robovm.apple.security.*;
import org.robovm.apple.dispatch.*;
/*</imports>*/

/*<javadoc>*/
/*</javadoc>*/
@Marshaler(NSLinguisticTagScheme.Marshaler.class)
/*<annotations>*/@Library("Foundation")/*</annotations>*/
/*<visibility>*/public/*</visibility>*/ class /*<name>*/NSLinguisticTagScheme/*</name>*/ 
    extends /*<extends>*/CocoaUtility/*</extends>*/ 
    /*<implements>*//*</implements>*/ {

    public static class Marshaler {
        @MarshalsPointer
        public static NSLinguisticTagScheme toObject(Class<NSLinguisticTagScheme> cls, long handle, long flags) {
            NSString o = (NSString) NSObject.Marshaler.toObject(NSString.class, handle, flags);
            if (o == null) {
                return null;
            }
            return NSLinguisticTagScheme.valueOf(o);
        }
        @MarshalsPointer
        public static long toNative(NSLinguisticTagScheme o, long flags) {
            if (o == null) {
                return 0L;
            }
            return NSObject.Marshaler.toNative(o.value(), flags);
        }
    }
    
    /*<ptr>*/
    /*</ptr>*/
    /*<bind>*/static { Bro.bind(NSLinguisticTagScheme.class); }/*</bind>*/
    /*<constants>*//*</constants>*/
    /**
     * @since Available in iOS 5.0 and later.
     */
    public static final NSLinguisticTagScheme TokenType = new NSLinguisticTagScheme("TokenTypeValue");
    /**
     * @since Available in iOS 5.0 and later.
     */
    public static final NSLinguisticTagScheme LexicalClass = new NSLinguisticTagScheme("LexicalClassValue");
    /**
     * @since Available in iOS 5.0 and later.
     */
    public static final NSLinguisticTagScheme NameType = new NSLinguisticTagScheme("NameTypeValue");
    /**
     * @since Available in iOS 5.0 and later.
     */
    public static final NSLinguisticTagScheme NameTypeOrLexicalClass = new NSLinguisticTagScheme("NameTypeOrLexicalClassValue");
    /**
     * @since Available in iOS 5.0 and later.
     */
    public static final NSLinguisticTagScheme Lemma = new NSLinguisticTagScheme("LemmaValue");
    /**
     * @since Available in iOS 5.0 and later.
     */
    public static final NSLinguisticTagScheme Language = new NSLinguisticTagScheme("LanguageValue");
    /**
     * @since Available in iOS 5.0 and later.
     */
    public static final NSLinguisticTagScheme Script = new NSLinguisticTagScheme("ScriptValue");
    
    private static NSLinguisticTagScheme[] values = new NSLinguisticTagScheme[] {TokenType, LexicalClass, NameType, NameTypeOrLexicalClass, Lemma, Language, Script};
    private final LazyGlobalValue<NSString> lazyGlobalValue;
    
    private NSLinguisticTagScheme(String getterName) {
        lazyGlobalValue = new LazyGlobalValue<>(getClass(), getterName);
    }
    /*<constructors>*//*</constructors>*/
    /*<properties>*//*</properties>*/
    /*<members>*//*</members>*/
    public NSString value() {
        return lazyGlobalValue.value();
    }
    
    public static NSLinguisticTagScheme valueOf(NSString value) {
        for (NSLinguisticTagScheme v : values) {
            if (v.value().equals(value)) {
                return v;
            }
        }
        throw new IllegalArgumentException("No constant with value " + value + " found in " 
            + /*<name>*/NSLinguisticTagScheme/*</name>*/.class.getName());
    }
    /*<methods>*/
    /**
     * @since Available in iOS 5.0 and later.
     */
    @GlobalValue(symbol="NSLinguisticTagSchemeTokenType", optional=true)
    protected static native NSString TokenTypeValue();
    /**
     * @since Available in iOS 5.0 and later.
     */
    @GlobalValue(symbol="NSLinguisticTagSchemeLexicalClass", optional=true)
    protected static native NSString LexicalClassValue();
    /**
     * @since Available in iOS 5.0 and later.
     */
    @GlobalValue(symbol="NSLinguisticTagSchemeNameType", optional=true)
    protected static native NSString NameTypeValue();
    /**
     * @since Available in iOS 5.0 and later.
     */
    @GlobalValue(symbol="NSLinguisticTagSchemeNameTypeOrLexicalClass", optional=true)
    protected static native NSString NameTypeOrLexicalClassValue();
    /**
     * @since Available in iOS 5.0 and later.
     */
    @GlobalValue(symbol="NSLinguisticTagSchemeLemma", optional=true)
    protected static native NSString LemmaValue();
    /**
     * @since Available in iOS 5.0 and later.
     */
    @GlobalValue(symbol="NSLinguisticTagSchemeLanguage", optional=true)
    protected static native NSString LanguageValue();
    /**
     * @since Available in iOS 5.0 and later.
     */
    @GlobalValue(symbol="NSLinguisticTagSchemeScript", optional=true)
    protected static native NSString ScriptValue();
    /*</methods>*/
}
