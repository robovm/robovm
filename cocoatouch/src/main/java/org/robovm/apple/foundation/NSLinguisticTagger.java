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

/*<javadoc>*/
/**
 * @since Available in iOS 5.0 and later.
 */
/*</javadoc>*/
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
    /**
     * @since Available in iOS 5.0 and later.
     */
    public NSLinguisticTagger(NSArray<?> tagSchemes, @MachineSizedUInt long opts) { super((SkipInit) null); initObject(initWithTagSchemes$options$(tagSchemes, opts)); }
    /*</constructors>*/
    /*<properties>*/
    
    /*</properties>*/
    /*<members>*//*</members>*/
    /*<methods>*/
    /**
     * @since Available in iOS 5.0 and later.
     */
    @Method(selector = "initWithTagSchemes:options:")
    protected native @Pointer long initWithTagSchemes$options$(NSArray<?> tagSchemes, @MachineSizedUInt long opts);
    /**
     * @since Available in iOS 5.0 and later.
     */
    @Method(selector = "tagSchemes")
    public native NSArray<?> tagSchemes();
    /**
     * @since Available in iOS 5.0 and later.
     */
    @Method(selector = "setString:")
    public native void setString(String string);
    /**
     * @since Available in iOS 5.0 and later.
     */
    @Method(selector = "string")
    public native String string();
    /**
     * @since Available in iOS 5.0 and later.
     */
    @Method(selector = "setOrthography:range:")
    public native void setOrthography$range$(NSOrthography orthography, @ByVal NSRange range);
    /**
     * @since Available in iOS 5.0 and later.
     */
    @Method(selector = "orthographyAtIndex:effectiveRange:")
    public native NSOrthography orthographyAtIndex$effectiveRange$(@MachineSizedUInt long charIndex, NSRange effectiveRange);
    /**
     * @since Available in iOS 5.0 and later.
     */
    @Method(selector = "stringEditedInRange:changeInLength:")
    public native void stringEditedInRange$changeInLength$(@ByVal NSRange newRange, @MachineSizedSInt long delta);
    /**
     * @since Available in iOS 5.0 and later.
     */
    @Method(selector = "enumerateTagsInRange:scheme:options:usingBlock:")
    public native void enumerateTagsInRange$scheme$options$usingBlock$(@ByVal NSRange range, String tagScheme, NSLinguisticTaggerOptions opts, ObjCBlock block);
    /**
     * @since Available in iOS 5.0 and later.
     */
    @Method(selector = "sentenceRangeForRange:")
    public native @ByVal NSRange sentenceRangeForRange$(@ByVal NSRange range);
    /**
     * @since Available in iOS 5.0 and later.
     */
    @Method(selector = "tagAtIndex:scheme:tokenRange:sentenceRange:")
    public native String tagAtIndex$scheme$tokenRange$sentenceRange$(@MachineSizedUInt long charIndex, String tagScheme, NSRange tokenRange, NSRange sentenceRange);
    /**
     * @since Available in iOS 5.0 and later.
     */
    @Method(selector = "tagsInRange:scheme:options:tokenRanges:")
    public native NSArray<?> tagsInRange$scheme$options$tokenRanges$(@ByVal NSRange range, String tagScheme, NSLinguisticTaggerOptions opts, NSArray.NSArrayPtr<?> tokenRanges);
    /**
     * @since Available in iOS 5.0 and later.
     */
    @Method(selector = "possibleTagsAtIndex:scheme:tokenRange:sentenceRange:scores:")
    public native NSArray<?> possibleTagsAtIndex$scheme$tokenRange$sentenceRange$scores$(@MachineSizedUInt long charIndex, String tagScheme, NSRange tokenRange, NSRange sentenceRange, NSArray.NSArrayPtr<?> scores);
    /**
     * @since Available in iOS 5.0 and later.
     */
    @Method(selector = "availableTagSchemesForLanguage:")
    public static native NSArray<?> availableTagSchemesForLanguage$(String language);
    /*</methods>*/
}
