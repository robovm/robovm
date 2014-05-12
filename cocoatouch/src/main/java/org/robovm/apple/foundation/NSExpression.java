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
 * @since Available in iOS 3.0 and later.
 */
/*</javadoc>*/
/*<annotations>*/@Library("Foundation") @NativeClass/*</annotations>*/
/*<visibility>*/public/*</visibility>*/ class /*<name>*/NSExpression/*</name>*/ 
    extends /*<extends>*/NSObject/*</extends>*/ 
    /*<implements>*//*</implements>*/ {

    /*<ptr>*/public static class NSExpressionPtr extends Ptr<NSExpression, NSExpressionPtr> {}/*</ptr>*/
    /*<bind>*/static { ObjCRuntime.bind(NSExpression.class); }/*</bind>*/
    /*<constants>*//*</constants>*/
    /*<constructors>*/
    public NSExpression() {}
    protected NSExpression(SkipInit skipInit) { super(skipInit); }
    public NSExpression(NSExpressionType type) { super((SkipInit) null); initObject(initWithExpressionType$(type)); }
    /*</constructors>*/
    /*<properties>*/
    
    /*</properties>*/
    /*<members>*//*</members>*/
    /*<methods>*/
    @Method(selector = "initWithExpressionType:")
    protected native @Pointer long initWithExpressionType$(NSExpressionType type);
    @Method(selector = "expressionType")
    public native NSExpressionType expressionType();
    @Method(selector = "constantValue")
    public native NSObject constantValue();
    @Method(selector = "keyPath")
    public native String keyPath();
    @Method(selector = "function")
    public native String function();
    @Method(selector = "variable")
    public native String variable();
    @Method(selector = "operand")
    public native NSExpression operand();
    @Method(selector = "arguments")
    public native NSArray<?> arguments();
    /**
     * @since Available in iOS 3.0 and later.
     */
    @Method(selector = "collection")
    public native NSObject collection();
    /**
     * @since Available in iOS 3.0 and later.
     */
    @Method(selector = "predicate")
    public native NSPredicate predicate();
    /**
     * @since Available in iOS 3.0 and later.
     */
    @Method(selector = "leftExpression")
    public native NSExpression leftExpression();
    /**
     * @since Available in iOS 3.0 and later.
     */
    @Method(selector = "rightExpression")
    public native NSExpression rightExpression();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @Method(selector = "expressionBlock")
    public native ObjCBlock expressionBlock(NSObject p0, NSArray<?> p1, NSMutableDictionary<?, ?> p2);
    @Method(selector = "expressionValueWithObject:context:")
    public native NSObject expressionValueWithObject$context$(NSObject object, NSMutableDictionary<?, ?> context);
    /**
     * @since Available in iOS 7.0 and later.
     */
    @Method(selector = "allowEvaluation")
    public native void allowEvaluation();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @Method(selector = "expressionWithFormat:argumentArray:")
    public static native NSExpression expressionWithFormat$argumentArray$(String expressionFormat, NSArray<?> arguments);
    @Method(selector = "expressionForConstantValue:")
    public static native NSExpression expressionForConstantValue$(NSObject obj);
    @Method(selector = "expressionForEvaluatedObject")
    public static native NSExpression expressionForEvaluatedObject();
    @Method(selector = "expressionForVariable:")
    public static native NSExpression expressionForVariable$(String string);
    @Method(selector = "expressionForKeyPath:")
    public static native NSExpression expressionForKeyPath$(String keyPath);
    @Method(selector = "expressionForFunction:arguments:")
    public static native NSExpression expressionForFunction$arguments$(String name, NSArray<?> parameters);
    /**
     * @since Available in iOS 3.0 and later.
     */
    @Method(selector = "expressionForAggregate:")
    public static native NSExpression expressionForAggregate$(NSArray<?> subexpressions);
    /**
     * @since Available in iOS 3.0 and later.
     */
    @Method(selector = "expressionForUnionSet:with:")
    public static native NSExpression expressionForUnionSet$with$(NSExpression left, NSExpression right);
    /**
     * @since Available in iOS 3.0 and later.
     */
    @Method(selector = "expressionForIntersectSet:with:")
    public static native NSExpression expressionForIntersectSet$with$(NSExpression left, NSExpression right);
    /**
     * @since Available in iOS 3.0 and later.
     */
    @Method(selector = "expressionForMinusSet:with:")
    public static native NSExpression expressionForMinusSet$with$(NSExpression left, NSExpression right);
    /**
     * @since Available in iOS 3.0 and later.
     */
    @Method(selector = "expressionForSubquery:usingIteratorVariable:predicate:")
    public static native NSExpression expressionForSubquery$usingIteratorVariable$predicate$(NSExpression expression, String variable, NSObject predicate);
    /**
     * @since Available in iOS 3.0 and later.
     */
    @Method(selector = "expressionForFunction:selectorName:arguments:")
    public static native NSExpression expressionForFunction$selectorName$arguments$(NSExpression target, String name, NSArray<?> parameters);
    /**
     * @since Available in iOS 7.0 and later.
     */
    @Method(selector = "expressionForAnyKey")
    public static native NSExpression expressionForAnyKey();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @Method(selector = "expressionForBlock:arguments:")
    public static native NSExpression expressionForBlock$arguments$(ObjCBlock block, NSArray<?> arguments);
    /*</methods>*/
}
