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
/*<visibility>*/public/*</visibility>*/ class /*<name>*/NSTextCheckingResult/*</name>*/ 
    extends /*<extends>*/NSObject/*</extends>*/ 
    /*<implements>*/implements NSCoding/*</implements>*/ {

    /*<ptr>*/public static class NSTextCheckingResultPtr extends Ptr<NSTextCheckingResult, NSTextCheckingResultPtr> {}/*</ptr>*/
    /*<bind>*/static { ObjCRuntime.bind(NSTextCheckingResult.class); }/*</bind>*/
    /*<constants>*//*</constants>*/
    /*<constructors>*/
    public NSTextCheckingResult() {}
    protected NSTextCheckingResult(SkipInit skipInit) { super(skipInit); }
    public NSTextCheckingResult(NSCoder aDecoder) { super((SkipInit) null); initObject(initWithCoder$(aDecoder)); }
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
    @Property(selector = "components")
    public native NSDictionary<?, ?> getComponents();
    @Property(selector = "URL")
    public native NSURL getURL();
    @Property(selector = "replacementString")
    public native String getReplacementString();
    @Property(selector = "alternativeStrings")
    public native NSArray<?> getAlternativeStrings();
    @Property(selector = "regularExpression")
    public native NSRegularExpression getRegularExpression();
    @Property(selector = "phoneNumber")
    public native String getPhoneNumber();
    @Property(selector = "addressComponents")
    public native NSDictionary<?, ?> getAddressComponents();
    @Property(selector = "numberOfRanges")
    public native @MachineSizedUInt long getNumberOfRanges();
    /*</properties>*/
    /*<members>*//*</members>*/
    /*<methods>*/
    @GlobalValue(symbol="NSTextCheckingNameKey")
    public static native NSString KeyName();
    @GlobalValue(symbol="NSTextCheckingJobTitleKey")
    public static native NSString KeyJobTitle();
    @GlobalValue(symbol="NSTextCheckingOrganizationKey")
    public static native NSString KeyOrganization();
    @GlobalValue(symbol="NSTextCheckingStreetKey")
    public static native NSString KeyStreet();
    @GlobalValue(symbol="NSTextCheckingCityKey")
    public static native NSString KeyCity();
    @GlobalValue(symbol="NSTextCheckingStateKey")
    public static native NSString KeyState();
    @GlobalValue(symbol="NSTextCheckingZIPKey")
    public static native NSString KeyZIP();
    @GlobalValue(symbol="NSTextCheckingCountryKey")
    public static native NSString KeyCountry();
    @GlobalValue(symbol="NSTextCheckingPhoneKey")
    public static native NSString KeyPhone();
    @GlobalValue(symbol="NSTextCheckingAirlineKey")
    public static native NSString KeyAirline();
    @GlobalValue(symbol="NSTextCheckingFlightKey")
    public static native NSString KeyFlight();
    
    @Method(selector = "rangeAtIndex:")
    public native @ByVal NSRange rangeAtIndex$(@MachineSizedUInt long idx);
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
    @Method(selector = "correctionCheckingResultWithRange:replacementString:alternativeStrings:")
    public static native NSTextCheckingResult correctionCheckingResultWithRange$replacementString$alternativeStrings$(@ByVal NSRange range, String replacementString, NSArray<?> alternativeStrings);
    @Method(selector = "regularExpressionCheckingResultWithRanges:count:regularExpression:")
    public static native NSTextCheckingResult regularExpressionCheckingResultWithRanges$count$regularExpression$(NSRange ranges, @MachineSizedUInt long count, NSRegularExpression regularExpression);
    @Method(selector = "phoneNumberCheckingResultWithRange:phoneNumber:")
    public static native NSTextCheckingResult phoneNumberCheckingResultWithRange$phoneNumber$(@ByVal NSRange range, String phoneNumber);
    @Method(selector = "transitInformationCheckingResultWithRange:components:")
    public static native NSTextCheckingResult transitInformationCheckingResultWithRange$components$(@ByVal NSRange range, NSDictionary<?, ?> components);
    @Method(selector = "encodeWithCoder:")
    public native void encodeWithCoder$(NSCoder aCoder);
    @Method(selector = "initWithCoder:")
    protected native @Pointer long initWithCoder$(NSCoder aDecoder);
    /*</methods>*/
}
