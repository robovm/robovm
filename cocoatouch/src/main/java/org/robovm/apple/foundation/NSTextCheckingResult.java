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
import org.robovm.apple.coregraphics.*;
import org.robovm.apple.coremedia.*;
import org.robovm.apple.security.*;
/*</imports>*/

/*<javadoc>*/
/**
 * @since Available in iOS 4.0 and later.
 */
/*</javadoc>*/
/*<annotations>*/@Library("Foundation") @NativeClass/*</annotations>*/
/*<visibility>*/public/*</visibility>*/ class /*<name>*/NSTextCheckingResult/*</name>*/ 
    extends /*<extends>*/NSObject/*</extends>*/ 
    /*<implements>*/implements NSCoding/*</implements>*/ {

    /*<ptr>*/public static class NSTextCheckingResultPtr extends Ptr<NSTextCheckingResult, NSTextCheckingResultPtr> {}/*</ptr>*/
    /*<bind>*/static { ObjCRuntime.bind(NSTextCheckingResult.class); }/*</bind>*/
    /*<constants>*//*</constants>*/
    /*<constructors>*/
    public NSTextCheckingResult() {}
    protected NSTextCheckingResult(SkipInit skipInit) { super(skipInit); }
    /*</constructors>*/
    /*<properties>*/
    @Property(selector = "resultType")
    public native NSTextCheckingType getResultType();
    @Property(selector = "range")
    public native @ByVal NSRange getRange();
    @Property(selector = "orthography")
    public native NSOrthography getOrthography();
    @Property(selector = "grammarDetails")
    public native NSArray<?> getGrammarDetails();
    @Property(selector = "date")
    public native NSDate getDate();
    @Property(selector = "timeZone")
    public native NSTimeZone getTimeZone();
    @Property(selector = "duration")
    public native double getDuration();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @Property(selector = "components")
    public native NSDictionary<?, ?> getComponents();
    @Property(selector = "URL")
    public native NSURL getURL();
    @Property(selector = "replacementString")
    public native String getReplacementString();
    /**
     * @since Available in iOS 7.0 and later.
     */
    @Property(selector = "alternativeStrings")
    public native NSArray<?> getAlternativeStrings();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @Property(selector = "regularExpression")
    public native NSRegularExpression getRegularExpression();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @Property(selector = "phoneNumber")
    public native String getPhoneNumber();
    @Property(selector = "addressComponents")
    public native NSDictionary<?, ?> getAddressComponents();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @Property(selector = "numberOfRanges")
    public native @MachineSizedUInt long getNumberOfRanges();
    /*</properties>*/
    /*<members>*//*</members>*/
    /*<methods>*/
    /**
     * @since Available in iOS 4.0 and later.
     */
    @Method(selector = "rangeAtIndex:")
    public native @ByVal NSRange rangeAtIndex$(@MachineSizedUInt long idx);
    /**
     * @since Available in iOS 5.0 and later.
     */
    @Method(selector = "resultByAdjustingRangesWithOffset:")
    public native NSTextCheckingResult resultByAdjustingRangesWithOffset$(@MachineSizedSInt long offset);
    @Method(selector = "orthographyCheckingResultWithRange:orthography:")
    public static native NSTextCheckingResult orthographyCheckingResultWithRange$orthography$(@ByVal NSRange range, NSOrthography orthography);
    @Method(selector = "spellCheckingResultWithRange:")
    public static native NSTextCheckingResult spellCheckingResultWithRange$(@ByVal NSRange range);
    @Method(selector = "grammarCheckingResultWithRange:details:")
    public static native NSTextCheckingResult grammarCheckingResultWithRange$details$(@ByVal NSRange range, NSArray<?> details);
    @Method(selector = "dateCheckingResultWithRange:date:")
    public static native NSTextCheckingResult dateCheckingResultWithRange$date$(@ByVal NSRange range, NSDate date);
    @Method(selector = "dateCheckingResultWithRange:date:timeZone:duration:")
    public static native NSTextCheckingResult dateCheckingResultWithRange$date$timeZone$duration$(@ByVal NSRange range, NSDate date, NSTimeZone timeZone, double duration);
    @Method(selector = "addressCheckingResultWithRange:components:")
    public static native NSTextCheckingResult addressCheckingResultWithRange$components$(@ByVal NSRange range, NSDictionary<?, ?> components);
    @Method(selector = "linkCheckingResultWithRange:URL:")
    public static native NSTextCheckingResult linkCheckingResultWithRange$URL$(@ByVal NSRange range, NSURL url);
    @Method(selector = "quoteCheckingResultWithRange:replacementString:")
    public static native NSTextCheckingResult quoteCheckingResultWithRange$replacementString$(@ByVal NSRange range, String replacementString);
    @Method(selector = "dashCheckingResultWithRange:replacementString:")
    public static native NSTextCheckingResult dashCheckingResultWithRange$replacementString$(@ByVal NSRange range, String replacementString);
    @Method(selector = "replacementCheckingResultWithRange:replacementString:")
    public static native NSTextCheckingResult replacementCheckingResultWithRange$replacementString$(@ByVal NSRange range, String replacementString);
    @Method(selector = "correctionCheckingResultWithRange:replacementString:")
    public static native NSTextCheckingResult correctionCheckingResultWithRange$replacementString$(@ByVal NSRange range, String replacementString);
    /**
     * @since Available in iOS 7.0 and later.
     */
    @Method(selector = "correctionCheckingResultWithRange:replacementString:alternativeStrings:")
    public static native NSTextCheckingResult correctionCheckingResultWithRange$replacementString$alternativeStrings$(@ByVal NSRange range, String replacementString, NSArray<?> alternativeStrings);
    /**
     * @since Available in iOS 4.0 and later.
     */
    @Method(selector = "regularExpressionCheckingResultWithRanges:count:regularExpression:")
    public static native NSTextCheckingResult regularExpressionCheckingResultWithRanges$count$regularExpression$(NSRange ranges, @MachineSizedUInt long count, NSRegularExpression regularExpression);
    /**
     * @since Available in iOS 4.0 and later.
     */
    @Method(selector = "phoneNumberCheckingResultWithRange:phoneNumber:")
    public static native NSTextCheckingResult phoneNumberCheckingResultWithRange$phoneNumber$(@ByVal NSRange range, String phoneNumber);
    /**
     * @since Available in iOS 4.0 and later.
     */
    @Method(selector = "transitInformationCheckingResultWithRange:components:")
    public static native NSTextCheckingResult transitInformationCheckingResultWithRange$components$(@ByVal NSRange range, NSDictionary<?, ?> components);
    @Method(selector = "encodeWithCoder:")
    public native void encode(NSCoder aCoder);
    /*</methods>*/
}
