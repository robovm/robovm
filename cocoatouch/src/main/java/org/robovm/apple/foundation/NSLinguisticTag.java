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
import org.robovm.apple.coregraphics.*;
import org.robovm.apple.coremedia.*;
import org.robovm.apple.security.*;
import org.robovm.apple.dispatch.*;
/*</imports>*/

/*<javadoc>*/
/*</javadoc>*/
@Marshaler(NSLinguisticTag.Marshaler.class)
/*<annotations>*/@Library("Foundation")/*</annotations>*/
/*<visibility>*/public/*</visibility>*/ class /*<name>*/NSLinguisticTag/*</name>*/ 
    extends /*<extends>*/Object/*</extends>*/ 
    /*<implements>*//*</implements>*/ {

    public static class Marshaler {
        @MarshalsPointer
        public static NSLinguisticTag toObject(Class<NSLinguisticTag> cls, long handle, long flags) {
            NSString o = (NSString) NSObject.Marshaler.toObject(NSString.class, handle, flags);
            if (o == null) {
                return null;
            }
            return NSLinguisticTag.valueOf(o);
        }
        @MarshalsPointer
        public static long toNative(NSLinguisticTag o, long flags) {
            if (o == null) {
                return 0L;
            }
            return NSObject.Marshaler.toNative(o.value(), flags);
        }
    }
    
    /*<ptr>*/
    /*</ptr>*/
    /*<bind>*/static { Bro.bind(NSLinguisticTag.class); }/*</bind>*/
    /*<constants>*//*</constants>*/
    /**
     * @since Available in iOS 5.0 and later.
     */
    public static final NSLinguisticTag Word = new NSLinguisticTag("WordValue");
    /**
     * @since Available in iOS 5.0 and later.
     */
    public static final NSLinguisticTag Punctuation = new NSLinguisticTag("PunctuationValue");
    /**
     * @since Available in iOS 5.0 and later.
     */
    public static final NSLinguisticTag Whitespace = new NSLinguisticTag("WhitespaceValue");
    /**
     * @since Available in iOS 5.0 and later.
     */
    public static final NSLinguisticTag Other = new NSLinguisticTag("OtherValue");
    /**
     * @since Available in iOS 5.0 and later.
     */
    public static final NSLinguisticTag Noun = new NSLinguisticTag("NounValue");
    /**
     * @since Available in iOS 5.0 and later.
     */
    public static final NSLinguisticTag Verb = new NSLinguisticTag("VerbValue");
    /**
     * @since Available in iOS 5.0 and later.
     */
    public static final NSLinguisticTag Adjective = new NSLinguisticTag("AdjectiveValue");
    /**
     * @since Available in iOS 5.0 and later.
     */
    public static final NSLinguisticTag Adverb = new NSLinguisticTag("AdverbValue");
    /**
     * @since Available in iOS 5.0 and later.
     */
    public static final NSLinguisticTag Pronoun = new NSLinguisticTag("PronounValue");
    /**
     * @since Available in iOS 5.0 and later.
     */
    public static final NSLinguisticTag Determiner = new NSLinguisticTag("DeterminerValue");
    /**
     * @since Available in iOS 5.0 and later.
     */
    public static final NSLinguisticTag Particle = new NSLinguisticTag("ParticleValue");
    /**
     * @since Available in iOS 5.0 and later.
     */
    public static final NSLinguisticTag Preposition = new NSLinguisticTag("PrepositionValue");
    /**
     * @since Available in iOS 5.0 and later.
     */
    public static final NSLinguisticTag Number = new NSLinguisticTag("NumberValue");
    /**
     * @since Available in iOS 5.0 and later.
     */
    public static final NSLinguisticTag Conjunction = new NSLinguisticTag("ConjunctionValue");
    /**
     * @since Available in iOS 5.0 and later.
     */
    public static final NSLinguisticTag Interjection = new NSLinguisticTag("InterjectionValue");
    /**
     * @since Available in iOS 5.0 and later.
     */
    public static final NSLinguisticTag Classifier = new NSLinguisticTag("ClassifierValue");
    /**
     * @since Available in iOS 5.0 and later.
     */
    public static final NSLinguisticTag Idiom = new NSLinguisticTag("IdiomValue");
    /**
     * @since Available in iOS 5.0 and later.
     */
    public static final NSLinguisticTag OtherWord = new NSLinguisticTag("OtherWordValue");
    /**
     * @since Available in iOS 5.0 and later.
     */
    public static final NSLinguisticTag SentenceTerminator = new NSLinguisticTag("SentenceTerminatorValue");
    /**
     * @since Available in iOS 5.0 and later.
     */
    public static final NSLinguisticTag OpenQuote = new NSLinguisticTag("OpenQuoteValue");
    /**
     * @since Available in iOS 5.0 and later.
     */
    public static final NSLinguisticTag CloseQuote = new NSLinguisticTag("CloseQuoteValue");
    /**
     * @since Available in iOS 5.0 and later.
     */
    public static final NSLinguisticTag OpenParenthesis = new NSLinguisticTag("OpenParenthesisValue");
    /**
     * @since Available in iOS 5.0 and later.
     */
    public static final NSLinguisticTag CloseParenthesis = new NSLinguisticTag("CloseParenthesisValue");
    /**
     * @since Available in iOS 5.0 and later.
     */
    public static final NSLinguisticTag WordJoiner = new NSLinguisticTag("WordJoinerValue");
    /**
     * @since Available in iOS 5.0 and later.
     */
    public static final NSLinguisticTag Dash = new NSLinguisticTag("DashValue");
    /**
     * @since Available in iOS 5.0 and later.
     */
    public static final NSLinguisticTag OtherPunctuation = new NSLinguisticTag("OtherPunctuationValue");
    /**
     * @since Available in iOS 5.0 and later.
     */
    public static final NSLinguisticTag ParagraphBreak = new NSLinguisticTag("ParagraphBreakValue");
    /**
     * @since Available in iOS 5.0 and later.
     */
    public static final NSLinguisticTag OtherWhitespace = new NSLinguisticTag("OtherWhitespaceValue");    
    /**
     * @since Available in iOS 5.0 and later.
     */
    public static final NSLinguisticTag PersonalName = new NSLinguisticTag("PersonalNameValue");
    /**
     * @since Available in iOS 5.0 and later.
     */
    public static final NSLinguisticTag PlaceName = new NSLinguisticTag("PlaceNameValue");
    /**
     * @since Available in iOS 5.0 and later.
     */
    public static final NSLinguisticTag OrganizationName = new NSLinguisticTag("OrganizationNameValue");
    
    private static NSLinguisticTag[] values = new NSLinguisticTag[] {Word, Punctuation, Whitespace, Other, Noun, Verb, Adjective, Adverb, Pronoun, 
        Determiner, Particle, Preposition, Number, Conjunction, Interjection, Classifier, Idiom, OtherWord, SentenceTerminator, OpenQuote, CloseQuote, 
        OpenParenthesis, CloseParenthesis, WordJoiner, Dash, OtherPunctuation, ParagraphBreak, OtherWhitespace, PersonalName, PlaceName, OrganizationName};
    private final LazyGlobalValue<NSString> lazyGlobalValue;
    
    private NSLinguisticTag(String getterName) {
        lazyGlobalValue = new LazyGlobalValue<>(getClass(), getterName);
    }
    /*<constructors>*//*</constructors>*/
    /*<properties>*//*</properties>*/
    /*<members>*//*</members>*/
    public NSString value() {
        return lazyGlobalValue.value();
    }
    
    public static NSLinguisticTag valueOf(NSString value) {
        for (NSLinguisticTag v : values) {
            if (v.value().equals(value)) {
                return v;
            }
        }
        throw new IllegalArgumentException("No constant with value " + value + " found in " 
            + /*<name>*/NSLinguisticTag/*</name>*/.class.getName());
    }
    /*<methods>*/
    /**
     * @since Available in iOS 5.0 and later.
     */
    @GlobalValue(symbol="NSLinguisticTagWord", optional=true)
    protected static native NSString WordValue();
    /**
     * @since Available in iOS 5.0 and later.
     */
    @GlobalValue(symbol="NSLinguisticTagPunctuation", optional=true)
    protected static native NSString PunctuationValue();
    /**
     * @since Available in iOS 5.0 and later.
     */
    @GlobalValue(symbol="NSLinguisticTagWhitespace", optional=true)
    protected static native NSString WhitespaceValue();
    /**
     * @since Available in iOS 5.0 and later.
     */
    @GlobalValue(symbol="NSLinguisticTagOther", optional=true)
    protected static native NSString OtherValue();
    /**
     * @since Available in iOS 5.0 and later.
     */
    @GlobalValue(symbol="NSLinguisticTagNoun", optional=true)
    protected static native NSString NounValue();
    /**
     * @since Available in iOS 5.0 and later.
     */
    @GlobalValue(symbol="NSLinguisticTagVerb", optional=true)
    protected static native NSString VerbValue();
    /**
     * @since Available in iOS 5.0 and later.
     */
    @GlobalValue(symbol="NSLinguisticTagAdjective", optional=true)
    protected static native NSString AdjectiveValue();
    /**
     * @since Available in iOS 5.0 and later.
     */
    @GlobalValue(symbol="NSLinguisticTagAdverb", optional=true)
    protected static native NSString AdverbValue();
    /**
     * @since Available in iOS 5.0 and later.
     */
    @GlobalValue(symbol="NSLinguisticTagPronoun", optional=true)
    protected static native NSString PronounValue();
    /**
     * @since Available in iOS 5.0 and later.
     */
    @GlobalValue(symbol="NSLinguisticTagDeterminer", optional=true)
    protected static native NSString DeterminerValue();
    /**
     * @since Available in iOS 5.0 and later.
     */
    @GlobalValue(symbol="NSLinguisticTagParticle", optional=true)
    protected static native NSString ParticleValue();
    /**
     * @since Available in iOS 5.0 and later.
     */
    @GlobalValue(symbol="NSLinguisticTagPreposition", optional=true)
    protected static native NSString PrepositionValue();
    /**
     * @since Available in iOS 5.0 and later.
     */
    @GlobalValue(symbol="NSLinguisticTagNumber", optional=true)
    protected static native NSString NumberValue();
    /**
     * @since Available in iOS 5.0 and later.
     */
    @GlobalValue(symbol="NSLinguisticTagConjunction", optional=true)
    protected static native NSString ConjunctionValue();
    /**
     * @since Available in iOS 5.0 and later.
     */
    @GlobalValue(symbol="NSLinguisticTagInterjection", optional=true)
    protected static native NSString InterjectionValue();
    /**
     * @since Available in iOS 5.0 and later.
     */
    @GlobalValue(symbol="NSLinguisticTagClassifier", optional=true)
    protected static native NSString ClassifierValue();
    /**
     * @since Available in iOS 5.0 and later.
     */
    @GlobalValue(symbol="NSLinguisticTagIdiom", optional=true)
    protected static native NSString IdiomValue();
    /**
     * @since Available in iOS 5.0 and later.
     */
    @GlobalValue(symbol="NSLinguisticTagOtherWord", optional=true)
    protected static native NSString OtherWordValue();
    /**
     * @since Available in iOS 5.0 and later.
     */
    @GlobalValue(symbol="NSLinguisticTagSentenceTerminator", optional=true)
    protected static native NSString SentenceTerminatorValue();
    /**
     * @since Available in iOS 5.0 and later.
     */
    @GlobalValue(symbol="NSLinguisticTagOpenQuote", optional=true)
    protected static native NSString OpenQuoteValue();
    /**
     * @since Available in iOS 5.0 and later.
     */
    @GlobalValue(symbol="NSLinguisticTagCloseQuote", optional=true)
    protected static native NSString CloseQuoteValue();
    /**
     * @since Available in iOS 5.0 and later.
     */
    @GlobalValue(symbol="NSLinguisticTagOpenParenthesis", optional=true)
    protected static native NSString OpenParenthesisValue();
    /**
     * @since Available in iOS 5.0 and later.
     */
    @GlobalValue(symbol="NSLinguisticTagCloseParenthesis", optional=true)
    protected static native NSString CloseParenthesisValue();
    /**
     * @since Available in iOS 5.0 and later.
     */
    @GlobalValue(symbol="NSLinguisticTagWordJoiner", optional=true)
    protected static native NSString WordJoinerValue();
    /**
     * @since Available in iOS 5.0 and later.
     */
    @GlobalValue(symbol="NSLinguisticTagDash", optional=true)
    protected static native NSString DashValue();
    /**
     * @since Available in iOS 5.0 and later.
     */
    @GlobalValue(symbol="NSLinguisticTagOtherPunctuation", optional=true)
    protected static native NSString OtherPunctuationValue();
    /**
     * @since Available in iOS 5.0 and later.
     */
    @GlobalValue(symbol="NSLinguisticTagParagraphBreak", optional=true)
    protected static native NSString ParagraphBreakValue();
    /**
     * @since Available in iOS 5.0 and later.
     */
    @GlobalValue(symbol="NSLinguisticTagOtherWhitespace", optional=true)
    protected static native NSString OtherWhitespaceValue();
    /**
     * @since Available in iOS 5.0 and later.
     */
    @GlobalValue(symbol="NSLinguisticTagPersonalName", optional=true)
    protected static native NSString PersonalNameValue();
    /**
     * @since Available in iOS 5.0 and later.
     */
    @GlobalValue(symbol="NSLinguisticTagPlaceName", optional=true)
    protected static native NSString PlaceNameValue();
    /**
     * @since Available in iOS 5.0 and later.
     */
    @GlobalValue(symbol="NSLinguisticTagOrganizationName", optional=true)
    protected static native NSString OrganizationNameValue();
    /*</methods>*/
}
