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
/*</imports>*/

/*<javadoc>*/
/**
 * @since Available in iOS 3.0 and later.
 */
/*</javadoc>*/
/*<annotations>*/@Library("Foundation") @NativeClass/*</annotations>*/
/*<visibility>*/public/*</visibility>*/ class /*<name>*/NSComparisonPredicate/*</name>*/ 
    extends /*<extends>*/NSPredicate/*</extends>*/ 
    /*<implements>*//*</implements>*/ {

    /*<ptr>*/public static class NSComparisonPredicatePtr extends Ptr<NSComparisonPredicate, NSComparisonPredicatePtr> {}/*</ptr>*/
    /*<bind>*/static { ObjCRuntime.bind(NSComparisonPredicate.class); }/*</bind>*/
    /*<constants>*//*</constants>*/
    /*<constructors>*/
    public NSComparisonPredicate() {}
    protected NSComparisonPredicate(SkipInit skipInit) { super(skipInit); }
    public NSComparisonPredicate(NSExpression lhs, NSExpression rhs, NSComparisonPredicateModifier modifier, NSPredicateOperatorType type, NSComparisonPredicateOptions options) { super((SkipInit) null); initObject(initWithLeftExpression$rightExpression$modifier$type$options$(lhs, rhs, modifier, type, options)); }
    public NSComparisonPredicate(NSExpression lhs, NSExpression rhs, Selector selector) { super((SkipInit) null); initObject(initWithLeftExpression$rightExpression$customSelector$(lhs, rhs, selector)); }
    /*</constructors>*/
    /*<properties>*/
    
    /*</properties>*/
    /*<members>*//*</members>*/
    /*<methods>*/
    @Method(selector = "initWithLeftExpression:rightExpression:modifier:type:options:")
    protected native @Pointer long initWithLeftExpression$rightExpression$modifier$type$options$(NSExpression lhs, NSExpression rhs, NSComparisonPredicateModifier modifier, NSPredicateOperatorType type, NSComparisonPredicateOptions options);
    @Method(selector = "initWithLeftExpression:rightExpression:customSelector:")
    protected native @Pointer long initWithLeftExpression$rightExpression$customSelector$(NSExpression lhs, NSExpression rhs, Selector selector);
    @Method(selector = "predicateOperatorType")
    public native NSPredicateOperatorType getPredicateOperatorType();
    @Method(selector = "comparisonPredicateModifier")
    public native NSComparisonPredicateModifier getComparisonPredicateModifier();
    @Method(selector = "leftExpression")
    public native NSExpression getLeftExpression();
    @Method(selector = "rightExpression")
    public native NSExpression getRightExpression();
    @Method(selector = "customSelector")
    public native Selector getCustomSelector();
    @Method(selector = "options")
    public native NSComparisonPredicateOptions getOptions();
    /*</methods>*/
}
