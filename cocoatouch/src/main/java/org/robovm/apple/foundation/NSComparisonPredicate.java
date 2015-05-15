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
    public NSComparisonPredicate(NSExpression lhs, NSExpression rhs, NSComparisonPredicateModifier modifier, NSPredicateOperatorType type, NSComparisonPredicateOptions options) { super((SkipInit) null); initObject(init(lhs, rhs, modifier, type, options)); }
    public NSComparisonPredicate(NSExpression lhs, NSExpression rhs, Selector selector) { super((SkipInit) null); initObject(init(lhs, rhs, selector)); }
    /*</constructors>*/
    /*<properties>*/
    @Property(selector = "predicateOperatorType")
    public native NSPredicateOperatorType getPredicateOperatorType();
    @Property(selector = "comparisonPredicateModifier")
    public native NSComparisonPredicateModifier getComparisonPredicateModifier();
    @Property(selector = "leftExpression")
    public native NSExpression getLeftExpression();
    @Property(selector = "rightExpression")
    public native NSExpression getRightExpression();
    @Property(selector = "customSelector")
    public native Selector getCustomSelector();
    @Property(selector = "options")
    public native NSComparisonPredicateOptions getOptions();
    /*</properties>*/
    /*<members>*//*</members>*/
    /*<methods>*/
    @Method(selector = "initWithLeftExpression:rightExpression:modifier:type:options:")
    protected native @Pointer long init(NSExpression lhs, NSExpression rhs, NSComparisonPredicateModifier modifier, NSPredicateOperatorType type, NSComparisonPredicateOptions options);
    @Method(selector = "initWithLeftExpression:rightExpression:customSelector:")
    protected native @Pointer long init(NSExpression lhs, NSExpression rhs, Selector selector);
    /*</methods>*/
}
