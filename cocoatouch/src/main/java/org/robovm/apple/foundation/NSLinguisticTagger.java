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
import org.robovm.apple.security.*;
/*</imports>*/

/**
 *
 * <div class="javadoc"></div>
 */
/*<annotations>*/@Library("Foundation") @NativeClass/*</annotations>*/
/*<visibility>*/public/*</visibility>*/ class /*<name>*/NSLinguisticTagger/*</name>*/ 
    extends /*<extends>*/NSObject/*</extends>*/ 
    /*<implements>*//*</implements>*/ {

    /*<ptr>*/public static class NSLinguisticTaggerPtr extends Ptr<NSLinguisticTagger, NSLinguisticTaggerPtr> {}/*</ptr>*/
    /*<bind>*/static { ObjCRuntime.bind(NSLinguisticTagger.class); }/*</bind>*/
    /*<constants>*//*</constants>*/
    /*<constructors>*/
    public NSLinguisticTagger() {}
    protected NSLinguisticTagger(SkipInit skipInit) { super(skipInit); }
    public NSLinguisticTagger(NSArray<?> tagSchemes, @MachineSizedUInt long opts) { super((SkipInit) null); initObject(initWithTagSchemes$options$(tagSchemes, opts)); }
    /*</constructors>*/
    /*<properties>*/
    
    /*</properties>*/
    /*<members>*//*</members>*/
    /*<methods>*/
    @GlobalValue(symbol="NSLinguisticTagSchemeTokenType", optional=true)
    public static native NSString TagSchemeTokenType();
    @GlobalValue(symbol="NSLinguisticTagSchemeLexicalClass", optional=true)
    public static native NSString TagSchemeLexicalClass();
    @GlobalValue(symbol="NSLinguisticTagSchemeNameType", optional=true)
    public static native NSString TagSchemeNameType();
    @GlobalValue(symbol="NSLinguisticTagSchemeNameTypeOrLexicalClass", optional=true)
    public static native NSString TagSchemeNameTypeOrLexicalClass();
    @GlobalValue(symbol="NSLinguisticTagSchemeLemma", optional=true)
    public static native NSString TagSchemeLemma();
    @GlobalValue(symbol="NSLinguisticTagSchemeLanguage", optional=true)
    public static native NSString TagSchemeLanguage();
    @GlobalValue(symbol="NSLinguisticTagSchemeScript", optional=true)
    public static native NSString TagSchemeScript();
    @GlobalValue(symbol="NSLinguisticTagWord", optional=true)
    public static native NSString TokenTypeWord();
    @GlobalValue(symbol="NSLinguisticTagPunctuation", optional=true)
    public static native NSString TokenTypePunctuation();
    @GlobalValue(symbol="NSLinguisticTagWhitespace", optional=true)
    public static native NSString TokenTypeWhitespace();
    @GlobalValue(symbol="NSLinguisticTagOther", optional=true)
    public static native NSString TokenTypeOther();
    @GlobalValue(symbol="NSLinguisticTagNoun", optional=true)
    public static native NSString LexicalClassNoun();
    @GlobalValue(symbol="NSLinguisticTagVerb", optional=true)
    public static native NSString LexicalClassVerb();
    @GlobalValue(symbol="NSLinguisticTagAdjective", optional=true)
    public static native NSString LexicalClassAdjective();
    @GlobalValue(symbol="NSLinguisticTagAdverb", optional=true)
    public static native NSString LexicalClassAdverb();
    @GlobalValue(symbol="NSLinguisticTagPronoun", optional=true)
    public static native NSString LexicalClassPronoun();
    @GlobalValue(symbol="NSLinguisticTagDeterminer", optional=true)
    public static native NSString LexicalClassDeterminer();
    @GlobalValue(symbol="NSLinguisticTagParticle", optional=true)
    public static native NSString LexicalClassParticle();
    @GlobalValue(symbol="NSLinguisticTagPreposition", optional=true)
    public static native NSString LexicalClassPreposition();
    @GlobalValue(symbol="NSLinguisticTagNumber", optional=true)
    public static native NSString LexicalClassNumber();
    @GlobalValue(symbol="NSLinguisticTagConjunction", optional=true)
    public static native NSString LexicalClassConjunction();
    @GlobalValue(symbol="NSLinguisticTagInterjection", optional=true)
    public static native NSString LexicalClassInterjection();
    @GlobalValue(symbol="NSLinguisticTagClassifier", optional=true)
    public static native NSString LexicalClassClassifier();
    @GlobalValue(symbol="NSLinguisticTagIdiom", optional=true)
    public static native NSString LexicalClassIdiom();
    @GlobalValue(symbol="NSLinguisticTagOtherWord", optional=true)
    public static native NSString LexicalClassOtherWord();
    @GlobalValue(symbol="NSLinguisticTagSentenceTerminator", optional=true)
    public static native NSString LexicalClassSentenceTerminator();
    @GlobalValue(symbol="NSLinguisticTagOpenQuote", optional=true)
    public static native NSString LexicalClassOpenQuote();
    @GlobalValue(symbol="NSLinguisticTagCloseQuote", optional=true)
    public static native NSString LexicalClassCloseQuote();
    @GlobalValue(symbol="NSLinguisticTagOpenParenthesis", optional=true)
    public static native NSString LexicalClassOpenParenthesis();
    @GlobalValue(symbol="NSLinguisticTagCloseParenthesis", optional=true)
    public static native NSString LexicalClassCloseParenthesis();
    @GlobalValue(symbol="NSLinguisticTagWordJoiner", optional=true)
    public static native NSString LexicalClassWordJoiner();
    @GlobalValue(symbol="NSLinguisticTagDash", optional=true)
    public static native NSString LexicalClassDash();
    @GlobalValue(symbol="NSLinguisticTagOtherPunctuation", optional=true)
    public static native NSString LexicalClassOtherPunctuation();
    @GlobalValue(symbol="NSLinguisticTagParagraphBreak", optional=true)
    public static native NSString LexicalClassParagraphBreak();
    @GlobalValue(symbol="NSLinguisticTagOtherWhitespace", optional=true)
    public static native NSString LexicalClassOtherWhitespace();
    @GlobalValue(symbol="NSLinguisticTagPersonalName", optional=true)
    public static native NSString NameTypePersonal();
    @GlobalValue(symbol="NSLinguisticTagPlaceName", optional=true)
    public static native NSString NameTypePlace();
    @GlobalValue(symbol="NSLinguisticTagOrganizationName", optional=true)
    public static native NSString NameTypeOrganization();
    
    @Method(selector = "initWithTagSchemes:options:")
    protected native @Pointer long initWithTagSchemes$options$(NSArray<?> tagSchemes, @MachineSizedUInt long opts);
    @Method(selector = "tagSchemes")
    public native NSArray<?> tagSchemes();
    @Method(selector = "setString:")
    public native void setString(String string);
    @Method(selector = "string")
    public native String string();
    @Method(selector = "setOrthography:range:")
    public native void setOrthography$range$(NSOrthography orthography, @ByVal NSRange range);
    @Method(selector = "orthographyAtIndex:effectiveRange:")
    public native NSOrthography orthographyAtIndex$effectiveRange$(@MachineSizedUInt long charIndex, NSRange effectiveRange);
    @Method(selector = "stringEditedInRange:changeInLength:")
    public native void stringEditedInRange$changeInLength$(@ByVal NSRange newRange, @MachineSizedSInt long delta);
    @Method(selector = "enumerateTagsInRange:scheme:options:usingBlock:")
    public native void enumerateTagsInRange$scheme$options$usingBlock$(@ByVal NSRange range, String tagScheme, NSLinguisticTaggerOptions opts, ObjCBlock block);
    @Method(selector = "sentenceRangeForRange:")
    public native @ByVal NSRange sentenceRangeForRange$(@ByVal NSRange range);
    @Method(selector = "tagAtIndex:scheme:tokenRange:sentenceRange:")
    public native String tagAtIndex$scheme$tokenRange$sentenceRange$(@MachineSizedUInt long charIndex, String tagScheme, NSRange tokenRange, NSRange sentenceRange);
    @Method(selector = "tagsInRange:scheme:options:tokenRanges:")
    public native NSArray<?> tagsInRange$scheme$options$tokenRanges$(@ByVal NSRange range, String tagScheme, NSLinguisticTaggerOptions opts, NSArray.NSArrayPtr<?> tokenRanges);
    @Method(selector = "possibleTagsAtIndex:scheme:tokenRange:sentenceRange:scores:")
    public native NSArray<?> possibleTagsAtIndex$scheme$tokenRange$sentenceRange$scores$(@MachineSizedUInt long charIndex, String tagScheme, NSRange tokenRange, NSRange sentenceRange, NSArray.NSArrayPtr<?> scores);
    @Method(selector = "availableTagSchemesForLanguage:")
    public static native NSArray<?> availableTagSchemesForLanguage$(String language);
    /*</methods>*/
}
