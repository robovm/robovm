/*
 * Copyright (C) 2014 Trillian AB
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
    @GlobalValue(symbol="NSLinguisticTagSchemeTokenType")
    public static native NSString TagSchemeTokenType();
    @GlobalValue(symbol="NSLinguisticTagSchemeLexicalClass")
    public static native NSString TagSchemeLexicalClass();
    @GlobalValue(symbol="NSLinguisticTagSchemeNameType")
    public static native NSString TagSchemeNameType();
    @GlobalValue(symbol="NSLinguisticTagSchemeNameTypeOrLexicalClass")
    public static native NSString TagSchemeNameTypeOrLexicalClass();
    @GlobalValue(symbol="NSLinguisticTagSchemeLemma")
    public static native NSString TagSchemeLemma();
    @GlobalValue(symbol="NSLinguisticTagSchemeLanguage")
    public static native NSString TagSchemeLanguage();
    @GlobalValue(symbol="NSLinguisticTagSchemeScript")
    public static native NSString TagSchemeScript();
    @GlobalValue(symbol="NSLinguisticTagWord")
    public static native NSString TokenTypeWord();
    @GlobalValue(symbol="NSLinguisticTagPunctuation")
    public static native NSString TokenTypePunctuation();
    @GlobalValue(symbol="NSLinguisticTagWhitespace")
    public static native NSString TokenTypeWhitespace();
    @GlobalValue(symbol="NSLinguisticTagOther")
    public static native NSString TokenTypeOther();
    @GlobalValue(symbol="NSLinguisticTagNoun")
    public static native NSString LexicalClassNoun();
    @GlobalValue(symbol="NSLinguisticTagVerb")
    public static native NSString LexicalClassVerb();
    @GlobalValue(symbol="NSLinguisticTagAdjective")
    public static native NSString LexicalClassAdjective();
    @GlobalValue(symbol="NSLinguisticTagAdverb")
    public static native NSString LexicalClassAdverb();
    @GlobalValue(symbol="NSLinguisticTagPronoun")
    public static native NSString LexicalClassPronoun();
    @GlobalValue(symbol="NSLinguisticTagDeterminer")
    public static native NSString LexicalClassDeterminer();
    @GlobalValue(symbol="NSLinguisticTagParticle")
    public static native NSString LexicalClassParticle();
    @GlobalValue(symbol="NSLinguisticTagPreposition")
    public static native NSString LexicalClassPreposition();
    @GlobalValue(symbol="NSLinguisticTagNumber")
    public static native NSString LexicalClassNumber();
    @GlobalValue(symbol="NSLinguisticTagConjunction")
    public static native NSString LexicalClassConjunction();
    @GlobalValue(symbol="NSLinguisticTagInterjection")
    public static native NSString LexicalClassInterjection();
    @GlobalValue(symbol="NSLinguisticTagClassifier")
    public static native NSString LexicalClassClassifier();
    @GlobalValue(symbol="NSLinguisticTagIdiom")
    public static native NSString LexicalClassIdiom();
    @GlobalValue(symbol="NSLinguisticTagOtherWord")
    public static native NSString LexicalClassOtherWord();
    @GlobalValue(symbol="NSLinguisticTagSentenceTerminator")
    public static native NSString LexicalClassSentenceTerminator();
    @GlobalValue(symbol="NSLinguisticTagOpenQuote")
    public static native NSString LexicalClassOpenQuote();
    @GlobalValue(symbol="NSLinguisticTagCloseQuote")
    public static native NSString LexicalClassCloseQuote();
    @GlobalValue(symbol="NSLinguisticTagOpenParenthesis")
    public static native NSString LexicalClassOpenParenthesis();
    @GlobalValue(symbol="NSLinguisticTagCloseParenthesis")
    public static native NSString LexicalClassCloseParenthesis();
    @GlobalValue(symbol="NSLinguisticTagWordJoiner")
    public static native NSString LexicalClassWordJoiner();
    @GlobalValue(symbol="NSLinguisticTagDash")
    public static native NSString LexicalClassDash();
    @GlobalValue(symbol="NSLinguisticTagOtherPunctuation")
    public static native NSString LexicalClassOtherPunctuation();
    @GlobalValue(symbol="NSLinguisticTagParagraphBreak")
    public static native NSString LexicalClassParagraphBreak();
    @GlobalValue(symbol="NSLinguisticTagOtherWhitespace")
    public static native NSString LexicalClassOtherWhitespace();
    @GlobalValue(symbol="NSLinguisticTagPersonalName")
    public static native NSString NameTypePersonal();
    @GlobalValue(symbol="NSLinguisticTagPlaceName")
    public static native NSString NameTypePlace();
    @GlobalValue(symbol="NSLinguisticTagOrganizationName")
    public static native NSString NameTypeOrganization();
    
    @Method(selector = "initWithTagSchemes:options:")
    protected native @Pointer long initWithTagSchemes$options$(NSArray<?> tagSchemes, @MachineSizedUInt long opts);
    @Method(selector = "tagSchemes")
    public native NSArray<?> tagSchemes();
    @Method(selector = "setString:")
    public native void setString$(String string);
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
